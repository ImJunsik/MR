<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<!-- title -->
<div id="content_header">
    <h4 class="content_header title">직무검토(1차 변경관리위원회)</h4>
</div> 
    <script>
    //alert("modeClass:"+"${modeClass}");	//DAA
    //alert("subModeClass:"+"${subModeClass}");	//IAD
    //alert("costModeClass:"+"${costModeClass}");	//IANN
    //alert("jobsReview.isJobsComplete:"+"${jobsReview.isJobsComplete}");	//false    
    //alert("costModeClass:"+"${costModeClass}");	//IANN
    //alert("appLine.ifStatCd:"+"${appLine.ifStatCd}");
    //alert("appLine.chrgEmpNo:"+"${appLine.chrgEmpNo}");
    //alert("appLine.loginEmpNo:"+"${appLine.loginEmpNo}");
    </script>    
<!-- button -->
<div class="button_top">
	<c:if test="${isSkip!='TRUE'}">
		<input type="image" src="images/btn_holdoff.png" style="display:none" class="vam cursor skip DAA"/> 
	</c:if>
    	<input type="image" src="images/btn_save.png" style="display:none" class="vam cursor save DAA"/>
    <c:if test="${subModeClass=='IAD' && jobsReview.isJobsComplete}">
    	<input type="image" src="images/btn_complete.png" class="vam cursor complete"/> 
    </c:if>
    <c:if test="${costModeClass=='IAFF' && subModeClass=='IAD'}">
    	<input type="image" src="images/btn_requestReratingIC.png" style="" class="vam cursor costModifyReq"/> 
    </c:if><!-- 투자비 재산정 -->
    <c:if test="${costModeClass!='IAFF' && (modeClass=='DAA' || modeClass=='DAB')}">
    	<input type="image" src="images/btn_reratingIC.png" style="" class="vam cursor costModify"/> 
    </c:if><!-- 투자비 재산정 -->
    <input type="hidden" id="reloadAndClose" value="1">
</div>

<div id="content_wrap">
<!-- space non 10 -->
<hr class="divider_none_10" />

<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${jobsReview.mrReqNo}"/>"/>
    <input type="hidden" id="mrNo" value="<c:out value="${jobsReview.mrNo}"/>"/>
    <input type="hidden" id="plantNo" value="<c:out value="${jobsReview.plant}"/>"/>
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
    <td colspan="4" class="topbg"></td>
    </tr>
    <tr>
        <th scope="row">MR번호</th>
        <td colspan="3"><c:out value="${jobsReview.mrNo}"/></td>
    </tr>
    <tr>
        <th scope="row">사업명</th>
        <td colspan="3"><c:out value="${jobsReview.mrReqTitle}"/></td>
    </tr>
    <tr>
        <th scope="row">요청구분</th>
        <td><c:out value="${jobsReview.reqClCdNm}"/> / <c:out value="${jobsReview.reqClDtlCdNm}"/></td>
        <th scope="row">개선대상</th>
        <td><c:out value="${jobsReview.plantNm}"/></td>
    </tr>
    <tr>
        <th scope="row">공정</th>
        <td>
           <table id="selectUnit" class="table_default">
              <c:forEach var="proc" items="${jobsReview.procs}" varStatus="loop">
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
                <c:forEach var="equip" items="${jobsReview.equips}" varStatus="loop">
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
        <td colspan="3"><c:out value="${jobsReview.workPsblClCdNm}"/> / <fmt:formatDate value="${jobsReview.workPsblDt}" pattern="yyyy-MM-dd" /></td>
    </tr>
    <tr>
      <th scope="row" class="vam fileAdd">첨부파일
      </th>
      <td class="section_text pl5 pt5 pb5" colspan="3">
        <div class="form_group">
          <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
            <tr style="display:none"><td></td></tr>
            <c:forEach var="mrAtchFiles" items="${jobsReview.mrAtchFiles}" varStatus="loop">
            <tr>
               <td>                
                <c:if test="${loop.last}">
                	<input type="hidden" id="multCnt" value ="${loop.index}"><!-- 추가 -->
                </c:if>
                <input id="multipartFile" type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
                <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
                <img src="images/btn_inFile.png" class="cursor fileDown"/>
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

<!-- space non 10 -->
<hr class="divider_none_10" />

<table class="table_row tech" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="13" class="dep1" scope="row">기술 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
            <span class="techText"></span>
            <input type="hidden" class="techValue" name="jobs[0].chrgEmpNo" />
            <input type="hidden" name="jobs[0].chrgrClCd" value="Z02H1"/>
            <input type="hidden" name="rvRsts[0].chrgrClCd" value="Z02H1"/>
            <input type="hidden" class="techDutyText" name="jobs[0].thdayPos" />
            <input type="hidden" class="techTeamText" name="jobs[0].thdayTeam" />
            <input type="hidden" class="techTeamValue" name="jobs[0].chrgTeamNo" />
            <img src="images/icon_search.png" class="vam cursor empChange" data-role="tech" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="techDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">설비 Datasheet</th>
        <td colspan="3"><input type="hidden" name="rvRsts[0].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>수행완료
            <input type="radio" name="rvRsts[0].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">배관/안전밸브 사양검토</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd02" class="i_radio clCd02" value="1" disabled="disabled"/>수행완료
            <input type="radio" name="rvRsts[0].clCd02" class="i_radio clCd02" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">DCS, PI 변경 필요 유무</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd03" class="i_radio clCd03" value="1" disabled="disabled"/>수행완료
            <input type="radio" name="rvRsts[0].clCd03" class="i_radio clCd03" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">환경영향평가</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd04" class="i_radio clCd04" value="1" disabled="disabled"/>수행완료
            <input type="radio" name="rvRsts[0].clCd04" class="i_radio clCd04" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">위험성 검토</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd05" class="i_radio clCd05" value="1" disabled="disabled"/>수행완료
            <input type="radio" name="rvRsts[0].clCd05" class="i_radio clCd05" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">계열사 공용자산 포함여부</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd06" class="i_radio clCd06 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[0].clCd06" class="i_radio clCd06 comment" value="2" disabled="disabled"/>공용자산
            <input type="text" name="rvRsts[0].clCmt06" class="i_input_300 clCmt06 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기본설계, 도면 확인</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd07" class="i_radio clCd07 comment" value="1" disabled="disabled"/>문제없음
            <input type="radio" name="rvRsts[0].clCd07" class="i_radio clCd07 comment" value="2" disabled="disabled"/>Comment
            <input type="text" name="rvRsts[0].clCmt07" class="i_input_300 clCmt07 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">배관/안전밸브 사양확인</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd08" class="i_radio clCd08 comment" value="1" disabled="disabled"/>문제없음
            <input type="radio" name="rvRsts[0].clCd08" class="i_radio clCd08 comment" value="2" disabled="disabled"/>Comment
            <input type="text" name="rvRsts[0].clCmt08" class="i_input_300 clCmt08 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">도면 (PFD/P&ID, UFD, UDD)</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd09" class="i_radio clCd09 comment" value="1" disabled="disabled"/>문제없음
            <input type="radio" name="rvRsts[0].clCd09" class="i_radio clCd09 comment" value="2" disabled="disabled"/>Comment
            <input type="text" name="rvRsts[0].clCmt09" class="i_input_300 clCmt09 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    
    <!-- 201907 기술관점 공정안전자료 변경유무  -->
    <!-- 기타가  clCmt10 이라 공정안전자료 코드는 clCmt11로 세팅-->
    <tr style="display:none">
        <th class="dep2" scope="row">공정안전자료 변경</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[0].clCd11" class="i_radio clCd11" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[0].clCd11" class="i_radio clCd11" value="2" disabled="disabled"/>수행필요
            <a href="images/Matrix.xls"><img id="logo" src="images/btn_ChangeControlMatrix.png"></a>            
            </div>
        </td>        
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[0].clCmt10" class="i_input_full clCmt10" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="tech" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin techFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- space non 5 -->
<hr class="divider_none_5 check" style="display:none">
<!-- 
<table class="table_row check" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="8" class="dep1" scope="row">검사 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="checkText"></span>
              <input type="hidden" class="checkValue" name="jobs[1].chrgEmpNo" />
              <input type="hidden" name="jobs[1].chrgrClCd" value="Z02H2"/>
              <input type="hidden" name="rvRsts[1].chrgrClCd" value="Z02H2"/>
              <input type="hidden" class="checkDutyText" name="jobs[1].thdayPos" />
              <input type="hidden" class="checkTeamText" name="jobs[1].thdayTeam" />
              <input type="hidden" class="checkTeamValue" name="jobs[1].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="check" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="checkDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">설비 수명</th>
        <td colspan="3">
            <div class="form_group">
            <input type="hidden" name="rvRsts[1].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">설비 신뢰성</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">검사주기</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">부식/침식 영향</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[1].clCmt15" class="i_input_full clCmt15" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[9].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="check" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin checkFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>
