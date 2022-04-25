package com.taogen.demo.springdatajpa.repository;

import com.taogen.demo.springdatajpa.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Taogen
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

//    User findByUserId(Integer userId);

//    List<User> findAllByUserName(String userName, Pageable pageable);
}
