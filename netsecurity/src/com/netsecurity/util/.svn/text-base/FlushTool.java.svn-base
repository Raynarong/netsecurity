package com.netsecurity.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;




public class FlushTool {

	//flushJson字符串 用于与datagrid的数据传递 和ajax显示

	public static void flushJsonRecord(Object toflush) throws IOException    {  
 
		HttpServletResponse response=ServletActionContext.getResponse();  
	    /* 
	     * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码), 
	     * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会 
	     * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。 
	     * */  
	    response.setContentType("text/html;charset=UTF-8");  
	    //response.setCharacterEncoding("UTF-8");  
	    PrintWriter out = response.getWriter();  
	    out.println(toflush); 
	    out.flush();  
	    out.close();  
	} 
}
