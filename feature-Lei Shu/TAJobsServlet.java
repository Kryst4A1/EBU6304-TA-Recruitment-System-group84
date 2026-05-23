package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/ta/jobs")
public class TAJobsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        String query = request.getParameter("q");
        Profile profile = AppContext.PROFILES.findByUserId(user.getId());
        List<JobPost> jobs = AppContext.JOBS.listOpenJobs();
        if (query != null && !query.trim().isEmpty()) {
            jobs = jobs.stream()
                    .filter(job -> WebUtils.containsIgnoreCase(job.getModuleCode(), query)
                            || WebUtils.containsIgnoreCase(job.getTitle(), query)
                            || WebUtils.containsIgnoreCase(job.getRequiredSkills(), query)
                            || WebUtils.containsIgnoreCase(job.getDescription(), query)
                            || WebUtils.containsIgnoreCase(job.getSemester(), query))
                    .collect(Collectors.toList());
        }
        Map<String, SkillMatch> matches = new LinkedHashMap<>();
        for (JobPost job : jobs) matches.put(job.getId(), AppContext.MATCHING.match(profile, job));
        jobs.sort((a, b) -> Integer.compare(matches.get(b.getId()).getScore(), matches.get(a.getId()).getScore()));
        request.setAttribute("jobs", jobs);
        request.setAttribute("matches", matches);
        request.setAttribute("query", query == null ? "" : query);
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/ta/jobs.jsp");
    }
}
