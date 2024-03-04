package com.myapp.foodordering.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.myapp.foodordering.model.LoginInformation;

@Repository
public class LoginInformationDAO {

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

	public String checkCustomerLogin(LoginInformation loginInfo) {
		System.out.println("checkCustomerLogin() invoked from DAO");
		
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

}
