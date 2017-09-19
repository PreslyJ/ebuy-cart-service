package com.kidz.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidz.model.CustomerOrderItem;

public interface CustomerOrderItemRepository extends CrudRepository <CustomerOrderItem, Long>{

}
