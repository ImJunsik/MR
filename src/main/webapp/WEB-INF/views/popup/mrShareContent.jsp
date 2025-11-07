<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
      <span style="color:red">*완료버튼 클릭시 PORC위원들에게 메일 발송</span>
      <c:if test="${empty jobsPorc.porcNo}">
      <input type="image" src="../images/btn_temp.png" class="cursor save IAD" style="display:none"/>
      <input type="image" src="../images/btn_sendMail.png" class="cursor complete IAD" style="display:none"/>
      </c:if>
</div>

<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${jobsPorc.mrReqNo}"/>"/>
<input type="hidden" id="porcYn" name="porcYn" value="<c:out value="${jobsPorc.porcYn}"/>"/>
<input type="hidden" id="modeClass" name="modeClass" value="<c:out value="${modeClass}"/>"/>
<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="70%">
    </colgroup>
    <tbody>
    <tr>	
        <th scope="row" <c:if test="${modeClass=='IAD'}">rowspan="10"</c:if><c:if test="${modeClass!='IAD'}">rowspan="9"</c:if>>PORC 위원<br /> 지정</th>
        <c:if test="${modeClass=='IAD'}">
        <th scope="row">담당자추가</th>
        <td>
            <div class="form_group">
            <select class="empClCd"></select>
            <input type="text" class="i_input empText" readOnly/>
            <input type="hidden" class="empValue"/>
            <input type="hidden" class="empDutyText"/>
            <input type="hidden" class="empTeamText"/>
            <input type="hidden" class="empTeamValue"/>
            <img src="../images/icon_search.png" class="cursor empSearch IAD" data-role="emp" style="display:none"/>
            <img src="../images/icon_plus.png" class="cursor addEmp" />
            </div>
        </td>
        </tr> 
        <tr>	     
        </c:if>
            
	       <th scope="row"><!-- jobsPorc.plant : ${jobsPorc.plant}-->위원장</th>
	       <td>
	           <table class="table_pop_default drive">  
	               <tr>
	                   <td>
	                        <span id="confirmer" class="vam techText"></span>
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
	                   	 
	                   	  <div class="form_group">
                          
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
	   
    <tr>        
        
    <c:if test="${modeClass!='IAD'}">
        <th scope="row">운전/생산</th>
        <td>
            <table class="table_pop_default drive">
            <c:forEach var="drive" items="${jobsPorc.drives}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam driveText"><c:out value="${drive.thdayTeam}"/> / <c:out value="${drive.thdayPos}"/> / <c:out value="${drive.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="driveValue" name="drives[${loop.index}].chrgEmpNo" value="<c:out value="${drive.chrgEmpNo}"/>"/>
                  <input type="hidden" class="driveCode" name="drives[${loop.index}].chrgrClCd" value="<c:out value="${drive.chrgrClCd}"/>"/>
                  <input type="hidden" class="driveDutyText" name="drives[${loop.index}].thdayPos" value="<c:out value="${drive.thdayPos}"/>"/>
                  <input type="hidden" class="driveTeamText" name="drives[${loop.index}].thdayTeam" value="<c:out value="${drive.thdayTeam}"/>"/>
                  <input type="hidden" class="driveTeamValue" name="drives[${loop.index}].chrgTeamNo" value="<c:out value="${drive.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </c:if>
    </tr>
    <c:if test="${modeClass=='IAD'}">
    <tr>
        <th scope="row">운전/생산</th>
        <td>
            <table class="table_pop_default drive">
            <c:forEach var="drive" items="${jobsPorc.drives}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam driveText"><c:out value="${drive.thdayTeam}"/> / <c:out value="${drive.thdayPos}"/> / <c:out value="${drive.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="driveValue" name="drives[${loop.index}].chrgEmpNo" value="<c:out value="${drive.chrgEmpNo}"/>"/>
                  <input type="hidden" class="driveCode" name="drives[${loop.index}].chrgrClCd" value="<c:out value="${drive.chrgrClCd}"/>"/>
                  <input type="hidden" class="driveDutyText" name="drives[${loop.index}].thdayPos" value="<c:out value="${drive.thdayPos}"/>"/>
                  <input type="hidden" class="driveTeamText" name="drives[${loop.index}].thdayTeam" value="<c:out value="${drive.thdayTeam}"/>"/>
                  <input type="hidden" class="driveTeamValue" name="drives[${loop.index}].chrgTeamNo" value="<c:out value="${drive.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    </c:if>

    <tr>
        <th scope="row">기술</th>
        <td>
            <table class="table_pop_default tech">
            <c:forEach var="tech" items="${jobsPorc.techs}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam techText"><c:out value="${tech.thdayTeam}"/> / <c:out value="${tech.thdayPos}"/> / <c:out value="${tech.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="techValue" name="techs[${loop.index}].chrgEmpNo" value="<c:out value="${tech.chrgEmpNo}"/>"/>
                  <input type="hidden" class="techCode" name="techs[${loop.index}].chrgrClCd" value="<c:out value="${tech.chrgrClCd}"/>"/>
                  <input type="hidden" class="techDutyText" name="techs[${loop.index}].thdayPos" value="<c:out value="${tech.thdayPos}"/>"/>
                  <input type="hidden" class="techTeamText" name="techs[${loop.index}].thdayTeam" value="<c:out value="${tech.thdayTeam}"/>"/>
                  <input type="hidden" class="techTeamValue" name="techs[${loop.index}].chrgTeamNo" value="<c:out value="${tech.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">공무</th>
        <td>
            <table class="table_pop_default build">
            <c:forEach var="build" items="${jobsPorc.builds}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam buildText"><c:out value="${build.thdayTeam}"/> / <c:out value="${build.thdayPos}"/> / <c:out value="${build.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="buildValue" name="builds[${loop.index}].chrgEmpNo" value="<c:out value="${build.chrgEmpNo}"/>"/>
                  <input type="hidden" class="buildCode" name="builds[${loop.index}].chrgrClCd" value="<c:out value="${build.chrgrClCd}"/>"/>
                  <input type="hidden" class="buildDutyText" name="builds[${loop.index}].thdayPos" value="<c:out value="${build.thdayPos}"/>"/>
                  <input type="hidden" class="buildTeamText" name="builds[${loop.index}].thdayTeam" value="<c:out value="${build.thdayTeam}"/>"/>
                  <input type="hidden" class="buildTeamValue" name="builds[${loop.index}].chrgTeamNo" value="<c:out value="${build.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">설계/검사</th>
        <td>
            <table class="table_pop_default check">
            <c:forEach var="check" items="${jobsPorc.checks}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam checkText"><c:out value="${check.thdayTeam}"/> / <c:out value="${check.thdayPos}"/> / <c:out value="${check.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="checkValue" name="checks[${loop.index}].chrgEmpNo" value="<c:out value="${check.chrgEmpNo}"/>"/>
                  <input type="hidden" class="checkCode" name="checks[${loop.index}].chrgrClCd" value="<c:out value="${check.chrgrClCd}"/>"/>
                  <input type="hidden" class="checkDutyText" name="checks[${loop.index}].thdayPos" value="<c:out value="${check.thdayPos}"/>"/>
                  <input type="hidden" class="checkTeamText" name="checks[${loop.index}].thdayTeam" value="<c:out value="${check.thdayTeam}"/>"/>
                  <input type="hidden" class="checkTeamValue" name="checks[${loop.index}].chrgTeamNo" value="<c:out value="${check.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">안전/환경</th>
        <td>
            <table class="table_pop_default safety">
            <c:forEach var="safety" items="${jobsPorc.safetys}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam safetyText"><c:out value="${safety.thdayTeam}"/> / <c:out value="${safety.thdayPos}"/> / <c:out value="${safety.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="safetyValue" name="safetys[${loop.index}].chrgEmpNo" value="<c:out value="${safety.chrgEmpNo}"/>"/>
                  <input type="hidden" class="safetyCode" name="safetys[${loop.index}].chrgrClCd" value="<c:out value="${safety.chrgrClCd}"/>"/>
                  <input type="hidden" class="safetyDutyText" name="safetys[${loop.index}].thdayPos" value="<c:out value="${safety.thdayPos}"/>"/>
                  <input type="hidden" class="safetyTeamText" name="safetys[${loop.index}].thdayTeam" value="<c:out value="${safety.thdayTeam}"/>"/>
                  <input type="hidden" class="safetyTeamValue" name="safetys[${loop.index}].chrgTeamNo" value="<c:out value="${safety.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">기타</th>
        <td>
            <table class="table_pop_default etc">
            <c:forEach var="etc" items="${jobsPorc.etcs}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam etcText"><c:out value="${etc.thdayTeam}"/> / <c:out value="${etc.thdayPos}"/> / <c:out value="${etc.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="etcValue" name="etcs[${loop.index}].chrgEmpNo" value="<c:out value="${etc.chrgEmpNo}"/>"/>
                  <input type="hidden" class="etcCode" name="etcs[${loop.index}].chrgrClCd" value="<c:out value="${etc.chrgrClCd}"/>"/>
                  <input type="hidden" class="etcDutyText" name="etcs[${loop.index}].thdayPos" value="<c:out value="${etc.thdayPos}"/>"/>
                  <input type="hidden" class="etcTeamText" name="etcs[${loop.index}].thdayTeam" value="<c:out value="${etc.thdayTeam}"/>"/>
                  <input type="hidden" class="etcTeamValue" name="etcs[${loop.index}].chrgTeamNo" value="<c:out value="${etc.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
</table>
</form>

<script>
var totalPorcCnt = 0;
$(window).ready(function() {
	
	
	///////////////////////////////yoo 20240315 위원장 표시
	
	//202001 porc 로직 변경
	//기술이나 공무 레디오버튼 체크여부 확인    	
	var radioTach = $('input[name="div010"]:checked').val();		//기술010
    var radioBuilds = $('input[name="div050"]:checked').val();		//공무050
    var sPlant = $('#div00spanPlant').text();
    //yoo 20211201 PORC 작성, 전체 아이템 체크에 따른 위원장 표시
    var confirmer = '';	/* = document.getElementById('confirmer');*/
    var commitChoice;
    var criteriaDateHDO0 = new Date('2021-11-01');
    var criteriaDateHDO1 = new Date('2023-11-10');    
    var criteriaDateHCP0 = new Date('2017-12-01');  
    var criteriaDateHCP1 = new Date('2024-07-01');			//yoo 240828 추가
    var criteriaDateHSB0 = new Date('2023-12-01');
    
    var approvalDate;
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
	var arrayDate = new Array();
	var HDObbb = 0;		//'정해원'
	var Last_Item_Date = '';		//yoo
	var HSBbbb = 0;		//'송규석'
	var HCPbbb = 0;		//'이병재'
	
	
	if(sPlant == 8000)	//HCP yoo 240611 HCP부분 추가
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
					//console.log('criteriaDate < approvalDate : ' + (criteriaDateHCP0 < approvalDate));
					if(criteriaDateHCP0 > approvalDate)
						HCPbbb = 1;			//'이병재'
					else if(criteriaDateHCP1 <= approvalDate)
						HCPbbb = 3;			//'유뱡문'
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
	

	console.log('confirmer 최종결과 : ' + confirmer);
	$('#confirmer').text(confirmer);
	
	
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
		//$('#confirmer').text(confirmer);
		
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
  
	//공무검토자 유효성검증 
/*    else if(radioBuilds != $('input[name="div999"]:checked').val()){
    	 
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
	
	//////////////////////////////////
	
	if($.getParameter('c') == 'c')
	{
		alert('메일이 전송 되었습니다.');	
		close();
	}
	totalPorcCnt = parseInt("${jobsPorc.drives.size()}")+parseInt("${jobsPorc.techs.size()}")+parseInt("${jobsPorc.builds.size()}")+parseInt("${jobsPorc.checks.size()}")+parseInt("${jobsPorc.safetys.size()}")+parseInt("${jobsPorc.etcs.size()}");

	$.popupTitle("공유 메일 수신자");
    var jobClass= "";



    $(".empSearch").click(function() {
         $.popupPopup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".save").click(function () {
    	if(opener.porcLineListCnt)
    	{
    		opener.porcLineListCnt.value = totalPorcCnt;
    	}
        if(confirm("저장 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrJobsPorcSave.do?porcYn=S").submit();
        }
    });

    $(".complete").click(function () {
    	if($.validation()){
        	if(opener.porcLineListCnt)
        	{
        		opener.porcLineListCnt.value = totalPorcCnt;
        	}
            if(confirm("PORC위원 지정을 완료하시겠습니까? 완료후에는 수정이 불가능합니다.")) {
                $.removeComma();
                $('#pageLoadingWrap').show();
                $("form").attr("action", "mrJobsPorcMail.do").submit();
                //window.open("", "_self").close();
                //setTimeout(tryClose, 2000);
            }
    	}
    });
/*
    function tryClose() {
    	console.log('== close!!');
   	  close();
   	}
*/
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
        if(confirm("PORC 위원을 추가하시겠습니까?")) {
            switch ($(".empClCd").val()) {
            case "Z02L1":
                addClass = "drive";
                break;
            case "Z02L2":
                addClass = "tech";
                break;
            case "Z02L3":
                addClass = "build";
                break;
            case "Z02L4":
                addClass = "check";
                break;
            case "Z02L5":
                addClass = "safety";
                break;
            case "Z02L6":
                addClass = "etc";
                break;
            }
            totalPorcCnt++;
            $(".tech").children("tbody").children("tr").each(function(){
                if($(this).find(".techValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".check").children("tbody").children("tr").each(function(){
                if($(this).find(".checkValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".drive").children("tbody").children("tr").each(function(){
                if($(this).find(".driveValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".safety").children("tbody").children("tr").each(function(){
                if($(this).find(".safetyValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".check").children("tbody").children("tr").each(function(){
                if($(this).find(".checkValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".etc").children("tbody").children("tr").each(function(){
                if($(this).find(".etcValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            if(isCheck>0) {
                alert("PORC위원은 중복선정이 불가능합니다.");
                return;
            }

            tr +="<tr><td>";
            tr +="<span class='vam "+addClass+"Text'>"+$(".empTeamText").val()+" / "+$(".empDutyText").val()+" / "+$(".empText").val()+"</span><img src='../images/icon_minus.png' class='vam cursor delEmp BAA BBA' />";
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


});

$.getParameter = function(sname){
	var params = location.search.substr(location.search.indexOf("?") + 1);

    var sval = "";

    params = params.split("&");

    for (var i = 0; i < params.length; i++) {

        temp = params[i].split("=");

        if ([temp[0]] == sname) { sval = temp[1]; }

    }

    return sval;
}

$.validation = function(){
    var isValid = false;
    var porcCount = 0;
    $(".drive").children("tbody").children("tr").each(function () {
    	porcCount++;
    });

    $(".tech").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".build").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".check").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".safety").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".etc").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    if(porcCount>0 ) {
    	isValid = true;
    } else  {
    	alert("PORC 담당자를 입력하세요.");
    }

    return isValid;
};

$(document).on("click",".delEmp", function () {
    var removeClass="";
    if(confirm("기술검토자를 삭제 하시겠습니까?")) {
    	totalPorcCnt--;
        removeClass = $(this).parent("td").parent("tr").parent("tbody").parent("table").attr("class");
        $(this).parent("td").parent("tr").remove();
        $.sort({className : removeClass+"Value", fristName : removeClass+"s", lastName : "chrgEmpNo"});
        $.sort({className : removeClass+"Code", fristName : removeClass+"s", lastName : "chrgrClCd"});
        $.sort({className : removeClass+"DutyText", fristName : removeClass+"s", lastName : "thdayPos"});
        $.sort({className : removeClass+"TeamText", fristName : removeClass+"s", lastName : "thdayTeam"});
        $.sort({className : removeClass+"TeamValue", fristName : removeClass+"s", lastName : "chrgTeamNo"});
    }
});



</script>