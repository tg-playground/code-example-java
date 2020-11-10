package com.taogen.demo.springbootcrud.core.filehandling.service.impl;

import com.taogen.demo.springbootcrud.core.filehandling.mapper.FileMapper;
import com.taogen.demo.springbootcrud.core.filehandling.entity.File;
import com.taogen.demo.springbootcrud.core.web.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.core.filehandling.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class FileServiceImpl
        extends AbstractCrudService<FileMapper, File>
        implements FileService {
    @Autowired
    @Override
    public void setMapper(FileMapper mapper) {
        this.mapper = mapper;
    }
}
