package com.study.reflact;

public class User {
	
	private String name;
	private String sex;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public User() {
		super();
	}
	
	public User(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	private String selfToString(String name, String sex, int age){
		return "[name]=" + name
				+"[sex]=" + sex
				+"[age]=" + age;
	}
	
}
