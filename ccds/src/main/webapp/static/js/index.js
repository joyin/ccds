var INDEX_ = {};
INDEX_.showMenuNav = function(moduleName) {
	var menu = MENU_DATA.menu[moduleName];
	if(!menu) return;
	$("#content-nav-div").empty().
	append('<span class="layui-breadcrumb">'+
			  	'<a href="javascript:void(0);" onclick="INDEX_.go2Content(\'' + menu.url + '\')" >' + menu.name + '</a>'+
			  	'<a href="javascript:void(0);"><cite>导航</cite></a>'+
			'</span>');
	element.init();
};

INDEX_.showContentNav = function(moduleName, index) {
	var menu = MENU_DATA.menu[moduleName];
	if(!menu) return;
	var subMenuLs = menu.sub;
	if(!subMenuLs || subMenuLs.length == 0) return;
	var sub = subMenuLs[index];
	var menuStr = "";
	for(var i=0; i<subMenuLs.length; i++) {
		menuStr += '<div class="menu-item" onclick="INDEX_.go2Content(\'' + subMenuLs[i].url + '\', ' + i + ')">' + subMenuLs[i].name + '</div>';
	}
	menuStr =  
		'<div class="nav2-menu-ls">'+
		 	menuStr +
		'</div>';
	var navStr = 
		'<div class="layui-breadcrumb nav2">'+
		  	'<a href="javascript:void(0);" onclick="INDEX_.go2Content(\'' + menu.url + '\')" >' + menu.name + '</a>'+
		  	'<a href="javascript:void(0);" style="position:relative;" >' +
		  		'<cite>' + sub.name + '</cite>' + 
		  		'<div class="nav2-menu">' + menuStr + '</div>' + 
		  	'</a>'+
		'</div>';
	$("#content-nav-div").empty().append(navStr);
	element.init();
};

INDEX_.go2Content = function(url, index) {
	var moduleName = $("#moduleName").val();
	if(index != undefined) this.showContentNav(moduleName, index);
	else this.showMenuNav(moduleName);
	var iframeId = 'content-iframe';
	COMMONS_JS.go2IframeDow(iframeId, ctx + url, function(){
		loadIndex = TOP_TIP_UTIL.loading("请稍等,正在拼命加载中....", true);
	}, function(){
		//为了右边内容展示区自适应
		var ifrTemp = COMMONS_JS.getIFrameDOM(iframeId);
		var screenHeight = window.screen.height;//屏幕高度 
		$(ifrTemp.body).css("min-height", (screenHeight-278)+"px");
		TOP_TIP_UTIL.unLoading(loadIndex);
	});
};

INDEX_.go2ShowMenuLs = function() {
	var moduleName = $("#moduleName").val();
	var menu = MENU_DATA.menu[moduleName];
	if(!menu) return;
	INDEX_.go2Content(menu.url);
};

//初始化首页body的样式
INDEX_.initTabMainCss = function(){
	//为了右边内容展示区自适应
	var screenHeight = window.innerHeight;//屏幕高度 
	var screenWidth = window.screen.innerWidth;//屏幕宽度 
	$("#main-content").css("min-height", (screenHeight)+"px");
	window.onresize = function() {
		INDEX_.initTabMainCss();
	};
};

//预加载
$(function(){
	//初始化首页body的样式
	INDEX_.initTabMainCss();
	//显示指定的菜单列表
	INDEX_.go2ShowMenuLs();
	//初始化layui元素
	element.init();
});

