package com.ai.ccds.util.config.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.ai.ccds.util.Constants;
import com.ai.redis.single.RedisUtilHset;

/**
 * 自定义下拉标签-兼容级联
 * @author 陈嘉瑛
 * @version 2016-0
 *
 */
@SuppressWarnings("serial")
public class SelectTag extends TagSupport {
	//字典编码-必填
	private String dictCode;
	//字典编码-后缀，一般在级联的时候用
	private String dictCodeSuffix;
	//默认值
	private String defVal;
	//id-必填
	private String id;
	//subId
	private String subId;
	//名称-必填
	private String name;
	//类样式
	private String cls;
	//自定义样式
	private String style;
	//onchange
	private String onChange;
	//onclick
	private String onClick;
	//是否只读
	private String disabled;
	//自定义提示语句，默认请选择
	private String selTip;
	//自定义提示语句，对应的值，默认空字符串
	private String selTipVal;
	//不希望显示的列表，如：1,2,3,4
	private String unShowVals;

	@Override
	public int doStartTag() throws JspException {
		try {
			String selStr = constructSel();
			if(StringUtils.isEmpty(selStr)) return EVAL_PAGE;
			String onChgScript = scriptOnChange();
            JspWriter out = this.pageContext.getOut();
            out.println(onChgScript + "\n" + selStr);
            out.flush();
		} catch(Exception e) {
            throw new JspException(e.getMessage());
        }
		return EVAL_PAGE;
	}

