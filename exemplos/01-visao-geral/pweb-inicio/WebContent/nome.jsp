<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo usando JSTL</title>
</head>
<body>
<c:forEach begin="1" end="5">
	<h2>Olha isso!</h2>
</c:forEach>

<h1>Olá ${nome}</h1>


</body>
</html>