package com.netsecurity.bean;
/**
 * 
 * @author Administrator
 *
 */
public class OnLineNum {
	private Integer labId;
	private String labName;
	private Long onlineNum;
	private Long offlineNum;
	private Long totalNum;
	
	public OnLineNum(Integer labId, Long onlineNum, Long offlineNum,
			Long totalNum)
	{
		super();
		this.labId = labId;
		this.onlineNum = onlineNum;
		this.offlineNum = offlineNum;
		this.totalNum = totalNum;
	}

	public OnLineNum(Integer labId,String labName, Long onlineNum, Long offlineNum,
			Long totalNum)
	{
		super();
		this.labId = labId;
		this.labName = labName;
		this.onlineNum = onlineNum;
		this.offlineNum = offlineNum;
		this.totalNum = totalNum;
	}
	
	public String getLabName()
	{
		return labName;
	}

	public void setLabName(String labName)
	{
		this.labName = labName;
	}

	public Integer getLabId()
	{
		return labId;
	}
	public void setLabId(Integer labId)
	{
		this.labId = labId;
	}
	public Long getOnlineNum()
	{
		return onlineNum;
	}
	public void setOnlineNum(Long onlineNum)
	{
		this.onlineNum = onlineNum;
	}
	public Long getOfflineNum()
	{
		return offlineNum;
	}
	public void setOfflineNum(Long offlineNum)
	{
		this.offlineNum = offlineNum;
	}
	public Long getTotalNum()
	{
		return totalNum;
	}
	public void setTotalNum(Long totalNum)
	{
		this.totalNum = totalNum;
	}
	



	
}
