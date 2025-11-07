<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
<!--  <c:out value="${modeClass}"/>  -->
    <input type="image" id="save" name="close" class="close HAA" style="display:none" src="../images/btn_close.png" />
</div>
<form id="registerForm">
<input type="hidden" name="mrReqNo" value="<c:out value="${register.mrReqNo}"/>"/>
<div class="pop_list_600 single">
<table class="table_pop_col" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="20%">
    <col width="20%">
    <col width="20%">
    <col width="40%">
    <col width="0%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="col">파일구분</th>
        <th scope="col">진행단계</th>
        <th scope="col">상세단계</th>
        <th scope="col">파일명</th>
        <th scope="col" style="display:none">파일명</th>
    </tr>
    <c:forEach var='fileManage' items='${fileManage}'>
    <tr>
        <td>${fileManage.fileCdNm}</td>
        <td>${fileManage.mrStepCdNm}</td>
        <td>${fileManage.fileStepNm}</td>
        <td>${fileManage.fileNm} <img src="../images/btn_inFile.png" class="cursor filePopDown" /></td>
        <td>
        <input type="hidden" value="${fileManage.fileCd}">
        <input type="hidden" value="${fileManage.mrStepCd}">
        <input type="hidden" value="${fileManage.mrReqProcStepDetNo}">
        <input type="hidden" value="${fileManage.fileProcCd}">
        <input type="hidden" class="drawMngNo" value="${fileManage.drawMngNo}">
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</form>
<script>
$(document).ready(function() {
    $.popupTitle("자료등록 관리 - 관련성과품");
 if("${message}" != null && "${message}" != "" ) {
     alert("${message}");
     window.open("", "_self").close();
 }
});
$(window).load(function() {

    if($("#writeDate").html()=="") {
        $("#writeDate").html(currentDate);
    }

    if("${modeClass}"=="HAA"){
        disabled = false;
    }

    $(".filePopDown").click(function() {
        location.href("../download.do?drawMngNo="+$(this).parent("td").parent("tr").children("td").children(".drawMngNo").val());
    });


    $(".close").click(function() {
        self.close();
    });

});

</script>
