<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">초기 투자비 산정</h4>
</div>
    <script>

    //console.log("${modeClass}");
    //alert("${modeClass}");			//CBB
    //alert("${appLine.chrgrClCd}");
    //alert("${appLine.ifStatCd}");
    //alert("${appLine.chrgEmpNo}");
    //alert("${appLine.loginEmpNo}");    
    
    </script>


<!-- button -->
<div class="button_top">
<!--
2014 07 17 삭제
    <input type="image" src="images/btn_skip.png" style="" class="vam cursor skip CAA" style="display:none"/> -->
    
    <!-- HSW 재전송 추가  -->
    <c:forEach var='appLine' items='${techInvest.appLine}' varStatus="loop">
    <c:if test="${appLine.chrgrClCd =='Z02I'}"> 
    <c:if test="${appLine.ifStatCd != '1' && appLine.chrgEmpNo == appLine.loginEmpNo}">
    <script>
    
    //alert("${modeClass}");
    //alert("${appLine.chrgrClCd}");
    //alert("${appLine.ifStatCd}");
    //alert("${appLine.chrgEmpNo}");
    //alert("${appLine.loginEmpNo}");    
    
    </script>
    <input type="image" src="images/btn_reRequestApproval.png" style="display:none" class="cursor reAppReq CBA"/>
    </c:if>
    </c:if>
    </c:forEach>
    <c:if test="${techInvest.engYn=='Y'}">
        <input type="image" src="images/btn_temp.png" style="display:none" class="vam cursor save CAA"/>        
        <input type="image" src="images/btn_requestApproval.png" style="display:none" class="vam cursor appReq CAA"/>
    </c:if>
    <!-- hajewook 수정 승인반려 버튼 비활성화  -->
    <!-- <input type="image" src="images/btn_approve.png" style="display:block" class="vam cursor app CBA"/>--><!-- 승인 주석 처리 (전자 결재) -->
    <!--<input type="image" src="images/btn_return.png" style="display:none" class="vam cursor return CBA"/> -->

    <!-- HSW 2022.01.05 주석처리
    <!-- <input type="image" src="images/btn_approve.png" style="display:none" class="vam cursor app "/>--> <!--승인 -->
    <!-- <input type="image" src="images/btn_return.png" style="display:none" class="vam cursor return "/>--> <!--반려-->
    <!-- <input type="image" src="images/btn_back2.png"  style="display:none" class="vam cursor back CAA"/>--> <!--반송--> 
    <!-- END -->
    
    <!-- <br><input type="image" src="images/btn_reRequestApproval.png" class="cursor reAppReq CBA"/> -->
    <!--<br><input type="image" src="images/btn_approve.png" class="vam cursor app "/>--><!--승인 -->
    <!-- <br><input type="image" src="images/btn_back2.png"   class="vam cursor back CAA"/>-->
    <!-- <br><input type="image" src="images/btn_return.png" style="display:none" class="vam cursor return "/>--><!--반려-->
</div>

<div id="content_wrap">
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${techInvest.mrReqNo}"/>"/>
<input type="hidden" name="mrInvstCostNo" value="<c:out value="${techInvest.mrInvstCostNo}"/>"/>
<input type="hidden" id="mrNo" value="<c:out value="${techInvest.mrNo}"/>"/>
<input type="hidden" id="plantNo" value="<c:out value="${techInvest.plant}"/>"/>
<!-- 
   <input type="hidden" class="appValue" name="appLine[1].chrgEmpNo" />
   <input type="hidden" name="appLine[1].chrgrClCd" value="Z02G"/>
   <input type="hidden" class="appDutyText" name="appLine[1].thdayPos" />
   <input type="hidden" class="appTeamText" name="appLine[1].thdayTeam" />
   <input type="hidden" class="appTeamValue" name="appLine[1].chrgTeamNo" />
   <input type="hidden" name="appLine[1].procTrmDt" class="appEndDate" value="9999-12-31"/> 
