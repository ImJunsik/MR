<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<form method="post">
    <table class="table_pop_row" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="35%">
        <col width="15%">
        <col width="35%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row">이름</th>
        <td>
            <div class="form_group">
            <input type="text" id="empName" name="empName" class="i_input">
            </div>
        </td>
        <th scope="row">부서</th>
        <td>
            <div class="form_group">
            <input type="text" id="teamName" name="teamName" class="i_input">
            </div>
        </td>
        </tr>
        </tbody>
    </table>
    <!-- space non 10 -->
    <hr class="divider_none_10" />
    <span style="color:blue">※  이름에"(부재중)"표시가 있는 경우 "(업무대행)"자를 선택한다.</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" src="../images/btn_inquiry.png">
</form>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="pop_list_350 single">
    <table class="table_pop_col" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="25%">
        <col width="25%">
        <col width="25%">
        <col width="25%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="col">사원번호</th>
            <th scope="col">부서</th>
            <th scope="col">직책</th>
            <th scope="col">이름</th>
        </tr>
        <c:forEach var="empInfo" items="${empInfoList}" >
          <tr class="cursor selectEmp">
              <td><c:out value="${empInfo.empNo}"/></td>
              <td><c:out value="${empInfo.teamName}"/></td>
              <td><c:out value="${empInfo.dutyName}"/></td>
              <td><c:out value="${empInfo.empName}"/></td>
              <td style="display:none"><c:out value="${empInfo.teamNo}"/></td>
          </tr>
        </c:forEach>
    </tbody>
    </table>
</div>

<script>
$(window).ready(function() {
	$.popupTitle("사원 조회");

    $("form").submit(function(){
        if(($("#empName").val() == null || $("#empName").val() == "") && ($("#teamName").val() == null || $("#teamName").val() == "")){

            alert("이름 부서중 하나는 반드시 입력하여야 합니다.");
            return;

        }
        $("form").attr("action", "empSearch.do").submit();
    });

    $(".selectEmp").mouseover(function() {
        $(this).css("background","#fcf8e3");
    }).mouseleave(function() {
       $(this).css("background","#ffffff");
    }).click(function(){
    	$(opener.location).attr("href","javascript:$.injectValue({text:'"+$($(this).get(0).cells[3]).html()+"', value:'"+$($(this).get(0).cells[0]).html()+"', dutyText:'"+$($(this).get(0).cells[2]).html()+"', teamText:'"+$($(this).get(0).cells[1]).html()+"', teamValue:'"+$($(this).get(0).cells[4]).html()+"'});");
     	window.open("", "_self").close();
    });

});
</script>