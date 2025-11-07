<%@ page import="java.util.Enumeration" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head><title>SSO 통합인증 테스트 사이트</title></head>
<body>
<%
    Enumeration<String> eHeader = request.getHeaderNames();
    while (eHeader.hasMoreElements())
    {
        String hName = (String) eHeader.nextElement();
        String hValue = request.getHeader(hName);
%>   <%=WebUtil.stripTag(hName + ":" + hValue)%><br/>
<%  } %>
<br/>
requeset.getServerName():<%=WebUtil.stripTag(request.getServerName())%><br/>
requeset.getRemoteAddr():<%=WebUtil.stripTag(request.getRemoteAddr())%><br/>
</body>
</html>
