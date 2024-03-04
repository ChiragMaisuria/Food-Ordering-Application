<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>
<html>

<head>
    <title>
        Eatery Register
    </title>
    <link rel="stylesheet" href="css//userRegister.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=KoHo">

</head>

<body class="loginBody">
    <div>
        <img src="images//registerLoginPage//loginCover.jpg" height="50">
        <p class="loginMainHeading">Northeastern Eatery</p>
        <hr>
        <img src="images//registerLoginPage//customerRegister.png" height="30">
        <p class="loginHeading">Register Yourself</p>
        <form:form action="/food-ordering/register" modelAttribute="registerCustomer" method="POST">
            <label class="loginLabel">First Name</label><br>
            <form:input class="loginInputBox" type="text" path="fname" placeholder="Jane" />
            <br>
            <label class="loginLabel">Last Name</label><br>
            <form:input class="loginInputBox" type="text" path="lname" placeholder="Doe" />
            <br>
            <label class="loginLabel">Email</label><br>
            <form:input class="loginInputBox" type="text" path="email" placeholder="example@gmail.com" />
            <br>
            <label class="loginLabel">Phone Number</label><br>
            <form:input class="loginInputBox" type="text" path="phoneNumber" placeholder="000 000 0000" />
            <br>
            <label class="loginLabel">Address City</label><br>
            <form:input class="loginInputBox" type="text" path="addressCity" placeholder="..." />
            <br>
            <label class="loginLabel">Password</label><br>
            <form:input class="loginInputBox" type="password" path="password" name="registerPassword" placeholder="******" />
            <br>
            <label class="loginLabel">Confirm Password</label><br>
            <input class="loginInputBox" type="password" name="registerConPassword" placeholder="******" />
            <br><br>
            <input class="registerButton" type="submit" value="Register">
        </form:form>
    </div>
</body>

</html>