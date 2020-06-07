package com.oauth2.example.authorizationserver.service;

import com.oauth2.example.authorizationserver.dto.PasswordCreationRequest;
import com.oauth2.example.authorizationserver.model.Registration;

public interface RegistrationService {

	Registration saveRegistration(Registration registration);

	Registration getRegistrationByEmailId(String emailId);

	Registration updateRegistration(Registration registration);

	Registration getRegistrationByConfirmationCode(String confirmationCode);

	void createOrganizationSetup(Registration registration, PasswordCreationRequest passwordCreation);

}
