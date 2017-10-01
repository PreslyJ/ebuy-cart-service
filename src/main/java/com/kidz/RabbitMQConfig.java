package com.kidz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

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
		return BindingBuilder.bind(loginQueue()).to(loginExchange()).with("login");
	}
	
	@Bean
	public RabbitTemplate serviceTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);

		// convetAndSend will use thease by default
		template.setExchange("ebuy.login.rpc.xch");
		template.setRoutingKey("login");
		template.setReceiveTimeout(50000);
	
		return template;
		
	}
	
	
}
