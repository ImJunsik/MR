package com.base.servlet.unbind.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 본 필터는 화면(JSP) 구현에 따라 메뉴 이동을 지원하고, 메뉴 이동과 함께
 * {@link Search} 인터페이스를 구현한 클래스가 HttpSession에 있다면 검색조건을
 * 초기화하는 기능을 수행한다.
 * <p>따라서 메뉴이동시 검색조건의 초기화가 필요한 경우 해당 필터를 이용하여
 * 메뉴 이동을 하도록 한다. (e.g. menu?redirectUrl=job/jobExecutedList.do)</p>
 * 
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 * 
 * @see SearchSessionUnbindingFilter
 */
@Component
public class SearchSessionUnbindingFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        String redirectUrl = request.getParameter("redirectUrl");
        if (redirectUrl == null) {
            throw new ServletException("There is no redirect url.");
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            List<String> searchSessionAttributeNames = findSearchSessionAttributeName(session);

            for (String searchSessionAttributeName : searchSessionAttributeNames) {
                session.removeAttribute(searchSessionAttributeName);
            }
        }

        response.sendRedirect(redirectUrl);
        return;
    }

    private List<String> findSearchSessionAttributeName(HttpSession session) {
        String attributeName = null;
        @SuppressWarnings("rawtypes")
        Enumeration attributeNames = session.getAttributeNames();
        List<String> searchSessionAttributeNames = new ArrayList<String>();
        Object sessionObject = null;

        while (attributeNames.hasMoreElements()) {
            attributeName = attributeNames.nextElement().toString();
            sessionObject = session.getAttribute(attributeName);

            if (sessionObject instanceof Search) {
                searchSessionAttributeNames.add(attributeName);
            }
        }
        return searchSessionAttributeNames;
    }
}