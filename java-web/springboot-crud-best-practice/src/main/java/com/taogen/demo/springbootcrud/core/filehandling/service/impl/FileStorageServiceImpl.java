package com.taogen.demo.springbootcrud.core.filehandling.service.impl;

import com.taogen.demo.springbootcrud.core.filehandling.mapper.FileMapper;
import com.taogen.demo.springbootcrud.core.filehandling.entity.File;
import com.taogen.demo.springbootcrud.core.filehandling.exception.FileStorageException;
import com.taogen.demo.springbootcrud.core.filehandling.properites.FileStorageProperties;
import com.taogen.demo.springbootcrud.core.filehandling.service.FileStorageService;
import com.taogen.demo.springbootcrud.core.web.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Taogen
 */
@Service
public class FileStorageServiceImpl
        extends AbstractCrudService<FileMapper, File>
        implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    @Override
    public Resource loadFileAsResource(String fileUri) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileUri).normalize();
            Resource resource = new UrlResource(filePath.toUri());
//            Resource resource = new UrlResource(fileUri);
            if(resource.exists()) {
                return resource;
            } else {
//                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
//            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
        return null;
    }
}
