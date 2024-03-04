package com.myapp.foodordering.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Component
@Entity
public class RestaurantDetails {

	@Id
	@GeneratedValue
	private int restaurantId;
	private String restaurantName;
	private String restaurantCuisine;
	private String restaurantEmail;
	private String restaurantPhone;
	private String restaurantAddressCity;
	private String restaurantPassword;
	private String restaurantCoverPhoto; // optional

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantCuisine() {
		return restaurantCuisine;
	}

	public void setRestaurantCuisine(String restaurantCuisine) {
		this.restaurantCuisine = restaurantCuisine;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public String getRestaurantPhone() {
		return restaurantPhone;
	}

	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

	public String getRestaurantAddressCity() {
		return restaurantAddressCity;
	}

	public void setRestaurantAddressCity(String restaurantAddressCity) {
		this.restaurantAddressCity = restaurantAddressCity;
	}

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
	}

	public String getRestaurantCoverPhoto() {
		return restaurantCoverPhoto;
	}

	public void setRestaurantCoverPhoto(String restaurantCoverPhoto) {
		this.restaurantCoverPhoto = restaurantCoverPhoto;
	}

}
