package com.netsecurity.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.netsecurity.bean.Groupinfo;
import com.netsecurity.bean.Userinfo;
import com.netsecurity.interfaces.GroupinfoInter;
import com.netsecurity.service.GroupinfoService;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAction extends ActionSupport{
	private String groupName;
	private int groupId;
	private String searchStr;
	private int labId;
	private int page;
	private int rows;
	private String leaderId;
	
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getLabId() {
		return labId;
	}
	public void setLabId(int labId) {
		this.labId = labId;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("groupId", groupId);
		return SUCCESS;
	}
	public void getGroups() throws IOException{
		GroupinfoInter ginter=new GroupinfoService();
		HttpSession session=ServletActionContext.getRequest().getSession();
		int userType=(Integer) session.getAttribute("userType");
		Userinfo user=(Userinfo) session.getAttribute("user");
		String userId=user.getUserid();
		List <Groupinfo>groups=ginter.getGroupByPage(searchStr,userType,userId, page, rows);
		List<Object> list=new ArrayList<Object>();
		if(groups!=null)
			for(Groupinfo g:groups){
				JSONObject obj=new  JSONObject();
				obj.put("groupName", g.getGroupName());
				obj.put("groupId", g.getGroupid());			
				if(g.getUserinfo()!=null){
					obj.put("leaderId", g.getUserinfo().getUserid());
					obj.put("leaderName", g.getUserinfo().getUsername());
				}else{
					obj.put("leaderId", "");
					obj.put("leaderName", "");
				}
				obj.put("members", ginter.getGroupMemberNamse(g.getGroupid()));
				list.add(obj);
			}
		JSONObject data=new JSONObject();
		data.put("rows", list);
		data.put("total", ginter.getGroupNum(searchStr));
		FlushTool.flushJsonRecord(data);
	}
	public void addGroup() throws IOException{
		GroupinfoInter ginter=new GroupinfoService();
		boolean result=ginter.addGroup(groupName, null);
		FlushTool.flushJsonRecord(result);
	}
	public void editGroup() throws IOException{
		GroupinfoInter ginter=new GroupinfoService();
		String returnMsg = null;
		//获取市列表信息
		HttpServletRequest request=ServletActionContext.getRequest();
		
		//获取县的名称		注意，初始密码统一设为：123456
		String student = request.getParameter("student");
		String userids[]=student.split(",");
		for(String s:userids){
			System.out.println("student:"+s);
		}
		boolean result=ginter.editGroupMember(groupId, userids);
		if(result)
			returnMsg="修改成功";
		else
			returnMsg="修改失败";
		FlushTool.flushJsonRecord(returnMsg) ;
	}
	public void updateLeader() throws IOException{
		GroupinfoInter ginter=new GroupinfoService();
		boolean result=ginter.updateGroupLeader(groupId, leaderId);
		String msg;
		if(result)
			msg="修改成功!";
		else
			msg="未修改组长!";
		FlushTool.flushJsonRecord(msg);
		
	}
	public void updateGroupName() throws IOException{
		GroupinfoInter ginter=new GroupinfoService();
		boolean result=ginter.updateGroupName(groupId, groupName);
		String msg;
		if(result)
			msg="修改成功!";
		else
			msg="修改失败!"; 
		FlushTool.flushJsonRecord(msg);
	}
}
