package com.example.oauth2_server.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth2_server.dao.UserDao;
import com.example.oauth2_server.dto.PasswordCreationRequest;
import com.example.oauth2_server.entity.Registration;
import com.example.oauth2_server.exceptionmapping.EntityAlreadyExistsException;
import com.example.oauth2_server.service.RegistrationService;


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
		logger.info("registerEmail--->" + registration.getRegistartionId());
		
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
