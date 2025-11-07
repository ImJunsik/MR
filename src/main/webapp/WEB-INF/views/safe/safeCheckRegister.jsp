<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">가동전 안전점검</h4>
</div>

<!-- 
조건부 SHE 시스템 안내 메시지 표시
- MR_REQ_MST 테이블 조건: MR_STEP_CD = 'Z0070' AND PROC_ST_CD = 'Z0110' AND SHE_CHG_YN = "Y"
- MR_REQ_PROC_STEP 테이블 조건: MR_STEP_CD = 'Z0070' AND PROC_ST_CD <> 'Z0110'
-->


<!-- button -->
<div class="button_top">
	<c:if test="${register.jobsSkipCheck=='false'}">
	    <input type="image" src="images/btn_temp.png" class="save FAA GAA" name="save" style="display:none" /><!-- 임시저장 -->
	    <input type="image" src="images/btn_temp.png" class="save2 FAD" name="save2" style="display:none" /><!-- 임시저장 기술검토자용 -->
	    <input type="image" src="images/btn_complete.png" class="comp FAA GAA" name="comp" style="display:none" /><!-- 완료 -->
	    <input type="image" src="images/btn_approve.png" class="appr GBA" name="appr" style="display:none" /><!-- 승인 -->
	    <input type="image" src="images/btn_exec_complete.png" class="safeCNF FAI" name="safeCNF"  style="display:none"/><!-- mr수행완료 -->
		<input type="image" src="images/btn_checkZ02D.png" class="checkZ02D FAD" name="checkZ02D" style="display:none" /><!-- 기술검토 확인 -->
	    <input type="image" src="images/btn_checkitem.png" class="safeRPT FAA GAA" name="safeRPT" /><!-- 점검항목 -->
	</c:if>
	
	<br/><!-- <input type="image" src="images/btn_approve.png" class="appr GBA" name="appr"  /> --><!-- 승인 -->
	<c:if test="${register.jobsSkipCheck=='true'}">
		<div class="red"> * 직무검토 보류건이 있습니다.</div>
	</c:if>
</div>

<div id="content_wrap">
	<form id="registerForm" method="post">
	<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${register.mrReqNo}"/>"/>
	<input type="hidden" id="mrNo" name="mrNo" value="<c:out value="${register.mrNo}"/>"/>
	<input type="hidden" id="plantNo" name="plant" value="<c:out value="${register.plant}"/>"/>
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
	        <td><c:out value="${register.mrNo}"/></td>
	        <th scope="row">CAPEX번호</th>
	        <td><c:out value="${register.capexNo}"/></td>
	    </tr>
	    <tr>
	        <th scope="row" >사업명</th>
	        <td colspan="3"><c:out value="${register.mrReqTitle}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">개선대상</th>
	        <td>
	             <c:out value="${register.plantNm}"/>
	        </td>
	        <th scope="row">공정</th>
	        <td>
	              <c:forEach var="proc" items="${register.procs}" varStatus="loop" begin="0" end="0">
	                  <c:out value="${proc.mrProcName}"/>
	              </c:forEach>
	        </td>
	    </tr>
	    <tr>
	        <th scope="row">설 비</th>
	        <td>
	              <c:forEach var="equip" items="${register.equips}" varStatus="loop" >
	                  <c:out value="${equip.mrEquipName}"/><br/>
	              </c:forEach>
	        </td>
	        <th scope="row">투자구분</th>
	        <td><c:out value="${register.invstCdNm}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">작업시점</th>
	        <td><c:out value="${register.workPsblClNm}"/></td>
	        <th scope="row">투자목적</th>
	        <td><c:out value="${register.invstPurpCdNm}"/></td>
	    </tr>
	    </tbody>
	</table>
	
	<!-- space non 10 -->
	<hr class="divider_none_10" />
	<%-- mrStepCd 값: ${register.mrStepCd}, procStCd 값: ${register.procStCd}, sheChgYn 값: ${register.sheChgYn} --%>
