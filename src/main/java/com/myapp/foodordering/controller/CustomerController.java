package com.myapp.foodordering.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.RestaurantDetails;
import com.myapp.foodordering.model.RestaurantMenu;
import com.myapp.foodordering.model.UserInformation;
import com.myapp.foodordering.model.UserOrders;
import com.myapp.foodordering.service.CustomerService;
import com.myapp.foodordering.service.RestaurantService;

import jakarta.persistence.Table;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	CustomerService cs;
	@Autowired
	RestaurantService rs;

	@RequestMapping(value = "/registerCustomer")
	public String addRestaurant(@ModelAttribute("registerCustomer") UserInformation userInfo) {
		return "user_register";
	}

	@RequestMapping(value = "/loginCustomer")
	public String loginRestaurant(@ModelAttribute("loginAuthenticate") LoginInformation loginInfo) {
		return "user_login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerNewUserAccount(@ModelAttribute("registerCustomer") UserInformation userInfo,
			HttpServletRequest request) {
//		Challenges:
//			1] Email needs to be unique.
		System.out.println(userInfo);
		boolean isAllValid = cs.addCustomerDetails(userInfo);
		HttpSession session;
		if (isAllValid) {
			request.getSession().invalidate();
			session = request.getSession(true);
			session.setAttribute("loggedIn", true);
		}else {
			return "user_register";
		}

		UserInformation userInfoForId = cs.fetchUserDetailsByEmail(userInfo.getEmail());
		int userId = userInfoForId.getUserId();

//		Create User Session as the user is registered.
		session.setAttribute("userId", userId);
		System.out.println(userId);

//		Get all restaurant Details and put in allRestaurantDetails Object.
		List<RestaurantDetails> restaurantDetails = cs.fetchAllRestaurantsDetails();
		request.setAttribute("allRestaurantDetails", restaurantDetails);

//		Redirect to HomePage - SignedIn
		return "view_all_restaurants_loggedIn";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginToAccount(@ModelAttribute("loginAuthenticate") LoginInformation loginInfo,
			HttpServletRequest request) {

		loginInfo.setUserRole("customer");
		System.out.println(loginInfo);
		System.out.println(cs.checkCustomerLogin(loginInfo));

		if (cs.checkCustomerLogin(loginInfo).equals("Authorized")) {
			UserInformation userInfo = cs.fetchUserDetailsByEmail(loginInfo.getEmail());
			request.getSession().invalidate();
			request.getSession(true).setAttribute("userInformation", userInfo);
			request.getSession(false).setAttribute("loggedIn", true);
			List<RestaurantDetails> restaurantDetails = cs.fetchAllRestaurantsDetails();
			request.setAttribute("allRestaurantDetails", restaurantDetails);

			request.getSession(false).setAttribute("userId", userInfo.getUserId());
			return "view_all_restaurants_loggedIn";
		} else {
			request.getSession(false).setAttribute("loggedId", false);
			return "user_login";
			
		}

//		simpleFormController
	}

	@RequestMapping("/allRestaurants")
	public String viewAllRestaurants(HttpServletRequest request) {

//		DATA REQUIRED:
//			- Check for user logged in or not from session [session exists if created when user logged in].
//			- List of all the restaurants from RestaurantDetails Table.

//		List allRestaurants = cs.fetchAllRestaurantsDetails(); // add to model.
//		Get all restaurant Details and put in allRestaurantDetails Object.
		List<RestaurantDetails> restaurantDetails = cs.fetchAllRestaurantsDetails();
		request.setAttribute("allRestaurantDetails", restaurantDetails);
//		request.getSession(false).invalidate();
		request.getSession().setAttribute("loggedIn", false);
		
//		change to view_all_restaurants
		return "view_all_restaurants";
//		return "view_all_restaurants_loggedIn";
	}

	@RequestMapping("/restaurantMenu")
	public String viewRestaurantMenu(@RequestParam("restaurantId") int restaurantId, HttpServletRequest request) {

//		DATA REQUIRED:
//			- Check for user logged in or not [check in session].
//			- Fetch the details from restaurantMenu Table WHERE id=restaurantId.
		List<RestaurantMenu> restaurantMenu = cs.fetchRestaurantMenu(restaurantId);
		request.setAttribute("restaurantMenu", restaurantMenu);
		RestaurantDetails restaurantDetails = rs.fetchRestaurantDetails(restaurantId);
		request.setAttribute("restaurantDetails", restaurantDetails);
		System.out.println("request.getSession(false): "+request.getSession(false).getId());
//		request.getSession(false).invalidate();
		if((boolean)request.getSession(false).getAttribute("loggedIn")) {
			request.setAttribute("loggedIn", true);
		}else {
			request.setAttribute("loggedIn", false);
		}
		return "restaurant_menu";
	}

	@RequestMapping("/addItemToCart")
	public String addMenuItemToCart(@RequestParam("itemId") int restaurantItemId,
			@RequestParam("restaurantId") int restaurantId, HttpServletRequest request) {

//		OPERATIONS REQUIRED:
//			- Check for user logged in or not.
//			
//			- if user logged in:
//				- add item to session + insert to UserOrders with status=in-cart
//			
//			- if user not logged in:
//				- add items to cookies

		UserInformation userInfo = (UserInformation) request.getSession(false).getAttribute("userInformation");
		cs.addItemsToCart(restaurantItemId, restaurantId, (int)userInfo.getUserId());
		List<UserOrders> cartItems = cs.fetchCartItems((int)userInfo.getUserId());
//		return the count of total items in the cart.
		System.out.println(cartItems.size());
		request.setAttribute("cartItemsCount", cartItems.size());
		return "ajax_menu_cart_count";
	}

	@RequestMapping("/cart")
	public String viewCartItems(HttpServletRequest request) {

//		OPERATIONS REQUIRED:
//		- Check for user logged in or not.
//		
//		- if user logged in:
//			- fetch item from session || logged in from another device fetch UserOrders with status=in-cart AND userId=userId
//		
//		- if user not logged in:
//			- fetch items from cookies if present or show custom message

//		List cartItems = cs.fetchCartItems(userId);
		List<UserOrders> cartItems = cs.fetchCartItems((int)request.getSession(false).getAttribute("userId"));
		request.setAttribute("cartItems", cartItems);
		int restaurantId = cartItems.get(0).getRestaurantId();
		RestaurantDetails restaurantDetails = rs.fetchRestaurantDetails(restaurantId);
		request.setAttribute("restaurantDetails", restaurantDetails);
		request.setAttribute("restaurantId", restaurantDetails.getRestaurantId());
		request.getSession(false).setAttribute("restaurantId", restaurantDetails.getRestaurantId());
		return "view_cart";
	}

	@RequestMapping("/removeFromCart")
	public String removeItemFromCart(@RequestParam("itemId") int itemId, HttpServletRequest request) {

//		OPERATIONS REQUIRED:
//		- Check for user logged in or not.
//		
//		- if user logged in:
//			- remove item from session || logged in from another device remove UserOrders with status=in-cart AND userId=userId
//		
//		- if user not logged in:
//			- remove items from cookies if present.

//		if (request.getSession(false) == null) {
////			- if user not logged in:
////			- remove items from cookies if present
//
//		} else {
//			cs.removeItemFromCart(itemId, request.getSession(false));
//		}

		cs.removeItemFromCart(itemId, (int) request.getSession(false).getAttribute("userId"));
//		request.setAttribute("restaurantId", request.getSession().getAttribute("restaurantId"));
//		return cart view by removing that item first and then retrieving the same
//		request.setAttribute("restaurantId", request.getAttribute("restaurantId"));
		return "redirect:/cart";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String viewOrders() {
		System.out.println("viewOrders() is called.");
		return "user_orders";
	}

	@RequestMapping("/pastOrders")
	public String viewPastOrders(HttpServletRequest request) {

//		DATA REQUIRED:
//			- Fetch all orders from UserOrders WHERE orderStatus=completed.
//		in-progress, completed
//		get userId from session object.
		List pastOrders = cs.viewOrders("completed", (int) request.getSession(false).getAttribute("userId"));

		return "";
	}

	@RequestMapping("/currentOrders")
	public String viewCurrentOrders(HttpServletRequest request) {

//		DATA REQUIRED:
//			- Fetch all orders from UserOrders WHERE orderStatus=in-progress.

		List currentOrders = cs.viewOrders("in-progress", (int) request.getSession(false).getAttribute("userId"));

		return "";
	}

	@RequestMapping("/orderSummary")
	public String placeOrderInitial(@RequestParam("restaurantId") int restaurantId, HttpServletRequest request) {

//		OPERATIONS PERFORMED:
//			- Fetch userId from userSession.
//			- change the order status in UserOrders from in-cart to placed.
//			- add an order in RestaurantOrders with status=waiting.

//		cs.updateOrderStatus(orderId);

		List<UserOrders> cartItems = cs.fetchCartItems((int) request.getSession(false).getAttribute("userId"));
		request.setAttribute("cartItems", cartItems);

		double sumOfCart = 0;
		for (UserOrders userOrder : cartItems) {
			sumOfCart += userOrder.getItemPrice();
		}

		RestaurantDetails restaurantDetails = rs.fetchRestaurantDetails(restaurantId);
		
		request.setAttribute("restaurantDetails", restaurantDetails);
		request.setAttribute("sumOfCart", sumOfCart);
		request.setAttribute("orderId", cartItems.get(0).getOrderId());

		return "order_summary";
	}

	@RequestMapping("/placeOrder")
	public String placeOrder2(@RequestParam("orderId") int orderId, HttpServletRequest request) {

//		OPERATIONS PERFORMED:
//			- Fetch userId from userSession.
//			- change the order status in UserOrders from in-cart to placed.
//			- add an order in RestaurantOrders with status=waiting.

		String previousStatus = "in-cart";
		String updateToStatus = "Placed";
		cs.updateOrderStatus((int) request.getSession(false).getAttribute("userId"), orderId, previousStatus, updateToStatus);

		return "user_orders";
	}

	@RequestMapping(value = "/account")
	public String viewAccountInfo() {

//		DATA REQUIRED:
//			- Fetch all the data from the UserInformation with userId=.

//		fetch the userId from the session.
//		UserInformation userInfo = cs.fetchUserDetails(userId);

		return "user_profile";
	}
	
	@RequestMapping("/signOut")
	public String logOut(HttpServletRequest request) {
		request.getSession(false).invalidate();
		return "redirect:/allRestaurants";
	}
}
