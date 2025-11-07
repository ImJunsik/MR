<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String jsonData;
    String errorCode = WebUtil.getRequestValue(request, "errorCode", StrUtil.EMPTY_STRING);
    String errorMessage = WebUtil.getRequestValue(request, "errorMessage", StrUtil.EMPTY_STRING);
    if (StrUtil.isNotEmpty(errorCode))
    {
        StringBuilder sb = new StringBuilder();
        sb
                .append("{")
                .append("\"result\": false,")
                .append("\"errorCode\": ").append(errorCode).append(",")
                .append("\"errorMessage\": \"").append(errorMessage).append("\",")
                .append("}");
%>
        <%=sb.toString()%>
<%
    }
    else
    {
        AuthCheck authCheck = new AuthCheck(request, response);
        authCheck.logon(true);
%>
        <%=authCheck.checkLogonJson()%><%
    }
%>
