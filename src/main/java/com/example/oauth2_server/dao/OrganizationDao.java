package com.example.oauth2_server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2_server.entity.Organization;

@Repository
public interface OrganizationDao  extends JpaRepository<Organization, Integer> {

}
