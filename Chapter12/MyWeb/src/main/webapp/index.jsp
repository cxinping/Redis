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
				<input name="userName" id="userName" class="easyui-validatebox" required="true"  missingMessage="登录名不能空" >
			</div>
			<div class="fitem">
				<label>age</label>
				<input name="age"  id="age" class="easyui-validatebox" validType="ageNum" >
			</div>
			<div class="fitem">
				<label>Phone:</label>
				<input name="phone" id="phone" class="easyui-validatebox" validType=phoneNum >
			</div>
			<div class="fitem">
				<label>Email:</label>
				<input name="email" id="email" class="easyui-validatebox" validType="email" invalidMessage="请输入正确的邮箱">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="addBtn" style="display:none" onclick="saveUser()">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="editBtn" style="display:none" onclick="editUserAction()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript" src="./js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="./js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		
		//扩展JqueryUI的校验规则
	    $.extend($.fn.validatebox.defaults.rules, {     
            phoneNum: { //验证手机号    
                validator: function(value, param){  
                 return /^1[3-8]+\d{9}$/.test(value);  
                },     
                message: '请输入正确的手机号码。'    
            },  
            
            ageNum: { //验证年龄    
                validator: function(value, param){  
                 return /^[0-9]{1,2}$/.test(value);  
                },     
                message: '请输入合法的年龄（1~99）岁。'    
            },  
             
            telNum:{ //既验证手机号，又验证座机号  
              validator: function(value, param){  
                  return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);  
                 },     
                 message: '请输入正确的电话号码。'  
            }    
        });  
		
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','添加用户');
			$('#fm').form('clear');
			
			$("#addBtn").show();
		}
	
		function saveUser(){
			
			var user = {
		            userName:$("#userName").val(),
		            age:$("#age").val(),
		            phone:$("#phone").val(),
		            email:$("#email").val()
		        };
			 
			 $.ajax({
				    url:  "user/api/users/v1/user/add",	
					type : "put" ,
					contentType: "application/json",
		            dataType: "json",					
					data: JSON.stringify(user) ,						
					error : function(json) {
						console.log( json );
					},
					success : function(data) {
						$('#dlg').dialog('close');		// close the dialog
						console.log(JSON.stringify(data))
					 
						getUsers();
					}
				});
		}
	   
		//修改用户	
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','修改用户');
				$('#fm').form('load',row);
				$("#editBtn").show();
			}else{
				$.messager.show({	// show error message
					title: 'Error',
					msg: '请选择操作的记录'
				});
			}
		}	
			
		 function editUserAction(){
				var row = $('#dg').datagrid('getSelected');
				var user = {
			            id :  row.id,
						userName:$("#userName").val(),
			            age:$("#age").val(),
			            phone:$("#phone").val(),
			            email:$("#email").val()
			        };
				
				$.ajax({
					    url:  "user/api/users/v1/user/update",	
						type : "post" ,
						contentType: "application/json",
			            dataType: "json",					
						data: JSON.stringify(user) ,						
						error : function(json) {
							console.log( json );
						},
						success : function(data) {
							// 关闭窗口
							$('#dlg').dialog('close');		// close the dialog
							getUsers();
						}
					});
		 }
		 
		//删除用户
		function removeUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('确认','你确认删除这条纪录吗?',function(r){
					if (r){
						console.log( JSON.stringify(r) );
						
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
								console.log( JSON.stringify(json) );
							},
							success : function(data) {
								console.log("111 "+ JSON.stringify(data))
								getUsers();
							}
						});
						
					}
				});
			}else{
				$.messager.show({	// show error message
					title: 'Error',
					msg: '请选择操作的记录'
				});
			}
		}
		
		
		
	
		//查询用户列表
		function getUsers() {
			$.ajax({
				async : true, //是否异步
				cache : false, //是否使用缓存
				type : "get", //请求方式 
				dataType : 'json', //返回数据的格式
				url : "user/api/users/v1/users",			
				error : function(json) {
					console.log( json );
				},
				success : function(data) {
					//重新加载用户列表
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