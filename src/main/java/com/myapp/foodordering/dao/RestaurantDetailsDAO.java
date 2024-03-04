package com.myapp.foodordering.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.RestaurantDetails;

@Repository
public class RestaurantDetailsDAO {

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

	public boolean insertRestaurantDetails(RestaurantDetails restaurantDetails) {

		createSetterSession();
		session.persist(restaurantDetails);
		closeSetterSession();

		return false;
	}

	public RestaurantDetails getRestaurantDetails(int restaurantId) {

		createGetterSession();
		RestaurantDetails restaurantDetails = session.get(RestaurantDetails.class, restaurantId);
		closeGetterSession();
		return restaurantDetails;
	}
	
	public RestaurantDetails getRestaurantDetailsByEmail(String email) {
		createGetterSession();
		String hql = "FROM RestaurantDetails WHERE restaurantEmail=:email";
		Query query = session.createQuery(hql, RestaurantDetails.class);
		query.setParameter("email", email);
		RestaurantDetails restaurantDetails = (RestaurantDetails) query.uniqueResult();
		closeGetterSession();
		return restaurantDetails;
	}

}
