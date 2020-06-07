package com.oauth2.example.authorizationserver.dto;

import java.io.Serializable;
import com.oauth2.example.authorizationserver.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUserAuthenticationDetails implements Serializable{
	
	private static final long serialVersionUID = -8208284961234313612L;
	
	private User user;
	private String UserAuthToken;
}
