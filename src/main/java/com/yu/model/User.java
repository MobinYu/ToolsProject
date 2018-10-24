package com.yu.model;

public class User {
	
	private String id;
	private String age;
	private String name;
	
	
	
	public User() {
		super();
	}

	public User(String id, String age, String name) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return id + "," + age + "," + name;
	}
}
