<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>签到方式选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/css/jquery-weui.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/demos.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/weui.min.css"
	rel="stylesheet">	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/fastclick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-weui.js"></script>
  </head>
  	
  <body>
  	<header class='demos-header'>
      <h1 class="demos-title">签到方式</h1>
    </header>
  	<div class='demos-content-padded'>
     <a href="${pageContext.request.contextPath }/weXinAction_qiandaoxz.action?qiandaostate=1" class="weui-btn weui-btn_plain-primary" role="button">上课签到</a>
     <a href="${pageContext.request.contextPath }/weXinAction_qiandaoxz.action?qiandaostate=2" class="weui-btn weui-btn_plain-primary" role="button">会议签到</a>
	</div>
	<script>
	  $(function() {
	    FastClick.attach(document.body);
	  });
	</script>
  </body>
</html>
