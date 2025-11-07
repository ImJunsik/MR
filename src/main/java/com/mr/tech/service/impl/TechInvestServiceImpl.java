package com.mr.tech.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.service.MrRvRstService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrReqProcStepDetVO;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechInvestVO;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.repository.TechInvestDao;
import com.mr.tech.service.TechInvestService;
/**
 * 기술 및 타당성 검토 서비스구현
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 박성룡 최초 작성
 * </pre>
 */
@Service
public class TechInvestServiceImpl extends BaseService implements TechInvestService{

    @Autowired
    TechInvestDao techInvestDao;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrRvRstService mrRvRstService;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrMailService mrMailService;

    //(201904반송기능추가)
    @Autowired
    MrStepRepository mrStepRepository;
    
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 투자비산정 내용 조회
     */
    @Override
    public TechInvestVO selectMrTechInvest(int mrReqNo) {
        TechInvestVO techInvestVO = techInvestDao.selectMrTechInvest(mrReqNo);

        if(techInvestVO!=null) {
            techInvestVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0030"));
        }

        return techInvestVO;
    }

    /**
     * 투자비 산정 내용 저장 (임시저장, 승인요청 버튼 클릭)
     */
    @Override
    public void insertMrTechInvest(TechInvestVO techInvestVO) {
    	
    	//투자비 산정 논리 삭제
    	techInvestDao.updateMrTechInvestDelYn(techInvestVO);    	

        if(techInvestVO.getTechInvests() != null) {
            for(TechInvestVO investVO : techInvestVO.getTechInvests()){
            	
            	//투자비 산정 내용저장
                investVO.setMrReqNo(techInvestVO.getMrReqNo());
                techInvestDao.insertMrTechInvest(investVO);
            }
        }

        //상세항목 업데이트
        if(techInvestVO.getRvRsts()!=null) {
            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(techInvestVO.getMrReqNo());
            updateDelYnRvRst.setItemCd("COST");            
            
            mrRvRstService.updateMrRvRstDelYnItemCd(updateDelYnRvRst);

            //mrRvRstVO 데이타를 
            for(MrRvRstVO mrRvRstVO : techInvestVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(techInvestVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.insertMrRvRst(mrRvRstVO);
            }
        }        
        
        //----------------------------------------------------//        
        //** Z02I1 처리절차
        //1. DET 테이블 Z02I1 전체 삭제처리 업데이트 
        //2. HIST 테이블 Z02I1 전체 삭제처리 업데이트
        //3. getAppLine 안에있는 Z02I1 담당자 DET 테이블에 인서트
        //4. 인서트한 DET NO를 가지고 HIST 인서트
        //----------------------------------------------------//
        mrStepService.updateDetHistDel(techInvestVO.getMrReqNo(),"Z0110", "Z0030", "Z02I1");			//테이블 업데이트(Z02I1 삭제처리)

        changeMrReqAppLine_Z02I1(techInvestVO.getMrReqNo(), "Z0030", null, techInvestVO.getAppLine());	//신규로 내역추가(Z02I1)        
        
        //직책과장, 선택된 직책과장을 제외한 나머지 직책과장들 상태값을 변경 , getChrgNo선택한 직책과장
        if(techInvestVO.getChrgDetNo()!=null){
        	techInvestDao.updateMrTechChrgNo(techInvestVO);
        }
        mrStepService.insertFile(techInvestVO.getMrReqNo(), "Z0030", "Z02I", "0301", techInvestVO.getMrAtchFiles());
    }
    
    //Z02I1저장    
    private void changeMrReqAppLine_Z02I1(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine) {
    	
    	int mrReqProcStepNo = 0;
    	
    	//권한체크
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);         

         //STEP NO
         mrReqProcStepNo = appInfo.getMrReqProcStepNo();         
         
         //Z02I1 입력(
         for (ChrgrChgHist chrgrChgHist: appLine) {        	
        	 
             if(chrgrChgHist.getChrgrClCd()!=null && chrgrChgHist.getChrgrClCd().equals("Z02I1"))  {
            	  //System.out.println("입력Z02I1  :: " + chrgrChgHist.getChrgrClCd());
                  MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
                  mrReqProcStepDetVO.setMrReqNo(mrReqNo);
                  mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepNo);
                  mrReqProcStepDetVO.setMrStepCd(mrStepCd);
                  mrReqProcStepDetVO.setProcStCd(procStCd);
                  mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
                  
                  //System.out.println("입력insertMrReqProcStepDet  :: " + mrReqProcStepDetVO);
                  mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

                  chrgrChgHist.setMrReqNo(mrReqNo);
                  chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());                  
                  
                  mrStepRepository.insertChrgrChgHist(chrgrChgHist); 
             }   
         }
    }
    
    /**
     * Project Engineer지정 처리
     */
    @Override
    public void insertMrTechInvestSetWriter(TechInvestVO techInvestVO) {
    	logger.info(" techInvestVO.getChrgDetNo() : " + techInvestVO.getChrgDetNo());
        if(techInvestVO.getAppLine()!=null) {
            mrStepService.insertMrStep2(techInvestVO.getMrReqNo(), "Z0030", null, techInvestVO.getAppLine());
            mrMailService.addCc("Z0020", "Z02D");
            mrMailService.mailSend(techInvestVO.getMrReqNo());
        }
    }

    /**
     * 투자비산정내용 승인요청 수행팀 팀장 
     */
    @Override
    public int insertMrTechInvestAppReq(TechInvestVO techInvestVO) {
        
    	//투자비 산정 내용 저장
    	insertMrTechInvest(techInvestVO);
    	
    	//초기투자비 멀티 담당자 관련
        mrStepService.insertNextAppEmpMulti(techInvestVO.getMrReqNo(), "Z0121");
        
        // 다음 담당자를 입력받지 않고 이전에 DB에 저장된 데이터를 기준으로 조회하여 활성화 할때 사용함. 
        int mrReqProcStepDetNo = mrStepService.updateMrStep2(techInvestVO.getMrReqNo(), "Z0030", "Z0121", "Z02G", "Z0020", "Z02G");
        
        
        //mrStepService.readProcess(techInvestVO.getMrReqNo(), "Z0030", "Z0121", "Z02G", "Z0020", "Z02G", mrReqProcStepDetNo);
        //hajewook 메일 발송 막음
        //mrMailService.mailSend(techInvestVO.getMrReqNo());
        return mrReqProcStepDetNo;
    }
    /**
     * 투자비산정내용 승인요청 수행팀 팀장  검색 및 비교
     *   //yoo 240207
     */
    public HashMap<String, String> mrTechInvestSearchTeam(String mrReqNo){
    	int nMrReqNo = Integer.parseInt(mrReqNo);
    	ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(nMrReqNo);

    	List<ChrgrChgHist> appInfoList = mrStepRepository.selectInvestSearchTeam(nMrReqNo);
        int count = 0;
    	HashMap<String, String> chrgTeamNoMap = new HashMap<String, String>();
    	logger.info("--- getLoginTeamNo : " + chrgrChgHist.getLoginTeamNo());
    	int nLoginTeamNo = Integer.parseInt(chrgrChgHist.getLoginTeamNo());
    	chrgTeamNoMap.put("LoginTeamNo", chrgrChgHist.getLoginTeamNo());
    	chrgTeamNoMap.put("LoginTeamName", chrgrChgHist.getLoginTeamName());
    	int i = 0;
        	for(ChrgrChgHist appChrgrChgHist : appInfoList) {
        		i++;
        		logger.info("--- TeamNo: " + appChrgrChgHist.getChrgTeamNo());
        		logger.info("--- ORG TeamNo: " + appChrgrChgHist.getThdayTeam());
        		int nChrgTeamNo = Integer.parseInt(appChrgrChgHist.getChrgTeamNo());
        		int nOrgTeamNo = Integer.parseInt(appChrgrChgHist.getThdayTeam());
        		//chrgTeamNoList.add(appChrgrChgHist.getChrgTeamNo());
        		chrgTeamNoMap.put("ChrgTeamNo" + i, appChrgrChgHist.getChrgTeamNo());
        		chrgTeamNoMap.put("OrgTeamNo" + i, appChrgrChgHist.getThdayTeam());
        		chrgTeamNoMap.put("ChrgEmpName" + i, appChrgrChgHist.getChrgEmpName());
        		if(nLoginTeamNo == nChrgTeamNo && nChrgTeamNo == nOrgTeamNo)
        		{
        			chrgTeamNoMap.put("ResultTeamNo", appChrgrChgHist.getChrgTeamNo());
            		chrgTeamNoMap.put("ResultEmpName", appChrgrChgHist.getChrgEmpName());
        			count++;
        		}
        	}           
        if(count > 0)
        	chrgTeamNoMap.put("result", "true");
        else
        	chrgTeamNoMap.put("result", "false");

    	return chrgTeamNoMap;
    }
    
    /**
     * 투자비 산정 승인
     */
    @Override
    public void insertMrTechInvestApp(int mrReqNo) {
    	
        //기술검토자로 할것인지 작성자로 할것인지 고민... 프로세스상 Z02D가 맞음.
        mrStepService.updateMrStep(mrReqNo, "Z0030", "Z0133", "Z02D", "Z0020", "Z02D");

        //yoo 20241024 Z002G 팀장 승인 완료 처리를 위해 조회 후 FST_PROC_TRM_DT NULL  업데이트...
        //List<ChrgrChgHist> chrgrChgHistList = mrStepRepository.selectAppStepEmpDetHist(mrReqNo,"Z0030","Z02G");
        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppIsolationStepEmp(mrReqNo,"Z0030","Z02G");
        if(chrgrChgHist != null)
     	{
	 		logger.info("Z002G chrgrChgHist : " + chrgrChgHist.getFstProcTrmDt());
	 		chrgrChgHist.setFstProcTrmDt(null);
	 		logger.info("Z002G chrgrChgHist : " + chrgrChgHist.getFstProcTrmDt());
	 		mrStepRepository.updateMrReqProcStepDetProcTrmDt(chrgrChgHist);
     	}
     	
        mrMailService.addCc("Z0030 ", "Z02I");
        mrMailService.mailSend(mrReqNo);
        
    }

    /**
     * 투자비 산정 반려
     */
    @Override
    public void insertMrTechInvestReturn(TechInvestVO techInvestVO) {    	
    	
    	for(int i=0; i<techInvestVO.getAppLine().size(); i++){
    		
    		ChrgrChgHist vo = techInvestVO.getAppLine().get(i);
    		System.out.println("getChrgEmpNo()  : "+vo.getChrgEmpNo());
    		System.out.println("size  : "+techInvestVO.getAppLine().size());
    	}
    	
        mrStepService.insertPrevAppEmp(techInvestVO.getMrReqNo(), "Z0030", "Z0141", techInvestVO.getAppLine(), "Z02I");
    }

    /**
     * (201904추가)투자비 산정 반송
     */
    @Override
    public void updateMrTechInvestBack(TechInvestVO techInvestVO) {    	

    	/* 반송
    	 * !! Z030 관련데이터를 삭제처리를 한 후 Z020의 데이타 날짜를 999912315959 로 업데이트한다
    	 * 1. MR_REQ_CHRGR_CHG_HIST
    	 * 2. MR_REQ_PROC_STEP_DET
    	 * 3. MR_REQ_PROC_STEP
    	 * 4. MR_REQ_MST
		*/
    	logger.info(techInvestVO.getMrReqNo() + " updateMrTechInvestBack : " + techInvestVO.getMrReqProcStepNo());
    	//투자비 산정 반송
        techInvestDao.updateMrTechInvestBack(techInvestVO);
    }
}
