package com.ai.ccds.exception;

/**
 * 异常编码配置
 * @author 陈嘉瑛
 * @version 2016-11-15
 *
 */
public enum ExceptionCodeEnum {
	C_9999("C_9999", "未知错误");
	
	private String code;
	private String msg;
	
	ExceptionCodeEnum(String code, String msg){
		this.code = code; 
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
