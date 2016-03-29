package com.netsecurity.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Computerinfo entity. @author MyEclipse Persistence Tools
 */

public class Computerinfo implements java.io.Serializable
{

	// Fields

	private String sn;
	private String computerType;
	private String operatingSystem;
	private String motherBoard;
	private Integer memorySize;
	private String networkAdapte;
	private String mac;
	private String cpuType;
	private Integer diskVolume;
	private String diskSerialnum;
	private Boolean online;
	private Integer systemType;
	private Set usercomputers = new HashSet(0);
	private Set computerstatuses = new HashSet(0);
	private Set computerstatusHistories = new HashSet(0);

	// Constructors

	/** default constructor */
	public Computerinfo()
	{
	}

	/** minimal constructor */
	public Computerinfo(String sn, Boolean online)
	{
		this.sn = sn;
		this.online = online;
	}

	/** full constructor */
	public Computerinfo(String sn, String computerType, String operatingSystem,
			String motherBoard, Integer memorySize, String networkAdapte,
			String mac, String cpuType, Integer diskVolume,
			String diskSerialnum, Boolean online, Integer systemType,
			Set usercomputers, Set computerstatuses, Set computerstatusHistories)
	{
		this.sn = sn;
		this.computerType = computerType;
		this.operatingSystem = operatingSystem;
		this.motherBoard = motherBoard;
		this.memorySize = memorySize;
		this.networkAdapte = networkAdapte;
		this.mac = mac;
		this.cpuType = cpuType;
		this.diskVolume = diskVolume;
		this.diskSerialnum = diskSerialnum;
		this.online = online;
		this.systemType = systemType;
		this.usercomputers = usercomputers;
		this.computerstatuses = computerstatuses;
		this.computerstatusHistories = computerstatusHistories;
	}

	// Property accessors

	public String getSn()
	{
		return this.sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getComputerType()
	{
		return this.computerType;
	}

	public void setComputerType(String computerType)
	{
		this.computerType = computerType;
	}

	public String getOperatingSystem()
	{
		return this.operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}

	public String getMotherBoard()
	{
		return this.motherBoard;
	}

	public void setMotherBoard(String motherBoard)
	{
		this.motherBoard = motherBoard;
	}

	public Integer getMemorySize()
	{
		return this.memorySize;
	}

	public void setMemorySize(Integer memorySize)
	{
		this.memorySize = memorySize;
	}

	public String getNetworkAdapte()
	{
		return this.networkAdapte;
	}

	public void setNetworkAdapte(String networkAdapte)
	{
		this.networkAdapte = networkAdapte;
	}

	public String getMac()
	{
		return this.mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac;
	}

	public String getCpuType()
	{
		return this.cpuType;
	}

	public void setCpuType(String cpuType)
	{
		this.cpuType = cpuType;
	}

	public Integer getDiskVolume()
	{
		return this.diskVolume;
	}

	public void setDiskVolume(Integer diskVolume)
	{
		this.diskVolume = diskVolume;
	}

	public String getDiskSerialnum()
	{
		return this.diskSerialnum;
	}

	public void setDiskSerialnum(String diskSerialnum)
	{
		this.diskSerialnum = diskSerialnum;
	}

	public Boolean getOnline()
	{
		return this.online;
	}

	public void setOnline(Boolean online)
	{
		this.online = online;
	}

	public Integer getSystemType()
	{
		return this.systemType;
	}

	public void setSystemType(Integer systemType)
	{
		this.systemType = systemType;
	}

	public Set getUsercomputers()
	{
		return this.usercomputers;
	}

	public void setUsercomputers(Set usercomputers)
	{
		this.usercomputers = usercomputers;
	}

	public Set getComputerstatuses()
	{
		return this.computerstatuses;
	}

	public void setComputerstatuses(Set computerstatuses)
	{
		this.computerstatuses = computerstatuses;
	}

	public Set getComputerstatusHistories()
	{
		return this.computerstatusHistories;
	}

	public void setComputerstatusHistories(Set computerstatusHistories)
	{
		this.computerstatusHistories = computerstatusHistories;
	}

}