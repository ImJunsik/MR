package com.mr.detailsearch.domain;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.base.domain.Domain;
import com.mr.mrrq.domain.MrReqEquipVO;
import com.mr.mrrq.domain.MrReqProcVO;

/**
 * 상세검색조건 도메인
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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class DetailSearchVO extends Domain{

    // 페이지당 갯수 (기본 100)
    private final int ROWLIMIT = 23;
    private String fromDate;
    private String toDate;
    private String mrNo;
    private String mrStepCd;
    private String requestTeamNo;
    private String requestEmpNo;
    private String drawNo;
    private String jobEmpNo;
    private String actionTeamNo;
    private String projectEmpNo;
    private String projectTitle;
    private String proc;
    private String equip;
    private String reqType;
    private String work;
    private String capexNo;
    private String myMrSearch;
    private String businessCencel;

    private Integer page;

    private List<MrReqEquipVO> equips;
    private List<MrReqProcVO> procs;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public String getMrStepCd() {
        return mrStepCd;
    }

    public void setMrStepCd(String mrStepCd) {
        this.mrStepCd = mrStepCd;
    }

    public String getRequestTeamNo() {
        return requestTeamNo;
    }

    public void setRequestTeamNo(String requestTeamNo) {
        this.requestTeamNo = requestTeamNo;
    }

    public String getRequestEmpNo() {
        return requestEmpNo;
    }

    public void setRequestEmpNo(String requestEmpNo) {
        this.requestEmpNo = requestEmpNo;
    }

    public String getDrawNo() {
        return drawNo;
    }

    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo;
    }

    public String getJobEmpNo() {
        return jobEmpNo;
    }

    public void setJobEmpNo(String jobEmpNo) {
        this.jobEmpNo = jobEmpNo;
    }

    public String getActionTeamNo() {
        return actionTeamNo;
    }

    public void setActionTeamNo(String actionTeamNo) {
        this.actionTeamNo = actionTeamNo;
    }

    public String getProjectEmpNo() {
        return projectEmpNo;
    }

    public void setProjectEmpNo(String projectEmpNo) {
        this.projectEmpNo = projectEmpNo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCapexNo() {
        return capexNo;
    }

    public void setCapexNo(String capexNo) {
        this.capexNo = capexNo;
    }

    public List<MrReqEquipVO> getEquips() {
        return equips;
    }

    public void setEquips(List<MrReqEquipVO> equips) {
        this.equips = equips;
    }

    public List<MrReqProcVO> getProcs() {
        return procs;
    }

    public void setProcs(List<MrReqProcVO> procs) {
        this.procs = procs;
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

    public String getMyMrSearch() {
        return myMrSearch;
    }

    public void setMyMrSearch(String myMrSearch) {
        this.myMrSearch = myMrSearch;
    }

	public String getBusinessCencel() {
		return businessCencel;
	}

	public void setBusinessCencel(String businessCencel) {
		this.businessCencel = businessCencel;
	}

}
