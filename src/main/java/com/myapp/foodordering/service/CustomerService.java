package com.myapp.foodordering.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myapp.foodordering.dao.FoodOrderingDBAccess;
import com.myapp.foodordering.dao.LoginInformationDAO;
import com.myapp.foodordering.dao.RestaurantDetailsDAO;
import com.myapp.foodordering.dao.RestaurantMenuDAO;
import com.myapp.foodordering.dao.RestaurantOrdersDAO;
import com.myapp.foodordering.dao.UserInformationDAO;
import com.myapp.foodordering.dao.UserOrdersDAO;
import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.RestaurantMenu;
import com.myapp.foodordering.model.UserInformation;
import com.myapp.foodordering.model.UserOrders;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	@Autowired
	private FoodOrderingDBAccess dbAccess;

	@Autowired
	private LoginInformationDAO loginInfoDAO;
	@Autowired
	private UserInformationDAO userInfoDAO;
	@Autowired
	private RestaurantDetailsDAO restaurantDetailsDAO;
	@Autowired
	private RestaurantMenuDAO restaurantMenuDAO;
	@Autowired
	private UserOrdersDAO userOrdersDAO;
	@Autowired
	private RestaurantOrdersDAO restaurantOrdersDAO;

	public boolean addCustomerDetails(UserInformation userInfo) {
		userInfoDAO.insertCustomerDetails(userInfo);
		return false;
	}

	public String checkCustomerLogin(LoginInformation loginInfo) {

//		Sanitize and Validate login Info
		return loginInfoDAO.checkCustomerLogin(loginInfo);
//		if("valid" && "authenticated") {
//			return "success";
//		}else if("valid" && "not authenticated") {
//			return "checkEmailOrPassword";
//		}else {
//			return "not valid";
//		}
	}

	public List fetchAllRestaurantsDetails() {
		return restaurantDetailsDAO.getAllRestaurantDetails();
	}

	public List fetchRestaurantMenu(int restaurantId) {
		return restaurantMenuDAO.getRestaurantMenu(restaurantId);

	}

	public boolean addItemsToCart(int restaurantItemId, int restaurantId, int userId) {

		boolean isSameRestaurantinCart = false;
		boolean isItemsInCart = false;

		List<UserOrders> userOrdersList = userOrdersDAO.getAllOrders("in-cart", userId);

		if (userOrdersList.size() > 0) {
			isItemsInCart = true;
			if (userOrdersList.get(0).getRestaurantId() == restaurantId) {
				isSameRestaurantinCart = true;
			}
		}

		System.out.println("Random orderId Finding!!");

		UserOrders userOrders = new UserOrders();
		if (isItemsInCart && isSameRestaurantinCart) {
			userOrders.setOrderId(userOrdersList.get(0).getOrderId());
		} else if (!isItemsInCart) {
//			Generate new orderId
			List<Integer> userOrderIds = userOrdersDAO.getAllUsersOrderId(userId);
			List<Integer> restaurantOrderIds = restaurantOrdersDAO.getAllRestaurantOrderId(restaurantId);

			Random random = new Random();
			int randomOrderId = 0;
//			do {
//			randomOrderId = random.nextInt(9001) + 1000;
//			if(userOrderIds.size() > 0 || )
//			}while(userOrderIds.contains(randomOrderId) || restaurantOrderIds.contains(randomOrderId));
//			
			for (;;) {
				int userContains = 1;
				int restaurantContains = 1;
				int userOrdersIdsLength = userOrderIds.size();
				int restaurantOrdersIdsLength = restaurantOrderIds.size();

				randomOrderId = random.nextInt(9001) + 1000;

				if (!(userOrderIds.size() > 0) && !(restaurantOrderIds.size() > 0)) {
					break;
				}
				
				if(userOrdersIdsLength <= 0) {
					userContains = 0;
				}
				if(restaurantOrdersIdsLength <= 0) {
					restaurantContains = 0;
				}
				
				
				if (userOrderIds.size() > 0) {
					if (!userOrderIds.contains(randomOrderId)) {
						userContains = 0;
					}
				}
				if (restaurantOrderIds.size() > 0) {
					if (!restaurantOrderIds.contains(randomOrderId)) {
						restaurantContains = 0;
					}
				}

				if (userContains == 0 && restaurantContains == 0) {
					break;
				}
			}
			System.out.println("Generated random OrderId = " + randomOrderId);
			userOrders.setOrderId(randomOrderId);
		} else {
			return false;
		}

		RestaurantMenu restMenuItem = restaurantMenuDAO.getRestaurantMenuItemById(restaurantItemId, restaurantId);
		userOrders.setUserId(userId);
		userOrders.setRestaurantId(restaurantId);
		userOrders.setOrderStatus("in-cart");
		userOrders.setOrderItemId((int) restMenuItem.getRestaurantMenuItemId());
		userOrders.setOrderItemName(restMenuItem.getRestaurantMenuItemName());
		userOrders.setItemPrice(restMenuItem.getRestaurantMenuItemPrice());
		userOrders.setOrderTotal(0);
		userOrders.setOrderDate(new Date());

		return userOrdersDAO.insertItemsToCart(userOrders);
	}

	public boolean removeItemFromCart(int itemId, int userId) {
		return userOrdersDAO.deleteItemsFromCart(itemId, userId);
	}

	public boolean addOrderDetails() {
		return userOrdersDAO.insertOrderDetails();
	}

	public List viewOrders(String status, int userId) {
		return userOrdersDAO.getAllOrders(status, userId);
	}

	public List<UserOrders> fetchCartItems(int userId) {
		return userOrdersDAO.getAllOrders("in-cart", userId);

	}

	public boolean updateOrderStatus(int userId, int orderId, String previousStatus, String updateToStatus) {
		return userOrdersDAO.updateOrder(userId, orderId, previousStatus, updateToStatus);
	}

	public UserInformation fetchUserDetailsById(int userId) {
		return userInfoDAO.getAllUserDetails(userId);
	}
	
	public UserInformation fetchUserDetailsByEmail(String email) {
		return userInfoDAO.getUserDetailsByEmail(email);
	}

}
