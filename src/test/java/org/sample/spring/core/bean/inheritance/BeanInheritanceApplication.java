package org.sample.spring.core.bean.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath*:inheritance/inheritance-p0.xml" })
public class BeanInheritanceApplication {

	private static final Logger logger = LoggerFactory.getLogger(BeanInheritanceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(BeanInheritanceApplication.class)
				.web(WebApplicationType.NONE).run(args);
		logger.info("Foo=> {}", ctx.getBean("foo", Foo.class));
		logger.info("Boo=> {}", ctx.getBean("boo", Boo.class));
		logger.info("booInstance=> {}", ctx.getBean("booInstance", Boo.class));
		logger.info("coo=> {}", ctx.getBean("coo", Coo.class));
	}
}

class Foo {

	private static final Logger logger = LoggerFactory.getLogger(Foo.class);

	private String name;

	private String code;

	public String getName() {
		logger.info(String.format("Method [%s] return [%s]", "getName", this.name));
		return name;
	}

	public void setName(String name) {
		logger.info(String.format("Method [%s] set [%s]", "setName", name));
		this.name = name;
	}

	public String getCode() {
		logger.info(String.format("Method [%s] return [%s]", "getCode", this.code));
		return code;
	}

	public void setCode(String code) {
		logger.info(String.format("Method [%s] return [%s]", "setCode", code));
		this.code = code;
	}

	@Override
	public String toString() {
		return "Foo [name=" + name + ", code=" + code + "]";
	}
}

class Boo {

	private static final Logger logger = LoggerFactory.getLogger(Boo.class);

	private String name;

	private String code;

	public String getName() {
		logger.info(String.format("Method [%s] return [%s]", "getName", this.name));
		return name;
	}

	public void setName(String name) {
		logger.info(String.format("Method [%s] set [%s]", "setName", name));
		this.name = name;
	}

	public String getCode() {
		logger.info(String.format("Method [%s] return [%s]", "getCode", this.code));
		return code;
	}

	public void setCode(String code) {
		logger.info(String.format("Method [%s] return [%s]", "setCode", code));
		this.code = code;
	}

	public static Boo staticMethodCall() {
		logger.info(String.format("Method [%s] static call", "staticMethodCall"));
		Boo boo = new Boo();
		return boo;
	}

	@Override
	public String toString() {
		return "Boo [name=" + name + ", code=" + code + "]";
	}
}

class Aoo {

	private static final Logger logger = LoggerFactory.getLogger(Aoo.class);

	public Boo createBoo() {
		logger.info(String.format("Method [%s] instance call", "createBoo"));
		Boo boo = new Boo();
		return boo;
	}

	public static Foo createFoo() {
		logger.info(String.format("Method [%s] static call", "createFoo"));
		Foo foo = new Foo();
		return foo;
	}

}

class Coo extends Foo {
}