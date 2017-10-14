package com.kidz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidz.cart.model.CartItem;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
