package com.taogen.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${app.fileUpload.uploadDir}")
    private String uploadDir;

    private static final String FILE_URI_PREFIX = "/files/download/";

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        StringBuilder realFilePath = new StringBuilder()
                .append(uploadDir)
                .append(File.separator);
        Path dir = Paths.get(realFilePath.toString()).toAbsolutePath().normalize();
        Files.createDirectories(dir);
        // Copy file to the target location (Replacing existing file with the same name)
        filename = filename.replaceAll("\\+", "%20");
        Path targetLocation = dir.resolve(filename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(FILE_URI_PREFIX)
                .path(filename)
                .toUriString();
        fileDownloadUri = fileDownloadUri.substring(fileDownloadUri.indexOf(FILE_URI_PREFIX));
        System.out.println("fileDownloadUri = " + fileDownloadUri);
        return URLDecoder.decode(fileDownloadUri, "utf8");
    }

    @GetMapping("doesFileExist")
    public Boolean doesFileExist(@RequestParam("fileUri") String fileUri) {
        String filePath = new StringBuilder()
                .append(uploadDir)
                .append(File.separator)
                .append(fileUri, fileUri.indexOf(FILE_URI_PREFIX) + FILE_URI_PREFIX.length(), fileUri.length())
                .toString();
        File file = new File(filePath);
        return file != null && file.exists() && file.isFile();
    }

    @DeleteMapping("deleteFile")
    public void deleteFile(@RequestParam("fileUri") String fileUri) {
        String filePath = new StringBuilder()
                .append(uploadDir)
                .append(File.separator)
                .append(fileUri, fileUri.indexOf(FILE_URI_PREFIX) + FILE_URI_PREFIX.length(), fileUri.length())
                .toString();
        File file = new File(filePath);
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
