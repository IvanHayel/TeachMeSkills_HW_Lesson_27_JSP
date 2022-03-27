<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <jsp:include page="basic-head.jsp"/>
</head>
<body>
<jsp:include page="basic-header.jsp"/>
<%
    User currentUser = (User) session.getAttribute("user");
%>
<div class="container position-absolute top-50 start-50 translate-middle">
    <p class="text-center fs-1 fw-bold">
        <%= currentUser == null ? "Welcome " : currentUser.getFullName() + " welcome "%>
        to JavaServer Pages!
    </p>
</div>
<jsp:include page="basic-footer.jsp"/>
</body>
</html>