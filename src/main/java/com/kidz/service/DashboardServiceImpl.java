package com.kidz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidz.dashboard.domain.SiteViews;
import com.kidz.repository.SiteViewRepository;

@Service
class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private SiteViewRepository siteViewRepository;
	
	@Override
	public void saveSiteViews(SiteViews siteView) {
		siteViewRepository.save(siteView);
	}

}
