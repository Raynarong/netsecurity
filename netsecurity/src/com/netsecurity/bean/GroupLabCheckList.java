package com.netsecurity.bean;

import java.util.ArrayList;
import java.util.List;

public class GroupLabCheckList {
	private List<GroupCheck> groupCheckList;
	private boolean allChecked=false;
	private int labid;
	private String labname;
	public GroupLabCheckList(List<GroupCheck> groupCheckList,int labid,String labname) {
		super();
		this.labid=labid;
		this.labname=labname;
		this.groupCheckList = new ArrayList<GroupCheck>();
		int count=0;
		for(int i=0;i<groupCheckList.size();i++){
			GroupCheck gc=new GroupCheck();
			gc.setInLab(groupCheckList.get(i).isInLab());
			gc.setUserid(groupCheckList.get(i).getUserid());
			gc.setUserName(groupCheckList.get(i).getUserName());
			this.groupCheckList.add(gc);
			if(gc.isInLab()==true)
				count++;
		}
		if(this.groupCheckList.size()==count){
			this.allChecked=true;
		}
	}
	public List<GroupCheck> getGroupCheckList() {
		return groupCheckList;
	}
	public void setGroupCheckList(List<GroupCheck> groupCheckList) {
		this.groupCheckList = groupCheckList;
	}
	public boolean isAllChecked() {
		return allChecked;
	}
	public void setAllChecked(boolean allChecked) {
		this.allChecked = allChecked;
	}
	public int getLabid() {
		return labid;
	}
	public void setLabid(int labid) {
		this.labid = labid;
	}
	public String getLabname() {
		return labname;
	}
	public void setLabname(String labname) {
		this.labname = labname;
	}
	
	
}
