package com.mr.common.prehandle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mr.common.domain.Authentication;
import com.mr.common.domain.EmpInfoVo;
import com.mr.common.service.LoginService;
import com.mr.common.service.SsoLoginCheckService;
/**
 * 로그인을 체크하여 SSO처리, 로그인화면을 처리한다.
 *
 * @author 박성룡
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * </pre>
 */

public class PreInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private SsoLoginCheckService ssoLoginCheckService;

    @Autowired
    private XMLConfiguration appConfig;



    /**
     * 전처리 Intercepter(시스템점검시간체크, 로그인체크)
     *
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean next = false;

        String URI = request.getRequestURI().toString();
        String URL = URI.substring(URI.lastIndexOf("/"));
        
        // ★★★ download2.do는 로그인 없이 접근 허용 ★★★
        if(URI.contains("/download2.do")) {
            return true;
        }
        
        String reqURL;

        String empNo = request.getParameter("empNo");
        String ssoEmpNo = null;
        String mrReqNo = request.getParameter("mrReqNo");
        String loginKey = request.getParameter("loginKey");
        String connkey = request.getParameter("connkey");
        String uid = request.getParameter("uid");
        String connFileId = request.getParameter("connFileId");
        
        Authentication auth  = (Authentication) request.getSession().getAttribute(Authentication.SESSION_ATTRIBUTE_KEY);

        //도면관리상단메뉴에서 연결되는 경우 로그인체크
        if(empNo!=null && loginKey!=null && enKeyCheck(empNo , loginKey)){
            createLoginSession(request, empNo);
        }

        //if(uid!=null || connkey!=null || connFileId!=null){
        //    createLoginSession(request, empNo);
       // }


        //세션정보가 존재할경우
        if((auth!=null&& !auth.getIsMail()) || (auth!=null && auth.getIsMail() && URI.contains(auth.getMrURL()))) {
            next = true;
        } else if(URI.equals("/mr/login.do") || URI.equals("/mr/logout.do")  || URI.equals("/mr/mail.do") || URI.equals("/ssoLogin.do") ){
            //로그인페이지, 로그아웃페이지로 접근할경우
            next = true;
        } else {


            //Sso로그인 체크
            ssoEmpNo = ssoLoginCheckService.getHdoSsoLoginUser(request);

            //ssoEmpNo !=null 일경우 로그인한것으로 본다.
            if(ssoEmpNo!=null) {

                //로그인 세션 생성
                if(createLoginSession(request, ssoEmpNo)) {
                    next = true;
                } else {
                    next = false;
                }

            } else {

                if(URI.indexOf("popup")>0) {
                    request.setAttribute("login", "false");
                    request.getRequestDispatcher("/WEB-INF/views/popup/popupLoginFail.jsp").forward(request, response);
                }
                else if(URL.contains("mrTech")){
                //hajewook 추가 파일 다운로드
                	
                }
                else {
                    if(mrReqNo==null || URL==null) {
                        response.sendRedirect("../mr/login.do");
                    } else {

                        if(URL.contains("mrRq")) {

                            reqURL = "/mrRqRegister.do";

                        } else if(URL.contains("mrTech")) {

                            reqURL = "/mrTech.do";

                        }else if(URL.contains("mrJobsCheck")) {

                            reqURL = "/mrJobsCheck.do";

                        } else if(URL.contains("mrComplete")) {

                            reqURL = "/mrComplete.do";

                        } else if(URL.contains("mrTechInvest")) {

                            reqURL = "/mrTechInvest.do";

                        } else if(URL.contains("mrJobsReview")) {

                            reqURL = "/mrJobsReview.do";

                        } else if(URL.contains("ivstCost")) {

                            reqURL = "/ivstCostRegister.do";

                        } else if(URL.contains("safeCheckExe")) {

                            reqURL = "/safeCheckExe.do";

                        } else if(URL.contains("safeCheckRegister")) {

                            reqURL = "/safeCheckRegister.do";

                        } else if(URL.contains("compFileManage")) {

                            reqURL = "/compFileManage.do";

                        } else if(URL.contains("compRptRegister")) {

                            reqURL = "/compRptRegister.do";

                        } else if(URL.contains("mrRiskCheck")) {

                            reqURL = "/mrRiskCheck.do";

                        } else {

                            reqURL = "/mrRqRegister.do";
                        }

                        response.sendRedirect("../mr/login.do?next="+reqURL+"&mrReqNo="+mrReqNo);
                    }
                }
            }

        }
        return next;
    }



    /**
     * 타시스템에서 로그인시 암호화키를 확인하여 로그인여부를 판단함.
     * app-config.xml edims에서 설정하시고 사용하세요.
     * <pre>
     * edims > login-minute에 분단위로 입력하시기 바랍니다.
     * </pre>
     * @param emp
     * @param loginKey 
     * @return
     */
    private boolean enKeyCheck(String emp, String loginKey){
        boolean isLogin = false;
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date to;
        for(int i=0; i <= appConfig.getInt("edims.login-minute"); i++) {
            to = new Date(currentTime - 1000 * 60 * i);
            if(loginKey.equals(encrypt(emp + transFormat.format(to).toString()))) {
                isLogin = true;
                break;
            }

        }
        return isLogin;
    }



    private boolean createLoginSession(HttpServletRequest request, String empNo){

        HttpSession session = request.getSession();
        EmpInfoVo loginEmpInfoVo = loginService.loginUserInfo(empNo);
        if(loginEmpInfoVo!=null) {
            Authentication authentication = new Authentication();
            authentication.setEmpNo(loginEmpInfoVo.getEmpNo());
            authentication.setEmpName(loginEmpInfoVo.getEmpName());
            authentication.setTeamNo(loginEmpInfoVo.getTeamNo());
            authentication.setTeamName(loginEmpInfoVo.getTeamName());
            authentication.setDutyNo(loginEmpInfoVo.getDutyNo());
            authentication.setDutyName(loginEmpInfoVo.getDutyName());
            session.setAttribute(Authentication.SESSION_ATTRIBUTE_KEY, authentication);
            return true;
        }

        return false;
    }

    private static String encrypt(String str) {

        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

}
