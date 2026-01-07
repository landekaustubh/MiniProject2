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
	
	private static final String GET_PRODUCT =
            "SELECT * FROM products WHERE productId = ?";

    private static final String UPDATE_QTY =
            "UPDATE products SET quantity = ? WHERE productId = ?";
	
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
        try (Connection con = JdbcConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_PRODUCT)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductDescription(rs.getString("productDescription"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                return p;
            }
            return null;
        } catch (Exception e) {
            throw new ProjectException("Product Not Found" + e.getMessage());
        }
    }

    @Override
    public boolean updateProductQuantity(int productId, int quantity) {
        try (Connection con = JdbcConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_QTY)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ProjectException("Quantity Update Failed" + e.getMessage());
        }
    }


}
