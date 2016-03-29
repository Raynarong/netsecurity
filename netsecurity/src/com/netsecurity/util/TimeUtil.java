package com.netsecurity.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author USER
 *
 */
public class TimeUtil {


	
	/**
	 * 将日期对象格式化为字符串形式
	 * @param date java.util.Date类型的日期对象
	 * @param patter 格式化字符串
	 * @return
	 */
	public static String convertDate(Date date, String patter){
		String result;
		SimpleDateFormat sdf = new SimpleDateFormat(patter);//进行格式化（日期 -> 文本)
		try{
			result = sdf.format(date);//将给定的 Date 格式化为日期/时间字符串
		}catch(Exception e){
			result="";
		}
		return result;
	}
	/**
	 * 获取时间筛选区间
	 * @param type
	 * 
	 * @return
	 */
	public static String getTimeLimit(int type)
	{
		String timeLimit = new String();

		switch (type)
		{
			case 1:
				Calendar start1 = Calendar.getInstance();
				start1.setFirstDayOfWeek(Calendar.MONDAY);
				start1.add(Calendar.MONTH, -1);
				start1.set(Calendar.DAY_OF_MONTH, 1);

				Calendar end1 = Calendar.getInstance();
				end1.setFirstDayOfWeek(Calendar.MONDAY);
				end1.set(Calendar.DAY_OF_MONTH, 1);
				end1.add(Calendar.DAY_OF_MONTH, -1);
				Date startTime1 = start1.getTime();
				Date endTime1 = end1.getTime();
				timeLimit = " and sta.date between '"
						+ TimeUtil.convertDate(startTime1, "yyyy-MM-dd")
						+ "' and '" + TimeUtil.convertDate(endTime1, "yyyy-MM-dd")+"'";

				break;
			case 2:
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
				timeLimit = " and sta.date between '"
					+ TimeUtil.convertDate(startTime2, "yyyy-MM-dd")
					+ "' and '" + TimeUtil.convertDate(endTime2, "yyyy-MM-dd")+"'";
				break;
			case 3:
				Calendar start3 = Calendar.getInstance(); // 起始日期，即上周的星期一
				start3.setFirstDayOfWeek(Calendar.MONDAY);
				start3.add(Calendar.WEEK_OF_YEAR, -1);
				start3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

				Calendar end3 = Calendar.getInstance(); // 结束日期，即上周的星期日，右开区间
				end3.setFirstDayOfWeek(Calendar.MONDAY);
				end3.add(Calendar.WEEK_OF_YEAR, -1);
				end3.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

				Date startTime3 = start3.getTime();
				Date endTime3 = end3.getTime();
				timeLimit = " and sta.date between '"
					+ TimeUtil.convertDate(startTime3, "yyyy-MM-dd")
					+ "' and '" + TimeUtil.convertDate(endTime3, "yyyy-MM-dd")+"'";
				break;
			case 4:
				Calendar start4 = Calendar.getInstance(); // 起始日期，即本周的星期一,左开区间
				start4.setFirstDayOfWeek(Calendar.MONDAY);
				start4.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

				Calendar end4 = Calendar.getInstance(); // 结束日期，即本周的星期日，右开区间
				end4.setFirstDayOfWeek(Calendar.MONDAY);
				end4.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

				Date startTime4 = start4.getTime();
				Date endTime4 = end4.getTime();
				timeLimit = " and sta.date between '"
					+ TimeUtil.convertDate(startTime4, "yyyy-MM-dd")
					+ "' and '" + TimeUtil.convertDate(endTime4, "yyyy-MM-dd")+"'";
				break;
			case 5:
				timeLimit="";
				break;
			default:
				break;
		}
		return timeLimit;
	}
	
}
