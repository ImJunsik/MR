package com.mr.mrrq.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.base.util.IsOperDistinc;
import com.mr.mrrq.domain.MRrqRegisterVO;
import com.mr.mrrq.service.MRrqService;
import com.mr.step.service.MrStepService;

//import com.mr.step.service.MrMailService; //메일테스트용

/**
 * 요청서 작성 컨트롤러
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * </pre>
 */
@Controller
@RequestMapping(value = "/")
public class MRrqController  extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    MRrqService mRrqService;

    @Autowired
    MrStepService mrStepService;
    
    //@Autowired
    //MrMailService mrMailService; //메일테스트용

    /**
     * 요청서 작성
     * @param mrReqNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegister.do")
    public String register(Integer mrReqNo, String mrAction, Model model){
        MRrqRegisterVO mRrqRegisterVO = null;
        String modeClass ="";
        
        if(mrReqNo == null) {
            mRrqRegisterVO = new MRrqRegisterVO();
            model.addAttribute("saveURL","mrRqRegisterSave.do");
            model.addAttribute("modeClass","AAA");
            model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(0));
        } else {
            mRrqRegisterVO = mRrqService.selectMrRreq(mrReqNo);
            modeClass = mrStepService.getModeClass(mRrqRegisterVO.getMrReqNo());
            model.addAttribute("modeClass",modeClass);
            if(modeClass.equals("AAA")) {
                model.addAttribute("saveURL","mrRqRegisterUpdate.do");
            } else {
                model.addAttribute("saveURL","mrRqStepUpdate.do");
            }

            if(modeClass.equals("ABA")) {
                model.addAttribute("appURL","mrRqRegisterAgree.do");
            } else if(modeClass.equals("ADA") || modeClass.equals("ACA")) {
                model.addAttribute("appURL","mrRqRegisterApp.do");
            }else{
            	logger.info("승인처리가 되지 않습니다. modeClass를 확인하여 주세요");
            }
   
            model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        }
        
        model.addAttribute("m_bOperDatabaseConfig", IsOperDistinc.m_bOperDatabaseConfig);
        //hajewook 추가 승인요청 액션 저장 2015.09.03
        model.addAttribute("mrAction", mrAction);
        model.addAttribute("register",mRrqRegisterVO);
        
        //테스트 용
        //System.out.println("메일보내기 시작   :: " + mRrqRegisterVO.getMrReqNo());
        //mrMailService.mailSend(mrReqNo);
        //System.out.println("메일보내기 끝   :: " + mRrqRegisterVO.getMrReqNo());
        		
        return "mrrq/mrrqRegister";
    }

    /**
     * 요청서 저장
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterSave.do")
    public String mrRqRegisterSave(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.insertMrReq(mRrqRegisterVO);
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 요청서 수정 (결재라인)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqStepUpdate.do")
    public String mrRqStepUpdate(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqStepUpdate(mRrqRegisterVO);
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 요청서 수정 (전체)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterUpdate.do")
    public String mrRqRegisterUpdate(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.updateMrReq(mRrqRegisterVO);
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 요청서 1차승인요청 처리(재상신)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterAppReq.do")
    public String mrRqRegisterAppReq(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqRegisterAppReq(mRrqRegisterVO);
        logger.info("redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo()+"&mrAction=mrRqRegisterAppReq");
      //hajewook 추가 승인요청 액션 저장 2015.09.03  콜백시 승인요청 플래그값        
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo()+"&mrAction=mrRqRegisterAppReq";
    }

    /**
     * 요청서 1차승인 승인처리 (2차승인요청)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterAgree.do")
    public String mrRqRegisterAgree(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqRegisterAgree(mRrqRegisterVO);
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 요청서 2차승인 승인처리
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterApp.do")
    public String mrRqRegisterApp(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqRegisterAgree(mRrqRegisterVO);
        return "redirect:mrTech.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 요청서 반려(1차, 2차 반려 동일하게 수행)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterReturn.do")
    public String mrRqRegisterReturn(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqRegisterReturn(mRrqRegisterVO);
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }

    /**
     * 사업취소(전체단계에서 호출하여도 됨)
     * @param mRrqRegisterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/mrRqRegisterCancel.do")
    public String mrRqRegisterCancel(MRrqRegisterVO mRrqRegisterVO, Model model){
        mRrqService.mrRqRegisterCancel(mRrqRegisterVO.getMrReqNo());
        return "redirect:mrRqRegister.do?mrReqNo=" + mRrqRegisterVO.getMrReqNo();
    }
}
