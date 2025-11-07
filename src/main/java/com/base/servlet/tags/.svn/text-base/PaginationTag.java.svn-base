package com.base.servlet.tags;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.base.servlet.tags.support.PaginationSearch;

/**
 * JSP에서 JSTL Custom tag를 사용하여 게시물 페이징을 구현할 수 있는 Custom tag 클래스
 * <p>본 Custom Tag Library는 ui.tld 에 정의되어 있고, &lt;ui:pagination /&gt; 와 같은
 * 형태로 사용할 수 있다.</p>
 * 
 * <p>게시물의 페이징을 원할경우 기본족으로 {@link PaginationSearch}을 구현한 클래스가
 * 필요하다. 하지만 본 클래스에서는 구현하지 않은 클래스도 가능하도록 {@link #setPagination(Pagination)}
 * 을 제공하고 있으나 {@link PaginationSearch}을 구현할 것으로 추천한다.</p>
 * 
 * <p>페이징에 필요한 Label은 프로젝트마다 혹은 화면마다 달라질 수 있으므로
 * '이전페이지', '다음페이지' 등과 같은 Label은 pagination.properties 에 정의하고 있다.
 * 프로젝트에 맞게 설정파일을 수정하여 사용하도록 한다.</p>
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
 * @see PaginationSearch
 */
