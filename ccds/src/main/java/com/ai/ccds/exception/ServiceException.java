package com.ai.ccds.exception;

@SuppressWarnings("serial")
public class ServiceException extends Exception{
	private Object rsObj;
	
	public ServiceException(ExceptionCodeEnum code){
		super(code.getCode());
		System.out.println(code.getMsg());
	}
	
	public Object getRsObj() {
		return rsObj;
	}

	public void setRsObj(Object rsObj) {
		this.rsObj = rsObj;
	}
}
