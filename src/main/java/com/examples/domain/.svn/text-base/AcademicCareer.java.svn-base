package com.examples.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.base.domain.Domain;

/**
 * 사원의 학력 정보
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
public class AcademicCareer extends Domain {
    private int academicCareerNo;
    private int employeeNo;

    @NotEmpty
    private String schoolName;

    @NotEmpty
    @Pattern(regexp="^[0-9]*$", message="숫자만 입력하세요.")
    @Length(min=4, max=4)
    private String graduationYear;
    private String graduationTypeCode;
    private String graduationTypeName;
    private float point;

    /**
     * @return 학력정보 번호 (key)
     */
    public int getAcademicCareerNo() {
        return academicCareerNo;
    }

    /**
     * @param academicCareerNo 학력정보 번호
     */
    public void setAcademicCareerNo(int academicCareerNo) {
        this.academicCareerNo = academicCareerNo;
    }

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
     * @return 학교명
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName 학교명
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return 졸업년도
     */
    public String getGraduationYear() {
        return graduationYear;
    }

    /**
     * @param graduationYear 졸업년도
     */
    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    /**
     * @return 졸업유형코드
     */
    public String getGraduationTypeCode() {
        return graduationTypeCode;
    }

    /**
     * @param graduationTypeCode 졸업유형코드
     */
    public void setGraduationTypeCode(String graduationTypeCode) {
        this.graduationTypeCode = graduationTypeCode;
    }

    /**
     * @return 졸업유형명
     */
    public String getGraduationTypeName() {
        return graduationTypeName;
    }

    /**
     * @param graduationTypeName 졸업유형명
     */
    public void setGraduationTypeName(String graduationTypeName) {
        this.graduationTypeName = graduationTypeName;
    }

    /**
     * @return 학점
     */
    public float getPoint() {
        return point;
    }

    /**
     * @param point 학점
     */
    public void setPoint(float point) {
        this.point = point;
    }
}