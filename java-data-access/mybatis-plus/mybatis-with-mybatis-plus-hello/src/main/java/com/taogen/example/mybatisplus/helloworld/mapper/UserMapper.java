package com.taogen.example.mybatisplus.helloworld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taogen.example.mybatisplus.helloworld.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Taogen
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
