package com.example.oauth2_server.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.oauth2_server.dao.OrganizationDao;
import com.example.oauth2_server.dao.RegistrationDao;
import com.example.oauth2_server.dao.UserDao;
import com.example.oauth2_server.dao.UserPasswordDao;
import com.example.oauth2_server.dto.PasswordCreationRequest;
import com.example.oauth2_server.entity.Organization;
import com.example.oauth2_server.entity.Registration;
import com.example.oauth2_server.entity.User;
import com.example.oauth2_server.entity.UserPassword;
import com.example.oauth2_server.exceptionmapping.ActivationLinkExpiryException;
import com.example.oauth2_server.service.RegistrationService;
import com.example.oauth2_server.util.TokenGenerator;

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
		userPassword.setPassword(passwordCreation.getPassword());
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
		user.setCreatedOn(new Date());
		user.setUpdatedOn(new Date());
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
