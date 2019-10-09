<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户绑定</title>

<meta name="viewport" content="width=device-width, initial-scale=0.5, minimum-scale=0.3, maximum-scale=2.0, user-scalable=yes" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />

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
      <h1 class="demos-title">用户绑定</h1>
    </header>
    <div class="weui-cells__title">绑定信息</div>
    <div class="weui-cells weui-cells_form">
      <form id="login" action="${pageContext.request.contextPath}/weXinAction_binding.action" method="post" accept-charset="utf-8">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">账号</label></div>
        <div class="weui-cell__bd">
          <input id="zhanghao" class="weui-input" type="number" name="userid" pattern="[0-9]*" placeholder="请输入账号">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd">
          <input id="xingming" class="weui-input" name="username" type="text" placeholder="请输入姓名">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
          <input id="mima" class="weui-input" name="password" type="password" placeholder="请输入密码">
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
        var number = $('#zhanghao').val();
        var name = $('#xingming').val();
        var password = $('#mima').val();
        if(!number || !/\d*/.test(number)) $.toptip('请输入账号');
        else if(!name) $.toptip('姓名不能为空');
        else if(!password) $.toptip('密码不能为空');
        else $("#login").submit();
      });
    </script>
</body>
</html>
