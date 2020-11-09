package com.taogen.demo.springbootcrud.core.properites;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Taogen
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
