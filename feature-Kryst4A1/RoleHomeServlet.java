package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/role-home")
public class RoleHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.currentUser(request);
        if (user == null) { response.sendRedirect(request.getContextPath() + "/login"); return; }
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/role-home.jsp");
    }
}
