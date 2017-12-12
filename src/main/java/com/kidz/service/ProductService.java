package com.kidz.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kidz.cart.model.Item;
import com.kidz.cart.model.PurchasedItems;
import com.kidz.cart.model.StockItems;

public interface ProductService {
	
	Page<Item> getAllItems(Pageable pageable,Map<String, Object> filterMap);
	
	Item getItemById(Long itemId);
	
	Item saveItem(Item itwm);
	
	void deleteItem(Long itemId);
	
	void saveStockItem(StockItems  stockItems);
	
	Page<StockItems> getAllStockItems(Pageable pageable,Map<String, Object> filterMap);
	
	void savePurchasedItems(PurchasedItems purchasedItems);
	
}
