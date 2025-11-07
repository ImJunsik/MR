<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">투자 지출 품의</h4>
</div>
<!-- button -->
<div class="button_top">
    <img src="images/btn_save.png" style="display:none" class="cursor save AWY"/>
    
    <!-- 품의서 출력 -->
    <!-- <input type="image" src="images/btn_printReport.png" class="cursor ivstRPT EAA" name="ivstRPT" /> 
    
    <!-- 임시저장 -->
    <input type="image" src="images/btn_temp.png" style="display:none" class="save EAA" name="save" />
    
    <!-- 품의서발송 -->
    <input type="image" src="images/btn_sendReport.png" style="display:none" class="send EAA" name="send" />
    
    <!-- mr종료  -->
    <input type="image" src="images/btn_endMR.png" style="display:none" class="end EAA" name="end" />
    
    <!-- 투자비재산정  -->
    <input type="image" src="images/btn_reratingIC.png" style="display:none" class="EAA vam cursor costModify"/>    
    <a class="tooltips cursor"><input type="image" src="images/btn_maintefee_spend.png" style="display:none" class="skip EAA" name="skip" />
    <span>0 ~ 300만원미만 투자비를 정비비용으로 <br>처리할 경우 투자지출품의를 생략합니다.</span></a>
</div>

<div id="content_wrap">
<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${register.mrReqNo}"/>"/>
<input type="hidden" id="mrInvstCostRptNo" name="mrInvstCostRptNo" value="<c:out value="${ivstRpt.mrInvstCostRptNo}"/>"/>
<input type="hidden" id="mrNo" name="mrNo" value="<c:out value="${register.mrNo}"/>"/>
<input type="hidden" id="plantNo" name="plant" value="<c:out value="${register.plant}"/>"/>
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
        <th scope="row">MR번호</th>
        <td><c:out value="${register.mrNo}"/></td>
        <th scope="row">CAPEX번호</th>
        <td><c:out value="${register.capexNo}"/></td>
    </tr>
    <tr>
        <th scope="row" >사업명</th>
        <td colspan="3"><c:out value="${register.mrReqTitle}"/></td>
    </tr>
    <tr>
        <th scope="row">개선대상</th>
        <td>
             <c:out value="${register.plantNm}"/>
        </td>
        <th scope="row">공정</th>
        <td>
              <c:forEach var="proc" items="${register.procs}" varStatus="loop" begin="0" end="0">
                  <c:out value="${proc.mrProcName}"/>
              </c:forEach>
        </td>
    </tr>
    <tr>
        <th scope="row">설 비</th>
        <td>
              <c:forEach var="equip" items="${register.equips}" varStatus="loop" begin="0" end="0">
                  <c:out value="${equip.mrEquipName}"/>
              </c:forEach>
        </td>
        
<!-- 2017/08/30 wj 투자구분 투자목적 변경되게 수정 
        <th>투자구분<span style="color:red">&nbsp;*</span></th>
        <td>
            <select class="invstCd BAA" name="invstCd"  disabled="disabled"></select>
        </td>
        <th>투자목적<span style="color:red">&nbsp;*</span></th>
        <td>
            <span class="invstPurpCdText">투자구분을 선택하세요.</span> <select class="invstPurpCd BAA" name="invstPurpCd"  disabled="disabled" style="display:none"></select>
        </td>
-->     
        <th scope="row"><span style="color:red">투자구분</span></th>
        <td>
        	<!-- wj투자구분, 투자목적 수정가능하게 변경 -->
        	<!--<c:out value="${register.invstCdNm}"/>-->
        	
        	<c:if test="${modeClass=='EAA'}">
        		<select class="invstCd BAA" name="invstCd"></select>
        	</c:if>
        	
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${register.invstCdNm}"/>
             </c:if>

        </td>
    </tr>
    <tr>
        <th scope="row">작업시점</th>
        <td><c:out value="${register.workPsblClNm}"/></td>
        <th scope="row">
        	<span style="color:red">투자목적</span>
        </th>
        <td>
        	<c:if test="${modeClass=='EAA'}">
	        	<!--<c:out value="${register.invstPurpCdNm}"/>-->
	        	<span class="invstPurpCdText">투자구분을 선택하세요.</span> <select class="invstPurpCd BAA" name="invstPurpCd" style="display:none"></select>
	        </c:if>
	        
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${register.invstPurpCdNm}"/>
             </c:if>
	        
        </td>
    </tr>
    </tbody>
