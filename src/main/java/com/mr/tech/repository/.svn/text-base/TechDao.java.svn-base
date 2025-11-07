package com.mr.tech.repository;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.tech.domain.TechReviewVO;

/**
 * 기술 및 타당성 검토 DB
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

@Repository
public class TechDao extends BaseSqlMapRepository{

    /**
     * 기술 및 타당성 검토 조회
     * @param mrReqNo
     * @return TechReviewVO
     */
    public TechReviewVO selectMrTechReview(int mrReqNo) {
        return selectOne("mr.tech.selectMrTechReview", mrReqNo);
    }

    /**
     * 기술 및 타당성 검토 논리삭제
     * @param techReviewVO
     */
    @Deprecated
    public void updateMrTechReviewDelYn(TechReviewVO techReviewVO) {
        update("mr.tech.updateMrTechReviewDelYn", techReviewVO);
    }

    /**
     * 기술 및 타당성 검토 내용저장
     * @param techReviewVO
     */
    public void insertMrTechReview(TechReviewVO techReviewVO) {
        insert("mr.tech.insertMrTechReview", techReviewVO);
    }

    /**
     * 기술 및 타당성 검토 내용수정
     * @param techReviewVO
     */
    public void updateMrTechReview(TechReviewVO techReviewVO) {
        update("mr.tech.updateMrTechReview", techReviewVO);

    }
    
    /**
     * 기술 및 타당성 검토 반려시 프로젝트 엔지니어 삭제 처리 원복 
     * @param techReviewVO
     */
    public void updateProjectEngineer(TechReviewVO techReviewVO) {
        update("mr.tech.updateProjectEngineer", techReviewVO);

    }
    

    /**
     * mr완료 조회
     * @param mrReqNo
     * @return
     */
    public TechReviewVO selectMrComplete(int mrReqNo) {
        return selectOne("mr.tech.selectMrComplete", mrReqNo);
    }
}