-->


<table class="table_row check" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="7%">
    <col width="8%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="19" class="dep1" scope="row">검사 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th colspan="2" class="dep2" scope="row">검토자</th>
        <td>
              <span class="checkText"></span>
              <input type="hidden" class="checkValue" name="jobs[1].chrgEmpNo" />
              <input type="hidden" name="jobs[1].chrgrClCd" value="Z02H2"/>
              <input type="hidden" name="rvRsts[1].chrgrClCd" value="Z02H2"/>
              <input type="hidden" class="checkDutyText" name="jobs[1].thdayPos" />
              <input type="hidden" class="checkTeamText" name="jobs[1].thdayTeam" />
              <input type="hidden" class="checkTeamValue" name="jobs[1].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="check" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="checkDate"></span></td>
    </tr>
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">설비 수명</th>
        <td colspan="3">
            <div class="form_group">
            <input type="hidden" name="rvRsts[1].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd01" class="i_radio clCd01" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">설비 신뢰성</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd02" class="i_radio clCd02" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">검사주기</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd03" class="i_radio clCd03" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">부식/침식 영향</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[1].clCd04" class="i_radio clCd04" value="3" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
    	<!-- 2025-04-28 ijs 체크항목 추가 -->
	  
        <th rowspan="2" class="dep2" scope="row">고정장치</th>
        <th class="dep2" scope="row">열처리</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd05" class="i_radio clCd05 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd05" class="i_radio clCd05 comment" value="2" disabled="disabled"/>수행필요
	            <span style="display: inline-block; width: 30px;"></span><input type="text" name="rvRsts[1].clCmt05" class="i_input_300 clCmt05 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">Special</br>Service</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd06" class="i_radio clCd06 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd06" class="i_radio clCd06 comment" value="2" disabled="disabled"/>해당
	            <span style="display: inline-block; width: 53px;"></span><input type="text" name="rvRsts[1].clCmt06" class="i_input_300 clCmt06 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th rowspan="2" class="dep2" scope="row">회전</th>
        <th class="dep2" scope="row">열처리</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd07" class="i_radio clCd07 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd07" class="i_radio clCd07 comment" value="2" disabled="disabled"/>수행필요
	            <span style="display: inline-block; width: 30px;"></span><input type="text" name="rvRsts[1].clCmt07" class="i_input_300 clCmt07 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">Special</br>Service</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd08" class="i_radio clCd08 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd08" class="i_radio clCd08 comment" value="2" disabled="disabled"/>해당
	            <span style="display: inline-block; width: 53px;"></span><input type="text" name="rvRsts[1].clCmt08" class="i_input_300 clCmt08 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th rowspan="2" class="dep2" scope="row">배관</th>
        <th class="dep2" scope="row">열처리</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd09" class="i_radio clCd09 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd09" class="i_radio clCd09 comment" value="2" disabled="disabled"/>수행필요
	            <span style="display: inline-block; width: 30px;"></span><input type="text" name="rvRsts[1].clCmt09" class="i_input_300 clCmt09 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">Special</br>Service</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd10" class="i_radio clCd10 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd10" class="i_radio clCd10 comment" value="2" disabled="disabled"/>해당
	            <span style="display: inline-block; width: 53px;"></span><input type="text" name="rvRsts[1].clCmt10" class="i_input_300 clCmt10 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th rowspan="2" class="dep2" scope="row">전기</th>
        <th class="dep2" scope="row">열처리</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd11" class="i_radio clCd11 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd11" class="i_radio clCd11 comment" value="2" disabled="disabled"/>수행필요
	            <span style="display: inline-block; width: 30px;"></span><input type="text" name="rvRsts[1].clCmt11" class="i_input_300 clCmt11 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">Special</br>Service</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd12" class="i_radio clCd12 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd12" class="i_radio clCd12 comment" value="2" disabled="disabled"/>해당
	            <span style="display: inline-block; width: 53px;"></span><input type="text" name="rvRsts[1].clCmt12" class="i_input_300 clCmt12 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th rowspan="2" class="dep2" scope="row">계기</th>
        <th class="dep2" scope="row">열처리</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd13" class="i_radio clCd13 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd13" class="i_radio clCd13 comment" value="2" disabled="disabled"/>수행필요
	            <span style="display: inline-block; width: 30px;"></span><input type="text" name="rvRsts[1].clCmt13" class="i_input_300 clCmt13 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">Special</br>Service</th>
        <td colspan="3">
            <div class="form_group">
	            <input type="radio" name="rvRsts[1].clCd14" class="i_radio clCd14 comment" value="1" disabled="disabled"/>해당없음
	            <input type="radio" name="rvRsts[1].clCd14" class="i_radio clCd14 comment" value="2" disabled="disabled"/>해당
	            <span style="display: inline-block; width: 53px;"></span><input type="text" name="rvRsts[1].clCmt14" class="i_input_300 clCmt14 commentForm" value="" disabled="disabled" style="display:none; width:70%;" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
    	<td colspan=5  scope="row" class="dep2">
			<div style="width:100%; padding: 5px 15px; text-align:left; font-size:14px; color:red; font-weight:600;">
				* Special Service : wet H2S Service/Amine & Caustic Service/Hydrogen Service 등
			</div>
    	</td>
    </tr>   
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[1].clCmt15" class="i_input_full clCmt15" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th colspan="2" class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[9].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th colspan="2" scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="check" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin checkFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- space non 5 -->
<hr class="divider_none_5 drive" style="display:none">

