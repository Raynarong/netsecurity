package com.netsecurity.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private String userid;
	private Laboratory laboratory;
	private String userpasswd;
	private String username;
	private String qq;
	private String phone;
	private String email;
	private Integer type;
	private Set groupinfos = new HashSet(0);
	private Set tasks = new HashSet(0);
	private Set groupusers = new HashSet(0);
	private Set usercomputers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(Laboratory laboratory, String userpasswd) {
		this.laboratory = laboratory;
		this.userpasswd = userpasswd;
	}
	
	public Userinfo(String userid) {
		super();
		this.userid = userid;
	}
	public Userinfo(String userid,String username) {
		super();
		this.userid = userid;
		this.username=username;
	}
	
	/** full constructor */
	public Userinfo(Laboratory laboratory, String userpasswd, String username,
			String qq, String phone, String email, Integer type,
			Set groupinfos, Set tasks, Set groupusers, Set usercomputers) {
		this.laboratory = laboratory;
		this.userpasswd = userpasswd;
		this.username = username;
		this.qq = qq;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.groupinfos = groupinfos;
		this.tasks = tasks;
		this.groupusers = groupusers;
		this.usercomputers = usercomputers;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Laboratory getLaboratory() {
		return this.laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public String getUserpasswd() {
		return this.userpasswd;
	}

	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set getGroupinfos() {
		return this.groupinfos;
	}

	public void setGroupinfos(Set groupinfos) {
		this.groupinfos = groupinfos;
	}

	public Set getTasks() {
		return this.tasks;
	}

	public void setTasks(Set tasks) {
		this.tasks = tasks;
	}

	public Set getGroupusers() {
		return this.groupusers;
	}

	public void setGroupusers(Set groupusers) {
		this.groupusers = groupusers;
	}

	public Set getUsercomputers() {
		return this.usercomputers;
	}

	public void setUsercomputers(Set usercomputers) {
		this.usercomputers = usercomputers;
	}

}