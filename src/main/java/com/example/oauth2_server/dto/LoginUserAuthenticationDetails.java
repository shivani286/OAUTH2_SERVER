package com.example.oauth2_server.dto;

import java.io.Serializable;
import com.example.oauth2_server.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUserAuthenticationDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4035849865312608529L;
	private User user;
	private String UserAuthToken;
}
