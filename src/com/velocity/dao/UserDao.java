package com.velocity.dao;

import java.util.List;

import com.velocity.model.Product;
import com.velocity.model.User;

public interface UserDao {

	int addingUser(User user);
	
	boolean login(String username,String password);
	
	List<Product> findAll();
	
}
