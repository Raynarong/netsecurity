package com.netsecurity.bean;

public class ComputerOnlineNumber {
	private String sn;
	private boolean online;
	
	public ComputerOnlineNumber(String sn, boolean online)
	{
		super();
		this.sn = sn;
		this.online = online;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public boolean getOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	
}
