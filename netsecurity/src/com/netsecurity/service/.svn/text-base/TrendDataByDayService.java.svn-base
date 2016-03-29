package com.netsecurity.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.netsecurity.bean.TrendData;
import com.netsecurity.enums.LevelType;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.TrendDataByDayInter;
import com.netsecurity.util.HibernateUtil;


public class TrendDataByDayService implements TrendDataByDayInter {
	
	@Override
	public List<TrendData> getLabTrendListByDay(String labId, String start,
			 StatusType statusType, UnitType unitType) {
		
			return getTrendListByDay(labId, start, statusType, unitType, LevelType.LAB);
		
	}
	
	public static void main(String[] args)
	{

		TrendDataByDayService ts = new TrendDataByDayService();
		List<TrendData> list = ts.getTrendListByUserSelectDay("1", StatusType.memoryRate, LevelType.LAB,"2014-9-4 00:00:00");
		//List<TrendData> list2 = ts.getTrendListByDay("4b4ed817b80c47f0", StatusType.cpuRate, LevelType.COMPUTER);
		

		System.out.println(list);
		System.out.println(list.size());
	}
	
	
	

	@Override
	public List<TrendData> getComputerTrendListByDay(String sn, String start,
			 StatusType statusType, UnitType unitType) {
		// TODO Auto-generated method stub
		return getTrendListByDay(sn, start, statusType, unitType, LevelType.COMPUTER);
	}

	

	@Override
	public List<TrendData> getUserTrendListByDay(String userId, String start,
			 StatusType statusType, UnitType unitType) {
		// TODO Auto-generated method stub
		return getTrendListByDay(userId, start, statusType, unitType, LevelType.USER);
	}
	
	public List<TrendData> getTrendListByDay(String Id, String start,
			StatusType statusType, UnitType unitType,LevelType levelType)
	{
		List<TrendData> list = new ArrayList<TrendData>();
		list = getTrendListNoUnit(Id, start, statusType, levelType);
		/**
		 * 对流量单位进行处理
		 */
		if (statusType == StatusType.downLoad
				|| statusType == StatusType.upLoad)
		{
			for (TrendData td : list)
			{
				long level = getLevel(unitType);
				double data;
				// data = Double.valueOf(((Long)td.getData()/level));
				Long temp = (Long) td.getData();

				data = ((double) temp / level);
				data = doubleRound(data, 2);
				td.setData(data);

			}
		}
		return list;
	}
	
	public static long getLevel(UnitType unitType)
	{
		long level = 1024 ; // K
		switch (unitType)
		{
			case KB:
				break; // 
			case MB:
				level *= 1024;
				break; // M
			case GB:
				level *= 1024 * 1024;
				break; // G
			case TB:
				level *= 1024 * 1024;
				break; // T
		}
		return level;
	}
	
