<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>已签到学生签到信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/myPage.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
    	 $(function() {
    	 	loadpage(${pageBean.visiblePages},${pageBean.currentPage},${pageBean.total},${pageBean.pageSize},"${pageBean.href}","${pageBean.parameter}");
    	 })
    </script>
  </head>
  <body>
	    <div class="container">
	    	<a class='btn btn-primary' href="signinActivityAction_pageQuery.action">返回</a>
	   		<c:if test="${signinActivity.activityType==1}">
	        	<a class='btn btn-primary' href="signinDetailAction_findNoSigninPageQuery.action?model.signinActivity.id=${signinActivity.id}">查看未签到的学生列表</a>
	        </c:if>
	        <h4 class="text-primary">签到活动描述:${signinActivity.information}</h4>
	        <hr>
		    <table class="table table-bordered">
			       <thead>
				        <tr>
				          <th>序号</th>
				          <th>姓名</th>
				          <th>学号</th>
				          <th>班级</th>
				          <th>电话</th>
				          <th>签到时间</th>
				          <th>签到经度</th>
				          <th>签到纬度</th>
				        </tr>
			      </thead>
			       <tbody>
				      	<c:forEach varStatus="varStatus" var="signinDetail" items="${pageBean.rows}">
				      		 <tr>
				      		 	  <td>${varStatus.index + 1}</td>
					      		  <td>${signinDetail.student.name}</td>
						          <td>${signinDetail.student.id}</td>
						          <td>${signinDetail.student.majorClass.name}</td>
						          <td>${signinDetail.student.telephone}</td>
						          <td>${signinDetail.signinDate}</td>
						          <td>${signinDetail.longitude}</td>
						          <td>${signinDetail.latitude}</td>
						     </tr>
				      	</c:forEach>
		      	</tbody>
		     </table>
		     <div class="text-center">
		        <ul class="pagination" id="pagination"></ul>
	  	     </div>
		</div>
		<script src="${pageContext.request.contextPath}/js/myPage.js" type="text/javascript"></script>
</body>
</html>