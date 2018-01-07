package com.ai.ccds.util;

import java.lang.reflect.Method;
import java.util.List;

import com.ai.ccds.usermodel.SeachModel;
import com.ai.ccds.usermodel.SeachRs;

/**
 * mybaties业务层基类
 * 作者：陈嘉瑛
 * 时间：2016-12-13
 * @author joyin
 *
 */
public class MyBatiesServiceBase {
	
	@SuppressWarnings("unchecked")
	public static <T> SeachRs<T> queryByPage(final Object mapper, final Class<T> beanCls, final SeachModel seachModel){
		try {
			Method lsByCount = mapper.getClass().getMethod("lsByCount", seachModel.getClass());
			Object lsCount = lsByCount.invoke(mapper, seachModel);
			SeachRs<T> seachRs = new SeachRs<T>();
			if(lsCount == null) seachRs.setCount(0);
			else {
				int count = Integer.valueOf(lsCount.toString());
				seachRs.setCount(count);
				if(count > 0){
					Method lsByPage = mapper.getClass().getMethod("lsByPage", seachModel.getClass());
					Object lsRs = lsByPage.invoke(mapper, seachModel);
					if(lsRs == null) seachRs.setRows(null);
					else seachRs.setRows((List<T>)lsRs);
				}else{
					seachRs.setRows(null);
				}
			}
			seachRs.setSeachModel(seachModel);
			return seachRs;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
