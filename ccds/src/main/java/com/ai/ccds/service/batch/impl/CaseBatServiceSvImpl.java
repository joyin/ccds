package com.ai.ccds.service.batch.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.ccds.dao.batch.CaseBatMapper;
import com.ai.ccds.entity.batch.CaseBat;
import com.ai.ccds.exception.ServiceException;
import com.ai.ccds.service.batch.ICaseBatServiceSv;
import com.ai.ccds.usermodel.SeachRs;
import com.ai.ccds.usermodel.batch.CaseBatModel;
import com.ai.ccds.util.MyBatiesServiceBase;

@Service
public class CaseBatServiceSvImpl implements ICaseBatServiceSv {
	@Autowired
	private CaseBatMapper caseBatMapper;

	@Override
	public void deleteCaseBatByPrimaryKey(String pid) throws ServiceException {
		caseBatMapper.deleteByPrimaryKey(pid);
	}

	@Override
	public void insertCaseBat(CaseBat record) throws ServiceException {
		caseBatMapper.insert(record);
	}

	@Override
	public void insertCaseBatSelective(CaseBat record) throws ServiceException {
		caseBatMapper.insertSelective(record);
	}

	@Override
	public CaseBat selectCaseBatByPrimaryKey(String pid)
			throws ServiceException {
		return caseBatMapper.selectByPrimaryKey(pid);
	}

	@Override
	public void updateCaseBatByPrimaryKeySelective(CaseBat record)
			throws ServiceException {
		record.setCbatLog(new Date());
		caseBatMapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateCaseBatByPrimaryKey(CaseBat record)
			throws ServiceException {
		record.setCbatLog(new Date());
		caseBatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CaseBat> lsCaseBatByFind(CaseBatModel seachModel)
			throws ServiceException {
		return caseBatMapper.lsByFind(seachModel);
	}

	@Override
	public SeachRs<CaseBat> lsCaseBatByPage(CaseBatModel seachModel)
			throws ServiceException {
		return MyBatiesServiceBase.queryByPage(caseBatMapper, CaseBat.class, seachModel);
	}

	@Override
	public void deleteUnPhysics(String ids) throws ServiceException {
		/*if(StringUtils.isEmpty(ids)) return ;
		for(String id : ids.split(",")){
			caseBatMapper.deleteByPrimaryKey(id);
		}*/
	}

}
