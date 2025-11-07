package com.mr.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acc.ep.hdoticket.SAPTicketVerifier;
import com.mr.common.service.SsoLoginCheckService;

@Service
public class SsoLoginCheckServiceImpl implements SsoLoginCheckService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String getHdoSsoLoginUser(HttpServletRequest request) {
        String loginUserId = null;
        try {
            //같은 도메인을 사용하는 사이트를 위한 sample 페이지
            //Declare & Initialize Variables
            String  MYSAPCOOKIENAME         = "MYSAPSSO2";
            // String  KEYSTORE_PATH           = "D:/OpenMinds/EDIMS/HDOTicket/portal.store";
            String  KEYSTORE_PATH           = "D:/MR/jdk1.7.0_67/bin/portal.store";
            String  KEYSTORE_PASSWORD       = "oilbank";
            String  RequestPage             = null;

            char passwd[]                   = KEYSTORE_PASSWORD.toCharArray();
            SAPTicketVerifier SAPVerifier   = SAPTicketVerifier.getInstance();
            SAPTicketVerifier.SAPTicketInfo  info  = null;

            Cookie SAPCookie             = null;
            Cookie cookies[]             = request.getCookies();
            String base64Value           = null;
            String cookieValue           = null;
            Cookie setcookie             = null;
            
            
            
            String empNo = request.getParameter("empNo");
            if(empNo != null)
            	loginUserId = empNo;
            
            //String tkt                   = "";
/*
            //Initialize TicketVerifier
            SAPVerifier.setCertificatesFromKeyStoreFile(KEYSTORE_PATH, passwd);
            Arrays.fill(passwd, ' ');
            RequestPage    =  request.getParameter("page");

            //Get Logon Ticket from Cookie
            if (cookies == null) {
                loginUserId=null;
                System.out.println("Request does not contain cookies");
            } else {
            
                try {
                    for (int i = 0; i < cookies.length; i++) {
                        Cookie cook  = cookies[i];
                        if (!cook.getName().trim().equals(MYSAPCOOKIENAME))
                            continue;
                        SAPCookie  = cook;
                        break;
                    }
                    cookieValue = SAPCookie.getValue();
                } catch (Exception ex) {
                    logger.error("#### SSO Error : " + ex.getStackTrace());
                }

                //Verify Logon Ticket
                if (!SAPTicketVerifier.isNullOrEmptyString(cookieValue)){
                    try {
                        base64Value  = URLDecoder.decode(cookieValue, "UTF-8");
                        info = SAPVerifier.verifyTicket(base64Value);
                        if (info != null) {
                            //out.println("User_ID is " + info.getUser());
                            //tkt = new sun.misc.BASE64Encoder().encodeBuffer(info.getUser().getBytes()).replace("\r\n","");
                            loginUserId = info.getUser().toString();
                        } else {
                            loginUserId = null;
                        }
                    } catch(UnsupportedEncodingException useex) {
                        logger.error("#### SSO Error : UTF-8 encoding not supported");
                    }
                }
            }
*/
        } catch (Exception e) {
            logger.error("#### SSO Error : " + e.getStackTrace());
        }

        return loginUserId;
    }

}