-->
<table class="table_row projectEngT" border="1" cellspacing="0">
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
        <th class="engHeader" scope="row" rowspan="2">Project Engineer지정<img id="addEmp" src="images/icon_s_plus.png" border="0" class="cursor" <c:if test="${techInvest.engYn !='N' or modeClass != 'CAA'}"> style="display:none"  </c:if> /></th>
    </tr>
    <tr>  
        <td>
            <div class="form_group">
              <span class="agreeText"><c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}"><span class="agreeText"></span></c:if>
              <c:if test="${techInvest.engYn=='N' and modeClass=='CAA'}"><input type="text" class="vam agreeText i_input" readOnly/></c:if>
              <input type="hidden" class="empLine0 agreeValue" name="appLine[0].chrgEmpNo" />
              <input type="hidden" class="empLine0 agreeCode"  name="appLine[0].chrgrClCd" value="Z02I"/>
              <input type="hidden" class="empLine0 agreeDutyText" name="appLine[0].thdayPos" />
              <input type="hidden" class="empLine0 agreeTeamText" name="appLine[0].thdayTeam" />
              <input type="hidden" class="empLine0 agreeTeamValue" name="appLine[0].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empSearch" data-role="agree" <c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}">style="display:none"</c:if>/>
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="agree" <c:if test="${techInvest.engYn!='Y' or modeClass!='CAA'}">style="display:none"</c:if>/>
            </div>
        </td>
        <th scope="row">작성일</th>
        <td>
            <div class="form_group">
	           <c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}"><span class="agreeDate"></span></c:if>
	           <c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}"><span class="agreeEndDate"></span></c:if>
	           <c:if test="${techInvest.engYn=='N' and modeClass=='CAA'}">
	                승인기한 : <input type="text" name="appLine[0].procTrmDt" class="inputDate agreeEndDate limitDate i_input" value="" readOnly/>(결재중)
	           </c:if>

              <c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}">
                    <c:out value="${techInvest.fstRgstDtText}"/>
              </c:if>
	           
	           <c:if test="${techInvest.engYn=='Y' or modeClass!='CAA'}">
	                <input type="hidden" name="appLine[0].procTrmDt" class="agreeEndDate" value=""/>
	           	</c:if>			
	           	<c:if test="${techInvest.engYn=='N' and modeClass=='CAA'}">
	           		<input type="image" src="images/btn_inAppointStaff.png" style="" class="vam cursor send CAA"/>
	           	</c:if>	           	
           </div>
        </td>
    </tr>
    </tbody>
	<tbody>
		<tr><!-- 201906추가담당자 -->
	        <th class="dep2" scope="row">추가담당자<img id="addE_t" src="images/icon_s_plus.png" border="0" class="cursor CAA" style="display:none"/></th>
	       	<td colspan="4">
				<table id="empL_t">
	            	<tr style="display:none"><td></td></tr>
	           	</table>
	       	</td>
	    </tr>
    </tbody>        
</table>


<!-- space non 10 -->
<hr class="divider_none_10" />


<!-- wj:직책과장 추가 -->
<!-- 드롭다운 리스트로 추가 기술검토항목에서 입력한 직책과장을 선택할수 있게 처리
		2명이상 직책과장이 입력되어 있을시 선택한 데이터 이외의 항목은 저장 날짜를 입력하여
		선택한 데이터만 유지가 가능하도록 처리-->
<!-- 2018-01-08 로직이 완벽하지 않아 운영서버 배포시 주석 -->		
<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
	    <col width="15%"> 
	    <col width="85%">
    </colgroup>    
    <tbody>    
	    <tr><!-- addEmp -->
	        <th scope="row">직책과장</th>
	        <td><select class="chrgDetNo CAA" name="chrgDetNo" disabled="disabled"></select></td>			
	    </tr>
    </tbody>
</table>

<hr class="divider_none_10" />

<!-- wj:직책과장2차 -->
<!-- 드롭다운 리스트 팝업창으로 추가처리 -->
<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
	    <col width="15%"> 
	    <col width="85%">
    </colgroup>    
    <tbody style="display:none">    
	    <tr><!-- addEmp -->
	        <th scope="row">직책과장</th>
	    </tr>
    </tbody>
</table>
<hr class="divider_none_10" />

