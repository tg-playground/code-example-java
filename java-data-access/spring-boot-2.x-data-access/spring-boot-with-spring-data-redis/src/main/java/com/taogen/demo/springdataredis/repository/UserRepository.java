package com.taogen.demo.springdataredis.repository;

import com.taogen.demo.springdataredis.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Taogen
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

}
