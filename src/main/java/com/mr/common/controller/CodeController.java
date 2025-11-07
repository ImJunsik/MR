package com.mr.common.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.servlet.BaseController;
import com.mr.common.domain.CommCdVO;
import com.mr.common.domain.UnitVo;
import com.mr.common.service.CodeService;
import com.mr.step.service.MrStepService;

/**
 * 코드관련 컨트롤러
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.25 박성룡 최초 작성
 * 2017.11.28 wj: 초기투자비 산정 직책과장 조회 추가
 * </pre>
 */

@Controller
@RequestMapping(value = "/")
public class CodeController extends BaseController {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CodeService codeService;


    @Autowired
    MrStepService mrStepService;


    /**
     * 그룹코드 전체를 반환한다.
     * @param model
     * @return
     */
    @RequestMapping(value="/codeGrpList.do")
    public Model codeGrpListAajx(Model model){
        CommCdVO inCommCdVO = new CommCdVO();
        //codeService.selectCommCd 에서 MrCommCd의 값이 GRP_ALL 일경우 전체 그룹코드를 반환해준다.
        inCommCdVO.setMrCommCd("GRP_ALL");

        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = codeService.selectCommCd(inCommCdVO);
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommGrpNm());
                codeValue.put("value", commCdVO.getMrCommGrpCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }


    /**
     * 코드리스트 조회 ajax
     * @param inCommCdVO
     * @param model
     * @return
     */
    @RequestMapping(value="/codeList.do")
    public Model codeListAajx(CommCdVO inCommCdVO, Model model){
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
       
        //wj: chrgDetNo 추가
        if (inCommCdVO.getMrCommGrpCd().equals("chrgDetNo")){
        	System.out.println("wj:: inCommCdVO.getMrCommGrpCd   :: " + inCommCdVO.getMrCommGrpCd());       	
        	System.out.println("wj:: inCommCdVO.getMrCommGrpCd   :: " + inCommCdVO.getParentCd());
        }
        List<CommCdVO> codeList = codeService.selectCommCd(inCommCdVO);  
        
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommNm());
                codeValue.put("value", commCdVO.getMrCommCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }

    /**
     * 전체공정 조회 ajax
     * @param model
     * @return
     */
    @RequestMapping(value="/allUnit.do")
    public Model allUnitAjax(Model model){
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = codeService.selectAllUnit();
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommNm());
                codeValue.put("value", commCdVO.getMrCommCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }

    /**
     * 공정에 따른 설비 조회 ajax
     * @param unitNo
     * @param model
     * @return
     */

    @RequestMapping(value="/getEquip.do")
    public Model getEquipAjax(String mrProcNo, Model model){
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = codeService.selectEquip(mrProcNo);
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommNm());
                codeValue.put("value", commCdVO.getMrCommCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }


