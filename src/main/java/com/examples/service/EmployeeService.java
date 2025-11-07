package com.examples.service;

import java.util.List;
import java.util.Map;

import com.base.util.CamelCaseMap;
import com.examples.domain.Employee;
import com.examples.web.EmployeeSearchCondition;

/**
 * 사원관리 기능에 대한 인터페이스
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
public interface EmployeeService {
    /**
     * @param employeeNo 사원번호
     * @return 사원정보
     */
    Employee getEmployee(int employeeNo);

    /**
     * @param searchCondition 검색조건
     * @return 검색조건에 해당하는 사원정보 목록
     */
    List<Map<String, Object>> getEmployeeList(EmployeeSearchCondition searchCondition);

    /**
     * @param searchCondition searchCondition 검색조건
     * @return 검색조건에 해당하는 사원정보 목록
     */
    List<CamelCaseMap> getCamelCaseEmployeeList(EmployeeSearchCondition searchCondition);

    /**
     * 사원정보를 저장한다.
     * @param employee 사원정보
     */
    void insertEmployee(Employee employee);

    /**
     * 사원정보를 User Transaction을 사용하여 저장한다.
     * @param employee 사원정보
     */
    void addEmployeeUserTx(Employee employee);

    /**
     * 사원정보를 업데이트한다.
     * @param employee 사원정보
     */
    void updateEmployee(Employee employee);

    /**
     * 사원정보를 삭제한다.
     * @param employeeNos 사원번호
     */
    void deleteEmployees(List<Integer> employeeNos);
}