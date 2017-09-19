package com.kidz.service;

import java.io.IOException;
import java.util.List;

import com.kidz.model.Cart;
import com.kidz.model.Customer;
import com.kidz.model.CustomerOrder;
import com.kidz.model.CustomerOrderShippingAddress;

public interface CustomerOrderService {
	
	double getCustomerOrderGrandTotalByCart(Cart cart);
	
	void addOrderDumpCart(CustomerOrderShippingAddress customerOrderShippingAddress, CustomerOrder customerOrder, Cart cart) throws IOException;
	
	List<CustomerOrder> getAllCustomerOrderByCustomer(Customer customer);
}
