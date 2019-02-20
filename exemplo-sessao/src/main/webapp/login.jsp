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
</style>
<body>
<form method="post">
	
	<fieldset>
		<legend>Credenciais</legend>
		
		<label>Login:</label>
		<input type="text" name="login" />
		
		<label>Senha:</label>
		<input type="password" name="senha" />
		
		<input type="submit" value="enviar" />
		
	</fieldset>

</form>

</body>
</html>