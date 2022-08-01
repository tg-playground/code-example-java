package com.taogen.example.servlet.request;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUploadByCommonsFileUploadServlet extends HttpServlet {

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
        DiskFileItemFactory diskFileItemFactory = getDiskFileItemFactory();
        ServletFileUpload servletFileUpload = getServletFileUpload(diskFileItemFactory);
        List<FileItem> formItems = getFileItems(servletFileUpload, request, out);
        if (formItems != null) {
            ensureUploadDirectoryExist();
            writeFilesFromFileItem(formItems, out);
        }
    }

    private boolean isMultipart(HttpServletRequest request) {
        return request != null && request.getContentType() != null
                && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1;
    }

    private DiskFileItemFactory getDiskFileItemFactory() {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(MAX_MEMORY_SIZE);
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        return diskFileItemFactory;
    }

    private ServletFileUpload getServletFileUpload(DiskFileItemFactory diskFileItemFactory) {
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);
        servletFileUpload.setSizeMax(MAX_FILE_SIZE);
        return servletFileUpload;
    }

    private List<FileItem> getFileItems(ServletFileUpload servletFileUpload, HttpServletRequest request,
                                        PrintWriter out) {
        List<FileItem> formItems = null;
        try {
            formItems = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.println(new StringBuilder(e.getClass().getName()).append(": ").append(e.getMessage()));
        }
        return formItems;
    }

    private void ensureUploadDirectoryExist() {
        File uploadDirectory = new File(UPLOAD_DIRECTORY_PATH);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }

    private void writeFilesFromFileItem(List<FileItem> formItems, PrintWriter out) {
        List<String> fileNames = new ArrayList<>();
        if (formItems != null) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    fileNames.add(fileName);
                    String filepath = new StringBuilder(UPLOAD_DIRECTORY_PATH).append(File.separator).append(fileName)
                            .toString();
                    File storeFile = new File(filepath);
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // response error message
                        out.println(new StringBuilder(e.getClass().getName()).append(": ").append(e.getMessage()));
                        return;
                    }
                }
            }
        }
        if (fileNames != null && !fileNames.isEmpty()) {
            for (String fileName : fileNames) {
                StringBuilder responseResult = new StringBuilder();
                responseResult.append("<p>File ");
                responseResult.append(fileName);
                responseResult.append(" has uploaded successfully! </p>");
                out.println(responseResult.toString());
            }
        } else {
            out.println("Not found upload files!");
        }
    }

    public void destroy() {
        // do nothing.
    }
}
