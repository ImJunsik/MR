<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<style>
/* 비활성화된 inputDate_t 섹션 스타일 */
.inputDate_t.disabled-section {
    background-color: #f5f5f5;
    opacity: 0.7;
} 

.inputDate_t.disabled-section input[type="text"] {
    background-color: #eeeeee;
    color: #999999;
    cursor: not-allowed;
}

.inputDate_t.disabled-section img.ui-datepicker-trigger {
    opacity: 0.5;
    cursor: not-allowed;
    pointer-events: none;
}

/* P&ID, MR수행완료 개별 컨트롤 비활성화 스타일 */
.pnid-input.disabled, .mrprfm-input.disabled {
    background-color: #eeeeee !important;
    color: #999999 !important;
    cursor: not-allowed !important;
    border: 1px solid #dddddd !important;
}

.disabled-calendar {
    opacity: 0.5 !important;
    cursor: not-allowed !important;
    pointer-events: none !important;
}

/* 비활성화된 버튼 스타일 */
.disabled-button {
    opacity: 0.3 !important;
    cursor: not-allowed !important;
    pointer-events: none;
    filter: grayscale(80%);
}

/* HAZOP 관련 메시지 스타일 */
.hazop-info-message {
    background-color: #fff3cd;
    border: 1px solid #ffeaa7;
    color: #856404;
    padding: 8px 12px;
    margin: 5px 0;
    border-radius: 4px;
    font-size: 12px;
    display: inline-block;
}

/* HAZOP 버튼 위치 조정 */
.hazop-button-wrapper {
    margin-left: 20px;
    display: inline-block;
}
</style>

<!-- title -->
<div id="content_header">
    <h4 class="content_header title">MR수행</h4>
</div>

<!-- button -->
<div class="button_top">
	<c:if test="${register.jobsSkipCheck=='false'}">
		<!-- HAZOP 상태에 따른 안내 메시지 -->
		<c:if test="${register.hazopActYn=='Y'}">
			<div class="hazop-info-message completion-status-message">
				<span class="hazop-pending-message">⚠️ HAZOP List 작성 후 완료 버튼 활성화.</span>
				<span class="hazop-completed-message" style="display:none;">✅ HAZOP List 작성 완료.</span>
			</div>
		</c:if>
		
		<!-- 임시저장 버튼 (기술검토자용) - HAZOP 상태와 무관하게 동작 -->
		<input type="image" src="images/btn_temp.png" class="save2 FAD" name="save2" style="display:none" />
		
		<!-- MR수행완료 버튼 - HAZOP 완료 후 활성화 -->
		<input type="image" src="images/btn_exec_complete.png" class="safeCNF FAI completion-button" name="safeCNF" style="display:none"/>
		
		<!-- 기술검토 확인 버튼 - HAZOP 완료 후 활성화 -->
		<input type="image" src="images/btn_checkZ02D.png" class="checkZ02D FAD completion-button" name="checkZ02D" style="display:none" />
		
		<!-- 완료 버튼 - HAZOP 완료 후 활성화 -->
		<input type="image" src="images/btn_complete.png" class="comp FAA completion-button" name="comp" style="display:none" />
		
		<!-- 점검항목 버튼 - 항상 활성화 -->
		<input type="image" src="images/btn_checkitem.png" class="safeRPT FAA GAA" name="safeRPT" />
	</c:if>
	
	<c:if test="${register.jobsSkipCheck=='true'}">
		<div class="red"> * 직무검토 보류건이 있습니다.</div>
	</c:if>
</div>

