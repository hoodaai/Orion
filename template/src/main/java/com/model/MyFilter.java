package com.model;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by varun on 05/01/16.
 */
public class MyFilter implements Filter {

    private FilterConfig fc;
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
         this.fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
