<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看所有学生</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css"/>
	<style>
		.date-inline {
			display:inline-block;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/myPage.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	  	$(function() {
		          loadpage(${pageBean.visiblePages},${pageBean.currentPage},${pageBean.total},${pageBean.pageSize},"${pageBean.href}","${pageBean.parameter}");
               });
    </script>
  </head>
  <body>
	    <div class="container">
		     <form action="studentAction_pageQuery.action" class="form-inline" method="post">
		     		  <div class="form-group">
		     		      <label for="studentid">学号:</label>
                          <input id="studentid" class="form-control" type="text" name="model.id" placeholder="选填">
                      </div>
                      <div class="form-group">
		     		      <label for="studentname">姓名:</label>
                          <input id="studentname" class="form-control" type="text" name="model.name" placeholder="选填">
                      </div>
                      <div class="form-group">
		     		      <label for="majorclass">专业班级:</label>
                          <input id="majorclass" class="form-control" type="text" name="model.majorClass.name" placeholder="选填">
                      </div>
	                  <div class="form-group buttonstyle">
			              <input type="reset"  class="btn btn-primary"/>
			          </div>
			          <div class="form-group buttonstyle">
		              	  <input type="submit"  class="btn btn-primary" value="查询"/>
		              </div>
		    </form>
	    <hr>
	    <table class="table table-bordered">
		      <thead>
			        <tr>
			          <th>序号</th>
			          <th>学号</th>
			          <th>姓名</th>
			          <th>专业班级</th>
			          <th>电话</th>
			          <th>操作</th> 
			        </tr>
		      </thead>
		       <tbody>
			      	<c:forEach varStatus="varStatus" var="student" items="${pageBean.rows}">
			      		 <tr>
			      		 	  <td>${varStatus.index + 1}</td>
				      		  <td>${student.id}</td>
					          <td>${student.name}</td>
					          <td>${student.majorClass.name}</td>
					          <td>${student.telephone}</td>  	
					          <td>
					          	<a class="btn btn-primary btn-xs" href="studentAction_editStudentPage.action?model.id=${student.id}">修改</a>
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