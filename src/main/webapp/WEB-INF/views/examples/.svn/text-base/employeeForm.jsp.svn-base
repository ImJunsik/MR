<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<style type="text/css">
  .attach {
    padding-right: 10px;
  }
</style>

<script type="text/javascript">
$(document).ready(function() {
    $("#tabs").tabs();
    $("#hireDate").datepicker();

    var academicCareerTemplate = $.format($.trim($("#academicCareerTemplate").val()));
    var academicCareerRowCount = $("#tabs-1 table tr:gt(0)").length - 1;
    $("#addAcademicCareerButton").click(function () {
        var param = [++academicCareerRowCount, academicCareerRowCount + 1];
        $(academicCareerTemplate(param)).appendTo("#tabs-1 table");
    });

    $("#tabs-1 table").on("click", "#removeAcademicCareerButton", function () {
        $(this).parents("tr").remove();
    });

    var careerTemplate = $.format($.trim($("#careerTemplate").val()));
    var careerRowCount = $("#tabs-2 table tr:gt(0)").length - 1;
    $("#addCareerButton").click(function () {
        var param = [++careerRowCount, careerRowCount + 1];
        $(careerTemplate(param)).appendTo("#tabs-2 table");
    });

    $("#tabs-2 table").on("click", "#removeCareerButton", function () {
        $(this).parents("tr").remove();
    });

    var attachFileTemplate = $.format($.trim($("#attachFileTemplate").val()));
    var attachFileCount = $("#tabs-3 li").length - 1;
    $("#addAttachFileButton").click(function () {
        $(attachFileTemplate(++attachFileCount)).appendTo("#tabs-3 ul");
    });
    
    $("#tabs-3 ul").on("click", "#removeAttachFileButton", function () {
        var $li = $(this).parents("li");
        if ($li.find("input[type='file']").length > 0) {
            $li.remove();
        } else {
            $li.data("delete", true).css("display", "none");
        }
    });

    $(".attach").appendDocumentViewAnchor();
    
    // http://akddd.net/287
    $("#employee").validate({
        ignore: "ui-tabs-hide",
        rules: {
            employeeName: {
                required: true,
                maxlength: 10,
            },
            hireDate: {
                required: true,
                dateISO: true
            },
            salary: {
                required: true,
                number: true
            }
        },
        messages: {
            employeeName: {
                required: "사원명을 입력하세요."
            }
        },
        invalidHandler: function(event, validator) {
            if (validator.numberOfInvalids() > 0) {
                var tabId = $(validator.errorList[0].element).parents("div[id^='tabs-']").attr('id');
                $("a[href$='" + tabId + "']").trigger("click");
            }
        },
        submitHandler: function(form) {
            if ($("#photoFile\\.multipartFile").val() != "") {
                $("#photoFileCode").val("");
            }

            $("#tabs-3 li").each(function () {
                if (!$(this).data("delete")) {
                    $(this).children("input").val("");
                }
            });

            if ($("#employeeNo").val() > 0) {
                $(form).attr("action", "employee/employeeUpdate.do");
            }
            form.submit();
        }
    });

    $.validator.addClassRules({
        schoolName: {
            required: true
        },
        graduationYear: {
            required: true,
            number: true,
            length: 4
        },
        point: {
            required: function (element) {
                return $(element).val() == "" ? true : false;
            },
            number: true,
            min: 0
        },
        companyName: {
            required: true
        },
        retirementDate: {
            required: true,
            number: true,
            length: 6
        },
        chargeJob: {
            required: true
        }
    });
    
    $("#txButton").click(function () {
    	$("form").attr("action", "employee/employeeAdd.do").submit();
    });

/*
 http://stackoverflow.com/questions/17789372/jquery-validation-plugin-with-a-custom-attribute
 http://stackoverflow.com/questions/15482523/jquery-validate-adding-dynamic-rules-with-messages
*/
});
</script>
<form:form modelAttribute="employee" method="post" enctype="multipart/form-data" action="employee/employeeInsert.do" class="form-inline">
  <div class="row">
    <div class="col-md-12 text-right">
      <button type="submit" id="saveButton" class="btn btn-primary">저장</button>
      <button type="button" id="txButton" class="btn btn-primary">저장(<strong>User Tx</strong>)</button>
      <a href="employee/employeeDelete.do?employeeNo=${employee.employeeNo}" class="btn btn-warning" role="button">삭제</a>
      <a href="employee/employeeList.do" class="btn btn-default" role="button">
        목록으로.. <span class="glyphicon glyphicon-th-list"></span>
      </a>
    </div>
  </div>
  <div class="paragraph"></div>
  <div class="row">
   <h4><strong>기본정보</strong></h4>
   <hr>
    <div class="col-md-3">
      <div class="panel panel-default">
        <div class="panel-body text-center">
          <img src="attachFile/download.do?code=${employee.photoFileCode}" width="90%" height="130">
          <form:hidden path="photoFileCode" />
          <form:hidden path="photoFile.code" />
        </div>
        <form:input path="photoFile.multipartFile" type="file" style="width: 100%" />
      </div>
    </div>
    <div class="col-md-9">
      <div class="panel panel-default">
        <div class="panel-body">
          <table class="table table-bordered">
            <tr>
              <th class="active">사원번호</th>
              <td><form:input path="employeeNo" class="form-control" readOnly="true" /></td>
              <th class="active">이름</th>
              <td>
                <form:input path="employeeName" class="form-control" />
                <form:errors path="employeeName" htmlEscape="false" class="errors" />
              </td>
            </tr>
            <tr>
              <th class="active">입사일</th>
              <td>
                <form:input path="hireDate" class="form-control" readOnly="true" />
                <form:errors path="hireDate" htmlEscape="false" class="errors" />
              </td>
              <th class="active">급여</th>
              <td>
                <form:input path="salary" class="form-control" />
                <form:errors path="salary" htmlEscape="false" class="errors" />
              </td>
            </tr>
            <tr>
              <th class="active">결혼여부</th>
              <td>
                <form:checkbox path="married" />
              </td>
              <th class="active">부서</th>
              <td>
                <form:select path="departmentNo" items="${departmentList}" itemValue="departmentNo" itemLabel="departmentName" class="form-control" />
                <form:errors path="departmentNo" htmlEscape="false" class="errors" />
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
  <!-- http://tjvantoll.com/2013/02/17/using-jquery-ui-tabs-with-the-base-tag/ -->
  <div class="row">
    <h4><strong>기타정보</strong></h4>
    <hr>
    <div class="col-md-12">
      <div id="tabs">
        <ul>
          <c:if test="${requestScope['javax.servlet.forward.query_string'] != null}">
            <c:set var="questionMark" value="?" />
          </c:if>
          <li><a href="${requestScope['javax.servlet.forward.request_uri']}${questionMark}${requestScope['javax.servlet.forward.query_string']}#tabs-1">학력</a></li>
          <li><a href="${requestScope['javax.servlet.forward.request_uri']}${questionMark}${requestScope['javax.servlet.forward.query_string']}#tabs-2">경력</a></li>
          <li><a href="${requestScope['javax.servlet.forward.request_uri']}${questionMark}${requestScope['javax.servlet.forward.query_string']}#tabs-3">첨부</a></li>
        </ul>
        <div id="tabs-1">
          <div class="text-right">
            <button type="button" id="addAcademicCareerButton" class="btn btn-info btn-xs">
              추가 <span class="glyphicon glyphicon-plus"></span>
            </button>
          </div>
          <div class="paragraph"></div>
          <table class="table table-bordered table-striped">
            <tr>
              <th>#</th>
              <th>학교명</th>
              <th>졸업년도</th>
              <th>졸업유형</th>
              <th>평점</th>
              <th></th>
            </tr>
            <c:forEach items="${employee.academicCareers}" varStatus="loop">
              <tr>
                <td style="vertical-align: middle">${loop.count}</td>
                <td>
                    <form:input path="academicCareers[${loop.index}].schoolName" class="form-control schoolName" />
                    <form:errors path="academicCareers[${loop.index}].schoolName" htmlEscape="false" class="errors" />
                    <form:hidden path="academicCareers[${loop.index}].employeeNo" />
                </td>
                <td>
                  <form:input path="academicCareers[${loop.index}].graduationYear" class="form-control graduationYear" />
                  <form:errors path="academicCareers[${loop.index}].graduationYear" htmlEscape="false" class="errors" />
                </td>
                <td><form:select path="academicCareers[${loop.index}].graduationTypeCode" items="${graduationTypes}" class="form-control" /></td>
                <td><form:input path="academicCareers[${loop.index}].point" class="form-control point" /></td>
                <td style="vertical-align: middle"><button type="button" id="removeAcademicCareerButton" class="close" aria-hidden="true">&times;</button></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div id="tabs-2">
          <div class="text-right">
            <button type="button" id="addCareerButton" class="btn btn-info btn-xs">
              추가 <span class="glyphicon glyphicon-plus"></span>
            </button>
          </div>
          <div class="paragraph"></div>
          <table class="table table-bordered table-striped">
            <tr>
              <th>#</th>
              <th>직장명</th>
              <th>직책</th>
              <th>퇴사일</th>
              <th>담당업무</th>
              <th></th>
            </tr>
            <c:forEach items="${employee.careers}" varStatus="loop">
              <tr>
                <td style="vertical-align: middle">${loop.count}</td>
                <td>
                  <form:input path="careers[${loop.index}].companyName" class="form-control companyName" />
                  <form:hidden path="careers[${loop.index}].employeeNo" />
                </td>
                <td><form:select path="careers[${loop.index}].officialPositionCode" items="${officialPositions}" class="form-control" /></td>
                <td><form:input path="careers[${loop.index}].retirementDate" class="form-control retirementDate" /></td>
                <td><form:input path="careers[${loop.index}].chargeJob" class="form-control chargeJob" /></td>
                <td style="vertical-align: middle"><button type="button" id="removeCareerButton" class="close" aria-hidden="true">&times;</button></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div id="tabs-3">
          <div class="row">
            <div class="text-right">
              <button type="button" id="addAttachFileButton" class="btn btn-info btn-xs">
                추가 <span class="glyphicon glyphicon-plus"></span>
              </button>
            </div>
          </div>
          <div class="paragraph"></div>
          <ul class="list-group">
            <c:forEach var="attachFile" items="${employee.attachFiles}" varStatus="loop">
              <li class="list-group-item">
                <form:hidden path="attachFiles[${loop.index}].code" />
                <span class="attach" data-code="${attachFile.code}"><c:out value="${attachFile.name}" /></span>
                <a href="attachFile/download.do?code=${attachFile.code}">[다운로드]</a>
                <button type="button" id="removeAttachFileButton" class="close" aria-hidden="true">&times;</button>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div>
