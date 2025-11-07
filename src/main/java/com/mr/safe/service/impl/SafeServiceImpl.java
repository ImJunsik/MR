package com.mr.safe.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.domain.MrAtchFile;
import com.common.file.repository.MrAtchFileRepository;
import com.common.file.service.MrAtchFileService;
import com.mr.jobs.service.JobsReviewService;
import com.mr.safe.domain.SafeCheckVO;
import com.mr.safe.domain.SafeChrgrChgHistVO;
import com.mr.safe.domain.SafeProcStepDetVO;
import com.mr.safe.repository.SafeDAO;
import com.mr.safe.service.SafeService;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import org.apache.log4j.Logger;

@Service
public class SafeServiceImpl extends BaseService implements SafeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    SafeDAO safeDAO;

    @Autowired
    MrStepService mrStepService;
    
    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrAtchFileRepository mrAtchFileRepository;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    JobsReviewService jobsReviewService;

    @Override
    public SafeCheckVO selectSafeCheck(int mrReqNo) {
        List<MrAtchFile> mrAtchFiles = null;
        SafeCheckVO safeCheckVO = null;
        safeCheckVO = safeDAO.selectSafeCheck(mrReqNo);	// yoo 여기서 'Z0070'이 아닌 'Z0080'을 가져오는 현상 발생
        safeCheckVO.setJobsSkipCheck(jobsReviewService.skipJobReviews(mrReqNo, false));

        //가동전 안전점검 첨부파일 조회
        if(safeCheckVO!=null) mrAtchFiles = mrAtchFileService.getMrAtchFileList(mrReqNo, safeCheckVO.getMrStepCd());		//Yoo
        if(mrAtchFiles!=null) safeCheckVO.setMrAtchFiles(mrAtchFiles);

        return safeCheckVO;
    }
    
    /*@Override
    public SafeCheckVO selectSafeCheck(int mrReqNo, String mrStepCd) {
        List<MrAtchFile> mrAtchFiles = null;
        SafeCheckVO safeCheckVO = null;
        safeCheckVO = safeDAO.selectSafeCheck(mrReqNo);	// yoo 여기서 'Z0070'이 아닌 'Z0080'을 가져오는 현상 발생하므로 selectSafeCheck(int mrReqNo, String mrStepCd) 메소드 추가 한 거임.
        safeCheckVO.setJobsSkipCheck(jobsReviewService.skipJobReviews(mrReqNo, false));

        //가동전 안전점검 첨부파일 조회
        if(safeCheckVO!=null) mrAtchFiles = mrAtchFileService.getMrAtchFileList(mrReqNo, mrStepCd);		//Yoo
        if(mrAtchFiles!=null) safeCheckVO.setMrAtchFiles(mrAtchFiles);

        return safeCheckVO;
    }*/
    @Override
    public SafeCheckVO selectSafeCheck(int mrReqNo, String mrStepCd) {
        List<MrAtchFile> mrAtchFiles = null;
        SafeCheckVO safeCheckVO = null;
        
        // ★ VO 생성 및 파라미터 설정
        SafeCheckVO paramVO = new SafeCheckVO();
        paramVO.setMrReqNo(mrReqNo);
        paramVO.setMrStepCd(mrStepCd);  // ← mrStepCd 설정 (중요!)
        
        // ★ VO를 파라미터로 전달
        safeCheckVO = safeDAO.selectSafeCheck(paramVO);
        
        // ★ null 체크 추가
        if(safeCheckVO == null) {
            logger.warn("selectSafeCheck result is null. mrReqNo: " + mrReqNo + ", mrStepCd: " + mrStepCd);
            return null;
        }
        
        logger.warn("selectSafeCheck result is null. mrReqNo: " + mrReqNo + ", mrStepCd: " + mrStepCd);
        
        safeCheckVO.setJobsSkipCheck(jobsReviewService.skipJobReviews(mrReqNo, false));
        
        // 가동전 안전점검 첨부파일 조회
        if(safeCheckVO != null) {
            mrAtchFiles = mrAtchFileService.getMrAtchFileList(mrReqNo, mrStepCd);
            if(mrAtchFiles != null) {
                safeCheckVO.setMrAtchFiles(mrAtchFiles);
            }
        }
        
        return safeCheckVO;
    }

    @Override
    public SafeChrgrChgHistVO countSafeCheck(SafeCheckVO safeCheckVO) {
        return safeDAO.countSafeCheck(safeCheckVO);
    }

    @Override
    public void insertSafeCheck(SafeCheckVO safeCheckVO, boolean flag) {

        // MR요청서 마스터 상태값 업데이트
        safeCheckVO.setMrStepCd("Z0070");
        safeCheckVO.setProcStCd("Z0110");
        safeDAO.updateSafeCheck(safeCheckVO);
        
        if(flag){ // 가동전 안전검검 저장 일경우에만 담당자 저장     
	        for (int i=0;i<safeCheckVO.getSafeChrgr().size();i++) {
	
	            // MR진행상세 등록
	            SafeProcStepDetVO safeProcStepDetVO = new SafeProcStepDetVO();
	            safeProcStepDetVO.setMrReqNo(safeCheckVO.getMrReqNo());
	            safeProcStepDetVO.setMrStepCd(safeCheckVO.getMrStepCd());
	            safeProcStepDetVO.setProcStCd(safeCheckVO.getProcStCd());
	            safeProcStepDetVO.setFstProcTrmDt(get99991231(safeCheckVO.getSafeChrgr().get(i).getEndDate()));
	            safeDAO.insertSafeProcStepDet(safeProcStepDetVO);
	
	            // MR진행담당자 등록
	            SafeChrgrChgHistVO safeChrgrChgHistVO = new SafeChrgrChgHistVO();
	            safeChrgrChgHistVO.setMrReqProcStepDetNo(safeProcStepDetVO.getMrReqProcStepDetNo());
	            safeChrgrChgHistVO.setChrgrClCd(safeCheckVO.getSafeChrgr().get(i).getChrgrClCd());
	            safeChrgrChgHistVO.setMrReqNo(safeCheckVO.getMrReqNo());
	            safeChrgrChgHistVO.setProcStCd(safeCheckVO.getProcStCd());
	            safeChrgrChgHistVO.setChrgTeamNo(safeCheckVO.getSafeChrgr().get(i).getChrgTeamNo());
	            safeChrgrChgHistVO.setChrgEmpNo(safeCheckVO.getSafeChrgr().get(i).getChrgEmpNo());
	            safeChrgrChgHistVO.setThdayTeam(safeCheckVO.getSafeChrgr().get(i).getThdayTeam());
	            safeChrgrChgHistVO.setThdayPos(safeCheckVO.getSafeChrgr().get(i).getThdayPos());
	            safeDAO.insertSafeChrgrChgHist(safeChrgrChgHistVO);
	        }
        } else{
        	// MR수행에서 완료 했을 때 가동전 안전점검 진행중으로 상태 변경
        	mrStepService.insertMrStep(safeCheckVO.getMrReqNo(), "Z0070", "Z0110", "Z02A");
        }
    }

    @Override
    public void updateSafeCheck(SafeCheckVO safeCheckVO, boolean flag) {
        // MR요청서 마스터 상태값 업데이트
        safeCheckVO.setMrStepCd("Z0070");
        safeCheckVO.setProcStCd("Z0110");
        safeDAO.updateSafeCheck(safeCheckVO);

        if(flag){ // 가동전 안전검검 저장 일경우에만 담당자 저장
	        for (int i=0;i<safeCheckVO.getSafeChrgr().size();i++) {
	
	            // MR진행담당자 수정
	            SafeChrgrChgHistVO safeChrgrChgHistVO = new SafeChrgrChgHistVO();
	            safeChrgrChgHistVO.setChrgrClCd(safeCheckVO.getSafeChrgr().get(i).getChrgrClCd());
	            safeChrgrChgHistVO.setMrReqNo(safeCheckVO.getMrReqNo());
	            safeChrgrChgHistVO.setMrStepCd(safeCheckVO.getMrStepCd());
	            safeChrgrChgHistVO.setProcStCd(safeCheckVO.getProcStCd());
	            safeChrgrChgHistVO.setChrgTeamNo(safeCheckVO.getSafeChrgr().get(i).getChrgTeamNo());
	            safeChrgrChgHistVO.setChrgEmpNo(safeCheckVO.getSafeChrgr().get(i).getChrgEmpNo());
	            safeChrgrChgHistVO.setThdayTeam(safeCheckVO.getSafeChrgr().get(i).getThdayTeam());
	            safeChrgrChgHistVO.setThdayPos(safeCheckVO.getSafeChrgr().get(i).getThdayPos());
	            safeChrgrChgHistVO.setFstProcTrmDt(get99991231(safeCheckVO.getSafeChrgr().get(i).getEndDate()));
	            int result = safeDAO.updateSafeChrgrChgHist(safeChrgrChgHistVO);
	            if(result == 0)	// 업데이트 건이 0이면 다른 조건으로 업데이트 처리
	            	result = safeDAO.updateSafeChrgrChgHist2(safeChrgrChgHistVO);
	            safeDAO.updateSafeProcStepDet(safeChrgrChgHistVO);
	        }
        }
    }
    
    @Override
    public void updateCheckZ02d(SafeCheckVO safeCheckVO) {
    	//safeDAO.updateCheckZ02d(safeCheckVO);					//wj 작성: 담당자 Z02I로 업데이트 (가동전 안전점검 기술검토확인)    	
    	
        safeCheckVO.setMrStepCd("Z0060");
        safeCheckVO.setProcStCd("Z0110");
        safeDAO.updateSafeChrgrEffEnd(safeCheckVO);
        //(int mrReqNo, String currentMrStepCd, String nextProcStCd, String nextChrgrClCd, String getMrStepCd, String getChrgrClCd)
        List<MrReqProcStepVO> check = mrStepService.checkInsertMrStep(safeCheckVO.getMrReqNo(), "Z0060","Z02I");		
        boolean bCheck = false;
        for(MrReqProcStepVO vo : check)
        {
        	bCheck = true;
        	break;
        }
        if(!bCheck)
        	mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0060", "Z0110", "Z02I", "Z0030", "Z02I");

        // 메일송신 : MR요청번호, 본문내용:설계팀 MR수행완료
        // 수신자   : MR요청자
        mrMailService.addCc("Z0030 ", "Z02I");
        mrMailService.mailSend(safeCheckVO.getMrReqNo());
    }   
    
    @Override
    public void safeCheckConf(SafeCheckVO safeCheckVO) {
    	   
    	// MR수행완료 (추가: 2021-11-05)
    	//if(StringUtils.isNotEmpty(safeCheckVO.getMrPrfmDt())){
    	safeDAO.updateCheckMrPrfmDt(safeCheckVO);
    	//}
    	
        // STEP 단계 업데이트
        safeCheckVO.setMrStepCd("Z0060");
        safeCheckVO.setProcStCd("Z0110");
        safeDAO.updateSafeChrgrEffEnd(safeCheckVO);
    	mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0060", "Z0131", "Z02A", "Z0010", "Z02A");

        // 메일송신 : MR요청번호, 본문내용:설계팀 MR수행완료
        // 수신자   : MR요청자
        mrMailService.addCc("Z0010 ", "Z02A");
        mrMailService.mailSend(safeCheckVO.getMrReqNo());
    }
    
  //가동전 안전점검 완료
    @Override
    public void compSafeCheck(SafeCheckVO safeCheckVO) {

        // STEP 단계 업데이트
        mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0070", "Z0121", "Z02A", "Z0070", "Z02A");
        safeCheckVO.setMrStepCd("Z0070");
        safeCheckVO.setProcStCd("Z0110");
        safeDAO.updateSafeChrgrEffEnd(safeCheckVO);

        // 메일송신 : MR요청번호, 본문내용:안전점검 일시/장소 공지
        // 수신자   : 가동전 안전점검 담당자들
        mrMailService.mailSend(safeCheckVO.getMrReqNo());
        
        //201912 mr-she 인터페이스 task
        safeDAO.insertMrSheInterfaceTask(safeCheckVO.getMrReqNo());
    }
    
    @Override
    public void apprSafeCheck(SafeCheckVO safeCheckVO) {

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(safeCheckVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(safeCheckVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(safeCheckVO.getMrReqNo());
                mrAtchFile.setMrStepCd("Z0070");
                mrAtchFile.setChrgrClCd("Z02A");
                mrAtchFile.setFileStepCd("0801");

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }

            }
        }
        // STEP 단계 업데이트

