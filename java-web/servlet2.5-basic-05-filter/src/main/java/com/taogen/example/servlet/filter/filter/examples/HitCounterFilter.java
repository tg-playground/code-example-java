package com.taogen.example.servlet.filter.filter.examples;

import com.taogen.example.servlet.filter.filter.Wrapper.CharResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class HitCounterFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;
    private AtomicInteger hitCounter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.hitCounter = new AtomicInteger(Integer.parseInt(context.getInitParameter("hitCounter")));
        logger.debug("HitCounterFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("HitCounterFilter Begin...");
        PrintWriter out = servletResponse.getWriter();
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, wrapper);
        logger.debug("wrapper content type is {}", wrapper.getContentType());
        if (wrapper.getContentType() != null && wrapper.getContentType().contains("text/html")){
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            int bodyEndingIndex = wrapper.toString().indexOf("</body>");
            if (bodyEndingIndex != -1) {
                charArrayWriter.write(wrapper.toString().substring(0, bodyEndingIndex-1));
            }else{
                charArrayWriter.write(wrapper.toString());
            }
            charArrayWriter.write("<p>\nYou are visitor number <font color='red'>"+(hitCounter.addAndGet(1))+"</font>");
            charArrayWriter.write("\n</body></html>");
            servletResponse.setContentLength(charArrayWriter.toString().length());
            out.write(charArrayWriter.toString());
        }else{
            out.write(wrapper.toString());
        }
        out.close();
        logger.debug("HitCounterFilter End.");
    }

    @Override
    public void destroy() {

    }
}
