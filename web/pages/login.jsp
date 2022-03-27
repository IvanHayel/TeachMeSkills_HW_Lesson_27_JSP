<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
    <jsp:include page="basic-head.jsp"/>
</head>
<body>
<jsp:include page="basic-header.jsp"/>
<form class="position-absolute top-50 start-50 translate-middle" method="post">
    <h1 class="h3 fw-normal">Please sign in</h1>
    <div class="form-floating mt-3">
        <input type="text" class="form-control" name="login" id="floatingLogin" placeholder="Login" required>
        <label for="floatingLogin">Login</label>
    </div>
    <div class="form-floating mt-3">
        <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="Password"
               required>
        <label for="floatingPassword">Password</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary mt-5" type="submit">Sign in</button>
</form>
<jsp:include page="basic-footer.jsp"/>
</body>
</html>
