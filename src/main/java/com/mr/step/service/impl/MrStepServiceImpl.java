package com.mr.step.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.common.file.domain.MrAtchFile;
import com.common.file.repository.MrAtchFileRepository;
import com.common.file.service.MrAtchFileService;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrReqProcStepDetVO;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrStepService;

@Service
public class MrStepServiceImpl extends BaseService implements MrStepService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MrStepRepository mrStepRepository;

    @Autowired
    MrAtchFileRepository mrAtchFileRepository;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    private XMLConfiguration appConfig;

    /**
     * 화면처리 및 단계처리를 위한 모드 권한 반환
     */
    @Override
    public String getModeClass(int mrReqNo) {
        String modeClass ="" ;
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);

      //현재 진행중인 단계의 정보 조회
        //ChrgrChgHist appInfo60 = null;
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo,null);
        /*
        if(appInfo == null)
        {
        	appInfo60 =  mrStepRepository.selectAppStepEmp60(mrReqNo,null);
        	if(appInfo60 != null)
        		appInfo = appInfo60;
        }
        */
        //if(appInfo.getMrStepCd().equals("Z0060")) {
        	
        //}
        /**
         * 진행중인 단계가 없을경우 FFF로 처리
         * 모드처리문자는 다음과 같은 규칙을 가진다.
         * 1자리 : MR_STEP_CD
         * 2자리 : PROC_ST_CD
         * 3자리 : 해당담당자 일경우 A 담당자가 아닐경우 B 
         * ======================================================
         *
         * 동시에 진행해야 하는 경우 하단에 STEP 번호를 OR 조건으로 추가하세요.
         *
         *
         */
        
        if(appInfo != null)
        {
	        logger.info("appInfo.getMrStepCd():" + appInfo.getMrStepCd());
	        logger.info("appInfo.getProcStCd():" + appInfo.getProcStCd());
        }
        
        
        if (appInfo == null) {
            modeClass = "FFF";
        } else {
            switch (appInfo.getMrStepCd()) {
                case "Z0010":
                    modeClass += "A";
                    break;
                case "Z0020":
                    modeClass += "B";
                    break;
                case "Z0025":
                    modeClass += "J";
                    break;
                case "Z0030":
                    modeClass += "C";
                    break;
                case "Z0040":
                    modeClass += "D";
                    break;
                case "Z0050":
                    modeClass += "E";
                    break;
                case "Z0060":
                    modeClass += "F";
                    break;
                case "Z0070":
                    modeClass += "G";
                    break;
                case "Z0080":
                    modeClass += "H";
                    break;
                case "Z0090":
                    modeClass += "I";
                    break;
                case "Z00Z0":
                    modeClass += "Z";
                    break;
            }

            if(modeClass.equals("A")||modeClass.equals("B")||modeClass.equals("C")||modeClass.equals("D")) {
                switch (appInfo.getProcStCd()) {
                    case "Z0110":
                        modeClass += "A";
                        break;
                    case "Z0121":
                        modeClass += "B";
                        break;
                    case "Z0122":
                        modeClass += "C";
                        break;
                    case "Z0133":
                        modeClass += "D";
                        break;
                    case "Z0131":
                        modeClass += "E";
                        break;
                    case "Z0141":
                        modeClass += "A";
                        break;
                }
            } else if (modeClass.equals("E")||modeClass.equals("F")||modeClass.equals("G")||modeClass.equals("H")||modeClass.equals("I")||modeClass.equals("Z")|| modeClass.equals("J")) {
                switch (appInfo.getProcStCd()) {
                    case "Z0110":
                        modeClass += "A";
                        break;
                    case "Z0121":
                        modeClass += "B";
                        break;
                    case "Z0131":
                        modeClass += "C";
                        break;
                    case "Z0122":
                        modeClass += "D";
                        break;
                    case "Z0132":
                        modeClass += "E";
                        break;
                    case "Z0123":
                        modeClass += "F";
                        break;
                    case "Z0133":
                        modeClass += "G";
                        break;
                    case "Z0141":
                        modeClass += "A";
                        break;
                    case "Z0142":
                        modeClass += "Z";
                        break;
                    case "Z0143":
                        modeClass += "Z";
                        break;
                }
            }
            //동시에 진행해야 하는 단계이면 여기에 추가하세요. 코드를 OR 조건으로 추가하세요.

            if(appInfo.getMrStepCd().equals("Z0030")|| appInfo.getMrStepCd().equals("Z0040")||appInfo.getMrStepCd().equals("Z0070") || appInfo.getMrStepCd().equals("Z0025")) {
                int appCount = 0;
                List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mrReqNo);
                
                //20150827 Project Engineer 선택 수정 시작 :  설정 가능 인원 변경 수행팀장, 직책과장 -> 수행팀원으로                
                if(appInfo.getMrStepCd().equals("Z0030") && (appInfo.getProcStCd().equals("Z0110") || appInfo.getProcStCd().equals("Z0121"))){
                	//logger.info(""+appInfo.getMrStepCd());

                	for(ChrgrChgHist appChrgrChgHist : appInfoList) {
                		
                    	//logger.info("getProcStCd : " + appInfo.getProcStCd());
                    	//logger.info("getChrgEmpNo : " + appChrgrChgHist.getChrgEmpNo());
                    	//logger.info("getLoginEmpNo : " + chrgrChgHist.getLoginEmpNo());
                    	//logger.info("getLoginTeamNo : " + chrgrChgHist.getLoginTeamNo());
                    	//logger.info("getChrgTeamNo : " + appChrgrChgHist.getChrgTeamNo());
                    	
                		//if (appInfo.getProcStCd().equals("Z0110")) {   //초기투자비 작성 단계 동일팀 모두 변경 가능
                		logger.info(chrgrChgHist.getLoginTeamNo() + " - TeamNo 비교 : " + appChrgrChgHist.getChrgTeamNo());
                		if(chrgrChgHist.getLoginTeamNo().equals(appChrgrChgHist.getChrgTeamNo())) {
                		   appCount++;
                		}
                		//} else if (appInfo.getProcStCd().equals("Z0121")){  //초기투자비 요청 단계 담당자만 변경 가능
                    	//	if(chrgrChgHist.getLoginEmpNo().equals(appChrgrChgHist.getChrgEmpNo())) {
                    	//		appCount++;
                    	//	}
            		    //}
                	}                	
                }
                else {
                	for(ChrgrChgHist appChrgrChgHist : appInfoList) {
                		logger.info(chrgrChgHist.getLoginEmpNo() + " - EmpNo 비교 : " + appChrgrChgHist.getChrgEmpNo());
                		if(chrgrChgHist.getLoginEmpNo().equals(appChrgrChgHist.getChrgEmpNo())) {
                			appCount++;
                		}
                	}
                }                
                                
                if(appCount>0) {
                    modeClass += "A";
                    logger.info(chrgrChgHist.getLoginEmpNo() + " 1) modeClass += A ");
                } else {
                    modeClass += "B";
                    logger.info(chrgrChgHist.getLoginEmpNo() + " 1) modeClass += B ");
                }

            } else {

                // 기술항목 검토 승인 기능 사용을 위함.
            	//if(appInfo.getMrStepCd().equals("Z0020") && chrgrChgHist.getLoginEmpNo().equals("199390")) {
                //    modeClass += "I";
                //} else if(chrgrChgHist.getLoginEmpNo().equals(appInfo.getChrgEmpNo())) {
            	if(chrgrChgHist.getLoginEmpNo().equals(appInfo.getChrgEmpNo())) {
                    if(appInfo.getMrStepCd().equals("Z0060") && appInfo.getChrgrClCd().equals("Z02I")) {
                       modeClass += "I";
                       logger.info(chrgrChgHist.getLoginEmpNo() + ": 2) modeClass += I :" + appInfo.getChrgEmpNo());
                    } else if(appInfo.getMrStepCd().equals("Z0060") && appInfo.getChrgrClCd().equals("Z02D")) {
                    	modeClass += "D"; //201905 기술검토자일 경우 
                    	logger.info(chrgrChgHist.getLoginEmpNo() + ": 2) modeClass += D :" + appInfo.getChrgEmpNo());
                    } else {
                	   modeClass += "A";
                	   logger.info(chrgrChgHist.getLoginEmpNo() + ": 2) modeClass += A :" + appInfo.getChrgEmpNo());
                    }
                } else {
                    modeClass += "B";
                    logger.info(chrgrChgHist.getLoginEmpNo() + ": 2) modeClass += B :" + appInfo.getChrgEmpNo());
                }
            }
        }
        logger.info("modeClass:"+ modeClass);
        return modeClass;
    }


    /*
     * 위험성 검토 및
     * */
    @Override
    public String isolationModeClass(int mrReqNo, String mrStepCd, List<String> ingMrStepCd, String chrgrClCd, boolean isMulti) {
    	
        String modeClass = "I";

        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        ChrgrChgHist appInfo = mrStepRepository.selectAppIsolationStepEmp(mrReqNo, mrStepCd, chrgrClCd);
        ChrgrChgHist currentAppInfo = mrStepRepository.selectAppStepEmp(mrReqNo,null);

        for (String targetMrStepCd : ingMrStepCd){
            if(currentAppInfo != null && currentAppInfo.getMrStepCd().equals(targetMrStepCd)){
                modeClass += "A";
                break;
            }
        }

        if(appInfo!=null && !isMulti){
            int clCdLength = appInfo.getChrgrClCd().length();
            String clCd = appInfo.getChrgrClCd().substring(clCdLength-1);

            if(chrgrChgHist.getLoginEmpNo().equals(appInfo.getChrgEmpNo())) {
                modeClass += clCd;
            } else {
                modeClass += "NN";
            }
        } else if (isMulti) {

            List<ChrgrChgHist> mutiAppLine = mrStepRepository.selectIsolationAppStepEmpMulti(mrReqNo, mrStepCd, chrgrClCd);
            for (ChrgrChgHist chrgrChgHistCheck : mutiAppLine){
                if(chrgrChgHistCheck.getChrgEmpNo().equals(chrgrChgHist.getLoginEmpNo())){
                    modeClass += "SA";
                    break;
                }
            }
        } else {
            modeClass += "FF";
        }
        
        if(chrgrClCd == null && !isMulti)
    	{
    		System.out.println("modeClass :  " + modeClass);
    	}else if(chrgrClCd.equals("Z02L") && isMulti){
    		System.out.println("porcClass :  " + modeClass);
    	}else{
    		System.out.println("!!!!!!!!!  ");
    	}
        return modeClass;
    }

    @Override
    public String getSubModeClass(int mrReqNo, String chrgrClCd, String mrStepCd, boolean isLike) {
        ChrgrChgHist loginInfo = new ChrgrChgHist();
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mrReqNo);
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo,null);
        String subModeClass = "";
        for(ChrgrChgHist chrgrChgHist : appInfoList) {
            subModeClass = "S-A";

            if(!isLike && chrgrChgHist.getChrgrClCd().equals(chrgrClCd)) {
                subModeClass += "A";
            }

            if(isLike && chrgrChgHist.getChrgrClCd().contains(chrgrClCd)) {
                subModeClass += "L";
            }

            if((appInfo.getMrStepCd().equals(mrStepCd) && chrgrChgHist.getChrgEmpNo().equals(loginInfo.getLoginEmpNo())) || (chrgrChgHist.getJobRvCd()!=null && chrgrChgHist.getJobRvCd().equals("SKIP"))){
                subModeClass += "A";
            }

            if(subModeClass.equals("S-AAA") || subModeClass.equals("S-ALA")) {
                break;
            }
        }

        return subModeClass;
    }

    /**
     * 화면 좌측 메뉴 상태 표시용 STEP 반환
     */
    @Override
    public List<MrReqProcStepVO> selectAllMrReqStep(int mrReqNo) {
        List<MrReqProcStepVO> mrReqProcStepVOs = mrStepRepository.selectAllMrReqStep(mrReqNo);
        if(mrReqNo==0) {
            MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
            mrReqProcStepVO.setMrStepCd("Z0010");
            mrReqProcStepVO.setProcStCd("Z0110");
            mrReqProcStepVO.setProcStNm("작성중");
            mrReqProcStepVOs.add(mrReqProcStepVO);
        }
        return mrReqProcStepVOs;
    }

    /**
     * 투자비 금액 가져오기 yoo 230710
     * 
     */
    @Override
    public String selectMrInvstCost(int mrReqNo) {
    	String mrInvstCost = mrStepRepository.selectMrInvstCost(mrReqNo);
        return mrInvstCost;
    }
    
    
    /**
     * STEP을 생성하거나 변경 그리고 담당자를 변경한다.
     */
    @Override
    public int insertMrStep(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine){
    	
        int mrReqProcStepNo = 0; 

        if(procStCd!=null){

            //STEP을 신규로 생성한다.
            MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
            mrReqProcStepVO.setMrReqNo(mrReqNo);
            mrReqProcStepVO.setMrStepCd(mrStepCd);
            mrReqProcStepVO.setProcStCd(procStCd);            
            
            //Step 생성            
            mrStepRepository.insertProcStep(mrReqProcStepVO);
            
            //마스터 테이블 STEP 업데이트
            mrStepRepository.updateMrReqStep(mrReqProcStepVO);
            
            mrReqProcStepNo = mrReqProcStepVO.getMrReqProcStepNo();
        } else {
            //논리 삭제를 위하여 담당자 변경 객체 생성
            //mrReqNo를 셋팅하고 있으나 객체 생성시 자동으로 loginEmp를 세션으로 부터 얻어옴.
            ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);

            //반려상태가 있을수 있으므로 업데이트시에는 procStCd를 다시 셋팅해준다.
            procStCd = appInfo.getProcStCd();

            //상세 DET 단부터는 논리삭제후 처리하므로 STEP 번호가 필요함.
            mrReqProcStepNo = appInfo.getMrReqProcStepNo();

            //상세 Step 논리삭제
 
            mrStepRepository.updateMrReqProcStepDetDelYn(appInfo);
            
            logger.info(appInfo.getMrReqProcStepDetNo() + " (1) insertMrStep : " + appInfo.getMrReqNo() + ":" + appInfo.getChrgTeamNo() + " : " + appInfo.getChrgEmpNo());
            //결재라인 논리삭제
            mrStepRepository.updateAppLineDelYn(appInfo);
            logger.info("updateAppLineDelYn  끝 :: " + appInfo.getMrStepCd());
        }
        
        //결재자정보를 새로 생성한다.
        logger.info("ChrgrChgHist  appLine 결재자정보 :: ");
        for (ChrgrChgHist chrgrChgHist: appLine) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepNo);
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);				// DET Insert

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            
            mrStepRepository.insertChrgrChgHist(chrgrChgHist); 							// HIST Insert
        }
        logger.info("ChrgrChgHist  appLine 결재자정보 끝 :: ");
        return mrReqProcStepNo;
    }   
    
    
    /**
     * STEP을 생성하거나 변경 그리고 담당자를 변경한다.
     */
    @Override
    public int insertMrStep2(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine){
    	
        int mrReqProcStepNo = 0; 

        if(procStCd!=null){

            //STEP을 신규로 생성한다.
            MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
            mrReqProcStepVO.setMrReqNo(mrReqNo);
            mrReqProcStepVO.setMrStepCd(mrStepCd);
            mrReqProcStepVO.setProcStCd(procStCd);            
            
            //Step 생성            
            mrStepRepository.insertProcStep(mrReqProcStepVO);
            
            //마스터 테이블 STEP 업데이트
            mrStepRepository.updateMrReqStep(mrReqProcStepVO);
            
            mrReqProcStepNo = mrReqProcStepVO.getMrReqProcStepNo();
        } else {
            //논리 삭제를 위하여 담당자 변경 객체 생성
            //mrReqNo를 셋팅하고 있으나 객체 생성시 자동으로 loginEmp를 세션으로 부터 얻어옴.
            ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);

            //반려상태가 있을수 있으므로 업데이트시에는 procStCd를 다시 셋팅해준다.
            procStCd = appInfo.getProcStCd();

            //상세 DET 단부터는 논리삭제후 처리하므로 STEP 번호가 필요함.
            mrReqProcStepNo = appInfo.getMrReqProcStepNo();

            //상세 Step 논리삭제
            mrStepRepository.updateMrReqProcStepDetDelYn(appInfo);
            Map map = new HashMap();
        	map.put("mrReqNo", appInfo.getMrReqNo());
        	map.put("mrStepCd", appInfo.getMrStepCd());
        	map.put("loginEmpNo", appInfo.getLoginEmpNo());
            mrStepRepository.updateDetHistDelDuplication(map);
            
            logger.info(appInfo.getMrReqProcStepDetNo() + " (1) insertMrStep : " + appInfo.getMrReqNo() + ":" + appInfo.getChrgTeamNo() + " : " + appInfo.getChrgEmpNo());
            //결재라인 논리삭제
            mrStepRepository.updateAppLineDelYn(appInfo);
            logger.info("updateAppLineDelYn  끝 :: " + appInfo.getMrStepCd());
        }
        
        //결재자정보를 새로 생성한다.
        logger.info("ChrgrChgHist  appLine 결재자정보 :: ");
        for (ChrgrChgHist chrgrChgHist: appLine) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepNo);
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            if(procStCd == null)		// yoo 240923  procStCd가 null일 경우, Z0110을 넣어 준다
            	mrReqProcStepDetVO.setProcStCd("Z0110");
            else
            	mrReqProcStepDetVO.setProcStCd(procStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);				// DET Insert

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            
            mrStepRepository.insertChrgrChgHist(chrgrChgHist); 							// HIST Insert
        }
        logger.info("ChrgrChgHist  appLine 결재자정보 끝 :: ");
        return mrReqProcStepNo;
    }   
    /**
     * 직무검토자 입력 서비스
     */
    @Override
    public void insertTechEmp(int mrReqNo, int mrReqProcStepNo, String mrStepCd, String procStCd, List<ChrgrChgHist> techEmps) {

        for (ChrgrChgHist chrgrChgHist: techEmps) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepNo);
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            
	        //상세 Step 생성
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            
            //결재라인생성
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);            
        }
    }
    
    
    /**
     *  입력 서비스
     */
    @Override
    public void insertCompEmp(int mrReqNo, int mrReqProcStepNo, String mrStepCd, String procStCd, List<ChrgrChgHist> techEmps) {

        for (ChrgrChgHist chrgrChgHist: techEmps) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            
            //결재라인생성
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);            
        }
    }

    /**
     * 보통 다음 단계 담당자 활성화시 사용한다. 상세 STEP만 변경시 사용하지 않도록 주의
     */
    @Override
    public ChrgrChgHist insertMrStepSingleAction(int mrReqNo, String mrStepCd, String procStCd, String chrgrClCd) {

        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppStepEmp(mrReqNo, chrgrClCd);
        chrgrChgHist.setMrReqNo(mrReqNo);
        mrStepRepository.updateProcStepEffEnd(chrgrChgHist);

        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd(mrStepCd);
        mrReqProcStepVO.setProcStCd(procStCd);
        mrStepRepository.insertProcStep(mrReqProcStepVO);
        logger.info(mrReqNo + mrStepCd + procStCd + chrgrClCd + " nMrReqProcStepKey : " + mrReqProcStepVO.getMrReqProcStepNo());
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);

        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepVO.getMrReqProcStepNo());
        mrReqProcStepDetVO.setMrStepCd(mrStepCd);
        mrReqProcStepDetVO.setProcStCd(procStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
        chrgrChgHist.setMrReqProcStepNo(mrReqProcStepVO.getMrReqProcStepNo());		// yoo 240103 추가  MrReqProcStepNo 값을 넘긴다
        chrgrChgHist.setProcStCd(procStCd);
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        
        return chrgrChgHist;
    }
    
    /**
     * 기존의 MrReqProcStepNo로 Insert 하는 메소드 - yoo 240103
     */
    @Override
    public int insertMrStepSingleAction(int mrReqNo, String currentMrStepCd, String nextProcStCd, String chrgrClCd, String mrStepCd, ChrgrChgHist chrgrChgHist2D) {
    	// mrReqNo, "Z00Z0", "Z0132", "Z02F", "Z0090");

    	ChrgrChgHist chrgrChgHist2F = mrStepRepository.selectAppLine(mrReqNo, mrStepCd, chrgrClCd);
    	chrgrChgHist2F.setMrReqNo(mrReqNo);
        
        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrReqProcStepNo(chrgrChgHist2D.getMrReqProcStepNo());
        mrReqProcStepDetVO.setMrStepCd(currentMrStepCd);
        mrReqProcStepDetVO.setProcStCd(nextProcStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(null);
        int detkey = mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
        chrgrChgHist2F.setMrReqProcStepNo(chrgrChgHist2D.getMrReqProcStepNo());		// yoo 240103 추가  MrReqProcStepNo 값을 넘긴다
        chrgrChgHist2F.setProcStCd(nextProcStCd);
        chrgrChgHist2F.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist2F);
        return detkey;
        
    }

    /**
     * 동시에 처리가 필요한 단계를 호출할때 처리하세요.
     */
