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
	
	private static final String DISPLAY = "SELECT * FROM products";
	
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
		List<Product> pro1 = new ArrayList<>();
		
		try(Connection con = JdbcConfig.getConnection();
				PreparedStatement ps = con.prepareStatement(DISPLAY)){
				
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						Product pro2 = new Product();
						pro2.setProductId(rs.getInt("productId"));
					    pro2.setProductName(rs.getString("productName"));
					    pro2.setProductDescription(rs.getString("productDescription"));
						pro2.setQuantity(rs.getInt("quantity"));
						pro2.setPrice(rs.getDouble("price"));
					    
						pro1.add(pro2);
					}
					
					return pro1;
				}
				catch(Exception e) {
					throw new ProjectException("Data is Not Available " + e.getMessage());
				}
		}
}
