package com.kidz.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.CustomerOrderItem;

public interface CustomerOrderItemRepository extends CrudRepository <CustomerOrderItem, Long>{

}