<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="35%">
    <col width="15%">
    <col width="35%">
    </colgroup>    
    <tbody>    
	    <tr>
	        <th scope="row">MR번호<!-- (초기투자비 산정)--></th>
	        <td colspan="3"><c:out value="${techInvest.mrNo}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">사업명</th>
	        <td colspan="3"><c:out value="${techInvest.mrReqTitle}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">요청구분</th>
	        <td><c:out value="${techInvest.reqClCdNm}"/> / <c:out value="${techInvest.reqClDtlCdNm}"/></td>
	        <th scope="row">개선대상</th>
	        <td><c:out value="${techInvest.plantNm}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">공정</th>
	        <td>
	           <table id="selectUnit" class="table_default">
	              <c:forEach var="proc" items="${techInvest.procs}" varStatus="loop">
	                  <tr>
	                    <td><c:out value="${proc.mrProcNo}"/></td>
	                    <td><c:out value="${proc.mrProcName}"/></td>
	                  <tr>
	              </c:forEach>
	           </table>
	        </td>
	        <th scope="row">설비</th>
	        <td>
	            <table id="selectEquip" class="table_default">
	                <c:forEach var="equip" items="${techInvest.equips}" varStatus="loop">
	                  <tr>
	                    <td><c:out value="${equip.mrEquipCd}"/></td>
	                    <td><c:out value="${equip.mrEquipName}"/></td>
	                  <tr>
	                </c:forEach>
	            </table>
	        </td>
	    </tr>
	    <tr>
	        <th scope="row">작업시점</th>
	        <td colspan="3"><c:out value="${techInvest.workPsblClCdNm}"/> / <fmt:formatDate value="${techInvest.workPsblDt}" pattern="yyyy-MM-dd" /></td>
    	</tr>
    </tbody>
</table>

<!-- space non 10 -->
<hr class="divider_none_10" />

<c:if test="${techInvest.engYn=='Y'}">
<div class="tr bold">단위 : 천원</div>

<!-- space non 5 -->
<hr class="divider_none_5" />

<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="19%">
    <col width="15%">
    <col width="18%">
    <col width="15%">
    <col width="18%">
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
              <c:if test="${modeClass=='CAA'}"><select class="startYear CAA" disabled="disabled">
                  <c:forEach var="year" begin="2005" end="${techInvest.currnetYear + 5}" >
                      <option value="${year}" <c:if test="${(empty techInvest.invstYear && year==techInvest.currnetYear) || (not empty techInvest.invstYear && year==techInvest.invstYear)}">selected</c:if> ><c:out value="${year}"/> </option>
                  </c:forEach>
              </select>
              <img src="images/btn_add.gif" class="vam cursor addYear" /></c:if>
              <c:if test="${modeClass!='CAA'}">
                    <c:out value="${techInvest.invstYear}"/>
              </c:if>
          </c:if>
          <c:if test="${loop.index>0 }">
              <c:if test="${modeClass!='CAA'}">
                  <c:out value="${techInvest.invstYear}"/>
              </c:if>
              <c:if test="${modeClass=='CAA'}">
                  <span class="invstYearText"></span><img src="images/btn_del.gif" class="vam cursor delYear" />
              </c:if>
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
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost1 number" name="" value="<fmt:formatNumber value="${investCost.cost01}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost01}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost2 number" name="" value="<fmt:formatNumber value="${investCost.cost02}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost02}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost3 number" name="" value="<fmt:formatNumber value="${investCost.cost03}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost03}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost4 number" name="" value="<fmt:formatNumber value="${investCost.cost04}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost04}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost5 number" name="" value="<fmt:formatNumber value="${investCost.cost05}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost05}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost6 number" name="" value="<fmt:formatNumber value="${investCost.cost06}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost06}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost7 number" name="" value="<fmt:formatNumber value="${investCost.cost07}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost07}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost8 number" name="" value="<fmt:formatNumber value="${investCost.cost08}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost08}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost9 number" name="" value="<fmt:formatNumber value="${investCost.cost09}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost09}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost10 number" name="" value="<fmt:formatNumber value="${investCost.cost10}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost10}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost11 number" name="" value="<fmt:formatNumber value="${investCost.cost11}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost11}" groupingUsed="true"/></c:if>
                </div>
            </td>
            <td class="num">
                <div class="form_group">
                <c:if test="${modeClass=='CAA'}">
                <input type="text" class="i_input_full num cost12 number" name="" value="<fmt:formatNumber value="${investCost.cost12}" groupingUsed="true"/>"/>
                </c:if>
                <c:if test="${modeClass!='CAA'}"><fmt:formatNumber value="${investCost.cost12}" groupingUsed="true"/></c:if>
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
              <select class="startYear CAA" disabled="disabled">
                  <c:forEach var="year" begin="2005" end="${techInvest.currnetYear + 5}" >
                      <option value="${year}" <c:if test="${year==techInvest.currnetYear}">selected</c:if> ><c:out value="${year}"/> </option>
                  </c:forEach>
              </select>
              <c:if test="${modeClass=='CAA'}"><img src="images/btn_add.gif" class="vam cursor addYear" /></c:if>
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
        <td><div class="form_group"><input type="text" class="i_input_full num cost12 number" name="" value="0"/></td>
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

