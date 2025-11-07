package com.mr.step.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;

public class MrReqProcStepDetVO extends Domain {

    private int mrReqProcStepDetNo;
    private int mrReqProcStepNo;
    private int mrReqNo;
    private String mrStepCd;
    private String procStCd;
    private String jobRvCd;

    private String connIfName;     //인퍼페이스 명
    private String connIfStatGubun;
    
    private String mrProcStepDetClCd;
    private String rvReqCtt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reqDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDt;

    private String procTrmClCd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstProcTrmDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date scndProcTrmDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thrdProcTrmDt;

    private int delYn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;
    
    //전자결재 관련 추가
    private List<ChrgrChgHist> appLine;
    
    public int getMrReqProcStepDetNo() {
        return mrReqProcStepDetNo;
    }

    public void setMrReqProcStepDetNo(int mrReqProcStepDetNo) {
        this.mrReqProcStepDetNo = mrReqProcStepDetNo;
    }

    public int getMrReqProcStepNo() {
        return mrReqProcStepNo;
    }

    public void setMrReqProcStepNo(int mrReqProcStepNo) {
        this.mrReqProcStepNo = mrReqProcStepNo;
    }

    public int getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(int mrReqNo) {
        this.mrReqNo = mrReqNo;
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

    public String getJobRvCd() {
        return jobRvCd;
    }

    public void setJobRvCd(String jobRvCd) {
        this.jobRvCd = jobRvCd;
    }

    public String getMrProcStepDetClCd() {
        return mrProcStepDetClCd;
    }

    public void setMrProcStepDetClCd(String mrProcStepDetClCd) {
        this.mrProcStepDetClCd = mrProcStepDetClCd;
    }

    public String getRvReqCtt() {
        return rvReqCtt;
    }

    public void setRvReqCtt(String rvReqCtt) {
        this.rvReqCtt = rvReqCtt;
    }

    public Date getReqDt() {
        return reqDt;
    }

    public void setReqDt(Date reqDt) {
        this.reqDt = reqDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getProcTrmClCd() {
        return procTrmClCd;
    }

    public void setProcTrmClCd(String procTrmClCd) {
        this.procTrmClCd = procTrmClCd;
    }

    public Date getFstProcTrmDt() {
        return fstProcTrmDt;
    }

    public void setFstProcTrmDt(Date fstProcTrmDt) {
        this.fstProcTrmDt = fstProcTrmDt;
    }

    public Date getScndProcTrmDt() {
        return scndProcTrmDt;
    }

    public void setScndProcTrmDt(Date scndProcTrmDt) {
        this.scndProcTrmDt = scndProcTrmDt;
    }

    public Date getThrdProcTrmDt() {
        return thrdProcTrmDt;
    }

    public void setThrdProcTrmDt(Date thrdProcTrmDt) {
        this.thrdProcTrmDt = thrdProcTrmDt;
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

	public String getConnIfName() {
		return connIfName;
	}

	public void setConnIfName(String connIfName) {
		this.connIfName = connIfName;
	}

	public String getConnIfStatGubun() {
		return connIfStatGubun;
	}

	public void setConnIfStatGubun(String connIfStatGubun) {
		this.connIfStatGubun = connIfStatGubun;
	}

	public List<ChrgrChgHist> getAppLine() {
		return appLine;
	}

	public void setAppLine(List<ChrgrChgHist> appLine) {
		this.appLine = appLine;
	}
	
	
	

}
