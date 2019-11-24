<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Spring MVC Form Handling</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/mycss.css">
</head>
<body>

<form:form action="viewproduct.do" method="POST"  modelAttribute="registrationBean">
 <table>
 <tr> <td>Name</td> <td><form:input type="text" path="name" name="name"/></td> </tr>
 <tr> <td>Age</td> <td><form:input type="number" path="age" name="age"/></td> </tr>
 <tr> <td>Journey Date</td> <td><form:input type="date" path="journeydate" name="journeydate"/></td> </tr>
 <tr> <td>Account Status</td>
 <td><form:select path="astatus" items="${astatuslist}" name="astatus"></form:select></td>
 </tr>
 <tr>
 <td><input type="submit" value="Register"/></td><td></td>
 </tr>
 </table>
 </form:form>

</body>
</html>