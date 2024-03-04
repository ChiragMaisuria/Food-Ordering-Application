<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<head>
<title>Eatery Cart Items</title>
<link rel="stylesheet" href="css//orderSummary.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=KoHo">

</head>

<body class="orderSumBody">
	<div>
		<img src="images//registerLoginPage//loginCover.jpg" height="40"
			style="display: inline-block;">
		<p class="orderSumMainHeading">Northeastern Eatery</p>
		<hr>
		<div style="display: inline-block; position: absolute; left: 30px;">
			<img src="images//Restaurant//editMenuHome.png" height="30">
		</div>
		<p class="orderSumHeading">${requestScope.restaurantDetails.restaurantName}</p>
		<p class="orderSumSubHeading"></p>
		<div class="orderSumViewSide">ORDER SUMMARY</div>
		<hr style="margin-top: 20px;">
		<form action="placeOrder">
			<table>
				<tr>
					<th></th>
					<th>Cart Items</th>
					<th>Items Price</th>
				</tr>
				<c:forEach items="${requestScope.cartItems}" var="cartItem">
					<tr>
						<td><img src="images//Customer//cartItems.png" height="25px"></td>
						<td class="orderSumItems">${cartItem.orderItemName}</td>
						<td class="orderSumItems">$ ${cartItem.itemPrice }</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td class="totalCart">Total</td>
					<td class="totalCart">$ ${requestScope.sumOfCart}</td>
				</tr>
			</table>

			<hr>
			<input type="hidden" name="orderId" value="${requestScope.orderId}">
			<input type="hidden" name="restaurantId" value="${requestScope.restaurantDetails.restaurantId}">
			<input class="orderSumButton" type="submit" value="Place Order">
			<hr>
		</form>
	</div>
</body>

</html>