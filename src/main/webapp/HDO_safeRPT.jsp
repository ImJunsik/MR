<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URLEncoder" %>

<%
request.setCharacterEncoding("EUC-KR");
response.setCharacterEncoding("EUC-KR");
response.setContentType("text/html;charset=EUC-KR");

// 상대 경로 설정
String relativePath = "/images/HDO_safeRPT.docx";
String filename = application.getRealPath(relativePath);

File file = new File(filename);
System.out.println("Resolved Path: " + filename);

if (file.exists() && file.isFile()) {
    response.setContentType("application/x-msdownload");

    String encodedFileName = URLEncoder.encode(file.getName(), "EUC-KR");
    response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ";");

    BufferedInputStream input = null;
    BufferedOutputStream output = null;

    try {
        input = new BufferedInputStream(new FileInputStream(file));
        output = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        output.flush();
        out.clear();
        out = pageContext.pushBody();

    } catch (Exception e) {
        System.out.println("에러메세지: " + e.getMessage());
    } finally {
        if (output != null) try { output.close(); } catch (IOException e) {}
        if (input != null) try { input.close(); } catch (IOException e) {}
    }
} else {
%>
<script>
    alert("파일이 존재하지 않습니다.");
    self.close();
</script>
<%
}
%>