package com.netsecurity.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Laboratory entity. @author MyEclipse Persistence Tools
 */

public class Laboratory implements java.io.Serializable
{

	// Fields

	private Integer labId;
	private String labName;
	private String labLocation;
	private Set userinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Laboratory()
	{
	}

	/** minimal constructor */
	public Laboratory(Integer labId, String labName, String labLocation)
	{
		this.labId = labId;
		this.labName = labName;
		this.labLocation = labLocation;
	}

	/** full constructor */
	public Laboratory(Integer labId, String labName, String labLocation,
			Set userinfos)
	{
		this.labId = labId;
		this.labName = labName;
		this.labLocation = labLocation;
		this.userinfos = userinfos;
	}

	// Property accessors

	public Integer getLabId()
	{
		return this.labId;
	}

	public void setLabId(Integer labId)
	{
		this.labId = labId;
	}

	public String getLabName()
	{
		return this.labName;
	}

	public void setLabName(String labName)
	{
		this.labName = labName;
	}

	public String getLabLocation()
	{
		return this.labLocation;
	}

	public void setLabLocation(String labLocation)
	{
		this.labLocation = labLocation;
	}

	public Set getUserinfos()
	{
		return this.userinfos;
	}

	public void setUserinfos(Set userinfos)
	{
		this.userinfos = userinfos;
	}

}