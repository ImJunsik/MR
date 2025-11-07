<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.ESSO" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.logging.SsoLogger" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page import="nets.sso.agent.web.v2020.conf.AgentConf" %>
<%
    String returnString = StrUtil.EMPTY_STRING;
    try
    {
        String runtype = WebUtil.getRequestValue(request, "runtype", StrUtil.EMPTY_STRING, false);

        String appid = WebUtil.getRequestValue(request, "appid", StrUtil.EMPTY_STRING, false);
        String param = WebUtil.getRequestValue(request, "param", StrUtil.EMPTY_STRING, false);

        String unregtype = WebUtil.getRequestValue(request, "type", StrUtil.EMPTY_STRING, false);
        String unregname = WebUtil.getRequestValue(request, "name", StrUtil.EMPTY_STRING, false);
        String unregpath = WebUtil.getRequestValue(request, "path", StrUtil.EMPTY_STRING, false);
        String unregpath32 = WebUtil.getRequestValue(request, "path32", StrUtil.EMPTY_STRING, false);
        String unregparam = WebUtil.getRequestValue(request, "param", StrUtil.EMPTY_STRING, false);

        AuthCheck auth = new AuthCheck(request, response);
        if (auth.getErrCode() == 0)
        {
            if (StrUtil.isNotEmpty(appid))
                returnString = ESSO.runApp(auth, appid, param);
            else
                returnString = ESSO.runAppUnregistered(auth, unregtype, unregname, unregpath, unregpath32, unregparam);
        }
    }
    catch (AgentException e)
    {
        SsoLogger.log(AgentConf.LogLevel.ERROR, e, request);
    }
%><%=returnString%>