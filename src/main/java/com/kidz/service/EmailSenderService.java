package com.kidz.service;

import com.kidz.model.Customer;

public interface EmailSenderService {

	void sendActiveCode(Customer customer);
	
	void sendResetPasswordCode(Customer customer);
}
