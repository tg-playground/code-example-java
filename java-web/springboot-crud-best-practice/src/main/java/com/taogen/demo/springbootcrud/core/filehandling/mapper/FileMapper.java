package com.taogen.demo.springbootcrud.core.filehandling.mapper;

import com.taogen.demo.springbootcrud.core.filehandling.entity.File;
import com.taogen.demo.springbootcrud.core.persistence.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FileMapper extends CrudMapper<File> {
}