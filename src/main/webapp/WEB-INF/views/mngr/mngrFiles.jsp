<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<!-- title -->
<div id="content_header">
    <h4 class="content_header title">첨부파일 일괄 관리</h4>
</div>
<!-- button -->
<div class="button_top">
    <input id="search" type="image" src="images/btn_inquiry.png">
<!--     <input id="save" type="image" src="images/btn_save.png"> -->
</div>

<div id="content_wrap">
<form id="searchForm" method="post">
	<input type="hidden" id="mrNo" />
	<input type="hidden" id="plantNo"/>
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
            <th scope="row">사업명</th>
            <td>
                <div class="form_group">
                <input type="text" name="projectTitle" class="i_input_full">
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
                <th scope='col'>요청자</th>
                <th scope='col'>요청팀</th>
                <th scope='col'>기술검토팀</th>
                <th scope='col'>Job Eng.</th>
                <th scope='col'>수행팀</th>
                <th scope='col'>Project Eng.</th>
                <th scope='col'>진행상태</th>
                <th scope='col'>Capex No</th>
                <th scope='col' style='display:none'>플랜트</th>
            </tr>
        </table>
    </div>

    <div class="button_middle">
        <!-- 저장 --><input id="save" type="image" style="vertical-align:bottom" src="images/btn_save.png">
    </div>
</div>


<div id="content_wrap" >
    <form id="selectForm" method="post">
    <input type="hidden" class="mrReqNo" name="mrReqNo" value="0"  />
    <input type="hidden" id="mrStepCd" name="mrStepCd" value=""  />
    <input type="hidden" class="mrReqTitle" name="mrReqTitle" value=""  />
    <table class="table_row" border="1" cellspacing="0" style="margin-bottom:20px;">
        <colgroup>
        <col width="15%">
        <col width="15%">
        <col width="5%">
        <col width="65%">
        </colgroup>
        <tr>
            <th scope='col' style='display:none'></th>
            <th scope='col' colspan="2">MR No.</th>
            <td class="cursor goMr" colspan="2"></td>
        </tr>
        <tr>
            <th scope='col' style='display:none'></th>
            <th scope='col' colspan="2">사업명</th>
            <td class="cursor goMr" colspan="2"></td>
        </tr>
        <tr>
            <th scope="col">단계</th>
            <th scope="col">구분</th>
            <th scope="col" colspan="2">첨부파일</th>
        </tr>
        <tr>
            <th scope="row" colspan="2">MR요청서</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0010" data-role="0101" data-step="Z0010" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0101FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">기술 및 타당성 검토</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0020" data-role="0201" data-step="Z0020" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0201FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">초기 투자비 산정</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0030" data-role="0301" data-step="Z0030" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0301FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">타당성검토 확인</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0030" data-role="0401" data-step="Z0030" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0401FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr class="mrJobsReview">
            <th scope="row" rowspan="10">직무검토</th>
            <th scope="row" class="dep2">기본</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0501" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0501FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">기술</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0502" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0502FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">검사</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0503" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0503FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">운전</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0504" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0504FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">환경</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0505" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0505FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">회계</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0507" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0507FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2"> 에너지</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0508" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0508FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">산업안전보건법</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0510" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0510FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">고압가스관계법</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0511" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0511FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" class="dep2">소방관계법</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0040" data-role="0512" data-step="Z0040" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0512FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr class="mrRiskCheck">
            <th scope="row" class="dep2" colspan="2">위험성검토 / PORC</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z00R0" data-role="0601" data-step="Z00R0" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0601FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr class="mrIvstCost">
            <th scope="row" colspan="2">투자지출품의</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0050" data-role="0701" data-step="Z0050" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0701FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">가동 전 안전점검</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0070" data-role="0801" data-step="Z0070" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0801FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">자료등록</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0080" data-role="0901" data-step="Z0080" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 0901FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
        <tr>
            <th scope="row" colspan="2">MR완료</th>
	        <th scope="row" class="dep2"><img src="images/icon_s_plus.png" border="0" class="cursor addFile Z0090" data-role="1001" data-step="Z0090" style="display:none"/></th>
	        <td>
	          <div class="form_group">
	            <table class="table_default_margin 1001FileList" border="1" cellspacing="0">
	              <tr style="display:none"><td></td></tr>
	            </table>
	          </div>
	        </td>
        </tr>
    </table>
</form>

