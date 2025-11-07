package com.mr.tech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.service.BaseService;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.service.MrRvRstService;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.mrrq.service.MRrqService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrMailVO;
import com.mr.step.domain.MrReqProcStepDetVO;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.repository.MrMailDao;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.repository.TechDao;
import com.mr.tech.service.TechService;
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
public class TechServiceImpl extends BaseService implements TechService{


	@Autowired
    MrMailDao mrMailDao;

    @Autowired
    TechDao techDao;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    MRrqService mRrqService;

    @Autowired
    MrRvRstService mrRvRstService;

    @Autowired
    MrAtchFileService mrAtchFileService;
    
    @Autowired
    MrStepRepository mrStepRepository;
    
    
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 기술 및 타당성검토 조회
     */
    @Override
    public TechReviewVO selectMrTechReview(int mrReqNo) {
        TechReviewVO techReviewVO = techDao.selectMrTechReview(mrReqNo);
        if(techReviewVO!=null) {
            techReviewVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0020"));
        }

        return techReviewVO;
    }

    /**
     * 기술 및 타당성검토 내용 저장
     */
    @Override
    public void insertMrTechReview(TechReviewVO techReviewVO) {
        int mrReqProcStepNo;

        //기술 및 타당성검토 입력내용 저장
        insertTechItem(techReviewVO);

        //신규결재라인등록        
        mrReqProcStepNo = mrStepService.insertMrStep(techReviewVO.getMrReqNo(), "Z0020", null, techReviewVO.getAppLine());
        

        //기존 요청서 기술및타당성검토자 및 수행팀 정보 변경
        changeMrReqAppLine(techReviewVO.getMrReqNo(), "Z0020", null, techReviewVO.getAppLine());

        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);

        //직무검토자 저장
        insertDetailTechAppEmp(techReviewVO, "Z0110");