</table>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="tl"> * 품의서 발송 시 <span style="color:red">투자예산번호/투자구분/투자목적</span>이 투자예산시스템에 각 항목의 값과 동일해야 투자지출품의번호가 생성됩니다.</div>
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
        <th scope="row">경영분석 담당자</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
               <input type="text" class="i_input mngAnalText" value="${ivstRpt.mngAnalEmpNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                 <c:out value="${ivstRpt.mngAnalEmpNm}"/>
            </c:if>
            <input type="hidden" class="i_input mngAnalValue" name="mngAnalEmpNo" value="${ivstRpt.mngAnalEmpNo}"/>
            <img src="images/icon_search.png" class="vam cursor empSearch EAA" style="display:none" data-role="mngAnal" />
            </div>
        </td>
        <th scope="row"><span style="color:blue">투자지출품의번호</span></th>
        <td><b>${ivstRpt.sapInvstCostRptNo}</b><span style="color:blue"> * 품의서발송 후 번호가 생성되었는지 꼭 확인하세요.</span> </td>
    </tr>
    <tr>
        <th scope="row">책임부서</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
                 <input type="text" class="i_input respTeamText" value="${ivstRpt.respTeamNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                 <c:out value="${ivstRpt.respTeamNm}"/>
            </c:if>
            <input type="hidden" class="respTeamValue" name="respTeamCd" value="<c:out value="${ivstRpt.respTeamCd}"/>"/>
            <img src="images/icon_search.png" class="vam cursor costTeamSearch EAA" style="display:none" data-role="respTeam" />
            </div>
        </td>
        <th scope="row">원가부서</th>
        <td>
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
                 <input type="text" class="i_input costTeamText" value="${ivstRpt.costTeamNm}" readOnly/>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                 <c:out value="${ivstRpt.costTeamNm}"/>
            </c:if>
            <input type="hidden" class="costTeamValue" name="costTeamCd" value="<c:out value="${ivstRpt.costTeamCd}"/>"/>
            <img src="images/icon_search.png" class="vam cursor costTeamSearch EAA" style="display:none" data-role="costTeam" />
        </div>
        </td>
    </tr>
    <tr>
        <th scope="row">자산Class</th>
        <td>
             <c:if test="${modeClass=='EAA'}">
            <select class="propClass" name="propClass"></select>
             </c:if>
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${ivstRpt.propClassNm}"/>
             </c:if>
        </td>
        <th scope="row">품의년도</th>
        <td>${ivstRpt.invstYear}</td>
    </tr>
    <tr>
        <th scope="row">투자시작일</th>
        <td>
             <div class="form_group">
             <c:if test="${modeClass=='EAA'}">
                <input type="text" name="invstStaDt" class="i_input inputDate invstStaDt" value="<c:out value="${ivstRpt.invstStaDt}"/>" readOnly/>
             </c:if>
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${ivstRpt.invstStaDt}"/>
             </c:if>
             </div>
        </td>
        <th scope="row">투자종료일</th>
        <td>
             <div class="form_group">
             <c:if test="${modeClass=='EAA'}">
                <input type="text" name="invstEndDt" class="i_input inputDate invstEndDt" value="<c:out value="${ivstRpt.invstEndDt}"/>" readOnly/>
             </c:if>
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${ivstRpt.invstEndDt}"/>
             </c:if>
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
    <tr>
        <th scope="row"><span style="color:red">투자예산번호</span></th>
        <td colspan="3">
             <div class="form_group">
             <c:if test="${modeClass=='EAA'}">
                <input type="text" name="invstManNo" class="i_input invstManNo" value="<c:out value="${ivstRpt.invstManNo}"/>"/>
             </c:if>
             <c:if test="${modeClass!='EAA'}">
                <c:out value="${ivstRpt.invstManNo}"/>
             </c:if>
             <span style="color:blue">  (자회사는 회사코드로 입력하시오. HCP:B000, HSB:C000, HDC:D000, HOC:E000)</span> 
             </div>            
        </td>
    </tr>
    <tr>
        <th scope="row">사업배경 및 목적</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
                <textarea name="bizPurpSurr" class="i_text_full bizPurpSurr" rows="" cols=""><c:out value="${ivstRpt.bizPurpSurr}"/></textarea>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                <pre><c:out value="${ivstRpt.bizPurpSurr}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr>
        <th scope="row">개선안</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
                <textarea name="imprvPlan" class="i_text_full imprvPlan" rows="" cols=""><c:out value="${ivstRpt.imprvPlan}"/></textarea>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                <pre><c:out value="${ivstRpt.imprvPlan}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr>
        <th scope="row">기대효과</th>
        <td colspan="3">
            <div class="form_group">
            <c:if test="${modeClass=='EAA'}">
                <textarea name="eptEft" class="i_text_full eptEft" rows="" cols=""><c:out value="${ivstRpt.eptEft}"/></textarea>
            </c:if>
            <c:if test="${modeClass!='EAA'}">
                <pre><c:out value="${ivstRpt.eptEft}"/></pre>
            </c:if>
            </div>
        </td>
    </tr>
    <tr>
        <th scope="row">온실가스</th>
        <td colspan="3">
            <div class="form_group">
		    	<c:if test="${modeClass=='EAA'}">
		        	온실가스 배출량 : <input type="text" class="i_input num number" name="zoutqty" id="zoutqty"  value="<fmt:formatNumber value="${register.zoutqty}" groupingUsed="true"/>" >&nbsp;tCO2&nbsp;/&nbsp;탄소배출권 비용 : <input type="text" class="i_input num number" id="zoutamt" name="zoutamt" value="<fmt:formatNumber value="${register.zoutamt}" groupingUsed="true"/>" >원
		        </c:if>
		        <c:if test="${modeClass!='EAA'}">
		        	온실가스 배출량 : <fmt:formatNumber value="${register.zoutqty}" groupingUsed="true"/>&nbsp;tCO2&nbsp;/&nbsp;탄소배출권 비용 : <fmt:formatNumber value="${register.zoutamt}" groupingUsed="true"/>원
		        </c:if>
		    </div>
        </td>
    </tr>
    <tr>
      <th scope="row" class="vam fileAdd">첨부파일
        <img id="addFile" src="images/icon_s_plus.png" border="0" class="cursor EAA fileAdd mt-2" disabled="true"/>
      </th>
      <td class="section_text pl5 pt5 pb5" colspan="3">
        <div class="form_group">
          <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
            <tr style="display:none"><td></td></tr>
            <c:forEach var="mrAtchFiles" items="${ivstRpt.mrAtchFiles}" varStatus="loop">
            <tr>
               <td>
        <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
        <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
        <img src="images/btn_inFile.png" class="cursor fileDown"/>
        <img class="cursor EAA removeDownFile" src="images/icon_minus.png" style="display:none"/>
        <input type="hidden" value="${mrAtchFiles.fileCd}"/>
        <input type="hidden" class="delFlag" name="mrAtchFiles[${loop.index}].deleted" value="false"/>
        <input type="hidden" class="insFlag" name="mrAtchFiles[${loop.index}].inserted" value="false"/>
        <input type="hidden" name="mrAtchFiles[${loop.index}].mrAtchFileNo" value="${mrAtchFiles.mrAtchFileNo}"/>
        <input type="hidden" class="drawMngNo" name="mrAtchFiles[${loop.index}].drawMngNo" value="${mrAtchFiles.drawMngNo}"/>
              </td>
            </tr>
          </c:forEach>
          </table>
        </div>
      </td>
    </tr>
    </tbody>
