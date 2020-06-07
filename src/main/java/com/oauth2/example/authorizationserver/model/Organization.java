package com.oauth2.example.authorizationserver.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Organization implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -341438324752124045L;

	@Id
	@Column(name = "organization_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer organizationId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "primary_contact_number")
	private String primaryContactNumber;
	
}
