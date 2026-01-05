package com.velocity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.velocity.config.JdbcConfig;
import com.velocity.dao.ProductDao;
import com.velocity.exception.ProjectException;
import com.velocity.model.Product;

public class ProductDaoImpl implements ProductDao{

	private static final String Insert="INSERT INTO products"
			+ "(productName,productDescription,quantity,price) VALUES (?,?,?,?)";
	
	private static final String ID="SELECT * FROM products WHERE productId = ?";
	
	private static final String UPDATE_QUANTITY="UPDATE products SET quantity = ? WHERE productId = ?";
	@Override
	public int addProduct(Product product) {
		try(Connection con = JdbcConfig.getConnection();
		 PreparedStatement ps = con.prepareStatement(Insert)){
		
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDescription());
			ps.setInt(3, product.getQuantity());
			ps.setDouble(4, product.getPrice());
			
			return ps.executeUpdate();
		}
		catch(Exception e) {
			throw new ProjectException("Data Not Added" + e.getMessage());
		}
	}

	@Override
	public Product getProductById(int productId) {
		try(Connection con = JdbcConfig.getConnection();
		PreparedStatement ps = con.prepareStatement(ID)){
		
			Product pro1 = null; 
			
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			
				new Product();
				
				pro1.setProductId(rs.getInt("productId"));
				pro1.setProductName(rs.getString("productName"));
				pro1.setProductDescription(rs.getString("productDescription"));
				pro1.setQuantity(rs.getInt("quantity"));
				pro1.setPrice(rs.getDouble("price"));
				
			}
			return pro1;
		}
		catch(Exception e) {
			throw new ProjectException("Id Not Found" + e.getMessage());
		}
	}

	@Override
	public void updateProductQuantity(int productId, int updatedQty) {
		try(Connection con = JdbcConfig.getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_QUANTITY)){
		
			ps.setInt(1, productId);
			ps.setInt(2, updatedQty);
			
			ps.executeUpdate();
		}
		catch(Exception e) {
			throw new ProjectException("Not Updated " + e.getMessage());
		}
		
	}

}
