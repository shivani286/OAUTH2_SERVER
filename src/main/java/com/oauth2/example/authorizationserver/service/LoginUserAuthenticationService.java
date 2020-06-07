package com.oauth2.example.authorizationserver.service;

import com.oauth2.example.authorizationserver.dto.LoginUserAuthenticationDetails;
import com.oauth2.example.authorizationserver.dto.UserCredentials;

public interface LoginUserAuthenticationService {

	LoginUserAuthenticationDetails authenticate(UserCredentials userCredentials);

}
