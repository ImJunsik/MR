package com.mr.comp.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
import com.common.file.domain.MrAtchFile;
import com.mr.common.domain.MrRvRstVO;

/**
 * 자료등록관리 도메인
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.07.28 조상욱 최초 작성
 * </pre>
 */

public class CompFileManageVO extends Domain {
    private int mrAtchFileNo;
    private int mrReqProcStepDetNo;
    private int mrReqNo;
    private String mrStepCd;
    private String procStCd;
    private String fileCd;
    private String fileNm;
    private String fileStepCd;
    private String drawMngNo;
    private String fileProcCd;
    private String mrNo;

    private int delYn;

    private String mrStepCdNm;
    private String fileCdNm;
    private String fileCnt;
    private String fileStepNm;

    private String pRtnCode;
    private String pRtnMsg;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;

    private List<CompChrgrChgHistVO> compChrgr;
    private List<MrAtchFile> mrAtchFiles;
    
    //(시작 ) 자료등록 단계에 PSM 자료 등록 여부 체크 로직 추가에 따라 적용 16.05.20 HSW
    private List<MrRvRstVO> rvRsts;

    public List<MrRvRstVO> getRvRsts() {
        return rvRsts;
    }

    public void setRvRsts(List<MrRvRstVO> rvRsts) {
        this.rvRsts = rvRsts;
    }
    // (끝)
    
    public int getMrAtchFileNo() {
        return mrAtchFileNo;
    }
    public void setMrAtchFileNo(int mrAtchFileNo) {
        this.mrAtchFileNo = mrAtchFileNo;
    }
    public int getMrReqProcStepDetNo() {
        return mrReqProcStepDetNo;
    }
    public void setMrReqProcStepDetNo(int mrReqProcStepDetNo) {
        this.mrReqProcStepDetNo = mrReqProcStepDetNo;
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
    public String getFileCd() {
        return fileCd;
    }
    public void setFileCd(String fileCd) {
        this.fileCd = fileCd;
    }
    public String getFileNm() {
        return fileNm;
    }
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }
    public String getFileStepCd() {
        return fileStepCd;
    }
    public void setFileStepCd(String fileStepCd) {
        this.fileStepCd = fileStepCd;
    }
    public String getFileStepNm() {
        return fileStepNm;
    }
    public void setFileStepNm(String fileStepNm) {
        this.fileStepNm = fileStepNm;
    }
    public String getDrawMngNo() {
        return drawMngNo;
    }
    public void setDrawMngNo(String drawMngNo) {
        this.drawMngNo = drawMngNo;
    }
    public String getFileProcCd() {
        return fileProcCd;
    }
    public void setFileProcCd(String fileProcCd) {
        this.fileProcCd = fileProcCd;
    }
    public String getMrNo() {
        return mrNo;
    }
    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }
    public int getDelYn() {
        return delYn;
    }
    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }
    public String getMrStepCdNm() {
        return mrStepCdNm;
    }
    public void setMrStepCdNm(String mrStepCdNm) {
        this.mrStepCdNm = mrStepCdNm;
    }
    public String getFileCdNm() {
        return fileCdNm;
    }
    public void setFileCdNm(String fileCdNm) {
        this.fileCdNm = fileCdNm;
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
    public List<CompChrgrChgHistVO> getCompChrgr() {
        return compChrgr;
    }
    public void setCompChrgr(List<CompChrgrChgHistVO> compChrgr) {
        this.compChrgr = compChrgr;
    }
    public List<MrAtchFile> getMrAtchFiles() {
        return mrAtchFiles;
    }
    public void setMrAtchFiles(List<MrAtchFile> mrAtchFiles) {
        this.mrAtchFiles = mrAtchFiles;
    }
    public String getFileCnt() {
        return fileCnt;
    }
    public void setFileCnt(String fileCnt) {
        this.fileCnt = fileCnt;
    }
    public String getpRtnCode() {
        return pRtnCode;
    }
    public void setpRtnCode(String pRtnCode) {
        this.pRtnCode = pRtnCode;
    }
    public String getpRtnMsg() {
        return pRtnMsg;
    }
    public void setpRtnMsg(String pRtnMsg) {
        this.pRtnMsg = pRtnMsg;
    }


}
