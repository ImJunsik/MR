package com.mr.tech.domain;

import java.util.Calendar;
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
 * 기술 및 타당성 검토 투자비 산정 VO
 *
 * @author 박성룡
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.07 박성룡 최초 작성
 * 2017.12.10 wj: chrgDetNo추가(직책과장드롭다운 리스트)
 * </pre>
 */
public class TechInvestVO extends Domain {

    private int mrReqNo;
    private int mrInvstCostNo;
    private int mrReqProcStepNo;
    private String mrNo;
    private String mrReqTitle;
    private String plant;
    private String plantNm;    
    private String reqClCd;
    
    private String chrgDetNo;			//wj 추가 직책과장    
    
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
    private String invstYear;
    private String costItemCd;
    private String costItemNm;
    private String costItemCtt;
    private long costTot;
    private long cost01;
    private long cost02;
    private long cost03;
    private long cost04;
    private long cost05;
    private long cost06;
    private long cost07;
    private long cost08;
    private long cost09;
    private long cost10;
    private long cost11;
    private long cost12;

    private long costSumC01;
    private long costSumC02;
    private long costSumC03;
    private long costSumC04;
    private long costSumC05;
    private long costSumC06;
    private long costTotalSum;

    private String engYn;
    private String totRvSugg;
    
    private String mrConnTitle;
    private String mrConnBdoy;
    
    private int delYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;
    
    //Z02I날짜추가
    private String fstRgstDtText;
    
    private String uid;            //userId
    private String connkey;        //전자결배번호
    private String connCode;       //문서종류
    private String connIfName;     //인퍼페이스 명
    private String connIfStatGubun;
    
    
    
    private List<ChrgrChgHist> appLine;
    private List<MrReqEquipVO> equips;
    private List<MrReqProcVO> procs;

    private List<TechInvestVO> techInvests;
    private List<TechInvestVO> investCosts;
    private List<MrRvRstVO> rvRsts;

    private List<MrAtchFile> mrAtchFiles;

    private Calendar cal = Calendar.getInstance();
    private int currnetYear = cal.get(cal.YEAR);

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

    public int getMrInvstCostNo() {
        return mrInvstCostNo;
    }

    public void setMrInvstCostNo(int mrInvstCostNo) {
        this.mrInvstCostNo = mrInvstCostNo;
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

    //wj 직책과장 추가
    public String getChrgDetNo() {
        return chrgDetNo;
    }

    public void setChrgDetNo(String chrgDetNo) {
        this.chrgDetNo = chrgDetNo;
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

    public String getInvstYear() {
        return invstYear;
    }

    public void setInvstYear(String invstYear) {
        this.invstYear = invstYear;
    }

    public String getCostItemCd() {
        return costItemCd;
    }

    public void setCostItemCd(String costItemCd) {
        this.costItemCd = costItemCd;
    }

    public String getCostItemNm() {
        return costItemNm;
    }

    public void setCostItemNm(String costItemNm) {
        this.costItemNm = costItemNm;
    }

    public String getCostItemCtt() {
        return costItemCtt;
    }

    public void setCostItemCtt(String costItemCtt) {
        this.costItemCtt = costItemCtt;
    }

    public long getCostTot() {
        return costTot;
    }

    public void setCostTot(long costTot) {
        this.costTot = costTot;
    }

    public long getCost01() {
        return cost01;
    }

    public void setCost01(long cost01) {
        this.cost01 = cost01;
    }

    public long getCost02() {
        return cost02;
    }

    public void setCost02(long cost02) {
        this.cost02 = cost02;
    }

    public long getCost03() {
        return cost03;
    }

    public void setCost03(long cost03) {
        this.cost03 = cost03;
    }

    public long getCost04() {
        return cost04;
    }

    public void setCost04(long cost04) {
        this.cost04 = cost04;
    }

    public long getCost05() {
        return cost05;
    }

    public void setCost05(long cost05) {
        this.cost05 = cost05;
    }

    public long getCost06() {
        return cost06;
    }

    public void setCost06(long cost06) {
        this.cost06 = cost06;
    }

    public long getCost07() {
        return cost07;
    }

    public void setCost07(long cost07) {
        this.cost07 = cost07;
    }

    public long getCost08() {
        return cost08;
    }

    public void setCost08(long cost08) {
        this.cost08 = cost08;
    }

    public long getCost09() {
        return cost09;
    }

    public void setCost09(long cost09) {
        this.cost09 = cost09;
    }

    public long getCost10() {
        return cost10;
    }

    public void setCost10(long cost10) {
        this.cost10 = cost10;
    }

    public long getCost11() {
        return cost11;
    }

    public void setCost11(long cost11) {
        this.cost11 = cost11;
    }

    public long getCost12() {
        return cost12;
    }

    public void setCost12(long cost12) {
        this.cost12 = cost12;
    }

    public long getCostSumC01() {
        return costSumC01;
    }

    public void setCostSumC01(long costSumC01) {
        this.costSumC01 = costSumC01;
    }

    public long getCostSumC02() {
        return costSumC02;
    }

    public void setCostSumC02(long costSumC02) {
        this.costSumC02 = costSumC02;
    }

    public long getCostSumC03() {
        return costSumC03;
    }

    public void setCostSumC03(long costSumC03) {
        this.costSumC03 = costSumC03;
    }

    public long getCostSumC04() {
        return costSumC04;
    }

    public void setCostSumC04(long costSumC04) {
        this.costSumC04 = costSumC04;
    }

    public long getCostSumC05() {
        return costSumC05;
    }

    public void setCostSumC05(long costSumC05) {
        this.costSumC05 = costSumC05;
    }

    public long getCostSumC06() {
        return costSumC06;
    }

    public void setCostSumC06(long costSumC06) {
        this.costSumC06 = costSumC06;
    }

    public long getCostTotalSum() {
        return costTotalSum;
    }

    public void setCostTotalSum(long costTotalSum) {
        this.costTotalSum = costTotalSum;
    }

    public String getEngYn() {
        return engYn;
    }

    public void setEngYn(String engYn) {
        this.engYn = engYn;
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
    
    //Z02I날짜추가
    public String getFstRgstDtText() {
        return fstRgstDtText;
    }

    public void setLastChgr(String lastChgr) {
        this.lastChgr = lastChgr;
    }

    //Z02I날짜추가
    public void setFstRgstDtText(String fstRgstDtText) {
        this.fstRgstDtText = fstRgstDtText;
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

    public List<TechInvestVO> getTechInvests() {
        return techInvests;
    }

    public void setTechInvests(List<TechInvestVO> techInvests) {
        this.techInvests = techInvests;
    }

    public List<TechInvestVO> getInvestCosts() {
        return investCosts;
    }

    public void setInvestCosts(List<TechInvestVO> investCosts) {
        this.investCosts = investCosts;
    }

    public int getCurrnetYear() {
        return currnetYear;
    }

    public List<MrRvRstVO> getRvRsts() {
        return rvRsts;
    }

    public void setRvRsts(List<MrRvRstVO> rvRsts) {
        this.rvRsts = rvRsts;
    }

    public List<MrAtchFile> getMrAtchFiles() {
        return mrAtchFiles;
    }

    public void setMrAtchFiles(List<MrAtchFile> mrAtchFiles) {
        this.mrAtchFiles = mrAtchFiles;
    }

    public String getTotRvSugg() {
        return totRvSugg;
    }

    public void setTotRvSugg(String totRvSugg) {
        this.totRvSugg = totRvSugg;
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