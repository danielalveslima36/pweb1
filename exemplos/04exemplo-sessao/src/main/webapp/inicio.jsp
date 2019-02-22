<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<h2>Olá, ${sessionScope.usuarioLogado} seja-bem vindo!</h2>
<a href='logout'>Fazer logout</a>
</body>
</html>
