package com.oauth2.example.authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.example.authorizationserver.model.Organization;


@Repository
public interface OrganizationDao  extends JpaRepository<Organization, Integer> {

}
