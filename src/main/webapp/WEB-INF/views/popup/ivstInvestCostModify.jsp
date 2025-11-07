<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
 
    <script>
//    alert("${modeClass}");
//    alert("${appLine.chrgrClCd}");
//    alert("${appLine.ifStatCd}");
//    alert("${appLine.chrgEmpNo}");
//    alert("${appLine.loginEmpNo}");
    </script>
 
<div class="pop_button_top">
    <input type="image" src="../images/btn_save.png" style="display:none" class="cursor save EAA"/>
</div>
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${techInvest.mrReqNo}"/>"/>
<input type="hidden" name="mrInvstCostNo" value="<c:out value="${techInvest.mrInvstCostNo}"/>"/>
<!-- space non 10 -->
<hr class="divider_none_10" />

    <input type="hidden" class="writerValue" name="appLine[0].chrgEmpNo" />
    <input type="hidden" name="appLine[0].chrgrClCd" value="Z02I"/>
    <input type="hidden" class="writerDutyText" name="appLine[0].thdayPos" />
    <input type="hidden" class="writerTeamText" name="appLine[0].thdayTeam" />
    <input type="hidden" class="writerTeamValue" name="appLine[0].chrgTeamNo" />
    <input type="hidden" name="appLine[0].procTrmDt" value="9999-12-31" readOnly/>
<!-- space non 10 -->
<hr class="divider_none_10" />

<c:if test="${techInvest.engYn=='Y'}">
<div style="width:100%;text-align:left;font-weight:bold;">
단위 : 천원
</div>
<!-- space non 10 -->
<hr class="divider_none_10" />
<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="20%">
    <col width="20%">
    <col width="10%">
    <col width="20%">
    <col width="10%">
    <col width="20%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row">총 투자비</th>
        <td colspan="5"><span class="costTotalSum"><fmt:formatNumber value="${techInvest.costTotalSum}" groupingUsed="true"/></span></td>
    </tr>
    <tr>
        <th scope="row">설계비</th>
        <td><span class="costSumC01"><fmt:formatNumber value="${techInvest.costSumC01}" groupingUsed="true"/></span></td>
        <th scope="row">자재비</th>
        <td><span class="costSumC02"><fmt:formatNumber value="${techInvest.costSumC02}" groupingUsed="true"/></span></td>
        <th scope="row">공사비</th>
        <td><span class="costSumC03"><fmt:formatNumber value="${techInvest.costSumC03}" groupingUsed="true"/></span></td>
    </tr>
    <tr>
        <th scope="row">매입임차비</th>
        <td><span class="costSumC04"><fmt:formatNumber value="${techInvest.costSumC04}" groupingUsed="true"/></span></td>
        <th scope="row">대여금</th>
        <td><span class="costSumC05"><fmt:formatNumber value="${techInvest.costSumC05}" groupingUsed="true"/></span></td>
        <th scope="row">기타</th>
        <td><span class="costSumC06"><fmt:formatNumber value="${techInvest.costSumC06}" groupingUsed="true"/></span></td>
    </tr>
    </tbody>
