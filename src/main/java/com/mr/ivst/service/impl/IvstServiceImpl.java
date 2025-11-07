package com.mr.ivst.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.eai.CmEaiManager;
import com.common.file.domain.MrAtchFile;
import com.common.file.repository.MrAtchFileRepository;
import com.common.file.service.MrAtchFileService;
import com.mr.ivst.domain.IvstCostItemSendVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.ivst.domain.IvstCostRptVO;
import com.mr.ivst.domain.IvstCostSendVO;
import com.mr.ivst.repository.IvstDAO;
import com.mr.ivst.service.IvstService;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;

@Service
public class IvstServiceImpl extends BaseService implements IvstService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    IvstDAO ivstDAO;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrAtchFileRepository mrAtchFileRepository;

    @Override
    public IvstCostRegisterVO selectIvstCost(int mrReqNo) {
        return ivstDAO.selectIvstCost(mrReqNo);
    }

    @Override
    public IvstCostRptVO selectIvstCostRpt(int mrReqNo) {
        List<MrAtchFile> mrAtchFiles = null;
        IvstCostRptVO ivstCostRptVO = null;
        ivstCostRptVO = ivstDAO.selectIvstCostRpt(mrReqNo);

        if(ivstCostRptVO!=null) mrAtchFiles = mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0050");
        if(mrAtchFiles!=null) ivstCostRptVO.setMrAtchFiles(mrAtchFiles);

        return ivstCostRptVO;
    }

    @Override
    public void insertIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
        ivstDAO.insertIvstCostRpt(ivstCostRptVO);

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(ivstCostRptVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(ivstCostRptVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(ivstCostRptVO.getMrReqNo());
                mrAtchFile.setMrStepCd("Z0050");
                mrAtchFile.setChrgrClCd("Z02D");
                mrAtchFile.setFileStepCd("0701");

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }
                
            }
        }
    }
    
    //wj 추가
    //투가구분, 투자목적 임시저장
    @Override
    public void insertIvstCostRpt_2(IvstCostRegisterVO ivstCostRegisterVO) {
        ivstDAO.insertIvstCostRpt_2(ivstCostRegisterVO);     
    }    

    @Override
    public void updateIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
        ivstDAO.updateIvstCostRpt(ivstCostRptVO);

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(ivstCostRptVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(ivstCostRptVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(ivstCostRptVO.getMrReqNo());
                mrAtchFile.setMrStepCd("Z0050");
                mrAtchFile.setChrgrClCd("Z02D");
                mrAtchFile.setFileStepCd("0701");

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }
            }
        }
    }
 
    //yoo 241224
    //투자지출 품의서 sap 발송
    @Override
    public void sendIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
    //public HashMap sendIvstCostRpt(IvstCostRptVO ivstCostRptVO) {   //sap용
        if(ivstCostRptVO.getMrInvstCostRptNo() > 0 ) {
            updateIvstCostRpt(ivstCostRptVO);
        } else {
            insertIvstCostRpt(ivstCostRptVO);
        }
        /*
        첨부파일
        if (CollectionUtils.isNotEmpty(ivstCostRptVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(ivstCostRptVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(ivstCostRptVO.getMrReqNo());
                mrAtchFile.setMrStepCd("Z0050");
                mrAtchFile.setChrgrClCd("Z02D");
                mrAtchFile.setFileStepCd("0701");

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }
            }
        }*/

      	// EAI-SAP Interface 송신
       	String sapInvstCostRptNo = "";
       	sapInvstCostRptNo = eaiConIvstCostRpt(ivstCostRptVO);
       	

       	//IF_OUT EAI-SAP Interface 송신 용-->
       	/*
       	HashMap map = eaiConIvstCostRpt(ivstCostRptVO);
       	
       	HashMap<String, String> iReturn = (HashMap<String, String>) map.get("I_RETURN") ;
        logger.info("### IF_OUT="+map);
        logger.info("### I_RETURN="+map.get("I_RETURN"));
        logger.info("### iReturn.get(CHKCD)="+iReturn.get("CHKCD"));

        if (!iReturn.get("CHKCD").equals("S")) {
            logger.info("### EAI Connect Error!");
        } else {
            logger.info("### 투자지출 승인번호="+(String)map.get("I_ZMANUM"));
            sapInvstCostRptNo = (String)map.get("I_ZMANUM");
        }  
        */

       	if(sapInvstCostRptNo!=null && sapInvstCostRptNo!="") {
       		// SAP 발송정보 업데이트
       		ivstCostRptVO.setSapInvstCostRptNo(sapInvstCostRptNo);
       		ivstDAO.sendIvstCostRpt(ivstCostRptVO);
       		ivstDAO.sendIvstCostRpt2(ivstCostRptVO);
	
       		// STEP 단계 업데이트
       		mrStepService.updateMrStep(ivstCostRptVO.getMrReqNo(), "Z0050", "Z0121", "Z02D", "Z0050", "Z02D");
	
       		// 메일송신 : MR요청번호, 본문내용 CAPEX_NO생성요청 => SAP에서 보내기로함
       		// 수신자   : 경영분석담당자
       		// mrMailService.mailSend(ivstCostRptVO.getMrReqNo());
       	}
       	//return map;   //EAI-SAP Interface 송신 용
    }
    
    @Override
    public void skipIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
        if(ivstCostRptVO.getMrInvstCostRptNo() > 0 ) {
            updateIvstCostRpt(ivstCostRptVO);
        } else {
            insertIvstCostRpt(ivstCostRptVO);
        }

        // 2015-11-23 ZERO CAPEX일 경우 투자지출 품의 SKIP 처리
        System.out.println("투자지출 품의 SKIP :: "+ivstCostRptVO.getInvstCostTotal());

        // STEP 단계 업데이트
       	mrStepService.insertNextAppEmp(ivstCostRptVO.getMrReqNo(), "Z0131");
        ivstDAO.skipIvstCostRpt(ivstCostRptVO);  //투자지출 품의서 CAPEX 생성 된 것으로 보고 MR 수행으로 넘김
    }
    
    //투자지출품의 기각
    @Override
    public void endIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
        int mrReqNo = ivstCostRptVO.getMrReqNo();
        
        // MR_REQ_MST 업데이트(종료처리)
        ivstDAO.updateIvstCostRptEnd(ivstCostRptVO);

        // 메일송신 : MR요청번호, 본문내용=투자지출 품의 최종 단계에서 품의가 기각되었음을 알립니다.
        // 수신자   : 관련된 모든담당자
        mrMailService.mailSend(ivstCostRptVO.getMrReqNo());

        // MR_STEP 종료처리
        mrStepService.insertNextAppEmp(mrReqNo, "Z0143");
        mrStepService.updateMrStepEffEnd(mrReqNo);
    }
