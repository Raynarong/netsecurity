<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/c" prefix="c" %>

<%--获取县名 --%>
<%String countyName=null; %>
<%String countyNameSql="select countyname from countyinfo where countyinfo.countyid="+parentAreaId;%>


<%countyName=dbconnection.getString(countyNameSql,null);%>

<%--获取县在线 离线 数 --%>
<%String offlineSql="select sum(case when online_state=0 then 1 else 0 end) from onlinedataview where countyid="+parentAreaId;%>
<%int offline=dbconnection.getNumber(offlineSql,null);%>
<%String onlineSql="select sum(case when online_state=1 then 1 else 0 end) from onlinedataview where countyid="+parentAreaId;%>
<%int online=dbconnection.getNumber(onlineSql,null);%>
<%--获取镇列表 --%>
<%List<Towninfo> townList=dbconnection.getTownList(parentAreaId); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>区域卫生信息化安全运维管控平台</title>
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>

<script type="text/javascript" src="lib/OpenCharts.js"></script>
<link rel="stylesheet" href="lib/style.css" type="text/css">

<script language="javascript">

//context.ready = function() {
$(function(){
	var $grid = $('#operation_log_grid');
	var wid = $(this).width();
    $grid.datagrid({
        fit: true,
		fitColumns: true,
        idField: 'tools',
        url: 'showAreaData.action?parentLevel=<%=parentAreaLevel%>&parentId=<%=parentAreaId%>',
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
			disabled:false,
			handler:function(){
				window.location = "CountyOnlineData.jsp";
			}
		},{
			text:'首页',
			iconCls:'icon-application',
			disabled:true,
			handler:function(){}
		},{
			text:'刷新',
			iconCls:'icon-reload',
			disabled:false,
			handler:function(){}
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
                {field: 'areaName', title: '乡镇',width:wid * 0.2,formatter:function(value,row,index){
					return '<a href="showDoctorList.action?parentLevel='+row.areaLevel+'&parentId='+row.areaId+'">'+value+'</a>'+'<a href="#" onclick="openLineChart1(\'dlg\', \''+value+'村医总流量统计\', \'dlg-toolbar\', \'#00C000\', \'canvas\','+row.areaLevel+','+row.areaId+');"><img title="流量线图" width="16" height="16" style="margin-left:5px;" border="0" src="enterUploadPage_files/line.png"></a><a href="#" onclick="openLineChart2(\'dlg1\', \''+value+'村医在线时常统计\', \'dlg-toolbar1\', \'#FFC000\', \'canvas1\','+row.areaLevel+','+row.areaId+');"><img title="在线时间线图" width="16" height="16" style="margin-left:5px;" border="0" src="enterUploadPage_files/line1.png"></a>';}, sortable: true},
				{field: 'areaLevel',hidden:true},
				{field: 'areaId',hidden:true},
				{field: 'allNum', title: '总村医',formatter:function(value){
					return '<span class="blue">'+value+'</span>';},width:wid * 0.2},
				{field: 'onlineNum', title: '实时在线村医',formatter:function(value){
					return '<span class="green2">'+value+'</span>';},width:wid * 0.2},
				{field: 'oflineNum', title: '离线村医',formatter:function(value){
					return '<span class="orange">'+value+'</span>';},width:wid * 0.2},
				{field: 'distribute', title: '操作', width:wid * 0.2, formatter: function(value) {
						return 	'<span title="导出报表" class="img-btn icon-save" onClick="export_excel();"></span><a  onClick="export_excel()" style="cursor:pointer">报表</a>';
				}}
               
            ]]        
    });
	
	$("#sel").change(function(){
		var json = random_json(parseInt($(this).val()));
		linechart_init(json, "#00C000", "canvas");
	});
	
	$("#sel1").change(function(){
		var json = random_json(parseInt($("#sel").val()));
		linechart_init(json, "#00C000", "canvas");
	});
	
	$("#sel2").change(function(){
		var json = random_json(parseInt($("#sel").val()));
		linechart_init(json, "#00C000", "canvas");
	});
	
	$("#sel3").change(function(){
		var json = random_json(parseInt($("#sel").val()));
		linechart_init(json, "#00C000", "canvas");
	});
	
	$("#sel4").change(function(){
		var json = random_json(parseInt($("#sel4").val()));
		linechart_init(json, "#FFC000", "canvas1");
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


function export_excel()
{
	$('#dlg3').dialog({
		title: "导出报表",
		width:450
	});
}



//显示流量面板
function openLineChart1(id, title, toolbar_id, line_color, canvas,parentLevel,parentId)
{
	$('#trafficParentLevel').attr('value',parentLevel);
	$('#trafficParentId').attr('value',parentId);
	
	$('#'+id).dialog({
		title: title,
		width:900,
		height:500,
		toolbar: '#'+toolbar_id
	});
	var type=$('#type').val();
	var vpn=$('#vpn').val();
	var unit=$('#unit').val();
	var url='getTrafficTrend.action';
	var up=$('#up').val();
	var parameters={
		'parentLevel':parentLevel,
		'parentId':parentId,
		'type':type,
		'vpn':vpn,
		'unit':unit,
		'up':up
	}
	$.post(url,parameters,callTrafficDateBack);
}




//显示在线时间面板
function openLineChart2(id, title, toolbar_id, line_color, canvas,parentLevel,parentId)
{
	$('#parentLevel').attr('value',parentLevel);
	$('#parentId').attr('value',parentId);
	
	$('#'+id).dialog({
		title: title,
		width:900,
		height:500,
		toolbar: '#'+toolbar_id
	});
	var onlineTimeDate=$('#onlineTimeDate');
	var date=onlineTimeDate.val();
	var url='getOnlineTime.action';

	var parameters={
		'parentLevel':parentLevel,
		'parentId':parentId,
		'type':date,
	}
	$.post(url,parameters,callOnlineTimeDateBack);
}

//画医生在线时间图
function callOnlineTimeDateBack(value){
	value=eval("("+value+")");
	line_color='#FFC000';
	canvas='canvas1';
	linechart_init(value, line_color, canvas);
}

function linechart_init(json, line_color, canvas)
{
	var items = [];
	var k = 0;	
	for (k in json.items)
	{	
		items.push(new OpenCharts.Item(json.items[k].data, k%2 ? json.items[k].time : "", line_color));	
	}

	charts = new OpenCharts.Chart.AreaChart(canvas);
	charts.strTitle = json.title;
	charts.showLegend(false);
	
	charts.addItems(items,"");
	charts.render();
}

//流量图select函数绑定函数
function trafficChange(){
		var type=$('#type').attr('value');
		var vpn=$('#vpn').attr('value');
		var up=$('#up').attr('value');
		var unit=$('#unit').attr('value');
		
		var url='getTrafficTrend.action';
		var parentLevel=$('#trafficParentLevel').attr('value');
		var parentId=$('#trafficParentId').attr('value');
		var parameters={
			'parentLevel':parentLevel,
			'parentId':parentId,
			'type':type,
			'vpn':vpn,
			'up':up,
			'unit':unit
		};
		$.post(url,parameters,callTrafficDateBack);
}
//画流量图
function callTrafficDateBack(value){
	value=eval("("+value+")");
	line_color='#00C000';
	canvas='canvas';
	linechart_init(value, line_color, canvas);
}
//在线时间图select函数绑定函数
function onlineTimeDateChange(){
		var date=$('#onlineTimeDate').attr('value');
		var url='getOnlineTime.action';
		var parentLevel=$('#parentLevel').attr('value');
		var parentId=$('#parentId').attr('value');
		var parameters={
			'parentLevel':parentLevel,
			'parentId':parentId,
			'type':date
		}
		
		 
		$.post(url,parameters,callOnlineTimeDateBack);
}
function callTownback(result,textStatus){  
    var sel2 = $('#town');  
    sel2.empty();  
    sel2.append("<option value=\"-1\" name=\"allTown\" id=\"allTown\">-请选择-</option>");
    var towns=eval(result);
    var town;
     for(var i=0;i<towns.length;i++)
    {
    	 town=towns[i];
    	 sel2.append("<option value = '"+town.townid+"'>"+town.townname+"</option>"); 
    } 
} 

function checkPreviewExcelOpt(){
	//开机时间输入验证
	//开机时长输入验证
	if($('#startUpCheck').is(":checked"))
	{
		var date = $.trim($("#startup_time_data_red").val());
         var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))(\s(([01]\d{1})|(2[0123])):([0-5]\d):([0-5]\d))?$/;

         if (!reg.test(date)) {
             alert("请输入正确的时间（格式: 2008-08-08 20:20:03）");
             return false;
         }
		 
		if(!(/^(\+|-)?\d+$/.test($('#online_time_data_red').attr("value") ))){
		    alert("开机时长必须是正整数！");   
		    return false
			 ;  
		}
	}
	else
	{
		$('#startup_timeC_red').attr("value",0);
		$('#online_timeC_red').attr("value",0);
	}
	//验证vpnRed流量
	if($('#vpnRedCheck').is(':checked'))
	{
		if (!(/^(\+|-)?\d+$/.test($('#vpn_flow_data_red').attr("value")))) {
		 	alert("vpn流量（标红条件）必须是正整数！");   
		    return false
		}
	}else
	{
		$('#vpn_C_red').attr('value',0);
		return false;
	}
	//验证internetRed流量 
	if($('#internetRedCheck').is(':checked')){
		if (!(/^(\+|-)?\d+$/.test($('#internet_flow_data_red').attr("value")))) {
		 	alert("上网流量（标红条件）必须是正整数！");   
		    return false
		}
	}else
	{
		$('#internet_flow_C_red').attr('value',0);
		return false;
	}
	
	//验证vpn流量
	if($('#vpnCheck').is(':checked'))
	{
		if (!(/^(\+|-)?\d+$/.test($('#vpn_flow_data').attr("value")))) {
		 	alert("vpn流量必须是正整数！");   
		    return false
		}
	}else
	{
		$('#vpn_C').attr('value',0);
		return false;
	}
	//验证internet流量 
	if($('#internetCheck').is(':checked')){
		if (!(/^(\+|-)?\d+$/.test($('#internet_flow_data').attr("value")))) {
		 	alert("上网流量必须是正整数！");   
		    return false
		}
	}else
	{
		$('#internet_flow_C').attr('value',0);
		return false;
	}
	
	return   true;
}

