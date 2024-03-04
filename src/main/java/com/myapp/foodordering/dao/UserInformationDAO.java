package com.myapp.foodordering.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.UserInformation;

@Repository
public class UserInformationDAO {

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

	public UserInformation getAllUserDetails(int userId) {
		System.out.println("getAllUserDetails() invoked from DAO");

		UserInformation userInfo = null;
		return userInfo;

	}
	
	public UserInformation getUserDetailsByEmail(String email) {
		UserInformation userInfo;
		
		createGetterSession();
		String hql = "FROM UserInformation WHERE email = :email";
		Query<UserInformation> query = session.createQuery(hql, UserInformation.class);
		query.setParameter("email", email);
		userInfo = query.uniqueResult();
		closeGetterSession();
		
		return userInfo;
	}

}
