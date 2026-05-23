package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ta/apply")
public class TAApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        String jobId = request.getParameter("jobId");
        String statement = request.getParameter("statement");
        if (statement == null || statement.trim().length() < 10) {
            WebUtils.flash(request, "Application statement should contain at least 10 characters.");
            response.sendRedirect(request.getContextPath() + "/ta/job-detail?id=" + jobId);
            return;
        }
        try {
            AppContext.APPLICATIONS.apply(jobId, user.getId(), statement);
            WebUtils.flash(request, "Application submitted. You can track the decision status here.");
            response.sendRedirect(request.getContextPath() + "/ta/applications");
        } catch (IllegalArgumentException ex) {
            WebUtils.flash(request, ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/ta/job-detail?id=" + jobId);
        }
    }
}
