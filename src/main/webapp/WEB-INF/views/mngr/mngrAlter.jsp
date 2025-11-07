<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">MR변경</h4>
</div>

<!-- button -->
<div class="button_top">
    <input id="search" type="image" src="images/btn_inquiry.png">
<!--     <input id="save" type="image" src="images/btn_save.png"> -->
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
            <th scope="row">MR번호</th>
            <td>
                <div class="form_group">
                <input type="text" name="mrNo" class="i_input"/>
                </div>
            </td>
            <th scope="row">임시MR번호</th>
            <td>
                <div class="form_group">
                <input type="text" name="mrTmpNo" class="i_input">
                <input type="hidden" name="page" class="page">
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    </form>


<table border="1" cellspacing="0" style="width: 100%; border-bottom: 29px solid #ffffff; border-right: 1px solid #ffffff; font-size: 12px; text-align: center; border-collapse: collapse; border-color:#fffff">
        <colgroup>
        <col width="15%">
        <col width="35%">
        <col width="15%">
        <col width="35%">
        </colgroup>
        <tbody>
        <tr>
        <td colspan="4"></td>
        </tr>
        </tbody>
    </table>
<div class="button_middle">
        <!-- MR 조회 --><!-- input id="save" type="image" style="vertical-align:bottom" src="images/btn_mrSearch.png"-->
        <!-- 사업명 변경 --><input id="bzNameChange" type="image" style="vertical-align:bottom" src="images/btn_bzNameChange.png">
        <!-- CAPEX적용 --><input id="capexApply" type="image" style="vertical-align:bottom" src="images/btn_capexApply.png">
        <!-- MR사업취소 --><input id="bzCancel" type="image" style="vertical-align:bottom" src="images/btn_bzCancel.png">
        <!-- MR삭제 --><input id="mrDelete" type="image" style="vertical-align:bottom" src="images/btn_mrDelete.png">
    </div>
<form id="modifyForm" method="post">
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
            <th scope="row">사업명</th>
            <td>
            	<div class="form_group">
            	<input type="hidden" id="mrNo" name="mrNo"/>
            	<input type="hidden" id="mrReqNo" name="mr_mrReqNoreq_no"/>
                <input type="text" id="mrReqTitle" name="mrReqTitle" class="i_input_full"/>
                </div>
            </td>
            <th scope="row">CAPEX번호</th>
            <td>
            	<div class="form_group">
                <input type="text" id="capexNo" name="capexNo" class="i_input_full">
                <input type="hidden" name="page" class="page">
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row">투자지출품의번호</th>
            <td>
            	<div class="form_group">
                <input type="text" id="invstCostNo" name="invstCostNo" class="i_input_full"/>
                </div>
            </td>
            <th scope="row">예산 금액</th>
            <td>
            	<div class="form_group">
                <input type="text" id="sapCostTot" name="sapCostTot" class="i_input_full">
                <input type="hidden" name="page" class="page">
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    </form>
    
    <!-- space non 10 -->
    <hr class="divider_none_10" />

    <div class="list_300 single searchList_div">
        <table class="table_col searchList" border="1" cellspacing="0">
            <tr>
                <th scope='col' style='display:none'>MR Req No.</th>
                <th scope='col' style='display:none'>상태 코드</th>
                <th scope='col'>MR No.</th>
                <th scope='col'>사업명</th>
                <th scope='col'>Capex No</th>
                <th scope='col'>투자지출품의번호</th>
                <th scope='col'>예산금액</th>
            </tr>
        </table>
    </div>

</div>

</form>

<table id="appLineTemp" style="display:none">
   <tr>
    <td>
        <div class="form_group">
            <input type="text" class="i_input tempText" readOnly/>
            <input type="hidden" class="tempValue value" name="" />
            <input type="hidden" class="tempCode code" name="" value="Z02C"/>
            <input type="hidden" class="tempDetNo detNo" name=""/>
            <input type="hidden" class="tempDutyText dutyText" name="" />
            <input type="hidden" class="tempTeamText teamText" name="" />
            <input type="hidden" class="tempTeamValue teamValue" name="" />
            <img src="images/icon_search.png" class="cursor empSearch" data-role="safeEtc3" />
            <input type="checkbox" class="check" value="true">
        </div>
    </td>
    <td>
        <div class="form_group">
            <input type="text" class="i_input limitDate" value="" readOnly  name=""/>
        </div>
    </td>
  </tr>
  
 
		            
</table>

