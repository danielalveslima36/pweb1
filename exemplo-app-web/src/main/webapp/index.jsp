
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <table>
        <td>
            <form action="Servlet1" method="post">
             <input type="submit" value="Servlet1">
            </form>
        </td>
        <td>
            <form action="Servlet2" method="post">
                <input type="submit" value="Servlet2">
            </form>
        </td>
    </table>

    <c:out value="${texto}"/>


</body>
</html>
