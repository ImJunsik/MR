<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
    $("#popupExample").click(function (event) {
        window.open($(this).attr("href"), "_blank", "width=1024, height=768, resizable=yes, location=no");
        event.preventDefault();
    });
    
    $("#datetime").datetimepicker({
        controlType: "slider",
        timeFormat: "HH:mm"
    });

    // https://github.com/phstc/jquery-dateFormat
    // http://plugins.jquery.com/formatDateTime/
    // http://plugins.jquery.com/df-number-format/
    $("#searchButton").click(function () {
        $.getJSON("examples/employeeList.do", $("form:eq(1)").serialize(), function (data) {
            var params = new Array();
            var employeeListTemplate = $.format($.trim($("#employeeListTemplate").val()));
            $("#ajaxTable > tbody").empty();

            $.each(data, function (index, entry) {
                params.push(entry.employeeNo);
                params.push(entry.employeeName);
                params.push($.datepicker.formatDate('yy/mm/dd', new Date(entry.hireDate)));
                params.push(entry.salary);
                params.push(entry.departmentName);
                $(employeeListTemplate(params)).appendTo("#ajaxTable > tbody");
                params = [];
            });
        });
    });
});
</script>

<div class="list-group">
  <a href="employee/popup/employeeForm.do" id="popupExample" class="list-group-item">
    <h4 class="list-group-item-heading"><strong>팝업 Layout</strong></h4>
    <p class="list-group-item-text">Apache Tiles를 사용하여 Layout을 구성하며, 어떤 Layout을 사용할 것인가는 URL을 기준으로 한다.</p>
  </a>
  <a href="examples/error.do?type=1" class="list-group-item">
    <h4 class="list-group-item-heading">Error 처리</h4>
    <p class="list-group-item-text">모든 Servlet, JSP 에러는 error.jsp를 통해 처리</p>
  </a>
  <div class="list-group-item">
    <h4 class="list-group-item-heading">Datatime</h4>
    <p class="list-group-item-text">JQuery UI는 Date 형태의 달력만 지원하고 JQuery UI를 확장한 addon 모듈을 추가 <input type="text" id="datetime" class="form-control"></p>
  </div>
  <div class="list-group-item">
    <h4 class="list-group-item-heading">CamelCase</h4>
    <p class="list-group-item-text">
      Java Naming Convention 에는 method, variable 이름들은 CamelCase를 기본으로 한다.
      반면 데이터베이스는 전통적으로 각각의 단어 구분을 Underscore(_) 를 기본으로 하기 때문에
      MyBatis와 같은 곳에서 select 결과의 result type을 Map과 같은 클래스를 사용할 경우
      데이터베이스 테이블의 컬럼명이 Map의 Key 값으로 사용되므로 이 때 테이블의 컬럼명의 Underscore가 
      사용된다. Underscore를 사용해도 전혀 문제가 없지만 CameCase를 기본으로하는 Java의 변수명과 테이블
      select 결과인 Map의 Underscore 변수(?) 명이 혼재하여 코드의 가독성을 떨어뜨리며 전체적인 
      프로젝트의 Naming Rule 파괴현상이 올 수 있다.
      그러므로 컬럼명의 Underscore를 Java의 CamelCase에 대응되도록 자동 변환할 것을 권장한다.
    </p>
    <p><a href="http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html" target="_blank">Code Conventions for the Java Programming Language</a></p>
    <p>
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
          <c:forEach var="employee" items="${employeeList}" end="2">
            <tr>
              <td class="text-center"><input type="checkbox" name="employeeNo" value="${employee.empNo}"></td>
              <td>${employee.empNo}</td>
              <td>
                <a href="employee/employeeForm.do?employeeNo=${employee.empNo}">
                  <c:out value="${employee.empName}" escapeXml="true" />
                </a>
              </td>
              <td><fmt:formatDate value="${employee.hireDate}" pattern="yyyy/MM/dd" /></td>
              <td><fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="￦"/></td>
              <td>${employee.deptName}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </p>
  </div>
  <div class="list-group-item">
    <h4 class="list-group-item-heading">AJAX</h4>
    <p class="list-group-item-text">jQuery Ajax 사용방법과 JSON 데이터의 Spring MVC 처리 방법을 보여준다.</p>
    <p>
      <form:form modelAttribute="employeeSearchCondition" method="get" class="form-inline">
        <div class="row">
          <div class="col-md-8">
            <div class="form-group">
              <form:select path="departmentNo" class="form-control">
                <form:option value="0" label="---- 선택 ----" />
                <form:options items="${departmentList}" itemValue="departmentNo" itemLabel="departmentName" />
              </form:select>
            </div>
            <div class="form-group">
              <form:input path="employeeName" class="form-control" placeholder="사원명" />
            </div>
            <button type="button" id="searchButton" class="btn btn-info">검색</button>
          </div>
        </div>
      </form:form>
    </p>
    <p>
      <table id="ajaxTable" class="table table-bordered table-striped">
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
        </tbody>
      </table>
    </p>
  </div>
  <div class="list-group-item">
    <h4 class="list-group-item-heading">Mail</h4>
    <p class="list-group-item-text">SMTP 발송</p>
    <p>
      <form method="post" action="examples/mailSend.do">
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="to">수신자</label>
              <input type="text" name="to" id="to" class="form-control" placeholder="Enter email">
            </div>
            <div class="form-group">
              <label for="subject">제목</label>
              <input type="text" name="subject" id="subject" class="form-control" placeholder="Enter subject">
            </div>
            <div class="form-group">
              <label for="contents">내용</label>
              <textarea type="text" name="contents" id="contents" rows="5" class="form-control" placeholder="Enter contents"></textarea>
            </div>
            <button type="submit" class="btn btn-info">메일 보내기 <span class="glyphicon glyphicon-send"></span></button>
          </div>
        </div>
      </form>
    </p>
  </div>

</div>

<textarea id="employeeListTemplate" style="display: none">
  <tr>
    <td class="text-center"><input type="checkbox" name="employeeNo" value="{0}"></td>
    <td>{0}</td>
    <td><a href="employee/employeeForm.do?employeeNo={0}">{1}</a></td>
    <td>{2}</td>
    <td>{3}</td>
    <td>{4}</td>
  </tr>
</textarea>