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

        String previousUri = (String) httpServletRequest.getSession().getAttribute("previousUri");
        httpServletRequest.getSession().setAttribute("previousUri", uri);

        LOG.info("ulr : %s".formatted(previousUri));
        if (session == null && !uri.contains("/main")) {
            ((HttpServletResponse) response).sendRedirect(httpServletRequest.getServletContext().getContextPath() + "/main");
            return;
        }

        if (session != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
                if (uri.contains("logout") || uri.contains("settings") || uri.contains("addadvert")) {
                    httpServletResponse.sendRedirect(previousUri);
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (uri.contains("registration") || uri.contains("authorization")) {
                    httpServletResponse.sendRedirect(previousUri);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
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


