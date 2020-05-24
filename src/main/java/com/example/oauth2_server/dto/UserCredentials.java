package com.example.oauth2_server.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserCredentials implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4564141291792338277L;

	private String userName;
	private String password;
}
