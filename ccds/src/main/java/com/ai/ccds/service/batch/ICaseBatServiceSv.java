package com.ai.ccds.service.batch;

import java.util.List;

import com.ai.ccds.entity.batch.CaseBat;
import com.ai.ccds.exception.ServiceException;
import com.ai.ccds.usermodel.SeachRs;
import com.ai.ccds.usermodel.batch.CaseBatModel;

public interface ICaseBatServiceSv {
	/**
	 * 删除指定的批次
	 * 作者：陈嘉瑛
	 * 时间：2018-01-06
	 * @param pid
	 * @return
	 */
	void deleteCaseBatByPrimaryKey(String pid) throws ServiceException;

	/**
	 * 新增批次
	 * 作者：陈嘉瑛
	 * 时间：2018-01-06
	 * @param record
	 * @throws ServiceException
	 */
	void insertCaseBat(CaseBat record) throws ServiceException;

    /**
	 * 新增字段不为空的批次
	 * 作者：陈嘉瑛
	 * 时间：2018-01-06
	 * @param record
	 * @throws ServiceException
	 */
	void insertCaseBatSelective(CaseBat record) throws ServiceException;

    /**
	 * 通过主键获取批次
	 * 作者：陈嘉瑛
	 * 时间：2018-01-06
	 * @param pid
	 * @throws ServiceException
	 */
    CaseBat selectCaseBatByPrimaryKey(String pid) throws ServiceException;

    /**
     * 更新字段不为空批次-通过主键
     * 作者：陈嘉瑛
	 * 时间：2018-01-06
     * @param record
     * @return
     * @throws ServiceException
     */
    void updateCaseBatByPrimaryKeySelective(CaseBat record) throws ServiceException;

    /**
     * 更新批次-通过主键
     * 作者：陈嘉瑛
	 * 时间：2018-01-06
     * @param record
     * @return
     * @throws ServiceException
     */
    void updateCaseBatByPrimaryKey(CaseBat record) throws ServiceException;
    
    /**
     * 获取批次列表
     * 作者：陈嘉瑛
	 * 时间：2018-01-06
     * @param seachModel
     * @return
     * @throws ServiceException
     */
    List<CaseBat> lsCaseBatByFind(CaseBatModel seachModel) throws ServiceException;

    /**
     * 获取批次列表-分页
     * 作者：陈嘉瑛
	 * 时间：2018-01-06
     * @param seachModel
     * @return
     * @throws ServiceException
     */
	SeachRs<CaseBat> lsCaseBatByPage(CaseBatModel seachModel) throws ServiceException;

	/**
	 * 删除批次
	 * 作者：陈嘉瑛
	 * 时间：2018-01-06
	 * @param ids
	 * @throws ServiceException
	 */
	void deleteUnPhysics(String ids) throws ServiceException;
}
