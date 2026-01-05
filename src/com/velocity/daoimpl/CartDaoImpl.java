package com.velocity.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.velocity.dao.CartDao;
import com.velocity.model.CartItem;
import com.velocity.model.Product;

public class CartDaoImpl implements CartDao{

	private List<CartItem> cartItems = new ArrayList<>();
	@Override
	public void addToCart(CartItem item) {
		cartItems.add(item);
		System.out.println("Product Added To Cart");
	}

	@Override
	public void viewCart() {
		
		if (cartItems.isEmpty()) {
	        System.out.println("Cart is empty");
	        return;
	    }

	    for (CartItem item : cartItems) {
	        System.out.println(
	            "Product Name : " + item.getProductName() +
	            "Product Quantity : " + item.getQuantity() + 
	            "Product Price : " + item.getPrice()
	        );
	    }
		
	}

}
