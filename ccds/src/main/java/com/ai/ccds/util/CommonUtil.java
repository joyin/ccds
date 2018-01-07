package com.ai.ccds.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ai.ccds.entity.sys.SysUser;
import com.ai.ccds.usermodel.SeachModel;
import com.ai.ccds.usermodel.SeachRs;
import com.ai.ccds.util.config.userCache.CatcheUserInfo;
import com.ai.excel.ExcelUtil;
import com.ai.excel.bean.ExcelCol;
import com.ai.excel.bean.ExcelRow;

public class CommonUtil {
	public static final String SUCCESS = "0000";
    public static final String FAILED = "1111";

    /**
     * 显示到页面-成功
     * 作者：陈嘉瑛
     * 时间：2016-11-29
     * @param msg
     * @param data
     * @return
     */
    public static Map<String,Object> responseSuccess( String msg, Object data) {
    	Map<String,Object> jo = new HashMap<String,Object>();
        jo.put("code", SUCCESS);
        jo.put("msg", msg);
        jo.put("data", data);
        return jo;
    }
    
    /**
     * 响应到页面-失败
     * 作者：陈嘉瑛
     * 时间：2016-11-29
     * @param msg
     * @param data
     * @return
     */
    public static Map<String,Object> responseError( String msg, Object data) {
    	Map<String,Object> jo = new HashMap<String,Object>();
        jo.put("code", FAILED);
        jo.put("msg", msg);
        jo.put("data", data);
        return jo;
    }
    
    /**
     * 重置分页参数
     * 作者：陈嘉瑛
     * 时间：2016-12-14
     * @param request
     * @return
     */
    public static void reSetSeachModel(HttpServletRequest request, SeachModel seachModel){
        Object page = request.getParameter(Constants.JQGRID_PAGE);
        Object rows =request.getParameter(Constants.JQGRID_LIMIT);
        if (page == null || rows == null) {
        	page = 1; //默认第一页
        	rows = 10;//默认一页显示10条
        }
        seachModel.setPage(Integer.valueOf(page.toString()));
        seachModel.setLimit(Integer.valueOf(rows.toString()));
        
        Integer pageInt = Integer.valueOf(page.toString());
        pageInt = pageInt == 0 ? pageInt : pageInt - 1;
        pageInt = pageInt *  Integer.valueOf(rows.toString());
        if(request.getParameter(Constants.JQGRID_SIDX) != null && request.getParameter(Constants.JQGRID_SORT) != null){
        	 String sidx = (String)request.getParameter(Constants.JQGRID_SIDX);
             String sord = (String)request.getParameter(Constants.JQGRID_SORT);
             String sortNames = "";
             for(String name : sidx.split(",")){
            	 if(StringUtils.isEmpty(name)) continue;
            	 sortNames = "," + name + " " +sord;
             }
             if(sortNames.length() > 0) sortNames = sortNames.substring(1);
             seachModel.setSortOrderNames(sortNames);
        }
    }

    /**
     * 封装分页数据，并响应给页面
     * @return
     */
    public static Map<String,Object> getPageData(HttpServletRequest request, SeachRs<?> seachRs){
    	//一页显示多少条
    	Map<String,Object> jo = new HashMap<String,Object>();
    	//当前页数
        jo.put(Constants.JQGRID_PAGE, seachRs.getSeachModel().getPage());
        //总页数
        jo.put(Constants.JQGRID_TOTAL, seachRs.getAllPage());
        //总条数
        jo.put(Constants.JQGRID_COUNT, seachRs.getCount());
        //总数据
        jo.put(Constants.JQGRID_DATA, seachRs.getRows());
        jo.put("code", 0);
    	jo.put("msg", "");
        return jo;
    }
    
    /**
     * 报表导出
     * @param title
     * @param request
     * @param response
     * @param ls
     */
    public static void doExportExcel(String title,HttpServletRequest request, HttpServletResponse response,List<Map<String, Object>> ls){
		//构造导出的excel
		List<ExcelCol> header = new ArrayList<ExcelCol>();
		String[] columnNames = request.getParameter("columnNames").split(",");
		for(int i=0; i<columnNames.length; i++){
			ExcelCol c = new ExcelCol();
			c.setPid(0L);
			c.setId(Long.valueOf(i + 1));
			c.setVal(columnNames[i]);
			header.add(c);
		}
		String[] columnFields = request.getParameter("columnFields").split(",");
		List<ExcelRow> dataLs = new ArrayList<ExcelRow>();
		for(Map<String, Object> m : ls){
			ExcelRow r = new ExcelRow();
			List<ExcelCol> cols = new ArrayList<ExcelCol>();
			for(int i=0; i<columnFields.length; i++){
				ExcelCol c = new ExcelCol();
				c.setPid(0L);
				c.setId(Long.valueOf(i + 1));
				c.setVal((m.get(columnFields[i])).toString());
				cols.add(c);
			}
			r.setCols(cols);
			dataLs.add(r);
		}
		ExcelUtil.exportExcel(title, header, dataLs, "", response);
	}
    
    /**
     * 获取缓存中的用户信息
     * @param request
     * @return
     */
    public static CatcheUserInfo getCatchUserInfo(HttpServletRequest request){
    	return (CatcheUserInfo)request.getSession().getAttribute(Constants.SESSION_USER);
    }

    /**
     * 初始化登录人员
     * 作者：陈嘉瑛
     * 时间：2017-01-10
     * @param request
     * @param sysUser
     */
	public static void initUserCatche(HttpServletRequest request, SysUser sysUser) {
		CatcheUserInfo cacheUser = new CatcheUserInfo();
		cacheUser.setUserId(sysUser.getUserUuid());
		cacheUser.setUserName(sysUser.getUserName());
		request.getSession().setAttribute(Constants.SESSION_USER, cacheUser);
	}
	
	/**
     * 重置缓存人员
     * 作者：陈嘉瑛
     * 时间：2017-01-10
     * @param request
     * @param sysUser
     */
	public static void resetUserCatche(HttpServletRequest request, CatcheUserInfo cacheUser) {
		request.getSession().setAttribute(Constants.SESSION_USER, cacheUser);
	}
}