//yoo 240731 문제의 메소드
    @Override
    public int insertMrStepMultiAction(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine, String lastAppChrgrClCd, int mrReqProcStepNo){
    	logger.info("@@@@ insertMrStepMultiAction - " + mrReqNo + mrStepCd + procStCd + appLine.size() + lastAppChrgrClCd + mrReqProcStepNo);
    	
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, lastAppChrgrClCd);
        
        if(appInfo != null)
        {
	        appInfo.setMrReqNo(mrReqNo);
	        mrStepRepository.updateProcStepEffEnd(appInfo);
        }
        
        
        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd(mrStepCd);
        mrReqProcStepVO.setProcStCd(procStCd);
        //if(mrStepCd.equals("Z0025") && lastAppChrgrClCd.equals("Z02C"))		//yoo240802 Step 테이블 중복 입력 방지
        	
        if(mrReqProcStepNo < 0)
        	mrStepRepository.insertProcStep(mrReqProcStepVO);
        else
        	mrReqProcStepVO.setMrReqProcStepNo(mrReqProcStepNo);
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);
        
        

        for (ChrgrChgHist chrgrChgHist: appLine) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepVO.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            if("Z0020".equals(mrStepCd) && "Z0110".equals(procStCd) && ("Z02G".equals(chrgrChgHist.getChrgrClCd()) || "Z02G1".equals(chrgrChgHist.getChrgrClCd()))){
                mrReqProcStepDetVO.setFstProcTrmDt(null);
            }else{
            	mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
            }
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setProcStCd(procStCd);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);		//insertChrgrChgHist(결재라인생성)
        }
      return mrReqProcStepVO.getMrReqProcStepNo();
        
    }


	public List<MrReqProcStepVO> checkInsertMrStep(int mrReqNo,String mrStepCd,String chrgrClCd)
	{
		ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
		chrgrChgHist.setMrReqNo(mrReqNo);
		chrgrChgHist.setMrStepCd(mrStepCd);
		chrgrChgHist.setChrgrClCd(chrgrClCd);
		return mrStepRepository.checkInsertMrStep(chrgrChgHist);
	}

    @Override
    public void updateAppLine(int mrReqNo, List<ChrgrChgHist> appLine, List<String> chrgrClCds) {

        boolean isContain = false;
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
        appInfo.setChrgrClCds(chrgrClCds);
        logger.info(appInfo.getMrReqProcStepDetNo() + " (2) updateAppLine : " + appInfo.getMrReqNo() + ":" + appInfo.getChrgTeamNo() + " : " + appInfo.getChrgEmpNo());
        mrStepRepository.updateAppLineDelYn(appInfo);

        for (ChrgrChgHist chrgrChgHist: appLine) {
            isContain = false;
            for(String chrgrClCd : chrgrClCds){
                if(chrgrChgHist.getChrgrClCd().equals(chrgrClCd)) {
                    isContain = true;
                }
            }

            if(isContain==false) {
                continue;
            }

            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            mrReqProcStepDetVO.setProcStCd(appInfo.getProcStCd());
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        }
    }

    @Override
    public void insertNextStepForJobs(int mrReqNo) {
        //종료 플래그 추가
        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setProcStCd("Z0132");
        mrStepRepository.updateProcStep(mrReqProcStepVO);

        //결재상태 마감
        ChrgrChgHist stepEffEnd = new ChrgrChgHist();
        stepEffEnd.setMrReqNo(mrReqNo);
        mrStepRepository.updateProcStepEffEnd(stepEffEnd);

        List<ChrgrChgHist> appInfoList = mrStepRepository.selectMrAllAppLine(mrReqNo);

        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd("Z0050");
        mrReqProcStepVO.setProcStCd("Z0110");
        mrStepRepository.insertProcStep(mrReqProcStepVO);
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);

        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepVO.getMrReqProcStepNo());
        mrReqProcStepDetVO.setMrStepCd("Z0050");
        mrReqProcStepDetVO.setProcStCd("Z0110");
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

        //결재라인에 사업수행팀 팀장 기술검토 팀장 추가.
        for(ChrgrChgHist chrgrChgHist : appInfoList) {
            if(chrgrChgHist.getChrgrClCd().equals("Z02D")) {
                chrgrChgHist.setMrReqNo(mrReqNo);
                chrgrChgHist.setFstProcTrmDt(getAppTerm());
                chrgrChgHist.setProcStCd("Z0110");
                chrgrChgHist.setChrgTeamNo(chrgrChgHist.getChrgTeamNo());
                chrgrChgHist.setThdayTeam(chrgrChgHist.getThdayTeam());
                chrgrChgHist.setThdayPos(chrgrChgHist.getThdayPos());
                chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
                mrStepRepository.insertChrgrChgHist(chrgrChgHist);
            }
        }
    }
    //yoo 240920 다음 스템을 구하는 메소드
    public String nextMrStepCd(String mrStepCd)
    {
    	String[] steps = {"Z0010","Z0020","Z0025","Z0030","Z0040","Z0050","Z0060","Z0070","Z0080","Z0090","Z00Z0"};
    	int index = 0;
    	for(String stepCd : steps)
    	{
    		index++;
    		if(stepCd.equals(mrStepCd))
    			break;
    	}
    	return steps[index];
    }
    @Override
    public String updateDetHistDelDuplication(int mrReqNo, String stepCd, String loginEmpNo) {
    	Map map = new HashMap();
    	map.put("mrReqNo", mrReqNo);
    	map.put("mrStepCd", stepCd);
    	map.put("loginEmpNo", loginEmpNo);
        mrStepRepository.updateDetHistDelDuplication(map);
        return "";
        
    }
    
    @Override
    public String insertNextAppEmp(int mrReqNo, String nextProcStCd) {

   
    logger.info(nextProcStCd + " (1)타당성검토  승인 : " + mrReqNo);
    ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
        if (appInfo!=null) {
        logger.info(appInfo.getChrgrClCd() + " (2)타당성검토  승인 : " + mrReqNo);
            mrStepRepository.updateAppLineEffEnd(appInfo);
            //단계업데이트
            if(nextProcStCd!=null) {
            logger.info(appInfo.getMrStepCd() + " (3)타당성검토  승인 : " + appInfo.getChrgrClCd());
                MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
                mrReqProcStepVO.setMrReqNo(mrReqNo);
                mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
                mrReqProcStepVO.setProcStCd(nextProcStCd);
                mrStepRepository.updateProcStep(mrReqProcStepVO);
                mrStepRepository.updateMrReqStep(mrReqProcStepVO);
            } else {
           
                nextProcStCd = appInfo.getProcStCd();
                logger.info(nextProcStCd + " (4)타당성검토승인 : " + appInfo.getChrgrClCd());
            }
            logger.info(appInfo.getMrReqProcStepNo() + " (5)타당성검토  승인 : " + appInfo.getChrgrClCd());
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
            logger.info(mrReqProcStepDetVO.getMrReqProcStepDetNo() + " (6)타당성검토승인 : " + appInfo.getChrgrClCd());
            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(appInfo); // insertChrgrChgHist(결재라인생성)
        }
      //Z002G가 중복 되는 현상이 있음 yoo 240925
            //Map map = new HashMap();
        //map.put("mrReqNo", mrReqNo);
        //map.put("mrStepCd", appInfo.getMrStepCd());
        //map.put("loginEmpNo", appInfo.getLoginEmpNo());
            //mrStepRepository.updateDetHistDelDuplication(map);   
        return null;
    //return appInfo.getChrgrClCd();
    }

    
    
    @Override
    public String insertNextAppEmp3(int mrReqNo, String nextProcStCd) {
    	//insertNextAppEmp3와 insertNextAppEmp의 차이는 중복 제거 기능이 있느냐 업느냐다 240926
    	
    	logger.info(nextProcStCd + " (1)타당성검토  승인 : " + mrReqNo);
	    ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
	        if (appInfo!=null) {
	        	logger.info(appInfo.getChrgrClCd() + " (2)타당성검토  승인 : " + mrReqNo);
	            mrStepRepository.updateAppLineEffEnd(appInfo);
	
	            //단계업데이트
	            if(nextProcStCd!=null) {
	            	logger.info(appInfo.getMrStepCd() + " (3)타당성검토  승인 : " + appInfo.getChrgrClCd());
	                MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
	                mrReqProcStepVO.setMrReqNo(mrReqNo);
	                mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
	                mrReqProcStepVO.setProcStCd(nextProcStCd);
	                mrStepRepository.updateProcStep(mrReqProcStepVO);
	                mrStepRepository.updateMrReqStep(mrReqProcStepVO);
	            } else {
	            	
	                nextProcStCd = appInfo.getProcStCd();
	                logger.info(nextProcStCd + " (4)타당성검토승인 : " + appInfo.getChrgrClCd());
	            }
	            logger.info(appInfo.getMrReqProcStepNo() + " (5)타당성검토  승인 : " + appInfo.getChrgrClCd());
	            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
	            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
	            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
	            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
	            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
	            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
	            logger.info(mrReqProcStepDetVO.getMrReqProcStepDetNo() + " (6)타당성검토승인 : " + appInfo.getChrgrClCd());
	            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
	            mrStepRepository.insertChrgrChgHist(appInfo);			// insertChrgrChgHist(결재라인생성)
	        }
	        
	        
	                
	    return appInfo.getChrgrClCd();
    }
    
    @Override
    public void insertNextAppEmp2(int mrReqNo, String nextProcStCd) {

    	
    	logger.info(nextProcStCd + " (1)타당성검토  승인 : " + mrReqNo);
	    	ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
	        if (appInfo!=null) {
	        	logger.info(appInfo.getChrgrClCd() + " (2)타당성검토  승인 : " + mrReqNo);
	        	//yoo 240920 Z0020 단계에서의 기술검토자가 표시가 안되어 주석 처리 함
	            //mrStepRepository.updateAppLineEffEnd(appInfo);
	
	            //단계업데이트
	            if(nextProcStCd!=null) {
	            	logger.info(appInfo.getMrStepCd() + " (3)타당성검토  승인 : " + appInfo.getChrgrClCd());
	                MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
	                mrReqProcStepVO.setMrReqNo(mrReqNo);
	                mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
	                mrReqProcStepVO.setProcStCd(nextProcStCd);
	                mrStepRepository.updateProcStep(mrReqProcStepVO);	// MR_REQ_PROC_STEP 업데이트
	                mrStepRepository.updateMrReqStep(mrReqProcStepVO);	//마스터 테이블 STEP 업데이트
	            } else {
	            	
	                nextProcStCd = appInfo.getProcStCd();
	                logger.info(nextProcStCd + " (4)타당성검토승인 : " + appInfo.getChrgrClCd());
	            }
	            logger.info(appInfo.getMrReqProcStepNo() + " (5)타당성검토  승인 : " + appInfo.getChrgrClCd());
	            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
	            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
	            
	            //yoo 기존 MrReqProcStepNo로  저장이 되므로 새로 생성을 위해 주석처리 한다
	            //mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
	            //yoo 240920 다음 Step으로 저장
	            mrReqProcStepDetVO.setMrStepCd(nextMrStepCd(appInfo.getMrStepCd()));
	            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
	            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
	            logger.info(mrReqProcStepDetVO.getMrReqProcStepDetNo() + " (6)타당성검토승인 : " + appInfo.getChrgrClCd());
	            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
	            mrStepRepository.insertChrgrChgHist(appInfo);			// insertChrgrChgHist(결재라인생성)
	        }
    	
    }
    
    @Override
    public void insertNextAppEmpDev(int mrReqNo, String nextProcStCd) {

        
    	//yoo 20210617 개발용 조회 에를들어 Z02D를 가저와야 하는데 Z02I를 가져오는 경우 사용...
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmpDev(mrReqNo, "Z02D");
        if (appInfo!=null) {

            mrStepRepository.updateAppLineEffEnd(appInfo);

            //단계업데이트
            if(nextProcStCd!=null) {
                MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
                mrReqProcStepVO.setMrReqNo(mrReqNo);
                mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
                mrReqProcStepVO.setProcStCd(nextProcStCd);
                mrStepRepository.updateProcStep(mrReqProcStepVO);
                mrStepRepository.updateMrReqStep(mrReqProcStepVO);
            } else {
                nextProcStCd = appInfo.getProcStCd();
            }

            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
            mrStepRepository.insertMrReqProcStepDetDev(mrReqProcStepDetVO);

            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(appInfo);
          
        }
    }
    

    //초기투자비 멀티 담당자 관련 
    @Override
    public void insertNextAppEmpMulti(int mrReqNo, String nextProcStCd) {

    	//결재자 여러명이 동시에 처리시 결재자정보 조회
        List<ChrgrChgHist> appInfoList = mrStepRepository.selectAppStepEmpMulti(mrReqNo);        
        
        //logger.info("초기투자비 멀티 담당자 관련  nextProcStCd: ");
        
        if(appInfoList.size() >0){
        	for(ChrgrChgHist appInfo : appInfoList){
		        if (appInfo!=null) {
		        	
		        	//결재라인 유효기간 종료
		        	//logger.info("결재라인 유효기간 종료 updateAppLineEffEnd: ");
		        	
		            mrStepRepository.updateAppLineEffEnd(appInfo);
		
		            //단계업데이트
		            if(nextProcStCd!=null) {
		                MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
		                mrReqProcStepVO.setMrReqNo(mrReqNo);
		                mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
		                mrReqProcStepVO.setProcStCd(nextProcStCd);
		                
		                //Step 변경
		                mrStepRepository.updateProcStep(mrReqProcStepVO);
		                
		                //마스터 테이블 STEP 업데이트
		                mrStepRepository.updateMrReqStep(mrReqProcStepVO);
		                
		                //logger.info("단계업데이트  ");
		            } else {
		                nextProcStCd = appInfo.getProcStCd();
		                //logger.info("단계업데이트 else  ");
		            }		
		
		            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
		            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
		            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
		            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
		            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
		            
		            //상세 Step 생성
		            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);
		
		            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
		            
		            //결재라인 생성
		            mrStepRepository.insertChrgrChgHist(appInfo);
		        }
        	}
        }
    }
    
    // 진행단계 업데이트
    @Override
    public int insertPrevAppEmp(int mrReqNo, String mrStepCd, String prevProcStCd, List<ChrgrChgHist> appLine, String chrgrClCd) {
        int mrReqProcStepNo = 0;
        MrReqProcStepDetVO mrReqProcStepDetVO = null;
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);	//현재 결재자 정보
        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd(appInfo.getMrStepCd());
        mrReqProcStepVO.setProcStCd(prevProcStCd);

        if(mrStepCd!=null) {
            mrReqProcStepVO.setMrStepCd(mrStepCd);
        }
        
        //MR_REQ_PROC_STEP(MR진행단계) update
        mrStepRepository.updateProcStep(mrReqProcStepVO);
        
        //MR_REQ_MST(MR요청) update
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);

        //MR_REQ_CHRGR_CHG_HIST(MR처리담당자 변경이력) update
        mrStepRepository.updateAppLineAllEffEnd(appInfo);

        if(appLine != null) {
            for (ChrgrChgHist chrgrChgHist: appLine) {
                mrReqProcStepDetVO = new MrReqProcStepDetVO();
                mrReqProcStepDetVO.setMrReqNo(mrReqNo);
                mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());

                //logger.info("insertPrevAppEmp mrStepCd : " + mrStepCd);
                if(mrStepCd==null) {
                    mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
                } else {
                    mrReqProcStepDetVO.setMrStepCd(mrStepCd);
                }

                mrReqProcStepDetVO.setProcStCd(prevProcStCd);
                mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
                if(chrgrClCd.equals(chrgrChgHist.getChrgrClCd())) {
                    mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
                }
                mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

                chrgrChgHist.setMrReqNo(mrReqNo);
                chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
                 int test = mrStepRepository.insertChrgrChgHist(chrgrChgHist);
                 //logger.info("test : " + test);
            }
        } else {
            //특정 Step에서 chrgrClCd가 일치하는 정보 가져오기.
            ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine(mrReqNo, mrStepCd, chrgrClCd);
            if(chrgrChgHist == null){
                for( ChrgrChgHist inChrgrChgHist : mrStepRepository.selectMrAllAppLine(mrReqNo)){
                    if(inChrgrChgHist.getChrgrClCd().equals(chrgrClCd)){
                        chrgrChgHist = inChrgrChgHist;
                        chrgrChgHist.setProcStCd(prevProcStCd);
                        break;
                    }
                }
            }
            
            mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            if(mrStepCd==null) {
                mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            } else {
                mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            }
            mrReqProcStepDetVO.setProcStCd(prevProcStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);	//mr처리 담당자 변경이력
        }

        mrReqProcStepNo = appInfo.getMrReqProcStepNo();
        return mrReqProcStepNo;
    }

    @Override
    public void insertIsolationNextAppEmp(int mrReqNo, String mrStepCd, String nextProcStCd) {

        ChrgrChgHist appInfo = mrStepRepository.selectAppIsolationStepEmp(mrReqNo, mrStepCd, null);
        if (appInfo!=null) {

            mrStepRepository.updateAppLineEffEnd(appInfo);

            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            mrReqProcStepDetVO.setProcStCd(nextProcStCd);
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            appInfo.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(appInfo);
        }
    }

    @Override
    public int insertIsolationPrevAppEmp(int mrReqNo, String mrStepCd, String prevProcStCd, List<ChrgrChgHist> appLine, String chrgrClCd) {
        int mrReqProcStepNo = 0;
        MrReqProcStepDetVO mrReqProcStepDetVO = null;
        ChrgrChgHist appInfo = mrStepRepository.selectAppIsolationStepEmp(mrReqNo, mrStepCd, null);

        mrStepRepository.updateAppLineAllEffEnd(appInfo);

        for (ChrgrChgHist chrgrChgHist: appLine) {
            mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());

            if(mrStepCd==null) {
                mrReqProcStepDetVO.setMrStepCd(appInfo.getMrStepCd());
            } else {
                mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            }

            mrReqProcStepDetVO.setProcStCd(prevProcStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());

            if(chrgrClCd.equals(chrgrChgHist.getChrgrClCd())) {
                mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
            }

            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        }

        mrReqProcStepNo = appInfo.getMrReqProcStepNo();
        return mrReqProcStepNo;
    }


    @Override
    public void updateMrStepEffEnd(int mrReqNo) {
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
        mrStepRepository.updateProcStepEffEnd(appInfo);
        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd("Z00Z0");
        mrReqProcStepVO.setProcStCd("Z0143");
        mrStepRepository.insertProcStep(mrReqProcStepVO);
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);
    }

    @Override
    public void updateMrAppLineEffEnd(int mrReqNo) {
        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);
        mrStepRepository.updateAppLineAllEffEnd(appInfo);
    }

    @Deprecated
    @Override
    public void updateMrAppLine(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine) {

        //논리 삭제를 위하여 담당자 변경 객체 생성
        //mrReqNo를 셋팅하고 있으나 객체 생성시 자동으로 loginEmp를 세션으로 부터 얻어옴.
        ChrgrChgHist appLineInfo = new ChrgrChgHist();
        appLineInfo.setMrReqNo(mrReqNo);
        appLineInfo.setMrStepCd(mrStepCd);
        appLineInfo.setProcStCd(procStCd);


        int mrReqProcStepNo = mrStepRepository.getMrReqProcStepNo(appLineInfo);

        mrStepRepository.updateMrReqProcStepDetDelYn(appLineInfo);
        logger.info(appLineInfo.getMrReqProcStepDetNo() + " (3) updateMrAppLine : "  + appLineInfo.getMrReqNo() + ":" + appLineInfo.getChrgTeamNo() + " : " + appLineInfo.getChrgEmpNo());
        mrStepRepository.updateAppLineDelYn(appLineInfo);

        for (ChrgrChgHist chrgrChgHist: appLine) {
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepNo);
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        }
    }


    @Override
    public int updateTechEmpDelYn(ChrgrChgHist chrgrChgHist) {
        return mrStepRepository.updateTechEmpDelYn(chrgrChgHist);
    }


    @Override
    public void insertMrStep(int mrReqNo, String mrStepCd, String procStCd, String chrgrClCd) {

        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppStepEmp(mrReqNo, chrgrClCd);
        chrgrChgHist.setMrReqNo(mrReqNo);
        mrStepRepository.updateProcStepEffEnd(chrgrChgHist);

        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setMrStepCd(mrStepCd);
        mrReqProcStepVO.setProcStCd(procStCd);
        mrStepRepository.insertProcStep(mrReqProcStepVO);
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);

        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrReqProcStepNo(mrReqProcStepVO.getMrReqProcStepNo());
        mrReqProcStepDetVO.setMrStepCd(mrStepCd);
        mrReqProcStepDetVO.setProcStCd(procStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

        chrgrChgHist.setProcStCd(procStCd);
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);
    }



    @Override
    public void insertPorcAppLine(int mrReqNo, String mrStepCd, String procStCd, List<ChrgrChgHist> appLine, boolean isAction) {

        ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(mrReqNo, null);


        for (ChrgrChgHist chrgrChgHist: appLine) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepNo(appInfo.getMrReqProcStepNo());
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            if(isAction){
                mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
            } else {
                mrReqProcStepDetVO.setFstProcTrmDt(null);
            }
            updatePorcAppLineDel(chrgrChgHist);
            mrStepRepository.insertMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        }
    }


    /**
     * 기본기한일을 자동으로 기본 appConfig에 따라 셋팅하게 된다.
     * @return
     */
    @Override
    public Date getAppTerm(){
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar cal = new GregorianCalendar();
        Date to = null;
        try {
            // resources\config\app-config.xml <default> 하위에 <term>에서 셋팅하는 값에 따라 자동 셋팅
            int defaultTerm = appConfig.getInt("default.term");
            cal.add(Calendar.DATE, defaultTerm);
            to = transFormat.parse(transFormat.format(cal.getTime()));
        } catch (ParseException e) {
            logger.debug(e.getStackTrace());
        }
        return to;
    }


    /**
     * 9999-12-31 날짜를 Date 형태로 변환하여 반환.
     * @return
     */
    @Override
    public Date get99991231(String endDate){
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = null;
        try {
            if(endDate==null) {
                endDate="9999-12-31";
            }
            to = transFormat.parse(endDate);
        } catch (ParseException e) {
            //logger.debug(e.getStackTrace());
        }
        return to;
    }


    @Override
    public void updatePorcAppLineDel(ChrgrChgHist chrgrChgHist) {
        mrStepRepository.updatePorcEmpDelYn(chrgrChgHist);
        logger.info(chrgrChgHist.getMrReqProcStepDetNo() + " updatePorcAppLineDel : " + chrgrChgHist.getMrReqNo() + ":" + chrgrChgHist.getMrReqNo() + ":" + chrgrChgHist.getChrgTeamNo() + " : " + chrgrChgHist.getChrgEmpNo());
        mrStepRepository.updatePorcStepDetDelYn(chrgrChgHist);
    }


    @Override
    public void updateAppLineDel(int mrReqNo, String mrStepCd) {
        ChrgrChgHist chrgrChgHist = new ChrgrChgHist();
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setMrStepCd(mrStepCd);
        logger.info(chrgrChgHist.getMrReqProcStepDetNo() + " (4) updateAppLineDel : " + chrgrChgHist.getMrReqNo() + ":" + chrgrChgHist.getChrgTeamNo() + " : " + chrgrChgHist.getChrgEmpNo());
        mrStepRepository.updateAppLineDelYn(chrgrChgHist);
        mrStepRepository.updateMrReqProcStepDetDelYn(chrgrChgHist);
    }

    @Override
    public int updateMrReqProcStepDetJobRvCd(int mrReqProcStepDetNo, String jobRvCd) {
        return mrStepRepository.updateMrReqProcStepDetJobRvCd(mrReqProcStepDetNo, jobRvCd);
    }

    /* 요청서 저장
     * 첨부파일 저장
     * */
    @Override
    public int insertFile(int mrReqNo, String mrStepCd, String chrgrClCd, String fileStepCd, List<MrAtchFile> mrAtchFiles){
    	
        int insertCount = 0;
        if (CollectionUtils.isNotEmpty(mrAtchFiles)) {
        	
            List<MrAtchFile> saveMrAtchFiles = mrAtchFileService.saveMrAtchFiles(mrAtchFiles);
            //logger.info("saveMrAtchFiles:"+ saveMrAtchFiles );
            for (MrAtchFile mrAtchFile : saveMrAtchFiles) {
            	
            	//logger.info("chrgrClCd:"+ chrgrClCd + " mrStepCd:"+mrStepCd  + " fileStepCd:"+fileStepCd  + " mrAtchFile:"+mrAtchFile );
            	
                mrAtchFile.setMrReqNo(mrReqNo);
                if(mrStepCd != null)mrAtchFile.setMrStepCd(mrStepCd);
                mrAtchFile.setChrgrClCd(chrgrClCd);
                if(fileStepCd != null)mrAtchFile.setFileStepCd(fileStepCd);
                
                logger.info("mrAtchFile.isDeleted():"+ mrAtchFile.isDeleted()+ "    mrAtchFile.isInserted():"+ mrAtchFile.isInserted() );
                
                //if(!mrAtchFile.getDrawMngNo().isEmpty())
                //{
                	
	                //202009 mrAtchFile.isInserted() 인식이 안됨  mrAtchFileRepository.insertMrAtchFile(mrAtchFile); 해당 메소스 실행해서 insert 쿼리 확인
	                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
	                	insertCount++;
	                	System.out.println("★★★★★★★★★★★★★★★★★★★★★ insertMrAtchFile  start★★★★★★★★★★★★★★★★★★★★★ ");
	                	logger.info("mrAtchFile.getDrawMngNo() : " + mrAtchFile.getDrawMngNo());
	                	System.out.println("mrAtchFile.getFileNm() : " + mrAtchFile.getFileNm());
	                	System.out.println("mrAtchFile.getMrAtchFileNo() : " + mrAtchFile.getMrAtchFileNo());
	                	System.out.println("mrAtchFile.getMrReqProcStepDetNo() : " + mrAtchFile.getMrReqProcStepDetNo());
	                	System.out.println("mrAtchFile.getMrReqNo() : " + mrAtchFile.getMrReqNo());
	                	System.out.println("mrAtchFile.getFileCd() : " + mrAtchFile.getFileCd());
	                	System.out.println("mrAtchFile.getFileStepCd() : " + mrAtchFile.getFileStepCd());
	                	System.out.println("mrAtchFile.getFileProcCd() : " + mrAtchFile.getFileProcCd());
	                	System.out.println("mrAtchFile.getFileCdNm() : " + mrAtchFile.getFileCdNm());
	                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
	                    System.out.println("★★★★★★★★★★★★★★★★★★★★★ insertMrAtchFile  end★★★★★★★★★★★★★★★★★★★★★ ");
	                } else if(mrAtchFile.isDeleted()) {
	                	insertCount++;
	                	//logger.info("mrAtchFile.isDeleted()" );
	                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
	                }
                //}else
                //{
                	//logger.info("mrAtchFile.getDrawMngNo().isEmpty()!!!!!!!!!!");
                //}

                
            }
        }
        return insertCount;
    }


	@Override
	public List<ChrgrChgHist> getMrReqTechEmpInfo(int mrReqNo,List<String> mrStepCds, List<String> chrgrClCds) {
		return mrStepRepository.getMrReqTechEmpInfo(mrReqNo,mrStepCds,chrgrClCds);
	}


	@Override
	public void updateReqTechEmpDelYn(ChrgrChgHist mrReqTechEmpInfo) {
		mrStepRepository.updateReqTechEmpDelYn(mrReqTechEmpInfo);
	}


	@Override
	public void insertChrgrChgHist(ChrgrChgHist mrReqTechEmpInfo) {
		mrStepRepository.insertChrgrChgHist(mrReqTechEmpInfo);
	}


	@Override
	public MrReqProcStepVO selectOneMrReqStep(int mrReqNo, String mrStepCd) {
		return mrStepRepository.selectOneMrReqStep(mrReqNo, mrStepCd);
	}


	@Override
	public void updatePorcStepDetNoDelYn(ChrgrChgHist delActEmpInfo) {
		mrStepRepository.updatePorcStepDetNoDelYn(delActEmpInfo);
		
	}


	@Override
	public void updateAppLineEffEnd(ChrgrChgHist delActEmpInfo) {
		mrStepRepository.updateAppLineEffEnd(delActEmpInfo);
		
	}


	@Override
	public void insertMrReqProcStepDet(MrReqProcStepDetVO actMrReqProcStepDet) {
		mrStepRepository.insertMrReqProcStepDet(actMrReqProcStepDet);
		
	}


    /**
     * 요청서 결재 연동 상태 수정
     */
    @Override
    public void updateMrIfStat01(MrReqProcStepDetVO mrReqProcStepDetVO) {
    	mrStepRepository.updateMrIfStat01(mrReqProcStepDetVO);
        //return 1; 
    }


	@Override
    public void updateDetHistDel(int mrReqNo, String procStCd, String stepCd, String chrgrClCd) {        
        mrStepRepository.updateDetHistDel(mrReqNo, procStCd, stepCd, chrgrClCd);
	}
	
	

	@Override
	public void readProcess(int mrReqNo, String currentMrStepCd, String nextProcStCd, String nextChrgrClCd, String getMrStepCd, String getChrgrClCd, int mrReqProcStepDetNo) {
		// TODO Auto-generated method stub
		
		
		ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setProcStCd(nextProcStCd);
        chrgrChgHist.setChrgrClCd(nextChrgrClCd);
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetNo);
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);
	}

	@Override
	public void updateMrStep(int mrReqNo, String currentMrStepCd, String nextProcStCd, String nextChrgrClCd,String getMrStepCd, String getChrgrClCd) {
		// TODO Auto-generated method stub
		logger.info("1번");
		MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setProcStCd(nextProcStCd);
        mrReqProcStepVO.setMrStepCd(currentMrStepCd);
        mrStepRepository.updateProcStep(mrReqProcStepVO);		//MR_REQ_PROC_STEP UPDATE
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);		//MR_REQ_MST UPDATE

 //yoo 20230111 승인자 지정으로 변경 Z02F
        logger.info("2번");
        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrStepCd(currentMrStepCd);
        mrReqProcStepDetVO.setProcStCd(nextProcStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        mrStepRepository.insertMrReqProcStepDetForStepLast(mrReqProcStepDetVO);

        logger.info("3번");
        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine2(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        if(chrgrChgHist == null)
        	chrgrChgHist = mrStepRepository.selectAppLine(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setProcStCd(nextProcStCd);
        chrgrChgHist.setChrgrClCd(nextChrgrClCd);
        
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);        
                
	}

    /**
     * 다음 담당자를 입력받지 않고 이전에 DB에 저장된 데이터를 기준으로 조회하여 활성화 할때 사용함.
     */
    @Override
    public int updateMrStep2(int mrReqNo, String currentMrStepCd, String nextProcStCd, String nextChrgrClCd, String getMrStepCd, String getChrgrClCd) {

    	// insert 문제로 메소드 내용 분할 yoo 20210608
        MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setProcStCd(nextProcStCd);
        mrReqProcStepVO.setMrStepCd(currentMrStepCd);
        mrStepRepository.updateProcStep(mrReqProcStepVO);		//MR_REQ_PROC_STEP UPDATE
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);		//MR_REQ_MST UPDATE

        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrStepCd(currentMrStepCd);
        mrReqProcStepDetVO.setProcStCd(nextProcStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        int result = mrStepRepository.insertMrReqProcStepDetForStepLast(mrReqProcStepDetVO);

        /*
        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setProcStCd(nextProcStCd);
        chrgrChgHist.setChrgrClCd(nextChrgrClCd);
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);
        */
        return mrReqProcStepDetVO.getMrReqProcStepDetNo();
    }

	@Override
	public void updateMrStep3(int mrReqNo, String currentMrStepCd, String nextProcStCd, String nextChrgrClCd,String getMrStepCd, String getChrgrClCd) {
		// TODO Auto-generated method stub
		logger.info("1번");
		MrReqProcStepVO mrReqProcStepVO = new MrReqProcStepVO();
        mrReqProcStepVO.setMrReqNo(mrReqNo);
        mrReqProcStepVO.setProcStCd(nextProcStCd);
        mrReqProcStepVO.setMrStepCd(currentMrStepCd);
        mrStepRepository.updateProcStep(mrReqProcStepVO);		//MR_REQ_PROC_STEP UPDATE
        mrStepRepository.updateMrReqStep(mrReqProcStepVO);		//MR_REQ_MST UPDATE

 //yoo 20230111 승인자 지정으로 변경 Z02F
        logger.info("2번");
        MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
        mrReqProcStepDetVO.setMrReqNo(mrReqNo);
        mrReqProcStepDetVO.setMrStepCd(currentMrStepCd);
        mrReqProcStepDetVO.setProcStCd(nextProcStCd);
        mrReqProcStepDetVO.setFstProcTrmDt(getAppTerm());
        mrStepRepository.insertMrReqProcStepDetForStepLast(mrReqProcStepDetVO);

        logger.info("3번");
        ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine2(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        if(chrgrChgHist == null)
        	chrgrChgHist = mrStepRepository.selectAppLine(mrReqNo, getMrStepCd, getChrgrClCd, nextProcStCd);
        
        
        logger.info("Old key : " + chrgrChgHist.getMrReqProcStepDetNo());
        logger.info("New key : " + mrReqProcStepDetVO.getMrReqProcStepDetNo());
        System.out.println(chrgrChgHist.getMrReqProcStepDetNo() + " - key - " + mrReqProcStepDetVO.getMrReqProcStepDetNo());
        
     
        
        chrgrChgHist.setMrReqNo(mrReqNo);
        chrgrChgHist.setProcStCd(nextProcStCd);
        chrgrChgHist.setChrgrClCd(nextChrgrClCd);
        
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
        mrStepRepository.insertChrgrChgHist(chrgrChgHist);  
      
     // yoo 240411 옛날 정보 삭제
        mrStepRepository.updateDetHistDel90(mrReqNo,chrgrChgHist, "Z0090", "Z02F");   //테이블 업데이트(Z02I1 삭제

	}    

	@Override
	public void insertMrReqProcStepDetDev(MrReqProcStepDetVO mrReqProcStepDetVO) {
		// TODO Auto-generated method stub
		mrStepRepository.insertMrReqProcStepDetDev(mrReqProcStepDetVO);
	}


	@Override
	public List<ChrgrChgHist> selectJobLine(int mrReqNo, String mrStepCd) {
		// TODO Auto-generated method stub
		return mrStepRepository.selectJobLine(mrReqNo, mrStepCd);
	}


	@Override
	public void updateChrgrChgHist(ChrgrChgHist mrReqTechEmpInfo) {
		// TODO Auto-generated method stub
		mrStepRepository.updateChrgrChgHist(mrReqTechEmpInfo);
	}


	@Override
	public void updateMrReqProcStepDet(MrReqProcStepDetVO actMrReqProcStepDet) {
		// TODO Auto-generated method stub
		mrStepRepository.updateMrReqProcStepDet(actMrReqProcStepDet);
	}


	@Override
	public void updateTechEmp(int mrReqNo, int mrReqProcStepDetNo, String mrStepCd, String procStCd,
			List<ChrgrChgHist> techEmps) {
		// TODO Auto-generated method stub
		logger.info("=========== updateTechEmp  ===================");
		
		
		for (ChrgrChgHist chrgrChgHist: techEmps) {
            if(chrgrChgHist.getChrgEmpNo() ==null) {
                continue;
            }
            MrReqProcStepDetVO mrReqProcStepDetVO = new MrReqProcStepDetVO();
            mrReqProcStepDetVO.setMrReqNo(mrReqNo);
            mrReqProcStepDetVO.setMrReqProcStepDetNo(mrReqProcStepDetNo);
            mrReqProcStepDetVO.setMrStepCd(mrStepCd);
            mrReqProcStepDetVO.setProcStCd(procStCd);
            mrReqProcStepDetVO.setFstProcTrmDt(chrgrChgHist.getProcTrmDt());
            
	        //상세 Step 생성
            mrStepRepository.updateMrReqProcStepDet(mrReqProcStepDetVO);

            chrgrChgHist.setMrReqNo(mrReqNo);
            chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetVO.getMrReqProcStepDetNo());
            
            //결재라인생성
            mrStepRepository.updateChrgrChgHist90(chrgrChgHist);            
        }
		
	}


}
