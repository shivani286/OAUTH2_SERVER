package com.example.oauth2_server.service;

import com.example.oauth2_server.dto.PasswordCreationRequest;
import com.example.oauth2_server.entity.Registration;

public interface RegistrationService {

	Registration saveRegistration(Registration registration);

	Registration getRegistrationByEmailId(String emailId);

	Registration updateRegistration(Registration registration);

	Registration getRegistrationByConfirmationCode(String confirmationCode);

	void createOrganizationSetup(Registration registration, PasswordCreationRequest passwordCreation);

}
