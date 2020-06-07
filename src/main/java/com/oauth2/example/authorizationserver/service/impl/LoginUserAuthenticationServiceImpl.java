package com.oauth2.example.authorizationserver.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oauth2.example.authorizationserver.dto.LoginUserAuthenticationDetails;
import com.oauth2.example.authorizationserver.dto.UserCredentials;
import com.oauth2.example.authorizationserver.model.User;
import com.oauth2.example.authorizationserver.repository.UserDao;
import com.oauth2.example.authorizationserver.service.LoginUserAuthenticationService;

@Service
public class LoginUserAuthenticationServiceImpl implements LoginUserAuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(LoginUserAuthenticationServiceImpl.class);
	@Autowired
	private UserDao userDao;
	
	@Override
	public LoginUserAuthenticationDetails authenticate(UserCredentials userCredentials) {
		logger.info("within authenticate() ");
		
		LoginUserAuthenticationDetails authenticationDetails = new LoginUserAuthenticationDetails();
		if (Objects.isNull(userCredentials) || Objects.isNull(userCredentials.getUserName())
				|| Objects.isNull(userCredentials.getPassword())) {
			logger.info("userCredentials is null");
			return null;
		}
		
		logger.info("userCredentials is not null ");
		
		User user = userDao.findUserByEmailId(userCredentials.getUserName());
		if (Objects.nonNull(user)) {
			logger.info("user object is not null");
			userDao.saveAndFlush(user);
			authenticationDetails.setUser(user);
		}
		
		logger.info("authenticationDetails object returning method end");
		return authenticationDetails; 
	}

}
