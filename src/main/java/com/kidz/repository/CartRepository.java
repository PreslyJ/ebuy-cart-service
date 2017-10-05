package com.kidz.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Cart;

@Transactional
public interface CartRepository extends CrudRepository<Cart, Long>{
	
}
