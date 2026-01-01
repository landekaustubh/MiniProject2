package com.velocity.test;

import java.util.Scanner;

import com.velocity.dao.UserDao;
import com.velocity.daoimpl.UserDaoImpl;
import com.velocity.exception.ProjectException;
import com.velocity.model.User;

public class Test1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = true;
		
		while(exit) {
			System.out.println("1. User "
					+ "2.Admin ");
			System.out.println("Choice");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("User Here");
				break;
			case 2:
				System.out.println("Admin Here");
				System.out.println("Type hello");
				if(sc.next().equals("hello")) {
					while(exit) {
						System.out.println("1. Admin"
								+ "2.Product");
						System.out.println("Enter Choice");
						int choice1 = sc.nextInt();
						switch(choice1) {
						case 1:
							System.out.println("Admin");
							break;
						case 2:
							System.out.println("Product");
							break;
						}
					}
				}
				else {
					System.out.println("Not Right Syntax");
				}
				break;
			}
		}
	}
}

