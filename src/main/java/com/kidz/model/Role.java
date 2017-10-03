package com.kidz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kidz.cart.model.Customer;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String authority;
	private String email;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;


	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
