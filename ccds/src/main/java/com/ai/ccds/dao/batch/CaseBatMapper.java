package com.ai.ccds.dao.batch;

import java.util.List;

import com.ai.ccds.entity.batch.CaseBat;
import com.ai.ccds.usermodel.batch.CaseBatModel;

public interface CaseBatMapper {
    int deleteByPrimaryKey(String cbatId);

    int insert(CaseBat record);

    int insertSelective(CaseBat record);

    CaseBat selectByPrimaryKey(String cbatId);

    int updateByPrimaryKeySelective(CaseBat record);

    int updateByPrimaryKey(CaseBat record);
    
    List<CaseBat> lsByFind(CaseBatModel seachModel);

	int lsByCount(CaseBatModel seachModel);

	List<CaseBat> lsByPage(CaseBatModel seachModel);
}