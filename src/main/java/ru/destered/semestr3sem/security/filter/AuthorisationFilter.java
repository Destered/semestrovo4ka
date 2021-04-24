package ru.destered.semestr3sem.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AuthorisationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String currentUrl = UrlUtils.buildRequestUrl((HttpServletRequest) servletRequest);
            if ("/signUp".equals(currentUrl) || "/signIn".equals(currentUrl)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/profile");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
