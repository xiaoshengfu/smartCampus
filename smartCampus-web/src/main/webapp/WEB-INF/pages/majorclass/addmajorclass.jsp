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
			<form id="myform" class="form-horizontal" action="majorClassAction_addMajorClass.action" method="post" style="margin-top: 5px;">
				<div class="form-group">
					<label for="majorclassid" class="col-sm-2 control-label">班级代号:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="majorclassid" name="model.id" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="majorclassname" class="col-sm-2 control-label">班级名称:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="majorclassname" name="model.name" required="required">
					</div>
				</div>
				<div class="form-group">
					<label for="majorclassinformation" class="col-sm-2 control-label">班级描述:</label>
					<div class="col-sm-6">
						<textarea id="majorclassinformation" name="model.information" class="form-control" rows="5" cols="8" value="${majorClass.information}"></textarea>
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