	/**
	 * 构造select下拉菜单
	 * 作者：陈嘉瑛
	 * 时间：2016-04-16
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String constructSel(){
		//获取指定字典编码的字典信息
		Object dict = new RedisUtilHset().hget(Constants.REDIS_DICT, Constants.REDIS_DICTKEYVAL);
		Map<String, Map<String, String>> dictKeyVal = null;
		if(dict == null) dictKeyVal = new HashMap<String, Map<String, String>>();
		else dictKeyVal = (Map<String, Map<String,String>>) dict;
		selTip = StringUtils.isEmpty(selTip) ? "请选择" : selTip;
		selTipVal = StringUtils.isEmpty(selTipVal) ? "" : selTipVal;
		StringBuilder sel = new StringBuilder();
		sel.append("<option value='"+selTipVal+"'>" + selTip + "</option>");
		String dictCodeTemp = dictCode + (StringUtils.isEmpty(dictCodeSuffix) ? "" : dictCodeSuffix);
		Map<String, String> keyVal = dictKeyVal.get(dictCodeTemp);
		if(keyVal != null) {
			for(String key : keyVal.keySet()) {
				if(StringUtils.isEmpty(key)) continue;
				String val = keyVal.get(key);
				if(StringUtils.isEmpty(val)) continue;
				if(!StringUtils.isEmpty(unShowVals)){
					Boolean canAppend = true;
					for(String unShowVal : unShowVals.split(",")){
						if(!val.equals(unShowVal)) continue;
						canAppend = false;
						break;
					}
					if(!canAppend) continue; 
				}
				String selected = "";
				if(val.equals(defVal)) selected = "selected = 'selected'";
				sel.append("<option value='" + val + "' " + selected + " >" + key + "</option>");
			}
		}
		cls = StringUtils.isEmpty(cls) ? "" : cls;
		style = StringUtils.isEmpty(style) ? "" : style;
		disabled = StringUtils.isEmpty(disabled) ? "" : disabled;
		if(disabled.equals("disabled")) disabled = "disabled = 'disabled'";
		String selStr = 
				"<select selTipVal='"+selTipVal+"' selTip='" + selTip + "' " + disabled + "  style='" + style + "' class='" + cls + "' id='" + id + "' name='" + name + "' " +
						(StringUtils.isEmpty(onClick) ? "" : "onclick='" + onClick + ";'") + 
						"onChange='go2SelSubFrom"+id+"(this);'>" +
						sel.toString() + 	
				"</select>";
		return selStr;
	}

	/**
	 * 构造onchange脚本事件
	 * 作者：陈嘉瑛
	 * 时间：2016-04-16
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String scriptOnChange(){
		//取得该层次字典下所有单层次的字典
		Object dict = new RedisUtilHset().hget(Constants.REDIS_DICT, Constants.REDIS_DICTKEYVAL);
		Map<String, Map<String, String>> dictKeyVal = null;
		if(dict == null) dictKeyVal = new HashMap<String, Map<String, String>>();
		else dictKeyVal = (Map<String, Map<String,String>>) dict;
		Map<String, Map<String, String>> subDictKeyVal = new HashMap<String, Map<String, String>>();
		for(String key : dictKeyVal.keySet()) {
			if(key.startsWith(dictCode)) 
				subDictKeyVal.put(key, dictKeyVal.get(key));
		}
		String dictKeyValStr = JSONObject.fromObject(subDictKeyVal).toString();
		String selSubFun = "go2SelSubFrom"+id+"(obj)";
		StringBuilder script = new StringBuilder();
		script.append("<script type='text/javascript'>\n");
		script.append("	function " + selSubFun + "{\n");
		if(!StringUtils.isEmpty(subId)){
			script.append("		var unShowVals = " + unShowVals + ";\n");
			script.append("		var dictKeyVal = " + dictKeyValStr + ";\n");
			script.append("		var dictCodePre = '" + dictCode + "';\n ");
			script.append("		var pid = '" + id + "';\n ");
			script.append("		var pVal = $('#'+pid).val();\n");
			script.append("		var selFlag = dictCodePre + pVal;\n");
			script.append("		var subDictKeyVal = dictKeyVal[selFlag]; \n");
			script.append("		var subId = '" + subId + "';\n ");
			script.append("		var selStr = ''; \n");
			script.append("		if(subDictKeyVal){\n");
			script.append("			for(var key in subDictKeyVal) {\n");
			script.append("				var canAppend = true; \n");
			script.append("				var val = subDictKeyVal[key]; \n");
			script.append("				if(unShowVals) {\n");
			script.append("					var unShowValsTemp = unShowVals.split(','); \n");
			script.append("					for(var i=0; i<unShowValsTemp.length; i++){ \n");
			script.append("						if(unShowValsTemp[i] != val) continue; \n");
			script.append("						canAppend = false; \n");
			script.append("						break; \n");
			script.append("					} \n");
			script.append("				}\n");
			script.append("				if(!canAppend) continue;\n");
			script.append("				selStr = selStr + '<option flag=\"" + dictCode + "\" value=\"' + val + '\" >' + key + '</option>';\n");
			script.append("			}\n");
			script.append("		}\n");
			script.append("		var subIds = subId.split(';');\n");
			script.append("		var k = 0;\n");
			script.append("		for(var i=0; i<subIds.length; i++){\n");
			script.append("			if(!$.trim(subIds[i])) continue;\n");
			script.append("			var selTip = $('#'+subIds[i]).attr('selTip');\n");
			script.append("			var selTipVal = $('#'+subIds[i]).attr('selTipVal');\n");
			script.append("			var tipOption = '<option value=\"'+selTipVal+'\" selected >'+selTip+'</option>';\n");
			script.append("			if(k==0) $('#'+subIds[i]).empty().append(tipOption + selStr);\n");
			script.append("			else $('#'+subIds[i]).empty().append(tipOption);\n");
			script.append("			$($('#'+subIds[i]).parent().find('.uew-select-text')[0]).html(selTip);\n");
			script.append("			k ++;\n");
			script.append("		}\n");
		}
		if(!StringUtils.isEmpty(onChange)){
			script.append("		" + onChange + ";\n");
		}
		script.append("	}\n");
		script.append("</script>");
		return script.toString();
	}

	@Override
	public int doEndTag() throws JspException {
		//返回这个参数，标识继续该标签下所有的jsp内容
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDefVal() {
		return defVal;
	}
	public void setDefVal(String defVal) {
		this.defVal = defVal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDictCodeSuffix() {
		return dictCodeSuffix;
	}

	public void setDictCodeSuffix(String dictCodeSuffix) {
		this.dictCodeSuffix = dictCodeSuffix;
	}

	public String getSelTip() {
		return selTip;
	}

	public void setSelTip(String selTip) {
		this.selTip = selTip;
	}

	public String getSelTipVal() {
		return selTipVal;
	}

	public void setSelTipVal(String selTipVal) {
		this.selTipVal = selTipVal;
	}

	public String getUnShowVals() {
		return unShowVals;
	}

	public void setUnShowVals(String unShowVals) {
		this.unShowVals = unShowVals;
	}
}
