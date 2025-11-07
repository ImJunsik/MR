package com.mr.tech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.step.domain.ChrgrChgHist;
import com.mr.tech.domain.TechReviewVO;
/**
 * 기술 및 타당성 검토 서비스
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
public interface TechService {

    /**
     * 기술 및 타당성검토 조회
     * @param mrReqNo
     * @return
     */
    TechReviewVO selectMrTechReview(int mrReqNo);
    /**
     * 기술 및 타당성검토 신규등록
     * @param techReviewVO
     */
    void insertMrTechReview(TechReviewVO techReviewVO);

    /**
     * 기술 및 타당성검토 수정
     * @param techReviewVO
     */

    void updateMrTechReview(TechReviewVO techReviewVO);
    /**
     * 기술 및 타당성검토 신규등록
     * @param techReviewVO
     */
    void insertMrTechReview(TechReviewVO techReviewVO,String mrStepCd);

    /**
     * 기술 및 타당성검토 수정
     * @param techReviewVO
     */

    void updateMrTechReview(TechReviewVO techReviewVO,String mrStepCd);

    /**
     * 직무검토(1) 승인요청
     * @param techReviewVO
     */
    void insertMrTechAppReq(TechReviewVO techReviewVO);
    
    /**
     * 기술 및 타당성검토 승인요청
     * @param techReviewVO
     */
    void insertMrTechAppReq2(TechReviewVO techReviewVO);
    /**
     * 기술 및 타당성검토 승인요청
     * yoo 240812 Z025처리를 위해 파라메터 추가
     * @param techReviewVO
     */
    void insertMrTechAppReq(TechReviewVO techReviewVO,String mrStepCd, String procStCd, String clcd);
    /**
    * 기술 및 타당성검토 승인
    * @param mrReqNo
    */
   void insertMrTechApp(TechReviewVO techReviewVO);
   
   /**
    * 기술 및 타당성검토 승인
    * @param mrReqNo
    */
   void insertMrTechApp2(TechReviewVO techReviewVO);
    
    /**
     * 기술 및 타당성검토 승인
     * @param mrReqNo
     */
    void insertMrTechApp(TechReviewVO techReviewVO, String mrStepCd);
  
    /**
     * 기술 및 타당성검토 반려
     * @param mrReqNo
     */
    void insertMrTechReturn(TechReviewVO techReviewVO);
    
    
    /**
     * 사업취소
     */
    public void mrRqRegisterCancel(int mrReqNo);
    
    
}
