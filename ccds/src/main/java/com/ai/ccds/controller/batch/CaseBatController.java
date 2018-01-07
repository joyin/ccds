package com.ai.ccds.controller.batch;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.ccds.entity.batch.CaseBat;
import com.ai.ccds.exception.ServiceException;
import com.ai.ccds.service.batch.ICaseBatServiceSv;
import com.ai.ccds.usermodel.SeachRs;
import com.ai.ccds.usermodel.batch.CaseBatModel;
import com.ai.ccds.util.CommonUtil;
import com.ai.ccds.util.config.userCache.CatcheUserInfo;
import com.ai.excel.ExcelUtil;

@Controller
@RequestMapping("caseBatch")
public class CaseBatController {
	@Autowired
	private ICaseBatServiceSv caseBatServiceSv;

	@RequestMapping("batchs")
	public String go2Cases(HttpServletRequest request, HttpServletResponse response){
		return "batch/batchs";
	}
	
	@RequestMapping("batchLs")
	@ResponseBody
	public Object findCases(HttpServletRequest request, HttpServletResponse response, CaseBatModel caseModel){
		try {
			CommonUtil.reSetSeachModel(request, caseModel);
			SeachRs<CaseBat> caseBatLs = caseBatServiceSv.lsCaseBatByPage(caseModel);
			return CommonUtil.getPageData(request, caseBatLs);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("edit/{id}")
	public String go2Update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id){
		try {
			request.setAttribute("caseBat", caseBatServiceSv.selectCaseBatByPrimaryKey(id));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "batch/batchEdit";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Object doUpdate(HttpServletRequest request, HttpServletResponse response, CaseBat caseBat){
		try {
			caseBatServiceSv.updateCaseBatByPrimaryKeySelective(caseBat);
			return CommonUtil.responseSuccess("修改批次成功!", null);
		} catch (ServiceException e) {
			e.printStackTrace();
			return CommonUtil.responseError("修改批次失败!", null);
		}
	}
	
	@RequestMapping("edit")
	public String go2Add(HttpServletRequest request, HttpServletResponse response){
		return "batch/batchEdit";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Object doAdd(HttpServletRequest request, HttpServletResponse response, CaseBat caseBat){
		try {
			caseBatServiceSv.insertCaseBat(caseBat);
			return CommonUtil.responseSuccess("添加批次成功!", null);
		} catch (ServiceException e) {
			e.printStackTrace();
			return CommonUtil.responseError("添加批次失败!", null);
		}
	}
	
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids){
		try {
			caseBatServiceSv.deleteUnPhysics(ids);
			return CommonUtil.responseSuccess("删除批次成功!", null);
		} catch (ServiceException e) {
			e.printStackTrace();
			return CommonUtil.responseError("删除批次失败!", null);
		}
	}
	
	/**
	 * 执行导出
	 * 作者：陈嘉瑛
	 * 时间：2017-03-06
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void doExpert(HttpServletRequest request, HttpServletResponse response, CaseBatModel batchModel){
		try {
			String flag = request.getParameter("flag");
			String title = request.getParameter("title");
			String headerStr = request.getParameter("columnNames");
			String headerKey = request.getParameter("columnFields");
			List<CaseBat> ls = null;
			if("all".equals(flag)){
				ls = caseBatServiceSv.lsCaseBatByFind(batchModel);
			}else{
				CommonUtil.reSetSeachModel(request, batchModel);
				SeachRs<CaseBat> lsPage = caseBatServiceSv.lsCaseBatByPage(batchModel);
				ls = lsPage.getRows();
			}
			//定义时间格式
			ExcelUtil.dateFormate = "yyyy-MM-dd";
			ls = ls == null ? new ArrayList<CaseBat>() : ls;
			CatcheUserInfo u = CommonUtil.getCatchUserInfo(request);
			String userName = u == null ? null : u.getUserName();
			ExcelUtil.exportExcel(title, headerStr, headerKey, ls, userName, response, CaseBat.class, null);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