</table>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="yearCostArea">

    <c:forEach var="techInvest" items="${techInvest.techInvests}" varStatus="loop" >
    <table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="10%">
    <col width="10%">
    <col width="8%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    </colgroup>
    <tbody>
    <tr>
        <td rowspan="7" class="title">
          <c:if test="${loop.index==0 }">
              <c:if test="${modeClass=='EAA'}">
              <select class="startYear EAA" disabled="disabled">
                  <c:forEach var="year" begin="2005" end="${techInvest.currnetYear + 5}" >
                      <option value="${year}" <c:if test="${year==techInvest.invstYear}">selected</c:if> ><c:out value="${year}"/> </option>
                  </c:forEach>
              </select>
              <img src="../images/btn_add.gif" class="vam cursor addYear" /></c:if>
              <c:if test="${modeClass!='EAA'}">
                    <c:out value="${techInvest.invstYear}"/>
              </c:if>
          </c:if>
          <c:if test="${loop.index>0 }">
              <span class="invstYearText"><fmt:formatNumber value="${investCost.invstYear}"/></span>
              <c:if test="${modeClass=='EAA'}"><img src="../images/btn_del.gif" class="vam cursor delYear" /></c:if>
          </c:if>
        </td>
        <th scope="col">비용항목</th>
        <th scope="col">합계</th>
        <th scope="col">1월</th>
        <th scope="col">2월</th>
        <th scope="col">3월</th>
        <th scope="col">4월</th>
        <th scope="col">5월</th>
        <th scope="col">6월</th>
        <th scope="col">7월</th>
        <th scope="col">8월</th>
        <th scope="col">9월</th>
        <th scope="col">10월</th>
        <th scope="col">11월</th>
        <th scope="col">12월</th>
    </tr>
      <c:forEach var="investCost" items="${techInvest.investCosts}" >
        <tr>
            <th scope="row"><c:out value="${investCost.costItemNm}"/><input type="hidden" class="costItemCd" name="" value="<c:out value="${investCost.costItemCd}"/>"/><input type="hidden" class="invstYear" name="" value="<c:out value="${investCost.invstYear}"/>"/></th>
            <td class="num"><span class="costTot"><fmt:formatNumber value="${investCost.costTot}" groupingUsed="true"/></span></td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost1 number" name="" value="<fmt:formatNumber value="${investCost.cost01}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost01}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost2 number" name="" value="<fmt:formatNumber value="${investCost.cost02}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost02}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost3 number" name="" value="<fmt:formatNumber value="${investCost.cost03}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost03}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost4 number" name="" value="<fmt:formatNumber value="${investCost.cost04}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost04}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost5 number" name="" value="<fmt:formatNumber value="${investCost.cost05}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost05}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost6 number" name="" value="<fmt:formatNumber value="${investCost.cost06}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost06}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost7 number" name="" value="<fmt:formatNumber value="${investCost.cost07}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost07}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost8 number" name="" value="<fmt:formatNumber value="${investCost.cost08}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost08}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost9 number" name="" value="<fmt:formatNumber value="${investCost.cost09}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost09}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost10 number" name="" value="<fmt:formatNumber value="${investCost.cost10}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost10}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost11 number" name="" value="<fmt:formatNumber value="${investCost.cost11}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost11}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='EAA'}">
                <input type="text" class="i_input_full num cost12 number" name="" value="<fmt:formatNumber value="${investCost.cost12}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='EAA'}"><fmt:formatNumber value="${investCost.cost12}" groupingUsed="true"/></c:if>
                </div>
            </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    </c:forEach>
    <c:if test="${empty techInvest.techInvests}">
    <table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="10%">
    <col width="10%">
    <col width="8%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    </colgroup>
    <tbody>
    <tr>
        <td rowspan="7" class="title">
              <select class="startYear">
                  <c:forEach var="year" begin="2005" end="${techInvest.currnetYear + 5}" >
                      <option value="${year}" <c:if test="${year==techInvest.currnetYear}">selected</c:if> ><c:out value="${year}"/> </option>
                  </c:forEach>
              </select>
              <img src="../images/btn_add.gif" class="vam cursor addYear" />
        </td>
        <th scope="col">비용항목</th>
        <th scope="col">합계</th>
        <th scope="col">1월</th>
        <th scope="col">2월</th>
        <th scope="col">3월</th>
        <th scope="col">4월</th>
        <th scope="col">5월</th>
        <th scope="col">6월</th>
        <th scope="col">7월</th>
        <th scope="col">8월</th>
        <th scope="col">9월</th>
        <th scope="col">10월</th>
        <th scope="col">11월</th>
        <th scope="col">12월</th>
    </tr>
     <tr>
        <th scope="row">설계비<input type="hidden" class="costItemCd" name="" value="C01"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">자재비<input type="hidden" class="costItemCd" name="" value="C02"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">공사비<input type="hidden" class="costItemCd" name="" value="C03"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">매입임차비<input type="hidden" class="costItemCd" name="" value="C04"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">대여금<input type="hidden" class="costItemCd" name="" value="C05"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">기타<input type="hidden" class="costItemCd" name="" value="C06"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    </tbody>
    </table>
    </c:if>