    /**
     * 공정검색 팝업
     * @param unitVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/unitSearch.do", method=RequestMethod.GET)
    public String unitSearch(HttpServletRequest request,UnitVo unitVo, Model model) {
    	
    	Map<String, String[]> parameters = request.getParameterMap();
    	String units = "";
    	String[] vals = null;
        for(String key : parameters.keySet()) {
            System.out.println(key);
            if(key.indexOf("procs[") > -1 && key.indexOf("].mrProcNo") > -1)
            {
	            vals = parameters.get(key);
	            for(String val : vals)
	            {
	                System.out.println(" -> " + val);
	                units += val + ",";
	                //units += "'" + val + "',";
	            }
            }
        }
        if(!units.equals(""))
        {
        	units = units.substring(0, units.length() -1);
    		unitVo.setMrProcName(units);
        }
    	List<UnitVo> plantInfo = codeService.selectPlantAndUnit(unitVo);
        String plantNo = "";
        String plantName = "";
        int i = 0;
        if(plantInfo.size() > 0)
        {
        	List<UnitVo> uv_new = new ArrayList<UnitVo>();
	        for(UnitVo uv : plantInfo)
	        {
	        	if(i == 0)
	        	{
		        	plantNo = uv.getPlantNo();
		        	plantName = uv.getPlantName();
	        	}
	        	else
	        	{
	        		UnitVo resultVo = new UnitVo();
	        		resultVo.setMrProcNo(uv.getPlantNo());
	        		resultVo.setMrProcName(uv.getPlantName());
	        		uv_new.add(resultVo);
	        	}
	        	i++;
	        }
	        unitVo.setProcs(uv_new);
        }
        
        if(plantInfo!=null) {
            unitVo.setPlantNo(plantNo);
            unitVo.setPlantName(plantName);
            model.addAttribute("unitVo", unitVo);
        }
        return "popup/unitSearch";
    }

    /**
     * 공정검색 Ajax
     * @param unitVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/unitSearchAjax.do")
    public Model unitSearchAjax(UnitVo unitVo, Model model) {
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;

        List<UnitVo> codeList = codeService.unitSearch(unitVo);
        if(codeList!=null) {
            for(UnitVo unitVoList : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("mrProcNo", unitVoList.getMrProcNo());
                codeValue.put("mrProcName", unitVoList.getMrProcName());
                //codeValue.put("PlnatNo", unitVoList.getPlantNo());	//2017.07.25 플랜트 코드조건추가
                mrSearchList.add(codeValue);
                model.addAttribute("searchList",mrSearchList);
            }
        }
        return model;
    }

    /**
     * 설비검색 팝업
     * @param unitVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/equipSearch.do", method=RequestMethod.GET)
    public String equipSearch(HttpServletRequest request,UnitVo unitVo, Model model) {
    	Map<String, String[]> parameters = request.getParameterMap();
    	String units = "";
    	String[] vals = null;
        for(String key : parameters.keySet()) {
            System.out.println(key);
            if(key.indexOf("procs[") > -1 && key.indexOf("].mrProcNo") > -1)
            {
	            vals = parameters.get(key);
	            for(String val : vals)
	            {
	                System.out.println(" -> " + val);
	                units += val + ",";
	                //units += "'" + val + "',";
	            }
            }
        }
        if(!units.equals(""))
        {
        	units = units.substring(0, units.length() -1);
    		unitVo.setMrProcName(units);
        }
        
        List<UnitVo> plantInfo = codeService.selectPlantAndUnit(unitVo);
        String plantNo = "";
        String plantName = "";
        int i = 0;
        if(plantInfo.size() > 0)
        {
        	List<UnitVo> uv_new = new ArrayList<UnitVo>();
	        for(UnitVo uv : plantInfo)
	        {
	        	if(i == 0)
	        	{
		        	plantNo = uv.getPlantNo();
		        	plantName = uv.getPlantName();
	        	}
	        	else
	        	{
	        		UnitVo resultVo = new UnitVo();
	        		resultVo.setMrProcNo(uv.getPlantNo());
	        		resultVo.setMrProcName(uv.getPlantName());
	        		uv_new.add(resultVo);
	        	}
	        	i++;
	        }
	        unitVo.setProcs(uv_new);
        }
        
        if(plantInfo!=null) {
	        model.addAttribute("units", unitVo.getProcs());
	        model.addAttribute("plant", unitVo.getPlantNo());
        }
        return "popup/equipSearch";
    }


    /**
     * 설비검색 팝업
     * @param unitVo
     * @param model
     * @return
     */
    @RequestMapping(value = "popup/equipSearchAjax.do")
    public Model equipSearchAjax(UnitVo unitVo, Model model) {
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;

        List<UnitVo> codeList = codeService.equipSearch(unitVo);
        if(codeList!=null) {
            for(UnitVo unitVoList : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("mrProcNo", unitVoList.getMrProcNo());
                codeValue.put("mrProcName", unitVoList.getMrProcName());
                codeValue.put("mrEquipCd", unitVoList.getMrEquipCd());
                codeValue.put("mrEquipName", unitVoList.getMrEquipName());
                mrSearchList.add(codeValue);
                model.addAttribute("searchList",mrSearchList);
            }
        }
        return model;
    }

    /**
     * 기본 결재기한 반환
     * @param model
     * @return
     */
    @RequestMapping(value = "defaultTermAjax.do")
    public Model defaultTermAjax(Model model) {
        model.addAttribute("term",mrStepService.getAppTerm());
        return model;
    }
    
    /**
     * wj:직책과장조회
     * @param model
     * @return
     */
    /*
    @RequestMapping(value="/techInvestEmp.do")
    public Model techInvestEmp(String mrNo, Model model){
    	List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = codeService.techInvestEmp(mrNo);
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommNm());
                codeValue.put("value", commCdVO.getMrCommCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }    
    */

    /**
     * 참고(공정에 따른 설비 조회)
     * @param model
     * @return
     
    @RequestMapping(value="/getEquip.do")
    public Model getEquipAjax(String mrProcNo, Model model){
        List<Map> mrSearchList = new ArrayList<Map>();
        Map<String, String> codeValue;
        List<CommCdVO> codeList = codeService.selectEquip(mrProcNo);
        if(codeList!=null) {
            for(CommCdVO commCdVO : codeList){
                codeValue = new LinkedHashMap<String, String>();
                codeValue.put("text", commCdVO.getMrCommNm());
                codeValue.put("value", commCdVO.getMrCommCd());
                mrSearchList.add(codeValue);
                model.addAttribute("selectList",mrSearchList);
            }
        }
        return model;
    }
    */
}