<table class="table_row drive" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="9" class="dep1" scope="row">운전 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="driveText"></span>
              <input type="hidden" class="driveValue" name="jobs[2].chrgEmpNo" />
              <input type="hidden" name="jobs[2].chrgrClCd" value="Z02H3"/>
              <input type="hidden" name="rvRsts[2].chrgrClCd" value="Z02H3"/>
              <input type="hidden" class="driveDutyText" name="jobs[2].thdayPos" />
              <input type="hidden" class="driveTeamText" name="jobs[2].thdayTeam" />
              <input type="hidden" class="driveTeamValue" name="jobs[2].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="drive" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="driveDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">운전 편의성</th>
        <td colspan="3"><input type="hidden" name="rvRsts[2].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <div class="form_group">
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio clCd01 comment" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio clCd01 comment" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[2].clCd01" class="i_radio clCd01 comment" value="3" disabled="disabled"/>해당없음
            <input type="text" name="rvRsts[2].clCmt09" class="i_input_300 clCmt01 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">운전 위험성</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[2].clCd02" class="i_radio clCd02 comment" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[2].clCd02" class="i_radio clCd02 comment" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[2].clCd02" class="i_radio clCd02 comment" value="3" disabled="disabled"/>해당없음
            <input type="text" name="rvRsts[2].clCmt09" class="i_input_300 clCmt02 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">운전 탄력성</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[2].clCd03" class="i_radio clCd03 comment" value="1" disabled="disabled"/>증가
            <input type="radio" name="rvRsts[2].clCd03" class="i_radio clCd03 comment" value="2" disabled="disabled"/>감소
            <input type="radio" name="rvRsts[2].clCd03" class="i_radio clCd03 comment" value="3" disabled="disabled"/>해당없음
            <input type="text" name="rvRsts[2].clCmt09" class="i_input_300 clCmt03 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기본 설계/도면 확인</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[2].clCd04" class="i_radio clCd04 comment" value="1" disabled="disabled"/>문제없음
            <input type="radio" name="rvRsts[2].clCd04" class="i_radio clCd04 comment" value="2" disabled="disabled"/>문제있음
            <input type="text" name="rvRsts[2].clCmt09" class="i_input_300 clCmt04 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">운전 매뉴얼 반영</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[2].clCd05" class="i_radio clCd05 clmanual" value="1" disabled="disabled"/>반영필요
            <input type="radio" name="rvRsts[2].clCd05" class="i_radio clCd05 clmanual" value="2" disabled="disabled"/>해당없음
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[2].clCmt06" class="i_input_full clCmt06" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[2].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="drive" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin driveFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>


<!-- space non 5 -->
<hr class="divider_none_5 equip" style="display:none">

<table class="table_row equip" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="11" class="dep1" scope="row">환경 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="equipText"></span>
              <input type="hidden" class="equipValue" name="jobs[3].chrgEmpNo" />
              <input type="hidden" name="jobs[3].chrgrClCd" value="Z02H4"/>
              <input type="hidden" name="rvRsts[3].chrgrClCd" value="Z02H4"/>
              <input type="hidden" class="equipDutyText" name="jobs[3].thdayPos" />
              <input type="hidden" class="equipTeamText" name="jobs[3].thdayTeam" />
              <input type="hidden" class="equipTeamValue" name="jobs[3].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="equip" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="equipDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">토양</th>
        <td colspan="3"><input type="hidden" name="rvRsts[3].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd01" class="i_radio clCd01 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd01" class="i_radio clCd01 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt01" class="i_input_300 clCmt01 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">대기</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd02" class="i_radio clCd02 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd02" class="i_radio clCd02 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt02" class="i_input_300 clCmt02 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">수질</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd03" class="i_radio clCd03 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd03" class="i_radio clCd03 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt03" class="i_input_300 clCmt03 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">폐기물</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd04" class="i_radio clCd04 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd04" class="i_radio clCd04 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt04" class="i_input_300 clCmt04 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">환경영향평가</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd05" class="i_radio clCd05 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd05" class="i_radio clCd05 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt05" class="i_input_300 clCmt05 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">해양</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd06" class="i_radio clCd06 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd06" class="i_radio clCd06 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt06" class="i_input_300 clCmt06 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">유독물</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd07" class="i_radio clCd07 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd07" class="i_radio clCd07 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt07" class="i_input_300 clCmt07 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <!--tr style="display:none">
        <th class="dep2" scope="row">온실가스</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[3].clCd09" class="i_radio clCd09 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[3].clCd09" class="i_radio clCd09 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[3].clCmt09" class="i_input_300 clCmt09 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr-->
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[3].clCmt08" class="i_input_full clCmt08" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[3].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="equip" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin equipFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>


<!-- space non 5 -->
<hr class="divider_none_5 safety" style="display:none">

<table class="table_row safety" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="8" class="dep1" scope="row">안전 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="safetyText"></span>
              <input type="hidden" class="safetyValue" name="jobs[4].chrgEmpNo" />
              <input type="hidden" name="jobs[4].chrgrClCd" value="Z02H5"/>
              <input type="hidden" name="rvRsts[4].chrgrClCd" value="Z02H5"/>
              <input type="hidden" class="safetyDutyText" name="jobs[4].thdayPos" />
              <input type="hidden" class="safetyTeamText" name="jobs[4].thdayTeam" />
              <input type="hidden" class="safetyTeamValue" name="jobs[4].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="safety" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="safetyDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">산업안전보건법</th>
        <td colspan="3"><input type="hidden" name="rvRsts[4].itemCd" class="vam clCmt10" value="JOBS" disabled="disabled"/>
            <div class="form_group">
            <input type="radio" name="rvRsts[4].clCd01" class="i_radio clCd01 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[4].clCd01" class="i_radio clCd01 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[4].clCmt01" class="i_input_300 clCmt01 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">고압가스관계법</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[4].clCd02" class="i_radio clCd02 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[4].clCd02" class="i_radio clCd02 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[4].clCmt02" class="i_input_300 clCmt02 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">소방관계법</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[4].clCd03" class="i_radio clCd03 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[4].clCd03" class="i_radio clCd03 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[4].clCmt03" class="i_input_300 clCmt03 commentForm" value="" disabled="disabled" style="display:none" data-role="2"/>
            </div>
        </td>
    </tr>
    <!-- 201907 안전 관점 공정안전자료 변경유무 추가 -->
    <tr style="display:none">
       <th class="dep2" scope="row">공정안전자료 변경</th>
       <td colspan="3">
           <div class="form_group">           
           <input type="radio" name="rvRsts[4].clCd05" class="i_radio clCd05" value="1" disabled="disabled"/>해당없음
           <input type="radio" name="rvRsts[4].clCd05" class="i_radio clCd05" value="2" disabled="disabled"/>수행필요   
           <a href="images/Matrix.xls"><img id="logo" src="images/btn_ChangeControlMatrix.png"></a>        
           </div>
       </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[4].clCmt04" class="i_input_full clCmt04" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[4].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="safety" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin safetyFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- space non 5 -->
