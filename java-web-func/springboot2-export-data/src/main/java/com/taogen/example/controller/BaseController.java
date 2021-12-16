package com.taogen.example.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Taogen
 */
public abstract class BaseController {
    protected void setFileDownloadResponse(HttpServletResponse response, String filename) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        String resultFileName = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + resultFileName + "\"");
    }
}
