package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/mo/jobs")
public class MOJobsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.MO);
        if (user == null) return;
        String action = request.getParameter("action");
        if ("new".equals(action) || "edit".equals(action)) {
            JobPost job = "edit".equals(action) ? AppContext.JOBS.findById(request.getParameter("id")) : new JobPost();
            if (job == null || (job.getId() != null && !job.getOwnerId().equals(user.getId()))) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); return;
            }
            request.setAttribute("job", job);
            request.setAttribute("flash", WebUtils.consumeFlash(request));
            WebUtils.forward(request, response, "/WEB-INF/jsp/mo/job-form.jsp");
            return;
        }
        request.setAttribute("jobs", AppContext.JOBS.listByOwner(user.getId()));
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/mo/jobs.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.MO);
        if (user == null) return;
        String action = request.getParameter("action");
        if ("close".equals(action)) {
            AppContext.JOBS.close(request.getParameter("jobId"), user.getId());
            WebUtils.flash(request, "Job closed. It will no longer appear in TA open jobs.");
            response.sendRedirect(request.getContextPath() + "/mo/jobs");
            return;
        }
        if ("reopen".equals(action)) {
            AppContext.JOBS.reopen(request.getParameter("jobId"), user.getId());
            WebUtils.flash(request, "Job reopened and is visible to TA applicants again.");
            response.sendRedirect(request.getContextPath() + "/mo/jobs");
            return;
        }

        String id = request.getParameter("jobId");
        JobPost old = (id == null || id.isBlank()) ? null : AppContext.JOBS.findById(id);
        if (old != null && !old.getOwnerId().equals(user.getId())) { response.sendError(HttpServletResponse.SC_FORBIDDEN); return; }

        int hours = WebUtils.parseInt(request.getParameter("hoursPerWeek"), -1);
        JobPost job = new JobPost(id, user.getId(), trim(request.getParameter("moduleCode")), trim(request.getParameter("title")),
                trim(request.getParameter("semester")), trim(request.getParameter("description")), trim(request.getParameter("requiredSkills")),
                hours, old == null ? "OPEN" : old.getStatus(), old == null ? null : old.getCreatedAt());

        String error = validate(job);
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("job", job);
            WebUtils.forward(request, response, "/WEB-INF/jsp/mo/job-form.jsp");
            return;
        }
        AppContext.JOBS.save(job);
        WebUtils.flash(request, "Job saved successfully.");
        response.sendRedirect(request.getContextPath() + "/mo/jobs");
    }

    private String validate(JobPost job) {
        if (blank(job.getModuleCode())) return "Module code is required.";
        if (blank(job.getTitle())) return "Job title is required.";
        if (blank(job.getSemester())) return "Semester is required.";
        if (blank(job.getDescription())) return "Description is required.";
        if (blank(job.getRequiredSkills())) return "At least one required skill is required.";
        if (job.getHoursPerWeek() <= 0 || job.getHoursPerWeek() > 20) return "Hours per week must be between 1 and 20.";
        return null;
    }

    private String trim(String value) { return value == null ? "" : value.trim(); }
    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
}
