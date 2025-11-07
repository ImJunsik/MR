package com.mr.comp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.base.service.BaseService;
import com.common.eai.CmEaiManager;
import com.common.file.domain.MrAtchFile;
import com.common.file.repository.MrAtchFileRepository;
import com.common.file.service.MrAtchFileService;
import com.mr.common.domain.MrRvRstVO;
import com.mr.common.service.MrRvRstService;
import com.mr.comp.domain.CompChrgrChgHistVO;
import com.mr.comp.domain.CompFileManageVO;
import com.mr.comp.domain.CompFileVO;
import com.mr.comp.domain.CompProcStepDetVO;
import com.mr.comp.domain.CompRptVO;
import com.mr.comp.repository.CompDAO;
import com.mr.comp.service.CompService;
import com.mr.jobs.domain.JobsCheckMasterVO;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrReqProcStepDetVO;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;

@Service
public class CompServiceImpl extends BaseService implements CompService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CompDAO compDAO;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    MrAtchFileService mrAtchFileService;

    @Autowired
    MrAtchFileRepository mrAtchFileRepository;

    @Autowired
    MrMailService mrMailService;

    @Autowired
    MrRvRstService mrRvRstService;
    
    @Autowired
    MrStepRepository mrStepRepository;
    
    @Override
    public CompFileVO selectCompFile(int mrReqNo) {
        return compDAO.selectCompFile(mrReqNo);
    }
    
    @Override
    public List<CompChrgrChgHistVO> selectCompLine(int mrReqNo) {
        return compDAO.selectCompLine(mrReqNo);
    }

    @Override
    public List<CompFileManageVO> selectCompFileManage(int mrReqNo) {

		// System.out.println("MR_REQ_NO  : "+mrReqNo);
        return compDAO.selectCompFileManage(mrReqNo);
    }
    
    @Override
    public List<Integer> selectCompCheckCount(int mrReqNo) {

		// System.out.println("MR_REQ_NO  : "+mrReqNo);
        return compDAO.selectCompCheckCount(mrReqNo);
    }

    @Override
    public List<CompChrgrChgHistVO> countCompFile(CompFileManageVO compFileManageVO) {
    	return compDAO.countCompFile(compFileManageVO);
    }
    @Override
    public void insertCompCheckList(CompFileVO compFileVO) {
    	if(compFileVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(compFileVO.getMrReqNo());
            updateDelYnRvRst.setItemCd("ATCH");
            mrRvRstService.updateMrRvRstDelYnItemCd(updateDelYnRvRst);

            HashMap<String, String> clcdMap = new HashMap<String, String>();
            
            for(MrRvRstVO mrRvRstVO : compFileVO.getRvRsts()) {
            	
                mrRvRstVO.setMrReqNo(compFileVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                
                if(mrRvRstVO.getItemCd() != null)
                {
                	if(mrRvRstVO.getItemCd().indexOf("ATCH_D") == 0)
                	{
                		String itemCd = mrRvRstVO.getItemCd().replace("_D", "");
                		mrRvRstVO.setItemCd(itemCd);
                		if(mrRvRstVO.getClCd01() != null)
                		{
                			mrRvRstVO.setClCd02(mrRvRstVO.getClCd01());
                			
                			String aVal = clcdMap.get(itemCd);
                            System.out.println(itemCd + " : " + aVal);
                            mrRvRstVO.setClCd01(aVal);
                            mrRvRstService.insertMrRvRst(mrRvRstVO);
                            clcdMap.remove(itemCd);
                            //logger.info("2) mrRvRstVO.getItemCd : " + mrRvRstVO.getItemCd());
                            //logger.info("2) clcdMap.size() : " + clcdMap.size());
                		}
                		
                	}else if(mrRvRstVO.getItemCd().indexOf("ATCH") == 0)
                	{
                		
                		clcdMap.put(mrRvRstVO.getItemCd(),mrRvRstVO.getClCd01());
                		//logger.info("3) mrRvRstVO.getItemCd : " + mrRvRstVO.getItemCd());
                		//logger.info("3) clcdMap.size() : " + clcdMap.size());
                	}
                	
                	
                }
                
            }
            
            //logger.info("4) clcdMap.size() : " + clcdMap.size());
            for (Object o : clcdMap.keySet()) {
            	MrRvRstVO mrRvRstVO = new MrRvRstVO();
            	mrRvRstVO.setItemCd(o.toString());
            	mrRvRstVO.setClCd01(clcdMap.get(o));
            	mrRvRstVO.setMrReqNo(compFileVO.getMrReqNo());
                //System.out.println("key : " + o.toString() + ", value : " + clcdMap.get(o));
                mrRvRstService.insertMrRvRst(mrRvRstVO);
            }
        }
    }
    @Override
    public void updateCompCheckList(CompFileManageVO compFileManageVO) {
    	if(compFileManageVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(compFileManageVO.getMrReqNo());
            /*mrRvRstService.updateTechMrRvRstDelYn(updateDelYnRvRst);*/
            
            HashMap<String, String> clcdMap = new HashMap<String, String>();
            
            for(MrRvRstVO mrRvRstVO : compFileManageVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(compFileManageVO.getMrReqNo());
                //mrRvRstVO.setMrReqProcStepDetNo(compFileManageVO.getMrReqProcStepDetNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);	// 사용하지 않는다.
                
                //logger.info("1) mrRvRstVO.getItemCd : " + mrRvRstVO.getItemCd());
                
                if(mrRvRstVO.getItemCd() != null)
                {
                	if(mrRvRstVO.getItemCd().indexOf("ATCH_D") == 0)
                	{
                		String itemCd = mrRvRstVO.getItemCd().replace("_D", "");
                		mrRvRstVO.setItemCd(itemCd);
                		if(mrRvRstVO.getClCd01() != null)
                		{
                			mrRvRstVO.setClCd02(mrRvRstVO.getClCd01());
                			
                			String aVal = clcdMap.get(itemCd);
                            System.out.println(itemCd + " : " + aVal);
                            mrRvRstVO.setClCd01(aVal);
                            mrRvRstService.updateMrRvRst(mrRvRstVO);
                            clcdMap.remove(itemCd);
                            //logger.info("2) mrRvRstVO.getItemCd : " + mrRvRstVO.getItemCd());
                            //logger.info("2) clcdMap.size() : " + clcdMap.size());
                		}
                		
                	}else if(mrRvRstVO.getItemCd().indexOf("ATCH") == 0)
                	{
                		
                		clcdMap.put(mrRvRstVO.getItemCd(),mrRvRstVO.getClCd01());
                		//logger.info("3) mrRvRstVO.getItemCd : " + mrRvRstVO.getItemCd());
                		//logger.info("3) clcdMap.size() : " + clcdMap.size());
                	}
                	
                	
                }
            }
            //logger.info("4) clcdMap.size() : " + clcdMap.size());
            for (Object o : clcdMap.keySet()) {
            	MrRvRstVO mrRvRstVO = new MrRvRstVO();
            	mrRvRstVO.setItemCd(o.toString());
            	mrRvRstVO.setClCd01(clcdMap.get(o));
            	mrRvRstVO.setMrReqNo(compFileManageVO.getMrReqNo());
                //System.out.println("key : " + o.toString() + ", value : " + clcdMap.get(o));
                mrRvRstService.updateMrRvRst(mrRvRstVO);
            }
        }
    }

    @Override
    public void insertCompFile(CompFileManageVO compFileManageVO, CompFileVO compFileVO) {

        compFileManageVO.setMrStepCd("Z0080");
        compFileManageVO.setProcStCd("Z0110");

        // 문서등록담당자가 1명이상일수 있음. 향후 추가시 대비 loop
        for (int i=0;i<compFileManageVO.getCompChrgr().size();i++) {

            //MR진행상세 등록
            CompProcStepDetVO compProcStepDetVO = new CompProcStepDetVO();

            compProcStepDetVO.setMrReqNo(compFileManageVO.getMrReqNo());
            compProcStepDetVO.setMrStepCd(compFileManageVO.getMrStepCd());
            compProcStepDetVO.setProcStCd(compFileManageVO.getProcStCd());
            compProcStepDetVO.setFstProcTrmDt(get99991231(compFileManageVO.getCompChrgr().get(i).getEndDate()));
            compDAO.insertCompProcStepDet(compProcStepDetVO);

            //MR진행담당자 등록
            CompChrgrChgHistVO compChrgrChgHistVO = new CompChrgrChgHistVO();

            logger.info(compFileManageVO.getCompChrgr().get(i).getChrgrClCd() +" : " + compFileManageVO.getCompChrgr().get(i).getChrgEmpNo());
            
            compChrgrChgHistVO.setMrReqProcStepDetNo(compProcStepDetVO.getMrReqProcStepDetNo());
            compChrgrChgHistVO.setChrgrClCd(compFileManageVO.getCompChrgr().get(i).getChrgrClCd());
            compChrgrChgHistVO.setMrReqNo(compFileManageVO.getMrReqNo());
            compChrgrChgHistVO.setProcStCd(compFileManageVO.getProcStCd());
            compChrgrChgHistVO.setChrgTeamNo(compFileManageVO.getCompChrgr().get(i).getChrgTeamNo());
            compChrgrChgHistVO.setChrgEmpNo(compFileManageVO.getCompChrgr().get(i).getChrgEmpNo());
            compChrgrChgHistVO.setThdayTeam(compFileManageVO.getCompChrgr().get(i).getThdayTeam());
            compChrgrChgHistVO.setThdayPos(compFileManageVO.getCompChrgr().get(i).getThdayPos());
            compDAO.insertCompChrgrChgHist(compChrgrChgHistVO);
        }

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(compFileManageVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(compFileManageVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(compFileManageVO.getMrReqNo());
                mrAtchFile.setMrStepCd(modifyFileCode("MrStepCd",mrAtchFile.getFileStepCd()));
                mrAtchFile.setChrgrClCd(modifyFileCode("ChrgrClCd",mrAtchFile.getFileStepCd()));

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchChrgrFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }

            }
        }

        /* 해당유무 INSERT - ATCH + _D01*/
        /*
        if(compFileVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(compFileVO.getMrReqNo());
            updateDelYnRvRst.setItemCd("ATCH");
            mrRvRstService.updateMrRvRstDelYnItemCd(updateDelYnRvRst);

            for(MrRvRstVO mrRvRstVO : compFileVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(compFileVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.insertMrRvRst(mrRvRstVO);
            }
        }
        */
        
    }

    @Override
    public void updateCompFile(CompFileManageVO compFileManageVO, CompFileVO compFileVO) {

        compFileManageVO.setMrStepCd("Z0080");
        compFileManageVO.setProcStCd("Z0110");

        for (int i=0;i<compFileManageVO.getCompChrgr().size();i++) {

            // MR진행담당자 수정
            CompChrgrChgHistVO compChrgrChgHistVO = new CompChrgrChgHistVO();

            compChrgrChgHistVO.setChrgrClCd(compFileManageVO.getCompChrgr().get(i).getChrgrClCd());
            compChrgrChgHistVO.setMrReqNo(compFileManageVO.getMrReqNo());
            compChrgrChgHistVO.setMrStepCd(compFileManageVO.getMrStepCd());
            compChrgrChgHistVO.setProcStCd(compFileManageVO.getProcStCd());
            compChrgrChgHistVO.setChrgTeamNo(compFileManageVO.getCompChrgr().get(i).getChrgTeamNo());
            compChrgrChgHistVO.setChrgEmpNo(compFileManageVO.getCompChrgr().get(i).getChrgEmpNo());
            compChrgrChgHistVO.setThdayTeam(compFileManageVO.getCompChrgr().get(i).getThdayTeam());
            compChrgrChgHistVO.setThdayPos(compFileManageVO.getCompChrgr().get(i).getThdayPos());
            compChrgrChgHistVO.setFstProcTrmDt(get99991231(compFileManageVO.getCompChrgr().get(i).getEndDate()));

            compDAO.updateCompChrgrChgHist(compChrgrChgHistVO);
            compDAO.updateCompProcStepDet(compChrgrChgHistVO);
        }

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(compFileManageVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(compFileManageVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(compFileManageVO.getMrReqNo());
                mrAtchFile.setMrStepCd(modifyFileCode("MrStepCd",mrAtchFile.getFileStepCd()));
                mrAtchFile.setChrgrClCd(modifyFileCode("ChrgrClCd",mrAtchFile.getFileStepCd()));

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchChrgrFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }

            }
        }

        /* 자료등록 여부 확인 사항 UPDATE */
        /*
        if(compFileVO.getRvRsts()!=null) {

            MrRvRstVO updateDelYnRvRst = new MrRvRstVO();
            updateDelYnRvRst.setMrReqNo(compFileVO.getMrReqNo());


            for(MrRvRstVO mrRvRstVO : compFileVO.getRvRsts()) {
                mrRvRstVO.setMrReqNo(compFileVO.getMrReqNo());
                mrRvRstVO.setMrReqProcStepDetNo(0);
                mrRvRstService.updateMrRvRst(mrRvRstVO);
            }
        }
    */
    }
    
    
    @Override
    public void requestDoc(int mrReqNo, char from, String rtnMsg) {

        // 메일송신 : MR요청번호, 본문내용:첨부파일등록요청
        // 수신자   : Z02A, Z02I
    	mrMailService.mailSendMultiDoc(mrReqNo, from, rtnMsg);
    	//mrMailService.mailDocuSend(mrReqNo);
    }
    
    
    @Override
    public void updateCompFileComp(CompFileManageVO compFileManageVO) {
        compDAO.updateCompFileComp(compFileManageVO);
        // 메일송신 : MR요청번호, 본문내용:첨부파일등록요청
        // 수신자   : JOB Engineer
        mrMailService.mailSend(compFileManageVO.getMrReqNo());
    }

    @Override
    public void requestCompFile(CompFileManageVO compFileManageVO) {

        // FILE요청 진행단계 업데이트 (FILE_PROC_CD=1)
        if (CollectionUtils.isNotEmpty(compFileManageVO.getMrAtchFiles())) {
            for(MrAtchFile mrAtchFile : compFileManageVO.getMrAtchFiles()){
                compDAO.requestCompFile(mrAtchFile);
            }
        }

        // STEP 단계 업데이트
        mrStepService.updateMrStep(compFileManageVO.getMrReqNo(), "Z0080", "Z0121", "Z02D", "Z0080", "Z02D");

        // MR_ATCH_FILE 등록요청 건수가 없으면 MR완료단계로 업데이트(Procedure호출)
        CompFileManageVO fileCnt = new CompFileManageVO();
        fileCnt = compDAO.countMrStepComp(compFileManageVO);
        if(fileCnt.getFileCnt().equals("0")) {
            compDAO.updateMrStepComp(compFileManageVO);
        } else {
            // 메일송신 : MR요청번호, 본문내용:문서관리등록요청
            // 수신자   : 문서관리담당자
            mrMailService.mailDocuSend(compFileManageVO.getMrReqNo());
        }
    }

    /**
     * FILE STEP DIV에 따른 STEP, 첨부파일 단계 셋팅
     * modNo=MrStepCd,ChrgrClCd
     * @return
     */
    public String modifyFileCode(String modNo, String modCd) {

        String code = "";
        if(modCd==null) modCd="isNull";
        if(modNo.equals("MrStepCd")) {
            switch (modCd) {
                case "0101":
                    return code="Z0010";
                case "0201":
                    return code="Z0020";
                case "0301":
                    return code="Z0030";
                case "0401":
                    return code="Z0020";
                case "0501":
                    return code="Z0020";
                case "0502":
                    return code="Z0040";
                case "0503":
                    return code="Z0040";
                case "0504":
                    return code="Z0040";
                case "0505":
                    return code="Z0040";
                case "0506":
                    return code="Z0040";
                case "0507":
                    return code="Z0040";
                case "0508":
                    return code="Z0040";
                case "0509":
                    return code="Z0040";
                case "0510":
                    return code="Z0040";
                case "0511":
                    return code="Z0040";
                case "0512":
                    return code="Z0040";
                case "0601":
                    return code="Z00R0";
                case "0602":
                    return code="Z00H0";
                case "0603":
                    return code="Z0040";
                case "0604":
                    return code="Z0040";
                case "0701":
                    return code="Z0050";
                case "0801":
                    return code="Z0070";
                case "0901":
                    return code="Z0080";
                case "1001":
                    return code="Z0090";
            }
        } else if (modNo.equals("ChrgrClCd")) {
            switch (modCd) {
                case "0101":
                    return code="Z02A";
                case "0201":
                    return code="Z02D";
                case "0301":
                    return code="Z02I";
                case "0401":
                    return code="Z02D";
                case "0501":
                    return code="Z02D";
                case "0502":
                    return code="Z02H1";
                case "0503":
                    return code="Z02H2";
                case "0504":
                    return code="Z02H3";
                case "0505":
                    return code="Z02H4";
                case "0506":
                    return code="Z02H5";
                case "0507":
                    return code="Z02H6";
                case "0508":
                    return code="Z02H7";
                case "0509":
                    return code="Z02H8";
                case "0510":
                    return code="Z02H9";
                case "0511":
                    return code="Z02H10";
                case "0512":
                    return code="Z02H11";
                case "0601":
                    return code="Z02D";
                case "0602":
                    return code="Z02J";
                case "0603":
                    return code="Z02D";
                case "0604":
                    return code="Z02D";
                case "0701":
                    return code="Z02D";
                case "0801":
                    return code="Z02A";
                case "0901":
                    return code="Z02D";
                case "1001":
                    return code="Z02D";
            }
        }

        return code;
    }

    /**
     * 9999-12-31 날짜를 Date 형태로 변환하여 반환.
     * @return
     */
    private Date get99991231(String endDate){
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
    public CompRptVO selectCompRpt(int mrReqNo) {
        List<MrAtchFile> mrAtchFiles = null;
        CompRptVO compRptVO = null;
        compRptVO = compDAO.selectCompRpt(mrReqNo);

        if(compRptVO!=null) mrAtchFiles = mrAtchFileService.getMrAtchFileList(mrReqNo, "Z0090");
        if(mrAtchFiles!=null) compRptVO.setMrAtchFiles(mrAtchFiles);

        return compRptVO;
    }

    @Override
    public CompChrgrChgHistVO countCompRpt(CompRptVO compRptVO) {
        return compDAO.countCompRpt(compRptVO);
    }
    
    
    /**
     * she 인터페이스 MR완료 승인요청 시 작동
     * P&ID, PDF가 있는지 여부 확인해서 있으면 인터페이스 테이블에 인서트
     * @return
     */
    @Override
    public void insertCompRptShe(CompRptVO compRptVO) {    	 
        compDAO.insertCompRptShe(compRptVO);
    }

    @Override
    public void insertCompRpt(CompRptVO compRptVO) {
        compDAO.updateMrReqMst(compRptVO);
        compDAO.insertCompRpt(compRptVO);
    }

    @Override
    public void updateCompRpt(CompRptVO compRptVO) {
        compDAO.updateMrReqMst(compRptVO);
        compDAO.updateCompRpt(compRptVO);

        /*첨부파일*/
        if (CollectionUtils.isNotEmpty(compRptVO.getMrAtchFiles())) {
            List<MrAtchFile> mrAtchFiles = mrAtchFileService.saveMrAtchFiles(compRptVO.getMrAtchFiles());
            for (MrAtchFile mrAtchFile : mrAtchFiles) {
                mrAtchFile.setMrReqNo(compRptVO.getMrReqNo());
                mrAtchFile.setMrStepCd(compRptVO.getMrStepCd());
                mrAtchFile.setChrgrClCd("Z02D");
                mrAtchFile.setFileStepCd("1001");

                if(!mrAtchFile.isDeleted() && mrAtchFile.isInserted()) {
                    mrAtchFileRepository.insertMrAtchFile(mrAtchFile);
                } else if(mrAtchFile.isDeleted()) {
                    mrAtchFileRepository.deleteMrAtchFile(mrAtchFile.getMrAtchFileNo());
                }

            }
        }

    }

    
    @Override
    public void insertCompCheck(CompRptVO compRptVO)
    {
    	
    	 ChrgrChgHist appInfo = mrStepRepository.selectAppStepEmp(compRptVO.getMrReqNo(),null);
         //논리 삭제
    	 
    	

    	 MrRvRstVO selectInfo = new MrRvRstVO();
         selectInfo.setMrReqNo(compRptVO.getMrReqNo());
         //selectInfo.setItemCd("RISK");

         ChrgrChgHist chrgrChgHist = mrStepRepository.selectAppLine(compRptVO.getMrReqNo(), compRptVO.getMrStepCd(), "Z02F");		// yoo Z0090
         if(chrgrChgHist == null)
         {
        	 mrStepService.insertTechEmp(compRptVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z0090", "Z0110", compRptVO.getAppLine());
         } else {
        	 mrStepService.updateTechEmp(compRptVO.getMrReqNo(), chrgrChgHist.getMrReqProcStepDetNo(),"Z0090", "Z0110", compRptVO.getAppLine());
         }
         
         /*
         if(compRptVO.getAppLine()!=null) {
             mrStepService.insertTechEmp(compRptVO.getMrReqNo(), appInfo.getMrReqProcStepNo(),"Z0090", "Z0110", compRptVO.getAppLine());
         }
    */
    }
    
    @Override
    public void compRptAppReq(CompRptVO compRptVO) {
    	
    	try {
        mrStepService.updateMrStep3(compRptVO.getMrReqNo(), "Z0090", "Z0121", "Z02F", "Z0090", "Z02F");
        
    	}catch(java.lang.NullPointerException e)
    	{
    		//insertCompCheck(compRptVO);
    		//mrStepService.updateMrStep(compRptVO.getMrReqNo(), "Z0090", "Z0121", "Z02F", "Z0090", "Z02F");
    		//logger.info("------ 승인자 임시저장이 안된 경우, 임시저장을 하고 승인요청을 한다 ---------- ");
    	}
        compRptVO.setMrStepCd("Z0090");

        //mr완료 버튼 클릭 시  she 인터페이스 
        compDAO.insertCompRptShe(compRptVO);
        
        //--HSW 2015.12.21
        //compRptVO.setProcStCd("Z0110");
        //compDAO.updateCompChrgrEffEnd(compRptVO);
        //--End
        compRptVO.setProcStCd("Z0141");
        compDAO.updateCompChrgrEffEnd(compRptVO);

        // 메일송신 : MR요청번호, 본문내용:MR완료보고 승인요청
        // 수신자   : 기술 및 타당성 검토팀장(1차)
        // hajewook 주2015.09.08 주석
        //mrMailService.mailSend(compRptVO.getMrReqNo());
    }

    @Override
    public void compRptRet(CompRptVO compRptVO) {
        compDAO.updateMrReqRet(compRptVO);
        mrStepService.insertPrevAppEmp(compRptVO.getMrReqNo(), "Z0090", "Z0141", null, "Z02D");

        // 메일송신 : MR요청번호, 본문내용:MR완료보고 승인반려
        // 수신자   : JobEngineer
        mrMailService.mailSend(compRptVO.getMrReqNo());
    }

    
    //mr완료
    @Override
    public void compRptAppr(CompRptVO compRptVO) {
        mrStepService.updateMrStep(compRptVO.getMrReqNo(), "Z0090", "Z0131", "Z02D", "Z0070", "Z02D");
        compRptVO.setMrStepCd("Z0090");
        compRptVO.setProcStCd("Z0110");
        compDAO.updateCompChrgrEffEnd(compRptVO);
        ChrgrChgHist chrgrChgHist = mrStepService.insertMrStepSingleAction(compRptVO.getMrReqNo(), "Z00Z0", "Z0132", "Z02D");
        // "Z00Z0", "Z0132", "Z02F", "Z0090");
        int mrReqProcStepDetNo = mrStepService.insertMrStepSingleAction(compRptVO.getMrReqNo(), "Z00Z0", "Z0132", "Z02F","Z0090", chrgrChgHist); //yoo 240103 승인자 저장 
        
        compRptVO.setMrStepCd("Z0090");
        compRptVO.setProcStCd("Z0110");
        compDAO.updateCompChrgrEffEnd(compRptVO);
        compRptVO.setMrStepCd("Z0090");
        compRptVO.setProcStCd("Z0121");
        compDAO.updateCompChrgrEffEnd(compRptVO);
        
        
        //MR 완료 승인 시, FST_PROC_TRM_DT를 NULL를 넣어주어야 함 yoo 241017 근데, Insert하고 Commit이 안된 상태에서 Update 불가 - 보류
        
        chrgrChgHist.setMrReqProcStepDetNo(mrReqProcStepDetNo);
        chrgrChgHist.setFstProcTrmDt(null);
        logger.info("!!!!!!!!!!!! detkey : @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + mrReqProcStepDetNo);
        //mrStepRepository.updateMrReqProcStepDetProcTrmDt(chrgrChgHist);
        
        // 메일송신 : MR요청번호, 본문내용:MR완료승인
        // 수신자   : 관련된 모든담당자
        mrMailService.mailSend(compRptVO.getMrReqNo());
    }

    @Override
    public void compRptCapex(CompRptVO compRptVO) {

        // SAP 송신정보 조회
        logger.info("### EAI Start!!");
        HashMap request = new HashMap();    // 실제로 전송할 HashMap
        HashMap IF_OUT = new HashMap();     // Output
        String capex_close = "N";

        /* CAPEX 번호 전송 */
        request.put("I_CAPEX_NO", compRptVO.getCapexNo());  /// CAPEX_NO

        /* SAP I/F 송신 - SAP,EAI 완료되면 주석제거할것(20140811) */
        try {
            // eAI Server Connect & Execute Service
            logger.info("### Eai Connect!! - " + compRptVO.getMrReqNo());
            CmEaiManager eaiCon = new CmEaiManager();
            logger.info("### CmEaiManager() Connect!!");

            /*
            'REL' Release (I0002) set
            'LKD' Locked (I0043) set/cancel
            'TECO' Technically completed (I0045) set/cancel
            'CLSD' Closed (I0046) set/cancel
            'AALK' Account assignment locked (I0064) set/cancel
            'MDLK' Master data locked (I0065) set/cancel
            'DLFL' Deletion flag (I0076) set/cancel
            */
            //String ip = "10.171.24.38:6300";
            //개발EAI
            //IF_OUT = eaiCon.executeService("172.18.1.56:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //현재 운영EAI
            //IF_OUT = eaiCon.executeService("172.18.3.110:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //logger.info("### eaiCon.executeService!!");
            //SAP S4/HANA용 EAI 검증기 테스트
            //IF_OUT = eaiCon.executeService("10.171.24.38:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //SAP S4/HANA용 EAI 운영기 테스트
            //IF_OUT = eaiCon.executeService("eai.oilbank.co.kr:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            IF_OUT = eaiCon.executeService("10.171.26.124:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            
            
            logger.info("### comp1-eaiCon.executeService!!");

            Set set = IF_OUT.keySet();
            logger.info("### IF_OUT.keySet()");

            IF_OUT = (HashMap) IF_OUT.get("IF_OUT");
            logger.info("### I_RETURN="+IF_OUT.get("I_RETURN"));

            HashMap<String, String> iReturn = (HashMap<String, String>) IF_OUT.get("I_RETURN") ;
            logger.info("### iReturn.get(CHKCD)="+iReturn.get("CHKCD"));
            if (iReturn.get("CHKCD").equals("S")) {
                capex_close = "Y";
            }else if (iReturn.get("CHKCD").equals("E")) {
                capex_close = "E";
            } else {
                capex_close = "N";
            }

        } catch(Exception ex) {
            logger.error("Exception : " + ex);
            capex_close = "E";
        }

        //capex_close="Y";
        compRptVO.setCapexClose(capex_close);
        compDAO.updateCompRptCapex(compRptVO);

    }
    
    

    
    
    
    
    @Override
    public void compRptCapexMail(CompRptVO compRptVO) {

        // SAP 송신정보 조회
        logger.info("### EAI Start!!");
        HashMap request = new HashMap();    // 실제로 전송할 HashMap
        HashMap IF_OUT = new HashMap();     // Output
        String capex_close = "N";

        /* CAPEX 번호 전송 */
        request.put("I_CAPEX_NO", compRptVO.getCapexNo());  /// CAPEX_NO

        /* SAP I/F 송신 - SAP,EAI 완료되면 주석제거할것(20140811) */
        try {
            // eAI Server Connect & Execute Service
            logger.info("### Eai Connect!!");
            CmEaiManager eaiCon = new CmEaiManager();
            logger.info("### CmEaiManager() Connect!!");

            //개발EAI
            //IF_OUT = eaiCon.executeService("172.18.1.56:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //운영EAI
            //IF_OUT = eaiCon.executeService("172.18.3.110:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //SAP S4/HANA용 EAI 검증기 테스트
            //IF_OUT = eaiCon.executeService("10.171.24.38:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //SAP S4/HANA용 EAI 운영기 테스트
            IF_OUT = eaiCon.executeService("10.171.26.124:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            //IF_OUT = eaiCon.executeService("eai.oilbank.co.kr:6300", "EDIMS_SAP_CAPEX_CLOSE.srv", "srcRoutine_Pub", request);
            
            logger.info("### comp2-eaiCon.executeService!!");

            Set set = IF_OUT.keySet();
            logger.info("### IF_OUT.keySet()");

            IF_OUT = (HashMap) IF_OUT.get("IF_OUT");
            logger.info("### I_RETURN="+IF_OUT.get("I_RETURN"));

            HashMap<String, String> iReturn = (HashMap<String, String>) IF_OUT.get("I_RETURN") ;
            logger.info("### iReturn.get(CHKCD)="+iReturn.get("CHKCD"));
            if (iReturn.get("CHKCD").equals("S")) {         // CAPEX CLOSE 상태
            	capex_close = "C";
            } else if (iReturn.get("CHKCD").equals("T")) { // CAPEX TECO 상태 
            	capex_close = "T";
            } else if (iReturn.get("CHKCD").equals("L")) {  // CAPEX 잠김 상태 
            	capex_close = "L";
            } else {
            	capex_close = "N";   //  CAPEX REL / NOT EXISTS 
            }


        } catch(Exception ex) {
            logger.error("Exception : " + ex);
            capex_close = "N";
        }

        
        compRptVO.setCapexClose(capex_close);
        

    }


}
