package com.base.domain;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.util.IsOperDistinc;
import com.base.util.SpringApplicationContext;
import com.mr.common.domain.Authentication;

import oracle.jdbc.pool.OracleDataSource;

/**
 * <p>Domain 클래스의 최상위 객체로서 하위 클래스들에 대한 공통적인 부분을 제공한다.
 * 데이터 DB insert, update 등을 수행할 경우 등록자, 수정자 아이디가 필요할텐데
 * getUserId()를 호출한다면 로그인한 사용자의 session 에서 사용자 아이디를
 * 리턴할 것이므로 업무단에서 불필요하게 session 에 접근하여 로그인 사용자 아디를 가져온다거
 * 하는 행위를 할 필요가 없다.</p>
 * <p>Domain 클래스에는 Domain 객체를 사용하여 Pagination을 구현할 경우 전체 Record Count을 활용할 수 있도록
 * getter/setter 제공하므로 Domain 하위 클래스에서 재정의하지 않도록 한다.</p>
 * {@link com.common.scheduling.web.JobMonitoringController#getJobExecutedList(com.common.scheduling.web.JobSearchCondition, org.springframework.ui.Model)}
 *
 * @author 조용상
 * @version 1.0
 *
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
public class Domain {
	protected String loginEmpNo;
    private String loginEmpName;
    private String loginDutyNo;
    private String loginDutyName;
    private String loginTeamNo;
    private String loginTeamName;
    private String remoteAddr;
    private int totalRecordCount;
    
    private final Logger logger = Logger.getLogger(this.getClass());

    
    public Domain() {
    	
    	IsOperDistinc.getInstance();
        initConnectedUserData();

    }

    private void initConnectedUserData() {
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            
            HttpSession session = request.getSession(false);

            if (session != null && session.getAttribute(Authentication.SESSION_ATTRIBUTE_KEY) != null) {
                Authentication authentication = (Authentication)session.getAttribute(Authentication.SESSION_ATTRIBUTE_KEY);
                loginEmpNo = authentication.getEmpNo();
                loginEmpName = authentication.getEmpName();
                loginDutyNo = authentication.getDutyNo();
                loginDutyName = authentication.getDutyName();
                loginTeamNo = authentication.getTeamNo();
                loginTeamName = authentication.getTeamName();
                remoteAddr = request.getRemoteAddr().startsWith("0:") ? "127.0.0.1" : request.getRemoteAddr();
                
                //System.out.println("loginEmpNo   :: " + loginEmpNo);
            }
        } catch (IllegalStateException e) {
            loginEmpNo = "SYSTEM";
            remoteAddr = "127.0.0.1";
        }
    }

    /**
     * Application config 객체를 반환한다.
     * @return Application config
     */
    public XMLConfiguration getAppConfig() {
        return SpringApplicationContext.getBean(XMLConfiguration.class);
    }

    public String setLoginEmpNo(String loginEmpNo) {
        return this.loginEmpNo = loginEmpNo;
    }
    
    /**
     * 로그인한 사용자의 아이디를 반환한다.
     * @return 로그인 사용자의 아이디
     */
    public String getLoginEmpNo() {
        return loginEmpNo;
    }

    /**
     * 로그인한 사용자의 부서번호를 반환한다.
     * @return 로그인 사용자의 부서번호
     */

    public String getLoginTeamNo() {
        return loginTeamNo;
    }


    /**
     * 로그인한 사용자의 직책번호를 반환한다.
     * @return 로그인 사용자의 직책번호
     */

    public String getLoginDutyNo() {
        return loginDutyNo;
    }


    /**
     * 로그인한 사용자의 아이디를 반환한다.
     * @return 로그인 사용자의 아이디
     */
    public String getLoginEmpName() {
        return loginEmpName;
    }

    /**
     * 로그인한 사용자의 부서번호를 반환한다.
     * @return 로그인 사용자의 부서번호
     */

    public String getLoginTeamName() {
        return loginTeamName;
    }


    /**
     * 로그인한 사용자의 직책번호를 반환한다.
     * @return 로그인 사용자의 직책번호
     */

    public String getLoginDutyName() {
        return loginDutyName;
    }

    /**
     * 로그인한 사용자 PC의 아이피를 반환한다.
     * @return 로그인 사용자 PC의 아이피
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }



    /**
     * Domain 객체를 사용하여 Pagination을 사용할 경우 전체 Record Count 반환한다.
     * @return 전체 Record Count
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * Domain 객체를 사용하여 Pagination을 사용할 경우 전체 Record Count 설정한다.
     * @param totalRecordCount 전체 Record Count
     */
    public void setTotalRecordCount(int totalCount) {
        this.totalRecordCount = totalCount;
    }
    
    
}