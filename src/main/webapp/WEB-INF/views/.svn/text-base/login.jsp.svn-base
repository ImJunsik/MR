<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<div id="mr_login">
    <div class="content">
        <div class="content-area">
            <form method="post">
<%--               <input type="text" name="next" value="<c:out value="${next}"/>">
              <input type="text" name="mrReqNo" value="<c:out value="${mrReqNo}"/>"> --%>
                  <table class="table_login" style="width:198px">
                  <tr>
                    <td><img src="images/loginBox_id.gif"></td>
                    <td colspan="2" ><input type="text" id="empNo" class="input_log"  tabindex=1 name="empNo" alt="사원번호" value="<c:out value="${empNo}"/>" placeholder="사원번호"></td>
                  </tr>
                  <tr>
                    <td><img src="images/loginBox_pw.gif"></td>
                    <td colspan="2" ><input type="password" id="passwd" class="input_log"  tabindex=2 name="passwd" alt="비밀번호" placeholder="비밀번호"></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td style="width:50%;padding-top:5px;">
                        <div class="form_group">
                        <input type='checkbox' class='i_check check' />아이디저장
                        </div>
                    </td>
                    <td style="wight:50%;text-align:right"><input type="image" tabindex=3 alt="로그인" src="images/btn_login.gif"></td>
                  </tr>
                </table>
            </form>
        </div>
    </div>
</div>





<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/jquery/jquery.cookie.js"></script>
<script>
$(document).ready(function() {
    var failMessage = "${failMessage}";
    if (failMessage != "") {
        alert(failMessage);
    }



    if($.cookie('mrEmpNoSave')){
    	$(".check").attr("checked",true);
    	if($("#empNo").val()==""){
            $("#empNo").val($.cookie('mrEmpNo'));
        }
    }




    $("#login").click(function(){
        if($("#empNo").val() == null || $("#empNo").val() == "") {

            alert("사원번호를 입력하세요.");
            $("#empNo").focus();
            return;

        } else  if($("#passwd").val() == null || $("#passwd").val() == "") {

            alert("패스워드를 입력하세요.");
            $("#passwd").focus();
            return;
        }

        if($(".check").is(":checked")) {
        	$.cookie('mrEmpNo', $("#empNo").val(), {
        	    expires: 10,
        	    path: '/',
        	    raw: true
        	});
        	$.cookie('mrEmpNoSave', $(".check").is(":checked"),{
                expires: 10,
                path: '/',
                raw: true
            });
        }


        $("form").attr("action", "login.do").submit();
    });

});
</script>