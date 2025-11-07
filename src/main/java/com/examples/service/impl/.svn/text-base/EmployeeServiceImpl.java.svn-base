package com.examples.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.TransformerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.base.service.BaseService;
import com.base.service.ServiceException;
import com.base.util.CamelCaseMap;
import com.common.file.collection.util.AttachFileAvailablePredicate;
import com.common.file.domain.AttachFile;
import com.common.file.service.AttachFileService;
import com.examples.domain.AcademicCareer;
import com.examples.domain.Career;
import com.examples.domain.Employee;
import com.examples.repository.EmployeeRepository;
import com.examples.service.EmployeeService;
import com.examples.web.EmployeeSearchCondition;

/**
 * 사원관리에 대한 비즈니스 로직을 담당하는 클래스이다.
 * <p>Service 클래스는 업무에 필요한 모든 로직을 담고있는 클래로 업무 로직을 비롯하여 Database Transation, 예외 처리 등
 * 업무에 있어 핵심적인 부분을 담당하게 된다.</p>
 * 
 * <p>Database Transation은 Spring AOP를 통해 Service 클래스의 특정 메소드에서 트랜젝션을 start하고 commit,
 * rollback을 처리하록 설정되어 있다.실제 소스 코드내에서는 트랜젝션을 시작하고 커밋하는 행위의 코드는 없지만
 * AOP를 통해 자동으로 처리가 된다. <strong>단, AOP에 의해 처리가 되기 위해서는 클래스 및 메소드 명명 규칙을 반드시 따라야 한다.</strong>
 * 
 * <p>Service 클래스 명은 impl 패키지 하위에 xxxxImpl 로 끝나야하며, 메소드는 개발표준가이드 문서에 정의된 것처럼
 * insert*, update*, delete*, get* 등으로 시작해야한다.
 * 필요에 따라 프로젝트가 진행 되면서 필요한 명명규칙이 규칙이 있다면 명명규칙을 정하고 <u>{@linkplain context-transaction.xml}
 * 에 명명에 해당하는 AOP 설정을 추가한다.</u></p>
 * 
 * <p>Spring AOP의 트랜젝션은 설정에 등록된 특정 메소드 이름으로 시작하는 메소드를 만나게 되면 트랜젝션이 시작되고 메소드 수행시
 * Exception이 발생하면 rollback을 수행하게 된다. 그렇기 때문에 여러 업무를 단일 트랜젝션으로 묶기 위해서는 insert*, update* 등으로
 * 시작되는 메소드에서 메소드 수행에 필요한 Service 또는 Repository 모두 호출해야만 단일 트랜젝션을 보장 받을 수 있다.</p>
 * 
 * <p>Service 클래스의 메소드 주석은 interface 클래스에 작성하도록 한다. 이유는 xxxServiceImpl은 특정 interface를 구현한 클래스이므로
 * 모든 API 노출은 interface가되고 그렇기 때문에 interface 클래스 메소드 주석을 작성해야 한다.
 * 구현 클래스인 xxxServiceImpl에 메소드 주석이 필요한 경우는 interface에 정의되지 않은 내부적으로 사용이되는 메소드가 작성된 경우에는
 * 구현 클래스 메소드에 주석을 작성해야 한다.</p>
 * 
 * <p>본 프로그램의 주석들은 예제를 위한 상세한 내용이 담겨야 하므로 구현체인 해당 클래스에 주석을 작성하였다...</p>
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
@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /*
     * 첨부파일 기능을 수행하기 위해 AttachFileService 선언하고 Injection 하였다.
     * 다른 클래스에서 제공하는 기능이 필요할 경우 그에 해당하는 Repository 클래스를
     * 사용하지 말고 Service 클래스를 사용하여 호출하는 것을 권장한다.
     * Service 클래스는 모든 비지니스 로직(데이터 가공 포함)과 예외 처리등을 담고있는
     * 캡슐화(encapsulation)된 클래스이기 때문이다.
     */
    @Autowired
    private AttachFileService attachFileService;

    @Override
    public Employee getEmployee(int employeeNo) {
        return employeeRepository.getEmployee(employeeNo);
    }

    @Override
    public List<Map<String, Object>> getEmployeeList(EmployeeSearchCondition searchCondition) {
        return employeeRepository.getEmployeeList(searchCondition);
    }

    @Override
    public List<CamelCaseMap> getCamelCaseEmployeeList(EmployeeSearchCondition searchCondition) {
        return employeeRepository.getCamelCaseEmployeeList(searchCondition);
    }

    @Override
    public void insertEmployee(Employee employee) {
        if (employee.getPhotoFile().isNew()) {
            attachFileService.saveAttachFile(employee.getPhotoFile());
        }

        employeeRepository.insertEmployee(employee);

        if (CollectionUtils.isNotEmpty(employee.getAcademicCareers())) {
            for (AcademicCareer academicCareer : employee.getAcademicCareers()) {
                academicCareer.setEmployeeNo(employee.getEmployeeNo());
                employeeRepository.insertAcademicCareer(academicCareer);
            }
        }

        if (CollectionUtils.isNotEmpty(employee.getCareers())) {
            for (Career career : employee.getCareers()) {
                career.setEmployeeNo(employee.getEmployeeNo());
                employeeRepository.insertCareer(career);
            }
        }

        if (CollectionUtils.isNotEmpty(employee.getAttachFiles())) {
            List<AttachFile> attachFiles = attachFileService.saveAttachFiles(employee.getAttachFiles());
            for (AttachFile attachFile : attachFiles) {
                employeeRepository.insertEmployeeAttachFileRelations(employee.getEmployeeNo(), attachFile.getCode());
            }
        }
    }

    /**
     * <p>스프링 프레임워크에서는 두 가지 방식의 트랜젝션은 제공한다.
     * 프로그램적 트랜잭션 모델(programmatic transaction model)과 선언적 트랜잭션 모델(declarative transaction model)
     * 방식의 트랜젝션을 제공하는데 본 프로젝트에서는 기본적으로 선언적 트랜젝션 방식을 사용한다.</p>
     * 
     * <p>하지만 선언적 트랜젝션만으로는 특정 요구사항을 만족시키기 어려운 경우가 있다.
     * 예를 들어 두 개의 테이블에 데이터를 인서트하는데 인서트하는 과정에서 두 번째 테이블 인서트시 Exception이 발생했다면
     * 선언적 트랜젝션 방식에서는 인서트가 성공한 첫번째 테이블의 데이터까지 rollback이 될 것이다.
     * 하지만 요구사항이 인서트가 성공한 테이블까지는 rollback을 하지 말고 commit이 되어야한다면 선언적 트랜젝션 방식으로는
     * 구현하기 어렵다. 그렇기 때문에 해당 메소드는 프로그램적 트랜젝션을 사용하는 방법을 가이드하고 있다.</p>
     * 
     * <p>프로그램적 트랜잭션을 사용하기 위해서는 기본적으로 선언적 트랜잭션 설정에서 선언된
     * 메소드 이름과는 다른 이름을 가져야 한다. 메소드 이름이 선언된 메소드 이름으로 시작하면
     * 아무리 트랜잭션 객체를 생성하여 commit, rollback 해봐야 의미가 없다. 즉 선언적 트랜잭션으로 동작한다는 것이다.
     * 그러므로 프로그램적 트랜잭션을 사용을 위해서는 context-transaction.xml에 정의된 메소드 이름과 다른 이름을
     * 사용해야 한다.</p>
     * 
     * <p>스프링 프레임워크의 프로그램적 트랜젝션을 제대로 이해하기 위해서는 많은 학습이 요구되는 부분으로
     * 일반적인 트랜젝션인 위에 기술한 내용(요구사항)을 만족하기 위한 코드이므로 참고하기 바란다.<p>
     */
    @Override
    public void addEmployeeUserTx(Employee employee) {
        /*
         * 트랜젝션을 시작하기 위해 REQUIRES_NEW 옵션의 트랜젝션 객체를 얻는다
         * REQUIRES_NEW의 의미는 "부모 트랜잭션을 무시하고 무조건 새로운 트랜잭션이 생성되도록 함" 이다.
         */
        TransactionStatus attachFileTransaction = getTransactionStatus();
        if (employee.getPhotoFile().isNew()) {
            attachFileService.saveAttachFile(employee.getPhotoFile());
        }
        // 증명사진 첨부파일 저장이 성공하였다면 트랜젝션 commit을 수행한다.
        commit(attachFileTransaction);

        // 새로운 트랜젝션을 시작하기 위해 다시 트랜젝션을 시작한다.
        TransactionStatus employeeTransaction = getTransactionStatus();
        employeeRepository.insertEmployee(employee);
        commit(employeeTransaction);

        TransactionStatus academicCareerTransaction = getTransactionStatus();

        try {
            if (CollectionUtils.isNotEmpty(employee.getAcademicCareers())) {
                for (AcademicCareer academicCareer : employee.getAcademicCareers()) {
                    academicCareer.setEmployeeNo(employee.getEmployeeNo());
                    employeeRepository.insertAcademicCareer(academicCareer);
                }

                /*
                 * 증명사진 첨부파일, 사원정보 저장은 commit이 되고
                 * 학력정보는 rollback 되는 것을 테스트하기 위해 exception을 발생시킴
                 */
                "".substring(3);

            }
        } catch (Exception e) {
            // Exception이 발생했을 경우 학력정보 트랜젝션을 rollback한다.
            rollback(academicCareerTransaction);
            throw new ServiceException("트랜젝션 테스트를 위해 무조건 예외를 발생시킨다.", e);
        }


        if (CollectionUtils.isNotEmpty(employee.getCareers())) {
            for (Career career : employee.getCareers()) {
                career.setEmployeeNo(employee.getEmployeeNo());
                employeeRepository.insertCareer(career);
            }
        }

        if (CollectionUtils.isNotEmpty(employee.getAttachFiles())) {
            List<AttachFile> attachFiles = attachFileService.saveAttachFiles(employee.getAttachFiles());
            for (AttachFile attachFile : attachFiles) {
                employeeRepository.insertEmployeeAttachFileRelations(employee.getEmployeeNo(), attachFile.getCode());
            }
        }
    }

    /**
     * 비지니스 로직에 따라 추상 예외 클래스인 Exception가 아닌 적절한
     * Exception을 throw할 경우가 업무쪽에는 종종 발생한다.
     * 이럴 경우 각 Layer에 따라 Controller는 WebException을 Service는
     * ServiceException, Repository는 RepositoryException을
     * 상속하여 업무에 맞는 Exception 클래스를 작성하거나 공통에서 제공해주는
     * WebException, ServiceException, RepositoryException을 적절히 사용하여  throw하도록한다.
     * 
     * @see com.common.file.service.impl.AttachFileServiceImpl#deleteAttachFiles(List)
     */
    @Override
    public void updateEmployee(Employee employee) {
        int updatedRowCount = 0;

        if (employee.getPhotoFile().isNew()) {
            attachFileService.saveAttachFile(employee.getPhotoFile());
        }

        updatedRowCount = employeeRepository.updateEmployee(employee);
        if (updatedRowCount == 0) {
            throw new ServiceException(messageSource, "employee.update.error");
        }

        if (CollectionUtils.isNotEmpty(employee.getAcademicCareers())) {
            updatedRowCount = 0;
            employeeRepository.deleteAcademicCareerByEmployeeNo(employee.getEmployeeNo());

            for (AcademicCareer academicCareer : employee.getAcademicCareers()) {
                academicCareer.setEmployeeNo(employee.getEmployeeNo());
                updatedRowCount += employeeRepository.insertAcademicCareer(academicCareer);
            }

            if (updatedRowCount != employee.getAcademicCareers().size()) {
                throw new ServiceException(messageSource, "employee.update.error");
            }
        }

        if (CollectionUtils.isNotEmpty(employee.getCareers())) {
            updatedRowCount = 0;
            employeeRepository.deleteCareerByEmployeeNo(employee.getEmployeeNo());

            for (Career career : employee.getCareers()) {
                career.setEmployeeNo(employee.getEmployeeNo());
                updatedRowCount += employeeRepository.insertCareer(career);
            }

            if (updatedRowCount != employee.getCareers().size()) {
                throw new ServiceException(messageSource, "employee.update.error");
            }
        }

        if (CollectionUtils.isNotEmpty(employee.getAttachFiles())) {
            List<AttachFile> attachFiles = attachFileService.saveAttachFiles(employee.getAttachFiles());

            updatedRowCount = 0;
            int attachFileAvailableCount = CollectionUtils.countMatches(attachFiles, new AttachFileAvailablePredicate());

            if (attachFileAvailableCount > 0) {
                for (AttachFile attachFile : attachFiles) {
                    if (attachFile.isNew()) {
                        updatedRowCount += employeeRepository.insertEmployeeAttachFileRelations(employee.getEmployeeNo(), attachFile.getCode());
                    }

                    if (attachFile.isDeleted()) {
                        updatedRowCount += employeeRepository.deleteEmployeeAttachFileRelations(employee.getEmployeeNo(), attachFile.getCode());
                    }
                }
            }

            if (updatedRowCount != attachFileAvailableCount) {
                throw new ServiceException(messageSource, "attach.file.error");
            }
        }
    }

    @Override
    public void deleteEmployees(List<Integer> employeeNos) {
        Employee employee = null;
        List<AttachFile> attachFiles = null;

        for (int employeeNo : employeeNos) {
            employee = employeeRepository.getEmployee(employeeNo);
            attachFiles = employeeRepository.getAttachFileListByEmployeeNo(employeeNo);
            employeeRepository.deleteEmployeeWithAllRelated(employeeNo);

            if (StringUtils.isNotEmpty(employee.getPhotoFileCode())) {
                attachFileService.deleteAttachFile(employee.getPhotoFileCode());
            }

            if (CollectionUtils.isNotEmpty(attachFiles)) {
                @SuppressWarnings("unchecked")
                Collection<String> codes = CollectionUtils.collect(attachFiles, TransformerUtils.invokerTransformer("getCode"));
                attachFileService.deleteAttachFiles(new ArrayList<String>(codes));
            }
        }
    }
}