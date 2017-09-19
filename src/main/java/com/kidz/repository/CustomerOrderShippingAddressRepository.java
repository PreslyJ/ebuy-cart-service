package com.kidz.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidz.model.CustomerOrderShippingAddress;

public interface CustomerOrderShippingAddressRepository 
					extends CrudRepository <CustomerOrderShippingAddress, Long> {

}
