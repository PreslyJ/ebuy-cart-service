package com.kidz.service;

import com.kidz.cart.model.Customer;

public interface EmailSenderService {

	void sendActiveCode(Customer customer);
	
	void sendResetPasswordCode(Customer customer);
}
