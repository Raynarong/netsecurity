package com.netsecurity.interfaces;

import java.util.List;

import com.netsecurity.bean.GroupCheck;
import com.netsecurity.bean.GroupLabCheckList;
import com.netsecurity.bean.Groupinfo;
import com.netsecurity.bean.Userinfo;

public interface GroupinfoInter {
	/***
	 * 添加组，并填写组员，leader 后两者可以为空
	 * @param groupName
	 * @param leaderId
	 * @param groupMembers 组员字符串 用逗号隔开例如 124611162,14611161,124611160
	 * @return false 失败，true 成功
	 */
	public boolean addGroup(String groupName,String leaderId);
	/***
	 * 删除组
	 * @param groupName
	 * @return false 失败 true 成功
	 */
	public boolean deleteGroup (String groupName);
	/****
	 * 更新组
	 * @param groupName 组名
	 * @param leaderId 组长id
	 * @param members 组员字符串
	 * @return
	 */
	public boolean updateGroup(String groupName,String leaderId,String members);
	/****
	 * 显示组记录
	 * @param searchStr 组名（查询使用，不查询时可以为空）
	 * @param pageNow
	 * @param itemCount
	 * @return 
	 */
	public List<Groupinfo> getGroupByPage(String searchStr,int pageNow,int itemCount);
	/***
	 * 分权限显示组的记录 userType 为0的全部显示，为1的显示管理的组
	 * @param searchStr
	 * @param userType
	 * @param userId
	 * @param pageNow
	 * @param itemCount
	 * @return
	 */
	public List<Groupinfo> getGroupByPage(String searchStr,int userType,String userId, int pageNow,
			int itemCount);
	/***
	 * 获取组数目
	 * @param searchStr
	 * @return
	 */
	public int getGroupNum(String searchStr);
	
	public int getGroupMumberNum(int groupId);
	/***
	 * 获得组员
	 * @param groupId 
	 * @return
	 */
	public List<Userinfo> getGroupMembers(int groupId);
	/**
	 * 获得组员名单
	 * @param groupId
	 * @return
	 */
	public String getGroupMemberNamse(int groupId);
	/***
	 * 通过实验室id，组id，获取某个labId实验室成员，是否在一某个groupid组内
	 * @param labId,groupId
	 * @return
	 */
	public List<GroupCheck> getGroupCheckInLabSql(int labId,int groupId);
	/****
	 * 获取所有实验室同学，在groupId组的情况
	 * @return
	 */
	public List<GroupLabCheckList>getGroupChekcInAllLab(int groupId);
	/**
	 * 获取组长信息
	 * @param groupId
	 * @return
	 */
	public Userinfo getGroupLeader(int groupId);
	
	/***
	 * 获取组信息
	 * @param groupid
	 * @return
	 */
	public Groupinfo getGroupinfo(int groupid);
	
	/**
	 * 删除某组全部成员
	 * @param groupId
	 * @return
	 */
	public boolean deleteGroupMember(int groupId);
	/***
	 * 添加组员
	 * @param groupId
	 * @param userids
	 * @return
	 */
	public boolean addGroupMember(int groupId,String[]userids);
	/***
	 * 编辑组员
	 * @param groupId
	 * @param userids
	 * @return
	 */
	public boolean editGroupMember(int groupId,String[]userids);
	/***
	 * 更换组长
	 * @param groupId
	 * @param leaderId
	 * @return
	 */
	public boolean updateGroupLeader(int groupId,String leaderId);
	/***
	 * 更改组名
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	public boolean updateGroupName(int groupId,String groupName);
}
