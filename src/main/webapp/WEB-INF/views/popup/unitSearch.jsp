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
        <th scope="row">플랜트</th>
        <td>
        	<c:out value="${unitVo.plantName}"/>
        		<input type="hidden" id="plantName" name="plantName" value="<c:out value="${unitVo.plantName}"/>">
        		<input type="hidden" id="plantNo" name="plantNo" value="<c:out value="${unitVo.plantNo}"/>">
        </td>
        <th scope="row">공정코드/공정이름</th>
        <td>
          <div class="form_group">
          <input type="text" id="mrProcName" name="mrProcName"  class="i_input" value="">
          </div>
        </td>
        </tr>
        </tbody>
    </table>
</form>
<!-- space non 10 -->
<hr class="divider_none_10" />
<input id="search" type="image" src="../images/btn_inquiry.png">
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="pop_list_320 tri_left">
    <table class="table_pop_col searchList" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="20%">
        <col width="70%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="col">선택</th>
            <th scope="col">코드</th>
            <th scope="col">공정이름</th>
        </tr>
        </tbody>
    </table>
</div>
<div class="pop_list_320 tri_center">
    <p><input type="image" id="select" src="../images/arrow_plus.png" alt="추가" /></p>
</div>
<div class="pop_list_320 tri_right">
    <table class="table_pop_col selectList" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="20%">
        <col width="70%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="col">선택</th>
            <th scope="col">코드</th>
            <th scope="col">공정이름</th>
        </tr>
        <c:forEach var="proc" items="${unitVo.procs}">
          <tr>
              <td><input type='checkbox' class='check' checked/></td>
              <td><c:out value="${proc.mrProcNo}"/></td>
              <td><c:out value="${proc.mrProcName}"/></td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- footer -->
<div id="pop_footer">
    <input type="image" id="add" name="" src="../images/btn_p_confirm.png" alt="확인" />
</div>
<!-- //footer -->
<script>
$(document).on("keydown", "input", function (e) {
      if (e.keyCode == 13) {
    	  $("#search").click();
    	  return false;
      }
});

$(window).ready(function() {
	$.popupTitle("공정조회");



    $("#search").click(function(){
        var tr ="";
        var count = 0;
        var formData = $("form").serialize();

        if($("#mrProcName").val() == null || $("#mrProcName").val() == ""){
            alert("공정명은 반드시 입력하여야 합니다.");
            return;
        }

        $(".searchList").find("tr:gt(0)").remove().end();

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"unitSearchAjax.do",
               data:formData,
               dataType:"JSON",
               success : function(data) {
            	   if(data.searchList!=null) {
                       $.each(data.searchList, function() {
                           count++;
                           tr = "";
                           tr += "<tr>";
                           tr += "<td><input type='checkbox' class='check'/></td>";
                           tr += "<td>"+this.mrProcNo+"</td>";
                           tr += "<td>"+this.mrProcName+"</td>";
                           tr += "<tr>";
                           $(".searchList").append(tr);
                       });
            	   }
                   tr = "";
                   tr = tr +"<tr>";
                   tr = tr +"<td colspan='3' class='foot'>총 "+count+ " 건이 조회 되었습니다.</td>";
                   tr = tr +"<tr>";
                   $(".searchList").append(tr);
               }
        });
    });

    $("#select").click(function(){
    	var tr = "";
    	var code = "";
    	var name = "";
    	var count = 0;
        $(".searchList").children("tbody").children("tr:gt(0)").each(function (){
            code = "";
            name = "";
            count = 0;
                if($(this).children("td:eq(0)").children("input").is(":checked")) {

                	code = $(this).children("td:eq(1)").html();
                	name = $(this).children("td:eq(2)").html();
                	$(".selectList").children("tbody").children("tr:gt(0)").each(function (){
                		if(code == $(this).children("td:eq(1)").html() && name == $(this).children("td:eq(2)").html()) {
                			count++;
                		}
                	});

                	if(count==0) {
                    	tr ="";
                        tr += "<tr>";
                        tr += "<td><input type='checkbox' class='check' checked/></td>";
                        tr += "<td>"+code+"</td>";
                        tr += "<td>"+name+"</td>";
                        tr += "<tr>";
                        $(".selectList").append(tr);
                	}
                }
        });
    });


    $("#add").click(function() {
        var unitNo, unitName;
        $(".selectList").children("tbody").children("tr:gt(0)").each(function (){
                if($(this).children("td:eq(0)").children("input").is(":checked")) {
                	unitNo = $(this).children("td:eq(1)").html();
                	unitName = $(this).children("td:eq(2)").html();
                    tr ="";
                    tr += "<tr>";
                    tr += "<td>"+unitNo+"<input type='hidden' class='mrProcNo' name='unitNo' value='"+unitNo+"'></td>";
                    tr += "<td>"+unitName+"<input type='hidden' class='mrProcName' name='unitNo' value='"+unitName+"'></td>";
                    tr += "<tr>";
                    $("#selectUnit", opener.document).append(tr);
                }
        });
        $(opener.location).attr("href","javascript:$.sortUintList();");
        window.open("", "_self").close();
    });

});
</script>