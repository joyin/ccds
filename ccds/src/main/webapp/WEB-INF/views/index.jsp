<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>银行催收管理系统</title>
	<%@ include file="./meta.jsp" %>
	<script src="${resourceRoot}/js/index.js"></script>
</head>
<body class="main-content">
	<input type="hidden" value="${module }" id="moduleName"/>
	<div id="main-content" class="main">
		<div class="nav" id="content-nav-div">
			<script type="text/javascript">INDEX_.showMenuNav("${module }");</script>
		</div>
		<div class="container">
			<div id="content-iframe-div" class="layui-anim" data-anim="layui-anim-scale">
				<iframe id="content-iframe" name="content-iframe" frameBorder="0" scrolling="no" src="" width="100%" height="100%;"></iframe>
			</div>
		</div>
	</div>
</body>
</html>