package com.oauth2.example.authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.example.authorizationserver.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User findUserByEmailId(String emailId);

	User findUserByUserId(Integer userId);

}
