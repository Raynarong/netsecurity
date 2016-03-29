package com.netsecurity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.netsecurity.bean.TrendData;
import com.netsecurity.util.TimeUtil;

public class CapTrendData
{
//	public static List<TrendData> capTrendDataByDay(List list)
//	{
//		List<TrendData> listData = new ArrayList<TrendData>();
//		
//		Date dayvartime = new Date();
//		Calendar dayvar = Calendar.getInstance(); // 
//		dayvar.set(Calendar.HOUR_OF_DAY, 0);
//		dayvar.set(Calendar.MINUTE, 0);
//		dayvar.set(Calendar.SECOND, 0);
//
//		/**
//		 * 构造List供展示
//		 */
//		for (int i = 0; i < 48; i++)
//		{
//			TrendData td = new TrendData();
//			dayvartime = dayvar.getTime();
//			td.setData((long)0);
//			td.setTime(TimeUtil.convertDate(dayvartime, "HH:mm"));
//			listData.add(td);
//			dayvar.add(Calendar.MINUTE, 30);
//		}
//		System.out.println(listData.size());
//		List<TrendData> listData2 = new ArrayList<TrendData>();
//		for (int i = 0; i < list.size(); i++)
//		{
//			Object[] obj = (Object[]) list.get(i);
//			if (null != obj && obj[0] != null && obj[1] != null)
//			{
//				TrendData td = new TrendData();
//				td.setData(obj[0]);
//				td.setTime(obj[1].toString());
//				listData2.add(td);
//			}
//		}
//		System.out.println(listData2.size());
//		for (int i = 0,j=0; i < listData.size()&&j<listData2.size(); i++)
//		{
//			if (listData.get(i).getTime().equals(listData2.get(j).getTime()))
//			{
//				listData.get(i).setData(listData2.get(j).getData());
//				j++;
//			}
//		}
//		System.out.println(listData.size());
//		return listData;
//	}

//	public static List<TrendData> capTrendDataByDay(List list)
//	{
//		List<TrendData> listData = new ArrayList<TrendData>();
//		
//		Date dayvartime = new Date();
//		Calendar dayvar = Calendar.getInstance(); // 
//		dayvar.set(Calendar.HOUR_OF_DAY, 0);
//		dayvar.set(Calendar.MINUTE, 0);
//		dayvar.set(Calendar.SECOND, 0);
//
//		/**
//		 * 构造List供展示
//		 */
//		for (int i = 0; i < 48; i++)
//		{
//			TrendData td = new TrendData();
//			dayvartime = dayvar.getTime();
//			td.setData((long)0);
//			td.setTime(TimeUtil.convertDate(dayvartime, "HH:mm"));
//			listData.add(td);
//			dayvar.add(Calendar.MINUTE, 30);
//		}
//		System.out.println(listData.size());
//		for (int i = 0; i < list.size(); i++)
//		{
//			Object[] obj = (Object[]) list.get(i);
//			if (null != obj && obj[0] != null && obj[1] != null)
//			{
//				TrendData td = new TrendData();
//				td.setData(obj[0]);
//				td.setTime(obj[1].toString());
//				for (TrendData trend : listData)
//				{
//					/**
//					 * 准备删！
//					 */
//					//System.out.println(trend.getTime());
//					//System.out.println(td.getTime());
//					//System.out.println("**");
//					if (trend.getTime().equals(td.getTime()))
//					{
//						trend.setData(td.getData());
//					}
//				}
//
//			}
//		}
//
//		System.out.println(listData.size());
//		return listData;
//
//	}

	public static List<TrendData> capTrendDataByDay(List list)
	{
		List<TrendData> listData = new ArrayList<TrendData>();
		
		Date dayvartime = new Date();
		Calendar dayvar = Calendar.getInstance(); // 
		dayvar.set(Calendar.HOUR_OF_DAY, 0);
		dayvar.set(Calendar.MINUTE, 0);
		dayvar.set(Calendar.SECOND, 0);

		/**
		 * 构造List供展示
		 */
		for (int i = 0; i < 1440; i++)
		{
			TrendData td = new TrendData();
			dayvartime = dayvar.getTime();
			td.setData((long)0);
			td.setTime(TimeUtil.convertDate(dayvartime, "HH:mm"));
			listData.add(td);
			dayvar.add(Calendar.MINUTE, 1);
		}
		System.out.println(listData.size());
		for (int i = 0; i < list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			if (null != obj && obj[0] != null && obj[1] != null)
			{
				TrendData td = new TrendData();
				td.setData(obj[0]);
				td.setTime(obj[1].toString());
				for (TrendData trend : listData)
				{
					/**
					 * 准备删！
					 */
//					System.out.println(trend.getTime());
//					System.out.println(td.getTime());
//					System.out.println("**");
					if (trend.getTime().equals(td.getTime()))
					{
						trend.setData(td.getData());
					}
				}

			}
		}

		System.out.println(listData.size());
		return listData;

	}
	
