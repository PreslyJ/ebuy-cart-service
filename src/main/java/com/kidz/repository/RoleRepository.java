package com.kidz.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidz.model.Customer;
import com.kidz.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByAuthorityAndCustomer(String auth, Customer customer);
}
