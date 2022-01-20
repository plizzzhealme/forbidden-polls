package io.github.plizzzhealme.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;

public class LoggerFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Collections.list(servletRequest.getParameterNames())
                .forEach(param -> logger.info(MessageFormat.format("{0} = {1}",
                        param,
                        servletRequest.getParameter(param))));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
