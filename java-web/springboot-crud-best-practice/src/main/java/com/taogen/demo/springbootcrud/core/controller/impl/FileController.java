package com.taogen.demo.springbootcrud.core.controller.impl;

import com.taogen.demo.springbootcrud.core.controller.AbstractRestController;
import com.taogen.demo.springbootcrud.core.dto.Id;
import com.taogen.demo.springbootcrud.core.entity.File;
import com.taogen.demo.springbootcrud.core.service.FileService;
import com.taogen.demo.springbootcrud.core.service.FileStorageService;
import com.taogen.demo.springbootcrud.core.vo.GenericResponseModel;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController extends AbstractRestController<FileService, File> {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    public void init(FileService service) {
        this.service = service;
        this.type = File.class;
        this.logger = LogManager.getLogger();
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseModel<File> uploadFile(HttpServletRequest request,
                                                 @RequestParam("file") MultipartFile file) {
        fileStorageService.storeFile(file);
        File fileObj = new File();
        fileObj.setName(file.getOriginalFilename());
        fileObj.setUri(file.getOriginalFilename());
        return super.save(request, fileObj);
    }

    @PostMapping(value = "/uploadMultipleFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseModel<List<Id>> uploadMultipleFiles(HttpServletRequest request,
                                                              @RequestParam("files") MultipartFile[] files) {
        List<File> fileList = new ArrayList<>();
        Arrays.asList(files)
                .forEach(file -> {
                    fileStorageService.storeFile(file);
                    File fileObj = new File();
                    fileObj.setName(file.getOriginalFilename());
                    fileObj.setUri(file.getOriginalFilename());
                    fileList.add(fileObj);
                    super.save(request, fileObj);
                });

        GenericResponseModel<List<Id>> genericResponseModel = new GenericResponseModel<>("");
        genericResponseModel.setResponseBody(fileList.stream().map(file -> new Id(file.getId())).collect(Collectors.toList()));
        return genericResponseModel;
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request,
                                                 @RequestParam("fileUri") String fileUri) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileUri);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
