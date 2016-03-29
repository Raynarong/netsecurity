<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/c" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>电脑信息统计</title>
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>
<script type="text/javascript" src="lib/highcharts.js"></script>
<script type="text/javascript" src="lib/exporting.js"></script>
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
        url: 'showComputersOnLineData.action?userId=${session.ComputerStatusUserId}',
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
				window.location = "showUsersOnLinePage.action?labId=${session.UserLabId}";
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
                
				{field: 'sn',title:'sn',formatter:function(value,row,index){
					return value+'<a href="#" onclick="openLineChart1(\'dlg\', \''+value+'用户行为统计\', \'dlg-toolbar\', \'#00C000\', \'canvas\',\''+row.sn+'\');"><img title="电脑状态线图" width="16" height="16" style="margin-left:5px;" border="0" src="enterUploadPage_files/line.png"></a>';
				},width:wid*0.2},
				{field: 'online', title: '在线情况',formatter:function(value){
					if(value==true)
						return '<span class="blue"> 在线 </span>';
               		else
               			return '<span class="blue"> 离线 </span>';
               			},width:wid * 0.2
               	}
            	
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
Date.prototype.Format = function(formatStr)
{
var str = formatStr;
var Week = ['日','一','二','三','四','五','六'];

str=str.replace(/yyyy|YYYY/,this.getFullYear());
str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());
str=str.replace(/M/g,this.getMonth());

str=str.replace(/w|W/g,Week[this.getDay()]);

str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
str=str.replace(/d|D/g,this.getDate());

str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
str=str.replace(/h|H/g,this.getHours());
str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
str=str.replace(/m/g,this.getMinutes());

str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
str=str.replace(/s|S/g,this.getSeconds());

return str;
}

function change(){
  var checkValue=$('#timeType').val();
  if(checkValue==0){
   trafficChange();
}
else{
   trafficChange1();}
}


//显示流量面板
function openLineChart1(id, title, toolbar_id, line_color, canvas,sn)
{
	$('#sn').attr('value',sn);

	$('#'+id).dialog({
		title: title,
		width:900,
		height:500,
		toolbar: '#'+toolbar_id
	});
	var timeType=$('#timeType').val();
	var statusType=$('#statusType').val();
	var unit=$('#unit').val();
	var url='showComputersOnLineStatus.action';
		
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	if(month<10)
		month='0'+month;
	var day=now.getDate();
	if(day<10)
		day='0'+day;
	var nowday=year+'-'+(month)+'-'+day;
	
	
	var lastmonth=getLastMonthYestdy(now);

	$('#start_time').datebox('setValue',lastmonth);
	$('#end_time').datebox('setValue',nowday);
	
	var parameters={
		'sn':sn,
		'timeType':timeType,
		'statusType':statusType,
		'unit':unit,
		'start':lastmonth+' 00:00:00',
		'end':nowday+' 23:59:59'
	}
	$.post(url,parameters,callTrafficDateBack,'html');

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
		'type':date
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
		var timeType=$('#timeType').attr('value');
		var unit=$('#unit').attr('value');
		var statusType=$('#statusType').val();
		var url='showComputersOnLineStatus.action';
		var sn=$('#sn').attr('value');
		var start = trim($("#start_time").datebox('getValue'));
		start=start+" 00:00:00";
		var end = trim($("#end_time").datebox('getValue'));
		end=end+" 23:59:59";
		var parameters={
			'sn':sn,
			'timeType':timeType,
			'statusType':statusType,
			'unit':unit,
			'start':start,
			'end':end
		};
		if($('#timeType').val()==0){
		 $('#dayinterval').show();
		 $('#day').hide();
		 $('#container').hide();
		 $('#canvas').show();
		 
		}
		else{
		$('#dayinterval').hide();
		$('#day').show();
		$('#container').show();
		$('#canvas').hide();
		}
		if($('#statusType').val()==7||$('#statusType').attr('value')==8)
			$('#unit').show();
		else
			$('#unit').hide();
		$.post(url,parameters,callTrafficDateBack);
}


   function trafficChange1(){

		var timeType=$('#timeType').attr('value');
		var unit=$('#unit1').attr('value');
		var statusType=$('#statusType1').val();
		var url='showComputersOnLineStatusByDay.action';
		var sn=$('#sn').attr('value');
		var start = trim($("#time").datebox('getValue'));
		
		
		if($('#timeType').val()==0){
		 $('#dayinterval').show();
		 $('#day').hide();
		 $('#container').hide();
		 $('#canvas').show();
		}
		else{
		$('#dayinterval').hide();
		 $('#day').show();
		 $('#container').show();
		 $('#canvas').hide();
		}
		if($('#statusType1').val()==7||$('#statusType1').attr('value')==8)
			$('#unit1').show();
		else
			$('#unit1').hide();
		var parameters={
			'sn':sn,
			'timeType':timeType,
			'statusType':statusType,
			'unit':unit,
			'start':start
			
		};
		$.post(url,parameters,highChartsData,'html');
}
function trim(str){
		return str.replace(/^\s*|\s*$/g,'');
	}
	
	
function highChartsData(value){
    value=eval("("+value+")");
    var start = trim($("#time").datebox('getValue'));
    var statusType=$('#statusType1 option:selected').text()
    var beginDate=start.split("-");
    var y=beginDate[0];
    var m=beginDate[1];
    var d=beginDate[2];
    var title=statusType+":"+start;
    highCharts(value,title,y,m,d,statusType);
	}
	
	