<hr class="divider_none_5 account" style="display:none">

<table class="table_row account" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="6" class="dep1" scope="row">회계 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="accountText"></span>
              <input type="hidden" class="accountValue" name="jobs[5].chrgEmpNo" />
              <input type="hidden" name="jobs[5].chrgrClCd" value="Z02H6"/>
              <input type="hidden" name="rvRsts[5].chrgrClCd" value="Z02H6"/>
              <input type="hidden" class="accountDutyText" name="jobs[5].thdayPos" />
              <input type="hidden" class="accountTeamText" name="jobs[5].thdayTeam" />
              <input type="hidden" class="accountTeamValue" name="jobs[5].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="account" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="accountDate"></span></td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">취등록세 검토</th>
        <td colspan="3">
            <div class="form_group">
            <input type="hidden" name="rvRsts[5].itemCd" class="i_radio clCmt10 comment" value="JOBS" disabled="disabled"/>
            <input type="radio" name="rvRsts[5].clCd01" class="i_radio clCd01 comment" value="1" disabled="disabled"/>해당있음
            <input type="radio" name="rvRsts[5].clCd01" class="i_radio clCd01 comment" value="2" disabled="disabled"/>해당없음
            <input type="text" name="rvRsts[5].clCmt01" class="i_input_300 clCmt01 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
<!--     
    <tr style="display:none">
        <th class="dep2" scope="row">원천세 검토</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[5].clCd02" class="i_radio clCd02 comment" value="1" disabled="disabled"/>해당있음
            <input type="radio" name="rvRsts[5].clCd02" class="i_radio clCd02 comment" value="2" disabled="disabled"/>해당없음
            <input type="text" name="rvRsts[5].clCmt02" class="i_input_300 clCmt02 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
 -->    
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[5].clCmt03" class="i_input_full clCmt03" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[5].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="account" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin accountFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- space non 5 -->
<hr class="divider_none_5 energy" style="display:none">

<table class="table_row energy" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="6" class="dep1" scope="row">에너지 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="energyText"></span>
              <input type="hidden" class="energyValue" name="jobs[6].chrgEmpNo" />
              <input type="hidden" name="jobs[6].chrgrClCd" value="Z02H7"/>
              <input type="hidden" name="rvRsts[6].chrgrClCd" value="Z02H7"/>
              <input type="hidden" class="energyDutyText" name="jobs[6].thdayPos" />
              <input type="hidden" class="energyTeamText" name="jobs[6].thdayTeam" />
              <input type="hidden" class="energyTeamValue" name="jobs[6].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="energy" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="energyDate"></span></td>
    </tr>
    <tr style="display:none">
           <th class="dep2" scope="row">에너지 절감량 검토</th>
           <td colspan="3">
               <div class="form_group">
               <input type="hidden" name="rvRsts[6].itemCd" class="i_radio clCmt10" value="JOBS" disabled="disabled"/>
               <input type="radio" name="rvRsts[6].clCd01" class="i_radio clCd01 comment" value="1" disabled="disabled"/>해당있음
               <input type="radio" name="rvRsts[6].clCd01" class="i_radio clCd01 comment" value="2" disabled="disabled"/>해당없음
               <input type="text" name="rvRsts[6].clCmt01" class="i_input_300 clCmt01 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
               </div>
           </td>
       </tr>
       <tr style="display:none">
           <th class="dep2" scope="row">설계, 구매시 생애주기비용 검토</th>
           <td colspan="3">
               <div class="form_group">
               <input type="radio" name="rvRsts[6].clCd02" class="i_radio clCd02 comment" value="1" disabled="disabled"/>해당있음
               <input type="radio" name="rvRsts[6].clCd02" class="i_radio clCd02 comment" value="2" disabled="disabled"/>해당없음
               <input type="text" name="rvRsts[6].clCmt02" class="i_input_300 clCmt02 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
               </div>
           </td>
       </tr>
       <tr style="display:none">
        <th class="dep2" scope="row">온실가스</th>
        <td colspan="3">
            <div class="form_group">
            <input type="radio" name="rvRsts[6].clCd03" class="i_radio clCd03 comment" value="1" disabled="disabled"/>해당없음
            <input type="radio" name="rvRsts[6].clCd03" class="i_radio clCd03 comment" value="2" disabled="disabled"/>수행필요
            <input type="text" name="rvRsts[6].clCmt03" class="i_input_300 clCmt03 commentForm" value="" disabled="disabled" style="display:none" data-role="1"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[6].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="energy" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin energyFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- 설계관점(design) -->
 <hr class="divider_none_5" style="display:none"> 

 <table class="table_row" border="1" cellspacing="0" style="display:none"> 

    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="6" class="dep1" scope="row">설계 관점 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="designText"></span>
              <input type="hidden" class="designValue" name="jobs[7].chrgEmpNo" />
              <input type="hidden" name="jobs[7].chrgrClCd" value="Z02H8"/>
              <input type="hidden" name="rvRsts[7].chrgrClCd" value="Z02H8"/>
              <input type="hidden" class="designDutyText" name="jobs[7].thdayPos" />
              <input type="hidden" class="designTeamText" name="jobs[7].thdayTeam" />
              <input type="hidden" class="designTeamValue" name="jobs[7].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="design" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="designDate"></span></td>
    </tr>
    <!-- 설계관점 공정안전자료 변경유무 -->
    <tr style="display:none">
       <th class="dep2" scope="row">공정안전자료 변경</th>
       <td colspan="3">
           <div class="form_group">
           <input type="hidden" name="rvRsts[7].itemCd" class="i_radio clCd01" value="JOBS" disabled="disabled"/>
           <input type="radio" name="rvRsts[7].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>해당없음
           <input type="radio" name="rvRsts[7].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>수행필요
           <a href="images/Matrix.xls"><img id="logo" src="images/btn_ChangeControlMatrix.png"></a>
           </div>
       </td>
    </tr>     
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[7].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="design" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin designFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- 산업안전보건법 -->
<hr class="divider_none_5 industrial" style="display:none">

