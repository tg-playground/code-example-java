package com.taogen.demo.springbootcrud.core.dao;

import com.taogen.demo.springbootcrud.core.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FileMapper extends CrudMapper<File>{
}