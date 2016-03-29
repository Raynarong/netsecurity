<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>实验室用户信息统计</title>
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>

<script type="text/javascript" src="lib/OpenCharts.js"></script>
<link rel="stylesheet" href="lib/style.css" type="text/css">

<script language="javascript">


$(function(){
	var $grid = $('#operation_log_grid');
	var wid = $(this).width();
    $grid.datagrid({
        fit: true,
		fitColumns: true,
        idField: 'tools',
        url: "/commonUser/showUserComputerTopStatus.action",
        pagination: true,
		pageSize: 10,
		rownumbers:true,
		singleSelect:true,
		remoteSort:false,
		
		sortName:"start_time",
		sortOrder:"desc",
             toolbar:[{
			text:'返回',
			iconCls:'icon-back',
			disabled:true,
			handler:function(){}
		},{
			text:'首页',
			iconCls:'icon-application',
			disabled:true,
			handler:function(){}
		},{
			text:'刷新',
			iconCls:'icon-reload',
			disabled:false,
			handler:function(){$('#operation_log_grid').datagrid('load');}
		},{
			text:'添加',
			iconCls:'icon-add',
			disabled:true,
			handler:function(){}
		},{
			text:'帮助',
			iconCls:'icon-help',
			disabled:false,
			handler:function(){
				$('#dlg2').dialog({
					title: '帮助',
					width:300,
					height:100
				});
			}
		}],
	   columns: [[
                /*{checkbox: true},*/
                {field: 'opera', title: '行为名称',width:wid * 0.2,},
				{field: 'timeTop1', title: '最高频段1',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2},
				{field: 'top1Value', title: '频段1数值',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2},	
				{field: 'timeTop2', title: '最高频段2',formatter:function(value){
					return '<span class="orange">'+value+'</span>';},width:wid * 0.2},
				{field: 'top2Value', title: '频段2数值',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2},	
				{field: 'timeTop3', title: '最高频段3',formatter:function(value){
					return '<span class="orange">'+value+'</span>';},width:wid * 0.2},
				{field: 'top3Value', title: '频段3数值',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2},	
				{field: 'timeTop4', title: '最高频段4',formatter:function(value){
					return '<span class="orange">'+value+'</span>';},width:wid * 0.2},
				{field: 'top4Value', title: '频段4数值',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2}
            ]]
    });
 
	$("dt input").click(function(){
		if ($(this).attr("checked"))
		{
			$(this).parents("dt").nextAll("dd").show();			
			export_excel();
		}
		else
		{
			$(this).parents("dt").nextAll("dd").hide();
			export_excel();
		}
	});
	
});
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}


//绑定确定按钮
$(function(){
	$('#bt_user_search_btn').click(function(){
		var startTime = $('#startTime').datebox("getValue");
		var endTime = $('#endTime').datebox("getValue");
		
		var arr = {'startTime':startTime,'endTime':endTime};
		$('#operation_log_grid').datagrid('options').queryParams=arr;
		$('#operation_log_grid').datagrid('load');
	})
})
</script>
</head>
<body>
 
<div class="easyui-layout" fit="true">
  <div region="north" style="border-bottom: none;" border="false" >
    <div class="vpn-hd">&nbsp; 
<span class="tableName"><strong><%--=cityName--%>实验室统计信息</strong></span>
  </div>
   <div class="easyui-panel"  fit="true" border="false">
    <form id="bt_user_search_from" style="height:50px; overflow:hidden; margin:0;" method="post" ">
      <table style="height:100%;margin-left:22px;" >
        <tr>
          <td><strong>电脑状态搜索：</strong></td>
          <td class="seaCondi icon-time">时间</td>
		  <td colspan="4"><input id="startTime" class="easyui-datebox"  value="" data-options="formatter:myformatter">—<input id="endTime" class="easyui-datebox"  value="" data-options="formatter:myformatter">
          </td>
          <td><a href="#" class="blue-btn sear-btn" id="bt_user_search_btn">查询</a></td>
        </tr>
      </table>
    </form>
    </div>
  </div>
  <div region="center" border="false">
    <table id="operation_log_grid" >
    </table>
  </div>
</div>

<div id="dlg" style="padding:10px; text-align:center">
	<div style="margin-left:auto; margin-right:auto;">
		<canvas id="canvas" width="800px" height="400px" />
	</div>
</div>



</body>
</html>

