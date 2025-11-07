<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">MR정보</h4>
</div>

<!-- button -->
<div class="button_top">
    <input id="search" type="image" src="images/btn_inquiry.png">
<!--     <input id="save" type="image" src="images/btn_save.png"> -->
</div>

<div id="content_wrap">
<form id="searchForm" method="post">
    <table class="table_row" border="1" cellspacing="0" >
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="35%">
        <col width="15%">
        <col width="35%">
        </colgroup>
        <tbody>
        <tr>
        <td colspan="4" class="topbg"></td>
        </tr>
        <tr>
            <th scope="row">MR번호</th>
            <td>
                <div class="form_group">
                <input type="text" name="mrNo" class="i_input"/>
                </div>
            </td>
            <th scope="row">사업명</th>
            <td>
                <div class="form_group">
                <input type="text" name="projectTitle" class="i_input_full">
                <input type="hidden" name="page" class="page">
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    </form>

    <!-- space non 10 -->
    <hr class="divider_none_10" />

    <div class="list_300 single searchList_div">
        <table class="table_col searchList" border="1" cellspacing="0">
            <tr>
                <th scope='col' style='display:none'>MR Req No.</th>
                <th scope='col' style='display:none'>상태 코드</th>
                <th scope='col'>MR No.</th>
                <th scope='col'>사업명</th>
                <th scope='col'>요청자</th>
                <th scope='col'>요청팀</th>
                <th scope='col'>기술검토팀</th>
                <th scope='col'>Job Eng.</th>
                <th scope='col'>수행팀</th>
                <th scope='col'>Project Eng.</th>
                <th scope='col'>진행상태</th>
                <th scope='col'>Capex No</th>
            </tr>
        </table>
    </div>

    <div class="button_middle">
        <!-- 저장 --><input id="save" type="image" style="vertical-align:bottom" src="images/btn_save.png">
    </div>
</div>


<div id="content_wrap">
    <form id="selectForm" method="post">
    <input type="hidden" class="mrReqNo" name="mrReqNo" value="0"  />
    <table class="table_row" border="1" cellspacing="0" >
        <colgroup>
        <col width="15%">
        <col width="15%">
        <col width="35%">
        <col width="35%">
        </colgroup>
        <tr>
            <th scope='col' style='display:none'></th>
            <th scope='col' colspan="2">MR No.</th>
            <td class="cursor goMr" colspan="2"></td>
        </tr>
        <tr>
            <th scope='col' style='display:none'></th>
            <th scope='col' colspan="2">사업명</th>
            <td class="cursor goMr" colspan="2"></td>
        </tr>
        <tr>
            <th scope="col">단계</th>
            <th scope="col">구분</th>
            <th scope="col">담당자</th>
            <th scope="col">결재일</th>
        </tr>
        <tr>
            <th scope="row" rowspan="2">요청서</th>
            <th scope="row" class="dep2">작성자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input reqAText" readOnly style="display:none"/>
                <input type="hidden" class="reqAValue value" name="" />
                <input type="hidden" class="reqADetNo detNo" name=""/>
                <input type="hidden" class="code" value="Z02A" name=""/>
                <input type="hidden" class="reqADutyText dutyText" name="" />
                <input type="hidden" class="reqATeamText teamText" name="" />
                <input type="hidden" class="reqATeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch reqA" data-role="reqA" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="reqADate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
