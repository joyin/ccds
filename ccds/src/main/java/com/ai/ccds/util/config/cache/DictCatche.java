package com.ai.ccds.util.config.cache;
/*package com.ai.fjdrp.util.config.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.redis.single.RedisUtilHset;
import com.ai.soho.common.expert.ExpertConst;
import com.ai.soho.common.expert.ExpertDictKeyEnum;
import com.ai.soho.expert.entity.ColumnSpecial;
import com.ai.soho.expert.entity.ExpSysArea;
import com.ai.soho.expert.entity.ExpSysDict;
import com.ai.soho.expert.service.IColumnSpecialSv;
import com.ai.soho.expert.service.IExpSysDictSv;
import com.ai.soho.userModel.ExpSysDictModel;
import com.ai.soho.userModel.expert.ColumnSpecialModel;
import com.ai.soho.util.ApplicationContextHelper;

*//**
 * 字典缓存表
 * @author 陈嘉瑛
 * @version 2016-04-13
 *
 *//*
public class DictCatche {
	private static Log logger = LogFactory.getLog(ExpertDictCatche.class);

	//字典key-val
	private static Map<String, Map<String, String>> dictKeyVal = new LinkedHashMap<String, Map<String, String>>(50); 
	//字典key-val
	private static Map<String, Map<String, String>> dictValKey = new LinkedHashMap<String, Map<String, String>>(50); 

	*//**
	 * 清空所有的缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 *//*
	public static void clearDict(){
		logger.debug("1========="+ExpertConst.REDIS_DICT);
		RedisUtilHset redisUtilHset = new RedisUtilHset();
		logger.debug("2========="+ExpertConst.REDIS_DICT);
		redisUtilHset.del(ExpertConst.REDIS_DICT);
		logger.debug("3========="+ExpertConst.REDIS_DICT);
	}
	
	*//**
	 * 初始化字典-并缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 * @param catcheCode 缓存编码
	 * @throws Exception 
	 *//*
	public static Boolean initDict(Integer catcheCode) throws Exception{
		logger.debug("=========正式初始化字典============");
		//标记是否是缓存所有信息
		catcheCode = catcheCode == null ? 0 : catcheCode;
		//如果是更新全部缓存，先清空redis中的全部缓存信息
		logger.debug("=========redis============");
		if(catcheCode.intValue() == 0) clearDict();
		logger.debug("=========redis redis============");
		//先从redis中取得缓存值
		dictKeyVal = getDictKeyVal();
		dictValKey = getDictValKey();
		//判断如果redis中没有缓存信息，则实例化map对象
		dictKeyVal = dictKeyVal == null ? new LinkedHashMap<String, Map<String, String>>() : dictKeyVal;
		dictValKey = dictValKey == null ? new LinkedHashMap<String, Map<String, String>>() : dictValKey;
		Boolean isAll = false, ifReflashDict = true;
		switch(catcheCode.intValue()){
		case 0 : 
			isAll = true;
		case 1:
			//初始化字典信息-并缓存
			doInitDict();
			logger.debug("=========初始化专家字典完成============");
			if(!isAll) break;
		case 2:
			//初始主题-并缓存
			initSpecial();
			logger.debug("=========初始化主题完成============");
			if(!isAll) break;
		case 3:
			//初始化区域
			initRegion();
			logger.debug("=========初始化区域完成============");
			if(!isAll) break;
		case 4:
			//初始化接口并缓存
			//initBizCode();
			if(!isAll) {
				ifReflashDict = false;
				break;
			}
		case 5:
			//初始化接口并角色-用户类型
			//initRole();
			if(!isAll) break;
		}
		if(!ifReflashDict) return true;
		logger.debug("=========初始化字典结束============");
		//把字典信息写入redis缓存中-因为连接池是单例，所以无需关闭它
		new RedisUtilHset().hmset(ExpertConst.REDIS_DICT, 
				new Object[]{ExpertConst.REDIS_DICTKEYVAL,ExpertConst.REDIS_DICTVALKEY}, 
				new Object[]{dictKeyVal,dictValKey});
		logger.debug("=========字典成功缓存到redis中============");
		//清空这些字典map对象
		dictKeyVal.clear();
		dictValKey.clear();
		dictKeyVal = null;
		dictValKey = null;
		return true;
	}
	
	*//**
	 * 初始化区域
	 * 作者：陈嘉瑛
	 * 时间：2016-12-23
	 *//*
	private static void initRegion() throws Exception{
		IExpSysDictSv expSysDictSvImpl = (IExpSysDictSv)ApplicationContextHelper.getBean("expSysDictSvImpl");
		List<ExpSysArea> lsExpSysArea = expSysDictSvImpl.findExpSysAreaLs();
		String regionDict = ExpertDictKeyEnum.SYS_REGION_DICT.getDictKey();
		//加载到字典缓存中
		Map<String, String> keyVal = new LinkedHashMap<String, String>();
		Map<String, String> valKey = new LinkedHashMap<String, String>();
		for(ExpSysArea  b : lsExpSysArea){
			keyVal.put(b.getName(), b.getId());
			valKey.put(b.getId(), b.getName());
		}
		putDictToMap(regionDict, keyVal, valKey);
		//构造层级字典
		constructRegionLevelsDict(lsExpSysArea, "0");
	}

	*//**
	 * 构造区域层级字典-并缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 *//*
	private static void constructRegionLevelsDict(List<ExpSysArea> regionLs, String pid) {
		String dictStr = ExpertDictKeyEnum.SYS_REGION_DICT_.getDictKey() + pid;
		//取出所有的根
		Map<String, String> root = new LinkedHashMap<String, String>();
		for(ExpSysArea  b : regionLs){
			String subPid = b.getPid();
			subPid = StringUtils.isEmpty(subPid) ? "0" : subPid;
			if(subPid.equals(pid)) 
				root.put(b.getName(), b.getId());
		}
		if(root.size() == 0) return;
		putDictToMap(dictStr, root, null);
		for(String key : root.keySet()) {
			if(StringUtils.isEmpty(key)) continue;
			constructRegionLevelsDict(regionLs, root.get(key));
		}
	}

	*//**
	 * 初始化主题字典并缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-12-23
	 * @throws Exception
	 *//*
	private static void initSpecial() throws Exception{
		IColumnSpecialSv columnSpecialSvImpl = (IColumnSpecialSv)ApplicationContextHelper.getBean("columnSpecialSvImpl");
		ColumnSpecialModel columnSpecialModel = new ColumnSpecialModel();
		columnSpecialModel.setSortNames("order_num");
		columnSpecialModel.setSortOrder("asc");
		columnSpecialModel.setStatus(ExpertConst.SYS_STATUS_1);
		List<ColumnSpecial> lsColumnSpecial = columnSpecialSvImpl.lsColumnSpecialByFind(columnSpecialModel);
		if(lsColumnSpecial != null){
			Map<String, String> keyVal = new HashMap<String, String>();
			Map<String, String> valKey = new HashMap<String, String>();
			for(ColumnSpecial s : lsColumnSpecial){
				keyVal.put(s.getSpecialName(), s.getSpecialId());
				valKey.put(s.getSpecialId(), s.getSpecialName());
			}
			dictKeyVal.put(ExpertDictKeyEnum.SPECIAL_DICT.getDictKey(), keyVal);
			dictValKey.put(ExpertDictKeyEnum.SPECIAL_DICT.getDictKey(), valKey);
		}
	}

	*//**
	 * 初始化字典信息-并缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 * @throws Exception 
	 *//*
	private static void doInitDict() throws Exception{
		logger.debug("========获取字典业务类实例============");
		IExpSysDictSv iExpSysDictSv = (IExpSysDictSv)ApplicationContextHelper.getBean("expSysDictSvImpl");
		ExpSysDictModel expSysDictModel = new ExpSysDictModel();
		expSysDictModel.setStatus(ExpertConst.SYS_STATUS_1);
		//获取所有字典编码
		List<Map<String,Object>> sysDictLs = iExpSysDictSv.findExpSysDictCode(expSysDictModel);
		if(sysDictLs == null || sysDictLs.size() == 0) return; 
		logger.debug("========获取所有字典编码============"+sysDictLs.size());
		//获取所有字典以及对应的值
		List<ExpSysDict> sysDictItemLs = iExpSysDictSv.findExpSysDict(expSysDictModel);
		if(sysDictItemLs == null || sysDictItemLs.size() == 0) return; 
		logger.debug("========获取所有字典以及对应的值============"+sysDictItemLs.size());
		//遍历封装所有的字典信息到缓存中
		for(Map<String,Object> dict : sysDictLs){
			if(sysDictItemLs == null) continue;
			if(dict == null) continue;
			if(dict.get("dictCode") == null) continue;
			String dictCode = (String)dict.get("dictCode");
			if(StringUtils.isEmpty(dictCode)) continue;
			dictCode = dictCode.trim();
			Map<String, String> keyVal = new LinkedHashMap<String, String>();
			Map<String, String> valKey = new LinkedHashMap<String, String>();
			for(ExpSysDict dictItem : sysDictItemLs){
				if(dictItem == null) continue;
				String dictCodeTemp = dictItem.getDictCode();
				if(StringUtils.isEmpty(dictCodeTemp)) continue;
				dictCodeTemp = dictCodeTemp.trim();
				if(!dictCodeTemp.equals(dictCode)) continue;
				String key = dictItem.getDictName();
				String val = dictItem.getDictVal();
				if(StringUtils.isEmpty(key) || StringUtils.isEmpty(val)) continue;
				keyVal.put(key, val);
				valKey.put(val, key);
			}
			putDictToMap(dictCode, keyVal, valKey);
		}
	}

	*//**
	 * 获取字典值-根据字典编码和字典名称
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 * @param dictCode 字典编码
	 * @param itemName 字典值对应的字典名称
	 * @return
	 *//*
	public static String getItemVal(String dictCode, String itemName){
		Map<String, Map<String, String>> keyValMap = getDictKeyVal();
		if(keyValMap == null) return null;
		Map<String, String> keyVal = keyValMap.get(dictCode);
		if(keyVal == null) return null;
		return keyVal.get(itemName);
	}

	*//**
	 * 获取字典名称-根据字典编码和字典值
	 * 作者：陈嘉瑛
	 * 时间：2016-04-13
	 * @param dictCode 字典编码
	 * @param itemVal 字典名称对应的字典值
	 * @return
	 *//*
	public static String getItemName(String dictCode, String itemVal){
		Map<String, Map<String, String>> valKeyMap = getDictValKey();
		if(valKeyMap == null) return null;
		Map<String, String> valKey = valKeyMap.get(dictCode);
		if(valKey == null) return null;
		return valKey.get(itemVal);
	}

	*//**
	 * 往字典map中添加对应字典信息
	 * 作者：陈嘉瑛
	 * 时间：2016-06-29
	 * @param dictCode
	 * @param keyVal 字典key-val, 如：失效:0
	 * @param valKey 字典val-key, 如：0:失效,  USER_NAME:张三
	 *//*
	private static void putDictToMap(String dictCode, Map<String, String> keyVal, Map<String, String> valKey){
		if(keyVal != null){
			dictKeyVal.put(dictCode, keyVal);
		}
		if(valKey != null){
			dictValKey.put(dictCode, valKey);
		}
	}

	*//**
	 * 取得所有key-val 字典缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-08-03
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getDictKeyVal() {
		Object keyValMap = new RedisUtilHset().hget(ExpertConst.REDIS_DICT, ExpertConst.REDIS_DICTKEYVAL);
		if(keyValMap == null) return null;
		return (Map<String, Map<String, String>>)keyValMap;
	}

	*//**
	 * 设置key-val 到字典缓存
	 * @param dictKeyVal
	 *//*
	public static void setDictKeyVal(Map<String, Map<String, String>> dictKeyVal) {
		new RedisUtilHset().hset(ExpertConst.REDIS_DICT, ExpertConst.REDIS_DICTKEYVAL, dictKeyVal);
	}
	
	*//**
	 * 取得指定字典下的对应key的val
	 * @param dictcode 字典code
	 * @param key 
	 * @return
	 *//*
	public static String getValByKey(String dictcode, String key){
		Map<String, Map<String, String>> keyValLs = getDictKeyVal();
		if(keyValLs == null) return null;
		Map<String, String> keyVal = keyValLs.get(dictcode);
		if(keyVal == null) return null;
		return keyVal.get(key);
	}

	*//**
	 * 取得所有val-key 字典缓存
	 * 作者：陈嘉瑛
	 * 时间：2016-08-03
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getDictValKey() {
		Object valKeyMap = new RedisUtilHset().hget(ExpertConst.REDIS_DICT, ExpertConst.REDIS_DICTVALKEY);
		if(valKeyMap == null) return null;
		return (Map<String, Map<String, String>>)valKeyMap;
	}

	*//**
	 * 设置val-key 到字典缓存
	 * @param dictKeyVal
	 *//*
	public static void setDictValKey(Map<String, Map<String, String>> dictValKey) {
		new RedisUtilHset().hset(ExpertConst.REDIS_DICT, ExpertConst.REDIS_DICTVALKEY, dictValKey);
	}
	
	*//**
	 * 取得指定字典下的对应val的key
	 * @param dictcode 字典code
	 * @param key 
	 * @return
	 *//*
	public static String getKeyByVal(String dictcode, String val){
		Map<String, Map<String, String>> valKeyLs = getDictValKey();
		if(valKeyLs == null) return null;
		Map<String, String> valKey = valKeyLs.get(dictcode);
		if(valKey == null) return null;
		return valKey.get(val);
	}
}
*/