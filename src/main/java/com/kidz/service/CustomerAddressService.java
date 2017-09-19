package com.kidz.service;

import java.util.List;

import com.kidz.model.ShippingAddress;

public interface CustomerAddressService {
	
	List<ShippingAddress> getAllShippingAddressByCustomerId(Object customerId);
	
	void addShippingAddressObject(Object customerId, ShippingAddress shippingAddress);
}
