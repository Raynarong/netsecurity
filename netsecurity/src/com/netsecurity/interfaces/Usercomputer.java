package com.netsecurity.interfaces;

public interface Usercomputer {
	/**
	 * 获得用户使用的电脑的个数
	 * @param userId 用户id 
	 */
	public Integer getComputerNum(String userId);
}
