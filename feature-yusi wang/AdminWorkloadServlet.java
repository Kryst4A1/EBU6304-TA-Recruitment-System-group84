package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/workload")
public class AdminWorkloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.ADMIN);
        if (user == null) return;
        request.setAttribute("rows", AppContext.WORKLOAD.buildRows());
        request.setAttribute("recommendations", AppContext.WORKLOAD.recommendRebalancing());
        request.setAttribute("threshold", AppContext.WORKLOAD.DEFAULT_THRESHOLD_HOURS);
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/admin/workload.jsp");
    }
}
