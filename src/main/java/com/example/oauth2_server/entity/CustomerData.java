package com.example.oauth2_server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerData {

		private String firstName;
	    private String lastName;
	    private int age;
	    private String email;
	    private String id;

}
