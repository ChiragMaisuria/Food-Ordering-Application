package com.myapp.foodordering.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class LoginInformation {

	@Id
	private String email;
	private String password;
	private String userRole; //customer or restaurant

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "LoginInformation [email=" + email + ", password=" + password + ", userRole=" + userRole + "]";
	}
	
	

}
