package com.oauth2.example.authorizationserver.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserCredentials implements Serializable{

	private static final long serialVersionUID = -5702500736238319236L;
	
	private String userName;
	private String password;
}
