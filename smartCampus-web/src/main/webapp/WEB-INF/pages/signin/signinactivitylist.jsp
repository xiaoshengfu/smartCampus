<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查询签到信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css"/>
	<style>
		.date-inline {
			display:inline-block;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqPaginator.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/myPage.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
    	function deleteSigninActivity(id){
				var isDelete = confirm("您确认要删除吗？");
				if(isDelete){
					//要删除
					location.href = "signinActivityAction_deleteSigninActivity.action?model.id="+id;
				}
			}
	  	$(function() {
                  $('#reservation').daterangepicker(null, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
                  initDate();
                  $('#resetButton').click(function() {
                    initDate();
                  });
		          loadpage(${pageBean.visiblePages},${pageBean.currentPage},${pageBean.total},${pageBean.pageSize},"${pageBean.href}","${pageBean.parameter}");
               });
         function initDate() {
         	var JsonDateValue = new Date();
         	var text = JsonDateValue.getFullYear()+"-"+(JsonDateValue.getMonth()+1)+"-"+JsonDateValue.getDate();
         	$('#reservation')[0].value = text+" - "+text;
         }
    </script>
  </head>
  <body>
	    <div class="container">
		     <form id="findForm" action="signinActivityAction_pageQuery.action" class="form-inline" method="post">
			    <fieldset>
			       <label for="reservation">日期范围:</label>
	                  <div class="control-group date-inline">
	                    <div class="controls">
	                     <div class="input-prepend input-group">
	                       <span class="add-on input-group-addon">
							 <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
						   </span>
						   <input type="text" readonly style="width: 200px" name="reservation" id="reservation" class="form-control"/> 
	                     </div>
	                    </div>
	                  </div>
	                   <div class="form-group date-inline">
			              <input id="resetButton" type="button"  class="btn btn-primary" value="重置"/>
			          </div>
			          <div class="form-group">
		              <input type="submit"  class="btn btn-primary" value="查询"/>
		              </div>
	              </fieldset>
		    </form>
	    <hr>
	    <table class="table table-bordered">
		      <thead>
			        <tr>
			          <th>序号</th>
			          <th>发起日期</th>
			          <th>发起用户</th>
			          <th>签到课程</th>
			          <th>发起经度</th>
			          <th>发起纬度</th>
			          <th>签到班级</th>
			          <th>持续时间(分钟)</th>
			          <th>应签人数</th>
			          <th>实签人数</th>
			          <th>相关操作</th>
			        </tr>
		      </thead>
		       <tbody>
			      	<c:forEach varStatus="varStatus" var="signinActivity" items="${pageBean.rows}">
			      		 <tr>
			      		 	  <td>${varStatus.index + 1}</td>
				      		  <td>${signinActivity.activityDate}</td>
					          <td>${signinActivity.user.name}</td>
					          <td>${signinActivity.curriculum.name}</td>
					          <td>${signinActivity.longitude}</td>
					          <td>${signinActivity.latitude}</td>
					          <td>
					          <c:forEach varStatus="varStatus2" var="majorClass" items="${signinActivity.majorClasses}" >
					              ${majorClass.name}<c:if test="${!varStatus2.last}">,</c:if>
					          </c:forEach>
					          </td>
					          <td>${signinActivity.duration}</td>
					          <td>${signinActivity.totalPeople}</td>
					          <td>${signinActivity.totalSigninPeople}</td>			         	
					          <td>
					          	<a  class="btn btn-primary btn-xs" href="signinDetailAction_findSigninPageQuery.action?model.signinActivity.id=${signinActivity.id}&model.signinActivity.activityType=${signinActivity.activityType}">详情</a>
					          	<a  class="btn btn-danger btn-xs" onclick="deleteSigninActivity('${signinActivity.id}')">删除</a>
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