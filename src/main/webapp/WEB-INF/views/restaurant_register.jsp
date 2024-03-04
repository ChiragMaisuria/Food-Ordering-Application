<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!doctype html>
<html>

<head>
    <title>
        Eatery Register
    </title>
    <link rel="stylesheet" href="..//css//userRegister.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=KoHo">

</head>

<body class="loginBody">
    <div>
        <img src="..//images//registerLoginPage//loginCover.jpg" height="50">
        <p class="loginMainHeading">Northeastern Eatery</p>
        <hr>
        <img src="..//images//registerLoginPage//restaurantRegister.png" height="30" >
        <p class="loginHeading">Register Your Restaurant</p>
        <form:form modelAttribute="restaurantInfo" action="register">
            <label class="loginLabel">Restaurant Name</label><br> 
            <form:input class="loginInputBox" type="text" path="restaurantName"  placeholder="Zaika"/>
            <br>
            <label class="loginLabel">Cuisine Type</label><br>
            <form:input class="loginInputBox" type="text" path="restaurantCuisine" placeholder="cuisine-type"/>
            <br>
            <label class="loginLabel">City</label><br>
            <form:input class="loginInputBox" type="text" path="restaurantAddressCity" placeholder="city"/>
            <br>
            <label class="loginLabel">Primary Email</label><br>
            <form:input class="loginInputBox" type="text" path="restaurantEmail" placeholder="zaika@restaurant.com"/>
            <br>
            <label class="loginLabel">Primary Phone</label><br>
            <form:input class="loginInputBox" type="text" path="restaurantPhone" placeholder="000-000-0000"/>
            <br>
            <label class="loginLabel">Password</label><br>
            <form:input class="loginInputBox" type="password" path="restaurantPassword" name="registerPassword" placeholder="******"/>
            <br>
            <label class="loginLabel">Confirm Password</label><br>
            <input class="loginInputBox" type="password" name="registerConPassword" placeholder="******"/>
            <br><br>
            <input class="registerButton" type="submit" value="Register">  
        </form:form>
    </div>
</body>

</html>