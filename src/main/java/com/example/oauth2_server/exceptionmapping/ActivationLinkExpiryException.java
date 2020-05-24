package com.example.oauth2_server.exceptionmapping;

import java.io.Serializable;

public class ActivationLinkExpiryException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -5736806605259756552L;

	public ActivationLinkExpiryException(String message) {
        super(message);
    }
}
