package com.mr.mrrq.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.common.domain.MrRvRstVO;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.mrrq.domain.MrReqEquipVO;
import com.mr.mrrq.domain.MrReqIssueReformVO;
import com.mr.mrrq.domain.MrReqProcVO;

/**
 * 요청서 작성 DB 처리
 *
 * @author 박성룡
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.26 박성룡 최초 작성
 * </pre>
 */

@Repository
public class MRrqDAO  extends BaseSqlMapRepository {


    /**
     * 요청서 조회
     * @param mrReqNo
     * @return
     */
    public MRrqRegisterVO selectMrReq(int mrReqNo){
    	System.out.println("selectMrReq   "+ mrReqNo);    	
        return selectOne("mr.mrReq.selectMrReq", mrReqNo);
    }

    /**
     * 요청서 저장
     * @param mRrqRegisterVO
     * @return
     */
    public int insertMrReq(MRrqRegisterVO mRrqRegisterVO){
        return insert("mr.mrReq.insertMrReq", mRrqRegisterVO);
    }

    /**
     * 요청서 수정
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReq(MRrqRegisterVO mRrqRegisterVO){
        return update("mr.mrReq.updateMrReq", mRrqRegisterVO);
    }

    /**
     * 요청서 논리 삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReqDelYn(MRrqRegisterVO mRrqRegisterVO){
        return update("mr.mrReq.updateMrReqDelYn", mRrqRegisterVO);
    }

    /**
     * 문제개선 조회
     * @param mrReqNo
     * @return
     */
    public List<MrReqIssueReformVO> selectMrReqIssue(int mrReqNo){
        return selectList("mr.mrReq.selectMrReqIssue", mrReqNo);
    }

    /**
     * 문제개선 저장
     * @param mrReqIssueReformVO
     * @return
     */
    public int insertMrReqIssue(MrReqIssueReformVO mrReqIssueReformVO){
        return insert("mr.mrReq.insertMrReqIssue", mrReqIssueReformVO);
    }

    /**
     * 공정 저장
     * @param mrReqProcVO
     * @return
     */
    public int insertMrReqProc(MrReqProcVO mrReqProcVO){
        return insert("mr.mrReq.insertMrReqProc", mrReqProcVO);
    }

    /**
     * 설비 저장
     * @param mrReqEquipVO
     * @return
     */
    public int insertMrReqEquip(MrReqEquipVO mrReqEquipVO){
        return insert("mr.mrReq.insertMrReqEquip", mrReqEquipVO);
    }


    /**
     * 문제개선 논리삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReqIssueDelYn(MRrqRegisterVO mRrqRegisterVO){
        return insert("mr.mrReq.updateMrReqIssueDelYn", mRrqRegisterVO);
    }

    /**
     * 공정 논리 삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReqProcDelYn(MRrqRegisterVO mRrqRegisterVO){
        return update("mr.mrReq.updateMrReqProcDelYn", mRrqRegisterVO);
    }

    /**
     * 설비 논리 삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReqEquipDelYn(MRrqRegisterVO mRrqRegisterVO){
        return update("mr.mrReq.updateMrReqEquipDelYn", mRrqRegisterVO);
    }


    /**
     * MR_NO 채번 업데이트
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMakeMrNo(int mrReqNo){
        return update("mr.mrReq.updateMakeMrNo", mrReqNo);
    }

    /**
     * HAZOP과 PORC 수행여부를 업데이트
     * @param mrRvRstVO
     * @return
     */
    public int updateHazopAndPorc(MrRvRstVO mrRvRstVO){
        return update("mr.mrReq.updateHazopAndPorc", mrRvRstVO);
    }


    public int updateMrReqTitle(MRrqRegisterVO mRrqRegisterVO) {
        return update("mr.mrReq.updateMrReqTitle", mRrqRegisterVO);
    }

    public void updateEdimsFileInfo(MRrqRegisterVO mRrqRegisterVO) {
        update("mr.mrReq.updateEdimsFileInfo", mRrqRegisterVO);
    }
    
    public int updateMrIfStat(MRrqRegisterVO mRrqRegisterVO) {
    	
        return 0; 
    }


    /**
     * MR수행 HAZOP과 PORC 수행여부를 업데이트
     * @param mrRvRstVO
     * @return
     */
    /*public int updateHazopAndPorcExe(MrRvRstVO mrRvRstVO){
        return update("mr.mrReq.updateHazopAndPorcExe", mrRvRstVO);
    }*/
}
