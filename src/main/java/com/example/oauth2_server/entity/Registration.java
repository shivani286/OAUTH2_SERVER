package com.example.oauth2_server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "new_registration")
@Setter
@Getter
@NoArgsConstructor
public class Registration implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "registration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer registartionId;
	
	@Column(name = "confirmation_code")
	private String confirmationCode;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "primary_contact_number")
	private String primaryContactNumber;
	
	@Column(name = "company_name")
	private String companyName;
	
}
