<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
    <input type="image" src="../images/btn_save.png" style="display:none" class="cursor save IASA"/>
</div>
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${jobsPorc.mrReqNo}"/>"/>
<input type="hidden" name="clCd01" id="selectVal" />
<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="85%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row">MR번호</th>
        <td><c:out value="${jobsPorc.mrNo}"/></td>
    </tr>
    <tr>
        <th scope="row">사업명</th>
        <td><c:out value="${jobsPorc.mrReqTitle}"/></td>
    </tr>
    <tr>
        <th scope="row">PORC번호</th>
        <td><c:out value="${jobsPorc.porcNo}"/></td>
        
    </tr>
    </tbody>
</table>

<!-- space non 10 -->
<hr class="divider_none_10" />

<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    	<col width="15%">
    	<col width="85%">
    </colgroup>    
    <tbody>
    	<script>
    	
    	//console.log("${jobsPorc.techs}");
		//console.log("${jobsPorc.drives}");
		//console.log("${jobsPorc.builds}");
		//console.log("${jobsPorc.checks}");
		//console.log("${jobsPorc.safetys}");
		//console.log("${jobsPorc.etcs}");
		//console.log("${jobsPorc.plant}");
		//console.log("${tech.rvRsts[0].lastChgDt}");
		//alert("${jobsPorc.plant}");
		//alert("${tech.rvRsts[0].lastChgDt}");
		
    	</script>
    	
   	<c:if test="${jobsPorc.techs != '[]' or jobsPorc.builds != '[]'}">   		
		<tr>	
	       <th scope="row"><!-- jobsPorc.plant : ${jobsPorc.plant}-->위원장</th>
	       <td>
	           <table class="table_pop_default builds">
                <colgroup>
	                <col width="27%">
	                <col width="73%">
                </colgroup>             	             
	               <tr>
	                   <td>
                          <span id="confirmer" class="vam techText"></span>

                    </td>
                    <td class="vat">
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="1" disabled="disabled"/>동의
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="2" disabled="disabled"/>반대
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="3" disabled="disabled"/>조건부동의                          
                          <span id='TechSpanTime'></span>
						  <input type="hidden" id="confirmer" name="confirmer" value="">
						  <input type="hidden" id="approvalDate" name="approvalDate" value="${approvalDate}">

	                   		<span class="pl5" id="div00spanPlant" name="div00spanPlant" value="${jobsPorc.plant}" style="display:none" />${jobsPorc.plant}</span>
	                   		
