package com.mr.step.domain;

import java.util.Date;
import java.util.List;

import com.base.domain.Domain;

public class MrMailVO extends Domain {
    private String mailTitle;
    private String mrNo;
    private Integer mrReqNo;
    private Date fstProcTrmDt;
    private String mrReqTitle;
    private String mrStepCd;
    private String procStCd;
    private String mrStepName;
    private String procStName;
    private String from;
    private String fromNm;
    private String fromTeamNm;
    private String fromGradeNm;
    private String to;
    private String toNm;
    private String content;
    private String url;
    private String chrgrClCd;
    private String chrgEmpNo;
    private String cnclRsnCtt;
    private String safetyChkDt;
    private String safetyChkLoc;

    private List<MailToInfoVo> toInfos;
    private List<String> ccs;

    public String getMrReqTitle() {
        return mrReqTitle;
    }

    public void setMrReqTitle(String mrReqTitle) {
        this.mrReqTitle = mrReqTitle;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public Integer getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(Integer mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

    public Date getFstProcTrmDt() {
        return fstProcTrmDt;
    }

    public void setFstProcTrmDt(Date fstProcTrmDt) {
        this.fstProcTrmDt = fstProcTrmDt;
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

    public String getMrStepName() {
        return mrStepName;
    }

    public void setMrStepName(String mrStepName) {
        this.mrStepName = mrStepName;
    }

    public String getProcStName() {
        return procStName;
    }

    public void setProcStName(String procStName) {
        this.procStName = procStName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChrgrClCd() {
        return chrgrClCd;
    }

    public void setChrgrClCd(String chrgrClCd) {
        this.chrgrClCd = chrgrClCd;
    }

    public String getFromNm() {
        return fromNm;
    }

    public void setFromNm(String fromNm) {
        this.fromNm = fromNm;
    }

    public String getFromTeamNm() {
        return fromTeamNm;
    }

    public void setFromTeamNm(String fromTeamNm) {
        this.fromTeamNm = fromTeamNm;
    }

    public String getFromGradeNm() {
        return fromGradeNm;
    }

    public void setFromGradeNm(String fromGradeNm) {
        this.fromGradeNm = fromGradeNm;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToNm() {
        return toNm;
    }

    public void setToNm(String toNm) {
        this.toNm = toNm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCnclRsnCtt() {
        return cnclRsnCtt;
    }

    public void setCnclRsnCtt(String cnclRsnCtt) {
        this.cnclRsnCtt = cnclRsnCtt;
    }

    public String getSafetyChkDt() {
        return safetyChkDt;
    }

    public void setSafetyChkDt(String safetyChkDt) {
        this.safetyChkDt = safetyChkDt;
    }

    public String getSafetyChkLoc() {
        return safetyChkLoc;
    }

    public void setSafetyChkLoc(String safetyChkLoc) {
        this.safetyChkLoc = safetyChkLoc;
    }

    public List<String> getCcs() {
        return ccs;
    }

    public void setCcs(List<String> ccs) {
        this.ccs = ccs;
    }


    public List<MailToInfoVo> getToInfos() {
        return toInfos;
    }

    public void setToInfos(List<MailToInfoVo> toInfos) {
        this.toInfos = toInfos;
    }

    public String getChrgEmpNo() {
        return chrgEmpNo;
    }

    public void setChrgEmpNo(String chrgEmpNo) {
        this.chrgEmpNo = chrgEmpNo;
    }

    /*2025-11-11 ijs 투자비재산정 용도 추가*/
    private String mailStep;
    

    public String getMailStep() {
        return mailStep;
    }

    public void setMailStep(String mailStep) {
        this.mailStep = mailStep;
    }

}
