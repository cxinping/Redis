<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>用户信息系统</title>
	<link rel="stylesheet" type="text/css" href="./js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./js/easyui/themes/icon.css">
	
	<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			color:#666;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
	</style>
	
</head>
<body>
	
	<table id="dg" title="用户列表" class="easyui-datagrid" style="width:700px;height:350px"
			
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="userName" width="50">姓名</th>
				<th field="age" width="50">年龄</th>
				<th field="phone" width="50">手机</th>
				<th field="email" width="50">邮箱</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>userName</label>
				<input name="userName" id="userName" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>age</label>
				<input name="age"  id="age" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Phone:</label>
				<input name="phone">
			</div>
			<div class="fitem">
				<label>Email:</label>
				<input name="email" class="easyui-validatebox" validType="email">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="editUserAction()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript" src="./js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="./js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','添加用户');
			$('#fm').form('clear');
		}
	
		function saveUser(){
			/**
			var record = {
					id : '1111',
					userName : 'abcdef',
					age : 26
			};
			//console.log(JSON.stringify(record));
			*/
			
			var user = {
		            userName:$("#userName").val(),
		            age:$("#age").val()
		        };
			console.log(JSON.stringify(user));
			
			//var param = $('#fm').serializeArray();
			//console.log( JSON.stringify(param));
			
			/**
			$('#fm').form('submit',{
				url:  "user/api/users/v1/user/add",
				contentType: 'application/json;charset=utf-8',
				dataType: "json",
				data: JSON.stringify(rowData) ,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.success){
						$('#dlg').dialog('close');		// close the dialog
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({
							title: 'Error',
							msg: result.msg
						});
					}
				},
				 error: function (xhr, status, p3, p4) {
	                    var err = "Error " + " " + status + " " + p3;
	                    if (xhr.responseText && xhr.responseText[0] == "{")
	                        err = JSON.parse(xhr.responseText).message;
	                    alert(err);
	            }
			});
			 **/
			 
			 $.ajax({
				    url:  "user/api/users/v1/user/add",	
					type : "put" ,
					contentType: "application/json",
		            dataType: "json",					
					data: JSON.stringify(user) ,						
					error : function(json) {
						//alert(  json  );
						console.log( json );
						
					},
					success : function(data) {
						$('#dlg').dialog('close');		// close the dialog
						console.log(JSON.stringify(data))
					 
						getUsers();
					}
				});
		}
			
	   
			
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','修改用户');
				/**
				$('#fm').form('load',row);
				url = 'update_user.php?id='+row.id;
				*/
				$('#fm').form('load',row);
				//console.log(  row.id );
			}
		}	
			
		 function editUserAction(){
				var row = $('#dg').datagrid('getSelected');
				var user = {
			            id :  row.id,
						userName:$("#userName").val(),
			            age:$("#age").val()
			        };
				console.log( 'editUserAction='+JSON.stringify(user) );
				
				$.ajax({
					    url:  "user/api/users/v1/user/update",	
						type : "post" ,
						contentType: "application/json",
			            dataType: "json",					
						data: JSON.stringify(user) ,						
						error : function(json) {
							//alert(  json  );
							console.log( json );
							
						},
						success : function(data) {
							$('#dlg').dialog('close');		// close the dialog
							//console.log(JSON.stringify(data))
						 
							getUsers();
						}
					});
		 
		 }
		 
		 
		 
		function removeUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('确认','你确认删除这条纪录吗?',function(r){
					if (r){
						/**
						$.post('remove_user.php',{id:row.id},function(result){
							if (result.success){
								$('#dg').datagrid('reload');	// reload the user data
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.msg
								});
							}
						},'json');
						**/

						$.ajax({
							async : true,
							cache : false,
							type : "delete",
							dataType : 'json',
							url : "user/api/users/v1/user/" + row.id ,			
							error : function(json) {
								console.log( "222 "+ json );
								console.log( JSON.stringify(json) );
								
							},
							success : function(data) {
								console.log("111 "+ JSON.stringify(data))
								//$('#dg').datagrid('reload');
								getUsers();
								
							}
						});
						
					}
				});
			}
		}
	
		function getUsers() {
			$.ajax({
				async : true, //是否异步
				cache : false, //是否使用缓存
				type : "get", //请求方式 
				dataType : 'json', //返回数据的格式
				url : "user/api/users/v1/users",			
				error : function(json) {
					alert(  json  );
					console.log( json );
				},
				success : function(data) {
					//console.log(JSON.stringify(data))
					$("#dg").datagrid("loadData", data.rows);
				}
			});
		}
		
		$(document).ready(function(){
			getUsers();
		});
		
	</script>
</body>
</html>