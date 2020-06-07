package com.oauth2.example.authorizationserver.service;

import com.oauth2.example.authorizationserver.model.User;

public interface UserService {

	User getUserDetailById(Integer userId);

}
