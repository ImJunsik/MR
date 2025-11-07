package com.examples.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;

/**
 * 부서에 대한 데이터베이스 처리를 담당한다.
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
public class DepartmentRepository extends BaseSqlMapRepository {
    /**
     * @return 부서정보 목록
     */
    public List<Map<String, Object>> getDepartmentList() {
        return selectList("examples.getDepartmentList");
    }
}