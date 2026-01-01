package com.velocity.dao;

import java.util.List;

import com.velocity.model.User;

public interface UserDao {

	int addingUser(User user);
	
	void login(String username,String password);
}
