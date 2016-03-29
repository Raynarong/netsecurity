package com.netsecurity.bean;

public class UserComputerOnlineNumber {
	private String userid;
	private String username;
	private Long onlineNum;
	private Long offlineNum;
	private Long totalNum;

	public UserComputerOnlineNumber(String userid, String username,
			Long onlineNum, Long offlineNum, Long totalNum)
	{
		super();
		this.userid = userid;
		this.username = username;
		this.onlineNum = onlineNum;
		this.offlineNum = offlineNum;
		this.totalNum = totalNum;
	}
	
	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Long getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(Long onlineNum) {
		this.onlineNum = onlineNum;
	}
	public Long getOfflineNum() {
		return offlineNum;
	}
	public void setOfflineNum(Long offlineNum) {
		this.offlineNum = offlineNum;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	
}
