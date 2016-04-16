package com.andieguo.spring.bean;

public class User {

	private Integer uid;
	private String name;
	private Integer age;
	private int gender;
	
	public User() {
		super();
	}
	
	public User(String name, Integer age, int gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public User(Integer uid, String name, Integer age, int gender) {
		super();
		this.uid = uid;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
}
