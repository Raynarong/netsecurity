package com.netsecurity.service;

import java.util.List;

import com.netsecurity.bean.Userinfo;
import com.netsecurity.interfaces.UserinfoInter;
import com.netsecurity.util.HibernateUtil;


public class UserinfoSerivce implements UserinfoInter
{
	/**
	 * 检查用户是否存在
	 */
	public Userinfo queryUser(String userName, String passWord,int permissionType)
	{
		String hql="from Userinfo where userid=? and userpasswd=? and type=?";
		String [] parameters={userName,passWord,String.valueOf(permissionType)};
		List<Userinfo> list=HibernateUtil.executeQuery(hql, parameters);
		if (list.size()==0)
		{
			return null;
		} else
		{
			return list.get(0);
		}
/*		Userinfo userinfo=new Userinfo();
		userinfo=(Userinfo)HibernateUtil.uniqueQuery(hql, parameters);
		return userinfo;*/
	}
	public Userinfo checkUser(String userName, String passWord)
	{
		String hql="from Userinfo where userid=? and userpasswd=?";
		String [] parameters={userName,passWord};
		List<Userinfo> list=HibernateUtil.executeQuery(hql, parameters);
		if (list.size()==0)
		{
			return null;
		} else
		{
			return list.get(0);
		}
		/*		Userinfo userinfo=new Userinfo();
		userinfo=(Userinfo)HibernateUtil.uniqueQuery(hql, parameters);
		return userinfo;*/
	}
	/**
	 * 修改密码
	 */
	public boolean changePwd(String userName, String passWordOld,
			String passWordNew)
	{
		if (checkUser(userName,passWordOld)==null)
		{
			return false;
		}
		String hql="update Userinfo u set u.userpasswd=? where username=? and userpasswd=?";
		String [] parameters={passWordNew,userName,passWordOld};	
		return HibernateUtil.executeUpdate(hql, parameters);
	}
	/**
	 * 根据labid获取用户数
	 */
	public Integer getUserNum(int labId)
	{
		String hql= "select count(*) from Userinfo userinfo where userinfo.laboratory.labId=?";
		String[] parameters={String.valueOf(labId)};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
	}
	
	public static void main(String[] args)
	{
		UserinfoSerivce us = new UserinfoSerivce();
//		us.getUserBySearchWord("孙", 2);
//		List<Userinfo> num = us.getUserBySearchWord("孙",2);
//		for(Userinfo u:num){
//			System.out.println(u.getUsername());
//		}
//		test changePower()
//		System.out.println(us.changePower("124612260", 1));
//		System.out.println(us.editPwd("124612260", "123456",""));
		boolean result=us.addLeader("124612260", 1);
		System.out.println(result);
	}
	public Integer testGroup(){
		String hql="select count(*) from Groupinfo ";
		String[] parameters={};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
		
	}
	public Integer getUserNumByName(String searchStr)
	{
		
		String hql= "select count(*) from Userinfo userinfo where userinfo.username like '%"+filterSearchStr(searchStr)+"%'";
		String[] parameters={};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
	}
	private String filterSearchStr(String searchStr){
		String result;
		if(searchStr==null||searchStr.length()<1||searchStr.equals("null"))
			result="";
		else 
			result=searchStr;
		return result;
	}
	@Override
	public Integer getUserNumByNameType(String searchStr,int showType)
	{
		String search = filterSearchStr(searchStr);
		String hql= "select count(*) from Userinfo userinfo where userinfo.username like '%"+search+"%' and type=?";
		String[] parameters={String.valueOf(showType)};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
	}
	
	/***
	 * 修改权限
	 */
	@Override
	public boolean changePower(String userid, int type) {
		if(type==1){//升级为组长
			String hql=" update Userinfo userinfo set userinfo.type =? where userid=?";
			String []params={String.valueOf(type),userid};
			return HibernateUtil.executeUpdate(hql,params);
		}else {//降级为组员
			String hql=" update Groupinfo groupinfo set groupinfo.leaderId =null where groupinfo.leaderId=?";
			String []params={userid};
			HibernateUtil.executeUpdate(hql,params);
			hql=" update Userinfo userinfo set userinfo.type =? where userid=?";
			params=new String[]{"",""};
			params[0]=String.valueOf(2);
			params[1]=userid;
			return HibernateUtil.executeUpdate(hql,params);
		}
		
		
	}
	
	
	@Override
	public List<Userinfo> getUserByPage(int pageNow, int itemCount,
			int showType, String searchWord) {
		
		String search="'%"+filterSearchStr(searchWord)+"%'";
		String hql= "from Userinfo where username like "+search+"  and type= ? order by username desc ";

		String[] parameters={String.valueOf(showType)};
		List list =HibernateUtil.executeQueryByPage(hql,parameters,itemCount,pageNow);
		
		return  list;

	}
	@Override
	public List<Userinfo> getUserBySearchWord(String name, int type) {
		String search="'%"+filterSearchStr(name)+"%'";
		String hql= "from Userinfo where username like "+search+"  and type= ? order by username desc ";

		String[] parameters={String.valueOf(type)};
		List list =HibernateUtil.executeQuery(hql,parameters);
		
		return  list;
	}
	@Override
	public boolean editPwd(String userId, String pwdOld,String pwd) {
		String hql="update Userinfo userinfo set userinfo.userpasswd=? where userinfo.userid=? and userinfo.userpasswd=?";
		String[] parameters={pwd,userId,pwdOld};
		boolean result= HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}
	@Override
	public boolean addLeader(String userId, int groupId) {
		String hql="update Groupinfo groupinfo set groupinfo.userinfo.userid=? where groupinfo.groupid=? ";
		String[] parameters={userId,String.valueOf(groupId)};
		boolean result= HibernateUtil.executeUpdate(hql, parameters);
		return result;
	}

	
}