<!-- 1 -->
<div id="content_wrap">
	<form id="registerForm" method="post">
	<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${register.mrReqNo}"/>"/>
	<input type="hidden" id="mrNo" name="mrNo" value="<c:out value="${register.mrNo}"/>"/>
	<input type="hidden" id="plantNo" name="plant" value="<c:out value="${register.plant}"/>"/>
	
	<input type="hidden" id="mrHazopYn" name="mrHazopYn" value="<c:out value="${register.mrHazopYn}"/>"/>
	<input type="hidden" id="stepCd" name="stepCd" value="<c:out value="${register.mrStepCd}"/>"/>
	
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
	        <th scope="row">사업명</th>
	        <td colspan="3"><c:out value="${register.mrReqTitle}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">개선대상</th>
	        <td><c:out value="${register.plantNm}"/></td>
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
	              <c:forEach var="equip" items="${register.equips}" varStatus="loop" >
	                  <c:out value="${equip.mrEquipName}"/><br/>
	              </c:forEach>
	        </td>
	        <th scope="row">투자구분</th>
	        <td><c:out value="${register.invstCdNm}"/></td>
	    </tr>
	    <tr>
	        <th scope="row">작업시기</th>
	        <td><c:out value="${register.workPsblClNm}"/></td>
	        <th scope="row">투자목적</th>
	        <td><c:out value="${register.invstPurpCdNm}"/></td>
	    </tr>
	    </tbody>
	</table>
	
	<!-- 2 HAZOP 관련 -->
	<hr class="divider_none_10" />
    <table class="table_row" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="20%">
        <col width="80%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="row">추가 위험성 검토 필요 여부 (Y/N)</th>
            <td>
            	<div class="form_group">
	            	<input type="radio" class="hazopActYn FAA GAA" name="hazopActYn" value="Y" <c:if test="${register.hazopActYn=='Y'}">checked </c:if> > 필요
		            <input type="radio" class="hazopActYn FAA GAA" name="hazopActYn" value="N" <c:if test="${register.hazopActYn=='N'}">checked </c:if> > 불필요
		            
		            <!-- HAZOP 버튼을 20px 오른쪽으로 이동 -->
		            <span class="hazop-button-wrapper">
		            	<img src="images/btn_apply.png" class="vam cursor hazop"/>
		            </span>
	            </div>
            </td>
        </tr>
        </tbody>
    </table>

	<!-- 3 P&ID최종본 -->
	<hr class="divider_none_10" />
    <table class="table_row inputDate_t" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tbody>
        <tr>
	    	<th scope="row">P&ID최종본</th>
		    <td>
		        <div class="form_group">
		            <c:if test="${modeClass=='FAD'}">
					    <input type="text" name="pnidDt" class="i_input inputDate pnid-input" value="${register.pnidDt}" readOnly/>
					    <img name="pnid-calendar" class="ui-datepicker-trigger pnid-calendar" src="images/icon_calendar_.png" alt="..." title="..." style="border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;">
					    <span>기술검토 담당자 확인 일자</span>
					</c:if>
					<c:if test="${modeClass!='FAD'}">
					    <input type="text" class="i_input pnid-input" value="${register.pnidDt}" readOnly/>
					    <img name="pnid-calendar" class="ui-datepicker-trigger pnid-calendar" src="images/icon_calendar_.png" alt="..." title="..." style="border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;">
					    <span>기술검토 담당자 확인 일자</span>
					</c:if>
		        </div>
		    </td>
		</tr>
        </tbody>
    </table>

	<!-- 4 MR수행완료 -->   
    <hr class="divider_none_10" />
    <table class="table_row inputDate_t" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tbody>
        <tr>
		    <th scope="row">MR수행완료</th>
		    <td>
		        <div class="form_group">
		            <c:if test="${modeClass=='FAI'}">
					    <input type="text" name="mrPrfmDt" class="i_input inputDate mrprfm-input" value="${register.mrPrfmDt}" readOnly/>
					    <img name="mrprfm-calendar" class="ui-datepicker-trigger mrprfm-calendar" src="images/icon_calendar_.png" alt="..." title="..." style="border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;">
					    <span>Project Engineer 확인 일자</span>
					</c:if>
					<c:if test="${modeClass!='FAI'}">
					    <input type="text" class="i_input mrprfm-input" value="${register.mrPrfmDt}" readOnly/>
					    <img name="mrprfm-calendar" class="ui-datepicker-trigger mrprfm-calendar" src="images/icon_calendar_.png" alt="..." title="..." style="border=0;cursor:Pointer;vertical-align:middle;margin-left: 4px;margin-right: 4px;">
					    <span>Project Engineer 확인 일자</span>
					</c:if>
		        </div>
		    </td>
		</tr>
        </tbody>
    </table>
	
	<!-- 5 첨부파일 -->	
    <hr class="divider_none_10" />
    <table class="table_row inputDate_t" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row" class="vam fileAdd">첨부파일
            <img id="addFile" src="images/icon_s_plus.png" border="0" class="cursor FAD GAA GBA fileAdd mt-2" disabled="true"/>
        </th>
        <td class="section_text pl5 pt5 pb5">
            <div class="form_group">
            <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
                <tr style="display:none"><td></td></tr>
                <c:forEach var="mrAtchFiles" items="${register.mrAtchFiles}" varStatus="loop">
                <tr>
                <td>
                    <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
                    <input type="text" class="i_input_300" value="${mrAtchFiles.fileNm}" readonly>
                    <img src="images/btn_inFile.png" class="cursor fileDown" />
                    <img class="cursor FAD GBA removeDownFile" src="images/icon_minus.png" style="display:none"/>
                    <input type="hidden" value="${mrAtchFiles.fileCd}"/>
                    <input type="hidden" class="delFlag" name="mrAtchFiles[${loop.index}].deleted" value="false"/>
                    <input type="hidden" class="insFlag" name="mrAtchFiles[${loop.index}].inserted" value="false"/>
                    <input type="hidden" name="mrAtchFiles[${loop.index}].mrAtchFileNo" value="${mrAtchFiles.mrAtchFileNo}"/>
                    <input type="hidden" class="drawMngNo" name="mrAtchFiles[${loop.index}].drawMngNo" value="${mrAtchFiles.drawMngNo}"/>
                    
                    <!-- wj: 이미 저장된 첨부파일 체크를 위한 hidden field 추가-->
                    <input type="hidden" class="fileCdFlag" name="mrAtchFiles[${loop.index}].fileCd" value="${mrAtchFiles.fileCd}"/>
                    <input type="hidden" class="fileNmFlag" name="mrAtchFiles[${loop.index}].fileNm" value="${mrAtchFiles.fileNm}"/>
                </td>
                </tr>
            </c:forEach>
            </table>
            </div>
        </td>
        </tr>
        </tbody>
       </table>
