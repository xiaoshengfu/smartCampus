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
  
  <body ontouchstart>
    <header class='demos-header'>
      <h1 class="demos-title">会议签到</h1>
    </header>
    <div class="weui-cells__title">内容输入</div>
    <div class="weui-cells weui-cells_form">
      <form id="login" action="${pageContext.request.contextPath }/weXinAction_qdfHY.action" method="post" accept-charset="utf-8">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">签到人数</label></div>
        <div class="weui-cell__bd">
          <input id="renshu" class="weui-input" type="number" name="qdfsk_total" pattern="[0-9]*" placeholder="请输入签到人数">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">签到持续时间</label></div>
        <div class="weui-cell__bd">
          <input id="fenzhongshu" class="weui-input" type="number" name="qdfsk_time" pattern="[0-9]*" placeholder="请输入分钟数">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">签到描述</label></div>
        <div class="weui-cell__bd">
          <input id="miaoshu" class="weui-input" type="text" name="qdfsk_ms">
        </div>
      </div>
      </form>
	</div>
	<div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>	 
    <script>
	  $(function() {
	    FastClick.attach(document.body);
	  });
	</script>
	<script>
      $("#showTooltips").click(function() {
        var peonum = $('#renshu').val();
        var timenum = $('#fenzhongshu').val();
        if(!peonum || !/\d*/.test(peonum)) $.toptip('请输入人数');
        else if(!timenum || !/\d*/.test(timenum)) $.toptip('请输入时间');
        else $("#login").submit();
      });
    </script>   
  </body>
</html>