<!-- 	                   		
	                   		<c:choose>
								<c:when test="${jobsPorc.plant eq '8000'}">HCP_현대코스모 / 상무 / 이영우</c:when>
								<c:when test="${jobsPorc.plant eq '8500'}">HSB_현대쉘베이스오일 / 상무 / 송규석</c:when>
								<c:when test="${jobsPorc.plant eq '1000'}"><span id="confirmer" class="vam techText">안전생산본부장 / 부사장 / </span></c:when>
								<c:otherwise>해당 위원장을 확인하여 주십시오..... ${jobsPorc.plant}</c:otherwise>
							</c:choose>
	                   	 
	                   </td>
	                   <td class="vat">
	                   	  <div class="form_group">
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="1" <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="2" <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div0199111" class="i_radio clCd01" value="3" <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
						  <input type="hidden" id="confirmer" name="confirmer" value="">
						  <input type="hidden" id="approvalDate" name="approvalDate" value="${approvalDate}">
						  <input type="hidden" id="criteriaDate" name="criteriaDate" value="${criteriaDate}">
	                      <span class="pl5"><fmt:formatDate value='${tech.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /><span id='TechSpanTime'></span>
                         </span>
							<div class="form_group pt3">
                         </div>
	                     </div>
 -->	                     
	                   </td>
	               </tr>
	           </table>
	       </td>
	   </tr>
	</c:if>
	<tr>
        <th scope="row">운전/생산</th>
        <td>
            <table class="table_pop_default drive">
                <colgroup>
	                <col width="27%">
	                <col width="73%">
                </colgroup>             
            	<c:forEach var="drive" items="${jobsPorc.drives}" varStatus="loop">                        
                <tr>
                    <td>
                      <span class="vam driveText"><c:out value="${drive.thdayTeam}"/> / <c:out value="${drive.thdayPos}"/> / <c:out value="${drive.chrgEmpName}"/></span>
                      <input type="hidden" class="driveValue" name="drives[${loop.index}].chrgEmpNo" value="<c:out value="${drive.chrgEmpNo}"/>"/>
                      <input type="hidden" class="driveCode" name="drives[${loop.index}].chrgrClCd" value="<c:out value="${drive.chrgrClCd}"/>"/>
                      <input type="hidden" class="driveDutyText" name="drives[${loop.index}].thdayPos" value="<c:out value="${drive.thdayPos}"/>"/>
                      <input type="hidden" class="driveTeamText" name="drives[${loop.index}].thdayTeam" value="<c:out value="${drive.thdayTeam}"/>"/>
                      <input type="hidden" class="driveTeamValue" name="drives[${loop.index}].chrgTeamNo" value="<c:out value="${drive.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div03${loop.index}" class="i_radio clCd01" value="1" <c:if test="${drive.rvRsts[0].clCd01 == '1'}">checked="checked" </c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=drive.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div03${loop.index}" class="i_radio clCd01" value="2" <c:if test="${drive.rvRsts[0].clCd01 == '2'}">checked="checked" </c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=drive.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div03${loop.index}" class="i_radio clCd01" value="3" <c:if test="${drive.rvRsts[0].clCd01 == '3'}">checked="checked" </c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=drive.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5"><fmt:formatDate value='${drive.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==drive.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${drive.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=drive.chrgEmpNo}">
                                <pre><c:out value="${drive.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">기술</th>
        <td>
            <table class="table_pop_default tech">
                <colgroup>
                <col width="27%">
                <col width="73%">
                </colgroup>
            <c:forEach var="tech" items="${jobsPorc.techs}" varStatus="loop">
                <tr>
                    <td>
                      <span class="vam techText"><c:out value="${tech.thdayTeam}"/> / <c:out value="${tech.thdayPos}"/> / <c:out value="${tech.chrgEmpName}"/></span>
                      <input type="hidden" class="techValue" name="techs[${loop.index}].chrgEmpNo" value="<c:out value="${tech.chrgEmpNo}"/>"/>
                      <input type="hidden" class="techCode" name="techs[${loop.index}].chrgrClCd" value="<c:out value="${tech.chrgrClCd}"/>"/>
                      <input type="hidden" class="techDutyText" name="techs[${loop.index}].thdayPos" value="<c:out value="${tech.thdayPos}"/>"/>
                      <input type="hidden" class="techTeamText" name="techs[${loop.index}].thdayTeam" value="<c:out value="${tech.thdayTeam}"/>"/>
                      <input type="hidden" class="techTeamValue" name="techs[${loop.index}].chrgTeamNo" value="<c:out value="${tech.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div01${loop.index}" class="i_radio clCd01" value="1" <c:if test="${tech.rvRsts[0].clCd01 == '1'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div01${loop.index}" class="i_radio clCd01" value="2" <c:if test="${tech.rvRsts[0].clCd01 == '2'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div01${loop.index}" class="i_radio clCd01" value="3" <c:if test="${tech.rvRsts[0].clCd01 == '3'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5" id="div01span${loop.index}" name="div01span${loop.index}" value="<fmt:formatDate value='${tech.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' />"><fmt:formatDate value='${tech.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==tech.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${tech.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=tech.chrgEmpNo}">
                                <pre><c:out value="${tech.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">공무</th>
        <td>
            <table class="table_pop_default build">
                <colgroup>
                <col width="27%">
                <col width="73%">
                </colgroup>
            <c:forEach var="build" items="${jobsPorc.builds}" varStatus="loop">
            
                <tr>
                    <td>
                      <span class="vam buildText"><c:out value="${build.thdayTeam}"/> / <c:out value="${build.thdayPos}"/> / <c:out value="${build.chrgEmpName}"/></span>
                      <input type="hidden" class="buildValue" name="builds[${loop.index}].chrgEmpNo" value="<c:out value="${build.chrgEmpNo}"/>"/>
                      <input type="hidden" class="buildCode" name="builds[${loop.index}].chrgrClCd" value="<c:out value="${build.chrgrClCd}"/>"/>
                      <input type="hidden" class="buildDutyText" name="builds[${loop.index}].thdayPos" value="<c:out value="${build.thdayPos}"/>"/>
                      <input type="hidden" class="buildTeamText" name="builds[${loop.index}].thdayTeam" value="<c:out value="${build.thdayTeam}"/>"/>
                      <input type="hidden" class="buildTeamValue" name="builds[${loop.index}].chrgTeamNo" value="<c:out value="${build.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div05${loop.index}" class="i_radio clCd01" value="1" <c:if test="${build.rvRsts[0].clCd01 == '1'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=build.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div05${loop.index}" class="i_radio clCd01" value="2" <c:if test="${build.rvRsts[0].clCd01 == '2'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=build.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div05${loop.index}" class="i_radio clCd01" value="3" <c:if test="${build.rvRsts[0].clCd01 == '3'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=build.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5" id="div05span${loop.index}" name="div05span${loop.index}"><fmt:formatDate value='${build.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==build.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${build.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=build.chrgEmpNo}">
                                <pre><c:out value="${build.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">설계/검사</th>
        <td>
            <table class="table_pop_default check">
                <colgroup>
                <col width="27%">
                <col width="73%">
                </colgroup>
            <c:forEach var="check" items="${jobsPorc.checks}" varStatus="loop">
                <tr>
                    <td>
                      <span class="vam checkText"><c:out value="${check.thdayTeam}"/> / <c:out value="${check.thdayPos}"/> / <c:out value="${check.chrgEmpName}"/></span>
                      <input type="hidden" class="checkValue" name="checks[${loop.index}].chrgEmpNo" value="<c:out value="${check.chrgEmpNo}"/>"/>
                      <input type="hidden" class="checkCode" name="checks[${loop.index}].chrgrClCd" value="<c:out value="${check.chrgrClCd}"/>"/>
                      <input type="hidden" class="checkDutyText" name="checks[${loop.index}].thdayPos" value="<c:out value="${check.thdayPos}"/>"/>
                      <input type="hidden" class="checkTeamText" name="checks[${loop.index}].thdayTeam" value="<c:out value="${check.thdayTeam}"/>"/>
                      <input type="hidden" class="checkTeamValue" name="checks[${loop.index}].chrgTeamNo" value="<c:out value="${check.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div02${loop.index}" class="i_radio clCd01" value="1" <c:if test="${check.rvRsts[0].clCd01 == '1'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=check.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div02${loop.index}" class="i_radio clCd01" value="2" <c:if test="${check.rvRsts[0].clCd01 == '2'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=check.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div02${loop.index}" class="i_radio clCd01" value="3" <c:if test="${check.rvRsts[0].clCd01 == '3'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=check.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5"><fmt:formatDate value='${check.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==check.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${check.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=check.chrgEmpNo}">
                                <pre><c:out value="${check.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">안전/환경</th>
        <td>
            <table class="table_pop_default safety">
                <colgroup>
                <col width="27%">
                <col width="73%">
                </colgroup>
            <c:forEach var="safety" items="${jobsPorc.safetys}" varStatus="loop">
                <tr>
                    <td>
                      <span class="vam safetyText"><c:out value="${safety.thdayTeam}"/> / <c:out value="${safety.thdayPos}"/> / <c:out value="${safety.chrgEmpName}"/></span>
                      <input type="hidden" class="safetyValue" name="safetys[${loop.index}].chrgEmpNo" value="<c:out value="${safety.chrgEmpNo}"/>"/>
                      <input type="hidden" class="safetyCode" name="safetys[${loop.index}].chrgrClCd" value="<c:out value="${safety.chrgrClCd}"/>"/>
                      <input type="hidden" class="safetyDutyText" name="safetys[${loop.index}].thdayPos" value="<c:out value="${safety.thdayPos}"/>"/>
                      <input type="hidden" class="safetyTeamText" name="safetys[${loop.index}].thdayTeam" value="<c:out value="${safety.thdayTeam}"/>"/>
                      <input type="hidden" class="safetyTeamValue" name="safetys[${loop.index}].chrgTeamNo" value="<c:out value="${safety.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div04${loop.index}" class="i_radio clCd01" value="1" <c:if test="${safety.rvRsts[0].clCd01 == '1'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=safety.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div04${loop.index}" class="i_radio clCd01" value="2" <c:if test="${safety.rvRsts[0].clCd01 == '2'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=safety.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div04${loop.index}" class="i_radio clCd01" value="3" <c:if test="${safety.rvRsts[0].clCd01 == '3'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=safety.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5" id="div04span${loop.index}"><fmt:formatDate value='${safety.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==safety.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${safety.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=safety.chrgEmpNo}">
                                <pre><c:out value="${safety.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">기타</th>
        <td>
            <table class="table_pop_default etc">
                <colgroup>
                <col width="27%">
                <col width="73%">
                </colgroup>
            <c:forEach var="etc" items="${jobsPorc.etcs}" varStatus="loop">
                <tr>
                    <td>
                      <span class="vam etcText"><c:out value="${etc.thdayTeam}"/> / <c:out value="${etc.thdayPos}"/> / <c:out value="${etc.chrgEmpName}"/></span>
                      <input type="hidden" class="etcValue" name="etcs[${loop.index}].chrgEmpNo" value="<c:out value="${etc.chrgEmpNo}"/>"/>
                      <input type="hidden" class="etcCode" name="etcs[${loop.index}].chrgrClCd" value="<c:out value="${etc.chrgrClCd}"/>"/>
                      <input type="hidden" class="etcDutyText" name="etcs[${loop.index}].thdayPos" value="<c:out value="${etc.thdayPos}"/>"/>
                      <input type="hidden" class="etcTeamText" name="etcs[${loop.index}].thdayTeam" value="<c:out value="${etc.thdayTeam}"/>"/>
                      <input type="hidden" class="etcTeamValue" name="etcs[${loop.index}].chrgTeamNo" value="<c:out value="${etc.chrgTeamNo}"/>"/>
                    </td>
                    <td class="vat">
                      <div class="form_group">
                          <input type="radio" name="div06${loop.index}" class="i_radio clCd01" value="1" <c:if test="${etc.rvRsts[0].clCd01 == '1'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=etc.chrgEmpNo}"> disabled="disabled"</c:if>/>동의
                          <input type="radio" name="div06${loop.index}" class="i_radio clCd01" value="2" <c:if test="${etc.rvRsts[0].clCd01 == '2'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=etc.chrgEmpNo}"> disabled="disabled"</c:if>/>반대
                          <input type="radio" name="div06${loop.index}" class="i_radio clCd01" value="3" <c:if test="${etc.rvRsts[0].clCd01 == '3'}">checked="checked"</c:if><c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=etc.chrgEmpNo}"> disabled="disabled"</c:if>/>조건부동의
                          <span class="pl5"><fmt:formatDate value='${etc.rvRsts[0].lastChgDt}' pattern='yyyy-MM-dd' /></span>
                          <div class="form_group pt3">
                            <c:if test="${modeClass=='IASA' and jobsPorc.loginEmpNo ==etc.chrgEmpNo}">
                                <textarea name="clCmt01" rows="" cols="" class="i_text_full"><c:out value="${etc.rvRsts[0].clCmt01}"/></textarea>
                            </c:if>
                            <c:if test="${modeClass!='IASA' or jobsPorc.loginEmpNo !=etc.chrgEmpNo}">
                                <pre><c:out value="${etc.rvRsts[0].clCmt01}"/></pre>
                            </c:if>
                          </div>
                      </div>
                    </td>
                </tr>
                <tr><td colspan="2" class="small"><hr class="divider_dashed" /></td></tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</form>
<script>
$(window).ready(function() {
	$.popupTitle("PORC 작성");
	
	//202001 porc 로직 변경
	//기술이나 공무 레디오버튼 체크여부 확인    	
	var radioTach = $('input[name="div010"]:checked').val();		//기술010
    var radioBuilds = $('input[name="div050"]:checked').val();		//공무050
    var sPlant = $('#div00spanPlant').text();
    //yoo 20211201 PORC 작성, 전체 아이템 체크에 따른 위원장 표시
    var confirmer = '';	/* = document.getElementById('confirmer');*/
    var criteriaDateHDO0 = new Date('2021-11-01');
    var criteriaDateHDO1 = new Date('2023-11-10');
    var criteriaDateHCP0 = new Date('2017-12-01');
    var criteriaDateHCP1 = new Date('2024-07-01');			//yoo 240828 추가
    var criteriaDateHSB0 = new Date('2023-12-01');
    var approvalDate;
    var commitChoice;           //윈원장 선택 radio 값
    console.log(document.getElementsByClassName("vam").length);
	var array_vam = document.getElementsByClassName("vam");
	
	// Sub
	var sTime = $('#div01span0').text();
	var hPar =  sTime.substring(11, 13);
	var mPar =  sTime.substring(14, 16);
	var sTimeDay = sTime.substring(0, 10).replace(/-/gi,"");

	console.log('array_vam.length : ' + array_vam.length);
	for(var element in array_vam)
	{
		console.log(element, array_vam[element].innerText);	
	}

    console.log(document.getElementsByClassName("pl5").length);
	var array_pl5 = document.getElementsByClassName("pl5");
	var array_radio = document.getElementsByClassName("i_radio");
	var arrayDate = new Array();
	var HDObbb = 0;		//'정해원'
	var Last_Item_Date = '';		//yoo
	var HSBbbb = 0;		//'송규석'
	var HCPbbb = 0;		//'이병재'
	
	
	if(sPlant == 8000)	//HCP yoo 240611 HCP부분 추가
	{
		console.log('array_pl5.length : ' + array_pl5.length);
        // 인사명령에 따른 PORC 위원장 적용
		for(var element in array_pl5)
		{
			console.log(element, array_pl5[element].innerText);	
			if(array_pl5[element].innerText != null)
				if(array_pl5[element].innerText.toString().indexOf('20') == 0)
				{
					approvalDate = new Date(array_pl5[element].innerText.toString());
					arrayDate.push(array_pl5[element].innerText.toString());
					//console.log('criteriaDate < approvalDate : ' + (criteriaDateHCP0 < approvalDate));
					if(criteriaDateHCP0 > approvalDate)
						HCPbbb = 1;			//'이병재'
					else if(criteriaDateHCP1 <= approvalDate)
						HCPbbb = 3;			//'유병문'
					else
						HCPbbb = 2;			//'이영우'
						
					Last_Item_Date = approvalDate;
				}
		}

		console.log('arrayDate.length : ' + arrayDate.length);
		
		if(HCPbbb == 1)
			confirmer = 'HCP_현대코스모 / 상무 / 이병재';
		else if(HCPbbb == 2)
			confirmer = 'HCP_현대코스모 / 상무 / 이영우';
		else if(HCPbbb == 3)
			confirmer = 'HCP_현대코스모 / 상무 / 유병문';
		else 
			confirmer = 'HCP_현대코스모 / 상무 / 유병문';
		
		
	}else if(sPlant == 8500)	//HSB
	{
		
		console.log('array_pl5.length : ' + array_pl5.length);
		for(var element in array_pl5)
		{
			console.log(element, array_pl5[element].innerText);	
			if(array_pl5[element].innerText != null)
				if(array_pl5[element].innerText.toString().indexOf('20') == 0)
				{
					approvalDate = new Date(array_pl5[element].innerText.toString());
					arrayDate.push(array_pl5[element].innerText.toString());
					//console.log('criteriaDate < approvalDate : ' + (criteriaDateHSB0 < approvalDate));
					if(criteriaDateHSB0 > approvalDate)
						HSBbbb = 1;			//'송규석'
					else if(criteriaDateHSB0 <= approvalDate)
						HSBbbb = 2;			//'조성호'
						
					Last_Item_Date = approvalDate;
				}
		}
		console.log('arrayDate.length : ' + arrayDate.length);
		
		if(HSBbbb == 1)
			confirmer = '안전생산본부장 / 부사장 / 송규석';
		else if(HSBbbb == 2)
			confirmer = '안전생산본부장 / 부사장 / 조성호';
		else 
			confirmer = '안전생산본부장 / 부사장 / 조성호';
		
	}else if(sPlant == 1000)
	{
		console.log('array_pl5.length : ' + array_pl5.length);
		for(var element in array_pl5)
		{
			console.log(element, array_pl5[element].innerText);	
			if(array_pl5[element].innerText != null)
				if(array_pl5[element].innerText.toString().indexOf('20') == 0)
				{
					approvalDate = new Date(array_pl5[element].innerText.toString());
					arrayDate.push(array_pl5[element].innerText.toString());
					console.log('HDObbb : ' + HDObbb);
					if(criteriaDateHDO0 > approvalDate)
						HDObbb = 1;			//'정혜원'
					else if(criteriaDateHDO1 <= approvalDate)
						HDObbb = 3;			//'정임주'
					else
						HDObbb = 2;			//'고영규'
						
					Last_Item_Date = approvalDate;
				}
		}
		console.log('arrayDate.length : ' + arrayDate.length);
		
		if(HDObbb == 1)
			confirmer = '안전생산본부장 / 부사장 / 정해원';
		else if(HDObbb == 2)
			confirmer = '안전생산본부장 / 부사장 / 고영규';
		else if(HDObbb == 3)
			confirmer = '안전생산본부장 / 부사장 / 정임주';
		else
			confirmer = '안전생산본부장 / 부사장 / 정임주';

	}else{
		confirmer = '해당 위원장을 확인 하여 주십시오....' + sPlant;
	}
	
	/* 
	else
	{
		if(sTimeDay == null)
			$('#div00Name').text('생산부문 / 부장 / 유병문 ');
		else if(sTimeDay < '20201123'){								//20201123 인사발령기준 분기추가
			$('#div00Name').text('생산부문 / 상무보 / 송규석');
		}else{
			$('#div00Name').text('생산부문 / 부장 / 유병문 ');
		}      	
	}*/
	console.log('confirmer 최종결과 : ' + confirmer);
	$('#confirmer').text(confirmer);
	//$('input[name="div0199111"]').val([commitChoice]);

	// HSW 20240613 PORC위원장 날짜 적용 (PORC위원 중 마지막 날짜+30분) 
	if(Last_Item_Date != '' && Last_Item_Date != null){
		//yoo 240219
		Last_Item_Date.setMinutes(Last_Item_Date.getMinutes() + 30);
	    var month = Last_Item_Date.getMonth() + 1;
	   	var day = Last_Item_Date.getDate();
	   	var hour = Last_Item_Date.getHours();
	   	var minute = Last_Item_Date.getMinutes();
	   	var second = Last_Item_Date.getSeconds();
	
	    month = month >= 10 ? month : '0' + month;
	    day = day >= 10 ? day : '0' + day;
	    //hour = hour >= 10 ? hour : '0' + hour;
	    //minute = minute >= 10 ? minute : '0' + minute;
	    //second = second >= 10 ? second : '0' + second;
	
	    //dateString =  Last_Item_Date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute;
	    dateString =  Last_Item_Date.getFullYear() + '-' + month + '-' + day;		// + ' ' + hour + ':' + minute;
	 	  
		//$('#TechSpanTime').text(sTime);
		$('#TechSpanTime').text(dateString);
		
		
        // PORC 위원장 Radio 선택 조건 적용
		for(var element in array_radio)
		{   
			//console.log(element, array_radio[element].innerText);	
			if(array_radio[element].checked) {
				commitChoice = array_radio[element].value;
				
				console.log('commitChoice : ' + commitChoice);
				if(commitChoice == "1")
					$('input[name="div0199111"]').val([1]);	//동의
				else if(commitChoice == "2")
				{
					$('input[name="div0199111"]').val([2]);	//'반대'
					break;
				}					
			}
		}		
        
    }
	
	//기술검토자 생성유효성검증 
//	if(radioTach != $('input[name="div999"]:checked').val()){    	 
    	//체크여부확인	 
//    	if(radioTach > 0){    		
//    		$('input[name="div0199111"]').val([radioTach]);
    		
    		//기술위원장 기준 +2
//      		if (sTime.length > 1) {
      			/*
          		hPar = (hPar*1) + 2;
          		hPar = (hPar > 24) ? 1 : hPar;
          		hPar = (hPar < 10) ? "0"+1 : hPar;
          		
          		sTime = sTime.substring(0, 10) + " " + String(hPar)+ ":" + mPar;
          		
          	    */
          	  //yoo 240219
//         	    Last_Item_Date.setMinutes(Last_Item_Date.getMinutes() + 30);
//	          	var month = Last_Item_Date.getMonth() + 1;
//	          	var day = Last_Item_Date.getDate();
//	          	var hour = Last_Item_Date.getHours();
//	          	var minute = Last_Item_Date.getMinutes();
//	          	var second = Last_Item_Date.getSeconds();
	
//	            month = month >= 10 ? month : '0' + month;
//	            day = day >= 10 ? day : '0' + day;
	            //hour = hour >= 10 ? hour : '0' + hour;
	            //minute = minute >= 10 ? minute : '0' + minute;
	            //second = second >= 10 ? second : '0' + second;
	
	            //dateString =  Last_Item_Date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute;
//	            dateString =  Last_Item_Date.getFullYear() + '-' + month + '-' + day;		// + ' ' + hour + ':' + minute;
            
          	  
          		//$('#TechSpanTime').text(sTime);
//          		$('#TechSpanTime').text(dateString);
//          		$('#confirmer').text(confirmer);
//      		}
//    	}
//   	}

/*  
	//공무검토자 유효성검증 
    else if(radioBuilds != $('input[name="div999"]:checked').val()){
    	 
    	if(radioBuilds > 0){    	
    		$('input[name="div0199111"]').val([radioBuilds]);
    	}

    	if(radioBuilds > 0){    		
    		$('input[name="div0199111"]').val([radioBuilds]);
    		
    		//공무위원장 기준 +2
    		var sTime05 = $('#div05span0').text();
    		
    		
    		//alert(sTime);
    		var hPar =  sTime05.substring(11, 13);
      		var mPar =  sTime05.substring(14, 16);
      		
      		if (sTime05.length > 1) {
          		hPar = (hPar*1) + 2;
          		hPar = (hPar > 24) ? 1 : hPar;
          		hPar = (hPar < 10) ? "0"+1 : hPar;
          		
          		sTime05 = sTime05.substring(0, 10) + " " + String(hPar)+ ":" + mPar;
          		//sTime05 = sTime05.substring(0, 10);		//yoo 시간은 뺀다 240223 + " " + String(hPar)+ ":" + mPar;
          		
          		$('#TechSpanTime').text(sTime05);
          		$('#confirmer').text(confirmer);
      		}
    	}    	
    	
    	
    	//기본값출력용(날짜가 없을때 디폴트로 보일값 출력)    
        if(sPlant!='8700'){
        		$('#div00Name').text(confirmer);      				
    		}else{
    			$('#div00Name').text('생산부문 / 부장 / 유병문');
    		}   
   	 }else{
   		 
   	//기술위원장 기준 +2
  		
  		//날짜가 있을때
  		$('#div00Name').text(confirmer);

  		if (sTime.length > 1) {
      		hPar = (hPar*1) + 2;
      		hPar = (hPar > 24) ? 1 : hPar;
      		hPar = (hPar < 10) ? "0"+1 : hPar;
      		
      		sTime = sTime.substring(0, 10) + " " + String(hPar)+ ":" + mPar;
      		//sTime = sTime.substring(0, 10);			//yoo 240223 + " " + String(hPar)+ ":" + mPar;
      		
      		$('#TechSpanTime').text(sTime);
      		$('#confirmer').text(confirmer);
  		}
   		 

   	}
*/
    
    $(".empSearch").click(function() {
         $.popupPopup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".save").click(function () {
    	var msg = "";
        if($("#selectVal").val()=="2") {
        	msg = "PORC 규정에 따라 위원님의 ‘반대’로 인해 본 MR전체가 취소됩니다. 반대하시겠습니까?";
        } else {
        	msg = "저장 하시겠습니까?";
        }

        if(confirm(msg)) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrJobsPorcWriteSave.do").submit();
            //window.open("", "_self").close();
        }
    }
    );

    $(".clCd01").click(function (){
    	$("#selectVal").val($(this).val());
    });

    $.selectLoad({
        className : "empClCd",
        url : "../codeList.do?mrCommGrpCd=MR_PROC&mrCommCd=Z02L",
        defaultText : "선택",
        selectValue : "${register.reqClCd}"+""
    });

    $(".addEmp").click(function () {
        var addClass ="";
        var tr = "";
        var isCheck=0;
        if(confirm("기술검토자를 추가하시겠습니까?")) {
            switch ($(".empClCd").val()) {
            case "Z02L1":
                addClass = "tech";
                break;
            case "Z02L2":
                addClass = "check";
                break;
            case "Z02L3":
                addClass = "drive";
                break;
            case "Z02L4":
                addClass = "safety";
                break;
            case "Z02L5":
                addClass = "build";
                break;
            case "Z02L6":
                addClass = "etc";
                break;
            }

            $("." + addClass).children("tbody").children("tr").each(function(){
                if($(this).find("."+addClass+"Value").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            if(isCheck>0) {
                alert("이미 추가된 검토자 입니다.");
                return;
            }

            tr +="<tr><td>";
            tr +="<span class='vam "+addClass+"Text'>"+$(".empTeamText").val()+" / "+$(".empDutyText").val()+" / "+$(".empText").val()+"</span><img src='../images/btn_del.gif' class='vam cursor delEmp BAA BBA' />";
            tr +="<input type='hidden' class='"+addClass+"Value' name='appLine[2].chrgEmpNo' value='"+$(".empValue").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"Code' name='appLine[2].chrgrClCd' value='"+$(".empClCd").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"DutyText' name='appLine[2].thdayPos' value='"+$(".empDutyText").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"TeamText' name='appLine[2].thdayTeam' value='"+$(".empTeamText").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"TeamValue' name='appLine[2].chrgTeamNo' value='"+$(".empTeamValue").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"FstProcTrmDt' name='appLine[2].fstProcTrmDt' value='9999-12-31'/>";
            tr +="</td></tr>";

            $("."+addClass).append(tr);
            $.sort({className : addClass+"Value", fristName : addClass+"s", lastName : "chrgEmpNo"});
            $.sort({className : addClass+"Code", fristName : addClass+"s", lastName : "chrgrClCd"});
            $.sort({className : addClass+"DutyText", fristName : addClass+"s", lastName : "thdayPos"});
            $.sort({className : addClass+"TeamText", fristName : addClass+"s", lastName : "thdayTeam"});
            $.sort({className : addClass+"TeamValue", fristName : addClass+"s", lastName : "chrgTeamNo"});
            $.sort({className : addClass+"FstProcTrmDt", fristName : addClass+"s", lastName : "fstProcTrmDt"});
            $(".empText").val("");
            $(".empText").val("");
            $(".empClCd").val("");
            $(".empDutyText").val("");
            $(".empTeamText").val("");
            $(".empTeamValue").val("");

        }
    });

    $(".clCd01").each(function() {
    	if($(this).attr("disabled")!="disabled" && $(this).attr("checked")=="checked"){
    		$("#selectVal").val($(this).val());
    	}
    });
});
</script>