package com.velocity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.velocity.config.JdbcConfig;
import com.velocity.dao.ProductDao;
import com.velocity.exception.ProjectException;
import com.velocity.model.Product;

public class ProductDaoImpl implements ProductDao{

	private static final String Insert="INSERT INTO products"
			+ "(productName,productDescription,quantity,price) VALUES (?,?,?,?)";
	
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

}
