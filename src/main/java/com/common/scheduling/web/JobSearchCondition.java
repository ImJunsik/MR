package com.common.scheduling.web;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.scheduling.JobState;
import com.base.servlet.tags.Pagination;
import com.base.servlet.tags.support.PaginationSearch;

/**
 * Job Scheduler 모니터링 검색조건
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
public class JobSearchCondition implements PaginationSearch {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date executionStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date executionEndDate;
    private JobState state;
    private String jobName;

    private Pagination pagination = new Pagination();

    /**
     * @return Job 실행 시작 시간
     */
    public Date getExecutionStartDate() {
        return executionStartDate;
    }

    /**
     * @param executionStartDate Job 실행 시작 시간
     */
    public void setExecutionStartDate(Date executionStartDate) {
        this.executionStartDate = executionStartDate;
    }

    /**
     * @return Job 실행 종료 시간
     */
    public Date getExecutionEndDate() {
        return executionEndDate;
    }

    /**
     * @param executionEndDate Job 실행 종료 시간
     */
    public void setExecutionEndDate(Date executionEndDate) {
        this.executionEndDate = executionEndDate;
    }

    /**
     * @return Job 실행 상태
     */
    public JobState getState() {
        return state;
    }

    /**
     * @param state Job 실행 상태
     */
    public void setState(JobState state) {
        this.state = state;
    }

    /**
     * @return Job 이름
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName Job 이름
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
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