<!--         <tr>
            <th scope="row" class="dep2">기술검토자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input reqDText" readOnly style="display:none"/>
                <input type="hidden" class="reqDValue value" name="" />
                <input type="hidden" class="reqDDetNo detNo" name=""/>
                <input type="hidden" class="code" value="Z02D" name=""/>
                <input type="hidden" class="reqDDutyText dutyText" name="" />
                <input type="hidden" class="reqDTeamText teamText" name="" />
                <input type="hidden" class="reqDTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch reqD" data-role="reqD" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="reqDDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr> -->
        <tr>
            <th scope="row" class="dep2">승인자</th>
            <td colspan="2">
          <table id="appLine" class="table_row" border="1" cellspacing="0" >
              <colgroup>
              <col width="50%">
              <col width="50%">
              </colgroup>
             <tbody>
             <tr>
             </tr>
             </tbody>
          </table>
            </td>
        </tr>
        <tr>
            <th scope="row" rowspan="4">기술 및 타당성 검토</th>
            <th scope="row" class="dep2">Job Engineer</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input techDText" readOnly style="display:none"/>
                <input type="hidden" class="techDValue value" name="" />
                <input type="hidden" class="techDDetNo detNo" name=""/>
                <input type="hidden" class="code" value="Z02D" name=""/>
                <input type="hidden" class="techDDutyText dutyText" name="" />
                <input type="hidden" class="techDTeamText teamText" name="" />
                <input type="hidden" class="techDTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch techD" data-role="techD" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="techDDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">승인자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input techFText" readOnly style="display:none"/>
                <input type="hidden" class="techFValue value" name="" />
                <input type="hidden" class="techFDetNo detNo" name=""/>
                <input type="hidden" class="code" value="Z02F" name=""/>
                <input type="hidden" class="techFDutyText dutyText" name="" />
                <input type="hidden" class="techFTeamText teamText" name="" />
                <input type="hidden" class="techFTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch techF" data-role="techF" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="techFDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">수행팀 팀장</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input techGText" readOnly style="display:none"/>
                <input type="hidden" class="techGValue value" name="" />
                <input type="hidden" class="code" name="appLine[1].chrgrClCd" value="Z02G"/>
                <input type="hidden" class="techGDetNo detNo" name=""/>
                <input type="hidden" class="techGDutyText dutyText" name="" />
                <input type="hidden" class="techGTeamText teamText" name="" />
                <input type="hidden" class="techGTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch techG" data-role="techG" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="techGDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">직무과장</th>
            <td colspan="2">
                <table id="techManager" class="table_row" border="1" cellspacing="0" >
                    <colgroup>
                    <col width="50%">
                    <col width="50%">
                    </colgroup>
                   <tbody>
                   <tr>
                   </tr>
                   </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">초기 투자비 산정</th>
            <th scope="row" class="dep2">Project Engineer</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input costIText" readOnly style="display:none"/>
                <input type="hidden" class="costIValue value" name="" />
                <input type="hidden" class="costIDetNo detNo" name=""/>
                <input type="hidden" class="code" value="Z02I" name=""/>
                <input type="hidden" class="costIDutyText dutyText" name="" />
                <input type="hidden" class="costITeamText teamText" name="" />
                <input type="hidden" class="costITeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch costI" data-role="costI" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="costIDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
    <!--     <tr>
            <td>수행팀 팀장</td>
            <td>
                  <div class="form_group">
                  <input type="text" class="i_input cost2Text" readOnly style="display:none"/>
                  <input type="hidden" class="cost2Value value" name="" />
                  <input type="hidden" class="cost2DetNo detNo" name="appLine[0].mrReqProcStepDetNo"/>
                  <input type="hidden" class="cost2DutyText dutyText" name="appLine[0].thdayPos" />
                  <input type="hidden" class="cost2TeamText teamText" name="appLine[0].thdayTeam" />
                  <input type="hidden" class="cost2TeamValue teamValue" name="appLine[0].chrgTeamNo" />
                  <img src="images/icon_search.png" class="cursor empSearch" data-role="cost2" style="display:none"/>
                  </div>
            </td>
            <td class="Date"></td>
        </tr> -->
        <tr>
            <th scope="row" rowspan="2">타당성검토 확인</th>
            <th scope="row" class="dep2">1차 승인자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input costFText" readOnly style="display:none"/>
                <input type="hidden" class="costFValue value" name="" />
                <input type="hidden" class="code" value="Z02I" name=""/>
                <input type="hidden" class="costFDetNo detNo" name=""/>
                <input type="hidden" class="costFDutyText dutyText" name="" />
                <input type="hidden" class="costFTeamText teamText" name="" />
                <input type="hidden" class="costFTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch costF" data-role="costF" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="costFDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">2차 승인자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input costCText" readOnly style="display:none"/>
                <input type="hidden" class="costCValue value" name="" />
                <input type="hidden" class="costCDetNo detNo" name=""/>
                <input type="hidden" class="costCDutyText dutyText" name="" />
                <input type="hidden" class="costCTeamText teamText" name="" />
                <input type="hidden" class="costCTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch costC" data-role="costC" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="costCDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2" >위험성검토</th>
            <th scope="row" class="dep2">승인자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input riskCText" readOnly style="display:none"/>
                <input type="hidden" class="riskCValue value" name="" />
                <input type="hidden" class="riskCStepCd stepCd" value="Z00R0"/>
                <input type="hidden" class="riskCCode code" name=""/>
                <input type="hidden" class="riskCDetNo detNo" name=""/>
                <input type="hidden" class="riskCDutyText dutyText" name="" />
                <input type="hidden" class="riskCTeamText teamText" name="" />
                <input type="hidden" class="riskCTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch riskC" data-role="riskC" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="riskCDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" rowspan="6">직무검토</th>
            <th scope="row" class="dep2">기술</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobTechText" readOnly style="display:none"/>
                <input type="hidden" class="jobTechValue value" name="" />
                <input type="hidden" class="jobTechDetNo detNo" name=""/>
                <input type="hidden" class="jobTechDutyText dutyText" name="" />
                <input type="hidden" class="jobTechTeamText teamText" name="" />
                <input type="hidden" class="jobTechTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobTech" data-role="jobTech" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobTechDate"></td>
        </tr>
        <tr>
            <th scope="row" class="dep2">검사</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobCheckText" readOnly style="display:none"/>
                <input type="hidden" class="jobCheckValue value" name="" />
                <input type="hidden" class="jobCheckDetNo detNo" name=""/>
                <input type="hidden" class="jobCheckDutyText dutyText" name="" />
                <input type="hidden" class="jobCheckTeamText teamText" name="" />
                <input type="hidden" class="jobCheckTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobCheck" data-role="jobCheck" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobCheckDate"></td>
        </tr>
        <tr>
            <th scope="row" class="dep2">운전</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobDriveText" readOnly style="display:none"/>
                <input type="hidden" class="jobDriveValue value" name="" />
                <input type="hidden" class="jobDriveDetNo detNo" name=""/>
                <input type="hidden" class="jobDriveDutyText dutyText" name="" />
                <input type="hidden" class="jobDriveTeamText teamText" name="" />
                <input type="hidden" class="jobDriveTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobDrive" data-role="jobDrive" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobDriveDate"></td>
        </tr>
        <tr>
            <th scope="row" class="dep2">환경</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobEquipText" readOnly style="display:none"/>
                <input type="hidden" class="jobEquipValue value" name="" />
                <input type="hidden" class="jobEquipDetNo detNo" name=""/>
                <input type="hidden" class="jobEquipDutyText dutyText" name="" />
                <input type="hidden" class="jobEquipTeamText teamText" name="" />
                <input type="hidden" class="jobEquipTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobEquip" data-role="jobEquip" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobEquipDate"></td>
        </tr>
        <tr>
            <th scope="row" class="dep2">회계</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobCostText" readOnly style="display:none"/>
                <input type="hidden" class="jobCostValue value" name="" />
                <input type="hidden" class="jobCostDetNo detNo" name=""/>
                <input type="hidden" class="jobCostDutyText dutyText" name="" />
                <input type="hidden" class="jobCostTeamText teamText" name="" />
                <input type="hidden" class="jobCostTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobCost" data-role="jobCost" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobCostDate"></td>
        </tr>
        <tr>
            <th scope="row" class="dep2">에너지</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input jobEnergyText" readOnly style="display:none"/>
                <input type="hidden" class="jobEnergyValue value" name="" />
                <input type="hidden" class="jobEnergyDetNo detNo" name=""/>
                <input type="hidden" class="jobEnergyDutyText dutyText" name="" />
                <input type="hidden" class="jobEnergyTeamText teamText" name="" />
                <input type="hidden" class="jobEnergyTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch jobEnergy" data-role="jobEnergy" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="jobEnergyDate"></td>
        </tr>
        <tr>
            <th scope="row" rowspan="6">PORC</th>
            <th scope="row" class="dep2">운전/생산</th>
            <td colspan="2">
                <table class="table_pop_default porcDrive"></table>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기술</th>
            <td colspan="2">
                <table class="table_pop_default porcTech"></table>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">공무</th>
            <td colspan="2">
                <table class="table_pop_default porcBuild"></table>
            </td>
        </tr>

        <tr>
            <th scope="row" class="dep2">설계/검사</th>
            <td colspan="2">
                <table class="table_pop_default porcCheck"></table>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">안전/환경</th>
            <td colspan="2">
                <table class="table_pop_default porcSafety"></table>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기타</th>
            <td colspan="2">
                <table class="table_pop_default porcEtc"></table>
            </td>
        </tr>


        <tr>
            <th scope="row" class="dep2" rowspan="10">MR수행</th>
            <th scope="row" class="dep2">점검팀 책임자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeOfficerText" readOnly style="display:none"/>
                <input type="hidden" class="safeOfficerValue value" name="" />
                <input type="hidden" class="safeOfficerDetNo detNo" name=""/>
                <input type="hidden" class="safeOfficerDutyText dutyText" name="" />
                <input type="hidden" class="safeOfficerTeamText teamText" name="" />
                <input type="hidden" class="safeOfficerTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeOfficer" data-role="safeOfficer" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeOfficerDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">공정설계 기술자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safePlanText" readOnly style="display:none"/>
                <input type="hidden" class="safePlanValue value" name="" />
                <input type="hidden" class="safePlanDetNo detNo" name=""/>
                <input type="hidden" class="safePlanDutyText dutyText" name="" />
                <input type="hidden" class="safePlanTeamText teamText" name="" />
                <input type="hidden" class="safePlanTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safePlan" data-role="safePlan" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safePlanDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">공정운전 기술자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeDriveText" readOnly style="display:none"/>
                <input type="hidden" class="safeDriveValue value" name="" />
                <input type="hidden" class="safeDriveDetNo detNo" name=""/>
                <input type="hidden" class="safeDriveDutyText dutyText" name="" />
                <input type="hidden" class="safeDriveTeamText teamText" name="" />
                <input type="hidden" class="safeDriveTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeDrive" data-role="safeDrive" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeDriveDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">설비설치 책임자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeEquipText" readOnly style="display:none"/>
                <input type="hidden" class="safeEquipValue value" name="" />
                <input type="hidden" class="safeEquipDetNo detNo" name=""/>
                <input type="hidden" class="safeEquipDutyText dutyText" name="" />
                <input type="hidden" class="safeEquipTeamText teamText" name="" />
                <input type="hidden" class="safeEquipTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeEquip" data-role="safeEquip" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeEquipDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">전기&계장 기술자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeElectronicText" readOnly style="display:none"/>
                <input type="hidden" class="safeElectronicValue value" name="" />
                <input type="hidden" class="safeElectronicDetNo detNo" name=""/>
                <input type="hidden" class="safeElectronicDutyText dutyText" name="" />
                <input type="hidden" class="safeElectronicTeamText teamText" name="" />
                <input type="hidden" class="safeElectronicTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeElectronic" data-role="safeElectronic" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeElectronicDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">검사&정비 기술자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeCheckText" readOnly style="display:none"/>
                <input type="hidden" class="safeCheckValue value" name="" />
                <input type="hidden" class="safeCheckDetNo detNo" name=""/>
                <input type="hidden" class="safeCheckDutyText dutyText" name="" />
                <input type="hidden" class="safeCheckTeamText teamText" name="" />
                <input type="hidden" class="safeCheckTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeCheck" data-role="safeCheck" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeCheckDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">안전기술자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeSafetyText" readOnly style="display:none"/>
                <input type="hidden" class="safeSafetyValue value" name="" />
                <input type="hidden" class="safeSafetyDetNo detNo" name=""/>
                <input type="hidden" class="safeSafetyDutyText dutyText" name="" />
                <input type="hidden" class="safeSafetyTeamText teamText" name="" />
                <input type="hidden" class="safeSafetyTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeSafety" data-role="safeSafety" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeSafetyDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기타 1</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeEtc1Text" readOnly style="display:none"/>
                <input type="hidden" class="safeEtc1Value value" name="" />
                <input type="hidden" class="safeEtc1DetNo detNo" name=""/>
                <input type="hidden" class="safeEtc1DutyText dutyText" name="" />
                <input type="hidden" class="safeEtc1TeamText teamText" name="" />
                <input type="hidden" class="safeEtc1TeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeEtc1" data-role="safeEtc1" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeEtc1Date">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기타 2</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeEtc2Text" readOnly style="display:none"/>
                <input type="hidden" class="safeEtc2Value value" name="" />
                <input type="hidden" class="safeEtc2DetNo detNo" name=""/>
                <input type="hidden" class="safeEtc2DutyText dutyText" name="" />
                <input type="hidden" class="safeEtc2TeamText teamText" name="" />
                <input type="hidden" class="safeEtc2TeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeEtc2" data-role="safeEtc2" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeEtc2Date">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기타 3</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input safeEtc3Text" readOnly style="display:none"/>
                <input type="hidden" class="safeEtc3Value value" name="" />
                <input type="hidden" class="safeEtc3DetNo detNo" name=""/>
                <input type="hidden" class="safeEtc3DutyText dutyText" name="" />
                <input type="hidden" class="safeEtc3TeamText teamText" name="" />
                <input type="hidden" class="safeEtc3TeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch safeEtc3" data-role="safeEtc3" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="safeEtc3Date">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        
        <!-- 2021.10.25 MR완료 추가  -->
        <tr>
            <th scope="row" class="dep2" rowspan="1">자료등록</th>
            <th scope="row" class="dep2">문서등록담당자</th>
            <td>
                <div class="form_group">
                <input type="text" class="i_input docQText" readOnly style="display:none"/>
                <input type="hidden" class="docQValue value" name="" />
                <input type="hidden" class="docQDetNo detNo" name=""/>
                <input type="hidden" class="docQDutyText dutyText" name="" />
                <input type="hidden" class="docQTeamText teamText" name="" />
                <input type="hidden" class="docQTeamValue teamValue" name="" />
                <img src="images/icon_search.png" class="cursor empSearch docQ" data-role="docQ" style="display:none"/>
                <input type="checkbox" class="check" value="true" style="display:none">
                </div>
            </td>
            <td class="docQDate">
                <span></span>
                <div class="form_group" style="display:none">
                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
                </div>
            </td>
        </tr>
        		<tr>
        		<th scope="row" class="dep2" rowspan="2">완료</th>
					<th scope="row" class="dep2">담당자</th>
					<td>
						<div class="form_group">
			              <input type="text" class="i_input compDText" readOnly style="display:none"/>
			              <input type="hidden" class="compDValue value" name="" />
			              <input type="hidden" class="compDDetNo detNo"  name=""/>
			              <input type="hidden" class="compDDutyText dutyText" name="" />
			              <input type="hidden" class="compDTeamText teamText" name="" />
			              <input type="hidden" class="compDTeamValue teamValue" name="" />
			              <img src="images/icon_search.png" class="cursor empSearch compD" data-role="compD" style="display:none"/>
			             <input type="checkbox" class="check" value="true" style="display:none">
			            </div>
					</td>
					<td class="compDDate">
		                <span></span>
		                <div class="form_group" style="display:none">
		                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
		                </div>
		            </td>
				</tr>
				
				<tr>
 
					<th scope="row" class="dep2">승인자</th>
					<td>
						<div class="form_group">
			              <input type="text" class="i_input compFText" readOnly style="display:none"/>
			              <input type="hidden" class="compFValue value" name="" />
			              <input type="hidden" class="compFDetNo detNo"  name=""/>
			              <input type="hidden" class="compFDutyText dutyText" name="" />
			              <input type="hidden" class="compFTeamText teamText" name="" />
			              <input type="hidden" class="compFTeamValue teamValue" name="" />
			              <img src="images/icon_search.png" class="cursor empSearch compF" data-role="compF" style="display:none"/>
			             <input type="checkbox" class="check" id="compF" value="true" style="display:none">
			            </div>
					</td>
					<td class="compFDate">
		                <span></span>
		                <div class="form_group" style="display:none">
		                    <input type='text' name='' class="i_input inputDate limitDate" value="" readOnly/>
		                </div>
		            </td>
		            
				</tr>
				
    </table>

