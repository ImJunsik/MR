<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<form method="post">
    <table class="table_pop_row" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="30%">
        <col width="70%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row">부서명</th>
        <td>
            <div class="form_group">
            <input type="text" id="teamName" name="teamText" class="i_input">
            <input type="hidden" id="plantNo" name="plantNo">
            </div>
        </td>
        </tr>
        </tbody>
    </table>
    <!-- space non 10 -->
    <hr class="divider_none_10" />
    <input type="image" src="../images/btn_inquiry.png">
</form>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="pop_list_350 single">
    <table class="table_pop_col" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="50%">
        <col width="50%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="col">부서ID</td>
            <th scope="col">부서명</td>
        </tr>
        <c:forEach var="teamInfoList" items="${teamInfoList}" >
          <tr class="cursor selectEmp">
              <td><c:out value="${teamInfoList.kostl}"/></td>
              <td><c:out value="${teamInfoList.lctxt}"/></td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
$(window).ready(function() {
	
	if(window.opener.$("#plantNo").val() != undefined){
		$("#plantNo").val(window.opener.$("#plantNo").val());
		//alert(window.opener.$("#plantNo").val());
	}
	$.popupTitle("부서 조회");
    $("form").submit(function(){
        if($("#teamName").val() == null || $("#teamName").val() == ""){

            alert("부서명을 입력하세요.");
            return;

        }
        $("form").attr("action", "costTeamSearch.do").submit();
    });

    $(".selectEmp").mouseover(function() {
        $(this).css("background","#fcf8e3");
    }).mouseleave(function() {
       $(this).css("background","#ffffff");
    }).click(function(){
    	$(opener.location).attr("href","javascript:$.injectValue({text:'"+$($(this).get(0).cells[1]).html()+"', value:'"+$($(this).get(0).cells[0]).html()+"'});");
    	window.open("", "_self").close();
    });

});
</script>