<!-- 4 -->	
	<!-- space non 10 -->
<!-- 	<hr class="divider_none_10" />
	
	<div class="tl"> * 안전점검 수행문서 원본 또는 스캔본 필히 첨부 (가동전 점검결과, 변경관리 교육 결과 첨부 필수)</div> -->
	
	</form>
</div>

<!-- JavaScript -->
<script>

$(window).load(function() {
    
    // 페이지 오픈 시 기본 설정: hazopActYn에 따른 inputDate_t 활성화/비활성화
    initializeHazopSettings();
    
    // HAZOP Study 저장 완료 플래그가 있는 경우 처리
    var urlParams = getUrlParams();
    if(urlParams['hazopSaveComplete'] === 'true') {
        enableInputDateT();
        enableCompletionButtons();
        setHazopCompletedFlag(true);
        alert("HAZOP Study가 저장되었습니다. 다음 단계를 진행하세요.");
        removeUrlParameter('hazopSaveComplete');
    }
    
    $(".safeRPT").click(function() {
        $.popup({url:"/mr/HDO_safeRPT.jsp", width:"0", height:"0"});
    });

    $(".save").click(function() {
        if(confirm("임시저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${saveURL}").submit();
        }
    });
    
    $(".save2").click(function() {
        if(confirm("임시저장 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            
            var riskFileCnt = 0;
            $("#fileList > tbody> tr").each(function (){
                if(!$(this).is(":hidden")){
                    var file = $(this).children("td").children(".fileNm").val();
                    if(file != "" && file != undefined) {
                        riskFileCnt++;
                    }
                }
            });        	
            
            $("form").attr("action", "safeCheckAppr2.do").submit();
        }
    });
  	
  	// 날짜 기본값 설정
  	if("${modeClass}" == "FAD" && $("input[name = pnidDt]").val() == ""){
  		$("input[name = pnidDt]").val($.getCurrentDate());
  	}
  	if("${modeClass}" == "FAI" && $("input[name = mrPrfmDt]").val() == ""){
  		$("input[name = mrPrfmDt]").val($.getCurrentDate());
  	}
  	
  	$(".hazop").click(function () {
  		//$.popup({url:"popup/mrPopRiskCheck.do?mrReqNo="+$("#mrReqNo").val()+"&stepCd="+$("#stepCd").val(), width:"900", height:"750"});
  		$.popup({url:"popup/mrPopRiskCheck.do?mrReqNo="+$("#mrReqNo").val()+"&stepCd=Z0060", width:"900", height:"750"});
  	});
  	
    // MR수행 완료 버튼
    $(".safeCNF").click(function() {
        // HAZOP Study가 필요한 경우 저장 완료 여부 체크
        var hazopActYn = $("input[name='hazopActYn']:checked").val();
        if(hazopActYn == "Y" && !isHazopCompleted()) {
            alert("HAZOP Study를 먼저 완료해주세요.");
            return false;
        }
        
        if($.validation() && confirm("MR수행 완료를 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "safeCheckConf.do").submit();
        }
    });
    
    // 완료 버튼
    $(".comp").click(function() {
        // HAZOP Study가 필요한 경우 저장 완료 여부 체크
        //var hazopActYn = "${register.hazopActYn}";
        var hazopActYn = $("input[name='hazopActYn']:checked").val();
        if(hazopActYn == "Y" && !isHazopCompleted()) {
            alert("HAZOP Study를 먼저 완료해주세요.");
            return false;
        }
        
        if($.validation() && confirm("완료 하시겠습니까?")) {
            $('#pageLoadingWrap').show();
            $("form").attr("action", "safeCheckComp.do").submit();
        }
    });
    
    // HAZOP 라디오 버튼 변경 시 처리
    $(".hazopActYn").click(function(){
	    if($(this).val()=="N") {
	        $(".hazop").hide();
	        enableInputDateT();  // 활성화
	        enableCompletionButtons();
	        updateHazopMessage(false);
	    } else {  // "Y"인 경우
	        $(".hazop").show();
	        disableInputDateTSimple();  // 조건 없이 무조건 비활성화
	        disableCompletionButtons();
	        updateHazopMessage(true);
	    }
	});
    
    // 기술검토 확인 버튼
    $(".checkZ02D").click(function() {
        // HAZOP Study가 필요한 경우 저장 완료 여부 체크
        //var hazopActYn = "${register.hazopActYn}";
        var hazopActYn = $("input[name='hazopActYn']:checked").val();
        if(hazopActYn == "Y" && !isHazopCompleted()) {
            alert("HAZOP Study를 먼저 완료해주세요.");
            return false;
        }
        
        if(confirm("완료 하시겠습니까? 완료 후 Project Engineer가 다음 단계를 진행합니다")) {
            $('#pageLoadingWrap').show();
            
            var riskFileCnt = 0;
            $("#fileList > tbody> tr").each(function (){
                if(!$(this).is(":hidden")){
                    var file = $(this).children("td").children(".fileNm").val();
                    if(file != "" && file != undefined) {
                        riskFileCnt++;
                    }
                }
            });        	
            $("form").attr("action", "safeCheckZ02d.do").submit();
        }
    });

    // 유효성 검사 함수
    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
            safetyChkDt : {method:"class",type:"text", msg:"P&ID 최종본 일자를 입력하세요."},
            safetyChkDt2 : {method:"class",type:"text", msg:"MR수행 일자를 입력하세요."},
        });
        return isValid;
    };
});

