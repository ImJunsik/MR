package com.mr.mngr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.base.util.IsOperDistinc;
import com.common.file.domain.MrAtchFile;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.CommCdVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.mngr.repository.MngrDAO;
import com.mr.mngr.service.MngrService;
import com.mr.mrrq.domain.MrAlterVO;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;


@Service
public class MngrServiceImpl extends BaseService implements MngrService{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MngrDAO mngrDAO;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    MrStepRepository mrStepRepository;
    
    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrStepService mrStepService;


    @Override
    public CommCdVO selectCode(CommCdVO commCdVO) {
        return mngrDAO.selectCode(commCdVO);
    }

    @Override
    public List<CommCdVO> selectCodeList(CommCdVO commCdVO) {
        return mngrDAO.selectCodeList(commCdVO);
    }

    @Override
    public int insertCode(CommCdVO commCdVO) {
        return mngrDAO.insertCode(commCdVO);
    }

    @Override
    public int updateCode(CommCdVO commCdVO) {
        return mngrDAO.updateCode(commCdVO);
    }

    @Override
    public int updateCodeDelYn(CommCdVO inCommCdVO) {
        int deleteCount = 0;

        for(CommCdVO commCdVO : inCommCdVO.getCodes()){
            deleteCount += mngrDAO.updateCodeDelYn(commCdVO);
        }

        return deleteCount;
    }

    //결재아인 정보조회
    @Override
    public List<ChrgrChgHist> selectMngrChangeList(int mrReqNo) {
        return mngrDAO.selectMngrChangeList(mrReqNo);
    }

  //MR 정보 담당자 저장 
    @Override
    public void updateMngrChangeList(JobsReviewVO jobsReviewVO) {
        List<String> changes = new ArrayList<String>();
        
        //2017.05.18 추가        
        String strChkClCd = "A,B,C,E,G,H,J,K,L,M,N,O,P,Q";
       
        for (ChrgrChgHist chrgrChgHist : jobsReviewVO.getAppLine()){
        	
        	if(chrgrChgHist.getEmpChange().equals("false"))
        		continue;
        	
            if(chrgrChgHist.getMrReqProcStepDetNo()==null) {
                continue;
            } else { 
                chrgrChgHist.setMrReqNo(jobsReviewVO.getMrReqNo());
                
                if(chrgrChgHist.getChrgrClCd()!=null && (chrgrChgHist.getChrgrClCd().equals("Z02D") 
						|| chrgrChgHist.getChrgrClCd().equals("Z02G")) 
						&& chrgrChgHist.getEmpChange().equals("true")) {
                    //CHRGR_CL_CD 담당자 구분
                	mrStepRepository.updateChrgrChgHistClCd(chrgrChgHist);
                } else if(chrgrChgHist.getEmpChange().equals("true")) {
                	//처리담당자 변경 detNo기준
                    mrStepRepository.updateChrgrChgHist(chrgrChgHist);
                }
                
                //기존코드는 일부 케이스 에서만 updateChrgrChgHistClCd 를 사용
                /* wj 2017.05.18 정의
                 * MR 진행상태에 따라 담당자 변경 처리 정의
                 * chrgrChgHist.getChrgrClCd 의 substr(3) 이 A, B, C, E, G, H, J, K, L, M, N, O, P, Q 일때
                   - mrStepRepository.updateChrgrChgHistClCd(chrgrChgHist); 사용
                 * D, F, I 인 경우는 관리자가 직접 수정(단계별 진행상태에 따라 변경할 데이터가 상이하여 직접수정해야함)
                */

                //************************변경코드***********************************/
                
                //변경코드에서는 strChkClCd 에 해당하는 값이면 updateChrgrChgHistClCd 사용                
                //String pClCd = null;			//
                //if (chrgrChgHist.getChrgrClCd()!=null)
                //{
                //	pClCd = chrgrChgHist.getChrgrClCd().substring(3,4);                
                //}
                
                //System.out.println("임시 :: "+pClCd);

                //if(chrgrChgHist.getChrgrClCd()!=null && strChkClCd.equals(pClCd)){
                //	mrStepRepository.updateChrgrChgHistClCd(chrgrChgHist);
                //}else
                //{
                //	mrStepRepository.updateChrgrChgHist(chrgrChgHist);
                //}
                //************************끝***********************************/

                
                //결재기한 변경
                if(chrgrChgHist.getFstProcTrmDt()!=null) {
                    mrStepRepository.updateMrReqProcStepDetProcTrmDt(chrgrChgHist);
                }
            }

            if(chrgrChgHist.getEmpChange()!=null && chrgrChgHist.getEmpChange().equals("true")){
                changes.add(chrgrChgHist.getChrgEmpNo());
            }
        }

        if(changes.size()>0){
        	if(IsOperDistinc.m_bOper)
        		mrMailService.managerChangeMailSend(jobsReviewVO.getMrReqNo(), changes);
        }

    }
    
    
  //MR 사업명 변경
    @Override
    public void updateMngrBzNameChange(JobsReviewVO jobsReviewVO) {
          
        int result = mrStepRepository.updateBzNameChange(jobsReviewVO);
        
        System.out.println("---------------- result : "+ result);
        
        /*if(changes.size()>0){
        	if(IsOperDistinc.m_bOper)
        		mrMailService.managerChangeMailSend(jobsReviewVO.getMrReqNo(), changes);
        }*/

    }
    
