package com.selfman.customer.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "customers")
public class Customer {
	@Id
	String id;
	String firstName;
    String lastName;
    @Indexed(unique = true)
    String email;
    String password;
    String country;
    String city;
    String street;
	String building;
	String zipcode;
    String phoneNumber;
    Set<String> roles;
    
    public Customer() {
    	roles = new HashSet<>();
    }

	public Customer(String firstName, String lastName, String email, String password, String country) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.country = country;
	}
    
	public boolean addRole(String role) {
		return roles.add(role);
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}

}
