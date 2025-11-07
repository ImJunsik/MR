package com.common.scheduling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.scheduling.JobAttributes;
import com.base.scheduling.JobException;
import com.base.scheduling.JobManuallyExecution;
import com.base.service.BaseService;
import com.base.service.ServiceException;
import com.common.scheduling.repository.JobMonitoringRepository;
import com.common.scheduling.service.JobMonitoringService;
import com.common.scheduling.web.JobSearchCondition;

@Service
public class JobMonitoringServiceImpl extends BaseService implements JobMonitoringService {
    @Autowired
    private JobMonitoringRepository jobMonitoringRepository;

    @Autowired
    private JobManuallyExecution jobManuallyExecution;

    @Override
    public JobAttributes getJobAttributes(String id) {
        return jobMonitoringRepository.getJobAttributes(id);
    }

    @Override
    public List<JobAttributes> getJobExecutedList(JobSearchCondition condition) {
        return jobMonitoringRepository.getJobExecutedList(condition);
    }

    /**
     * @throws ServiceException Job 수동실행 또는 Job 수행도중 에러가 발생했을 경우
     * @see com.common.scheduling.service.JobMonitoringService#executeJob(java.lang.String)
     */
    @Override
    public void executeJob(String beanId) {
        try {
            jobManuallyExecution.execute(beanId);
        } catch (JobException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}