package cn.bupt.tarecruitment.util;

import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class WebUtils {
    private WebUtils() {}

    public static User currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        Object user = session.getAttribute("currentUser");
        return user instanceof User ? (User) user : null;
    }

    public static User requireRole(HttpServletRequest request, HttpServletResponse response, Role role)
            throws IOException {
        User user = currentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return null;
        }
        if (user.getRole() != role) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return null;
        }
        return user;
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String jsp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    public static String defaultPath(HttpServletRequest request, User user) {
        if (user == null) return request.getContextPath() + "/login";
        switch (user.getRole()) {
            case TA: return request.getContextPath() + "/role-home";
            case MO: return request.getContextPath() + "/role-home";
            case ADMIN: return request.getContextPath() + "/role-home";
            default: return request.getContextPath() + "/login";
        }
    }

    public static void flash(HttpServletRequest request, String message) {
        request.getSession(true).setAttribute("flash", message);
    }

    public static String consumeFlash(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        Object value = session.getAttribute("flash");
        session.removeAttribute("flash");
        return value == null ? null : value.toString();
    }

    public static String html(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    public static String nvl(String value) {
        return value == null ? "" : value;
    }

    public static String statusClass(String status) {
        if (status == null) return "status";
        switch (status.trim().toUpperCase()) {
            case "OPEN": return "open";
            case "CLOSED": return "closed";
            case "SELECTED": return "selected";
            case "SHORTLISTED": return "shortlisted";
            case "REJECTED": return "rejected";
            case "SUBMITTED": return "submitted";
            default: return "status";
        }
    }

    public static String shortText(String value, int maxLength) {
        String text = nvl(value).replaceAll("\\s+", " ").trim();
        if (text.length() <= maxLength) return text;
        return text.substring(0, Math.max(0, maxLength - 1)) + "…";
    }

    public static String nl2br(String value) {
        return html(value).replace("\r\n", "\n").replace("\r", "\n").replace("\n", "<br>");
    }

    public static boolean containsIgnoreCase(String source, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        if (source == null) return false;
        return source.toLowerCase(java.util.Locale.ROOT).contains(query.trim().toLowerCase(java.util.Locale.ROOT));
    }

    public static int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return fallback;
        }
    }

    public static String cleanFileName(String fileName) {
        if (fileName == null) return "";
        String justName = fileName.replace("\\", "/");
        int slash = justName.lastIndexOf('/');
        if (slash >= 0) justName = justName.substring(slash + 1);
        return justName.replaceAll("[^A-Za-z0-9._-]", "_");
    }
}
