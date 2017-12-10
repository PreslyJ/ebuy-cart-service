package com.kidz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kidz.rpc.LoginService;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

	@Autowired
	ConnectionFactory connectionFactory;
	
	@Bean
	Queue loginQueue(){
		return new Queue("ebuy.login.rpc", true);
	}
	
	@Bean
	TopicExchange loginExchange() {
		return new TopicExchange("ebuy.login.rpc.xch");
	}
	
	@Bean
	public Binding regbinding() {
		// Bind the queue and exchange with routing key
		return BindingBuilder.bind(loginQueue()).to(loginExchange()).with("login");
	}
	
	@Bean
	public RabbitTemplate serviceTemplate() {
		
		RabbitTemplate template = new RabbitTemplate(connectionFactory);

		// convetAndSend will use thease by default
		template.setExchange("ebuy.login.rpc.xch");
		template.setRoutingKey("login");
		// Timeout after 50 seconds
		template.setReceiveTimeout(50000);
	
		return template;
		
	}
	
	@Bean
	public AmqpProxyFactoryBean loginService() {
		AmqpProxyFactoryBean ret = new AmqpProxyFactoryBean();

		// setup the interface
		ret.setServiceInterface(LoginService.class);
		ret.setAmqpTemplate(serviceTemplate());
		ret.setRoutingKey("login");

		return ret;
	}

}
