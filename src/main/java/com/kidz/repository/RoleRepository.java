package com.kidz.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Customer;
import com.kidz.cart.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByAuthorityAndCustomer(String auth, Customer customer);
}
