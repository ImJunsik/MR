package com.mr.step.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.mr.common.domain.MrRvRstVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.mrrq.domain.MrAlterVO;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrReqProcStepDetVO;
import com.mr.step.domain.MrReqProcStepVO;


/**
 * 요청서 작성 DB 처리
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.26 박성룡 최초 작성
 * </pre>
 */

@Repository
public class MrStepRepository extends BaseSqlMapRepository {

	private final Logger logger = Logger.getLogger(this.getClass()); 
    /**
     * 요청서 단계번호 조회
     * @param chrgrChgHist
     * @return
     */
    public int getMrReqProcStepNo(ChrgrChgHist chrgrChgHist) {
        return selectOne("mr.step.getMrReqProcStepNo", chrgrChgHist);
    }

    /**
     * 요청서 전체단계 조회
     * @param mrReqNo
     * @return
     */
    public List<MrReqProcStepVO> selectAllMrReqStep(int mrReqNo) {
        return selectList("mr.step.selectAllMrReqStep", mrReqNo);
    }
    
    /**
     * 요청서 전체단계 조회
     * @param mrReqNo
     * @return
     */
    public String selectMrInvstCost(int mrReqNo) {
        return selectOne("mr.step.selectMrInvstCost", mrReqNo);
    }

    /**
     * MR 단계 조회
     * @param mrReqNo
     * @return
     */
    public MrReqProcStepVO selectOneMrReqStep(int mrReqNo,String mrStepCd) {
    	Map map = new HashMap();
    	map.put("mrReqNo", mrReqNo);
    	map.put("mrStepCd", mrStepCd);
        return selectOne("mr.step.selectOneMrReqStep", map);
    }
    
    /**
     * MR 사업명 변경
     * @param chrgrChgHist
     * @return
     */
    public int updateBzNameChange(JobsReviewVO jobsReviewVO) {
        return update("mr.step.updateBzNameChange", jobsReviewVO);
    }
    
    /**
     * MR 삭제
     * @param MR_SET_DELETE_FLAG_MODIFY
     * @return
     */
    public boolean updateMrDelete(IvstCostRegisterVO ivstCostRegisterVO) {
        //return update("mr.step.updateMrDelete", jobsReviewVO);
    	boolean result = false;
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("V_MR_NO", ivstCostRegisterVO.getMrNo());
    	map.put("V_MR_TMP_NO", ivstCostRegisterVO.getMrTmpNo());
    	map.put("V_RETURN_NO", 0);
    	
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    			System.out.println( strKey +":"+ strValue.toString() );
    	}

    	selectOne("mr.step.mrSetDeleteFlagModify", map);
    	
