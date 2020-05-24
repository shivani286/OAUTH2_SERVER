package com.example.oauth2_server.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth2_server.dao.redis.UserJedisDao;
import com.example.oauth2_server.dto.LoginUserAuthenticationDetails;
import com.example.oauth2_server.dto.PasswordCreationRequest;
import com.example.oauth2_server.dto.UserCredentials;
import com.example.oauth2_server.entity.Registration;
import com.example.oauth2_server.exceptionmapping.InvalidLoginCredentials;
import com.example.oauth2_server.service.LoginUserAuthenticationService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginUserAuthenticationService userAuthenticationService;
	@Autowired
	private UserJedisDao userJedisRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping
	public LoginUserAuthenticationDetails login(@RequestBody UserCredentials userCredentials, HttpSession session) {
		
		logger.info("authetication Login-->" + userCredentials);
		LoginUserAuthenticationDetails details = new LoginUserAuthenticationDetails();
		
		details = userAuthenticationService.authenticate(userCredentials);
		
		if (Objects.isNull(details.getUser())) {
			throw new InvalidLoginCredentials("Wrong Login Credentials");
		}
		logger.info("user Login successfull-->");
		
		details.setUserAuthToken(session.getId());
		logger.info("session id"+session.getId());

		userJedisRepository.save(details);
		return details;
	}
	
	@GetMapping("get/call/test")
	public String getStringPrint() {
		return "Hello Shivani";
	}
	
}
