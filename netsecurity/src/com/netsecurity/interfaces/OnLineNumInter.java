package com.netsecurity.interfaces;

import java.util.List;

import com.netsecurity.bean.ComputerOnlineNumber;
import com.netsecurity.bean.OnLineNum;
import com.netsecurity.bean.UserComputerOnlineNumber;
import com.netsecurity.bean.UserTopStatus;

public interface OnLineNumInter
{
	public List<UserComputerOnlineNumber> getUsersOnlineStaById(
			String userId);
	/**
	 * 获得所有实验室在线情况
	 *  @param pageSize:显示页面的数目
	 * @param pageNow：当前第几页
	 * * @return  List<OnLineNumInter>
	 */
	public List<OnLineNum> getLabsOnlineNumByPage(int pageNow,int pageSize);
	/**
	 * 获得某实验室学生在线情况
	 *  @param labId:实验室id
	 *  @param pageSize:显示页面的数目
	 * @param pageNow：当前第几页
	 * * @return  List<OnLineNumInter>
	 */
	public List<UserComputerOnlineNumber> getStudentsOnlineNumByPage(
			int labId, int pageNow, int pageSize);
	
	/**
	 * 获得某实验室学生在线情况
	 *  @param Username:学生姓名 注意要模糊查询
	 *  @param pageSize:显示页面的数目
	 * @param pageNow：当前第几页
	 * * @return  List<OnLineNumInter>
	 */
	public List<UserComputerOnlineNumber> getStudentsOnlineNumByPage(
			String Username, int pageNow, int pageSize);
	/**
	 * 获得某用户某电脑在线情况
	 *  @param userId:用户室id
	 *  @param pageSize:显示页面的数目
	 * @param pageNow：当前第几页
	 * * @return  List<OnLineNumInter>
	 */
	public List<ComputerOnlineNumber> getComputersOnlineNumByPage(
			String userId, int pageNow, int pageSize);
	/**
	 * 获得某用户在某段时间内11项指标使用频率最高的4个时间段以及相应的数值和实验室内排名
	 * 9项指标分别为：内存利用率，cpu利用率，鼠标左键点击数，鼠标右键点击数，键盘敲击数，进程数，任务数，上行流量，下行流量，开机时间，关机时间
	 *  @param userId:用户id
	 *  @param startTime:开始时间
	 * @param endTime：结束时间
	 * * @return  List<OnLineNumInter>
	 */
	public List<UserTopStatus> getUserTopStatus(
			String userId, String startTime, String endTime);
	/****
	 * 获得某组用户某电脑在线情况
	 * @param groupId
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<UserComputerOnlineNumber> getGroupStudentsOnlineNumByPage(
			int groupId,int pageNow, int pageSize) ;
}
