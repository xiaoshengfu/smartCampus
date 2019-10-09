<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加学生信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#submitbutton").click(function() {
    			var studentpassword = $("#studentpassword").val();
                var repeatstudentpassword = $("#repeatstudentpassword").val();
                if(studentpassword == repeatstudentpassword){
                	$("#studentform").submit();
                }else{
                	$("#studentpassword")[0].value = "";
                	$("#repeatstudentpassword")[0].value = "";
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
			<form id="studentform" class="form-horizontal" action="studentAction_addStudent.action" method="post" style="margin-top: 5px;">
				<div class="form-group">
					<label for="studentid" class="col-sm-2 control-label">学号:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="studentid" name="model.id" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="majorclass" class="col-sm-2 control-label">专业班级:</label>
					<div class="col-sm-6">
						<select name="model.majorClass.id" id="majorclass" class="form-control">
						<option selected="selected">----请选择班级----</option>
							<c:forEach var="majorClass" items="${majorClasses}">
								<option value="${majorClass.id}">${majorClass.name}</option>	 
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="studentname" class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="studentname" name="model.name" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="studenttelephone" class="col-sm-2 control-label">电话:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="studenttelephone" name="model.telephone" placeholder="选填">
					</div>
				</div>
				<div class="form-group">
					<label for="studentpassword" class="col-sm-2 control-label">登录密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="studentpassword" name="model.password" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="userpassword" class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="repeatstudentpassword" required="required">
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