<table class="table_row industrial" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="6" class="dep1" scope="row">산업안전보건법<img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="industrialText"></span>
              <input type="hidden" class="industrialValue" name="jobs[8].chrgEmpNo" />
              <input type="hidden" name="jobs[8].chrgrClCd" value="Z02H9"/>
              <input type="hidden" name="rvRsts[8].chrgrClCd" value="Z02H9"/>
              <input type="hidden" class="industrialDutyText" name="jobs[8].thdayPos" />
              <input type="hidden" class="industrialTeamText" name="jobs[8].thdayTeam" />
              <input type="hidden" class="industrialTeamValue" name="jobs[8].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="industrial" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="industrialDate"></span></td>
    </tr>    
    <tr style="display:none">
       <th class="dep2" scope="row">산업안전보건법</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[8].itemCd" class="i_radio clCd01" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[8].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[8].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>수행필요	                      
           </div>
       </td>
    </tr> 
    <tr style="display:none">
       <th class="dep2" scope="row">공정안전자료 변경</th>
       <td colspan="3">
           <div class="form_group">           
           <input type="radio" name="rvRsts[8].clCd02" class="i_radio clCd02" value="1" disabled="disabled"/>해당없음
           <input type="radio" name="rvRsts[8].clCd02" class="i_radio clCd02" value="2" disabled="disabled"/>수행필요
           <a href="images/Matrix.xls"><img id="logo" src="images/btn_ChangeControlMatrix.png"></a>
           </div>
       </td>
    </tr>     
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[8].clCmt03" class="i_input_full clCmt03" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[8].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="industrial" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin industrialFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- 고압가스관계법 -->
<hr class="divider_none_5 highPressure" style="display:none">

<table class="table_row highPressure" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="9" class="dep1" scope="row">고압가스관계법 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="highPressureText"></span>
              <input type="hidden" class="highPressureValue" name="jobs[9].chrgEmpNo" />
              <input type="hidden" name="jobs[9].chrgrClCd" value="Z02H10"/>
              <input type="hidden" name="rvRsts[9].chrgrClCd" value="Z02H10"/>
              <input type="hidden" class="highPressureDutyText" name="jobs[9].thdayPos" />
              <input type="hidden" class="highPressureTeamText" name="jobs[9].thdayTeam" />
              <input type="hidden" class="highPressureTeamValue" name="jobs[9].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="highPressure" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="highPressureDate"></span></td>
    </tr>
    <!-- 설계관점 공정안전자료 변경유무 -->
    <tr style="display:none">
       <th class="dep2" scope="row">고압가스관계법</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[9].itemCd" class="i_radio clCd01" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[9].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[9].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>수행필요
           </div>
       </td>
    </tr>
    <tr style="display:none">
       <th class="dep2" scope="row">HDO 공유 필요 여부</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[9].itemCd" class="i_radio clCd02" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[9].clCd02" class="i_radio clCd02" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[9].clCd02" class="i_radio clCd02" value="2" disabled="disabled"/>확인필요
           </div>
       </td>
    </tr>
    <tr style="display:none">
       <th class="dep2" scope="row">HDC 공유 필요 여부</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[9].itemCd" class="i_radio clCd03" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[9].clCd03" class="i_radio clCd03" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[9].clCd03" class="i_radio clCd03" value="2" disabled="disabled"/>확인필요
           </div>
       </td>
    </tr>
    <!-- 2025-01-24 ijs 허세욱책임 요청으로 주석처리함
    <tr style="display:none !important">
       <th  scope="row">HCP 공유 필요 여부</th>
       <td colspan="3">
           <div >
	           <input type="hidden" name="rvRsts[9].itemCd" class="i_radio clCd04" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[9].clCd04" class="i_radio clCd04" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[9].clCd04" class="i_radio clCd04" value="2" disabled="disabled"/>확인필요
           </div>
       </td>
    </tr>
     -->
    <tr style="display:none">
       <th class="dep2" scope="row">HSB 공유 필요 여부</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[9].itemCd" class="i_radio clCd05" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[9].clCd05" class="i_radio clCd05" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[9].clCd05" class="i_radio clCd05" value="2" disabled="disabled"/>확인필요
           </div>
       </td>
    </tr>     
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[9].clCmt02" class="i_input_full clCmt02" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[9].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="highPressure" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin highPressureFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>

<!-- 소방관계법 -->
<hr class="divider_none_5 fire" style="display:none">

<table class="table_row fire" border="1" cellspacing="0" style="display:none">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="40%">
    <col width="15%">
    <col width="15%">
    </colgroup>
    <tbody>
    <tr class="cursor fold">
        <th rowspan="5" class="dep1" scope="row">소방관계법 <img src="images/icon_s_dropdown.png" class="vam cursor"></th>
        <th class="dep2" scope="row">검토자</th>
        <td>
              <span class="fireText"></span>
              <input type="hidden" class="fireValue" name="jobs[10].chrgEmpNo" />
              <input type="hidden" name="jobs[10].chrgrClCd" value="Z02H11"/>
              <input type="hidden" name="rvRsts[10].chrgrClCd" value="Z02H11"/>
              <input type="hidden" class="fireDutyText" name="jobs[10].thdayPos" />
              <input type="hidden" class="fireTeamText" name="jobs[10].thdayTeam" />
              <input type="hidden" class="fireTeamValue" name="jobs[10].chrgTeamNo" />
              <img src="images/icon_search.png" class="vam cursor empChange" data-role="fire" style="display:none"/>
        </td>
        <th class="dep2" scope="row">검토일</th>
        <td><span class="fireDate"></span></td>
    </tr>
    <!-- 설계관점 공정안전자료 변경유무 -->
    <tr style="display:none">
       <th class="dep2" scope="row">소방관계법</th>
       <td colspan="3">
           <div class="form_group">
	           <input type="hidden" name="rvRsts[10].itemCd" class="i_radio clCd01" value="JOBS" disabled="disabled"/>
	           <input type="radio" name="rvRsts[10].clCd01" class="i_radio clCd01" value="1" disabled="disabled"/>해당없음
	           <input type="radio" name="rvRsts[10].clCd01" class="i_radio clCd01" value="2" disabled="disabled"/>수행필요
           </div>
       </td>
    </tr> 
    <tr style="display:none">
        <th class="dep2" scope="row">기타</th>
        <td colspan="3">
            <div class="form_group">
            <input type="text" name="rvRsts[10].clCmt02" class="i_input_full clCmt02" value="" disabled="disabled"/>
            </div>
        </td>
    </tr>    
    <tr style="display:none">
        <th class="dep2" scope="row">종합검토의견</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='DAA'}">
            <textarea name="rvRsts[10].totRvSugg" class="i_text_full totRvSugg" rows="" cols="" disabled="disabled"></textarea>
            </c:if>
            <c:if test="${modeClass!='DAA'}">
                <pre class="totRvSuggText"><c:out value="${techReview.totRvSugg1}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr style="display:none">
        <th scope="row" class="dep2">첨부파일
        <img src="images/icon_s_plus.png" border="0" class="cursor addJobFile" data-role="fire" style="display:none"/></th>
        <td colspan="3">
          <div class="form_group">
            <table class="table_default_margin fireFileList" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
            </table>
          </div>
        </td>
    </tr>
    </tbody>
