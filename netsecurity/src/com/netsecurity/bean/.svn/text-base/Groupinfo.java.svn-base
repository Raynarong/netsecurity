package com.netsecurity.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Groupinfo entity. @author MyEclipse Persistence Tools
 */

public class Groupinfo implements java.io.Serializable {

	// Fields

	private Integer groupid;
	private Userinfo userinfo;
	private String groupName;
	private Set groupusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Groupinfo() {
	}

	public Groupinfo(Integer groupid) {
		super();
		this.groupid = groupid;
	}

	/** minimal constructor */
	public Groupinfo(String groupName) {
		this.groupName = groupName;
	}

	/** full constructor */
	public Groupinfo(Userinfo userinfo, String groupName, Set groupusers) {
		this.userinfo = userinfo;
		this.groupName = groupName;
		this.groupusers = groupusers;
	}
	
	public Groupinfo(Userinfo userinfo, String groupName) {
		super();
		this.userinfo = userinfo;
		this.groupName = groupName;
	}

	// Property accessors

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set getGroupusers() {
		return this.groupusers;
	}

	public void setGroupusers(Set groupusers) {
		this.groupusers = groupusers;
	}

}