<%@ page import="com.mr.step.domain.ChrgrChgHist"%>
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
<%@ page import="com.mr.step.domain.MrReqProcStepDetVO"%>
<%@ page import="com.base.util.mrInterfaceUtil"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
/* hajewook
* mrInterface_01.jsp 
* 통합결재 페이지에서  데이터 전송 성공실패 유무를 전송
*/
    HashMap paramMap = new HashMap();  //전자결재 전달 인수 값 
 
    request.setCharacterEncoding("UTF-8");
    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;   
    MRrqRegisterVO mRrqRegisterVO = new MRrqRegisterVO();
    TechInvestVO techInvestVO = new TechInvestVO();
    TechReviewVO techReviewVO = new TechReviewVO();
    CompRptVO compRptVO = new CompRptVO();    
    MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
    Model model = null;
    //통합결재 리턴값
    String rslt = "01";
    
    String uid = "";            //userId
    String connkey = "";        //전자결배번호
    String connCode = "";       //문서종류
    String connIfName = "";     //인퍼페이스 명
    String connIfStatGubun = "";//인터페이스 성공,실패여부
    
    Document doc = null;
    //파리미터 받는 방식 스트림으로 변경
    try{
    	InputStream is = request.getInputStream();
    	System.out.println("read : " + is.read());
        doc = (Document)mrInterfaceUtil.parsePostParam(is);
        doc.getDocumentElement().normalize();
        Node nodeUid = doc.getElementsByTagName("uid").item(0);
        Node nodeConnkey = doc.getElementsByTagName("connkey").item(0);
        Node nodeConnCode = doc.getElementsByTagName("connCode").item(0);
        Node nodeConnIfName = doc.getElementsByTagName("connIfName").item(0);
        Node nodeConnIfStatGubun = doc.getElementsByTagName("connIfStatGubun").item(0);
         
        uid = nodeUid.getFirstChild().getNodeValue();
        connkey = nodeConnkey.getFirstChild().getNodeValue();
        connCode = nodeConnCode.getFirstChild().getNodeValue();
        connIfName = nodeConnIfName.getFirstChild().getNodeValue();
        connIfStatGubun = nodeConnIfStatGubun.getFirstChild().getNodeValue();
        
    
	    /* uid = request.getParameter("uid");
	    connkey = request.getParameter("connkey");
	    connCode = request.getParameter("connCode");
	    connIfName = request.getParameter("connIfName");
	    connIfStatGubun = request.getParameter("connIfStatGubun");
	    */
	    
	    System.out.println("mrInterface_01.jsp");
	    System.out.println("connkey  "+ connkey);
	    System.out.println("connCode  "+ connCode);
	    System.out.println("connIfStatGubun  "+ connIfStatGubun);
	    
	    paramMap.put("connkey", connkey);    //MR 번호 저장
	    paramMap.put("connCode", connCode);  //문서종류
	    
	    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
	    MrAprvIfGwController controller = (MrAprvIfGwController)context.getBean("mrAprvIfGwController");
	    controller.setInterfaceFile(this.getClass().getSimpleName());
	    controller.setConnCode(connCode);

	    if("Z0010".equals(connCode)){
	    	//mr요청서
	    	mRrqRegisterVO = controller.mrAprvInit_Z0010(paramMap, model);    	
	    	mrReqProcStepDetVO.setMrReqNo(mRrqRegisterVO.getMrReqNo());   	
	    	mrReqProcStepDetVO.setAppLine(mRrqRegisterVO.getAppLine());
	    }
	    else if("Z0030_Z0110".equals(connCode)){
	    	//초기투자비
		    techInvestVO = controller.mrAprvInit_Z0030_Z0110(paramMap, model);
	    	mrReqProcStepDetVO.setMrReqNo(techInvestVO.getMrReqNo());
	    	mrReqProcStepDetVO.setAppLine(techInvestVO.getAppLine());
	    }
	    else if("Z0030_Z0122".equals(connCode)){	    	
	        //타당성검토 승인
	        //System.out.println("mrInterface_01.jsp_Z0030_Z0122");
	        techReviewVO = controller.mrAprvInit_Z0030_Z0122(paramMap, model);
	    	mrReqProcStepDetVO.setMrReqNo(techReviewVO.getMrReqNo());
	    	mrReqProcStepDetVO.setAppLine(techReviewVO.getAppLine());
	    }
	    else if("Z0090".equals(connCode)){
	        //MR완료
	    	compRptVO = controller.mrAprvInit_Z0090(paramMap, model);
	    	mrReqProcStepDetVO.setMrReqNo(compRptVO.getMrReqNo());
	    	mrReqProcStepDetVO.setAppLine(compRptVO.getAppLine());
	    }

	    mrReqProcStepDetVO.setConnIfName(connIfName);
	    mrReqProcStepDetVO.setConnIfStatGubun(connIfStatGubun);
	    
	    List<ChrgrChgHist> appLine = mrReqProcStepDetVO.getAppLine();
    	
   		for(ChrgrChgHist chrgrChgHist : appLine){
    		//System.out.println("*getMrReqNo : " + chrgrChgHist.getMrReqNo() + " ****");
    		//System.out.println("*getMrReqProcStepDetNo : " + chrgrChgHist.getMrReqProcStepDetNo() + " ****");
    		mrReqProcStepDetVO.setMrReqProcStepDetNo(chrgrChgHist.getMrReqProcStepDetNo()); 
    	  	controller.updateMrIfStat01(mrReqProcStepDetVO, model);
    	}   		
    } catch(Exception e ) {
        rslt = "02";
        System.out.println("3333e " +e);
    }
%>

<?xml version="1.0" encoding="utf-8"?>
<DATA>
    <APRV_IF_NAME><%=connIfName %></APRV_IF_NAME>
    <APRV_RESULT><%=rslt %></APRV_RESULT>
</DATA>