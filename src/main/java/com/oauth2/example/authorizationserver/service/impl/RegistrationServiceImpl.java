package com.oauth2.example.authorizationserver.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oauth2.example.authorizationserver.repository.OrganizationDao;
import com.oauth2.example.authorizationserver.repository.RegistrationDao;
import com.oauth2.example.authorizationserver.repository.UserDao;
import com.oauth2.example.authorizationserver.repository.UserPasswordDao;
import com.oauth2.example.authorizationserver.dto.PasswordCreationRequest;
import com.oauth2.example.authorizationserver.model.Organization;
import com.oauth2.example.authorizationserver.model.Registration;
import com.oauth2.example.authorizationserver.model.User;
import com.oauth2.example.authorizationserver.model.UserPassword;
import com.oauth2.example.authorizationserver.exceptionmapping.ActivationLinkExpiryException;
import com.oauth2.example.authorizationserver.service.RegistrationService;
import com.oauth2.example.authorizationserver.util.TokenGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	public static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private  UserDao userDao;
	@Autowired
	private UserPasswordDao userPasswordDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Registration saveRegistration(Registration registration) {
		logger.info("create new NewRegistration ");
		
		registration.setCreatedOn(new Date());
		registration.setConfirmationCode(TokenGenerator.generateToken());
		registration.setCompanyName(registration.getCompanyName());
		registration.setEmailId(registration.getEmailId());
		registration.setFirstName(registration.getFirstName());
		registration.setLastName(registration.getLastName());
		registration.setPrimaryContactNumber(registration.getPrimaryContactNumber());
		registrationDao.save(registration);

		//sendRegistrationEmail(registration);

		return registration;
	}
	
	@Override
	public Registration getRegistrationByEmailId(String emailId) {
		logger.info("Fetching Registration by emailId = " + emailId);
		return registrationDao.getRegistrationByEmailId(emailId);
	}

	@Override
	public Registration updateRegistration(Registration registration) {
		logger.info("update existing registration ");

		registration.setCreatedOn(new Date());
		registration.setConfirmationCode(TokenGenerator.generateToken());
		registrationDao.saveAndFlush(registration);

	//	sendRegistrationEmail(registration);

		return registration;
	}

	@Override
	public Registration getRegistrationByConfirmationCode(String confirmationCode) {
		logger.info("Fetchngring registration by confirmationCode = " + confirmationCode);
		
		Registration registration = registrationDao.getRegistrationgByConfirmationCode(confirmationCode);
		if (Objects.nonNull(registration)) {
			Date registrationDate = registration.getCreatedOn();
			Boolean activationLinkExpiry =activitionLinkExpiry(registrationDate);
			
			if (activationLinkExpiry.equals(true)) {
				return registration;
			}
		}else throw new ActivationLinkExpiryException("Sorry, Your activation link has been expired") ;
		return registration;
	}

	private Boolean activitionLinkExpiry(Date registrationDate) {
		
		String maxExpiryTime = "24";
		long maximumExipiryTime=Long.parseLong(maxExpiryTime);
		LocalDateTime localRegisterdDate = LocalDateTime.ofInstant(registrationDate.toInstant(), ZoneId.systemDefault());
		LocalDateTime today = LocalDateTime.now();
		long numberOfHours = Duration.between(localRegisterdDate, today).toHours();
		if (numberOfHours <= maximumExipiryTime) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void createOrganizationSetup(Registration registration, PasswordCreationRequest passwordCreation) {


		// step1: create organization
		Organization createOrg = createOrganization(registration);

		// step2:create user
		User user = createUser(registration,createOrg);

		// step3: create user password
		createPassword(passwordCreation,user);

		// step4: delete record from new registration
		//deleteRegistrationByConfirmationCode(passwordCreation.getConfirmationCode());

	}

	private void createPassword(PasswordCreationRequest passwordCreation, User user) {
		logger.debug("Inside getPassword creation method");
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword(passwordEncoder.encode(passwordCreation.getPassword()));
		userPassword.setUserId(user.getUserId());																																																														
		userPassword.setCreatedOn(new Date());
		userPassword.setUpdatedOn(new Date());
		
		logger.info("*********************password created******************");
		userPasswordDao.save(userPassword);
	}

	private User createUser(Registration registration, Organization createOrg) {
		logger.debug("Inside user creation method");
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmailId(registration.getEmailId());
		user.setContactNumber(registration.getPrimaryContactNumber());
		user.setOrganization(Objects.nonNull(createOrg) ? createOrg : null);
		user.setCompanyName(registration.getCompanyName());
		user.setCreatedOn(new Date());
		user.setUpdatedOn(new Date());
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setAccountNonLocked(true);
		User createUser = userDao.save(user);
		logger.info("**************************user created*****************************");
		return createUser;
	}

	private Organization createOrganization(Registration registration) {
		logger.debug("inside organization create method");
		Organization organization = new Organization();
		organization.setName(registration.getCompanyName());
		organization.setEmailId(registration.getEmailId());
		organization.setPrimaryContactNumber(registration.getPrimaryContactNumber());
		Organization createOrganization = organizationDao.save(organization);
		logger.info("**************************user organization created*****************************");
		return createOrganization;

	}

}
