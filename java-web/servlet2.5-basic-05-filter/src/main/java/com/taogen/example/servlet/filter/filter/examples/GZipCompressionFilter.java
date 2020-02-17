package com.taogen.example.servlet.filter.filter.examples;

import com.taogen.example.servlet.filter.filter.Wrapper.GZipServletResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZipCompressionFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        logger.debug("GZipCompressionFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("GZipCompressionFilter Begin...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (acceptGZipEncoding(request)){
            logger.debug("request accept gzip encoding");
            response.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(response);
            filterChain.doFilter(servletRequest, gzipResponse);
            gzipResponse.close();
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        logger.debug("GZipCompressionFilter End.");
    }

    private boolean acceptGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1;
    }

    @Override
    public void destroy() {

    }
}
