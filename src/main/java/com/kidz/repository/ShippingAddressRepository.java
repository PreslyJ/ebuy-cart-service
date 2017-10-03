package com.kidz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Customer;
import com.kidz.model.ShippingAddress;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long>{
	
	List<ShippingAddress> findAllByCustomer(Customer customer);
	
	
}
