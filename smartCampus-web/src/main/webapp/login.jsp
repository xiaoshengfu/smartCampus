<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台登录</title>
		<link href="${pageContext.request.contextPath }/css/login.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="login">
			<div class="message">智慧校园管理登录</div>
			<div id="darkbannerwrap"></div>
			<form method="post" action="userAction_login.action" >
				<p style="font-weight: bold; color: red">${error}</p>
				<input name="id" placeholder="帐号" required="required" type="text">
				<hr class="hr15">
				<input name="password" placeholder="密码" required="required" type="password">
				<hr class="hr15">
				<div class="yzmdiv">
				<input name="checkcode" placeholder="验证码" required="required" type="text"> 
				<div class="yzm">
				<img id="vCode" alt="验证码" src="${pageContext.request.contextPath }/validatecode.jsp" onclick="javascript:document.getElementById('vCode').src='${pageContext.request.contextPath }/validatecode.jsp?math='+Math.random();"/>
				</div>
				</div>
				<hr class="hr15">
				<input value="登录" style="width:100%;" type="submit">
				<hr class="hr20">
			</form>
		</div>
	</body>
</html>