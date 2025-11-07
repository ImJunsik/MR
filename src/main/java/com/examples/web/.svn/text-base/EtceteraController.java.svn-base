package com.examples.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.servlet.BaseController;
import com.base.servlet.WebException;
import com.base.util.CamelCaseMap;
import com.base.util.Mailer;
import com.examples.service.DepartmentService;
import com.examples.service.EmployeeService;

@Controller
@RequestMapping(value = "/examples")
public class EtceteraController extends BaseController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private Mailer mailer;

    @ModelAttribute
    public void setDepartmentList(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartmentList());
    }

    @ModelAttribute
    public EmployeeSearchCondition getEmployeeSearchCondition() {
        return new EmployeeSearchCondition();
    }

    @RequestMapping(value = "/etceteras.do")
    public String getEtceteras() {
        return "examples/etceteras";
    }

    // JSP 에러 및 404 에러 함께 데모...
    /**
     * 에러처리 데모 위한 API
     * @param type
     * @return
     */
    @RequestMapping(value = "/error.do")
    public String throwException(@RequestParam Integer type) {
        if (type == 1) {
            throw new WebException("어쩌고 저쩌고.. 에러가 발생 했어요~");
        } else {
            "".substring(3);
        }
        return null;
    }

    /**
     * Java 카멜 표기법을 데모 위한 API
     * @param model
     */
    @ModelAttribute
    public void setCamelCaseEmployeeList(Model model) {
        List<CamelCaseMap> employeeList = employeeService.getCamelCaseEmployeeList(new EmployeeSearchCondition());
        model.addAttribute("employeeList", employeeList);
    }

    /**
     * Spring MVC에서의 Ajax 응답 데이터의 데모를 보여주기 위한 API
     * @param searchCondition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/employeeList.do")
    public List<Map<String, Object>> getEmployeeList(EmployeeSearchCondition searchCondition) {
        //model에 넣어서 ResponseBody 할 수도 있음..
        return employeeService.getEmployeeList(searchCondition);
    }

    /**
     * 메일 발송 데모를위한 API
     * @param to
     * @param subject
     * @param contents
     * @return
     */
    @RequestMapping(value = "/mailSend.do")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String contents) {
        mailer.sendHtml("master@esr.com", to, subject, contents);
        return "redirect:etceteras.do";
    }
}