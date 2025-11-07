package com.mr.tech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.servlet.BaseController;
import com.base.util.IsOperDistinc;
import com.mr.step.domain.ChrgrChgHist;
import com.mr.step.repository.MrStepRepository;
import com.mr.step.service.MrMailService;
import com.mr.step.service.MrStepService;
import com.mr.tech.domain.TechInvestVO;
import com.mr.tech.domain.TechReviewVO;
import com.mr.tech.service.TechInvestService;
import com.mr.tech.service.TechService;

/**
 * 기술 및 타당성 검토 투자비산정
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 박성룡 최초 작성
 * 2019.04    투자비 산정 반송로직 추가
 * </pre>
 */
@Controller
public class TechInvestController extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    TechInvestService techInvestService;

    @Autowired
    MrStepService mrStepService;

    @Autowired
    TechService techService;
    
    @Autowired
    MrMailService mrMailService;

    @Autowired
    MrStepRepository mrStepRepository;

    /**
     * 초기투자비 산정 페이지 조회
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvest.do")
    public String register(Integer mrReqNo, String mrAction, Model model){
    	TechInvestVO techInvestVO = techInvestService.selectMrTechInvest(mrReqNo);
        model.addAttribute("m_bOperDatabaseConfig", IsOperDistinc.m_bOperDatabaseConfig);
        model.addAttribute("techInvest",techInvestVO);
        logger.info(mrReqNo + " 1. - mrReqNo === mrTechInvest.do - techInvestVO.chrgDetNo:" + techInvestVO.getChrgDetNo());
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        String modeClass = mrStepService.getModeClass(mrReqNo);
        model.addAttribute("modeClass",modeClass);
        model.addAttribute("mrAction",mrAction);//승인요청 상태값 저장
        logger.info(mrReqNo + " 2. - mrReqNo === mrTechInvest.do - mrAction:" + mrAction);
        logger.info("techInvestCostRegister?mrReqNo=" + mrReqNo+"&mrAction="+mrAction+"&modeClass="+modeClass);
        return "tech/techInvestCostRegister";
    }

    
    //20210608 Insert Commit를 위한 메소드 분할
    @RequestMapping(value = "/mrTechInvestRead.do")
    public String read(Integer mrReqNo, Integer mrAction, Model model){
        logger.info(mrReqNo + " 1. - mrReqNo === mrTechInvestRead.do - mrAction:" + mrAction);
        mrStepService.readProcess(mrReqNo, "Z0030", "Z0121", "Z02G", "Z0020", "Z02G", mrAction);
        logger.info("redirect:mrTechInvest.do?mrReqNo=" + mrReqNo+"&mrAction=mrTechInvestAppReq");
        return "redirect:mrTechInvest.do?mrReqNo=" + mrReqNo+"&mrAction=mrTechInvestAppReq";			//mrTechInvestAppReq
    }
    
    // 20210614 Insert Commit를 위한 메소드 분할 
    @RequestMapping(value = "/mrTechInvestReadAndMail.do")
    public String readAndMail(Integer mrReqNo, Integer mrAction, Model model){
        logger.info(mrReqNo + " - mrReqNo === mrTechInvestReadAndMail.do - mrAction:" + mrAction);
        mrStepService.readProcess(mrReqNo, "Z0030", "Z0121", "Z02G", "Z0020", "Z02G", mrAction);
        mrMailService.addCc("Z0030 ", "Z02I");
        mrMailService.mailSend(mrReqNo);
        logger.info("mrTechInvestReadAndMail.do - END");
        return "";
    }
    
    
    /**
     * Project Engineer지정
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestLineSet.do")
    public String mrTechInvestLineSet(TechInvestVO techInvestVO, Model model){
    	
        techInvestService.insertMrTechInvestSetWriter(techInvestVO);
        return "redirect:mrTechInvest.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * 투자비 산정 저장(임시저장)
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestSave.do")
    public String mrTechInvestSave(TechInvestVO techInvestVO, Model model){    	
        techInvestService.insertMrTechInvest(techInvestVO);
        return "redirect:mrTechInvest.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * 초기 투자비 산정 승인요청 버튼클릭 
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestAppReq.do")
    public String mrTechInvestAppReq(TechInvestVO techInvestVO, Model model ){
    	
    	// 투자비산정내용 승인요청 수행팀 팀장
        int mrReqProcStepDetNo = techInvestService.insertMrTechInvestAppReq(techInvestVO);
        
        
        return "redirect:mrTechInvestRead.do?mrReqNo=" + techInvestVO.getMrReqNo()+"&mrAction=" + mrReqProcStepDetNo;
        //return "redirect:mrTechInvest.do?mrReqNo=" + techInvestVO.getMrReqNo()+"&mrAction=mrTechInvestAppReq";//		yoo commit를 위한 메소드 분리
    }
    
    /**
     * yoo 240205
     * 초기 투자비 산정 사업수행팀 코드 조회
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestSearchTeam.do", method=RequestMethod.POST)
    public Model mrTechInvestSearch(String mrReqNo, Model model){
    	
    	logger.info("---------- mrTechInvestSearch.do ------------ " + mrReqNo);
    	HashMap<String,String> map = techInvestService.mrTechInvestSearchTeam(mrReqNo);

        model.addAttribute("chrgTeamNoList",map);
    	return model;
    }

    /**
     * 투자비 산정 결재 승인
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestApp.do")
    public String mrTechInvestApp(TechInvestVO techInvestVO, Model model){
        techInvestService.insertMrTechInvestApp(techInvestVO.getMrReqNo());
        
        return "redirect:mrComplete.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * skip 기능 (사용 하지 않음)
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestSkip.do")
    public String mrTechInvestSkip(TechInvestVO techInvestVO, Model model){
        techInvestService.insertMrTechInvestApp(techInvestVO.getMrReqNo());
        return "redirect:mrTechInvest.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

    /**
     * 투자비 산정 반려
     * @param techInvestVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrTechInvestReturn.do")
    public String mrTechInvestReturn(TechInvestVO techInvestVO, Model model){
    	for(int i=0; i<techInvestVO.getAppLine().size(); i++){
    		ChrgrChgHist vo = techInvestVO.getAppLine().get(i);    	
    	}   	
    	
        techInvestService.insertMrTechInvestReturn(techInvestVO);
        
        return "redirect:mrTechInvest.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }
    
    /**
     * (201904추가)투자비 산정 반송
     * @param techInvestVO
     */
    @RequestMapping(value = "/mrTechInvestBack.do")
    public String mrTechInvestBack(TechInvestVO techInvestVO, Model model){
    	//System.out.println("wj:반송처리 시작(mrTechInvestBack)  : " );
    	logger.info(techInvestVO.getMrReqNo() + " updateMrTechInvestBack : " + techInvestVO.getMrReqProcStepNo());
    	//투자비 산정 반송
        techInvestService.updateMrTechInvestBack(techInvestVO);
        
        //반송완료 후 기술검토항목 조회
        return "redirect:mrTech.do?mrReqNo=" + techInvestVO.getMrReqNo();
    }

}