<!-- space non 10 -->
<hr class="divider_none_10" />

<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="10%">
    <col width="10%">
    <col width="80%">
    </colgroup>
    <tbody>
    <tr>
        <th class="dep1" rowspan="5" scope="row">설계 타당성<br />검토</th>
        <th class="dep2" scope="row">자재납기 제약</th>
        <td>
            <div class="form_group COST01_group">
            <input type="hidden" name="rvRsts[0].itemCd" value="COST01" />
            <input type="radio" name="rvRsts[0].clCd01" class="i_radio CAA COST01" value="1" disabled="disabled"/>있음
            <input type="radio" name="rvRsts[0].clCd01" class="i_radio CAA COST01" value="2" disabled="disabled"/>없음
            <input type="radio" name="rvRsts[0].clCd01" class="i_radio CAA COST01" value="3" disabled="disabled"/>조건부 가능
            <span class="ml30">의견 : <c:if test="${modeClass!='CAA'}"><span class="COST01_SUGG_text"></span></c:if><c:if test="${modeClass=='CAA'}">
            <input type="text" name="rvRsts[0].totRvSugg" class="i_input_200 CAA COST01_SUGG" value="" /></c:if></span>
            </div>
        </td>
    </tr>
    <tr>
        <th class="dep2" scope="row">설치 공간 제약</th>
        <td>
            <div class="form_group COST02_group">
            <input type="hidden" name="rvRsts[1].itemCd" value="COST02" />
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio CAA COST02" value="1" disabled="disabled"/>있음
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio CAA COST02" value="2" disabled="disabled"/>없음
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio CAA COST02" value="3" disabled="disabled"/>조건부 가능
            <span class="ml30">의견 : <c:if test="${modeClass!='CAA'}"><span class="COST02_SUGG_text"></span></c:if><c:if test="${modeClass=='CAA'}">
            <input type="text" name="rvRsts[1].totRvSugg" class="i_input_200 CAA COST02_SUGG" value="" /></c:if></span>
            </div>
        </td>
    </tr>
    <tr>
        <th class="dep2" scope="row">정비 공간 제약</th>
        <td>
            <div class="form_group COST03_group">
            <input type="hidden" name="rvRsts[2].itemCd" value="COST03" />
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio CAA COST03" value="1" disabled="disabled"/>있음
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio CAA COST03" value="2" disabled="disabled"/>없음
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio CAA COST03" value="3" disabled="disabled"/>조건부 가능
            <span class="ml30">의견 : <c:if test="${modeClass!='CAA'}"><span class="COST03_SUGG_text"></span></c:if><c:if test="${modeClass=='CAA'}">
            <input type="text" name="rvRsts[2].totRvSugg" class="i_input_200 CAA COST03_SUGG" value="" /></c:if></span>
            </div>
        </td>
    </tr>
    <tr>
        <th class="dep2" scope="row">시공 시기</th>
        <td>
            <div class="form_group COST04_group">
            <input type="hidden" name="rvRsts[3].itemCd" value="COST04" />
            <input type="radio" name="rvRsts[3].clCd01" class="i_radio CAA COST04" value="1" disabled="disabled"/>가능
            <input type="radio" name="rvRsts[3].clCd01" class="i_radio CAA COST04" value="2" disabled="disabled"/>불가능
            <input type="radio" name="rvRsts[3].clCd01" class="i_radio CAA COST04" value="3" disabled="disabled"/>조건부 가능
            <span class="ml30">의견 : 
            <c:if test="${modeClass!='CAA'}"><span class="COST04_SUGG_text"></span></c:if>
            <c:if test="${modeClass=='CAA'}">
            <input type="text" name="rvRsts[3].totRvSugg" class="i_input_200 CAA COST04_SUGG" value=""/></c:if></span>
            </div>
        </td>
    </tr>
    <tr>
        <th class="dep2" scope="row">기타</th>
        <td>
            <div class="form_group">
            <input type="hidden" name="rvRsts[4].itemCd" value="COST05" />
            <span class="ml30">
            <c:if test="${modeClass!='CAA'}">
            <input type="text" name="rvRsts[4].totRvSugg" class="i_input_200 CAA COST05_SUGG" value="" readonly="readonly" /></c:if>
            <c:if test="${modeClass=='CAA'}">
            <input type="text" name="rvRsts[4].totRvSugg" class="i_input_200 CAA COST05_SUGG" value=""/></c:if></span>
            </div>
        </td>
    </tr>
    </tbody>
