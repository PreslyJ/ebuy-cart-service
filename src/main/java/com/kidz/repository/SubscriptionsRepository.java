package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidz.dashboard.model.Subscriptions;


public interface SubscriptionsRepository extends  JpaRepository<Subscriptions, Long>{

}
