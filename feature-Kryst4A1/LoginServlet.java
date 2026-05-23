package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = WebUtils.currentUser(request);
        if (current != null) {
            response.sendRedirect(WebUtils.defaultPath(request, current));
            return;
        }
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = AppContext.AUTH.login(username, password);
        if (user == null) {
            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("username", username);
            WebUtils.forward(request, response, "/WEB-INF/jsp/login.jsp");
            return;
        }
        request.getSession(true).setAttribute("currentUser", user);
        response.sendRedirect(WebUtils.defaultPath(request, user));
    }
}