<table id="fileTemp" class="table_row" border="1" cellspacing="0" style="display:none">
      <tr>
	      <td>
	        <select class="fileDiv fileCd" name="mrAtchFiles[0].fileCd"></select>
	        <input type="file" class="fileForm" multiple>
	        <input type="hidden" class="realPath i_input_300">
	        <input type="text" class="i_input_300 multipartFile fileNm" name="mrAtchFiles[0].fileNm">
	        <img src="images/btn_search.png" class="inputFile cursor"/>
	        <img id="deleteFile" class="cursor removeFile" src="images/icon_minus.png" />
	        <input type="hidden" class="i_input_200 delFlag" name="mrAtchFiles[0].deleted" value="false"/>
	        <input type="hidden" class="i_input_200 insFlag" name="mrAtchFiles[0].inserted" value="true"/>
	        <input type="hidden" class="drawMngNo" name="mrAtchFiles[0].drawMngNo" value=""/>
	        <input type="hidden" class="fileStepCd" name="mrAtchFiles[0].fileStepCd" value=""/>
	        <input type="hidden" class="mrStepCd" name="mrAtchFiles[0].mrStepCd" value=""/>
	      </td>
      </tr>
  </table>
</div>

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
	                              tr += "<td style='display:none'>"+this.mrReqNo+"</td>";
	                              tr += "<td style='display:none'>"+this.mrStepCd+"</td>";
	                              tr += "<td>"+this.mrNo+"</td>";
	                              tr += "<td>"+this.mrReqTitle+"</td>";
	                              tr += "<td>"+this.reqEmpName+"</td>";
	                              tr += "<td>"+this.reqTeamName+"</td>";
	                              tr += "<td>"+this.jobTeamName+"</td>";
	                              tr += "<td>"+this.jobEmpName+"</td>";
	                              tr += "<td>"+this.actTeamName+"</td>";
	                              tr += "<td>"+this.proEmpName+"</td>";
	                              tr += "<td>"+this.mrStepNm+"</td>";
	                              tr += "<td>"+this.capexNo+"</td>";
	                              tr += "<td style='display:none'>"+this.plant+"</td>";
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

    $("#search").click(function(){
        var tr ="";
        count = 0;
        page=1;
        var formData = $("#searchForm").serialize();
        $(".searchList").find("tr").remove().end();
        tr ="<tr>";
        tr += "<th scope='col' style='display:none'>MR Req No.</th>";
        tr += "<th scope='col' style='display:none'>상태 코드</th>";
        tr += "<th scope='col'>MR No.</th>";
        tr += "<th scope='col'>사업명</th>";
        tr += "<th scope='col'>요청자</th>";
        tr += "<th scope='col'>요청팀</th>";
        tr += "<th scope='col'>기술검토팀</th>";
        tr += "<th scope='col'>Job Eng.</th>";
        tr += "<th scope='col'>수행팀</th>";
        tr += "<th scope='col'>Project Eng.</th>";
        tr += "<th scope='col'>진행상태</th>";
        tr += "<th scope='col'>Capex No</th>";
        tr += "<th scope='col' style='display:none'>플랜트</th>";
        tr += "</tr>";

        $(".searchList").append(tr);
        $.ajax({
               type:"POST",
               url:"detailSearch.do",
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
                             tr += "<td style='display:none'>"+this.mrStepCd+"</td>";
                             tr += "<td>"+this.mrNo+"</td>";
                             tr += "<td>"+this.mrReqTitle+"</td>";
                             tr += "<td>"+this.reqEmpName+"</td>";
                             tr += "<td>"+this.reqTeamName+"</td>";
                             tr += "<td>"+this.jobTeamName+"</td>";
                             tr += "<td>"+this.jobEmpName+"</td>";
                             tr += "<td>"+this.proTeamName+"</td>";
                             tr += "<td>"+this.proEmpName+"</td>";
                             tr += "<td>"+this.mrStepNm+"</td>";
                             tr += "<td>"+this.capexNo+"</td>";
                             tr += "<td style='display:none'>"+this.plant+"</td>";
                             tr += "<tr>";
                             $(".searchList").append(tr);
                          });
                   }
               }
        });

    });

});

var originalFileLength;
var clickLink;

