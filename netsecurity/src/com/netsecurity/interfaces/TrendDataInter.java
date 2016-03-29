package com.netsecurity.interfaces;

import java.util.List;

import com.netsecurity.bean.TrendData;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;

public interface TrendDataInter
{
	/**
	 * 获得实验室状态趋势图数据
	 * @param labId 实验室id
	 * @param timeType 时间类型
	 * @param statusType 电脑状态类型 
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @return
	 */
	public List<TrendData> getLabTrendList(String labId, String start,String end,
			StatusType statusType, UnitType unitType);
	/**
	 * 根据不同的时间获取标题
	 * @param timeType
	 * @return
	 */
	public String getChartTitle( String start,String end, StatusType statusType);
	/**
	 * 获得用户状态趋势图数据
	 * @param userId 用户id
	 * @param timeType 时间类型
	 * @param statusType 电脑状态类型 
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @return
	 */
	public List<TrendData> getComputerTrendList(String sn,  String start,String end,
			StatusType statusType, UnitType unitType);
	/**
	 * 获得电脑状态趋势图数据
	 * @param userId 用户id
	 * @param timeType 时间类型
	 * @param statusType 电脑状态类型 
	 * 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数
	 * 6进程数，7任务数 ，8上行流量，9下行流量
	 * @return
	 */
	public List<TrendData> getUserTrendList(String sn,  String start,String end,
			StatusType statusType, UnitType unitType);
	
}
