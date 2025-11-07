package com.base.servlet.tags.support;

import com.base.servlet.tags.Pagination;
import com.base.servlet.tags.PaginationTag;
import com.base.servlet.unbind.search.Search;

/**
 * 게시물의 페이징을 원하는 클래스는 해당 interface를 구현하도록 한다.
 * <p>{@link PaginationTag} 에서 페이징 정보를 얻기위해 해당 interface를 구현한
 * 클래스를 찾아 페이징 태그를 완성한다.</p>
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
 * @see PaginationTag
 */
public interface PaginationSearch extends Search {
    /**
     * @return 페이징 정보
     */
    Pagination getPagination();

    /**
     * @param pagination 페이징 정보
     */
    void setPagination(Pagination pagination);
}