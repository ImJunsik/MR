package com.mr.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.mr.common.domain.CommCdVO;
import com.mr.common.domain.UnitVo;
import com.mr.common.repository.CodeDAO;
import com.mr.common.service.CodeService;

/**
 * 공통코드 서비스 구현
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * 2017.11.28 wj: 초기투자비 산정 직책과장 조회 추가
 * </pre>
 */
@Service
public class CodeServiceImpl extends BaseService implements CodeService {

    @Autowired
    CodeDAO codeDAO;

    @Override
    public List<CommCdVO> selectCommCd(CommCdVO commCdVO) {

        /**
         * MrCommCd가 GRP_ALL일 경우 전체 그룹코드를 조회하고 아닐경우는
         * MrCommCd가 일치하는 경우만 조회 함.
         */

        List<CommCdVO> commCdVOs = null;
        if(commCdVO.getMrCommCd()!=null && commCdVO.getMrCommCd().equals("GRP_ALL")){
            commCdVOs = codeDAO.selectAllGrpCd();
        }
        //wj 직책과장 select box 추가
        else if(commCdVO.getMrCommGrpCd().equals("chrgDetNo")){
        	System.out.println("wj:: selectCommCd.commCdVO.getMrCommCd()   :: " + commCdVO.getMrCommCd());
        	System.out.println("wj:: selectCommCd.commCdVO.getParentCd()   :: " + commCdVO.getParentCd());
        	System.out.println("wj:: selectCommCd.commCdVO.getCodes()   :: " + commCdVO.getCodes());
        	
        	commCdVOs = codeDAO.techInvestEmp(commCdVO);
        }
    	else {
    		commCdVOs = codeDAO.selectCommCd(commCdVO);
        }
        return commCdVOs;
    }

    @Override
    public UnitVo selectPlant(UnitVo unitVo) {
        return codeDAO.selectPlant(unitVo);
    }

    @Override
    public List<CommCdVO> selectAllUnit() {
        return codeDAO.selectAllUnit();
    }

    @Override
    public List<CommCdVO> selectEquip(String mrProcNo) {
        return codeDAO.selectEquip(mrProcNo);
    }

    @Override
    public List<UnitVo> unitSearch(UnitVo unitVo) {
        return codeDAO.unitSearch(unitVo);
    }

    @Override
    public List<UnitVo> equipSearch(UnitVo unitVo) {
        return codeDAO.equipSearch(unitVo);
    }

	@Override
	public List<UnitVo> selectPlantAndUnit(UnitVo unitVo) {
		// TODO Auto-generated method stub
		return codeDAO.selectPlantAndUnit(unitVo);
	}
}