<c:if test="${(register.sheChgYn == 'Y')}">
    <div class="she_system_notice" style="border: 2px solid #0066cc; background-color: #f0f8ff; padding: 20px; margin: 10px 0; height: 200px; text-align: center; border-radius: 5px;">
        <div style="color: #0066cc; font-size: 18px; font-weight: bold;padding: 70px;">
            SHE시스템 PSM에서 가동전 안전점검을 진행하시기 바랍니다.<BR><BR>
            <a href="https://www.naver.com" target="_blank" style="color: #B21016; font-size : 16px; text-decoration: none; font-weight: bold;">PSM 가동전 안전점검 바로가기  </a>  <!-- 기술검토 확인 -->
        </div>
    </div>
</c:if>
	<!-- 
	조건에 따라 안전점검 담당자 지정 섹션 표시/숨김
	- MR작성 중 (MR_STEP_CD = 'Z0070' AND PROC_ST_CD = 'Z0110' AND SHE_CHG_YN = "Y")일 때는 숨김
	- 그 외에는 기존과 동일하게 표시
	-->
	
<c:if test="${(register.sheChgYn == 'N')}">
	<table class="table_row" border="1" cellspacing="0">
	    <caption class="blind"></caption>
	    <colgroup>
	    <col width="15%">
	    <col width="15%">
	    <col width="70%">
	    </colgroup>
	    <tbody>
	    <tr>
	        <th scope="row" rowspan="10">안전점검<br />담당자 지정</th>
	        <th class="dep2" scope="row">점검팀 책임자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02PText" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02PText" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02PValue" name="safeChrgr[0].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[0].chrgrClCd" value="Z02P"/>
	           <input type="hidden" class="Z02PDutyText" name="safeChrgr[0].thdayPos" />
	           <input type="hidden" class="Z02PTeamText" name="safeChrgr[0].thdayTeam" />
	           <input type="hidden" class="Z02PTeamValue" name="safeChrgr[0].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P" />
	           <input type="hidden" class="i_input Z02PEndDate" name="safeChrgr[0].endDate" value="9999-12-31" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel0" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">공정설계 기술자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P1Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P1Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P1Value" name="safeChrgr[1].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[1].chrgrClCd" value="Z02P1"/>
	           <input type="hidden" class="Z02P1DutyText" name="safeChrgr[1].thdayPos" />
	           <input type="hidden" class="Z02P1TeamText" name="safeChrgr[1].thdayTeam" />
	           <input type="hidden" class="Z02P1TeamValue" name="safeChrgr[1].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P1" />
	           <input type="hidden" class="i_input Z02P1EndDate" name="safeChrgr[1].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel1" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">공정운전 기술자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P2Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P2Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P2Value" name="safeChrgr[2].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[2].chrgrClCd" value="Z02P2"/>
	           <input type="hidden" class="Z02P2DutyText" name="safeChrgr[2].thdayPos" />
	           <input type="hidden" class="Z02P2TeamText" name="safeChrgr[2].thdayTeam" />
	           <input type="hidden" class="Z02P2TeamValue" name="safeChrgr[2].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P2" />
	           <input type="hidden" class="i_input Z02P2EndDate" name="safeChrgr[2].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel2" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">설비설치 책임자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P3Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P3Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P3Value" name="safeChrgr[3].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[3].chrgrClCd" value="Z02P3"/>
	           <input type="hidden" class="Z02P3DutyText" name="safeChrgr[3].thdayPos" />
	           <input type="hidden" class="Z02P3TeamText" name="safeChrgr[3].thdayTeam" />
	           <input type="hidden" class="Z02P3TeamValue" name="safeChrgr[3].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P3" />
	           <input type="hidden" class="i_input Z02P3EndDate" name="safeChrgr[3].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel3" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">전기＆계장 기술자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P4Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P4Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P4Value" name="safeChrgr[4].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[4].chrgrClCd" value="Z02P4"/>
	           <input type="hidden" class="Z02P4DutyText" name="safeChrgr[4].thdayPos" />
	           <input type="hidden" class="Z02P4TeamText" name="safeChrgr[4].thdayTeam" />
	           <input type="hidden" class="Z02P4TeamValue" name="safeChrgr[4].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P4" />
	           <input type="hidden" class="i_input Z02P4EndDate" name="safeChrgr[4].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel4" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">검사＆정비 기술자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P5Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P5Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P5Value" name="safeChrgr[5].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[5].chrgrClCd" value="Z02P5"/>
	           <input type="hidden" class="Z02P5DutyText" name="safeChrgr[5].thdayPos" />
	           <input type="hidden" class="Z02P5TeamText" name="safeChrgr[5].thdayTeam" />
	           <input type="hidden" class="Z02P5TeamValue" name="safeChrgr[5].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P5" />
	           <input type="hidden" class="i_input Z02P5EndDate" name="safeChrgr[5].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel5" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">안전기술자</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P6Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P6Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P6Value" name="safeChrgr[6].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[6].chrgrClCd" value="Z02P6"/>
	           <input type="hidden" class="Z02P6DutyText" name="safeChrgr[6].thdayPos" />
	           <input type="hidden" class="Z02P6TeamText" name="safeChrgr[6].thdayTeam" />
	           <input type="hidden" class="Z02P6TeamValue" name="safeChrgr[6].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P6" />
	           <input type="hidden" class="i_input Z02P6EndDate" name="safeChrgr[6].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel6" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">기타 1</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P7Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P7Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P7Value" name="safeChrgr[7].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[7].chrgrClCd" value="Z02P7"/>
	           <input type="hidden" class="Z02P7DutyText" name="safeChrgr[7].thdayPos" />
	           <input type="hidden" class="Z02P7TeamText" name="safeChrgr[7].thdayTeam" />
	           <input type="hidden" class="Z02P7TeamValue" name="safeChrgr[7].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P7" />
	           <input type="hidden" class="i_input Z02P7EndDate" name="safeChrgr[7].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel7" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">기타 2</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P8Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P8Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P8Value" name="safeChrgr[8].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[8].chrgrClCd" value="Z02P8"/>
	           <input type="hidden" class="Z02P8DutyText" name="safeChrgr[8].thdayPos" />
	           <input type="hidden" class="Z02P8TeamText" name="safeChrgr[8].thdayTeam" />
	           <input type="hidden" class="Z02P8TeamValue" name="safeChrgr[8].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P8" />
	           <input type="hidden" class="i_input Z02P8EndDate" name="safeChrgr[8].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel8" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th class="dep2" scope="row">기타 3</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	           <input type="text" class="i_input Z02P9Text" value="" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	           <input type="text" class="i_input Z02P9Text" value="" readOnly/>
	        </c:if>
	           <input type="hidden" class="Z02P9Value" name="safeChrgr[9].chrgEmpNo" />
	           <input type="hidden" name="safeChrgr[9].chrgrClCd" value="Z02P9"/>
	           <input type="hidden" class="Z02P9DutyText" name="safeChrgr[9].thdayPos" />
	           <input type="hidden" class="Z02P9TeamText" name="safeChrgr[9].thdayTeam" />
	           <input type="hidden" class="Z02P9TeamValue" name="safeChrgr[9].chrgTeamNo" />
	           <img src="images/icon_search.png" class="vam cursor empSearch FAA GAA" style="display:none" data-role="Z02P9" />
	           <input type="hidden" class="i_input Z02P9EndDate" name="safeChrgr[9].endDate" value="" readOnly/>
	           <img src="images/icon_minus.png" class="vam cursor FAA GAA chrgrDel9" style="display:none" />
	        </div>
	        </td>
	    </tr>
	    </tbody>
	</table>

	<!-- space non 10 -->
	<hr class="divider_none_10" />

	<table class="table_row" border="1" cellspacing="0">
	    <caption class="blind"></caption>
	    <colgroup>
	    <col width="15%">
	    <col width="85%">
	    </colgroup>
	    <tbody>
	    <tr>
	        <th scope="row">안전점검 일시</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	             일자 : <input type="text" name="safetyChkDt" class="i_input inputDate safetyChkDt" value="${register.safetyChkDt}" readOnly/>
	             시간 : <input type="text" name="safetyChkTime" class="i_input inputTime safetyChkTime" value=" ${register.safetyChkTime}" readOnly/>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	             일자 : <input type="text" class="i_input" value="${register.safetyChkDt}" readOnly/>
	             시간 : <input type="text" class="i_input" value="${register.safetyChkTime}" readOnly/>
	        </c:if>
	             <input type="hidden" name="safetyDate" class="safetyDate" value="${register.safetyDate}" readOnly/>
	        </div>
	        </td>
	    </tr>
	    <tr>
	        <th scope="row">안전점검 장소</th>
	        <td>
	        <div class="form_group">
	        <c:if test="${modeClass=='FAA'||modeClass=='GAA'}">
	             <textarea name="safetyChkLoc" class="i_text_full safetyChkLoc" rows="" cols=""><c:out value="${register.safetyChkLoc}"/></textarea>
	        </c:if>
	        <c:if test="${modeClass!='FAA'&&modeClass!='GAA'}">
	             <textarea class="i_text_full" readonly><c:out value="${register.safetyChkLoc}"/></textarea>
	        </c:if>
	        </div>
	        </td>
	    </tbody>
	</table>
	
	
	<!-- space non 10 -->
	<hr class="divider_none_10" />
	
	<table class="table_row" border="1" cellspacing="0">
	    <caption class="blind"></caption>
	    <colgroup>
	    <col width="15%">
	    <col width="85%">
	    </colgroup>
	    <tbody>
	    <tr>
	      <th scope="row" class="vam fileAdd">첨부파일
	        <img id="addFile" src="images/icon_s_plus.png" border="0" class="cursor FAD GAA GBA fileAdd mt-2" disabled="true"/>
	      </th>
	      <td class="section_text pl5 pt5 pb5">
	        <div class="form_group">
	          <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
	            <tr style="display:none"><td></td></tr>
	            <c:forEach var="mrAtchFiles" items="${register.mrAtchFiles}" varStatus="loop">
	            <tr>
	               <td>
	                <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
	                <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
	                <img src="images/btn_inFile.png" class="cursor fileDown" />
	                <img class="cursor FAD GBA removeDownFile" src="images/icon_minus.png" style="display:none"/>
	                <input type="hidden" value="${mrAtchFiles.fileCd}"/>
	                <input type="hidden" class="delFlag" name="mrAtchFiles[${loop.index}].deleted" value="false"/>
	                <input type="hidden" class="insFlag" name="mrAtchFiles[${loop.index}].inserted" value="false"/>
	                <input type="hidden" name="mrAtchFiles[${loop.index}].mrAtchFileNo" value="${mrAtchFiles.mrAtchFileNo}"/>
	                <input type="hidden" class="drawMngNo" name="mrAtchFiles[${loop.index}].drawMngNo" value="${mrAtchFiles.drawMngNo}"/>
	                
	                <!-- wj: 이미 저장된 첨부파일 체크를 위한 hidden field 추가-->
	                <input type="hidden" class="fileCdFlag" name="mrAtchFiles[${loop.index}].fileCd" value="${mrAtchFiles.fileCd}"/>
	                <input type="hidden" class="fileNmFlag" name="mrAtchFiles[${loop.index}].fileNm" value="${mrAtchFiles.fileNm}"/>
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
	
	<div class="tl"> * 안전점검 수행문서 원본 또는 스캔본 필히 첨부 (가동전 점검결과, 변경관리 교육 결과 첨부 필수)</div>
	
