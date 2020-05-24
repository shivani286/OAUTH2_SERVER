package com.example.oauth2_server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2_server.entity.UserPassword;

@Repository
public interface UserPasswordDao extends JpaRepository<UserPassword, Integer>{

	UserPassword findUserPasswordByUserId(Integer userId);


}
