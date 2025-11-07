<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title"> 기준정보</h4>
</div>

<!-- button -->
<div class="button_top">
    <input id="search" type="image" src="images/btn_inquiry.png">
    <input id="new" type="image" src="images/btn_new.png">
    <input id="delete" type="image" src="images/btn_delete.png">
</div>

<div id="content_wrap">
<form id="searchForm" method="post">
    <table class="table_row" border="1" cellspacing="0" >
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
            <th scope="row">그룹코드</th>
            <td><select class="grpCode" name="mrCommGrpCd"></select></td>
            <th scope="row">코드</th>
            <td><span class="codeWarning">그룹코드를 먼저 선택하세요.</span><select class="code" name="mrCommCd" style="display:none"></select></td>
        </tr>
        <tr>
            <th scope="row">그룹코드명</th>
            <td>
                  <div class="form_group">
                  <input type="text" name="mrCommGrpNm" class="i_input"/>
                  </div>
            </td>
            <th scope="row">코드명</th>
            <td>
                  <div class="form_group">
                  <input type="text" name="mrCommNm" class="i_input"/>
                  </div>
            </td>
        </tr>
        <tr>
            <th scope="row">SAP 코드명</th>
            <td colspan="3">
                  <div class="form_group">
                  <input type="text" name="sapNm" class="i_input"/>
                  </div>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<!-- space non 10 -->
<hr class="divider_none_10" />


<form id="selectForm" method="post">
    <table class="table_col searchList" border="1" cellspacing="0">
    </table>
</form>
</div>
<script>
$(document).ready(function() {

    $.selectLoad({
        className : "grpCode",
        url : "codeList.do?mrCommGrpCd=XXX",
        defaultText : "선택"
    });

    $("#new").click(function(){
    	$.popup({url:"popup/mngrCodeRegister.do", width:"500", height:"500"});
    });

    $("#search").click(function(){
        var tr ="";
        var count = 0;
        var formData = $("form").serialize();
        $(".searchList").find("tr").remove().end();
        tr ="<tr>";
        tr += "<th scope='col'>선택</th>";
        tr += "<th scope='col'>그룹코드</th>";
        tr += "<th scope='col'>그룹코드명</th>";
        tr += "<th scope='col'>코드</th>";
        tr += "<th scope='col'>코드명</th>";
        tr += "<th scope='col'>정렬</th>";
        tr += "<th scope='col'>SAP 코드</th>";
        tr += "<th scope='col'>SAP 코드명</th>";
        tr += "<th scope='col'>삭제여부</th>";
        tr += "</tr>";

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"mngrCodeAjax.do",
               data:formData,
               dataType:"JSON",
               success : function(data) {
                   if(data.list!=null) {
                         $.each(data.list, function() {
                           count++;
                             tr = "";
                             tr += "<tr>";
                             tr += "<td><input type='checkbox' class='check'/></td>";
                             tr += "<td class='cursor modifyCode'>"+this.mrCommGrpCd+"<input type='hidden' class='commGrpCd' name='' value='"+this.mrCommGrpCd+"'></td>";
                             tr += "<td class='cursor modifyCode'>"+this.mrCommGrpNm+"</td>";
                             tr += "<td class='cursor modifyCode'>"+this.mrCommCd+"<input type='hidden' class='commCd' name='' value='"+this.mrCommCd+"'></td>";
                             tr += "<td class='cursor modifyCode'>"+this.mrCommNm+"</td>";
                             tr += "<td class='cursor modifyCode'>"+this.priority+"</td>";
                             tr += "<td class='cursor modifyCode'>"+this.sapNm+"</td>";
                             tr += "<td class='cursor modifyCode'>"+this.sapCd+"</td>";
                             tr += "<td class='cursor modifyCode'>"+this.delYn+"</td>";
                             tr += "<tr>";
                             $(".searchList").append(tr);
                          });
                   }
                   tr = "";
                   tr = tr +"<tr>";
                   tr = tr +"<td colspan='10' class='foot'>총 "+count+ " 건이 조회 되었습니다.</td>";
                   tr = tr +"<tr>";
                   $(".searchList").append(tr);
               }
        });

    });

});


$(document).on("mouseover",".modifyCode",function() {
    $(this).parent("tr").css("background","#fcf8e3");
}).on("mouseleave",".modifyCode",function() {
	$(this).parent("tr").css("background","#ffffff");
}).on("click",".modifyCode",function() {
	$.popup({url:"popup/mngrCodeRegister.do?mrCommGrpCd="+$(this).parent("tr").children("td:eq(1)").children(".commGrpCd").val()+"&mrCommCd="+$(this).parent("tr").children("td:eq(3)").children(".commCd").val(), width:"500", height:"500"});
});


$(document).on("click","#delete",function() {
	var count = 0;

	$(".searchList").children("tbody").children("tr:gt(0)").each(function () {
		if($(this).children("td:eq(0)").children(".check").is(":checked")) {
			$(this).children("td:eq(1)").children(".commGrpCd").attr("name","codes["+count+"].mrCommGrpCd");
			$(this).children("td:eq(3)").children(".commCd").attr("name","codes["+count+"].mrCommCd");
			count++;
		}
	});
	if(count<1) {
		alert("삭제할 코드를 선택하세요.");
		return;
	}
	if(confirm("총 "+count+"건을 삭제하시겠습니까?"))
	$("#selectForm").attr("action", "mngrCodeDelete.do").submit();
});

</script>