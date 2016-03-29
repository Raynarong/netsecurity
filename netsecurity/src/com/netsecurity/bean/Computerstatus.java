package com.netsecurity.bean;

import java.sql.Timestamp;

/**
 * Computerstatus entity. @author MyEclipse Persistence Tools
 */

public class Computerstatus implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Computerinfo computerinfo;
	private Long upload;
	private Long download;
	private Float memoryRate;
	private Float cpuRate;
	private Integer diskRemain;
	private Integer mouseLeftType;
	private Integer mouseRightType;
	private Integer appNum;
	private Integer threadNum;
	private Timestamp datetime;
	private Integer keyboardType;

	// Constructors

	/** default constructor */
	public Computerstatus()
	{
	}

	/** minimal constructor */
	public Computerstatus(Computerinfo computerinfo)
	{
		this.computerinfo = computerinfo;
	}

	/** full constructor */
	public Computerstatus(Computerinfo computerinfo, Long upload,
			Long download, Float memoryRate, Float cpuRate, Integer diskRemain,
			Integer mouseLeftType, Integer mouseRightType, Integer appNum,
			Integer threadNum, Timestamp datetime, Integer keyboardType)
	{
		this.computerinfo = computerinfo;
		this.upload = upload;
		this.download = download;
		this.memoryRate = memoryRate;
		this.cpuRate = cpuRate;
		this.diskRemain = diskRemain;
		this.mouseLeftType = mouseLeftType;
		this.mouseRightType = mouseRightType;
		this.appNum = appNum;
		this.threadNum = threadNum;
		this.datetime = datetime;
		this.keyboardType = keyboardType;
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

	public Computerinfo getComputerinfo()
	{
		return this.computerinfo;
	}

	public void setComputerinfo(Computerinfo computerinfo)
	{
		this.computerinfo = computerinfo;
	}

	public Long getUpload()
	{
		return this.upload;
	}

	public void setUpload(Long upload)
	{
		this.upload = upload;
	}

	public Long getDownload()
	{
		return this.download;
	}

	public void setDownload(Long download)
	{
		this.download = download;
	}

	public Float getMemoryRate()
	{
		return this.memoryRate;
	}

	public void setMemoryRate(Float memoryRate)
	{
		this.memoryRate = memoryRate;
	}

	public Float getCpuRate()
	{
		return this.cpuRate;
	}

	public void setCpuRate(Float cpuRate)
	{
		this.cpuRate = cpuRate;
	}

	public Integer getDiskRemain()
	{
		return this.diskRemain;
	}

	public void setDiskRemain(Integer diskRemain)
	{
		this.diskRemain = diskRemain;
	}

	public Integer getMouseLeftType()
	{
		return this.mouseLeftType;
	}

	public void setMouseLeftType(Integer mouseLeftType)
	{
		this.mouseLeftType = mouseLeftType;
	}

	public Integer getMouseRightType()
	{
		return this.mouseRightType;
	}

	public void setMouseRightType(Integer mouseRightType)
	{
		this.mouseRightType = mouseRightType;
	}

	public Integer getAppNum()
	{
		return this.appNum;
	}

	public void setAppNum(Integer appNum)
	{
		this.appNum = appNum;
	}

	public Integer getThreadNum()
	{
		return this.threadNum;
	}

	public void setThreadNum(Integer threadNum)
	{
		this.threadNum = threadNum;
	}

	public Timestamp getDatetime()
	{
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime)
	{
		this.datetime = datetime;
	}

	public Integer getKeyboardType()
	{
		return this.keyboardType;
	}

	public void setKeyboardType(Integer keyboardType)
	{
		this.keyboardType = keyboardType;
	}

}