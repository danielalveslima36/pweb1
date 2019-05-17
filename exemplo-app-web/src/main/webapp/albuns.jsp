<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h1>Álbum de fotos</h1>
<table>
<thead>
	<th>Título</th>
	<th>Autor</th>
	<th>Publicado</th>
	<th>Arquivos</th>
</thead>
<tbody>
<c:forEach items="${albuns}" var="album">
	<tr>
		<td>${album.titulo}</td>
		<td>${album.autor}</td>
		<td>${album.publicado}</td>
		<td>
			<ul>
			<c:forEach items="${album.arquivos}" var="arquivo">
				<li>${arquivo}</li>
			</c:forEach>
			</ul>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
