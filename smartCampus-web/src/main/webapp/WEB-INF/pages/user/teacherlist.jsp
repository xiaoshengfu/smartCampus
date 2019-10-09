<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看所有用户</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css"/>
	<style>
		.date-inline {
			display:inline-block;
		}
		/* .buttonstyle {
			margin-top: 10px;
		} */
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
		     <form action="userAction_teacherPageQuery.action" class="form-inline" method="post">
		     		  <div class="form-group">
		     		      <label for="userid">账号:</label>
                          <input id="userid" class="form-control" type="text" name="model.id" placeholder="选填">
                      </div>
                      <div class="form-group">
		     		      <label for="username">姓名:</label>
                          <input id="username" class="form-control" type="text" name="model.name" placeholder="选填">
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
			          <th>账号</th>
			          <th>姓名</th>
			          <th>电话</th>
			          <th>状态</th>
			          <th>操作</th> 
			        </tr>
		      </thead>
		       <tbody>
			      	<c:forEach varStatus="varStatus" var="user" items="${pageBean.rows}">
			      		 <tr>
			      		 	  <td>${varStatus.index + 1}</td>
				      		  <td>${user.id}</td>
					          <td>${user.name}</td>
					          <td>${user.telephone}</td> 
					          <td>
					          	<c:choose>
							      <c:when test="${user.userstate == 1}">
							           	正常
							      </c:when>
							      <c:otherwise>
				     				           限制登录
	                			  </c:otherwise>
						  	    </c:choose>
					          </td>	
					          <td>
					          	<a class="btn btn-primary btn-xs" href="userAction_userCurriculumPageQuery.action?model.id=${user.id}">教师课程管理</a>
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