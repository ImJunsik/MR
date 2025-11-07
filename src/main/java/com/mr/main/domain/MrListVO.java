package com.mr.main.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.base.domain.Domain;

/**
 * 메인페이지 하단 상세용 VO
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.24 박성룡 최초 작성
 * 2018.05.01 중복현상 수정
 * 2019.08.28 품의금액, 예산금액 컬럼추가
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class MrListVO extends Domain {
    private String mrNo;
    private String mrReqNo;
    private String plant;
    private String workPsblClNm;
    private String mrReqTitle;
    private String fstRgstDt;
    private String teamNo;
    private String fstRgstr;
    private String teamName;
    private String empName;
    private String actEmpName;
    private String reqEmpName;
    private String jobEmpName;
    private String proEmpName;
    private String actTeamName;
    private String reqTeamName;
    private String jobTeamName;
    private String proTeamName;
    private String mrStepCd;
    private String mrStepNm;
    private String procStCd;
    private String procStNm;
    private String effStaDtm;
    private String capexNo;    
    private Integer totalCount;
    private String sapCostTot;		//품의금액
    private String clCd06;			//공용자산
    private String costTotalSum;	//예산금액    

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public String getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(String mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

    public String getWorkPsblClNm() {
        return workPsblClNm;
    }

    public void setWorkPsblClNm(String workPsblClNm) {
        this.workPsblClNm = workPsblClNm;
    }

    public String getMrReqTitle() {
        return mrReqTitle;
    }

    public void setMrReqTitle(String mrReqTitle) {
        this.mrReqTitle = mrReqTitle;
    }

    public String getFstRgstDt() {
        return fstRgstDt;
    }

    public void setFstRgstDt(String fstRgstDt) {
        this.fstRgstDt = fstRgstDt;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getFstRgstr() {
        return fstRgstr;
    }

    public void setFstRgstr(String fstRgstr) {
        this.fstRgstr = fstRgstr;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getActEmpName() {
        return actEmpName;
    }

    public void setActEmpName(String actEmpName) {
        this.actEmpName = actEmpName;
    }

    public String getReqEmpName() {
        return reqEmpName;
    }

    public void setReqEmpName(String reqEmpName) {
        this.reqEmpName = reqEmpName;
    }

    public String getJobEmpName() {
        return jobEmpName;
    }

    public void setJobEmpName(String jobEmpName) {
        this.jobEmpName = jobEmpName;
    }

    public String getProEmpName() {
        return proEmpName;
    }

    public void setProEmpName(String proEmpName) {
        this.proEmpName = proEmpName;
    }

    public String getActTeamName() {
        return actTeamName;
    }

    public void setActTeamName(String actTeamName) {
        this.actTeamName = actTeamName;
    }

    public String getReqTeamName() {
        return reqTeamName;
    }

    public void setReqTeamName(String reqTeamName) {
        this.reqTeamName = reqTeamName;
    }

    public String getJobTeamName() {
        return jobTeamName;
    }

    public void setJobTeamName(String jobTeamName) {
        this.jobTeamName = jobTeamName;
    }

    public String getProTeamName() {
        return proTeamName;
    }

    public void setProTeamName(String proTeamName) {
        this.proTeamName = proTeamName;
    }

    public String getMrStepCd() {
        return mrStepCd;
    }

    public void setMrStepCd(String mrStepCd) {
        this.mrStepCd = mrStepCd;
    }

    public String getMrStepNm() {
        return mrStepNm;
    }

    public void setMrStepNm(String mrStepNm) {
        this.mrStepNm = mrStepNm;
    }

    public String getProcStCd() {
        return procStCd;
    }

    public void setProcStCd(String procStCd) {
        this.procStCd = procStCd;
    }

    public String getProcStNm() {
        return procStNm;
    }

    public void setProcStNm(String procStNm) {
        this.procStNm = procStNm;
    }

    public String getEffStaDtm() {
        return effStaDtm;
    }

    public void setEffStaDtm(String effStaDtm) {
        this.effStaDtm = effStaDtm;
    }

    public String getCapexNo() {
        return capexNo;
    }

    public void setCapexNo(String capexNo) {
        this.capexNo = capexNo;
    }
    
    public String getSapCostTot() {
        return sapCostTot;
    }

    public String getClCd06() {
        return clCd06;
    }

    public void setSapCostTot(String sapCostTot) {
        this.sapCostTot = sapCostTot;
    }
    
    public void setClCd06(String clCd06) {
        this.clCd06 = clCd06;
    }
    
    public String getCostTotalSum() {
        return costTotalSum;
    }

    public void setCostTotalSum(String costTotalSum) {
        this.costTotalSum = costTotalSum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