//-- 2015.12.21 수정
//-- 수정 전        
/*
        mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0070", "Z0131", "Z02I", "Z0030", "Z02I");
        safeCheckVO.setMrStepCd("Z0070");
        safeCheckVO.setProcStCd("Z0121");
        safeDAO.updateSafeChrgrEffEnd(safeCheckVO);
        mrStepService.insertMrStepSingleAction(safeCheckVO.getMrReqNo(), "Z0080", "Z0110", "Z02I");

        // 메일송신 : MR요청번호, 본문내용:안전점검 모두 완료
        // 수신자   : JobEngineer, ProjectEngineer
        mrMailService.addCc("Z0030 ", "Z02I");
        mrMailService.mailSend(safeCheckVO.getMrReqNo());
*/
//-- 수정 후
        // STEP 단계 업데이트
        //mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0070", "Z0131", "Z02D", "Z0030", "Z02D");
        mrStepService.updateMrStep(safeCheckVO.getMrReqNo(), "Z0070", "Z0131", "Z02D", "Z0020", "Z02D");
        safeCheckVO.setMrStepCd("Z0070");
        safeCheckVO.setProcStCd("Z0121");
        safeDAO.updateSafeChrgrEffEnd(safeCheckVO);
        mrStepService.insertMrStepSingleAction(safeCheckVO.getMrReqNo(), "Z0080", "Z0110", "Z02D");

        // 메일송신 : MR요청번호, 본문내용:안전점검 모두 완료
        // 수신자   : JobEngineer
        mrMailService.addCc("Z0020 ", "Z02D");
        mrMailService.mailSend(safeCheckVO.getMrReqNo());
        //mrMailService.addCc("Z0030 ", "Z02I");
        //mrMailService.mailSend(safeCheckVO.getMrReqNo());
