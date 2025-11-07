package com.mr.comp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.common.domain.MrRvRstVO;
import com.mr.comp.domain.CompChrgrChgHistVO;
import com.mr.comp.domain.CompFileManageVO;
import com.mr.comp.domain.CompFileVO;
import com.mr.comp.domain.CompRptVO;

/**
 * MR 완료 / 자료등록관리 서비스
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.28 조상욱 최초 작성
 * </pre>
 */
@Service
public interface CompService{

    /**
     * MR 마스터조회
     * @param mrReqNo
     * @return
     */
    CompFileVO selectCompFile(int mrReqNo);
    
    /**
     * 자료등록 메일 받는사람 조회
     * @param mrReqNo
     * @return
     */
    List<CompChrgrChgHistVO> selectCompLine(int mrReqNo);

    /**
     * 자료등록관리 조회
     * @param mrReqNo
     * @return
     */
    List<CompFileManageVO> selectCompFileManage(int mrReqNo);
    
    /**
     * 자료등록 체크 리스트 카운트 조회
     * @param mrReqNo
     * @return
     */
    List<Integer> selectCompCheckCount(int mrReqNo);

    /**
     * 자료등록관리 임시저장
     * @param compFileManageVO
     */
    void insertCompFile(CompFileManageVO compFileManageVO, CompFileVO compFileVO);
    
    /**
     * 자료등록관리 임시저장
     * @param compFileManageVO
     */
    void insertCompCheckList(CompFileVO compFileVO);

    /**
     * 자료등록관리 입력여부
     * @param compFileManageVO
     */
    List<CompChrgrChgHistVO> countCompFile(CompFileManageVO compFileManageVO);

    /**
     * 자료등록관리 임시저장(수정)
     * @param compFileManageVO
     */
    void updateCompFile(CompFileManageVO compFileManageVO, CompFileVO compFileVO);
    
    /**
     * 자료등록관리 임시저장(수정)
     * @param compFileManageVO
     */
    void updateCompCheckList(CompFileManageVO compFileManageVO);

    /**
     * 자료등록확인요청
     * @param compFileManageVO
     */
    void requestDoc(int mrReqNo, char from, String rtnMsg);
    
    /**
     * 자료등록관리 완료(P/E)
     * @param compFileManageVO
     */
    void updateCompFileComp(CompFileManageVO compFileManageVO);

    /**
     * 자료등록관리 문서등록요청
     * @param safeCheckVO
     */
    void requestCompFile(CompFileManageVO compFileManageVO);
    
    /**
     * MR 완료 승인자 저장
     * @param mRrqRegisterVO
     * @return
     */
    void insertCompCheck(CompRptVO mrRvRstVO);

    /**
     * MR 완료보고 조회
     * @param mrReqNo
     * @return
     */
    CompRptVO selectCompRpt(int mrReqNo);

    /**
     * MR완료 입력여부
     * @param compRptVO
     */
    CompChrgrChgHistVO countCompRpt(CompRptVO compRptVO);

    /**
     * MR완료 임시저장
     * @param compRptVO
     */
    void insertCompRpt(CompRptVO compRptVO);

    /**
     * MR완료 임시저장(수정)
     * @param compRptVO
     */
    void updateCompRpt(CompRptVO compRptVO);

    /**
     * MR완료 승인요청
     * @param compRptVO
     */
    void compRptAppReq(CompRptVO compRptVO);

    /**
     * MR완료 반송
     * @param compRptVO
     */
    void compRptRet(CompRptVO compRptVO);

    /**
     * MR완료 승인
     * @param compRptVO
     */
    void compRptAppr(CompRptVO compRptVO);

    /**
     * CAPEX 완료 조회
     * @param compRptVO
     */
    void compRptCapex (CompRptVO compRptVO);
    
    /**
     * CAPEX 미완료 메일 보내기
     * @param compRptVO
     */
    void compRptCapexMail (CompRptVO compRptVO);

    /**
     * MR SHE 인터페이스
     * @param compRptVO
     */
    void insertCompRptShe(CompRptVO compRptVO);

}
