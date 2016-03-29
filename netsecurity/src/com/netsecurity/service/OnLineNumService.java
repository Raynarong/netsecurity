package com.netsecurity.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.UniqueConstraint;

import com.netsecurity.bean.ComputerOnlineNumber;
import com.netsecurity.bean.OnLineNum;
import com.netsecurity.bean.UserComputerOnlineNumber;
import com.netsecurity.bean.UserTopStatus;
import com.netsecurity.enums.StatusType;
import com.netsecurity.interfaces.OnLineNumInter;
import com.netsecurity.util.HibernateUtil;

public class OnLineNumService implements OnLineNumInter {
	/**
	 * 获得所有实验室在线情况 分页查询
	 * 
	 * @param pageSize
	 *            :显示页面的数目
	 * @param pageNow
	 *            ：当前第几页
	 * @return
	 */
	public List<OnLineNum> getLabsOnlineNumByPage(int pageNow, int pageSize) {
		/**
		 * 直接用类封 count()返回的数据类型是Long
		 */
		String hql = "select new com.netsecurity.bean.OnLineNum ("
				+ " lab.labId,lab.labName,sum(case when  userinfos.userid is not null and usercomputers.computerinfo is not null and  computerinfo.online=true then 1 else 0 end) "
				+ " ,sum(case when  userinfos.userid is not null  and usercomputers.computerinfo is not null and computerinfo.online=false   then 1 else 0 end) ,count(userinfos.userid))"
				+ " from Laboratory lab left  join  lab.userinfos userinfos  left join userinfos.usercomputers usercomputers  left join usercomputers.computerinfo computerinfo "
				+ "group by lab.labId";
		String[] parameters = {};
		List<OnLineNum> list = HibernateUtil.executeQueryByPage(hql,
				parameters, pageSize, pageNow);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}

	/**
	 * 获得某实验室学生在线情况
	 * 
	 * @param labId
	 *            :实验室id
	 * @param pageSize
	 *            :显示页面的数目
	 * @param pageNow
	 *            ：当前第几页 * @return List<OnLineNumInter>
	 */

	public List<ComputerOnlineNumber> getComputersOnlineNumByPage(
			String userId, int pageNow, int pageSize) {
		String hql = "select new com.netsecurity.bean.ComputerOnlineNumber ("
				+ " computerinfo.sn,computerinfo.online) "
				+ " from Userinfo userinfos left  join  "
				+ " userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " where userinfos.userid=? group by computerinfo.sn";
		// + "group by lab.labId";

		String[] parameters = { String.valueOf(userId) };
		List<ComputerOnlineNumber> list = HibernateUtil.executeQueryByPage(hql,
				parameters, pageSize, pageNow);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}

	/**
	 * 获取用户在线，离线情况的列表
	 */
	public List<UserComputerOnlineNumber> getStudentsOnlineNumByPage(int labId,
			int pageNow, int pageSize) {
		/**
		 * 直接用类封 count()返回的数据类型是Long
		 */
		String hql = "select new com.netsecurity.bean.UserComputerOnlineNumber ("
				+ " userinfos.userid,userinfos.username,sum(case when computerinfo.online=1 then 1 else 0 end) "
				+ " ,sum(case when computerinfo.online=0 then 1 else 0 end) ,sum(case when computerinfo.online=1 then 1 else 0 end)+sum(case when computerinfo.online=0 then 1 else 0 end) )"
				+ " from Laboratory lab join  lab.userinfos userinfos left  join  "
				+ " userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " where lab.labId=? group by userinfos.userid";
		// + "group by lab.labId";

		String[] parameters = { String.valueOf(labId) };
		List<UserComputerOnlineNumber> list = HibernateUtil.executeQueryByPage(
				hql, parameters, pageSize, pageNow);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}
	
