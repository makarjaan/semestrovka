package ru.kpfu.itis304.filter;

import ru.kpfu.itis304.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;


@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class AuthFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession(false);

        HttpSession currentSession = httpServletRequest.getSession(true);
        String previousUri = (String) currentSession.getAttribute("previousUri");
        currentSession.setAttribute("previousUri", uri);

        if (session == null && !uri.contains("/main")) {
            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + "/main");
            return;
        }

        boolean isAdminPage = uri.contains("/admin");

        boolean isAuthorizedAdmin = session != null &&
                "admin@mail.ru".equals(session.getAttribute("adminLogin")) &&
                isAdminInDatabase((String) session.getAttribute("adminLogin"));

        if (isAdminPage) {
            if (isAuthorizedAdmin) {
                chain.doFilter(request, response);
            } else {
                LOG.warning("Попытка доступа к странице администратора без прав.");
                httpServletResponse.sendRedirect(previousUri != null ? previousUri : httpServletRequest.getServletContext().getContextPath() + "/main");
            }
            return;
        }


        if (session != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
                if (uri.contains("logout") || uri.contains("settings") || uri.contains("addadvert") || uri.contains("favorites") || uri.contains("admin")) {
                    httpServletResponse.sendRedirect(previousUri != null ? previousUri : httpServletRequest.getServletContext().getContextPath() + "/main");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (uri.contains("registration") || uri.contains("authorization") || uri.contains("admin")) {
                    httpServletResponse.sendRedirect(previousUri != null ? previousUri : httpServletRequest.getServletContext().getContextPath() + "/main");
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isAdminInDatabase(String adminLogin) {
        return "admin@mail.ru".equals(adminLogin);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}


