<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form name="add" id="add" method="post" action="">
  <TABLE width="98%">
    <TBODY>
      <TR>
        <TD width="150">组名（不能和已有组名重复）</TD>
        <TD colspan="3"><input name="groupName" id="groupName" type="text" maxlength="10" value="" />
        				<div id="snError" style="display:inline"></div>
        </TD>

      </TR>
	 

    </TBODY>
  </TABLE>
</form>

<script language="javascript">

function searchName(){
	var url="searchGroupMumberNames.action";
	var name=$('#userName').attr('value');
	var param={'searchStr':name};
	$.post(url,param,callLeaderNameback,'html');
}
function callLeaderNameback(data){
    var sel = $('#userid');  
    sel.empty();  

    sel.append("<option value=\"-1\">-请选择-</option>");
    var rows = eval(data);
    console.log(rows);
    var city;
    if(rows!="")
     for(var i=0;i<rows.length;i++)
    {
    	 row=rows[i];
    	 sel.append("<option value = '"+row.userid+"'>"+row.username+"</option>"); 
    } 
} 

function callcountyback(data,status){  
    var sel = $('#countyid');  
    sel.empty();  
    console.log(data);
    sel.append("<option value=\"-1\" name=\"allCounty\" id=\"allCounty\">-请选择-</option>");
    
    var counties = eval(data);
    
    var county;
     for(var i=0;i<counties.length;i++)
    {
    	 county=counties[i];
    	 sel.append("<option value = '"+county.countyid+"'>"+county.countyname+"</option>"); 
    } 
}

//异步查询县
var selectcity=$('#cityid');
selectcity.change(function(){
    var cityId =$("#cityid").val(); 
     
    url = "getDeviceCountyAreaInEdit.action";  
    //console.log(cityId);
    var params = {  
        'cityid':cityId  
    };  
    
    //console.log($("#cpy"));
   
    $.post(url, params, callcountyback); 
});

function calltownback(data,status){  
    var sel = $('#townid');  
    sel.empty();  
    console.log(data);
    sel.append("<option value=\"-1\" name=\"allTown\" id=\"allTown\">-请选择-</option>");
    
    var towns = eval(data);
    
    var town;
     for(var i=0;i<towns.length;i++)
    {
    	 town=towns[i];
    	 sel.append("<option value = '"+town.id+"'>"+town.townname+"</option>"); 
    } 
}

//异步查询镇
var selectcounty=$('#countyid');
selectcounty.change(function(){
    var countyId =$("#countyid").val(); 
     
    url = "getDeviceTownArea.action";  
    //console.log(cityId);
    var params = {  
        'countyid':countyId  
    };  
    
    //console.log($("#cpy"));
   
    $.post(url, params, calltownback); 
});

	function trim(str){
		return str.replace(/^\s*|\s*$/g,'');
	}
	
	function isDigit(str){ 
		var reg = /^\d*$/; 
		return reg.test(str); 
	} 	



	
</script>