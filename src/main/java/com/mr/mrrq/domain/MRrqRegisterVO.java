package com.mr.mrrq.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
import com.common.file.domain.MrAtchFile;
import com.mr.step.domain.ChrgrChgHist;

/**
 * MR요청서 도메인
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

public class MRrqRegisterVO extends Domain {
    private int mrReqNo;
    private String mrReqTitle;
    private String mrNo;
    private String mrTmpNo;
    private String mrStepCd;
    private String procStCd;
    private String plant;
    private String plantNm;
    
    private String reqClCd;
    
    //직책과장 test    
	private String reqClCd_2;
    
    private String reqClNm;
    private String reqClDtlCd;
    private String reqClDtlNm;
    private String workPsblClCd;
    private String workPsblClNm;
    
    private String uid;            //userId
    private String connkey;        //전자결배번호
    private String connCode;       //문서종류
    private String connIfName;     //인퍼페이스 명
    private String connIfStatGubun;
    
    
    //hajewook 추가 결재시 사용한 타이틀 본문 내용
    private String mrConnTitle;
    private String mrConnBdoy;    

    public String getPlantNm() {
		return plantNm;
	}

	public void setPlantNm(String plantNm) {
		this.plantNm = plantNm;
	}

	public String getReqClNm() {
		return reqClNm;
	}

	public void setReqClNm(String reqClNm) {
		this.reqClNm = reqClNm;
	}

	public String getReqClDtlNm() {
		return reqClDtlNm;
	}

	public void setReqClDtlNm(String reqClDtlNm) {
		this.reqClDtlNm = reqClDtlNm;
	}

	public String getWorkPsblClNm() {
		return workPsblClNm;
	}

	public void setWorkPsblClNm(String workPsblClNm) {
		this.workPsblClNm = workPsblClNm;
	}

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
    
    
    

    private List<ChrgrChgHist> appLine;
    private List<MrReqIssueReformVO> issues;
    private List<MrReqEquipVO> equips;
    private List<MrReqProcVO> procs;
    private List<MrAtchFile> mrAtchFiles;

    public String getMrConnTitle() {
		return mrConnTitle;
	}

	public void setMrConnTitle(String mrConnTitle) {
		this.mrConnTitle = mrConnTitle;
	}

	public String getMrConnBdoy() {
		return mrConnBdoy;
	}

	public void setMrConnBdoy(String mrConnBdoy) {
		this.mrConnBdoy = mrConnBdoy;
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

    public String getReqClCd() {
        return reqClCd;
    }

    public void setReqClCd(String reqClCd) {
        this.reqClCd = reqClCd;
    }

    //wj:직책과장
    public String getReqClCd_2() {
        return reqClCd;
    }
    //wj:직책과장_test
    public void setReqClCd_2(String reqClCd_2) {
        this.reqClCd_2 = reqClCd_2;
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

    public List<MrReqIssueReformVO> getIssues() {
        return issues;
    }

    public void setIssues(List<MrReqIssueReformVO> issues) {
        this.issues = issues;
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

    public List<MrAtchFile> getMrAtchFiles() {
        return mrAtchFiles;
    }

    public void setMrAtchFiles(List<MrAtchFile> mrAtchFiles) {
        this.mrAtchFiles = mrAtchFiles;
    }

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getConnkey() {
		return connkey;
	}

	public void setConnkey(String connkey) {
		this.connkey = connkey;
	}

	public String getConnCode() {
		return connCode;
	}

	public void setConnCode(String connCode) {
		this.connCode = connCode;
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

}
