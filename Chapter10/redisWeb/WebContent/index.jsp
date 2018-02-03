<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html  >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>监控redis</title>
</head>
<body>
	<div id="myDiv" style="width: 900px; height: 350px;"></div>

	<div id="indexDiv2" style="width: 900px; height: 350px;"></div>

</body>

<script src="js/plotly.min.js"></script>
<script>
	var data = [ {
		x : [ '2018-01-04 12:23:00', '2018-01-04 13:33:00',
				'2018-01-04 14:23:00' ],
		y : [ 1, 3, 6 ],
		type : 'scatter',
		name : '内存占用'
	} ];

	var layout = {
		title : 'Redis 分配的内存总量',
	};

	Plotly.newPlot('myDiv', data, layout);

	var data2 = [ {
		x : [ '2018-01-04 12:23:00', '2018-01-04 13:33:00',
				'2018-01-04 14:23:00' ],
		y : [ 1, 3, 6 ],
		type : 'scatter',
		name : '内存占用'
	} ];

	var layout2 = {
		title : 'Redis Key的实时数量',
	};

	Plotly.newPlot('indexDiv2', data2, layout2);
</script>
</html>