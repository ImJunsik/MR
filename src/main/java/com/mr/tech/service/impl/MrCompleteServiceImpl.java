package com.mr.tech.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.repository.MrRvRstDAO;
import com.mr.common.service.MrRvRstService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.repository.TechDao;
import com.mr.tech.service.MrCompleteService;
/**
 * 기술검토항목 서비스구현
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
public class MrCompleteServiceImpl extends BaseService implements MrCompleteService{

	private final Logger logger = Logger.getLogger(this.getClass());
	
    @Autowired
    TechDao techDao;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrRvRstService mrRvRstService;

    @Autowired
    MrStepRepository mrStepRepository;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrMailService mrMailService;
    
    @Autowired
    MrRvRstDAO mrRvRstDAO;

    /**
     * MR 완료처리를 위하여 기술 및 타당성검토내용 조회
     */
    @Override
    public TechReviewVO selectMrComplete(int mrReqNo) {
        TechReviewVO techReviewVO = techDao.selectMrComplete(mrReqNo);
        if(techReviewVO!=null) {
            techReviewVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0030"));
        }
      //yoo240924 전자결재 인터페이스 로직과 동일하게 처리 하기 위해 변경 처리
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mrReqNo);
        try{
        	
               if(appInfoList.size()==1) {
            	   
            	   techReviewVO.setConnIfStatGubun(appInfoList.size() + ":2차 승인: ");
            	   logger.info("결재라인 최종승인자 - 2차 승인자 " + appInfoList.size() + ":2차 승인: ");
                  //최종승인자 승인
	          	  //mrCompleteService.insertMrCompleteApp(techReviewVO);
               } else {
            	   
            	   techReviewVO.setConnIfStatGubun(appInfoList.size() + ":1차 승인: ");
            	   logger.info("결재라인 중간승인자 - 1차 승인자 " + appInfoList.size() + ":1차 승인: ");
                  //중간 승인자 및 최종 승인 요청
        	      //mrCompleteService.insertMrCompleteAgree(techReviewVO.getMrReqNo());
               }

        } catch(Exception e){

        	System.out.println(e);
        }
        return techReviewVO;
    }


    /**
     * 기술 및 타당성검토 내용 수정(타당성 검토 임시저장, 승인요청도 해당로직 후 결재 로직 탐)
     */
    @Override
    public void updateMrTechReview(TechReviewVO techReviewVO) {
        int mrReqProcStepNo;

        //기술 및 타당성검토내용 입력
        insertTechItem(techReviewVO);
        
        System.out.println("mrReqProcStepNo   :: " + techReviewVO.getAppLine());
        //단계상세, 담당자 논리삭제후 생성
        mrReqProcStepNo = mrStepService.insertMrStep(techReviewVO.getMrReqNo(), "Z0030", null, techReviewVO.getAppLine());
        
        
        System.out.println("setMrReqProcStepNo   :: " + mrReqProcStepNo);
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);

        //직무검토자 저장
        System.out.println("insertDetailTechAppEmp   :: " + techReviewVO);
        insertDetailTechAppEmp(techReviewVO,"Z0110");
        
    }
    
    
    /**
     * 기술 및 타당성검토 내용 수정(타당성 검토 임시저장, 승인요청도 해당로직 후 결재 로직 탐)
     * yoo 240910 타당성 검토에서 임시저장 시, 추가 담담자 및 직책과장을 삭제 플래그를 주는 로직이 있어 새로 생성 함
     */
    @Override
    public void updateMrTechReview2(TechReviewVO techReviewVO) {
        int mrReqProcStepNo;

        //기술 및 타당성검토내용 입력
        insertTechItem(techReviewVO);
        
        System.out.println("mrReqProcStepNo   :: " + techReviewVO.getAppLine());
        //단계상세, 담당자 논리삭제후 생성 , 논리 삭제를 하지 않는 메소드 사용 yoo 240910
        mrReqProcStepNo = mrStepService.insertMrStep2(techReviewVO.getMrReqNo(), "Z0030", null, techReviewVO.getAppLine());

        System.out.println("setMrReqProcStepNo   :: " + mrReqProcStepNo);
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);

        //직무검토자 저장
        System.out.println("insertDetailTechAppEmp   :: " + techReviewVO);
        insertDetailTechAppEmp(techReviewVO,"Z0110");
        
        //Z002I가 중복되면 추가 담당자가 표시가 안되는 현상이 있음 yoo 240911
        Map map = new HashMap();
    	map.put("mrReqNo", techReviewVO.getMrReqNo());
    	map.put("mrStepCd", "Z0030");
    	map.put("loginEmpNo", techReviewVO.getLoginEmpNo());
        mrStepRepository.updateDetHistDelDuplication(map);
        
    }
    


    /**
     * 1차 승인요청(타당성검토 승인요청)
     */
    @Override
    public void insertMrCompleteAppReq(TechReviewVO techReviewVO) {
        //저장을 안하고 승인요청을 클릭할 수 있으므로 저장 로직을 먼저 수행한다.
    	//yoo 240911 직책과장이 사라지는 현상 발생하여 주석 처리 하고 새로운 메소드 추가 
        //updateMrTechReview(techReviewVO);
    	//yoo 240911 직책과장이 사라지는 현상 발생하여 새로운 메소드 추가
    	updateMrTechReview2(techReviewVO);
        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0122");
        mrMailService.addCc("Z0030 ", "Z02I");
        Map map = new HashMap();
    	map.put("mrReqNo", techReviewVO.getMrReqNo());
    	map.put("mrStepCd", "Z0030");
    	map.put("loginEmpNo", techReviewVO.getLoginEmpNo());
        mrStepRepository.updateDetHistDelDuplication(map);
        //hajewook 2017.09.08 주석처리
        //mrMailService.mailSend(techReviewVO.getMrReqNo());
    }

    /**
     * 1차 승인
     */
    @Override
    public void insertMrCompleteAgree(int mrReqNo) {
    	logger.info("insertMrCompleteAgree : " + mrReqNo);
    	
        String chrgrClCd = mrStepService.insertNextAppEmp3(mrReqNo, "Z0131");
        logger.info(chrgrClCd);
        if(chrgrClCd.equals("Z02D"))		//yoo240924 1차 승인 시 Z02D로 승인처리가 된 경우, Z02F로 승인 처리를 다시 해 주어야 한다. 이유는 가져오는 쿼리 문에 MIN을 사용하여 잘못 가져오는 경우가 발생 됨
        {
        	chrgrClCd = mrStepService.insertNextAppEmp3(mrReqNo, "Z0131");
        	//logger.info(chrgrClCd);
        }
    
        //hajewook 2015.09.08 주석처리
        //mrMailService.addCc("Z0020 ", "Z02D");
        //mrMailService.mailSend(mrReqNo);
    }

    /**
     * 2차 승인
     */
    @Override
    public void insertMrCompleteApp(TechReviewVO techReviewVO) {
    	
    	logger.info("insertMrCompleteApp : " + techReviewVO.getMrNo());
        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
        //List<ChrgrChgHist> appInfoList = mrStepRepository.selectMrAllAppLine(techReviewVO.getMrReqNo());

        //다음단계 직무검토를 위하여 직무검토 담당자 들을 결재라인에 병합시켜줌
        if(techReviewVO.getJobs()!=null) {
            appLine.addAll(techReviewVO.getJobs());
        }

/* HSW 적용하였다가 막음        
        if(appInfoList.size()==1) {
        	System.out.println("test1");
            //최종승인자 승인
            mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0132");
            mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0040", "Z0110", appLine, "Z02C");
        } else {
         	System.out.println("test2");
           //최종승인자 승인요청
           mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0131");
        }
*/
        //결재라인에 사업수행팀 팀장 기술검토 팀장 추가. 사업수행팀 팀장 제거 2014.08.25 chrgrChgHist.getChrgrClCd().equals("Z02G") ||
        /* for(ChrgrChgHist chrgrChgHist : appInfoList) {
             if(chrgrChgHist.getChrgrClCd().equals("Z02D")) {
                chrgrChgHist.setFstProcTrmDt(mrStepService.getAppTerm());
                appLine.add(chrgrChgHist);
            }
        }*/

        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0132"); //Z0132: 승인완료
        mrStepService.insertMrStepMultiAction(techReviewVO.getMrReqNo(), "Z0040", "Z0110", appLine, "Z02C", -1); //Z0040:직무검토, Z0110:작성중, Z02C: 승인자

        //yoo 최종 승인 시 Z0025에서 Z0030으로 Copy한다
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(techReviewVO.getMrReqNo());
        int n = mrRvRstDAO.copyMrRvRst(mrRvRstVO);
        logger.info("insertMrCompleteAgree - Insert Row Count: " + n);
        

        mrMailService.addCc("Z0020 ", "Z02D");
        mrMailService.addCc("Z0030 ", "Z02C");
        mrMailService.addCc("Z0030 ", "Z02F");
        mrMailService.mailSendMulti(techReviewVO.getMrReqNo());
    }

    
    /**
     * 타당성검토반려 처리
     */
    @Override
    public void insertMrTechReturn(TechReviewVO techReviewVO) {
    	logger.info("insertMrTechReturn : " + techReviewVO.getMrNo());
    	int mrReqProcStepNo = 0;   	

        //기존로직 주석처리: mr 진행단계 업데이트. Z0020:기술 및 타당성 검토, Z0141:반려, Z02D:기술검토자
        //mrReqProcStepNo = mrStepService.insertPrevAppEmp(techReviewVO.getMrReqNo(),"Z0020", "Z0141", null, "Z02D");        
  	
    	//허세욱: Z0030 의 작성중(Z0133) as-is: 반려시 초기투자비 산정 작성중 -> to-be: 반려시 초기투자비 산정 팀장확인완료로 변경   	
    	mrReqProcStepNo = mrStepService.insertPrevAppEmp(techReviewVO.getMrReqNo(),"Z0030", "Z0133", null, "Z02D");
       
    	//20180306 wj: 초기투자비 산정 반려시 Project engineer 삭제처리됨 삭제여부 수정로직 추가
    	techDao.updateProjectEngineer(techReviewVO);    	
    	
        techReviewVO.setMrReqProcStepNo(mrReqProcStepNo);
        
        //wj기존로직 주석처리
        // 직무검토자 생성 로직 주석처리
        //insertDetailTechAppEmp(techReviewVO, "Z0141");
        
        /*메일발송*/
        //허세욱: 반려시 기존 Z02I, Z02C 에 메일을 보냈으나 변경 후 Z02D 에게만 메일 보냄        
        mrMailService.addCc("Z0030 ", "Z02D");

        mrMailService.mailSend(techReviewVO.getMrReqNo());
    }

    private void insertTechItem(TechReviewVO techReviewVO){
        //Tech마스터 신규 저장
        techDao.updateMrTechReview(techReviewVO);

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

        //세부항목 업데이트
        if(techReviewVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(techReviewVO.getMrReqNo());
            /*mrRvRstService.updateTechMrRvRstDelYn(updateDelYnRvRst);*/

            for(MrRvRstVO mrRvRstVO : techReviewVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(techReviewVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.updateMrRvRst(mrRvRstVO);
            }
        }
    }

    private void insertDetailTechAppEmp(TechReviewVO techReviewVO, String procStCd){

        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(techReviewVO.getMrReqNo());
        chrgrChgHist.setMrStepCd("Z0020");
        
        //특정 직무검토자 논리삭제
        mrStepService.updateTechEmpDelYn(chrgrChgHist);

        //직무검토자 입력 서비스
        if(techReviewVO.getJobs()!=null) {
            mrStepService.insertTechEmp(techReviewVO.getMrReqNo(), techReviewVO.getMrReqProcStepNo(), "Z0020", procStCd, techReviewVO.getJobs());
        }

    }


}
