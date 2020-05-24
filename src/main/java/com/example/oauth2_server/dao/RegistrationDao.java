package com.example.oauth2_server.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth2_server.entity.Registration;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Integer> {

	Registration getRegistrationByEmailId(String emailId);

	Registration getRegistrationgByConfirmationCode(String confirmationCode);

}
