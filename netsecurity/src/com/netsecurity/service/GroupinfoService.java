package com.netsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.netsecurity.bean.GroupCheck;
import com.netsecurity.bean.GroupLabCheckList;
import com.netsecurity.bean.Groupinfo;
import com.netsecurity.bean.Groupuser;
import com.netsecurity.bean.Laboratory;
import com.netsecurity.bean.Userinfo;
import com.netsecurity.interfaces.GroupinfoInter;
import com.netsecurity.interfaces.LaboratoryInter;
import com.netsecurity.util.HibernateUtil;

public class GroupinfoService implements GroupinfoInter{

//	@Override
	public boolean addGroup(String groupName, String leaderId) {
		
		//
		
		Groupinfo g;
		if(leaderId!=null &&leaderId.length()>0)
		{
			Userinfo u=new Userinfo(leaderId);
			g=new Groupinfo(u,groupName);
		}else{
			g=new Groupinfo(groupName);
		}

		return HibernateUtil.save2(g);
	}

	@Override
	public boolean deleteGroup(String groupName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Groupinfo> getGroupByPage(String searchStr, int pageNow,
			int itemCount) {
		String hql="from Groupinfo group where group.groupName like '%"+filterStr(searchStr)+"%'  ";
		String parameters[]={};
		List list=HibernateUtil.executeQueryByPage(hql, parameters, itemCount, pageNow);
		return list;
	}
	
	public List<Groupinfo> getGroupByPage(String searchStr,int userType,String userId, int pageNow,
			int itemCount){
		if(userType==0)//超级管理员
			return getGroupByPage( searchStr,  pageNow,itemCount);
		else if(userType==1)
		{
			String hql=" from Groupinfo group where group.groupName like '%"+
						filterStr(searchStr)+"%' and group.userinfo.userid=? ";
			String parameters[]={String.valueOf(userId)};
			List list=HibernateUtil.executeQueryByPage(hql, parameters, itemCount, pageNow);
			return list;
			
		}else
			return null;
	}
	
	//@Override
	public List<Groupinfo> getGroupByPage(int groupId, int pageNow,	int itemCount) {
		String hql="from Groupinfo group where group.groupid = ? ";
		String parameters[]={String.valueOf(groupId)};
		List list=HibernateUtil.executeQueryByPage(hql, parameters, itemCount, pageNow);
		return list;
	}
	
	public static void main(String[] args) {
		GroupinfoService gservice = new GroupinfoService();
//		List<Userinfo> users=ginter.getGroupMembers(1);
//		for(Userinfo u:users){
//			System.out.println(u.getUsername());
//		}
		
//		System.out.println(ginter.getGroupNum("网络"));
		
//		List<Groupinfo> groups=ginter.getGroupByPage("", 1, 4);
//		for(Groupinfo g:groups){
//			System.out.println(g.getGroupName());
//		}
//		addgroup teste
//		System.out.println(ginter.addGroup("aab",null));
//		System.out.println(ginter.addGroup("aac","124611162"));
//		System.out.println(ginter.addGroup("aac","124611162x"));
		
		//test getGroupMemberNamse
//		System.out.print(ginter.getGroupMemberNamse(1));
//		test getGroupMemberNamse
		
//		List<GroupCheck>groupList=gservice.getGroupCheckInLabSql(1,2);
//		for(GroupCheck g:groupList){
//			System.out.println(g.getUserName()+g.isInLab());
//		}
		
//		Userinfo u=gservice.getGroupLeader(1);
//		System.out.println(u.getUsername()+u.getUserid());
//		Groupinfo g=gservice.getGroupinfo(1);
//		System.out.println(g.getGroupName());
		
//		List<GroupLabCheckList> allLabGroupList = gservice.getGroupChekcInAllLab(1);
//		for(GroupLabCheckList glcl:allLabGroupList)
//			System.out.println(glcl.getLabid()+glcl.getLabname());
		
//		boolean result=gservice.deleteGroupLeader("134611143", 1);
//		System.out.println(result);
		
//		boolean result=gservice.deleteGroupMember(2);
//		System.out.println(result);
		
//		String parame[]={"124611162"};
//		boolean result=gservice.addGroupMember(2, parame);
//		System.out.println(result);
		
//		String parame[]={"124611162","124611163","134611143","124612260"};
//		boolean result=gservice.editGroupMember(1,parame);
//		System.out.println(result);
		
//		int num=gservice.getGroupNumberById(2);
//		System.out.println(num);
		
//		boolean result=gservice. updateGroupLeader(1,"124611162");
//		System.out.println(result);
		
//		boolean result=gservice.updateGroupName(5,"参数组");
//		System.out.println(result);
		
//		int num=gservice.getGroupMumberNum(1);
//		System.out.println(num);
		
		
		List<Groupinfo> list=gservice.getGroupByPage("", 1, "124612260", 1, 10);
		for(Groupinfo g:list){
			System.out.println(g.getGroupName());
		}
	}
	@Override
	public boolean updateGroup(String groupName, String leaderId, String members) {
		// TODO Auto-generated method stub
		return false;
	}
	private String filterStr(String searchStr){
		String result="";
		if(searchStr==null||searchStr.length()<1||searchStr.equals("null"))
			result="";
		else
			result=searchStr;
		return result;
	}

	@Override
	public int getGroupNum(String searchStr) {
		String hql="select count(*) from Groupinfo groupinfo where groupinfo.groupName like '%"+filterStr(searchStr)+"%'";
		String parameters[]={};
		List list= HibernateUtil.executeQuery(hql, parameters);
		Long num=(Long) list.get(0);
		return num.intValue();
	}
	
	
	
	@Override
	public List<Userinfo> getGroupMembers(int groupId){
		
		String hql="select userinfo from " +
				"Groupinfo groupinfo left join groupinfo.groupusers groupusers left join groupusers.userinfo userinfo  " +
				"where groupinfo.groupid=?";
		String parameters[]={String.valueOf(groupId)};
		
		List<Userinfo> list=HibernateUtil.executeQuery(hql, parameters);
		return list;
	}

	@Override
	public String getGroupMemberNamse(int groupId) {
		String result="";
		List <Userinfo>users=getGroupMembers(groupId);
		if(users!=null&&users.size()>0)
		for(Userinfo u:users)
		{
			if(u!=null)
				result=result+u.getUsername()+",";
			else
				return "";
		}
		if(users.size()>0){
			result=result.substring(0,result.length()-1);
		}
		return result;
	}
	public List<GroupCheck>getGroupCheckInLabSql(int labId,int groupId){
		String sql="select l.lab_id lab_id, u.userid userid,u.username username" +
				",max(case when gu.groupid=? then 1 else 0 END) inLab" +
				"from laboratory l LEFT OUTER JOIN userinfo u ON l.lab_id=u.lab_id LEFT JOIN " +
				"groupuser gu on gu.userid= u.userid where l.lab_id=?  GROUP BY u.userid";

		String parameters[]={String.valueOf(groupId),String.valueOf(labId)};
		
		List list=HibernateUtil.executeSQLToCheckGroup(sql, parameters,"inLab");
		
		List<GroupCheck> resultList=new ArrayList<GroupCheck>();
		for(int i=0;i<list.size();i++){
			GroupCheck gc=new GroupCheck();
			Map m=(Map)list.get(i);
			gc.setUserid((String)m.get("userid"));
			gc.setUserName((String)m.get("username"));
			int inLab=((Integer)m.get("inLab"));
			if(inLab==1)
				gc.setInLab(true);
			else
				gc.setInLab(false);
			resultList.add(gc);
		}
		return resultList;
	}
	public List<GroupLabCheckList> getGroupChekcInAllLab(int groupId){
		                             
		LaboratoryInter labInter=new LaboratorySerivce();
		List<Laboratory>labList=labInter.getLabsNames();
		List<GroupLabCheckList> allLabList=new ArrayList<GroupLabCheckList>();
		GroupinfoInter ginter=new GroupinfoService();
		for(Laboratory lab:labList){
			if(lab!=null){
				String groupname=ginter.getGroupinfo(groupId).getGroupName();
				GroupLabCheckList memberList=new GroupLabCheckList(ginter.getGroupCheckInLabSql(lab.getLabId(), groupId),lab.getLabId(),lab.getLabName());
				allLabList.add(memberList);
			}
		}
		return allLabList;
	}
	@Override
	public Userinfo getGroupLeader(int groupId){
		Userinfo leader = null;
		String hql="select new com.netsecurity.bean.Userinfo(userinfo.userid,userinfo.username) " +
				"from Userinfo userinfo,Groupinfo groupinfo " +
				"where userinfo.userid=groupinfo.userinfo.userid and " +
				"groupinfo.groupid=?";
		String parameters[]={String.valueOf(groupId)};
		List list=HibernateUtil.executeQuery(hql, parameters);
		if(list!=null&&list.size()>0)
		leader=(Userinfo)list.get(0);
		return leader;
	}
	public Groupinfo getGroupinfo(int groupid){
		String hql="from Groupinfo groupinfo where groupinfo.groupid=?";
		String parameters[]={String.valueOf(groupid)};
		Groupinfo groupinfo;
		List list=HibernateUtil.executeQuery(hql, parameters);
		groupinfo=(Groupinfo) list.get(0);
		return groupinfo;
	}
	public boolean deleteGroupLeader(String leaderId,int groupId){
		String hql="delete from Groupuser gu where gu.groupinfo.groupid=? and gu.userinfo.userid=?";
		String[]parameters={String.valueOf(groupId),leaderId};
		boolean result=HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}
	public boolean deleteGroupMember(int groupId){
		int groupNum=getGroupNumberById(groupId);
		if(groupNum==0)return true;
		String hql="delete from Groupuser gu where gu.groupinfo.groupid=? ";
		String[]parameters={String.valueOf(groupId)};
		boolean result=HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}
	public boolean addGroupMember(int groupId,String[]userids){
		boolean result=false;
		int count = 0;
		for(String userid:userids){
			Groupinfo groupinfo=new Groupinfo(groupId);
			Userinfo userinfo=new Userinfo(userid);
			Groupuser gu=new Groupuser(groupinfo,userinfo);
			
			result=HibernateUtil.save2(gu);
			if(result)count++;
		}
		if(count==userids.length)
			return true;
		else
			return false;
	}

	@Override
	public boolean editGroupMember(int groupId, String[] userids) {
		boolean result=deleteGroupMember(groupId);
		if(result){
			result=addGroupMember(groupId, userids);
		}
		return result;
	}
	public int getGroupNumberById(int groupId){
		String hql="select count(*) from Groupuser gu where gu.groupinfo.groupid=? ";
		String[]parameters={String.valueOf(groupId)};
		List list=HibernateUtil.executeQuery(hql, parameters);
		Long result=0l;
		if(list!=null&&list.size()>0)
			result=(Long) list.get(0);
		return result.intValue();
	}
	public boolean updateGroupLeader(int groupId,String leaderId){
		
		String hql="update Groupinfo groupinfo set groupinfo.userinfo.userid=? " +
				  "where groupinfo.groupid=?";
		String parameters[]={leaderId,String.valueOf(groupId)};
		boolean result=HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}
	public boolean updateGroupName(int groupId,String groupName){
		String hql="update Groupinfo g set g.groupName=? where g.groupid=?";
		String parameters[]={groupName,String.valueOf(groupId)};
		boolean result=HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}
	public int getGroupMumberNum(int groupId){
		String hql="select count(*) from Groupuser gu where gu.groupinfo.groupid=?";
		String parameters[]={String.valueOf(groupId)};
		List list=HibernateUtil.executeQuery(hql, parameters);
		Long count=(Long) list.get(0);
		return  count.intValue();
	}
	
}