$(window).ready(function() {
    // 달력 설정
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
});

// HAZOP 설정 초기화 함수
/*
function initializeHazopSettings() {
    var hazopActYn = "${register.hazopActYn}";
    
    // 3가지 조건 중 하나라도 참이면 활성화
    var shouldEnable = (
        hazopActYn == "N" ||                    // 불필요 선택
        isHazopCompleted() ||                   // HAZOP 완료
        $("input[name='hazopActYn']:checked").val() == "N"  // 현재 불필요 선택됨
    );
    
    if(shouldEnable) {
        if(hazopActYn == "N") {
            $(".hazop").hide();
        }
        enableInputDateT();
        enableCompletionButtons();
        updateHazopMessage(false);
    } else if(hazopActYn == "Y") {
        $(".hazop").show();
        disableInputDateT();
        disableCompletionButtons();
        updateHazopMessage(true);
    }
}
*/

function initializeHazopSettings() {
    var hazopActYn = "${register.hazopActYn}";
    
    if(hazopActYn == "N") {
        $(".hazop").hide();
        enableInputDateT();
        enableCompletionButtons();
    } else if(hazopActYn == "Y") {
        $(".hazop").show();
        disableInputDateTSimple();  // 조건 없는 비활성화 사용
        disableCompletionButtons();
    }
}

