<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/c" prefix="c"%>
<%@ page import="com.netsecurity.bean.*"%>
<%@ page import="com.netsecurity.bean.*"%>
<%@ page import="com.netsecurity.interfaces.*"%>
<%@ page import="com.netsecurity.service.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="javascript" type="text/javascript">
$(function(){
	$("#allArea").click(function(){            //操作"所有"checkbox
		if ($(this).is(":checked"))
			$("input").attr("checked", true);
		else
			$("input").attr("checked", false);
	});	
	
	var laboratory_boxes = $("input:checkbox[name='laboratory']");  //操作城市checkbox
	var laboratory_len = laboratory_boxes.length;
	laboratory_boxes.click(function(){
		//var town_boxes = $(this).parent("td").parent().find("input:checkbox[name='town']");
		var town_boxes = $(this).parent("td").next().find("input:checkbox[name='student']");
		if ($(this).is(":checked"))
		{
			town_boxes.attr("checked", true);
			if ($("input:checkbox:checked[name='laboratory']").length == laboratory_len)$("#allArea").attr("checked", true);
		}
		else
		{
			town_boxes.attr("checked", false);
			$("#allArea").attr("checked", false);
		}
	});
	
	var alltown_boxes = $("input:checkbox[name='student']");     //操作县checkbox
	alltown_boxes.click(function(){
		//var td = $(this).parent("td");
		var td = $(this).parent().parent();	//修改后，市和县在不同的td中。
		if (td.find("input:checkbox[name='student']").not(":checked").length == 0)
		{
			td.find("input:checkbox[name='laboratory']").attr("checked", true);
			if ($("input:checkbox:checked[name='laboratory']").length == laboratory_len)$("#allArea").attr("checked", true);
		}
		else 
		{
			td.find("input:checkbox[name='laboratory']").attr("checked", false);
			//$(this).parent().parent("td").next().find("input:checkbox[name='laboratory']").attr("checked", false);
			$("#allArea").attr("checked", false);
		}
	});
});

//提交时，取消选中laboratory的所有student! 或全选时，只提交厂商所有的市!
/*function removeTown(){
	//alert("Enter removeTown!");
	var allArea = $("#allArea"); 			//全选按钮
	var laboratory_boxesRe = $("input:checkbox[name='laboratory']"); 	 	//操作城市checkbox
	var allTown_boxesRe = $("input:checkbox[name='student']"); 	//操作县、区的checkbox
	//当选择全选按钮时，只提交所有的市。(因为各个厂商所有的地区可能不一样。)
	if(allArea.is(":checked")) {
		allTown_boxesRe.attr('checked',false);
	} else {
		//检查有选择市的，取消其市下面所有的区和县。
		for(var i=0 ;i<laboratory_boxesRe.length;i++){
 		var laboratoryRe=$("input:checkbox[name='laboratory']")[i];
 		var town_boxesRe = $(laboratoryRe).parent("td").next().find("input:checkbox[name='student']");
 			if($(laboratoryRe).is(":checked"))
 			{
 				//alert(town_boxesRe.attr('checked'));
 				town_boxesRe.attr('checked',false);
 			}
 		}
	}
 }*/
</script>
<form id="edit" action="editGroup.action" method="post" >
  <TABLE width="98%">
    <TBODY>
      <TR>
      	<% 
      		//UserInfo userEdit = (UserInfo)session.getAttribute("userEditInfo");
      		int groupId=Integer.valueOf(request.getParameter("groupId"));
      		System.out.println("groupId："+groupId);
      		GroupinfoInter ginter=new GroupinfoService();
      		Userinfo leader=ginter.getGroupLeader(groupId);
      		if(leader==null)leader=new Userinfo();
      	%>
        <TD width="13%">用户名</TD>
        <TD colspan="3">
        	<input name="account" id="account" type="text" value="<%=leader.getUsername()%>" maxlength="20" />
        	<input type="hidden" id="leaderId" name="leaderId" value="<%=leader.getUserid()%>" />
        	<input type="hidden" id="groupId" name="groupId" value="<%=groupId%>" />
        </TD>
      </TR>
      <TR>
        <TD>姓名</TD>
        <TD width="40%"><input name="userName" id="userName" type="text" value="" maxlength="20" /></TD>
        <TD width="10%">电话 </TD>
        <TD width="40%"><input name="phone" id="phone" type="text" value="" maxlength="20" /></TD>
      </TR>
      <TR>
        <TD>管理地区</TD>
        <TD colspan="3" id="all_td">
        	
        </TD>
      </TR>
       <%			int i,j;
     				List<GroupLabCheckList> allLabGroupList = ginter.getGroupChekcInAllLab(groupId);
     				//System.out.println("laboratoryInfoList: " + session.getAttribute("laboratoryInfoList").getClass());	
     				for(i=0; i< allLabGroupList.size(); i++) {
     				GroupLabCheckList labList=allLabGroupList.get(i);
     	%>
        <tr class="box_tr">
	        <td>
		        <% 	if(labList.isAllChecked()==true){ %>	
		        <input name="laboratory" value="<%=labList.getLabid()%>" type="checkbox"  checked="checked"/>
		        <%=labList.getLabname() %>
		        <%} else { %>
		        <input name="laboratory" value="<%=labList.getLabid()%>" type="checkbox" />
		        <%=labList.getLabname()  %>
		        <%} %>
	        </td>
	        <td class="rg" align="left" colspan="5">
					(
	     		<% 
	   					List<GroupCheck> memberList =labList.getGroupCheckList();
	   					
	   					for(j=0; j< memberList.size(); j++) {
	   					GroupCheck gc=memberList.get(j);
	     		%>
	     			<% 	if(gc.isInLab()==true || labList.isAllChecked()==true){ %>	
			        <input name="student" value="<%=gc.getUserid() %>" type="checkbox"  checked="checked"/>
			        <%=gc.getUserName() %>
			        <%} else { %>
			        <input name="student" value="<%=gc.getUserid() %>" type="checkbox" />
					<%=gc.getUserName() %>
			        <%} %>				
				<% 
					}
				%>
	     			)				
	        </td>
      	</tr>
      	<% 
     		}   
     	%>
    </TBODY>
  </TABLE>
</form>
