package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("")
public class LandingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = WebUtils.currentUser(request);
        response.sendRedirect(WebUtils.defaultPath(request, user));
    }
}
