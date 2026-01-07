package com.velocity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.velocity.config.JdbcConfig;
import com.velocity.dao.OrderHistoryDao;
import com.velocity.exception.ProjectException;
import com.velocity.model.CartItem;

public class OrderHistoryDaoImpl implements OrderHistoryDao{

	private static final String SAVE_HISTORY="INSERT INTO orderHistory"
		+ "(userId, productId, productDescription, quantity) VALUES (?,?,?,?)";
	
	private static final String USER_HISTORY="SELECT * FROM orderHistory WHERE userId = ?";
	@Override
	public void saveHistory(int userid, CartItem item) {
		try(Connection con = JdbcConfig.getConnection();
		PreparedStatement ps = con.prepareStatement(SAVE_HISTORY)){
		
			ps.setInt(1, userid);
			ps.setInt(2, item.getProductId());
			ps.setString(3, item.getProductDescription());
			ps.setInt(4, item.getQuantity());
			
			ps.executeUpdate();
		}
		catch(Exception e) {
			throw new ProjectException("Not Inserted " + e.getMessage());
		}
		
	}

	@Override
	public List<CartItem> getUserHistory(int userid) {
		 List<CartItem> list = new ArrayList<>();
		 
		 try(Connection con = JdbcConfig.getConnection();
		 PreparedStatement ps = con.prepareStatement(USER_HISTORY)){
		
			 ps.setInt(1, userid);
			 
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 CartItem cart = new CartItem();
				 
				 cart.setProductId(rs.getInt("productId"));
				 cart.setProductDescription(rs.getString("productDescription"));
				 cart.setQuantity(rs.getInt("quantity"));
				 
				 list.add(cart);
			 }
			 return list;
		 }
		 catch(Exception e) {
			 throw new ProjectException("User History Not Found " + e.getMessage());
		 }
	}

}
