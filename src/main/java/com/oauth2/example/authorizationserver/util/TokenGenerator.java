package com.oauth2.example.authorizationserver.util;


import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {

	private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        return DatatypeConverter.printBase64Binary((new BigInteger(100, random).toString(32)).getBytes());
    }
}