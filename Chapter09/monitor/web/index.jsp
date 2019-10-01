<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>自定义Redis监控平台</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="./css/sb-admin-2.min.css" rel="stylesheet">

<script src="https://cdn.bootcss.com/echarts/3.7.2/echarts.min.js"></script>
<style>
	.row_class {
		display: flex;
	}
	
	.row_class>div {
		flex: 1;
	}
</style>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">自定义Redis监控平台 </h1>

					</div>

					<!-- Content Row -->
					<div class="row row_class">

						<!-- used memory Card Example -->
						<div class="  col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">
												 used memory												
												</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="Memory_used_memory"></div>
										</div>

									</div>
								</div>
							</div>
						</div>

						<!-- connected clients Card Example -->
						<div class="  col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div 
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												connected clients
												</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="Clients_connected_clients" ></div>
										</div>

									</div>
								</div>
							</div>
						</div>

						<!--Clients Card Example -->
						<div class="  col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div	class="text-xs font-weight-bold text-warning text-uppercase mb-1">
											 used_memory_rss
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="Memory_used_memory_rss"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- commands processed Card Example -->
						<div class="  col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
												commands processed
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="Stats_total_commands_processed"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- used_cpus_sys Card Example -->
						<div class="  col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-warning text-uppercase mb-1">
												used_cpus_sys
												</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="CPU_used_cpu_sys"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- used_cpu_user Card Example -->
						<div class="col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
												used_cpu_user
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800" id="CPU_used_cpu_user"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<!-- Content Row -->
					<div class="row">
						<div class="col-lg-12 mb-2">
							<!-- Illustrations -->
							<div class="card shadow mb-2">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary"> <div id="message"></div> </h6>
								</div>
								<div class="card-body">
									<div id="main" style="width: 95%; height: 400px;"></div>
								</div>
							</div>
						</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; 信平 2019</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
	
	<script type="text/javascript">
	
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
		jsonObj = JSON.parse(event.data);
		//console.log(jsonObj);
		renderBoard(jsonObj);
		renderChat(jsonObj);
	}

	function trim(x) {
		return x.replace(/^\s+|\s+$/gm, '');
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
	
	function renderBoard(jsonObj){		
		//console.log(jsonObj);
		Memory_used_memory = jsonObj.Memory_used_memory;
		Clients_connected_clients = jsonObj.Clients_connected_clients ;
		Memory_used_memory_rss = jsonObj.Memory_used_memory_rss;
		Stats_total_commands_processed = jsonObj.Stats_total_commands_processed;
		CPU_used_cpu_sys = jsonObj.CPU_used_cpu_sys;
		CPU_used_cpu_user = jsonObj.CPU_used_cpu_user;
		
		document.getElementById('Memory_used_memory').innerHTML = Memory_used_memory + " M";
		document.getElementById('Clients_connected_clients').innerHTML = Clients_connected_clients ;
		document.getElementById('Memory_used_memory_rss').innerHTML = Memory_used_memory_rss + " M" ;
		document.getElementById('Stats_total_commands_processed').innerHTML = Stats_total_commands_processed ;
		document.getElementById('CPU_used_cpu_sys').innerHTML = CPU_used_cpu_sys ;
		document.getElementById('CPU_used_cpu_user').innerHTML = CPU_used_cpu_user ;		
	}
	
	var time_arr = [];
	var data_arr =[];
	
	function initChat(){
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		option = {
				title : {
					text : 'keys数量'
				},
				tooltip : {},
			    xAxis: {
			        type: 'category',
			        data: time_arr
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        data: data_arr,
			        type: 'line',
			        smooth: true
			    }]
			};

		
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		
	}
	function renderChat(jsonObj){		
		keys = jsonObj.db0_keys;
		time = jsonObj.time;
		//console.log(keys, time);
		
		// 使曲线图中最多显示20个点
		if (time_arr.length < 5) {			
			time_arr.push(time);
			data_arr.push(keys);
		}else{
			// 删除数组中的第一个元素
			time_arr.splice(0, 1);
			data_arr.splice(0, 1);
			
			time_arr[time_arr.length] = time;
			data_arr[data_arr.length] = keys;			
		}
		
		//console.log(time_arr.length);		
		initChat();
	}
	
	initChat(); 
</script>
									

</body>

</html>
