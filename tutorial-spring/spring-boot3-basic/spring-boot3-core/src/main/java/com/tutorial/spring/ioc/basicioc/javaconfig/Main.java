package com.tutorial.spring.ioc.basicioc.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tutorial.spring.ioc.basicioc.component.AppConfig;

public class Main {

	public static void main(String[] args) {
		fromAnnotationConfigApplicationContext();
		fromProgrammatically();
		fromComponentScanningByProgrammatically();
		fromComponentScanningByXml();
		fromComponentScanningByAnnotation();
	}
	
	static void fromAnnotationConfigApplicationContext() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		CustomerService customerService = applicationContext.getBean(CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		System.out.println("[basic-ioc] With JavaConfig - fromAnnotationConfigApplicationContext Completed.");
	}
	
	static void fromProgrammatically() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ApplicationConfiguration.class);
		ctx.refresh();
		CustomerService customerService = ctx.getBean(CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		System.out.println("[basic-ioc] With JavaConfig - fromProgrammatically Completed.");
	}
	
	static void fromComponentScanningByProgrammatically() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		String []packages = new String[] {
		  "com.tutorial.spring.ioc.basicioc.javaconfig"
		};
		ctx.scan(packages);
		ctx.refresh();
		CustomerService customerService = ctx.getBean(CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		System.out.println("[basic-ioc] With JavaConfig - fromComponentScanningByProgrammatically Completed.");
	}
	
	static void fromComponentScanningByXml() {
		String path = "com/tutorial/spring/ioc/basicioc/javaconfig/context.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
		CustomerService customerService = applicationContext.getBean(CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		System.out.println("[basic-ioc] With JavaConfig - fromComponentScanningByXml Completed.");
	}
	
	static void fromComponentScanningByAnnotation() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AppConfig.class);
		applicationContext.refresh();
		CustomerService customerService = applicationContext.getBean(CustomerService.class);
		assert null != customerService : "the customerService reference can't be null!";
		System.out.println("[basic-ioc] With JavaConfig - fromComponentScanningByAnnotation Completed.");
	}
}
