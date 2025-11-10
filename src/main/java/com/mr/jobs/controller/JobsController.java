package com.mr.jobs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.servlet.BaseController;
import com.mr.common.domain.MrRvRstVO;

import com.mr.jobs.domain.JobsCheckMasterVO;
import com.mr.jobs.domain.JobsPorcVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.jobs.service.JobsReviewService;
import com.mr.step.domain.MrReqProcStepVO;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechInvestVO;
import com.mr.tech.service.TechInvestService;

import com.mr.common.service.MrRvRstService;  



/**
 * 직무검토
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.16 박성룡 최초 작성
 * 2014.07.18 박성룡 PORC, HAZOP, CHECKLIST 페이지 추가
 * 2014.07.20 박성룡 팝업페이지 서브 권한 적용
 * 2014.07.23 박성룡 PORC 직무검토 완료
 * 2014.08.20 박성룡 위험성검토 추가 및 각페이지 권한 수정
 * </pre>
 */
@Controller
public class JobsController extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    JobsReviewService jobsReviewService;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    TechInvestService techInvestService;

    /**
     * 직무검토 초기 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsCheck.do")
    public String mrJobsCheck(Integer mrReqNo, Model model){
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0025");

        String modeClass= mrStepService.getModeClass(mrReqNo);

        Map map = new HashMap();
        map.put("mrReqNo", mrReqNo);
        map.put("mrStepCd", "Z0025");
        
        //직무검토1차 조회
        JobsReviewVO jobsReview = jobsReviewService.selectJobsReview(map);

        model.addAttribute("jobsReview", jobsReview );

        //PORC, HAZOP 여부 선택은 별도 권한이 필요하여 subModeClass를 이용함.
        //isolationModeClass: 기술검토항목
        String sSubModeClass = mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false);
        model.addAttribute("subModeClass",sSubModeClass);

        //투자비재산정용 모드정보
        String sCostModeClass = mrStepService.isolationModeClass(mrReqNo, "Z00I0", targetMode, null, false);
        model.addAttribute("costModeClass",sCostModeClass);
        
        
        logger.info("====================================== start model ======================================");
      
        logger.info("modeClass : " + modeClass);
        logger.info("sSubModeClass : " + sSubModeClass);
        logger.info("sCostModeClass : " + sCostModeClass);

        
        
        List<MrReqProcStepVO> lAllMrReqStep = mrStepService.selectAllMrReqStep(mrReqNo);
        
        for(MrReqProcStepVO vo : lAllMrReqStep)
        {
        	logger.info("-------------------------");

        	logger.info("vo.getMrReqProcStepNo() : " + vo.getMrReqProcStepNo());
        	logger.info("vo.getMrReqNo() : " + vo.getMrReqNo());
        	logger.info("vo.getMrStepCd() : " + vo.getMrStepCd());
        	logger.info("vo.getProcStCd() : " + vo.getProcStCd());
        	logger.info("vo.getProcStNm() : " + vo.getProcStNm());
        	logger.info("vo.getEffEndDtm() : " + vo.getEffEndDtm());
        	logger.info("vo.getEffStaDtm() : " + vo.getEffStaDtm());
        	
        	logger.info("-------------------------");
        	
        }
        logger.info("====================================== end model ======================================");
        model.addAttribute("mrSteps",lAllMrReqStep);

        if(modeClass.indexOf("JA") > -1){
            //직무검토용 모드정보
        	//model.addAttribute("isSkip","FALSE");		//Yoo
            model.addAttribute("modeClass",modeClass);
            model.addAttribute("saveURL","mrJobsReviewSave.do");
        } else {
            if(jobsReviewService.getSkipJobReviewer(mrReqNo)){
                model.addAttribute("modeClass","JAA");
                model.addAttribute("isSkip","TRUE");
                model.addAttribute("saveURL","skipMrJobsReviewSave.do");
            } else {
                model.addAttribute("modeClass",modeClass);
            }
        }
        logger.info("modeClass : " + modeClass);
        return "jobs/jobsCheckRegister";
    }
    
    /**
     * 직무검토 초기 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsReview.do")
    public String jobsReview(Integer mrReqNo, Model model){
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0040");
        Map map = new HashMap();
        map.put("mrReqNo", mrReqNo);
        map.put("mrStepCd", "Z0040");
        String modeClass= mrStepService.getModeClass(mrReqNo);

        //직무검토 조회
        JobsReviewVO jobsReview = jobsReviewService.selectJobsReview(map);

        model.addAttribute("jobsReview", jobsReview );

        //PORC, HAZOP 여부 선택은 별도 권한이 필요하여 subModeClass를 이용함.
        //isolationModeClass: 기술검토항목
        String sSubModeClass = mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false);
        model.addAttribute("subModeClass",sSubModeClass);

        //투자비재산정용 모드정보
        String sCostModeClass = mrStepService.isolationModeClass(mrReqNo, "Z00I0", targetMode, null, false);
        model.addAttribute("costModeClass",sCostModeClass);
        
        
        logger.info("====================================== start model ======================================");
      
        logger.info("modeClass : " + modeClass);
        logger.info("sSubModeClass : " + sSubModeClass);
        logger.info("sCostModeClass : " + sCostModeClass);

        List<MrReqProcStepVO> lAllMrReqStep = mrStepService.selectAllMrReqStep(mrReqNo);
        
        for(MrReqProcStepVO vo : lAllMrReqStep)
        {
        	logger.info("-------------------------");

        	logger.info("vo.getMrReqProcStepNo() : " + vo.getMrReqProcStepNo());
        	logger.info("vo.getMrReqNo() : " + vo.getMrReqNo());
        	logger.info("vo.getMrStepCd() : " + vo.getMrStepCd());
        	logger.info("vo.getProcStCd() : " + vo.getProcStCd());
        	logger.info("vo.getProcStNm() : " + vo.getProcStNm());
        	logger.info("vo.getEffEndDtm() : " + vo.getEffEndDtm());
        	logger.info("vo.getEffStaDtm() : " + vo.getEffStaDtm());
        	
        	logger.info("-------------------------");
        	
        }
        logger.info("====================================== end model ======================================");
        model.addAttribute("mrSteps",lAllMrReqStep);

        
  
        if(modeClass.indexOf("DA") > -1){
            //직무검토용 모드정보
        	//model.addAttribute("isSkip","FALSE");		//Yoo
            model.addAttribute("modeClass",modeClass);
            model.addAttribute("saveURL","mrJobsReviewSave.do");
        } else {
            if(jobsReviewService.getSkipJobReviewer(mrReqNo)){
                model.addAttribute("modeClass","DAA");
                model.addAttribute("isSkip","TRUE");
                model.addAttribute("saveURL","skipMrJobsReviewSave.do");
            } else {
                model.addAttribute("modeClass",modeClass);
            }
        }
        logger.info("modeClass : " + modeClass);
        return "jobs/jobsReviewRegister";
    }


    /**
     * 직무검토 내용 저장
     * @param jobsReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsReviewSave.do")
    public String mrJobsReviewSave(JobsReviewVO jobsReviewVO, Model model){
    	
    	//System.out.println("-----------------------------------------------");
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(0).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(0).isInserted());
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(1).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(1).isInserted());
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(2).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(2).isInserted());
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(3).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(3).isInserted());
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(4).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(4).isInserted());
    	//System.out.println("nm"+jobsReviewVO.getMrAtchFiles().get(5).getFileNm()+ "   isInsert"+ jobsReviewVO.getMrAtchFiles().get(5).isInserted());    	

        jobsReviewService.insertMrJobsReview(jobsReviewVO);
        
        if(jobsReviewVO.getMrStepCd() == null)
        	return "redirect:mrJobsReview.do?mrReqNo=" + jobsReviewVO.getMrReqNo();
        else
        {
        	if(jobsReviewVO.getMrStepCd().equals("Z0025"))
            	return "redirect:mrJobsCheck.do?mrReqNo=" + jobsReviewVO.getMrReqNo();		//yoo240806 mrJobsReviewSave메서드로 Z0025, Z0040 겸용 사용을 위한 추가
            else
            	return "redirect:mrJobsReview.do?mrReqNo=" + jobsReviewVO.getMrReqNo();
        }
        
    }

    /**
     * 직무검토 내용 저장 ( SKIP)
     * @param jobsReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/skipMrJobsReviewSave.do")
    public String skipMrJobsReviewSave(JobsReviewVO jobsReviewVO, Model model){
        jobsReviewService.insertSkipMrJobsReview(jobsReviewVO);

        if(jobsReviewVO.getMrStepCd().equals("Z0025"))
        	return "redirect:mrJobsCheck.do?mrReqNo=" + jobsReviewVO.getMrReqNo();	//yoo240806 mrJobsReviewSave메서드로 Z0025, Z0040 겸용 사용을 위한 추가
        else
        	return "redirect:mrJobsReview.do?mrReqNo=" + jobsReviewVO.getMrReqNo();
        
    }


    /**
     * 직무검토 보류
     * @param jobsReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsReviewSkip.do")
    public String mrJobsReviewSkip(JobsReviewVO jobsReviewVO, Model model){
        jobsReviewService.insertMrJobsReviewSkip(jobsReviewVO);
        return "redirect:mrJobsReview.do?mrReqNo=" + jobsReviewVO.getMrReqNo();
    }

    /**
     * 직무검토 완료처리
     * @param jobsReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsReviewComplete.do")
    public String mrJobsReviewComplete(Integer mrReqNo, Model model){
        jobsReviewService.insertMrJobsReviewComplete(mrReqNo);
        return "redirect:ivstCostRegister.do?mrReqNo=" + mrReqNo;
    }

    /**
     * PORC 위원 선정 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorc.do")
    public String mrJobsPorc(HttpServletRequest request,Integer mrReqNo, Model model){

    	String porcYn = request.getParameter("porcYn");
    	
    	
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");
        JobsPorcVO jobsPorc = jobsReviewService.selectMrJobsPorc(mrReqNo);
        
        jobsPorc.setPorcYn(porcYn);
    	System.out.println("porcYn : " + jobsPorc.getPorcYn());

        if(jobsPorc!=null && jobsPorc.getPorcNo()!=null) {
            model.addAttribute("modeClass","FFF");
        } else {
            model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false));
        }

        model.addAttribute("jobsPorc", jobsPorc);
        return "popup/jobsPorcSelect";
    }

    /**
     * 내용 공유  페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrShareContent.do")
    public String mrShareContent(HttpServletRequest request,  Integer mrReqNo, Model model){
    	
    	String porcYn = request.getParameter("porcYn");
    	String bClose = request.getParameter("c");
    	
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");
        JobsPorcVO jobsPorc = jobsReviewService.selectMrJobsPorc(mrReqNo);
        
        jobsPorc.setPorcYn("S");
    	System.out.println("porcYn : " + jobsPorc.getPorcYn());


        if(jobsPorc!=null && jobsPorc.getPorcNo()!=null) {
            model.addAttribute("modeClass","FFF");
        } else {
            model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false));
        }

        model.addAttribute("jobsPorc", jobsPorc);
        if(bClose == null)
        	return "popup/mrShareContent";
        else if(bClose.equals("c"))
        	return "popup/mrShareContent?c=c";
        else
        	return "popup/mrShareContent";
    }
    
    /**
     * PORC 위원 임시저장
     * @param jobsPorcVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorcSave.do")
    public String mrJobsPorcSave(HttpServletRequest request, JobsPorcVO jobsPorcVO, Model model){
    	String porcYn = request.getParameter("porcYn");
        jobsReviewService.insertMrJobsPorcSave(jobsPorcVO);
        if(porcYn.equals("S"))
        	return "redirect:mrShareContent.do?mrReqNo=" + jobsPorcVO.getMrReqNo();
        else
        	return "redirect:mrJobsPorc.do?mrReqNo=" + jobsPorcVO.getMrReqNo();
    }

    /**
     * PORC 위원 선정 완료
     * @param jobsPorcVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorcComplete.do")
    public String mrJobsPorcComplete(JobsPorcVO jobsPorcVO, Model model){

        jobsReviewService.insertMrJobsPorcComplete(jobsPorcVO);
        return "redirect:mrJobsPorc.do?mrReqNo=" + jobsPorcVO.getMrReqNo();
    }
    
    /**
     * PORC 위원 선정 및 메일 전송
     * @param jobsPorcVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorcMail.do")
    public String mrJobsPorcMail(JobsPorcVO jobsPorcVO, Model model){

        jobsReviewService.insertMrJobsPorcComplete(jobsPorcVO);
        
        return "redirect:mrShareContent.do?mrReqNo=" + jobsPorcVO.getMrReqNo()+ "&c=c";
    }

    /**
     * PORC 작성페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorcWrite.do")
    public String mrJobsPorcWrite(Integer mrReqNo, Model model){
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");
        model.addAttribute("jobsPorc", jobsReviewService.selectMrJobsPorc(mrReqNo));
        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, "Z02L", true));
        return "popup/jobsPorcRegister";
    }

    /**
     * PORC 작성저장
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrJobsPorcWriteSave.do")
    public String mrJobsPorcWriteSave(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertMrJobPorcWriteSave(mrRvRstVO);
        return "redirect:mrJobsPorcWrite.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * 위험성검토
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheck.do")
    public String mrRiskCheck(Integer mrReqNo, Model model){
        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");

        MrRvRstVO mrRvRstVO = jobsReviewService.selectMrRiskCheck(mrReqNo);
        //JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheck(mrReqNo);
        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheck(mrReqNo, "Z0040");
        
        if(mrRvRstVO==null || (mrRvRstVO!=null && mrRvRstVO.getMrRvRstNo()==null)) {
            model.addAttribute("saveURL", "mrRiskCheckSave.do");
        } else {
            model.addAttribute("saveURL", "mrRiskCheckUpdate.do");
        }
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        //yoo 230710 투자비 금액 가져 오기
        model.addAttribute("mrInvstCost",mrStepService.selectMrInvstCost(mrReqNo));
        model.addAttribute("riskCheck", mrRvRstVO);
        model.addAttribute("mrReqNo", mrReqNo);
        model.addAttribute("checkList", jobsCheckMasterVO);

        model.addAttribute("porcClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, "Z02L", true));

        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, null, false));
        return "jobs/riskCheck";
    }

    /**
     * 위험성검토 저장
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckSave.do")
    public String mrRiskCheckSave(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheck(mrRvRstVO);
        return "redirect:mrRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * 위험성검토 내용 수정
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckUpdate.do")
    public String mrRiskCheckUpdate(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.updateRiskCheck(mrRvRstVO);
        //202001 e-mail test JobsReviewServiceImpl에 추가
        return "redirect:mrRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }
    
    /**
     * MR수행 위험성검토 저장
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckSaveExe.do")
    public String mrRiskCheckSaveExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckExe(mrRvRstVO);
        return "redirect:mrPopRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * MR수행 위험성검토 내용 수정
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckUpdateExe.do")
    public String mrRiskCheckUpdateExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.updateRiskCheckExe(mrRvRstVO);
        //202001 e-mail test JobsReviewServiceImpl에 추가
        return "redirect:mrPopRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }


    /**
     * 위험성검토 승인요청
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckAppReq.do")
    public String mrRiskCheckAppReq(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckAppReq(mrRvRstVO);
        //201912 JobsReviewServiceImpl 에 she인터페이스 추가, porc위원 email발송 추가
        return "redirect:mrRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * 위험성검토 승인
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckApp.do")
    public String mrRiskCheckApp(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckApp(mrRvRstVO.getMrReqNo());
        return "redirect:mrRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * 위험성검토 반려
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckReturn.do")
    public String mrRiskCheckReturn(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckReturn(mrRvRstVO);
        return "redirect:mrRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    
    /**
     * MR수행 위험성검토 승인요청
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckAppReq.do")
    public String mrRiskCheckAppReqExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckAppReqExe(mrRvRstVO);
        //201912 JobsReviewServiceImpl 에 she인터페이스 추가, porc위원 email발송 추가
        return "redirect:mrPopRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * MR수행 위험성검토 승인
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckAppExe.do")
    public String mrRiskCheckAppExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckApp(mrRvRstVO.getMrReqNo());
        return "redirect:mrPopRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }

    /**
     * MR수행 위험성검토 반려
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRiskCheckReturnExe.do")
    public String mrRiskCheckReturnExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertRiskCheckReturnExe(mrRvRstVO);
        return "redirect:mrPopRiskCheck.do?mrReqNo=" + mrRvRstVO.getMrReqNo();
    }
    
    /**
     * HAZOP 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrHazopStudy.do")
    public String mrHazopStudy(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");

        MrRvRstVO mrRvRstVO = jobsReviewService.selectMrHazop(mrReqNo);

        if(mrRvRstVO==null) {
            model.addAttribute("saveURL", "mrHazopStudySave.do");
        } else {
            model.addAttribute("saveURL", "mrHazopStudyUpdate.do");
        }

        model.addAttribute("mrReqNo", mrReqNo);
        model.addAttribute("hazop", mrRvRstVO);
        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, "Z02D", false));
        return "popup/jobsHazopStudy";
    }

    /**
     * HAZOP 저장
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrHazopStudySave.do")
    public String mrHazopStudySave(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertMrHazop(mrRvRstVO);
        return "redirect:mrHazopStudy.do?mrReqNo=" + mrRvRstVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * HAZOP 내용 수정
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrHazopStudyUpdate.do")
    public String mrHazopStudyUpdate(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.updateMrHazop(mrRvRstVO);
        return "redirect:mrHazopStudy.do?mrReqNo=" + mrRvRstVO.getMrReqNo() + "&stepCd=Z0060";
    }

    
    @RequestMapping(value = "popup/mrHazopStudyExe.do")
    public String mrHazopStudyExe(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        //targetMode.add("Z0020");
        //targetMode.add("Z0030");
        //targetMode.add("Z0040");
        targetMode.add("Z0060");

        MrRvRstVO mrRvRstVO = jobsReviewService.selectMrHazopExe(mrReqNo);

        if(mrRvRstVO==null) {
            model.addAttribute("saveURL", "mrHazopStudySaveExe.do");  //mrExeHazopStudySave.do
        } else {
            model.addAttribute("saveURL", "mrHazopStudyUpdateExe.do");  //mrExeHazopStudyUpdate.do
        }

        model.addAttribute("mrReqNo", mrReqNo);
        model.addAttribute("hazop", mrRvRstVO);
        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, "Z02D", false));
        return "popup/HazopStudyExe";       //어디로 가야 하나
    }


    /**
     * HAZOP 저장
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrHazopStudySaveExe.do")
    public String mrHazopStudySaveExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.insertMrHazopExe(mrRvRstVO);
        return "redirect:mrHazopStudyExe.do?mrReqNo=" + mrRvRstVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * HAZOP 내용 수정
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrHazopStudyUpdateExe.do")
    public String mrHazopStudyUpdateExe(MrRvRstVO mrRvRstVO, Model model){
        jobsReviewService.updateMrHazopExe(mrRvRstVO);
        return "redirect:mrHazopStudyExe.do?mrReqNo=" + mrRvRstVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * CHECK LIST 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckList.do")
    public String mrCheckList(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0020");
        targetMode.add("Z0030");
        targetMode.add("Z0040");

        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheck(mrReqNo, "Z0040");
        if(jobsCheckMasterVO.getMrHazopNo()==null) {
            model.addAttribute("saveURL", "mrCheckListSave.do");
        } else {
            model.addAttribute("saveURL", "mrCheckListUpdate.do");
        }

        model.addAttribute("mrReqNo",mrReqNo);
        model.addAttribute("checkList",jobsCheckMasterVO);

        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false));
        return "popup/jobsCheckList";
    }
    
    /**
     * CHECK LIST 페이지 출력
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "print/mrCheckList.do")
    public String mrCheckListPrint(Integer mrReqNo, Model model){
        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheck(mrReqNo, "Z0040");

        if(jobsCheckMasterVO.getMrHazopNo()==null) {
            model.addAttribute("saveURL", "mrCheckListSave.do");
        } else {
            model.addAttribute("saveURL", "mrCheckListUpdate.do");
        }
        model.addAttribute("mrReqNo",mrReqNo);
        model.addAttribute("checkList",jobsCheckMasterVO);
        model.addAttribute("modeClass","PRINT");
        return "popup/jobsCheckList";
    }

    /**
     * CHECK LIST 저장
     * @param jobsCheckMasterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckListSave.do")
    public String mrCheckListSave(JobsCheckMasterVO jobsCheckMasterVO, Model model){
        jobsReviewService.insertMrCheck(jobsCheckMasterVO);
        return "redirect:mrCheckList.do?mrReqNo=" + jobsCheckMasterVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * CHECK LIST 수정
     * @param jobsCheckMasterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckListUpdate.do")
    public String mrCheckListUpdate(JobsCheckMasterVO jobsCheckMasterVO, Model model){
        jobsReviewService.updateMrCheck(jobsCheckMasterVO);
        return "redirect:mrCheckList.do?mrReqNo=" + jobsCheckMasterVO.getMrReqNo() + "&stepCd=Z0060";
    }


    /**
     * CHECK LIST 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckListExe.do")
    public String mrCheckListExe(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        //targetMode.add("Z0020");
        //targetMode.add("Z0030");
        //targetMode.add("Z0040");
        targetMode.add("Z0060");

        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheckExe(mrReqNo, "Z0060");
        if(jobsCheckMasterVO.getMrHazopNo()==null) {
            model.addAttribute("saveURL", "mrCheckListSaveExe.do");
        } else {
            model.addAttribute("saveURL", "mrCheckListUpdateExe.do");
        }

        model.addAttribute("mrReqNo",mrReqNo);
        model.addAttribute("checkList",jobsCheckMasterVO);

        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z0020", targetMode, "Z02D", false));
        return "popup/CheckListExe";  //어디로 가야 하나
    }
    
    /**
     * CHECK LIST 페이지 출력
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "print/mrCheckListExe.do")
    public String mrCheckListPrintExe(Integer mrReqNo, Model model){
        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheckExe(mrReqNo, "Z0060");

        if(jobsCheckMasterVO.getMrHazopNo()==null) {
            model.addAttribute("saveURL", "mrCheckListSaveExe.do");
        } else {
            model.addAttribute("saveURL", "mrCheckListUpdateExe.do");
        }
        model.addAttribute("mrReqNo",mrReqNo);
        model.addAttribute("checkList",jobsCheckMasterVO);
        model.addAttribute("modeClass","PRINT");
        return "popup/CheckListExe";       //어디로 가야 하나
    }

    /**
     * CHECK LIST 저장
     * @param jobsCheckMasterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckListSaveExe.do")
    public String mrCheckListSaveExe(JobsCheckMasterVO jobsCheckMasterVO, Model model){
        jobsReviewService.insertMrCheckExe(jobsCheckMasterVO);
        return "redirect:mrCheckListExe.do?mrReqNo=" + jobsCheckMasterVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * CHECK LIST 수정
     * @param jobsCheckMasterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/mrCheckListUpdateExe.do")
    public String mrCheckListUpdateExe(JobsCheckMasterVO jobsCheckMasterVO, Model model){
        jobsReviewService.updateMrCheckExe(jobsCheckMasterVO);
        return "redirect:mrCheckListExe.do?mrReqNo=" + jobsCheckMasterVO.getMrReqNo() + "&stepCd=Z0060";
    }

    /**
     * 직무검토 투자비 재산정 요청
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/techInvestCostModifyReq.do")
    public String techInvestCostModifyReq(Integer mrReqNo, Model model){
        jobsReviewService.insertInvestCostModifyReq(mrReqNo);
        return "redirect:mrJobsReview.do?mrReqNo=" + mrReqNo;
    }

    /**
     * 직무검토 투자비 재산정 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/techInvestCostModify.do")
    public String techInvestCostModify(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0040");

        TechInvestVO techInvestVO = jobsReviewService.selectMrTechInvestModify(mrReqNo);
        model.addAttribute("techInvest",techInvestVO);
        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00I0", targetMode, null, false));
        return "popup/techInvestCostModify";
    }

    /**
     * 직무검토 투자비 재산정 저장
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/techInvestCostSave.do")
    public String techInvestCostSave(TechInvestVO techInvestVO, Model model){
        jobsReviewService.insertMrTechInvest(techInvestVO);
        return "redirect:techInvestCostModify.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }


    /**
     * 직무검토 투자비 재산정 승인요청
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/investCostAppReq.do")
    public String mrInvestCostAppReq(TechInvestVO techInvestVO, Model model){
        jobsReviewService.insertInvestCostAppReq(techInvestVO);
        return "redirect:techInvestCostModify.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * 직무검토 투자비 재산정 승인
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/investCostApp.do")
    public String mrInvestCostApp(TechInvestVO techInvestVO, Model model){
        jobsReviewService.insertInvestCostApp(techInvestVO.getMrReqNo());
        return "redirect:techInvestCostModify.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * 직무검토 투자비 재산정 반려
     * @param mrRvRstVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/investCostReturn.do")
    public String mrInvestCostReturn(TechInvestVO techInvestVO, Model model){
        jobsReviewService.insertInvestCostReturn(techInvestVO);
        return "redirect:techInvestCostModify.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }
    
    /**
     * 위험성검토
     * @param mrRvRstVO
     * @param model
     * @return
     */
    
    @Autowired
    private MrRvRstService mrRvRstService;  // ← 추가
    
    @RequestMapping(value = "popup/mrPopRiskCheck.do")
    public String mrPopRiskCheck(
    	    @RequestParam(value = "mrReqNo") Integer mrReqNo,  // ← 추가
    	    @RequestParam(value = "stepCd") String stepCd,     // ← 추가
    	    Model model
    	){
        List<String> targetMode = new ArrayList<String>();
//        targetMode.add("Z0020");
//        targetMode.add("Z0030");
        targetMode.add("Z0060");
        
        MrRvRstVO mrRvRstVO = new MrRvRstVO();
        mrRvRstVO.setMrReqNo(mrReqNo);
        mrRvRstVO.setStepCd(stepCd);  // ← 먼저 설정

        //mrRvRstVO = jobsReviewService.selectMrRiskCheck(mrReqNo);
                        
        mrRvRstVO = mrRvRstService.selectMrRvRstRisk(mrRvRstVO);
        
        // stepCd 값이 존재한다면 mrStepCD 에 복사
        /*if (mrRvRstVO.getStepCd() != null && mrRvRstVO.getStepCd().equals("Z0060")) {
            mrRvRstVO.setMrStepCd(mrRvRstVO.getStepCd());  // ← 소문자 d
        }*/
        
        //여기서 mrStepCd를 보내야 할 것 같은데...
        JobsCheckMasterVO jobsCheckMasterVO = jobsReviewService.selectMrCheck(mrReqNo, "Z0060");
        
        if(mrRvRstVO==null || (mrRvRstVO!=null && mrRvRstVO.getMrRvRstNo()==null)) {
            model.addAttribute("saveURL", "mrRiskCheckSaveExe.do");
        } else {
            model.addAttribute("saveURL", "mrRiskCheckUpdateExe.do");
        }
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        //yoo 230710 투자비 금액 가져 오기
        model.addAttribute("mrInvstCost",mrStepService.selectMrInvstCost(mrReqNo));
        model.addAttribute("riskCheck", mrRvRstVO);
        model.addAttribute("mrReqNo", mrReqNo);
        model.addAttribute("stepCd", stepCd);  // ← model에 stepCd 추가 //Z0060으로 고정
        model.addAttribute("checkList", jobsCheckMasterVO);

        model.addAttribute("porcClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, "Z02L", true));

        model.addAttribute("modeClass",mrStepService.isolationModeClass(mrReqNo, "Z00R0", targetMode, null, false));
        return "popup/PopRiskCheck";
    }

}
