<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">위험성검토</h4>
</div>
<!-- button -->

<!-- HSW 재전송 추가  -->

<div class="button_top">    
    <input type="image" src="images/btn_complete.png"  class="cursor save"/><!-- 저장 :확인-->
    <input type="image" src="images/btn_close.png" class="cursor close"/><!-- 닫기-->
</div>

<form id="registerForm" method="post">
<input type="hidden" id="mrReqNo" name="mrReqNo" value="<c:out value="${riskCheck.mrReqNo}"/>"/>
    <input type="hidden" id="mrNo" value="<c:out value="${riskCheck.mrNo}"/>"/>
    <input type="hidden" id="mrInvstCost" value="<c:out value="${mrInvstCost}"/>"/>
    <input type="hidden" id="plantNo" value="<c:out value="${riskCheck.plant}"/>"/>
	<input type="hidden" name="itemCd" value="RISK"/>
    <input type="hidden" class="writerValue" name="appLine[0].chrgEmpNo" />
    <input type="hidden" name="appLine[0].chrgrClCd" value="Z02D"/>
    <input type="hidden" class="writerDutyText" name="appLine[0].thdayPos" />
    <input type="hidden" class="writerTeamText" name="appLine[0].thdayTeam" />
    <input type="hidden" class="writerTeamValue" name="appLine[0].chrgTeamNo" />
    <input type="hidden" name="appLine[0].procTrmDt" value="9999-12-31" readOnly/>
    <input type="hidden" id="stepCd" name="stepCd" value="Z0060"/>
    
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
            <th class="dep2" scope="row">확인자</th>
            <td>
                <div class="form_group">
                <c:if test="${modeClass!='IAD'}"><span class="agreeText"></span></c:if>
                <c:if test="${modeClass=='IAD'}"><input type="text" class="i_input agreeText" readOnly/></c:if>
                <input type="hidden" class="agreeValue" name="appLine[1].chrgEmpNo" />
                <input type="hidden" name="appLine[1].chrgrClCd" value="Z02C"/>
                <input type="hidden" class="agreeDutyText" name="appLine[1].thdayPos" />
                <input type="hidden" class="agreeTeamText" name="appLine[1].thdayTeam" />
                <input type="hidden" class="agreeTeamValue" name="appLine[1].chrgTeamNo" />
                <img src="images/icon_search.png" class="cursor empSearch IAD" data-role="agree" style="display:none"/>
                </div>
            </td>
            <th scope="row">확인일</th>
            <td>
               <div class="form_group">               
                    <input type="text" name="appLine[1].procTrmDt" class="i_input inputDate limitDate agreeEndDate" value="" readOnly/>(결재중)
               
	               <c:if test="${modeClass!='IAD'}">
	                    <input type="hidden" name="appLine[1].procTrmDt" class="agreeEndDate" value=""/>
	               </c:if>
               </div>
            </td>
        </tr>
        <tr>
        	<th scope="row">MR번호</th>
        	<td colspan="3"><c:out value="${checkList.mrNo}"/></td>
    	</tr>
    	<tr>
        	<th scope="row">사업명</th>
        	<td colspan="3"><c:out value="${checkList.mrReqTitle}"/></td>
    	</tr>    
        </tbody>
    </table>

<!-- space non 10 -->
<hr class="divider_none_10" />

