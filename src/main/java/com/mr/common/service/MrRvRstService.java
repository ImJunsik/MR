package com.mr.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.common.domain.MrRvRstVO;
import com.mr.mrrq.domain.MrReqProcVO;
/**
 * 검토 서비스
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
public interface MrRvRstService{

    
    
    /**
     * 검토 입력
     * @param mrRvRstVO
     */
    void insertMrRvRst(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 입력
     * @param mrRvRstVO
     * yoo 240903
     */
    void updateTechMrRvRst(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 입력
     * yoo 240830 추가
     * @param mrRvRstVO
     */
    int copyMrRvRst(MrRvRstVO mrRvRstVO);

    /**
     * 검토 조회
     * @param mrRvRstVO
     * @return
     */
    MrRvRstVO selectMrRvRst(MrRvRstVO mrRvRstVO);

    /**
     * 검토조회 appLine 포함
     * @param mrRvRstVO
     * @return
     */
    MrRvRstVO selectMrRvRstRisk(MrRvRstVO mrRvRstVO);

    /**
     * 검토 수정
     * @param mrRvRstVO
     */
    int updateMrRvRst(MrRvRstVO mrRvRstVO);

    /**
     * 검토 삭제 det_no기준
     * @param mrRvRstVO
     */
    void updateMrRvRstDelYnDet(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 삭제 det_no기준
     * @param mrRvRstVO
     */
    int selectMrRvRstItemCount(MrRvRstVO mrRvRstVO);

    /**
     * 검토 삭제 item_cd 기준
     * @param mrRvRstVO
     */
    void updateMrRvRstDelYnItemCd(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 check여부 확인.
     * @param mrRvRstVO
     * @return
     */
    boolean selectRvRstIsCheck(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 갯수 반환
     * @param mrRvRstVO
     * @return
     */
    int selectRvRstCount(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 갯수 반환 (detNo기준)
     * @param mrRvRstVO
     * @return
     */
    int selectRvRstCountDet(MrRvRstVO mrRvRstVO);

    /**
     * 검토일 업데이트(승인여부판단용)
     * @param mrReqNo
     * @param mrStepCd
     */
    void updateMrRvRstApp(int mrReqNo, String itemCd);

    /**
     * 검토승인여부 반환
     * @param mrRvRstVO
     * @return
     */
    boolean selectRvRstIsAppCheck(int mrReqNo, String itemCd);

    /**
     * 201906 담당자 삭제처리(step_det, chg_hist)
     * @param mrReqNo
     * @param procStCd
     * @param stepCd
     * @param ChrgrClCd
     */
	//void updateDetHistDel(int mrReqNo, String procStCd, String stepCd, String chrgrClCd);
    
    //MR수행//////////////////////////////////////////////////////////////////////////////
    

    /**
     * 검토 입력
     * @param mrRvRstVO
     */
    void insertMrRvRstExe(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 입력
     * @param mrRvRstVO
     * yoo 240903
     */
    void updateTechMrRvRstExe(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 입력
     * yoo 240830 추가
     * @param mrRvRstVO
     */
    /*int copyMrRvRstExe(MrRvRstVO mrRvRstVO);*/

    /**
     * 검토 조회
     * @param mrRvRstVO
     * @return
     */
    MrRvRstVO selectMrRvRstExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토조회 appLine 포함
     * @param mrRvRstVO
     * @return
     */
    MrRvRstVO selectMrRvRstRiskExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 수정
     * @param mrRvRstVO
     */
    int updateMrRvRstExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 삭제 det_no기준
     * @param mrRvRstVO
     */
    void updateMrRvRstDelYnDetExe(MrRvRstVO mrRvRstVO);
    
    /**
     * 검토 삭제 det_no기준
     * @param mrRvRstVO
     */
    int selectMrRvRstItemCountExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 삭제 item_cd 기준
     * @param mrRvRstVO
     */
    void updateMrRvRstDelYnItemCdExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 check여부 확인.
     * @param mrRvRstVO
     * @return
     */
    boolean selectRvRstIsCheckExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 갯수 반환
     * @param mrRvRstVO
     * @return
     */
    int selectRvRstCountExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토 내역 갯수 반환 (detNo기준)
     * @param mrRvRstVO
     * @return
     */
    int selectRvRstCountDetExe(MrRvRstVO mrRvRstVO);

    /**
     * 검토일 업데이트(승인여부판단용)
     * @param mrReqNo
     * @param mrStepCd
     */
    void updateMrRvRstAppExe(int mrReqNo, String itemCd);

    /**
     * 검토승인여부 반환
     * @param mrRvRstVO
     * @return
     */
    boolean selectRvRstIsAppCheckExe(int mrReqNo, String itemCd);
}
