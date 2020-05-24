package com.example.oauth2_server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2_server.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User findUserByEmailId(String emailId);

}
