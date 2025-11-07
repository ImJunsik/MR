package com.mr.tech.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.base.util.IsOperDistinc;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.service.MrCompleteService;

/**
 * 기술 및 타당성 검토 DB
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
@Controller
public class MrCompleteController  extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MrCompleteService mrCompleteService;

    @Autowired
    MrStepService mrStepService;

    //yoo 240924
    private String ApproverVerdict(TechReviewVO techReviewVO, Model model)
    {
    	String appGubun = techReviewVO.getConnIfStatGubun();
    	// 화면에 1차 승인자, 2차 승인자에 의한 판정
        if(techReviewVO.getAppLine()!=null){
            for(ChrgrChgHist app : techReviewVO.getAppLine()) {
                if(app.getChrgrClCd().contains("Z02F")) {		//	1차 승인
                	if(app.getLoginEmpNo().equals(app.getChrgEmpNo()))
                	{
                		appGubun += "0";
                		logger.info("화면 버튼 중간승인자 - 1차 승인자 :  " + appGubun);
                		model.addAttribute("appURL","mrCompleteAgree.do");		// 1. 타당성 검토 승인 요청 - 초기투자비 산정 확인 완료
                		break;
                	}
                }else if(app.getChrgrClCd().contains("Z02C")) {		//	2차 승인
                	if(app.getLoginEmpNo().equals(app.getChrgEmpNo()))
                	{
                		appGubun += "1";
                		logger.info("화면 버튼 중간승인자 - 2차 승인자 :  " + appGubun);
                		model.addAttribute("appURL","mrCompleteApp.do");		// 2. 타당성 검토 승인  - 직무검토단계로 넘기는 처리
                		break;
                	}
                }
            }
        }
        
     // 카운트에 의한 판정 1차 승인자, 2차 승인자에 의한 판정
        if(appGubun.indexOf(":") > -1)
        {
	        String[] appType = appGubun.split(":");
	        if(appType[0].equals("1") && appType[0].equals(appType[2])) {
	        	logger.info("버튼 최종승인자 - 2차 승인자  :" + appGubun);
	        	logger.info("appURL : mrCompleteApp.do");
	            model.addAttribute("appURL","mrCompleteApp.do");		// 2. 타당성 검토 승인  - 직무검토단계로 넘기는 처리
	        } else{
	        	logger.info("버튼 중간승인자 - 1차 승인자 :  " + appGubun);
	        	logger.info("appURL : mrCompleteAgree.do");
	            model.addAttribute("appURL","mrCompleteAgree.do");		// 1. 타당성 검토 승인 요청 - 초기투자비 산정 확인 완료

	        	logger.info("1차 승인이거나 아직 승인자가 지정이 안되거나 파악 오류 !!!!!!!!! " + appGubun);
	        	
	        	if(appType[2].equals(" 1")) {
	            	logger.info("버튼 최종승인자 - 2차 승인자  :" + appGubun);
	            	logger.info("appURL : mrCompleteApp.do");
	                model.addAttribute("appURL","mrCompleteApp.do");		// 2. 타당성 검토 승인  - 직무검토단계로 넘기는 처리
	            } else if(appType[2].equals(" 0")){
	            	logger.info("버튼 중간승인자 - 1차 승인자 :  " + appGubun);
	            	logger.info("appURL : mrCompleteAgree.do");
	                model.addAttribute("appURL","mrCompleteAgree.do");		// 1. 타당성 검토 승인 요청 - 초기투자비 산정 확인 완료
	            }
	        }
        }
        return appGubun;
    }
    /**
     * MR 완료 페이지 (타당성검토확인 임시저장)
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrComplete.do")
    public String mrComplete(Integer mrReqNo, String mrAction, Model model){

        TechReviewVO techReviewVO = mrCompleteService.selectMrComplete(mrReqNo);
        String modeClass = mrStepService.getModeClass(mrReqNo);

        if(techReviewVO.getMrTechRvNo()==0) {
        	logger.info("saveURL - mrTechSave.do");
            model.addAttribute("saveURL","mrTechSave.do");
        } else {
        	logger.info("saveURL - mrTechUpdate.do");
            model.addAttribute("saveURL","mrTechUpdate.do");
        }
        logger.info("modeClass : " + modeClass);
        
      //yoo240924 전자결재 인터페이스 로직과 동일하게 처리 하기 위해 변경 처리
        ApproverVerdict(techReviewVO, model);
        
        model.addAttribute("m_bOperDatabaseConfig", IsOperDistinc.m_bOperDatabaseConfig);
        model.addAttribute("techReview",techReviewVO);
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        model.addAttribute("modeClass",modeClass);
        
        //hajewook 추가 승인요청 액션 저장 2015.09.08
        model.addAttribute("mrAction", mrAction);

        return "tech/mrCompleteRegister";
    }

    /**
     * MR 완료 내용 저장 (타당성검토확인 임시저장)
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrCompleteSave.do")
    public String mrCompleteSave(TechReviewVO techReviewVO, Model model){
        mrCompleteService.updateMrTechReview2(techReviewVO);
        return "redirect:mrComplete.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

    /**
     * MR 완료 1차 승인 요청(타당성검토 승인요청)
     * @param techReviewVO
     * @param model
     * @return
     */    
    @RequestMapping(value = "/mrCompleteAppReq.do")
    public String mrCompleteAppReq(TechReviewVO techReviewVO, Model model){
    	//저장을 안하고 승인요청을 클릭할 수 있으므로 내부적으로 임시저장 후 승인요청 로직을 수행
        mrCompleteService.insertMrCompleteAppReq(techReviewVO);        
        
        //hajewook 추가 승인요청 액션 저장 2015.09.03  콜백시 승인요청 플래그값        
        return "redirect:mrComplete.do?mrReqNo=" + techReviewVO.getMrReqNo()+"&mrAction=mrCompleteAppReq";
    }

    /**
     * MR 타당성검토 1차 승인
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrCompleteAgree.do")
    public String mrCompleteAgree(TechReviewVO techReviewVO, Model model){
        mrCompleteService.insertMrCompleteAgree(techReviewVO.getMrReqNo());
        return "redirect:mrComplete.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

    /**
     * MR 타당성검토 2차 승인
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrCompleteApp.do")
    public String mrCompleteApp(TechReviewVO techReviewVO, Model model){
        mrCompleteService.insertMrCompleteApp(techReviewVO);
        //2차승인 후 직무검토2차 단계로 넘어감 
        return "redirect:mrJobsReview.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

    /**
     * MR완료 반려
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrCompleteReturn.do")
    public String mrCompleteReturn(TechReviewVO techReviewVO, Model model){
        mrCompleteService.insertMrTechReturn(techReviewVO);
        return "redirect:mrComplete.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

}
