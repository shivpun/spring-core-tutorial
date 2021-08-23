package org.sample.spring.core.bean.programatical;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BeanRegisterApplication {

	private static final Logger logger = LoggerFactory.getLogger(BeanRegisterApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = new SpringApplicationBuilder(BeanRegisterApplication.class)
				.web(WebApplicationType.NONE).run(args);
		Aoo aoo1 = cac.getBean(Aoo.class);
		logger.info("Aoo instance {} by ConfigurableApplicationContext", aoo1);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.registerBean(Aoo.class);
		gac.refresh();
		Aoo aoo = gac.getBean(Aoo.class);
		logger.info("GAC {} create instance of {}", gac, aoo);

	}
}

@Component
class AooApplicationContextAware implements ApplicationContextAware, BeanNameAware {

	private static final Logger logger = LoggerFactory.getLogger(AooApplicationContextAware.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("ApplicationContextAware Method:{} set value of:{}", "setApplicationContext", applicationContext);
	}

	@Override
	public void setBeanName(String name) {
		logger.info("BeanNameAware Method:{} set value of:{}", "setBeanName", name);
	}
}

@Component
class AooBeanPostProcessor implements BeanPostProcessor {

	private static final Logger logger = LoggerFactory.getLogger(AooBeanPostProcessor.class);

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.info("BeanPostProcessor postProcessBeforeInitialization | bean:{} | beanName:{}", bean, beanName);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("BeanPostProcessor postProcessAfterInitialization  | bean:{} | beanName:{}", bean, beanName);
		return bean;
	}
}

@Component
class Aoo {

	private static final Logger logger = LoggerFactory.getLogger(Aoo.class);

	@Value(value = "${name:Products}")
	private String name;

	@Value("${some.key:one,two,three}")
	private String[] stringArrayWithDefaults;

	@Value("${some.key:1,2,3}")
	private int[] intArrayWithDefaults;

	@Value("#{'${some.key:one,two,three}'.split(',')}")
	private List<String> aooString;

	public String getName() {
		logger.info("Method:{} return a value:{}", "getName", name);
		return name;
	}

	public void setName(String name) {
		logger.info("Method:{} set a value:{}", "setName", name);
		this.name = name;
	}

	public String[] getStringArrayWithDefaults() {
		logger.info("Method:{} return a value:{}", "getStringArrayWithDefaults", stringArrayWithDefaults);
		return stringArrayWithDefaults;
	}

	public void setStringArrayWithDefaults(String[] stringArrayWithDefaults) {
		logger.info("Method:{} set a value:{}", "setStringArrayWithDefaults", stringArrayWithDefaults);
		this.stringArrayWithDefaults = stringArrayWithDefaults;
	}

	public int[] getIntArrayWithDefaults() {
		logger.info("Method:{} return a value:{}", "getIntArrayWithDefaults", intArrayWithDefaults);
		return intArrayWithDefaults;
	}

	public void setIntArrayWithDefaults(int[] intArrayWithDefaults) {
		logger.info("Method:{} set a value:{}", "setIntArrayWithDefaults", intArrayWithDefaults);
		this.intArrayWithDefaults = intArrayWithDefaults;
	}

	public List<String> getAooString() {
		logger.info("Method:{} return a value:{}", "getAooString", aooString);
		return aooString;
	}

	public void setAooString(List<String> aooString) {
		logger.info("Method:{} set a value:{}", "setAooString", aooString);
		this.aooString = aooString;
	}

	@Override
	public String toString() {
		return "Aoo [name=" + name + ", stringArrayWithDefaults=" + Arrays.toString(stringArrayWithDefaults)
				+ ", intArrayWithDefaults=" + Arrays.toString(intArrayWithDefaults) + ", aooString=" + aooString + "]";
	}
}
