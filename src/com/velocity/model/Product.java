package com.velocity.model;

import java.util.List;
import java.util.Scanner;

import com.velocity.cart.Cart;
import com.velocity.dao.ProductDao;
import com.velocity.dao.UserDao;
import com.velocity.daoimpl.ProductDaoImpl;
import com.velocity.daoimpl.UserDaoImpl;

public class Product {

	private int productId;
	private String productName;
	private String productDescription;
	private int quantity;
	private double price;
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "\nProduct Id >> " + productId
		      +"\nProduct Name >> " + productName 
			  +"\nProduct Description >> " + productDescription
			  +"\nAvailable Quantity >> " + quantity
			  +"\nPrice >> " + price + "\n-----------";
	}
	
	public static void insertProduct() {
		Product pro = new Product();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Product Name : ");
		pro.setProductName(sc.nextLine());
		
		System.out.println("Enter Product Description : ");
		pro.setProductDescription(sc.nextLine());
		
		System.out.println("Enter Quantity : ");
		pro.setQuantity(sc.nextInt());
		
		System.out.println("Enter Price : ");
		pro.setPrice(sc.nextDouble());
		
		ProductDao pro1 = new ProductDaoImpl();
		int insertPro = pro1.addProduct(pro);
		System.out.println("Product Added " + insertPro);
	}
	
	public static void displayAllProducts() {
		UserDao ud1 = new UserDaoImpl();
 	   List<Product> list = ud1.findAll();
 	   System.out.println(list);
	}
	
	public static void buyProduct() {

	    Scanner sc = new Scanner(System.in);
	    ProductDao productDao = new ProductDaoImpl();

	    System.out.println("Enter the product id to buy product>>");
	    int productId = sc.nextInt();

	    System.out.println("Enter the quantity>>");
	    int qty = sc.nextInt();

	    Product product = productDao.getProductById(productId);

	    if (product == null) {
	        System.out.println("Product not available");
	        return;
	    }

	    if (qty > product.getQuantity()) {
	        System.out.println("Insufficient quantity");
	        return;
	    }

	    CartItem item = new CartItem();
	    item.setProductId(product.getProductId());
	    item.setProductName(product.getProductName());
	    item.setQuantity(qty);
	    item.setPrice(product.getPrice());

	    Cart.addItem(item);

	    System.out.println("Do you want to view cart (Yes/No)");
	    
	    String option = sc.next();

	    if (option.equalsIgnoreCase("yes")) {
	        Cart.viewCart();
	    }
	}
}
