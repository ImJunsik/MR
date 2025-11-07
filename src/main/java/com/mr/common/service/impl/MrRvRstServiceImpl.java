package com.mr.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.repository.MrRvRstDAO;
import com.mr.common.service.MrRvRstService;
/**
 * 검토 서비스
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
public class MrRvRstServiceImpl extends BaseService implements MrRvRstService{

    @Autowired
    MrRvRstDAO mrRvRstDAO;

    @Override
    public void insertMrRvRst(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.insertMrRvRst(mrRvRstVO);
    }

    @Override
    public int updateMrRvRst(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.updateMrRvRst(mrRvRstVO);
    }


    @Override
    public void updateMrRvRstDelYnDet(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.updateMrRvRstDelYnDet(mrRvRstVO);
    }

    @Override
    public void updateMrRvRstDelYnItemCd(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.updateMrRvRstDelYnItemCd(mrRvRstVO);
    }

    @Override
    public MrRvRstVO selectMrRvRst(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectMrRvRst(mrRvRstVO);
    }


    @Override
    public MrRvRstVO selectMrRvRstRisk(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectMrRvRstRisk(mrRvRstVO);
    }

    @Override
    public boolean selectRvRstIsCheck(MrRvRstVO mrRvRstVO) {
        boolean isCheck = false;
        String strCheck  = mrRvRstDAO.selectRvRstIsCheck(mrRvRstVO);
        if(strCheck!=null && strCheck.equals("TRUE")){
            isCheck = true;
        }
        return isCheck;
    }

    @Override
    public int selectRvRstCount(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectRvRstCount(mrRvRstVO);
    }

    @Override
    public int selectRvRstCountDet(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectRvRstCountDet(mrRvRstVO);
    }

    @Override
    public void updateMrRvRstApp(int mrReqNo, String itemCd) {
        mrRvRstDAO.updateMrRvRstApp(mrReqNo, itemCd);

    }

    @Override
    public boolean selectRvRstIsAppCheck(int mrReqNo, String itemCd) {
        boolean isCheck = false;
        String strCheck  = mrRvRstDAO.selectRvRstIsAppCheck(mrReqNo, itemCd);
        if(strCheck!=null && strCheck.equals("TRUE")){
            isCheck = true;
        }
        return isCheck;
    }

	@Override
	public void updateTechMrRvRst(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
		mrRvRstDAO.updateTechMrRvRst(mrRvRstVO);
	}

	@Override
	public int copyMrRvRst(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
		return mrRvRstDAO.copyMrRvRst(mrRvRstVO);
	}

	@Override
	public int selectMrRvRstItemCount(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
		return mrRvRstDAO.selectMrRvRstItemCount(mrRvRstVO);
	}

//MR수행///////////////////////////////////////////////////////////////////////////////////////////////
	
    @Override
    public void insertMrRvRstExe(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.insertMrRvRstExe(mrRvRstVO);
    }

    @Override
    public int updateMrRvRstExe(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.updateMrRvRstExe(mrRvRstVO);
    }


    @Override
    public void updateMrRvRstDelYnDetExe(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.updateMrRvRstDelYnDetExe(mrRvRstVO);
    }

    @Override
    public void updateMrRvRstDelYnItemCdExe(MrRvRstVO mrRvRstVO) {
        mrRvRstDAO.updateMrRvRstDelYnItemCdExe(mrRvRstVO);
    }

    @Override
    public MrRvRstVO selectMrRvRstExe(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectMrRvRstExe(mrRvRstVO);
    }


    @Override
    public MrRvRstVO selectMrRvRstRiskExe(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectMrRvRstRiskExe(mrRvRstVO);
    }

    @Override
    public boolean selectRvRstIsCheckExe(MrRvRstVO mrRvRstVO) {
        boolean isCheck = false;
        String strCheck  = mrRvRstDAO.selectRvRstIsCheckExe(mrRvRstVO);
        if(strCheck!=null && strCheck.equals("TRUE")){
            isCheck = true;
        }
        return isCheck;
    }

    @Override
    public int selectRvRstCountExe(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectRvRstCountExe(mrRvRstVO);
    }

    @Override
    public int selectRvRstCountDetExe(MrRvRstVO mrRvRstVO) {
        return mrRvRstDAO.selectRvRstCountDetExe(mrRvRstVO);
    }

    @Override
    public void updateMrRvRstAppExe(int mrReqNo, String itemCd) {
        mrRvRstDAO.updateMrRvRstAppExe(mrReqNo, itemCd);

    }

    @Override
    public boolean selectRvRstIsAppCheckExe(int mrReqNo, String itemCd) {
        boolean isCheck = false;
        String strCheck  = mrRvRstDAO.selectRvRstIsAppCheckExe(mrReqNo, itemCd);
        if(strCheck!=null && strCheck.equals("TRUE")){
            isCheck = true;
        }
        return isCheck;
    }

	@Override
	public void updateTechMrRvRstExe(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
		mrRvRstDAO.updateTechMrRvRstExe(mrRvRstVO);
	}

	@Override
	public int selectMrRvRstItemCountExe(MrRvRstVO mrRvRstVO) {
		// TODO Auto-generated method stub
		return mrRvRstDAO.selectMrRvRstItemCountExe(mrRvRstVO);
	}

}
