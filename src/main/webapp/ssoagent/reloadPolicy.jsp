<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page import="nets.sso.agent.web.v2020.conf.SSOConfig" %>
<%@ page import="nets.sso.agent.web.v2020.conf.SSOProvider" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int prePolicyVer = 0;
    int postPolicyVer = 0;
    try
    {
        prePolicyVer = SSOConfig.getInstance().getCurrentSSOProvider(request.getServerName()).getPolicyVer();
    }
    catch (Exception ignore)
    {
    }
    SSOConfig.reLoad();
    SSOConfig ssoConfig = SSOConfig.getInstance();
    if (ssoConfig == null)
    {
%>The instance of SSOConfig is null.<%
        return;
    }
%>
<%=ssoConfig.toXML()%>
<%
    SSOProvider ssoProvider = ssoConfig.getCurrentSSOProvider(request.getServerName());
    if (null == ssoProvider)
    {
%>
Can not find configuration for '<%=WebUtil.stripTag(request.getServerName())%>'.
<%
    return;
}
    postPolicyVer = SSOConfig.getInstance().getCurrentSSOProvider(request.getServerName()).getPolicyVer();
%>
policy version:<%=WebUtil.stripTag(String.valueOf(prePolicyVer))%> -> <%=WebUtil.stripTag(String.valueOf(postPolicyVer))%>
