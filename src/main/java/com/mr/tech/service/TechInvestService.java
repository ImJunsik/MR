package com.mr.tech.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mr.tech.domain.TechInvestVO;
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
public interface TechInvestService {

    TechInvestVO selectMrTechInvest(int mrReqNo);

    void insertMrTechInvest(TechInvestVO techInvestVO);

    void insertMrTechInvestSetWriter(TechInvestVO techInvestVO);

    void insertMrTechInvestApp(int mrReqNo);

    void insertMrTechInvestReturn(TechInvestVO techInvestVO);

    int insertMrTechInvestAppReq(TechInvestVO techInvestVO);
    
  //yoo 240207 사업수행팀 검색 및 비교
    HashMap<String, String> mrTechInvestSearchTeam(String mrReqNo);
    
    //(201904추가)투자비 산정 반송
	void updateMrTechInvestBack(TechInvestVO techInvestVO);

}
