<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.DupCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String jsonData;
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    try
    {
        AuthCheck authCheck = new AuthCheck(request, response);
        DupCheck dupCheck = new DupCheck(authCheck, request, response);
        sb
                .append("\"result\": true,")
                .append("\"errorCode\": 0,")
                .append("\"userID\": \"" + dupCheck.getDupUserID() + "\",")
                .append("\"time\": \"" + dupCheck.getDupTime() + "\",")
                .append("\"ip\": \"" + dupCheck.getDupIP() + "\",")
                .append("\"timeoutMinutes\": " + dupCheck.getTimeoutMinutes());
    }
    catch (AgentException ae)
    {
        String errorMessage = StrUtil.isNullOrEmpty(ae.getMessage()) ? "Failed to receive duplication logon." : ae.getMessage();
        sb
                .append("\"result\": false,")
                .append("\"errorCode\": " + ae.getExceptionCode().getValue() + ",")
                .append("\"errorMessage\": \"" + errorMessage + "\"");
    }
    sb.append("}");
    jsonData = sb.toString();
%><%=jsonData%>
