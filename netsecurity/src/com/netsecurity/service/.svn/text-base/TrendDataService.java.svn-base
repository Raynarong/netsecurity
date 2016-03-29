package com.netsecurity.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.netsecurity.bean.TrendData;
import com.netsecurity.enums.LevelType;
import com.netsecurity.enums.StatusType;
import com.netsecurity.enums.TimeType;
import com.netsecurity.enums.UnitType;
import com.netsecurity.interfaces.TrendDataInter;
import com.netsecurity.util.HibernateUtil;
import com.netsecurity.util.TimeUtil;

public class TrendDataService implements TrendDataInter
{
	public List<TrendData> getComputerTrendList(String sn,
			String start,String end, StatusType statusType, UnitType unitType)
	{
		return getTrendList(sn, start,end, statusType, unitType, LevelType.COMPUTER);
	}

	public List<TrendData> getLabTrendList(String labId, String start,String end,
			StatusType statusType, UnitType unitType)
	{
		return getTrendList(labId, start,end, statusType, unitType, LevelType.LAB);
	}

	public List<TrendData> getUserTrendList(String userId, String start,String end,
			StatusType statusType, UnitType unitType)
	{
		return getTrendList(userId, start,end, statusType, unitType, LevelType.USER);
	}

	public List<TrendData> getTrendList(String Id, String start,String end,
			StatusType statusType, UnitType unitType,LevelType levelType)
	{
		List<TrendData> list = new ArrayList<TrendData>();
		list = getTrendListNoUnit(Id, start,end, statusType, levelType);
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

	/**
	 * 获得实验室趋势图数据
	 * 
	 * @param labId
	 *            实验室id
	 * @param timeType
	 *            时间类型
	 * @param statusType
	 *            电脑状态类型 1内存利用率，2cpu利用率，3鼠标左键点击数，4鼠标右键点击数，5键盘敲击数 6进程数，7任务数
	 *            ，8上行流量，9下行流量 Computerstatus类中 select new
	 *            com.netsecurity.bean.TrendData()
	 * @return
	 */

	private List<TrendData> getTrendListNoUnit(String Id, String start,String end,
			StatusType statusType,LevelType levelType)
	{
		List<TrendData> list = new ArrayList<TrendData>();
//		switch (timeType)
//		{
//			case DAY:
//				list = getTrendListByDay(Id, statusType, levelType);
//				break;
//			case MONTH:
//				list = getTrendListByMonth(Id, statusType, levelType);
//				break;
//			case WEEK:
//				list = getTrendListByWeek(Id, statusType, levelType);
//				break;
//			default:
//				break;
//		}
		list = getTrendListByUserSelect(Id, statusType, levelType, start, end);
		return list;
	}
/**
 * 有问题，再加时间限制
 * @param Id
 * @param statusType
 * @param levelType
 * @return
 */
	private List<TrendData> getTrendListByMonth(String Id,
			StatusType statusType,LevelType levelType)
	{
		String LimitStr = getStatusTypeLimitStr(statusType);
		String TimeLimitStr = getTimeLimit(TimeType.MONTH);
		System.out.println("######TimeLimitStr:"+TimeLimitStr);
		String hql = new String();
		String[] parameters={Id};
		
		switch (levelType)
		{
			case LAB:
			 hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where lab.labId=? "+TimeLimitStr+"  GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			  
				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where computerinfo.sn=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
		
			break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where userinfos.userid=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			break;
				
			default:
				break;
		}
		

		
		List list = HibernateUtil.executeQuery(hql, parameters);
		List listToday = getListToday(Id, statusType, levelType);
		list.addAll(listToday);
		return CapTrendData.capTrendDataByMonth(list);
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
	/****
	 * 根据用户选择展示趋势图
	 * @param Id
	 * @param statusType
	 * @param levelType
	 * @return
	 */
	private List<TrendData> getTrendListByUserSelect(String Id,
			StatusType statusType,LevelType levelType,String start,String end)
	{
		if(!valid(start)||!valid(end)){
			System.out.println("start or end format error from getTrendListByUserSelect");
			return null;
		}
			
		String LimitStr = getStatusTypeLimitStr(statusType);
		String TimeLimitStr = "and computerstatuses.datetime between '"+start+"' and '"+end+"'";//getTimeLimit(TimeType.MONTH);
		System.out.println("######TimeLimitStr:"+TimeLimitStr);
		String hql = new String();
		String[] parameters={Id};
		
		switch (levelType)
		{
			case LAB:
			 hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where lab.labId=? "+TimeLimitStr+"  GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			  
				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where computerinfo.sn=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
		
			break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where userinfos.userid=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			break;
				
			default:
				break;
		}
		

		
	    List list = HibernateUtil.executeQuery(hql, parameters);
		return CapTrendData.capTrendDataByUserSelect(list,start,end);
		//return CapTrendData.capTrendDataByMonth(list);
	}
	/**
	 * 没加时间限制
	 * ComputerstatusHistory 查询 按周获取数据
	 * 
	 * @param labId
	 * @param statusType
	 * @return
	 */
	private List<TrendData> getTrendListByWeek(String Id,
			StatusType statusType,LevelType levelType)
	{
		String LimitStr = getStatusTypeLimitStr(statusType);
		String TimeLimitStr=getTimeLimit(TimeType.WEEK);
		String hql = new String();
		switch (levelType)
		{
			case LAB:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where lab.labId=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where computerinfo.sn=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
		
			break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatusHistories computerstatuses "
					+ " where userinfos.userid=? "+TimeLimitStr+" GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			break;

			default:
				break;
		}
		
		String[] parameters = { Id};
		List list = HibernateUtil.executeQuery(hql, parameters);
		List listToday = getListToday(Id, statusType, levelType);
		list.addAll(listToday);

		return CapTrendData.capTrendDataByWeek(list);
	}
	private List getListToday(String Id,
			StatusType statusType,LevelType levelType)
	{
		String LimitStr = getStatusTypeLimitStr(statusType);
		String hql = new String();
		switch (levelType)
		{
			case LAB:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where lab.labId=?  GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where computerinfo.sn=?  GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
		
			break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d') "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where userinfos.userid=? GROUP BY DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d')";
			break;

			default:
				break;
		}
		
		String[] parameters = { Id};
		List list = HibernateUtil.executeQuery(hql, parameters);
		return list;
	}
	/**
	 * 获取时间筛选区间
	 * @param type
	 * 
	 * @return
	 */
	public static String getTimeLimit(TimeType timeType)
	{
		String timeLimit = new String();

		switch (timeType)
		{
			/**
			 * 上月
			 */
//			case 1:
//				Calendar start1 = Calendar.getInstance();
//				start1.setFirstDayOfWeek(Calendar.MONDAY);
//				start1.add(Calendar.MONTH, -1);
//				start1.set(Calendar.DAY_OF_MONTH, 1);
//
//				Calendar end1 = Calendar.getInstance();
//				end1.setFirstDayOfWeek(Calendar.MONDAY);
//				end1.set(Calendar.DAY_OF_MONTH, 1);
//				end1.add(Calendar.DAY_OF_MONTH, -1);
//				Date startTime1 = start1.getTime();
//				Date endTime1 = end1.getTime();
//				timeLimit = " and sta.date between '"
//						+ TimeUtil.convertDate(startTime1, "yyyy-MM-dd")
//						+ "' and '" + TimeUtil.convertDate(endTime1, "yyyy-MM-dd")+"'";
//
//				break;
				/**
				 * 本月
				 */
			case MONTH:
				Calendar start2 = Calendar.getInstance();
				start2.setFirstDayOfWeek(Calendar.MONDAY);
				start2.set(Calendar.DAY_OF_MONTH, 1);

				Calendar end2 = Calendar.getInstance();
				end2.setFirstDayOfWeek(Calendar.MONDAY);
				end2.add(Calendar.MONTH, 1);
				end2.set(Calendar.DAY_OF_MONTH, 1);
				end2.add(Calendar.DAY_OF_MONTH, -1);
				Date startTime2 = start2.getTime();
				Date endTime2 = end2.getTime();
				timeLimit = " and computerstatuses.datetime between '"
					+ TimeUtil.convertDate(startTime2, "yyyy-MM-dd")
					+ "' and '" + TimeUtil.convertDate(endTime2, "yyyy-MM-dd")+"'";
				break;
				/**
				 * 上周
				 */
//			case 3:
//				Calendar start3 = Calendar.getInstance(); // 起始日期，即上周的星期一
//				start3.setFirstDayOfWeek(Calendar.MONDAY);
//				start3.add(Calendar.WEEK_OF_YEAR, -1);
//				start3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//
//				Calendar end3 = Calendar.getInstance(); // 结束日期，即上周的星期日，右开区间
//				end3.setFirstDayOfWeek(Calendar.MONDAY);
//				end3.add(Calendar.WEEK_OF_YEAR, -1);
//				end3.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//
//				Date startTime3 = start3.getTime();
//				Date endTime3 = end3.getTime();
//				timeLimit = " and sta.date between '"
//					+ TimeUtil.convertDate(startTime3, "yyyy-MM-dd")
//					+ "' and '" + TimeUtil.convertDate(endTime3, "yyyy-MM-dd")+"'";
//				break;
				/**
				 * 本周
				 */
			case WEEK:
				Calendar start4 = Calendar.getInstance(); // 起始日期，即本周的星期一,左开区间
				start4.setFirstDayOfWeek(Calendar.MONDAY);
				start4.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

				Calendar end4 = Calendar.getInstance(); // 结束日期，即本周的星期日，右开区间
				end4.setFirstDayOfWeek(Calendar.MONDAY);
				end4.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

				Date startTime4 = start4.getTime();
				Date endTime4 = end4.getTime();
				
				timeLimit = " and computerstatuses.datetime between '"
					+ TimeUtil.convertDate(startTime4, "yyyy-MM-dd")
					+ "' and '" + TimeUtil.convertDate(endTime4, "yyyy-MM-dd")+"'";
				break;
			
			default:
				break;
		}
		return timeLimit;
	}

	/**
	 * 在Computerstatus中查询
	 * 
	 * @param labId
	 * @param statusType
	 * 
	 *            new com.netsecurity.bean.TrendData ()
	 * @return
	 */
	private List<TrendData> getTrendListByDay(String labId,
			StatusType statusType,LevelType levelType)
	{
		String LimitStr = getStatusTypeLimitStr(statusType);
		String hql= new String();
	//	String todayCtrlStr = "to_days(computerstatuses.datetime) = to_days(now())";
		switch (levelType)
		{
			case LAB:
					hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i' ) "
					+ " from Laboratory lab "
					+ " left  join lab.userinfos userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where lab.labId=? and to_days(computerstatuses.datetime) = to_days(now())" 
					+ "  GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/1800)";
					

				break;
			case COMPUTER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i' )  "
					+ " from Computerinfo computerinfo"
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where computerinfo.sn=? and to_days(computerstatuses.datetime) = to_days(now()) GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/1800)";
					
				
				break;
			case USER:
				hql = "select "
					+ LimitStr
					+ ",DATE_FORMAT( computerstatuses.datetime, '%H:%i' )  "
					+ " from Userinfo userinfos "
					+ " left  join  userinfos.usercomputers usercomputers "
					+ " left  join  usercomputers.computerinfo computerinfo "
					+ " left  join  computerinfo.computerstatuses computerstatuses "
					+ " where userinfos.userid=? and to_days(computerstatuses.datetime) = to_days(now()) GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/1800)";
				
				break;

			default:
				break;
		}
		
		// String hql = "select "
		// +
		// " sum(computerstatuses.memoryRate) ,DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d %H:%i' ) "
		// + " from Laboratory lab "
		// + " left  join lab.userinfos userinfos "
		// + " left  join  userinfos.usercomputers usercomputers "
		// + " left  join  usercomputers.computerinfo computerinfo "
		// + " left  join  computerinfo.computerstatuses computerstatuses "
		// +
		// " where lab.labId=? GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/420) ";

		String[] parameters = { String.valueOf(labId) };
		List list = HibernateUtil.executeQuery(hql, parameters);
		return CapTrendData.capTrendDataByDay(list);
	}

