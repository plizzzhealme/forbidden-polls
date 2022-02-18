package io.github.plizzzhealme.controller.filter;

import io.github.plizzzhealme.controller.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LocalizationFilter implements Filter {

    private static final String GET = "GET";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(Util.COMMAND);
        String method = request.getMethod();

        if (!Util.CHANGE_LOCALE_COMMAND.equals(command) && GET.equals(method)) {
            saveUrlToSession((HttpServletRequest) servletRequest);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void saveUrlToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> parameters = Collections.list(request.getParameterNames());
        String url = parameters
                .stream()
                .map(p -> p + "=" + request.getParameter(p))
                .collect(Collectors.joining("&", request.getRequestURL().append("?"), ""));

        session.setAttribute(Util.URL, url);
    }
}
