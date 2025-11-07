package com.common.file.domain;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import com.base.domain.Domain;

/**
 * 첨부파일 정보를 담고있는 도메인 클래스.
 *
 * @see com.examples.domain.Employee#getAttachFiles()
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class MrAtchFile extends Domain {
    public static final Format CONVERSION = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);

    private int mrAtchFileNo;
    private int mrReqProcStepDetNo;
    private int mrReqNo;
    private String fileCd;
    private String fileNm;
    private String drawMngNo;
    private String fileProcCd;

    private String mrStepCd;
    private String chrgrClCd;
    private String fileStepCd;
    private long fileSize;
    private String fileCdNm;
    private String fileStepNm;

    private boolean deleted;
    private boolean inserted;
    private MultipartFile multipartFile;

    private int delYn;
    private Date fstRgstDt;
    private String fstRgstr;
    private Date lastChgDt;
    private String lastChgr;


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

    public static Format getConversion() {
        return CONVERSION;
    }

    public String getMrStepCd() {
        return mrStepCd;
    }

    public void setMrStepCd(String mrStepCd) {
        this.mrStepCd = mrStepCd;
    }

    public String getChrgrClCd() {
        return chrgrClCd;
    }

    public void setChrgrClCd(String chrgrClCd) {
        this.chrgrClCd = chrgrClCd;
    }

    public String getFileStepCd() {
        return fileStepCd;
    }

    public void setFileStepCd(String fileStepCd) {
        this.fileStepCd = fileStepCd;
    }

    /**
     * @return 첨부파일 크기
     */
    public long getFileSize() {
        return isMultipartFileNotEmpty() ? getMultipartFile().getSize() : fileSize;
    }

    /**
     * @param size 첨부파일 크기
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileCdNm() {
        return fileCdNm;
    }

    public void setFileCdNm(String fileCdNm) {
        this.fileCdNm = fileCdNm;
    }

    public String getFileStepNm() {
        return fileStepNm;
    }

    public void setFileStepNm(String fileStepNm) {
        this.fileStepNm = fileStepNm;
    }

    /**
     * @return 첨부파일이 삭제되었는지 여부
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted 첨부파일이 삭제되었는지 여부
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return 첨부파일이 추가되었는지 여부
     */
    public boolean isInserted() {
        return inserted;
    }

    /**
     * @return inserted 첨부파일이 추가되었는지 여부
     */
    public void setInserted(boolean inserted) {
        this.inserted = inserted;
    }


    /**
     * @return 웹으로부터 전송된 첨부파일
     */
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    /**
     * @param multipartFile 웹으로부터 전송된 첨부파일
     */
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 있는지의 여부
     */
    public boolean isMultipartFileNotEmpty() {
        return multipartFile != null && !multipartFile.isEmpty();
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 없는지의 여부
     */
    public boolean isMultipartFileEmpty() {
        return multipartFile == null || multipartFile.isEmpty();
    }

    /**
     * @return 웹으로부터 전송된 첨부파일 있는지 여부
     */
    public boolean isNew() {
        return isMultipartFileNotEmpty() ? true : false;
    }

}