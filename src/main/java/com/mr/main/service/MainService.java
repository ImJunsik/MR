package com.mr.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.common.domain.EmpInfoVo;
import com.mr.main.domain.MrListVO;
import com.mr.main.domain.MrSummary;
import com.mr.main.domain.MrSummaryEx;
/**
 * 메인화면의 서비스로직
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * 2014.07.28 박성룡 임시변경 종합추가
 * </pre>
 */
@Service
public interface MainService{

    /**
     * 메인화면 상단 종합 //yoo 241104 Mybatis XML 수정  - 기존 메소드는 한자리수  Z01x ~ Z09x까지만 지원 하므로 새로 생성 함 Z025추가
     * @param empInfoVo
     * @return SummaryList
     */
    List<MrSummary> selectMrSummary(EmpInfoVo empInfoVo);
    

    /**
     * 결재라인에 포함된 MR Summary
     * @param empInfoVo
     * @return
     */
    List<MrSummary> selectAppSummary(EmpInfoVo empInfoVo);

    /**
     * 임시변경 종합
     * @param empInfoVo
     * @return
     */
    MrSummary selectTempMrSummary(EmpInfoVo empInfoVo);

    /**
     * 로그인 사용자의 MR 상세리스트
     * @param empInfoVo
     * @return MR Summary
     */
    List<MrListVO> selectMrList(MrListVO mrListVO);

    /**
     * 로그인 사용자가 포함된 MR 상세리스트
     * @param mrListVO
     * @return
     */
    List<MrListVO> selectAppMrList(MrListVO mrListVO);

	int getTechInvestCnt(EmpInfoVo empInfoVo);
}
