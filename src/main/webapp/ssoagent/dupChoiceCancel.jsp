<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.DupCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.logging.SsoLogger" %>
<%@ page import="nets.sso.agent.web.v2020.conf.AgentConf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try
    {
        AuthCheck auth = new AuthCheck(request, response);
        DupCheck dupCheck = new DupCheck(auth, request, response);
        dupCheck.processCancel();
    }
    catch (AgentException e)
    {
        SsoLogger.log(AgentConf.LogLevel.ERROR, e, request);
    }
%>