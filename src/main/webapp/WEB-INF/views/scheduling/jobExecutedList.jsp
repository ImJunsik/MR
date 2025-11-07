<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<style type="text/css">
td {font-size: 13px;}

.danger {background-color: rgb(242, 222, 222) !important}
</style>

<script type="text/javascript">
$(document).ready(function() {
    $("button[name='jobExecuteButton']").click(function (event) {
        var beanId = $(this).data("id");
        var jobName = $(this).data("name");
        $.get("job/jobExecute.do", {beanId: beanId}, function () {
            alert("수동으로 실행한 '" + jobName + "' 실행을 완료하였습니다.");
            location.reload();
        });
    });

    $("a[href$='jobAttributes.do']").click(function (event) {
        var param = $.param({id: $(this).data("id")});
        window.open($(this).attr("href") + "?" + param, "_blank", "width=800, height=530, resizable=yes, location=no");
        event.preventDefault();
    });

    $("#executionStartDate").datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3,
        onClose: function(selectedDate) {
          $("#executionEndDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#executionEndDate").datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3,
        onClose: function(selectedDate) {
          $("#executionStartDate").datepicker("option", "maxDate", selectedDate);
        }
    });
});
</script>
<form:form modelAttribute="jobSearchCondition" method="get" class="form-inline" data-role="search">
  <div class="row">
    <div class="col-md-3">
      <div class="form-group">
        <form:select path="state" class="form-control">
          <form:option value="0" label="---- 실행상태 ----" />
          <form:options items="${jobStateList}" itemValue="value" itemLabel="label" />
        </form:select>
      </div>
    </div>
    <div class="col-md-9">
      <div class="form-group">
        <form:input path="executionStartDate" class="form-control" placeholder="실행일" /> 
      </div>
      ~
      <div class="form-group">
        <form:input path="executionEndDate" class="form-control" placeholder="실행일" />
      </div>
      <div class="form-group">
        <form:input path="jobName" class="form-control" placeholder="스케줄러명" />
      </div>
      <button type="submit" id="searchButton" class="btn btn-info">검색</button>
    </div>
  </div>
</form:form>
<br>
<div class="row">
  <div class="col-md-12">
    <table class="table table-bordered table-striped">
      <thead>
        <tr>
          <th class="text-center">#</th>
          <th>실행일</th>
          <th>스케줄러명</th>
          <th>실행상태</th>
          <th>실행유형</th>
          <th>실행시작일시</th>
          <th>실행종료일시</th>
          <th>다음실행일시</th>
          <th>재실행</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="attributes" items="${jobAttributesList}" varStatus="loop">
          <tr>
            <td class="text-center">${jobSearchCondition.pagination.getPageNumbering(loop.index)}</td>
            <td><fmt:formatDate value="${attributes.executionStartTime}" pattern="yyyy-MM-dd" /></td>
            <td><a href="job/popup/jobAttributes.do" data-id="${attributes.id}">${attributes.jobName}</a></td>
            <td>${attributes.state.label}</td>
            <td>
              <c:choose>
                <c:when test="${attributes.nextFireTime == null}">
                  수동실행
                </c:when>
                <c:otherwise>
                  스케줄러
                </c:otherwise>
              </c:choose>
            </td>
            <td><fmt:formatDate value="${attributes.executionStartTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td><fmt:formatDate value="${attributes.executionEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td><fmt:formatDate value="${attributes.nextFireTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td class="text-center">
              <c:if test="${attributes.state.value == '1'}">
                <button type="button" name="jobExecuteButton" data-id="${attributes.programName}" data-name="${attributes.jobName}" class="btn btn-info btn-xs">
                  <span class="glyphicon glyphicon-play"></span>
                </button>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<div class="row">
  <div class="col-md-12 text-center">
      <ui:pagination />
  </div>
</div>