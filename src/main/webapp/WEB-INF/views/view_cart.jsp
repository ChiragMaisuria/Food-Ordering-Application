<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<head>
<title>Eatery Cart Items</title>
<link rel="stylesheet" href="css//viewCart.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=KoHo">
<script src="js//viewCart.js"></script>

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
		<p class="cartHeading">${requestScope.restaurantDetails.restaurantName}</p>
		<p class="cartSubHeading">Cart Items</p>
		<hr style="margin-top: 20px;">

		<table>
			<tr>
				<th></th>
				<th>Item Name</th>
				<th>Price</th>
				<th></th>
			</tr>
			<c:forEach items="${requestScope.cartItems}" var="cartItem">
				<tr>
					<td><img src="images//Restaurant//itemsMarker.png" height="25"
						style="display: inline-block;" /></td>
					<td>${cartItem.orderItemName}</td>
					<td>$ ${cartItem.itemPrice }</td>
					<td><button
							onclick="deleteItemFromCart(${cartItem.id})">
							<img src="images//Restaurant//deleteItems.png" height="25"
								style="display: inline-block; cursor: pointer;" />
						</button></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td class="totalCart">Total</td>
				<td class="totalCart">$18.00</td>
				<td></td>
			</tr>
		</table>
		<form action="orderSummary">
			<hr>			
			<input type="hidden" name="restaurantId" value="${requestScope.restaurantDetails.restaurantId}">
			<input class="cartButton" type="submit" value="Proceed To Checkout">
		</form>
	</div>
</body>

</html>