	public static List<TrendData> capTrendDataByWeek(List list)
	{
		List<TrendData> listData = new ArrayList<TrendData>();

		Date dayvartime = new Date();
		Calendar dayvar = Calendar.getInstance(); // 
		dayvar.setFirstDayOfWeek(Calendar.MONDAY);
		// dayvar.set(Calendar.DAY_OF_WEEK, 1);
		dayvar.set(Calendar.DAY_OF_WEEK, dayvar.getFirstDayOfWeek());
		/**
		 * 构造List供展示
		 */
		for (int i = 0; i < 7; i++)
		{
			TrendData td = new TrendData();
			dayvartime = dayvar.getTime();
			td.setData((long)0);
			td.setTime(TimeUtil.convertDate(dayvartime, "yyyy-MM-dd"));
			listData.add(td);
			dayvar.add(Calendar.DAY_OF_WEEK, 1);
		}
		System.out.println(listData);
		for (int i = 0; i < list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			if (null != obj && obj[0] != null && obj[1] != null)
			{
				TrendData td = new TrendData();
				td.setData(obj[0]);
				td.setTime(obj[1].toString());
				for (TrendData trend : listData)
				{
					/**
					 * 准备删！
					 */
					System.out.println(trend.getTime());
					System.out.println(td.getTime());
					System.out.println("**");
					if (trend.getTime().equals(td.getTime()))
					{
						trend.setData(td.getData());
					}
				}

			}
		}

		System.out.println(listData.size());
		return listData;
	}
	
	public static List<TrendData> capTrendDataByMonth(List list)
	{
		List<TrendData> listData = new ArrayList<TrendData>();

		Date dayvartime = new Date();
		Calendar dayvar = Calendar.getInstance(); // 
		dayvar.setFirstDayOfWeek(Calendar.MONDAY);
		dayvar.set(Calendar.DAY_OF_MONTH, 1);
		// dayvar.set(Calendar.DAY_OF_WEEK, 1);
		/**
		 * 构造List供展示
		 */
		for (int i = 0; i < dayvar.getActualMaximum(Calendar.DATE); i++)
		{
			TrendData td = new TrendData();
			dayvartime = dayvar.getTime();
			td.setData((long)0);
			td.setTime(TimeUtil.convertDate(dayvartime, "yyyy-MM-dd"));
			listData.add(td);
			dayvar.add(Calendar.DAY_OF_MONTH, 1);
		}
		System.out.println(listData);
		for (int i = 0; i < list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			if (null != obj && obj[0] != null && obj[1] != null)
			{
				TrendData td = new TrendData();
				td.setData(obj[0]);
				td.setTime(obj[1].toString());
				for (TrendData trend : listData)
				{
					//System.out.println(trend.getTime());
					//System.out.println(td.getTime());
					//System.out.println("**");
					if (trend.getTime().equals(td.getTime()))
					{
						trend.setData(td.getData());
					}
				}

			}
		}

		System.out.println(listData.size());
		return listData;
	}
	/***
	 * 
	 * @param list 传入list
	 * @param start 开始时间 
	 * @param end 结束时间
	 * @return
	 */
	public static List<TrendData> capTrendDataByUserSelect(List list,String start,String end)
	{
		List<TrendData> listData = new ArrayList<TrendData>();

		Date dayvartime = new Date();
		Calendar dayvar = Calendar.getInstance(); // 
		dayvar.setFirstDayOfWeek(Calendar.MONDAY);
		dayvar.set(Calendar.DAY_OF_MONTH, 1);
		// dayvar.set(Calendar.DAY_OF_WEEK, 1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=new Date(),d2=new Date();
		try {
			 d1=sdf.parse(start);
			 dayvar.setTime(d1);
			 d2=sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		//此处+1是为了保证最后一天不被拉下
		long days=((d2.getTime()-d1.getTime())/1000/60/60+1)/24;
		
		/**
		 * 构造List供展示
		 */
		for (int i = 0; i < days; i++)
		{
			TrendData td = new TrendData();
			dayvartime = dayvar.getTime();
			td.setData((long)0);
			td.setTime(TimeUtil.convertDate(dayvartime, "yyyy-MM-dd"));
			listData.add(td);
			dayvar.add(Calendar.DAY_OF_MONTH, 1);
		}
		System.out.println(listData);
		for (int i = 0; i < list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			if (null != obj && obj[0] != null && obj[1] != null)
			{
				TrendData td = new TrendData();
				td.setData(obj[0]);
				td.setTime(obj[1].toString());
				for (TrendData trend : listData)
				{
					//System.out.println(trend.getTime());
					//System.out.println(td.getTime());
					//System.out.println("**");
					if (trend.getTime().equals(td.getTime()))
					{
						trend.setData(td.getData());
					}
				}

			}
		}

		System.out.println(listData.size());
		return listData;
	}
}
