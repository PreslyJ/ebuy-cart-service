package com.kidz.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.CartItem;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
