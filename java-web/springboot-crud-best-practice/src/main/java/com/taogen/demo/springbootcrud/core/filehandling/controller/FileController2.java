package com.taogen.demo.springbootcrud.core.filehandling.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("files2")
public class FileController2 {

    private static final Logger logger = LogManager.getLogger();

    public static final String UPLOAD_DIR = "D:\\My Desktop\\upload";

    public static final String DOWNLOAD_PREFIX = "/files/download";

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file, String moduleUri) throws IOException {
        String uploadFileUri = getUploadFileUri(file, moduleUri);
        String uploadFilePath = new StringBuilder()
                .append(UPLOAD_DIR)
                .append(File.separator)
                .append(uploadFileUri)
                .toString();
        ensureDirectoryExist(uploadFilePath);
        try (
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                        new FileOutputStream(uploadFilePath));
        ) {
            byte[] cache = new byte[1024];
            int len;
            InputStream inputStream = file.getInputStream();
            while ((len = inputStream.read(cache)) != -1) {
                bufferedOutputStream.write(cache, 0, len);
            }
        }
        return new StringBuilder()
                .append(DOWNLOAD_PREFIX)
                .append("/")
                .append(uploadFileUri)
                .toString();
    }

    private String getUploadFileUri(MultipartFile file, String moduleUri) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Calendar calendar = Calendar.getInstance();
        StringBuilder dateUri = new StringBuilder()
                .append(calendar.get(Calendar.YEAR))
                .append("/")
                .append(calendar.get(Calendar.MONTH) + 1)
                .append("/")
                .append(calendar.get(Calendar.DAY_OF_MONTH));
        StringBuilder resultFileUri = new StringBuilder()
                .append(moduleUri)
                .append("/")
                .append(dateUri)
                .append("/")
                .append(uuid)
                .append("/")
                .append(file.getOriginalFilename());
        return resultFileUri.toString();
    }

    private void ensureDirectoryExist(String filePath) {
        filePath = filePath.replace("/", File.separator).replace("\\", File.separator);
        String fileDir = filePath.substring(0, filePath.lastIndexOf(File.separator));
        logger.debug("fileDir: {}", fileDir);
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @GetMapping(value = "download/**")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        String filePath = new StringBuilder()
                .append(UPLOAD_DIR)
                .append(File.separator)
                .append(servletPath.substring(DOWNLOAD_PREFIX.length()))
                .toString();
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))
        ) {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] cache = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, len);
            }
        }
    }
}
