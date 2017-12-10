package com.kidz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.kidz.cart.model.CartItem;
import com.kidz.cart.model.Customer;
import com.kidz.service.CartService;
import com.kidz.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CartApplicationTests {

	@Autowired
	CustomerService customerService; 
	
	@Autowired
	CartService cartService; 
	
	@Test
	public void removeFromCart() {
		
		Customer customer=customerService.getCustomerById(2l);
		
		CartItem cartItem=cartService.getCartItem(4);
		
		
		customer.getCart().setGrandTotal(customer.getCart().getGrandTotal()-cartItem.getTotalPriceDouble());
		
		customerService.save(customer);
		
		cartService.deleteCartItem(cartItem.getId());
		
	}

}
