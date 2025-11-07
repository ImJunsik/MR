<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.webservice.AgentServiceProxy" %>
<%@ page import="nets.sso.agent.web.v2020.common.webservice.ReceiveResponse" %>
<%
    AuthCheck auth = new AuthCheck(request, response);
    AgentServiceProxy serviceProxy = new AgentServiceProxy(auth.getSsoProvider());
    ReceiveResponse receiveResponse = serviceProxy.generateKey(request.getServerName());
    String keyBox = receiveResponse.getData("keyBox");
%><%=keyBox%>