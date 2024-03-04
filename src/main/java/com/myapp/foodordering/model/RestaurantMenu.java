package com.myapp.foodordering.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Component
@Entity
public class RestaurantMenu {

	@Id
	@GeneratedValue
	private int restaurantMenuItemId;
	private int restaurantId;
	private String restaurantMenuItemName;
	private double restaurantMenuItemPrice;

	public int getRestaurantMenuItemId() {
		return restaurantMenuItemId;
	}

	public void setRestaurantMenuItemId(int restaurantMenuItemId) {
		this.restaurantMenuItemId = restaurantMenuItemId;
	}

	public String getRestaurantMenuItemName() {
		return restaurantMenuItemName;
	}

	public void setRestaurantMenuItemName(String restaurantMenuItemName) {
		this.restaurantMenuItemName = restaurantMenuItemName;
	}

	public double getRestaurantMenuItemPrice() {
		return restaurantMenuItemPrice;
	}

	public void setRestaurantMenuItemPrice(double restaurantMenuItemPrice) {
		this.restaurantMenuItemPrice = restaurantMenuItemPrice;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
}
