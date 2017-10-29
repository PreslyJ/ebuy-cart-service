package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.kidz.cart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>,QueryDslPredicateExecutor<Category> {
	
	
}
