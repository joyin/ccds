<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" type="text/css" href="${resourceRoot }/js/common/plugin/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${resourceRoot }/css/main.css">
<script src="${resourceRoot }/js/common/plugin/layui/layui.all.js" ></script>


<!--[if !IE]> -->
<script src="${resourceRoot}/js/common/plugin/jquery/jquery.min.js"></script>
<!-- <![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${resourceRoot}/js/common/plugin/jquery/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${resourceRoot}/js/common/plugin/jquery/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script src="${resourceRoot}/js/common/plugin/jquery/jquery-from-params.js"></script>

<script src="${resourceRoot }/js/common/plugin/layui/top_tip_util.js"></script>
<script src="${resourceRoot }/js/common/util/serverReqUtil.js"></script>
<script src="${resourceRoot }/js/common/util/commons-util.js"></script>
<script src="${resourceRoot}/js/menuData.js"></script>

<script>
	//上下文
	var ctx = "${ctx}", resourceRoot = "${resourceRoot }";
	//layui模块开始
	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	var form = layui.form;
	var table = layui.table;
	var laydate = layui.laydate;
	
	//layui模块结束
	
	//页面自适应
	$(document).ready(function(){
		//页面自适应
		var dynIframeSizeInterval_ = undefined;
		var beginTimeDynIframe_ = function(){
			if(dynIframeSizeInterval_) closeTimeDynIframe_();
			dynIframeSizeInterval_ = window.setInterval(function(){
				COMMONS_JS.dynIframeSize();
			}, 10);
		};
		var closeTimeDynIframe_ = function(){
			window.clearInterval(dynIframeSizeInterval_);
			dynIframeSizeInterval_ = undefined;
		};
		var lsX = undefined, lsY = undefined;
		$("*").bind("mouseover", function(){
			var e = event || window.event; 
			closeTimeDynIframe_();
			beginTimeDynIframe_();
		});
		$("*").bind("mouseout", function(){
			closeTimeDynIframe_();
		});
		window.onunload = function(){
			closeTimeDynIframe_();
		};
		
		var init_size_time = 300;
		var initIframeSize = function(){
			if(init_size_time <= 0) return;
			COMMONS_JS.dynIframeSize();
			window.setTimeout(initIframeSize, 50);
			init_size_time -= 50;
		};
		initIframeSize();
	});
</script>

