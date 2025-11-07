package com.mr.common.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.common.domain.MrRvRstVO;
/**
 * 검토 DAO
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 박성룡 신규작성
 * </pre>
 */
@Repository
public class MrRvRstDAO  extends BaseSqlMapRepository {

    public void insertMrRvRst(MrRvRstVO mrRvRstVO) {
        insert("mr.rvRst.insertMrRvRst",mrRvRstVO);
    }
    
    //yoo 240902 추가
    public int selectMrRvRstItemCount(MrRvRstVO mrRvRstVO) {
    	return selectInt("mr.rvRst.selectMrRvRstItemCount",mrRvRstVO);
    }

    //yoo 240830 추가
    public int copyMrRvRst(MrRvRstVO mrRvRstVO) {
        return insert("mr.rvRst.copyMrRvRst",mrRvRstVO);
    }

    public void updateTechMrRvRst(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
    	update("mr.rvRst.updateTechMrRvRst",mrRvRstVO);
	}
    
    public MrRvRstVO selectMrRvRst(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectMrRvRst",mrRvRstVO);
    }

    public MrRvRstVO selectMrRvRstRisk(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectMrRvRstRisk",mrRvRstVO);
    }

    public List<MrRvRstVO> selectTechMrRvRst(int mrReqNo) {
        return selectList("mr.rvRst.selectTechMrRvRst",mrReqNo);
    }

    public int updateMrRvRst(MrRvRstVO mrRvRstVO) {
        return update("mr.rvRst.updateMrRvRst",mrRvRstVO);
    }

    public void updateMrRvRstDelYnDet(MrRvRstVO mrRvRstVO) {
        update("mr.rvRst.updateMrRvRstDelYnDet",mrRvRstVO);
    }

    public void updateMrRvRstDelYnItemCd(MrRvRstVO mrRvRstVO){
        update("mr.rvRst.updateMrRvRstDelYnItemCd",mrRvRstVO);
    }

    public String selectRvRstIsCheck(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstIsCheck",mrRvRstVO);
    }

    public int selectRvRstCount(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstCount",mrRvRstVO);
    }

    public int selectRvRstCountDet(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstCountDet",mrRvRstVO);
    }

    public String selectRvRstIsAppCheck(int mrReqNo, String itemCd) {
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setItemCd(itemCd);
        return selectOne("mr.rvRst.selectRvRstIsAppCheck",mrRvRstVO);
    }

    public void updateMrRvRstApp(int mrReqNo, String itemCd) {
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setItemCd(itemCd);
        update("mr.rvRst.updateMrRvRstApp",mrRvRstVO);
    }

    //MR수행 //////////////////////////////////////////////////////////////////////

    public void insertMrRvRstExe(MrRvRstVO mrRvRstVO) {
    	mrRvRstVO.setItemCd("EXE_HAZOP");
    	
        insert("mr.rvRst.insertMrRvRstExe",mrRvRstVO);
    }
    
    //yoo 240902 추가
    public int selectMrRvRstItemCountExe(MrRvRstVO mrRvRstVO) {
    	return selectInt("mr.rvRst.selectMrRvRstItemCountExe",mrRvRstVO);
    }

    //yoo 240830 추가
    /*public int copyMrRvRstExe(MrRvRstVO mrRvRstVO) {
        return insert("mr.rvRst.copyMrRvRstExe",mrRvRstVO);
    }*/

    public void updateTechMrRvRstExe(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
    	update("mr.rvRst.updateTechMrRvRstExe",mrRvRstVO);
	}
    
    public MrRvRstVO selectMrRvRstExe(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectMrRvRstExe",mrRvRstVO);
    }

    public MrRvRstVO selectMrRvRstRiskExe(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectMrRvRstRiskExe",mrRvRstVO);
    }

    public List<MrRvRstVO> selectTechMrRvRstExe(int mrReqNo) {
        return selectList("mr.rvRst.selectTechMrRvRstExe",mrReqNo);
    }

    public int updateMrRvRstExe(MrRvRstVO mrRvRstVO) {
        return update("mr.rvRst.updateMrRvRstExe",mrRvRstVO);
    }

    public void updateMrRvRstDelYnDetExe(MrRvRstVO mrRvRstVO) {
        update("mr.rvRst.updateMrRvRstDelYnDetExe",mrRvRstVO);
    }

    public void updateMrRvRstDelYnItemCdExe(MrRvRstVO mrRvRstVO){
        update("mr.rvRst.updateMrRvRstDelYnItemCdExe",mrRvRstVO);
    }

    public String selectRvRstIsCheckExe(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstIsCheckExe",mrRvRstVO);
    }

    public int selectRvRstCountExe(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstCountExe",mrRvRstVO);
    }

    public int selectRvRstCountDetExe(MrRvRstVO mrRvRstVO) {
        return selectOne("mr.rvRst.selectRvRstCountDetExe",mrRvRstVO);
    }

    public String selectRvRstIsAppCheckExe(int mrReqNo, String itemCd) {
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setItemCd(itemCd);
        return selectOne("mr.rvRst.selectRvRstIsAppCheckExe",mrRvRstVO);
    }

    public void updateMrRvRstAppExe(int mrReqNo, String itemCd) {
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setItemCd(itemCd);
        update("mr.rvRst.updateMrRvRstAppExe",mrRvRstVO);
    }
}
