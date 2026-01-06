package com.velocity.dao;

import com.velocity.model.Product;

public interface ProductDao {

	int addProduct(Product product);

	Product getProductById(int productId);

	boolean updateProductQuantity(int productId, int quantity);

}