</table>

<c:set var="totRvSugg_" value='${fn:replace(techInvest.totRvSugg,"¶", "\'")}' />
<!-- space non 10 -->
<hr class="divider_none_10" />
    <table class="table_row" id="file" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="90%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row">종합검토의견</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='CAA'}">
                <input type="hidden" name="rvRsts[5].itemCd" value="COST06" />
                <textarea name="rvRsts[5].totRvSugg" rows="" cols="" class="i_text_full"><c:out value="${totRvSugg_}"/></textarea>
            </c:if>
            <c:if test="${modeClass!='CAA'}">
                <pre><c:out value="${totRvSugg_}"/></pre>
            </c:if>
            </div>
        </td>
        </tr>
        </tbody>
    </table>

<!-- space non 10 -->
<hr class="divider_none_10" />

    <table class="table_row" id="file" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="90%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row" class="vam fileAdd">첨부파일
          <img id="addFile" src="images/icon_s_plus.png" border="0" class="cursor CAA addFile" style="display:none"/>
        </th>
        <td class="section_text pl5 pt5 pb5" colspan="3">
          <div class="form_group">
            <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
              <c:forEach var="mrAtchFiles" items="${techInvest.mrAtchFiles}" varStatus="loop">
              <tr>
                 <td>
                  <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
                  <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
                  <img src="images/btn_inFile.png" class="cursor fileDown"/>
                  <img class="cursor CAA removeDownFile" src="images/icon_minus.png" style="display:none"/>
                  <input type="hidden" value="${mrAtchFiles.fileCd}"/>
                  <input type="hidden" class="delFlag" name="mrAtchFiles[${loop.index}].deleted" value="false"/>
                  <input type="hidden" class="insFlag" name="mrAtchFiles[${loop.index}].inserted" value="false"/>
                  <input type="hidden" name="mrAtchFiles[${loop.index}].mrAtchFileNo" value="${mrAtchFiles.mrAtchFileNo}"/>
                  <input type="hidden" class="drawMngNo" name="mrAtchFiles[${loop.index}].drawMngNo" value="${mrAtchFiles.drawMngNo}"/>
                </td>
              </tr>
            </c:forEach>
            </table>
          </div>
        </td>
        </tr>
        </tbody>
    </table>
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
        <td rowspan="7" class="title"><span class="invstYearText">2014</span><img src="images/btn_del.gif" class="vam cursor delYear" /></td>
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
        <td><div class="form_group"><input type="text" class="i_input_full num cost1 number" name="" value="0" maxlength="10"/></div></td>
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
</div>

<!-- 201906추가담당자 템프테이블-->
<table id="empT_t" style="display:none">
    <tr>
        <td style="border:0px;padding:0px;margin:0px;">
            <div class="form_group">
            <c:if test="${modeClass!='CAA'}"><span class="tempText empText" style="border:0px;"></span></c:if>
            <c:if test="${modeClass=='CAA'}"><input type="text" class="i_input tempText empText" readOnly/></c:if>
            <input type="hidden" class="tempValue empValue" name="appLine[3].chrgEmpNo" />
            <input type="hidden" class="tempCode empCode" name="appLine[3].chrgrClCd" value="Z02I1"/>
            <input type="hidden" class="tempDutyText empDuty" name="appLine[3].thdayPos" />
            <input type="hidden" class="tempTeamText empTeam" name="appLine[3].thdayTeam" />
            <input type="hidden" class="tempTeamValue empTeamValue" name="appLine[3].chrgTeamNo" />
            <img src="images/icon_search.png" class="cursor empSearch CAA" data-role="temp" style="display:none"/>
            <img class="cursor removeE_t CAA" src="images/icon_minus.png" style="display:none"/>
            </div>
        </td>
    </tr>
</table>

<script>
var gianServer = "";
if("${m_bOperDatabaseConfig}" == "false")
	gianServer = "dev";

console.log("gianServer : " + gianServer);

