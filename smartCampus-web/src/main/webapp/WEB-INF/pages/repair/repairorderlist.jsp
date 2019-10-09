<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看所有报修单</title>
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
	  	$(function() {
                  $('#reservation').daterangepicker(null, function(start, end, label) {
                     console.log(start.toISOString(), end.toISOString(), label);
                  });
                     initDate();
                  $('#resetButton').click(function() {
                     initform();
                  });
		          loadpage(${pageBean.visiblePages},${pageBean.currentPage},${pageBean.total},${pageBean.pageSize},"${pageBean.href}","${pageBean.parameter}");
               });
         function initDate() {
         	var JsonDateValue = new Date();
         	var text = JsonDateValue.getFullYear()+"-"+(JsonDateValue.getMonth()+1)+"-"+JsonDateValue.getDate();
         	$('#reservation')[0].value = text+" - "+text;
         }
         function initform() {
         	initDate();
         	$("#stateSelect option").map(function(){
			  	 $(this).attr("selected",false);
			})
         }
    </script>
  </head>
  <body>
	    <div class="container">
		     <form id="findForm" action="repairOrderAction_pageQuery.action" class="form-inline" method="post">
			    <fieldset>
				     <div class="form-group">
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
		              </div>
	                  <div class="form-group">
	                  <label for="stateSelect">报修单状态:</label>
			          <select id="stateSelect" class="form-control limit" name="model.orderState" >
			          	    <option value="4" selected="selected">所有状态</option> 
			               	<option value="0">未处理</option>
			               	<option value="1">处理中</option>
			               	<option value="2">已处理</option>
			   		  </select> 
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
			          <th>报修学生</th>
			          <th>报修地点</th>
			          <th>联系电话</th>
			          <th>保修单状态</th>
			          <th>学生评分</th>
			          <th>操作</th> 
			        </tr>
		      </thead>
		       <tbody>
			      	<c:forEach varStatus="varStatus" var="repairOrder" items="${pageBean.rows}">
			      		 <tr>
			      		 	  <td>${varStatus.index + 1}</td>
				      		  <td>${repairOrder.orderDate}</td>
					          <td>${repairOrder.student.name}</td>
					          <td>${repairOrder.place}</td>
					          <td>${repairOrder.telephone}</td>
					          <td>
								  <c:choose>
						          			<c:when test="${repairOrder.orderState == 0}">
						          				未处理
						          			</c:when>
						          			<c:when test="${repairOrder.orderState == 1}">
						          				处理中
						          			</c:when>
						          			<c:when test="${repairOrder.orderState == 2}">
						          				已处理
						          			</c:when>
						          </c:choose>
					          </td>
					          <td>
					          	  <c:choose>
						          			<c:when test="${empty repairOrder.rating}">
						          				学生未给出评分
						          			</c:when>
						          			<c:otherwise>
						          				${repairOrder.rating}
                							</c:otherwise>
						          </c:choose>
						      </td>			         	
					          <td>
					          	<a  class="btn btn-primary btn-xs" href="repairOrderAction_findRepairOrder.action?model.id=${repairOrder.id}">详情</a>
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