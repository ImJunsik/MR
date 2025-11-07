package com.examples.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.base.util.CamelCaseMap;
import com.common.file.domain.AttachFile;
import com.examples.domain.AcademicCareer;
import com.examples.domain.Career;
import com.examples.domain.Employee;
import com.examples.web.EmployeeSearchCondition;

/**
 * 사원관리에 대한 데이터베이스 처리를 담당한다.
 * <p>Repository 클래스는 MyBatis를 이용하여 데이터베이스 쿼리를 수행한다.
 * Repository 클래스는 순수하게 메소드 parameter로 받아온 정보를 바탕으로 쿼리를 수행하도록 해야한다.
 * Repository 클래스에서 비지니스 로직을 포함한다거나 또는 필요에 의해서 다른 Repository 클래스를 주입받아 호출하는 경우가 생겨서는 안된다.
 * Service 클래스 주석에도 있지만 모든 비지니스 로직은 Service 클래스에 존재해야하며 다른 Repository가 제공하는
 * 기능을 사용하고할 때는 Service 클래스에서 해당 Repository를 포함하는 Service 클래스를 주입받아 호출해야
 * 제대로된 트랜젝션을 보장 받을 수 있기 때문이다.</p>
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
@Repository
public class EmployeeRepository extends BaseSqlMapRepository {
    /**
     * @param employeeNo 사원번호
     * @return 사원정보
     */
    public Employee getEmployee(int employeeNo) {
        return selectOne("examples.getEmployee", employeeNo);
    }

    /**
     * @param searchCondition 검색조건
     * @return 검색조건에 해당하는 사원목록
     */
    public List<Map<String, Object>> getEmployeeList(EmployeeSearchCondition searchCondition) {
        return selectList("examples.getEmployeeList", searchCondition);
    }

    /**
     * @param searchCondition 검색조건
     * @return 검색조건에 해당하는 사원목록
     */
    public List<CamelCaseMap> getCamelCaseEmployeeList(EmployeeSearchCondition searchCondition) {
        return selectList("examples.getCamelCaseEmployeeList", searchCondition);
    }

    /**
     * @param employeeNo 사원번호
     * @return 사원번호에 해당하는 첨부파일 목록
     */
    public List<AttachFile> getAttachFileListByEmployeeNo(int employeeNo) {
        return selectList("examples.getAttachFileListByEmployeeNo", employeeNo);
    }

    /**
     * 사원정보를 저장한다.
     * @param employee 사원정보
     * @return 저장된 로우 수
     */
    public int insertEmployee(Employee employee) {
        return insert("examples.insertEmployee", employee);
    }

    /**
     * 사원의 학력정보를 저장한다.
     * @param academicCareer 학력정보
     * @return 저장된 로우 수
     */
    public int insertAcademicCareer(AcademicCareer academicCareer) {
        return insert("examples.insertAcademicCareer", academicCareer);
    }

    /**
     * 사원의 경력정보를 저장한다.
     * @param career 경력정보
     * @return 저장된 로우 수
     */
    public int insertCareer(Career career) {
        return insert("examples.insertCareer", career);
    }

    /**
     * 사원-첨부파일 관계 정보를 저장한다.
     * @param employeeNo 사원번호
     * @param code 첨부파일 코드
     * @return 저장된 로우 수
     */
    public int insertEmployeeAttachFileRelations(int employeeNo, String code) {
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("employeeNo", employeeNo);
        param.put("code", code);

        return insert("examples.insertEmployeeAttachFileRelations", param);
    }

    /**
     * 사원정보를 업데이트한다.
     * @param employee 사원정보
     * @return 업데이트된 로우 수
     */
    public int updateEmployee(Employee employee) {
        return update("examples.updateEmployee", employee);
    }

    /**
     * 사원의 학력정보를 업데이트한다.
     * @param academicCareer 학력정보
     * @return 업데이트된 로우 수
     */
    public int updateAcademicCareer(AcademicCareer academicCareer) {
        return update("examples.updateAcademicCareer", academicCareer);
    }

    /**
     * 사원의 경력정보를 업데이트한다.
     * @param career 경력정보
     * @return 업데이트된 로우 수
     */
    public int updateCareer(Career career) {
        return update("examples.updateCareer", career);
    }

    /**
     * 사원정보를 삭제한다.
     * @param employeeNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteEmployee(int employeeNo) {
        return delete("examples.deleteEmployee", employeeNo);
    }

    /**
     * 사원정보와 더불어 사원정보와 관계된 모든 정보를 삭제한다.
     * @param employeeNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteEmployeeWithAllRelated(int employeeNo) {
        String[] tableNames = new String[]{"EXAM_EMP_ACDMCR", "EXAM_EMP_CAREER", "EXAM_EMP_FILE_RLS", "EXAM_EMP"};
        Map<String, Object> param = new HashMap<String, Object>(2);
        int deletedRowCount = 0;

        for (int i = 0; i < tableNames.length; i++) {
            param.put("tableName", tableNames[i]);
            param.put("employeeNo", employeeNo);
            deletedRowCount += delete("examples.deleteEmployeeWithAllRelated", param);
            param.clear();
        }
        return deletedRowCount;
    }

    /**
     * 사원번호에 해당하는 학력정보를 삭제한다.
     * @param employeeNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteAcademicCareerByEmployeeNo(int employeeNo) {
        return delete("examples.deleteAcademicCareerByEmployeeNo", employeeNo);
    }

    /**
     * 사원의 경력정보를 삭제한다.
     * @param careerNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteCareer(int careerNo) {
        return delete("examples.deleteCareer", careerNo);
    }

    /**
     * 사원번호에 해당하는 경력정보를 삭제한다.
     * @param careerNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteCareerByEmployeeNo(int employeeNo) {
        return delete("examples.deleteCareerByEmployeeNo", employeeNo);
    }

    /**
     * 사원-첨부파일 관계 정보를 삭제한다.
     * @param employeeNo 사원번호
     * @param code 첨부파일 코드
     * @return 삭제된 로우 수
     */
    public int deleteEmployeeAttachFileRelations(int employeeNo, String code) {
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("employeeNo", employeeNo);
        param.put("code", code);

        return delete("examples.deleteEmployeeAttachFileRelations", param);
    }

    /**
     * 사원번호에 해당하는 첨부파일 관계 정보를 삭제한다.
     * @param employeeNo 사원번호
     * @return 삭제된 로우 수
     */
    public int deleteEmployeeAttachFileRelationsByEmployeeNo(int employeeNo) {
        return delete("examples.deleteEmployeeAttachFileRelationsByEmployeeNo", employeeNo);
    }
}