function previewExcelSubmit(){
	if(checkPreviewExcelOpt()==true)
	$('#previewExcelForm').submit();
}
</script>
</head>
<body>
<div class="easyui-layout" fit="true">
  <div region="north" style="border-bottom: none;" >
  <div class="vpn-hd">t &nbsp;  
  	 <span class="welcomeAdm">管理员：<b>${session.user.username }</b>您好，欢迎登陆使用！<a href="logout.action" class="exitLo">退出登陆</a><a href="changePwd.jsp" class="fixP">修改密码</a></span>
     <span class="tableName"><strong><%--=countyName --%>村医统计</strong>(总村医<b><%--=online+offline --%></b>位，实时在线村医<b><%--=online --%></b>位，离线村医<b><%--=offline --%></b>位)</span>
  </div>
   <div class="easyui-panel" fit="true" border="false">    
    <form id="bt_user_search_from" style="height:50px; overflow:hidden; margin:0;">
      <table style="height:50px; margin-left:22px;" >
        <tr>
          <td><strong>村医搜索：</strong></td>
		  <td class="seaCondi icon-Company">所在地：</td>
		  <td>
            <select class="town">
              <option value="-1">-选择-</option>
              <%--for(Towninfo t:townList){ --%>
              <option value="<%--=t.getTownid()--%>"><%--=t.getTownname() --%></option>
              <%--} --%>
            </select>
          </td>
		  <td class="seaCondi icon-shebei-sea">SN号：</td>
          <td><input name="" type="text"></td>
          <td><a href="" class="blue-btn sear-btn" id="bt_user_search_btn">查询</a></td>
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
<div id="dlg-toolbar" style="padding:2px 0">
	<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
		<ipput type="hidden" id="trafficParentLevel"></input>
		<ipput type="hidden" id="trafficParentId"></input>
			<td style="padding-left:2px">
				<select name="type" id="type" onchange="trafficChange()">
				  <option value="1">上月</option>
				  <option value="2">本月</option>
				  <option value="3">上周</option>
				  <option value="4">本周</option>
				</select>
				<select name="vpn" id="vpn" onchange="trafficChange()">
				  <option value="true">VPN流量</option>
				  <option value="false">上网流量</option>
				</select>
				<select name="up" id="up" onchange="trafficChange()">
				  <option value="true">上行</option>
				  <option value="false">下行</option>
				</select>
				<select name="unit" id="unit" onchange="trafficChange()">
				  <option value="0">单位：K</option>
				  <option value="1">单位：M</option>
				  <option value="2">单位：G</option>
				  <option value="3">单位：T</option>
				</select>				
			</td>
		</tr>
	</table>
