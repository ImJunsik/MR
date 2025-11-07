<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
    <input type="image" name="comp" class="comp" src="../images/btn_complete.png" />
    <input type="image" name="close" class="close" src="../images/btn_close.png" />
</div>

<form id="ivstEndPopForm">
<input type="hidden" name="mrReqNo" value=""/>
<table class="table_pop_row" border="1" cellspacing="0" summary="">
    <caption class="blind"></caption>
    <colgroup>
    <col width="20%">
    <col width="30%">
    <col width="20%">
    <col width="30%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row">승인취소자</th>
        <td>
           <div class="form_group">
           <input type="text" class="i_input cnclEmpIdText" readOnly/>
           <input type="hidden" class="i_input cnclEmpIdValue" name="cnclEmpId" />
           <img src="/mr/images/icon_search.png" class="cursor empSearch" data-role="cnclEmpId" />
           </div>
        </td>
        <th scope="row">취소 일시</th>
        <td>
           <div class="form_group">
           <input type="text" name="cnclDt" class="i_input inputLimitDate cnclDt" readOnly/>
           </div>
        </td>
    </tr>
    <tr>
        <th scope="row">승인취소 사유</th>
        <td colspan="3">
             <div class="form_group">
             <textarea name="cnclRsnCtt" class="i_text_full_150 cnclRsnCtt" rows="" cols=""></textarea>
             </div>
        </td>
    </tr>
    </tbody>
</table>

</form>
<script>
$(document).ready(function() {
    $.popupTitle("투자 지출 품의-MR종료");
 if("${message}" != null && "${message}" != "" ) {
     alert("${message}");
     window.open("", "_self").close();
 }
});

$(window).load(function() {

	ivstEndPopForm.mrReqNo.value = opener.registerForm.mrReqNo.value;

	var currentDate = $.getCurrentDate();

    if($("#writeDate").html()=="") {
        $("#writeDate").html(currentDate);
    }

    $(".empSearch").click(function() {
        $.popupPopup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".comp").click(function() {
        if($.validation())
        if(confirm("MR 종료처리를 하시겠습니까?")) {
            $("form").attr("action", "ivstCostRptEnd.do").submit();
            opener.window.location.reload();
            self.close();
        }
    });

    $(".close").click(function() {
        self.close();
    });

    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
        	        cnclEmpIdText : {method:"class",type:"text", msg:"승인취소자를 입력하세요."},
        	        cnclDt : {method:"class",type:"text", msg:"취소일시를 입력하세요."},
        	        cnclRsnCtt : {method:"class",type:"text", msg:"취소사유를 입력하세요."},
                });
        return isValid;
    };

});

$(window).ready(function() {

    $(".inputLimitDate").datepicker({
        dateformat:"yyyy-mm-dd",
        showOn:"button",
        changeYear: true,
        changeMonth: true,
        buttonImage:"/mr/images/icon_calendar.png",
        buttonImageOnly:true,
        minDate :"today",
        maxDate :"+3M"
    });
    $("img.ui-datepicker-trigger").attr("style", "border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;");
    $(".ui-datepicker-month").attr("width", "30px");

});

</script>
