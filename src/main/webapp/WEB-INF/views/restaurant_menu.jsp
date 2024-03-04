<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<head>
<title>Eatery Cart Items</title>
<link rel="stylesheet" href="css//restaurantMenu.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=KoHo">
<script src="js//restaurantMenu.js"></script>

</head>

<body class="cartBody">
	<div>
		<img src="images//registerLoginPage//loginCover.jpg" height="40"
			style="display: inline-block;">
		<p class="cartMainHeading">Northeastern Eatery</p>
		<hr>
		<div style="display: inline-block; position: absolute; left: 30px;">
			<img src="images//Restaurant//editMenuHome.png" height="30">
		</div>
		<p class="cartHeading">${requestScope.restaurantDetails.restaurantName }</p>
		<p class="cartSubHeading">MENU</p>
		<div class="cartViewSide">
			<img src="images//Customer//viewCart.png" height="40px">
			<div class="cartViewSideText">
				Cart<br>
				<p id="cartItemsCount">0</p>
			</div>
		</div>
		<hr style="margin-top: 20px;">
		<table>
			<tr>
				<th></th>
				<th>Cart Items</th>
				<th>Items Price</th>
				<th></th>
			</tr>
			<c:forEach items="${requestScope.restaurantMenu}" var="menuItems">
				<tr>
					<td><img src="images//Customer//cartItems.png" height="25px"></td>
					<td class="cartItems">${menuItems.restaurantMenuItemName}</td>
					<td class="cartItems">${menuItems.restaurantMenuItemPrice}</td>
					<td>
					<c:if test="${requestScope.loggedIn == true}">
					<button class="addButton" onclick="addToCart(${requestScope.restaurantDetails.restaurantId}, ${menuItems.restaurantMenuItemId})">Add
					</button>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>${fn:length(requestScope.restaurantMenu)}</td>
				<td class="totalCart">Total</td>
				<td class="totalCart" id="cartTotal">$18.00</td>
				<td></td>
			</tr>
		</table>
		<hr>
		<c:if test="${sessionScope.loggedIn}">
		<form action="cart">
<%-- 			<input type="hidden" name="restaurantId" value="${requestScope.restaurantDetails.restaurantId}"> --%>
			<input class="cartButton" type="submit" value="Proceed To Checkout">
		</form>
		</c:if>
		<c:if test="${!sessionScope.loggedIn}">
		You must Register / Log In to add Items to cart.
		</c:if>
		<hr>
	</div>
</body>

</html>