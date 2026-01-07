package com.velocity.cart;

import java.util.ArrayList;
import java.util.List;
import com.velocity.model.CartItem;

public class Cart {

	    private static List<CartItem> cartList = new ArrayList<>();

	    private static double totalBillAmount = 0;
	    
	    public static void addItem(CartItem item) {
	        cartList.add(item);
	    }

	    public static List<CartItem> getCartItems() {
	        return cartList;
	    }
	    
	    public static void setTotalBillAmount(double amount) {
	        totalBillAmount = amount;
	    }

	    public static double getTotalBillAmount() {
	        return totalBillAmount;
	    }
	    
	    public static void viewCart() {

		    if (Cart.getCartItems().isEmpty()) {
		        System.out.println("Cart is Empty");
		        return;
		    }

		    for (CartItem item : Cart.getCartItems()) {
		        System.out.println(item);
		    }

		    System.out.println("Product item has been added to cart");
		}
}
