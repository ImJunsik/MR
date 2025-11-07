package com.mr.tech.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.service.TechService;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.domain.MrMailVO;
import com.mr.step.repository.MrMailDao;

/**
 * 기술 및 타당성 검토
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
public class TechController extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    //@Autowired
    //MrMailDao mrMailDao;
    
    @Autowired
    TechService techService;

    @Autowired
    MrStepService mrStepService;

    /**
     * 기술 및 타당성 검토 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTech.do")
    public String register(Integer mrReqNo, Model model){
        TechReviewVO techReviewVO = techService.selectMrTechReview(mrReqNo);
        
        if(techReviewVO.getMrTechRvNo()==0) {        	        	
            model.addAttribute("saveURL","mrTechSave.do");	//최초저장시
            logger.info("saveURL : mrTechSave.do");
        } else {        	
            model.addAttribute("saveURL","mrTechUpdate.do");     
            logger.info("saveURL : mrTechUpdate.do");
        }
        model.addAttribute("techReview",techReviewVO);
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));        
        model.addAttribute("modeClass",mrStepService.getModeClass(mrReqNo));
        return "tech/techReviewRegister";
    }

    /**
     * 기술 및 타당성 검토 저장
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechSave.do")
    public String mrTechSave(TechReviewVO techReviewVO, Model model){
    	logger.info("mrTechSave.do - " + techReviewVO.getMrReqNo());
        techService.insertMrTechReview(techReviewVO, "Z0020");
        return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

    /**
     * 기술 및 타당성 검토 수정, 기술검토항목 임시저장, 수정
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechUpdate.do")
    public String mrTechUpdate(TechReviewVO techReviewVO, Model model){
        techService.updateMrTechReview(techReviewVO);        
        return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }

    /**
     * 기술 및 타당성 검토 승인요청
     * @param techReviewVO
     * @param model
     * 기술검토항목 단계에서 다음 단계(직무검토1차)로 넘기는 절차 진행	yoo 240809
     * @return
     */
    @RequestMapping(value = "/mrTechAppReq.do")
    public String mrTechAppReq(TechReviewVO techReviewVO, Model model){
    	logger.info(techReviewVO.getMrTechRvNo() + " mrTechAppReq.do - " + techReviewVO.getMrReqNo());
    	
    	techService.insertMrTechAppReq(techReviewVO);
    //  기술항목검토 요청자 처리
    	//techService.insertMrTechAppReq2(techReviewVO);
        
        //yoo 240729 결제라인 삽입
        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0110"); //'Z0132: 승인완료'를 'Z0110: 작성중'으로 변경 함 yoo240802

      //Z002G가 중복 되는 현상이 있음 yoo 240925 담당자 중복 제거
        mrStepService.updateDetHistDelDuplication(techReviewVO.getMrReqNo(), "Z0020", techReviewVO.getLoginEmpNo());

      

        ////return "redirect:mrTechInvest.do?mrReqNo=" + techReviewVO.getMrReqNo();		//Z0030이 아닌 Z0025로 변경을 위해 주석 처리 한다
        return "redirect:mrJobsCheck.do?mrReqNo=" + techReviewVO.getMrReqNo();		//Z0030이 아닌 Z0025로 변경
    // 20150820 기술검토 승인 프로세스 제거 작업 끝
    }
    
    /**
     * 기술 및 타당성 검토 승인요청
     * @param techReviewVO
     * @param model
     * 직무검토(1)에서 완료 버튼 클릭 시 초기 투자비 단계로 넘기는 로직 진행 
     * @return
     */
    @RequestMapping(value = "/mrTechAppReq2.do")
    public String mrTechAppReq2(TechReviewVO techReviewVO, Model model){
    	logger.info(techReviewVO.getMrTechRvNo() + " - mrTechAppReq2.do - " + techReviewVO.getMrReqNo());
    	
    	
    	// 승인 요청하면서 승인 처리까지 한번에 하기 때문에 이 문장이 필요한지 의문??????????
        mrStepService.insertNextAppEmp(techReviewVO.getMrReqNo(), "Z0131");

        // 20150820 기술검토 승인 프로세스 변경 시작
        // 기술검토 요청 시 승인 로직을 함께 적용 

        //mrMailService.mailSend(techReviewVO.getMrReqNo());
        
        // 기술항목 승인 처리 및 초기투자비 산정 요청 
        //insertMrTechApp2(techReviewVO);
        
    	
    	////////////////////////////////////////////////////////////////////////
    	
    	
       	//  기술항목검토 요청자 처리
        //techService.insertMrTechAppReq(techReviewVO,"Z0025","Z0110","Z02C");
    	//techService.insertMrTechAppReq(techReviewVO,"Z0025","Z0110","Z02G");
        
        // 기술항목 승인 처리 및 초기투자비 산정 요청 
       	techService.insertMrTechApp(techReviewVO,"Z0030");
       	
       	
       	//techService.insertMrTechApp(techReviewVO);
        
        // 20150820 기술검토 승인 프로세스 제거 작업 시작
        // 리턴 페이지 변경
        //return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
        return "redirect:mrTechInvest.do?mrReqNo=" + techReviewVO.getMrReqNo();
    // 20150820 기술검토 승인 프로세스 제거 작업 끝
    }
    

    /**
     * 직무검토 1차 승인요청
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrJobsAppReq.do")
    public String mrJobsAppReq(TechReviewVO techReviewVO, Model model){
    	logger.info(techReviewVO.getMrTechRvNo() + " mrJobsAppReq.do - " + techReviewVO.getMrReqNo());
    //  기술항목검토 요청자 처리
        techService.insertMrTechAppReq(techReviewVO,"Z0030","Z0110","Z02C");

        // 20150820 기술검토 승인 프로세스 제거 작업 시작
        // 리턴 페이지 변경
        //return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
        return "redirect:mrTechInvest.do?mrReqNo=" + techReviewVO.getMrReqNo();
    // 20150820 기술검토 승인 프로세스 제거 작업 끝
    }
    
    /**
     * 기술 및 타당성 검토 승인
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechApp.do")
    public String mrTechApp(TechReviewVO techReviewVO, Model model){
    	techService.insertMrTechApp(techReviewVO,"Z0025");
    	//List<ChrgrChgHist> appline = techService.insertMrTechApp(techReviewVO);
        
        return "redirect:mrTechInvest.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }


    /**
     * 기술 및 타당성 검토 반려
     * @param techReviewVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechReturn.do")
    public String mrTechReturn(TechReviewVO techReviewVO, Model model){
        techService.insertMrTechReturn(techReviewVO);
        return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }
    
    /**
     * 사업취소(전체단계에서 호출하여도 됨)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechAppReqCancel.do")
    public String mrRqRegisterCancel(TechReviewVO techReviewVO, Model model){
    	techService.mrRqRegisterCancel(techReviewVO.getMrReqNo());
        return "redirect:mrTech.do?mrReqNo=" + techReviewVO.getMrReqNo();
    }
}
