package com.example.oauth2_server.service;

import com.example.oauth2_server.dto.LoginUserAuthenticationDetails;
import com.example.oauth2_server.dto.UserCredentials;

public interface LoginUserAuthenticationService {

	LoginUserAuthenticationDetails authenticate(UserCredentials userCredentials);

}
