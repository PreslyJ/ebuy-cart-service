package com.kidz.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kidz.cart.model.Item;
import com.kidz.cart.model.Product;

public interface ProductService {
	
	Page<Product> getAllProducts(Pageable pageable,Map<String, Object> filterMap);
	
	Product getProductById(Long productId);
	
	void save(Product product);
	
	void delete(Long productId);
	
	Page<Item> getAllItems(Pageable pageable,Map<String, Object> filterMap);
	
	Item getItemById(Long itemId);
	
	void saveItem(Item itwm);
	
	void deleteItem(Long itemId);
	
}
