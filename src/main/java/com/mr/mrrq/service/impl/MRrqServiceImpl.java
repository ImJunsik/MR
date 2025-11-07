package com.mr.mrrq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.domain.MrAtchFile;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.mrrq.domain.MrReqEquipVO;
import com.mr.mrrq.domain.MrReqIssueReformVO;
import com.mr.mrrq.domain.MrReqProcVO;
import com.mr.mrrq.repository.MRrqDAO;
import com.mr.mrrq.service.MRrqService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;

@Service
public class MRrqServiceImpl extends BaseService implements MRrqService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MRrqDAO mrRqDAO;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    MrStepRepository mrStepRepository;

    @Autowired
    MrAtchFileService mrAtchFileService;

    /**
     * 요청서 조회
     */
    @Override
    public MRrqRegisterVO selectMrRreq(int mrReqNo) {

    	//mr 요청서 조회
        MRrqRegisterVO mRrqRegisterVO = mrRqDAO.selectMrReq(mrReqNo);
        
        //첨부파일조회
        if(mRrqRegisterVO!=null) {
            mRrqRegisterVO.setMrAtchFiles(mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0010"));
        }

        return mRrqRegisterVO;
    }

    /**
     * 요청서 신규저장
     */
    @Override
    public void insertMrReq(MRrqRegisterVO mRrqRegisterVO) {    	
    	
        mrRqDAO.insertMrReq(mRrqRegisterVO);

        insertMrReqDetailItem(mRrqRegisterVO);

        if (mRrqRegisterVO.getAppLine() != null) {
            mrStepService.insertMrStep(mRrqRegisterVO.getMrReqNo(), "Z0010", "Z0110", mRrqRegisterVO.getAppLine());
        } else {
            MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
            mrReqProcStepVO.setMrReqNo(mRrqRegisterVO.getMrReqNo());
            mrReqProcStepVO.setMrStepCd("Z0010");
            mrReqProcStepVO.setProcStCd("Z0110");
            mrStepRepository.insertProcStep(mrReqProcStepVO);
            mrStepRepository.updateMrReqStep(mrReqProcStepVO);
        }

        mrStepService.insertFile(mRrqRegisterVO.getMrReqNo(), "Z0010", "Z02A", "0101", mRrqRegisterVO.getMrAtchFiles());

    }

    /**
     * 요청서 결재라인만 수정
     */
    @Override
    public void mrRqStepUpdate(MRrqRegisterVO mRrqRegisterVO) {
        List<String> chrgrClCds = new ArrayList<String>();
        chrgrClCds.add("Z02C");
        chrgrClCds.add("Z02D");

        //실제로는 수정되지 않는다. 화면에 모드가 활성화 되지않음.(STEP이 꼬이는걸 방지하기 위하여 추가.)
        chrgrClCds.add("Z02E");
        if (mRrqRegisterVO.getAppLine() != null) {
            mrStepService.updateAppLine(mRrqRegisterVO.getMrReqNo(), mRrqRegisterVO.getAppLine(), chrgrClCds);
        }
    }

    /**
     * 요청서 수정
     */
    @Override
    public void updateMrReq(MRrqRegisterVO mRrqRegisterVO) {

        mrRqDAO.updateMrReq(mRrqRegisterVO);
        insertMrReqDetailItem(mRrqRegisterVO);

        //단계상세, 담당자 논리삭제후 생성
        mrStepService.insertMrStep(mRrqRegisterVO.getMrReqNo(), "Z0010", null, mRrqRegisterVO.getAppLine());
        mrStepService.insertFile(mRrqRegisterVO.getMrReqNo(), "Z0010", "Z02A", "0101", mRrqRegisterVO.getMrAtchFiles());
    }

    /**
     * 요청서 승인요청
     */
    @Override
    public void mrRqRegisterAppReq(MRrqRegisterVO mRrqRegisterVO) {
        if(mRrqRegisterVO.getMrReqNo()<1){
            insertMrReq(mRrqRegisterVO);
        } else {
            updateMrReq(mRrqRegisterVO);
        }

        mrStepService.insertNextAppEmp(mRrqRegisterVO.getMrReqNo(), "Z0121");
        
        //hajewook 수정  2015.09.08 상신메일 막음
        //mrMailService.mailSend(mRrqRegisterVO.getMrReqNo());
    }

    /**
     * 요청서 1차 승인
     */
    @Override
    public void mrRqRegisterAgree(MRrqRegisterVO mRrqRegisterVO) {
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mRrqRegisterVO.getMrReqNo());

        System.out.println("appInfoList.size()  jewook  :: "+appInfoList.size());
        System.out.println("mRrqRegisterVO.getMrReqNo()  jewook  :: "+mRrqRegisterVO.getMrReqNo());
        //System.out.println("mRrqRegisterVO.getMrReqNo()  jewook  :: "+appInfoList.appInfoList);
        
        if(appInfoList.size()>2) {
        	System.out.println("test1");
            //최종승인자가 아닐때
            mrStepService.insertNextAppEmp(mRrqRegisterVO.getMrReqNo(), "Z0121");
            //HSW 2015.0923 Remark 처리
            //mrMailService.mailSend(mRrqRegisterVO.getMrReqNo());

        } else if(appInfoList.size()==2) {
        	System.out.println("test2");
            //최종승인자 승인요청
            mrStepService.insertNextAppEmp(mRrqRegisterVO.getMrReqNo(), "Z0122");
            //HSW 2015.0923 Remark 처리
            //mrMailService.mailSend(mRrqRegisterVO.getMrReqNo());

        } else if(appInfoList.size()==1) {
        	System.out.println("test3");
            //최종승인자 승인
            mrRqRegisterApp(mRrqRegisterVO);

        }

    }

    /**
     * 요청서 2차 승인
     */
    @Override
    public void mrRqRegisterApp(MRrqRegisterVO mRrqRegisterVO) {
        
    	//yoo 20240111 Z0010단계에서 현재 단계 쿼리 문으로 가져오기 때문에 Z0010단계에서 Z00R0이 생성이 되는 문제 발생
    	//ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mRrqRegisterVO.getMrReqNo(),null);
    	
        List<ChrgrChgHist> riskApplist = new ArrayList<ChrgrChgHist>();
        List<ChrgrChgHist> actionApplist = new ArrayList<ChrgrChgHist>();
        for(ChrgrChgHist chrgrChgHist : mRrqRegisterVO.getAppLine()) {
        	if("Z02D".equals(chrgrChgHist.getChrgrClCd()) || "Z02G".equals(chrgrChgHist.getChrgrClCd()) || "Z02G1".equals(chrgrChgHist.getChrgrClCd())){
        		actionApplist.add(chrgrChgHist);
        	}
            if(chrgrChgHist.getChrgrClCd()!=null && "Z02D".equals(chrgrChgHist.getChrgrClCd())){
                chrgrChgHist.setProcTrmDt(mrStepService.get99991231(null));
                riskApplist.add(chrgrChgHist);
            }
        }
        mrStepService.updateAppLineDel(mRrqRegisterVO.getMrReqNo(), "Z00R0");
        
        //yoo 20240111 Z0010단계에서 현재 단계 쿼리 문으로 가져오기 때문에 Z0010단계에서 Z00R0이 생성이 되는 문제 발생
        //mrStepService.insertTechEmp(mRrqRegisterVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", riskApplist);

        //2025-07-30 ijs 딜레이 추가
        /* 2025-08-13 IJS 원복
        try {
            Thread.sleep(1000);  // 1000ms = 1초 동안 딜레이
        } catch (InterruptedException e) {
        	mrRqDAO.updateMakeMrNo(mRrqRegisterVO.getMrReqNo());
        }
        */
        
        mrRqDAO.updateMakeMrNo(mRrqRegisterVO.getMrReqNo());
        
        //mrRqDAO.updateMakeMrNo(mRrqRegisterVO.getMrReqNo());
        mrStepService.insertNextAppEmp(mRrqRegisterVO.getMrReqNo(), "Z0132");
        mrStepService.insertMrStepMultiAction(mRrqRegisterVO.getMrReqNo(), "Z0020", "Z0110", actionApplist,"Z02D", -1);
        
        // Z0020단계의 MR_REQ_PROC_STEP_NO 번호 읽어 오기
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mRrqRegisterVO.getMrReqNo(),null);
        logger.info("1. appInfo.getMrReqProcStepNo() " + appInfo.getMrReqProcStepNo());
        System.out.println("1. appInfo.getMrReqProcStepNo() " + appInfo.getMrReqProcStepNo());
        // Z0020단계에 위험성검토/PORC 담당자 Z00R0 추가
        mrStepService.insertTechEmp(mRrqRegisterVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z00R0", "Z0110", riskApplist);
        
        if(mRrqRegisterVO.getMrAtchFiles() != null) {
            List<MrAtchFile> mrFiles = new ArrayList<MrAtchFile>();
            for(MrAtchFile mrAtchFile : mRrqRegisterVO.getMrAtchFiles()) {
                if(mrAtchFile.getDrawMngNo()!=null) {
                    mrFiles.add(mrAtchFile);
                }
            }
            if(mrFiles.size()>0) {
                mRrqRegisterVO.setMrAtchFiles(mrFiles);
                //업로드된 파일 정보 변경
                mrRqDAO.updateEdimsFileInfo(mRrqRegisterVO);
            }
        }

        //2차승인후 메일발송 임시주석
        mrMailService.addCc("Z0010", "Z02A");
        //mrMailService.mailSend(mRrqRegisterVO.getMrReqNo());
    }

    /**
     * 요청서반려
     */
    @Override
    public void mrRqRegisterReturn(MRrqRegisterVO mRrqRegisterVO) {
    	System.out.println("mrRqRegisterReturn  >> ");
        mrStepService.insertPrevAppEmp(mRrqRegisterVO.getMrReqNo(), null, "Z0141", mRrqRegisterVO.getAppLine(), "Z02A");
        mrMailService.mailSend(mRrqRegisterVO.getMrReqNo());
    }

    /**
     * 사업취소
     */
    @Override
    public void mrRqRegisterCancel(int mrReqNo) {
        mrStepService.insertNextAppEmp(mrReqNo, "Z0143");
        mrMailService.allMrEmpMailSend(mrReqNo, null, "Z0143");
    }

    /**
     * 요청서 상세아이템 저장
     * @param mRrqRegisterVO
     */
    private void insertMrReqDetailItem(MRrqRegisterVO mRrqRegisterVO){
        //문제개선 사항 저장
        if (mRrqRegisterVO.getIssues() != null) {
            mrRqDAO.updateMrReqIssueDelYn(mRrqRegisterVO);
            for (MrReqIssueReformVO mrReqIssueReformVO : mRrqRegisterVO.getIssues()) {
                if (mrReqIssueReformVO.getIssueCd() == null)
                    continue;
                mrReqIssueReformVO.setMrReqNo(mRrqRegisterVO.getMrReqNo());
                mrRqDAO.insertMrReqIssue(mrReqIssueReformVO);
            }
        }

        //공정 저장
        if (mRrqRegisterVO.getProcs() != null) {
            mrRqDAO.updateMrReqProcDelYn(mRrqRegisterVO);
            for (MrReqProcVO mrReqProcVO : mRrqRegisterVO.getProcs()) {
                if (mrReqProcVO.getMrProcNo() == null)
                    continue;
                mrReqProcVO.setMrReqNo(mRrqRegisterVO.getMrReqNo());
                mrRqDAO.insertMrReqProc(mrReqProcVO);
            }
        }

        //설비 저장
        if (mRrqRegisterVO.getEquips() != null) {
            mrRqDAO.updateMrReqEquipDelYn(mRrqRegisterVO);
            for (MrReqEquipVO mrReqEquipVO : mRrqRegisterVO.getEquips()) {
                if (mrReqEquipVO.getMrEquipCd() == null)
                    continue;
                mrReqEquipVO.setMrReqNo(mRrqRegisterVO.getMrReqNo());
                mrRqDAO.insertMrReqEquip(mrReqEquipVO);
            }
        }
    }

    @Override
    public int updateHazopAndPorc(MrRvRstVO mrRvRstVO) {
        return mrRqDAO.updateHazopAndPorc(mrRvRstVO);
    }


    /**
     * 요청서 타이틀 수정
     */
    @Override
    public int updateMrReqTitle(MRrqRegisterVO mRrqRegisterVO) {
        return mrRqDAO.updateMrReqTitle(mRrqRegisterVO);
    }

    /**
     * 요청서 타이틀 수정
     */
    @Override
    public int updateMrIfStat(MRrqRegisterVO mRrqRegisterVO) {
        return 1; 
    }

}
