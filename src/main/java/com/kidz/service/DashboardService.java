package com.kidz.service;

import java.util.List;
import java.util.Map;

import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;

public interface DashboardService {
	
	public void saveSiteViews(SiteViews siteView);
	
	public void saveSubscription(Subscriptions subscriptions);
	
	public int getAvgViews();
	
	public int getAvgItemPurchases();
	
	public List<Map<String,Object>> findAllItems();
	
}