</c:if>
	
	<!--
	<c:out value="${modeClass}"/>
	<c:out value="${register.jobsSkipCheck}"/>
	-->
	</form>
</div>
<script>
$(window).load(function() {
    var checkList = new Array();
    var disabled = true;

    if($("#writeDate").html()=="") {
        $("#writeDate").html(currentDate);
    }

    if("${modeClass}"=="FAA"||"${modeClass}"=="GAA" || "${modeClass}"=="FAD"){
        disabled = false;
    }
    
    //alert("${modeClass}"); 
    //alert("${appLine.chrgrClCd}");
    //alert("${appLine.ifStatCd}");
    //alert("${appLine.chrgEmpNo}");
    //alert("${appLine.loginEmpNo}");
    
    "<c:forEach var='appLine' items='${register.appLine}'>";
    
    switch ("${appLine.chrgrClCd}") {
    case "Z02P":
        appClass = "Z02P";
        break;
    case "Z02P1":
        appClass = "Z02P1";
        break;
    case "Z02P2":
        appClass = "Z02P2";
        break;
    case "Z02P3":
        appClass = "Z02P3";
        break;
    case "Z02P4":
        appClass = "Z02P4";
        break;
    case "Z02P5":
        appClass = "Z02P5";
        break;
    case "Z02P6":
        appClass = "Z02P6";
        break;
    case "Z02P7":
        appClass = "Z02P7";
        break;
    case "Z02P8":
        appClass = "Z02P8";
        break;
    case "Z02P9":
        appClass = "Z02P9";
        break;    
    }    
    
    $.appLineSet({className:appClass, dutyText:"${appLine.thdayPos}",
        text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
        teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
        endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
        date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
    "</c:forEach>";
    
    if("${modeClass}"=="FAA"||"${modeClass}"=="GAA"){
        $(".safetyDate").val($(".safetyChkDt").val()+$(".safetyChkTime").val());
    } else {
    	$(".safetyDate").val("");
    }

    $.selectLoad({
        className : "reqClCd",
        url : "codeList.do?mrCommGrpCd=REQ",
        defaultText : "선택",
        selectValue : "${register.reqClCd}"
    });

    $.selectLoad({
        className : "workPsblClCd",
        url : "codeList.do?mrCommGrpCd=WORK",
        defaultText : "선택",
        selectValue : "${register.workPsblClCd}"
    });

    $.selectLoad({
        className : "plantNo",
        url : "codeList.do?mrCommGrpCd=PLANT",
        defaultText : "선택",
        selectValue : "${register.plant}"
    });

    $.selectLoad({
        className : "propClass",
        url : "codeList.do?mrCommGrpCd=PROP",
        defaultText : "선택",
        selectValue : "${ivstRpt.propClass}",
    });

    $(".empSearch").click(function() {
        $.popup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".teamSearch").click(function() {
        $.popup({url:"popup/teamSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".safeRPT").click(function() {
      $.popup({url:"/mr/HDO_safeRPT.jsp", width:"0", height:"0"});
    });

    $(".save").click(function() {
        if(confirm("임시저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${saveURL}").submit(); //saveURL: safeCheckSave.do, safeCheckUpdate.do
        }
    });
    
  	//임시저장 mr수행의 기술검토확인 용
    $(".save2").click(function() {
        if(confirm("임시저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();            
            //저장 분기 saveURL: safeCheckSave2.do, safeCheckUpdate2.do 
            	//alert("${saveURL2}");
            
        	//첨부파일 첨부여부 카운트
        	var riskFileCnt = 0;
        	var AttechChkCnt = 0;
        	
        	//wj:이미 저장된 첨부파일 체크를 위한 로직 추가(20180125)
        	var riskFileCnt2 = 0;
        	var AttechChkCnt2 = 0;        	
        	
        	$("#fileList > tbody> tr").each(function (){
        		if(!$(this).is(":hidden")){
            		var fileCd = $(this).children("td").children(".fileCd").val();
            		var file = $(this).children("td").children(".fileNm").val();            		 
            		//var fileCd_2 = $(this).children("td").children(".fileCdFlag").val();
            		//var file2 = $(this).children("td").children(".fileNmFlag").val();
    				
    			    //alert(fileCd);
        			if(file != "" && file != undefined) {
            			riskFileCnt++;
            			//alert(riskFileCnt);
       	    		}
        		}
    		});        	
        	
        	$("form").attr("action", "safeCheckAppr2.do").submit();		//가동전 안전점검 승인        	
        }
    });

    $(".safeCNF").click(function() {
        if($.validation())
        if(confirm("MR요청자에게 가동전 안전점검을 요청 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "safeCheckConf.do").submit();
        }
    });
    
    $(".comp").click(function() {
        if($.validation()){
        	//-- 승인 로직으로 이동함.
        	//첨부파일 첨부여부 카운트
        	/* var riskFileCnt = 0;
        	var AttechChkCnt = 0;
        	$("#fileList > tbody> tr").each(function (){
        		if(!$(this).is(":hidden")){
	        		var file = $(this).children("td").children(".fileNm").val();
	        		var fileCd = $(this).children("td").children(".fileCd").val();
    				switch (fileCd) {
	    		    case "0014":  //가동전 점검결과
	    		    	AttechChkCnt++;
					    break;
		    	    case "0015":  //변경관리 교육 결과
		    	    	AttechChkCnt++;
					    break;
				    }
	    			if(file != "" && file != undefined){
	        			riskFileCnt++;
	   	    		}
	    		}
    		});    	

        	if(riskFileCnt < 1){
	       		alert("가동전 점검결과 및 변경관리 교육 결과를 필히 첨부하세요.");
        		return;
        	}
	    	if(AttechChkCnt < 2){
	       		alert("가동전 점검결과 및 변경관리 교육 결과를 필히 첨부하세요.");
	       		return;
	       	}   */    
	    	
	        if(confirm("완료 하시겠습니까?")) {
	            $('#pageLoadingWrap').show();
	            $("form").attr("action", "safeCheckComp2.do").submit();
	        }
        }
    });
    
    //승인
    $(".appr").click(function() { 
    	
    	//첨부파일 첨부여부 카운트
    	var riskFileCnt = 0;
    	var AttechChkCnt = 0;
    	
    	//wj:이미 저장된 첨부파일 체크를 위한 로직 추가(20180125)
    	var riskFileCnt2 = 0;
    	var AttechChkCnt2 = 0;
    	
    	$("#fileList > tbody> tr").each(function (){
    		if(!$(this).is(":hidden")){
        		var fileCd = $(this).children("td").children(".fileCd").val();
        		var file = $(this).children("td").children(".fileNm").val();
        		 
        		var fileCd_2 = $(this).children("td").children(".fileCdFlag").val();
        		var file2 = $(this).children("td").children(".fileNmFlag").val();
        		
        		//alert("기존fileCd:" + fileCd +"||추가fileCd_2:" + fileCd_2);
        		//alert("기존file:" + file + "||추가file2:" + file2);        		
        		//alert(fileCd);
				switch (fileCd) {
    		    case "0014":  //가동전 점검결과
    		    	AttechChkCnt++;
				    break;
	    	    case "0015":  //변경관리 교육 결과
	    	    	AttechChkCnt++;
				    break;
			    }

				//wj:기존로직에선 fileCd 조회안되기 때문에 로직 추가(20180125)
				switch (fileCd_2) {
    		    case "0014":  //가동전 점검결과
    		    	AttechChkCnt2++;
				    break;
	    	    case "0015":  //변경관리 교육 결과
	    	    	AttechChkCnt2++;
				    break;
			    }
				
    			if(file != "" && file != undefined) {
        			riskFileCnt++;
   	    		}
    			
    			//wj: 기존로직에선 fileCd 조회안되기 때문에 로직 추가(20180125)
    			if(file2 != "" && file2 != undefined) {
        			riskFileCnt2++;
   	    		}
    		}
		});    	

    	if(riskFileCnt < 1 && riskFileCnt2 < 1 ){
       		alert("가동전(항목) 점검결과 및 변경관리 교육 결과를 필히 첨부하세요.");
    		return;
    	}
    	if(AttechChkCnt < 2 && AttechChkCnt2 < 1){
       		alert("가동전(파일) 점검결과 및 변경관리 교육 결과를 필히 첨부하세요.");
       		return;
       	}    	
    	
        if(confirm("승인 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "safeCheckAppr.do").submit();
        }
    });
    
    //201905 기술검토확인(checkZ02D)
    $(".checkZ02D").click(function() {
    	
    	  if(confirm("기술검토확인을 완료 하시겠습니까? 완료 후 Project Engineer가 다음 단계를 진행합니다")) {
              $('#pageLoadingWrap').show();
              //참고 $("form").attr("action", "safeCheckConf.do").submit();
                          
          }
           
       	//첨부파일 첨부여부 카운트
       	var riskFileCnt = 0;
       	var AttechChkCnt = 0;
       	
       	//wj:이미 저장된 첨부파일 체크를 위한 로직 추가(20180125)
       	var riskFileCnt2 = 0;
       	var AttechChkCnt2 = 0;        	
       	
       	$("#fileList > tbody> tr").each(function (){
       		if(!$(this).is(":hidden")){
           		var fileCd = $(this).children("td").children(".fileCd").val();
           		var file = $(this).children("td").children(".fileNm").val();            		 
           		//var fileCd_2 = $(this).children("td").children(".fileCdFlag").val();
           		//var file2 = $(this).children("td").children(".fileNmFlag").val();
   				
   			    //alert(fileCd);
       			if(file != "" && file != undefined) {
           			riskFileCnt++;
           			//alert(riskFileCnt);
      	    		}
       		}
   		});        	
       	$("form").attr("action", "safeCheckZ02d.do").submit();
    	   
    	   
      
    });


    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
        	safetyChkDt : {method:"class",type:"text", msg:"안전점검 일자를 입력하세요."},
        	safetyChkLoc : {method:"class",type:"text", msg:"안전점검 수행할 장소를 입력하세요."},
        });
        return isValid;
    };

    $.validation2 = function(){
        var isValid = false;
        isValid = $.validator({
            fileAdd : {method:"class",type:"text", msg:"안전점검 수행결과 파일을 첨부하세요."},
        });
        return isValid;
    };

//삭제버튼
    $(".chrgrDel0").click(function() {
        $(".Z02PText").val("");
        $(".Z02PValue").val("");
        $(".Z02PDutyText").val("");
        $(".Z02PTeamText").val("");
        $(".Z02PTeamValue").val("");
        $(".Z02PEndDate").val("");
    });
    $(".chrgrDel1").click(function() {
        $(".Z02P1Text").val("");
        $(".Z02P1Value").val("");
        $(".Z02P1DutyText").val("");
        $(".Z02P1TeamText").val("");
        $(".Z02P1TeamValue").val("");
        $(".Z02P1EndDate").val("");
    });
    $(".chrgrDel2").click(function() {
        $(".Z02P2Text").val("");
        $(".Z02P2Value").val("");
        $(".Z02P2DutyText").val("");
        $(".Z02P2TeamText").val("");
        $(".Z02P2TeamValue").val("");
        $(".Z02P2EndDate").val("");
    });
    $(".chrgrDel3").click(function() {
        $(".Z02P3Text").val("");
        $(".Z02P3Value").val("");
        $(".Z02P3DutyText").val("");
        $(".Z02P3TeamText").val("");
        $(".Z02P3TeamValue").val("");
        $(".Z02P3EndDate").val("");
    });
    $(".chrgrDel4").click(function() {
        $(".Z02P4Text").val("");
        $(".Z02P4Value").val("");
        $(".Z02P4DutyText").val("");
        $(".Z02P4TeamText").val("");
        $(".Z02P4TeamValue").val("");
        $(".Z02P4EndDate").val("");
    });
    $(".chrgrDel5").click(function() {
        $(".Z02P5Text").val("");
        $(".Z02P5Value").val("");
        $(".Z02P5DutyText").val("");
        $(".Z02P5TeamText").val("");
        $(".Z02P5TeamValue").val("");
        $(".Z02P5EndDate").val("");
    });
    $(".chrgrDel6").click(function() {
        $(".Z02P6Text").val("");
        $(".Z02P6Value").val("");
        $(".Z02P6DutyText").val("");
        $(".Z02P6TeamText").val("");
        $(".Z02P6TeamValue").val("");
        $(".Z02P6EndDate").val("");
    });
    $(".chrgrDel7").click(function() {
        $(".Z02P7Text").val("");
        $(".Z02P7Value").val("");
        $(".Z02P7DutyText").val("");
        $(".Z02P7TeamText").val("");
        $(".Z02P7TeamValue").val("");
        $(".Z02P7EndDate").val("");
    });
    $(".chrgrDel8").click(function() {
        $(".Z02P8Text").val("");
        $(".Z02P8Value").val("");
        $(".Z02P8DutyText").val("");
        $(".Z02P8TeamText").val("");
        $(".Z02P8TeamValue").val("");
        $(".Z02P8EndDate").val("");
    });
    $(".chrgrDel9").click(function() {
        $(".Z02P9Text").val("");
        $(".Z02P9Value").val("");
        $(".Z02P9DutyText").val("");
        $(".Z02P9TeamText").val("");
        $(".Z02P9TeamValue").val("");
        $(".Z02P9EndDate").val("");
    });
});

$(window).ready(function() {
    $(".inputTime").timepicker({
        dateformat:"yyyy-mm-dd",
        showOn:"button",
        changeYear: true,
        changeMonth: true,
        buttonImage:"images/icon_calendar.png",
        buttonImageOnly:true,
        minDate: "today"
    });

    $(".inputLimitDate").datepicker({
        dateformat:"yyyy-mm-dd",
        showOn:"button",
        changeYear: true,
        changeMonth: true,
        buttonImage:"images/icon_calendar.gif",
        buttonImageOnly:true,
        minDate :"today",
        maxDate :"+3M"
    });
    $("img.ui-datepicker-trigger").attr("style", "border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;");
    $(".ui-datepicker-month").attr("width", "30px");

    $.injectUintList = function(option){
        var tr ="";
        tr += "<tr>";
        tr += "<td>"+option.unitNo+"<input type='hidden' class='mrProcNo' name='unitNo' value='"+option.unitNo+"'></td>";
        tr += "<td>"+option.unitName+"<input type='hidden' class='mrProcName' name='unitNo' value='"+option.unitName+"'></td>";
        tr += "<tr>";
        $("#selectUnit").append(tr);
        $.sort({className : "mrProcNo", fristName : "procs", lastName : "mrProcNo"});
        $.sort({className : "mrProcName", fristName : "procs", lastName : "mrProcName"});
    };
});

$(document).on("change",".safetyChkDt",function() {
    $(".safetyDate").val($(".safetyChkDt").val()+$(".safetyChkTime").val());
});

$(document).on("change",".safetyChkTime",function() {
	$(".safetyDate").val($(".safetyChkDt").val()+$(".safetyChkTime").val());
});

</script>