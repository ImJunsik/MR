package com.base.scheduling;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import com.base.exception.NoAffectedException;
import com.base.scheduling.repository.JobSchedulingRepository;

/**
 * <p>Spring SchedulerFactoryBean의 setGlobalJobListeners(JobListener[] globalJobListeners)에
 * 해당 Listener를 등록하여 배치잡 수행 결과 로그를 기록하는 기능을 제공한다.</p>
 * <p>context-scheduling.xml에 정의</p>
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
public class JobListenerImpl implements JobListener {
    private final Logger logger = Logger.getLogger(this.getClass());
    private static final String DEFAULT_SCHEDULER_NAME = "DefaultQuartzScheduler";

    private JobSchedulingRepository jobSchedulingRepository;

    public void setJobSchedulingRepository(JobSchedulingRepository jobSchedulingRepository) {
        this.jobSchedulingRepository = jobSchedulingRepository;
    }

    @Override
    public String getName() {
        return "Suwon Job listener!";
    }

    /**
     * 잡 스케줄러가 실행되지 않을 경우에 호출된다.
     * 잡 스케줄러가 호출되지 않을 경우 로그도 기록할 이유가 없기 때문에 해당 메소드는 구현하지 않는다.
     * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        // do nothing
    }

    /**
     * 잡 스케줄러가 실행되기 직전에 호출된다.
     * 실행된 잡 스케줄러를 DB에 인서트하고 모니터링 가능하게 한다.
     * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Trigger trigger = context.getTrigger();
        JobAttributes jobAttributes = new JobAttributes();

        //try {
            jobAttributes.setJobName(jobDataMap.getString("jobName"));
            jobAttributes.setProgramName(jobDetail.getName());
            jobAttributes.setExecutionStartTime(new Date());
            jobAttributes.setState(JobState.UNDERWAY);
            // 수동 실행일 경우 next fire time 이 null
            jobAttributes.setNextFireTime(trigger.getNextFireTime());
            logger.info("jobAttributes.getJobName : " + jobAttributes.getJobName());
            logger.info("jobAttributes.getProgramName : " + jobAttributes.getProgramName());
            logger.info("jobAttributes.getExecutionStartTime : " + jobAttributes.getExecutionStartTime());
            logger.info("jobAttributes.getState : " + jobAttributes.getState());
            try {
				jobSchedulingRepository.insertJobAttributes(jobAttributes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            jobDataMap.put(jobDetail.getKey().toString(), jobAttributes);
        //} catch (Exception e) {
            //logger.error("Job scheduling log inserts fail!", e);
        //}
    }

    /**
     * 잡 스케줄러 실행 완료(정상/실패 포함)후 호출된다.
     * 정상 실행의 경우 exception는 null 이고, 예외가 발생했을 경우
     * 발생한 예외를 묶어(exception chain) 파라미터로 전달된다.
     * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException exception) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        try {
            JobAttributes jobAttributes = (JobAttributes)jobDataMap.get(jobDetail.getKey().toString());
            jobAttributes.setParameter(jobDataMap.getString(JobAttributes.JOB_DATA_PARAMETER_KEY));
            jobAttributes.setExecutionEndTime(new Date());

            if (exception != null) {
                jobAttributes.setState(JobState.ABNORMAL);
                jobAttributes.setErrorMessage(exception.getMessage());
                jobAttributes.setStackTrace(ExceptionUtils.getStackTrace(exception));
            } else {
                jobAttributes.setState(JobState.NORMAL);
            }
            logger.info("getParameter : " + jobAttributes.getParameter());
            logger.info("getExecutionEndTime : " + jobAttributes.getExecutionEndTime().toString());
            logger.info("getState : " + jobAttributes.getState());
            logger.info("getErrorMessage : " + jobAttributes.getErrorMessage());
            logger.info("getStackTrace : " + jobAttributes.getStackTrace());
            int result = jobSchedulingRepository.updateJobAttributes(jobAttributes);
            if (result == 0) {
                throw new NoAffectedException();
            }

            Scheduler scheduler = context.getScheduler();
            if (scheduler.getSchedulerName().equals(DEFAULT_SCHEDULER_NAME)) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            logger.error("Job scheduling log update fail!", e);
        }
    }
}