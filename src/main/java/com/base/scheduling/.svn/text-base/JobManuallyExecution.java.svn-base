package com.base.scheduling;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.MessageSource;

import com.base.util.SpringApplicationContext;

/**
 * context-scheduling.xml에 설정된 시간에 따라 자동으로 실행되는 잡 스케줄러를
 * 수동으로 실행하자고 할 때 수동 실행을 지원한다.
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
public class JobManuallyExecution {
    private JobListener jobListener;
    private MessageSource messageSource;

    /**
     * 잡 스케줄러 리스너를 설정한다.
     * @param jobListener 잡 스케줄러 리스너
     */
    public void setJobListener(JobListener jobListener) {
        this.jobListener = jobListener;
    }

    /**
     * 메시지 소스를 설정한다.
     * @param messageSource 메시지 소스
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 잡 스케줄러를 수동으로 실행한다.
     * 수동으로 실행하기 위해 spring context에 등록되어 있는 bean 이름을 이용하여
     * spring context 에서 look-up 하여 특정 잡을 실행한다.
     * 
     * @param beanId spring context에 내 존재하는 job scheduler 이름
     * @throws JobException 잡 스케줄러 수행 도중 에러 발생 시
     */
    public void execute(String beanId) throws JobException {
        JobDataMap jobDataMap = null;

        try {
            JobDetail jobDetail = (JobDetail)((JobDetail)SpringApplicationContext.getBean(beanId)).clone();
            jobDetail.addJobListener(jobListener.getName());
            jobDataMap = jobDetail.getJobDataMap();

            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            scheduler.addJobListener(jobListener);
            scheduler.scheduleJob(jobDetail, new SimpleTrigger(Scheduler.DEFAULT_GROUP, new Date()));
            scheduler.start();
        } catch (Exception e) {
            if (e instanceof ObjectAlreadyExistsException) {
                throw new JobException(messageSource, "job.already.running.info", new String[] {jobDataMap.getString("jobName")});
            } else {
                throw new JobException(e.getMessage(), e);
            }
        }
    }
}