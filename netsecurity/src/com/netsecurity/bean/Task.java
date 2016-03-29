package com.netsecurity.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */

public class Task implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Userinfo userinfo;
	private String name;
	private Integer status;
	private Timestamp startTime;
	private Timestamp endTime;
	private String pathSrczip;
	private String pathDatazip;
	private String pathReszip;
	private Integer runway;
	private Integer platform;
	private Integer divide;
	private String winCmdCompile;
	private String winCmdRun;
	private String linuxCmdCompile;
	private String linuxCmdRun;
	private Set taskChilds = new HashSet(0);

	// Constructors

	/** default constructor */
	public Task()
	{
	}

	/** minimal constructor */
	public Task(Integer id)
	{
		this.id = id;
	}

	/** full constructor */
	public Task(Integer id, Userinfo userinfo, String name, Integer status,
			Timestamp startTime, Timestamp endTime, String pathSrczip,
			String pathDatazip, String pathReszip, Integer runway,
			Integer platform, Integer divide, String winCmdCompile,
			String winCmdRun, String linuxCmdCompile, String linuxCmdRun,
			Set taskChilds)
	{
		this.id = id;
		this.userinfo = userinfo;
		this.name = name;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pathSrczip = pathSrczip;
		this.pathDatazip = pathDatazip;
		this.pathReszip = pathReszip;
		this.runway = runway;
		this.platform = platform;
		this.divide = divide;
		this.winCmdCompile = winCmdCompile;
		this.winCmdRun = winCmdRun;
		this.linuxCmdCompile = linuxCmdCompile;
		this.linuxCmdRun = linuxCmdRun;
		this.taskChilds = taskChilds;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Userinfo getUserinfo()
	{
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo)
	{
		this.userinfo = userinfo;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Timestamp getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime)
	{
		this.startTime = startTime;
	}

	public Timestamp getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime)
	{
		this.endTime = endTime;
	}

	public String getPathSrczip()
	{
		return this.pathSrczip;
	}

	public void setPathSrczip(String pathSrczip)
	{
		this.pathSrczip = pathSrczip;
	}

	public String getPathDatazip()
	{
		return this.pathDatazip;
	}

	public void setPathDatazip(String pathDatazip)
	{
		this.pathDatazip = pathDatazip;
	}

	public String getPathReszip()
	{
		return this.pathReszip;
	}

	public void setPathReszip(String pathReszip)
	{
		this.pathReszip = pathReszip;
	}

	public Integer getRunway()
	{
		return this.runway;
	}

	public void setRunway(Integer runway)
	{
		this.runway = runway;
	}

	public Integer getPlatform()
	{
		return this.platform;
	}

	public void setPlatform(Integer platform)
	{
		this.platform = platform;
	}

	public Integer getDivide()
	{
		return this.divide;
	}

	public void setDivide(Integer divide)
	{
		this.divide = divide;
	}

	public String getWinCmdCompile()
	{
		return this.winCmdCompile;
	}

	public void setWinCmdCompile(String winCmdCompile)
	{
		this.winCmdCompile = winCmdCompile;
	}

	public String getWinCmdRun()
	{
		return this.winCmdRun;
	}

	public void setWinCmdRun(String winCmdRun)
	{
		this.winCmdRun = winCmdRun;
	}

	public String getLinuxCmdCompile()
	{
		return this.linuxCmdCompile;
	}

	public void setLinuxCmdCompile(String linuxCmdCompile)
	{
		this.linuxCmdCompile = linuxCmdCompile;
	}

	public String getLinuxCmdRun()
	{
		return this.linuxCmdRun;
	}

	public void setLinuxCmdRun(String linuxCmdRun)
	{
		this.linuxCmdRun = linuxCmdRun;
	}

	public Set getTaskChilds()
	{
		return this.taskChilds;
	}

	public void setTaskChilds(Set taskChilds)
	{
		this.taskChilds = taskChilds;
	}

}