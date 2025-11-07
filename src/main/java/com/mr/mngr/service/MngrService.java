package com.mr.mngr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.common.file.domain.MrAtchFile;
import com.mr.common.domain.CommCdVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.mrrq.domain.MrAlterVO;
import com.mr.step.domain.ChrgrChgHist;

/**
 * 관리자화면의 서비스로직
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.08.04 박성룡 최초 작성
 * </pre>
 */

@Service
public interface MngrService {


    /**
     * 단일코드조회
     * @param commCdVO
     * @return
     */
    CommCdVO selectCode(CommCdVO commCdVO);

    /**
     * 코드 조회
     * @param commCdVO
     * @return
     */
    List<CommCdVO> selectCodeList(CommCdVO commCdVO);

    /**
     * 신규기준정보등록
     * @param commCdVO
     * @return
     */
    int insertCode(CommCdVO commCdVO);

    /**
     * 기준정보수정
     * @param commCdVO
     * @return
     */
    int updateCode(CommCdVO commCdVO);

    /**
     * 코드정보 논리삭제
     * @param inCommCdVO
     */
    int updateCodeDelYn(CommCdVO inCommCdVO);

    /**
     * 결재라인정보 조회
     * @param mrReqNo
     * @return
     */
    List<ChrgrChgHist> selectMngrChangeList(int mrReqNo);

    /**
     * 결재라인정보 변경저장
     * @param mrReqNo
     * @param appList
     */
    void updateMngrChangeList(JobsReviewVO jobsReviewVO);
    
    /**
     * 사업명 변경저장
     * @param mrReqNo
     * @param appList
     */
    void updateMngrBzNameChange(JobsReviewVO jobsReviewVO);
    
    /**
     * MR 삭제 변경저장
     * @param mrReqNo
     * @param appList
     */
    boolean updateMngrMrDelete(IvstCostRegisterVO ivstCostRegisterVO);
    
    /**
     * 사업 취소 변경저장
     * @param mrReqNo
     * @param appList
     */
    boolean updateMngrBzCancelList(IvstCostRegisterVO ivstCostRegisterVO);
    
    /**
     * CAPEX 적용 변경저장
     * @param mrReqNo
     * @param appList
     */
    boolean updateMngrCapexApplyList(MrAlterVO mrAlterVO);
	
	 /**
    * 해당 mr의 진행상황 조회
    * @param mrReqNo
    * @return
    */
	List<ChrgrChgHist> selectStepCdList(Integer mrReqNo);

    /**
     * 첨부파일정보 조회
     * @param mrReqNo
     * @return
     */
	List<MrAtchFile> selectFilesList(Integer mrReqNo);
	
	/**
     * 첨부파일 정보 변경저장
     * @param mrReqNo
     * @param fileList
     */
    int updateMngrFilesList(JobsReviewVO jobsReviewVO);
}
