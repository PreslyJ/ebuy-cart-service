package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kidz.cart.model.StockItems;

public interface StockItemsReository extends JpaRepository<StockItems, Long>{

}
