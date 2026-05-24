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

@WebServlet("/mo/applicants")
public class MOApplicantsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.MO);
        if (user == null) return;
        String jobId = request.getParameter("jobId");
        JobPost job = AppContext.JOBS.findById(jobId);
        if (job == null || !job.getOwnerId().equals(user.getId())) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
        Map<String, Profile> profileMap = AppContext.PROFILES.listAll().stream().collect(Collectors.toMap(Profile::getUserId, p -> p, (a, b) -> a));
        List<Application> apps = AppContext.APPLICATIONS.listByJob(jobId);
        Map<String, SkillMatch> matches = new HashMap<>();
        for (Application app : apps) matches.put(app.getId(), AppContext.MATCHING.match(profileMap.get(app.getTaUserId()), job));
        apps.sort((a, b) -> Integer.compare(matches.get(b.getId()).getScore(), matches.get(a.getId()).getScore()));
        request.setAttribute("job", job);
        request.setAttribute("applications", apps);
        request.setAttribute("profileMap", profileMap);
        request.setAttribute("matches", matches);
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/mo/applicants.jsp");
    }
}
