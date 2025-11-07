<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.authcheck.TFAReceiver" %>
<%@ page import="nets.sso.agent.web.v2020.common.constant.ParamInfo" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page import="nets.sso.agent.web.v2020.common.logging.SsoLogger" %>
<%@ page import="nets.sso.agent.web.v2020.conf.AgentConf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    try
    {
        AuthCheck auth = new AuthCheck(request, response);
        String logonUrl = String.format("%s?%s=%s",
                auth.getSsoSite().getProviderLoginUrl(request),
                ParamInfo.SITE_ID,
                auth.getSsoSite().getCode()
        );

        String errorMessage = "";
        TFAReceiver tfaReceiver = new TFAReceiver(auth);
        if (!tfaReceiver.getEnable())
            errorMessage = "TFA is disabled.";

        if (tfaReceiver.getEnable() && auth.getSsoProvider().getMfa().getTwoStep() && tfaReceiver.getTargetYN())
        {
            String tfaMethod = tfaReceiver.getMethod();
            Map<String, String> misc = tfaReceiver.getMiscellaneous();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>NETS*SSO TFA</title>
    <script type="text/javascript">
        function startTimer(duration, display) {
            if (0 == duration) {
                display.textContent = "limitless";
            } else {
                var timer = duration, minutes, seconds;
                setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);
                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;
                    display.textContent = minutes + ":" + seconds;
                    if (--timer < 0) {
                        timer = duration;
                    }
                }, 1000);
            }
        }
        function OnLogon() {
            if (document.getElementById("txtCode").value == "") {
                alert("보안 코드를 입력해주세요.");
                return;
            }
            document.forms["form1"].target = "_top";
            document.forms["form1"].submit();
        }
        function OnInit() {
            document.getElementById("txtCode").focus();
            var minutes = 60 * <%=auth.getSsoProvider().getMfa().getExpireMin()%>;
            var display = document.querySelector('#time');
            startTimer(minutes, display);
        }
    </script>
</head>
<body onLoad="OnInit();">
<form id="form1" method="post" action="<%=WebUtil.stripTag(logonUrl)%>">
    <input type="hidden" name="<%=ParamInfo.USER_ID%>" value="<%=WebUtil.stripTag(tfaReceiver.getUserID())%>"/>
    <input type="hidden" name="<%=ParamInfo.TFA_ID%>" value="<%=WebUtil.stripTag(tfaReceiver.getTfaID())%>"/>
    <input type="hidden" name="<%=ParamInfo.RETURN_URL%>" value="<%=WebUtil.stripTag(tfaReceiver.getReturnUrl())%>"/>
    <input type="hidden" id="credType" name="<%=ParamInfo.CRED_TYPE%>" value="NTFA"/>
    <% if (StrUtil.isNotEmpty(tfaReceiver.getDevice()))
    {%>
    <input type="hidden" name="<%=ParamInfo.DEVICE%>" value="<%=WebUtil.stripTag(tfaReceiver.getDevice())%>"/>
    <% } %>
    <table>
        <tr>
            <td colspan="2">Two factor authentication</td>
        </tr>
        <tr>
            <td>User ID :</td>
            <td><%=WebUtil.stripTag(tfaReceiver.getUserID())%>
            </td>
        </tr>
        <tr>
            <td>Secret Code :</td>
            <td><input type="text" id="txtCode" name="<%=ParamInfo.CODE%>"/> left: <span id="time"></span></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="Logon" onclick="OnLogon();"/></td>
        </tr>
    </table>
</form>
</body>
</html>
<%
    }
    else
    {
        if (!auth.getSsoProvider().getMfa().getEnable())
        { %>NETS*SSO TFA is disabled.<% }
        else if (!auth.getSsoProvider().getMfa().getTwoStep())
        { %>2 step process of NETS*SSO TFA is disabled.<% }
        else if (!tfaReceiver.getTargetYN())
        { %>"<%=WebUtil.stripTag(tfaReceiver.getUserID())%>" is not user who must be authenticated with TFA.<% }
    %><br/><a href="../logon.jsp">logon page</a><%
    }
}
catch (AgentException e)
{
    SsoLogger.log(AgentConf.LogLevel.ERROR, e, request);
}
%>