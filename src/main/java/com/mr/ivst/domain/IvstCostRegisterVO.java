package com.mr.ivst.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
import com.mr.step.domain.ChrgrChgHist;

/**
 * 투자지출 품의서 도메인
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 조상욱 최초 작성
 * </pre>
 */

public class IvstCostRegisterVO extends Domain {
    private int mrReqNo;
    private String mrReqTitle;
    private String mrNo;
    private String mrTmpNo;
    private String mrStepCd;
    private String procStCd;
    private String plant;
    private String plantNm;
    private String reqClCd;
    private String reqClDtlCd;
    private String workPsblClCd;
    private String workPsblClNm;
    private String zoutqty;
    private String zoutamt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workPsblDt;
    private String workPsblClRsn;
    private String rigionCd;
    private String issueCtt;
    private String inflnceLoss;
    private String incnvCtt;
    private Integer useCnt;
    private String useCntPrd;
    private String imprvPrpslCtt;
    private String qualEptEftCtt;
    private String quanEptEftCtt;
    private String quanEptEftCd;
    private String cnclEmpNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cnclDt;

    private String cnclRsnCtt;
    private String costClCd;
    private String costClDtlCd;

    private Date safetyChkDt;
    private String safetyChkLoc;
    private String invstCostNo;
    private String capexNo;
    private int delYn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;

    private String invstCd;
    private String invstPurpCd;
    private String invstCdNm;
    private String invstPurpCdNm;

    private double invstCostTotal;

    private List<ChrgrChgHist> appLine;
    //    private List<MrReqIssueReformVO> issues;
    private List<IvstReqEquipVO> equips;
    private List<IvstReqProcVO> procs;

