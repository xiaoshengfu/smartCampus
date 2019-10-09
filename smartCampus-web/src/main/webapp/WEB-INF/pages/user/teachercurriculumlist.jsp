<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>教师课程管理</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/myPage.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
    	 $(function() {
    	 	loadpage(${pageBean.visiblePages},${pageBean.currentPage},${pageBean.total},${pageBean.pageSize},"${pageBean.href}","${pageBean.parameter}");
    	 })
    	 function deleteUserCurriculum(id){
				var isDelete = confirm("您确认要删除吗？");
				if(isDelete){
					//要删除
					location.href = "userAction_deleteUserCurriculum.action?model.id=${user.id}&curriculumId="+id;
				}
			}
    </script>
  </head>
  <body>
	    <div class="container">
	    	<a class='btn btn-primary' href="userAction_teacherPageQuery.action">返回</a>
	    	<a class='btn btn-primary' href="userAction_addUserCurriculumPage.action?model.id=${user.id}">添加课程</a>
	        <hr>
		    <table class="table table-bordered">
			       <thead>
				        <tr>
				          <th>序号</th>
				          <th>课程号</th>
				          <th>课程名称</th>
				          <th>课程信息</th>
				          <th>操作</th>
				        </tr>
			      </thead>
			       <tbody>
				      	<c:forEach varStatus="varStatus" var="curriculum" items="${pageBean.rows}">
				      		 <tr>
				      		 	  <td>${varStatus.index + 1}</td>
					      		  <td>${curriculum.id}</td>
						          <td>${curriculum.name}</td>
						          <td>${curriculum.information}</td>
						          <td>
						          	<a class="btn btn-danger btn-xs" onclick="deleteUserCurriculum('${curriculum.id}')">删除</a>
						          </td>
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