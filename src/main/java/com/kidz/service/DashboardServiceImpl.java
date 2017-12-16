package com.kidz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidz.cart.model.Item;
import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;
import com.kidz.repository.AbstractJpaDao;
import com.kidz.repository.SiteViewRepository;
import com.kidz.repository.SubscriptionsRepository;

@Service
class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private SiteViewRepository siteViewRepository;
	
	@Autowired
	private SubscriptionsRepository subscriptionsRepository;
	
	@Autowired
    AbstractJpaDao abstractJpaDao; 
	
	@Override
	public void saveSiteViews(SiteViews siteView) {
		siteViewRepository.save(siteView);
	}

	@Override
	public void saveSubscription(Subscriptions subscriptions) {
		subscriptionsRepository.save(subscriptions);
	}

	
	public List<Map<String,Object>> findAllItems() {
		 
		List<Map<String,Object>> mapList=new ArrayList<>();
		List<Item> items=abstractJpaDao.findAll(Item.class, " status='active'");
		 
		for (Item item : items) {
		
			Map<String,Object> map=new HashMap<>();
			map.put("item",item.getName());
			map.put("purchased",getPurchasedStock(item.getId()));
			map.put("stocks",getStockIn(item.getId()));
			
			mapList.add(map);
			
		}
		 
		 
		 return mapList;
	}
	
	public int getPurchasedStock(long itemId) {
		
		Integer purchases= abstractJpaDao.findCount("SELECT count(e.noOfItems) from PurchasedItems e where  e.item.id="+itemId);

		if(purchases==null)
			 purchases=0;

		return  purchases;
	
	}

	public int getStockIn(long itemId) {

		 Integer stock=abstractJpaDao.findCount("SELECT count(e.noOfItems) from StockItems e where  e.item.id="+itemId);
	
		 if (stock==null)
			 stock=0;
		 
	 	 return stock;
		 
	}

	public int getAvgItemPurchases() {

		 Integer stock=abstractJpaDao.findNativeCount("select sum(no_of_items)/count(distinct date(purchased_date)) from purchased_items");
	
		 if (stock==null)
			 stock=0;
		 
	 	 return stock;
		 
	}
	
	public int getAvgViews() {

		 Integer view=abstractJpaDao.findNativeCount("select count(*)/count(distinct date(s.date)) from site_views s");
	
		 if (view==null)
			 view=0;
		 
	 	 return view;
		 
	}

	@Override
	public List<Subscriptions> getAllSubscriptions() {
		return subscriptionsRepository.findAll();
	}
	
	
	
}
