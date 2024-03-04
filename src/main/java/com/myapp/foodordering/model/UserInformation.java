package com.myapp.foodordering.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class UserInformation {

	@Id
	@GeneratedValue
	private int userId;
	private String fname;
	private String lname;
	private String email;
	private String phoneNumber;
	private String addressCity;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String address) {
		this.addressCity = address;
	}

	@Override
	public String toString() {
		return "UserInformation [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", addressCity=" + addressCity + ", password=" + password + "]";
	}
	
	

}
