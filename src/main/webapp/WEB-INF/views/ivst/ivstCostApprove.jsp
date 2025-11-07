<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">투자 지출 품의</h4>
</div>
<!-- button -->
<div class="button_top">
    <img src="images/btn_temp.png" style="display:none" class="vam cursor save AWY"/>
    <c:out value="${modeClass}"/>
    <button class="goRegister" name="goRegister">품의서작성</button>
    <button class="save EAA" style="display:none" name="save">임시저장</button>
    <!-- <button class="file" name="file">파일저장</button>  -->
    <button class="send" name="send">품의서발송</button>
    <button class="end" name="end">MR종료</button>
</div>

<div id="content_wrap">
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${ivstRpt.mrReqNo}"/>"/>
<table class="table_row" border="1" cellspacing="0">
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
        <th scope="row">경영분석 담당자</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='EAC'}">
               <input type="text" class="i_input mngAnalText" value="${ivstRpt.mngAnalEmpNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAC'}">
                 <c:out value="${ivstRpt.mngAnalEmpNm}"/>
            </c:if>
            <input type="hidden" class="i_input mngAnalValue" name="mngAnalEmpNo" value="${ivstRpt.mngAnalEmpNo}"/>
            <img src="images/icon_search.png" class="vam cursor empSearch" data-role="mngAnal" />
            </div>
        </td>
    </tr>
    <tr>
        <th scope="row">사업명</th>
        <td><c:out value="${register.mrReqTitle}"/></td>
        <th scope="row">품의년도</th>
        <td>2014</td>
    </tr>
    <tr>
        <th scope="row">책임부서</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='EAC'}">
                 <input type="text" class="i_input respTeamText" value="${ivstRpt.respTeamNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAC'}">
                 <c:out value="${ivstRpt.respTeamNm}"/>
            </c:if>
            <input type="hidden" class="i_input respTeamValue" name="respTeamCd" value="<c:out value="${ivstRpt.respTeamCd}"/>"/>
            <img src="images/icon_search.png" class="vam cursor teamSearch" data-role="respTeam" />
            </div>
        </td>
        <th scope="row">원가부서</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='EAC'}">
                 <input type="text" class="i_input costTeamText" value="${ivstRpt.costTeamNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAC'}">
                 <c:out value="${ivstRpt.costTeamNm}"/>
            </c:if>
            <input type="hidden" class="i_input costTeamValue" name="costTeamCd" value="<c:out value="${ivstRpt.costTeamCd}"/>"/>
            <img src="images/icon_search.png" class="vam cursor teamSearch" data-role="costTeam" />
            </div>
        </td>
    </tr>
    <tr>
        <th scope="row">공정</th>
        <td>
              <c:forEach var="proc" items="${register.procs}" varStatus="loop" begin="1" end="1">
                  <c:out value="${proc.mrProcName}"/>
              </c:forEach>
        </td>
        <th scope="row">자산Class</th>
        <td>
            <select class="propClass" disabled="disabled" name="propClass"></select>
        </td>
    </tr>
    <tr>
        <th scope="row">투자시작일</th>
        <td>
             <div class="form_group">
             <input type="text" name="invstStaDt" class="i_input inputDate invstStaDt" disabled="disabled" value="<c:out value="${ivstRpt.invstStaDt}"/>" readOnly/>
             </div>
        </td>
        <th scope="row">투자종료일</th>
        <td>
             <div class="form_group">
             <input type="text" name="invstEndDt" class="i_input inputDate invstEndDt" disabled="disabled" value="<c:out value="${ivstRpt.invstEndDt}"/>" readOnly/>
             </div>
        </td>
    </tr>
    <tr>
        <th scope="row">작성자(발송자)</th>
        <td><c:out value="${ivstRpt.sapSenderNm}"/>
        </td>
        <th scope="row">작성일(발송일)</th>
        <td><c:out value="${ivstRpt.sapSendDt}"/>
        </td>
    </tr>
    </tbody>
</table>


<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="85%">
    </colgroup>
    <tbody>
    <tr>
    <td colspan="2" class="topbg"></td>
    </tr>
    <tr>
        <th scope="row">투자지출 스캔첨부파일 <img src="images/btn_add.gif" class="vam fileAdd" />
        </th>
        <td></td>
    </tr>
    <tr>
        <th scope="row">첨부파일 <img src="images/btn_add.gif" class="vam fileAdd" />
        </th>
        <td></td>
    </tr>
    <tr>
        <th scope="row">최종품의서 파일 <img src="images/btn_add.gif" class="vam fileAdd" />
        </th>
        <td></td>
    </tr>
</table>

</form>
</div>

<script>

$(window).load(function() {

    var currentDate = $.getCurrentDate();
    var disabled = true;

    if("${modeClass}"=="EAA"){
        disabled = false;
    }

    if($("#writeDate").html()=="") {
        $("#writeDate").html(currentDate);
    }

    $.selectLoad({
        className : "propClass",
        url : "codeList.do?mrCommGrpCd=PROP",
        defaultText : "선택",
        selectValue : "${ivstRpt.propClass}",
    });

    $(".empSearch").click(function() {
        $.popup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".teamSearch").click(function() {
        $.popup({url:"popup/teamSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".goRegister").click(function() {
        if(confirm("투자지출품의서 작성단계로 가시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "ivstCostRegisterLink.do").submit();
        }
    });

    $(".save").click(function() {
        if(confirm("저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "ivstCostRptUpdate.do").submit();
        }
    });

    $(".send").click(function() {
        if($.validation())
        if(confirm("투자지출 품의서를 SAP으로 발송하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "ivstCostRptSend.do").submit();
        }
    });

    $(".end").click(function() {
        $.popup({url:"popup/ivstCostRptEndPop.do", width:"700", height:"300"});
    });

    $(document).on("click", "#fileAdd",function(){

        $("#fileTemp").children("tbody").children("tr").children("td").children(".multipartFile").attr("name","attachFiles["+$("#fileList").children("tbody").children("tr:last").index()+"].multipartFile");
        $("#fileTemp").children("tbody").children("tr").clone().insertAfter($("#fileList").children("tbody").children("tr:last"));
        fileCnt++;
    });

    $(".fileAdd").click(function() {
        alert("첨부파일 팝업");
    });

    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
        	        mngAnalText : {method:"class",type:"text", msg:"경영분석 담당자를 입력하세요."},
        	        respTeamText : {method:"class",type:"text", msg:"책임부서를 입력하세요."},
        	        costTeamText : {method:"class",type:"text", msg:"원가부서를 입력하세요."},
        	        propClass : {method:"class",type:"text", msg:"자산Class를 입력하세요."},
        	        invstStaDt : {method:"class",type:"text", msg:"투자시작일을 입력하세요."},
        	        invstEndDt : {method:"class",type:"text", msg:"투자종료일을 입력하세요."},
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
        buttonImage:"images/icon_calendar.png",
        buttonImageOnly:true,
        minDate :"today",
        maxDate :"+3M"
    });
    $("img.ui-datepicker-trigger").attr("style", "border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;");
    $(".ui-datepicker-month").attr("width", "30px");

});

</script>
