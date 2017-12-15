package com.kidz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kidz.dashboard.model.SiteViews;
import com.kidz.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(value="/saveSiteView",method=RequestMethod.GET)
	public void saveCustomer(HttpServletRequest request) {

		SiteViews siteViews=new SiteViews();
		siteViews.setDate(new Date());		
		siteViews.setIp(request.getRemoteAddr());
		
		dashboardService.saveSiteViews(siteViews);
		
	}
	
	
	@RequestMapping(value="/getStockDetails",method=RequestMethod.GET)
	public Map<String,Object> getStockDetails() {
		
		Map<String,Object> map=new HashMap<>();
		
		map.put("content",dashboardService.findAllItems());
		
		return map;
		
	}

	@RequestMapping(value="/getAvgItemPurchases",method=RequestMethod.GET)
	public Map<String,Object> getAvgItemPurchases() {
		
		Map<String,Object>  map=new HashMap<String, Object>();
		
		int avgItems= dashboardService.getAvgItemPurchases();
		
		map.put("avgItemPurchases",avgItems);
		
		return map;
		
	}
	
	@RequestMapping(value="/getAvgViews",method=RequestMethod.GET)
	public Map<String,Object> getAvgViews() {
		
		Map<String,Object>  map=new HashMap<String, Object>();
		
		int avgViews= dashboardService.getAvgViews();
		
		map.put("avgViews",avgViews);
		
		return map;
		
	}

	
}