//-- End                
    }

    //기술 검토 임시저장
    @Override
    public void apprSafeCheck2(SafeCheckVO safeCheckVO) {   	
   
    	// 기술 검토 확인 (추가: 2021-11-05)
    	//if(StringUtils.isNotEmpty(safeCheckVO.getPnidDt())){
    		
    	safeDAO.updateCheckPnidDt(safeCheckVO);
    	//}
    	
    	mrStepService.insertFile(safeCheckVO.getMrReqNo(), "Z0060", "Z02D", "0201", safeCheckVO.getMrAtchFiles());
    	
      /*  첨부파일
        if (CollectionUtils.isNotEmpty(safeCheckVO.getMrAtchFiles())) {

        	System.out.println("mrAtchFiles:: ");
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(safeCheckVO.getMrAtchFiles());
            
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(safeCheckVO.getMrReqNo());
                mrAtchFile.setMrStepCd("Z0060");
                mrAtchFile.setChrgrClCd("Z02A");
                mrAtchFile.setFileStepCd("0801");
                
                System.out.println("mrAtchFile:: " + mrAtchFile.isDeleted() + "---"+mrAtchFile.isInserted());
                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }
            }
        }*/       

    }
    
    /**
     * 9999-12-31 날짜를 Date 형태로 변환하여 반환.
     * @return
     */
    private Date get99991231(String endDate){
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = null;
        try {
            if(endDate==null) {
                endDate="9999-12-31";
            }
            to = transFormat.parse(endDate);
        } catch (ParseException e) {
            //logger.debug(e.getStackTrace());
        }
        return to;
    }
}