<table class="table_pop_row" border="1" cellspacing="0" summary="">
    <caption class="blind"></caption>
    <colgroup>
    <col width="5%">
    <col width="85%">
    <col width="5%">
    <col width="5%">
    </colgroup>
    <thead>
    <tr>
        <th rowspan="2" scope="col">번호</th>
        <th rowspan="2" scope="col">필수항목</th>
        <th colspan="2" scope="col">해당유무</th>
    </tr>
    <tr>
        <th scope="col">O</th>
        <th scope="col">X</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row" class="fff">1</th>
        <td>
            설비 변경에 따른 기존 설계 운전(유량/온도/압력) 조건 초과 시<br />
            (단, 유량의 경우 Design조건의 10% 이하는 미실시함)
        </td>
        <td class="center"><input type="hidden" name="clCd01" value="RISK01" />
            <input type="radio" name="clCtt01" class="vam IAD clCtt01" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt01=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt01" class="vam IAD clCtt01" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt01=='2'}">checked</c:if>/></td>
    </tr>
    <tr>
        <th scope="row" class="fff">2</th>
        <td>
            공정 원료 변경 사항이 있을 시
        </td>
        <td class="center"><input type="hidden" name="clCd02" value="RISK02" />
            <input type="radio" name="clCtt02" class="vam IAD clCtt02" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt02=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt02" class="vam IAD clCtt02" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt02=='2'}">checked</c:if>/></td>
    </tr>
    <tr>
        <th scope="row" class="fff">3</th>
        <td>
            Control logic / Interlock logic 등의 변경 사항이 있을 시<br>
            (단,  Control logic 의 경우는 설비 변경 시에만 적용함)
        </td>
        <td class="center"><input type="hidden" name="clCd03" value="RISK03" />
            <input type="radio" name="clCtt03" class="vam IAD clCtt03" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt03=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt03" class="vam IAD clCtt03" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt03=='2'}">checked</c:if>/></td>
    </tr>
    <tr>
        <th scope="row" class="fff">4</th>
        <td>
            Control valve 설치 시
        </td>
        <td class="center"><input type="hidden" name="clCd04" value="RISK04" />
            <input type="radio" name="clCtt04" class="vam IAD clCtt04" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt04=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt04" class="vam IAD clCtt04" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt04=='2'}">checked</c:if>/></td>
    </tr>
    <tr>
        <th scope="row" class="fff">5</th>
        <td>
            Relief load에 따른 변경 사항이 있을 시
        </td>
        <td class="center"><input type="hidden" name="clCd05" value="RISK05" />
            <input type="radio" name="clCtt05" class="vam IAD clCtt05" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt05=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt05" class="vam IAD clCtt05" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt05=='2'}">checked</c:if>/></td>
    </tr>
     <!-- 202007 6번항목 제외처리 -->
    <tr style="display:none">
        <th scope="row" class="fff">6</th>
        <td>
            설비 변경이 수반되는 운전 절차서 변경이 있을 시
        </td>
        <td class="center"><input type="hidden" name="clCd06" value="RISK06" />
            <input type="radio" name="clCtt06" class="vam IAD clCtt06" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt06=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt06" class="vam IAD clCtt06" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt06=='2'}">checked</c:if>/></td>
    </tr>
    
    <tr>
        <th scope="row" class="fff">6</th>
        <td>
            공정 또는 설비의 용도 변경이 있을 시
        </td>
        <td class="center"><input type="hidden" name="clCd07" value="RISK07" />
            <input type="radio" name="clCtt07" class="vam IAD clCtt07" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt07=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt07" class="vam IAD clCtt07" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt07=='2'}">checked</c:if>/></td>
    </tr>
    <tr>
        <th scope="row" class="fff">7</th>
        <td>
            이외 해당 기술팀 팀장이 설비 변경에 따른 신규 공정 위험성 평가가 필요하다고 판단될 경우
        </td>
        <td class="center"><input type="hidden" name="clCd08" value="RISK08" />
            <input type="radio" name="clCtt08" class="vam IAD clCtt08" value="1" disabled="disabled" <c:if test="${riskCheck.clCtt08=='1'}">checked</c:if>/>
        </td>
        <td class="center"><input type="radio" name="clCtt08" class="vam IAD clCtt08" value="2" disabled="disabled" <c:if test="${riskCheck.clCtt08=='2'}">checked</c:if>/></td>
    </tr>
    </tbody>
</table>


<!-- space non 10 -->
<hr class="divider_none_10" />

<table class="table_row" border="1" cellspacing="0">
    <caption class="blind"></caption>
    <colgroup>
    <col width="15%">
    <col width="85%">
    </colgroup>
    <tbody>
    <tr>
        <th scope="row">HAZOP / CheckList</th>
        <td>
            <div class="form_group">
            <input type="radio" class="hazopActYn" name="hazopActYn" value="Y" <c:if test="${riskCheck.hazopActYn=='Y'}">checked </c:if> disabled="disabled"> HAZOP
            <c:if test="${riskCheck.hazopActYn=='Y'}"><img src="images/btn_inWrite.png" class="vam cursor hazop"/></c:if>
            <img src="images/btn_inWrite.png" class="vam cursor hazop"/>
            <input type="radio" class="hazopActYn" name="hazopActYn" value="N" <c:if test="${riskCheck.hazopActYn=='N'}">checked </c:if> disabled="disabled"> CheckList
            <c:if test="${riskCheck.hazopActYn=='N'}"><img src="images/btn_inWrite.png" class="vam cursor checkList"/></c:if>
            <img src="images/btn_inWrite.png" class="vam cursor checkList"/>
            
            </div>
        </td>       
    </tr>
    </tbody>
