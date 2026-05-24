package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/mo/applicant-review")
public class MOApplicantReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.MO);
        if (user == null) return;
        Application app = AppContext.APPLICATIONS.findById(request.getParameter("appId"));
        if (app == null) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
        JobPost job = AppContext.JOBS.findById(app.getJobId());
        if (job == null || !job.getOwnerId().equals(user.getId())) { response.sendError(HttpServletResponse.SC_FORBIDDEN); return; }
        Profile profile = AppContext.PROFILES.findByUserId(app.getTaUserId());
        request.setAttribute("application", app);
        request.setAttribute("job", job);
        request.setAttribute("profile", profile);
        request.setAttribute("match", AppContext.MATCHING.match(profile, job));
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/mo/applicant-detail.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.MO);
        if (user == null) return;
        Application app = AppContext.APPLICATIONS.findById(request.getParameter("appId"));
        if (app == null) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
        JobPost job = AppContext.JOBS.findById(app.getJobId());
        if (job == null || !job.getOwnerId().equals(user.getId())) { response.sendError(HttpServletResponse.SC_FORBIDDEN); return; }
        AppContext.APPLICATIONS.updateStatus(app.getId(), request.getParameter("status"));
        WebUtils.flash(request, "Application status updated.");
        response.sendRedirect(request.getContextPath() + "/mo/applicants?jobId=" + app.getJobId());
    }
}
