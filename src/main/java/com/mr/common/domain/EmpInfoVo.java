package com.mr.common.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;

/**
 * 사원정보 도메인
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.20 박성룡 최초 작성
 * </pre>
 */
public class EmpInfoVo extends Domain {
    private String empNo;
    private String empName;
    private String dutyNo;
    private String dutyName;
    private String teamNo;
    private String teamName;
    private String passwd;
    private String chrgrClCd;
    private String next;
    private String mrReqNo;
    private String empEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date procTrmDt;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDutyNo() {
        return dutyNo;
    }

    public void setDutyNo(String dutyNo) {
        this.dutyNo = dutyNo;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getChrgrClCd() {
        return chrgrClCd;
    }

    public void setChrgrClCd(String chrgrClCd) {
        this.chrgrClCd = chrgrClCd;
    }

    public Date getProcTrmDt() {
        return procTrmDt;
    }

    public void setProcTrmDt(Date procTrmDt) {
        this.procTrmDt = procTrmDt;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(String mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

}
