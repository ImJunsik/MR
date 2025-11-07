package com.mr.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mr.common.domain.CostCenterVO;
import com.mr.common.domain.EmpInfoVo;
import com.mr.step.domain.ChrgrChgHist;
/**
 * 사원, 팀관련 서비스
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.22 박성룡 최초 작성
 * </pre>
 */
@Service
public interface EmpAndTeamService{

    /**
     * 사원정보 검색 서비스
     * @param empInfoVo 사원명 혹은 팀명
     * @return 사원정보 리스트
     */
    List<EmpInfoVo> empSearch(EmpInfoVo empInfoVo);

    /**
     * 팀정보 검색 서비스
     * @param empInfoVo 팀명
     * @return 팀리스트
     */
    List<EmpInfoVo> teamSearch(EmpInfoVo empInfoVo);

    /**
     * 직무검토 담당자 변경
     * @param empNo
     * @return
     */
    int jobEmpChange(String chrgEmpNo, Integer mrReqNo);

    /**
     * 처리담당자 조회
     * @param mrReqProcStepDetNo
     * @return
     */
    ChrgrChgHist getChrgrChgHist(int mrReqProcStepDetNo);

    /**
     * 처리담당자 조회 (리스트 중복체크용)
     * @param mrReqProcStepDetNo
     * @return
     */
    List<ChrgrChgHist> selectJobLine(int mrReqNo, String mrStepCd);

    /**
     * 처리담당자 변경
     * @param chrgrChgHist
     */
    void jobEmpChangeSave(ChrgrChgHist chrgrChgHist);


    /**
     * 회계부서코드 조회
     * @param
     * @return 코드리스트
     */
    List<CostCenterVO> selectCostCenter(String teamText);

    List<CostCenterVO> selectCostCenterMap(Map map);
}
