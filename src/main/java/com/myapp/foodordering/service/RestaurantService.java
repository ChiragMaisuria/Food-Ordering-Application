package com.myapp.foodordering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.foodordering.dao.FoodOrderingDBAccess;
import com.myapp.foodordering.dao.LoginInformationDAO;
import com.myapp.foodordering.dao.RestaurantDetailsDAO;
import com.myapp.foodordering.dao.RestaurantMenuDAO;
import com.myapp.foodordering.dao.RestaurantOrdersDAO;
import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.RestaurantDetails;
import com.myapp.foodordering.model.RestaurantMenu;

@Service
public class RestaurantService {

	@Autowired
	private FoodOrderingDBAccess dbAccess;

	@Autowired
	private RestaurantDetailsDAO restaurantDetailsDAO;

	@Autowired
	private LoginInformationDAO loginInformationDAO;

	@Autowired
	private RestaurantOrdersDAO restaurantOrdersDAO;

	@Autowired
	private RestaurantMenuDAO restaurantMenuDAO;

	public boolean addRestaurantDetails(RestaurantDetails restaurantDetails) {

		return restaurantDetailsDAO.insertRestaurantDetails(restaurantDetails);
	}

	public String checkRestaurantLogin(LoginInformation loginInformation) {

//		Perform validation and sanitization of user Input.
		loginInformationDAO.authorizeRestaurantLogin(loginInformation);

		return "";
	}

	public RestaurantDetails fetchRestaurantDetails(int restaurantId) {
//		fetch restaurantId from session object.
		return restaurantDetailsDAO.getRestaurantDetails(restaurantId);

	}
	
	public RestaurantDetails fetchRestaurantDetailsByEmail(String email) {
		return restaurantDetailsDAO.getRestaurantDetailsByEmail(email);
	}

	public boolean updateOrderStatus(int orderId, String status) {

		return restaurantOrdersDAO.updateOrderStatus(orderId, status);
	}

	public boolean addItemsToMenu(RestaurantMenu restaurantMenu) {

		return restaurantMenuDAO.inserItemToMenu(restaurantMenu);
	}

	public boolean removeItemsFromMenu(int restaurantId, int menuItemId) {

		return restaurantMenuDAO.removeItemsFromMenu(restaurantId, menuItemId);
	}

	public List viewRestaurantOrders(String status) {
		int restaurantId = 0;
		return restaurantOrdersDAO.getRestaurantOrders(restaurantId, status);

	}

	public void viewMenu() {
		int restaurantId = 123;
		restaurantMenuDAO.getRestaurantMenu(restaurantId);
	}

	
	public List<RestaurantMenu> fetchAllMenuItems(int restaurantId){
		return restaurantMenuDAO.getAllMenuItems(restaurantId);
	}
}
