package com.netsecurity.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netsecurity.bean.OnLineNum;
import com.netsecurity.bean.TrendData;
import com.netsecurity.bean.UserComputerOnlineNumber;
import com.netsecurity.bean.Userinfo;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.GroupinfoInter;
import com.netsecurity.interfaces.LaboratoryInter;
import com.netsecurity.interfaces.OnLineNumInter;
import com.netsecurity.interfaces.TrendDataByDayInter;
import com.netsecurity.interfaces.TrendDataInter;
import com.netsecurity.interfaces.UserinfoInter;
import com.netsecurity.service.GroupinfoService;
import com.netsecurity.service.LaboratorySerivce;
import com.netsecurity.service.OnLineNumService;
import com.netsecurity.service.TrendDataByDayService;
import com.netsecurity.service.TrendDataService;
import com.netsecurity.service.UserinfoSerivce;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;

public class UsersAction extends ActionSupport{
	private int labId;
	private String userId;
	private int page=1;
	private int rows=10;
	private String start;
	private String end;
	private TimeType timeType;
	private StatusType statusType;
	
	private String searchStr;
	private UnitType unit;
	
	private int showUserType;
	private String pwd;
	private String pwdOld;
	private int groupId;
	//private int unit2;
	
	public int getShowUserType() {
		return showUserType;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setShowUserType(int showUserType) {
		this.showUserType = showUserType;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	
	public int getLabId() {
		return labId;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserId(int userId) {
		this.userId = String.valueOf(userId);
		System.out.println(this.userId);
	}
	public void setLabId(int labId) {
		this.labId = labId;
	}
	public void setLabId(String labId) {
		this.labId = Integer.valueOf(labId);
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
	

	public TimeType getTimeType() {
		return timeType;
	}

	
	public void setTimeType(int timeType) {
		if(timeType==0)
			this.timeType =TimeType.DAY;
		else if(timeType==1)
			this.timeType =TimeType.WEEK;
		else 
			this.timeType =TimeType.MONTH;
	}
	public StatusType getStatusType() {
		return statusType;
	}

	
	public void setStatusType(int statusType) {
		if(statusType==0)
			this.statusType = StatusType.memoryRate;
		else if(statusType==1)
			this.statusType=StatusType.cpuRate;
		else if(statusType==2)
			this.statusType=StatusType.mouseLeftType;
		else if(statusType==3)
			this.statusType=StatusType.mouseRightType;
		else if(statusType==4)
			this.statusType=StatusType.keyboardType;
		else if(statusType==5)
			this.statusType=StatusType.threadNum;
		else if(statusType==6)
			this.statusType=StatusType.appNum;
		else if(statusType==7)
			this.statusType=StatusType.upLoad;
		else if(statusType==8)
			this.statusType=StatusType.downLoad;
	}
	public UnitType getUnit() {
		return unit;
	}

	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getPwdOld() {
		return pwdOld;
	}

	public void setPwdOld(String pwdOld) {
		this.pwdOld = pwdOld;
	}

	public void setUnit(int unit) {
		switch(unit){
		case 0:
			this.unit=UnitType.KB;
			break;
		case 1:
			this.unit=UnitType.MB;
			break;	
		case 2:
			this.unit=UnitType.GB;
			break;
		case 3:
			this.unit=UnitType.TB;
			break;
		}
	}
	/**
	 * 调转到用户页面
	 */
	@Override
	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("UserLabId", labId);
		
		return SUCCESS;
	}
	
	/**
	 * showFindUsersOnLinePage
	 */
	public String showFindUsersOnLinePage() throws IOException{
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("searchStr", searchStr);
		return SUCCESS;
	}
	/**
	 * ajax显示某实验室用户行为列表
	 * @throws IOException
	 */
	public void showFindUsersOnLineData() throws IOException{
		OnLineNumInter  onlineinter=new OnLineNumService();
		UserinfoInter userinter=new UserinfoSerivce();
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		List<UserComputerOnlineNumber> labsOnLineList=onlineinter.getStudentsOnlineNumByPage(searchStr, page, rows);
		int total=userinter.getUserNumByName(searchStr);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", labsOnLineList);
		map.put("total",total );
		String json=JSON.toJSONString(map);
		FlushTool.flushJsonRecord(json);
	}
	
	
	/**
	 * ajax显示某实验室特定用户行为列表
	 * @throws IOException
	 */
	public void showUsersOnLineData() throws IOException{
		OnLineNumInter  onlineinter=new OnLineNumService();
		UserinfoInter userinter=new UserinfoSerivce();
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		labId=(Integer)session.getAttribute("UserLabId");
		List<UserComputerOnlineNumber> labsOnLineList=onlineinter.getStudentsOnlineNumByPage(labId, page, rows);
		int total=userinter.getUserNum(labId);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", labsOnLineList);
		map.put("total",total );
		String json=JSON.toJSONString(map);
		FlushTool.flushJsonRecord(json);
	}
	
	/**
	 * ajax显示某实验室特定用户行为列表
	 * @throws IOException
	 */
	public void showGroupUsersOnLineData() throws IOException{
		OnLineNumInter  onlineinter=new OnLineNumService();
		UserinfoInter userinter=new UserinfoSerivce();
		GroupinfoInter ginter=new GroupinfoService();
		List<UserComputerOnlineNumber> groupOnLineList=onlineinter.getGroupStudentsOnlineNumByPage(groupId, page, rows);
		int total=ginter.getGroupMumberNum(groupId);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", groupOnLineList);
		map.put("total",total );
		String json=JSON.toJSONString(map);
		FlushTool.flushJsonRecord(json);
	}
	
	/**
	 * 获得某个用户的数据趋势图，并将json数据发送到浏览器
	 * 趋势图种类：
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @throws IOException 
	 */
	public void getUserStatusTrendData() throws IOException{
		TrendDataInter trendinter = new TrendDataService();
		List<TrendData> statusTrendList=trendinter.getUserTrendList(userId,start,end,statusType,unit);
		
		//不要年月 只剩下日 
		//if(timeType!=TimeType.DAY)
		for(TrendData trenddata:statusTrendList)
		{
			String fulldate=trenddata.getTime();
			String month=fulldate.substring(fulldate.indexOf("-")+1,fulldate.lastIndexOf("-"));
			String day=fulldate.substring(fulldate.lastIndexOf("-")+1);
			if(day.startsWith("0"))
				day=day.substring(1);
			trenddata.setTime(month+"."+day);
		}
		
		String title=trendinter.getChartTitle(start,end, statusType);
		Map<Object,Object>status=new HashMap<Object,Object>();
		status.put("items", statusTrendList);
		status.put("title", title);
		String json=JSON.toJSONString(status);
		FlushTool.flushJsonRecord(json);
	}

	public void getUserStatusTrendDataByDay() throws IOException{
		TrendDataByDayInter trendinter = new TrendDataByDayService();
		List<TrendData> statusTrendListByDay=trendinter.getUserTrendListByDay(String.valueOf(userId),start,statusType,unit);
		List<Object> dataByDay=new ArrayList<Object>();
		for(TrendData trenddata:statusTrendListByDay)
		{
			dataByDay.add(trenddata.getData());
		}
		String data=JSON.toJSONString(dataByDay);
		FlushTool.flushJsonRecord(data);
	}
	/***
	 * 获取leader列表
	 * @throws IOException
	 */
	public void getLeaderList() throws IOException{
		UserinfoInter userinter=new UserinfoSerivce();
		List<Userinfo> userInfos=userinter.getUserByPage(page, rows, 1, searchStr);
		JSONObject data=new JSONObject();
		List<Object>datarows=new ArrayList<Object>();
		if(userInfos!=null)
		for(Userinfo u:userInfos){
			JSONObject datarow=new JSONObject();
			datarow.put("username", u.getUsername());
			datarow.put("userid", u.getUserid());
			datarow.put("labId", u.getLaboratory().getLabId());
			datarows.add(datarow);
		}
		data.put("rows", datarows);
		data.put("total", userinter.getUserNumByNameType(searchStr, 1));
		FlushTool.flushJsonRecord(data);
	}
	/**
	 * 获取未添加的组长信息
	 * @throws IOException 
	 */
	public void searchGroupMumberNames() throws IOException{
		UserinfoInter userinter=new UserinfoSerivce();
		List<Userinfo>userInfos=userinter.getUserBySearchWord(searchStr, 2);
		JSONObject data=new JSONObject();
		List<Object>datarows=new ArrayList<Object>();
		if(userInfos!=null)
		for(Userinfo u:userInfos){
			JSONObject datarow=new JSONObject();
			datarow.put("username", u.getUsername());
			datarow.put("userid", u.getUserid());
			datarows.add(datarow);
		}
		
		FlushTool.flushJsonRecord(datarows);
	}
	/**
	 * 添加组长
	 * @throws IOException 
	 */
	public void addLeader() throws IOException{
		UserinfoInter userinter=new UserinfoSerivce();
		boolean result=userinter.changePower(searchStr, 1);
		FlushTool.flushJsonRecord(result);
	}

	/**
	 * 删除组长
	 * @throws IOException 
	 */
	public void delLeader() throws IOException{
		UserinfoInter userinter=new UserinfoSerivce();
		boolean result=userinter.changePower(userId, 2);
		FlushTool.flushJsonRecord(result);
	}
	
	public void editPassWord() throws IOException{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Userinfo u=(Userinfo) session.getAttribute("user");
		UserinfoInter uinter=new UserinfoSerivce();
		boolean result=false;
		if(u!=null)
			result= uinter.editPwd(u.getUserid(), pwdOld,pwd);
		FlushTool.flushJsonRecord(result);
	}
	//处理管理员编辑用户的事件。
//	public String groupEdit() {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		String returnMsg = null;
//		//获取市列表信息
//		String[] labList = request.getParameterValues("laboratory");	
//		//获取县的名称		注意，初始密码统一设为：123456
//		String[] studentList = request.getParameterValues("student");	
//		//获取UID
//		String uid = request.getParameter("userid");
//		//获取用户名
//		String account = request.getParameter("account");
//		//获取姓名
//		String userName = request.getParameter("userName");
//		//获取电话
//		String phone = request.getParameter("phone");
//		
//		System.out.println("添加用户表单参数信息:\nAccount: " + account + "\nUid: " + uid
//				+ "\nUserName: " + userName + " \nPhone: " + phone + "\nCityList: " + labList + "\nCountyList: " + studentList);	
//		 {
//			Userinfo userInfo = accountDao.getUserInfoByUid(uid);
//			String oldUserAccount = userInfo.getAccount();
//			System.out.println("oldUserAccount: " + oldUserAccount + "\nAccount: " + account + "\n!oldUserAccount.equals(account): " + !oldUserAccount.equals(account));
//			boolean accountEqualsOld = oldUserAccount.equals(account);
//			boolean accountIsExist = accountDao.accountIsExist(account);
//			if(!accountEqualsOld && accountIsExist) {
//				returnMsg = "此账号已存在，请选用其他账号名称！";
//			} else {
//				//在账号不重复的前提下,修改用户信息。
//				boolean userEdity = accountDao.accountEdit(uid, account, userName, phone);
//				if(!userEdity) {
//					returnMsg = "编辑用户信息出错！";
//				} else {
//					accountDao.deleteUserAreaByUid(uid);	//不管有没有记录，先在resources中删除所有此用户管理的区域！！
//					if(accountDao.accountAreaEdit(uid, cityList, countyList)){
//						returnMsg = "编辑成功！";
//					} else {
//						returnMsg = "编辑失败！";
//					}
//				}
//			}
//		}
//		request.setAttribute("returnEditMsg",returnMsg);
//		return SUCCESS;	
//	} 
	
}
