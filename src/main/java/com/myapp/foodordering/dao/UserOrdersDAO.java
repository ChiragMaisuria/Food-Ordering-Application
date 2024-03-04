package com.myapp.foodordering.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.RestaurantMenu;
import com.myapp.foodordering.model.RestaurantOrders;
import com.myapp.foodordering.model.UserOrders;

@Repository
public class UserOrdersDAO {

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

	public boolean insertItemsToCart(UserOrders userOrders) {

		createSetterSession();
		session.persist(userOrders);
		closeSetterSession();

		System.out.println("insertItemsToCart() invoked from DAO");
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateOrder(int userId, int orderId, String previousStatus, String updateToStatus) {
		System.out.println("updateOrder() invoked from DAO");

		List<UserOrders> userOrders = getAllOrders(previousStatus, userId);

		createSetterSession();
		String userHql = "UPDATE UserOrders SET orderStatus = :status WHERE orderId = :orderId";
		Query query = session.createQuery(userHql);
		query.setParameter("status", updateToStatus);
		query.setParameter("orderId", orderId);
		int rowsUpdated = query.executeUpdate();
		closeSetterSession();
//		________________________________________________

		createSetterSession();
		double orderTotal = 0;
		for (UserOrders userOrder : userOrders) {
			orderTotal += userOrder.getItemPrice();
		}

		Date orderPlacedTime = new Date();

		for (UserOrders userOrder : userOrders) {

			RestaurantOrders restaurantOrders = new RestaurantOrders();
			restaurantOrders.setOrderId(orderId);
			restaurantOrders.setUserId(userId);
			restaurantOrders.setRestaurantId(rowsUpdated);
			restaurantOrders.setOrderStatus("waiting");
			restaurantOrders.setOrderItems(userOrder.getOrderItemName());
			restaurantOrders.setItemPrice(userOrder.getItemPrice());
			restaurantOrders.setOrderTotal(orderTotal);
			restaurantOrders.setOrderDate(orderPlacedTime);

			session.persist(restaurantOrders);
		}
		closeSetterSession();

		try {
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean deleteItemsFromCart(int itemId, int userId) {
		System.out.println("deleteItemsFromCart() invoked from DAO");

		createSetterSession();
		String hql = "DELETE FROM UserOrders WHERE userId=" + userId + " AND id=" + itemId;
		System.out.println(hql);
		Query query = session.createQuery(hql);
		int rowsUpdated = query.executeUpdate();
		System.out.println("rowsUpdated: " + rowsUpdated);
		closeSetterSession();
		if (rowsUpdated > 0) {
			return true;
		} else {
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

	public List<UserOrders> getAllOrders(String status, int userId) {
		System.out.println("getAllOrders() invoked from DAO");

		createGetterSession();
		String hql = "FROM UserOrders WHERE userId=" + userId + " AND orderStatus='" + status + "'";
		Query<UserOrders> query = session.createQuery(hql, UserOrders.class);
		List<UserOrders> userOrders = query.list();
//		System.out.println(userOrders.get(0).getId());
		closeGetterSession();
		return userOrders;

	}

	public List<Integer> getAllUsersOrderId(int userId) {

		createGetterSession();
		String hql = "FROM UserOrders WHERE userId=" + userId;
		Query query = session.createQuery(hql, UserOrders.class);
		List<UserOrders> userOrders = query.list();
		System.out.println(userOrders.isEmpty());
		Map<Integer, Integer> userOrderId = new HashMap<>();
		for (UserOrders userOrder : userOrders) {
			userOrderId.put(userOrder.getOrderId(), 0);
		}
		closeGetterSession();

		List<Integer> userOrderIdList = new ArrayList<Integer>(userOrderId.keySet());

		return userOrderIdList;

	}

}
