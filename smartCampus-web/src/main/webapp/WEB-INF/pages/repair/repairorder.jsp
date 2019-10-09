<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>保修单详情</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
	    <div class="container">
	    	<a class='btn btn-primary' href="repairOrderAction_pageQuery.action">返回</a>
			<c:choose>
				 <c:when test="${repairOrder.orderState == 0}"> 
				 <a class='btn btn-primary' href="repairOrderAction_changeOrderState.action?model.id=${repairOrder.id}&model.orderState=1">标记为正处理</a>         			
				 </c:when>
				 <c:when test="${repairOrder.orderState == 1}">
				 <a class='btn btn-success' href="repairOrderAction_changeOrderState.action?model.id=${repairOrder.id}&model.orderState=2">标记为已处理</a>
				 </c:when>   	          		
			</c:choose>	        	  
	        <hr>
		    <table class="table table-bordered">
				  <tr>
				     <th>发起日期</th>
				     <td>${repairOrder.orderDate}</td>
				  </tr>
				  <tr>
				     <th>报修单状态</th>
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
				  </tr>
				  <tr>
				     <th>报修学生</th>
				     <td>${repairOrder.student.name}</td>
				  </tr>
				  <tr>
				     <th>报修地点</th>
				     <td>${repairOrder.place}</td>
				  </tr>
				  <tr>
				     <th>联系电话</th>
				     <td>${repairOrder.telephone}</td>
				  </tr>
				  <tr>
				     <th>报修描述</th>
				     <td>${repairOrder.information}</td>
				  </tr>
				  <tr>
				     <th>报修图片</th>
				     <td>
					     <c:choose>
							      <c:when test="${empty repairOrder.pictureUrl}">
							                     学生未上传照片
							      </c:when>
							      <c:otherwise>
				     				<img style="height: 280px" alt="报修图片" src="repairOrderAction_findRepairOrderPicture.action?model.pictureUrl=${repairOrder.pictureUrl}"/>
	                			  </c:otherwise>
						  </c:choose>	
				     </td>
				  </tr>
				  <tr>
				     <th>学生评分</th>
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
				  </tr>
				  <tr>
				     <th>学生评价</th>
				     <td>${repairOrder.evaluationContent}
				         <c:choose>
						      <c:when test="${empty repairOrder.evaluationContent}">
						          	学生未作出评价
						      </c:when>
						      <c:otherwise>
						          	${repairOrder.evaluationContent}
                			  </c:otherwise>
					    </c:choose>
				     </td>
				  </tr>
		     </table>
		</div>
	</body>
</html>