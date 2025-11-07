<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.DupCheck" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    AuthCheck authCheck = new AuthCheck(request, response);
    DupCheck dupCheck = new DupCheck(authCheck, request, response);
%><%=dupCheck.processCancel()%>
