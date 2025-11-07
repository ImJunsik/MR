package com.mr.mrrq.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.base.domain.Domain;
/**
 * 설비관련 도메인
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
public class MrReqEquipVO extends Domain {
    private int mrReqEquipNo;
    private int mrReqNo;
    private String mrProcNo;
    private String mrEquipCd;
    private String mrEquipName;

    private int delYn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fstRgstDt;
    private String fstRgstr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastChgDt;
    private String lastChgr;

    public int getMrReqEquipNo() {
        return mrReqEquipNo;
    }

    public void setMrReqEquipNo(int mrReqEquipNo) {
        this.mrReqEquipNo = mrReqEquipNo;
    }

    public int getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(int mrReqNo) {
        this.mrReqNo = mrReqNo;
    }

    public String getMrProcNo() {
        return mrProcNo;
    }

    public void setMrProcNo(String mrProcNo) {
        this.mrProcNo = mrProcNo;
    }

    public String getMrEquipCd() {
        return mrEquipCd;
    }

    public void setMrEquipCd(String mrEquipCd) {
        this.mrEquipCd = mrEquipCd;
    }

    public String getMrEquipName() {
        return mrEquipName;
    }

    public void setMrEquipName(String mrEquipName) {
        this.mrEquipName = mrEquipName;
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

}
