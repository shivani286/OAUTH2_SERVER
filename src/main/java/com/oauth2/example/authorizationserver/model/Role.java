package com.oauth2.example.authorizationserver.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Role implements Serializable {

	private static final long serialVersionUID = -314408508110057215L;
	@Id
	@Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")})
    private List<Permission> permissions;


}
