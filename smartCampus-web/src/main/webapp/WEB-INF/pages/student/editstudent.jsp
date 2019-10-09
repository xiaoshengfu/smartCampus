<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改学生信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
</head>
<body>
	<div class="container"
		style="width: 100%; background: url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<a class='btn btn-primary' href="studentAction_pageQuery.action">返回</a>
				<form id="myform" class="form-horizontal" action="studentAction_updateStudent.action" method="post" style="margin-top: 5px;">
						<div class="form-group">
							<label for="studentid" class="col-sm-2 control-label">学号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="studentid" name="model.id" value="${student.id}" readonly="readonly" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="studentname" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="studentname" name="model.name" value="${student.name}" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="majorclass" class="col-sm-2 control-label">专业班级:</label>
							<div class="col-sm-6">
								<select name="model.majorClass.id" id="majorclass" class="form-control">
									<c:forEach var="majorClass" items="${majorClasses}">
										<c:choose>
										     <c:when test="${majorClass.id != student.majorClass.id}">
										        <option value="${majorClass.id}">${majorClass.name}</option>
										     </c:when>
										     <c:otherwise>
										     	<option value="${majorClass.id}" selected="selected">${majorClass.name}</option>
				                			 </c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="studenttelephone" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="studenttelephone" name="model.telephone" value="${student.telephone}" placeholder="选填">
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
							<label for="inlineRadio1" class="col-sm-2 control-label">解绑微信:</label>
							<div class="col-sm-6">
								<label class="radio-inline"> 
									<input type="radio" name="resetOpenid" value="true">是
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="resetOpenid" value="false" checked="checked">否
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