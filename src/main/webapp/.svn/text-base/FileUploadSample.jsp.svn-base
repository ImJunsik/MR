<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script src="js/lib/prototype.js"></script> -->
<script src="js/jquery/jquery-1.10.2.js"></script>
<script src="js/file_with_jQuery.js"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

var doc_seq1 ;
var doc_seq2 ;

alert('Start !') ;

Openfile.addFileForAttachment("<%=request.getLocalAddr()%>", "D:/00. S½Ā�mages/[00000000-000000-A3-0005]-08110-e18-107-005-a1.dwg", "admin", "HDO", "000000111", "123321" , function(doc_seq) {
    alert("doc_seq=" + doc_seq) ;
    doc_seq1 = doc_seq ;
    Openfile.requestRegister("wcjung94", "admin", "000000001", doc_seq1, "comment", function(wf_id) {
        alert("wf_id=" + wf_id) ;
        alert(Openfile.downloadFileByFC("<%=request.getLocalAddr()%>", "EDM_DOC",   doc_seq1, "D:/")) ;
    });
});


</script>
</body>
</html>