</table>


<!-- space non 10 -->
<hr class="divider_none_10" />

<!-- <table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="35%">
    <col width="15%">
    <col width="35%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row">HAZOP</th>
        <td>
            <div class="form_group">
            <input type="radio" class="hazopActYn" name="hazopActYn" value="Y" <c:if test="${jobsReview.hazopActYn=='Y'}">checked </c:if> <c:if test="${modeClass!='DAA' || subModeClass!='S-ALA'}">disabled="disabled"</c:if>> 필요
            <input type="radio" class="hazopActYn" name="hazopActYn" value="N" <c:if test="${jobsReview.hazopActYn=='N'}">checked </c:if> <c:if test="${modeClass!='DAA' || subModeClass!='S-ALA'}">disabled="disabled"</c:if>> 불필요
            <c:if test="${jobsReview.hazopActYn=='Y'}"><img src="images/btn_inWrite.png" class="vam cursor hazop"/></c:if>
            </div>
        </td>
        <th scope="row">PORC</th>
        <td>
            <div class="form_group">
            <input type="radio" class="porcActYn" name="porcActYn" value="Y" <c:if test="${jobsReview.porcActYn=='Y'}">checked </c:if> <c:if test="${modeClass!='DAA' || subModeClass!='S-ALA'}">disabled="disabled"</c:if>> 필요
            <input type="radio" class="porcActYn" name="porcActYn" value="N" <c:if test="${jobsReview.porcActYn=='N'}">checked </c:if> <c:if test="${modeClass!='DAA' || subModeClass!='S-ALA'}">disabled="disabled"</c:if>> 불필요
            <c:if test="${jobsReview.porcActYn=='Y'}">
                <img src="images/btn_inAppointChairman.png" class="vam cursor porcSelect"/>
                <img src="images/btn_inWrite.png" class="vam cursor porcWrite"/>
            </c:if>
            </div>
        </td>
    </tr>
    </tbody>
</table> -->
</form>

  <table id="jobFileTemp" class="table_row" border="1" cellspacing="0" style="display:none">
      <tr>
	      <td>
	        <select class="fileDiv fileCd" name="mrAtchFiles[0].fileCd"></select>
	        <input type="file" class="jobFileForm fileForm" multiple>
	        <input type="hidden" class="realPath i_input_300">
	        <input type="text" class="i_input_300 multipartFile fileNm" name="mrAtchFiles[0].fileNm">
	        <img src="images/btn_search.png" class="inputJobFile cursor"/>
	        <img id="deleteFile" class="cursor removeFile" src="images/icon_minus.png" />
	        <input type="hidden" class="i_input_200 insFlag" name="mrAtchFiles[0].inserted" value="true"/>
	        <input type="hidden" class="drawMngNo" name="mrAtchFiles[0].drawMngNo" value=""/>
	      </td>
      </tr>
  </table>
</div>

