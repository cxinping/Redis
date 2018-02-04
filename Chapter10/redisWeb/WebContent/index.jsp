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
	Welcome
	<br />
	<input id="text" type="text" />
	<button onclick="send()">发送消息</button>
	<button onclick="closeWebSocket()">关闭WebSocket连接</button>
	<hr />

	<div id="message"></div>

	<div id="indexDiv1" style="width: 900px; height: 400px;"></div>

	<div id="indexDiv2" style="width: 800px; height: 300px;"></div>

</body>

<script src="js/plotly.min.js"></script>
<script>
	var websocket = null;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/redisWeb/websocket");
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

		//loopData();
	}

	function loopData() {
		send();
		setTimeout('loopData()', 5000);

	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
		console.log(event.data);
	
		jsonObj = JSON.parse(event.data);
		//console.log(jsonObj);

		render(jsonObj);

	}

	function trim(x) {
		return x.replace(/^\s+|\s+$/gm, '');
	}

	var data1 = {};
	data1.type = 'scatter';
	data1.name = '内存占用';

	var datas1 = [];
	var x1 = new Array();
	var y1 = new Array();

	function render(jsonObj) {

		
		for (var i = 0; i < jsonObj.length; i++) {
			//console.log(jsonObj[i].date + ", " + jsonObj[i].key + ", "	+ jsonObj[i].value);

			if (jsonObj[i].key == 'used_memory') {
				x1.push(jsonObj[i].date);
				y1.push(jsonObj[i].value);
				data1.x = x1;
				data1.y = y1;
			} else if (jsonObj[i].key == 'keys') {

			}

		}

		datas1.push(data1);
		//console.log(datas1);
		console.log(JSON.stringify(datas1));

		var layout1 = {
			title : 'Redis 分配的内存总量',
		};

		//Plotly.newPlot('indexDiv1', datas1, layout1 );
		//Plotly.newPlot('indexDiv1', datas1);
		Plotly.newPlot('indexDiv1', datas1, layout1);

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
		var message = document.getElementById('text').value;
		websocket.send(message);
	}
	/***
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
	 **/

	//Plotly.newPlot('indexDiv1', data, layout);

	var data2 = [ {
		x : [ '2018-01-04 12:23:00', '2018-01-04 13:33:00',
				'2018-01-04 14:23:00', '2018-01-04 14:35:00' ],
		y : [ 1, 3, 1, 10 ],
		type : "scatter",
		mode : "lines+markers",
		line : {
			color : '#17BECF'
		}

	} ];

	var layout2 = {
		title : 'Redis Key的实时数量',
	};

	Plotly.newPlot('indexDiv2', data2, layout2);
</script>
</html>