</div>
</c:if>
<div class="tempYearCost" style="display:none">
<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="10%">
    <col width="10%">
    <col width="8%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    <col width="6%">
    </colgroup>
    <tbody>
    <tr>
        <td rowspan="7" class="title"><span class="invstYearText">2014</span><img src="../images/btn_del.gif" class="vam cursor delYear" /></td>
        <th scope="col">비용항목</th>
        <th scope="col">합계</th>
        <th scope="col">1월</th>
        <th scope="col">2월</th>
        <th scope="col">3월</th>
        <th scope="col">4월</th>
        <th scope="col">5월</th>
        <th scope="col">6월</th>
        <th scope="col">7월</th>
        <th scope="col">8월</th>
        <th scope="col">9월</th>
        <th scope="col">10월</th>
        <th scope="col">11월</th>
        <th scope="col">12월</th>
    </tr>
    <tr>
        <th scope="row">설계비<input type="hidden" class="costItemCd" name="" value="C01"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">자재비<input type="hidden" class="costItemCd" name="" value="C02"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">공사비<input type="hidden" class="costItemCd" name="" value="C03"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">매입임차비<input type="hidden" class="costItemCd" name="" value="C04"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">대여금<input type="hidden" class="costItemCd" name="" value="C05"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    <tr>
        <th scope="row">기타<input type="hidden" class="costItemCd" name="" value="C06"/><input type="hidden" class="invstYear" name="" /></th>
        <td class="num"><span class="costTot">0</span></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost2 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost3 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost4 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost5 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost6 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost7 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost8 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost9 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost10 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost11 number" name="" value="0"/></div></td>
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></div></td>
    </tr>
    </tbody>
</table>
</div>
</form>
<br />

