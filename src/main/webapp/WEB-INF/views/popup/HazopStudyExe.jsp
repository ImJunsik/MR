<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<!-- button -->
<div class="pop_button_top">
    <input type="image" src="../images/btn_save.png" style="display:none" class="cursor save IAD"/>
</div>
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${mrReqNo}"/>"/>
    <input type="hidden" id="mrNo" value="<c:out value="${hazop.mrNo}"/>"/>
    <input type="hidden" id="plantNo" value="<c:out value="${hazop.plant}"/>"/>
<input type="hidden" name="itemCd" value="HAZOP"/>
<table class="table_pop_row" border="1" cellspacing="0">
 <caption class="blind"></caption>
     <colgroup>
    <col width="15%">
    <col width="20%">
    <col width="20%">
    <col width="15%">
    <col width="12%">
    <col width="12%">
     </colgroup>
    <thead>
    <tr>
        <th scope="row">HAZOP Study 일시</th>
        <td colspan="5">
            <div class="form_group">
            <c:if test="${modeClass=='IAD'}">
                <input type="text" name="clCtt01" class="inputDate i_input agreeEndDate" value="<c:out value="${hazop.clCtt01}"/>" readOnly/>
            </c:if>
            <c:if test="${modeClass!='IAD'}">
                <c:out value="${hazop.clCtt01}"/>
            </c:if>
            </div>
        </td>
    </tr>
    
     <tr>
         <th colspan="6" scope="row">위험성평가 팀 구성</th>
     </tr>
     <tr>
         <th scope="row">담당공정 엔지니어</th>
         <td colspan="2">
         	<div class="form_group">  <!--직책과장  value 재설정 필요 DB 확인필요 -->
              <input type="text" class="i_input_200 writerText"  value="<c:out value="${hazop.rgstName}"/>" readOnly/>
              <input type="hidden" class="writerValue" name="rgstId" value="<c:out value="${hazop.rgstId}"/>"/>
            </div>
		 </td>  <!-- 담당공정 엔지니어 재설정 필요 DB 확인필요 -->
         <th rowspan="3" scope="row">설계, 설비 담당자 중 추가 참석자<br>(필요 시)</th>
         <td rowspan="3" colspan="2">
		 	<div class="form_group">
            	<textarea name="peAddName" class="i_text_full IAD" rows="" cols="" disabled="disabled"><c:out value="${hazop.peAddName}"/></textarea>
            </div>
		 </td>   <!--설계, 설비 담당자 value 재설정 필요 DB 확인필요 -->
     </tr>
     <tr>
         <th rowspan="2" scope="row">공정 운전부서<br>직책과장 및 G/F</th>
         <th scope="row">직책과장<font style="color:red">*</font></th>
         <th scope="row">G/F<font style="color:red">*</font></th>
     </tr>
     <tr>
         <td>
         	<div class="form_group">
	         	<input type="text" class="i_input_125 penameText"  value="<c:out value="${hazop.peName}"/>" readOnly/>
		        <input type="hidden" class="penameValue" name="peId" value="<c:out value="${hazop.peId}"/>"/>
	            <img src="../images/icon_search.png" class="vam cursor empSearch IAD" data-role="pename" style="display:none"/>	            
            </div>
         </td>
         <td>
         	<div class="form_group">  <!-- F/F  value 재설정 필요 DB 확인필요 -->
	              <input type="text" class="i_input_125 checkText" value="<c:out value="${hazop.confName}" />" readOnly />
	              <input type="hidden" class="checkValue" name="confId" value="<c:out value="${hazop.confId}" />" />
	              <img src="../images/icon_search.png" class="vam cursor empSearch IAD" data-role="check" style="display:none" />
              </div>
         </td>
     </tr>
     <%-- <tr>
         <th scope="row">담당공정 엔지니어<br /><font style="color:red">(필수)</font></th>
         <td colspan="2">
         	<div class="form_group">
            <c:if test="${modeClass=='IAD'}">
                <input type="text" name="clCmt01" class="i_input_full" value="<c:out value="${hazop.clCmt01}"/>"/>
            </c:if>
            <c:if test="${modeClass!='IAD'}">
                <c:out value="${hazop.clCmt01}"/>
            </c:if>
            </div>
         </td>  <!-- 담당공정 엔지니어 재설정 필요 DB 확인필요 -->
         <th rowspan="3" scope="row">설계, 설비 담당자 중 추가 참석자<br>(필요 시)</th>
         <td rowspan="3" colspan="2">
         	<div class="form_group">
	            <c:if test="${modeClass=='IAD'}">
	                <input type="text" name="clCmt02" class="i_input_full" value="<c:out value="${hazop.clCmt02}"/>"/>
	            </c:if>
	            <c:if test="${modeClass!='IAD'}">
	                <c:out value="${hazop.clCmt02}"/>
	            </c:if>
            </div>
         </td>   
     </tr>
     <tr>
         <th rowspan="2" scope="row">공정 운전부서 직책과장 및 G/F<br><font style="color:red">(필수)</font></th>
         <th scope="row">직책과장</th>
         <th scope="row">G/F</th>
     </tr>
     <tr>
         <td>
         	<div class="form_group">
	            <c:if test="${modeClass=='IAD'}">
	                <input type="text" name="clCmt03" class="i_input_full" value="<c:out value="${hazop.clCmt03}"/>"/>
	            </c:if>
	            <c:if test="${modeClass!='IAD'}">
	                <c:out value="${hazop.clCmt03}"/>
	            </c:if>
            </div>
         </td>
         <td>
         	<div class="form_group">
	            <c:if test="${modeClass=='IAD'}">
	                <input type="text" name="clCmt04" class="i_input_full" value="<c:out value="${hazop.clCmt04}"/>"/>
	            </c:if>
	            <c:if test="${modeClass!='IAD'}">
	                <c:out value="${hazop.clCmt04}"/>
	            </c:if>
            </div>
         </td>
     </tr> --%>
    <tr>
        <th scope="row">HAZOP Study 결과</th>
        <td colspan="5">
           <div class="form_group">
            <c:if test="${modeClass=='IAD'}">
               <textarea name="totRvSugg" rows="" style="height:170px;" cols="" class="i_text_full"><c:out value="${hazop.totRvSugg}"/></textarea>
            </c:if>
            <c:if test="${modeClass!='IAD'}">
               <c:out value="${hazop.totRvSugg}"/>
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
        <col width="16%">
        <col width="84%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row" class="vam fileAdd">첨부파일
          <img id="addFile" src="../images/icon_s_plus.png" border="0" class="cursor IAD addFile" style="display:none"/>
        </th>
        <td class="section_text pl5 pt5 pb5" colspan="3">
          <div class="form_group">
            <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
              <c:forEach var="mrAtchFiles" items="${hazop.mrAtchFiles}" varStatus="loop">
              <tr>
                 <td>
                  <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
                  <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
                  <img src="../images/btn_inFile.png" class="cursor fileDown"/>
                  <img class="cursor IAD removeDownFile" src="../images/icon_minus.png" style="display:none"/>
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
</form>


<script>
$(window).ready(function() {
	
	
	//modeClass : ${modeClass}
	
    $.popupTitle("Hazop Study");

    $(".save").click(function () {
        if(confirm("저장하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrHazopStudySave.do").submit();
        }
    });
    $(".empSearch").click(function() {
        $.popup({popupName:"newWindow",url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });
});
</script>