$(document).on("mouseover",".mrLink",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".mrLink",function() {
   $(this).css("background","#ffffff");
}).on("click",".mrLink",function() {
	clickLink = this;
	$(".removeDownFile").hide();
	var infoTr = $("#selectForm").find("table").children("tbody");


	infoTr.children("tr:eq(0)").children("th:eq(0)").html($(this).children("td:eq(1)").html());
    infoTr.children("tr:eq(1)").children("th:eq(0)").html($(this).children("td:eq(1)").html());

	infoTr.children("tr:eq(0)").children("td:last").html($(this).children("td:eq(2)").html());
    infoTr.children("tr:eq(1)").children("td:last").html($(this).children("td:eq(3)").html());

	var target="";
	var tr = "";
	var porcCount = 14;
	var reqCount = 1;
	var procRow = 0;
	$("#selectForm").find("input").each(function () {
        $(this).hide();
		if($(this).attr("type")=="text") {
			$(this).val("");
		}
	});

    $("#appLine").children("tbody").children("tr:gt(0)").each(function () {
        $(this).remove();
    });

    $("#selectForm").find("img").each(function () {
        $(this).hide();
    });

    $("#selectForm").find("span").each(function () {
        $(this).html(" ");
    });

    $(".mrReqNo").val($(this).children("td:eq(0)").html());
    $("#mrStepCd").val($(this).children("td:eq(1)").html());
    $("#mrNo").val($(this).children("td:eq(2)").html());
    $(".mrReqTitle").val($(this).children("td:eq(3)").html());
    $("#plantNo").val($(this).children("td:eq(12)").html());
    
    $.ajax({
        type:"GET",
        url:"mngrFilesAjax.do?mrReqNo="+$(this).children("td:eq(0)").html(),
        dataType:"JSON",
        success : function(data) {
        	// tr 아래를 empty 시켜준다.
			$(".table_default_margin").empty();
			$(".table_default_margin").append("<tr style='display:none'><td></td></tr>");
			
			if(data.stepList!=null){
            	$.each(data.stepList, function(index, step){
                	$("."+step.mrStepCd).show();
            	})
            }
			
            if(data.fileList!=null) {
          	  	  originalFileLength = data.fileList.length;
                  $.each(data.fileList, function(index) {
                	  //console.log(this);
                	  var tr = "";
                	    tr += "<tr>";
                	    tr += "<td>";
                	    
                	    tr += "<input type='text' class='i_input' value='"+this.fileCdNm+"' readonly>";
                	    tr += "<input type='text' class='i_input_300 multipartFile mngrFileNm fileNm' value='"+this.fileNm+"' readonly>";
                	    tr += "<img src='images/btn_inFile.png' class='cursor fileDown'/>";
                	    tr += "<img class='cursor removeDownFile' src='images/icon_minus.png' style='display:none'/>";
//                	    tr += "<input type='hidden' value='${mrAtchFiles.fileCd}'/>";
                	    tr += "<input type='hidden' class='delFlag' name='mrAtchFiles["+index+"].deleted' value='false'/>";
                	    tr += "<input type='hidden' class='insFlag' name='mrAtchFiles["+index+"].inserted' value='false'/>";
                	    tr += "<input type='hidden' name='mrAtchFiles["+index+"].mrAtchFileNo' value='"+this.mrAtchFileNo+"'/>";
                	    tr += "<input type='hidden' name='mrAtchFiles["+index+"].fileStepCd' value='"+this.fileStepCd+"'/>";
                	    if(this.drawMngNo == null) this.drawMngNo = "";
                	    tr += "<input type='hidden' class='drawMngNo' name='mrAtchFiles["+index+"].drawMngNo' value='"+this.drawMngNo+"'/>";
                	    tr += "</td>";
                	    tr += "</tr>";
                	    $("."+this.fileStepCd+"FileList").append(tr);
                    	$(".removeDownFile").show();
                  });
            }
        }
    });
});

