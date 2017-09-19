package com.kidz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kidz.model.Category;
import com.kidz.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByOrderByProductViewsDesc();
	
	List<Product> findAllByProductCategory(Category category);
	
}
