package com.mr.ivst.service;

//import java.util.HashMap;  //sap interface test 용도

import org.springframework.stereotype.Service;

import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.ivst.domain.IvstCostRptVO;

/**
 * 투자지출 품의서 작성 서비스
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 조상욱 최초 작성
 * </pre>
 */
@Service
public interface IvstService{

    /**
     * 투자지출 품의서 조회
     * @param mrReqNo
     * @return
     */
    IvstCostRegisterVO selectIvstCost(int mrReqNo);

    /**
     * 투자지출 품의보고서 조회
     * @param mrReqNo
     * @return
     */
    IvstCostRptVO selectIvstCostRpt(int mrReqNo);

    /**
     * 투자지출 품의서 작성 임시저장
     * @param ivstCostRptVO
     */
    void insertIvstCostRpt(IvstCostRptVO ivstCostRptVO);
    
    
    /**
     * 투자구분, 투자목적 저장 
     * @param ivstCostRptVO
     * wj추가
     * 2018/08/31
     */
    void insertIvstCostRpt_2(IvstCostRegisterVO ivstCostRegisterVO);   


    /**
     * 투자지출 품의서 작성 임시저장(수정)
     * @param ivstCostRptVO
     */
    void updateIvstCostRpt(IvstCostRptVO ivstCostRptVO);

    /**
     * 투자지출 품의보고서 SAP 발송
     * @param ivstCostRptVO
     * yoo 241224
     */
    void sendIvstCostRpt(IvstCostRptVO ivstCostRptVO);
    //HashMap sendIvstCostRpt(IvstCostRptVO ivstCostRptVO);   //sap interface test 용도

    /**
     * 투자지출 품의 MR수행으로 SKIP 처리 (CAPEX OPEN없이 진행하는 MR)
     * @param ivstCostRptVO
     */
    void skipIvstCostRpt(IvstCostRptVO ivstCostRptVO);

    /**
     * 투자지출 품의보고서 취소(MR종료)
     * @param mrReqNo
     * @return
     */
    void endIvstCostRpt(IvstCostRptVO ivstCostRptVO);

    /**
     * 투자지출 품의보고서 EAI 송신
     * yoo 241224 품의 번호와 함께 에러 메시지도 리턴하도록 변경 함
     * @param ivstCostRptVO
     */
    String eaiConIvstCostRpt(IvstCostRptVO ivstCostRptVO);
    //HashMap eaiConIvstCostRpt(IvstCostRptVO ivstCostRptVO);   //sap interface test 용도    

}