<script>
var jobEmpDriveYn = "";
$(window).ready(function() {

    "<c:forEach var='jobFile' items='${jobsReview.mrJobAtchFiles}'>";
    $.jobFileAppender({fileStepCd : "${jobFile.fileStepCd}", fileCdNm : "${jobFile.fileCdNm}" , fileNm : "${jobFile.fileNm}", mrAtchFileNo : "${jobFile.mrAtchFileNo}" ,drawMngNo : "${jobFile.drawMngNo}"});
    "</c:forEach>";

	//직무검토자 셋팅
    "<c:forEach var='job' items='${jobsReview.appLine}'>";
    jobClass="";
    //console.log('${job.chrgrClCd}');
    switch ("${job.chrgrClCd}") {
    case "Z02H1":
        jobClass = "tech";
        break;
    case "Z02H2":
        jobClass = "check";
        break;
    case "Z02H3":
        jobClass = "drive";
        break;
    case "Z02H4":
        jobClass = "equip";
        break;
    case "Z02H5":
        jobClass = "safety";
        break;
    case "Z02H6":
        jobClass = "account";
        break;
    case "Z02H7":
        jobClass = "energy";
        break;
    case "Z02H8":
        jobClass = "design";
        break;
    case "Z02H9":
        jobClass = "industrial";
        break;
    case "Z02H10":
        jobClass = "highPressure";
        break;
    case "Z02H11":
        jobClass = "fire";
        break;
    }

    if(jobClass == "drive" && "${jobsReview.loginEmpNo}"=="${job.chrgEmpNo}"){
   		jobEmpDriveYn = "Y";
    }
    //현재 로그인 되어 있는 직무검토자
    if($("."+jobClass).css("display")=="none") {
        $("."+jobClass).show();
    }

    if("${subModeClass}"=="IAD") {
        $("."+jobClass).children("tbody").children("tr:eq(0)").children("td:eq(0)").children(".empChange").show();
    }

    if("${modeClass}"=="DAA" && "${jobsReview.loginEmpNo}"=="${job.chrgEmpNo}") {
        $("."+jobClass).children("tbody").children("tr:gt(0)").each(function (){

        	$(".save").show();
            $(this).show();
            $(this).find("input").each(function (){
                $(this).attr("disabled",false);
            });

            $(this).find("textarea").each(function (){
                $(this).attr("disabled",false);
            });
        });
        
        $("."+jobClass).children("tbody").children("tr:eq(0)").children("td:eq(0)").children(".empChange").show();
        $("."+jobClass).find(".addJobFile").show();
        $("."+jobClass).find(".removeDownFile").show();
    }

    $.appLineSet({className:jobClass, dutyText:"${job.thdayPos}",
        text:"${job.chrgEmpName}", value:"${job.chrgEmpNo}",
        teamText:"${job.thdayTeam}" , teamValue:"${job.chrgTeamNo}",
        endDate:"<fmt:formatDate value='${job.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
        date:"<fmt:formatDate value='${job.lastChgDt}' pattern='yyyy-MM-dd' />"});


        "<c:forEach var='rvRst' items='${job.rvRsts}'>";
        if("${job.mrReqProcStepDetNo}"=="${rvRst.mrReqProcStepDetNo}") {
        	   "<c:forEach var='i' begin='1' end='15'>";
        	   "<c:if test='${i<10}'><c:set var='varSatus'>0${i}</c:set></c:if>";
        	   "<c:if test='${i>9}'><c:set var='varSatus'>${i}</c:set></c:if>";
        	   "<c:set var='varClCdName'>clCd${varSatus}</c:set>";
        	   "<c:set var='varClCmtName'>clCmt${varSatus}</c:set>";
        	   $("."+jobClass).find(".clCd"+"${varSatus}").each(function () {
        		   if("${rvRst[varClCdName]}"==$(this).val()) {
        			   $(this).attr("checked", true);
        		   }
        	   });

        	   $("."+jobClass).find(".clCmt"+"${varSatus}").each(function () {
        		   if("${rvRst[varClCmtName]}"!=""){
        			   $(this).show();
        		   }
                	   $(this).val("${rvRst[varClCmtName]}");
               });

               $("."+jobClass).find(".totRvSugg").val("${rvRst.totRvSugg}");
               $("."+jobClass).find(".totRvSuggText").html("${rvRst.totRvSugg}");
               $("."+jobClass+"Date").html("<fmt:formatDate value='${rvRst.lastChgDt}' pattern='yyyy-MM-dd' />");
                "</c:forEach>";
        }
        "</c:forEach>";

    "</c:forEach>";

    $(".hazopActYn").click(function(){
        if($(this).val()=="N") {
        	$(".hazop").hide();
        }
    });

    $(".porcActYn").click(function(){
        if($(this).val()=="N") {
            $(".porcSelect").hide();
            $(".porcWrite").hide();
        }
    });

    $(".fold").click(function(event) {

        var table = $(this).parent("tbody").parent("table");
        var isShow = false;
        if(table.children("tbody").children("tr:gt(0)").css("display")=="none") {
            isShow = true;
        } else {
            isShow = false;
        }
        table.children("tbody").children("tr:gt(0)").each(function () {
            if(isShow) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
        event.stopPropagation();
    });

    $(".save").click(function () {
    	if($.jobsValidation())
        if(confirm("저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${saveURL}").submit();
        }
    });
    
   
    $(".skip").click(function () {
        if(confirm("보류 처리시 해당 MR은 진행이 불가합니다. 계속 진행을 할까요?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrJobsReviewSkip.do").submit();
        }
    });

    $(".appReq").click(function () {
        if(confirm("승인요청 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrCompleteAppReq.do").submit();
        }
    });

    $(".app").click(function () {
        if(confirm("승인 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${appURL}").submit();
        }
    });


    $(".complete").click(function () {
    	var msg = null;
    	if("${jobsReview.isJobsComplete}"=="true" && "${jobsReview.isPorcDisagree}" =="false"){
    		msg = "완료 하시겠습니까?";
    	} else if("${jobsReview.isJobsComplete}"=="true" && "${jobsReview.isPorcDisagree}" =="true"){
    		msg = "PORC 규정에 따라 위원님중‘반대’가 있으므로 본 MR이 취소됩니다.";
    	}

    	if(msg!=null) {
          if(confirm(msg)) {
              $('#pageLoadingWrap').show();
              $("form").attr("action", "mrJobsReviewComplete.do").submit();
          }
    	}
    });



    $(".return").click(function () {
        if(confirm("반려 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrCompleteReturn.do").submit();
        }
    });

    $(".cancel").click(function () {
        if(confirm("사업취소 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrRqRegisterCancel.do").submit();
        }
    });

    $(".costModifyReq").click(function () {
    	if(confirm("투자비재산정요청 하시겠습니까?")) {
    		  document.location.href = "techInvestCostModifyReq.do?&mrReqNo="+$("#mrReqNo").val();
    	}
    });

    $(".costModify").click(function () {
        $.popup({url:"popup/techInvestCostModify.do?&mrReqNo="+$("#mrReqNo").val(), scroll:"yes", width:"880", height:"700",popupName:"techInvestCostModify"});
    });


    $(".porcSelect").click(function () {
            $.popup({url:"popup/mrJobsPorc.do?&mrReqNo="+$("#mrReqNo").val(), width:"880", height:"500"});
    });

    $(".hazop").click(function () {
        $.popup({url:"popup/mrHazopStudy.do?&mrReqNo="+$("#mrReqNo").val(), width:"880", height:"500"});
    });

    $(".porcWrite").click(function () {
        $.popup({url:"popup/mrJobsPorcWrite.do?&mrReqNo="+$("#mrReqNo").val(), width:"880", height:"500"});
    });


    $(".empChange").click(function (event) {
    	
    	console.log('----------');
    	console.log($(this).attr("data-role"));
    	var chrgrClCd = $(this).attr("data-role");
    	
    	switch (chrgrClCd) {
        case "tech":
        	chrgrClCd = "Z02H1";		// Z02H1
            break;
        case "check":
        	chrgrClCd = "Z02H2";		// Z02H2
            break;
        case "drive":
        	chrgrClCd = "Z02H3";		// Z02H3
            break;
        case "equip":
        	chrgrClCd = "Z02H4";		// Z02H4
            break;
        case "safety":
        	chrgrClCd = "Z02H5";		// Z02H5
            break;
        case "account":
        	chrgrClCd = "Z02H6";		// Z02H6
            break;
        case "energy":
        	chrgrClCd = "Z02H7";		// Z02H7 
            break;
        case "design":
        	chrgrClCd = "Z02H8";		// Z02H8
            break;
        case "industrial":
        	chrgrClCd = "Z02H9";		// Z02H9
            break;
        case "highPressure":
        	chrgrClCd = "Z02H10";		// Z02H10
            break;
        case "fire":
        	chrgrClCd = "Z02H11";		// Z02H11
            break;
        }
    	
    	console.log('----------');
    	$.popup({url:"popup/jobEmpChange.do?&mrReqNo="+$("#mrReqNo").val() + "&chrgrClCd=" + chrgrClCd + "&empNo="+$("."+$(this).attr("data-role")+"Value").val(), width:"880", height:"500"});
    	event.stopPropagation();
    });

    $(".comment").click(function () {
    	if($(this).val()==$(this).parent("div").children(".commentForm").attr("data-role")) {
    		$(this).parent("div").children(".commentForm").show();
    	} else {
    		$(this).parent("div").children(".commentForm").val("");
    		$(this).parent("div").children(".commentForm").hide();
    	}


    });

	//202009var fileCnt = $(".multipartFile").length - 1;
	//가끔식 첨부파일이 추가가 안되는 이유 :첨부파일 갯수를 확인하여 fileCnt를 만들어 주는데  이미 전 작업으로 첨부파일이  추가가 되어 있으면  fileCnt(파일카운트)에 카운트가 더해진다	 
	//:해결하려면 기존에 추가되어있는 갯수를 빼주고 카운트를 해야 함 아니면 로직을 분리
    $(".addJobFile").click(function () {
        var fileCnt = $(".multipartFile").length - 1;
        
        var multCnt  = $("#multCnt").val();	//multiCt 신규추가        
        if($.isNumeric(multCnt) == false){        	
        	multCnt = 0;
        }
        
		//202011 filecnt 재 설정, 상단에 추가되어 있는 첨부파일 개숫만큼 더해주기
        fileCnt =  $(".multipartFile").length - 1 + Number(multCnt);		

        var insertClass= $(this).attr("data-role");
        var fileTemp = $("#jobFileTemp").children("tbody").children("tr");
        var fileTempTd = fileTemp.children("td");
        fileTempTd.children(".multipartFile").attr("name","mrAtchFiles["+fileCnt+"].fileNm");
        fileTempTd.children(".fileCd").attr("name","mrAtchFiles["+fileCnt+"].fileCd");
        fileTempTd.children(".insFlag").attr("name","mrAtchFiles["+fileCnt+"].inserted");
        fileTempTd.children(".drawMngNo").attr("name","mrAtchFiles["+fileCnt+"].drawMngNo");
        fileTemp.clone().insertAfter($("."+insertClass+"FileList").children("tbody").children("tr:last"));

        var insertTd = $("."+insertClass+"FileList").children("tbody").children("tr:last").children("td");
        insertTd.children(".multipartFile").attr("id", "multipartFile");
        insertTd.children(".jobFileForm").attr("data-role", insertClass);
    });

});

$(document).on("click",".inputJobFile", function () {
    $(this).parent("td").find(".jobFileForm").click();
});

$(document).on("change",".jobFileForm", function () {
    var fileList = new Array();
    var fileName = this.value;
    var td = $(this).parent("td");

    var insertClass= $(this).attr("data-role");
    var fileTemp = $("#jobFileTemp").children("tbody").children("tr");
    var fileTr;
    var fileType;
    var fileCnt=0;
    var fileTempTd = fileTemp.children("td");
    fileList = fileName.split(", C:\\");

    if(fileList.length>1){
        fileType = td.children(".fileCd").val();
        td.parent("tr").remove();
        for( var i = 0; i<fileList.length; i++){
        	fileCnt = $(".multipartFile").length-1;
            fileTempTd.children(".multipartFile").attr("name","mrAtchFiles["+fileCnt+"].fileNm");
            fileTempTd.children(".fileCd").attr("name","mrAtchFiles["+fileCnt+"].fileCd");
            fileTempTd.children(".insFlag").attr("name","mrAtchFiles["+fileCnt+"].inserted");
            fileTempTd.children(".drawMngNo").attr("name","mrAtchFiles["+fileCnt+"].drawMngNo");
            fileTemp.clone().insertAfter($("."+insertClass+"FileList").children("tbody").children("tr:last"));

            fileTr = $("."+insertClass+"FileList").children("tbody").children("tr:last");
            fileTr.children("td").children(".realPath").val(i==0 ? fileList[i] : "C:\\"+fileList[i]);
            fileTr.children("td").children(".fileCd").val(fileType);
            fileTr.children("td").children(".multipartFile").val(fileList[i].substring(fileList[i].lastIndexOf("\\") + 1, fileList[i].length));

        }
    } else {
        td.find(".realPath").val(fileName);
        td.find(".multipartFile").val(fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length));
    }

    var insertTd = $("."+insertClass+"FileList").children("tbody").children("tr:last").children("td");
    insertTd.children(".multipartFile").attr("id", "multipartFile");
    insertTd.children(".jobFileForm").attr("data-role", insertClass);

});

//<br>IANN
//alert("wj costModeClass:" + "${costModeClass}");
if("${costModeClass}"!="IAFF" && "${costModeClass}"!="IFF") {
	$.popup({url:"popup/techInvestCostModify.do?&mrReqNo="+$("#mrReqNo").val(), scroll:"yes", width:"880", height:"700",popupName:"techInvestCostModify"});
}

$.jobsValidation = function (){
	var div;
	var count = 0;
	$("input").each(function(){

		if($(this).attr("type")==="radio" && $(this).attr("disabled")!="disabled"){
			div = $(this).parent("div");
			div.css("border-style","solid");
			div.css("border-color","#fffff");
			div.css("border-width","0px");
		    if($.type(div.children("input:checked").val())==="undefined" || div.children("input:checked").val()==""){
		    	div.focus();
		    	div.css("border-style","solid");
		    	div.css("border-color","#91ce44");
		    	div.css("border-width","2px");
		        alert(div.parent("td").parent("tr").children("th:eq(0)").html() +" 항목을 입력하세요.");
		         count++;
		    	 return false;
	        }
		}
	});

	//운전관점 운전메뉴얼 반영 필요시 파일유무 체크
	if(jobEmpDriveYn == "Y" && $(".drive .clCd05:checked").val()=="1"){
	 	var driveFileCnt = 0;
		$(".driveFileList > tbody> tr").each(function (){
			if(!$(this).is(":hidden")){
	    		var file = $(this).children("td").children(".fileNm").val();
	    		if(file != "" && file != undefined){
	    			driveFileCnt++;
	    		}
			} 
		});
		if(driveFileCnt < 1){
		    alert("운전 매뉴얼 반영 필요시 매뉴얼을 첨부하셔야 합니다.");
//		    return false;
		}
	} 
	if(count >0 ){
		return false;
	} else {
		return true;
	}

};

$.jobFileAppender = function (option){
	var tr = "";
    var fileCnt = $("#multipartFile").length;
    var insertClass="";
    
    //console.log(option.fileStepCd);
      
    switch (option.fileStepCd) {
    case "0502":
    	insertClass = "tech";
        break;
    case "0503":
    	insertClass = "check";
        break;
    case "0504":
    	insertClass = "drive";
        break;
    case "0505":
    	insertClass = "equip";
        break;
    case "0506":
    	insertClass = "safety";
        break;
    case "0507":
    	insertClass = "account";
        break;
    case "0508":
    	insertClass = "energy";
        break;
    case "0509":
    	insertClass = "design";
        break;        
    case "0510":
    	insertClass = "industrial";
        break;        
    case "0511":
    	insertClass = "highPressure";
        break;        
    case "0512":
    	insertClass = "fire";
        break;        
    }
    tr += "<tr>";
    tr += "<td>";
    tr += "<input type='text' class='i_input' value='"+option.fileCdNm+"' readonly>";
    tr += "<input type='text' id='multipartFile' class='i_input_300 fileNm' value='"+option.fileNm+"' readonly>";
    tr += "<img src='images/btn_inFile.png' class='cursor fileDown'/>";
    tr += "<img class='cursor removeDownFile' src='images/icon_minus.png' style='display:none'/>";
//    tr += "<input type='hidden' value='${mrAtchFiles.fileCd}'/>";
    tr += "<input type='hidden' class='delFlag' name='mrAtchFiles["+fileCnt+"].deleted' value='false'/>";
    tr += "<input type='hidden' class='insFlag' name='mrAtchFiles["+fileCnt+"].inserted' value='false'/>";
    tr += "<input type='hidden' name='mrAtchFiles["+fileCnt+"].mrAtchFileNo' value='"+option.mrAtchFileNo+"'/>";
    tr += "<input type='hidden' class='drawMngNo' name='mrAtchFiles["+fileCnt+"].drawMngNo' value='"+option.drawMngNo+"'/>";
    tr += "</td>";
    tr += "<tr>";
    $("."+insertClass+"FileList").append(tr);
};
</script>