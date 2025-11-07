<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="org.springframework.stereotype.Controller"%>
<%@ page import="org.springframework.ui.Model"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.Node"%>
<%@ page import="com.mr.common.controller.*"%>
<%@ page import="com.mr.mrrq.domain.MRrqRegisterVO"%>
<%@ page import="com.base.util.mrInterfaceUtil"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="com.mr.step.domain.ChrgrChgHist"%>
<%@ page import="com.common.file.web.AttachFileController"%>



AttachFileController
<%
    request.setCharacterEncoding("UTF-8");
    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;   
    
    //통합결재 리턴값
    String connkey = "";        //전자결재번호
    String connCode = "";       //문서종류
    String connAprvFileNm = ""; //문서파일명
    String connAprvFileCd = ""; //문서시디
    String connAprvFileId = ""; //문서파일아이디
    String uid = "";            //사용자ID
    
    HashMap paramMap = new HashMap();
    Model model = null;
    MRrqRegisterVO mRrqRegisterVO = new MRrqRegisterVO();
    Document doc = null;
    List<ChrgrChgHist> appLine = null;
    
    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
    MrAprvIfGwController controller = (MrAprvIfGwController)context.getBean("mrAprvIfGwController");
    
    
    try{
        
        System.out.println("test 04");
        /*
        doc = (Document)mrInterfaceUtil.parsePostParam(request.getInputStream());
        doc.getDocumentElement().normalize();
        
        Node nodeConnkey = doc.getElementsByTagName("connkey").item(0);
        Node nodeConnCode = doc.getElementsByTagName("connCode").item(0);
        Node nodeConnAprvFileCd = doc.getElementsByTagName("connAprvFileCd").item(0);
        Node nodeConnAprvFileId = doc.getElementsByTagName("connAprvFileId").item(0);
        Node nodeUid = doc.getElementsByTagName("uid").item(0);
        
        connkey = nodeConnkey.getFirstChild().getNodeValue();
        connCode = nodeConnCode.getFirstChild().getNodeValue();
        connAprvFileCd = nodeConnAprvFileCd.getFirstChild().getNodeValue();
        connAprvFileId = nodeConnAprvFileId.getFirstChild().getNodeValue();
        uid = nodeUid.getFirstChild().getNodeValue();
        */
        connkey = request.getParameter("connkey");
        connCode = request.getParameter("connCode");
        connAprvFileCd = request.getParameter("connAprvFileCd");
        connAprvFileId = request.getParameter("connAprvFileId");
        uid = request.getParameter("uid");
        controller.setInterfaceFile(this.getClass().getSimpleName());
        controller.setConnCode(connCode);
        
    } catch(Exception e ) {
        System.out.println("jewook e " +e);
    } 
        //System.out.println("connkey  "+ connkey);
        //System.out.println("connCode  "+ connCode);
        //System.out.println("connAprvFileCd  "+ connAprvFileCd);
        //System.out.println("connAprvFileId  "+ connAprvFileId);
        //System.out.println("uid  "+ uid);
        
        paramMap.put("connkey", connkey);
        paramMap.put("connCode", connCode);
        paramMap.put("connAprvFileCd", connAprvFileCd);
        paramMap.put("connAprvFileId", connAprvFileId);
        paramMap.put("uid", uid);
        
        MrAprvIfGwController ctl = new MrAprvIfGwController();
        //ctl.appDownload(connAprvFileId);
        
        //AttachFileController ctl = new AttachFileController();
        //ctl.download(connAprvFileId, request);
   
    //TODO if log 테이블 추가해야 함
%>

<html>
<head>
<title>파일다운로드22</title>
</head>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<body>
    <script type="text/javascript">
    
    $(window).ready(function() {
        //var popup = window.open("_self", "hiddenframe", "width=0, height=0, top=0, statusbar=no, scrollbars=no, toolbar=no");
        //$.popup({url:"/download.do", width:"500", height:"500", targetName:"hiddenframe"});
    	
        $("#frm").attr("action", "http:localhost:8084/mr/main.do").submit();
        
    });
    </script>
    
</body>
<form id="frm" method="post">
<!-- <iframe width=0 height=0 name="hiddenframe" style="display:none;"></iframe> -->
<input type="hidden"  name="drawMngNo" value="${connAprvFileId}"/>
</form>
</html>

