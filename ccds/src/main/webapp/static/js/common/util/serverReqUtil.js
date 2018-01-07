/*********客户端请求工具类*************/
var SERVER_REQ_UTIL = {};

/**
 * 异步get方式请求
 * @param {Object} url 请求路径
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 */
SERVER_REQ_UTIL.doAsyncAndGetReq = function(url, params, callBack) {
	var that = this;
	that.doReq(url,params, callBack, true, "get");
};

/**
 * 异步post方式请求
 * @param {Object} url 请求路径
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 */
SERVER_REQ_UTIL.doAsyncAndPostReq = function(url, params, callBack) {
	var that = this;
	that.doReq(url,params, callBack, true, "post");
};

/**
 * 非异步get方式请求
 * @param {Object} url 请求路径
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 */
SERVER_REQ_UTIL.doUnAsyncAndGetReq = function(url, params, callBack) {
	var that = this;
	that.doReq(url, params, callBack, false, "get");
};

/**
 * 非异步POST方式请求
 * @param {Object} url 请求路径
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 */
SERVER_REQ_UTIL.doUnAsyncAndPostReq = function(url, params, callBack) {
	var that = this;
	that.doReq(url, params, callBack, false, "post");
};

/**
 * 非异步请求
 * @param {Object} url 请求路径
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 * @param {Object} async 是否异步
 * @param {Object} type 请求类型-默认post
 */
SERVER_REQ_UTIL.doReq = function(url, params, callBack, async, type) {
	var that = this;
	type = !type ? "post" : type;
	params = params ? params : {};
	$.ajax({
		url: url,
		type: type,
		dataType: 'json',
		cache: false,
		async: (async ? true : false),
		data: params,
		success: function(rs) {
			if(callBack) callBack(true, rs);
		},
		error: function(result) {
			var responseText = result.responseText;
			if(responseText.indexOf("http") != -1){
				window.top.location.href = responseText;
			}else{
				TOP_TIP_UTIL.msg({msg:"未知错误", rollBack:null, icon:5});
				if(callBack) callBack(false, {});
			}
		}
	});

};

/**
 * 附件下载
 * @param {Object} bizCode 访问接口
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} callBack 回调参数 
 * @example SERVER_REQ_UTIL
.go2Download('w10001', {'userName':'张三'}, function(true/false, msg, rspData){});
 */
SERVER_REQ_UTIL.go2Download = function(bizCode, params, callBack) {
	var that = this;

};

/**
 * 文件上传
 * @param {Object} bizCode 访问接口
 * @param {Object} params 请求参数  ，必须是对象型的
 * @param {Object} files 文件
 * @param {Function} callBack 回调函数
 */
SERVER_REQ_UTIL.upload = function(bizCode, params, files, callBack) {

};

