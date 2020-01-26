package org.sample.spring.core.lifecycle;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;
import org.springframework.web.context.ServletContextAware;

public class DefaultBeanLifeCycleApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultBeanLifeCycleApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DefaultBeanLifeCycleApplication.class).web(WebApplicationType.NONE).run(args);
		logger.info("DefaultBeanLifeCycleApplication | start");
		ctx.registerShutdownHook();
	}
	
	@Bean(value = {"fooApp"}, initMethod = "init")
	public FooApp fooApp() {
		return new FooApp();
	}
	
	@Bean(value = {"booApp"})
	public BooApp booApp() {
		return new BooApp();
	}
}

/**
 * 
 * https://docs.spring.io/spring-framework/docs/5.2.3.RELEASE/javadoc-api/index.html?org/springframework/context/event/AbstractApplicationEventMulticaster.html
 * 
 * Bean factory implementations should support the standard bean lifecycle interfaces as far as possible. The full set of initialization methods and their standard order is:
 * 1.  BeanNameAware's setBeanName
 * 2.  BeanClassLoaderAware's setBeanClassLoader
 * 3.  BeanFactoryAware's setBeanFactory
 * 4.  EnvironmentAware's setEnvironment
 * 5.  EmbeddedValueResolverAware's setEmbeddedValueResolver
 * 6.  ResourceLoaderAware's setResourceLoader (only applicable when running in an application context)
 * 7.  ApplicationEventPublisherAware's setApplicationEventPublisher (only applicable when running in an application context)
 * 8.  MessageSourceAware's setMessageSource (only applicable when running in an application context)
 * 9.  ApplicationContextAware's setApplicationContext (only applicable when running in an application context)
 * 10. ServletContextAware's setServletContext (only applicable when running in a web application context)
 * 11. postProcessBeforeInitialization methods of BeanPostProcessors
 * 12. InitializingBean's afterPropertiesSet
 * 13. a custom init-method definition
 * 14. postProcessAfterInitialization methods of BeanPostProcessors
 * 
 * On shutdown of a bean factory, the following lifecycle methods apply:
 * 
 * 1.  postProcessBeforeDestruction methods of DestructionAwareBeanPostProcessors
 * 2.  DisposableBean's destroy
 * 3.  a custom destroy-method definition
 **/
class FooApp implements ServletContextAware, MessageSourceAware, ApplicationContextAware, ApplicationEventPublisherAware, EmbeddedValueResolverAware, ResourceLoaderAware, InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware, BeanPostProcessor, BeanFactoryPostProcessor {

	private static final Logger logger = LoggerFactory.getLogger(FooApp.class);
	
	public FooApp() {
		logger.info(String.format("Create [%s] by [%s]", "Foo", "default constructor"));
	}
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		logger.info(String.format("Method: [%s] by [%s] via [%s]", "setBeanClassLoader", "BeanClassLoaderAware", "Aware"));
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		logger.info(String.format("Method: [%s] by [%s] via [%s]", "setBeanFactory", "BeanFactoryAware", "Aware"));
	}

	@Override
	public void setBeanName(String name) {
		logger.info(String.format("Method: [%s] by [%s] via [%s]", "setBeanName", "BeanNameAware", "Aware"));
	}

	@Override
	public void destroy() throws Exception {
		logger.info(String.format("Method: [%s] by [%s]", "destroy", "DisposableBean"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(String.format("Method: [%s] by [%s]", "afterPropertiesSet", "InitializingBean"));
	}
	
	public void init() {
		logger.info(String.format("Method: [%s] by [%s]", "init", "InitializingBean"));
	}
	
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info(String.format("Method: [%s] by [%s] with bean name [%s]", "postProcessBeforeInitialization", "BeanPostProcessor", beanName));
		return bean;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info(String.format("Method: [%s] by [%s] with bean name [%s]", "postProcessAfterInitialization", "BeanPostProcessor", beanName));
		return bean;
	}
	
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info(String.format("Method: [%s] by [%s]", "postProcessBeanFactory", "BeanFactoryPostProcessor"));
	}

	@Override
	public void setEnvironment(Environment environment) {
		logger.info(String.format("Method: [%s] by [%s]", "setEnvironment", "EnvironmentAware"));
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		logger.info(String.format("Method: [%s] by [%s]", "setResourceLoader", "ResourceLoaderAware"));
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		logger.info(String.format("Method: [%s] by [%s]", "setEmbeddedValueResolver", "EmbeddedValueResolverAware"));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info(String.format("Method: [%s] by [%s]", "setApplicationContext", "ApplicationContextAware"));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		logger.info(String.format("Method: [%s] by [%s]", "setApplicationEventPublisher", "ApplicationEventPublisherAware"));
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		logger.info(String.format("Method: [%s] by [%s]", "setMessageSource", "MessageSourceAware"));
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		logger.info(String.format("Method: [%s] by [%s]", "setServletContext", "ServletContextAware"));		
	}
}

class BooApp {}