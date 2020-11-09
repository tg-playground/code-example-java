package com.taogen.demo.springbootcrud.core.service.impl;

import com.taogen.demo.springbootcrud.core.dao.FileMapper;
import com.taogen.demo.springbootcrud.core.entity.File;
import com.taogen.demo.springbootcrud.core.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.core.service.FileService;
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
