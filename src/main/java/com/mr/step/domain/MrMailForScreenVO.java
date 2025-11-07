package com.mr.step.domain;

import com.base.domain.Domain;

public class MrMailForScreenVO extends Domain {
    private String mrReqTitle;
    private String mrNo;
    private String mrStepCd;
    private String mrStepNm;
    private String procStCd;
    private String procStNm;
    private String chrgr;

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public String getMrReqTitle() {
        return mrReqTitle;
    }

    public void setMrReqTitle(String mrReqTitle) {
        this.mrReqTitle = mrReqTitle;
    }

    public String getmrStepNm() {
        return mrStepNm;
    }

    public void setmrStepNm(String mrStepNm) {
        this.mrStepNm = mrStepNm;
    }

    public String getProcStNm() {
        return procStNm;
    }

    public void setProcStNm(String procStNm) {
        this.procStNm = procStNm;
    }

    public String getMrStepCd() {
        return mrStepCd;
    }

    public void setMrStepCd(String mrStepCd) {
        this.mrStepCd = mrStepCd;
    }

    public String getProcStCd() {
        return procStCd;
    }

    public void setProcStCd(String procStCd) {
        this.procStCd = procStCd;
    }

    public String getChrgr() {
        return chrgr;
    }

    public void setChrgr(String chrgr) {
        this.chrgr = chrgr;
    }

}