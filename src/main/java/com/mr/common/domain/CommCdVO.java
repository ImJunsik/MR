package com.mr.common.domain;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.base.domain.Domain;

/**
 * 공통코드 도메인
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
public class CommCdVO extends Domain {
    private String mrCommGrpCd;
    private String mrCommGrpNm;
    private String mrCommCd;
    private String mrCommNm;
    private String parentCd;
    private String priority;
    private String sapCd;
    private String sapNm;
    private String div1;
    private String div2;
    private String div3;
    private String div4;
    private String div5;
    private int delYn;
    List<CommCdVO> codes;

    public String getMrCommGrpCd() {
        return mrCommGrpCd;
    }

    public void setMrCommGrpCd(String mrCommGrpCd) {
        this.mrCommGrpCd = mrCommGrpCd;
    }

    public String getMrCommGrpNm() {
        return mrCommGrpNm;
    }

    public void setMrCommGrpNm(String mrCommGrpNm) {
        this.mrCommGrpNm = mrCommGrpNm;
    }

    public String getMrCommCd() {
        return mrCommCd;
    }

    public void setMrCommCd(String mrCommCd) {
        this.mrCommCd = mrCommCd;
    }

    public String getMrCommNm() {
        return mrCommNm;
    }

    public void setMrCommNm(String mrCommNm) {
        this.mrCommNm = mrCommNm;
    }

    public String getParentCd() {
        return parentCd;
    }

    public void setParentCd(String parentCd) {
        this.parentCd = parentCd;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSapCd() {
        return sapCd;
    }

    public void setSapCd(String sapCd) {
        this.sapCd = sapCd;
    }

    public String getSapNm() {
        return sapNm;
    }

    public void setSapNm(String sapNm) {
        this.sapNm = sapNm;
    }

    public String getDiv1() {
        return div1;
    }

    public void setDiv1(String div1) {
        this.div1 = div1;
    }

    public String getDiv2() {
        return div2;
    }

    public void setDiv2(String div2) {
        this.div2 = div2;
    }

    public String getDiv3() {
        return div3;
    }

    public void setDiv3(String div3) {
        this.div3 = div3;
    }

    public String getDiv4() {
        return div4;
    }

    public void setDiv4(String div4) {
        this.div4 = div4;
    }

    public String getDiv5() {
        return div5;
    }

    public void setDiv5(String div5) {
        this.div5 = div5;
    }

    public int getDelYn() {
        return delYn;
    }

    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }

    public List<CommCdVO> getCodes() {
        return codes;
    }

    public void setCodes(List<CommCdVO> codes) {
        this.codes = codes;
    }

}
