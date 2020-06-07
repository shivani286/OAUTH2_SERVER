package com.oauth2.example.authorizationserver.exceptionmapping;

import java.io.Serializable;

public class EntityAlreadyExistsException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -3889537485038759826L;

	public EntityAlreadyExistsException(String message) {
	        super(message);
	    }

}
