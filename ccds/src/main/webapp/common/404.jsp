<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
	<base href="<%=basePath%>">
	<title>404 - 页面不存在</title>
	<link href="<%=basePath%>/static/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="<%=basePath%>/static/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script language="javascript">
		$(function(){
		    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
			$(window).resize(function(){  
		    	$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
		    }) ; 
		});  
	</script> 
</head>
<body style="background:#edf6fa;">
	<div class="error">
    	<h2>非常遗憾，您访问的页面不存在！</h2>
    	<p>看到这个提示，就自认倒霉吧!</p>
    	<div class="reindex">
    		<a href="${ctx }/index.jsp" target="_parent">返回首页</a>
    	</div>
    </div>
</body>
</html>