@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {
    private Pagination pagination;
    private String prefix = "pagination.";

    protected final Properties paginationProperties = new Properties();

    /**
     * @param pagination 페이징 정보
     */
    public void setPagination(Pagination pagination) {
        if (pagination == null) {
            throw new IllegalArgumentException("pagination is can not be null.");
        }
        this.pagination = pagination;
    }

    /**
     * 페이지를 이동할 경우 몇 번째 페이지로 이동을 하는지
     * <code>Pagination</code> 객체에 페이지 번호를 파라미터로 전달해야 한다.
     * 이 때 <code>Pagination</code> 객체가 어떤 특정 객체의 멤버 변수로서
     * 존재하는지 아니면 단독 객체로서 존재하는지 Custom Tag에서는 알 수 있는 방법이 없다.
     * <code>Pagination</code> 객체에 어떻게 접근할 수 있는지 정보를 설정한다.
     * <pre>
     * public class EmployeeSearch {
     *     Pagination pagination;
     *     public Pagination getPagination();
     *     public void setPagination();
     * }
     * </pre>
     * 위와 같은 경우는 페이지 번호를 Pagination 객체에 전달하기 위해서
     * HTTP 파라미터를 pagination.currentPageNo 와 같이 전달해야 하므로 prefix 의 값은 'pagination' 이 된다.
     * @param prefix Pagination getter 이름
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix != null ? prefix : "";
    }

    @Override
    public int doStartTag() throws JspException {
        loadPaginationProperties();

        if (pagination == null) {
            PaginationSearch paginationSearch = getPaginationSearchFromSession();
            pagination = paginationSearch.getPagination();
            prefix = "pagination";
        }

        int firstPageNo = pagination.getFirstPageNo();
        int firstPageNoOnPageList = pagination.getFirstPageNoOnPageList();
        int totalPageCount = pagination.getTotalPageCount();
        int pageSize = pagination.getPageSize();
        int lastPageNoOnPageList = pagination.getLastPageNoOnPageList();
        int currentPageNo = pagination.getCurrentPageNo();
        int lastPageNo = pagination.getLastPageNo();

        StringBuffer paginationTag = new StringBuffer();
        paginationTag.append(getPaginationHeaderLabel());

        if (totalPageCount > pageSize) {
            if (firstPageNoOnPageList > pageSize) {
                paginationTag.append(getFirstPageLabel(firstPageNo));
                paginationTag.append(getPreviousPageLabel(firstPageNoOnPageList - 1));
            } else {
                paginationTag.append(getFirstPageLabel(firstPageNo));
                paginationTag.append(getPreviousPageLabel(firstPageNo));
            }
        }

        for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
            if (i == currentPageNo) {
                paginationTag.append(getCurrentPageLabel(i));
            } else {
                paginationTag.append(getOtherPageLabel(i));
            }
        }

        if (totalPageCount > pageSize) {
            if (lastPageNoOnPageList < totalPageCount) {
                paginationTag.append(getNextPageLabel(firstPageNoOnPageList + pageSize));
                paginationTag.append(getLastPageLabel(lastPageNo));
            } else {
                paginationTag.append(getNextPageLabel(lastPageNo));
                paginationTag.append(getLastPageLabel(lastPageNo));
            }
        }

        paginationTag.append(getPaginationFooterLabel());

        try {
            pageContext.getOut().print(paginationTag.toString());
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        pagination = null;
        return SKIP_BODY;
    }

    /**
     * 페이징 property 파일 로드
     * @throws JspException porperty 로드 실패의 경우
     */
    protected void loadPaginationProperties() throws JspException {
        try {
            paginationProperties.load(this.getClass().getClassLoader().getResourceAsStream("config/property/pagination.properties"));
        } catch (IOException e) {
            throw new JspException("'config/property/pagination.propertie' the classpath file does not exist.", e);
        }
    }

    /**
     * @return 페이징 Label 중 헤더 Label
     */
    protected String getPaginationHeaderLabel() {
        return generatePageLabel("pagination.header.label");
    }

    /**
     * @return 페이징 Label 중 풋터 Label
     */
    protected String getPaginationFooterLabel() {
        return generatePageLabel("pagination.footer.label");
    }

    /**
     * @return 페이징 Label 중 첫페이지 Label
     */
    protected String getFirstPageLabel(int pageNo) {
        return generatePageLabel("pagination.first.page.label", pageNo);
    }

    /**
     * @return 페이징 Label 중 이전페이지 Label
     */
    protected String getPreviousPageLabel(int pageNo) {
        return generatePageLabel("pagination.previous.page.label", pageNo);
    }

    /**
     * @return 페이징 Label 중 현재페이지 Label
     */
    protected String getCurrentPageLabel(int pageNo) {
        return generatePageLabel("pagination.current.page.label", pageNo);
    }

    /**
     * @return 페이징 Label 중 현재페이지가 아닌 페이지 Label
     */
    protected String getOtherPageLabel(int pageNo) {
        return generatePageLabel("pagination.other.page.label", pageNo);
    }

    /**
     * @return 페이징 Label 중 다음페이지 Label
     */
    protected String getNextPageLabel (int pageNo) {
        return generatePageLabel("pagination.next.page.label", pageNo);
    }

    /**
     * @return 페이징 Label 중 마지막페이지 Label
     */
    protected String getLastPageLabel(int pageNo) {
        return generatePageLabel("pagination.last.page.label", pageNo);
    }

    /**
     * 페이징 Label을 생성
     * @param propertyName 페이지 Label property key
     * @return propertyName에 해당하는 페이징 Label
     */
    private String generatePageLabel(String propertyName) {
        return generatePageLabel(propertyName, -1);
    }

    /**
     * 페이징 Label을 생성
     * @param propertyName 페이지 Label property key
     * @param pageNo 페이지 번호
     * @return propertyName에 해당하는 페이징 Label
     */
    private String generatePageLabel(String propertyName, int pageNo) {
        String label = paginationProperties.getProperty(propertyName);
        StringBuilder builder = new StringBuilder();

        if (pageNo > -1) {
            builder.append(getRequestURI()).append("?").append(prefix).append(".").append("currentPageNo=").append(pageNo);
        }
        return MessageFormat.format(label, builder.toString(), pageNo);
    }

    /**
     * {@link #pagination} 값이 null일 경우 HttpSession에서 PaginationSearch를
     * 구현한 클래스 찾아 반환한다.
     * @return PaginationSearch 구현 클래스
     * @see PaginationSearch
     */
    private PaginationSearch getPaginationSearchFromSession() {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession(false);

        String attributeName = null;
        @SuppressWarnings("rawtypes")
        Enumeration attributeNames = session.getAttributeNames();
        Object sessionObject = null;

        while (attributeNames.hasMoreElements()) {
            attributeName = attributeNames.nextElement().toString();
            sessionObject = session.getAttribute(attributeName);

            if (sessionObject instanceof PaginationSearch) {
                return (PaginationSearch)sessionObject;
            }
        }
        return null;
    }

    /**
     * @return 현재 요청한 URI 주소
     */
    private String getRequestURI() {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        return request.getAttribute("javax.servlet.forward.request_uri").toString();
    }
}