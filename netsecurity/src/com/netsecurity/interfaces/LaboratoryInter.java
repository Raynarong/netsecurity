package com.netsecurity.interfaces;

import java.util.List;

import com.netsecurity.bean.Laboratory;
import com.netsecurity.bean.OnLineNum;
import com.netsecurity.bean.TrendData;

public interface LaboratoryInter {

	/**
	 * 获得实验室个数
	 */
	public Integer getlabNum();
	
	/**
	 * 获得各个实验室id以及名称
	 */
	public List<Laboratory> getLabsNames();
	

}
