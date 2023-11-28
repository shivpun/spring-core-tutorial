package com.tutorial.spring.ioc.basicioc.jsr250;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootMain {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootMain.class, args);
		//CustomerService customerService = ctx.getBean(CustomerService.class);
		CustomerService customerService = ctx.getBean("customer-service", CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		assert null != customerService.getDataSource() : "the Datasource reference can't be injected null!";
		System.out.println("[basic-ioc] With JSR-350 Completed.");
	}
}