<script>
$(document).ready(function() {

	var page= 1;
	var totalCount = 0;
	var count = 0;
	$(".searchList_div").scroll(function() {
		  var elem = $(this);
	      var tr = "";
		  if ( elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight() && count < totalCount)
		    {
	          page++;
	          $(".page").val(page);
			  var formData = $("#searchForm").serialize();
	            $.ajax({
	                type:"POST",
	                url:"detailSearch.do",
	                data:formData,
	                dataType:"JSON",
	                success : function(data) {
	                    if(data.mrSearchList!=null) {
	                          $.each(data.mrSearchList, function() {
	                              count++;
	                              tr = "";
	                              tr += "<tr class='mrLink cursor'>";
	                              tr += "<td style='display:none'>"+this.mrReqNo +"</td>";
	                              tr += "<td>"+this.mrNo +"</td>";
	                              tr += "<td>"+this.mrReqTitle +"</td>";
	                              tr += "<td>"+this.capexNo +"</td>";
	                              tr += "<td>"+this.invstCostNo +"</td>";
	                              tr += "<td>"+this.sapCostTot +"</td>";
	                              tr += "</tr>";
	                              $(".searchList").append(tr);
	                           });
	                    }
	                 },
	                 error : function(request) {
	                     if(request.status==500) {
	                         history.go(0);
	                     }
	                 }
	             });
		    }
	});

	$("#bzNameChange").click(function(){
		if($("#mrReqTitle").val()=="") {
			alert("담당자를 변경하려고 하는 MR을 먼저 선택하세요.");
		}else if(confirm("사업자명을 변경하시겠습니까?")) {
			//navigator.clipboard.writeText(this.mrNo);
			//선택한것만 변경하기
			
	    	//$.sortMrInfo();
            //$('#pageLoadingWrap').show();
            
         	 //$('input:checkbox[id="compC"]').attr("checked", $('input:checkbox[id="compF"]').val()); 
         	 
            $("#modifyForm").attr("action", "bzNameChange.do").submit();
	    }
	});
	
	$("#mrDelete").click(function(){
		if($("#mrNo").val()=="") {
			alert("MR 삭제 하려고 하는 MR을 먼저 선택하세요.");
		}else if(confirm("삭제하시겠습니까?")) {
			//navigator.clipboard.writeText(this.mrNo);
			//선택한것만 변경하기
			
	    	//$.sortMrInfo();
            //$('#pageLoadingWrap').show();
            
         	 //$('input:checkbox[id="compC"]').attr("checked", $('input:checkbox[id="compF"]').val()); 
         	 
            $("#modifyForm").attr("action", "mrDelete.do").submit();
	    }
	});
	
	$("#bzCancel").click(function(){
		if($("#mrNo").val()=="") {
			alert("사업 취소 하려고 하는 MR을 먼저 선택하세요.");
		}else if(confirm("취소하시겠습니까?")) {
			//navigator.clipboard.writeText(this.mrNo);
			//선택한것만 변경하기
			
	    	//$.sortMrInfo();
            //$('#pageLoadingWrap').show();
            
         	 //$('input:checkbox[id="compC"]').attr("checked", $('input:checkbox[id="compF"]').val()); 
         	 
            $("#modifyForm").attr("action", "bzCancel.do").submit();
	    }
	});
	
	$("#capexApply").click(function(){
		if($("#mrNo").val()=="") {
			alert("CAPEX적용을 변경하려고 하는 MR을 먼저 선택하세요.");
		}else if(confirm("CAPEX적용을 변경하시겠습니까?")) {
			//navigator.clipboard.writeText(this.mrNo);
			//선택한것만 변경하기
			
	    	//$.sortMrInfo();
            //$('#pageLoadingWrap').show();
            
         	 //$('input:checkbox[id="compC"]').attr("checked", $('input:checkbox[id="compF"]').val()); 
         	 
            $("#modifyForm").attr("action", "capexApply.do").submit();
	    }
	});

    $("#search").click(function(){
    	document.getElementById("mrReqTitle").value = '';
    	document.getElementById("capexNo").value = '';
    	document.getElementById("invstCostNo").value = '';
    	document.getElementById("sapCostTot").value = '';
    	
        var tr ="";
        count = 0;
        page=1;
        var formData = $("#searchForm").serialize();
        $(".searchList").find("tr").remove().end();
        tr ="<tr>";
        tr += "<th scope='col' style='display:none'>MR Req No.</th>";
        tr += "<th scope='col'>MR No.</th>";
        tr += "<th scope='col'>사업명</th>";
        tr += "<th scope='col'>CAPEX번호</th>";
        tr += "<th scope='col'>투자지출품의번호</th>";
        tr += "<th scope='col'>예산금액</th>";
        tr += "</tr>";

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"alterSearch.do",
               data:formData,
               dataType:"JSON",
               success : function(data) {
                   if(data.mrSearchList!=null) {
                         $.each(data.mrSearchList, function() {
                           if(totalCount==0){
                                 totalCount = this.totalCount;
                                 //wj alert('리스트:' + totalCount);
                           }
                           count++;
                             tr = "";
                             tr += "<tr class='mrLink cursor'>";
                             tr += "<td style='display:none'>"+this.mrReqNo+"</td>";
                             tr += "<td>"+this.mrNo+"</td>";
                             tr += "<td>"+this.mrReqTitle+"</td>";
                             tr += "<td>"+this.capexNo+"</td>";
                             tr += "<td>"+this.invstCostNo+"</td>";
                             tr += "<td>"+this.sapCostTot+"</td>";
                             tr += "<tr>";
                             $(".searchList").append(tr);
                          });
                   }
               }
        });

    });

});


