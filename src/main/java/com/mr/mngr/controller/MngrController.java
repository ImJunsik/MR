package com.mr.mngr.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.common.file.domain.MrAtchFile;
import com.mr.common.domain.Authentication;
import com.mr.common.domain.CommCdVO;
import com.mr.ivst.domain.IvstCostRegisterVO;
import com.mr.jobs.domain.JobsReviewVO;
import com.mr.mngr.service.MngrService;
import com.mr.mrrq.domain.MrAlterVO;
import com.mr.step.domain.ChrgrChgHist;

/**
 * 관리자컨트롤러
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.08.04 박성룡 최초 작성
 * </pre>
 */

@Controller
@RequestMapping(value = "/")
public class MngrController extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MngrService mngrService;

    @RequestMapping(value = "/mngrCode.do")
    public String mngrCode(Model model) {
        return "mngr/mngrCode";
    }


    @RequestMapping(value = "popup/mngrCodeRegister.do")
    public String mngrCodeRegister(CommCdVO inCommCdVO, Model model) {
        CommCdVO commCdVO = null;
        if(inCommCdVO.getMrCommGrpCd() != null &&inCommCdVO.getMrCommCd() != null){
            commCdVO = mngrService.selectCode(inCommCdVO);
            if(commCdVO!=null ){
                model.addAttribute("saveURL", "mngrCodeUpdate.do");
                model.addAttribute("commCdVO", commCdVO);
            }
        } else {
            model.addAttribute("saveURL", "mngrCodeNew.do");
        }
        return "popup/mngrCodeRegister";
    }


    @RequestMapping(value = "popup/mngrCodeNew.do")
    public String mngrCodeNew(CommCdVO inCommCdVO, Model model) {
        mngrService.insertCode(inCommCdVO);
        model.addAttribute("message", "정상적으로 등록되었습니다.");
        return "popup/mngrCodeRegister";
    }


    @RequestMapping(value = "popup/mngrCodeUpdate.do")
    public String mngrCodeUpdate(CommCdVO inCommCdVO, Model model) {
        mngrService.updateCode(inCommCdVO);
        model.addAttribute("message", "정상적으로 수정되었습니다.");
        return "popup/mngrCodeRegister";
    }


    @RequestMapping(value = "mngrCodeDelete.do")
    public String mngrCodeDelete(CommCdVO inCommCdVO, Model model) {
        int deleteCount = mngrService.updateCodeDelYn(inCommCdVO);
        model.addAttribute("message", "총 "+deleteCount+" 건이 정상적으로 삭제되었습니다.");
        return "redirect:mngrCode.do";
    }



    @RequestMapping(value = "/mngrCodeAjax.do")
    public Model mngrCodeAjax(CommCdVO inCommCdVO, Model model) {

        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = mngrService.selectCodeList(inCommCdVO);
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("mrCommGrpNm", commCdVO.getMrCommGrpNm()==null ? " " : commCdVO.getMrCommGrpNm());
                codeValue.put("mrCommGrpCd", commCdVO.getMrCommGrpCd()==null ? " " : commCdVO.getMrCommGrpCd());
                codeValue.put("mrCommNm", commCdVO.getMrCommNm()==null ? " " : commCdVO.getMrCommNm());
                codeValue.put("mrCommCd", commCdVO.getMrCommCd()==null ? " " : commCdVO.getMrCommCd());
                codeValue.put("priority", commCdVO.getPriority()==null ? " " : commCdVO.getPriority());
                codeValue.put("sapNm", commCdVO.getSapNm()==null ? " " : commCdVO.getSapNm());
                codeValue.put("sapCd", commCdVO.getSapCd()==null ? " " : commCdVO.getSapCd());
                codeValue.put("delYn", commCdVO.getDelYn()==0 ? " " : "O" );
                mrSearchList.add(codeValue);
                model.addAttribute("list",mrSearchList);
            }
        }
        return model;
    }

    //mr정보 저장
    @RequestMapping(value = "/mngrChange.do")
    public String mngrChange(Model model) {
        return "mngr/mngrChange";
    }
    
    //mr정보 변경
    @RequestMapping(value = "/mngrAlter.do")
    public String mrChange(Model model) {
        return "mngr/mngrAlter";
    }

    //mr정보New
    @RequestMapping(value = "/mngrChangeNew.do")
    public String mngrChangeNew(Model model) {
        return "mngr/mngrChangeNew";
    }

    @RequestMapping(value = "/mngrChangeAjax.do")
    public Model mngrChangeAjax(Integer mrReqNo, Model model) {
        List<Map> searchList = new ArrayList<Map>();
        Map<String, String> value;
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = null;
        try {
            if(mrReqNo !=null ){
            	//selectMngrChangeList(mr정보 리스트 조회)
                List<ChrgrChgHist> appLineList = mngrService.selectMngrChangeList(mrReqNo);
                if(appLineList!=null) {
                    for(ChrgrChgHist chrgrChgHist : appLineList){
                        value = new LinkedHashMap<String, String>();
                        value.put("status", chrgrChgHist.getStatus());
                        value.put("mrReqProcStepDetNo", chrgrChgHist.getMrReqProcStepDetNo()+"");
                        value.put("mrStepCd", chrgrChgHist.getMrStepCd());
                        value.put("procStCd", chrgrChgHist.getProcStCd());
                        value.put("chrgrClCd", chrgrChgHist.getChrgrClCd());
                        value.put("chrgEmpName", chrgrChgHist.getChrgEmpName());
                        value.put("chrgEmpNo", chrgrChgHist.getChrgEmpNo());
                        value.put("chrgTeamNo", chrgrChgHist.getChrgTeamNo());
                        value.put("thdayTeam", chrgrChgHist.getThdayTeam());
                        value.put("thdayPos", chrgrChgHist.getThdayPos());
                        value.put("fstProcTrmDt", chrgrChgHist.getFstProcTrmDt()==null ? "" :  transFormat.format(chrgrChgHist.getFstProcTrmDt())+"");
                        value.put("lastChgDt", transFormat.format(chrgrChgHist.getLastChgDt())+"");
                        searchList.add(value);
                        model.addAttribute("list",searchList);
                    }
                }

            }
        } catch (Exception e) {
            logger.debug(e.getStackTrace());
        }
        return model;
    }

    //MR 정보 담당자 저장
    @RequestMapping(value = "/mngrChangeSave.do")
    public String mngrChangeSave(JobsReviewVO jobsReviewVO , Model model) {

        mngrService.updateMngrChangeList(jobsReviewVO);

        return "redirect:mngrChange.do";
    }
    
    //MR 사업명 변경
    @RequestMapping(value = "/bzNameChange.do")
    public String bzNameChange(JobsReviewVO jobsReviewVO , Model model) {

        mngrService.updateMngrBzNameChange(jobsReviewVO);

        return "redirect:mngrAlter.do";
    }
    
  //MR 삭제
    @RequestMapping(value = "/mrDelete.do")
    public String mrDelete(IvstCostRegisterVO ivstCostRegisterVO , Model model) {

        boolean result = mngrService.updateMngrMrDelete(ivstCostRegisterVO);

        return "redirect:mngrAlter.do?result="+ result;
    }
    
  //MR 사업 취소
    @RequestMapping(value = "/bzCancel.do")
    public String bzCancel(IvstCostRegisterVO ivstCostRegisterVO , Model model) {

    	boolean result = mngrService.updateMngrBzCancelList(ivstCostRegisterVO);

        return "redirect:mngrAlter.do?result="+ result;
    }
    
 
    
  //CAPEX 적용
    @RequestMapping(value = "/capexApply.do")
    public String capexApply(MrAlterVO mrAlterVO , Model model) {

    	boolean result = mngrService.updateMngrCapexApplyList(mrAlterVO);

        return "redirect:mngrAlter.do?result="+ result;
    }
    

    //첨부파일 일괄관리
    @RequestMapping(value = "/mngrFiles.do")
    public String mngrFiles(Model model, HttpSession session) {
    	model.addAttribute("emp", session.getAttribute(Authentication.SESSION_ATTRIBUTE_KEY));
        return "mngr/mngrFiles";
    }

    //첨부파일 목록 조회
    @RequestMapping(value = "/mngrFilesAjax.do")
    public Model mngrFilesAjax(Integer mrReqNo, Model model) {
        List<Map> searchList = new ArrayList<Map>();
        Map<String, String> value;
    	try {
            if(mrReqNo !=null ){
            	//selectMngrChangeList(mr정보 리스트 조회)
            	//직무검토 조회
                List<MrAtchFile> fileList = mngrService.selectFilesList(mrReqNo);
                if(fileList!=null) {
                    for(MrAtchFile mrAtchFile : fileList){
                        value = new LinkedHashMap<String, String>();
                        value.put("mrAtchFileNo", Integer.toString(mrAtchFile.getMrAtchFileNo()));
                        value.put("fileStepCd", mrAtchFile.getFileStepCd());
                        value.put("fileStepNm", mrAtchFile.getFileStepNm());
                        value.put("fildCd", mrAtchFile.getFileCd());
                        value.put("fileCdNm", mrAtchFile.getFileCdNm());
                        value.put("fileNm", mrAtchFile.getFileNm());
                        value.put("drawMngNo", mrAtchFile.getDrawMngNo());
						searchList.add(value);
                    }
                    model.addAttribute("fileList",searchList);
                }
                
                List<ChrgrChgHist> stepList = mngrService.selectStepCdList(mrReqNo);
                model.addAttribute("stepList", stepList);

            }
        } catch (Exception e) {
            logger.debug(e.getStackTrace());
        }
    	
        return model;
    }
    
  //첨부파일 목록 저장
    @RequestMapping(value = "/mngrFilesSave.do")
    public Model mngrFilesSave(JobsReviewVO jobsReviewVO, Model model) {
    	int result = mngrService.updateMngrFilesList(jobsReviewVO);
    	String resultText = "총 "+result+" 건의 첨부파일이 정상 업데이트되었습니다."+" ("+jobsReviewVO.getMrReqTitle()+"_"+jobsReviewVO.getMrReqNo()+")";
    	
    	model.addAttribute("result", resultText);
    	return model;
    }
}
