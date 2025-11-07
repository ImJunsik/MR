package com.mr.common.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.servlet.BaseController;
import com.mr.common.domain.Authentication;
import com.mr.common.domain.EmpInfoVo;
import com.mr.common.service.LoginService;

/**
 * 로그인을 체크하여 SSO처리, 로그인화면을 처리한다.
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
public class LoginController extends BaseController {
	
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    LoginService loginService;


    /**
     * 로그인 처리
     * 세션을 체크하여 자동으로 로그인 혹은 로그인페이지로 이동시킨다.
     * @param loginVo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do")
    public String login(EmpInfoVo empInfoVo, Model model, HttpSession session) {
    	
        if (session != null && session.getAttribute(Authentication.SESSION_ATTRIBUTE_KEY) != null) {

            if(empInfoVo.getNext()!=null &&  empInfoVo.getMrReqNo()!=null){
                return "redirect:"+empInfoVo.getNext()+"?mrReqNo="+empInfoVo.getMrReqNo();
            } else {
                return "redirect:/main.do";
            }


        }else if (empInfoVo.getEmpNo() != null && empInfoVo.getPasswd() != null) {

            EmpInfoVo loginEmpInfoVo = loginService.loginCheck(empInfoVo);

            if(loginEmpInfoVo ==null) {
                model.addAttribute("empNo", empInfoVo.getEmpNo());
                model.addAttribute("failMessage", messageSource.getMessage("login.error", new String[]{empInfoVo.getEmpNo()}, Locale.KOREAN));
                return "login";
            } else {

                Authentication authentication = new Authentication();
                authentication.setEmpNo(loginEmpInfoVo.getEmpNo());
                authentication.setEmpName(loginEmpInfoVo.getEmpName());
                authentication.setTeamNo(loginEmpInfoVo.getTeamNo());
                authentication.setTeamName(loginEmpInfoVo.getTeamName());
                authentication.setDutyNo(loginEmpInfoVo.getDutyNo());
                authentication.setDutyName(loginEmpInfoVo.getDutyName());

                session.setAttribute(Authentication.SESSION_ATTRIBUTE_KEY, authentication);

                
                logger.info("------- Login ----- empInfoVo.getEmpNo() : " + empInfoVo.getEmpNo());
                
                
                
                if(empInfoVo.getNext()!=null &&  empInfoVo.getMrReqNo()!=null){
                    return "redirect:"+empInfoVo.getNext()+"?mrReqNo="+empInfoVo.getMrReqNo();
                } else {
                    return "redirect:/main.do";
                }

            }
            
            
            
        }
        model.addAttribute("next", empInfoVo.getNext());
        model.addAttribute("mrReqNo", empInfoVo.getMrReqNo());
        
        

        return "login";
    }

    
    /**
     * 로그인 처리
     * 세션을 체크하여 자동으로 로그인 혹은 로그인페이지로 이동시킨다.
     * @param loginVo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/ssoLogin.do")
    public String ssoLogin(EmpInfoVo empInfoVo, Model model, HttpSession session, HttpServletRequest request) {
    	
        /*if (session != null && session.getAttribute(Authentication.SESSION_ATTRIBUTE_KEY) != null) {

            if(empInfoVo.getNext()!=null &&  empInfoVo.getMrReqNo()!=null){
                return "redirect:"+empInfoVo.getNext()+"?mrReqNo="+empInfoVo.getMrReqNo();
            } else {
                return "redirect:/main.do";
            }


        }else if (empInfoVo.getEmpNo() != null && empInfoVo.getPasswd() != null) {*/
    	
    	String empNo = request.getParameter("empNo");
    	empInfoVo.setEmpNo(empNo);
   
    	if (empInfoVo.getEmpNo() != null) {

            EmpInfoVo loginEmpInfoVo = loginService.ssoLoginCheck(empInfoVo);

            if(loginEmpInfoVo ==null) {
                model.addAttribute("empNo", empInfoVo.getEmpNo());
                model.addAttribute("failMessage", messageSource.getMessage("login.error", new String[]{empInfoVo.getEmpNo()}, Locale.KOREAN));
                return "login";
            } else {

                Authentication authentication = new Authentication();
                authentication.setEmpNo(loginEmpInfoVo.getEmpNo());
                authentication.setEmpName(loginEmpInfoVo.getEmpName());
                authentication.setTeamNo(loginEmpInfoVo.getTeamNo());
                authentication.setTeamName(loginEmpInfoVo.getTeamName());
                authentication.setDutyNo(loginEmpInfoVo.getDutyNo());
                authentication.setDutyName(loginEmpInfoVo.getDutyName());

                session.setAttribute(Authentication.SESSION_ATTRIBUTE_KEY, authentication);

                
                logger.info("------- Login ----- empInfoVo.getEmpNo() : " + empInfoVo.getEmpNo());
                
                
                
                if(empInfoVo.getNext()!=null &&  empInfoVo.getMrReqNo()!=null){
                    return "redirect:"+empInfoVo.getNext()+"?mrReqNo="+empInfoVo.getMrReqNo();
                } else {
                    return "redirect:/main.do";
                }

            }
            
            
            
        }
        model.addAttribute("next", empInfoVo.getNext());
        model.addAttribute("mrReqNo", empInfoVo.getMrReqNo());
        
        

        return "login";
    }
    
    /**
     * 로그아웃 세션 만료
     * @param session
     * @return 로그인 페이지로 Redirect
     */
    @RequestMapping(value = "/logout.do")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login.do";
    }
    
    
}
