<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>

<html>

<head>
    <title>
        Eatery Login
    </title>
    <link rel="stylesheet" href="css//userLogin.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=KoHo">
</head>

<body class="loginBody">
    <div>
        <img src="images//registerLoginPage//loginCover.jpg" height="300">
        <p class="loginMainHeading">Northeastern Eatery</p>
        <hr>
        <p class="loginHeading">Login to your Account</p>
        <form:form modelAttribute="loginAuthenticate" action="/food-ordering/login" method="POST">
            <label class="loginLabel">Email</label><br>
            <form:input class="loginInputBox" type="text" path="email" placeholder="userName@domain.com" />
            <br>
            <label class="loginLabel">Password</label><br>
            <form:input class="loginInputBox" type="password" path="password" placeholder="******" /><br><br>
            <input class="loginButton" type="submit" value="Login">
        </form:form>
    </div>
</body>

</html>