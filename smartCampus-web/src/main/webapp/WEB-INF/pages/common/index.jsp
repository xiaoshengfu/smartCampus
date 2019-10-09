<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>智慧校园管理系统</title>
    <link href="${pageContext.request.contextPath}/public/media/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/style-metro.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="${pageContext.request.contextPath}/public/media/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/media/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/public/media/css/colpick.css" type="text/css" />
	<link href="${pageContext.request.contextPath}/public/static/css/website.css" type="text/css" />
	<link href="${pageContext.request.contextPath}/public/swiper/dist/css/swiper.min.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/public/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.cookie.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/jquery.backstretch.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/app.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/bootstrap-modal.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/bootstrap-modalmanager.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/media/js/ui-modals.js"></script>
	<style type="text/css">
		.setbg input[type="text"],
		.setcol .yu_bgcol {
		    background-color: initial!important;
		}
		.m-logo {
		    color: #ccc;
		    font-size: 16px;
		    display: inline-block;
		    margin-top: 15px;
		}
		iframe {
		    margin-top: 50px;
		    height: 800px;
		}
	</style>
</head>
	<body class="page-header-fixed">
	    <div class="header navbar navbar-inverse navbar-fixed-top">
	        <div class="navbar-inner">
	            <div class="container-fluid">
	                  <span class="m-logo">智慧校园管理系统</span>
	                <ul class="nav pull-right">
	                    <li class="dropdown user">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                            <span class="username">${loginUser.name}</span>
	                            <i class="icon-angle-down"></i>
	                        </a>
	                        <ul class="dropdown-menu">
	                            <li><a href="userAction_updatePassword.action"><i class="icon-lock"></i>修改密码</a></li>
	                            <li><a href="userAction_logout.action"><i class="icon-key"></i>退出</a></li>
	                        </ul>
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="page-container row-fluid">
	        <div class="page-sidebar nav-collapse collapse">
	            <ul class="page-sidebar-menu">
	                <li>
	                    <div class="sidebar-toggler hidden-phone"></div>
	                </li>
	                <shiro:hasPermission name="user_manager">
		                <li class="start">
		                    <a href="javascript:;">
		                        <i class="icon-reorder"></i>
		                        <span class="title">用户管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="userAction_pageQuery.action">查看所有用户</a>
		                            <a target="main" href="userAction_addUserPage.action">导入用户信息</a>
		                            <a target="main" href="userAction_teacherPageQuery.action">管理教师课程</a>
		                            <a target="main" href="userAction_batchAddUserPage.action">批量导入用户信息</a>
		                            <a target="main" href="userAction_batchAddUserCurriculumPage.action">批量导入教师课程</a>
		                        </li>
		                    </ul>
		                </li>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="majorclass_manager">
		                <li>
		                    <a href="javascript:;">
		                        <i class="icon-comments"></i>
		                        <span class="title">班级管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="majorClassAction_pageQuery.action">查看所有班级</a>
		                            <a target="main" href="majorClassAction_addMajorClassPage.action">导入班级信息</a>
		                            <a target="main" href="majorClassAction_batchAddMajorClassPage.action">批量导入学生信息</a>
		                        </li>
		                    </ul>
		                </li>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="curriculum_manager">
		                <li>
		                    <a href="javascript:;">
		                        <i class="icon-comments"></i>
		                        <span class="title">课程管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="curriculumAction_pageQuery.action">查看所有课程</a>
		                            <a target="main" href="curriculumAction_addCurriculumPage.action">导入课程信息</a>
		                            <a target="main" href="curriculumAction_batchAddCurriculumPage.action">批量导入课程信息</a>
		                        </li>
		                    </ul>
		                </li>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="student_manager">
		                <li>
		                    <a href="javascript:;">
		                        <i class="icon-book"></i>
		                        <span class="title">学生管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="studentAction_pageQuery.action">查看所有学生</a>
		                            <a target="main" href="studentAction_addStudentPage.action">导入学生信息</a>
		                            <a target="main" href="studentAction_batchAddStudentPage.action">批量导入学生信息</a>
		                        </li>
		                    </ul>
		                </li>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="signin_manager">
		                <li>
		                    <a href="javascript:;">
		                        <i class="icon-group"></i>
		                        <span class="title">签到管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="signinActivityAction_pageQuery.action">查看签到信息</a>
		                            <a target="main" href="signinActivityAction_analysisSigninActivity.action">签到信息分析</a>
		                        </li>
		                    </ul>
		                </li>
	           		</shiro:hasPermission>
	           		<shiro:hasPermission name="repair_manager">
		                <li>
		                    <a href="javascript:;">
		                        <i class="icon-credit-card"></i>
		                        <span class="title">报修管理</span>
		                        <span class="arrow "></span>
		                    </a>
		                    <ul class="sub-menu">
		                        <li>
		                            <a target="main" href="repairOrderAction_pageQuery.action">查看所有保修单</a>
		                            <a target="main" href="repairOrderAction_analysisRepairOrders.action">报修信息分析</a>
		                        </li>
		                    </ul>
		                </li>
	                </shiro:hasPermission> 
	            </ul>
	        </div>
	        <div class="page-content">
	            <iframe src="userAction_welcome.action" frameborder="0" width="100%" name="main"></iframe>
	        </div>
	        <div id="static" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	                <h3 id="titler"></h3>
	            </div>
	            <div class="modal-body">
	                <p id="contenter"></p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" data-dismiss="modal" class="btn">确定</button>
	                <button type="button" data-dismiss="modal" class="btn green">关闭会话</button>
	            </div>
	        </div>
	    </div>
	    <script src="${pageContext.request.contextPath}/public/media/js/colpick.js"></script>
	    <script src="${pageContext.request.contextPath}/public/media/js/plugin.js"></script>
	    <script src="${pageContext.request.contextPath}/public/media/js/website.js"></script>
	    <script src="${pageContext.request.contextPath}/public/swiper/dist/js/swiper.min.js"></script>
	    <script>
	    $(document).ready(function() {
	        App.init();
	        UIModals.init();
	        $('.datainfo .span12,.loading').hide();
	    });
	    </script>
	</body>
</html>
