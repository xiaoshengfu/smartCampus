<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加用户信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#submitbutton").click(function() {
    			var userpassword = $("#userpassword").val();
                var repeatuserpassword = $("#repeatuserpassword").val();
                if(userpassword == repeatuserpassword){
                	$("#userform").submit();
                }else{
                	$("#userpassword")[0].value = "";
                	$("#repeatuserpassword")[0].value = "";
                	$("#error")[0].innerHTML = "两次输入的密码不一致!";
                }
            })
    	})
    </script>
  </head>
  <body>
		<div class="container"
		style="width: 100%; background: url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
			<c:choose>
				<c:when test="${message == 0}">
					<p style="font-weight: bold; color: red">导入失败!</p>
				</c:when>
				<c:when test="${message == 1}">
					<p style="font-weight: bold; color: green">导入成功!</p>
				</c:when>
			</c:choose>
			<p id="error" style="font-weight: bold; color: red"></p>
			<form id="userform" class="form-horizontal" action="userAction_addUser.action" method="post" style="margin-top: 5px;">
				<div class="form-group">
					<label for="userid" class="col-sm-2 control-label">账号:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="userid" name="model.id" required="required">
					</div>
				</div>
				<c:choose>
					<c:when test="${loginUser.id == 'admin'}">
						<div class="form-group">
							<label for="schools" class="col-sm-2 control-label">学校:</label>
							<div class="col-sm-6">
								<select name="model.school.id" id="schools" class="form-control">
								<option selected="selected">----请选择学校----</option>
									<c:forEach var="school" items="${schools}">
										<option value="${school.id}">${school.name}</option>	 
									</c:forEach>
								</select>
							</div>
						</div>
					</c:when>
					<c:otherwise>
				     	<div class="form-group opt">
							<label for="userrole" class="col-sm-2 control-label">用户角色:</label>
							<div class="col-sm-6">
								<c:forEach var="authRole" items="${authRoles}">
									<c:if test="${authRole.id != 1}">
										<label class="radio-inline"> 
											<input id="userrole" type="checkbox" name="userRoles" value="${authRole.id}">${authRole.name}
										</label>
									</c:if> 
								</c:forEach>
							</div>
						</div>		
	                </c:otherwise>
				</c:choose>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="username" name="model.name" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="usertelephone" class="col-sm-2 control-label">电话:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="usertelephone" name="model.telephone" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="userpassword" class="col-sm-2 control-label">登录密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="userpassword" name="model.password" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="userpassword" class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="repeatuserpassword" required="required">
					</div>
				</div>
				<div class="form-group from-inline">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="reset" width="100" class="btn btn-primary">
						<input id="submitbutton" type="button" width="100" value="提交" class="btn btn-primary">
					</div>
				</div>
			</form>
	 		</div>
		</div>
	</div>
</body>
</html>