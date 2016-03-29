package com.netsecurity.service;

import java.util.List;

import com.netsecurity.interfaces.Usercomputer;
import com.netsecurity.util.HibernateUtil;

public class UsercomputerService implements Usercomputer
{
	/**
	 * 根据userId获取电脑数目
	 */
	public Integer getComputerNum(String userId)
	{
		String hql= "select count(*) from Usercomputer usercomputer " 
			+	" where usercomputer.userinfo.userid=?";
		String[] parameters={String.valueOf(userId)};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
	}
	public static void main(String[] args)
	{
	
		int i = new UsercomputerService().getComputerNum("124612261");
		System.out.println(i);
	}

}
