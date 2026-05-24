package cn.bupt.tarecruitment.filter;

import cn.bupt.tarecruitment.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (isPublic(path) || WebUtils.currentUser(request) != null) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

    private boolean isPublic(String path) {
        return "/".equals(path)
                || "/login".equals(path)
                || "/register".equals(path)
                || path.startsWith("/assets/")
                || path.startsWith("/favicon");
    }
}
