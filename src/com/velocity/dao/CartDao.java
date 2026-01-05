package com.velocity.dao;

import com.velocity.model.CartItem;

public interface CartDao {
	
	void addToCart(CartItem item);
	
    void viewCart();

}
