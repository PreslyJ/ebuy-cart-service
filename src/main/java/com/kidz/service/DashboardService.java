package com.kidz.service;

import java.util.List;
import java.util.Map;

import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;

public interface DashboardService {
	
	public void saveSiteViews(SiteViews siteView);
	
	public void saveSubscription(Subscriptions subscriptions);
	
	public int getAvgViews(long itemId);
	
	public int getAvgItemPurchases(long itemId);
	
	public List<Map<String,Object>> findAllItems();
	
}
