<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.bohuatech.action.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%CityAction ca=new CityAction(); %>
<%--客户端获得县镇村 --%>
<%=ca.areaInfo()%>
