package com.base.scheduling;

import java.util.Date;

import com.base.domain.Domain;

/**
 * 잡 스케줄러의 정보
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
public class JobAttributes extends Domain {
    public static final String JOB_DATA_PARAMETER_KEY = "__parameter__";

    private String id;
    private String jobName;
    private String programName;
    private String parameter;
    private Date executionStartTime;
    private Date executionEndTime;
    private JobState state;
    private String errorMessage;
    private String stackTrace;
    private Date nextFireTime;

    /**
     * 잡 스케줄러의 아이디를 반환한다.
     * @return 잡 스케줄러의 아이디
     */
    public String getId() {
        return id;
    }

    /**
     * 잡 스케줄러의 아이디를 설정한다.
     * @param id 잡 스케줄러 아이디
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 잡 스케줄러명을 반환한다.
     * @return 잡 스케줄러명
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 잡 스케줄러명을 설정한다.
     * @param jobName 잡 스케줄러명
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 잡 스케줄러의 프로그램명을 반환한다.
     * @return 잡 스케줄러 프로그램명
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 잡 스케줄러의 프로그램명을 설정한다.
     * @param programName 잡 스케줄러의 프로그램명
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 잡 스케줄러에서 사용되는 파라미터를 반환한다.
     * @return 잡 스케줄러 파라미터
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 잡 스케줄러에서 사용되는 파라미터를 설정한다.
     * @param parameter 잡 스케줄러 파라미터
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * 잡 스케줄러의 실행 시작시간을 반환한다.
     * @return 잡 스케줄러 실행 시작시간
     */
    public Date getExecutionStartTime() {
        return executionStartTime;
    }

    /**
     * 잡 스케줄러의 실행 시작시간을 설정한다.
     * @param executionStartTime 잡 스케줄러 실행 시작시간
     */
    public void setExecutionStartTime(Date executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    /**
     * 잡 스케줄러의 실행 종료시간을 반환한다.
     * @return 잡 스케줄러 실행 종료시간
     */
    public Date getExecutionEndTime() {
        return executionEndTime;
    }

    /**
     * 잡 스케줄러의 실행 종료시간을 설정한다.
     * @param executionEndTime 잡 스케줄러 실행 종료시간
     */
    public void setExecutionEndTime(Date executionEndTime) {
        this.executionEndTime = executionEndTime;
    }

    /**
     * 잡 스케줄러 실행 상태를 반환한다.
     * @return 잡 스케줄러 실행 상태
     */
    public JobState getState() {
        return state;
    }

    /**
     * 잡 스케줄러 실행 상태를 설정한다.
     * @param result 잡 스케줄러 실행 상태
     */
    public void setState(JobState state) {
        this.state = state;
    }

    /**
     * 잡 스케줄러 실행 시 에레메시지를 반환한다.(에러 발생 시)
     * @return 에레메시지
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 잡 스케줄러 실행 시 에레메시지를 설정한다.
     * @param errorMessage 에레메시지
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 잡 스케줄러 실행 시 스택트레이스를 반환한다.(에러 발생 시)
     * @return 스택트레이스
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * 잡 스케줄러 실행 시 스택트레이스를 설정한다.
     * @param stackTrace 스택트레이스
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * 잡 스케줄러의 다음 실행 시작시간을 반환한다.
     * @return 잡 스케줄러 다음 실행 시작시간
     */
    public Date getNextFireTime() {
        return nextFireTime;
    }

    /**
     * 잡 스케줄러의 다음 실행 시작시간을 설정한다.
     * @param nextFireTime 잡 스케줄러 다음 실행 시작시간
     */
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }
}