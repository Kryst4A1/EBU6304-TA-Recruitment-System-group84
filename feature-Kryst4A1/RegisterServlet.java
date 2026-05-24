package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebUtils.forward(request, response, "/WEB-INF/jsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = trim(request.getParameter("username"));
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");
        String displayName = trim(request.getParameter("displayName"));
        String roleText = request.getParameter("role");
        if (blank(username) || blank(password)) {
            fail(request, response, "Username and password are required."); return;
        }
        if (!username.matches("[A-Za-z0-9._-]{3,30}")) {
            fail(request, response, "Username must be 3-30 characters and may contain letters, numbers, dots, underscores, or hyphens."); return;
        }
        if (password.length() < 3) {
            fail(request, response, "Password must contain at least 3 characters for the demo environment."); return;
        }
        if (!password.equals(confirm)) {
            fail(request, response, "The two passwords do not match."); return;
        }
        Role role;
        try {
            role = Role.valueOf(roleText == null ? "TA" : roleText.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            fail(request, response, "Please select a valid role."); return;
        }
        try {
            User user = AppContext.AUTH.registerUser(username, password, displayName, role);
            WebUtils.flash(request, "Registration completed for " + user.getUsername() + " (" + user.getRole() + "). Please sign in.");
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IllegalArgumentException ex) {
            fail(request, response, ex.getMessage());
        }
    }

    private void fail(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        request.setAttribute("error", error);
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("displayName", request.getParameter("displayName"));
        request.setAttribute("role", request.getParameter("role"));
        WebUtils.forward(request, response, "/WEB-INF/jsp/register.jsp");
    }

    private String trim(String value) { return value == null ? "" : value.trim(); }
    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
}
