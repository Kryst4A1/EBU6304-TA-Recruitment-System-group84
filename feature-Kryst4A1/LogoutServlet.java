package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        WebUtils.flash(request, "You have signed out.");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
