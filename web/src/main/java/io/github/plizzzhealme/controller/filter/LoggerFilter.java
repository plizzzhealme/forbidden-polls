package io.github.plizzzhealme.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LoggerFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String line = "------------------------------------------";
        logger.info("request:");
        logger.info(line);
        Collections.list(servletRequest.getParameterNames())
                .stream()
                .map(param -> param + " = " + servletRequest.getParameter(param))
                .forEach(logger::info);
        logger.info(line);

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        List<String> attributeNames = Collections.list(session.getAttributeNames());

        logger.info("session:");
        logger.info(line);
        attributeNames.stream()
                .map(p -> p + " = " + session.getAttribute(p))
                .forEach(logger::info);
        logger.info(line);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
