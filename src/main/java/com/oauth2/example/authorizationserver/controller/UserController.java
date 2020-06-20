package com.oauth2.example.authorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.example.authorizationserver.model.User;
import com.oauth2.example.authorizationserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/detail/by/{userId}")
	public User getUserDetail(@PathVariable("userId") Integer userId) {
		return userService.getUserDetailById(userId);
	}
}