$(".addFile").click(function () {
	var insertClass= $(this).attr("data-role");
	var mrStepCd= $(this).attr("data-step");
    var fileCnt = originalFileLength+1;
    originalFileLength+=1;
    var fileTemp = $("#fileTemp").children("tbody").children("tr");
    var fileTempTd = fileTemp.children("td");
    var fileStepCd;
    
    fileTempTd.children(".multipartFile").attr("name","mrAtchFiles["+fileCnt+"].fileNm");
    fileTempTd.children(".fileCd").attr("name","mrAtchFiles["+fileCnt+"].fileCd");
    fileTempTd.children(".insFlag").attr("name","mrAtchFiles["+fileCnt+"].inserted");
    fileTempTd.children(".drawMngNo").attr("name","mrAtchFiles["+fileCnt+"].drawMngNo");
    fileTempTd.children(".fileStepCd").attr("name","mrAtchFiles["+fileCnt+"].fileStepCd");
    fileTempTd.children(".mrStepCd").attr("name","mrAtchFiles["+fileCnt+"].mrStepCd");
    
    fileTemp.clone().insertAfter($("."+insertClass+"FileList").children("tbody").children("tr:last"));

    var insertTd = $("."+insertClass+"FileList").children("tbody").children("tr:last").children("td");
    insertTd.children(".multipartFile").attr("id", "multipartFile");
    insertTd.children(".multipartFile").addClass("mngrFileNm");
    fileTempTd.children(".insFlag").val('true');
    insertTd.children(".fileForm").attr("data-role", insertClass);
    insertTd.children(".fileStepCd").val(insertClass);
    insertTd.children(".mrStepCd").val(mrStepCd);
});



$("#save").click(function(){
    var edimsAddr = url.substring(7, url.indexOf("/mr/"));
	if($(".mrReqNo").val()==0) {
		alert("첨부파일을 변경하려고 하는 MR을 먼저 선택하세요.");
	}else if("첨부파일 목록을 변경하시겠습니까?") {
        $('#pageLoadingWrap').show();
		//$("#selectForm").attr("action", "mngrFilesSave.do").submit();
		
    	if(!$.fileCdValidation()) {
    		alert("파일구분을 선택하세요.");
    		$('#pageLoadingWrap').hide();
    		return false;
    	}

        if(!$.fileNameValidation()) {
            alert("파일을 선택하세요.");
            $('#pageLoadingWrap').hide();
            return false;
        }
        
        var totInsCnt = 0;
        var totDelCnt = 0;
        var insCnt = 0;
        $(document).find(".mngrFileNm").each(function(index){
        	var td = $(this).parent("td");
			if($(this).val()!=""){
				if(td.children(".insFlag").val()=="true") {
					totInsCnt++;
				} else if(td.children(".delFlag").val()=="true"){
					totDelCnt++;
    			}
			}
        });
        if(totInsCnt > 0){
        	$(document).find(".mngrFileNm").each(function(index){
        		var td = $(this).parent("td");
    			if($(this).val()!=""){
    				if(td.children(".insFlag").val()=="true") {
    					
        				var data = new FormData();
             			data.append("localAddr", edimsAddr);
             			data.append("filePath", $(td).find(".fileForm")[0].files[0]);
             			data.append("empNo", "${emp.empNo}");
             			data.append("plantNo", $("#plantNo").val());
             			data.append("mrNo", $("#mrNo").val());		
             			data.append("mrNo2", "");
             			
             			$.ajax({
             		    	type:"POST",
             		        url:"fileUploadAjax.do",
             		        data:data,
             		        dataType:"JSON",
      	       		      	enctype: 'multipart/form-data',
      	   		          	processData : false,
      	   		            contentType : false,
      	   		            async: false,
             		        success : function(data) {
             		        	if(data.doc_seq){
          		        			td.children(".drawMngNo").val(data.doc_seq);
              		        	} else{
              		            	alert("첨부파일 업로드에 실패하였습니다.");
          		        			td.parent("tr").remove();
    							}
             		        },
             		        error : function(request) {
          		            	alert("첨부파일 업로드에 실패하였습니다.");
        		            	td.parent("tr").remove();
             		        },
             		        complete : function(){
            					insCnt++;
             		        	if(totInsCnt == insCnt){
             		        		dbFileUploadAjax();
             		        	}
             		        }
    					}); 
    				}
        		}
        	});
        } else if(totDelCnt > 0){
        	dbFileUploadAjax();
        }
        
    }
});

function dbFileUploadAjax(){
	var formData = $("#selectForm").serialize();
	$.ajax({
        type:"POST",
        url:"mngrFilesSave.do",
        data:formData,
        dataType:"JSON",
        success : function(data) {
           if(data.result!=null) {
				alert(data.result);
				$(clickLink).click();
           }
        },
        error : function(request) {
			alert("저장에 실패하였습니다.");
        }
    });
}

$(document).on("mouseover",".goMr",function() {
    $(this).css("background","#fcf8e3");
}).on("mouseleave",".goMr",function() {
   $(this).css("background","#ffffff");
}).on("click",".goMr",function() {
    var url ="";
    if($(".mrReqNo").val()==0) {
		return;
	}

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
</script>