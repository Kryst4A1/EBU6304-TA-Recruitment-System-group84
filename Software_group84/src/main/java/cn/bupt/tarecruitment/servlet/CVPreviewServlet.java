package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.util.AppPaths;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/cv/preview")
public class CVPreviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (WebUtils.currentUser(request) == null) { response.sendRedirect(request.getContextPath() + "/login"); return; }
        String fileName = WebUtils.cleanFileName(request.getParameter("file"));
        Path file = AppPaths.uploadsDir().resolve(fileName).normalize();
        if (!file.startsWith(AppPaths.uploadsDir()) || !Files.exists(file)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); return;
        }
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        response.setContentType(getServletContext().getMimeType(fileName) == null ? "application/octet-stream" : getServletContext().getMimeType(fileName));
        Files.copy(file, response.getOutputStream());
    }
}
