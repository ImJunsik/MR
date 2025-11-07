package com.examples.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.base.domain.Domain;
import com.common.file.collection.util.AttachFilePathTransformer;
import com.common.file.domain.AttachFile;

/**
 * 사원 정보
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
public class Employee extends Domain {
    private int employeeNo;

    /*
     * JSR-303 Validation Annotation
     */
    @NotEmpty
    @Length(max=10)
    private String employeeName;

    /*
     * Controller에서 웹 요청 데이터를 바인딩할 때
     * String 형태의 웹 요청 데이터를 어떤 형태의 포멧 데이터를
     * 바인딩(String <-> int)할 것인지의 정보를 알려주는 Spring Annotation
     * 아래 @DateTimeFormat 도 마찬가지임.
     */
    @NumberFormat(style = Style.NUMBER, pattern = "#,###.###")
    private int salary;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private boolean married;
    private int departmentNo;
    private String departmentName;
    private String photoFileCode;

    private AttachFile photoFile;

    @Valid
    private List<AcademicCareer> academicCareers;
    private List<Career> careers;
    private List<AttachFile> attachFiles;

    /**
     * @return 사원번호
     */
    public int getEmployeeNo() {
        return employeeNo;
    }

    /**
     * @param employeeNo 사원번호
     */
    public void setEmployeeNo(int employeeNo) {
        this.employeeNo = employeeNo;
    }

    /**
     * @return 사원명
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName 사원명
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return 급여
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary 급여
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return 입사일
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate 입사일
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @return 결혼여부
     */
    public boolean isMarried() {
        return married;
    }

    /**
     * @param married 결혼여부
     */
    public void setMarried(boolean married) {
        this.married = married;
    }

    /**
     * @return 부서번호
     */
    public int getDepartmentNo() {
        return departmentNo;
    }

    /**
     * @param departmentNo 부서번호
     */
    public void setDepartmentNo(int departmentNo) {
        this.departmentNo = departmentNo;
    }

    /**
     * @return 부서명
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName 부서명
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return 증명사진 파일코드
     */
    public String getPhotoFileCode() {
        if (photoFileCode == null && photoFile != null && photoFile.isNew()) {
            return photoFile.getCode();
        }
        return photoFileCode;
    }

    /**
     * @param photoFileCode 증명사진 파일코드
     */
    public void setPhotoFileCode(String photoFileCode) {
        this.photoFileCode = photoFileCode;
    }

    /**
     * @return 증명사진 파일
     */
    public AttachFile getPhotoFile() {
        if (photoFile != null) {
            photoFile.setPath(getAppConfig().getString("attach-path.example"));
        }
        return photoFile;
    }

    /**
     * @param photoFile 증명사진 파일
     */
    public void setPhotoFile(AttachFile photoFile) {
        this.photoFile = photoFile;
    }

    /**
     * @return 학력정보 목록
     */
    public List<AcademicCareer> getAcademicCareers() {
        return academicCareers;
    }

    /**
     * @param academicCareers 학력정보 목록
     */
    public void setAcademicCareers(List<AcademicCareer> academicCareers) {
        this.academicCareers = academicCareers;
    }

    /**
     * @return 경력정보 목록
     */
    public List<Career> getCareers() {
        return careers;
    }

    /**
     * @param careers 경력정보 목록
     */
    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    /**
     * @return 첨부파일 목록
     */
    public List<AttachFile> getAttachFiles() {
        if (CollectionUtils.isNotEmpty(attachFiles)) {
            CollectionUtils.transform(attachFiles, new AttachFilePathTransformer(getAppConfig().getString("attach-path.example")));
        }
        return attachFiles;
    }

    /**
     * @param attachFiles 첨부파일 목록
     */
    public void setAttachFiles(List<AttachFile> attachFiles) {
        this.attachFiles = attachFiles;
    }
}