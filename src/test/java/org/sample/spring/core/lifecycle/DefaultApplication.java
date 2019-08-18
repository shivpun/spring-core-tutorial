package org.sample.spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DefaultApplication {

	private static final Logger logger = LoggerFactory.getLogger(DefaultApplication.class);

	public static void main(String[] args) {
		new SpringApplicationBuilder(DefaultApplication.class).web(WebApplicationType.NONE).run(args);
	}

	@Bean
	public Foo foo() {
		logger.info(String.format("Bean [%s] is created in method [%s]", "Foo", "foo"));
		return new Foo();
	}

	@Bean
	public Boo boo() {
		logger.info(String.format("Bean [%s] is created in method [%s]", "Boo", "boo"));
		return new Boo();
	}

	@Bean(initMethod = "init", destroyMethod = "destory")
	public Poo poo() {
		logger.info(String.format("Bean [%s] is created in method [%s]", "Poo", "poo"));
		return new Poo();
	}

	@Bean(initMethod = "customInit", destroyMethod = "customDestory")
	public Poo pooCustom() {
		logger.info(String.format("Bean [%s] is created in method [%s]", "Poo", "pooCustom"));
		return new Poo();
	}

	@Bean(initMethod = "init", destroyMethod = "destory")
	public Clue clue() {
		logger.info(String.format("Bean [%s] is created in method [%s]", "Clue", "clue"));
		return new Clue();
	}
}

/**
 * Spring Bean life cycle events using InitializingBean, DisposableBean
 */
class Foo implements InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(Foo.class);

	public Foo() {
		logger.info(String.format("Create [%s] by [%s]", "Foo", "default constructor"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "afterPropertiesSet"));
	}

	@Override
	public void destroy() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "destroy"));
	}
}

@Component
class Foo1 implements InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(Foo1.class);

	public Foo1() {
		logger.info(String.format("Create [%s] by [%s]", "Foo1", "default constructor"));
	}

	public void init() {
		logger.info(String.format("Triggered Method:[%s]", "->init"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "afterPropertiesSet"));
	}

	@Override
	public void destroy() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "destroy"));
	}
}

/**
 * Spring Bean life cycle events using JSR-250 i.e. @PostConstruct
 * and @PreDestroy annotations
 */
class Boo {

	private static final Logger logger = LoggerFactory.getLogger(Boo.class);

	public Boo() {
		logger.info(String.format("Create [%s] by [%s]", "Boo", "default constructor"));
	}

	@PostConstruct
	public void jsr250_postConstruct() {
		logger.info(String.format("Triggered Method:[%s]", "jsr250_postConstruct"));
	}

	@PreDestroy
	public void jsr250_preDestory() {
		logger.info(String.format("Triggered Method:[%s]", "jsr250_preDestory"));
	}
}

/**
 * Spring Bean life cycle events using Custom `init()` and `destroy()` methods
 * for XML based by default in bean configuration file
 */
class Poo {

	private static final Logger logger = LoggerFactory.getLogger(Poo.class);

	public Poo() {
		logger.info(String.format("Create [%s] by [%s]", "Poo", "default constructor"));
	}

	public void init() {
		logger.info(String.format("Triggered Method:[%s]", "->init"));
	}

	public void destory() {
		logger.info(String.format("Triggered Method:[%s]", "->destory"));
	}

	public void customInit() {
		logger.info(String.format("Triggered Method:[%s]", "customInit"));
	}

	public void customDestory() {
		logger.info(String.format("Triggered Method:[%s]", "customDestory"));
	}
}

class Clue implements InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(Clue.class);

	public Clue() {
		logger.info(String.format("Create [%s] by [%s]", "Clue", "default constructor"));
	}

	@Override
	public void destroy() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "destory"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(String.format("Triggered Method:[%s]", "afterPropertiesSet"));
	}

	@PostConstruct
	public void jsr250_postConstruct() {
		logger.info(String.format("Triggered Method:[%s]", "jsr250_postConstruct"));
	}

	@PreDestroy
	public void jsr250_preDestory() {
		logger.info(String.format("Triggered Method:[%s]", "jsr250_preDestory"));
	}

	public void init() {
		logger.info(String.format("Triggered Method:[%s]", "init via annotation args"));
	}

	public void destory() {
		logger.info(String.format("Triggered Method:[%s]", "destory via annotation args"));
	}
}
