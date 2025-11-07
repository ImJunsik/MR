<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<%-- 
  EL & JSTL 간단히 참고
  http://slog2.egloos.com/3581446
 --%>

<script type="text/javascript">
$(document).ready(function() {
    $("#checkAll").click(function () {
        $(":checkbox[name='employeeNo']").prop("checked", $(this).prop("checked"));
    });
});
</script>
<%--
  본 프로젝트에서는 검색 조건을 HttpSession에 넣어 검색조건을 유지하는 것을 기본으로하고 있다.
  하지만 검색을 할 때, 검색을 수행하는 순간 검색조건(?) Pagination의 데이터가 
  특정 값으로 변경되어야 하는 데이터가 있다.
  예를 들어 페이징을 사용하고 있고 사용자가 현재 3번째 페이지에 있을 경우 검색 키워드를 넣고
  검색한 결과 총 5건로 로우를 결과를 얻었다고 가정을한다.
  5건의 검색 결과가 목록이 첫번째 페이지에 출력되어야 할 것인데
  HttpSession에 검색조건(Pagination 객체 포함)을 유지 시켜 놓았기 때문에 사용자가 검색하기 전
  머물러있던 세번째 페이지에 그대로 머물러 있어 실제 검색한 결과 데이터를 볼 수 없는 상황이
  발생될 것이다.
  
  이런 현상을 방지하기 위해 html user data를 사용했다.
  검색을 위한 form tag가 있다면 data-role="search" 형태의 값을주게 되면 강제적으로 Pagination의
  currentPageNo 값을 1로 설정하는 기능을 한다.

  defaultLayout.jsp 의 아래 스크립트 참조  
  $("form[role=search]").submit(function () {
      $(this).initPagination();
  });
--%>
<form:form modelAttribute="employeeSearchCondition" method="get" class="form-inline" data-role="search">
  <div class="row">
    <div class="col-md-8 col-md-offset-3">
      <div class="form-group">
        <form:select path="departmentNo" class="form-control">
          <form:option value="0" label="---- 선택 ----" />
          <form:options items="${departmentList}" itemValue="departmentNo" itemLabel="departmentName" />
        </form:select>
      </div>
      <div class="form-group">
        <form:input path="employeeName" class="form-control" placeholder="사원명" />
      </div>
      <button type="submit" id="searchButton" class="btn btn-info">검색</button>
    </div>
  </div>
</form:form>
<div class="paragraph"></div>
<form name="employeeListForm" method="post" action="employee/employeeDelete.do">
  <div class="row">
    <div class="col-md-12">
      <div class="pull-right">
        <a href="employee/employeeForm.do" class="btn btn-primary" role="button">등록</a>
        <button id="deleteButton" class="btn btn-warning">삭제</button>
      </div>
    </div>
  </div>
  <div class="paragraph"></div>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered table-striped">
        <thead>
          <tr>
            <th class="text-center"><input type="checkbox" id="checkAll"></th>
            <th>사원번호</th>
            <th>사원명</th>
            <th>입사일</th>
            <th>연봉</th>
            <th>부서</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="employee" items="${employeeList}">
            <tr>
              <td class="text-center"><input type="checkbox" name="employeeNo" value="${employee.employeeNo}"></td>
              <td>${employee.employeeNo}</td>
              <td>
                <a href="employee/employeeForm.do?employeeNo=${employee.employeeNo}">
                  <c:out value="${employee.employeeName}" escapeXml="true" />
                </a>
              </td>
              <td><fmt:formatDate value="${employee.hireDate}" pattern="yyyy/MM/dd" /></td>
              <td><fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="￦"/></td>
              <td>${employee.departmentName}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</form>
<div class="row">
  <div class="col-md-12 text-center">
      <ui:pagination />
  </div>
</div>