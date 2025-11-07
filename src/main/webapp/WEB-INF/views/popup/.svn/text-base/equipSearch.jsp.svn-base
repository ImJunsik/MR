<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<form method="post">
<input type="hidden" name="plant"  value="<c:out value="${plant}"/>">
<div class="pop_list_180 single">
    <table class="table_pop_col" border="1" cellspacing="0">
          <caption class="blind"></caption>
          <colgroup>
          <col width="40%">
          <col width="60%">
          </colgroup>
          <tbody>
          <tr>
          <th scope="col">코드</th>
          <th scope="col">공정이름</th>
          </tr>
        <c:forEach var="unit" items="${units}" varStatus="loop">
          <tr>
              <td><c:out value="${unit.mrProcNo}"/><input type="hidden" name="procs[${loop.index}].mrProcNo"  value="<c:out value="${unit.mrProcNo}"/>"></td>
              <td><c:out value="${unit.mrProcName}"/><input type="hidden" name="procs[${loop.index}].mrProcName"  value="<c:out value="${unit.mrProcName}"/>"></td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- space non 10 -->
<hr class="divider_none_10" />
<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="30%">
    <col width="40%">
    <col width="30%">
    </colgroup>
    <tbody>
    <tr>
    <th scope="row">설비코드/설비이름</th>
    <td>
        <div class="form_group">
        <input type="text" id="mrEquipCd" name="mrEquipCd" class="i_input_full" value="<c:out value="${unitVo.mrEquipCd}"/>">
        </div>
    </td>
    <td>
        <img id="search" class="vam cursor" src="../images/btn_inquiry.png">
    </td>
    </tr>
    </tbody>
</table>
</form>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="pop_list_270 tri_left">
    <table class="table_pop_col searchList" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="15%">
        <col width="30%">
        <col width="15%">
        <col width="30%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="col">선택</th>
        <th scope="col">공정코드</th>
        <th scope="col">공정이름</th>
        <th scope="col">설비코드</th>
        <th scope="col">설비이름</th>
        </tr>
        </tbody>
    </table>
</div>
<div class="pop_list_270 tri_center">
    <input type="image" id="select" src="../images/arrow_plus.png" alt="추가" />
</div>
<div class="pop_list_270 tri_right">
    <table class="table_pop_col selectList" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="10%">
        <col width="30%">
        <col width="60%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="col">선택</th>
        <th scope="col">설비코드</th>
        <th scope="col">설비이름</th>
    </tr>
    <c:forEach var="equip" items="${unitVo.equips}">
    <c:if test="${not empty equip.mrEquipCd}">
      <tr>
        <td><input type='checkbox' class='check' checked/></td>
        <td style='display:none'><c:out value="${equip.mrProcNo}" /></td>
        <td><c:out value="${equip.mrEquipCd}" /></td>
        <td><c:out value="${equip.mrEquipName}" /></td>
      </tr>
      </c:if>
    </c:forEach>
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
	$.popupTitle("설비 조회");
    $("#search").click(function(){

        var tr ="";
        var count = 0;
        var formData = $("form").serialize();

        if(($("#mrEquipCd").val() == null || $("#mrEquipCd").val() == "") && ($("#mrEquipName").val() == null || $("#mrEquipName").val() == "")){

            alert("설비코드와 설비이름중 하나는 반드시 입력하여야 합니다.");
            return;

        }

        $(".searchList").find("tr:gt(0)").remove().end();

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"equipSearchAjax.do",
               data:formData,
               dataType:"JSON",
               success : function(data) {
            	   if(data.searchList!=null){
                         $.each(data.searchList, function() {
                             count++;
                             tr = "";
                             tr += "<tr>";
                             tr += "<td><input type='checkbox' class='check'/></td>";
                             tr += "<td>"+this.mrProcNo+"</td>";
                             tr += "<td>"+this.mrProcName+"</td>";
                             tr += "<td>"+this.mrEquipCd+"</td>";
                             tr += "<td>"+this.mrEquipName+"</td>";
                             tr += "<tr>";
                             $(".searchList").append(tr);
                          });
                   }
                   tr = "";
                   tr = tr +"<tr>";
                   tr = tr +"<td colspan='5' class='foot'>총 "+count+ " 건이 조회 되었습니다.</td>";
                   tr = tr +"<tr>";
                   $(".searchList").append(tr);
               }
        });
    });

    $("#select").click(function(){
        var tr ="";
        var code = "";
        var name = "";
        var porcCd = "";
        var count = 0;
        $(".searchList").children("tbody").children("tr:gt(0)").each(function (){
        	code = "";
        	name = "";
        	porcCd = "";
        	count = 0;
                if($(this).children("td:eq(0)").children("input").is(":checked")) {

                    code = $(this).children("td:eq(3)").html();
                    name = $(this).children("td:eq(4)").html();
                    porcCd = $(this).children("td:eq(1)").html();
                    $(".selectList").children("tbody").children("tr:gt(0)").each(function (){
                        if(code == $(this).children("td:eq(2)").html() && name == $(this).children("td:eq(3)").html() && porcCd == $(this).children("td:eq(1)").html()) {
                            count++;
                        }
                    });

                    if(count==0) {
                        tr = "";
                        tr += "<tr>";
                        tr += "<td><input type='checkbox' class='check' checked/></td>";
                        tr += "<td style='display:none'>"+$(this).children("td:eq(1)").html()+"</td>";
                        tr += "<td>"+$(this).children("td:eq(3)").html()+"</td>";
                        tr += "<td>"+$(this).children("td:eq(4)").html()+"</td>";
                        tr += "<tr>";
                        $(".selectList").append(tr);
                    }
                }
        });
    });


    $("#add").click(function(){
    	    var equipNo, equipName, unitNo;
            var tr ="";
    	    $(".selectList").children("tbody").children("tr:gt(0)").each(function (){
    	    	equipNo = "";
    	    	equipName = "";
    	    	unitNo = "";
    	            if($(this).children("td:eq(0)").children("input").is(":checked")) {
    	            	tr ="";
    	            	unitNo = $(this).children("td:eq(1)").html();
    	            	equipNo = $(this).children("td:eq(2)").html();
    	            	equipName = $(this).children("td:eq(3)").html();
    	                tr += "<tr>";
    	                tr += "<td>"+equipNo+"<input type='hidden' class='equipCd' name='equipNo' value='"+equipNo+"'><input type='hidden' class='procNo' name='unitNo' value='"+unitNo+"'>&nbsp;&nbsp;</td>";  //wj &nbsp추가 
    	                tr += "<td>"+equipName+"<input type='hidden' class='equipName' name='equipNo' value='"+equipName+"'></td>";
    	                tr += "<tr>";
    	                $("#selectEquip", opener.document).append(tr);

    	            }
    	    });
            $(opener.location).attr("href","javascript:$.sortEquipList();");
    	    window.open("", "_self").close();
    });
});
</script>