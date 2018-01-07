package com.ai.ccds.usermodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 查询基础bean
 * @author 陈嘉瑛
 * @version 2016-11-29
 *
 */
@SuppressWarnings("serial")
public class SeachModel implements Serializable{
	//当前页
	private int page;
	//下标
	private int offSet;
	//一页显示多少数据
	private int limit;
	//排序类型，desc, asc
	private String sortOrder;
	//排序名:如：name,age
	private String sortNames;
	//多名称，排序类型，name desc, age asc
	private String sortOrderNames;
	//字典
	private Map<String, String> dict;
	
	public int getPage() {
		if(page == 0) page = 1;
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffSet() {
		int offSet = page;
		if(offSet > 0) offSet = offSet - 1;
		this.offSet = offSet * limit;
		return this.offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public int getLimit() {
		if(limit == 0) limit = 15;
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortNames() {
		return sortNames;
	}
	public void setSortNames(String sortNames) {
		this.sortNames = sortNames;
	}
	public String getSortOrderNames() {
		return sortOrderNames;
	}
	public void setSortOrderNames(String sortOrderNames) {
		this.sortOrderNames = sortOrderNames;
	}
	public Map<String, String> getDict() {
		return dict;
	}
	public void addDict(String field, String dictKey) {
		if(StringUtils.isEmpty(field) || StringUtils.isEmpty(dictKey)) return;
		dict = dict == null ? new HashMap<String, String>() : dict;
		dict.put(field.trim(), dictKey.trim());
	}
	
}