	public static void main(String[] args)
	{

		TrendDataService ts = new TrendDataService();
		List<TrendData> list = ts.getTrendListByUserSelect("124612260", StatusType.memoryRate, LevelType.USER,"2014-07-01 00:00:00","2014-07-06 23:23:23");
		//List<TrendData> list2 = ts.getTrendListByDay("4b4ed817b80c47f0", StatusType.cpuRate, LevelType.COMPUTER);
		
		
		System.out.println(list.toString());
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

	public String getChartTitle(TimeType timeType, StatusType statusType)
	{
		String title = new String();
		switch (timeType)
		{
			// case 1:
			// /**
			// * 上月
			// */
			// Calendar start1=Calendar.getInstance();
			// start1.setFirstDayOfWeek(Calendar.MONDAY);
			// start1.add(Calendar.MONTH, -1);
			// start1.set(Calendar.DAY_OF_MONTH, 1);
			//				
			// Calendar end1=Calendar.getInstance();
			// end1.setFirstDayOfWeek(Calendar.MONDAY);
			// end1.set(Calendar.DAY_OF_MONTH, 1);
			// end1.add(Calendar.DAY_OF_MONTH, -1);
			// Date startTime1 = start1.getTime();
			// Date endTime1 = end1.getTime();
			// title = TimeUtil.convertDate(startTime1, "yyyy-MM-dd") + "~"
			// + TimeUtil.convertDate(endTime1, "yyyy-MM-dd");
			// break;
			case MONTH:
				/**
				 * 本月
				 */
				Calendar start2 = Calendar.getInstance();
				start2.setFirstDayOfWeek(Calendar.MONDAY);
				start2.set(Calendar.DAY_OF_MONTH, 1);

				Calendar end2 = Calendar.getInstance();
				end2.setFirstDayOfWeek(Calendar.MONDAY);
				end2.add(Calendar.MONTH, 1);
				end2.set(Calendar.DAY_OF_MONTH, 1);
				end2.add(Calendar.DAY_OF_MONTH, -1);
				Date startTime2 = start2.getTime();
				Date endTime2 = end2.getTime();
				title = TimeUtil.convertDate(startTime2, "yyyy-MM-dd") + "~"
						+ TimeUtil.convertDate(endTime2, "yyyy-MM-dd");
				break;
			// case 3:
			// /**
			// *上周
			// */
			// Calendar start3 = Calendar.getInstance(); // 起始日期，即上周的星期一
			// start3.setFirstDayOfWeek(Calendar.MONDAY);
			// start3.add(Calendar.WEEK_OF_YEAR, -1);
			// start3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			//				
			// Calendar end3 = Calendar.getInstance(); // 结束日期，即上周的星期日，右开区间
			// end3.setFirstDayOfWeek(Calendar.MONDAY);
			// end3.add(Calendar.WEEK_OF_YEAR, -1);
			// end3.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			//
			// Date startTime3 = start3.getTime();
			// Date endTime3 = end3.getTime();
			// title = TimeUtil.convertDate(startTime3, "yyyy-MM-dd") + "~"
			// + TimeUtil.convertDate(endTime3, "yyyy-MM-dd");
			// break;
			case WEEK:
				/**
				 * 本周
				 */
				Calendar start4 = Calendar.getInstance(); // 起始日期，即本周的星期一,左开区间
				start4.setFirstDayOfWeek(Calendar.MONDAY);
				start4.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

				Calendar end4 = Calendar.getInstance(); // 结束日期，即本周的星期一，右开区间
				end4.setFirstDayOfWeek(Calendar.MONDAY);
				end4.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

				Date startTime4 = start4.getTime();
				Date endTime4 = end4.getTime();
				title = TimeUtil.convertDate(startTime4, "yyyy-MM-dd") + "~"
						+ TimeUtil.convertDate(endTime4, "yyyy-MM-dd");
				break;
			case DAY:
				Calendar today = Calendar.getInstance();
				Date todayTime = today.getTime();
				title = TimeUtil.convertDate(todayTime, "yyyy-MM-dd");
			default:
				break;
		}
		return addExtraTitle(title, statusType);
	}

	private String addExtraTitle(String title, StatusType statusType)
	{
		String extraTitle = new String();
		switch (statusType)
		{
			case memoryRate:
				extraTitle = "内存利用率：" + title;
				break;
			case appNum:
				extraTitle = "应用数：" + title;
				break;
			case cpuRate:
				extraTitle = "CPU利用率：" + title;
				break;
			case keyboardType:
				extraTitle = "键盘击键数：" + title;
				break;
			case downLoad:
				extraTitle = "下行流量：" + title;
				break;
			case mouseLeftType:
				extraTitle = "鼠标左键击键数：" + title;
				break;
			case mouseRightType:
				extraTitle = "鼠标右键击键数：" + title;
				break;
			case threadNum:
				extraTitle = "进程数：" + title;
				break;
			case upLoad:
				extraTitle = "上行流量：" + title;
				break;
			default:
				break;
		}

		return extraTitle;
	}

	@Override
	public String getChartTitle(String start, String end, StatusType statusType) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		
		try {
			date1 = sdf.parse(start);
			Date date2=sdf.parse(end);
			start=sdf.format(date1);
			end=sdf.format(date2);
		} catch (ParseException e) {
			start="非法开始时间";
			end="非法结束时间";
		}
		
		String title=start+"~"+end;
		
		return addExtraTitle(title, statusType);
	}
}