$.sortEmpLine = function(){
	var empDiv;
	var count = 0;
	$(".projectEngT").children("tbody").children("tr:gt(1)").each(function () {

		empDiv = $(this).children("td").children("div");
		
		empDiv.find(".agreeValue").attr("name","appLine["+count+"].chrgEmpNo");
		empDiv.find(".agreeCode").attr("name","appLine["+count+"].chrgrClCd");
		empDiv.find(".agreeDutyText").attr("name","appLine["+count+"].thdayPos");
		empDiv.find(".agreeTeamText").attr("name","appLine["+count+"].thdayTeam");
		empDiv.find(".agreeTeamValue").attr("name","appLine["+count+"].chrgTeamNo");
		empDiv.find(".agreeEndDate").attr("name","appLine["+count+"].procTrmDt");

        empDiv.find(".agreeText").attr("class","empLine"+count+"Text agreeText i_input");
        empDiv.find(".agreeValue").attr("class","empLine"+count+"Value agreeValue");
        empDiv.find(".agreeCode").attr("class","empLine"+count+"Code agreeCode");
        empDiv.find(".agreeDutyText").attr("class","empLine"+count+"DutyText agreeDutyText");
        empDiv.find(".agreeTeamText").attr("class","empLine"+count+"TeamText agreeTeamText");
        empDiv.find(".agreeTeamValue").attr("class","empLine"+count+"TeamValue agreeTeamValue");
        empDiv.find(".empSearch").attr("data-role","empLine"+count);		
        count++;
	});
};


//201906추가담당
$.sortE_t = function(){
	var empDiv;	
	var count = 1;	
	$("#empL_t").children("tbody").children("tr").each(function () {
		//console.log("확인내역    appLine["+count+"].chrgEmpNo");
		empDiv = $(this).children("td").children("div");
		empDiv.find(".empValue").attr("name","appLine["+count+"].chrgEmpNo");
		empDiv.find(".empCode").attr("name","appLine["+count+"].chrgrClCd");
		empDiv.find(".empDuty").attr("name","appLine["+count+"].thdayPos");
		empDiv.find(".empTeam").attr("name","appLine["+count+"].thdayTeam");
		empDiv.find(".empTeamValue").attr("name","appLine["+count+"].chrgTeamNo");
		
		empDiv.find(".empText").attr("class","empLine"+count+"Text empText i_input");
        empDiv.find(".empValue").attr("class","empLine"+count+"Value empValue");
        empDiv.find(".empCode").attr("class","empLine"+count+"Code empCode");
        empDiv.find(".empDuty").attr("class","empLine"+count+"DutyText empDuty");
        empDiv.find(".empTeam").attr("class","empLine"+count+"TeamText empTeam");
        empDiv.find(".empTeamValue").attr("class","empLine"+count+"TeamValue empTeamValue");
        empDiv.find(".empSearch").attr("data-role","empLine"+count);
		count++;
	});
};    

