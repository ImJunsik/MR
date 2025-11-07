<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
    <script type="text/javascript">
        var login = "${login}";
        if (login=="false") {
        	 if(window.opener!=null) {
        		    opener.location.href="/mr/login.do";
                    self.close();
        	 } else {
        		 location.href="../login.do";
        	 }

        }
    </script>