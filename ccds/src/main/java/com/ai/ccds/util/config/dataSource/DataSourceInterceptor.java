package com.ai.ccds.util.config.dataSource;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import com.ai.PropertyUtil;

@Component  
public class DataSourceInterceptor {  
	//数据源名称集合-用于动态切换
	private static String dataSourseNames = PropertyUtil.getValue("resourceBundle.properties", "vacational.sql.datasource.name");
    //事务控制层
    private static String transactionFold = PropertyUtil.getValue("resourceBundle.properties", "vacational.sql.transaction.fold");
    
    private static Map<String, String> datasourceNameMap = new HashMap<String, String>();

	static{
		if(!StringUtils.isEmpty(dataSourseNames)){
			String[] dataSourceTemps = dataSourseNames.split(",");
			for(String dataSourceName : dataSourceTemps){
				if(dataSourceName.split(":").length != 2) continue;
				String[] dataSource = dataSourceName.split(":");
				datasourceNameMap.put(dataSource[0].trim(), dataSource[1].trim());
				if(!transactionFold.trim().equals("*"))
					datasourceNameMap.put(dataSource[0].trim()+"."+transactionFold.trim(), dataSource[1].trim());
				
			}
		}
		
	}
	
	public void setDataSource(JoinPoint jp) {
		String clsName = jp.getTarget().getClass().getName();
		for(String fold : datasourceNameMap.keySet()){
			if(clsName.indexOf(fold) == -1) continue;
			 DatabaseContextHolder.setCustomerType(datasourceNameMap.get(fold));  
			 return;
		}
    }  
}  

