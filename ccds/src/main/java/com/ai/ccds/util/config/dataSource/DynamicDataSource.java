package com.ai.ccds.util.config.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{  

	@Override  
	protected Object determineCurrentLookupKey() {  
		return DatabaseContextHolder.getCustomerType();   
	}
	
	public void determineTargetDataSourceSelf(){
		super.determineTargetDataSource();
	}
}  
