package com.taogen.example.servlet.servletcontext._1initparams;

import com.taogen.example.servlet.servletcontext.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class InitialParamsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // servlet context initial parameters defined in web.xml <context-param>
        String projectName = getServletContext().getInitParameter("project-name");
        logger.info("project name is {}", projectName);
        String projectDesc = getServletContext().getInitParameter("project-desc");
        logger.info("project description is {}", projectDesc);

        // traverse all ServletContext initialParams
        Enumeration initialParamNames = getServletContext().getInitParameterNames();
        logger.info("Traverse servletContext initialParams: ");
        while(initialParamNames.hasMoreElements()){
            String paramName = initialParamNames.nextElement().toString();
            logger.info("One of initialParamNames: {} is {}", paramName, getServletContext().getInitParameter(paramName));
        }

        resp.setContentType("text/html");
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("projectName", projectName);
        resultMap.put("projectDesc", projectDesc);
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, resultMap.toString());
    }
}