  //MR 삭제
    @Override
    public boolean updateMngrMrDelete(IvstCostRegisterVO ivstCostRegisterVO) {
          
    	boolean result = mrStepRepository.updateMrDelete(ivstCostRegisterVO);
        
        /*if(changes.size()>0){
        	if(IsOperDistinc.m_bOper)
        		mrMailService.managerChangeMailSend(jobsReviewVO.getMrReqNo(), changes);
        }*/
    	return result;
    }
    
  //MR 사업 취소
    @Override
    public boolean updateMngrBzCancelList(IvstCostRegisterVO ivstCostRegisterVO) {
          
    	boolean result = mrStepRepository.updateBzCancel(ivstCostRegisterVO);
        
        /*if(changes.size()>0){
        	if(IsOperDistinc.m_bOper)
        		mrMailService.managerChangeMailSend(jobsReviewVO.getMrReqNo(), changes);
        }*/
    	return result;
    }
    
  //CAPEX 적용
    @Override
    public boolean updateMngrCapexApplyList(MrAlterVO mrAlterVO) {
          
    	boolean result = mrStepRepository.updateCapexApply(mrAlterVO);
        
        /*if(changes.size()>0){
        	if(IsOperDistinc.m_bOper)
        		mrMailService.managerChangeMailSend(jobsReviewVO.getMrReqNo(), changes);
        }*/
    	return result;
    }
    
    
    @Override
	public List<ChrgrChgHist> selectStepCdList(Integer mrReqNo) {
		return mngrDAO.selectStepCdList(mrReqNo);
	}

	@Override
	public List<MrAtchFile> selectFilesList(Integer mrReqNo) {
		return mrAtchFileService.getMrAtchFileList(mrReqNo, null);
	}

	@Override
	public int updateMngrFilesList(JobsReviewVO jobsReviewVO) {
		//JobsReviewServiceImpl > insertMrJobsReview 코드 참조
		List<MrAtchFile> insertFiles = new ArrayList<MrAtchFile>();
		
		if(jobsReviewVO.getMrAtchFiles() != null) {
            for(MrAtchFile mrAtchFile : jobsReviewVO.getMrAtchFiles()) {
                if(mrAtchFile.getFileStepCd() != null) {
                    insertFiles.add(mrAtchFile);
                }
            }
        }
		
		return mrStepService.insertFile(jobsReviewVO.getMrReqNo(), null, null, null, insertFiles);
	}



}
