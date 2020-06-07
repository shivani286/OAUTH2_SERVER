package com.oauth2.example.authorizationserver.model;

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
import lombok.ToString;

@Entity
@Table(name="user_password")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserPassword implements Serializable {
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 106197346943451588L;

		@Id
	    @Column(name="user_password_id")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer userPasswordId;
	 
	    @Column(name="created_on")
	    private Date createdOn;
	    
	    @Column(name="updated_on")
	    private Date updatedOn;
	    
	    @Column(name="password")
	    private String password;
	    
	    @Column(name="user_id_fk")
	    private Integer userId;
}
