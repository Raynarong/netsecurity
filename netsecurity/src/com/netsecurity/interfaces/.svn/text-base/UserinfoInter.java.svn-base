package com.netsecurity.interfaces;

import java.util.List;

import com.netsecurity.bean.Userinfo;

public interface UserinfoInter {
	/**
	 * 
	 * @param userName 
	 * @param passWord
	 * @return
	 */
	public Userinfo checkUser(String userName,String passWord);
	public Userinfo queryUser(String userName,String passWord,int permissionType);
	public boolean changePwd(String userName,String passWordOld,String passWordNew);
	public Integer getUserNum(int labId);
	/**
	 * 
	 * @param searchStr
	 * @return
	 */
	public Integer getUserNumByName(String searchStr);
	
	/***
	 * 根据用户姓名查询用户信息
	 * @param name 姓名
	 * @param type 类型 1组长，2组员
	 * @return List<Userinfo>
	 */
	public List<Userinfo> getUserBySearchWord(String name,int type);

	/*****
	 * 根据用户id改变用户身份
	 * @param userid 
	 * @param type (1 组长，2组员)
	 * @return false 修改失败 true 修改成功
	 */
	public boolean changePower(String userid,int type);
	/****
	 * 分页显示组长，组员信息
	 * @param pageNow 当前页数
	 * @param itemCount 每页多少条记录
	 * @param showType 显示组长为 1 显示组员为2
	 * @param searchWord  如果showType为1searchWord为组长姓名， 如果showType为2searchWord为组员姓名，
	 * @return
	 */
	public List<Userinfo> getUserByPage(int pageNow,int itemCount,int showType, String searchWord);
	/****
	 * 获取查询用户数
	 * @param searchStr
	 * @param type
	 * @return
	 */
	public Integer getUserNumByNameType(String searchStr,int type);
	/***
	 * 修改密码
	 * @param userId
	 * @param pwdOld
	 * @param pwd
	 * @return
	 */
	public boolean editPwd(String userId,String pwdOld,String pwd);
	
	public boolean addLeader(String userId,int groupId);
}
