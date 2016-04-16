package com.andieguo.spring.dao;

import java.util.List;

import com.andieguo.spring.bean.User;

public interface UserDao {

	public List<User> findAll();
	
	public User findById(int id);
	
	public int save(User user);
	
	public int delete(int id);
	
	public int update(User user);
}
