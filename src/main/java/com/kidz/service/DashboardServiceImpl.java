package com.kidz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;
import com.kidz.repository.SiteViewRepository;
import com.kidz.repository.SubscriptionsRepository;

@Service
class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private SiteViewRepository siteViewRepository;
	
	@Autowired
	private SubscriptionsRepository subscriptionsRepository;
	
	@Override
	public void saveSiteViews(SiteViews siteView) {
		siteViewRepository.save(siteView);
	}

	@Override
	public void saveSubscription(Subscriptions subscriptions) {
		subscriptionsRepository.save(subscriptions);
	}

}
