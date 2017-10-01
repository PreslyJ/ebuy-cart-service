package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.kidz.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>,QueryDslPredicateExecutor<Item> {

}