</div>
<inpute type="hidden" id="onlinetimeData"></inpute>
<div id="dlg1" style="padding:10px; text-align:center">
	<div style="margin-left:auto; margin-right:auto;">
		<canvas id="canvas1" width="800px" height="400px" />
	</div>
</div>
<div id="dlg-toolbar1" style="padding:2px 0">
	<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td style="padding-left:2px">
				<select name="onlineTImeDate" id="onlineTimeDate" onchange="onlineTimeDateChange()">
				  <option value="7">上月</option>
				  <option value="8">本月</option>
				  <option value="9">上周</option>
				  <option value="10">本周</option>
				</select>
			</td>
		</tr>
	</table>
</div>

<div id="dlg2" style="padding:10px; text-align:center">
	<div>
		<span><img width="16" height="16" border="0" src="enterUploadPage_files/line.png">-流量线图</span>
		<span style="margin-left:20px"><img width="16" height="16" border="0" src="enterUploadPage_files/line1.png">-在线时间线图</span>
	</div>
</div>

<div id="dlg3" style="padding:10px; text-align:center;">	
	<form id="previewExcelForm" name="previewExcelForm" action="previewExcelOption.action" method="post">
	<input type="hidden" id="exareaLevel" name="areaLevel"></input>
	<input type="hidden" id="exareaId" name="areaId"></input>
    <div class="filter-condi prominent">
    	<strong>数据标红条件</strong>
        <dl>
        	<dt>开机时间及时长：<span style="vertical-align:middle"><input name="startUpCheck" id="startUpCheck" type="checkbox" checked="checked" value=""></span></dt>
            <dd>每天开机时间<span><select name="startup_timeC_red" id="startup_timeC_red" onChange="">
				  <option value="1">大于</option>
				  <option value="2">小于</option>				  
				</select></span><input id="startup_time_data_red" name="startup_time_data_red" type="text"></dd>
            <dd>开机时长<span><select name="online_timeC_red" id="online_timeC_red" onChange="">
				  <option value="1">大于</option>
				  <option value="2" selected="selected">小于</option>				  
				</select></span><input name="online_time_data_red" id="online_time_data_red" type="text"></dd>
        </dl>
        <dl>
        	<dt>VPN流量(每天)：<span style="vertical-align:middle"><input name="vpnRedCheck" id="vpnRedCheck" type="checkbox" checked="checked" value=""></span></dt>
            <dd>                
                    <select name="vpn_type_red" id="vpn_type_red" onChange="">
                      <option value="1">总流量</option>
                      <option value="2">上行</option>
                      <option value="3">下行</option>
                    </select>
                    <span><select name="vpn_C_red" id="vpn_C_red" onChange="">
				  <option value="1">大于</option>
				  <option value="2">小于</option>				  
				</select></span><input name="vpn_flow_data_red" id="vpn_flow_data_red" type="text">M
             </dd>
        </dl>
         <dl>
        	<dt>上网流量(每天)：<span style="vertical-align:middle"><input name="internetRedCheck" id="internetRedCheck" type="checkbox" checked="checked" value=""></span></dt>
            <dd>
                <select name="internet_type_red" id="internet_type_red" onChange="">
				  <option value="1">总流量</option>
				  <option value="2">上行</option>
				  <option value="3">下行</option>
				</select>
                <span><select name="internet_flow_C_red" id="internet_flow_C_red" onChange="">
				  <option value="1">大于</option>
				  <option value="2">小于</option>				  
				</select></span><input name="internet_flow_data_red" id="internet_flow_data_red" type="text">
                </dd>
        </dl>
    </div>
    
    <div class="filter-condi filerr">    
      <strong>数据过滤条件(满足条件不显示)</strong>
      <dl>
      	<dt>VPN流量：<span style="vertical-align:middle"><input name="vpnCheck" id="vpnCheck" type="checkbox" checked="checked" value=""></span></dt>
        <dd>          
            <select name="vpn_type" id="vpn_type" onChange="">
              <option value="1">总流量</option>
              <option value="2">上行</option>
              <option value="3">下行</option>
            </select>
            <span><select name="vpn_C" id="vpn_C" onChange="">
				  <option value="1">大于</option>
				  <option value="2">小于</option>				  
				</select></span><input name="vpn_flow_data" id="vpn_flow_data" type="text">G
         
        </dd>
      </dl>      
      <dl>
        	<dt>上网流量：<span style="vertical-align:middle"><input name="internetCheck" id="internetCheck" type="checkbox" checked="checked" value=""></span></dt>
            <dd>
                <select name="internet_type" id="internet_type" onChange="">
				  <option value="1">总流量</option>
				  <option value="2">上行</option>
				  <option value="3">下行</option>
				</select>
				<span><select name="internet_flow_C" id="internet_flow_C" onChange="">
				  <option value="1">大于</option>
				  <option value="2">小于</option>				  
				</select>
				</span><input name="internet_flow_data" id="internet_flow_data" type="text">G</dd>
        </dl>
    </div>
    <div class="filter-condi time-part">    
    <input id="parentLevel" type="hidden"></input>
    <input id="parentId" type="hidden"></input>
       <p><strong>时间段</strong>
       <select name="date" id="date" onChange="">
              <option value="1">上月</option>
              <option value="2">本月</option>
              <option value="3">上周</option>
              <option value="4">本周</option>
              <option value="5">全部</option>
            </select></p>
      </dl>
    </div>
    
    <p><a id="bt_user_search_btn" class="blue-btn view-btn" href="javascript:previewExcelSubmit()"></a></p>
    </form>

</div>
</body>
</html>

