package com.mr.common.domain;

import java.util.List;

/**
 * 플랜트, 공정, 설비용 도메인
 *
 * @author IT1212003
 *
 */
public class UnitVo {
    private String mrProcNo;
    private String mrProcName;
    private String mrEquipCd;
    private String mrEquipName;
    private String plantNo;
    // 작성화면에서 넘어 오는 파라미터때문에 추가
    // 셋팅만 하고 가져올때는 plantNo 사용
    private String plant;
    private String plantName;
    private List<UnitVo> procs;
    private List<UnitVo> equips;

    public String getMrProcNo() {
        return mrProcNo;
    }

    public void setMrProcNo(String mrProcNo) {
        this.mrProcNo = mrProcNo;
    }

    public String getMrProcName() {
        return mrProcName;
    }

    public void setMrProcName(String mrProcName) {
        this.mrProcName = mrProcName;
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

    public void setPlant(String plant) {
        this.plant = plant;
        this.plantNo = plant;
    }

    public String getPlantNo() {
        return plantNo;
    }

    public void setPlantNo(String plantNo) {
        this.plantNo = plantNo;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public List<UnitVo> getProcs() {
        return procs;
    }

    public void setProcs(List<UnitVo> procs) {
        this.procs = procs;
    }

    public List<UnitVo> getEquips() {
        return equips;
    }

    public void setEquips(List<UnitVo> equips) {
        this.equips = equips;
    }

}
