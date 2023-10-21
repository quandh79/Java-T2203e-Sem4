<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<!-- Form tìm kiếm -->
<form action="student" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="keyword" placeholder="Search by name...">
    <input type="submit" value="Search">
</form>
<h1>Student List</h1>
<table >
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
            <td>
                <fmt:formatDate value="${student.birthday}" pattern="dd-MM-yyyy"/> <!-- Sử dụng fmt:formatDate để định dạng ngày tháng -->
            </td>
            <td><c:out value="${student.phone}"/></td>
            <td>
                <a href="student?action=edit&id=${student.id}">Edit</a>
                <a href="student?action=delete&id=${student.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<c:choose>
    <c:when test="${pageNumber > 1}">
        <a href="student?pageNumber=${pageNumber - 1}&pageSize=${pageSize}">Previous</a>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${studentList.size() == pageSize}">
        <a href="student?pageNumber=${pageNumber + 1}&pageSize=${pageSize}">Next</a>
    </c:when>
</c:choose>

<h2>Add Student</h2>
<form action="student" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="Name"><br>
    <input type="date" name="birthday" placeholder="Birthday"><br>
    <input type="text" name="phone" placeholder="Phone"><br>
    <input type="file" name="avatar" accept="image/*"><br>
    <input type="submit" value="Add Student">
</form>

<h2>Edit Student</h2>
<form action="student" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${student.id}">
    <input type="text" name="name" value="${student.name}"><br>
    <input type="date" name="birthday" value="<fmt:formatDate value='${student.birthday}' pattern='yyyy-MM-dd' />"><br>
    <input type="text" name="phone" value="${student.phone}"><br>
    <input type="file" name="avatar" accept="image/*"><br>
    <input type="submit" value="Update Student">
</form>
</body>
</html>
