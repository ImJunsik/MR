package com.examples.web;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.base.servlet.BaseController;
import com.base.servlet.tags.util.PaginationUtils;
import com.examples.domain.Employee;
import com.examples.service.DepartmentService;
import com.examples.service.EmployeeService;

/**
 * 사원관리에 대한 Front-End(Client)의 요청을 받아 처리한다.<br>
 * <ol>
 *   <li><b>@Controller</b></li>
 *   <ul>
 *     <li>@Controller Annotation은 특별한 기능이 있다기 보다는 Spring에서의 bean 자동 스캔 및 각 bean을 구분해주는 역할
 *       정도를 하는 Annotation이라고 생각하면 된다.
 *     </li>
 *     <li>@Controller의 Annotation이 무엇이냐? 라는 의미보다는 Controller class에는 무조건 @Controller 붙여준다.</li>
 *     <li>@Controller 외에 @Service, @Repository 또한 동일하므로 참고한다.</li>
 *   </ul>
 *   <li><b>@RequestMapping(value="/employee")</b></li>
 *   <ul>
 *     <li>
 *       class 레벨의 @RequestMapping(value="/employee")은 요청 URL중의 일부이다.
 *       예를 들어 http://localhost/employee/employeeList.do 라는 url이 있다면
 *       EmployeeController.java는 사원관리에 필요한 많은 메소드를 가지고 있을 것이고 각각의 메소드는 사원관리라는
 *       특징을 나타내기 위해 /employee/insert.do, /employee/update.do와 같이 일부 반복되는(/employee) url을 입력할 것이다.
 *       이렇게 반복되는 입력을 지양하고 쉬운 코드 관리를 위해 class 레벨의 @RequestMapping Annotation을 이용한다.
 *     </li>
 *   </ul>
 * </ol>
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
@RequestMapping(value = "/employee")
@SessionAttributes("employeeSearchCondition")
public class EmployeeController extends BaseController {
    /*
     * @Autowired Annotation은 특정한 bean을 Spring container에서 자동으로 찾아 해당 변수에 assign해준다.
     * 예제에서는 Spring 프레임워크에 의해서 스캔되어진 bean 중 EmployyService Type의 bean을 찾아
     * employeeService 변수에 주입된다.
     */
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 사원목록 검색조건을 Session에 담기위해 ModelAttribute에 등록
     * @return 검색조건
     */
    @ModelAttribute
    public EmployeeSearchCondition getEmployeeSearchCondition() {
        return new EmployeeSearchCondition();
    }

    /**
     * 부서목록(List&lt;Department&gt;)을 "departmentList" 라는 이름으로 model에 넣는다.
     * <p>{@link #getEmployeeSearchCondition()}와 같은 역할을 한다. 동일한 행위를 두 가지 방식으로 할 수 있다.</p>
     * @param model spring model 객체
     * @throws Exception 모든 에러의 경우
     */
    @ModelAttribute
    public void setDepartmentList(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartmentList());
    }

    /**
     * 사원정보를 조회한다.
     * <p>@RequestParam Annotation은 웹 요청 파라미터를 변수에 바인딩하는데 쓰인다.
     * 기본적으로 @RequestParam에 변경수명 주지 않는다면 변수명이 파라미터 키 값으로 사용되고
     * 파라미터 키 값을 임의로 주고자 한다면 @RequestParam(value=employeeNo) 형태로 사용하면 된다.</p>
     * <p>Caused by: java.lang.IllegalStateException: Optional int parameter 'employeeNo' is not present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.</p>
     * @param employeeNo 사원번호
     * @param model spring model 객체
     * @return 사원정보
     */
    @RequestMapping(value = {"/employeeForm.do", "/popup/employeeForm.do"})
    public String getEmployee(@RequestParam(required=false) Integer employeeNo, Model model) {
        addEmployeeFormModelAttributes(model);

        if (employeeNo != null) {
            model.addAttribute("employee", employeeService.getEmployee(employeeNo));
        } else {
            model.addAttribute("employee", new Employee());
        }
        return "examples/employeeForm";
    }

    /**
     * 본 프로젝트에서는 insert, update, select(단일건)과 같이 business를 표현이 가능한
     * 경우에는 Domain Object를 사용하며, 목록, 삭제(키 값만 필요한 경우).. 에는
     * Map 또는 Java Primitive type 을 사용한다.
     * @param searchCondition 검색 조건
     * @param model spring model 객체
     */
    @RequestMapping(value = "/employeeList.do")
    public String getEmployeeList(EmployeeSearchCondition searchCondition, Model model) {
        List<Map<String, Object>> employeeList = employeeService.getEmployeeList(searchCondition);
        PaginationUtils.bindTotalRecordCount(searchCondition.getPagination(), employeeList, "totalCount");

        model.addAttribute("employeeList", employeeList);

        return "examples/employeeList";
    }

    /**
     * <p>해당 메소드의 인수인 Employee, Model은 Spring MVC의
     * DefaultAnnotaionHandlerMapping와 AnnotationMethodHandlerAdapter 가
     * 메소드가 필요로하는 인수를 자동으로 생성하여 호출하여 준다.</p>
     * Spring MVC가 자동으로 처리 가능한 요청 처리 메소드 인수는 다음과 같다.
     * 
     * <ul>
     *   <li>Servlet API</li>
     *   <li>WebRequest, NativeWebRequest</li>
     *   <li>Locale</li>
     *   <li>InputStream/Reader, OutputStream/Writer</li>
     *   <li>@RequestParam</li>
     *   <li>Map, Model, ModelMap(Model model에 해당)</li>
     *   <li>Command/form objects(Employee employy에 해당)</li>
     *   <li>Errors/BindingResult</li>
     *   <li>SessionStatus</li>
     * </ul>
     * 요청 처리 메소드 반환 타입은 다음과 같다.
     * <ul>
     *   <li>ModelAndView</li>
     *   <li>Model (뷰 이름은 CoC 사용)</li>
     *   <li>Map (위와 동일)</li>
     *   <li>View (모델은 커맨드 객체와 @ModelAttribute를 사용한 메소드가 반환하는 객체)</li>
     *   <li>String (위와 동일)</li>
     *   <li>void (응답을 response 객체를 사용해서 직접 처리하거나, CoC 사용)</li>
     *   <li>Other return type (해당 객체를 model attribute로 뷰에서 사용가능)</li>
     * </ul>
     * 
     * <p>위 내용외 많은 내용들이 더 있지만 실제 프로젝트에서에 자주 사용되는 기능들은 한정되어져 있고
     * 필요한 기능들은 sample 예제를 통해 지속적으로 가이드 하도록 한다.</p>
     *
     * <p>다음으로 메소드 명명과 URL 명명에 대한 고찰로 편의상 메소드명과 URL 주소를 동일하게
     * 줄 수도 있지만 메소드 명명과 URL 명명은 기본적인 다르다.
     * Mehtod naming rule은 동사로 시작을하며 URL 주소는 명사로 시작하는 것이 기본이다.
     * 외부에 노출되는 API, URL은 일반적인 관례를 따르는 것이 좋겠다.</p>
     *
     * <p><a href="http://beyondj2ee.wordpress.com/2013/03/21/%EB%8B%B9%EC%8B%A0%EC%9D%98-api%EA%B0%80-restful-%ED%95%98%EC%A7%80-%EC%95%8A%EC%9D%80-5%EA%B0%80%EC%A7%80-%EC%A6%9D%EA%B1%B0/" target="_blank">당신의 API가 RESTFUL 하지 않은 5가지 증거</a></p>
     * <p><a href="http://docs.oracle.com/javase/tutorial/java/javaOO/methods.html" target="_blank">Defining Methods</a></p>
     * <p><a href="http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html" target="_blank">Code Conventions for the Java TM Programming Language</a></p>
     * 
     * <p>@Valid Annotation은 JSR-303 Spec에 따라 Spring MVC(구현체는 hibernate)
     * 가 Model Binding 검증을 할 수 있도록 한다.
     * {@link com.examples.domain.Employee}</p>
     * 
     * @param employee 사원정보
     * @param model spring model 객체
     */
    @RequestMapping(value = {"/employeeInsert.do", "/employeeUpdate.do"})
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "examples/employeeForm";
        }

        if (employee.getEmployeeNo() > 0) {
            employeeService.updateEmployee(employee);
        } else {
            employeeService.insertEmployee(employee);
        }
        return "redirect:employeeList.do";
    }

    /**
     * User Transaction을 사용하여 사원정보를 저장한다.
     * @param employee 사원정보
     * @return 사원목록 View
     */
    @RequestMapping(value = "/employeeAdd.do")
    public String addEmployeeUserTx(Employee employee) {
        employeeService.addEmployeeUserTx(employee);
        return "redirect:employeeList.do";
    }

    /**
     * 여러건의 사원정보를 삭제한다.
     * @param employeeNo 사원번호
     * @return 사원목록 View
     */
    @RequestMapping(value = "/employeeDelete.do")
    public String deleteEmployee(@RequestParam Integer[] employeeNo) {
        employeeService.deleteEmployees(Arrays.asList(employeeNo));
        return "redirect:employeeList.do";
    }

    private void addEmployeeFormModelAttributes(Model model) {
        Map<String, String> graduationTypes = new LinkedHashMap<String, String>();
        graduationTypes.put("1", "졸업");
        graduationTypes.put("2", "재학");
        graduationTypes.put("3", "휴학");
        graduationTypes.put("4", "자퇴");
        model.addAttribute("graduationTypes", graduationTypes);

        Map<String, String> officialPositions = new LinkedHashMap<String, String>();
        officialPositions.put("1", "주무관");
        officialPositions.put("2", "사무관");
        officialPositions.put("3", "서기관");
        officialPositions.put("4", "마담");
        officialPositions.put("5", "도우미");
        officialPositions.put("6", "사원");
        officialPositions.put("7", "대리");
        officialPositions.put("8", "과장");
        officialPositions.put("9", "차장");
        officialPositions.put("10", "부장");
        model.addAttribute("officialPositions", officialPositions);
    }
}