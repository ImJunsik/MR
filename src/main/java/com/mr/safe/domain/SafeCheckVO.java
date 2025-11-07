package com.mr.safe.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
import com.common.file.domain.MrAtchFile;
import com.mr.ivst.domain.IvstReqEquipVO;
import com.mr.ivst.domain.IvstReqProcVO;

/**
 * 가동전 안전점검 도메인
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.21 조상욱 최초 작성
 * </pre>
 */

public class SafeCheckVO extends Domain {
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

    private String safetyChkDt;
    private String safetyChkTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date safetyDate;
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

    private String pnidDt;
    private String mrPrfmDt;
    
    private List<SafeChrgrChgHistVO> appLine;
    private List<IvstReqEquipVO> equips;
    private List<IvstReqProcVO> procs;

    private List<SafeChrgrChgHistVO> safeChrgr;

    private List<MrAtchFile> mrAtchFiles;

    private boolean jobsSkipCheck;
    
    private String hazopActYn;

    public String getPnidDt() {
		return pnidDt;
	}

	public void setPnidDt(String pnidDt) {
		this.pnidDt = pnidDt;
	}

	public String getMrPrfmDt() {
		return mrPrfmDt;
	}

	public void setMrPrfmDt(String mrPrfmDt) {
		this.mrPrfmDt = mrPrfmDt;
	}
	
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

    public String getSafetyChkDt() {
        return safetyChkDt;
    }

    public void setSafetyChkDt(String safetyChkDt) {
        this.safetyChkDt = safetyChkDt;
    }

    public String getSafetyChkTime() {
        return safetyChkTime;
    }

    public void setSafetyChkTime(String safetyChkTime) {
        this.safetyChkTime = safetyChkTime;
    }

    public Date getSafetyDate() {
        return safetyDate;
    }

    public void setSafetyDate(Date safetyDate) {
        this.safetyDate = safetyDate;
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

    public List<SafeChrgrChgHistVO> getAppLine() {
        return appLine;
    }

    public void setAppLine(List<SafeChrgrChgHistVO> appLine) {
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

    public List<SafeChrgrChgHistVO> getSafeChrgr() {
        return safeChrgr;
    }

    public void setSafeChrgr(List<SafeChrgrChgHistVO> safeChrgr) {
        this.safeChrgr = safeChrgr;
    }

    public List<MrAtchFile> getMrAtchFiles() {
        return mrAtchFiles;
    }

    public void setMrAtchFiles(List<MrAtchFile> mrAtchFiles) {
        this.mrAtchFiles = mrAtchFiles;
    }

    public boolean isJobsSkipCheck() {
        return jobsSkipCheck;
    }

    public void setJobsSkipCheck(boolean jobsSkipCheck) {
        this.jobsSkipCheck = jobsSkipCheck;
    }
    
    private String sheChgYn;
    
    public String getSheChgYn() {
        return sheChgYn;
    }

    public void setSheChgYn(String sheChgYn) {
        this.sheChgYn = sheChgYn;
    }

    public String getHazopActYn() {
        return hazopActYn;
    }

    public void setHazopActYn(String hazopActYn) {
        this.hazopActYn = hazopActYn;
    }
    
    private String mrHazopYn;
    

    public String getMrHazopYn() {
        return mrHazopYn;
    }

    public void setMrHazopYn(String hazopActYn) {
        this.mrHazopYn = mrHazopYn;
    }
    
    private String stepType;
    

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }
    
}
