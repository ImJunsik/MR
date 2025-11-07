package com.mr.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.base.util.IsOperDistinc;
import com.mr.step.service.MrMailService;

//context-scheduling.xml >> 승인기한을 넘긴 MR 메일 발송
public class LimitDateMailSender  extends QuartzJobBean {

	
    private MrMailService mrMailService;


    public void setMrMailService(MrMailService mrMailService) {
        this.mrMailService = mrMailService;
    }


    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

    	//if(IsOperDistinc.m_bOper)		// Yoo 운영서버일때만 동작 한다
    	if(false)						// 승인기한을 넘긴 MR 메일 발송을 일시적으로 막는다 23.09.12
    	{
	    	//승인기한을 넘긴 MR 메일 발송
	        mrMailService.limitDateMrSendMail();
    	}
    }

}
