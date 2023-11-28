package com.tutorial.spring.ioc.basicioc.component;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
	
	@Autowired
	private DataSource dataSource;

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }
}