</table>
<!-- space non 10 -->
<hr class="divider_none_10" />
<div class="tl"> * 첨부파일 품의서 및 부속문서는 원본과 스캔본 모두 저장</div>
<!--
<c:out value="${modeClass}"/>
 -->
</form>
</div>

<script>
$(window).load(function() {
    var checkList = new Array();
    var disabled = true;
    
    if($("#writeDate").html()=="") {
        $("#writeDate").html(currentDate);
    }

    if("${modeClass}"=="EAA"){
        disabled = false;
    }

    $.selectLoad({
        className : "reqClCd",
        url : "codeList.do?mrCommGrpCd=REQ",
        defaultText : "선택",
        selectValue : "${register.reqClCd}"
    });

    $.selectLoad({
        className : "workPsblClCd",
        url : "codeList.do?mrCommGrpCd=WORK",
        defaultText : "선택",
        selectValue : "${register.workPsblClCd}"
    });

    $.selectLoad({
        className : "plantNo",
        url : "codeList.do?mrCommGrpCd=PLANT",
        defaultText : "선택",
        selectValue : "${register.plant}"
    });

    $.selectLoad({
        className : "propClass",
        url : "codeList.do?mrCommGrpCd=PROP",
        defaultText : "선택",
        selectValue : "${ivstRpt.propClass}",
    });

    $(".costModify").click(function () {
        $.popup({url:"popup/ivstInvestCostModify.do?&mrReqNo="+$("#mrReqNo").val(), scroll:"yes", width:"880", height:"700"});
    });

    $(".empSearch").click(function() {
        $.popup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".teamSearch").click(function() {
        $.popup({url:"popup/teamSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".costTeamSearch").click(function() {
        $.popup({url:"popup/costTeamSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".ivstRPT").click(function() {
      $.popup({url:"/mr/HDO_ivstRPT.jsp", width:"0", height:"0"});
    });

    $(".save").click(function() {
        if(confirm("저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${saveURL}").submit();
        }
    });

    $(".send").click(function() {
        if($.validation())
        if(confirm("투자지출 품의서의 CAPEX 번호를 요청하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "ivstCostRptSend.do").submit();
        }
    });

    $(".skip").click(function() {
        if($.validation())
          if("${register.invstCostTotal}" < 3000) {
            if(confirm("투자비 300만원 미만은 정비비용으로 처리합니다. 진행하시겠습니까?")) {
	            $('#pageLoadingWrap').show();
    	        $("form").attr("action", "ivstCostRptSkip.do").submit();
	        }
         }
    });
    
    $(".end").click(function() {
        $.popup({url:"popup/ivstCostRptEndPop.do", width:"700", height:"300"});
    });

    $.validation = function(){
        var isValid = false;
        
        isValid = $.validator({
        	
        	invstCd : {method:"class",type:"text", msg:"투자구분을 입력하세요."},
        	invstPurpCd : {method:"class",type:"text", msg:"투자목적을 입력하세요."},
        	
        	
            mngAnalText : {method:"class",type:"text", msg:"경영분석 담당자를 입력하세요."},
            respTeamText : {method:"class",type:"text", msg:"책임부서를 입력하세요."},
            costTeamText : {method:"class",type:"text", msg:"원가부서를 입력하세요."},
            propClass : {method:"class",type:"text", msg:"자산Class를 입력하세요."},
            invstStaDt : {method:"class",type:"text", msg:"투자시작일을 입력하세요."},
            invstEndDt : {method:"class",type:"text", msg:"투자종료일을 입력하세요."},
            bizPurpSurr : {method:"class",type:"text", msg:"사업배경 및 목적을 입력하세요."},
            imprvPlan : {method:"class",type:"text", msg:"개선안을 입력하세요."},
            eptEft : {method:"class",type:"text", msg:"기대효과를 입력하세요."},
            invstManNo : {method:"class",type:"text", msg:"투자예산번호를 입력하세요."},            
        });        
        return isValid;
    };

});

function getParamMap(queryString){ 
    var splited = queryString.replace("?", "").split(/[=?&]/); 
    var param = {}; 
    for (var i = 0; i < splited.length; i++) { param[splited[i]] = splited[++i]; } 
    return param;
} 
$(window).ready(function() {

    $(".inputLimitDate").datepicker({
        dateformat:"yyyy-mm-dd",
        showOn:"button",
        changeYear: true,
        changeMonth: true,
        buttonImage:"images/icon_calendar.gif",
        buttonImageOnly:true,
        minDate :"today",
        maxDate :"+3M"
    });
    $("img.ui-datepicker-trigger").attr("style", "border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;");
    $(".ui-datepicker-month").attr("width", "30px");

    $.injectUintList = function(option){
        var tr ="";
        tr += "<tr>";
        tr += "<td>"+option.unitNo+"<input type='hidden' class='mrProcNo' name='unitNo' value='"+option.unitNo+"'></td>";
        tr += "<td>"+option.unitName+"<input type='hidden' class='mrProcName' name='unitNo' value='"+option.unitName+"'></td>";
        tr += "<tr>";
        $("#selectUnit").append(tr);
        $.sort({className : "mrProcNo", fristName : "procs", lastName : "mrProcNo"});
        $.sort({className : "mrProcName", fristName : "procs", lastName : "mrProcName"});
    };
    
    
    
    //wj 2017/08/29 투자구분, 투자목적 추가---------------------//
	var invstCd = "${register.invstCd}";	

    $.selectLoad({
        className : "invstCd",
        url : "codeList.do?mrCommGrpCd=IVST",
        defaultText : "선택",
        selectValue : "${register.invstCd}"+""
    });

    if(invstCd!=""){
    	$.selectLoad({
            className : "invstPurpCd",
            url : "codeList.do?mrCommGrpCd=IVST_PUR&mrCommCd="+invstCd.substr(0, 1),
            defaultText : "선택",
            selectValue : "${register.invstPurpCd}"+""
        });
        $(".invstPurpCdText").hide();
        $(".invstPurpCd").show();
    }
    
    /* yoo 241224 품의 번호와 함께 에러 메시지도 리턴하도록 변경 , 에러 메시지일 경우, 메시지 창 띄움 */
    var url = location.href;
    console.log(url);
    const params = getParamMap(url);
    console.log(params.code, params.err);
    if(params.code == 'E')
    {
    	//const decodedComponent = decodeURIComponent(params.err);
    	const decodedComponent = decodeURIComponent((params.err + '').replace(/\+/g, '%20'));
    	console.log(decodedComponent)
    	alert(decodedComponent);	
    }
  	//끝-----------------------------------------------------//
});

	//wj 2017/08/29 투자구분 변경시 투자목적 바인딩-----------------//
$(document).on("change",".invstCd", function () {
	var commCd = $(this).val();

	if($(this).val()!=""){
		$(".invstPurpCdText").hide();
		$(".invstPurpCd").show();
		$.selectLoad({
	        className : "invstPurpCd",
	        url : "codeList.do?mrCommGrpCd=IVST_PUR&mrCommCd="+commCd.substr(0, 1),
	        defaultText : "선택"
	    });
	} else {
		$(".invstPurpCd").val("");
		$(".invstPurpCdText").show();
		$(".invstPurpCd").hide();
	}
});
	
	
	
$("input[name='greenhouseGas']:radio").change(function () {
    //라디오 버튼 값을 가져온다.
    var result = this.value;
                    
    if(result == 2)
    {
    	console.log('활성화 시킨다.');	
    	$('#greenhouseGasMemo').removeAttr("disabled");
    }else{
    	console.log('비활성화 시킨다.');
    	$('#greenhouseGasMemo').attr("disabled", "disabled");
    }
});
	//끝-----------------------------------------------------//

</script>
