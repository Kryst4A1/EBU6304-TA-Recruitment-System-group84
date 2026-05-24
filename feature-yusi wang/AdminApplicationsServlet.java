package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/admin/applications")
public class AdminApplicationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.ADMIN);
        if (user == null) return;

        List<Application> apps = AppContext.STORE.readApplications();
        Map<String, JobPost> jobMap = new HashMap<>();
        for (JobPost job : AppContext.STORE.readJobs()) jobMap.put(job.getId(), job);
        Map<String, Profile> profileMap = new HashMap<>();
        for (Profile p : AppContext.STORE.readProfiles()) profileMap.put(p.getUserId(), p);
        Map<String, User> userMap = new HashMap<>();
        for (User u : AppContext.STORE.readUsers()) userMap.put(u.getId(), u);

        String status = request.getParameter("status");
        String query = request.getParameter("q");
        if (status != null && !status.trim().isEmpty()) {
            apps = apps.stream().filter(a -> status.equalsIgnoreCase(a.getStatus())).collect(Collectors.toList());
        }
        if (query != null && !query.trim().isEmpty()) {
            apps = apps.stream().filter(a -> {
                JobPost job = jobMap.get(a.getJobId());
                Profile p = profileMap.get(a.getTaUserId());
                User mo = job == null ? null : userMap.get(job.getOwnerId());
                return WebUtils.containsIgnoreCase(a.getStatement(), query)
                        || WebUtils.containsIgnoreCase(p == null ? a.getTaUserId() : p.getFullName(), query)
                        || WebUtils.containsIgnoreCase(job == null ? a.getJobId() : job.getTitle(), query)
                        || WebUtils.containsIgnoreCase(job == null ? "" : job.getModuleCode(), query)
                        || WebUtils.containsIgnoreCase(mo == null ? "" : mo.getDisplayName(), query);
            }).collect(Collectors.toList());
        }

        request.setAttribute("applications", apps);
        request.setAttribute("jobMap", jobMap);
        request.setAttribute("profileMap", profileMap);
        request.setAttribute("userMap", userMap);
        request.setAttribute("status", status == null ? "" : status);
        request.setAttribute("query", query == null ? "" : query);
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/admin/applications.jsp");
    }
}
