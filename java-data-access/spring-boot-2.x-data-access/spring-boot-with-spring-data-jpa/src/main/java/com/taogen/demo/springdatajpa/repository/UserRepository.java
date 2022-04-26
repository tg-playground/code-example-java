package com.taogen.demo.springdatajpa.repository;

import com.taogen.demo.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update user set user_name = ?2, user_email = ?3 where user_id = ?1", nativeQuery = true)
//    @Query(value = "update user u set u.userName = ?2, u.userEmail = ?3 where u.userId = ?1")
    int updateByParam(Integer id, String username, String email);

    @Query("select u from user u where u.userName like %?1% and u.userEmail like %?2%")
    List<User> findByParams(String username, String email);
//    List<User> findByParams(@Param("username") String username, @Param("email") String email);
}
