package com.kidz.service;

import java.io.IOException;
import java.util.List;

import com.kidz.cart.model.Cart;
import com.kidz.cart.model.Customer;
import com.kidz.cart.model.CustomerOrder;
import com.kidz.cart.model.CustomerOrderShippingAddress;

public interface CustomerOrderService {
	
	double getCustomerOrderGrandTotalByCart(Cart cart);
	
	void addOrderDumpCart(CustomerOrderShippingAddress customerOrderShippingAddress, CustomerOrder customerOrder, Cart cart) throws IOException;
	
	List<CustomerOrder> getAllCustomerOrderByCustomer(Customer customer);
}
