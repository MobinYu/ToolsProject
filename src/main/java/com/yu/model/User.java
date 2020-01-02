package com.yu.model;

public class User {
	
	private String id;
	private String name;
	private int age;
	
	
	
	public User() {
		super();
	}

	public User(String id, int age, String name) {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
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
