package com.mr.ivst.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.ivst.domain.IvstCostItemSendVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.ivst.domain.IvstCostRptVO;
import com.mr.ivst.domain.IvstCostSendVO;

/**
 * 투자지출품의서 작성 DB 처리
 *
 * @author 조상욱
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 조상욱 최초 작성
 * </pre>
 */

@Repository
public class IvstDAO  extends BaseSqlMapRepository {


    /**
     * 투자지출품의서 조회
     * @param mrReqNo
     * @return
     */
    public IvstCostRegisterVO selectIvstCost(int mrReqNo){
        return selectOne("mr.ivst.selectIvstCost", mrReqNo);
    }

    /**
     * 투자지출품의보고서 조회
     * @param mrReqNo
     * @return
     */
    public IvstCostRptVO selectIvstCostRpt(int mrReqNo){
        return selectOne("mr.ivst.selectIvstCostRpt", mrReqNo);
    }

    /**
     * 투자지출품의서 저장
     * @param ivstCostRptVO
     * @return
     */
    public int insertIvstCostRpt(IvstCostRptVO ivstCostRptVO){
        return insert("mr.ivst.insertIvstCostRpt", ivstCostRptVO);
    }

    /**
     * 투자구분, 투자목적 임시저장
     * @param ivstCostRegisterVO
     * @return
     */
    public int insertIvstCostRpt_2(IvstCostRegisterVO ivstCostRegisterVO){
        return update("mr.ivst.updateIvstCostRpt_2", ivstCostRegisterVO);
    }

    /**
     * 투자지출품의서 수정
     * @param ivstCostRptVO
     * @return
     */
    public int updateIvstCostRpt(IvstCostRptVO ivstCostRptVO){
        return update("mr.ivst.updateIvstCostRpt", ivstCostRptVO);
    }

    /**
     * 투자지출품의 보고서 SAP 발송
     * @param ivstCostRptVO
     * @return
     */
    public int sendIvstCostRpt(IvstCostRptVO ivstCostRptVO){
        return update("mr.ivst.sendIvstCostRpt", ivstCostRptVO);
    }

    /**
     * 투자지출품의 보고서 SAP 발송(MR마스터 업데이트)
     * @param ivstCostRptVO
     * @return
     */
    public int sendIvstCostRpt2(IvstCostRptVO ivstCostRptVO){
        return update("mr.ivst.sendIvstCostRpt2", ivstCostRptVO);
    }

    /**
     * 투자지출품의 보고서 취소(기각) - MR종료
     * @param ivstCostRptVO
     * @return
     */
    public int updateIvstCostRptEnd(IvstCostRptVO ivstCostRptVO){
        return update("mr.ivst.updateIvstCostRptEnd", ivstCostRptVO);
    }

    /**
     * 투자지출품의서 전송 조회
     * @param mrReqNo
     * @return
     */
    public IvstCostSendVO selectIvstCostSend(int mrReqNo){
        return selectOne("mr.ivst.selectIvstCostSend", mrReqNo);
    }

    /**
     * 투자지출품의서 비용항목 전송 조회
     * @param mrReqNo
     * @return
     */
    public List<IvstCostItemSendVO> selectIvstCostItemSend(int mrReqNo){
        return selectList("mr.ivst.selectIvstCostItemSend", mrReqNo);
    }

    /**
     * 투자지출품의 ZERO CAPEX MR수행 단계로 SKIP처리
     * @param ivstCostRptVO
     * @return
     */
    public int skipIvstCostRpt(IvstCostRptVO ivstCostRptVO){
        return update("mr.ivst.skipIvstCostRpt", ivstCostRptVO);
    }
}
