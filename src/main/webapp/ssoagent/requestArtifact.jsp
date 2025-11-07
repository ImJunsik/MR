<%@ page import="nets.sso.agent.web.v2020.authcheck.AuthCheck" %>
<%@ page import="nets.sso.agent.web.v2020.common.enums.AuthStatus" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentException" %>
<%@ page import="nets.sso.agent.web.v2020.common.exception.AgentExceptionCode" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.StrUtil" %>
<%@ page import="nets.sso.agent.web.v2020.common.util.WebUtil" %>
<%@ page import="nets.sso.agent.web.v2020.common.webservice.ReceiveResponse" %>
<%@ page import="nets.sso.agent.web.v2020.common.logging.SsoLogger" %>
<%@ page import="nets.sso.agent.web.v2020.conf.AgentConf" %>
<%
    String returnFormat = "{ \"status\":\"%s\", \"code\":\"%s\", \"uriScheme\":\"%s\", \"essoUrl\":\"%s\" }";
    String returnString = "";
    try
    {
        AuthCheck auth = new AuthCheck(request, response);
        AuthStatus status = auth.checkLogon();
        //인증상태별 처리
        if (status == AuthStatus.SSOSuccess)
        {
            String targetID = WebUtil.getRequestValue(request, "targetID", StrUtil.EMPTY_STRING);
            String artifactParam = WebUtil.getRequestValue(request, "artifactParam", StrUtil.EMPTY_STRING);
            if (StrUtil.isNullOrEmpty(targetID))
                throw new AgentException(AgentExceptionCode.ArtifactMissingTargetID);

            ReceiveResponse receiveResponse = auth.requestArtifact(targetID, artifactParam);
            if (receiveResponse.getData("status").equalsIgnoreCase("true"))
            {
                //연동 성공
                if (receiveResponse.getData("targetType").equalsIgnoreCase("03"))
                {
                    //Application 형식
                    returnString = String.format(returnFormat,
                            "true",
                            receiveResponse.getData("artifactID"),
                            receiveResponse.getData("uriScheme"),
                            receiveResponse.getData("essoUrl")
                    );
                }
                else
                {
                    //Web 형식
                    returnString = String.format(returnFormat,
                            "true",
                            receiveResponse.getData("artifactID"),
                            StrUtil.EMPTY_STRING,
                            receiveResponse.getData("essoUrl")
                    );
                }
            }
            else
            {
                //연동 실패
                returnString = String.format(returnFormat,
                        "false",
                        receiveResponse.getData("errorCode"),
                        StrUtil.EMPTY_STRING,
                        receiveResponse.getData("essoUrl")
                );
            }
        }
        else
        {
            throw new AgentException(AgentExceptionCode.ArtifactRequiredLogon);
        }
    }
    catch (AgentException e)
    {
        //오류 발생
        SsoLogger.log(AgentConf.LogLevel.ERROR, e, request);
        returnString = String.format(returnFormat,
                "false",
                e.getExceptionCode().getValue(),
                StrUtil.EMPTY_STRING,
                StrUtil.EMPTY_STRING
        );
    }
%><%=WebUtil.stripTag(returnString)%>