</table>

<!-- space non 10 -->
<hr class="divider_none_10" />

    <table class="table_row" id="file" border="1" cellspacing="0">
        <caption class="blind"></caption>
        <colgroup>
        <col width="15%">
        <col width="85%">
        </colgroup>
        <tbody>
        <tr>
        <th scope="row" class="vam fileAdd">첨부파일
          <img id="addFile" src="images/icon_s_plus.png" border="0" class="cursor IAD addFile" style="display:none"/>
        </th>
        <td class="section_text pl5 pt5 pb5" colspan="3">
          <div class="form_group">
            <table id="fileList" class="table_default_margin" border="1" cellspacing="0">
              <tr style="display:none"><td></td></tr>
              <c:forEach var="mrAtchFiles" items="${riskCheck.mrAtchFiles}" varStatus="loop">
              <tr>
                 <td>
                  <input type="text" class="i_input" value="${mrAtchFiles.fileCdNm}" readonly>
                  <input type="text" class="i_input_300 fileNm" value="${mrAtchFiles.fileNm}" readonly>
                  <img src="images/btn_inFile.png" class="cursor fileDown"/>
                  <img class="cursor IAD removeDownFile" src="images/icon_minus.png" style="display:none"/>
                  <input type="hidden" class="fileCode" value="${mrAtchFiles.fileCd}"/>
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
</form>
<input type="hidden" id= "porcLineListCnt" value="0"> 
<input type="hidden" id= "checkListCnt" value="0"> 

