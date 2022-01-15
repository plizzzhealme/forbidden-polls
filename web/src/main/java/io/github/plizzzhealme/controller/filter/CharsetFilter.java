package io.github.plizzzhealme.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/controller")
public class CharsetFilter implements Filter {

    public static final String UTF_8 = "utf-8";
    public static final String REQUEST_ENCODING = "requestEncoding";

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(REQUEST_ENCODING);

        if (encoding == null) {
            encoding = UTF_8;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
