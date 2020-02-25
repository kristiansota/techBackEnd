package com.kristian.test.repository;

import com.kristian.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users  WHERE username = :username AND to_date is NULL",
            nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE to_date IS NULL", nativeQuery = true)
    List<User> findAll();

}
