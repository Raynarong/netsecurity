<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>内网安全监控分布式计算平台</title>
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>
<script language="javascript">
//<!--页面加载完执行-->
	$(function(){
		//#rcenter easyUi显示div
		var t = $('#rcenter').tabs({			
		});
			var title, url,iconClass;
			//当以get方式在url中传递了请求参数时，可以利用location的search属性提取参数的值
		if (location.search.substring(1) == "val=0")
		{
			//title = "内网资源统计信息";
			//url = "/supervision/LabOnlineData.jsp";
		}			
		else if(location.search.substring(1) == "val=1")
		{
			title = "账号管理";
			url = "warningAccountManage.jsp";
		}
		else if(location.search.substring(1) == "val=2")
		{
			title = "上传特征库";
			url="enterUploadPage.action";
		}
		else if(location.search.substring(1) == "val=3")
		{
			title="数据库备份";
			url="dbDiffBackup.jsp";
		}
		else if(location.search.substring(1) == "val=4")
		{
			title="修改密码";
			url="changePWD.jsp";
		} else
		//默认情况的加载:？后面不带参数
		{
		<%int userType=(Integer)session.getAttribute("userType");%>
		<%if(userType==2){%>
			title = "修改密码";
			url = "/supervision/editPassword.jsp";
		<%}else if(userType==1){%>
			title = "小组管理";
			url = "/supervision/groupManage.jsp";
		<%}else if(userType==0){%>
			title = "内网资源统计信息";
			url = "/supervision/LabOnlineData.jsp";
		<%}%>
			iconClass="icon-light";
		}
		//增加新的 tab panel
		$('#rcenter').tabs('add',{
			//Tab panel 的标题文字
			title:title,
			//显示在tab panel 标题上的图标的 CSS 类
			iconCls:iconClass,
			//iframe 元素会创建包含另外一个文档的内联框架
			//显示滚动条；框架无边框；显示文档的url;元素行内样式
			content:'<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
			//当设置为 true 时，tab panel 将显示一个关闭按钮，点击它就能关闭这个tab panel
			closable:true
			
		});
		//tree 下的超链
		$("#tree a").click(function(){
			//$(this)调用函数的元素
			//attr:元素的属性
			dst = $(this).attr("url");
			//内容
			title = $(this).html();	
			//open1(dst, title);
			//iconClass = $(this).parent().parent().attr("iconCls");
			//console.log(iconClass);		
			iconClass = $(this).attr("iconCls");
			console.log(iconClass);
			open1(dst, title, iconClass);
		});
		
		//$("#sysmenu").show();
	});
	
	function open1(url, title, iconClass){
	
		if ($('#rcenter').tabs('exists',title))
		{
//			if(title=="预警账号管理")
//			{
///				var tab = $('#rcenter').tabs('getTab', "预警账号管理");
//				$('#rcenter').tabs('update', {
//					tab:tab,
//					options:{
//						title:"预警账号管理",
//						iconCls:iconClass,
//						content:'<iframe scrolling="yes" frameborder="0"  src="warningAccountManage.jsp" style="width:100%;height:100%;"></iframe>',
//						closable:true
						
//					}
///				});				
//			}
			$('#rcenter').tabs('select', title);
		} 
		else
		{
		
			var tab = $('#rcenter').tabs('add',{
				title:title,
				iconCls:iconClass,
				content:'<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
				closable:true
				
			});	
		}
	}
	
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:100px; border-bottom:5px solid #F7F7F7;position:relative; background:url(../img_gray/images/GRAY_04.jpg) repeat-x; overflow:hidden;">
  <span  style="position:absolute; display:block; width:500px; height:94px; top:0; left:2%; font-size: 30px" >内网安全运维管控平台</span>
  <i style="; display:block; width:440px; height:92px; position:absolute; right:1%; top:0;"></i>
  <a href="logout.action" style="CURSOR: pointer" class="exit-logined" onclick="javascript:if(confirm('确认退出系统吗？')){window.top.location='adminlogout.action'}" style="background:url(../img/exit.png) no-repeat right center; padding-right:15px;" >退出登录</a>
  <span  style="background:url(../img/admin.png) no-repeat left center; position:absolute; right:2%; bottom:45px;color:#646464; padding-left:20px;">管理员：<b style="margin-right:5px; color:#097BBA">${session.user.username }</b>您好，欢迎登录使用！</span>
		<ul class="linkOther">
			  	<%if(session.getAttribute("user_warningMonitor")!=null){ %>
		    	<li><a href="/AIO/warning/index.jsp">监测预警</a></li>
		    	<%} %>
		    	<%if(session.getAttribute("user_netSecurity")!=null){ %>
		    	<li><a href="/network/mainframe.jsp?val=1">网络安全</a></li>	
		    	<%} %>
		    	<%if(session.getAttribute("adminuser")!=null){ %>
		    	 <li><a href="/admin/full.jsp">超级管理员</a></li>	
		    	<%} %>
		    	<%if(session.getAttribute("user_operation_admin")!=null){ %>
		    	<li><a href="/AIO/operation/full1.jsp?val=1">运维管理</a></li>	
		    	<%} %>
		    	<%if(session.getAttribute("user_operation")!=null){ %>
		    	<li><a href="/AIO/operation/full.jsp?val=1">运维服务</a></li>	
		    	<%} %>
			  	<%if(session.getAttribute("user_audit")!=null){ %>
		    	<li><a href="/log/mainframe.jsp?val=0">系统审计</a></li>	
		    	<%} %>
		  	</ul>
    </div>
