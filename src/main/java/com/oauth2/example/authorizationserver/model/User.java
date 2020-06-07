package com.oauth2.example.authorizationserver.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.oauth2.example.authorizationserver.model.Organization;
import com.oauth2.example.authorizationserver.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class User  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5229386323371619260L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "email_id")
	private String emailId;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name = "organization_id_fk")
	private Organization organization;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Column(name = "enabled")
    private boolean enabled;
	
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;
    
    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;
    
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private List<Role> roles;
}
