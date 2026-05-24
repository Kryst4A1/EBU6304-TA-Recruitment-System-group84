package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ta/job-detail")
public class TAJobDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        JobPost job = AppContext.JOBS.findById(request.getParameter("id"));
        if (job == null) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
        Profile profile = AppContext.PROFILES.findByUserId(user.getId());
        request.setAttribute("job", job);
        request.setAttribute("match", AppContext.MATCHING.match(profile, job));
        request.setAttribute("alreadyApplied", AppContext.APPLICATIONS.hasActiveApplication(job.getId(), user.getId()));
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/ta/job-detail.jsp");
    }
}
