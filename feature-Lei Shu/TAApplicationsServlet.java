package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/ta/applications")
public class TAApplicationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        Map<String, JobPost> jobMap = AppContext.JOBS.listAll().stream().collect(Collectors.toMap(JobPost::getId, j -> j, (a, b) -> a));
        request.setAttribute("applications", AppContext.APPLICATIONS.listByTa(user.getId()));
        request.setAttribute("jobMap", jobMap);
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/ta/applications.jsp");
    }
}
