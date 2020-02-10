package com.taogen.example.servlet.annotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/fileUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class FileUploadByMultipartConfigServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String uploadPath = getUploadPath(req);
        ensureUploadDirectoryExist(uploadPath);
        writeFileFromParts(req.getParts(), uploadPath, out);
    }

    private String getUploadPath(HttpServletRequest req) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadPath = new StringBuilder().append(applicationPath).append(File.separator).append(UPLOAD_DIR)
                .toString();
        return uploadPath;
    }

    private void ensureUploadDirectoryExist(String uploadPath) {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }

    private void writeFileFromParts(Collection<Part> parts, String uploadPath, PrintWriter out) {
        List<String> uploadFileNames = new ArrayList<>();
        for (Part part : parts) {
            System.out.println("part ContentType: " + part.getContentType());
            if (part.getContentType() != null) {
                String fileName = getFileNameByPart(part);
                try {
                    part.write(new StringBuilder(uploadPath).append(File.separator).append(fileName).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadFileNames.add(fileName);
            } else {
                String paramValue = null;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));) {
                    paramValue = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringBuilder result = new StringBuilder("Param ").append(part.getName()).append(" is ").append(paramValue);
                System.out.println(result.toString());
            }
        }
        if (!uploadFileNames.isEmpty()) {
            for (String fileName : uploadFileNames) {
                out.println(new StringBuilder("File ").append(fileName).append("has uploaded successfully!"));
            }
        } else {
            out.println("No files upload!");
        }

    }

    private String getFileNameByPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition of header: " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}