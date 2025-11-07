package com.mr.ivst.domain;

import com.base.domain.Domain;

/**
 * 투자지출 품의서 발송 도메인
 *
 * @author 조상욱
 * @version 1.0
 *
 *          <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.08.07 조상욱 최초 작성
 * </pre>
 */

public class IvstCostItemSendVO extends Domain {

    private int mrInvstCostNo;
    private String mrReqNo;
    private String invstYear;
    private String costItemCd;
    private String costItemCtt;
    private double costTot;
    private double cost01;
    private double cost02;
    private double cost03;
    private double cost04;
    private double cost05;
    private double cost06;
    private double cost07;
    private double cost08;
    private double cost09;
    private double cost10;
    private double cost11;
    private double cost12;

    private String mngAccount;


    public int getMrInvstCostNo() {
        return mrInvstCostNo;
    }

    public void setMrInvstCostNo(int mrInvstCostNo) {
        this.mrInvstCostNo = mrInvstCostNo;
    }

    public String getMrReqNo() {
        return mrReqNo;
    }

    public void setMrReqNo(String mrReqNo) {
        this.mrReqNo = mrReqNo;
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

    public String getCostItemCtt() {
        return costItemCtt;
    }

    public void setCostItemCtt(String costItemCtt) {
        this.costItemCtt = costItemCtt;
    }

    public double getCostTot() {
        return costTot;
    }

    public void setCostTot(double costTot) {
        this.costTot = costTot;
    }

    public double getCost01() {
        return cost01;
    }

    public void setCost01(double cost01) {
        this.cost01 = cost01;
    }

    public double getCost02() {
        return cost02;
    }

    public void setCost02(double cost02) {
        this.cost02 = cost02;
    }

    public double getCost03() {
        return cost03;
    }

    public void setCost03(double cost03) {
        this.cost03 = cost03;
    }

    public double getCost04() {
        return cost04;
    }

    public void setCost04(double cost04) {
        this.cost04 = cost04;
    }

    public double getCost05() {
        return cost05;
    }

    public void setCost05(double cost05) {
        this.cost05 = cost05;
    }

    public double getCost06() {
        return cost06;
    }

    public void setCost06(double cost06) {
        this.cost06 = cost06;
    }

    public double getCost07() {
        return cost07;
    }

    public void setCost07(double cost07) {
        this.cost07 = cost07;
    }

    public double getCost08() {
        return cost08;
    }

    public void setCost08(double cost08) {
        this.cost08 = cost08;
    }

    public double getCost09() {
        return cost09;
    }

    public void setCost09(double cost09) {
        this.cost09 = cost09;
    }

    public double getCost10() {
        return cost10;
    }

    public void setCost10(double cost10) {
        this.cost10 = cost10;
    }

    public double getCost11() {
        return cost11;
    }

    public void setCost11(double cost11) {
        this.cost11 = cost11;
    }

    public double getCost12() {
        return cost12;
    }

    public void setCost12(double cost12) {
        this.cost12 = cost12;
    }

    public String getMngAccount() {
        return mngAccount;
    }

    public void setMngAccount(String mngAccount) {
        this.mngAccount = mngAccount;
    }


}
