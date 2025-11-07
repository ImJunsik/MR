package com.mr.scheduling;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.base.util.IsOperDistinc;
import com.mr.comp.domain.CompRptVO;
import com.mr.comp.service.CompService;
import com.mr.step.service.MrMailService;

public class IncompleteMrMailSender extends QuartzJobBean{

	private MrMailService mrMailService;

	private final Logger logger = Logger.getLogger(this.getClass());


    public void setMrMailService(MrMailService mrMailService) {
        this.mrMailService = mrMailService;
    }


    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

    	// 현재 날짜 구하기
    	LocalDate now = LocalDate.now();
    	 
    	// 포맷 정의
    	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	 
    	// 포맷 적용
    	//String formatedNow = now.format(formatter);
    	 
    	 // 결과 출력
    	//System.out.println(formatedNow);  
    	
    	logger.info(now + " ============================= IncompleteMrMailSender Start ===================================");

    	if(IsOperDistinc.m_bOper)
    		mrMailService.incompleteMrMail();
    		
    	logger.info(now + " ============================= IncompleteMrMailSender End ===================================");
    }
    
}
