package com.myapp.foodordering.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTest {

	@Id
	private int age;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
