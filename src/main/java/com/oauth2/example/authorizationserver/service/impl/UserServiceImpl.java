package com.oauth2.example.authorizationserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oauth2.example.authorizationserver.model.User;
import com.oauth2.example.authorizationserver.repository.UserDao;
import com.oauth2.example.authorizationserver.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserDetailById(Integer userId) {
		return userDao.findUserByUserId(userId);
	}

}
