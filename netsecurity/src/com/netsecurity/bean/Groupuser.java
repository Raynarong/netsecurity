package com.netsecurity.bean;

/**
 * Groupuser entity. @author MyEclipse Persistence Tools
 */

public class Groupuser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Groupinfo groupinfo;
	private Userinfo userinfo;

	// Constructors

	/** default constructor */
	public Groupuser() {
	}

	/** full constructor */
	public Groupuser(Groupinfo groupinfo, Userinfo userinfo) {
		this.groupinfo = groupinfo;
		this.userinfo = userinfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Groupinfo getGroupinfo() {
		return this.groupinfo;
	}

	public void setGroupinfo(Groupinfo groupinfo) {
		this.groupinfo = groupinfo;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

}