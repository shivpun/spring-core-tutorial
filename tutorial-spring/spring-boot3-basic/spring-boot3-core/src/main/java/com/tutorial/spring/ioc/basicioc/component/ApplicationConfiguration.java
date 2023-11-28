package com.tutorial.spring.ioc.basicioc.component;

import static com.tutorial.spring.ioc.basicioc.javaconfig.ApplicationConfiguration.DATABASE_PROPERTIES_FILE;

import javax.sql.DataSource;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@PropertySource(value = {
  DATABASE_PROPERTIES_FILE
})
public class ApplicationConfiguration {
	
	public static final String DATABASE_PROPERTIES_FILE = "classpath:/com/tutorial/spring/ioc/config.properties";
	
	@Bean
    public DataSource dataSource(Environment environment) {
        Driver jdbcDriver = new Driver();
        String jdbcUrl = environment.getProperty("ds.url");
        return new SimpleDriverDataSource(jdbcDriver, jdbcUrl);
    }

    @Bean
    public CustomerService customerService(DataSource dataSource) {
        CustomerService customerService = new CustomerService();
        customerService.setDataSource(dataSource);
        return customerService;
    }
}
