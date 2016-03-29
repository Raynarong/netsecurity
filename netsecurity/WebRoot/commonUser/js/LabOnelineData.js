//显示流量面板
function openLineChart1(id, title, toolbar_id, line_color, canvas,doctorid)
{

	$('#'+id).dialog({
		title: title,
		width:900,
		height:500,
		toolbar: '#'+toolbar_id
	});
	var type=$('#type').val();
	var vpn=$('#vpn').val();
	var unit=$('#unit').val();
	var url='getDocTrafficTrend.action';
	var up=$('#up').val();
	var parameters={
		'doctorid':doctorid,
		'type':type,
		'vpn':vpn,
		'unit':unit,
		'up':up
	}
	$.post(url,parameters,callTrafficDateBack);
}

//流量图select函数绑定函数
function trafficChange(){
		var type=$('#type').attr('value');
		var vpn=$('#vpn').attr('value');
		var up=$('#up').attr('value');
		var unit=$('#unit').attr('value');
		
		var url='getDocTrafficTrend.action';
		var doctorid=$('#doctoridDring').attr('value');
		var parameters={
			'doctorid':doctorid,
			'type':type,
			'vpn':vpn,
			'up':up,
			'unit':unit
		};
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
//在线时间图select函数绑定函数
function onlineTimeDateChange(){
		var date=$('#onlineTimeDate').attr('value');
		var url='getDocOnlineTime.action';
		var doctorid=$('#doctoridDring').attr('value');
		var parameters={
			'doctorid':doctorid,
			'type':date
		}
		
		 
		$.post(url,parameters,callOnlineTimeDateBack);
}

//context.ready = function() {
$(function(){	
	$("#city").change(function(){
       $("#city option").each(function(i,o){
           if($(this).attr("selected"))
           {
          
               $(".town").hide();
               $(".town").eq(i).show();
           }
       });
   });
   $("#city").change();
});

function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}

$(function(){
	$('#captureBtn').click(function(){
		captureDesk();
	})
})	
$(function(){
	$(".count_table a").click(function(){
		
		var doctorid= $(this).find("input").val();
		$('#doctoridDring').attr('value',doctorid);
		var url="showDoctorInfo.action";
		var params={'doctorid':doctorid};
		$.post(url,params,callDoctorBack);	
		return false;
		})
		
	
function callDoctorBack(value){
	var doctor=eval('('+value+')');
	var doctorname=document.getElementById('doctorname');
		doctorname.innerHTML=doctor.doctorname;
	var sn=document.getElementById('sn');
		sn.innerHTML=doctor.sn;
	var qq=document.getElementById('qq');	
		qq.innerHTML=doctor.qq;
	var phone=document.getElementById('phone');
		phone.innerHTML=doctor.phone;
	var doctorcountry=document.getElementById('doctorcountry');
		doctorcountry.innerHTML=doctor.doctorcountry;
	var startup_time=document.getElementById('startup_time');
		startup_time.innerHTML=doctor.startup_time;
	var startup_time=document.getElementById('ip');
		startup_time.innerHTML=doctor.ip;	
	var onlineState=document.getElementById('onlineState');
		onlineState.innerHTML=doctor.onlineState;
	$('#win').panel({title: doctor.doctorname+'详细信息'});	
	$('#win').window('open');
}		
	//var img=document.getElementById("picture");
	//path="/desksecurity/pic2.jpg";
	//img.src=path;
	//$('#photo').window('open');
	
	
	$(".seaTable-jg td a").toggle(function(){
	  var sil = $(this).parent("td").siblings("td").children("a");
	  sil.removeClass().addClass("seaDsc_gray");
	  $(this).removeClass().addClass("seaDsc");
	},function(){
	  var sil = $(this).parent("td").siblings("td").children("a");
	  sil.removeClass().addClass("seaDsc_gray");	
	  $(this).removeClass().addClass("seaAsc");
	})
	
	$("#chart1").click(function(){
		var doctorid=$('#doctorid').attr('value');
		var title=$('#doctorname').attr('value');
		openLineChart1('dlg', title+'在线流量统计', 'dlg-toolbar', '#00C000', 'canvas',doctorid);		
	});
	
	$("#chart2").click(function(){
	var doctorid=$('#doctorid').attr('value');
		var title=$('#doctorname').attr('value');
		openLineChart2('dlg1', title+'在线时长统计', 'dlg-toolbar1', '#FFC000', 'canvas1',doctorid);		
	});

})
//画流量图
function callTrafficDateBack(value){
	value=eval("("+value+")");
	line_color='#00C000';
	canvas='canvas';
	linechart_init(value, line_color, canvas);
}
function openLineChart(id, title, toolbar_id, line_color, canvas)
{
	$('#'+id).dialog({
		title: title,
		width:900,
		height:500,
		toolbar: '#'+toolbar_id
	});
	var json = (id == "dlg" ? random_json(1) : random_json(7));
	linechart_init(json, line_color, canvas);
}

