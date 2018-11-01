<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuários</title>
</head>
<body>

	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Login</th>
		</tr>
		<c:forEach var="usuario" items="${usuarios}">
		<tr>
			<td>${usuario.id}</td>
			<td>${usuario.nome}</td>
			<td>${usuario.login}</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>