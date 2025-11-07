<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<script>
$(window).ready(function() {
    $.popupTitle("MR 사용자 매뉴얼");

    $("li").mouseover(function() {
        $(this).css("background","#fcf8e3");
    }).mouseleave(function() {
        $(this).css("background","#ffffff");
    }).click(function(){
        location.replace("#"+$(this).attr("data-role"));
    });

    var URL = window.opener.document.URL;
    var targetMenu = "";
    if(URL.indexOf("mrRqRegister.do")>-1) {

        //reqURL = "/mrRqRegister.do";
    	targetMenu = "help9";

    } else if(URL.indexOf("mrTech.do")>-1) {

        //reqURL = "/mrTech.do";
    	targetMenu = "help10";

    } else if(URL.indexOf("mrComplete.do")>-1) {

        //reqURL = "/mrComplete.do";
    	targetMenu = "help13";

    } else if(URL.indexOf("mrTechInvest.do")>-1) {

        //reqURL = "/mrTechInvest.do";
    	targetMenu = "help11";

    } else if(URL.indexOf("mrJobsReview.do")>-1) {

        //reqURL = "/mrJobsReview.do";
    	targetMenu = "help19";

    } else if(URL.indexOf("ivstCost")>-1) {

        //reqURL = "/ivstCostRegister.do";
    	targetMenu = "help22";

    } else if(URL.indexOf("safeCheckExe")>-1) {

        //reqURL = "/safeCheckExe.do";
    	targetMenu = "help24";

    } else if(URL.indexOf("safeCheckRegister")>-1) {

        //reqURL = "/safeCheckRegister.do";
    	targetMenu = "help24";

    } else if(URL.indexOf("compFileManage")>-1) {

        //reqURL = "/compFileManage.do";
    	targetMenu = "help25";

    } else if(URL.indexOf("compRptRegister")>-1) {

        //reqURL = "/compRptRegister.do";
    	targetMenu = "help26";

    } else if(URL.indexOf("mrRiskCheck")>-1) {

        //reqURL = "/mrRiskCheck.do";
    	targetMenu = "help14";

    } else {

        //reqURL = "/mrRqRegister.do";
    	targetMenu = "top";
    }

    location.replace("#"+targetMenu);


});
</script>
<div style="float:left; text-align:left">
    <ul>
        <li class="cursor" data-role="help3"><h5>1. MR프로세스</h5></li>
        <li class="cursor" data-role="help5"><h5>2. 로그인</h5></li>
        <li class="cursor" data-role="help6"><h5>3. 화면구성</h5></li>
        <li class="cursor" data-role="help7"><h5>4. 메인화면</h5></li>
        <li class="cursor" data-role="help8"><h5>5. 상세조회</h5></li>
        <li class="cursor" data-role="help9"><h5>6. MR요청서</h5></li>
        <li class="cursor" data-role="help10"><h5>7. 기술검토항목</h5></li>
        <li class="cursor" data-role="help11"><h5>8. 초기투자비산정(Project Engineer지정)</h5></li>
        <li class="cursor" data-role="help12"><h5>9. 초기투자비산정</h5></li>
        <li class="cursor" data-role="help13"><h5>10. 타당성검토확인</h5></li>
        <li class="cursor" data-role="help14"><h5>11. 위험성검토</h5></li>
        <li class="cursor" data-role="help15"><h5>12. 위험성검토 – Check List</h5></li>
        <li class="cursor" data-role="help16"><h5>13. 위험성검토 – Hazop Study</h5></li>
        <li class="cursor" data-role="help17"><h5>14. 위험성검토 – PORC 위원선정</h5></li>
        <li class="cursor" data-role="help18"><h5>15. 위험성검토 – PORC 작성</h5></li>
        <li class="cursor" data-role="help19"><h5>16. 직무검토</h5></li>
        <li class="cursor" data-role="help20"><h5>17. 직무검토 - 투자비재산정</h5></li>
        <li class="cursor" data-role="help21"><h5>18. 직무검토 – 직무검토자 변경</h5></li>
        <li class="cursor" data-role="help22"><h5>19. 투자지출품의</h5></li>
        <li class="cursor" data-role="help23"><h5>20. MR수행</h5></li>
        <li class="cursor" data-role="help24"><h5>21. 가동전안전점검</h5></li>
        <li class="cursor" data-role="help25"><h5>22. 자료등록</h5></li>
        <li class="cursor" data-role="help26"><h5>23. MR완료</h5></li>
        <li class="cursor" data-role="help27"><h5>24. 공통-사원조회</h5></li>
        <li class="cursor" data-role="help28"><h5>25. 공통-공정조회</h5></li>
        <li class="cursor" data-role="help29"><h5>26. 공통-설비조회</h5></li>
        <li class="cursor" data-role="help29"><h5>26. 공통-설비조회</h5></li>
        <li class="cursor" data-role="fileUpload"><h5>27. 공통-파일업로드 안될경우 해결방법</h5></li>
        <br>
        <br>
    </ul>
</div>
<BR>
<div>
<a name="help1"></a><img src="../help/help1.png"/>
<a name="help2"></a><img src="../help/help2.png"/>
<a name="help3"></a><img src="../help/help3.png"/>
<a name="help4"></a><img src="../help/help4.png"/>
<a name="help5"></a><img src="../help/help5.png"/>
<a name="help6"></a><img src="../help/help6.png"/>
<a name="help7"></a><img src="../help/help7.png"/>
<a name="help8"></a><img src="../help/help8.png"/>
<a name="help9"></a><img src="../help/help9.png"/>
<a name="help10"></a><img src="../help/help10.png"/>
<a name="help11"></a><img src="../help/help11.png"/>
<a name="help12"></a><img src="../help/help12.png"/>
<a name="help13"></a><img src="../help/help13.png"/>
<a name="help14"></a><img src="../help/help14.png"/>
<a name="help15"></a><img src="../help/help15.png"/>
<a name="help16"></a><img src="../help/help16.png"/>
<a name="help17"></a><img src="../help/help17.png"/>
<a name="help18"></a><img src="../help/help18.png"/>
<a name="help19"></a><img src="../help/help19.png"/>
<a name="help20"></a><img src="../help/help20.png"/>
<a name="help21"></a><img src="../help/help21.png"/>
<a name="help22"></a><img src="../help/help22.png"/>
<a name="help23"></a><img src="../help/help23.png"/>
<a name="help24"></a><img src="../help/help24.png"/>
<a name="help25"></a><img src="../help/help25.png"/>
<a name="help26"></a><img src="../help/help26.png"/>
<a name="help27"></a><img src="../help/help27.png"/>
<a name="help28"></a><img src="../help/help28.png"/>
<a name="help29"></a><img src="../help/help29.png"/>
<a name="fileUpload"></a><img src="../help/FileErrorGuide.png"/>
</div>


<div style="position:fixed;top:650px; left:1150px;"><a href="#top"><img src="../images/icon_top.gif"/></a></div>