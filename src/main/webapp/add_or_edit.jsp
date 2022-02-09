<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
    <link href="meals.jsp">

</head>
<body>
<c:set var="meal" value='${requestScope["meal"]}'/>
<h3><a href="index.html">Home</a></h3>
<br>
<hr>
<br><br>
<h1>Edit meal</h1>
<br>

<form method="post" action="meals">
    <input type="text" readonly="readonly" name="id" hidden="hidden"/>
    <div>
        DateTime: <input type="datetime-local" name="date" value="${meal.dateTime}">
    </div>
    <br>
    <div>
        Description:<input type="text" name="description" value="${meal.description}">
    </div>
    <br>
    <div>
        Calories:<input type="text" name="calories" value="${meal.calories}">
    </div>

    <br>
    <br>

    <input type="submit" value="save">
    <a href="meals"><input type="button" value="cancel"></a>
</form>


</body>
</html>