</div>

</form>

<table id="appLineTemp" style="display:none">
   <tr>
    <td>
        <div class="form_group">
            <input type="text" class="i_input tempText" readOnly/>
            <input type="hidden" class="tempValue value" name="" />
            <input type="hidden" class="tempCode code" name="" value="Z02C"/>
            <input type="hidden" class="tempDetNo detNo" name=""/>
            <input type="hidden" class="tempDutyText dutyText" name="" />
            <input type="hidden" class="tempTeamText teamText" name="" />
            <input type="hidden" class="tempTeamValue teamValue" name="" />
            <img src="images/icon_search.png" class="cursor empSearch" data-role="safeEtc3" />
            <input type="checkbox" class="check" value="true">
        </div>
    </td>
    <td>
        <div class="form_group">
            <input type="text" class="i_input limitDate" value="" readOnly  name=""/>
        </div>
    </td>
  </tr>
  
 
		            
</table>

<script>
$(document).ready(function() {

	var page= 1;
	var totalCount = 0;
	var count = 0;
	$(".searchList_div").scroll(function() {
		  var elem = $(this);
	      var tr = "";
		  if ( elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight() && count < totalCount)
		    {
	          page++;
	          $(".page").val(page);
			  var formData = $("#searchForm").serialize();
	            $.ajax({
	                type:"POST",
	                url:"detailSearch.do",
	                data:formData,
	                dataType:"JSON",
	                success : function(data) {
	                    if(data.mrSearchList!=null) {
	                          $.each(data.mrSearchList, function() {
	                              count++;
	                              tr = "";
	                              tr += "<tr class='mrLink cursor'>";
	                              tr += "<td style='display:none'>"+this.mrReqNo+"</td>";
	                              tr += "<td style='display:none'>"+this.mrStepCd+"</td>";
	                              tr += "<td>"+this.mrNo+"</td>";
	                              tr += "<td>"+this.mrReqTitle+"</td>";
	                              tr += "<td>"+this.reqEmpName+"</td>";
	                              tr += "<td>"+this.reqTeamName+"</td>";
	                              tr += "<td>"+this.jobTeamName+"</td>";
	                              tr += "<td>"+this.jobEmpName+"</td>";
	                              tr += "<td>"+this.actTeamName+"</td>";
	                              tr += "<td>"+this.proEmpName+"</td>";
	                              tr += "<td>"+this.mrStepNm+"</td>";
	                              tr += "<td>"+this.capexNo+"</td>";
	                              tr += "</tr>";
	                              $(".searchList").append(tr);
	                           });
	                    }
	                 },
	                 error : function(request) {
	                     if(request.status==500) {
	                         history.go(0);
	                     }
	                 }
	             });
		    }
	});

	$("#save").click(function(){
		if($(".reqAText").val()=="") {
			alert("담당자를 변경하려고 하는 MR을 먼저 선택하세요.");
		}else if(confirm("변경하시겠습니까?")) {
			
			//선택한것만 변경하기
			
	    	$.sortMrInfo();
            $('#pageLoadingWrap').show();
            
         	 //$('input:checkbox[id="compC"]').attr("checked", $('input:checkbox[id="compF"]').val()); 
         	 
            $("#selectForm").attr("action", "mngrChangeSave.do").submit();
	    }
	});

    $("#search").click(function(){
        var tr ="";
        count = 0;
        page=1;
        var formData = $("#searchForm").serialize();
        $(".searchList").find("tr").remove().end();
        tr ="<tr>";
        tr += "<th scope='col' style='display:none'>MR Req No.</th>";
        tr += "<th scope='col' style='display:none'>상태 코드</th>";
        tr += "<th scope='col'>MR No.</th>";
        tr += "<th scope='col'>사업명</th>";
        tr += "<th scope='col'>요청자</th>";
        tr += "<th scope='col'>요청팀</th>";
        tr += "<th scope='col'>기술검토팀</th>";
        tr += "<th scope='col'>Job Eng.</th>";
        tr += "<th scope='col'>수행팀</th>";
        tr += "<th scope='col'>Project Eng.</th>";
        tr += "<th scope='col'>진행상태</th>";
        tr += "<th scope='col'>Capex No</th>";
        tr += "</tr>";

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"detailSearch.do",
               data:formData,
               dataType:"JSON",
               success : function(data) {
                   if(data.mrSearchList!=null) {
                         $.each(data.mrSearchList, function() {
                           if(totalCount==0){
                                 totalCount = this.totalCount;
                                 //wj alert('리스트:' + totalCount);
                           }
                           count++;
                             tr = "";
                             tr += "<tr class='mrLink cursor'>";
                             tr += "<td style='display:none'>"+this.mrReqNo+"</td>";
                             tr += "<td style='display:none'>"+this.mrStepCd+"</td>";
                             tr += "<td>"+this.mrNo+"</td>";
                             tr += "<td>"+this.mrReqTitle+"</td>";
                             tr += "<td>"+this.reqEmpName+"</td>";
                             tr += "<td>"+this.reqTeamName+"</td>";
                             tr += "<td>"+this.jobTeamName+"</td>";
                             tr += "<td>"+this.jobEmpName+"</td>";
                             tr += "<td>"+this.proTeamName+"</td>";
                             tr += "<td>"+this.proEmpName+"</td>";
                             tr += "<td>"+this.mrStepNm+"</td>";
                             tr += "<td>"+this.capexNo+"</td>";
                             tr += "<tr>";
                             $(".searchList").append(tr);
                          });
                   }
               }
        });

    });

});