$(document).on("mouseover",".mrLink",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".mrLink",function() {
   $(this).css("background","#ffffff");
}).on("click",".mrLink",function() {
	var infoTr = $("#selectForm").find("table").children("tbody");

	document.getElementById("mrReqNo").value = $(this).children("td:eq(0)").html();
	document.getElementById("mrNo").value = $(this).children("td:eq(1)").html();
	document.getElementById("mrReqTitle").value = $(this).children("td:eq(2)").html();
	document.getElementById("capexNo").value = $(this).children("td:eq(3)").html();
	document.getElementById("invstCostNo").value = $(this).children("td:eq(4)").html();
	document.getElementById("sapCostTot").value = $(this).children("td:eq(5)").html();
	
	
/*
	infoTr.children("tr:eq(0)").children("th:eq(0)").html($(this).children("td:eq(1)").html());
    infoTr.children("tr:eq(1)").children("th:eq(0)").html($(this).children("td:eq(1)").html());

	infoTr.children("tr:eq(0)").children("td:last").html($(this).children("td:eq(2)").html());
    infoTr.children("tr:eq(1)").children("td:last").html($(this).children("td:eq(3)").html());
*/

});

$(document).on("click",".empSearch",function() {
    $.popupPopup({url:"popup/empSearch.do", width:"500", height:"500", targetName:$(this).attr("data-role")});
});

$(document).on("mouseover",".goMr",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".goMr",function() {
   $(this).css("background","#ffffff");
}).on("click",".goMr",function() {
    var url ="";


    switch ($(this).parent("tr").children("th:eq(0)").html()) {
    case "Z0010":
        url="mrRqRegister";
        break;
    case "Z0020":
        url="mrTech";
        break;
    case "Z0030":
        url="mrTechInvest";
        break;
    case "Z0040":
        url="mrJobsReview";
        break;
    case "Z0050":
        url="ivstCostRegister";
        break;
    default:
        url="mrRqRegister";
        break;
    }
    document.location.href = url+".do?mrReqNo="+$(".mrReqNo").val();
});

$.sortMrInfo = function () {
	var count = 0 ;
	var thisInput;
	
	//alert("카운터 시작");
	
	$(".value").each(function(){

		thisInput = $(this).parent("div");
		
		//담당자		
		//alert("$(this).val():"+$(this).val());

		if($(this).val()!=""){
			
    		thisInput.children(".value").attr("name", "appLine["+count+"].chrgEmpNo");
    		thisInput.children(".detNo").attr("name", "appLine["+count+"].mrReqProcStepDetNo");
    		thisInput.children(".dutyText").attr("name", "appLine["+count+"].thdayPos");
    		thisInput.children(".teamText").attr("name", "appLine["+count+"].thdayTeam");
    		thisInput.children(".teamValue").attr("name", "appLine["+count+"].chrgTeamNo");
    		thisInput.children(".check").attr("name", "appLine["+count+"].empChange");
    	    thisInput.children(".code").attr("name", "appLine["+count+"].chrgrClCd");
    	    if(thisInput.children(".stepCd")!=null) {
    	    	thisInput.children(".stepCd").attr("name", "appLine["+count+"].mrStepCd");
    	    }

    		if(thisInput.parent("td").parent("tr").children("td:last").children("div").children(".inputDate")!=null) {
    			thisInput.parent("td").parent("tr").children("td:last").children("div").children(".inputDate").attr("name", "appLine["+count+"].fstProcTrmDt");
    		}

    	    count++;
		}
	});



}
</script>