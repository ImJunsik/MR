package com.mr.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.mr.common.domain.EmpInfoVo;
import com.mr.common.service.EmpAndTeamService;
import com.mr.step.domain.ChrgrChgHist;

/**
 * 사원, 팀관련 컨트롤러
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * 2014.06.24 박성룡 팀검색 추가
 * </pre>
 */

@Controller
@RequestMapping(value = "/")
public class EmpAndTeamController extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    EmpAndTeamService empAndTeamService;


    /**
     * 사원검색
     * @param empInfoVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/empSearch.do")
    public String empSearch(EmpInfoVo empInfoVo, Model model) {
        if(empInfoVo.getEmpName()!=null || empInfoVo.getTeamName()!=null) {
            model.addAttribute("empInfoList", empAndTeamService.empSearch(empInfoVo));
        }
        return "popup/empSearch";
    }

    /**
     * 팀검색
     * @param teamName
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/teamSearch.do")
    public String teamSearch(EmpInfoVo empInfoVo, Model model) {
        if(!StringUtils.isEmpty(empInfoVo.getTeamName())) {
            model.addAttribute("teamInfoList", empAndTeamService.teamSearch(empInfoVo));
        }
        return "popup/teamSearch";
    }


    /**
     * 팀목록 Ajax
     * @return
     */
    @RequestMapping(value = "allTeam.do")
    public Model getAllTeam(Model model) {
        List<Map> teamList = new ArrayList<Map>();
        Map<String, String> codeValue;
        for(EmpInfoVo empInfoVo : empAndTeamService.teamSearch(null)){
            codeValue = new LinkedHashMap<String, String>();
            codeValue.put("value", empInfoVo.getTeamNo()+"");
            codeValue.put("text", empInfoVo.getTeamName());
            teamList.add(codeValue);
        }
        model.addAttribute("selectList",teamList);
        return model;
    }


    /**
     * 직무검토 담당자 변경
     * @param empInfoVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/jobEmpChange.do")
    public String jobEmpChange(EmpInfoVo empInfoVo, String empNo, int mrReqNo, Model model) {
        int mrReqProcStepDetNo = 0;
        mrReqProcStepDetNo = empAndTeamService.jobEmpChange(empNo, mrReqNo);
        model.addAttribute("mrReqProcStepDetNo", mrReqProcStepDetNo);
        model.addAttribute("jobLine", empAndTeamService.selectJobLine(mrReqNo, "Z0040"));
        model.addAttribute("mrReqNo", mrReqNo);
        model.addAttribute("chrgrClCd", empInfoVo.getChrgrClCd());
        model.addAttribute("empNo", empNo);
        model.addAttribute("chrgrChgHist", empAndTeamService.getChrgrChgHist(mrReqProcStepDetNo));
        if(empInfoVo.getEmpName()!=null || empInfoVo.getTeamName()!=null) {
            model.addAttribute("empInfoList", empAndTeamService.empSearch(empInfoVo));
        }
        return "popup/empChange";
    }

    /**
     * 직무검토 담당자 변경 저장
     * @param chrgrChgHist
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/jobEmpChangeSave.do")
    public String jobEmpChangeSave(ChrgrChgHist chrgrChgHist, Model model) {
        empAndTeamService.jobEmpChangeSave(chrgrChgHist);
        return "popup/empChange";
    }



    /**
     * 팀검색
     * @param teamName
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/costTeamSearch.do")
    public String costTeamSearch(String teamText, String plantNo, Model model) {
        if(!StringUtils.isEmpty(teamText) && StringUtils.isEmpty(plantNo)) {
            model.addAttribute("teamInfoList", empAndTeamService.selectCostCenter(teamText));
        }
        if(!StringUtils.isEmpty(teamText) && !StringUtils.isEmpty(plantNo)) {
        	Map dataMap = new HashMap();
        	dataMap.put("teamText", teamText);
        	dataMap.put("burks", plantNo);
            model.addAttribute("teamInfoList", empAndTeamService.selectCostCenterMap(dataMap));
        }
        return "popup/costTeamSearch";
    }

}
