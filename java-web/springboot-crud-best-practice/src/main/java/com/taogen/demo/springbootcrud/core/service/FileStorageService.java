package com.taogen.demo.springbootcrud.core.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Taogen
 */
public interface FileStorageService {
    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileUri);
}
