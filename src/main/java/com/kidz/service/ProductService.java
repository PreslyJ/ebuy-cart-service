package com.kidz.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kidz.cart.model.Item;

public interface ProductService {
	
	Page<Item> getAllItems(Pageable pageable,Map<String, Object> filterMap);
	
	Item getItemById(Long itemId);
	
	void saveItem(Item itwm);
	
	void deleteItem(Long itemId);
	
}