$(document).on("mouseover",".mrLink",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".mrLink",function() {
   $(this).css("background","#ffffff");
}).on("click",".mrLink",function() {
	var infoTr = $("#selectForm").find("table").children("tbody");


	infoTr.children("tr:eq(0)").children("th:eq(0)").html($(this).children("td:eq(1)").html());
    infoTr.children("tr:eq(1)").children("th:eq(0)").html($(this).children("td:eq(1)").html());

	infoTr.children("tr:eq(0)").children("td:last").html($(this).children("td:eq(2)").html());
    infoTr.children("tr:eq(1)").children("td:last").html($(this).children("td:eq(3)").html());

	var target="";
	var tr = "";
	var porcCount = 14;
	var reqCount = 1;
	var procRow = 0;
	$("#selectForm").find("input").each(function () {
        $(this).hide();
		if($(this).attr("type")=="text") {
			$(this).val("");
		}
	});

    $("#appLine").children("tbody").children("tr:gt(0)").each(function () {
        $(this).remove();
    });
    
    $("#techManager").children("tbody").children("tr:gt(0)").each(function () {
        $(this).remove();
    });

    $("#selectForm").find("img").each(function () {
        $(this).hide();
    });
    
    $("#selectForm").find("span").each(function () {
        $(this).html(" ");
    });

    $(".porcDrive").find("tr").remove().end();
    $(".porcTech").find("tr").remove().end();
    $(".porcBuild").find("tr").remove().end();
    $(".porcCheck").find("tr").remove().end();
    $(".porcSafety").find("tr").remove().end();
    $(".porcEtc").find("tr").remove().end();

    $(".mrReqNo").val($(this).children("td:eq(0)").html());
    $.ajax({
        type:"GET",
        url:"mngrChangeAjax.do?mrReqNo="+$(this).children("td:eq(0)").html(),
        dataType:"JSON",
        success : function(data) {
            if(data.list!=null) {
                  $.each(data.list, function() 
                  {
                	  console.log(data.list[procRow]);
                	  target="";
                	  tr = "";
                      switch (this.mrStepCd) {
    					case "Z0010":
    						target = "req";
    						break;
    					case "Z0020":
    						target = "tech";
                            break;
                        case "Z0030":
                        	target = "cost";
                            break;
                        case "Z0040":
                        	target = "job";
                            break;
                        case "Z00R0":
                            target = "risk";
                            break;
                        case "Z0080":
                            target = "doc";
                            break;
                        case "Z0090": case "Z00Z0":
                            target = "comp";
                            break;
  					  }

                      if(target.indexOf("job")<0) {
                    	  target += this.chrgrClCd.substr(3, 1);
                      } else if (this.chrgrClCd.indexOf("Z02H")>=0) {
                          switch (this.chrgrClCd) {
                          case "Z02H1":
                              target = "jobTech";
                              break;
                          case "Z02H2":
                              target = "jobCheck";
                              break;
                          case "Z02H3":
                              target = "jobDrive";
                              break;
                          case "Z02H4":
                              target = "jobEquip";
                              break;
                          case "Z02H5":
                              target = "jobSafety";
                              break;
                          case "Z02H6":
                              target = "jobCost";
                              break;
                          case "Z02H7":
                              target = "jobEnergy";
                              break;
                          }
                      }
                      
                      if (this.chrgrClCd.indexOf("Z02L")>=0) {
                          switch (this.chrgrClCd) {
                          case "Z02L1":
                              target = "porcDrive";
                              break;
                          case "Z02L2":
                              target = "porcTech";
                              break;
                          case "Z02L3":
                              target = "porcBuild";
                              break;
                          case "Z02L4":
                              target = "porcCheck";
                              break;
                          case "Z02L5":
                              target = "porcSafety";
                              break;
                          case "Z02L6":
                              target = "porcEtc";
                              break;
                          }
                      }

                      if (this.chrgrClCd.indexOf("Z02P")>=0) {
                          switch (this.chrgrClCd) {
                          case "Z02P":
                              target = "safeOfficer";
                              break;
                          case "Z02P1":
                              target = "safePlan";
                              break;
                          case "Z02P2":
                              target = "safeDrive";
                              break;
                          case "Z02P3":
                              target = "safeEquip";
                              break;
                          case "Z02P4":
                              target = "safeElectronic";
                              break;
                          case "Z02P5":
                              target = "safeCheck";
                              break;
                          case "Z02P6":
                              target = "safeSafety";
                              break;
                          case "Z02P7":
                              target = "safeEtc1";
                              break;
                          case "Z02P8":
                              target = "safeEtc2";
                              break;
                          case "Z02P9":
                              target = "safeEtc3";
                              break;
                          }
                      }

                      if(target.indexOf("risk")>=0 || (this.chrgrClCd.indexOf("Z02L")<0 && !(this.mrStepCd == "Z0010" && this.chrgrClCd.indexOf("Z02C")>=0) && this.chrgrClCd.indexOf("Z02G1")<0 && this.chrgrClCd.indexOf("Z02B")<0)) {
                          
                          $("."+target).show();
                          $("."+target).parent("div").children(".check").show();
                          $("."+target+"Text").show();
                          if($("."+target+"Code")!=null)
                          {
                        	  $("."+target+"Code").val(this.chrgrClCd);
                          }
                          $.appLineSet({className:target, dutyText:this.thdayPos,
                              text:this.chrgEmpName, value:this.chrgEmpNo,
                              teamText:this.thdayTeam , teamValue:this.chrgTeamNo,
                              DetNo:this.mrReqProcStepDetNo});
                          if(this.status=="ING" && this.chrgrClCd.indexOf("Z02H")<0) {
                        	  $("."+target+"Date").find("div").show();
                        	  $("."+target+"Date").find("input").show();
                        	  $("."+target+"Date").find("img").show();
                        	  $("."+target+"Date").find(".inputDate").val(this.fstProcTrmDt);
                        	  $("."+target+"Date").find(".inputDate").attr("name","appLine["+(parseInt($("."+target+"Date").parent("tr").index())-3)+"].fstProcTrmDt");
                          } else {
                        	  $("."+target+"Date").children("div").hide();
                        	  $("."+target+"Date").children("span").html(this.lastChgDt);
                          }

                      } else if(this.chrgrClCd.indexOf("Z02L")>=0) {
                          tr +="<tr><td>";
                          tr +="<div class='form_group'>";
                          tr +="<input type='text' class='i_input "+ target+porcCount +"Text'  value='"+this.chrgEmpName+"' readOnly/>";
                          tr +="<input type='hidden' class='"+target+porcCount+"DetNo detNo' name='appLine["+porcCount+"].mrReqProcStepDetNo' value='"+this.mrReqProcStepDetNo+"'/>";
                          tr +="<input type='hidden' class='code' name='appLine["+porcCount+"].chrgrClCd' value='"+this.chrgrClCd+"'/>";
                          tr +="<input type='hidden' class='"+target+porcCount+"Value value' name='appLine["+porcCount+"].chrgEmpNo' value='"+this.chrgEmpNo+"'/>";
                          tr +="<input type='hidden' class='"+target+porcCount+"DutyText dutyText' name='appLine["+porcCount+"].thdayPos' value='"+this.thdayPos+"'/>";
                          tr +="<input type='hidden' class='"+target+porcCount+"TeamText teamText' name='appLine["+porcCount+"].thdayTeam' value='"+this.thdayTeam+"'/>";
                          tr +="<input type='hidden' class='"+target+porcCount+"TeamValue teamValue' name='appLine["+porcCount+"].chrgTeamNo' value='"+this.chrgTeamNo+"'/>";
                          tr +="<img src='images/icon_search.png' class='cursor empSearch' data-role='"+target+porcCount+"'/>";
                          tr +="<input type='checkbox' class='check' value='true'>";
                          tr +="</div>";
                          tr +="</td></tr>";
                          $("."+target).append(tr);
                          porcCount++;
                      } else if(this.chrgrClCd.indexOf("Z02C")>=0 || this.chrgrClCd.indexOf("Z02B")>=0 || this.chrgrClCd.indexOf("Z02G1")>=0) {
                          if(this.mrStepCd == "Z0010" && this.chrgrClCd.indexOf("Z02G1")<0){
                              target = "appLine";
                          } else if (this.mrStepCd == "Z0020" && this.chrgrClCd.indexOf("Z02G1")>=0) {
                              target = "techManager";
                          }
                          var div = "";
                          var date = null;
                          var classDiv="";
                              div = $("#appLineTemp tbody").children("tr").children("td:eq(0)").children("div");
                              div.children(".tempText").val(this.chrgEmpName);
                              div.children(".tempDetNo").val(this.mrReqProcStepDetNo);
                              div.children(".tempCode").val(this.chrgrClCd);
                              div.children(".tempValue").val(this.chrgEmpNo);
                              div.children(".tempDutyText").val(this.thdayPos);
                              div.children(".tempTeamText").val(this.thdayTeam);
                              div.children(".tempTeamValue").val(this.chrgTeamNo);

                              $("#appLineTemp tr").clone().insertAfter($("#" + target + " tbody tr:last"));

                              div.children(".tempText").val("");
                              div.children(".tempCode").val("");
                              div.children(".tempDetNo").val("");
                              div.children(".tempValue").val("");
                              div.children(".tempDutyText").val("");
                              div.children(".tempTeamText").val("");
                              div.children(".tempTeamValue").val("");

                              classDiv = $("#" + target + " tbody tr:last").children("td:eq(0)").children("div");
                              classDiv.children(".tempText").attr("class","i_input req"+reqCount+"Text");
                              classDiv.children(".tempValue").attr("class","req"+reqCount+"Value value");
                              classDiv.children(".tempCode").attr("class","req"+reqCount+"Code code");
                              classDiv.children(".tempDutyText").attr("class","req"+reqCount+"DutyText dutyText");
                              classDiv.children(".tempTeamText").attr("class","req"+reqCount+"TeamText teamText");
                              classDiv.children(".tempTeamValue").attr("class","req"+reqCount+"TeamValue teamValue");
                              classDiv.children(".empSearch").attr("data-role","req"+reqCount);

                              date = $("#" + target + " tbody tr:last").children("td:eq(1)").children("div");

                              if(this.status=="ING") {
                                  date.children(".i_input").val(this.fstProcTrmDt);
                                  date.children(".i_input").datepicker({
                                        dateformat:"yyyy-mm-dd",
                                        showOn:"button",
                                        changeYear: true,
                                        changeMonth: true,
                                        buttonImage:"images/icon_calendar.png",
                                        buttonImageOnly:true,
                                        maxDate: "today"
                                   });
                              } else {
                            	  date.hide();
                            	  $("#" + target + " tbody tr:last").children("td:eq(1)").html(this.lastChgDt);
                              }

                          }
                      reqCount++;
                      procRow++;
                   });
            }
        }
    });

});

