<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>qiandao2weima</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  
  <style>
  	*{
  		margin: auto 0;
  	}
  	#output{
  		position: absolute;
		left: 50%;
		top: 50%;
		width:400px;
		height:400px;
		margin-left:-100px;
		margin-top:-50px;
  	}
  </style>
  </head>
  	
  <body >
  	<div id="output">
      
    </div>
    <script src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.qrcode.min.js"></script>
	<script>
		jQuery(function(){
			jQuery('#output').qrcode("${signinactivitycode}");
		})
	</script>	
  </body>
</html>