	public static double doubleRound(double d, int num)
	{
		BigDecimal bd = new BigDecimal(d);

		BigDecimal bd1 = bd.setScale(num, bd.ROUND_HALF_UP); // 按 舍入远离零的舍入模式
		return bd1.doubleValue();

	}
	private List<TrendData> getTrendListNoUnit(String Id, String start,
			StatusType statusType,LevelType levelType)
	{
		List<TrendData> list = new ArrayList<TrendData>();

		list =  getTrendListByUserSelectDay(Id, statusType, levelType, start);
		return list;
	}
	
	
	/****
	 * 根据用户选择日期展示趋势图
	 * @param Id
	 * @param statusType
	 * @param levelType
	 * @param start
	 * @return
	 */
	private List<TrendData> getTrendListByUserSelectDay(String Id,
			StatusType statusType,LevelType levelType,String start)
	{
		
			
		String LimitStr = getStatusTypeLimitStr(statusType);
		String TimeLimitStr = "and computerstatuses.datetime = '"+start+"'" ;//getTimeLimit(TimeType.MONTH);
		System.out.println("######TimeLimitStr:"+TimeLimitStr);
		String hql = new String();
		String[] parameters={Id};
		Date now=new Date();
		DateFormat d1 = DateFormat.getDateInstance(); 
		String str1 = d1.format(now);
		if(!str1.equals(start)){
		switch (levelType)
		{
			case LAB:
				
				hql = "select "
					+ LimitStr
					+",DATE_FORMAT( computerstatuses.datetime, '%H:%i') "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join computerinfo.computerstatusHistories  computerstatuses "
					+ " where lab.labId=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d '))"
					+ "  GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)"
					;
					
				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i') "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where computerinfo.sn=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d '))"
					+ "  GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)"
					;
		
			break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i' )  "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join computerinfo.computerstatusHistories  computerstatuses "
					+ " where userinfos.userid=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d ')) GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)"
					;
				
				break;
			
				
			default:
				break;
		}
		}
		else{
			switch (levelType)
			{
				case LAB:
					
					hql = "select "
						+ LimitStr
						+",DATE_FORMAT( computerstatuses.datetime, '%H:%i') "
						+ " from Laboratory lab "
						+ " left  join lab.userinfos userinfos "
						+ " left  join  userinfos.usercomputers usercomputers "
						+ " left  join  usercomputers.computerinfo computerinfo "
						+ " left  join computerinfo.computerstatuses computerstatuses "
						+ " where lab.labId=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d '))"
						+ "  GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)"
						;
						
					break;
				case COMPUTER:
					hql = "select "
						+ LimitStr
						+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i') "
						+ " from Computerinfo computerinfo"
						+ " left  join  computerinfo.computerstatuses computerstatuses "
						+ " where computerinfo.sn=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d '))"
						+ "  GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)"
						;
			
				break;
				case USER:
					hql = "select "
						+ LimitStr
						+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i' )  "
						+ " from Userinfo userinfos "
						+ " left  join  userinfos.usercomputers usercomputers "
						+ " left  join  usercomputers.computerinfo computerinfo "
						+ " left  join  computerinfo.computerstatuses computerstatuses "
						+ " where userinfos.userid=? and to_days(computerstatuses.datetime) = to_days(DATE_FORMAT('" +start+"', '%Y-%m-%d ')) GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/60)";
					
					break;
				
					
				default:
					break;
			}
			
			
		}
			List list = HibernateUtil.executeQuery(hql, parameters);
			return CapTrendData.capTrendDataByDay(list);
		}
		
	
	public boolean valid(String str){
		final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try{
	        Date date = (Date)formatter.parse(str);
	        return str.equals(formatter.format(date));
	    }catch(Exception e){
	       return false;
	    }
	}
		
		private String getStatusTypeLimitStr(StatusType statusType)
		{
			String LimitStr = "";
			switch (statusType)
			{
			case memoryRate:
				LimitStr = "avg(computerstatuses.memoryRate)";
				break;
			case appNum:
				LimitStr = "avg(computerstatuses.appNum)";
				break;
			case cpuRate:
				LimitStr = "avg(computerstatuses.cpuRate)";
				break;
			case keyboardType:
				LimitStr = "sum(computerstatuses.keyboardType)";
				break;
			case downLoad:
				LimitStr = "sum(computerstatuses.download)";
				break;
			case mouseLeftType:
				LimitStr = "sum(computerstatuses.mouseLeftType)";
				break;
			case mouseRightType:
				LimitStr = "sum(computerstatuses.mouseRightType)";
				break;
			case threadNum:
				LimitStr = "avg(computerstatuses.threadNum)";
				break;
			case upLoad:
				LimitStr = "sum(computerstatuses.upload)";
				break;
			default:
				break;
			}
			return LimitStr;
		}
		
	public String getChartByDayTitle( String start, StatusType statusType){
		return null;
	}




	@Override
	public String getChartTitle(String start, StatusType statusType) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
