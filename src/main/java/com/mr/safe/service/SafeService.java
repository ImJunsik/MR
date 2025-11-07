package com.mr.safe.service;

import org.springframework.stereotype.Service;

import com.mr.safe.domain.SafeCheckVO;
import com.mr.safe.domain.SafeChrgrChgHistVO;

/**
 * 가동전 안전점검 작성 서비스
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.21 조상욱 최초 작성
 * </pre>
 */
@Service
public interface SafeService{

    /**
     * 가동전 안전점검 조회
     * @param mrReqNo
     * @return
     */
    SafeCheckVO selectSafeCheck(int mrReqNo);
    
    //20210623 yoo mrStepCd를 왼쪽메뉴에 대한 것을 가져오지 않고 현재진행상태인 것을 가져오므로 메뉴의 진행 상태를 가져오는 메소드를 추가함.
    SafeCheckVO selectSafeCheck(int mrReqNo, String mrStepCd);

    /**
     * 가동전 안전점검 입력여부
     * @param safeCheckVO
     */
    SafeChrgrChgHistVO countSafeCheck(SafeCheckVO safeCheckVO);

    /**
     * 가동전 안전점검 작성 임시저장
     * @param safeCheckVO
     */
    /*void insertSafeCheck(SafeCheckVO safeCheckVO);*/

    /**
     * MR 수행/가동전 안전점검 작성 임시저장
     * @param safeCheckVO
     */
    void insertSafeCheck(SafeCheckVO safeCheckVO, boolean flag);

    /**
     * MR 수행/가동전 안전점검 작성 임시저장(수정)
     * @param safeCheckVO
     */
    void updateSafeCheck(SafeCheckVO safeCheckVO, boolean flag);
    
    /**
     * 가동전 안전점검 작성 임시저장(수정)
     * @param safeCheckVO
     */
    /*void updateSafeCheck(SafeCheckVO safeCheckVO);*/

    /**
     * 가동전 안전점검 작성 완료
     * @param safeCheckVO
     */
    void compSafeCheck(SafeCheckVO safeCheckVO);

    /**
     * 설계팀 MR수행 완료 MR요청자에게 가동전 안전점검 작성 요청
     * @param safeCheckVO
     */
    void safeCheckConf(SafeCheckVO safeCheckVO);


    /**
     * 가동전 안전점검 작성 승인
     * @param safeCheckVO
     */
    void apprSafeCheck(SafeCheckVO safeCheckVO);
   
    /**
     * 201905 MR수행 기술검토 확인 
     * Z02D -> Z02I 로 업데이트 해주는 로직 
     * @param safeCheckVO
     */
	void updateCheckZ02d(SafeCheckVO safeCheckVO);

	/**
	 * 201905 MR수행
	 * 파일임시저장
	 * @param safeCheckVO
	 */	
	void apprSafeCheck2(SafeCheckVO safeCheckVO);
}