    public int getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(int mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

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

    public String getMrTmpNo() {
        return mrTmpNo;
    }

    public void setMrTmpNo(String mrTmpNo) {
        this.mrTmpNo = mrTmpNo;
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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getReqClCd() {
        return reqClCd;
    }

    public void setReqClCd(String reqClCd) {
        this.reqClCd = reqClCd;
    }

    public String getReqClDtlCd() {
        return reqClDtlCd;
    }

    public void setReqClDtlCd(String reqClDtlCd) {
        this.reqClDtlCd = reqClDtlCd;
    }

    public String getWorkPsblClCd() {
        return workPsblClCd;
    }

    public void setWorkPsblClCd(String workPsblClCd) {
        this.workPsblClCd = workPsblClCd;
    }

    public String getWorkPsblClNm() {
        return workPsblClNm;
    }

    public void setWorkPsblClNm(String workPsblClNm) {
        this.workPsblClNm = workPsblClNm;
    }

    public Date getWorkPsblDt() {
        return workPsblDt;
    }

    public void setWorkPsblDt(Date workPsblDt) {
        this.workPsblDt = workPsblDt;
    }

    public String getWorkPsblClRsn() {
        return workPsblClRsn;
    }

    public void setWorkPsblClRsn(String workPsblClRsn) {
        this.workPsblClRsn = workPsblClRsn;
    }

    public String getRigionCd() {
        return rigionCd;
    }

    public void setRigionCd(String rigionCd) {
        this.rigionCd = rigionCd;
    }

    public String getIssueCtt() {
        return issueCtt;
    }

    public void setIssueCtt(String issueCtt) {
        this.issueCtt = issueCtt;
    }

    public String getInflnceLoss() {
        return inflnceLoss;
    }

    public void setInflnceLoss(String inflnceLoss) {
        this.inflnceLoss = inflnceLoss;
    }

    public String getIncnvCtt() {
        return incnvCtt;
    }

    public void setIncnvCtt(String incnvCtt) {
        this.incnvCtt = incnvCtt;
    }

    public Integer getUseCnt() {
        return useCnt;
    }

    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    public String getUseCntPrd() {
        return useCntPrd;
    }

    public void setUseCntPrd(String useCntPrd) {
        this.useCntPrd = useCntPrd;
    }

    public String getImprvPrpslCtt() {
        return imprvPrpslCtt;
    }

    public void setImprvPrpslCtt(String imprvPrpslCtt) {
        this.imprvPrpslCtt = imprvPrpslCtt;
    }

    public String getQualEptEftCtt() {
        return qualEptEftCtt;
    }

    public void setQualEptEftCtt(String qualEptEftCtt) {
        this.qualEptEftCtt = qualEptEftCtt;
    }

    public String getQuanEptEftCtt() {
        return quanEptEftCtt;
    }

    public void setQuanEptEftCtt(String quanEptEftCtt) {
        this.quanEptEftCtt = quanEptEftCtt;
    }

    public String getQuanEptEftCd() {
        return quanEptEftCd;
    }

    public void setQuanEptEftCd(String quanEptEftCd) {
        this.quanEptEftCd = quanEptEftCd;
    }

    public String getCnclEmpNo() {
        return cnclEmpNo;
    }

    public void setCnclEmpNo(String cnclEmpNo) {
        this.cnclEmpNo = cnclEmpNo;
    }

    public Date getCnclDt() {
        return cnclDt;
    }

    public void setCnclDt(Date cnclDt) {
        this.cnclDt = cnclDt;
    }

    public String getCnclRsnCtt() {
        return cnclRsnCtt;
    }

    public void setCnclRsnCtt(String cnclRsnCtt) {
        this.cnclRsnCtt = cnclRsnCtt;
    }

    public String getCostClCd() {
        return costClCd;
    }

    public void setCostClCd(String costClCd) {
        this.costClCd = costClCd;
    }

    public String getCostClDtlCd() {
        return costClDtlCd;
    }

    public void setCostClDtlCd(String costClDtlCd) {
        this.costClDtlCd = costClDtlCd;
    }

    public Date getSafetyChkDt() {
        return safetyChkDt;
    }

    public void setSafetyChkDt(Date safetyChkDt) {
        this.safetyChkDt = safetyChkDt;
    }

    public String getSafetyChkLoc() {
        return safetyChkLoc;
    }

    public void setSafetyChkLoc(String safetyChkLoc) {
        this.safetyChkLoc = safetyChkLoc;
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

    public List<ChrgrChgHist> getAppLine() {
        return appLine;
    }

    public void setAppLine(List<ChrgrChgHist> appLine) {
        this.appLine = appLine;
    }

    public String getInvstCd() {
        return invstCd;
    }

    public void setInvstCd(String invstCd) {
        this.invstCd = invstCd;
    }

    public String getInvstPurpCd() {
        return invstPurpCd;
    }

    public void setInvstPurpCd(String invstPurpCd) {
        this.invstPurpCd = invstPurpCd;
    }

    public String getInvstCdNm() {
        return invstCdNm;
    }

    public void setInvstCdNm(String invstCdNm) {
        this.invstCdNm = invstCdNm;
    }
    
    public String getInvstPurpCdNm() {
        return invstPurpCdNm;
    }

    public void setInvstPurpCdNm(String invstPurpCdNm) {
        this.invstPurpCdNm = invstPurpCdNm;
    }

    public double getInvstCostTotal() {
        return invstCostTotal;
    }

    public void setInvstCostTotal(double invstCostTotal) {
        this.invstCostTotal = invstCostTotal;
    }
    public List<IvstReqEquipVO> getEquips() {
        return equips;
    }

    public void setEquips(List<IvstReqEquipVO> equips) {
        this.equips = equips;
    }

    public List<IvstReqProcVO> getProcs() {
        return procs;
    }

    public void setProcs(List<IvstReqProcVO> procs) {
        this.procs = procs;
    }

	public String getZoutqty() {
		return zoutqty;
	}

	public void setZoutqty(String zoutqty) {
		this.zoutqty = zoutqty;
	}

	public String getZoutamt() {
		return zoutamt;
	}

	public void setZoutamt(String zoutamt) {
		this.zoutamt = zoutamt;
	}

}