<script>
$(window).ready(function() {
	$.popupTitle("투자비재산정");

    "<c:forEach var='rvRst' items='${techInvest.rvRsts}'>";
    if("${rvRst.itemCd}" !="") {
        $(".${rvRst.itemCd}").each(function () {
            if($(this).val()=="${rvRst.clCd01}") {
                $(this).prop("checked",true);
            }
        });

        $(".${rvRst.itemCd}_SUGG").val("${rvRst.totRvSugg}");
        if("${modeClass}"!="EAA") {
            $(".${rvRst.itemCd}_SUGG_text").html("${rvRst.totRvSugg}");
        }
    }
    "</c:forEach>";

    "<c:forEach var='appLine' items='${techInvest.appLine}'>";
      if("${appLine.chrgrClCd}"=="Z02I") {
        $.appLineSet({className:"writer", dutyText:"${appLine.thdayPos}",
            text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
            teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
            endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
            date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
      } else if("${appLine.chrgrClCd}"=="Z02C") {
              $.appLineSet({className:"app", dutyText:"${appLine.thdayPos}",
                  text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
                  teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
                  endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
                  date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
      }
    "</c:forEach>";

    $(".save").click(function () {
        if(confirm("저장 하시겠습니까?")) {
        	$.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "ivstInvestCostSave.do").submit();
        }
    });

    $(".appReq").click(function () {
        if(confirm("승인요청 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "investCostAppReq.do").submit();
        }
    });

    $(".app").click(function () {
        if(confirm("승인 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "investCostApp.do").submit();
        }
    });

    $(".return").click(function () {
        if(confirm("반려 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "investCostAppReturn.do").submit();
        }
    });

    $(".addYear").click(function () {
        $(".yearCostArea").append($(".tempYearCost").html());
        $.sortInvest();
    });

    $(".empSearch").click(function() {
        $.popupPopup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
   });

    $.validation = function(){
    	var isValid = false;
    	isValid = $.validator({
                    agreeText : {method:"class",type:"text", msg:"1차 승인자를 입력하세요."},
                    agreeEndDate : {method:"class",type:"text", msg:"1차 승인일을 입력하세요."},
                    appText : {method:"class",type:"text", msg:"2차 승인자를 입력하세요."},
                    appEndDate : {method:"class",type:"text", msg:"2차 승인일을 입력하세요."},
                    techText : {method:"class",type:"text", msg:"기술검토자를 입력하세요."},
                    mrReqTitle : {method:"class",type:"text", msg:"사업명을 입력하세요."},
                    plantNo : {method:"class",type:"select", msg:"플랜트를 선택하세요."},
                    selectUnit : {method:"id",type:"list", focus:"unitDiv", msg:"공정을 선택하세요."},
                    selectEquip : {method:"id",type:"list", focus:"equipDiv", msg:"설비를 선택하세요."},
                    reqClCd : {method:"class",type:"select", msg:"요청구분을 선택하세요."},
                    workPsblClCd : {method:"class",type:"select", msg:"작업시점을 선택하세요."}
                });
    	if($(".reqClCd").val()=="0006") {
          isValid = $.validator({
              tempText : {method:"class",type:"text", msg:"원상복구 책임자를 입력하세요."},
              tempEndDate : {method:"class",type:"text", msg:"원상복구기한을 입력하세요."}
          });
    	}
        return isValid;
    };
     
    $(".startYear").change(function (){
    	$.sortInvest();
    });

    $.sortInvest = function() {
    	var startYear = $(".startYear").val();
    	var totalCount = 0;
    	$(".yearCostArea").children("table").each(function(){
    	    $(this).children("tbody").children("tr:eq(0)").children("td:eq(0)").children(".invstYearText").html(startYear);
    	    $(this).children("tbody").children("tr:gt(0)").each(function() {
    	    	$(this).find(".invstYear").val(startYear);
    	    	$(this).find(".invstYear").attr("name","techInvests["+totalCount+"].invstYear");
    	    	$(this).find(".costItemCd").attr("name","techInvests["+totalCount+"].costItemCd");
    	    	$(this).find(".costTot").attr("name","techInvests["+totalCount+"].costTot");
    	    	for(var i = 1; i < 13; i++ ) {
                    if(i<10) {
    	    		  $(this).find(".cost"+i).attr("name","techInvests["+totalCount+"].cost0"+i);
                    } else {
                        $(this).find(".cost"+i).attr("name","techInvests["+totalCount+"].cost"+i);
                    }
    	    	}
    	    	totalCount++;
    	    });
    	    startYear++;
    	});
    };

    $.sortInvest();


});


$(document).on("click",".delYear", function () {
    $(this).parent("td").parent("tr").parent("tbody").parent("table").remove();
    $.sortInvest();
});


$(document).on("keyup", ".number", function () {


    var row = 0;
    var rowSum = 0;

    row = $(this).parent("div").parent("td").parent("tr").index();


    $(this).parent("div").parent("td").parent("tr").children("td:gt(0)").each(function(){
        rowSum += parseInt($.removeSeparator($(this).children("div").children("input").val()));
    });




    $(this).parent("div").parent("td").parent("tr").children("td:eq(0)").children(".costTot").html($.separator(rowSum+""));
    $.costSummary();
});

$.costSummary = function () {
    var row = 0;
    var totalSum = 0;
    var sum  = new Array();
    for(var i = 1; i<7; i++) {
        sum[i] = 0;
    }

    $(".yearCostArea").children("table").each(function() {
        row = 0;
        $(this).children("tbody").children("tr:gt(0)").each(function() {
            row++;
            sum[row] = sum[row] + parseInt($.removeSeparator($(this).children("td:eq(0)").children(".costTot").html()));
        });
    });
    for(var j = 1; j<7; j++) {
        $(".costSumC0"+j).html($.separator(sum[j]+""));
        totalSum += parseInt(sum[j]);
    }

    $(".costTotalSum").html($.separator(totalSum+""));

};
</script>