function highCharts(data,date,y,m,d,statusType) {
	
    $('#container').highcharts({
        chart: {
            zoomType: 'x',
            spacingRight: 20
        },
        title: {
            text: date
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                'Click and drag in the plot area to zoom in' :
                'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime',
            maxZoom: 10* 60000, // 十分钟
            title: {
                text: null
            }
        },
        yAxis: {
            title: {
                text: statusType
            }
        },
        tooltip: {
            shared: true
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },
          
       
        series: [{
            type: 'area',
            name: statusType,
            pointInterval: 60*1000,
            pointStart: Date.UTC(y,m-1,d),
            data:data
            }]
    });
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
		var start = trim($("#start_time").datebox('getValue'));
		start=start+" 00:00:00";
		var end = trim($("#end_time").datebox('getValue'));
		end=end+" 23:59:59";
		var parameters={
			'parentLevel':parentLevel,
			'parentId':parentId,
			'type':date
		}
		
		 
		$.post(url,parameters,callOnlineTimeDateBack,'html');
}

//修改日期格式
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
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

//绑定确定按钮
$(function(){
	$('#querynow').click(function(){
	trafficChange();
	})
}
)

//绑定确定按钮1
$(function(){
	$('#querynow1').click(function(){
	trafficChange1();
	})
}
)

//初始化开始，结束时间,并查询
$(function(){
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	
	if(month<10)
		month='0'+month;
		
	var day=now.getDate();
	
	var nowday=year+'-'+month+'-'+day;
	var lastmonth=getLastMonthYestdy(now);
	$('#start_time').datebox('setValue',lastmonth);
	$('#end_time').datebox('setValue',nowday);
	$('#time').datebox('setValue',nowday);
	
}
)
 //获得上个月这一天的日期   
  function getLastMonthYestdy(date){   
     var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);   
     var strYear = date.getFullYear();     
     var strDay = date.getDate();     
     var strMonth = date.getMonth()+1;   
     if(strYear%4 == 0 && strYear%100 != 0){   
        daysInMonth[2] = 29;   
     }   
     if(strMonth - 1 == 0)   
     {   
        strYear -= 1;   
        strMonth = 12;   
     }   
     else  
     {   
        strMonth -= 1;   
     }   
     strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];   
     if(strMonth<10)     
     {     
        strMonth="0"+strMonth;     
     }   
     if(strDay<10)     
     {     
        strDay="0"+strDay;     
     }   
     datastr = strYear+"-"+strMonth+"-"+strDay;   
     return datastr;   
  }   


</script>
</head>
<body>
<div class="easyui-layout" fit="true">
  <div region="north" style="border-bottom: none;" >
  <div class="vpn-hd"> &nbsp;  
<!--  	 <span class="welcomeAdm">管理员：<b>${session.user.username }</b>您好，欢迎登陆使用！<a href="logout.action" class="exitLo">退出登陆</a></span>-->
     <span class="tableName"><strong>用户电脑信息统计</strong></span>
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
	<div id="container" style="min-width:700px;height=400px" ></div>
</div>
<div id="dlg-toolbar" style="padding:2px 0">
	<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
		<ipput type="hidden" id="sn"></input>
		<ipput type="hidden" id="trafficParentId"></input>
			<td style="padding-left:2px ; width:120px">
				
				 <select name="timeType" id="timeType" onchange="change()">
				  <option value="0">时间段查询</option>
				  <option value="1">当前日期查询</option>
				  </select>
			</td>
	
				<!--  <select name="timeType" id="timeType" onchange="trafficChange()">
				  <option value="0">day</option>
				  <option value="1">week</option>
				  <option value="2">month</option>

				</select>-->
				<td id="dayinterval">
				<input id="start_time" name="start_time" type="text" class="easyui-datebox" data-options="formatter:myformatter" value=""></input> 
				<input id="end_time" name="end_time" type="text" class="easyui-datebox" data-options="formatter:myformatter" value=""></input>
				
				<select name="statusType" id="statusType" onchange="trafficChange()">
				  <option value="0">内存利用率</option>
				  <option value="1">cpu利用率</option>
				  <option value="2">鼠标左键点击数</option>
				  <option value="3">鼠标右键点击数</option>
				  <option value="4">键盘敲击数</option>
				  <option value="5">进程数</option>
				  <option value="6">任务数 </option>
				  <option value="7">上行流量</option>
				  <option value="8">下行流量</option>
				</select>
				<select name="unit" id="unit" onchange="trafficChange()" style="display:none">
				  <option value="0">单位：K</option>
				  <option value="1">单位：M</option>
				  <option value="2">单位：G</option>
				  <option value="3">单位：T</option>
				</select>					
				<button id="querynow">确定</button>			
			</td>
				</td>
				
				<td id="day"  style="display:none">
				<input id="time" name="time" type="text" class="easyui-datebox" data-options="formatter:myformatter" value=""  ></input> 
				<select name="statusType1" id="statusType1" onchange="trafficChange1()">
				  <option value="0">内存利用率</option>
				  <option value="1">cpu利用率</option>
				  <option value="2">鼠标左键点击数</option>
				  <option value="3">鼠标右键点击数</option>
				  <option value="4">键盘敲击数</option>
				  <option value="5">进程数</option>
				  <option value="6">任务数 </option>
				  <option value="7">上行流量</option>
				  <option value="8">下行流量</option>
				</select>
				<select name="unit1" id="unit1" onchange="trafficChange1()"  style="display:none">
				  <option value="0">单位：K</option>
				  <option value="1">单位：M</option>
				  <option value="2">单位：G</option>
				  <option value="3">单位：T</option>
				</select>				
				<button id="querynow1">确定</button>
				
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
		<span><img width="16" height="16" border="0" src="enterUploadPage_files/line.png">-趋势线图</span>
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

