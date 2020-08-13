package com.taogen.example.mybatis.annotation.mapper;


import com.taogen.example.mybatis.annotation.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Delete("delete from t_user where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into t_user (name, age) values (#{name}, #{age})")
    int insert(User user);

    @Insert("insert into t_user (id, name, age) values (#{id}, #{name}, #{age})")
    int insertWithId(User user);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User selectByPrimaryKey(Integer id);

    @Update("update t_user set name=#{name}, age=#{age} where id=#{id}")
    int updateByPrimaryKey(User user);
}