<%@page language="java" contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page import="java.util.*, java.text.*"  %>

<%

 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyMMddHHmmss");
 String today = formatter.format(new java.util.Date());

//엑셀 처리를 위한 header 셋팅
String fileName = request.getAttribute("fileName").toString();
if(fileName == null){
    fileName = "temp";
}

response.setContentType("text/html; charset=utf-8");
response.setHeader("Content-Type", "application/vnd.ms-xls");
response.setHeader("Content-Disposition", "attachment; filename="+fileName+"_"+today+".xls");
response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>
<tiles:insertAttribute name="content" />
