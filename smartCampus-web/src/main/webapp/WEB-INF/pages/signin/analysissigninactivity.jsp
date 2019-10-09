<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>签到分析</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="all"
	href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css" />
<style>
.date-inline {
	display: inline-block;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/moment.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/exporting.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/highcharts-zh_cn.js"></script>
<script type="text/javascript">
	$(function() {
		$('#reservation').daterangepicker(null, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
		initDate();
		$('#resetButton').click(function() {
			initDate();
		});
		//loadHighcharts();
		myHighcharts();
		$('#send').click(function() {
			loadHighcharts();
		});
		$("#submitButton").click(function() {
			$.ajax({
				type : 'post',
				url : 'signinActivityAction_loadHighchartsData.action',
				data : $("#findForm").serialize(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					myHighcharts(data);
				}
			});
		});
	});
	function initDate() {
		var JsonDateValue = new Date();
		var month = (JsonDateValue.getMonth()) + "";
		var endmonth = (JsonDateValue.getMonth() + 1) + "";
		if (month.length != 2) {
			month = "0" + month;
		}
		if (endmonth.length != 2) {
			endmonth = "0" + endmonth;
		}
		var date = JsonDateValue.getDate() + "";
		if (date.length != 2) {
			date = "0" + date;
		}
		var startdate = JsonDateValue.getFullYear() + "-" + month + "-01";
		var enddate = JsonDateValue.getFullYear() + "-" + endmonth + "-01";
		$('#reservation')[0].value = startdate + " - " + enddate;
	}
	function loadHighcharts() {
		$.ajax({
			type : "POST",
			url : "signinActivityAction_loadHighchartsData.action",
			data : {
				reservation : $("#reservation").val()
			},
			dataType : "json",
			success : function(data) {
				myHighcharts(data);
			}
		});
	}
	function myHighcharts(ajaxobj) {
		$('#signinanaly').highcharts({
			title : {
				text : "2017-8-1 - 2017-10-1签到率折线图",
				x : -20
			},
			xAxis : {
				categories : [ '第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周' ]
			},
			yAxis : {
				title : {
					text : '签到率 (%)'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			tooltip : {
				valueSuffix : '%'
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			},
			series : [ {
				name : '高等数学',
				data : [ 99.0, 97.0, 85.0, 88.0, 91.0, 94.0, 97.0, 90.0, 93.0 ]
			}, {
				name : '大学英语',
				data : [ 100.0, 96.0, 95.0, 90.0, 91.0, 92.0, 94.0, 96.0, 98.0 ]
			}, {
				name : '大学物理',
				data : [ 100.0, 98.0, 91.0, 92.0, 93.0, 94.0, 97.0, 99.0, 93.0 ]
			}, {
				name : '离散数学',
				data : [ 98.0, 90.0, 87.0, 92.0, 96.0, 99.0, 100.0, 96.0, 95.0 ]
			} ]
		});
	}
</script>
</head>
<body>
	<div class="container">
		<form id="findForm"
			action="signinActivityAction_loadHighchartsData.action"
			class="form-inline" method="post">
			<fieldset>
				<div class="form-group">
					<label for="reservation">日期范围:</label>
					<div class="control-group date-inline">
						<div class="controls">
							<div class="input-prepend input-group">
								<span class="add-on input-group-addon"> <i
									class="glyphicon glyphicon-calendar fa fa-calendar"></i>
								</span> <input type="text" readonly style="width: 200px"
									name="reservation" id="reservation" class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group date-inline">
					<input id="resetButton" type="button" class="btn btn-primary"
						value="重置" />
				</div>
				<div class="form-group">
					<input id="submitButton" type="button" class="btn btn-primary"
						value="查询" />
				</div>
			</fieldset>
		</form>
		<hr>
		<div id="signinanaly" style="min-width:400px;height:400px"></div>
	</div>
</body>
</html>