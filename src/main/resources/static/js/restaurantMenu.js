function addToCart(restaurantId, itemId) {
	// add the item from menu to userOrders and then get the total count of the items in cart and return it.
	const xmlHttpRequest = new XMLHttpRequest();
	let url = "/food-ordering/addItemToCart?itemId=" + itemId + "&restaurantId=" + restaurantId;
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			console.log("Success");
			document.getElementById("cartItemsCount").innerHTML = xmlHttpRequest.responseText;
		}
	}
	xmlHttpRequest.send();
	console.log("hello");
	document.getElementById("cartTotal").innerHTML = "$" + 1111;
}