package com.examples.web;

import com.base.servlet.tags.Pagination;
import com.base.servlet.tags.support.PaginationSearch;

/**
 * 사원목록 검색조건
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
public class EmployeeSearchCondition implements PaginationSearch {
    private int departmentNo;
    private String employeeName;
    private Pagination pagination = new Pagination();

    /**
     * @return 부서번호
     */
    public int getDepartmentNo() {
        return departmentNo;
    }

    /**
     * @param departmentNo 부서번호
     */
    public void setDepartmentNo(int departmentNo) {
        this.departmentNo = departmentNo;
    }

    /**
     * @return 사원이름
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName 사원이름
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}