package com.velocity.model;

import java.util.List;
import java.util.Scanner;

import com.velocity.cart.Cart;
import com.velocity.dao.OrderHistoryDao;
import com.velocity.dao.ProductDao;
import com.velocity.dao.UserDao;
import com.velocity.daoimpl.OrderHistoryDaoImpl;
import com.velocity.daoimpl.ProductDaoImpl;
import com.velocity.daoimpl.UserDaoImpl;
import com.velocity.exception.ProjectException;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String city;
	private String mailId;
	private int mobileNumber;

	// Getter and Setter Methods
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	// To String Method
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", city=" + city + ", mailId=" + mailId + ", mobileNumber=" + mobileNumber + "]";
	}

	public static void insertData() {
		User user1 = new User();

		UserDao insertSql = new UserDaoImpl();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter First Name : ");
		user1.setFirstName(sc.next());

		System.out.println("Enter Last Name : ");
		user1.setLastName(sc.next());

		boolean validate = false;
		
		while(!validate) {
		System.out.println("Enter Username : ");
		String username = sc.next();
		
		if(insertSql.usernameExist(username)) {
			System.out.println("Username Already Taken Try Another");
		}
		else {
			user1.setUsername(username);
			validate = true;
		}
	}

		System.out.println("Enter Password : ");
		user1.setPassword(sc.next());

		System.out.println("Enter City : ");
		user1.setCity(sc.next());

		System.out.println("Enter Mail ID : ");
		user1.setMailId(sc.next());

		System.out.println("Enter Mobile Number : ");
		user1.setMobileNumber(sc.nextInt());

		int data = insertSql.addingUser(user1);
		System.out.println("Registered " + data);
	}

	public static void login() {

		UserDao user2 = new UserDaoImpl();
		Scanner sc = new Scanner(System.in);

		boolean exit = true;

		System.out.println("Enter Username :");
		String a = sc.next();

		System.out.println("Enter Password :");
		String b = sc.next();

		if (a.equals("admin") && b.equals("admin")) {
			while (exit) {
				System.out.println("1. Add Product \n"
			                  + "2. Calculate Bill \n" 
						      + "3. Display amount \n"
				              + "4. Check Quantity \n"
						      + "5. Check Register User \n"
						      + "6. Check the particular user history \n" 
						      + "7. Exit \n");
				System.out.println("Enter Choice");
				int choice1 = sc.nextInt();
				switch (choice1) {
				case 1:
					Product.insertProduct();
					break;
				case 2:
					User.calculateBill();
					break;
				case 3:
					User.displayBillAmount();
					break;
				case 4:
					User.checkQuantity();
					break;
				case 5:
				    User.displayAllUsers();
				    break;
				case 6:
					User.checkUserHistoryById();
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
		} else {
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
					Product.displayAllProducts();
					break;
				case 2:
					Product.buyProduct();
					break;
				case 3:
					/*
					 * after selecting this option
					 * you can switch to the admin 
					 * and calculate bill and display
					 * the bill to end user
					 */
					Cart.viewCart();
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

	public static void purchaseItem(String username) {

		ProductDao productDao = new ProductDaoImpl();
		
	    OrderHistoryDao orderHistoryDao = new OrderHistoryDaoImpl();
	    
	    int userId = UserDaoImpl.getLoggedInUserId();
	    
		double totalAmount = 0;

		for (CartItem item : Cart.getCartItems()) {

			Product product = productDao.getProductById(item.getProductId());

			int remainingQty = product.getQuantity() - item.getQuantity();

			productDao.updateProductQuantity(item.getProductId(), remainingQty);

			orderHistoryDao.saveHistory(userId, item);
			
			totalAmount += item.getPrice() * item.getQuantity();
		}

		System.out.println("Username>> " + username);
		System.out.println("Total Bill Amount>> " + totalAmount);

		Cart.clearCart();
	}

	public static void displayAllUsers() {
		UserDao ud1 = new UserDaoImpl();
		List<User> list = ud1.findAll1();
		System.out.println(list);
	}
	
	public static void calculateBill() {

	    double totalAmount = 0;

	    if (Cart.getCartItems().isEmpty()) {
	        System.out.println("Cart is Empty ");
	        return;
	    }

	    for (CartItem item : Cart.getCartItems()) {
	        totalAmount += item.getPrice() * item.getQuantity();
	    }

	    Cart.setTotalBillAmount(totalAmount);

	    System.out.println("Admin will calculate the bill");
	}
	
	public static void displayBillAmount() {

	    double amount = Cart.getTotalBillAmount();
	    
	    if (amount == 0) {
	        System.out.println("Bill not calculated yet.");
	    } else {
	        System.out.println("Display the amount to End User>> " + amount);
	    }
	}
	
	public static void checkQuantity() {

	    Scanner sc = new Scanner(System.in);
	    ProductDao productDao = new ProductDaoImpl();

	    System.out.println("Enter Product Id>> ");
	    int productId = sc.nextInt();

	    Product product = productDao.getProductById(productId);

	    if (product == null) {
	        System.out.println("Product not found");
	    } else {
	        System.out.println("Quantity is>> " + product.getQuantity());
	    }
	}

	public static void checkUserHistoryById() {

	    Scanner sc = new Scanner(System.in);
	    OrderHistoryDao historyDao = new OrderHistoryDaoImpl();

	    System.out.println("Enter the user id>> ");
	    int userId = sc.nextInt();

	    List<CartItem> history = historyDao.getUserHistory(userId);

	    if (history.isEmpty()) {
	        System.out.println("No purchase history found");
	        return;
	    }

	    for (CartItem item : history) {
	        System.out.println("Product id>> " + item.getProductId());
	        System.out.println("Product Description>> " + item.getProductDescription());
	        System.out.println("Quantity>> " + item.getQuantity());
	        System.out.println("-------------------");
	    }
	}
}