</div>

<div data-options="region:'west',split:true,title:'管理菜单'" style="width:185px; overflow:auto;">
  <ul class="easyui-tree" id="tree">
  
    <li iconCls="icon-ytj-jk"> <span>内网资源监控</span>
      <ul>
     
      <%if(userType==0){ %>
        <li iconCls="icon-light"><span><a url="/supervision/LabOnlineData.jsp"  iconCls="icon-light">内网资源统计信息</a></span></li>
      	<li iconCls="icon-user_error"><span><a url="/supervision/leaderManage.jsp" iconCls="icon-user_error">组长管理</a></span></li> 
      <%} %> 
      <%if(userType<2){ %>
      <li iconCls="icon-VPN-jk"><span><a url="/supervision/groupManage.jsp" iconCls="icon-VPN-jk">小组管理</a></span></li>
      <%} %>
       
        <li iconCls="icon-fixPwd"><span><a url="/supervision/editPassword.jsp">修改密码</a></span></li>
<!--        <li iconCls="icon-shebei"><span><a url="deviceManage.jsp" iconCls="icon-shebei">设备管理</a></span></li>-->
      </ul>
    </li>
    
	 <li iconCls="icon-sysFolder"> <span>分布式计算平台</span>
	  <ul>
		<li iconCls="icon-sysLoad"><span><A style="CURSOR: pointer" url="/compute/uploadFile.jsp" iconCls="icon-sysLoad">任务上传</A></span></li>
	  </ul>
	</li>   
  </ul>
</div>
<div data-options="region:'south',border:false" style="height:41px; border-top:5px solid #F7F7F7; line-height:41px; color:#646464; text-align:center;background:url(../img_gray/images/GRAY_08.jpg) repeat-x;">内网安全信息化安全运维管控平台</div>
<div region="center">
  <div  id="rcenter" class="easyui-tabs" fit="true">
    <!-- 取消了自动显示 -->
    
  </div>
</div>
<!--<div data-options="region:'center'">
    <div id="rcenter" class="easyui-tabs" fit="true">  
        <div title="Tab1" style="height:inherit">  
            <iframe scrolling="yes" frameborder="0"  src="http://chinaunix.com" style=" width:100%; height:100%;"></iframe>
        </div>  
        <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">  
            tab2  
        </div>  
        <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">  
            tab3  
        </div>
    </div>  
</div>-->
</body>
</html>
