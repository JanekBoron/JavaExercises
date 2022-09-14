<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Car Rental</title>
</head>
<body>
<h1><%= "Car Rental" %>
</h1>
<br/>
<h3>Enter your preffered car</h3>
<form action="ProcessInfo" method ="get">
    <label>Car:</label>
    <input type="text" name="car" id="car"><br>
    <input type="submit"value="Send">
</form>
</body>
</html>