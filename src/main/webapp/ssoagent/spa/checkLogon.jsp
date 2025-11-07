<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%=new AuthCheck(request, response).checkLogonJson()%>
