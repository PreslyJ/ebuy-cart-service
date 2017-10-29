package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.kidz.cart.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>,QueryDslPredicateExecutor<SubCategory>{

}
