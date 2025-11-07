<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.File"%>
<%

 request.setCharacterEncoding("EUC-KR");
 response.setCharacterEncoding("EUC-KR");
 response.setContentType("text/html;charset=EUC-KR");

// String filename = request.getParameter("downfilename");
// String filename = "C:/Development/workspaces/mr/src/main/webapp/images/HDO_ivstRPT.xlsx";
 String filename = "D:/OpenMinds/EDIMS/mr/images/HDO_ivstRPT.xlsx";

 File file = new File(filename);

 System.out.println(filename);
 System.out.println(file.getAbsoluteFile());
 System.out.println(file.length());

 byte b[] = new byte[(int)file.length()];

 if(file.length() > 0 && file.isFile()){

  response.setContentType("application/x-msdownload");
  response.setHeader("Content-Disposition", "attachment;filename="+ file.getName() + ";");

  BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
  BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

  int read = 0;

  try{

   while((read = input.read(b)) != -1){
    output.write(b, 0, read);
   }

   output.close();
   input.close();
   out.clear();
   out = pageContext.pushBody();

  }catch(Exception e){
   System.out.println("에러메세지: " + e.getMessage());
  }finally{

   if(output != null){output.close();}
   if(input != null){input.close();}
  }
 }else{
%>

<script>
alert("파일이 존재 하지 않습니다.");
self.close();
</script>

<%
 }
%>