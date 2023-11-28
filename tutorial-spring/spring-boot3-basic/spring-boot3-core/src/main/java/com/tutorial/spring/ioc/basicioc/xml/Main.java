package com.tutorial.spring.ioc.basicioc.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String []args) {
		String path = "com/tutorial/spring/ioc/basicioc/xml/context.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        assert null != customerService : "the customerService reference can't be null!";
        System.out.println("[basic-ioc] With XML Completed.");
	}
}
