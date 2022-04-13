package com.taogen.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taogen.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper接口
 *
 * @author Taogen
 * @date 2022-04-13
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
