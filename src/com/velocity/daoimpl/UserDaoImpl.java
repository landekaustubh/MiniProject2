package com.velocity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.velocity.config.JdbcConfig;
import com.velocity.dao.UserDao;
import com.velocity.exception.ProjectException;
import com.velocity.model.Product;
import com.velocity.model.User;

public class UserDaoImpl implements UserDao{

	private static final String INSERT="INSERT INTO users"
			+ "(firstName,lastName,username,password,city,mailId,mobileNumber) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	private static final String LOGIN="SELECT * FROM users WHERE username = ? AND password = ? ";
	
	private static final String DISPLAY_PRODUCTS = "SELECT * FROM products";
	
	private static final String DISPLAY_USERS="SELECT * FROM users";
	
	private static final String USERNAME_EXIST="SELECT * FROM users WHERE username = ?";
	
	private static int loggedInUserId; 
	
	public static int getLoggedInUserId() {
	    return loggedInUserId;
	}

	@Override
	public int addingUser(User user) {
		try(Connection con = JdbcConfig.getConnection();
		PreparedStatement ps = con.prepareStatement(INSERT)){
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getCity());
			ps.setString(6, user.getMailId());
			ps.setInt(7, user.getMobileNumber());
		
			return ps.executeUpdate();
			
		}
		catch(Exception e) {
			throw new ProjectException("Not Inserted " + e.getMessage());
		}
	}
	@Override
	public boolean login(String username, String password) {
		try(Connection con = JdbcConfig.getConnection();
		  PreparedStatement ps = con.prepareStatement(LOGIN)){
			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
					  loggedInUserId = rs.getInt("userId"); 
				      System.out.println("login Successfully \n");
				      return true;
				}
				else {
					return false;
				}
			
		}
		catch(Exception e) {
			throw new ProjectException("Did Not Found " + e.getMessage());
		}
	}
	
	@Override
		public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		
		try(Connection con = JdbcConfig.getConnection();
				PreparedStatement ps = con.prepareStatement(DISPLAY_PRODUCTS)){
				
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						Product pro1 = new Product();
						pro1.setProductId(rs.getInt("productId"));
					    pro1.setProductName(rs.getString("productName"));
					    pro1.setProductDescription(rs.getString("productDescription"));
						pro1.setQuantity(rs.getInt("quantity"));
						pro1.setPrice(rs.getDouble("price"));
					    
						list.add(pro1);
					}
					
					return list;
				}
				catch(Exception e) {
					throw new ProjectException("Data is Not Available " + e.getMessage());
				}
		}
	@Override
	public List<User> findAll1() {
		
		List<User> list = new ArrayList<>();
		try(Connection con = JdbcConfig.getConnection();
		PreparedStatement ps = con.prepareStatement(DISPLAY_USERS)){
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user1 = new User(); 
			user1.setFirstName(rs.getString("firstName"));
			user1.setLastName(rs.getString("lastName"));
			user1.setUsername(rs.getString("username"));
			user1.setPassword(rs.getString("password"));
			user1.setCity(rs.getString("city"));
			user1.setMailId(rs.getString("mailId"));
			user1.setMobileNumber(rs.getInt("mobileNumber"));
			
			list.add(user1);
			
			}
			return list;
		}
		catch(Exception e) {
			throw new ProjectException("User Not Here" + e.getMessage());
		}
	}

	@Override
	public boolean usernameExist(String username) {
		try(Connection con = JdbcConfig.getConnection();
				PreparedStatement ps = con.prepareStatement(USERNAME_EXIST)){
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		}
		catch(Exception e) {
			throw new ProjectException("Error in username" + e.getMessage());
		}
	}
}
