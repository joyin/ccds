COMMONS_JS = {};
/**************************iframe自适应开始************************************/
/**
 * iframe自适应
 * @param iframeId，可以不用了
 */
COMMONS_JS.dynIframeSize = function(ifId, lastIfId) {
	var that = this;
	//利用闭包避免导致全局污染
	(function(ifId, lastIfId){
		var iframeId = ifId;
		//获取当前层iframe
		if(!iframeId) iframeId = window.frameElement && window.frameElement.id || '';
		var flag = (lastIfId && lastIfId != ifId) || !lastIfId;
		if(that.checkHasTheIframe(iframeId) && flag)
			that.doDynIframeSize(iframeId);
		else if(that.getWinParent(1).COMMONS_JS.checkHasTheIframe(iframeId))
			that.getWinParent(1).COMMONS_JS.doDynIframeSize(iframeId);
		else if(that.getWinParent(2).COMMONS_JS.checkHasTheIframe(iframeId))
			that.getWinParent(2).COMMONS_JS.doDynIframeSize(iframeId);
		else if(that.getWinParent(3).COMMONS_JS.checkHasTheIframe(iframeId))
			that.getWinParent(3).COMMONS_JS.doDynIframeSize(iframeId);
		else if(that.getWinParent(4).COMMONS_JS.checkHasTheIframe(iframeId))
			that.getWinParent(4).COMMONS_JS.doDynIframeSize(iframeId);
		else if(COMMONS_JS.getWinParent(5).COMMONS_JS.checkHasTheIframe(iframeId))
			that.getWinParent(5).COMMONS_JS.doDynIframeSize(iframeId);
	})(ifId, lastIfId);
};

//获取iframe节点信息
COMMONS_JS.go2IframeDow = function(iframeId,url, beforeFun, loadedFun){
	if(url.indexOf("?") != -1){
		url = url + "&tempFlag="+new Date().getTime();
	}else{
		url = url + "?tempFlag="+new Date().getTime();
	}
	var ifr = COMMONS_JS.getIFrameDOM(iframeId);
	ifr.location.href = url;
	if(beforeFun) beforeFun();
	$("#"+iframeId).unbind("load");
	$("#"+iframeId).load(function(){
		var that = this;
		if(loadedFun) loadedFun();
		$(that).attr("width", "100%");
		$(that).attr("height", "100%");
		COMMONS_JS.dynIframeSize($(that).attr("id"));
	});
};

/**
 * 判断该窗口中是否存在该iframe
 */
COMMONS_JS.checkHasTheIframe = function(iframeId){
	if(document.getElementById(iframeId)) return true;
	return false;
};

//获取iframe元素
COMMONS_JS.getIFrameDOM = function(id){
	return window.document.getElementById(id).contentDocument || window.frames[id].document;
};

/**
 * 获取指定层的父窗口
 */
COMMONS_JS.getWinParent = function(num){
	var winParent = window;
	for(var i=0; i<num; i++){
		winParent = winParent.parent;
	}
	return winParent;
};

/**
 * 执行iframe自适应
 * @param iframeId
 */
COMMONS_JS.doDynIframeSize = function(iframeId){
	/*var pTar = null;
	if (document.getElementById) {
		pTar = document.getElementById(iframeId);
	} else {
		eval('pTar = ' + down + ';');
	}
	if (pTar && !window.opera) {
		//begin resizing iframe 
		pTar.style.display = "block";
		var screenHeight = window.screen.height;//屏幕高度 
		var screenWidth = window.screen.width;//屏幕宽度
		var hTemp = 0;
		var wTemp = 0;
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			//ns6 syntax 
			hTemp = pTar.contentDocument.body.offsetHeight;
			//wTemp = pTar.contentDocument.body.scrollWidth;
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			//ie5+ syntax 
			hTemp = pTar.Document.body.scrollHeight;
			//wTemp = pTar.Document.body.scrollWidth;
		}
		if(hTemp < screenHeight-230 && iframeId == "content_iframe") {
			hTemp = screenHeight-305;
		}
		pTar.height = hTemp;
	}*/
	var ifm= document.getElementById(iframeId);
	if(!ifm) return false;
	var subWeb = document.frames ? document.frames[iframeId].document : ifm.contentDocument;
	
	if(ifm != null && subWeb != null && subWeb.body) {
		ifm.height = subWeb.body.scrollHeight-10;
		//ifm.width = subWeb.body.scrollWidth;
	}
	if(document.getElementById("content_iframe_div")){
		var sH = window.screen.height - 275;
		if(ifm.height < sH) ifm.height = sH;
	}
	//取得当前层的iframe
	var iframeIdTemp = window.frameElement && window.frameElement.id || '';
	if(iframeIdTemp && iframeIdTemp != ""){
		//如果存在，自适应本层iframe
		COMMONS_JS.dynIframeSize(iframeIdTemp, iframeId);
	}
};
/**************************iframe自适应结束************************************/


/*************************校验规则*******************************************/
COMMONS_JS.valid = {
		init:function(v, vRq, vRg, vLen, obj){
			var that = this;
			var flag = true;
			var placeholder = $(obj).attr("placeholder");
			if(vRq || $.trim(v).length > 0) {
				if(vRq && !that.rq(v)) return placeholder||"必填字段!";
				if(vRg && !that.rg(v, vRg)) return "请输入正确的格式!";
				if(vLen) {
					var lenTemp = vLen.split("-");
					if(!that.len(v, lenTemp[0], lenTemp[1])) return "请输入在"+lenTemp[0]+"~"+lenTemp[1]+"个字符之内!";
				}
			}
		},
		rq:function(v){
			if($.trim(v).length == 0) return false;
			return true;
		},
		rg:function(v, rg){
			if(!rg.test(v)) return false;
			return true;
		},
		len:function(v, min, max){
			v = $.trim(v);
			if(v.length < min || v.length > max) return false;
			return true;
		}
};
/*************************校验规则结束*******************************************/

//实现思路：获得毫秒数 再转化为需要时间格式。形如：yyyy-MM-dd
//比如：后台传一个date类型的数据，d,调用的时候只要formatDate(d, "yyyy-MM-dd")
COMMONS_JS.formatDate = function(time, format) {
	if(!time || time == 0) return "";
	if(!format) format = "yyyy-MM-dd HH:mm:ss";
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0': '') + i;
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
			function(a) {
		switch (a) {
		case 'yyyy':
			return tf(t.getFullYear());
			break;
		case 'MM':
			return tf(t.getMonth() + 1);
			break;
		case 'mm':
			return tf(t.getMinutes());
			break;
		case 'dd':
			return tf(t.getDate());
			break;
		case 'HH':
			return tf(t.getHours());
			break;
		case 'ss':
			return tf(t.getSeconds());
			break;
		}
	});
};

//执行导出
COMMONS_JS.doExportExcel = function(columnNames, columnFields, url, param){
	var that = this;
	param["columnNames"] = columnNames;
	param["columnFields"] = columnFields;
	var excelPortFormId = "excelPortForm";
	var form = document.getElementById(excelPortFormId);
	if(!form){
		form = jQuery('<form action="'+url+'" method="post" id="'+excelPortFormId+'" name="'+excelPortFormId+'"></form>');	
	}
	$(form).empty();
	for ( var p in param ){
		$(form).append('<input type="hidden"  name="'+p+'" value="'+param[p]+'"/>');	
	}
	jQuery(form).css('position', 'absolute');
	jQuery(form).css('top', '-2000px');
	jQuery(form).css('left', '-2000px');
	jQuery(form).appendTo('body');	
	$("#"+excelPortFormId).submit();
};
