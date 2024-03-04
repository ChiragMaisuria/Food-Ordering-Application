function redirectTo(page) {
	if (page == "addRestaurant") {
		window.location.href = "/food-ordering/manageRestaurant/addRestaurant";
	} else if (page == "restaurantLogin") {
		window.location.href = "/food-ordering/manageRestaurant/loginRestaurant";
	} else if (page == "customerLogin") {
		window.location.href = "/food-ordering/loginCustomer";
	} else if (page == "customerRegister") {
		window.location.href = "/food-ordering/registerCustomer";
	}
}

function redirectToMenu(restaurantId) {
	window.location.href = "/food-ordering/restaurantMenu?restaurantId=" + restaurantId;
}