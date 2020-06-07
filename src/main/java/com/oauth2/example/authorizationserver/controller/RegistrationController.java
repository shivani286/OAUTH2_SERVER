package com.oauth2.example.authorizationserver.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.example.authorizationserver.dto.PasswordCreationRequest;
import com.oauth2.example.authorizationserver.exceptionmapping.EntityAlreadyExistsException;
import com.oauth2.example.authorizationserver.model.Registration;
import com.oauth2.example.authorizationserver.repository.UserDao;
import com.oauth2.example.authorizationserver.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	
	@PostMapping
	public Registration createRegistration(@RequestBody Registration registration,HttpServletRequest request) {
		
		if (Objects.isNull(registration.getEmailId()) || registration.getEmailId().isEmpty()) {
			throw new NullPointerException("Please enter your valid bussiness email ID");
		}
		if (Objects.nonNull(
				Objects.nonNull(registration.getEmailId()) ? userDao.findUserByEmailId(registration.getEmailId()): null)) {
			throw new EntityAlreadyExistsException("Sorry, the email ID you have entered is already registered");
		}
		
		Registration registerEmail = registrationService.getRegistrationByEmailId(registration.getEmailId());
		logger.info("registerEmail--->" + registration.getEmailId());
		
		if (Objects.isNull(registerEmail)) {
			logger.debug("create NewRegistration....");
			return registrationService.saveRegistration(registration);
		}
		
		registration.setRegistartionId(registerEmail.getRegistartionId());
		return registrationService.updateRegistration(registration);
	}
	
	@PostMapping("/confirm")
	public Registration confirmPassword(@RequestBody PasswordCreationRequest passwordCreation) {
		
		Registration registration = registrationService.getRegistrationByConfirmationCode(passwordCreation.getConfirmationCode());
		
		if (Objects.nonNull(registration)) {
			registrationService.createOrganizationSetup(registration, passwordCreation);
		}
		return registration;
	}
}
