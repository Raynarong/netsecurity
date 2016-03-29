package com.netsecurity.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.netsecurity.bean.ComputerOnlineNumber;
import com.netsecurity.bean.TrendData;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.TrendDataByDayInter;
import com.netsecurity.interfaces.Usercomputer;
import com.netsecurity.interfaces.OnLineNumInter;
import com.netsecurity.interfaces.TrendDataInter;
import com.netsecurity.service.OnLineNumService;
import com.netsecurity.service.TrendDataByDayService;
import com.netsecurity.service.TrendDataService;
import com.netsecurity.service.UsercomputerService;
import com.netsecurity.service.UserinfoSerivce;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;

public class ComputerAction extends ActionSupport{
	private int page;
	private int rows;
	private TimeType timeType;
	private StatusType statusType;
	private UnitType unit;
	private String userId;
	private String sn;
	private String start;
	private String end;
	
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	@Override
	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("ComputerStatusUserId", userId);
		return SUCCESS;
	}
	/**
	 * ajax显示某用户电脑行为列表
	 * @throws IOException
	 */
	public void showComputersOnLineData() throws IOException{
		OnLineNumInter  onlineinter=new OnLineNumService();
		Usercomputer computerinter=new UsercomputerService();
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		userId=(String)session.getAttribute("ComputerStatusUserId");
		List<ComputerOnlineNumber> labsOnLineList=onlineinter.getComputersOnlineNumByPage(userId, page, rows);
		int total=computerinter.getComputerNum(userId);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", labsOnLineList);
		map.put("total",total );
		String json=JSON.toJSONString(map);
		FlushTool.flushJsonRecord(json);
	}
	/**
	 *按时间段查询 获得某个用户的数据趋势图，并将json数据发送到浏览器
	 * 趋势图种类：
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @throws IOException 
	 * @throws IOException 
	 */
	public void showComputersOnLineStatus() throws IOException{
		
		
			TrendDataInter trendinter = new TrendDataService();
			List<TrendData> statusTrendList=trendinter.getComputerTrendList(sn,start,end,statusType,unit);
			
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
	


/**
 *按日查询 获得某个用户的数据趋势图，并将json数据发送到浏览器
 * 趋势图种类：
 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
 * 6进程数，7任务数 ，8上行流量，9下行流量
 * @throws IOException 
 * @throws IOException 
 */
public void showComputersOnLineStatusByDay() throws IOException{
	
	
	    TrendDataByDayInter  trendinter = new TrendDataByDayService();
		List<TrendData> statusTrendList=trendinter.getComputerTrendListByDay(sn,start,statusType,unit);
		List<Object> dataByDay=new ArrayList<Object>();
		for(TrendData trenddata:statusTrendList)
		{
			dataByDay.add(trenddata.getData());
		}
		String data=JSON.toJSONString(dataByDay);
		FlushTool.flushJsonRecord(data);	
}

}