$(document).on("click",".empSearch",function() {
    $.popupPopup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
});

$(document).on("mouseover",".goMr",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".goMr",function() {
   $(this).css("background","#ffffff");
}).on("click",".goMr",function() {
    var url ="";


    switch ($(this).parent("tr").children("th:eq(0)").html()) {
    case "Z0010":
        url="mrRqRegister";
        break;
    case "Z0020":
        url="mrTech";
        break;
    case "Z0030":
        url="mrTechInvest";
        break;
    case "Z0040":
        url="mrJobsReview";
        break;
    case "Z0050":
        url="ivstCostRegister";
        break;
    default:
        url="mrRqRegister";
        break;
    }
    document.location.href = url+".do?mrReqNo="+$(".mrReqNo").val();
});

$.sortMrInfo = function () {
	var count = 0 ;
	var thisInput;
	
	//alert("카운터 시작");
	
	$(".value").each(function(){

		thisInput = $(this).parent("div");
		
		//담당자		
		//alert("$(this).val():"+$(this).val());

		if($(this).val()!=""){
			
    		thisInput.children(".value").attr("name", "appLine["+count+"].chrgEmpNo");
    		thisInput.children(".detNo").attr("name", "appLine["+count+"].mrReqProcStepDetNo");
    		thisInput.children(".dutyText").attr("name", "appLine["+count+"].thdayPos");
    		thisInput.children(".teamText").attr("name", "appLine["+count+"].thdayTeam");
    		thisInput.children(".teamValue").attr("name", "appLine["+count+"].chrgTeamNo");
    		thisInput.children(".check").attr("name", "appLine["+count+"].empChange");
    	    thisInput.children(".code").attr("name", "appLine["+count+"].chrgrClCd");
    	    if(thisInput.children(".stepCd")!=null) {
    	    	thisInput.children(".stepCd").attr("name", "appLine["+count+"].mrStepCd");
    	    }

    		if(thisInput.parent("td").parent("tr").children("td:last").children("div").children(".inputDate")!=null) {
    			thisInput.parent("td").parent("tr").children("td:last").children("div").children(".inputDate").attr("name", "appLine["+count+"].fstProcTrmDt");
    		}

    	    count++;
		}
	});



}
</script>