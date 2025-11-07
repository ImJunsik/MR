<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
});
</script>
<div class="row">
  <div class="col-md-12">
    <div class="panel panel-default">
      <div class="panel-body">
        <table class="table table-bordered">
          <tr>
            <th class="active">스케줄러명</th>
            <td>${jobAttributes.jobName}</td>
            <th class="active">실행상태</th>
            <td>${jobAttributes.state.label}</td>
          </tr>
          <tr>
            <th class="active">실행시작일시</th>
            <td><fmt:formatDate value="${jobAttributes.executionStartTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <th class="active">실행종료일시</th>
            <td><fmt:formatDate value="${jobAttributes.executionEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
          <tr>
            <th class="active">다음실행일시</th>
            <td><fmt:formatDate value="${jobAttributes.nextFireTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <th class="active">파라미터</th>
            <td>${jobAttributes.parameter}</td>
          </tr>
          <tr>
            <th class="active">에러메시지</th>
            <td colspan="3"><textarea class="form-control" rows="3">${jobAttributes.errorMessage}</textarea></td>
          </tr>
          <tr>
            <th class="active">Stack Trace</th>
            <td colspan="3"><textarea class="form-control" rows="6">${jobAttributes.stackTrace}</textarea></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>