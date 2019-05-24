<%--
  Created by IntelliJ IDEA.
  User: danielalves
  Date: 23/05/19
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista Usuario</title>
</head>
<body>
    <h2>Dados do Usuário:</h2>
    <h5>Nome: <c:out value="${usuario.primeiroNome}"></c:out></h5>
    <h5>Sobrenome: <c:out value="${usuario.ultimoNome}"></c:out></h5>
    <h5>Data de nascimento: <c:out value="${usuario.dataNascimento}"></c:out></h5>
    <h5>Gênero: <c:out value="${usuario.genero}"></c:out></h5>
    <h5>Telefone: <c:out value="${usuario.telefone}"></c:out></h5>
    <h5>Email: <c:out value="${usuario.email}"></c:out></h5>
    <h5>Senha: <c:out value="${usuario.senha}"></c:out></h5>
    <h5>Cidade: <c:out value="${usuario.cidade}"></c:out></h5>
    <h5>País: <c:out value="${usuario.pais}"></c:out></h5>


</body>
</html>
