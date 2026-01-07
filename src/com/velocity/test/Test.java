package com.velocity.test;

import java.util.Scanner;

import com.velocity.exception.ProjectException;
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
				User.insertData();
				break;
			case 2:
				/*
				 * for login admin username=admin and password=admin
				 * you can get admins options.
				 * for user if exist you can directly login
				 * otherwise you have to register first then 
				 * you can get users options.
				 * you can also use sample data.
				 */
				User.login();
	    		break;
			case 3:
				Product.displayAllProducts();
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
}
