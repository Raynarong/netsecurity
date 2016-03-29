package com.netsecurity.commonUser.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.netsecurity.bean.TrendData;
import com.netsecurity.bean.UserComputerOnlineNumber;
import com.netsecurity.bean.Userinfo;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.OnLineNumInter;
import com.netsecurity.interfaces.TrendDataInter;
import com.netsecurity.interfaces.UserinfoInter;
import com.netsecurity.service.OnLineNumService;
import com.netsecurity.service.TrendDataService;
import com.netsecurity.service.UserinfoSerivce;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;

public class CommonUserAction extends ActionSupport{
	private int labId;
	private String userId;
	private int page=1;
	private int rows=10;

	private TimeType timeType;
	private StatusType statusType;
	private String start;
	private String end; 
	private String searchStr;
	private UnitType unit;
	
	//private int unit2;
	
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
	
	public void showCommonUserOnlineData() throws IOException 
	{
		OnLineNumInter  onlineinter=new OnLineNumService();
		UserinfoInter userinter=new UserinfoSerivce();
		
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		
	//	labId=(Integer)session.getAttribute("UserLabId");
		//Userinfo userinfo = (Userinfo) session.getAttribute("commonUser");
		userId = (String) session.getAttribute("CommonUserId");
		List<UserComputerOnlineNumber> labsOnLineList=onlineinter.getUsersOnlineStaById(userId);
	//	int total=userinter.getUserNum(labId);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", labsOnLineList);
		//仅一条记录
		map.put("total",1 );
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
		HttpSession session=ServletActionContext.getRequest().getSession();
		userId = (String) session.getAttribute("CommonUserId");
		List<TrendData> statusTrendList=trendinter.getUserTrendList(userId,start,end,statusType,unit);
		
		//不要年月 只剩下日 
		// if(timeType!=TimeType.DAY)
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


}
