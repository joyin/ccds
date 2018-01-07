<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>菜单栏</title>
	<%@ include file="./meta.jsp" %>
	<script type="text/javascript">
		var NAV_ = {};
		NAV_.showSubMenu = function(moduleName) {
			var menu = MENU_DATA.menu[moduleName];
			if(!menu) return;
			var subMenuLs = menu.sub;
			var menuStr = "";
			for(var i=0; i<subMenuLs.length; i++) {
				menuStr += 
					'<div class="item" onclick="NAV_.go2Content(\'' + subMenuLs[i].url + '\', ' + i + ')">'+
						'<img src="' + resourceRoot + '/' + subMenuLs[i].img + '">'+
						'<span>' + subMenuLs[i].name + '</span>'+
					'</div>';
			}
			document.write(menuStr);
		};
		
		NAV_.go2Content = function(url, index) {
			window.parent.INDEX_.go2Content(url, index);
		};

	</script>
</head>
<body>
	<div class="layui-anim" data-anim="layui-anim-scale">
		<div class="sub-menu-tip">您要使用哪个功能？</div>
		<div class="sub-menu">
			<script type="text/javascript">NAV_.showSubMenu("${module}");</script>
		</div>
	</div>
</body>
</html>