<script>
$(window).ready(function() {
	$("#porcLineListCnt").val("${riskCheck.porcLineList.size()}");
	$("#checkListCnt").val("${checkList.checks.size()}"==""?0:"${checkList.checks.size()}");

	var appClass;
	   "<c:forEach var='appLine' items='${riskCheck.appLine}'>";
	        appClass = "";

    	    switch ("${appLine.chrgrClCd}") {
    	    case "Z02C":
    	        appClass = "agree";
    	        break;
    	    case "Z02D":
    	        appClass = "writer";
    	        break;
    	    }

    	    $.appLineSet({className:appClass, dutyText:"${appLine.thdayPos}",
    	        text:"${appLine.chrgEmpName}", value:"${appLine.chrgEmpNo}",
    	        teamText:"${appLine.thdayTeam}" , teamValue:"${appLine.chrgTeamNo}",
    	        endDate:"<fmt:formatDate value='${appLine.fstProcTrmDt}' pattern='yyyy-MM-dd' />",
    	        date:"<fmt:formatDate value='${appLine.lastChgDt}' pattern='yyyy-MM-dd' />"});
	    "</c:forEach>";

    $(".checkList").click(function() {
         $.popupPopup({url:"mrCheckListExe.do?&mrReqNo="+$("#mrReqNo").val() + "&stepCd=Z0060", width:"820", height:"700", targetName:$(this).attr("data-role"), scroll:"yes"});
    });

    $(".hazop").click(function() {
        $.popupPopup({url:"mrHazopStudyExe.do?&mrReqNo="+$("#mrReqNo").val() + "&stepCd=Z0060", width:"820", height:"700", targetName:$(this).attr("data-role"), scroll:"yes"});
    });






    $(".empSearch").click(function() {
        $.popup({url:"empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
    });

    $(".save").click(function () {
     	if($.validation())
        if(confirm("저장 하시겠습니까?")) {
            $.removeComma();
            $('#pageLoadingWrap').show();
            $("form").attr("action", "${saveURL}").submit();	//mrRiskCheckSave.do, mrRiskCheckUpdate.do
            //window.open("", "_self").close();
        }
     	
     	alert("저장이 완료되었습니다.");
        
        // 부모 창에 완료 알림
        if(window.opener && typeof window.opener.handleHazopSaveComplete === 'function') {
            window.opener.handleHazopSaveComplete();
        }
     	
        // 팝업 닫기
        window.close();
    });
    
    $(".close").click(function () {
     	if(confirm("창을 닫으시겠습니까?")) {
     		window.close();
        }     	
    });

    $(".appReq").click(function () {
    	var hazopActFlag = "";
    	
        if($.validation()){
        	if ($("input:radio[name='hazopActYn']:checked").val() =="Y" ) {
        		hazopActFlag = "Y";
        	}
        	if($("#checkListCnt").val()<1 && $("input:radio[name='hazopActYn']:checked").val() =="N" ){
        		alert("checkList 정보가 아직 등록되지 않았습니다.");
        		return;
        	}
        	if($("#porcLineListCnt").val()<1 && $("input:radio[name='porcActYn']:checked").val() =="Y" ){
        		alert("porc위원이 최소 1명이상 지정 되어야 합니다.");
        		return;
        	}
        	
        	//첨부파일 첨부여부 카운트
        	var riskFileCnt = 0;
        	var hazopActCnt = 0;
        	$("#fileList > tbody> tr").each(function (){
        		if(!$(this).is(":hidden")){
	        		var file = $(this).children("td").children(".fileNm").val();
	        		var fileCd = $(this).children("td").children(".fileCode").val();
	    			if (hazopActFlag == "Y"){
	    				console.log("fileCd : " + fileCd);
	    				switch (fileCd) {
	    	    	    case "0001":  //P&ID
	    	    	    	hazopActCnt++;
	    			    	break;
	    	    	    case "0005":  //DBM
	    	    	    	hazopActCnt++;
	    			    	break;
	    	    	    case "0008":  //HAZOP
	    	    	    	hazopActCnt++;
	    			    	break;
	    	    	    case "0013":  //정량적 평가
	    	    	    	//hazopActCnt++;
	    			    	break;
	    				}
	    			}
	    			if(file != "" && file != undefined){
	        			riskFileCnt++;
	   	    		}
	    		}
	    	});    	
			console.log("hazopActCnt : " + hazopActCnt);
			console.log("hazopActFlag : " + hazopActFlag);
	    	if(hazopActCnt < 3 && hazopActFlag =="Y"){
	    		alert("첨부파일을 등록하세요. ( 필수 : HAZOP, DBM, P&ID)");
	    		return;			//yoo 테스트 원복 240925
	    	}    	
        	if(riskFileCnt < 1 && hazopActFlag =="Y"){
        		alert("첨부파일을 첨부하세요.");
        		return;
        	}            
        }
    });

    //201909인터페이스 및 메일 테스트
    $(".appReq2").click(function () {
    	var hazopActFlag = "";
    	
            if(confirm("승인요청 하시겠습니까?")) {
                $.removeComma();
                $('#pageLoadingWrap').show();
                $("form").attr("action", "mrRiskCheckAppReq.do").submit();
            }
            //201912 승인요청시 HAZOP 체크후 she 인터페이스 추가
            //201912 지정된 porc 위원에게 e-mail 발송 
       
    });

    $(".porcSelect").click(function () {
    	//aaaaaaaa
    	
    	
    	var selectValue = $("input[type=radio][name=porcActYn]:checked").val();
    	//console.log(('input[name="porcActYn"]:checked').value);
    	console.log(selectValue);
    	 
    	
    	var mrInvstCost = document.getElementById('mrInvstCost').value;
    	console.log(mrInvstCost);
    	var nInvstCose = parseInt(mrInvstCost) * 1000;
    	
    	
    	switch (selectValue) {
    	  
    	  case 'Y':		// 필요
    	  {
    		  console.log( 'Y' );
    		  
    		  
    		  	if(nInvstCose >= 1000000000)
	   	    	{
	   	    		$.popup({url:"mrJobsPorc.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
	   	    	   
	   	    	}else{
	   	    		var retVal = confirm("10억 미만 입니다. 계속 하시겠습니까?");
	
	   	     	   if( retVal == true ){
	
	   	     		  console.log("확인선택!");
	   	     		 $.popup({url:"mrJobsPorc.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
	
	   	     	   }else{
	
	   	     		  console.log("취소선택!");
	
	   	     	   }
	   	    	}
    		  
    	  }
    	    break;

    	  case 'N':		// 불필요
    	  {
    		  console.log( 'N' );
    		  //$.popup({url:"mrJobsPorc.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
    	  }
    	    break;

    	  case 'S':		// 내용 공유
    	  {
    		  console.log( 'S' );
    		  
    		  if(nInvstCose < 50000000)
	  	    	{
    			  
    			    var retVal = confirm("해당 MR건이 투자비 5천만원 이상, 10억원 미만의 범위를 벗어 났습니다. 해당 건은 5천 미만 입니다. 계속 하시겠습니까?");

	  	     	    if( retVal == true ){
	
	  	     		    console.log("확인선택!");
	  	     		 $.popup({url:"mrShareContent.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
	
	  	     	    }else{
	
	  	     		    console.log("취소선택!");
	
	  	     	    }

	  	    		
	  	    	   
	  	    	}else if(nInvstCose > 1000000000){
  	    		   var retVal = confirm("해당 MR건이 투자비 5천만원 이상, 10억원 미만의 범위를 벗어 났습니다. 해당 건은 10억 이상 입니다. 계속 하시겠습니까?");

  	    		 if( retVal == true ){
  	    			
	  	     		    console.log("확인선택!");
	  	     		 $.popup({url:"mrShareContent.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
	
	  	     	    }else{
	
	  	     		    console.log("취소선택!");
	
	  	     	    }
	
	  	    	}else
	  	    	{
	  	    		$.popup({url:"mrShareContent.do?mrReqNo="+$("#mrReqNo").val() + "&porcYn=" + selectValue, scroll:"yes", width:"880", height:"500"});
	  	    	}
    		  
    	  }
    	    break;
    	  default:
    	    return;
    	}
    	
    	
        
    });

    //202007 6번(clCtt06)항목 제외처리
    $.validation = function(){
        var isValid = false;
        isValid = $.validator({
        	 agreeText : {method:"class",type:"text", msg:"승인자를 입력하세요."},
        	 //agreeEndDate : {method:"class",type:"text", msg:"승인기한을 입력하세요."},
        	 clCtt01 : {method:"class",type:"check", msg:"1번 항목을 선택하세요."},
        	 clCtt02 : {method:"class",type:"check", msg:"2번 항목을 선택하세요."},
        	 clCtt03 : {method:"class",type:"check", msg:"3번 항목을 선택하세요."},
        	 clCtt04 : {method:"class",type:"check", msg:"4번 항목을 선택하세요."},
        	 clCtt05 : {method:"class",type:"check", msg:"5번 항목을 선택하세요."},
        	 clCtt07 : {method:"class",type:"check", msg:"6번 항목을 선택하세요."},
        	 clCtt08 : {method:"class",type:"check", msg:"7번 항목을 선택하세요."}
        });
        return isValid;
    };


});

if("${riskCheck.porcActYn}" == "Y" && "${porcClass}" == "IASA") {
	$.popup({url:"mrJobsPorcWrite.do?&mrReqNo="+$("#mrReqNo").val(), scroll:"yes", width:"880", height:"500"});
};


$(function() {
    $( document ).tooltip({
      position: {
        my: "center bottom-20",
        at: "center top",
        using: function( position, feedback ) {
          $( this ).css( position );
          $( "<div>" )
            .addClass( "arrow" )
            .addClass( feedback.vertical )
            .addClass( feedback.horizontal )
            .appendTo( this );
        }
      }
    });
  });
//폼 초기화 함수
function initializeForm() {
    // 텍스트 입력 필드 초기화
    $("#popRiskCheckForm input[type='text']").val('');
    $("#popRiskCheckForm textarea").val('');
    
    // 라디오 버튼 초기화 (기본값 설정)
    $("#popRiskCheckForm input[type='radio']").prop('checked', false);
    
    // 체크박스 초기화
    $("#popRiskCheckForm input[type='checkbox']").prop('checked', false);
    
    // 셀렉트 박스 초기화
    $("#popRiskCheckForm select").prop('selectedIndex', 0);
    
    // 날짜 필드는 오늘 날짜로 설정
    var today = new Date().toISOString().split('T')[0].replace(/-/g, '-');
    $("#popRiskCheckForm input[type='date'], .inputDate").val(today);
}
</script>