	/**
	 * 获取某一个组的用户在线，离线情况的列表
	 */
	public List<UserComputerOnlineNumber> getGroupStudentsOnlineNumByPage(int groupId,
			int pageNow, int pageSize) {
		/**
		 * 直接用类封 count()返回的数据类型是Long
		 */
		String hql = "select new com.netsecurity.bean.UserComputerOnlineNumber ("
				+ " userinfos.userid,userinfos.username,sum(case when computerinfo.online=1 then 1 else 0 end) "
				+ " ,sum(case when computerinfo.online=0 then 1 else 0 end) ,sum(case when computerinfo.online=1 then 1 else 0 end)+sum(case when computerinfo.online=0 then 1 else 0 end) )"
				+ " from Groupinfo groupinfo join  groupinfo.groupusers groupuser left  join  "
				+ " groupuser.userinfo userinfos left join userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " where groupinfo.groupid=? group by userinfos.userid";
		// + "group by lab.labId";

		String[] parameters = { String.valueOf(groupId) };
		List<UserComputerOnlineNumber> list = HibernateUtil.executeQueryByPage(
				hql, parameters, pageSize, pageNow);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}
	public List<UserComputerOnlineNumber> getUsersOnlineStaById(String userId) {
		/**
		 * 直接用类封 count()返回的数据类型是Long
		 */
		String hql = "select new com.netsecurity.bean.UserComputerOnlineNumber ("
				+ " userinfos.userid,userinfos.username,sum(case when computerinfo.online=1 then 1 else 0 end) "
				+ " ,sum(case when computerinfo.online=0 then 1 else 0 end) ,count(*) )"
				+ " from Userinfo userinfos left  join  "
				+ " userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " where userinfos.userid=? group by userinfos.userid";
		// + "group by lab.labId";

		String[] parameters = { userId };
		List<UserComputerOnlineNumber> list = HibernateUtil.executeQuery(hql,
				parameters);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}

	public List<UserComputerOnlineNumber> getStudentsOnlineNumByPage(
			String Username, int pageNow, int pageSize) {
		String searchStr = "'%" + Username + "%'";
		System.out.println(searchStr);
		String hql = "select new com.netsecurity.bean.UserComputerOnlineNumber ("
				+ " userinfos.userid,userinfos.username,sum(case when computerinfo.online=1 then 1 else 0 end) "
				+ " ,sum(case when computerinfo.online=0 then 1 else 0 end) ,count(*) )"
				+ " from Userinfo userinfos left  join  "
				+ " userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " where userinfos.username like "
				+ searchStr
				+ " group by userinfos.userid";
		// + "group by lab.labId";

		String[] parameters = {};
		List<UserComputerOnlineNumber> list = HibernateUtil.executeQueryByPage(
				hql, parameters, pageSize, pageNow);
		// List<OnLineNum> list =HibernateUtil.executeQuery(hql, parameters);
		return list;
	}

