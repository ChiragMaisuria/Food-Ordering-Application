<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<head>
<title>Eatery Login</title>
<link rel="stylesheet" href="css//viewAllRestaurantsLoggedIn.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=KoHo">
<script src="js//restaurantLoggedIn.js"></script>
</head>

<body class="viewAllResBody">
	<div>
		<img src="images//registerLoginPage//loginCover.jpg" height="150">
		<p class="viewAllResMainHeading">Northeastern Eatery</p>
		<hr>
		<button class="viewUserName" onclick="redirectTo('user_profile')">Hi,
			${sessionScope.userInformation.fname}
			${sessionScope.userInformation.lname}</button>
		<button style="margin-left: 45%;" class="viewAllButton"
			onclick="redirectTo('all_orders')">Orders</button>
		<button class="viewAllButton" onclick="redirectTo('cart')">Cart</button>
		<button class="viewAllButton" onclick="redirectTo('logOut')">Log Out</button>
		<hr>

		<c:if test="${fn:length(requestScope.allRestaurantDetails) > 0}">

			<c:forEach items="${requestScope.allRestaurantDetails}"
				var="restaurant">
				<div class="restaurantCardsContainer">
					<div class="restaurantCards">
						<img src="images//Customer//restaurantCover.png" height="100"><br>
						<div class="restaurantCardsElements">
							<div class="restaurantCardsText">
								${restaurant.restaurantName}<br>
								${restaurant.restaurantCuisine}
							</div>
							<button style="background: white; border: 0px; cursor: pointer;"
								onclick="redirectToMenu(${restaurant.restaurantId})">
								<img src="images//Customer//menuIcon.png" height="40">
							</button>

						</div>
					</div>
			</c:forEach>

			<!-- 				<div class="restaurantCards"> -->
			<!-- 					<img src="images//Customer//restaurantCover.png" height="100"><br> -->
			<!-- 					<div class="restaurantCardsElements"> -->
			<!-- 						<div class="restaurantCardsText"> -->
			<!-- 							restaurant_name<br> cuisine -->
			<!-- 						</div> -->
			<!-- 						<button style="background: white; border: 0px; cursor: pointer;" -->
			<!-- 							onclick="redirectToRestaurant('menu', 123)"> -->
			<!-- 							<img src="images//Customer//menuIcon.png" height="40"> -->
			<!-- 						</button> -->

			<!-- 					</div> -->
			<!-- 				</div> -->
		</c:if>

		<c:if test="${fn:length(requestScope.allRestaurantDetails) <= 0}">
			<p>Sorry, there are no restaurants available currently.</p>
		</c:if>

	</div>

	</div>
</body>

</html>