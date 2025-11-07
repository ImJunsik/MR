<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- button -->
<div class="pop_button_top">
      <span style="color:red">*완료버튼 클릭시 PORC위원들에게 메일 발송</span>
      <c:if test="${empty jobsPorc.porcNo}">
      <input type="image" src="../images/btn_temp.png" class="cursor save IAD" style="display:none"/>
      <input type="image" src="../images/btn_complete.png" class="cursor complete IAD" style="display:none"/>
      </c:if>
</div>

<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${jobsPorc.mrReqNo}"/>"/>
<input type="hidden" id="porcYn" name="porcYn" value="<c:out value="${jobsPorc.porcYn}"/>"/>
<input type="hidden" id="modeClass" name="modeClass" value="<c:out value="${modeClass}"/>"/>
<table class="table_pop_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="15%">
    <col width="70%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row" <c:if test="${modeClass=='IAD'}">rowspan="8"</c:if><c:if test="${modeClass!='IAD'}">rowspan="7"</c:if>>PORC 위원<br /> 지정</th>
        <c:if test="${modeClass=='IAD'}">
        <th scope="row">담당자추가</th>
        <td>
            <div class="form_group">
            <select class="empClCd"></select>
            <input type="text" class="i_input empText" readOnly/>
            <input type="hidden" class="empValue"/>
            <input type="hidden" class="empDutyText"/>
            <input type="hidden" class="empTeamText"/>
            <input type="hidden" class="empTeamValue"/>
            <img src="../images/icon_search.png" class="cursor empSearch IAD" data-role="emp" style="display:none"/>
            <img src="../images/icon_plus.png" class="cursor addEmp" />
            </div>
        </td>
        </c:if>
    <c:if test="${modeClass!='IAD'}">
        <th scope="row">운전/생산</th>
        <td>
            <table class="table_pop_default drive">
            <c:forEach var="drive" items="${jobsPorc.drives}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam driveText"><c:out value="${drive.thdayTeam}"/> / <c:out value="${drive.thdayPos}"/> / <c:out value="${drive.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="driveValue" name="drives[${loop.index}].chrgEmpNo" value="<c:out value="${drive.chrgEmpNo}"/>"/>
                  <input type="hidden" class="driveCode" name="drives[${loop.index}].chrgrClCd" value="<c:out value="${drive.chrgrClCd}"/>"/>
                  <input type="hidden" class="driveDutyText" name="drives[${loop.index}].thdayPos" value="<c:out value="${drive.thdayPos}"/>"/>
                  <input type="hidden" class="driveTeamText" name="drives[${loop.index}].thdayTeam" value="<c:out value="${drive.thdayTeam}"/>"/>
                  <input type="hidden" class="driveTeamValue" name="drives[${loop.index}].chrgTeamNo" value="<c:out value="${drive.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </c:if>
    </tr>
    <c:if test="${modeClass=='IAD'}">
    <tr>
        <th scope="row">운전/생산</th>
        <td>
            <table class="table_pop_default drive">
            <c:forEach var="drive" items="${jobsPorc.drives}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam driveText"><c:out value="${drive.thdayTeam}"/> / <c:out value="${drive.thdayPos}"/> / <c:out value="${drive.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="driveValue" name="drives[${loop.index}].chrgEmpNo" value="<c:out value="${drive.chrgEmpNo}"/>"/>
                  <input type="hidden" class="driveCode" name="drives[${loop.index}].chrgrClCd" value="<c:out value="${drive.chrgrClCd}"/>"/>
                  <input type="hidden" class="driveDutyText" name="drives[${loop.index}].thdayPos" value="<c:out value="${drive.thdayPos}"/>"/>
                  <input type="hidden" class="driveTeamText" name="drives[${loop.index}].thdayTeam" value="<c:out value="${drive.thdayTeam}"/>"/>
                  <input type="hidden" class="driveTeamValue" name="drives[${loop.index}].chrgTeamNo" value="<c:out value="${drive.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    </c:if>

    <tr>
        <th scope="row">기술</th>
        <td>
            <table class="table_pop_default tech">
            <c:forEach var="tech" items="${jobsPorc.techs}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam techText"><c:out value="${tech.thdayTeam}"/> / <c:out value="${tech.thdayPos}"/> / <c:out value="${tech.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="techValue" name="techs[${loop.index}].chrgEmpNo" value="<c:out value="${tech.chrgEmpNo}"/>"/>
                  <input type="hidden" class="techCode" name="techs[${loop.index}].chrgrClCd" value="<c:out value="${tech.chrgrClCd}"/>"/>
                  <input type="hidden" class="techDutyText" name="techs[${loop.index}].thdayPos" value="<c:out value="${tech.thdayPos}"/>"/>
                  <input type="hidden" class="techTeamText" name="techs[${loop.index}].thdayTeam" value="<c:out value="${tech.thdayTeam}"/>"/>
                  <input type="hidden" class="techTeamValue" name="techs[${loop.index}].chrgTeamNo" value="<c:out value="${tech.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">공무</th>
        <td>
            <table class="table_pop_default build">
            <c:forEach var="build" items="${jobsPorc.builds}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam buildText"><c:out value="${build.thdayTeam}"/> / <c:out value="${build.thdayPos}"/> / <c:out value="${build.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="buildValue" name="builds[${loop.index}].chrgEmpNo" value="<c:out value="${build.chrgEmpNo}"/>"/>
                  <input type="hidden" class="buildCode" name="builds[${loop.index}].chrgrClCd" value="<c:out value="${build.chrgrClCd}"/>"/>
                  <input type="hidden" class="buildDutyText" name="builds[${loop.index}].thdayPos" value="<c:out value="${build.thdayPos}"/>"/>
                  <input type="hidden" class="buildTeamText" name="builds[${loop.index}].thdayTeam" value="<c:out value="${build.thdayTeam}"/>"/>
                  <input type="hidden" class="buildTeamValue" name="builds[${loop.index}].chrgTeamNo" value="<c:out value="${build.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">설계/검사</th>
        <td>
            <table class="table_pop_default check">
            <c:forEach var="check" items="${jobsPorc.checks}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam checkText"><c:out value="${check.thdayTeam}"/> / <c:out value="${check.thdayPos}"/> / <c:out value="${check.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="checkValue" name="checks[${loop.index}].chrgEmpNo" value="<c:out value="${check.chrgEmpNo}"/>"/>
                  <input type="hidden" class="checkCode" name="checks[${loop.index}].chrgrClCd" value="<c:out value="${check.chrgrClCd}"/>"/>
                  <input type="hidden" class="checkDutyText" name="checks[${loop.index}].thdayPos" value="<c:out value="${check.thdayPos}"/>"/>
                  <input type="hidden" class="checkTeamText" name="checks[${loop.index}].thdayTeam" value="<c:out value="${check.thdayTeam}"/>"/>
                  <input type="hidden" class="checkTeamValue" name="checks[${loop.index}].chrgTeamNo" value="<c:out value="${check.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">안전/환경</th>
        <td>
            <table class="table_pop_default safety">
            <c:forEach var="safety" items="${jobsPorc.safetys}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam safetyText"><c:out value="${safety.thdayTeam}"/> / <c:out value="${safety.thdayPos}"/> / <c:out value="${safety.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="safetyValue" name="safetys[${loop.index}].chrgEmpNo" value="<c:out value="${safety.chrgEmpNo}"/>"/>
                  <input type="hidden" class="safetyCode" name="safetys[${loop.index}].chrgrClCd" value="<c:out value="${safety.chrgrClCd}"/>"/>
                  <input type="hidden" class="safetyDutyText" name="safetys[${loop.index}].thdayPos" value="<c:out value="${safety.thdayPos}"/>"/>
                  <input type="hidden" class="safetyTeamText" name="safetys[${loop.index}].thdayTeam" value="<c:out value="${safety.thdayTeam}"/>"/>
                  <input type="hidden" class="safetyTeamValue" name="safetys[${loop.index}].chrgTeamNo" value="<c:out value="${safety.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th scope="row">기타</th>
        <td>
            <table class="table_pop_default etc">
            <c:forEach var="etc" items="${jobsPorc.etcs}" varStatus="loop">
                <tr>
                  <td>
                  <span class="vam etcText"><c:out value="${etc.thdayTeam}"/> / <c:out value="${etc.thdayPos}"/> / <c:out value="${etc.chrgEmpName}"/></span>
                  <img src="../images/icon_minus.png" class="vam cursor delEmp" <c:if test="${modeClass!='IAD'}">style="display:none"</c:if>/>
                  <input type="hidden" class="etcValue" name="etcs[${loop.index}].chrgEmpNo" value="<c:out value="${etc.chrgEmpNo}"/>"/>
                  <input type="hidden" class="etcCode" name="etcs[${loop.index}].chrgrClCd" value="<c:out value="${etc.chrgrClCd}"/>"/>
                  <input type="hidden" class="etcDutyText" name="etcs[${loop.index}].thdayPos" value="<c:out value="${etc.thdayPos}"/>"/>
                  <input type="hidden" class="etcTeamText" name="etcs[${loop.index}].thdayTeam" value="<c:out value="${etc.thdayTeam}"/>"/>
                  <input type="hidden" class="etcTeamValue" name="etcs[${loop.index}].chrgTeamNo" value="<c:out value="${etc.chrgTeamNo}"/>"/>
                  </td>
                </tr>
            </c:forEach>
            </table>
        </td>
    </tr>
</table>
</form>

<script>
var totalPorcCnt = 0;
$(window).ready(function() {
	totalPorcCnt = parseInt("${jobsPorc.drives.size()}")+parseInt("${jobsPorc.techs.size()}")+parseInt("${jobsPorc.builds.size()}")+parseInt("${jobsPorc.checks.size()}")+parseInt("${jobsPorc.safetys.size()}")+parseInt("${jobsPorc.etcs.size()}");

	$.popupTitle("PORC 위원선정");
    var jobClass= "";



    $(".empSearch").click(function() {
         $.popupPopup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".save").click(function () {
    	if(opener.porcLineListCnt)
    	{
    		opener.porcLineListCnt.value = totalPorcCnt;
    	}
        if(confirm("저장 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "mrJobsPorcSave.do?porcYn=Y").submit();
        }
    });

    $(".complete").click(function () {
    	if($.validation()){
        	if(opener.porcLineListCnt)
        	{
        		opener.porcLineListCnt.value = totalPorcCnt;
        	}
            if(confirm("PORC위원 지정을 완료하시겠습니까? 완료후에는 수정이 불가능합니다.")) {
                $.removeComma();
                $('#pageLoadingWrap').show();
                $("form").attr("action", "mrJobsPorcComplete.do").submit();
                //window.open("", "_self").close();
            }
    	}
    });


    $.selectLoad({
        className : "empClCd",
        url : "../codeList.do?mrCommGrpCd=MR_PROC&mrCommCd=Z02L",
        defaultText : "선택",
        selectValue : "${register.reqClCd}"+""
    });

    $(".addEmp").click(function () {
        var addClass ="";
        var tr = "";
        var isCheck=0;
        if(confirm("PORC 위원을 추가하시겠습니까?")) {
            switch ($(".empClCd").val()) {
            case "Z02L1":
                addClass = "drive";
                break;
            case "Z02L2":
                addClass = "tech";
                break;
            case "Z02L3":
                addClass = "build";
                break;
            case "Z02L4":
                addClass = "check";
                break;
            case "Z02L5":
                addClass = "safety";
                break;
            case "Z02L6":
                addClass = "etc";
                break;
            }
            totalPorcCnt++;
            $(".tech").children("tbody").children("tr").each(function(){
                if($(this).find(".techValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".check").children("tbody").children("tr").each(function(){
                if($(this).find(".checkValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".drive").children("tbody").children("tr").each(function(){
                if($(this).find(".driveValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".safety").children("tbody").children("tr").each(function(){
                if($(this).find(".safetyValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".check").children("tbody").children("tr").each(function(){
                if($(this).find(".checkValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            $(".etc").children("tbody").children("tr").each(function(){
                if($(this).find(".etcValue").val() == $(".empValue").val()) {
                    isCheck++;
                }
            });

            if(isCheck>0) {
                alert("PORC위원은 중복선정이 불가능합니다.");
                return;
            }

            tr +="<tr><td>";
            tr +="<span class='vam "+addClass+"Text'>"+$(".empTeamText").val()+" / "+$(".empDutyText").val()+" / "+$(".empText").val()+"</span><img src='../images/icon_minus.png' class='vam cursor delEmp BAA BBA' />";
            tr +="<input type='hidden' class='"+addClass+"Value' name='appLine[2].chrgEmpNo' value='"+$(".empValue").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"Code' name='appLine[2].chrgrClCd' value='"+$(".empClCd").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"DutyText' name='appLine[2].thdayPos' value='"+$(".empDutyText").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"TeamText' name='appLine[2].thdayTeam' value='"+$(".empTeamText").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"TeamValue' name='appLine[2].chrgTeamNo' value='"+$(".empTeamValue").val()+"'/>";
            tr +="<input type='hidden' class='"+addClass+"FstProcTrmDt' name='appLine[2].fstProcTrmDt' value='9999-12-31'/>";
            tr +="</td></tr>";

            $("."+addClass).append(tr);
            $.sort({className : addClass+"Value", fristName : addClass+"s", lastName : "chrgEmpNo"});
            $.sort({className : addClass+"Code", fristName : addClass+"s", lastName : "chrgrClCd"});
            $.sort({className : addClass+"DutyText", fristName : addClass+"s", lastName : "thdayPos"});
            $.sort({className : addClass+"TeamText", fristName : addClass+"s", lastName : "thdayTeam"});
            $.sort({className : addClass+"TeamValue", fristName : addClass+"s", lastName : "chrgTeamNo"});
            $.sort({className : addClass+"FstProcTrmDt", fristName : addClass+"s", lastName : "fstProcTrmDt"});
            $(".empText").val("");
            $(".empText").val("");
            $(".empClCd").val("");
            $(".empDutyText").val("");
            $(".empTeamText").val("");
            $(".empTeamValue").val("");

        }
    });


});

$.validation = function(){
    var isValid = false;
    var porcCount = 0;
    $(".drive").children("tbody").children("tr").each(function () {
    	porcCount++;
    });

    $(".tech").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".build").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".check").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".safety").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    $(".etc").children("tbody").children("tr").each(function () {
        porcCount++;
    });

    if(porcCount>0 ) {
    	isValid = true;
    } else  {
    	alert("PORC 담당자를 입력하세요.");
    }

    return isValid;
};

$(document).on("click",".delEmp", function () {
    var removeClass="";
    if(confirm("기술검토자를 삭제 하시겠습니까?")) {
    	totalPorcCnt--;
        removeClass = $(this).parent("td").parent("tr").parent("tbody").parent("table").attr("class");
        $(this).parent("td").parent("tr").remove();
        $.sort({className : removeClass+"Value", fristName : removeClass+"s", lastName : "chrgEmpNo"});
        $.sort({className : removeClass+"Code", fristName : removeClass+"s", lastName : "chrgrClCd"});
        $.sort({className : removeClass+"DutyText", fristName : removeClass+"s", lastName : "thdayPos"});
        $.sort({className : removeClass+"TeamText", fristName : removeClass+"s", lastName : "thdayTeam"});
        $.sort({className : removeClass+"TeamValue", fristName : removeClass+"s", lastName : "chrgTeamNo"});
    }
});



</script>