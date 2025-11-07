package com.common.scheduling.web;

import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.base.scheduling.JobAttributes;
import com.base.scheduling.JobException;
import com.base.scheduling.JobState;
import com.base.scheduling.propertyeditor.JobStatePropertyEditor;
import com.base.servlet.BaseController;
import com.base.servlet.tags.util.PaginationUtils;
import com.common.scheduling.service.JobMonitoringService;

/**
 * Job Scheduler 모니터링에 대한 Front-End의 요청 처리를 담당한다.
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
@Controller
@RequestMapping(value = "/job")
@SessionAttributes("jobSearchCondition")
public class JobMonitoringController extends BaseController {
    @Autowired
    private JobMonitoringService jobMonitoringService;

    /**
     * 웹 요청 데이터 중 JobState에 해당하는 데이터를 적절하게 바인딩하기 위해
     * JobStatePropertyEditor를 등록한다.
     * @param dataBinder WebDataBinder 객체
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(JobState.class, new JobStatePropertyEditor());
    }

    /**
     * 실행된 Job 목록 검색조건을 Session에 담기위해 ModelAttribute에 등록
     * @return 검색조건
     */
    @ModelAttribute
    public JobSearchCondition getJobSearchCondition() {
        return new JobSearchCondition();
    }

    /**
     * 검색조건 중 하나인 Job 상태를 ModelAttribute에 등록
     * <p>{@link #getJobSearchCondition()}와 같은 역할을 한다. 동일한 행위를 두 가지 방식으로 할 수 있다.</p>
     * @param model
     */
    @ModelAttribute
    public void setJobStateSet(Model model) {
        model.addAttribute("jobStateList", EnumSet.allOf(JobState.class));
    }

    /**
     * 실행된 Job 목록을 조회한다.
     * @param condition 검색조건
     * @param model Model 객체
     * @return Job 목록 View
     */
    @RequestMapping(value = "/jobExecutedList.do")
    public String getJobExecutedList(JobSearchCondition condition, Model model) {
        List<JobAttributes> jobAttributesList = jobMonitoringService.getJobExecutedList(condition);
        PaginationUtils.bindTotalRecordCount(condition.getPagination(), jobAttributesList, "totalRecordCount");
        model.addAttribute("jobAttributesList", jobAttributesList);

        return "scheduling/jobExecutedList";
    }

    /**
     * Job id에 해당하는 Job 정보를 조회한다.
     * @param id Job id
     * @param model Model 객체
     * @return Job 정보 조회 View
     */
    @RequestMapping(value = "popup/jobAttributes.do")
    public String getJobAttributes(@RequestParam String id, Model model) {
        model.addAttribute("jobAttributes", jobMonitoringService.getJobAttributes(id));
        return "scheduling/jobAttributes";
    }

    /**
     * Spring Context에 존재하는 Job의 Bean id 값으로 특정 Job을 스케줄러와는 별도로
     * Job을 수동으로 실행 시킨다.
     * @param beanId Spring Context에 존재하는 Job의 Bean id
     * @throws JobException ServiceException Job 수동실행 또는 Job 수행도중 에러가 발생했을 경우
     */
    @ResponseBody
    @RequestMapping(value = "/jobExecute.do")
    public void executeJob(@RequestParam String beanId) throws JobException {
        jobMonitoringService.executeJob(beanId);

        /*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        if (true) {
            throw new WebException("그냥 에러났음.!!!!");
        }
         */
    }
}