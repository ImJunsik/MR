package com.mr.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.mr.common.domain.CostCenterVO;
import com.mr.common.domain.EmpInfoVo;
import com.mr.common.repository.EmpAndTeamDAO;
import com.mr.common.service.EmpAndTeamService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.repository.MrStepRepository;

/**
 * 사원정보 팀 서비스 구현
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * </pre>
 */
@Service
public class EmpAndTeamServiceImpl extends BaseService implements EmpAndTeamService{

    @Autowired
    EmpAndTeamDAO empAndTeamDAO;

    @Autowired
    MrStepRepository mrStepRepository;

    @Override
    public List<EmpInfoVo> empSearch(EmpInfoVo empInfoVo) {
        return empAndTeamDAO.empSearch(empInfoVo);
    }


    @Override
    public List<EmpInfoVo> teamSearch(EmpInfoVo empInfoVo) {
        return empAndTeamDAO.teamSearch(empInfoVo);
    }


    @Override
    public int jobEmpChange(String chrgEmpNo, Integer mrReqNo) {
        int mrReqProcStepDetNo = 0;
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mrReqNo);

        /**
         * appInfoList : 현재 직무검토자  리스트
         *
         * 작성이 활성화 되어 있는 사용자는 로그인 사용자 이므로 로그인사용자의 DEP_NO 셋팅
         * 여러분야를 한번에 등록하는 사용자가 없으므로 로그인 사용자만 구분해 내고 FOR문을 BREAK 시킨다.
         */
        for(ChrgrChgHist appChrgrChgHist : appInfoList) {
            if(chrgEmpNo.equals(appChrgrChgHist.getChrgEmpNo())) {
                mrReqProcStepDetNo = appChrgrChgHist.getMrReqProcStepDetNo();
                break;
            }
        }
        return mrReqProcStepDetNo;
    }


    @Override
    public ChrgrChgHist getChrgrChgHist(int mrReqProcStepDetNo) {
        return mrStepRepository.selectChrgrChgHist(mrReqProcStepDetNo);
    }


    @Override
    public void jobEmpChangeSave(ChrgrChgHist chrgrChgHist) {
        //mrStepRepository.updateChrgrChgHist(chrgrChgHist);
        mrStepRepository.updateChrgrChgHist20(chrgrChgHist);
    }


    @Override
    public List<ChrgrChgHist> selectJobLine(int mrReqNo, String mrStepCd) {
        return mrStepRepository.selectJobLine(mrReqNo, mrStepCd);
    }


    @Override
    public List<CostCenterVO> selectCostCenter(String teamText) {
        return empAndTeamDAO.selectCostCenter(teamText);
    }

    @Override
    public List<CostCenterVO> selectCostCenterMap(Map map) {
        return empAndTeamDAO.selectCostCenterMap(map);
    }
}
