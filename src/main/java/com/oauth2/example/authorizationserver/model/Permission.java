package com.oauth2.example.authorizationserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "permission")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Permission implements Serializable {
   
	private static final long serialVersionUID = -232995282756884199L;
	@Id
	@Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    @Column(name = "name")
    private String name;
}

