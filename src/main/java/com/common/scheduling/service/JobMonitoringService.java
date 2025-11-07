package com.common.scheduling.service;

import java.util.List;

import com.base.scheduling.JobAttributes;
import com.common.scheduling.web.JobSearchCondition;

/**
 * Job Scheduler 모니터링에 대한 비지니스(업무 로직) 로직을 표현한다.
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
public interface JobMonitoringService {
    /**
     * @param id Job Schedule 아이디
     * @return Job 정보
     */
    JobAttributes getJobAttributes(String id);

    /**
     * @param condition 실행된 Job 목록 검색조건
     * @return 검색조건에 해당하는 실행된 Job 목록
     */
    List<JobAttributes> getJobExecutedList(JobSearchCondition condition);

    /**
     * Spring Context에 존재하는 Job의 Bean id 값으로 특정 Job을 스케줄러와는 별도로
     * Job을 수동으로 실행 시킨다.
     * @param beanId Spring Context에 존재하는 Job의 Bean id
     */
    void executeJob(String beanId);
}