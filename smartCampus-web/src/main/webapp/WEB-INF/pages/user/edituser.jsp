<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改用户信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		if(${user.userstate} == "1"){
			$("#userstate1").attr("checked",true); 
		}else{
			$("#userstate0").attr("checked",true); 
		}
	})
</script>
</head>
<body>
	<div class="container"
		style="width: 100%; background: url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<a class='btn btn-primary' href="userAction_pageQuery.action">返回</a>
				<form id="myform" class="form-horizontal" action="userAction_updateUser.action" method="post" style="margin-top: 5px;">
						<div class="form-group">
							<label for="userid" class="col-sm-2 control-label">账号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="userid" name="model.id" value="${user.id}" readonly="readonly" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="model.name" value="${user.name}" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="usertelephone" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="usertelephone" name="model.telephone" value="${user.telephone}" required="required">
							</div>
						</div>
						<div class="form-group opt">
							<label for="inlineRadio1" class="col-sm-2 control-label">重置密码:</label>
							<div class="col-sm-6">
								<label class="radio-inline"> 
									<input type="radio" name="resetPassword" value="true">是
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="resetPassword" value="false" checked="checked">否
								</label>
							</div>
						</div>
						<div class="form-group opt">
							<label for="" class="col-sm-2 control-label">解绑微信:</label>
							<div class="col-sm-6">
								<label class="radio-inline"> 
									<input type="radio" name="resetOpenid" value="true">是
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="resetOpenid" value="false" checked="checked">否
								</label>
							</div>
						</div>
						<div class="form-group opt">
							<label for="" class="col-sm-2 control-label">限制登录:</label>
							<div class="col-sm-6">
								<label class="radio-inline"> 
									<input id="userstate0" type="radio" name="model.userstate" value="0">是
								</label> 
								<label class="radio-inline"> 
									<input id="userstate1" type="radio" name="model.userstate" value="1">否
								</label>
							</div>
						</div>
						<div class="form-group from-inline">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="reset" width="100" class="btn btn-primary">
								<input type="submit" width="100" value="提交" class="btn btn-primary">
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>