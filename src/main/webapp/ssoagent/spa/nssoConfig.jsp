<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.constant.LiteralConst" %>
<%@ page import="nets.sso.agent.web.v2020.common.constant.ParamInfo" %>
<%@ page import="nets.sso.agent.web.v2020.common.enums.AuthStatus" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentExceptionCode" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.Encode" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page import="nets.sso.agent.web.v2020.conf.SSOProvider" %>
<%@ page import="nets.sso.agent.web.v2020.conf.SSOSite" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String jsonData;
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    try {
        AuthCheck authCheck = new AuthCheck(request, response);
        SSOProvider ssoProvider = authCheck.getSsoProvider();
        SSOSite ssoSite = authCheck.getSsoSite();
        String logonUrl = ssoSite.getProviderLoginUrl(request);
        String logoffUrl = ssoSite.getProviderLogoutUrl(request);
        String ssoServiceUrl = ssoSite.getProviderSsoUrl(request);
        sb
                .append("\"result\": true,")
                .append("\"ssosite\": \"").append(ssoSite.getCode()).append("\",")
                .append("\"defaultUrl\": \"").append(ssoSite.getUrl()).append("\",")
                .append("\"urlSSOLogonService\": \"").append(logonUrl).append("\",")
                .append("\"urlSSOCheckService\": \"").append(ssoServiceUrl).append("\",")
                .append("\"urlSSOLogoffService\": \"").append(logoffUrl).append("\"");
    } catch (AgentException ae) {
        sb
                .append("\"result\": false,")
                .append("\"errorCode\": ").append(ae.getExceptionCode().getValue()).append(",")
                .append("\"errorMessage\": \"").append(ae.getMessage()).append("\"");
    }
    sb.append("}");
    jsonData = sb.toString();
%><%=jsonData%>
