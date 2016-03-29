package com.netsecurity.bean;

public class GroupCheck {
	private String userName;
	private String userid;
	private boolean inLab;
	
	public GroupCheck() {
		super();
	}

	public GroupCheck(String userName, String userid, boolean inLab) {
		super();
		this.userName = userName;
		this.userid = userid;
		this.inLab = inLab;
	}
	
	public GroupCheck(String userName, String userid) {
		super();
		this.userName = userName;
		this.userid = userid;
		this.inLab=true;
	}

	public GroupCheck(String userName, String userid,Object i) {
		super();
		this.userName = userName;
		this.userid = userid;
		if((Integer)i==0)
			this.inLab=false;
		else
			this.inLab=true;
			
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean isInLab() {
		return inLab;
	}
	public void setInLab(boolean inLab) {
		this.inLab = inLab;
	}
	
}
