package com.example.oauth2_server.exceptionmapping;

import java.io.Serializable;

public class InvalidLoginCredentials extends RuntimeException implements Serializable{


	private static final long serialVersionUID = -832955735325386964L;

	public InvalidLoginCredentials(String message) {
		super(message);
	}
}
