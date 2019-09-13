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

	<div id="message"></div>
	<hr />

	<div id="indexDiv1" style="width: 800px; height: 300px;"></div>

	<div id="indexDiv2" style="width: 800px; height: 300px;"></div>

</body>

<script src="js/plotly.min.js"></script>
<script>
	var websocket = null;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/monitor/websocket");
		//console.log('websocket=' + websocket);
	} else {
		alert('当前浏览器 Not support websocket')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket连接发生错误");
	};

	//连接成功建立的回调方法
	websocket.onopen = function() {
		setMessageInnerHTML("WebSocket连接成功");
		send();
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		//setMessageInnerHTML(event.data);
		//console.log(event.data);

		jsonObj = JSON.parse(event.data);
		//console.log(jsonObj);
		render(jsonObj);
	}

	function trim(x) {
		return x.replace(/^\s+|\s+$/gm, '');
	}

	// 创建曲线图1的数据源
	var data1 = {};
	data1.type = 'scatter';
	data1.name = '内存占用';
	var datas1 = [];
	data1.x = [];
	data1.y = [];
	datas1.push(data1);

	// 创建曲线图2的数据源
	var data2 = {};
	data2.type = 'scatter';
	data2.name = 'keys数量';
	var datas2 = [];
	data2.x = [];
	data2.y = [];
	datas2.push(data2);

	function render(jsonObj) {

		for (var i = 0; i < jsonObj.length; i++) {
			console.log(jsonObj[i].date + ", " + jsonObj[i].key + ", "	+ jsonObj[i].value);

			if (jsonObj[i].key == 'used_memory') {
				// 使曲线图中最多显示20个点
				if (data1.x.length < 20) {
					data1.x[data1.x.length] = jsonObj[i].date;
					data1.y[data1.y.length] = jsonObj[i].value;

				} else {
					data1.x.splice(0, 1);
					data1.y.splice(0, 1);

					data1.x[data1.x.length] = jsonObj[i].date;
					data1.y[data1.y.length] = jsonObj[i].value;
				}

			} else if (jsonObj[i].key == 'keys') {
				if (data2.x.length < 18) {
					data2.x[data2.x.length] = jsonObj[i].date;
					data2.y[data2.y.length] = jsonObj[i].value;

				} else {
					data2.x.splice(0, 1);
					data2.y.splice(0, 1);

					data2.x[data2.x.length] = jsonObj[i].date;
					data2.y[data2.y.length] = jsonObj[i].value;
				}
			}
		}

		//console.log(datas1);
		console.log(JSON.stringify(datas1));
		console.log(JSON.stringify(datas2));

		var layout1 = {
			title : 'Redis 分配的内存总量(单位:M)',
		};

		Plotly.newPlot('indexDiv1', datas1, layout1);

		var layout2 = {
			title : 'Redis Key的实时数量(单位:个)',
		};

		Plotly.newPlot('indexDiv2', datas2, layout2);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML += innerHTML + '<br/>';
	}

	//关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send() {
		var message = Math.floor(100000 * Math.random());
		websocket.send(message);
	}
</script>
</html>