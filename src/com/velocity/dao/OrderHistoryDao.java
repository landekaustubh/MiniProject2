package com.velocity.dao;

import java.util.List;

import com.velocity.model.CartItem;

public interface OrderHistoryDao {

	void saveHistory(int userid, CartItem item);

    List<CartItem> getUserHistory(int userid);
}
