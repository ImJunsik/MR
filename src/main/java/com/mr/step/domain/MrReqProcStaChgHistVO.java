package com.mr.step.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;

public class MrReqProcStaChgHistVO extends Domain {

    private int mrReqProcStepDetNo;
    private String chrgrClCd;
    private String effEndDtm;
    private String effStaDtm;
    private int mrReqNo;
    private String procStCd;

    private int delYn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;

    public int getMrReqProcStepDetNo() {
        return mrReqProcStepDetNo;
    }

    public void setMrReqProcStepDetNo(int mrReqProcStepDetNo) {
        this.mrReqProcStepDetNo = mrReqProcStepDetNo;
    }

    public String getChrgrClCd() {
        return chrgrClCd;
    }

    public void setChrgrClCd(String chrgrClCd) {
        this.chrgrClCd = chrgrClCd;
    }

    public String getEffEndDtm() {
        return effEndDtm;
    }

    public void setEffEndDtm(String effEndDtm) {
        this.effEndDtm = effEndDtm;
    }

    public String getEffStaDtm() {
        return effStaDtm;
    }

    public void setEffStaDtm(String effStaDtm) {
        this.effStaDtm = effStaDtm;
    }

    public int getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(int mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

    public String getProcStCd() {
        return procStCd;
    }

    public void setProcStCd(String procStCd) {
        this.procStCd = procStCd;
    }

    public int getDelYn() {
        return delYn;
    }

    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }

    public Date getFstRgstDt() {
        return fstRgstDt;
    }

    public void setFstRgstDt(Date fstRgstDt) {
        this.fstRgstDt = fstRgstDt;
    }

    public String getFstRgstr() {
        return fstRgstr;
    }

    public void setFstRgstr(String fstRgstr) {
        this.fstRgstr = fstRgstr;
    }

    public Date getLastChgDt() {
        return lastChgDt;
    }

    public void setLastChgDt(Date lastChgDt) {
        this.lastChgDt = lastChgDt;
    }

    public String getLastChgr() {
        return lastChgr;
    }

    public void setLastChgr(String lastChgr) {
        this.lastChgr = lastChgr;
    }

}
