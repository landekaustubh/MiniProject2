package com.velocity.test;

import java.util.Scanner;

import com.velocity.dao.ProductDao;
import com.velocity.dao.UserDao;
import com.velocity.daoimpl.ProductDaoImpl;
import com.velocity.daoimpl.UserDaoImpl;
import com.velocity.exception.ProjectException;
import com.velocity.model.Product;
import com.velocity.model.User;

public class Test {
	
	public static void main(String[] args) {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
		    boolean exit = true;
		    
		    while(exit) {
		    	System.out.println("1. Register User \n"
		    			+ "2. Login User \n"
		    			+ "3. View the Products \n"
		    			+ "4. Buy Product \n"
		    			+ "5. View Cart \n"
		    			+ "6. Purchase the Item \n"
		    			+ "7. Exit \n");

		    	System.out.println("Enter Choice : ");
		    	int choice = sc.nextInt();
		    	switch(choice) {
		    	case 1:
		    		insertData();
		    		break;
		    	case 2:
		    		UserDao user2 = new UserDaoImpl();
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
		    				}
		    			}
		    			break;
		    		}
		    		else {
		    			user2.login(a, b);
		    		}
		    		
		    		break;
		    	case 15:
		    		exit = false;
		    		break;
		    	}
		    }
		}
		catch(Exception e) {
			throw new ProjectException("Somthing Fishy" + e.getMessage());
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
		pro.setProductName(sc.next());
		
		System.out.println("Enter Product Description : ");
		pro.setProductDescription(sc.next());
		
		System.out.println("Enter Quantity : ");
		pro.setQuantity(sc.nextInt());
		
		System.out.println("Enter Price : ");
		pro.setPrice(sc.nextDouble());
		
		ProductDao pro1 = new ProductDaoImpl();
		int insertPro = pro1.addProduct(pro);
		System.out.println("Product Added " + insertPro);
	}
}
