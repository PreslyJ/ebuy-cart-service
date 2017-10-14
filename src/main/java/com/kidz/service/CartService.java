package com.kidz.service;

import java.io.IOException;

import com.kidz.cart.model.Cart;
import com.kidz.cart.model.CartItem;

public interface CartService {
	
	Cart validate(Object customerId) throws IOException;
	
	void emptyCart(Cart cart);
	
	void save(Cart cart);
	
	void saveCartItem(CartItem cartItem);
	
}
