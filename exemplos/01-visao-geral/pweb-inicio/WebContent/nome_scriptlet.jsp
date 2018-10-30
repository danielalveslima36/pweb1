<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página exemplo JSP</title>
</head>
<body>
<%
for(int i=0; i<10; i++) {
	out.println("<h2>OLHA ISSO!</h2>");
}
%>
<h1>Olá, <%=request.getAttribute("nome")%>. Bem-vindo!</h1>
</body>
</html>