package com.kidz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidz.dashboard.model.SiteViews;

public interface SiteViewRepository extends JpaRepository<SiteViews, Long>{

}
