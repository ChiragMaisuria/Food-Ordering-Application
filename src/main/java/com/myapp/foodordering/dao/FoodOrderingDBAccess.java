package com.myapp.foodordering.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.RestaurantDetails;
import com.myapp.foodordering.model.RestaurantMenu;
import com.myapp.foodordering.model.RestaurantOrders;
import com.myapp.foodordering.model.UserInformation;
import com.myapp.foodordering.model.UserOrders;

@Repository
public class FoodOrderingDBAccess {

	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.buildSessionFactory();
	private static Session session;

	private static void createSetterSession() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	private static void closeSetterSession() {
		session.getTransaction().commit();
		session.close();
	}

	private static void createGetterSession() {
		session = sessionFactory.openSession();
	}

	private static void closeGetterSession() {
		session.close();
	}

	public boolean insertCustomerDetails(UserInformation userInfo) {
		System.out.println("Hello this is insertCustomerDetails() called....");
		userInfo.getFname();

		createSetterSession();
		session.persist(userInfo);
		LoginInformation loginInfo = new LoginInformation();
		loginInfo.setEmail(userInfo.getEmail());
		loginInfo.setPassword(userInfo.getPassword());
		loginInfo.setUserRole("customer");
		session.persist(loginInfo);
		closeSetterSession();

		return false;
	}

	public String checkCustomerLogin(LoginInformation loginInfo) {
		System.out.println("checkCustomerLogin() invoked from DAO");
//		createSetterSession();
//		RestaurantDetails rs = new RestaurantDetails();
//		rs.setRestaurantAddressCity("1");
//		rs.setRestaurantCoverPhoto("2");
//		rs.setRestaurantCuisine("3");
//		rs.setRestaurantEmail("4");
//		rs.setRestaurantId(5);
//		rs.setRestaurantName("6");
//		rs.setRestaurantPhone("7");
//		session.persist(rs);
//		closeSetterSession();
		createGetterSession();
		if (session.get(LoginInformation.class, loginInfo.getEmail()) == null) {
			return "Invalid";
		} else if (session.get(LoginInformation.class, loginInfo.getEmail()) != null) {
			LoginInformation loginIn = (LoginInformation) session.get(LoginInformation.class, loginInfo.getEmail());
			if (!loginInfo.getPassword().equals(loginIn.getPassword())) {
				return "Unauthorized";
			} else {
				return "Authorized";
			}
		}
		closeGetterSession();

		return "Invalid";
	}

	public List<RestaurantDetails> getAllRestaurantDetails() {
		System.out.println("getAllRestaurantDetails() invoked from DAO");

		createGetterSession();
		String allRestaruantsQuery = "FROM RestaurantDetails";
		Query query = session.createQuery(allRestaruantsQuery);
		System.out.println(query.getResultList());

		List<RestaurantDetails> restaurantDetails = query.getResultList();
		for (RestaurantDetails rs : restaurantDetails) {
			System.out.println(rs.getRestaurantEmail());
		}
		closeGetterSession();
		return restaurantDetails;

	}

	public List<RestaurantMenu> getRestaurantMenu(int restaurantId) {
		System.out.println("getRestaurantMenu() invoked from DAO");

		createGetterSession();
		String restaurantMenuQuery = "FROM RestaurantMenu WHERE restaurantId=" + restaurantId;
		Query query = session.createQuery(restaurantMenuQuery);
		List<RestaurantMenu> restaurantMenu = query.list();
		closeGetterSession();

		return restaurantMenu;
	}

	public boolean insertItemsToCart(int restaurantItemId, int restaurantId, int userId) {

		createSetterSession();
		UserOrders userOrder = new UserOrders();
		userOrder.setOrderId(111);
		userOrder.setUserId(userId);
		userOrder.setRestaurantId(restaurantId);
		userOrder.setOrderStatus("in-cart");
		List<RestaurantMenu> restaurantMenu = getRestaurantMenu(restaurantId);
		userOrder.setOrderItemName(null);
//		session.persist();
		closeSetterSession();

		System.out.println("insertItemsToCart() invoked from DAO");
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateOrder(int orderId) {
		System.out.println("updateOrder() invoked from DAO");

		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean deleteItemsFromCart(int itemId) {
		System.out.println("deleteItemsFromCart() invoked from DAO");
		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean insertOrderDetails() {
		System.out.println("insertOrderDetails() invoked from DAO");
		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List getAllOrders(String status) {
		System.out.println("getAllOrders() invoked from DAO");

		List allOrders = null;
		return allOrders;

	}

	public UserInformation getAllUserDetails(int userId) {
		System.out.println("getAllUserDetails() invoked from DAO");

		UserInformation userInfo = null;
		return userInfo;

	}
//________________________________________________________________________________________________
//________________________________________________________________________________________________

	public boolean insertRestaurantDetails(RestaurantDetails restaurantDetails) {
		System.out.println("hello");

		createSetterSession();
		session.persist(restaurantDetails);
		closeSetterSession();

		return false;
	}

	public String authorizeRestaurantLogin(LoginInformation loginInfo) {
		createGetterSession();
		if (session.get(LoginInformation.class, loginInfo.getEmail()) == null) {
			return "Invalid";
		} else if (session.get(LoginInformation.class, loginInfo.getEmail()) != null) {
			LoginInformation loginIn = (LoginInformation) session.get(LoginInformation.class, loginInfo.getEmail());
			if (!loginInfo.getPassword().equals(loginIn.getPassword())) {
				return "Unauthorized";
			} else {
				return "Authorized";
			}
		}
		closeGetterSession();
		return "Invalid";
	}

	public RestaurantDetails getRestaurantDetails(int restaurantId) {

		createGetterSession();
		RestaurantDetails restaurantDetails = session.get(RestaurantDetails.class, restaurantId);
		closeGetterSession();
		return restaurantDetails;
	}

	public boolean inserItemToMenu(RestaurantMenu restaurantMenu) {

		createSetterSession();
		session.persist(restaurantMenu);
		closeSetterSession();
		return false;
	}

	public boolean removeItemsFromMenu(int restaurantId, int menuItemId) {

		createGetterSession();
		String removeItemQuery = "DELETE FROM RestaurantMenu WHERE restaurantMenuItemId=" + menuItemId
				+ " AND restaurantId=" + restaurantId;
		Query query = session.createQuery(removeItemQuery);
		int removedCount = query.executeUpdate();
		closeGetterSession();
		if (removedCount > 0) {
			return true;
		}
		return false;
	}

	public boolean updateOrderStatus(int orderId, String status) {

		createGetterSession();
		String updateStatus = "UPDATE RestaurantOrders SET orderStatus=" + status + " WHERE orderId=" + orderId;
		Query query = session.createQuery(updateStatus);
		int rowsUpdated = query.executeUpdate();
		closeGetterSession();

		if (rowsUpdated > 0) {
			return true;
		}
		return false;
	}

	public List<RestaurantOrders> getRestaurantOrders(int restaurantId, String status) {
		createGetterSession();

		List<RestaurantOrders> restaurantOrders = null;
		String ordersQuery;
		if (status.equals("all")) {
			ordersQuery = "FROM RestaurantOrders WHERE restaurantId=" + restaurantId;

		} else {
			ordersQuery = "FROM RestaurantOrders WHERE restaurantId=" + restaurantId + " AND orderStatus=" + status;
		}
		Query query = session.createQuery(ordersQuery);
		restaurantOrders = query.getResultList();
		closeGetterSession();
		return restaurantOrders;
	}

}
