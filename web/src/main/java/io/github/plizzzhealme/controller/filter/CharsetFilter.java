package io.github.plizzzhealme.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    public static final String UTF_8 = "utf-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding(UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
