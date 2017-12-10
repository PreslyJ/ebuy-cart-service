package com.kidz.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kidz.dashboard.domain.SiteViews;
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
	
}
