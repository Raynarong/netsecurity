package com.netsecurity.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.netsecurity.bean.Userinfo;
import com.netsecurity.interfaces.UserinfoInter;
import com.netsecurity.service.UserinfoSerivce;
import com.netsecurity.util.FlushTool;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport
{

	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		if (code == null || !code.equals(checkCode))
		{
			request.setAttribute("errorMsg", "验证码错误");
			return ERROR;
		}
		UserinfoInter uinter = null;
		uinter = new UserinfoSerivce();
		Userinfo user = uinter.queryUser(name, password, permissionType);
		// 这里要想想
		
		// 查到用户
		if (user != null)
		{
			session.setAttribute("user", user);    
			//0 超级管理员   1组长   2普通用户
			session.setAttribute("userType", permissionType);
			if (permissionType==0)
			{
				return "superAdmin";
			}
			else if(permissionType == 1)
			{
				return "admin";
			}
			else
			{
				//普通用户登陆信息
				session.setAttribute("commonUser", user);
				session.setAttribute("CommonUserId", name);
				return "commonUser";
			}
			
		} else
		{
			request.setAttribute("errorMsg", "用户名或者密码错误");
			return ERROR;
		}
	}

	// ajax 反馈修改结果
	public void changePwd() throws IOException
	{
		UserinfoInter uinter = null;
		uinter = new UserinfoSerivce();
		Userinfo user = (Userinfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");

		boolean result = uinter.changePwd(user.getUsername(), passwordOld,
				password);
		if (result)
			FlushTool.flushJsonRecord(JSON.parse("true"));
		else
			FlushTool.flushJsonRecord(JSON.parse("false"));
	}

	// 登出
	public String logout()
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", null);
		return SUCCESS;
	}

	String name;
	String password;
	String checkCode;
	String passwordOld;
	private int permissionType;

	public int getPermissionType()
	{
		return permissionType;
	}

	public void setPermissionType(int permissionType)
	{
		this.permissionType = permissionType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getCheckCode()
	{
		return checkCode;
	}

	public void setCheckCode(String checkCode)
	{
		this.checkCode = checkCode;
	}

	public String getPasswordOld()
	{
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld)
	{
		this.passwordOld = passwordOld;
	}
}