function random_json(type)        //随机产生1上月流量、2本月流量、3上周流量、4本周流量、5昨天流量、6本天流量、7上月平均在线时长、8本月平均在线时长、9上周平均在线时长、10本周月平均在线时长的json数据
{
	var json = [];
	json.items = [];
	var i = 0;
	switch(type)
	{
	case 1:
		json.title = "上月流量(2013-10)";
		for (i = 0; i < 30; i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(i+1)+"日"});
		break;
	case 2:
		json.title = "本月流量(2013-11)";
		for (i = 0; i < Math.floor(Math.random()*31); i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(i+1)+"日"});
		for ( ; i < 30; i++)
			json.items.push ({"data":0, "time":String(i+1)+"日"});
		break;
	case 3:
		json.title = "上周流量(2013-11-18~2013-11-24)";
		for (i = 0; i < 7; i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(18+i)+"日"});
		break;
	case 4:
		json.title = "本周流量(2013-11-25~2013-12-01)";
		for (i = 0; i < Math.floor(Math.random()*7); i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(25+i)+"日"});
		for ( ; i < 7; i++)
			json.items.push ({"data":0, "time":String(25+i)+"日"});
		break;
	case 5:
		json.title = "昨天流量(2013-11-27)";
		for (i = 0; i < 24; i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(i+1)+":00"});
		break;
	case 6:
		json.title = "本天流量(2013-11-28)";
		for (i = 0; i < Math.floor(Math.random()*24); i++)
			json.items.push ({"data":Math.floor(Math.random()*100), "time":String(i+1)+":00"});
		for ( ; i < 24; i++)
			json.items.push ({"data":0, "time":String(i+1)+":00"});
		break;
	case 7:
		json.title = "上月平均在线时长(2013-10 单位：分钟)";
		for (i = 0; i < 30; i++)
			json.items.push ({"data":Math.floor(Math.random()*1440), "time":String(i+1)+"日"});
		break;
	case 8:
		json.title = "本月平均在线时长(2013-11 单位：分钟)";
		for (i = 0; i < Math.floor(Math.random()*31); i++)
			json.items.push ({"data":Math.floor(Math.random()*1440), "time":String(i+1)+"日"});
		for ( ; i < 30; i++)
			json.items.push ({"data":0, "time":String(i+1)+"日"});
		break;
	case 9:
		json.title = "上周平均在线时长(2013-11-18~2013-11-24 单位：分钟)";
		for (i = 0; i < 7; i++)
			json.items.push ({"data":Math.floor(Math.random()*1440), "time":String(18+i)+"日"});
		break;
	case 10:
		json.title = "本周平均在线时长(2013-11-25~2013-12-01 单位：分钟)";
		for (i = 0; i < Math.floor(Math.random()*7); i++)
			json.items.push ({"data":Math.floor(Math.random()*1440), "time":String(25+i)+"日"});
		for ( ; i < 7; i++)
			json.items.push ({"data":0, "time":String(25+i)+"日"});
		break;
	default:
	}
	return json;
}

function captureDesk(){
	var sn=document.getElementById('sn');
	var param={'sn':sn.innerHTML};
	var url="captureDesk.action";
	$.post(url,param,callCaptureBack);
}
function callCaptureBack(value){
	
	console.log(value);
	var result=eval('('+value+')');
	var img=document.getElementById("picture");
	var path;
	if(result.sn.indexOf(".") > 0)
	{
		path="/desksecurity/"+result.sn;
		img.src=path;
		$('#photo').window('open');
	}
	else
	alert("截屏失败");
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

