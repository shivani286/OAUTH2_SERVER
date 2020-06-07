package com.oauth2.example.authorizationserver.repository;

import org.springframework.stereotype.Repository;

import com.oauth2.example.authorizationserver.model.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Integer> {

	Registration getRegistrationByEmailId(String emailId);

	Registration getRegistrationgByConfirmationCode(String confirmationCode);

}
