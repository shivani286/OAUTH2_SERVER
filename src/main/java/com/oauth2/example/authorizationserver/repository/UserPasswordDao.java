package com.oauth2.example.authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.example.authorizationserver.model.UserPassword;

@Repository
public interface UserPasswordDao extends JpaRepository<UserPassword, Integer>{

	UserPassword findUserPasswordByUserId(Integer userId);


}
