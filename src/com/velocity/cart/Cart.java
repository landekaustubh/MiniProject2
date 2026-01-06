package com.velocity.cart;

import java.util.ArrayList;
import java.util.List;
import com.velocity.model.CartItem;

public class Cart {

	 private static List<CartItem> cartList = new ArrayList<>();

	    public static void addItem(CartItem item) {
	        cartList.add(item);
	    }

	    public static List<CartItem> getCartItems() {
	        return cartList;
	    }

	    public static void clearCart() {
	        cartList.clear();
	    }
}
