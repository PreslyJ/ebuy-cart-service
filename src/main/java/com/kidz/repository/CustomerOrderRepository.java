package com.kidz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Customer;
import com.kidz.cart.model.CustomerOrder;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long>{

	List<CustomerOrder> findAllByCustomer(Customer customer);
}
