package com.common.scheduling.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.orm.ibatis.BaseSqlMapRepository;
import com.base.scheduling.JobAttributes;
import com.common.scheduling.web.JobSearchCondition;

/**
 * Job Scheduler 모니터링에 대한 데이터베이스 처리를 담당한다.
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
public class JobMonitoringRepository extends BaseSqlMapRepository {
    /**
     * @param id Job Schedule 아이디
     * @return Job 정보
     */
    public JobAttributes getJobAttributes(String id) {
        return selectOne("common.job.getJobAttributes", id);
    }

    /**
     * @param condition 실행된 Job 목록 검색조건
     * @return 검색조건에 해당하는 실행된 Job 목록
     */
    public List<JobAttributes> getJobExecutedList(JobSearchCondition condition) {
        return selectList("common.job.getJobExecutedList", condition);
    }
}