<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        a {
            margin-bottom: 10px;
        }

        th, td {
            padding: 10px;
            border: 1px solid black;
        }
    </style>
</head>
<body>

<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<a href="meals?action=add">Add Meal</a>
<br><br>

<table>
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
        <td>&nbsp</td>
        <td>&nbsp</td>
    </tr>
    <c:forEach items="${requestScope.mealsTo}" var="meal">

        <c:set var="col" value="${meal.excess ? 'red': 'green'}"/>

        <tr style="color:${col}">
            <td>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                               type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
            </td>
            <td>
                    ${meal.description}
            </td>
            <td>
                    ${meal.calories}
            </td>
            <td>
                <a href="meals?action=update&id=<c:out value="${meal.id}"/>">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
