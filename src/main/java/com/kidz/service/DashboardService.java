package com.kidz.service;

import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;

public interface DashboardService {
	
	public void saveSiteViews(SiteViews siteView);
	
	public void saveSubscription(Subscriptions subscriptions);
	
}
