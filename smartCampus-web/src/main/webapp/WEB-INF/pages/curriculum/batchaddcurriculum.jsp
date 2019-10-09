<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加学生信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileinput.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zh.js"></script>
	<script type="text/javascript">
		$(function(){
		   	var control = $("#curriculumfile"); 
			control.fileinput({
			    language: 'zh', //设置语言
			    allowedFileExtensions : ['xls'],//接收的文件后缀
			    showUpload: true, //是否显示上传按钮
			    showCaption: false,//是否显示标题
			    browseClass:"btn btn-primary", //按钮样式             
			});
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
					<c:when test="${batchMessage == 0}">
						<p style="font-weight: bold; color: red">批量导入失败!</p>
					</c:when>
					<c:when test="${batchMessage == 1}">
						<p style="font-weight: bold; color: green">批量导入成功!</p>
					</c:when>
				</c:choose>
				<form id="myform" class="form-horizontal" action="curriculumAction_batchAddCurriculum.action" method="post" style="margin-top: 5px" enctype="multipart/form-data">
					<div class="form-group from-inline">
						<label for="curriculumexceldemo" class="col-sm-3 control-label">批量导入样表:</label>
						<div class="col-sm-5">
							<a class='btn btn-primary' id="curriculumexceldemo" href="curriculumAction_downloadCurriculumExcelDemo.action">下载</a>
						</div>
					</div>
					<div class="form-group">
						<label for="curriculumfile" class="col-sm-3 control-label">请选择xls文件:</label>
						<div class="col-sm-5">
							<input id="curriculumfile" name="curriculumFile" type="file">
						</div>
					</div>
				</form>
	 		</div>
		</div>
	</div>
</body>
</html>