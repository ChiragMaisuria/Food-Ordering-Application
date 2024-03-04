<!doctype html>
<html>

<head>
<title>Eatery Edit Menu</title>
<link rel="stylesheet" href="..//css//editMenuHome.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=KoHo">

</head>

<body class="editBody">
	<div>
		<img src="..//images//registerLoginPage//loginCover.jpg" height="40"
			style="display: inline-block;">
		<p class="editMainHeading">Northeastern Eatery</p>
		<hr>
		<img src="..//images//Restaurant//editMenuHome.png" height="30">
		<p class="editHeading">${requestScope.restaurantDetails.restaurantName}</p>
		<hr width="500px">
		<p class="editSubHeading">Edit your Restaurants Menu</p>
		<hr>
		<p class="editSubHeading2">How many Items do you want to add in
			the Menu?</p>
		<form action="editMenuItems">
			<input class="editInputBox" type="text" name="itemsCount"
				placeholder="0" /> <br> <input class="editButton"
				type="submit" name="formSubmit" value="Proceed"><br> <input
				class="editButton" type="submit" name="formSubmit" value="View Menu">
			<br> <input class="editButton" type="submit" name="formSubmit"
				value="View All Orders"> <input class="editButton"
				type="submit" name="formSubmit" value="View Restaurant Profile">
			<br> <input class="editButton" type="submit" name="formSubmit"
				value="log Out"> <input type="hidden"
				name="restaurantDetails" value="${requestScope.restaurantDetails}">
		</form>
	</div>
</body>

</html>