    	System.out.println( "CALL mrSetDeleteFlagModify!!!" );
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    		{
    			System.out.println( strKey +":"+ strValue.toString());
    			if(strKey.equals("V_RETURN_NO"))
    			{
    				
    				if(!strValue.toString().equals("0"))
    					result = true;
    			}
    		}
    	}
    	
    	return result;
    }
    
    /**
     * MR 사업 취소
     * @param MR_CANCEL_MODIFY
     * @return
     */
    public boolean updateBzCancel(IvstCostRegisterVO ivstCostRegisterVO) {
    	boolean result = false;
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("V_MR_NO", ivstCostRegisterVO.getMrNo());
    	map.put("V_MR_TMP_NO", ivstCostRegisterVO.getMrTmpNo());
    	map.put("V_RETURN_NO", 0);
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    			System.out.println( strKey +":"+ strValue.toString() );
    	}
    	
    	selectOne("mr.step.mrCancelModify", map);
    	System.out.println( "CALL mrCancelModify!!!" );
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    		{
    			System.out.println( strKey +":"+ strValue.toString());
    			if(strKey.equals("V_RETURN_NO"))
    			{
    				
    				if(!strValue.toString().equals("0"))
    					result = true;
    			}
    		}
    	}
    	
    	return result;
    }
    
    /**
     * CAPEX 적용
     * @param MR_INVEST_RPT_APPROVE
     * @return
     */
    public boolean updateCapexApply(MrAlterVO mrAlterVO) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("P_MR_NO", mrAlterVO.getMrNo());
    	
    	map.put("P_CAPEX_NO", mrAlterVO.getCapexNo());
    	map.put("P_ZMANUM", mrAlterVO.getInvstCostNo());
    	map.put("P_COST_TOT", mrAlterVO.getSapCostTot());
    	map.put("P_RTN_CODE", mrAlterVO.getRtnCode());
    	map.put("P_RTN_MSG", mrAlterVO.getRtnMsg());
    	
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    		{
    			System.out.println( strKey +":"+ strValue.toString());
    			if(strKey.equals("P_RTN_CODE"))
    			{
    				
    				//if(!strValue.toString().equals("0"))
    					//result = true;
    			}else if(strKey.equals("P_RTN_MSG"))
    			{
    				
    				//if(!strValue.toString().equals("0"))
    					//result = true;
    			}
    		}
    	}
    	
    	
    	selectOne("mr.step.mrInvestRptApprove", map);
    	System.out.println( "CALL mrInvestRptApprove!!!" );
    	
    	for( String strKey : map.keySet() ){
    		Object strValue = map.get(strKey);
    		if(strValue == null)
    			System.out.println( strKey +": NULL" );
    		else
    		{
    			System.out.println( strKey +":"+ strValue.toString());
    			if(strKey.equals("P_RTN_CODE"))
    			{
    				
    				//if(!strValue.toString().equals("0"))
    					//result = true;
    			}else if(strKey.equals("P_RTN_MSG"))
    			{
    				
    				//if(!strValue.toString().equals("0"))
    					//result = true;
    			}
    		}
    	}
    	
    	return false;
    }

    
    
    /**
     * 결재라인 업데이트
     * @param chrgrChgHist
     * @return
     */
    public int updateChrgrChgHist(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("updateChrgrChgHist - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return insert("mr.step.updateChrgrChgHist", chrgrChgHist);
    }
    
    /**
     * 결재라인 업데이트
     * @param chrgrChgHist
     * @return
     */
    public int updateChrgrChgHist20(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("updateChrgrChgHist20 - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return insert("mr.step.updateChrgrChgHist20", chrgrChgHist);
    }
    
    /**
     * 결재라인 업데이트
     * @param chrgrChgHist
     * @return
     */
    public int updateChrgrChgHist90(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("updateChrgrChgHist90 - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return insert("mr.step.updateChrgrChgHist90", chrgrChgHist);
    }
    
    /**
     * 옛날 정보 삭제
     * @param chrgrChgHist
     * @return
     */
    public int deleteOldDet(ChrgrChgHist chrgrChgHist) {
    	return update("mr.step.deleteOldDet", chrgrChgHist);
    }
    
    public int deleteOldHist(ChrgrChgHist chrgrChgHist) {
    	return update("mr.step.deleteOldHist", chrgrChgHist);
    }
    
    /**
     * 결재라인 생성
     * @param chrgrChgHist
     * @return
     */
    public int insertChrgrChgHist(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("insertChrgrChgHist - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return insert("mr.step.insertChrgrChgHist", chrgrChgHist);
    }

    public int insertMrReqProcStepDetDev(MrReqProcStepDetVO mrReqProcStepDetVO) {
        return insert("mr.step.insertMrReqProcStepDetDev", mrReqProcStepDetVO);
    }
    
    public List<MrReqProcStepVO> checkInsertMrStep(ChrgrChgHist chrgrChgHist)
    {
    	return selectList("mr.step.checkInsertMrStep", chrgrChgHist);
    }
    /**
     * Step 생성
     * @param mrReqProcStepVO
     * @return
     */
    public int insertProcStep(MrReqProcStepVO mrReqProcStepVO) {
        return insert("mr.step.insertProcStep", mrReqProcStepVO);
    }

    /**
     * 상세 Step 생성
     * @param mrReqProcStepDetVO
     * @return
     */
    public int insertMrReqProcStepDet(MrReqProcStepDetVO mrReqProcStepDetVO) {
        return insert("mr.step.insertMrReqProcStepDet", mrReqProcStepDetVO);
    }

    
    /**
     * 상세 Step Update
     * @param mrReqProcStepDetVO
     * @return
     */
    public int updateMrReqProcStepDet(MrReqProcStepDetVO mrReqProcStepDetVO) {
    	String str = String.format("updateMrReqProcStepDet - %d:%d", mrReqProcStepDetVO.getMrReqNo(), mrReqProcStepDetVO.getMrReqProcStepDetNo());
    	logger.info(str);
        return insert("mr.step.updateMrReqProcStepDet", mrReqProcStepDetVO);
    }

    
    /**
     * 상세 Step 생성 최근 Step으로 셋팅
     * @param mrReqProcStepDetVO
     * @return
     */
    public int insertMrReqProcStepDetForStepLast(MrReqProcStepDetVO mrReqProcStepDetVO) {
    	String str = String.format("insertMrReqProcStepDetForStepLast - %d:%d", mrReqProcStepDetVO.getMrReqNo(), mrReqProcStepDetVO.getMrReqProcStepDetNo());
    	logger.info(str);
        return insert("mr.step.insertMrReqProcStepDetForStepLast", mrReqProcStepDetVO);
    }

    /**
     * 결재라인 논리삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateAppLineDelYn(ChrgrChgHist appLineInfo){
    	String str = String.format("updateAppLineDelYn - %d:%s", appLineInfo.getMrReqNo(), appLineInfo.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateAppLineDelYn", appLineInfo);
    }

    
    

    /**
     * 결재라인 유효기간 종료
     * @param mRrqRegisterVO
     * @return
     */
    public int updateAppLineEffEnd(ChrgrChgHist appInfo){
    	String str = String.format("updateAppLineEffEnd - %d:%s", appInfo.getMrReqNo(), appInfo.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateAppLineEffEnd", appInfo);
    }

    /**
     * 결재라인 전체 유효기간 종료(반려, 기각시 사용)
     * @param mRrqRegisterVO
     * @return
     */
    public int updateAppLineAllEffEnd(ChrgrChgHist appInfo){
    	String str = String.format("updateAppLineAllEffEnd - %d:%s", appInfo.getMrReqNo(), appInfo.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateAppLineAllEffEnd", appInfo);
    }


    /**
     * 상세 Step 논리삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateMrReqProcStepDetDelYn(ChrgrChgHist chrgrChgHist){
    	String str = String.format("updateMrReqProcStepDetDelYn - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateMrReqProcStepDetDelYn", chrgrChgHist);
    }

    
    /**
     * 상세 중복 논리삭제 yoo 240910 
     * @param mRrqRegisterVO
     * @return
     */
    public String updateDetHistDelDuplication(Map map){
    	//String str = String.format("updateDetHistDelDuplication - %d:%s", map.get("mrReqNo"));
    	//logger.info(str);
    	update("mr.step.updateDetHistDelDuplication", map);
        update("mr.step.updateDetHistDelDuplication2", map);
        return "";
    }
    
    /**
     * Step 논리삭제
     * @param mRrqRegisterVO
     * @return
     */
    public int updateProcStepDelYn(ChrgrChgHist chrgrChgHist){
    	String str = String.format("updateProcStepDelYn - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateProcStepDelYn", chrgrChgHist);
    }
    
    /**
     * 반송기능을 위한 Step 논리삭제(201904추가) 
     * @param mRrqRegisterVO
     * @return
     */
    public int updateProcStepDelYnBack(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updateProcStepDelYnBack", chrgrChgHist);
    }
    
    


    /**
     * Step 유효기간 종료
     * @param mRrqRegisterVO
     * @return
     */
    public int updateProcStepEffEnd(ChrgrChgHist chrgrChgHist){
    	//String str = String.format("updateProcStepEffEnd - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	//logger.info(str);
        return update("mr.step.updateProcStepEffEnd", chrgrChgHist);
    }

    /**
     * 현재 결재자 정보
     * @param mrReqNo
     * @return
     */
    public ChrgrChgHist selectAppStepEmp(int mrReqNo, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppStepEmp", chrgrChgHist);
    }
    
    /**
     * yoo 20240104
     * @param mrReqNo
     * @return
     */
    public ChrgrChgHist selectAppStepEmp(int mrReqNo, String chrgrClCd, String mrStepCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        chrgrChgHist.setMrStepCd(mrStepCd);
        return selectOne("mr.step.selectAppStepEmp", chrgrChgHist);
    }
    
    /**
     * 현재 결재자 정보 yoo 230811 위험성 검토용 (미사용)
     * @param mrReqNo
     * @return
     */
    public ChrgrChgHist selectAppStepEmpR0(int mrReqNo, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppStepEmpR0", chrgrChgHist);
    }

    public ChrgrChgHist selectAppStepEmp60(int mrReqNo, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppStepEmp60", chrgrChgHist);
    }
    
    public ChrgrChgHist selectAppStepEmpDev(int mrReqNo, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppStepEmpDev", chrgrChgHist);
    }
    
    /**
     * 특정단계 현재 결재자 정보(여러단계를 동시에 진행할 경우 사용
     * @param mrReqNo
     * @param chrgrClCd
     * @return
     */
    public ChrgrChgHist selectAppIsolationStepEmp(int mrReqNo, String mrStepCd, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppIsolationStepEmp", chrgrChgHist);
    }



    /**
     * 특정단계 결재자 여러명이 동시에 처리시 결재자정보 조회
     * @param mrReqNo
     * @param chrgrClCd
     * @param mrStepCd
     * @return
     */
    public List<ChrgrChgHist> selectIsolationAppStepEmpMulti(int mrReqNo, String mrStepCd, String chrgrClCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectList("mr.step.selectIsolationAppStepEmpMulti", chrgrChgHist);
    }



    /**
     * 특정단계, 특정결재자 조회
     * @param mrReqNo
     * @param mrStepCd
     * @param chrgrClCd
     * @return
     */
    public ChrgrChgHist selectAppLine(int mrReqNo, String mrStepCd, String chrgrClCd ) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        return selectOne("mr.step.selectAppLine", chrgrChgHist);
    }

    
    public ChrgrChgHist selectAppLine(int mrReqNo, String mrStepCd, String chrgrClCd , String nextProcStCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        chrgrChgHist.setProcStCd(nextProcStCd);
        return selectOne("mr.step.selectAppLine", chrgrChgHist);
    }
    
    public ChrgrChgHist selectAppLine2(int mrReqNo, String mrStepCd, String chrgrClCd , String nextProcStCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        chrgrChgHist.setChrgrClCd(chrgrClCd);
        chrgrChgHist.setProcStCd(nextProcStCd);
        return selectOne("mr.step.selectAppLine2", chrgrChgHist);
    }
    
    /**
     * Step 변경
     * @param mRrqRegisterVO
     * @return
     */
    public int updateProcStep(MrReqProcStepVO mrReqProcStepVO){
    	String str = String.format("updateProcStep - %d:%s", mrReqProcStepVO.getMrReqNo(), mrReqProcStepVO.getProcStCd());
    	logger.info(str);
        return update("mr.step.updateProcStep", mrReqProcStepVO);
    }

    /**
     * 특정 Step 활성화
     * @param mRrqRegisterVO
     * @return
     */
    public int updateProcStepActive(MrReqProcStepVO mrReqProcStepVO){
    	String str = String.format("updateProcStepActive - %d:%s", mrReqProcStepVO.getMrReqNo(), mrReqProcStepVO.getProcStCd());
    	logger.info(str);
        return update("mr.step.updateProcStepActive", mrReqProcStepVO);
    }



    /**
     * 특정 직무검토자 논리삭제
     * @param chrgrChgHist
     * @return
     */
    public int updateTechEmpDelYn(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updateTechEmpDelYn", chrgrChgHist);
    }

    /**
     * 결재자 여러명이 동시에 처리시 결재자정보 조회
     * @param mrReqNo
     * @return
     */
    public List<ChrgrChgHist> selectAppStepEmpMulti(int mrReqNo) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        return selectList("mr.step.selectAppStepEmpMulti", chrgrChgHist);
    }

    /**
     * 결재자 승인자 팀 조회 yoo 240207
     * @param mrReqNo
     * @return
     */
    public List<ChrgrChgHist> selectInvestSearchTeam(int mrReqNo) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        return selectList("mr.step.selectInvestSearchTeam", chrgrChgHist);
    }

    /**
     * MR에서 처리가 완료된 단계의 담당자 정보
     * @param mrReqNo
     * @return
     */
    public List<ChrgrChgHist> selectMrAllAppLine(int mrReqNo) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        return selectList("mr.step.selectMrAllAppLine", chrgrChgHist);
    }

    /**
     * PORC 담당자 논리 삭제
     * @param chrgrChgHist
     * @return
     */
    public int updatePorcEmpDelYn(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updatePorcEmpDelYn", chrgrChgHist);
    }

    /**
     * PORC 담당자 논리 상세 단계 논리삭제.
     * @param chrgrChgHist
     * @return
     */
    public int updatePorcStepDetDelYn(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updatePorcStepDetDelYn", chrgrChgHist);
    }

    /**
     * PORC 담당자 논리 상세 단계 논리삭제.
     * @param chrgrChgHist
     * @return
     */
    public int updatePorcStepDetNoDelYn(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updatePorcStepDetNoDelYn", chrgrChgHist);
    }
    /**
     * 담당자 조회 DET_NO 기준
     * @param mrReqProcStepDetNo
     * @return
     */
    public ChrgrChgHist selectChrgrChgHist(int mrReqProcStepDetNo) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetNo);
        return selectOne("mr.step.selectChrgrChgHist", chrgrChgHist);
    }


    /**
     * 처리담당자 변경 ClCd기준
     * @param chrgrChgHist
     * @return
     */
    public int updateChrgrChgHistClCd(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("updateChrgrChgHistClCd - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateChrgrChgHistClCd", chrgrChgHist);
    }

    /**
     * 처리담당자 변경 MrStepCd기준
     * @param chrgrChgHist
     * @return
     */
    public int updateChrgrChgHistMrStepCd(ChrgrChgHist chrgrChgHist) {
    	String str = String.format("updateChrgrChgHistMrStepCd - %d:%s", chrgrChgHist.getMrReqNo(), chrgrChgHist.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateChrgrChgHistMrStepCd", chrgrChgHist);
    }


    /**
     * 마스터 테이블 STEP 업데이트
     * @param mrReqProcStepVO
     * @return
     */
    public int updateMrReqStep(MrReqProcStepVO mrReqProcStepVO) {
    	String str = String.format("updateMrReqStep - %d:%s", mrReqProcStepVO.getMrReqNo(), mrReqProcStepVO.getMrStepCd());
    	logger.info(str);
        return update("mr.mrReq.updateMrReqStep", mrReqProcStepVO);
    }


    /**
     * 처리담당자 리스트(직무검토 중복체크용)
     * @param mrReqNo
     * @param mrStepCd
     * @return
     */
    public List<ChrgrChgHist> selectJobLine(int mrReqNo, String mrStepCd ) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        return selectList("mr.step.selectJobLine", chrgrChgHist);
    }

    /**
     * 직무검토 보류를 위한 JobRvCd 업데이트
     * @param mrReqNo
     * @param jobRvCd
     * @return
     */
    public int updateMrReqProcStepDetJobRvCd(int mrReqProcStepDetNo, String jobRvCd) {
        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqProcStepDetNo(mrReqProcStepDetNo);
        mrReqProcStepDetVO.setJobRvCd(jobRvCd);
        return update("mr.step.updateMrReqProcStepDetJobRvCd", mrReqProcStepDetVO);
    }

    /**
     * 결재기한변경
     * @param chrgrChgHist
     * @return
     */
    public int updateMrReqProcStepDetProcTrmDt(ChrgrChgHist chrgrChgHist){
        return update("mr.step.updateMrReqProcStepDetProcTrmDt", chrgrChgHist);
    }

    public int updateChrgrChgHistZ02G1(ChrgrChgHist chrgrChgHist) {
        return update("mr.step.updateChrgrChgHistZ02G1", chrgrChgHist);
    }

	public List<ChrgrChgHist> getMrReqTechEmpInfo(int mrReqNo,List<String> mrStepCds,List<String> chrgrClCds) {
		Map map = new HashMap();
		map.put("mrReqNo", mrReqNo);
		map.put("mrStepCds", mrStepCds);
		map.put("chrgrClCds", chrgrClCds);
        return selectList("mr.step.getMrReqTechEmpInfo", map);
	}

	public int updateReqTechEmpDelYn(ChrgrChgHist chrgrChgHist) {
        return update("mr.step.updateReqTechEmpDelYn", chrgrChgHist);
	}


    public int updateMrIfStat01(MrReqProcStepDetVO mrReqProcStepDetVO) {
    	return update("mr.step.updateMrIfStat01", mrReqProcStepDetVO);
    }
    
    /**
     * 초기투자비 산정 - 추가담당자
     * @param mrReqNo
     * @param procStCd
     * @param stepCd
     * @param chrgrClCd
     */
    public void updateDetHistDel(int mrReqNo, String procStCd, String stepCd, String chrgrClCd) {
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setPorcStCd(procStCd);
        mrRvRstVO.setStepCd(stepCd);
        mrRvRstVO.setChrgrClCd(chrgrClCd);        
        
        update("mr.rvRst.updateDetHistDel",mrRvRstVO);
    }
  
    
    /**
     * MR완료 yoo 240411
     * @param mrReqNo
     * @param i
     * @param stepCd
     * @param chrgrClCd
     */
    public void updateDetHistDel90(int mrReqNo, ChrgrChgHist chrgrChgHist, String stepCd, String chrgrClCd) {     
        
        Map map = new HashMap();
		map.put("mrReqNo", mrReqNo);
		map.put("mrReqProcStepDetNo", chrgrChgHist.getMrReqProcStepDetNo());
		map.put("stepCd", stepCd);
		map.put("loginEmpNo", chrgrChgHist.getLoginEmpNo());
		map.put("chrgrClCd", chrgrClCd);
        
        update("mr.step.updateDetHistDel90",map);
    }
    
    //MR수행  ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * MR수행 결재라인 논리삭제
     * @param mRrqRegisterVO
     * @return
     */
    /*public int updateAppLineDelYnExe(ChrgrChgHist appLineInfo){
    	String str = String.format("updateAppLineDelYn - %d:%s", appLineInfo.getMrReqNo(), appLineInfo.getChrgEmpNo());
    	logger.info(str);
        return update("mr.step.updateAppLineDelYnExe", appLineInfo);
    }*/

    
}
