package com.taogen.demo.springdatajdbc.repository;

import com.taogen.demo.springdatajdbc.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 1
 * In Spring Data JDBC, we write queries in plain SQL by @Query.
 * We don't use any higher-level query language like JPQL.
 * As a result, the application becomes tightly coupled with the database vendor.
 * 2
 * To summarize, Spring Data JDBC offers a solution that is as simple as using Spring JDBC — there is no magic behind it. Nonetheless, it also offers a majority of features that we're accustomed to using Spring Data JPA.
 * One of the biggest advantages of Spring Data JDBC is the improved performance when accessing the database as compared to Spring Data JPA. This is due to Spring Data JDBC communicating directly to the database. Spring Data JDBC doesn't contain most of the Spring Data magic when querying the database.
 * One of the biggest disadvantages when using Spring Data JDBC is the dependency on the database vendor. If we decide to change the database from MySQL to Oracle, we might have to deal with problems that arise from databases having different dialects.
 *
 * @author Taogen
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Modifying
    @Query("update user set user_name = :username, user_email = :email where user_id = :id")
    int updateByParam(@Param("id") Integer id, @Param("username") String username, @Param("email") String email);

    @Query("select * from user where user_name = :username  and user_email = :email")
    List<User> findByParams(@Param("username") String username, @Param("email") String email);

//    List<User> findAllByUserName(String userName, Pageable pageable);
}
