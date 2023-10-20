<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h1>Student List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Birthday</th>
        <th>Phone</th>
        <th>Action</th>
    </tr>

<%--    <jsp:useBean id="studentList" scope="request" type="java.util.List"/>--%>
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.birtday}"/></td>
            <td><c:out value="${student.phone}"/></td>
            <td>
                <a href="student?action=edit&id=${student.id}">Edit</a>
                <a href="student?action=delete&id=${student.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add Student</h2>
<form action="student" method="post">
    <input type="text" name="name" placeholder="Name"><br>
    <input type="date" name="birthday" placeholder="Birthday"><br>
    <input type="text" name="phone" placeholder="Phone"><br>
    <input type="submit" value="Add Student">
</form>

<h2>Edit Student</h2>
<form action="student" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${student.id}">
    <input type="text" name="name" value="${student.name}"><br>
    <input type="text" name="birthday" value="${student.birthday}"><br>
    <input type="text" name="phone" value="${student.phone}"><br>
    <input type="submit" value="Update Student">
</form>
</body>
</html>
