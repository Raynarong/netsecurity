package com.netsecurity.action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.netsecurity.bean.Laboratory;
import com.netsecurity.bean.OnLineNum;
import com.netsecurity.bean.TrendData;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.LaboratoryInter;
import com.netsecurity.interfaces.OnLineNumInter;
import com.netsecurity.interfaces.TrendDataByDayInter;
import com.netsecurity.interfaces.TrendDataInter;
import com.netsecurity.service.LaboratorySerivce;
import com.netsecurity.service.OnLineNumService;
import com.netsecurity.service.TrendDataByDayService;
import com.netsecurity.service.TrendDataService;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;
public class LaboratoryAction extends ActionSupport{
	private int page;
	private int rows;
	private int labId;
	private TimeType timeType;
	private String start;
	private String end;
	private StatusType statusType;
	private UnitType unit;
	
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
	
	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		if(timeType==0)
			this.timeType =TimeType.DAY;
		else if(timeType==1)
			this.timeType =TimeType.WEEK;
		else if(timeType==2)
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
	public int getLabId() {
		return labId;
	}
	public void setLabId(int labId) {
		this.labId = labId;
	}
	/**
	 * 获得各实验室在线，离线，总人数 并将json数据发送到浏览器
	 */

	public void showLabsOnLineData() throws IOException{
		OnLineNumInter  onlineinter=new OnLineNumService();
		LaboratoryInter labinter=new LaboratorySerivce();
		List<OnLineNum> labsOnLineList=	onlineinter.getLabsOnlineNumByPage(page,rows);
		int total=labinter.getlabNum();
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("rows", labsOnLineList);
		map.put("total",total );
		String json=JSON.toJSONString(map);
		FlushTool.flushJsonRecord(json);
	}
	/**
	 * 获得所有实验室id和名称，并将json数据发送到浏览器
	 * @throws IOException 
	 */
	public void getLabs() throws IOException{
		LaboratoryInter labinter = null;//接口未实现
		List<Laboratory> labs=labinter.getLabsNames();
		String json=JSON.toJSONString(labs);
		FlushTool.flushJsonRecord(json);
	}
	/**
	 * 获得某个实验室的数据趋势图，并将json数据发送到浏览器
	 * 趋势图种类：
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @throws IOException 
	 */
	public void getLabStatusTrendData() throws IOException{
		TrendDataInter trendinter = new TrendDataService();
		List<TrendData> statusTrendList=trendinter.getLabTrendList(String.valueOf(labId),start,end,statusType,unit);
		
		//显示时间时，单位不要年 月 只剩下日 
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
	public String showLabOnlineData(){
		
		return SUCCESS;
	}

	
	public void getLabStatusTrendDataByDay() throws IOException{
		TrendDataByDayInter trendinter = new TrendDataByDayService();
		List<TrendData> statusTrendListByDay=trendinter.getLabTrendListByDay(String.valueOf(labId),start,statusType,unit);
		List<Object> dataByDay=new ArrayList<Object>();
		for(TrendData trenddata:statusTrendListByDay)
		{
			dataByDay.add(trenddata.getData());
		}
		String data=JSON.toJSONString(dataByDay);
		FlushTool.flushJsonRecord(data);
	}
	

	
}
