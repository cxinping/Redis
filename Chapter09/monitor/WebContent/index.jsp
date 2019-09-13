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
											<div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
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
											<div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
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
											 Clients
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
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
											<div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
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
											<div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
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
											<div class="h5 mb-0 font-weight-bold text-gray-800">19</div>
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
									<h6 class="m-0 font-weight-bold text-primary">Illustrations</h6>
								</div>
								<div class="card-body">

									<div id="main" style="width: 100%; height: 400px;"></div>

									

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
	
	function render(){
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			title : {
				text : 'ECharts 入门示例'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				data : [ "衬衫", "羊毛衫", "雪纺衫",
						"裤子", "高跟鞋", "袜子" ]
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				data : [ 5, 20, 36, 10, 10, 20 ]
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
	
	
</script>
									

</body>

</html>
