package com.mr.ivst.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.ivst.domain.IvstCostRptVO;
import com.mr.ivst.service.IvstService;
import com.mr.jobs.service.JobsReviewService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechInvestVO;


/**
 * 투자지출 품의서 작성 컨트롤러
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
@Controller
@RequestMapping(value = "/")
public class IvstController  extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    IvstService ivstService;

    @Autowired
    MrStepService mrStepService;
    
    @Autowired
    JobsReviewService jobsReviewService;

    @RequestMapping(value = "/ivstCostRegister.do")
    public String register(Integer mrReqNo, Model model){

        IvstCostRegisterVO ivstCostRegisterVO = null;
        IvstCostRptVO ivstCostRptVO = null;

        String modeClass ="";
        String rtnJsp = "ivst/ivstCostRegister";

        ivstCostRegisterVO = ivstService.selectIvstCost(mrReqNo);	
        ivstCostRptVO = ivstService.selectIvstCostRpt(mrReqNo);
        modeClass = mrStepService.getModeClass(ivstCostRegisterVO.getMrReqNo());

        model.addAttribute("modeClass",modeClass);

        if(ivstCostRptVO==null) {
            model.addAttribute("saveURL","ivstCostRptSave.do");
        } else {
            model.addAttribute("saveURL","ivstCostRptUpdate.do");
        }
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        model.addAttribute("register",ivstCostRegisterVO);		//투자지출 품의서 모델
        model.addAttribute("ivstRpt",ivstCostRptVO);

        return rtnJsp;
    }

    //투자구분, 투자목적 추가
    @RequestMapping(value = "/ivstCostRptSave.do")
    public String ivstCostRptSave(IvstCostRptVO ivstCostRptVO, IvstCostRegisterVO ivstCostRegisterVO,  Model model){
        ivstService.insertIvstCostRpt(ivstCostRptVO);
        
        //System.out.println("## Log : " + ivstCostRegisterVO.toString());
        //wj투자구분, 투자목적 추가
        ivstService.insertIvstCostRpt_2(ivstCostRegisterVO);
        
        return "redirect:ivstCostRegister.do?mrReqNo=" + ivstCostRptVO.getMrReqNo();
    }


    @RequestMapping(value = "/ivstCostRptUpdate.do")
    public String ivstCostRptUpdate(IvstCostRptVO ivstCostRptVO, IvstCostRegisterVO ivstCostRegisterVO, Model model){
        ivstService.updateIvstCostRpt(ivstCostRptVO);
        ivstService.insertIvstCostRpt_2(ivstCostRegisterVO);
        return "redirect:ivstCostRegister.do?mrReqNo=" + ivstCostRptVO.getMrReqNo();
    }

    /*
     * yoo 241224 품의 번호와 함께 에러 메시지도 리턴하도록 변경 함
     * 투자지출 품의서 SAP 전송 - Servlet (Abaper 확인)
     */
    @RequestMapping(value = "/ivstCostRptSend.do")
    public String ivstCostRptSend(IvstCostRptVO ivstCostRptVO, Model model){
    	
    	logger.info("@@@ ivstCostRptVO=" + ivstCostRptVO);
    	
        IvstCostRptVO ivstCostRptCheck = null;
        ivstCostRptCheck = ivstService.selectIvstCostRpt(ivstCostRptVO.getMrReqNo());

        logger.info("@@@ ivstCostRptCheck=" + ivstCostRptCheck);
        
        if (ivstCostRptCheck==null) {
            ivstService.insertIvstCostRpt(ivstCostRptVO);
        } else {
        	
            ivstService.updateIvstCostRpt(ivstCostRptVO);
        }
        
        logger.info("@@@ ivstCostRptCheck=aaaaaa");
        
        ivstService.sendIvstCostRpt(ivstCostRptVO);
        logger.info("@@@ ivstCostRptCheck=bbbbbb");
        return "redirect:ivstCostRegister.do?mrReqNo=" + ivstCostRptVO.getMrReqNo();

        //yoo 241224 품의 번호와 함께 에러 메시지도 리턴하도록 변경 함 
        
    }

    /*
     * 투자지출 품의를 MR 수행으로 SKIP 처리함.
     */
    @RequestMapping(value = "/ivstCostRptSkip.do")
    public String ivstCostRptSkip(IvstCostRptVO ivstCostRptVO, Model model){

        IvstCostRptVO ivstCostRptCheck = null;
        ivstCostRptCheck = ivstService.selectIvstCostRpt(ivstCostRptVO.getMrReqNo());

        if (ivstCostRptCheck==null) {
            ivstService.insertIvstCostRpt(ivstCostRptVO);
        } else {
            ivstService.updateIvstCostRpt(ivstCostRptVO);
        }
        ivstService.skipIvstCostRpt(ivstCostRptVO);
        return "redirect:ivstCostRegister.do?mrReqNo=" + ivstCostRptVO.getMrReqNo();
    }

    /*
     * MR 종료 - Popup
     */
    @RequestMapping(value = "popup/ivstCostRptEndPop.do")
    public String ivstCostRptEndPop(IvstCostRptVO ivstCostRptVO, Model model){
        return "ivst/ivstCostEndPop";
    }

    /*
     * MR 종료 - 끝!
     */
    @RequestMapping(value = "popup/ivstCostRptEnd.do")
    public String ivstCostRptEnd(IvstCostRptVO ivstCostRptVO, Model model){
        ivstService.endIvstCostRpt(ivstCostRptVO);
        return "ivst/ivstCostRegister";
    }
    
    /**
     * 직무검토 투자비 재산정 페이지
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/ivstInvestCostModify.do")
    public String ivstInvestCostModify(Integer mrReqNo, Model model){

        List<String> targetMode = new ArrayList<String>();
        targetMode.add("Z0050");

        TechInvestVO techInvestVO = jobsReviewService.selectMrTechInvestModify(mrReqNo);
        model.addAttribute("techInvest",techInvestVO);
        model.addAttribute("modeClass",mrStepService.getModeClass(techInvestVO.getMrReqNo()));
        return "popup/ivstInvestCostModify";
    }

    /**
     * 직무검토 투자비 재산정 저장
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/popup/ivstInvestCostSave.do")
    public String ivstInvestCostSave(TechInvestVO techInvestVO, Model model){
        jobsReviewService.insertMrTechInvest(techInvestVO);
        return "redirect:ivstInvestCostModify.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

}
