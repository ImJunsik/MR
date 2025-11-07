package com.base.servlet.tags;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시물의 페이징에 필요한 정보를 담고있는 클래스
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
public class Pagination {
    private int currentPageNo = 1; // 현재 페이지 번호
    private int recordCountPerPage = 10; // 한 페이지당 게시되는 게시물 건 수
    private int pageSize = 10; // 페이지 리스트에 게시되는 페이지 건수
    private int totalRecordCount; // 전체 게시물 건 수
    private List<Integer> pageNumbering = new ArrayList<Integer>();

    public Pagination() {
        calculatePageNumbering();
    }

    /**
     * 페이징의 전체 게시물 건 수를 기반으로 객체를 생성한다
     * @param totalRecordCount 전체 레코드 건 수
     */
    public Pagination(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return 한 페이지당 게시되는 게시물 건 수
     */
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    /**
     * @param recordCountPerPage 한 페이지당 게시되는 게시물 건 수
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * @return 페이지 리스트에 게시되는 페이지 건수
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize 페이지 리스트에 게시되는 페이지 건수
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return 현재 페이지 번호
     */
    public int getCurrentPageNo() {
        return currentPageNo;
    }

    /**
     * @param currentPageNo 현재 페이지 번호
     */
    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
        calculatePageNumbering();
    }

    /**
     * @return 전체 게시물 건 수
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount 전체 게시물 건 수
     */
    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return 페이지 개수
     */
    public int getTotalPageCount() {
        return (getTotalRecordCount() - 1) / getRecordCountPerPage() + 1;
    }

    /**
     * @return 첫번째 페이지 번호
     */
    public int getFirstPageNo() {
        return 1;
    }

    /**
     * @return 마지막 페이지 번호
     */
    public int getLastPageNo() {
        return getTotalPageCount();
    }

    /**
     * @return 페이지 리스트의 첫 페이지 번호
     */
    public int getFirstPageNoOnPageList() {
        return ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
    }

    /**
     * @return 페이지 리스트의 마지막 페이지 번호
     */
    public int getLastPageNoOnPageList() {
        int lastPageNoOnPageList = (getFirstPageNoOnPageList() + getPageSize()) - 1;
        return lastPageNoOnPageList > getTotalPageCount() ? getTotalPageCount() : lastPageNoOnPageList;
    }

    /**
     * @return 페이징 SQL의 조건절에 사용되는 시작 rownum
     */
    public int getFirstRecordIndex() {
        return (getCurrentPageNo() - 1) * getRecordCountPerPage();
    }

    /**
     * @return 페이징 SQL의 조건절에 사용되는 마지막 rownum
     */
    public int getLastRecordIndex() {
        return getCurrentPageNo() * getRecordCountPerPage();
    }

    /**
     * 목록의 페이징 시 현재 페이지의 목록 번호를 반환한다.
     * e.g. 3페이지의 목록 번호는 31, 32, 33... 와 같이 표현해야 할 경우 사용
     * @param index
     * @return 각 페이지 리스트의 번호(e.g. 페이지 목록의 번호)
     */
    public int getPageNumbering(int index) {
        return pageNumbering.get(index);
    }

    /**
     * 현재 페이지에 대한 목록 번호를 계산한다.
     */
    private void calculatePageNumbering() {
        pageNumbering.clear();
        int numbering = (getCurrentPageNo() - 1) * getRecordCountPerPage() + 1;

        for (int i = 0; i < getPageSize(); i++) {
            pageNumbering.add(numbering++);
        }
    }
}