	/**
 * 
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserTopStatus> getUserTopStatus(String userId,
			String startTime, String endTime) {
		int i=0;
		
		
		
		
//		if(null==startTime||null==endTime)
//		{
//			timeStr="";
//		}
		//如果输入有误，设置极限数据
		if(startTime==null||startTime.length()==0)
			startTime="1970-1-1";
		if(endTime==null||endTime.length()==0)
			endTime="2100-12-12";
		if(startTime.equals(endTime)){
			DateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
			
			Date t1=null;
			
			try {
				t1=dd.parse(endTime);
				Calendar ca=Calendar.getInstance();
				ca.setTime(t1);
				ca.set(Calendar.DATE, ca.get(Calendar.DATE) + 1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				endTime= sdf.format(ca.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String timeStr = "and computerstatuses.datetime between '"+startTime+ "'  and '"+endTime+"'";
		List<UserTopStatus> ustList = new ArrayList<UserTopStatus>();
		for (StatusType st : StatusType.values()) {
			String LimitStr = getStatusTypeLimitStr(st);
			String hql = " select "
				+ LimitStr
				+ " ,DATE_FORMAT( computerstatuses.datetime, '%Y-%m-%d %H:00' )"
				+ " from Userinfo userinfos left  join  "
				+ " userinfos.usercomputers usercomputers left  join  usercomputers.computerinfo computerinfo "
				+ " left  join  computerinfo.computerstatusHistories computerstatuses "
				+ " where userinfos.userid=? "+timeStr
				+ " GROUP BY floor(UNIX_TIMESTAMP(computerstatuses.datetime)/3600)"
				+ " order by  " + LimitStr + "  desc";
			String[] parameters = { userId };

			List item = HibernateUtil.executeQueryByPage(hql, parameters, 4, 1);
			UserTopStatus ust = capUserTopStatus(item);
			
			String limitTitle = getStatusTitle(st);
			ust.setOpera(limitTitle);
			ustList.add(ust);
			
			System.out.println("count :"+i++);
		}
		
		
		return ustList;
	}

	private UserTopStatus capUserTopStatus(List item) {

		UserTopStatus ust = new UserTopStatus("no time","no data","no time","no data","no time","no data","no time","no data");
		
		// ust.setTop1Value(item.get(0).)
		System.out.println(item.size());
		
		for (int i = 0; i < item.size(); i++) {
		
			Object[] obj = (Object[]) item.get(i);
			if (i == 0) {
				if(null!= obj[1])
				ust.setTimeTop1((String) obj[1]);
	
				if(null!= obj[0])
				ust.setTop1Value(obj[0].toString());
		
			} else if (i == 1) {
				if(null!= obj[1])
				ust.setTimeTop2((String) obj[1]);
		
				if(null!= obj[0])
				ust.setTop2Value(obj[0].toString());
		
			} else if (i == 2) {
				if(null!= obj[1])
				ust.setTimeTop3((String) obj[1]);
		
				if(null!= obj[0])
				
				ust.setTop3Value(obj[0].toString());
		
			} else {
				if(null!= obj[1])
				ust.setTimeTop4((String) obj[1]);
			
				if(null!= obj[0])
				
				ust.setTop4Value(obj[0].toString());
		
			}
		}
		return ust;
	}

	private String getStatusTypeLimitStr(StatusType statusType) {
		String LimitStr = "";
		switch (statusType) {
		case memoryRate:
			LimitStr = "floor(avg(computerstatuses.memoryRate))";
			break;
		case appNum:
			LimitStr = "floor(avg(computerstatuses.appNum))";
			break;
		case cpuRate:
			LimitStr = "floor(avg(computerstatuses.cpuRate))";
			break;
		case keyboardType:
			LimitStr = "sum(computerstatuses.keyboardType)";
			break;
		case downLoad:
			LimitStr = "sum(computerstatuses.download)/1048676";
			break;
		case mouseLeftType:
			LimitStr = "sum(computerstatuses.mouseLeftType)";
			break;
		case mouseRightType:
			LimitStr = "sum(computerstatuses.mouseRightType)";
			break;
		case threadNum:
			LimitStr = "floor(avg(computerstatuses.threadNum))";
			break;
		case upLoad:
			LimitStr = "sum(computerstatuses.upload)/1048676";
			break;
		default:
			break;
		}
		return LimitStr;
	}
	private String getStatusTitle(StatusType statusType) {
		String LimitStr = "";
	
		switch (statusType) {
		case memoryRate:
			LimitStr = "内存利用率";
			break;
		case appNum:
			LimitStr = "任务数";
			break;
		case cpuRate:
			LimitStr = "cpu利用率";
			break;
		case keyboardType:
			LimitStr = "键盘敲击数";
			break;
		case downLoad:
			LimitStr = "下行流量(MB)";
			break;
		case mouseLeftType:
			LimitStr = "鼠标左键点击数";
			break;
		case mouseRightType:
			LimitStr = "鼠标右键点击数";
			break;
		case threadNum:
			LimitStr = "进程数";
			break;
		case upLoad:
			LimitStr = "上行流量(MB)";
			break;
		default:
			break;
		}
		return LimitStr;
	}
	public static void main(String[] args) {
		OnLineNumService os = new OnLineNumService();
//		os.getUserTopStatus("124611162", "a", "b");
//		System.out.println("a");

		// OnLineNumService os = new OnLineNumService();
		// List<UserComputerOnlineNumber> l = os.getStudentsOnlineNumByPage("三",
		// 1, 2);
		// System.out.println(l.size());
		List<UserComputerOnlineNumber> ucon=os.getGroupStudentsOnlineNumByPage(1,1,100);
		for(UserComputerOnlineNumber un:ucon){
			System.out.println(un.getUsername());
		}

	}
}
