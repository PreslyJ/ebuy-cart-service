package com.kidz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kidz.cart.model.Customer;
import com.kidz.dashboard.model.SiteViews;
import com.kidz.dashboard.model.Subscriptions;
import com.kidz.service.CustomerService;
import com.kidz.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	CustomerService customerService;
	
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

	
	@RequestMapping(value="/saveSubscription",method=RequestMethod.PUT)
	@CrossOrigin
	public void saveSubscription(@RequestBody Subscriptions subscription) {

		dashboardService.saveSubscription(subscription);
		
	}
	
	@RequestMapping(value="/sendEmailToAllSubscribers",method=RequestMethod.POST)
	public void sendEmail(@RequestBody String  review) {
		
/*		String review= map.get("msg");
*/		
		Map<String,String>  map1=new HashMap<>();
		
		for (Subscriptions subscription : dashboardService.getAllSubscriptions()) {
			
			if(map1.get(subscription.getEmail())==null){
				try {
					SimpleMailMessage message = new SimpleMailMessage(); 
					message.setTo(subscription.getEmail()); 
					message.setSubject("Kidz Land"); 
					message.setText(review);
					emailSender.send(message);
					map1.put(subscription.getEmail(), "email");
				} catch (MailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		for (Customer cus : customerService.getAllCustomer()) {
			
			if(map1.get(cus.getEmail())==null){
				try {
					SimpleMailMessage message = new SimpleMailMessage(); 
					message.setTo(cus.getEmail()); 
					message.setSubject("Kidz Land"); 
					message.setText(review);
					emailSender.send(message);
					map1.put(cus.getEmail(), "email");
				} catch (MailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
