package com.ai.ccds.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	//附件保存路径
	public static String FILE_UPLOAD_PATH = "fileUpload";
	//用户session对应的key
	public static final String SESSION_USER = "SOHO_ADMIN_SESSION_USER";
	/*******************分页配置*****************************/
	//分页参数对接mybaties
	public static final String MYBATIES_OFFSET = "offset";//数据库获取数据下标
	public static final String MYBATIES_PAGESIZE = "pageSize";//每次获取多少条数据
	public static final String MYBATIES_SORTNAMES = "sortNames";//排序名
	public static final String MYBATIES_SORTTYPE = "sortType";//排序类型
	//分页参数对接jqgrid
	public static final String JQGRID_PAGE = "page";//当前页数
	public static final String JQGRID_TOTAL = "total";//总页数
	public static final String JQGRID_COUNT = "count";//总条数
	public static final String JQGRID_DATA = "data";//响应时作为总数据
	public static final String JQGRID_LIMIT = "limit";//请求时作为一页显示多少条，
	public static final String JQGRID_SIDX = "sidx";//排序名
	public static final String JQGRID_SORT = "sord";//排序类型

	/******************附件上传配置***************************/
	//文件类型及大小限制
	public static Map<String, String> limitTypeMap = new HashMap<String, String>();
	public static Map<String, Integer> limitSizeMap = new HashMap<String, Integer>();
	static{
		limitTypeMap.put("FILE_LIMIT_TYPE_01", "jpg,jpeg,png,");
		limitSizeMap.put("FILE_LIMIT_SIZE_01", 5*1024*1024);
	}

	/***************************redis缓存配置********************/
	//redis中字典缓存key
	public static final String REDIS_DICT = "FJDRP_DICT_REDIS";
	public static final String REDIS_DICTKEYVAL = "FJDRP_DICT_REDIS_DICTKEYVAL";
	public static final String REDIS_DICTVALKEY = "FJDRP_DICT_REDIS_DICTVALKEY";

	//系统生失效状态码，1=有效，2=无效
	public static final int SYS_STATUS_1 = 1;
	public static final int SYS_STATUS_2 = 2;

	//是否登录角色1=是，2=否
	public static final int IS_LOGIN_ROLE_1 = 1;
	public static final int IS_LOGIN_ROLE_2 = 2;

	//菜单类型1=目录，2=模块，3=菜单
	public static final String MENU_TYPE_1 = "1";
	public static final String MENU_TYPE_2 = "2";
	public static final String MENU_TYPE_3 = "3";

	/*********************字典编码*****************************/
}
