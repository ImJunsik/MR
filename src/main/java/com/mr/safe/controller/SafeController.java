package com.mr.safe.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.mr.safe.domain.SafeCheckVO;
import com.mr.safe.domain.SafeChrgrChgHistVO;
import com.mr.safe.service.SafeService;
import com.mr.step.service.MrStepService;


/**
 * 가동전 안전점검 작성 컨트롤러
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
@Controller
@RequestMapping(value = "/")
public class SafeController  extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    SafeService safeService;

    @Autowired
    MrStepService mrStepService;

    /*
     * MR사업수행
     */
    @RequestMapping(value = "/safeCheckExe.do")
    public String registerExe(Integer mrReqNo, Model model){

        SafeCheckVO safeCheckVO = null;
        SafeChrgrChgHistVO safeCheckCount = null;

        String modeClass ="";
        String rtnJsp = "safe/safeCheckExe";
        
        safeCheckVO = safeService.selectSafeCheck(mrReqNo,"Z0060");

        safeCheckVO.setMrStepCd("Z0060");
        
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);
        modeClass = mrStepService.getModeClass(safeCheckVO.getMrReqNo());

        model.addAttribute("modeClass",modeClass);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            model.addAttribute("saveURL","safeCheckSave.do");
        } else {
            model.addAttribute("saveURL","safeCheckUpdate.do");
        }
        
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        model.addAttribute("register",safeCheckVO);
        return rtnJsp;
    }

    /*
     * 201905 mr수행 임시저장(Insert)
     */
    @RequestMapping(value = "/safeCheckSave2.do")
    public String safeCheckSave2(SafeCheckVO safeCheckVO, Model model){
        //safeService.insertSafeCheck2(safeCheckVO);
    	//safeCheckExe.do(mr수행)
        return "redirect:safeCheckExe.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }

    /*
     * 201905 mr수행 임시저장(Update)
     */
    @RequestMapping(value = "/safeCheckUpdate2.do")
    public String safeCheckUpdate2(SafeCheckVO safeCheckVO, Model model){
        //safeService.updateSafeCheck2(safeCheckVO);
        return "redirect:safeCheckExe.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }

    /*
     * mr수행 임시저장 file
     */
    @RequestMapping(value = "/safeCheckAppr2.do")
    public String safeCheckAppr2(SafeCheckVO safeCheckVO, Model model){
        safeService.apprSafeCheck2(safeCheckVO);
        return "redirect:safeCheckExe.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }

    /*
     * 201905 MR수행 기술검토확인
     */
    @RequestMapping(value = "/safeCheckZ02d.do")
    public String safeCheckZ02d(SafeCheckVO safeCheckVO, Model model){
    	safeService.apprSafeCheck2(safeCheckVO);  		//파일저장
        safeService.updateCheckZ02d(safeCheckVO);       //기술검토확인  
        return "redirect:safeCheckExe.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }
    
    /*
     * 가동전안전점검 작성
     */
    @RequestMapping(value = "/safeCheckRegister.do")
    public String register(Integer mrReqNo, Model model){

        SafeCheckVO safeCheckVO = null;
        SafeChrgrChgHistVO safeCheckCount = null;

        String modeClass ="";
        String rtnJsp = "safe/safeCheckRegister";

        safeCheckVO = safeService.selectSafeCheck(mrReqNo,"Z0060");
        
        safeCheckVO.setMrStepCd("Z0060");  // ← 추가
        
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);
        modeClass = mrStepService.getModeClass(safeCheckVO.getMrReqNo());

        model.addAttribute("modeClass",modeClass);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            model.addAttribute("saveURL","safeCheckSave.do");
        } else {
            model.addAttribute("saveURL","safeCheckUpdate.do");
        }
        model.addAttribute("mrSteps",mrStepService.selectAllMrReqStep(mrReqNo));
        model.addAttribute("register",safeCheckVO);
        return rtnJsp;
    }

    /*
     * 가동전안전점검 임시저장(Insert)
     */
    @RequestMapping(value = "/safeCheckSave.do")
    public String safeCheckSave(SafeCheckVO safeCheckVO, Model model){
        safeService.insertSafeCheck(safeCheckVO, true);
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }

    /*
     * 가동전안전점검 임시저장(Update)
     */
    @RequestMapping(value = "/safeCheckUpdate.do")
    public String safeCheckUpdate(SafeCheckVO safeCheckVO, Model model){
        safeService.updateSafeCheck(safeCheckVO, true);
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }   

    /*
     * 가동전안전점검 완료
     */
    /*@RequestMapping(value = "/safeCheckComp.do") 
    public String safeCheckComp(SafeCheckVO safeCheckVO, Model model){

        SafeChrgrChgHistVO safeCheckCount = null;
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            safeService.insertSafeCheck(safeCheckVO);
        } else {
            safeService.updateSafeCheck(safeCheckVO);
        }
        
        //가동전 안전점검 완료시 mr->she 인터페이스 추가
        safeService.compSafeCheck(safeCheckVO);
        
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }*/

    /*
     * MR수행 완료
     */
    @RequestMapping(value = "/safeCheckComp.do") 
    public String safeCheckCompMR(SafeCheckVO safeCheckVO, Model model){

        SafeChrgrChgHistVO safeCheckCount = null;
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            safeService.insertSafeCheck(safeCheckVO, false);
        } else {
            safeService.updateSafeCheck(safeCheckVO, false);
        }
        
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }   

    /*
     * 가동전 안전점검 완료
     */
    @RequestMapping(value = "/safeCheckComp2.do") 
    public String safeCheckComp(SafeCheckVO safeCheckVO, Model model){

        SafeChrgrChgHistVO safeCheckCount = null;
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            safeService.insertSafeCheck(safeCheckVO, true);
        } else {
            safeService.updateSafeCheck(safeCheckVO, true);
        }
        
        //가동전 안전점검 완료시 mr->she 인터페이스 추가
        safeService.compSafeCheck(safeCheckVO);
        
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }

    /*
     * 설계팀 MR완료 MR요청자에게 가동전안전점검 실시 요청
     */
    @RequestMapping(value = "/safeCheckConf.do")
    public String safeCheckConf(SafeCheckVO safeCheckVO, Model model){

    	safeService.safeCheckConf(safeCheckVO);
        
        ////////////////////////////////////////////////////// yoo 2022-10-07 MR수행 완료 시, 완료 처리까지 수행
        
        SafeChrgrChgHistVO safeCheckCount = null;
        safeCheckCount = safeService.countSafeCheck(safeCheckVO);

        if(safeCheckCount.getCheckCnt().equals("0")) {
            safeService.insertSafeCheck(safeCheckVO, false);
        } else {
            safeService.updateSafeCheck(safeCheckVO, false);
        }
        
        return "redirect:safeCheckRegister.do?mrReqNo=" + safeCheckVO.getMrReqNo();
        
        //////////////////////////////////////////////////////
        //return "redirect:safeCheckExe.do?mrReqNo=" + safeCheckVO.getMrReqNo();

    }        
        
    /*
     * 가동전안전점검 승인
     */
    @RequestMapping(value = "/safeCheckAppr.do")
    public String safeCheckAppr(SafeCheckVO safeCheckVO, Model model){

        safeService.apprSafeCheck(safeCheckVO);
        return "redirect:compFileManage.do?mrReqNo=" + safeCheckVO.getMrReqNo();
    }
}
