package com.velocity.test;

import java.util.List;
import java.util.Scanner;

import com.velocity.cart.Cart;
import com.velocity.dao.ProductDao;
import com.velocity.dao.UserDao;
import com.velocity.daoimpl.ProductDaoImpl;
import com.velocity.daoimpl.UserDaoImpl;
import com.velocity.exception.ProjectException;
import com.velocity.model.CartItem;
import com.velocity.model.Product;
import com.velocity.model.User;

public class Test {
	
public static void main(String[] args) {
		
	try {
		Scanner sc = new Scanner(System.in);
		
		boolean exit = true;
		
		while(exit)
		{
			System.out.println("1. User Register\n"
					+ "2. User Login\n"
					+ "3. Guest\n"
					+ "4. Exit ");
			System.out.println("Enter choice :");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				insertData();
				break;
			case 2:
				login();
	    		break;
			case 3:
				displayAllProducts();
				break;
			case 4: 
				exit = false;
				break;
			}
		}
	}
	catch(Exception e) {
		throw new ProjectException("Invalid Input" + e.getMessage());
	}
}
	
	public static void insertData() {
		User user1 = new User();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter First Name : ");
		user1.setFirstName(sc.next());
		
		System.out.println("Enter Last Name : ");
		user1.setLastName(sc.next());
		
		System.out.println("Enter Username : ");
		user1.setUsername(sc.next());
		
		System.out.println("Enter Password : ");
		user1.setPassword(sc.next());
		
		System.out.println("Enter City : ");
		user1.setCity(sc.next());
		
		System.out.println("Enter Mail ID : ");
		user1.setMailId(sc.next());
		
		System.out.println("Enter Mobile Number : ");
		user1.setMobileNumber(sc.nextInt());
		
		UserDao insertSql = new UserDaoImpl();
		int data = insertSql.addingUser(user1);
		System.out.println("Registered " + data);
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
	
	public static void login() {
		
		UserDao user2 = new UserDaoImpl();
		Scanner sc = new Scanner(System.in);
		
		boolean exit = true;
		
		System.out.println("Enter Username :");
		String a = sc.next();
		
		System.out.println("Enter Password :");
		String b = sc.next();
		
		if(a.equals("admin") && b.equals("admin")) {
			while(exit) {
				System.out.println("1. Add Product \n"
						+ "2. Calculate Bill \n"
						+ "3. Display amount \n"
						+ "4. Check Quantity \n"
						+ "5. Check Register User \n"
						+ "6. Check the particular user history \n"
						+ "7. Exit \n");
				System.out.println("Enter Choice");
				int choice1 = sc.nextInt();
				switch(choice1) {
				case 1:
					insertProduct();
					break;
				case 7:
					exit = false;
					break;
				}
			}
		}
		boolean login = user2.login(a, b);

	    if (!login) {
	        System.out.println("You are not registered Yet");
	    }else {
	    while (exit) {
	        System.out.println("1. User view Product item\n"
	                + "2. Buy Product\n"
	                + "3. View Cart\n"
	                + "4. Purchase the item\n"
	                + "5. Exit");

	        System.out.println("Enter Choice :");
	        int choice2 = sc.nextInt();

	        switch (choice2) {
	            case 1:
	            	displayAllProducts();
	                break;
	            case 2:
	            	buyProduct();
	            	break;
	            case 3:
	            	viewCart();
	            	break;
	            case 4:
	            	purchaseItem(a);
	            	break;
	            case 5:
	                exit = false;
	                break;
	        }
	    }
    }
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
	        viewCart();
	    }
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
	
	public static void purchaseItem(String username) {

	    ProductDao productDao = new ProductDaoImpl();
	    double totalAmount = 0;

	    for (CartItem item : Cart.getCartItems()) {

	        Product product = productDao.getProductById(item.getProductId());

	        int remainingQty = product.getQuantity() - item.getQuantity();

	        productDao.updateProductQuantity(item.getProductId(), remainingQty);

	        totalAmount += item.getPrice() * item.getQuantity();
	    }

	    System.out.println("Username>> " + username);
	    System.out.println("Total Bill Amount>> " + totalAmount);

	    Cart.clearCart();
	}
}
