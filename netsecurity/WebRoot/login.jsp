<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/jstl/c" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="width: 100%;height: 100%;overflow: hidden;">
	<head>
		<title>内网安全监测系统-登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="../easyui-resourse/Public/Css/login.css"
			type="text/css" media="screen" />
		<script type="text/javascript"
			src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="../include/js/md5.js"></script>
		<script type="text/javascript">
	function trim(str)
	{
		return str.replace(/^\s*|\s*$/g, '');
	}
	function login2()
	{
		if (checkLogin())
			$('#loginForm').submit();
	}
	//验证输入信息
	function checkLogin()
	{

		var name1 = trim(document.getElementById("name").value);
		if (trim(name1) == '')
		{
			alert("请输入用户名");
			document.getElementById("name").focus();
			return false;
		}

		var passwd1 = trim(document.getElementById("password").value);
		if (trim(passwd1) == '')
		{
			alert("请输入密码");
			document.getElementById("password").focus();
			return false;
		}

		var checkCode1 = trim(document.getElementById("checkCode").value);
		if (trim(checkCode1) == '')
		{
			alert("请输入验证码");
			document.getElementById("checkCode").focus();
			return false;
		}

		var hash = hex_md5(trim(passwd1));
		document.getElementById("password").value = hash;
		return true;
	}
	function trim(str)
	{
		return str.replace(/^\s*|\s*$/g, '');
	}
	$(function()
	{
		$('#changeCode').click(function()
		{
			var url = 'CheckCode.jsp';
			$('#codePic').attr('src', '/supervision/CheckCode.jsp');
		});
	});
	// 验证码 密码处 回车登录
	$(function()
	{
		$('#checkCode').bind('keypress', function(event)
		{
			if (event.keyCode == "13")
			{
				login2();
			}
		});
		$('#password').bind('keypress', function(event)
		{
			if (event.keyCode == "13")
			{
				login2();
			}
		})
	});
	$(function()
	{
		//?
		if ('${request.errorMsg!=null}' == 'true')
		{
			alert('${request.errorMsg}');
		}

	}

	);
</script>
	</head>
	<style>
</style>
	<body
		style="width: 100%; height: 100%; overflow: hidden; padding: 0; margin: 0;">
		<div class="content_img">
			<img src="../include/images/content_bg_noline.jpg" border="0" />
		</div>
		
	 <form name="loginForm" id="loginForm" method="post" action="login.action"">
		<dl class="loginContent">
			<dt>
				<h3>
					内网安全监测系统
				</h3>
				<span></span>
			</dt>


				<dd>
					<label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号：
					</label>
					<input class="easyui-validatebox account form-textbox" type="text"
						id="name" name="name" value="" required>
				</dd>
				<dd>
					<label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码：
					</label>
					<input class="easyui-validatebox  password form-textbox"
						type="password" name="password" id="password" required>
				</dd>

				<dd class="yzm">
					<label>
						&nbsp;&nbsp;验证码：
					</label>
					<input class="easyui-validatebox  yzm form-textbox" type="text"
						id="checkCode" name="checkCode" value="" required>
					<img id="codePic" src="/supervision/CheckCode.jsp" alt="点击刷新"
						title="点击刷新" class="verifyImg">
					<a id="changeCode" href="javascript:void(0)" class="changeOne">换一个？</a>
				</dd>
				
				<dd>
				<label>用户权限:</label>
								 
					&nbsp;&nbsp;&nbsp;<select name="permissionType">
							<option value="0">
								超级管理员
							</option>
							<option value="1">
								管理员
							</option>
							<option value="2" selected="selected">
								普通用户
							</option>
						</select>
					</span>
			
				
				</dd>
				<dd>
				&nbsp;&nbsp;&nbsp;
				</dd>
				<dd class="btn">
					<a href="#" class="backto"></a><a href="javascript:login2()"
						class="loginin"></a>
				</dd>
			
		</dl>
		
		</form>
	</body>



</html>
