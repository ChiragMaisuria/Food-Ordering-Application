package com.myapp.foodordering.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.RestaurantOrders;

@Repository
public class RestaurantOrdersDAO {

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

	public List<Integer> getAllRestaurantOrderId(int restaurantId){
		
		createGetterSession();
		String hql = "FROM RestaurantOrders WHERE restaurantId=" + restaurantId;
		Query query = session.createQuery(hql, RestaurantOrders.class);
		List<RestaurantOrders> restaurantOrders = query.list();
		Map<Integer, Integer> restaurantOrderId = new HashMap<>();
		for(RestaurantOrders restOrder: restaurantOrders) {
			restaurantOrderId.put(restOrder.getOrderId(), 0);
		}
		closeGetterSession();
		
		List<Integer> restaurantOrderIdList = new ArrayList<Integer>(restaurantOrderId.keySet());
		
		return restaurantOrderIdList;
		
	}

}