$(window).ready(function() {	
	$(document).on("click","#addEmp", function () {
		$("#empTemp").children("tbody").children("tr").each(function (){
			var tempDiv = $(this).children("td").children("div");
			tempDiv.find(".agreeText").val("");
			tempDiv.find(".agreeValue").val("");
			tempDiv.find(".agreeDutyText").val("");
			tempDiv.find(".agreeTeamText").val("");
			tempDiv.find(".agreeTeamValue").val("");
		});
	    var empTemp = $("#empTemp").children("tbody").children("tr");
	    empTemp.clone().insertAfter($(".projectEngT").children("tbody").children("tr:last"));
	    $(".engHeader").prop("rowspan",$(".projectEngT").children("tbody").children("tr").length+1);
	    $.sortEmpLine();
	});


    //201906추가담당자 (+)버튼 클릭
    $(document).on("click","#addE_t", function () {    	
    	//alert("addEmp_t");	console.log("생성");    	
        var empTemp = $("#empT_t").children("tbody").children("tr");
        empTemp.clone().insertAfter($("#empL_t").children("tbody").children("tr:last"));    
        $.sortE_t();
    });
    
    //추가담당자 (-)버튼 클릭
    $(document).on("click", ".removeE_t", function() {
        $(this).parent("div").parent("td").parent("tr").remove();
        $.sortE_t();
    });	
    
    "<c:forEach var='rvRst' items='${techInvest.rvRsts}'>";
    if("${rvRst.itemCd}" !="") {
    var index = '.COST' + '${fn:replace(rvRst.itemCd, "COST", "")}' + '_SUGG';
    var val_ =  '${fn:replace(rvRst.totRvSugg, "¶", "")}';
    //var val_ =  '${rvRst.totRvSugg}';
    $(index).val(val_);
        $(".${rvRst.itemCd}").each(function () {
            if($(this).val()=="${rvRst.clCd01}") {
                $(this).prop("checked",true);
            }
        });
    }
    "</c:forEach>";
    
        
    //직책과장 추가(파라미터: 해당 MR 번호)
    $.selectLoad({
        className : "chrgDetNo",
        url : "codeList.do?mrCommGrpCd=chrgDetNo&parentCd=${techInvest.mrReqNo}",
        defaultText : "선택",
        selectValue : "${techInvest.chrgDetNo}"+""
    });    
    
    var empCount=0;
    var empC_t = 2;    
    "<c:forEach var='appLine' items='${techInvest.appLine}'>";
    console.log("appLine.chrgrClCd : ${appLine.chrgrClCd}")
      if("${appLine.chrgrClCd}"=="Z02I") {
    	  if(empCount >0){
    	  	$("#addEmp").click(); 
    	  }
    	  $.sortEmpLine();
          var appClass = "empLine"+empCount;
          empCount++;
        $.appLineSet({className:appClass, dutyText:"${appLine.thdayPos}",
            text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
            teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
            endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
            date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});    
      } else if("${appLine.chrgrClCd}"=="Z02G") {
        $.appLineSet({className:"app", dutyText:"${appLine.thdayPos}",
            text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
            teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
            endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
            date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
      } else if("${appLine.chrgrClCd}"=="Z02I1") {//201906추가담당
	   	  	if(empC_t >0){
		  		$("#addE_t").click();
		  	}
	  		$.sortE_t();	  		
	  		
     		var appC_t = "empLine"+empC_t;
      	  	empC_t++
	         $.appLineSet({className:appC_t, dutyText:"${appLine.thdayPos}",
	             text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
	             teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
	             endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
	             date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
      } 
      
    "</c:forEach>";

    $(".skip").click(function () {
        if(confirm("SKIP 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrTechInvestSkip.do").submit();
        }
    });    
    
    //담당자 지정클릭
    $(".send").click(function () {
    	if($.projectEngineerValidation())
        if(confirm("지정 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrTechInvestLineSet.do").submit();
        }
    });

    //임시저장 클릭
    $(".save").click(function () {
        if(confirm("저장 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrTechInvestSave.do").submit();
        }
    });

	//승인요청 클릭
    $(".appReq").click(function () {
    	
    	
    	//alert('start!!');
    	//yoo 240205 승인 요청 사전에 로그인 자의 팀과 사업수행팀이 같은 지 체크 한다
    	var data = new FormData();
			  data.append("mrReqNo", "${techInvest.mrReqNo}");
    	var bTeamCompare = false;
    	$.ajax({
            type:"POST",
            url:"mrTechInvestSearchTeam.do",
            data:data,
            dataType:"JSON",
            processData : false,
	        contentType : false,
	        async: false,
            success : function(data) {
            	var map = data.chrgTeamNoList;
            	console.log(map['result']);
            	console.log(map['ChrgEmpName1']);
            	
            	
      		    if(map['result'] == 'true')
          		{
          		    bTeamCompare = true;
          		}else if(map['result'] == 'false')
       		    {
          		    bTeamCompare = false;
       		    	alert('승인자 ' + map['ChrgEmpName1'] + '님의 부서코드가 다릅니다\nIT담당자에게 변경 요청하시기 바랍니다');
       		    }	
       		    
      		    //yoo 240208 값 디버깅용 - 값을 찍어 본다
           		$.each(data.chrgTeamNoList, function(index, value){
           		    console.log(index + " : " + value);		
           		})
            		
             },
             error : function(request) {
            	 alert('error!!');
                 if(request.status==500) {
                 	history.go(0);
                 }
             }
     	});
    	
    	//alert(bTeamCompare);
    	if(bTeamCompare)
    	{
	    	if($.validation())
	        if(confirm("승인요청 하시겠습니까?")) {
	            $.removeComma();
	            $('#pageLoadingWrap').show();
	            $("form").attr("action", "mrTechInvestAppReq.do").submit();
	            
	        }
    	}
    	
    	
    	
    });

/***************************************
 * HSW 재상신
 * 기안 로직 
 ***************************************/
    $(".reAppReq").click(function () {
		//alert("재상신");    	
        //alert("${mrAction}");
        //alert("${modeClass}");
        /*var url = "http://" + gianServer + "gian.oilbank.co.kr/connlogin.aspx?"*/
   	  var url = "https://" + gianServer + "hdep.oilbank.co.kr/approval/legacy/goFormLink.do?"     
        + "&uid="      + "${techInvest.loginEmpNo}"
        + "&connkey="  + "${techInvest.mrReqNo}"
        + "&connType=" + "WEB"
        + "&connName=" + "MR"
        + "&connCode=" + "Z0030_Z0110"
        + "&endTime="  + "DRAFT"
        + "&auto="     + "N";
    window.open(url, "mrTechInvestAppReq");
   });
	
	
	
	//승인클릭
    $(".app").click(function () {
        if(confirm("승인 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrTechInvestApp.do").submit();
        }
    });

    $(".return").click(function () {
        if(confirm("반려 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrTechInvestReturn.do").submit();
        }
    });    

	//반송
    $(".back").click(function () {
        if(confirm("반송 하시겠습니까(반송시 기술검토항목 승인전으로 돌아갑니다)?")) {            
        	
        	$.removeComma();			//class 에 number 형식인 항목의 값 쉼표 제거(baseLayout.jsp)
            $('#pageLoadingWrap').show();	
            $("form").attr("action", "mrTechInvestBack.do").submit();
        }
    });

    $(".addYear").click(function () {
        $(".yearCostArea").append($(".tempYearCost").html());
        $.sortInvest();
    });

    $(document).on("click", ".empSearch", function() {
        $.popup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
   });

    //Project Engineer Validation 
    $.projectEngineerValidation = function(){
    	var isValid = false;    	
        isValid = $.validator({
        	agreeText : {method:"class",type:"text", msg:"Project Engineer를 입력하세요."},        	
        	agreeEndDate : {method:"class",type:"text", msg:"작성일을 입력하세요."}        	 
        	
        });
        return isValid;
    };
    
    //임시저장, 승인요청 Validation
    //20180206 직책과장추가 (직책과장이 애시당초 없을때 담당자에게 처리 문의)
    //20190322 직책과장 벨리데이션 제거 
    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
            COST01 : {method:"class",type:"check",focus :"COST01_group",msg:"자재납기 제약을 선택하세요."},
            COST02 : {method:"class",type:"check",focus :"COST02_group",msg:"설치 공간 제약을 선택하세요."},
            COST03 : {method:"class",type:"check",focus :"COST03_group",msg:"정비 공간 제약을 선택하세요."},
            COST04 : {method:"class",type:"check",focus :"COST04_group",msg:"시공 시기를 선택하세요."}
            //chrgDetNo : {method:"class",type:"select", msg:"직책과장을 선택하세요."}
        });
        
        //20171201배포시 주석
        //if(isValid && $(".reqClDtlCd").val()!="0006") {
  	    //  isValid = $.validator({
  	   // 	  reqClDtlCd : {method:"class",type:"select", msg:"요청구분 상세를 선택하세요."}
        //    });
	  	//}       
        
        return isValid;
    };

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
            if(totalCount>1) {
                startYear++;
            }
        });
    };

    $.sortInvest();


    $(".empChange").click(function (event) {
        $.popup({url:"popup/jobEmpChange.do?&mrReqNo="+$("#mrReqNo").val() +"&empNo="+$("."+$(this).attr("data-role")+"Value").val(), width:"880", height:"500"});
        event.stopPropagation();
    });
    
    
    /***************************************
     * hajewook 추가
     * 기안 로직
     * 테스트 url "http://devhdep.oilbank.co.kr/approval/legacy/goFormLink.do?"
     ***************************************/
     //alert("기안 "+  "${modeClass}");
    
    console.log('mrAction : ' + '${mrAction}' + ' modeClass : ' + '${modeClass}');
     if("${mrAction}" == "mrTechInvestAppReq" &&  "${modeClass}" == "CBA"){
    	//alert("상신시작");
        //alert("${mrAction}");
        //alert("${modeClass}");
    	
    	//alert("${modeClass}");
         //var url = "http:hdep.oilbank.co.kr/approval/legacy/goFormLink.do?"                
        		 
    	 /*var url = "http://" + gianServer + "gian.oilbank.co.kr/connlogin.aspx?"*/
    	var url = "https://" + gianServer + "hdep.oilbank.co.kr/approval/legacy/goFormLink.do?"
         + "uid="       + "${techInvest.loginEmpNo}"
         + "&connkey="  + "${techInvest.mrReqNo}"
         + "&connType=" + "WEB"
         + "&connName=" + "MR"
         + "&connCode=" + "Z0030_Z0110"
         + "&endTime="  + "DRAFT"
         + "&auto="     + "N";
     	window.open(url, "mrTechInvestAppReq");         
     }
});



$(document).on("click",".delYear", function () {
    $(this).parent("td").parent("tr").parent("tbody").parent("table").remove();
    $.sortInvest();
});

$(document).on("change", ".startYear", function () {
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