// inputDate_t 비활성화 함수 (개선된 버전)
function enableInputDateT() {
    console.log("enableInputDateT 호출됨");
    
    $(".inputDate_t").removeClass("disabled-section");
    $(".inputDate_t input[type='text']").prop("disabled", false).removeClass("disabled");
    
    // 모든 달력 아이콘 기본 활성화
    $(".inputDate_t img.ui-datepicker-trigger").css({
        "opacity": "1",
        "pointer-events": "auto",
        "cursor": "pointer"
    }).removeClass("disabled-calendar").prop("disabled", false);
    
    // name 속성별 개별 처리
    $("img[name='pnid-calendar']").css({
        "opacity": "1",
        "pointer-events": "auto",
        "cursor": "pointer"
    }).removeClass("disabled-calendar").prop("disabled", false);
    
    $("img[name='mrprfm-calendar']").css({
        "opacity": "1",
        "pointer-events": "auto", 
        "cursor": "pointer"
    }).removeClass("disabled-calendar").prop("disabled", false);
    
    // 클래스별 개별 처리
    $(".pnid-calendar, .mrprfm-calendar").css({
        "opacity": "1",
        "pointer-events": "auto",
        "cursor": "pointer"
    }).removeClass("disabled-calendar").prop("disabled", false);
    
    $(".pnid-input, .mrprfm-input").prop("disabled", false).removeClass("disabled");
    
    // 달력 이벤트 재바인딩
    rebindCalendarEvents();
}

// 확실한 달력 비활성화 함수 
function disableInputDateT() {
    console.log("disableInputDateT 호출됨");
    
    // 3가지 조건 확인
    var hazopActYn = "${register.hazopActYn}";
    var currentSelection = $("input[name='hazopActYn']:checked").val();
    var isCompleted = isHazopCompleted();
    
    // 하나라도 참이면 활성화 유지
    if (hazopActYn == "N" || currentSelection == "N" || isCompleted) {
        console.log("조건 만족 - 활성화 유지");
        enableInputDateT();
        return;
    }
    
    $(".inputDate_t").addClass("disabled-section");
    $(".inputDate_t input[type='text']").prop("disabled", true).addClass("disabled");
    
    // 모든 방식으로 비활성화
    $(".inputDate_t img.ui-datepicker-trigger").css({
        "opacity": "0.5",
        "pointer-events": "none",
        "cursor": "not-allowed"
    }).addClass("disabled-calendar").prop("disabled", true);
    
    $("img[name='pnid-calendar'], img[name='mrprfm-calendar']").css({
        "opacity": "0.5",
        "pointer-events": "none",
        "cursor": "not-allowed"
    }).addClass("disabled-calendar").prop("disabled", true);
    
    $(".pnid-calendar, .mrprfm-calendar").css({
        "opacity": "0.5",
        "pointer-events": "none",
        "cursor": "not-allowed"
    }).addClass("disabled-calendar").prop("disabled", true);
    
    $(".pnid-input, .mrprfm-input").prop("disabled", true).addClass("disabled");
}

function rebindCalendarEvents() {
    // 기존 이벤트 제거
    $(".inputDate").off('focus');
    
    // 새로운 이벤트 바인딩
    $(".inputDate").datepicker({
        dateFormat: "yy-mm-dd",
        showOn: "button",
        changeYear: true,
        changeMonth: true,
        buttonImage: "images/icon_calendar_.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택"
    });
}

