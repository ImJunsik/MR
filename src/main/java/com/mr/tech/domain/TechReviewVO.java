package com.mr.tech.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
import com.common.file.domain.MrAtchFile;
import com.mr.common.domain.MrRvRstVO;
import com.mr.mrrq.domain.MrReqEquipVO;
import com.mr.mrrq.domain.MrReqProcVO;
import com.mr.step.domain.ChrgrChgHist;

/**
 * 기술 및 타당성 검토 VO
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 박성룡 최초 작성
 * </pre>
 */

public class TechReviewVO extends Domain {
    private int mrReqNo;
    private int mrTechRvNo;
    private int mrReqProcStepNo;
    private String mrNo;
    private String mrReqTitle;
    private String plant;
    private String plantNm;
    private String reqClCd;
    private String reqClCdNm;
    private String reqClDtlCd;
    private String reqClDtlCdNm;
    private String workPsblClCd;
    private String workPsblClCdNm;
    private Date workPsblDt;
    private String qualEptEftCtt;
    private String quanEptEftCtt;
    private String quanEptEftCd;
    private String quanEptEftCdNm;
    private String quanEff;
    private String quanEffCd;
    private String qualEff;
    private String zoutqty;
    private String zoutamt;
    private String totRvSugg1;
    private String totRvSugg2;
    private String invstCd;
    private String invstNm;
    private String invstPurpCd;
    private String invstPurpNm;

    private String uid;            //userId
    private String connkey;        //전자결배번호
    private String connCode;       //문서종류
    private String connIfName;     //인퍼페이스 명
    private String connIfStatGubun;
    
    public String getInvstNm() {
		return invstNm;
	}

	public void setInvstNm(String invstNm) {
		this.invstNm = invstNm;
	}

	public String getInvstPurpNm() {
		return invstPurpNm;
	}

	public void setInvstPurpNm(String invstPurpNm) {
		this.invstPurpNm = invstPurpNm;
	}

	/*
     * hajewook 추가 통합결재시 타이틀, 본문 담을 변수 츄가*
     */
    private String mrConnTitle;
    private String mrConnBdoy;
    //private String loginEmpNo;
    
    /*public String getLoginEmpNo() {
		return loginEmpNo;
	}
	
	public void setLoginEmpNo(String loginEmpNo) {
		this.loginEmpNo = loginEmpNo;
	}
	*/

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

	private int delYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;

    private List<MrRvRstVO> rvRsts;

    private List<ChrgrChgHist> appLine;
    private List<MrReqEquipVO> equips;
    private List<MrReqProcVO> procs;

    private List<ChrgrChgHist> jobs;


    private List<MrAtchFile> mrAtchFiles;

    public int getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(int mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

    public int getMrTechRvNo() {
        return mrTechRvNo;
    }

    public void setMrTechRvNo(int mrTechRvNo) {
        this.mrTechRvNo = mrTechRvNo;
    }

    public int getMrReqProcStepNo() {
        return mrReqProcStepNo;
    }

    public void setMrReqProcStepNo(int mrReqProcStepNo) {
        this.mrReqProcStepNo = mrReqProcStepNo;
    }

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

    public String getReqClCdNm() {
        return reqClCdNm;
    }

    public void setReqClCdNm(String reqClCdNm) {
        this.reqClCdNm = reqClCdNm;
    }

    public String getReqClDtlCd() {
        return reqClDtlCd;
    }

    public void setReqClDtlCd(String reqClDtlCd) {
        this.reqClDtlCd = reqClDtlCd;
    }

    public String getReqClDtlCdNm() {
        return reqClDtlCdNm;
    }

    public void setReqClDtlCdNm(String reqClDtlCdNm) {
        this.reqClDtlCdNm = reqClDtlCdNm;
    }

    public String getWorkPsblClCd() {
        return workPsblClCd;
    }

    public void setWorkPsblClCd(String workPsblClCd) {
        this.workPsblClCd = workPsblClCd;
    }

    public String getWorkPsblClCdNm() {
        return workPsblClCdNm;
    }

    public void setWorkPsblClCdNm(String workPsblClCdNm) {
        this.workPsblClCdNm = workPsblClCdNm;
    }

    public Date getWorkPsblDt() {
        return workPsblDt;
    }

    public void setWorkPsblDt(Date workPsblDt) {
        this.workPsblDt = workPsblDt;
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

    public String getQuanEptEftCdNm() {
        return quanEptEftCdNm;
    }

    public void setQuanEptEftCdNm(String quanEptEftCdNm) {
        this.quanEptEftCdNm = quanEptEftCdNm;
    }

    public String getQuanEff() {
        return quanEff;
    }

    public void setQuanEff(String quanEff) {
        this.quanEff = quanEff;
    }

    public String getQuanEffCd() {
        return quanEffCd;
    }

    public void setQuanEffCd(String quanEffCd) {
        this.quanEffCd = quanEffCd;
    }

    public String getQualEff() {
        return qualEff;
    }

    public void setQualEff(String qualEff) {
        this.qualEff = qualEff;
    }

    public String getTotRvSugg1() {
        return totRvSugg1;
    }

    public void setTotRvSugg1(String totRvSugg1) {
        this.totRvSugg1 = totRvSugg1;
    }

    public String getTotRvSugg2() {
        return totRvSugg2;
    }

    public void setTotRvSugg2(String totRvSugg2) {
        this.totRvSugg2 = totRvSugg2;
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

    public List<MrRvRstVO> getRvRsts() {
        return rvRsts;
    }

    public void setRvRsts(List<MrRvRstVO> rvRsts) {
        this.rvRsts = rvRsts;
    }

    public List<ChrgrChgHist> getAppLine() {
        return appLine;
    }

    public void setAppLine(List<ChrgrChgHist> appLine) {
        this.appLine = appLine;
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

    public List<ChrgrChgHist> getJobs() {
        return jobs;
    }

    public void setJobs(List<ChrgrChgHist> jobs) {
        this.jobs = jobs;
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

	private String popRiskCheck;

	public String getPopRiskCheck() {
		return popRiskCheck;
	}

	public void setPopRiskCheck(String popRiskCheck) {
		this.popRiskCheck = popRiskCheck;
	}
	

}
