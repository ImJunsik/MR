package com.mr.step.domain;

import com.base.domain.Domain;

public class MailToInfoVo extends Domain {
    String toMailAddress;
    String toName;
    String toEmpNo;

    public String getToMailAddress() {
        return toMailAddress;
    }

    public void setToMailAddress(String toMailAddress) {
        this.toMailAddress = toMailAddress;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToEmpNo() {
        return toEmpNo;
    }

    public void setToEmpNo(String toEmpNo) {
        this.toEmpNo = toEmpNo;
    }

}
