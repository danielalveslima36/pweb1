<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alunos</title>
</head>
<body>
	<table>
		<tr>
			<th>Matrícula</th>
			<th>Nome</th>
			<th>Idade</th>
		</tr>
		<c:forEach var="aluno" items="${alunos}">
			<tr>
				<td>${aluno.matricula}</td>
				<td>${aluno.nome}</td>
				<td>${aluno.idade}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>