package com.velocity.model;

public class CartItem {

	private Product pro;
	private int productId;
	private String productName;
	private String productDescription;
	private int quantity;
	private double price;

	public CartItem(Product pro, int quantity) {
		this.pro = pro;
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return pro.getPrice() * quantity;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getPrice() {
		return price;
	}
}
