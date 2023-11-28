package com.tutorial.spring.ioc.basicioc.jsr250;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component(value = "customer-service")
public class CustomerService {
	
	@Resource
	private DataSource dataSource;

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

	public DataSource getDataSource() {
		return dataSource;
	}
}