// 달력 기능 초기화
function initializeDatePickers() {
    // 기존 datepicker 제거 후 재생성
    $(".inputDate").datepicker("destroy");
    
    $(".inputDate").datepicker({
        dateFormat: "yyyy-mm-dd",
        showOn: "button",
        changeYear: true,
        changeMonth: true,
        buttonImage: "images/icon_calendar_.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택"
    });
}

// 완료 버튼들 비활성화 함수
function disableCompletionButtons() {
    $(".completion-button, .safeCNF, .comp, .checkZ02D").prop("disabled", true).css({
        "opacity": "0.3",
        "cursor": "not-allowed",
        "filter": "grayscale(80%)",
        "pointer-events": "none"
    }).addClass("disabled-button");
}


function disableInputDateTSimple() {
    $(".inputDate_t").addClass("disabled-section");
    $(".inputDate_t input[type='text']").prop("disabled", true).addClass("disabled");
    
    // 모든 달력 아이콘 비활성화
    $(".inputDate_t img.ui-datepicker-trigger, .pnid-calendar, .mrprfm-calendar").css({
        "opacity": "0.5",
        "pointer-events": "none",
        "cursor": "not-allowed"
    }).addClass("disabled-calendar").prop("disabled", true);
    
    // 입력 필드 비활성화
    $(".pnid-input, .mrprfm-input").prop("disabled", true).addClass("disabled");
}

// 완료 버튼들 활성화 함수
function enableCompletionButtons() {
    $(".completion-button, .safeCNF, .comp, .checkZ02D").prop("disabled", false).css({
        "opacity": "1",
        "cursor": "pointer",
        "filter": "none",
        "pointer-events": "auto"
    }).removeClass("disabled-button");
}

// HAZOP 메시지 업데이트 함수
function updateHazopMessage(isHazopRequired) {
    if(isHazopRequired) {
        $(".hazop-pending-message").show();
        $(".hazop-completed-message").hide();
    } else {
        $(".hazop-pending-message").hide();
        $(".hazop-completed-message").hide();
    }
}

// HAZOP 완료 여부 체크 함수
function isHazopCompleted() {
    return window.hazopCompleted === true || 
           sessionStorage.getItem('hazopCompleted_' + $("#mrReqNo").val()) === 'true';
}

// HAZOP 완료 플래그 설정 함수
function setHazopCompletedFlag(completed) {
    window.hazopCompleted = completed;
    var mrReqNo = $("#mrReqNo").val();
    if(mrReqNo) {
        sessionStorage.setItem('hazopCompleted_' + mrReqNo, completed.toString());
    }
}

// HAZOP Study 저장 완료 후 호출되는 함수 (팝업에서 호출)
function handleHazopSaveComplete() {
    enableInputDateT();
    enableCompletionButtons();
    setHazopCompletedFlag(true);
    updateHazopMessage(false);
    $(".hazop-completed-message").show();
    alert("HAZOP Study가 저장되었습니다.");
}

// URL 파라미터를 가져오는 함수 (호환성을 위한 기존 방식)
function getUrlParams() {
    var params = {};
    var queryString = window.location.search.substring(1);
    var pairs = queryString.split('&');
    
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split('=');
        if (pair.length === 2) {
            params[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
        }
    }
    return params;
}

// URL에서 특정 파라미터를 제거하는 함수
function removeUrlParameter(paramName) {
    var url = window.location.href;
    var urlParts = url.split('?');
    
    if (urlParts.length >= 2) {
        var params = urlParts[1].split('&');
        var newParams = [];
        
        for (var i = 0; i < params.length; i++) {
            var param = params[i].split('=');
            if (param[0] !== paramName) {
                newParams.push(params[i]);
            }
        }
        
        var newUrl = urlParts[0];
        if (newParams.length > 0) {
            newUrl += '?' + newParams.join('&');
        }
        
        window.history.replaceState({}, document.title, newUrl);
    }
}

</script>