</form:form>  

<textarea id="academicCareerTemplate" style="display: none">
  <tr>
    <td style="vertical-align: middle">{1}</td>
    <td><input type="text" name="academicCareers[{0}].schoolName" class="form-control schoolName"></td>
    <td><input type="text" name="academicCareers[{0}].graduationYear" class="form-control graduationYear"></td>
    <td>
      <select name="academicCareers[{0}].graduationTypeCode" class="form-control">
        <c:forEach var="graduationType" items="${graduationTypes}">
          <option value="${graduationType.key}">${graduationType.value}</option>
        </c:forEach>
      </select>
    </td>
    <td><input type="text" name="academicCareers[{0}].point" value="0.0" class="form-control point"></td>
    <td style="vertical-align: middle"><button type="button" id="removeAcademicCareerButton" class="close" aria-hidden="true">&times;</button></td>
  </tr>
</textarea>

<textarea id="careerTemplate" style="display: none">
  <tr>
    <td style="vertical-align: middle">{1}</td>
    <td><input type="text" name="careers[{0}].companyName" class="form-control companyName" /></td>
    <td>
      <select name="careers[{0}].officialPositionCode" class="form-control">
        <c:forEach var="officialPosition" items="${officialPositions}">
          <option value="${officialPosition.key}">${officialPosition.value}</option>
        </c:forEach>
      </select>
    </td>
    <td><input type="text" name="careers[{0}].retirementDate" class="form-control retirementDate" /></td>
    <td><input type="text" name="careers[{0}].chargeJob" class="form-control chargeJob" /></td>
    <td style="vertical-align: middle"><button type="button" id="removeCareerButton" class="close" aria-hidden="true">&times;</button></td>
  </tr>
</textarea>

<textarea id="attachFileTemplate" style="display: none">
  <li class="list-group-item">
    <div class="row">
      <div class="col-md-6">
        <input type="file" name="attachFiles[{0}].multipartFile">
      </div>
      <div class="col-md-6">
        <button type="button" id="removeAttachFileButton" class="close" aria-hidden="true">&times;</button>
      </div>
    </div>
  </li>
</textarea>