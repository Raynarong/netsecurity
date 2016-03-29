<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>账号管理</title>
	<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="./lib/md5.js"></script>
</head>
<body>	
	<div class="easyui-panel" title="修改密码">
		<div style="padding:10px 0 10px 60px">
	    <form id="ff" method="post" action="">
	    	<table>
	    		<tr>
	    			<td class="tit">原密码</td>
	    			<td><input class="easyui-validatebox" type="password" id="pwdOld"name="pwdOld" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td class="tit">新密码</td>
	    			<td><input class="easyui-validatebox" type="password" id="pwd"name="pwd" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td class="tit">确认密码</td>
	    			<td><input class="easyui-validatebox" type="password" id="pwd2" name="pwd2" data-options="required:true"></input></td>
	    		</tr>
				<tr>
	    			<td></td>
	    			<td>
						<a id="submit" href="javascript:void(0)" class="blue-btn submit-btn" >提交</a>
						<a href="javascript:void(0)" class="blue-btn clear-btn" onClick="clearForm()">清空</a>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:left;padding-left:50px;">
	    	
	    </div>
	</div>
	<script>
		function submitForm(){
		checkPassword();
		
		//	$('#ff').form('submit');
		}
		var selectcpy=$('#submit');
		selectcpy.click(function(){
			if(checkPassword())	
			{
				var pwd =$("#pwd").attr('value'); 
			    var pwdOld=$('#pwdOld').attr('value'); 
			    var url = "editPassWord.action";  
			    var params = {  
			        'pwd':pwd  ,
			        'pwdOld':pwdOld
			    };  
		    //console.log($("#cpy"));
		   
		    $.post(url, params, callcityback,"html");
			}
		    
		});
		function callcityback(result,textStatus){  
		    if(eval(result)==true)
		    {
		    	alert("修改成功！");
		    	$('#ff').form('clear');
		    }
		    else
		    alert("修改失败");
		} 
		
		function clearForm(){
			$('#ff').form('clear');
		}
	
function trim(str){
      return str.replace(/^\s*|\s*$/g,'');
    }		
function checkPassword() {
	  var oldpwd = document.getElementById("pwdOld").value;
	  if(trim(oldpwd) == ''){
	  alert("请输入原密码");
	  document.getElementById("pwdOld").focus();
	  return false;
	  }
	  
	  var passwd1 = document.getElementById("pwd").value;
	  if(trim(passwd1) == ''){
	  alert("请输入新密码");
	  document.getElementById("pwd").focus();
	  return false;
	  }
	  var passwd2 = document.getElementById("pwd2").value;
	  if(passwd1 != passwd2){
	     alert("两次密码输入不一致");
	     document.getElementById("pwd2").focus();
	     return false;
	  }
	  var hash = hex_md5(trim(passwd1));
	  document.getElementById("pwd").value=hash;
	  document.getElementById("pwd2").value=hash;
	  document.getElementById("pwdOld").value=hex_md5(trim(oldpwd));
		
	  return true;
	}		
	</script>
</body>
</html>