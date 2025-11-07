<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.TFAReceiver" %>
<%@ page import="nets.sso.agent.web.v2020.common.enums.AuthStatus" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String jsonData = "{}";
    try
    {
        String errorMessage;
        AuthCheck auth = new AuthCheck(request, response);
        TFAReceiver tfaReceiver = new TFAReceiver(auth);
        if (!tfaReceiver.getEnable())
        {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("{")
                    .append("\"result\": false,")
                    .append("\"errorCode\": 11100023").append(",")
                    .append("\"errorMessage\": \"").append("NETS*SSO TFA is disabled.").append("\",")
                    .append("}");
            jsonData = sb.toString();
        }
        else
        {
            if (!auth.getSsoProvider().getMfa().getTwoStep())
            {
                StringBuilder sb = new StringBuilder();
                sb
                        .append("{")
                        .append("\"result\": false,")
                        .append("\"errorCode\": 11100004").append(",")
                        .append("\"errorMessage\": \"").append("2-step process of NETS*SSO TFA is disabled.").append("\",")
                        .append("}");
                jsonData = sb.toString();
            }
            else
            {
                if (!tfaReceiver.getTargetYN())
                {
                    StringBuilder sb = new StringBuilder();
                    sb
                            .append("{")
                            .append("\"result\": false,")
                            .append("\"errorCode\": 13100003").append(",")
                            .append("\"errorMessage\": \"").append(tfaReceiver.getUserID()).append(" is not user who must be authenticated with TFA.").append("\",")
                            .append("}");
                    jsonData = sb.toString();
                }
                else
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("{")
                            .append("\"result\": true,")
                            .append("\"errorCode\": 0,")
//                            .append("\"userID\": \"").append(tfaReceiver.getUserID()).append("\",")
                            .append("\"tfaID\": \"").append(tfaReceiver.getTfaID()).append("\",")
                            .append("\"targetYN\": ").append(String.valueOf(tfaReceiver.getTargetYN()).toLowerCase()).append(",")
                            .append("\"device\": \"").append(tfaReceiver.getDevice()).append("\",")
                            .append("\"code\": \"").append(tfaReceiver.getCode()).append("\",")
                            .append("\"method\": \"").append(tfaReceiver.getMethod()).append("\",")
                            .append("\"timeoutMinutes\": ").append(auth.getSsoProvider().getMfa().getExpireMin())
                            .append("}");
                    jsonData = sb.toString();
                }
            }
        }
    }
    catch (AgentException ae)
    {
        StringBuilder sb = new StringBuilder();
        sb
                .append("{")
                .append("\"result\": false,")
                .append("\"authStatus\": \"").append(AuthStatus.SSOFail.name()).append("\",")
                .append("\"errorCode\": ").append(ae.getExceptionCode().getValue()).append(",")
                .append("\"errorMessage\": \"").append(ae.getMessage()).append("\",")
                .append("}");
        jsonData = sb.toString();
    }
%><%=jsonData%>