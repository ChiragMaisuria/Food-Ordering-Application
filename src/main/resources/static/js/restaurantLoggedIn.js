function redirectTo(page) {
	console.log("redirectTo() method invoked with " + page);
	if (page == "user_profile") {
		window.location.href = "/food-ordering/account";
	} else if (page == "all_orders") {
		console.log("Order method invoked.");
		window.location.href = "/food-ordering/orders"
	} else if (page == "cart") {
		window.location.href = "/food-ordering/cart";
	} else if (page == "logOut") {
		window.location.href = "/food-ordering/signOut"
	}
}

function redirectToRestaurant(page, restaurantId) {
	if (page == "menu") {
		console.log("Menu called with restauratId=" + restaurantId);
		window.location.href = "/food-ordering/restaurantMenu?restaurantId=" + restaurantId;
	}
}

function redirectToMenu(restaurantId) {
	window.location.href = "/food-ordering/restaurantMenu?restaurantId=" + restaurantId;
}