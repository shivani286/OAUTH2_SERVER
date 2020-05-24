package com.example.oauth2_server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordCreationRequest {

	private Integer id;
	private String password;
	private String confirmPassword;
	private String confirmationCode;
	private String currentPassword;
	private String emailId;
}
