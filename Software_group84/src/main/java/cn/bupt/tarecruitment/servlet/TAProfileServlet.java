package cn.bupt.tarecruitment.servlet;

import cn.bupt.tarecruitment.context.AppContext;
import cn.bupt.tarecruitment.model.Profile;
import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.util.AppPaths;
import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@WebServlet("/ta/profile")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class TAProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        request.setAttribute("profile", AppContext.PROFILES.findByUserId(user.getId()));
        request.setAttribute("flash", WebUtils.consumeFlash(request));
        WebUtils.forward(request, response, "/WEB-INF/jsp/ta/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.requireRole(request, response, Role.TA);
        if (user == null) return;
        Profile old = AppContext.PROFILES.findByUserId(user.getId());
        String cvFileName = old.getCvFileName();
        String fullName = trim(request.getParameter("fullName"));
        String email = trim(request.getParameter("email"));
        String skills = trim(request.getParameter("skills"));
        if (fullName.isEmpty()) {
            request.setAttribute("error", "Full name is required.");
            request.setAttribute("profile", buildProfile(user.getId(), cvFileName, request));
            WebUtils.forward(request, response, "/WEB-INF/jsp/ta/profile.jsp");
            return;
        }
        if (!email.isEmpty() && !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            request.setAttribute("error", "Please enter a valid email address.");
            request.setAttribute("profile", buildProfile(user.getId(), cvFileName, request));
            WebUtils.forward(request, response, "/WEB-INF/jsp/ta/profile.jsp");
            return;
        }
        if (skills.isEmpty()) {
            request.setAttribute("error", "Please enter at least one skill so the matching feature can work.");
            request.setAttribute("profile", buildProfile(user.getId(), cvFileName, request));
            WebUtils.forward(request, response, "/WEB-INF/jsp/ta/profile.jsp");
            return;
        }
        Part cvPart = request.getPart("cv");
        if (cvPart != null && cvPart.getSize() > 0) {
            String original = WebUtils.cleanFileName(cvPart.getSubmittedFileName());
            String lower = original.toLowerCase(Locale.ROOT);
            if (!(lower.endsWith(".pdf") || lower.endsWith(".doc") || lower.endsWith(".docx") || lower.endsWith(".txt"))) {
                request.setAttribute("error", "CV must be a PDF, DOC, DOCX, or TXT file.");
                request.setAttribute("profile", buildProfile(user.getId(), cvFileName, request));
                WebUtils.forward(request, response, "/WEB-INF/jsp/ta/profile.jsp");
                return;
            }
            String stored = user.getId() + "_" + System.currentTimeMillis() + "_" + original;
            Files.createDirectories(AppPaths.uploadsDir());
            Path target = AppPaths.uploadsDir().resolve(stored).normalize();
            cvPart.write(target.toString());
            cvFileName = stored;
        }
        Profile profile = buildProfile(user.getId(), cvFileName, request);
        AppContext.PROFILES.save(profile);
        WebUtils.flash(request, "Profile saved. Skill matching has been updated.");
        response.sendRedirect(request.getContextPath() + "/ta/profile");
    }

    private Profile buildProfile(String userId, String cvFileName, HttpServletRequest request) {
        return new Profile(userId, trim(request.getParameter("fullName")), trim(request.getParameter("email")),
                trim(request.getParameter("major")), trim(request.getParameter("year")), trim(request.getParameter("availability")),
                trim(request.getParameter("skills")), trim(request.getParameter("experience")), trim(request.getParameter("introduction")), cvFileName);
    }

    private String trim(String value) { return value == null ? "" : value.trim(); }
}
