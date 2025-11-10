package com.mr.jobs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.base.util.IsOperDistinc;
import com.common.file.domain.MrAtchFile;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.service.MrRvRstService;
import com.mr.jobs.domain.JobsCheckMasterVO;
import com.mr.jobs.domain.JobsCheckVO;
import com.mr.jobs.domain.JobsPorcVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.jobs.repository.JobsReviewDao;
import com.mr.jobs.service.JobsReviewService;
import com.mr.mrrq.service.MRrqService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MailToInfoVo;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechInvestVO;
import com.mr.tech.repository.TechInvestDao;

/**
 * 직무검토 서비스 구현
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.14 박성룡 최초 작성
 * 2014.07.18 박성룡 PORC 담당자 지정
 * 2014.07.22 박성룡 PORC 저장완료
 * 2014.07.23 박성룡 직무검토 전체 완료 로직 추가
 * 2014.08.26 박성룡 HAZOP Study 작성자 정보 DB입력추가
 * </pre>
 */

@Service
public class JobsReviewServiceImpl extends BaseService implements JobsReviewService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    JobsReviewDao jobsReviewDao;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrStepRepository mrStepRepository;

    @Autowired
    MrRvRstService mrRvRstService;

    @Autowired
    MRrqService mRrqService;

    @Autowired
    TechInvestDao techInvestDao;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrMailService mrMailService;

    /**
     * 직무검토 조회
     */
    @Override
    public JobsReviewVO selectJobsReview(Map map) {
    	
    	
        JobsReviewVO jobsReviewVO = jobsReviewDao.selectJobsReview(map);
        int mrReqNo = Integer.parseInt(map.get("mrReqNo").toString());
        String mrStepCd = map.get("mrStepCd").toString();
        //porc반대여부판단
        logger.info("porc반대여부판단");
        if(jobsReviewDao.selectPorcDisagreeCount(mrReqNo)>0) {
            jobsReviewVO.setPorcDisagree(true);            
        }

        if(jobsReviewVO!=null) {
            jobsReviewVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0020"));
        }

        if(jobsReviewVO!=null) {
            jobsReviewVO.setMrJobAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, mrStepCd));
        }
        if(mrStepCd.equals("Z0025"))
        {
	        //직무검토 완료여부 판단
	        jobsReviewVO.setJobsComplete(isJobsComplete(jobsReviewVO.getMrReqNo(),mrStepCd));
        }else
        {
        	//직무검토 완료여부 판단
        	jobsReviewVO.setJobsComplete(isJobsComplete(jobsReviewVO.getMrReqNo()));
        }
        return jobsReviewVO;
    }

    /**
     * 직무검토 저장
     */
    @Override
    public void insertSkipMrJobsReview(JobsReviewVO jobsReviewVO) {
        int mrReqProcStepDetNo = 0;
        String chrgrClCd = null;
        String fileStepCd = null;
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectJobLine(jobsReviewVO.getMrReqNo(), "Z0040");

        List<MrAtchFile> insertFiles = new ArrayList<MrAtchFile>();
        /**
         * appInfoList : 현재 직무검토자  리스트
         *
         * 작성이 활성화 되어 있는 사용자는 로그인 사용자 이므로 로그인사용자의 DEP_NO 셋팅
         * 여러분야를 한번에 등록하는 사용자가 없으므로 로그인 사용자만 구분해 내고 FOR문을 BREAK 시킨다.
         *
         */
        //yoo 240930 멀티를 위해 추가 함
        HashMap<String, Integer> chrgrClCdMap = new HashMap<>() ;
        for(ChrgrChgHist appChrgrChgHist : appInfoList) {
            if(jobsReviewVO.getLoginEmpNo().equals(appChrgrChgHist.getChrgEmpNo()) && appChrgrChgHist.getChrgrClCd().contains("Z02H")) {
                mrReqProcStepDetNo = appChrgrChgHist.getMrReqProcStepDetNo();
                chrgrClCd = appChrgrChgHist.getChrgrClCd();
                chrgrClCdMap.put(chrgrClCd, mrReqProcStepDetNo);
            }
            //logger.info("★★★★★★★★★★★★★★★★★★★★★ chrgrClCd  start★★★★★★★★★★★★★★★★★★★★★ ");
            //logger.info("chrgrClCd : " + chrgrClCd);
            //logger.info("★★★★★★★★★★★★★★★★★★★★★ chrgrClCd  end★★★★★★★★★★★★★★★★★★★★★ ");
            logger.debug("★★★★★★★★★★★★★★★★★★★★★ chrgrClCd  start★★★★★★★★★★★★★★★★★★★★★ ");
            logger.debug("chrgrClCd : " + chrgrClCd);
            logger.debug("★★★★★★★★★★★★★★★★★★★★★ chrgrClCd  end★★★★★★★★★★★★★★★★★★★★★ ");
        }


        switch (chrgrClCd) {
            case "Z02H1":
                fileStepCd = "0502";
                break;
            case "Z02H2":
                fileStepCd = "0503";
                break;
            case "Z02H3":
                fileStepCd = "0504";
                break;
            case "Z02H4":
                fileStepCd = "0505";
                break;
            case "Z02H5":
                fileStepCd = "0506";
                break;
            case "Z02H6":
                fileStepCd = "0507";
                break;
            case "Z02H7":
                fileStepCd = "0508";
                break;
            case "Z02H8":
                fileStepCd = "0509";
                break;
            case "Z02H9":
                fileStepCd = "0510";
                break;
            case "Z02H10":
                fileStepCd = "0511";
                break;
            case "Z02H11":
                fileStepCd = "0512";
                break;
        }

        if(jobsReviewVO.getMrAtchFiles() != null) {
            for(MrAtchFile mrAtchFile : jobsReviewVO.getMrAtchFiles()) {
                if(true) {
                    insertFiles.add(mrAtchFile);
                }
            }
        }

        //직무검토 내용 저장
        if(mrReqProcStepDetNo>0) {

            jobsReviewVO.setMrReqProcStepDetNo(mrReqProcStepDetNo);
            saveMrJobsReview(jobsReviewVO, chrgrClCdMap);
        }

        mrStepService.updateMrReqProcStepDetJobRvCd(mrReqProcStepDetNo, "");

        if(isJobsComplete(jobsReviewVO.getMrReqNo())){
            mrMailService.selectMailSend(jobsReviewVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }

        mrStepService.insertFile(jobsReviewVO.getMrReqNo(), "Z0040", chrgrClCd, fileStepCd , insertFiles);
    }


    /**
     * 직무검토 저장
     */
    @Override
    public void insertMrJobsReview(JobsReviewVO jobsReviewVO) {
        int mrReqProcStepDetNo = 0;
        String chrgrClCd = null;
        String fileStepCd = null;
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(jobsReviewVO.getMrReqNo());

        List<MrAtchFile> insertFiles = new ArrayList<MrAtchFile>();
        /**
         * appInfoList : 현재 직무검토자  리스트
         *
         * 작성이 활성화 되어 있는 사용자는 로그인 사용자 이므로 로그인사용자의 DEP_NO 셋팅
         * 여러분야를 한번에 등록하는 사용자가 없으므로 로그인 사용자만 구분해 내고 FOR문을 BREAK 시킨다.
         *
         */
        
        
      //yoo 240930 멀티를 위해 추가 함
        HashMap<String, Integer> chrgrClCdMap = new HashMap<>() ;

            
        for(ChrgrChgHist appChrgrChgHist : appInfoList) {
            if(jobsReviewVO.getLoginEmpNo().equals(appChrgrChgHist.getChrgEmpNo()) && appChrgrChgHist.getChrgrClCd().contains("Z02H")) {
                mrReqProcStepDetNo = appChrgrChgHist.getMrReqProcStepDetNo();
                chrgrClCd = appChrgrChgHist.getChrgrClCd();
                chrgrClCdMap.put(chrgrClCd, mrReqProcStepDetNo);
            }
        }


        switch (chrgrClCd) {
            case "Z02H1":
                fileStepCd = "0502";
                break;
            case "Z02H2":
                fileStepCd = "0503";
                break;
            case "Z02H3":
                fileStepCd = "0504";
                break;
            case "Z02H4":
                fileStepCd = "0505";
                break;
            case "Z02H5":
                fileStepCd = "0506";
                break;
            case "Z02H6":
                fileStepCd = "0507";
                break;
            case "Z02H7":
                fileStepCd = "0508";
                break;
            case "Z02H8":
                fileStepCd = "0509";
                break;
            case "Z02H9":
                fileStepCd = "0510";
                break;
            case "Z02H10":
                fileStepCd = "0511";
                break;
            case "Z02H11":
                fileStepCd = "0512";
                break;
        }

        logger.info("jobsReviewVO.getMrAtchFiles()" + jobsReviewVO.getMrAtchFiles() );
        
        if(jobsReviewVO.getMrAtchFiles() != null) {
            for(MrAtchFile mrAtchFile : jobsReviewVO.getMrAtchFiles()) {
                if(true) {
                	logger.info("insertFiles" + mrAtchFile.getFileNm() );
                    insertFiles.add(mrAtchFile);
                }
            }
        }
        
        //직무검토 내용 저장
        if(mrReqProcStepDetNo>0) {
            jobsReviewVO.setMrReqProcStepDetNo(mrReqProcStepDetNo);
            saveMrJobsReview(jobsReviewVO, chrgrClCdMap);
        }
        
        mrStepService.updateMrReqProcStepDetJobRvCd(mrReqProcStepDetNo, "");
        
        if(isJobsComplete(jobsReviewVO.getMrReqNo())){
        	logger.info("m_bOper : [" + IsOperDistinc.m_bOper + "]");
        	if(IsOperDistinc.m_bOper)		// Yoo 운영서버일때만 동작 한다
        		mrMailService.selectMailSend(jobsReviewVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");        
        }
        
        mrStepService.insertFile(jobsReviewVO.getMrReqNo(), "Z0040", chrgrClCd, fileStepCd , insertFiles);        
    }

    /**
     * 직무검토 완료
     */
    @Override
    public void insertMrJobsReviewComplete(int mrReqNo) {
        if(jobsReviewDao.selectPorcDisagreeCount(mrReqNo)>0) {
            mRrqService.mrRqRegisterCancel(mrReqNo);
        } else {
            mrStepService.insertNextStepForJobs(mrReqNo);
        }
    }

    /**
     *  직무검토 보류
     */
    @Override
    public void insertMrJobsReviewSkip(JobsReviewVO jobsReviewVO) {
        //마스터 테이블은 조작하지 않도록 null 처리해서 저장로직을 넘긴다.
        int mrReqProcStepDetNo = 0;
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(jobsReviewVO.getMrReqNo());

        /**
         * appInfoList : 현재 직무검토자  리스트
         *
         * 작성이 활성화 되어 있는 사용자는 로그인 사용자 이므로 로그인사용자의 DEP_NO 셋팅
         * 여러분야를 한번에 등록하는 사용자가 없으므로 로그인 사용자만 구분해 내고 FOR문을 BREAK 시킨다.
         *
         */
        for(ChrgrChgHist appChrgrChgHist : appInfoList) {
            if(jobsReviewVO.getLoginEmpNo().equals(appChrgrChgHist.getChrgEmpNo()) && appChrgrChgHist.getChrgrClCd().contains("Z02H")) {
                mrReqProcStepDetNo = appChrgrChgHist.getMrReqProcStepDetNo();
                break;
            }
        }

        if(mrReqProcStepDetNo>0) {

            MrRvRstVO mrRvRstVO = new MrRvRstVO();
            mrRvRstVO.setMrReqProcStepDetNo(mrReqProcStepDetNo);

            //논리삭제
            mrRvRstService.updateMrRvRstDelYnDet(mrRvRstVO);

            mrRvRstVO.setItemCd("JOBS");
            mrRvRstVO.setClCmt15("SKIP");
            mrRvRstVO.setMrReqNo(jobsReviewVO.getMrReqNo());
            mrRvRstService.insertMrRvRst(mrRvRstVO);

        }

        mrStepService.updateMrReqProcStepDetJobRvCd(mrReqProcStepDetNo, "SKIP");

        if(isJobsComplete(jobsReviewVO.getMrReqNo())){
            mrMailService.selectMailSend(jobsReviewVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }
    }


    /**
     * PORC 위원 선정 내역 조회
     */
    @Override
    public JobsPorcVO selectMrJobsPorc(int mrReqNo) {
        return jobsReviewDao.selectJobsPorc(mrReqNo);
    }

    /**
     * PROC 위원 선정 임시저장
     */
    @Override
    public void insertMrJobsPorcSave(JobsPorcVO jobsPorcVO) {

        ChrgrChgHist delInfo = new ChrgrChgHist();
        delInfo.setMrReqNo(jobsPorcVO.getMrReqNo());
        delInfo.setMrStepCd("Z00P0");
        mrStepService.updatePorcAppLineDel(delInfo);

        //PORC 위원 저장
        porcAppLineSave(jobsPorcVO, false);
        
        //202001
        //202001 proc 위원에게 e-mail 발송
        logger.info("e-mail 로직시작   :: " + jobsPorcVO);
        //mrMailService.addCc("Z0020 ", "Z02D");
        //mrMailService.addCc("Z0030 ", "Z02C");
        //mrMailService.addCc("Z0030 ", "Z02F");
        //mrMailService.mailSendMulti(techReviewVO.getMrReqNo());
        
        
        /*
         * mailSendMulti내용
         * 1) 보내는 사람 세팅
         *  - getMailInfo(mrReqNo, null, null);
        
        */ 
        //임시저장시는 메일을 보내지 않는다 2021-04-14 
        //mrMailService.mailSendMulti(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", "Z02D");        
        logger.info("e-mail 끝:: ");
    }

    /**
     * PORC위원 선정 완료
     */
    @Override
    public void insertMrJobsPorcComplete(JobsPorcVO jobsPorcVO) {

        ChrgrChgHist delInfo = new ChrgrChgHist();
        delInfo.setMrReqNo(jobsPorcVO.getMrReqNo());
        delInfo.setMrStepCd("Z00P0");
        mrStepService.updatePorcAppLineDel(delInfo);

        //PORC 위원 저장
        porcAppLineSave(jobsPorcVO, true);

        //PORC 번호 생성
        jobsReviewDao.updateMakePorcNo(jobsPorcVO.getMrReqNo());

        logger.info("e-mail 로직시작   :: " + jobsPorcVO);
        String procYn = jobsPorcVO.getPorcYn();
        if(procYn == null)
        	mrMailService.mailSendMultiProc(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", "Z02L");
        else if(procYn.equals("Y"))
        	mrMailService.mailSendMultiProc(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", "Z02L");
        else if(procYn.equals("S"))
        	mrMailService.mailSendMultiShare(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", "Z02L");
        logger.info("e-mail 끝:: ");
    }


    /**
     * PORC 내용 저장
     */
    @Override
    public void insertMrJobPorcWriteSave(MrRvRstVO mrRvRstVO) {
        List<ChrgrChgHist> jobs = porcListAllAdd(mrRvRstVO.getMrReqNo());

        /**
         * jobs : 현재 PORC  리스트
         *
         * PORC 작성이 활성화 되어 있는 사용자는 로그인 사용자 이므로 로그인사용자의 DEP_NO 셋팅
         * 여러분야를 한번에 등록하는 사용자가 없으므로 로그인 사용자만 구분해 내고 FOR문을 BREAK 시킨다.
         */

        for(ChrgrChgHist chrgrChgHist : jobs) {
            if(chrgrChgHist.getChrgEmpNo().equals(mrRvRstVO.getLoginEmpNo())) {
                mrRvRstVO.setMrReqProcStepDetNo(chrgrChgHist.getMrReqProcStepDetNo());
                break;
            }
        }
        //PORC 내역 논리삭제
        mrRvRstService.updateMrRvRstDelYnDet(mrRvRstVO);

        //신규 PORC 내역 INSERT
        mrRvRstVO.setItemCd("PORC");
        mrRvRstService.insertMrRvRst(mrRvRstVO);
        /*
        //PORC 위원중 한명이라도 반대를 입력하면 직무검토를 종료하지 않고 기술검토자가 다시 저장할때 종료된다.
        if(!mrRvRstVO.getClCd01().equals("2")) {
            //직무검토 종료 체크
            isJobsComplete(Integer.parseInt(mrRvRstVO.getMrReqNo()));
        }*/


        if(isJobsComplete(mrRvRstVO.getMrReqNo())){
            mrMailService.selectMailSend(mrRvRstVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }
    }




    @Override
    public JobsCheckMasterVO selectMrCheck(int mrReqNo, String mrStepCd) {
        //2025-07-22 ijs 첨부파일 추가
        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewDao.selectMrCheckMaster(mrReqNo);
        		//mrRvRstService.selectMrRvRst(selectInfo);
        		
        if(jobsCheckMasterVO!=null) {
        	jobsCheckMasterVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, mrStepCd));
        }
    	
    	return jobsCheckMasterVO;  //jobsReviewDao.selectMrCheckMaster(mrReqNo);
    }

    @Override
    public void insertMrCheck(JobsCheckMasterVO jobsCheckMasterVO) {
        //기존 체크리스트 마스터 데이터 논리삭제
        jobsReviewDao.updateMrCheckMasterDelYn(jobsCheckMasterVO);

        //기존 체크리스트 데이터 논리삭제
        jobsReviewDao.updateMrCHeckDelYn(jobsCheckMasterVO);

        //신규 체크리스트 마스터 입력
        jobsReviewDao.insertMrCheckMaster(jobsCheckMasterVO);
        
        List<MrAtchFile> insertFiles = new ArrayList<MrAtchFile>();

        //신규 체크리스트 상세목록 입력
        if(jobsCheckMasterVO.getChecks() != null) {
            for(JobsCheckVO check : jobsCheckMasterVO.getChecks()) {
                check.setMrReqNo(jobsCheckMasterVO.getMrReqNo());
                check.setMrHazopNo(jobsCheckMasterVO.getMrHazopNo());
                jobsReviewDao.insertMrCheck(check);
            }
        }
        
        mrStepService.insertFile(jobsCheckMasterVO.getMrReqNo(), "Z0040", "Z02D" , "0603", jobsCheckMasterVO.getMrAtchFiles());
    }
    
    /*@Override
    public void insertMrExeCheck(JobsCheckMasterVO jobsCheckMasterVO) {
        //기존 체크리스트 마스터 데이터 논리삭제
        jobsReviewDao.updateMrCheckMasterDelYn(jobsCheckMasterVO);

        //기존 체크리스트 데이터 논리삭제
        jobsReviewDao.updateMrCHeckDelYn(jobsCheckMasterVO);

        //신규 체크리스트 마스터 입력
        jobsReviewDao.insertMrCheckMaster(jobsCheckMasterVO);
        
        List<MrAtchFile> insertFiles = new ArrayList<MrAtchFile>();

        //신규 체크리스트 상세목록 입력
        if(jobsCheckMasterVO.getChecks() != null) {
            for(JobsCheckVO check : jobsCheckMasterVO.getChecks()) {
                check.setMrReqNo(jobsCheckMasterVO.getMrReqNo());
                check.setMrHazopNo(jobsCheckMasterVO.getMrHazopNo());
                jobsReviewDao.insertMrCheck(check);
            }
        }
        
        mrStepService.insertFile(jobsCheckMasterVO.getMrReqNo(), "Z0040", "Z02D" , "0603", jobsCheckMasterVO.getMrAtchFiles());
    }*/

    @Override
    public void updateMrCheck(JobsCheckMasterVO jobsCheckMasterVO) {

        //기존 체크리스트 데이터 논리삭제
        jobsReviewDao.updateMrCHeckDelYn(jobsCheckMasterVO);

        //신규 체크리스트 마스터 입력
        jobsReviewDao.updateMrCheckMaster(jobsCheckMasterVO);

        //신규 체크리스트 상세목록 입력
        if(jobsCheckMasterVO.getChecks() != null) {
            for(JobsCheckVO check : jobsCheckMasterVO.getChecks()) {
                check.setMrReqNo(jobsCheckMasterVO.getMrReqNo());
                check.setMrHazopNo(jobsCheckMasterVO.getMrHazopNo());
                jobsReviewDao.insertMrCheck(check);
            }
        }

        if(isJobsComplete(jobsCheckMasterVO.getMrReqNo())){
            mrMailService.selectMailSend(jobsCheckMasterVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }
        
        mrStepService.insertFile(jobsCheckMasterVO.getMrReqNo(), "Z0040", "Z02D" , "0603", jobsCheckMasterVO.getMrAtchFiles());
    }
    
    /*@Override
    public void updateMrExeCheck(JobsCheckMasterVO jobsCheckMasterVO) {

        //기존 체크리스트 데이터 논리삭제
        jobsReviewDao.updateMrCHeckDelYn(jobsCheckMasterVO);

        //신규 체크리스트 마스터 입력
        jobsReviewDao.updateMrCheckMaster(jobsCheckMasterVO);

        //신규 체크리스트 상세목록 입력
        if(jobsCheckMasterVO.getChecks() != null) {
            for(JobsCheckVO check : jobsCheckMasterVO.getChecks()) {
                check.setMrReqNo(jobsCheckMasterVO.getMrReqNo());
                check.setMrHazopNo(jobsCheckMasterVO.getMrHazopNo());
                jobsReviewDao.insertMrCheck(check);
            }
        }

        if(isJobsComplete(jobsCheckMasterVO.getMrReqNo())){
            mrMailService.selectMailSend(jobsCheckMasterVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }
        
        mrStepService.insertFile(jobsCheckMasterVO.getMrReqNo(), "Z0040", "Z02D" , "0603", jobsCheckMasterVO.getMrAtchFiles());
    }*/

    @Override
    public MrRvRstVO selectMrRiskCheck(int mrReqNo) {
        MrRvRstVO selectInfo = new MrRvRstVO();
        selectInfo.setMrReqNo(mrReqNo);
        selectInfo.setItemCd("RISK");

        MrRvRstVO mrRvRstVO = mrRvRstService.selectMrRvRstRisk(selectInfo);

        if(mrRvRstVO!=null) {
            mrRvRstVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z00R0"));
        }

        /*
        if(mrRvRstVO!=null) {
            mrRvRstVO.setIsCheck(mrRvRstService.selectRvRstIsCheck(selectInfo));
        }*/

        return mrRvRstVO;
    }

    @Override
    public void insertRiskCheck(MrRvRstVO mrRvRstVO) {
        if(mrRvRstVO.getMrReqNo()!=null && mrRvRstVO.getItemCd()!=null) {
            mrRvRstService.updateMrRvRstDelYnItemCd(mrRvRstVO);
            mrRvRstService.insertMrRvRst(mrRvRstVO);
        }

        mrRvRstVO.setItemCd("RISK");

        //위험성검토 체크 입력을 확인한다.
        MrRvRstVO checkWrite = mrRvRstService.selectMrRvRst(mrRvRstVO);
        if(checkWrite!=null) {
            if(mrRvRstService.selectRvRstIsCheck(mrRvRstVO)) {
                mrRvRstVO.setHazopActYn("N");
            }else {
                mrRvRstVO.setHazopActYn("Y");
            }
        }

        if (mrRvRstVO.getPorcActYn() != null || mrRvRstVO.getHazopActYn() !=null) {
            //HAZOP 여부와 PORC 여부를 저장 (MR_REQ_MST 테이블 업데이트)
            mRrqService.updateHazopAndPorc(mrRvRstVO);
        }

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00R0");
        if(mrRvRstVO.getAppLine()!=null) {
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", mrRvRstVO.getAppLine());
        }

        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00R0", "Z02D", "0601" , mrRvRstVO.getMrAtchFiles());

    }

    @Override
    public void updateRiskCheck(MrRvRstVO mrRvRstVO) {
        mrRvRstService.updateMrRvRst(mrRvRstVO);

        mrRvRstVO.setItemCd("RISK");

        //위험성검토 체크 입력을 확인한다.
        MrRvRstVO checkWrite = mrRvRstService.selectMrRvRst(mrRvRstVO);
        if(checkWrite!=null) {
            if(mrRvRstService.selectRvRstIsCheck(mrRvRstVO)) {
                mrRvRstVO.setHazopActYn("N");
            }else {
                mrRvRstVO.setHazopActYn("Y");
            }
        }

        if (mrRvRstVO.getPorcActYn() != null || mrRvRstVO.getHazopActYn() !=null) {
            //HAZOP 여부와 PORC 여부를 저장 (MR_REQ_MST 테이블 업데이트)
            mRrqService.updateHazopAndPorc(mrRvRstVO);
        }

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00R0");
        if(mrRvRstVO.getAppLine()!=null) {
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", mrRvRstVO.getAppLine());

        }
        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00R0", "Z02D", "0601" , mrRvRstVO.getMrAtchFiles());
    }
    
    /*MR수행*/ 
    @Override
    public MrRvRstVO selectMrRiskCheckExe(int mrReqNo) {
        MrRvRstVO selectInfo = new MrRvRstVO();
        selectInfo.setMrReqNo(mrReqNo);
        selectInfo.setItemCd("EXE_HAZOP");

        MrRvRstVO mrRvRstVO = mrRvRstService.selectMrRvRstRiskExe(selectInfo);

        if(mrRvRstVO!=null) {
            mrRvRstVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0060"));
        }

        /*
        if(mrRvRstVO!=null) {
            mrRvRstVO.setIsCheck(mrRvRstService.selectRvRstIsCheck(selectInfo));
        }*/

        return mrRvRstVO;
    }
    
    /*MR수행*/ 
    @Override
    public void insertRiskCheckExe(MrRvRstVO mrRvRstVO) {
        if(mrRvRstVO.getMrReqNo()!=null && mrRvRstVO.getItemCd()!=null) {
            mrRvRstService.updateMrRvRstDelYnItemCdExe(mrRvRstVO);
            mrRvRstService.insertMrRvRstExe(mrRvRstVO);
        }

        mrRvRstVO.setItemCd("EXE_CHECK");

        //위험성검토 체크 입력을 확인한다.
        MrRvRstVO checkWrite = mrRvRstService.selectMrRvRstExe(mrRvRstVO);
        if(checkWrite!=null) {
            if(mrRvRstService.selectRvRstIsCheckExe(mrRvRstVO)) {
                mrRvRstVO.setHazopActYn("N");
            }else {
                mrRvRstVO.setHazopActYn("Y");
            }
        }

        if (mrRvRstVO.getPorcActYn() != null || mrRvRstVO.getHazopActYn() !=null) {
            //HAZOP 여부와 PORC 여부를 저장 (MR_REQ_MST 테이블 업데이트)
            mRrqService.updateHazopAndPorc(mrRvRstVO);
        }

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);  //null대신 MR수행 플래그가 들어가야 할 듯
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00R0");
        if(mrRvRstVO.getAppLine()!= null) {
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", mrRvRstVO.getAppLine());
        }

        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00R0", "Z02D", "0601" , mrRvRstVO.getMrAtchFiles());

    }

    /*MR수행*/ 
    @Override
    public void updateRiskCheckExe(MrRvRstVO mrRvRstVO) {
        mrRvRstService.updateMrRvRstExe(mrRvRstVO);

        mrRvRstVO.setItemCd("RISK");

        //위험성검토 체크 입력을 확인한다.
        MrRvRstVO checkWrite = mrRvRstService.selectMrRvRstExe(mrRvRstVO);
        if(checkWrite!=null) {
            if(mrRvRstService.selectRvRstIsCheckExe(mrRvRstVO)) {
                mrRvRstVO.setHazopActYn("N");
            }else {
                mrRvRstVO.setHazopActYn("Y");
            }
        }

        if (mrRvRstVO.getPorcActYn() != null || mrRvRstVO.getHazopActYn() !=null) {
            //HAZOP 여부와 PORC 여부를 저장 (MR_REQ_MST 테이블 업데이트)
            mRrqService.updateHazopAndPorc(mrRvRstVO);
        }

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00R0");
        if(mrRvRstVO.getAppLine()!=null) {
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", mrRvRstVO.getAppLine());

        }
        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00R0", "Z02D", "0601" , mrRvRstVO.getMrAtchFiles());
    }



    @Override
    public void insertRiskCheckAppReq(MrRvRstVO mrRvRstVO) {
        MrRvRstVO checkMrRvRst = selectMrRiskCheck(mrRvRstVO.getMrReqNo());
        if(checkMrRvRst==null || (mrRvRstVO!=null && mrRvRstVO.getMrRvRstNo()==null)) {
            insertRiskCheck(mrRvRstVO);
        } else {
            updateRiskCheck(mrRvRstVO);
        }
        mrStepService.insertIsolationNextAppEmp(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0122");

        mrMailService.mailSendMulti(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0122", "Z02C");
        
        //201912 she 인터페이스 추가  하잡일경우 인터페이스 테이블에 인서트
        jobsReviewDao.insertMrSheInterface(mrRvRstVO.getMrReqNo());
    }

    @Override
    public void insertRiskCheckAppReqExe(MrRvRstVO mrRvRstVO) {
        MrRvRstVO checkMrRvRst = selectMrRiskCheckExe(mrRvRstVO.getMrReqNo());
        if(checkMrRvRst==null || (mrRvRstVO!=null && mrRvRstVO.getMrRvRstNo()==null)) {
            insertRiskCheckExe(mrRvRstVO);
        } else {
            updateRiskCheckExe(mrRvRstVO);
        }
        mrStepService.insertIsolationNextAppEmp(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0122");

        mrMailService.mailSendMulti(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0122", "Z02C");
        
        //201912 she 인터페이스 추가  하잡일경우 인터페이스 테이블에 인서트
        jobsReviewDao.insertMrSheInterface(mrRvRstVO.getMrReqNo());
    }


    @Override
    public void insertRiskCheckApp(Integer mrReqNo) { 
        //승인시 DT를 변경하여 승인하였는지 판단하도록 한다.
        mrRvRstService.updateMrRvRstApp(mrReqNo, "RISK");
        mrStepService.insertIsolationNextAppEmp(mrReqNo, "Z00R0", "Z0132");

        if(isJobsComplete(mrReqNo)){
            mrMailService.selectMailSend(mrReqNo, "Z0040", "COMPLETE", "Z0020", "Z02D");
        }

    }



    @Override
    public void insertRiskCheckReturn(MrRvRstVO mrRvRstVO) {
        mrStepService.insertIsolationPrevAppEmp(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0110", mrRvRstVO.getAppLine(), "Z02D");
        mrMailService.mailSendMulti(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0110", "Z02D");
    }


    @Override
    public void insertRiskCheckAppExe(Integer mrReqNo) { 
        //승인시 DT를 변경하여 승인하였는지 판단하도록 한다.
        mrRvRstService.updateMrRvRstAppExe(mrReqNo, "RISK");
        mrStepService.insertIsolationNextAppEmp(mrReqNo, "Z00R0", "Z0132");

        if(isJobsComplete(mrReqNo)){
            mrMailService.selectMailSend(mrReqNo, "Z0040", "COMPLETE", "Z0020", "Z02D");
        }

    }



    @Override
    public void insertRiskCheckReturnExe(MrRvRstVO mrRvRstVO) {
        mrStepService.insertIsolationPrevAppEmp(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0110", mrRvRstVO.getAppLine(), "Z02D");
        mrMailService.mailSendMulti(mrRvRstVO.getMrReqNo(), "Z00R0", "Z0110", "Z02D");
    }

    @Override
    public MrRvRstVO selectMrHazop(int mrReqNo) {
        MrRvRstVO selectInfo = new MrRvRstVO();
        selectInfo.setMrReqNo(mrReqNo);
        selectInfo.setItemCd("HAZOP");

        MrRvRstVO mrRvRstVO = mrRvRstService.selectMrRvRst(selectInfo);
        if(mrRvRstVO!=null) {
            mrRvRstVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z00H0"));
        }

        return mrRvRstVO;
    }

    /*@Override
    public MrRvRstVO selectMrExeHazop(int mrReqNo) {
        MrRvRstVO selectInfo = new MrRvRstVO();
        selectInfo.setMrReqNo(mrReqNo);
        selectInfo.setItemCd("HAZOP");

        MrRvRstVO mrRvRstVO = mrRvRstService.selectMrRvRst(selectInfo);
        if(mrRvRstVO!=null) {
            mrRvRstVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z006H"));
        }

        return mrRvRstVO;
    }*/
    
    @Override
    public void insertMrHazop(MrRvRstVO mrRvRstVO) {
        if(mrRvRstVO.getMrReqNo()!=null && mrRvRstVO.getItemCd()!=null) {
            mrRvRstService.updateMrRvRstDelYnItemCd(mrRvRstVO);
            mrRvRstService.insertMrRvRst(mrRvRstVO);

            List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
            ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
            appInfo.setChrgrClCd("Z02J");
            appLine.add(appInfo);
            //논리 삭제
            mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00H0");
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00H0", null, appLine);


            mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00H0", "Z02J", "0602" , mrRvRstVO.getMrAtchFiles());

            if(isJobsComplete(mrRvRstVO.getMrReqNo())){
                mrMailService.selectMailSend(mrRvRstVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
            }

        }
    }

    @Override
    public void updateMrHazop(MrRvRstVO mrRvRstVO) {
        mrRvRstService.updateMrRvRst(mrRvRstVO);

        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
        appInfo.setChrgrClCd("Z02J");
        appLine.add(appInfo);
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00H0");
        mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00H0", null, appLine);

        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00H0", "Z02J", "0602" , mrRvRstVO.getMrAtchFiles());

        if(isJobsComplete(mrRvRstVO.getMrReqNo())){
            mrMailService.selectMailSend(mrRvRstVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }

    }
    
    /*@Override
    public void insertMrExeHazop(MrRvRstVO mrRvRstVO) {
        if(mrRvRstVO.getMrReqNo()!=null && mrRvRstVO.getItemCd()!=null) {
            mrRvRstService.updateMrRvRstDelYnItemCd(mrRvRstVO);
            mrRvRstService.insertMrRvRst(mrRvRstVO);

            List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
            ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
            appInfo.setChrgrClCd("Z02J");
            appLine.add(appInfo);
            //논리 삭제
            mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00H0");
            mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00H0", null, appLine);


            mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00H0", "Z02J", "0602" , mrRvRstVO.getMrAtchFiles());

            if(isJobsComplete(mrRvRstVO.getMrReqNo())){
                mrMailService.selectMailSend(mrRvRstVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
            }

        }
    }*/

    /*@Override
    public void updateMrExeHazop(MrRvRstVO mrRvRstVO) {
        mrRvRstService.updateMrRvRst(mrRvRstVO);

        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrRvRstVO.getMrReqNo(),null);
        appInfo.setChrgrClCd("Z02J");
        appLine.add(appInfo);
        //논리 삭제
        mrStepService.updateAppLineDel(mrRvRstVO.getMrReqNo(), "Z00H0");
        mrStepService.insertTechEmp(mrRvRstVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00H0", null, appLine);

        mrStepService.insertFile(mrRvRstVO.getMrReqNo(), "Z00H0", "Z02J", "0602" , mrRvRstVO.getMrAtchFiles());

        if(isJobsComplete(mrRvRstVO.getMrReqNo())){
            mrMailService.selectMailSend(mrRvRstVO.getMrReqNo(), "Z0040", "COMPLETE", "Z0020", "Z02D");
        }

    }*/



    /**
     * 직무검토 완료 여부 판단 
     * @param mrReqNo
     */
    private boolean isJobsComplete(int mrReqNo){
        boolean isComplete = false;
        
        Map map = new HashMap();
        map.put("mrReqNo", mrReqNo);
        map.put("mrStepCd", "Z0040");

        JobsReviewVO jobsReviewVO= jobsReviewDao.selectJobsReview(map);

        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setItemCd("JOBS");

        //직무검토자와 직무검토 입력갯수가 일치하면 직무검토를 다 입력한 것으로 판단함.
        logger.info("직무검토자   :: " + jobsReviewVO.getAppLine().size()+ "입력갯수   :: " + mrRvRstService.selectRvRstCountDet(mrRvRstVO));        
        if(jobsReviewVO.getAppLine().size()==mrRvRstService.selectRvRstCountDet(mrRvRstVO)) {
            isComplete = true;
        }
        logger.info("직무검토자와 직무검토 입력갯수가 일치하면 직무검토를 다 입력한 것으로 판단함:: " + isComplete);
        
        //위험성검토수행여부 체크
        if(isComplete && mrRvRstService.selectRvRstIsAppCheck(mrRvRstVO.getMrReqNo(),"RISK")) {

            List<String> riskCheckMode = new ArrayList<String>(); 
            riskCheckMode.add("Z0020");
            riskCheckMode.add("Z0030");
            riskCheckMode.add("Z0040");

            if(mrStepService.isolationModeClass(mrReqNo, "Z00R0", riskCheckMode, null, false).equals("IAFF")){
                isComplete = true;
            }else {
                isComplete = false;
            }
        }
        logger.info("위험성검토수행여부 체크:: " + isComplete);

        logger.info("하잡상테 체크:: " + jobsReviewVO.getHazopActYn());
        
        if(isComplete && jobsReviewVO.getHazopActYn()!=null && jobsReviewVO.getHazopActYn().equals("Y")) {
            //Hazop일 경우
            mrRvRstVO.setItemCd("HAZOP");
            //HAZOP항목 입력 값이 최소 1이상일때
            if(mrRvRstService.selectRvRstCount(mrRvRstVO)>0){
                //HAZOP 해당항목의 해당유무가 O이 있으면 TRUE 로 반환함
                isComplete = true;
            } else {
                isComplete = false;                
            }
            logger.info("Hazop일 경우:: " + isComplete);

        } else if(isComplete && jobsReviewVO.getHazopActYn()!=null && jobsReviewVO.getHazopActYn().equals("N")) {

            //체크리스트가 작성 되었을 경우
            if ( jobsReviewDao.selectIsMrCHeck(mrReqNo).equals("TRUE")){
                isComplete = true;
                logger.info("체크리스트가 작성 되었을 경우:: " + isComplete);
            }

        } else if (isComplete && jobsReviewVO.getHazopActYn()==null) {
            isComplete = false;
            logger.info("하잡:: " + isComplete);
        }
        

        //PORC 수행이 필요할 경우
        if(isComplete && jobsReviewVO.getPorcActYn()!=null && jobsReviewVO.getPorcActYn().equals("Y")) {
            isComplete = false;
            //현재 입력된 porc 갯수
            mrRvRstVO.setItemCd("PORC");

            int n1 = porcListAllAdd(mrReqNo).size();
            int n2 = mrRvRstService.selectRvRstCount(mrRvRstVO);
            
            logger.info("n1 : " + n1 + ", n2 : " + n2);
            
            if(n1 > 0 && n1 == n2){
                isComplete = true;
            }
            logger.info("PORC 수행이 필요할 경우:: " + isComplete);

        } else if (isComplete && jobsReviewVO.getPorcActYn()==null) {
            isComplete = false;
            logger.info("PORC 수행이 필요할 경우:: " + isComplete);
        }

        List<String> costModeClass = new ArrayList<String>();
        costModeClass.add("Z0040");

        if(isComplete && !mrStepService.isolationModeClass(mrReqNo, "Z00I0", costModeClass, null, false).equals("IAFF")) {
            isComplete = false;
            logger.info("Z00I0:: " + isComplete);
        }

        logger.info("최종결과::" +  isComplete);
        
        return isComplete;
    }


    
    /**
     * 직무검토 1차 완료 여부 판단 
     * @param mrReqNo
     * yoo 240806
     */
    private boolean isJobsComplete(int mrReqNo, String mrStepCd){
        boolean isComplete = false;
        
        Map map = new HashMap();
        map.put("mrReqNo", mrReqNo);
        map.put("mrStepCd", mrStepCd);

        JobsReviewVO jobsReviewVO= jobsReviewDao.selectJobsReview(map);

        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setStepCd(mrStepCd);
        mrRvRstVO.setItemCd("JOBS");

        //직무검토자와 직무검토 입력갯수가 일치하면 직무검토를 다 입력한 것으로 판단함.
        logger.info("직무검토자   :: " + jobsReviewVO.getAppLine().size()+ "입력갯수   :: " + mrRvRstService.selectRvRstCountDet(mrRvRstVO));        
        if(jobsReviewVO.getAppLine().size()==mrRvRstService.selectRvRstCountDet(mrRvRstVO)) {
            isComplete = true;
        }
        logger.info("직무검토자와 직무검토 입력갯수가 일치하면 직무검토를 다 입력한 것으로 판단함:: " + isComplete);
        
       /*
        List<String> costModeClass = new ArrayList<String>();
        costModeClass.add(mrStepCd);

        if(isComplete && !mrStepService.isolationModeClass(mrReqNo, "Z00I0", costModeClass, null, false).equals("IAFF")) {
            isComplete = false;
            logger.info("Z00I0:: " + isComplete);
        }
*/
        logger.info("최종결과::" +  isComplete);
        
        return isComplete;
    }
    

    /**
     * PORC 결재라인 저장
     * @param jobsPorcVO
     * @param isAction : true를 입력받을 경우 활성화 하여 진행이 가능하도록 함. 임시저장 시에는 false를 사용하세요.
     */
    private void porcAppLineSave(JobsPorcVO jobsPorcVO, boolean isAction){
        //운전
        if(jobsPorcVO.getDrives()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getDrives(), isAction);
        }

        //기술
        if(jobsPorcVO.getTechs()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getTechs(), isAction);
        }

        //공무
        if(jobsPorcVO.getBuilds()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getBuilds(), isAction);
        }

        //검사
        if(jobsPorcVO.getChecks()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getChecks(), isAction);
        }

        //안전
        if(jobsPorcVO.getSafetys()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getSafetys(), isAction);
        }

        //기타
        if(jobsPorcVO.getEtcs()!=null) {
            mrStepService.insertPorcAppLine(jobsPorcVO.getMrReqNo(), "Z00P0", "Z0110", jobsPorcVO.getEtcs(), isAction);
        }
    }


    /**
     * PORC 리스트를 하나로 병합하여 반환함
     * @param mrReqNo
     * @return
     */
    private List<ChrgrChgHist> porcListAllAdd(int mrReqNo){
        JobsPorcVO jobsPorcVO = jobsReviewDao.selectJobsPorc(mrReqNo);
        List<ChrgrChgHist> jobs = new ArrayList<ChrgrChgHist>();
        //운전
        if(jobsPorcVO.getDrives()!=null) {
            jobs.addAll(jobsPorcVO.getDrives());
            logger.info("운전 jobs.size : " + jobsPorcVO.getDrives().size());
        }

        //기술
        if(jobsPorcVO.getTechs()!=null) {
            jobs.addAll(jobsPorcVO.getTechs());
            logger.info("기술 jobs.size : " + jobsPorcVO.getTechs().size());
        }

        //공무
        if(jobsPorcVO.getBuilds()!=null) {
            jobs.addAll(jobsPorcVO.getBuilds());
            logger.info("공무 jobs.size : " + jobsPorcVO.getBuilds().size());
        }

        //검사
        if(jobsPorcVO.getChecks()!=null) {
            jobs.addAll(jobsPorcVO.getChecks());
            logger.info("검사 jobs.size : " + jobsPorcVO.getChecks().size());
        }

        //안전
        if(jobsPorcVO.getSafetys()!=null) {
            jobs.addAll(jobsPorcVO.getSafetys());
            logger.info("안전 jobs.size : " + jobsPorcVO.getSafetys().size());
        }

        //기타
        if(jobsPorcVO.getEtcs()!=null) {
            jobs.addAll(jobsPorcVO.getEtcs());
            logger.info("기타 jobs.size : " + jobsPorcVO.getEtcs().size());
        }

        logger.info("TOTAL - jobs.size : " + jobs.size());
        return jobs;
    }
    private void saveMrRvRst(JobsReviewVO jobsReviewVO,String chrgrClCd,int nMrReqProcStepDetNo)
    {
    	MrRvRstVO mrRvRstVO = new MrRvRstVO();
        //delMrRvRstVO.setMrReqProcStepDetNo(jobsReviewVO.getMrReqProcStepDetNo());
        mrRvRstVO.setMrReqNo(jobsReviewVO.getMrReqNo());
		////////////////
		  mrRvRstVO.setChrgrClCd(chrgrClCd);
		  int count = mrRvRstService.selectMrRvRstItemCount(mrRvRstVO);	// yoo 240902 
		  logger.info(count + " : count !!!!!!!!!!! chrgrClCd : " + chrgrClCd);
		  //mrRvRstService.updateMrRvRstDelYnDet(delMrRvRstVO);		// yoo 240902 MrReqProcStepDetNo가 0일 때, 문제 생김, 따라서 방식을 바꾸어 제거
		  int index = 0;
		  if(count == 0)
		  {
		      if(jobsReviewVO.getRvRsts()!=null) {
		          for(MrRvRstVO mrRvRstVO_ : jobsReviewVO.getRvRsts()) {
		          	
		          	logger.info("mrRvRstVO_.getChrgrClCd() : " + mrRvRstVO_.getChrgrClCd());
		          	logger.info("jobsReviewVO.getJobs().get().getChrgEmpNo() : " + jobsReviewVO.getJobs().get(index).getChrgEmpNo());
		          	if(mrRvRstVO_.getLoginEmpNo().equals(jobsReviewVO.getJobs().get(index).getChrgEmpNo()))
		          	{
		          		if(mrRvRstVO_.getChrgrClCd().equals(chrgrClCd))
		          		{
			                mrRvRstVO_.setItemCd("JOBS");
			                mrRvRstVO_.setMrReqProcStepDetNo(nMrReqProcStepDetNo);		//
			                mrRvRstVO_.setMrReqNo(jobsReviewVO.getMrReqNo());
			                mrRvRstService.insertMrRvRst(mrRvRstVO_);
			                break;
		          		}
		          	}
			        index++;
		          }
		      }
		  }else{
		  	
		  	if(jobsReviewVO.getRvRsts()!=null) {
		          for(MrRvRstVO mrRvRstVO_ : jobsReviewVO.getRvRsts()) {
		          	
		          	logger.info("mrRvRstVO_.getChrgrClCd() : " + mrRvRstVO_.getChrgrClCd());
		          	logger.info("jobsReviewVO.getJobs().get().getChrgEmpNo() : " + jobsReviewVO.getJobs().get(index).getChrgEmpNo());
		          	if(mrRvRstVO_.getLoginEmpNo().equals(jobsReviewVO.getJobs().get(index).getChrgEmpNo()))
		          	{
		          		if(mrRvRstVO_.getChrgrClCd().equals(chrgrClCd))
		          		{
			                mrRvRstVO_.setItemCd("JOBS");
			                mrRvRstVO_.setMrReqProcStepDetNo(nMrReqProcStepDetNo);
			                mrRvRstVO_.setMrReqNo(jobsReviewVO.getMrReqNo());
			                mrRvRstService.updateTechMrRvRst(mrRvRstVO_);
			                break;
		          		}
		          	}
			        index++;
		          }
		      }
		  	
		  }
		  
		  /////////
    }
    //직무검토 내용을 저장함
    private void saveMrJobsReview(JobsReviewVO jobsReviewVO, HashMap<String, Integer> chrgClCdMap){

        
        String chrgrClCd = "";
        List<MrRvRstVO> list = jobsReviewVO.getRvRsts();
        int index = 0;
        //List<String> chrgrClCds = new ArrayList<>();
        
        for(MrRvRstVO item : list)
        {
        	if(item.getChrgrClCd() != null)
        	{
        		if(item.getLoginEmpNo().equals(jobsReviewVO.getJobs().get(index).getChrgEmpNo()))
            	{
	        		logger.info("item.getChrgrClCd() : " + item.getChrgrClCd());
	        		chrgrClCd = item.getChrgrClCd();
	        		saveMrRvRst(jobsReviewVO, chrgrClCd,chrgClCdMap.get(chrgrClCd));
	        		//chrgrClCds.add(chrgrClCd);
            	}
        	}
        	index++;
        }
        
    }

    @Override
    public TechInvestVO selectMrTechInvestModify(Integer mrReqNo) {
        return techInvestDao.selectMrTechInvestModify(mrReqNo);
    }

    @Override
    public void insertInvestCostModifyReq(Integer mrReqNo) {
        List<ChrgrChgHist> appLine = new ArrayList<ChrgrChgHist>();
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectMrAllAppLine(mrReqNo);

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo,null);

        //결재라인에 사업수행팀 팀장 기술검토 팀장 추가. 사업수행팀 팀장 제거 2014.08.25 chrgrChgHist.getChrgrClCd().equals("Z02G") ||
        for(ChrgrChgHist chrgrChgHist : appInfoList) {
            if(chrgrChgHist.getChrgrClCd().equals("Z02I")) {
                chrgrChgHist.setProcTrmDt(mrStepService.getAppTerm());
                appLine.add(chrgrChgHist);
            }
        }

        mrStepService.updateAppLineDel(mrReqNo, "Z00I0");
        mrStepService.insertTechEmp(mrReqNo, appInfo.getMrReqProcStepNo(),"Z00I0", "Z0110", appLine);


    }

    @Override
    public void insertMrTechInvest(TechInvestVO techInvestVO) {
        techInvestDao.updateMrTechInvestDelYn(techInvestVO);

        if(techInvestVO.getTechInvests() != null) {
            for(TechInvestVO investVO : techInvestVO.getTechInvests()){
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

            for(MrRvRstVO mrRvRstVO : techInvestVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(techInvestVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.insertMrRvRst(mrRvRstVO);
            }
        }

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(techInvestVO.getMrReqNo(),null);
        //논리 삭제
        mrStepService.updateAppLineDel(techInvestVO.getMrReqNo(), "Z00I0");
        if(techInvestVO.getAppLine()!=null) {
            mrStepService.insertTechEmp(techInvestVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00I0", null, techInvestVO.getAppLine());

        }

    }


    @Override
    public void insertInvestCostAppReq(TechInvestVO techInvestVO) {
        insertMrTechInvest(techInvestVO);
        mrStepService.insertIsolationNextAppEmp(techInvestVO.getMrReqNo(), "Z00I0", "Z0122");
    }



    @Override
    public void insertInvestCostApp(Integer mrReqNo) {
        mrStepService.insertIsolationNextAppEmp(mrReqNo, "Z00I0", "Z0132");
    }



    @Override
    public void insertInvestCostReturn(TechInvestVO techInvestVO) {
        mrStepService.insertIsolationPrevAppEmp(techInvestVO.getMrReqNo(), "Z00I0", "Z0110", techInvestVO.getAppLine(), "Z02I");
    }

    @Override
    public boolean skipJobReviews(Integer mrReqNo, boolean isMailSend) {

        boolean isSkip = false;
        List<ChrgrChgHist> jobs = mrStepRepository.selectJobLine(mrReqNo, "Z0040");

        List<MailToInfoVo> toInfos = new ArrayList<MailToInfoVo>();
        if(jobs!=null) {
            for(ChrgrChgHist job : jobs){
                if(job.getJobRvCd()!=null && job.getJobRvCd().equals("SKIP")){
                    MailToInfoVo toInfo = new MailToInfoVo();
                    toInfo.setToMailAddress(job.getEmpEmail());
                    toInfo.setToName(job.getChrgEmpName());
                    toInfo.setToEmpNo(job.getEmpEmail());
                    toInfos.add(toInfo);
                }
            }
        }

        if(toInfos.size()>0) {
            isSkip = true;
        } else {
            isSkip = false;
        }

        if(isMailSend) {
            mrMailService.selectMailSend(mrReqNo, "Z0040", "SKIP", toInfos);
        }

        return isSkip;
    }


    @Override
    public boolean getSkipJobReviewer(Integer mrReqNo) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        List<ChrgrChgHist> jobs = mrStepRepository.selectJobLine(mrReqNo, "Z0040");
        if(jobs!=null) {
            for(ChrgrChgHist job : jobs){
                if(job.getJobRvCd()!=null && job.getJobRvCd().equals("SKIP") && chrgrChgHist.getLoginEmpNo().equals(job.getChrgEmpNo())){
                    return true;
                }
            }
        }

        return false;
    }
    //MR수행 위험성검토//////////////////////////////////////////////////////////////////////////////////
    /**
     * 실행 HAZOP 조회
     */
    @Override
    public MrRvRstVO selectMrHazopExe(int mrReqNo) {
        return jobsReviewDao.selectMrHazopExe(mrReqNo);
    }

    /**
     * 실행 HAZOP 수정
     */
    @Override
    public void updateMrHazopExe(MrRvRstVO mrRvRstVO) {
        jobsReviewDao.updateMrHazopExe(mrRvRstVO);
    }

    /**
     * 실행 HAZOP 저장
     */
    @Override
    public void insertMrHazopExe(MrRvRstVO mrRvRstVO) {
        jobsReviewDao.insertMrHazopExe(mrRvRstVO);
    }

    /**
     * 실행 CHECK 조회
     */
    @Override
    public JobsCheckMasterVO selectMrCheckExe(Integer mrReqNo, String mrStepCd) {
        return jobsReviewDao.selectMrCheckExe(mrReqNo, mrStepCd);
    }

    /**
     * 실행 CHECK 저장
     */
    @Override
    public void insertMrCheckExe(JobsCheckMasterVO jobsCheckMasterVO) {
        jobsReviewDao.insertMrCheckExe(jobsCheckMasterVO);
    }

    /**
     * 실행 CHECK 수정
     */
    @Override
    public void updateMrCheckExe(JobsCheckMasterVO jobsCheckMasterVO) {
        jobsReviewDao.updateMrCheckExe(jobsCheckMasterVO);
    }

}
