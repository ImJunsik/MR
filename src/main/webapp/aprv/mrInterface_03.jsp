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
<%@ page import="com.mr.tech.domain.TechInvestVO"%>
<%@ page import="com.mr.tech.domain.TechReviewVO"%>
<%@ page import="com.mr.comp.domain.CompRptVO"%>

<%@ page import="com.base.util.mrInterfaceUtil"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="com.mr.step.domain.ChrgrChgHist"%>

<%

/*
*전자결재에서 승인/반려 처리 리턴값
*/
    request.setCharacterEncoding("UTF-8");
    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;   
    
    //통합결재 리턴값
    String connkey = "";        //전자결재번호
    String connCode = "";       //문서종류
    String connStatGubun = "";  //승인반려
    String uid = "";            //사용자ID
    String connAprverRemrk = "";//의견
    
    
    HashMap paramMap = new HashMap();
    Model model = null;
    MRrqRegisterVO mRrqRegisterVO = new MRrqRegisterVO();
    TechInvestVO techInvestVO = new TechInvestVO();
    TechReviewVO techReviewVO = new TechReviewVO();
    CompRptVO compRptVO = new CompRptVO();
    
    List<ChrgrChgHist> appLine = null;
    
    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
    MrAprvIfGwController controller = (MrAprvIfGwController)context.getBean("mrAprvIfGwController");
    
    Document doc = null;
    try{
        
        //System.out.println("test 03");        
        
        doc = (Document)mrInterfaceUtil.parsePostParam(request.getInputStream());
        doc.getDocumentElement().normalize();        
        
        Node nodeConnkey = doc.getElementsByTagName("connkey").item(0);
        Node nodeConnCode = doc.getElementsByTagName("connCode").item(0);
        Node nodeConnStatGubun = doc.getElementsByTagName("connStatGubun").item(0);
        Node nodeUid = doc.getElementsByTagName("uid").item(0);
        Node nodeConnAprverRemrk = doc.getElementsByTagName("connAprverRemrk").item(0);        
        
        connkey = nodeConnkey.getFirstChild().getNodeValue();
        connCode = nodeConnCode.getFirstChild().getNodeValue();
        connStatGubun = nodeConnStatGubun.getFirstChild().getNodeValue();
        uid = nodeUid.getFirstChild().getNodeValue();
        connAprverRemrk = nodeConnAprverRemrk.getFirstChild().getNodeValue();        
        
    } catch(Exception e ) {
        System.out.println("jewook e " +e);
    } 
       
        /*
        connkey = request.getParameter("connkey");
        connCode = request.getParameter("connCode");
        connStatGubun = request.getParameter("connStatGubun");
        uid = request.getParameter("uid");
        connAprverRemrk = request.getParameter("connAprverRemrk");
       */
       
        /*
        System.out.println("connkey  "+ connkey);
        System.out.println("connCode  "+ connCode);
        System.out.println("connStatGubun  "+ connStatGubun);
        System.out.println("uid  "+ uid);
        */
        
        paramMap.put("connkey", connkey);
        paramMap.put("connCode", connCode);
        paramMap.put("connStatGubun", connStatGubun);
        paramMap.put("uid", uid);       
        
        MrAprvIfGwController ctl = new MrAprvIfGwController();
        
        controller.setInterfaceFile(this.getClass().getSimpleName());
	    controller.setConnCode(connCode);
        System.out.println("connCode : " + connCode);
        if("Z0010".equals(connCode)){                      //MR요청서 승인/반려처리
        	mRrqRegisterVO = controller.selectMRrqRegisterVO(paramMap, model);
            controller.appAgreeReturn_Z0010(mRrqRegisterVO, paramMap, model);	
        }
        else if("Z0030_Z0110".equals(connCode)){           //초기투자비 승인/반려처리
        	techInvestVO = controller.selectTechInvestVO(paramMap, model);
        	controller.appAgreeReturn_Z0030_Z0110(techInvestVO, paramMap, model);
        	// 20210614 Insert시 Commit 시키고 조회를 위해 URL을 태운다  - yoo
         	//response.sendRedirect("/mr/mrTechInvestReadAndMail.do?mrReqNo=" + techInvestVO.getMrReqNo()+"&mrAction=" + mrReqProcStepDetNo);
            //pageContext.forward("mrTechInvestRead.do?mrReqNo=" + techInvestVO.getMrReqNo()+"&mrAction=" + mrReqProcStepDetNo);
        }
        else if("Z0030_Z0122".equals(connCode)){        	
        	System.out.println("mrInterface_03.jsp_Z0030_Z0122");        	//타당성검토 승인/반려처리
        	techReviewVO = controller.selectTechReviewVO(paramMap, model);
            controller.appAgreeReturn_Z0030_Z0122(techReviewVO, paramMap, model);
        }
        else if("Z0090".equals(connCode)){
        	compRptVO = controller.selectCompRptVO(paramMap, model);
            controller.appAgreeReturn_Z0090(compRptVO, paramMap, model);
        }
    //TODO if log 테이블 추가해야 함
   
%>
<?xml version="1.0" encoding="utf-8"?>
<DATA>
 <APRV_RESULT>01</APRV_RESULT>
</DATA>