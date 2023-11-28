package com.tutorial.spring.ioc.basicioc.jsr330;

import javax.sql.DataSource;

import jakarta.inject.Inject;
import jakarta.inject.Named;

//@Named
@Named("customer-service") // Optional
public class CustomerService {
	
	@Inject
	private DataSource dataSource;

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

	public DataSource getDataSource() {
		return dataSource;
	}
}
