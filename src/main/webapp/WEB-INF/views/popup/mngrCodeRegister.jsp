<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
    <input id="save" type="image" src="../images/btn_save.png">
</div>
<form id="registerForm" method="post">
<table class="table_row" border="1" cellspacing="0" >
    <tr>
        <th scope="row">그룹코드</th>
        <td><select class="grpCode" name="mrCommGrpCd"></select></td>
        <th scope="row">코드</th>
        <td>
              <div class="form_group">
              <input type="text" name="mrCommCd" class="i_input" value="${commCdVO.mrCommCd}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">그룹코드명</th>
        <td>
              <div class="form_group">
              <input type="text" name="mrCommGrpNm" class="i_input mrCommGrpNm" value="${commCdVO.mrCommGrpNm}" readOnly/>
              </div>
        </td>
        <th scope="row">코드명</th>
        <td>
              <div class="form_group">
              <input type="text" name="mrCommNm" class="i_input" value="${commCdVO.mrCommNm}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">SAP 코드</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="sapCd" class="i_input" value="${commCdVO.sapCd}" readonly/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">SAP 코드명</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="sapNm" class="i_input" value="${commCdVO.sapNm}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">정렬순서</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="priority" class="i_input" value="${commCdVO.priority}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">확장1</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="div1" class="i_input" value="${commCdVO.div1}"/>&nbsp;(업무대행 : 시작일자 'YYYYMMDD')
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">확장2</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="div2" class="i_input" value="${commCdVO.div2}"/>&nbsp;(업무대행 : 종료일자 'YYYYMMDD')
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">확장3</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="div3" class="i_input" value="${commCdVO.div3}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">확장4</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="div4" class="i_input" value="${commCdVO.div4}"/>
              </div>
        </td>
    </tr>
    <tr>
        <th scope="row">확장5</th>
        <td colspan="3">
              <div class="form_group">
              <input type="text" name="div5" class="i_input" value="${commCdVO.div5}"/>
              </div>
        </td>
    </tr>
</table>
</form>

<script>
$(document).ready(function() {
	   $.popupTitle("기준정보 관리");
	if("${message}" != null && "${message}" != "" ) {
		alert("${message}");
		window.open("", "_self").close();
	}

    $.selectLoad({
        className : "grpCode",
        url : "../codeList.do?mrCommGrpCd=XXX",
        defaultText : "선택",
        selectValue : "${commCdVO.mrCommGrpCd}"
    });

    $("#save").click(function(){
    	$("form").attr("action", "${saveURL}").submit();
    });

});

$(document).on("change", ".grpCode", function () {
	$(".mrCommGrpNm").val($(".grpCode option:selected").text());
});

</script>