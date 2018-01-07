package com.ai.ccds.usermodel;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SeachRs<T> implements Serializable{
	//总条数
	private int count;
	//总页数
	private int allPage;
	//当前分页取到的数据
	private List<T> rows;
	//回传查询模态bean
	private SeachModel seachModel;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getAllPage() {
		allPage = count / seachModel.getLimit();
		if(count % seachModel.getLimit() != 0) allPage++;
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public SeachModel getSeachModel() {
		return seachModel;
	}
	public void setSeachModel(SeachModel seachModel) {
		this.seachModel = seachModel;
	}
	
}
