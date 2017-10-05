package com.kidz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kidz.CartApplication;
import com.kidz.rpc.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(SpringRunner.class)
public class StarterApplicationTests {

	@Autowired
	LoginService loginService; 
	
	@Test
	public void contextLoads() {
		//System.out.println(loginService.getAuthToken());
	}

}
