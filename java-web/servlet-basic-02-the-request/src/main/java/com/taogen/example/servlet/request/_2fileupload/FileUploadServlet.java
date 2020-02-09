package com.taogen.example.servlet.request._2fileupload;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// Extend HttpServlet class
@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String UPLOAD_DIRECTORY_PATH;
    private int MAX_FILE_SIZE;
    private int MAX_MEMORY_SIZE;

    public void init() throws ServletException {
        // Do required initialization
        UPLOAD_DIRECTORY_PATH = getServletContext().getInitParameter("file-upload-directory");
        try {
            MAX_FILE_SIZE = Integer.valueOf(getServletContext().getInitParameter("max-file-size"));
            System.out.println("Max File Size is " + MAX_FILE_SIZE);
        } catch (NumberFormatException e) {
            MAX_FILE_SIZE = 50 * 1024;
            e.printStackTrace();
        }
        try {
            MAX_MEMORY_SIZE = Integer.valueOf(getServletContext().getInitParameter("max-memory-size"));
            System.out.println("Max Memory Size is " + MAX_MEMORY_SIZE);
        } catch (NumberFormatException e) {
            MAX_MEMORY_SIZE = 4 * 1024;
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request contentType is " + request.getContentType());
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isMultipart(request)) {
            out.println("<p>No file uploaded</p>");
            return;
        }

        ServletFileUpload servletFileUpload = getServletFileUpload();
        File uploadDirectory = new File(UPLOAD_DIRECTORY_PATH);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
        List<FileItem> formItems = null;
        try {
            formItems = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.println(new StringBuilder(e.getClass().getName()).append(": ").append(e.getMessage()));
            return;
        }
        boolean haveUploadFiles = false;
        if (formItems != null) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    haveUploadFiles = true;
                    String filename = new File(item.getName()).getName();
                    String filepath = new StringBuilder(UPLOAD_DIRECTORY_PATH).append(File.separator).append(filename)
                            .toString();
                    File storeFile = new File(filepath);
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println(new StringBuilder(e.getClass().getName()).append(": ").append(e.getMessage()));
                        return;
                    }
                    StringBuilder responseResult = new StringBuilder();
                    responseResult.append("<p>File ");
                    responseResult.append(filename);
                    responseResult.append(" has uploaded successfully! </p>");
                    out.println(responseResult.toString());
                }
            }
        }
        if (!haveUploadFiles) {
            out.println("Not found upload files!");
        }

    }

    public ServletFileUpload getServletFileUpload() {
        ServletFileUpload servletFileUpload = new ServletFileUpload(getDiskFileItemFactory());
        servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);
        servletFileUpload.setSizeMax(MAX_FILE_SIZE);
        return servletFileUpload;
    }

    public DiskFileItemFactory getDiskFileItemFactory() {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(MAX_MEMORY_SIZE);
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        return diskFileItemFactory;
    }

    private boolean isMultipart(HttpServletRequest request) {
        return request != null && request.getContentType() != null
                && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1;
    }

    public void destroy() {
        // do nothing.
    }
}