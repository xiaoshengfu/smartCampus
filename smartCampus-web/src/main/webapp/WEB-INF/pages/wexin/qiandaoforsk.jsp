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
	<h1 class="demos-title">上课签到</h1>
	</header>
	<div class="weui-cells">
		<form id="login" role="form"
			action="${pageContext.request.contextPath }/weXinAction_qdfSK.action">
			<div class="weui-cells__title">签到课程：</div>
			<div class="weui-cell weui-cell_select">
				<div class="weui-cell__bd">
					<select class="weui-select" name="qdfsk_kc">
						<option value="1">大学物理</option>
						<option value="2">高等数学</option>
						<option value="3">线性代数</option>
					</select> </select>
				</div>
			</div>
			<div class="weui-cells__title">签到班级:</div>
			<div class="weui-cell weui-cell_select">
				<div class="weui-cell__bd">
					<select class="weui-select" name="qdfsk_bj" multiple="multiple">
						<option value="1">计算机科学与技术16-3</option>
						<option value="2">会计16-1</option>
						<option value="3">通讯工程15-1</option>
						<option value="100">计算机科学与技术15-4</option>
						<option value="99">计算机科学与技术15-2</option>
					</select>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">签到持续时间</label>
				</div>
				<div class="weui-cell__bd">
					<input id="fenzhongshu" class="weui-input" type="number"
						name="qdfsk_time" pattern="[0-9]*" placeholder="请输入分钟数">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">签到描述</label>
				</div>
				<div class="weui-cell__bd">
					<input id="miaoshu" class="weui-input" type="text" name="qdfsk_ms">
				</div>
			</div>
		</form>
	</div>
	<div class="weui-btn-area">
		<a class="weui-btn weui-btn_primary" href="javascript:"
			id="showTooltips">确定</a>
	</div>
</body>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
<script>
	$("#showTooltips").click(function() {
		var peonum = $('#renshu').val();
		var timenum = $('#fenzhongshu').val();
		if (!timenum || !/\d*/.test(timenum)) $.toptip('请输入时间');
		else $("#login").submit();
	});
</script>
</html>
