<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticação</title>
</head>
<style>
body {
font-family: Arial, sans-serif
}
fieldset {
	width: 100%;
	background: #d1d1d1;
	color: #080808;
	padding: 10px;
}
label {
float: left;
width: 20%;
text-align: right;
padding: 5px;
}
input {
width: 70%;
padding: 5px;
float: left;
}
button{
margin-left: 22%;
width: 20%;
padding: 5px;
} 
#erro{
	padding: 5px;
	color: #f00;
	background: #d1d1d1;
	border: 1px solid #c0c0c0;
}
</style>
<body>
<form method="post" action="login">

	<c:if test="${sessionScope.msgerro != null}">
		<div id="erro">
			${sessionScope.msgerro}
		</div>
	</c:if>
	
	<fieldset>
		<legend>Credenciais</legend>
		
		<label>Login:</label>
		<input type="text" name="login" />
		
		<label>Senha:</label>
		<input type="password" name="senha" />
		
		<button type="submit">Enviar</button>
		
	</fieldset>

</form>

</body>
</html>