        mrStepService.insertFile(techReviewVO.getMrReqNo(), "Z0020", "Z02D", "0201", techReviewVO.getMrAtchFiles());
    }

	/**
     * 기술 및 타당성검토 내용 수정
     * techReviewRegister.jsp -> TechController.java -> TechInvestServiceImpl.java
     */
    @Override
    public void updateMrTechReview(TechReviewVO techReviewVO) {
        int mrReqProcStepNo;
        
        //기술 및 타당성검토 입력내용 수정
        updateTechItem(techReviewVO);

        /*참고: Z02G1
         * 단계상세에서 G1 계정을 한번 입력하고
         * 기존요청서 기술및 타당성 검토자 에서 한번더 입력한다
         * */

        //단계상세, 담당자 논리삭제후 생성 
        mrReqProcStepNo = mrStepService.insertMrStep(techReviewVO.getMrReqNo(), "Z0020", null, techReviewVO.getAppLine());        

        //기존 요청서 기술및타당성검토자 및 수행팀 정보 변경        
        changeMrReqAppLine(techReviewVO.getMrReqNo(), "Z0020", null, techReviewVO.getAppLine());
        
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);        
        
        insertDetailTechAppEmp(techReviewVO,"Z0110");        
        
        mrStepService.insertFile(techReviewVO.getMrReqNo(), "Z0020", "Z02D", "0201", techReviewVO.getMrAtchFiles());        
    }

    
    /**
     * 기술 및 타당성검토 내용 저장
     */
    @Override
    public void insertMrTechReview(TechReviewVO techReviewVO,String mrStepCd) {
        int mrReqProcStepNo;

        //기술 및 타당성검토 입력내용 저장
        insertTechItem(techReviewVO);

        //신규결재라인등록        
        mrReqProcStepNo = mrStepService.insertMrStep(techReviewVO.getMrReqNo(), mrStepCd, null, techReviewVO.getAppLine());
        

        //기존 요청서 기술및타당성검토자 및 수행팀 정보 변경
        changeMrReqAppLine(techReviewVO.getMrReqNo(), mrStepCd, null, techReviewVO.getAppLine());

        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);

        //직무검토자 저장
        insertDetailTechAppEmp(techReviewVO, "Z0110");

        mrStepService.insertFile(techReviewVO.getMrReqNo(), mrStepCd, "Z02D", "0201", techReviewVO.getMrAtchFiles());
    }

	/**
     * 기술 및 타당성검토 내용 수정
     * techReviewRegister.jsp -> TechController.java -> TechInvestServiceImpl.java
     */
    @Override
    public void updateMrTechReview(TechReviewVO techReviewVO,String mrStepCd) {
        int mrReqProcStepNo;
        
        //기술 및 타당성검토 입력내용 수정
        updateTechItem(techReviewVO);

        /*참고: Z02G1
         * 단계상세에서 G1 계정을 한번 입력하고
         * 기존요청서 기술및 타당성 검토자 에서 한번더 입력한다
         * */

        //단계상세, 담당자 논리삭제후 생성 
        mrReqProcStepNo = mrStepService.insertMrStep(techReviewVO.getMrReqNo(), mrStepCd, null, techReviewVO.getAppLine());        

        //기존 요청서 기술및타당성검토자 및 수행팀 정보 변경        
        changeMrReqAppLine(techReviewVO.getMrReqNo(), mrStepCd, null, techReviewVO.getAppLine());
        
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);        
        
        insertDetailTechAppEmp(techReviewVO,"Z0110");        
        
        mrStepService.insertFile(techReviewVO.getMrReqNo(), mrStepCd, "Z02D", "0201", techReviewVO.getMrAtchFiles());        
    }
    
    /**
     * 승인 요청
     */
    @Override
    public void insertMrTechAppReq(TechReviewVO techReviewVO) {

        /**
         * 기술 및 타당성검토내용을 저장하지 않고 바로 승인요청 하는 경우가 있으므로
         * 시퀀스 번호로 판단하여 신규, 수정 로직 수행후 승인요청
         *
         */
    	    	
        if(techReviewVO.getMrTechRvNo()<1){
            insertMrTechReview(techReviewVO);
        } else {
            updateMrTechReview(techReviewVO);
        }

        // 승인 요청하면서 승인 처리까지 한번에 하기 때문에 이 문장이 필요한지 의문??????????
        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0121");

        // 20150820 기술검토 승인 프로세스 변경 시작
        // 기술검토 요청 시 승인 로직을 함께 적용 

        //mrMailService.mailSend(techReviewVO.getMrReqNo());
        
        // 기술항목 승인 처리 및 초기투자비 산정 요청 
        //insertMrTechApp(techReviewVO);
     // 기술항목 승인 처리 및 초기투자비 산정 요청 yoo241008 H~ 각 담당자 저장 
        insertMrTechApp2(techReviewVO);
        //20150820 기술검토 승인 프로세스 변경 끝  
        
    }
    
    /**
     * 승인 요청
     */
    @Override
    public void insertMrTechAppReq2(TechReviewVO techReviewVO) {

        /**
         * 기술 및 타당성검토내용을 저장하지 않고 바로 승인요청 하는 경우가 있으므로
         * 시퀀스 번호로 판단하여 신규, 수정 로직 수행후 승인요청
         *
         */
    	    	
        if(techReviewVO.getMrTechRvNo()<1){
            insertMrTechReview(techReviewVO);
        } else {
            updateMrTechReview(techReviewVO);
        }
     
        // Z02D (기술검토자) Insert
        mrStepService.insertNextAppEmp2(techReviewVO.getMrReqNo(), "Z0121");

        // 20150820 기술검토 승인 프로세스 변경 시작
        // 기술검토 요청 시 승인 로직을 함께 적용 

        //mrMailService.mailSend(techReviewVO.getMrReqNo());
        
        // 기술항목 승인 처리 및 초기투자비 산정 요청 
        insertMrTechApp2(techReviewVO);

        
    }
    
    /**
     * 승인 요청
     */
    @Override
    public void insertMrTechAppReq(TechReviewVO techReviewVO,String mrStepCd, String procStCd, String clcd) {
    	logger.info("@@@@ insertMrTechAppReq - " + techReviewVO.getMrReqNo() + techReviewVO.getConnCode() + techReviewVO.getConnIfName() + techReviewVO.getConnIfStatGubun());
    	//yoo 240730 test 테스트 - OK
    	//MrMailVO mrMailVO = mrMailDao.selectMrMailInfo(techReviewVO.getMrReqNo());
    	List<ChrgrChgHist> appInfoList = null;
    	if(techReviewVO.getAppLine() == null)
    	{
    		appInfoList = mrStepRepository.selectMrAllAppLine(techReviewVO.getMrReqNo());
    		techReviewVO.setAppLine(appInfoList);
    	}
        /**
         * 기술 및 타당성검토내용을 저장하지 않고 바로 승인요청 하는 경우가 있으므로
         * 시퀀스 번호로 판단하여 신규, 수정 로직 수행후 승인요청
         *
         */
    	//yoo 240812 techReviewVO.getMrTechRvNo() 값이 나오지 않아 데이타 중복 발생 되는 현상 때문은 추가 함
    	MrRvRstVO selectInfo = new MrRvRstVO();
        selectInfo.setMrReqNo(techReviewVO.getMrReqNo());
        selectInfo.setItemCd("TECH");
        
        int nCount = mrRvRstService.selectRvRstCount(selectInfo);
        if(nCount < 1){
        //if(mrRvRstVO.getMrTechRvNo()<1){
            insertMrTechReview(techReviewVO, mrStepCd);
        } else {
            updateMrTechReview(techReviewVO, mrStepCd);
        }

        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
      //다음단계 직무검토를 위하여 직무검토 담당자 들을 결재라인에 병합시켜줌
        if(techReviewVO.getJobs()!=null) {
            appLine.addAll(techReviewVO.getJobs());
        }
        // 승인 요청하면서 승인 처리까지 한번에 하기 때문에 이 문장이 필요한지 의문??????????
        //mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0121");
        //mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), mrStepCd, procStCd, appLine, "Z02C"); //Z0025:직무검토, Z0110:작성중, Z02C: 승인자 yoo240731

        // 20150820 기술검토 승인 프로세스 변경 시작
        // 기술검토 요청 시 승인 로직을 함께 적용 

        //mrMailService.mailSend(techReviewVO.getMrReqNo());
        
        // 기술항목 승인 처리 및 초기투자비 산정 요청 
        insertMrTechApp(techReviewVO,mrStepCd);
        //List<ChrgrChgHist> appline = insertMrTechApp(techReviewVO);
        
     // 승인 요청하면서 승인 처리까지 한번에 하기 때문에 이 문장이 필요한지 의문??????????
        //mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0121");
        mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), mrStepCd, procStCd, appLine, clcd, -1); //Z0025:직무검토, Z0110:작성중, Z02C: 승인자 yoo240731
               
        //20150820 기술검토 승인 프로세스 변경 끝  
        
    }
    
    /**
     * 승인
     */
    @Override
    public void insertMrTechApp(TechReviewVO techReviewVO) {

        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();

        if(techReviewVO.getAppLine()!=null){
            for(ChrgrChgHist app : techReviewVO.getAppLine()) {
                if(app.getChrgrClCd().contains("Z02G")) {
                    appLine.add(app);
                }
            }
        }

        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0133");
        mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0025", "Z0110", appLine, "Z02G",-1);
        //mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0030", "Z0110", appLine, "Z02G",-1);

        //2017.11.27 한명의 직원에게 승인 메일 연속으로 발송하는 현상 발생(MR_REQ:2310, MR_NO:20170016, 당사자:193129/권기오)
        mrMailService.addCc("Z0020", "Z02D");
        mrMailService.mailSendMulti(techReviewVO.getMrReqNo());
        mrMailService.mailSend(techReviewVO.getMrReqNo());
    }
    
    /**
     * 기술검토항목 단계에서 기술검토자가 완료 시 로직 yoo240905
     */
    @Override
    public void insertMrTechApp2(TechReviewVO techReviewVO) {

        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();

        if(techReviewVO.getAppLine()!=null){
            for(ChrgrChgHist app : techReviewVO.getAppLine()) {
                if(app.getChrgrClCd().contains("Z02G")) {
                    appLine.add(app);
                }
            }
        }

        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0133");
        //mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0030", "Z0110", appLine, "Z02G");
        int mrStepProcNo = mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0025", "Z0110", appLine, "Z02G", -1);		//yoo 240821 직무검토(1)의 추가로 Z0030에서 Z0025로 변경
        List<ChrgrChgHist> jobs = new ArrayList<ChrgrChgHist>();
        if(techReviewVO.getJobs()!=null){
            for(ChrgrChgHist app : techReviewVO.getJobs()) {
                if(app.getChrgrClCd().contains("Z02H")) {
                	jobs.add(app);
                }
            }
        }
        //Z0025 각 직무검토 담당자 생성
        mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0025", "Z0110", jobs, "Z02H", mrStepProcNo);		//yoo 240821 직무검토(1)의 추가로 Z0030에서 Z0025로 변경
        //2017.11.27 한명의 직원에게 승인 메일 연속으로 발송하는 현상 발생(MR_REQ:2310, MR_NO:20170016, 당사자:193129/권기오)
        mrMailService.addCc("Z0020", "Z02D");
        mrMailService.mailSendMulti(techReviewVO.getMrReqNo());
        mrMailService.mailSend(techReviewVO.getMrReqNo());
    }
    
    /**
     * 승인
     */
    @Override
    public void insertMrTechApp(TechReviewVO techReviewVO, String mrStepCd) {

    	
        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
        //yoo 240823 수정
        if(techReviewVO.getAppLine()==null){
        	
            for(ChrgrChgHist app : techReviewVO.getJobs()) {
            	if(app.getChrgrClCd() != null)
	                if(app.getChrgrClCd().contains("Z02G")) {
	                    appLine.add(app);
	                }
            }
        }else{
        	for(ChrgrChgHist app : techReviewVO.getAppLine()) {
        		if(app.getChrgrClCd() != null)
	                if(app.getChrgrClCd().contains("Z02G")) {
	                    appLine.add(app);
	                }
            }
        }

        //mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0133");
        
        //return appLine;
        
        MrMailVO mrMailVO = mrMailDao.selectMrMailInfo(techReviewVO.getMrReqNo());
        
        // yoo  240730 아래 코드가 문제임
        mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), mrStepCd, "Z0110", appLine, "Z02G", -1);	//yoo 240723 직무검토1 Z0025로 생성
        
        //mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0025", "Z0110", appLine, "Z02G");	//기존 코드
        //mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0030", "Z0110", appLine, "Z02G");	//기존 코드

        
        //2017.11.27 한명의 직원에게 승인 메일 연속으로 발송하는 현상 발생(MR_REQ:2310, MR_NO:20170016, 당사자:193129/권기오)
        mrMailService.addCc("Z0020", "Z02D");
        mrMailService.mailSendMulti(techReviewVO.getMrReqNo(), mrMailVO);
        mrMailService.mailSend(techReviewVO.getMrReqNo(), mrMailVO);
        
    }
    
    
  

    /**
     * 반려
     */
    @Override
    public void insertMrTechReturn(TechReviewVO techReviewVO) {
        int mrReqProcStepNo = 0;
        mrReqProcStepNo = mrStepService.insertPrevAppEmp(techReviewVO.getMrReqNo(), null, "Z0141", techReviewVO.getAppLine(), "Z02A");
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);
        insertDetailTechAppEmp(techReviewVO, "Z0141");
        mrMailService.mailSend(techReviewVO.getMrReqNo());
    }

    private void insertTechItem(TechReviewVO techReviewVO){
        //Tech마스터 신규 저장
    	    	
        techDao.insertMrTechReview(techReviewVO);

        //요청서 타이틀 변경
        MRrqRegisterVO mRrqRegisterVO = new MRrqRegisterVO();
        mRrqRegisterVO.setMrReqTitle(techReviewVO.getMrReqTitle());
        mRrqRegisterVO.setMrReqNo(techReviewVO.getMrReqNo());


        mRrqService.updateMrReqTitle(mRrqRegisterVO);

        //상세항목 업데이트
        if(techReviewVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(techReviewVO.getMrReqNo());
            updateDelYnRvRst.setItemCd("TECH");
            mrRvRstService.updateMrRvRstDelYnItemCd(updateDelYnRvRst);

            for(MrRvRstVO mrRvRstVO : techReviewVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(techReviewVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.insertMrRvRst(mrRvRstVO);
            }
        }
    }


    private void updateTechItem(TechReviewVO techReviewVO){
        //Tech마스터 업데이트
        techDao.updateMrTechReview(techReviewVO);

        //요청서 타이틀 변경
        MRrqRegisterVO mRrqRegisterVO = new MRrqRegisterVO();
        mRrqRegisterVO.setMrReqTitle(techReviewVO.getMrReqTitle());
        mRrqRegisterVO.setMrReqNo(techReviewVO.getMrReqNo());
        mRrqService.updateMrReqTitle(mRrqRegisterVO);

        //세부항목 업데이트
        if(techReviewVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(techReviewVO.getMrReqNo());
            /*mrRvRstService.updateTechMrRvRstDelYn(updateDelYnRvRst);*/

            for(MrRvRstVO mrRvRstVO : techReviewVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(techReviewVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                int n = mrRvRstService.updateMrRvRst(mrRvRstVO);
                System.out.println("!!!! mrRvRstService.updateMrRvRst : " + n);
                if(n == 0)
                {
                	mrRvRstService.insertMrRvRst(mrRvRstVO);		//yoo 22.02.18 Update가 안되면 Insert 시킨다
                }
            }
        }
    }

    private void insertDetailTechAppEmp(TechReviewVO techReviewVO, String procStCd){

        if(techReviewVO.getJobs()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getJobs());
        }
        /**
         * 2014.07.17 1관점당 1담당자로 변경됨.
        //기술
        if(techReviewVO.getTechs()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getTechs());
        }
        //검사
        if(techReviewVO.getChecks()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getChecks());
        }

        //운전
        if(techReviewVO.getDrives()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getDrives());
        }

        //안전
        if(techReviewVO.getSafetys()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getSafetys());
        }

        //회계
        if(techReviewVO.getAccounts()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getAccounts());
        }

        //에너지
        if(techReviewVO.getEnergys()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getEnergys());
        }

        //기타
        if(techReviewVO.getEtcs()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getEtcs());
        }
         */
    }

    /**
     * 기술검토 및 타당성 단계 담당자 및 수행팀 팀장 직책 과장 변경 시 요청서 정보 변경 함.
     * @param mrReqNo
     * @param string
     * @param object
     * @param appLine
     */
    private void changeMrReqAppLine(int mrReqNo, String string, Object object,List<ChrgrChgHist> appLine) {
        
    	//changeMrReqAppLine(techReviewVO.getMrReqNo(), "Z0020", null, techReviewVO.getAppLine());
    	
    	//요청서 직무검토자,위험성/PORC 담당자 변경    	
    	List<ChrgrChgHist> actChrgrChgHistList = new ArrayList<ChrgrChgHist>();    	
    	
    	
        for (ChrgrChgHist chrgrChgHist: appLine) {        	
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            //기술및 타당성검토 담당자 변경            
            if(chrgrChgHist.getChrgrClCd().contains("Z02D")){
	        	List<String> chrgrClCds = new ArrayList<String>();	        	
	        	chrgrClCds.add("Z02D");
	        	List<String> mrStepCds = new ArrayList<String>();
	        	mrStepCds.add("Z0010");
	        	mrStepCds.add("Z00R0");	        	
	        	
	            List<ChrgrChgHist> mrReqTechEmpInfoList = mrStepService.getMrReqTechEmpInfo(mrReqNo,mrStepCds,chrgrClCds);
	            for(ChrgrChgHist mrReqTechEmpInfo : mrReqTechEmpInfoList){
	            	logger.info(mrReqTechEmpInfo.getMrReqProcStepDetNo() + " mrReqActEmpInfoList : " + mrReqTechEmpInfo.getChrgTeamNo() + " : " + mrReqTechEmpInfo.getChrgEmpNo());
	            	mrStepService.updateReqTechEmpDelYn(mrReqTechEmpInfo);
		            mrReqTechEmpInfo.setChrgEmpName(chrgrChgHist.getChrgEmpName());
		            mrReqTechEmpInfo.setChrgEmpNo(chrgrChgHist.getChrgEmpNo());
		            mrReqTechEmpInfo.setThdayTeam(chrgrChgHist.getThdayTeam());
		            mrReqTechEmpInfo.setChrgTeamNo(chrgrChgHist.getChrgTeamNo());
		            mrReqTechEmpInfo.setProcStCd(null);
		            mrReqTechEmpInfo.setAprvChgYn(0);
		            mrReqTechEmpInfo.setSugg(" 기술 및 타당성검토 담당자변경");		            
		            mrStepService.insertChrgrChgHist(mrReqTechEmpInfo);		            
	            }
            }
            //변경된 수행팀정보 
            if(chrgrChgHist.getChrgrClCd().contains("Z02G")){
            	actChrgrChgHistList.add(chrgrChgHist);
            }
        }
        
        //기존요청서 수행팀 정보 삭제 
    	List<String> chrgrClCds = new ArrayList<String>();
    	chrgrClCds.add("Z02G");
    	chrgrClCds.add("Z02G1");
    	List<String> mrStepCds = new ArrayList<String>();
    	mrStepCds.add("Z0010");
    	MrReqProcStepVO actProcInfo = mrStepService.selectOneMrReqStep(mrReqNo, "Z0010");
    	
    	List<ChrgrChgHist> mrReqActEmpInfoList = mrStepService.getMrReqTechEmpInfo(mrReqNo,mrStepCds,chrgrClCds);    	
    	
        for(ChrgrChgHist delActEmpInfo : mrReqActEmpInfoList){     
        	logger.info(delActEmpInfo.getMrReqProcStepDetNo() + " mrReqActEmpInfoList : "  + delActEmpInfo.getMrReqNo() + ":" + delActEmpInfo.getMrReqNo() + ":" + delActEmpInfo.getChrgTeamNo() + " : " + delActEmpInfo.getChrgEmpNo());
        	mrStepService.updatePorcStepDetNoDelYn(delActEmpInfo);
        	mrStepService.updateAppLineEffEnd(delActEmpInfo );        	
        }
        
        //신규 수행팀 정보 등록
        if(actChrgrChgHistList.size()>0){
        	
        	for(ChrgrChgHist actChrgrChgHist : actChrgrChgHistList){        		
        		MrReqProcStepDetVO actMrReqProcStepDet =  new MrReqProcStepDetVO();
        		actMrReqProcStepDet.setMrStepCd("Z0010");
        		actMrReqProcStepDet.setMrReqNo(mrReqNo);
        		actMrReqProcStepDet.setMrReqProcStepNo(actProcInfo.getMrReqProcStepNo());
        		actMrReqProcStepDet.setDelYn(0);
        		
        		mrStepService.insertMrReqProcStepDet(actMrReqProcStepDet);
        		actChrgrChgHist.setMrReqProcStepDetNo(actMrReqProcStepDet.getMrReqProcStepDetNo());
        		actChrgrChgHist.setProcStCd(null);
        		actChrgrChgHist.setAprvChgYn(0);
        		actChrgrChgHist.setSugg(" 기술 및 타당성검토 담당자변경");
        		mrStepService.insertChrgrChgHist(actChrgrChgHist);
        	}
        }        
	}
    
    /**
     * 사업취소
     */
    @Override
    public void mrRqRegisterCancel(int mrReqNo) {
        mrStepService.insertNextAppEmp(mrReqNo, "Z0143");        
        mrMailService.allMrEmpMailSend(mrReqNo, null, "Z0143");
    }
}
