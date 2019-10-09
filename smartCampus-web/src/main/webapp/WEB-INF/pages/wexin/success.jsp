<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>绑定成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${pageContext.request.contextPath}/css/jquery-weui.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/demos.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/weui.min.css"
	rel="stylesheet">	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/fastclick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-weui.js"></script>
<style>
      .icon-box {
        margin-bottom: 25px;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center
      }

      .icon-box i {
        margin-right: 18px
      }

      .icon-box__ctn {
        -webkit-flex-shrink: 100;
        flex-shrink: 100
      }

      .icon-box__title {
        font-weight: 400
      }

      .icon-box__desc {
        margin-top: 6px;
        font-size: 12px;
        color: #888
      }

      .icon_sp_area {
        margin-top: 10px;
        text-align: left
      }

      .icon_sp_area i:before {
        margin-bottom: 5px
      }
    </style>
  </head>

  <body ontouchstart>
    <header class='demos-header'>
      <h1 class="demos-title">绑定结果</h1>
    </header>
    <div class='demos-content-padded'>
      <div class="icon-box">
        <i class="weui-icon-success weui-icon_msg"></i>
        <div class="icon-box__ctn">
          <h3 class="icon-box__title">成功</h3>
          <p class="icon-box__desc">绑定成功</p>
        </div>
      </div>
    </div>
    <script src="../lib/jquery-2.1.4.js"></script>
<script src="../lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
  </body>
</html>
