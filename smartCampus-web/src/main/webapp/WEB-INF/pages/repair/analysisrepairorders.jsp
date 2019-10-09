<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>报修单分析</title>
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
		loadHighcharts();
		$('#send').click(function() {
			loadHighcharts();
		});
		$("#submitButton").click(function() {
			$.ajax({
				type : 'post',
				url : 'repairOrderAction_loadHighchartsData.action',
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
		var month = (JsonDateValue.getMonth() + 1) + "";
		if (month.length != 2) {
			month = "0" + month;
		}
		var date = JsonDateValue.getDate() + "";
		if (date.length != 2) {
			date = "0" + date;
		}
		var startdate = JsonDateValue.getFullYear() + "-" + month + "-01";
		var enddate = JsonDateValue.getFullYear() + "-" + month + "-" + date;
		$('#reservation')[0].value = startdate + " - " + enddate;
	}
	function loadHighcharts() {
		$.ajax({
			type : "POST",
			url : "repairOrderAction_loadHighchartsData.action",
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
		$('#repair').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : ajaxobj.title
			},
			tooltip : {
				headerFormat : '{series.name}<br>',
				pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						format : '<b>{point.name}</b>: {point.percentage:.1f} %',
						style : {
							color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '物业维修评星',
				data : ajaxobj.series
			} ]
		});
	}
</script>
</head>
<body>
	<div class="container">
		<form id="findForm"
			action="repairOrderAction_loadHighchartsData.action"
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
		<div id="repair" style="min-width:400px;height:400px"></div>
	</div>
</body>
</html>