// yoo 241224 품의 번호와 함께 에러 메시지도 리턴하도록 변경 함
    @Override
    public String eaiConIvstCostRpt(IvstCostRptVO ivstCostRptVO) {
    //public HashMap eaiConIvstCostRpt(IvstCostRptVO ivstCostRptVO) {

        // SAP 송신정보 조회
        IvstCostSendVO ivstCostSendVO = null;
        ivstCostSendVO = ivstDAO.selectIvstCostSend(ivstCostRptVO.getMrReqNo());

        List<IvstCostItemSendVO> ivstCostItemSendVO = null;
        ivstCostItemSendVO = ivstDAO.selectIvstCostItemSend(ivstCostRptVO.getMrReqNo());

        String sapInvstCostRptNo = "";
        
        
        // yoo 온실가스 값 주입
        String Zoutqty = ivstCostRptVO.getZoutqty();
        if(Zoutqty == null)
        {
        	ivstCostSendVO.setZoutqty("0");     /// 온실가스 배출량
        }else
        {
	        Zoutqty = Zoutqty.replace(",", "");
	        ivstCostSendVO.setZoutqty(Zoutqty);     /// 온실가스 배출량
        }
        String Zoutamt = ivstCostRptVO.getZoutamt();
        if(Zoutamt == null)
        {
        	ivstCostSendVO.setZoutamt("0");
        }else
        {
        	Zoutamt = Zoutamt.replace(",", "");
        	ivstCostSendVO.setZoutamt(Zoutamt);
        }
        

        logger.info("### EAI Start!!");
        HashMap request = new HashMap();    // 실제로 전송할 HashMap
        HashMap<String, Object> reqData = new HashMap<>() ;
        request.put("IF_IN", reqData) ;

        HashMap IF_IN_RPT = new HashMap();     // Input 투자지출품의정보
        HashMap[] IF_IN_COST = new HashMap[ivstCostItemSendVO.size()] ;   // Input 투자지출비용항목

        HashMap IF_OUT = new HashMap();     // Output

        HashMap[] dataF = new HashMap[1] ;

        /* 투자지출품의서 마스터 전송 */
        IF_IN_RPT.put("KOKRS", ivstCostSendVO.getMngAccount());     /// 관리회계
        IF_IN_RPT.put("ZIYEAR", ivstCostSendVO.getInvstYear());     /// 투자년도
        IF_IN_RPT.put("ESTYEAR", ivstCostSendVO.getInvstYear());    /// 회계년도
        IF_IN_RPT.put("RESP_DEPT", ivstCostSendVO.getRespTeamCd()); /// 책임부서
        IF_IN_RPT.put("QMTXT", ivstCostSendVO.getMrReqTitle());     /// 투자명
        IF_IN_RPT.put("COST_DEPT", ivstCostSendVO.getCostTeamCd()); /// 원가부서
        IF_IN_RPT.put("ZINVTY", ivstCostSendVO.getInvstCd());       /// 투자구분
        IF_IN_RPT.put("INVOBJ", ivstCostSendVO.getInvstPurpCd());   /// 투자목적
        IF_IN_RPT.put("STAND", ivstCostSendVO.getMrProcNo());       /// 공정
        IF_IN_RPT.put("WERKS_I", ivstCostSendVO.getPlant());        /// 플랜트
        IF_IN_RPT.put("WERKS_SD", ivstCostSendVO.getRegionCd());    /// 지역
        IF_IN_RPT.put("FICLASS", ivstCostSendVO.getPropClass());    /// 자산클래스
        IF_IN_RPT.put("STRMN", ivstCostSendVO.getInvstStaDt());     /// 투자시작일
        IF_IN_RPT.put("LTRMN", ivstCostSendVO.getInvstEndDt());     /// 투자종료일
        IF_IN_RPT.put("ZREQID", ivstCostSendVO.getSapSender());     /// SAP시스템 사용자
        IF_IN_RPT.put("WAERS", ivstCostSendVO.getWaers());          /// 통화키
        IF_IN_RPT.put("ZSENDD", ivstCostSendVO.getSapSendDt());     /// 발송일
        IF_IN_RPT.put("ZSENDID", ivstCostSendVO.getSapSender());    /// 발송자
        IF_IN_RPT.put("ZCONFID", ivstCostSendVO.getMngAnalEmpNo()); /// 승인자
        IF_IN_RPT.put("ZLEGACY", ivstCostSendVO.getMrNo());         /// MR번호
        IF_IN_RPT.put("ZTXT_01", ivstCostSendVO.getBizPurpSurr());  /// 사업배경목적
        IF_IN_RPT.put("ZTXT_02", ivstCostSendVO.getImprvPlan());    /// 개선안
        IF_IN_RPT.put("ZTXT_03", ivstCostSendVO.getEptEft());       /// 기대효과
        IF_IN_RPT.put("ZTXT_04", "");                               /// 조정사유
        IF_IN_RPT.put("MANNO", ivstCostSendVO.getInvstManNo());     /// 사업계획 관리번호
        IF_IN_RPT.put("ZOUTQTY", ivstCostSendVO.getZoutqty());     /// 온실가스 배출량
        IF_IN_RPT.put("ZOUTAMT", ivstCostSendVO.getZoutamt());     /// 온실가스 배출권 비용

        dataF[0] = IF_IN_RPT ;
        reqData.put("IT_ZPMS0034", dataF);

        /* 투자지출품의서 비용항목 전송 */
        for(int i=0;i<ivstCostItemSendVO.size();i++) {
            HashMap dataC = new HashMap<>() ;

            dataC.put("KOKRS", ivstCostItemSendVO.get(i).getMngAccount()) ;
            dataC.put("ZIYEAR", ivstCostItemSendVO.get(i).getInvstYear()) ;
            dataC.put("ZCPITEM", ivstCostItemSendVO.get(i).getCostItemCd()) ;
            dataC.put("ZCPTXT", "") ;
            dataC.put("ZTOTAL", ivstCostItemSendVO.get(i).getCostTot()) ;
            dataC.put("ZMON01", ivstCostItemSendVO.get(i).getCost01()) ;
            dataC.put("ZMON02", ivstCostItemSendVO.get(i).getCost02()) ;
            dataC.put("ZMON03", ivstCostItemSendVO.get(i).getCost03()) ;
            dataC.put("ZMON04", ivstCostItemSendVO.get(i).getCost04()) ;
            dataC.put("ZMON05", ivstCostItemSendVO.get(i).getCost05()) ;
            dataC.put("ZMON06", ivstCostItemSendVO.get(i).getCost06()) ;
            dataC.put("ZMON07", ivstCostItemSendVO.get(i).getCost07()) ;
            dataC.put("ZMON08", ivstCostItemSendVO.get(i).getCost08()) ;
            dataC.put("ZMON09", ivstCostItemSendVO.get(i).getCost09()) ;
            dataC.put("ZMON10", ivstCostItemSendVO.get(i).getCost10()) ;
            dataC.put("ZMON11", ivstCostItemSendVO.get(i).getCost11()) ;
            dataC.put("ZMON12", ivstCostItemSendVO.get(i).getCost12()) ;

            IF_IN_COST[i] = dataC ;
        }
        reqData.put("IT_ZPMS0035", IF_IN_COST);

        try {
            // eAI Server Connect & Execute Service
            logger.info("### Eai Connect!!");
            CmEaiManager eaiCon = new CmEaiManager();
            logger.info("### CmEaiManager() Connect!! - EDIMS_SAP_CAPEX_REQUEST.srv");
            
            //String ip = "10.171.24.38:6300";
            ////String ip = "172.18.1.56:6300";
            //logger.info("### eaiCon.executeService!! ************ " + ip);
            //개발EAI
            //IF_OUT = eaiCon.executeService("172.18.1.56:6300", "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            //운영EAI            
            //IF_OUT = eaiCon.executeService("172.18.3.110:6300", "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            
            //SAP S4/HANA용 EAI 검증기 테스트
            ////IF_OUT = eaiCon.executeService("10.171.24.38:6300", "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            //IF_OUT = eaiCon.executeService(ip, "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            //logger.info("### eaiCon.executeService!! ***************** " + ip);
            //SAP S4/HANA용 EAI 운영기 테스트
            //IF_OUT = eaiCon.executeService("eai.oilbank.co.kr:6300", "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            IF_OUT = eaiCon.executeService("10.171.26.124:6300", "EDIMS_SAP_CAPEX_REQUEST.srv", "srcRoutine_Pub", request);
            
            logger.info("### ivst-eaiCon.executeService!!");
            
            Set set = IF_OUT.keySet();
            logger.info("### IF_OUT.keySet()");

            IF_OUT = (HashMap) IF_OUT.get("IF_OUT");
            HashMap<String, String> iReturn = (HashMap<String, String>) IF_OUT.get("I_RETURN") ;
            logger.info("### IF_OUT="+IF_OUT);
            logger.info("### I_RETURN="+IF_OUT.get("I_RETURN"));
            logger.info("### iReturn.get(CHKCD)="+iReturn.get("CHKCD"));

            if (!iReturn.get("CHKCD").equals("S")) {
                logger.info("### EAI Connect Error!");
            } else {
                logger.info("### 투자지출 승인번호="+(String)IF_OUT.get("I_ZMANUM"));
                sapInvstCostRptNo = (String)IF_OUT.get("I_ZMANUM");
            }

        } catch(Exception ex) {
            logger.error("Exception : " + getExceptionInfo(ex));
        	
        }
        //return IF_OUT; //sap용
        return sapInvstCostRptNo;
    }
    
    public String getExceptionInfo(Throwable e) 
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
    	StackTraceElement[] el = e.getStackTrace();
     	for (int i = 0; i < el.length; i++) 
    	{
     		if (i != 0)
     		{
     				sb.append("\n");
     				
     		}
    		sb.append(" ").append("at ").append(el[i].getClassName() + "." + el[i].getMethodName());
    		sb.append("(").append(el[i].getFileName()).append(":").append(el[i].getLineNumber()).append(")");
    		logger.error(sb.toString());
    		sb.setLength(0);
    	}
    	Throwable innerE = e.getCause(); 
    	if (innerE != null) 
    	{
    		sb.append("\nCaused by: ");
    		sb.append(getExceptionInfo(innerE));
    		logger.error(sb.toString());
    		sb.setLength(0);
    	} 
    	return sb.toString(); 
    }

}
