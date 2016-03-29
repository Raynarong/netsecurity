package com.netsecurity.service;

import java.util.List;

import com.netsecurity.bean.Laboratory;
import com.netsecurity.interfaces.LaboratoryInter;
import com.netsecurity.util.HibernateUtil;
import com.sun.org.apache.xml.internal.utils.IntVector;

public class LaboratorySerivce implements LaboratoryInter
{

	/**
	 * 获得各个实验室id以及名称
	 * Laboratory对象的list
	 * Laboratory对象封装了实验室id、名称、所在位置
	 */
	public List<Laboratory> getLabsNames()
	{
		String hql= "from Laboratory";
		String[] parameters={};
		List<Laboratory> list =HibernateUtil.executeQuery(hql,parameters);
		return  list;
	}

	/**
	 * 获得实验室个数
	 * 获取实验室表中所有的记录数总和
	 */
	public Integer getlabNum()
	{
		String hql= "select count(*) from Laboratory";
		String[] parameters={};
		List list =HibernateUtil.executeQuery(hql,parameters);
		Long num = (Long) list.get(0);
		int num2 = (int) num.longValue();
		return  num2;
	}

}
