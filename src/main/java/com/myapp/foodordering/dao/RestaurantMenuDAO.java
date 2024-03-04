package com.myapp.foodordering.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.RestaurantMenu;

@Repository
public class RestaurantMenuDAO {

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

	public List<RestaurantMenu> getRestaurantMenu(int restaurantId) {
		System.out.println("getRestaurantMenu() invoked from DAO");

		createGetterSession();
		String restaurantMenuQuery = "FROM RestaurantMenu WHERE restaurantId=" + restaurantId;
		Query<RestaurantMenu> query = session.createQuery(restaurantMenuQuery, RestaurantMenu.class);
		List<RestaurantMenu> restaurantMenu = query.list();
		closeGetterSession();

		return restaurantMenu;
	}

	public boolean inserItemToMenu(RestaurantMenu restaurantMenu) {

		createSetterSession();
		session.persist(restaurantMenu);
		closeSetterSession();
		return false;
	}

	public boolean removeItemsFromMenu(int restaurantId, int menuItemId) {

		createSetterSession();
		String removeItemQuery = "DELETE FROM RestaurantMenu WHERE restaurantMenuItemId=" + menuItemId
				+ " AND restaurantId=" + restaurantId;
		Query query = session.createQuery(removeItemQuery);
		int removedCount = query.executeUpdate();
		System.out.println("removedCount: " + removedCount);
		closeSetterSession();
		if (removedCount > 0) {
			return true;
		}
		return false;
	}

	public RestaurantMenu getRestaurantMenuItemById(int menuItemId, int restaurantId) {
		createGetterSession();
		String hql = "FROM RestaurantMenu WHERE restaurantId=" + restaurantId + " AND restaurantMenuItemId="
				+ menuItemId;
		Query query = session.createQuery(hql, RestaurantMenu.class);
		RestaurantMenu restMenuItem = (RestaurantMenu) query.uniqueResult();
		closeGetterSession();
		return restMenuItem;
	}
	
	public List<RestaurantMenu> getAllMenuItems(int restaurantId){
		
		createGetterSession();
		String hql = "FROM RestaurantMenu WHERE restaurantId=:restaurantId";
		Query query = session.createQuery(hql, RestaurantMenu.class);
		query.setParameter("restaurantId", restaurantId);
		List<RestaurantMenu> restaurantMenu = query.list();
		closeGetterSession();
		
		return restaurantMenu;
	}

}
