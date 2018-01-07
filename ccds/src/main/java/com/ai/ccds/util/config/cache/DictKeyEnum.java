package com.ai.ccds.util.config.cache;

/**
 * 字典表dict字段注册枚举类，
 * 所有字典的dict唯一编码都注册在该枚举类中，便于后续维护
 * @author joyin
 *
 */
public enum DictKeyEnum {
	SEX("SEX", "性别")
	;

	//dict唯一标识
	private String dictKey;
	//该字典的描述
	private String remark;
	
	DictKeyEnum(String dictKey, String remark){
		this.dictKey = dictKey;
		this.remark = remark;
	}

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
