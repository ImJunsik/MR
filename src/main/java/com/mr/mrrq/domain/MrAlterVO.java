package com.mr.mrrq.domain;

public class MrAlterVO {
	private final int ROWLIMIT = 23;
	String mrReqNo;
	String mrNo;
	String mrTmpNo;
	String MrReqTitle;
	String projectTitle;
	String invstCostNo;
	String capexNo;
	int sapCostTot;
	String rtnCode;
	String rtnMsg;
	Integer page;
	
	public String getMrNo() {
		return mrNo;
	}
	public void setMrNo(String mrNo) {
		this.mrNo = mrNo;
	}
	public String getMrReqTitle() {
		return MrReqTitle;
	}
	public void setMrReqTitle(String mrReqTitle) {
		MrReqTitle = mrReqTitle;
	}
	public String getInvstCostNo() {
		return invstCostNo;
	}
	public void setInvstCostNo(String invstCostNo) {
		this.invstCostNo = invstCostNo;
	}
	public String getCapexNo() {
		return capexNo;
	}
	public void setCapexNo(String capexNo) {
		this.capexNo = capexNo;
	}
	public String getMrReqNo() {
		return mrReqNo;
	}
	public void setMrReqNo(String mrReqNo) {
		this.mrReqNo = mrReqNo;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public int getSapCostTot() {
		return sapCostTot;
	}
	public void setSapCostTot(int sapCostTot) {
		this.sapCostTot = sapCostTot;
	}
	public String getMrTmpNo() {
		return mrTmpNo;
	}
	public void setMrTmpNo(String mrTmpNo) {
		this.mrTmpNo = mrTmpNo;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getROWLIMIT() {
		return ROWLIMIT;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

}
