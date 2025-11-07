package com.mr.comp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.common.file.domain.MrAtchFile;
import com.mr.comp.domain.CompChrgrChgHistVO;
import com.mr.comp.domain.CompFileManageVO;
import com.mr.comp.domain.CompFileVO;
import com.mr.comp.domain.CompProcStepDetVO;
import com.mr.comp.domain.CompRptVO;

/**
 * 자료등록관리,MR완료 DB 처리
 *
 * @author 조상욱
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.28 조상욱 최초 작성
 * </pre>
 */

@Repository
public class CompDAO  extends BaseSqlMapRepository {


    /**
     * 자료등록관리 MR 마스터 조회
     * @param mrReqNo
     * @return
     */
    public CompFileVO selectCompFile(int mrReqNo){
        return selectOne("mr.comp.selectCompFile", mrReqNo);
    }
    
    /**
     * 자료등록관리 MR 마스터 조회
     * @param mrReqNo
     * @return
     */
    public List<CompChrgrChgHistVO> selectCompLine(int mrReqNo){
        return selectList("mr.comp.selectCompLine", mrReqNo);
    }

    /**
     * 자료등록관리 조회
     * @param mrReqNo
     * @return
     */
    public List<CompFileManageVO> selectCompFileManage(int mrReqNo){
        return selectList("mr.comp.selectCompFileManage", mrReqNo);
    }
    
    /**
     * 자료등록관리 조회
     * @param mrReqNo
     * @return
     */
    public List<Integer> selectCompCheckCount(int mrReqNo){
        return selectList("mr.comp.selectCompCheckCount", mrReqNo);
    }

    /**
     * 자료등록관리 등록여부
     * @param mrReqNo
     * @return
     */
    public List<CompChrgrChgHistVO> countCompFile(CompFileManageVO compFileManageVO){
        return selectList("mr.comp.countCompFile", compFileManageVO);
    }

    /**
     * 자료등록관리 저장
     * @param compFileManageVO
     * @return
     */
    public int insertCompFile(CompFileManageVO compFileManageVO){
        return update("mr.comp.insertCompFile", compFileManageVO);
    }

    /**
     * 자료등록관리 수정
     * @param compFileManageVO
     * @return
     */
    public int updateCompFile(CompFileManageVO compFileManageVO){
        return update("mr.comp.updateCompFile", compFileManageVO);
    }

    /**
     * 자료등록관리 완료
     * @param compFileManageVO
     * @return
     */
    public int updateCompFileComp(CompFileManageVO compFileManageVO){
        return update("mr.comp.updateCompFileComp", compFileManageVO);
    }

    /**
     * 자료등록관리 문서등록요청
     * @param compFileManageVO
     * @return
     */
    public int requestCompFile(MrAtchFile mrAtchFile){
        return update("mr.comp.requestCompFile", mrAtchFile);
    }

    /**
     * 자료등록관리 진행상세 저장(신규)
     * @param compProcStepDetVO
     * @return
     */
    public int insertCompProcStepDet(CompProcStepDetVO compProcStepDetVO){
        return insert("mr.comp.insertCompFileProcStepDet", compProcStepDetVO);
    }

    /**
     * 자료등록관리 담당자 저장(신규)
     * @param compChrgrChgHistVO
     * @return
     */
    public int insertCompChrgrChgHist(CompChrgrChgHistVO compChrgrChgHistVO){
        return insert("mr.comp.insertCompFileChrgrChgHist", compChrgrChgHistVO);
    }

    /**
     * 자료등록관리 진행상세 저장(수정)
     * @param compChrgrChgHistVO
     * @return
     */
    public int updateCompProcStepDet(CompChrgrChgHistVO compChrgrChgHistVO){
        return update("mr.comp.updateCompFileProcStepDet", compChrgrChgHistVO);
    }

    /**
     * 자료등록관리 담당자 저장(수정)
     * @param compChrgrChgHistVO
     * @return
     */
    public int updateCompChrgrChgHist(CompChrgrChgHistVO compChrgrChgHistVO){
        return update("mr.comp.updateCompFileChrgrChgHist", compChrgrChgHistVO);
    }

    /**
     * 자료등록관리 문서등록요청건수 확인
     * @param mrReqNo
     * @return
     */
    public CompFileManageVO countMrStepComp(CompFileManageVO compFileManageVO){
        return selectOne("mr.comp.countMrStepComp", compFileManageVO);
    }

    /**
     * 자료등록관리 문서등록완료처리=>MR완료단계 Procedure 호출
     * @param compChrgrChgHistVO
     * @return
     */
    public int updateMrStepComp(CompFileManageVO compFileManageVO){
        return update("mr.comp.updateMrStepComp", compFileManageVO);
    }

    /**
     * MR완료보고 조회
     * @param mrReqNo
     * @return
     */
    public CompRptVO selectCompRpt(int mrReqNo){
        return selectOne("mr.comp.selectCompRpt", mrReqNo);
    }

    /**
     * MR완료보고 등록여부
     * @param mrReqNo
     * @return
     */
    public CompChrgrChgHistVO countCompRpt(CompRptVO compRptVO){
        return selectOne("mr.comp.countCompRpt", compRptVO);
    }

    /**
     * MR완료보고 저장
     * @param compRptVO
     * @return
     */
    public int insertCompRpt(CompRptVO compRptVO){
        return insert("mr.comp.insertCompRpt", compRptVO);
    }

    /**
     * MR완료보고 수정
     * @param compRptVO
     * @return
     */
    public int updateCompRpt(CompRptVO compRptVO){
        return update("mr.comp.updateCompRpt", compRptVO);
    }

    /**
     * MR마스터 수정
     * @param compRptVO
     * @return
     */
    public int updateMrReqMst(CompRptVO compRptVO){
        return update("mr.comp.updateMrReqMst", compRptVO);
    }

    /**
     * MR완료시 담당자 기한수정
     * @param compRptVO
     * @return
     */
    public int updateCompChrgrEffEnd(CompRptVO compRptVO){
        return update("mr.comp.updateCompChrgrEffEnd", compRptVO);
    }

    /**
     * MR완료 반송
     * @param compRptVO
     * @return
     */
    public int updateMrReqRet(CompRptVO compRptVO){
        return update("mr.comp.updateCompRptRet", compRptVO);
    }

    /**
     * CAPEX 완료여부 업데이트
     * @param compRptVO
     * @return
     */
    public int updateCompRptCapex(CompRptVO compRptVO){
        return update("mr.comp.updateCompRptCapex", compRptVO);
    }
    
    /**
     * SHE 인테페이스 insertCompRpt 
     * @param compRptVO
     * @return
     */
    public int insertCompRptShe(CompRptVO compRptVO){
        return insert("mr.comp.insertCompRptShe", compRptVO);
    }
}
