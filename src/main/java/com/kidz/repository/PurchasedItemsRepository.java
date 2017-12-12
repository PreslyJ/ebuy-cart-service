package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidz.cart.model.PurchasedItems;

public interface PurchasedItemsRepository extends JpaRepository<PurchasedItems, Long>{

}
