package com.taogen.demo.springbootcrud.core.filehandling.service.impl;

import com.taogen.demo.springbootcrud.core.filehandling.entity.File;
import com.taogen.demo.springbootcrud.core.filehandling.mapper.FileMapper;
import com.taogen.demo.springbootcrud.core.filehandling.service.FileService;
import com.taogen.demo.springbootcrud.core.web.service.AbstractCrudService;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class FileServiceImpl
        extends AbstractCrudService<FileMapper, File>
        implements FileService {
}
