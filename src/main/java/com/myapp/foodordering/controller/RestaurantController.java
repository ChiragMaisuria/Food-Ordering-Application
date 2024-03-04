package com.myapp.foodordering.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.foodordering.model.LoginInformation;
import com.myapp.foodordering.model.RestaurantDetails;
import com.myapp.foodordering.model.RestaurantMenu;
import com.myapp.foodordering.service.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manageRestaurant")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@RequestMapping(value = "/addRestaurant")
	public String addRestaurant(@ModelAttribute("restaurantInfo") RestaurantDetails restaurantDetails) {
		return "restaurant_register";
	}

	@RequestMapping("/register")
	public String registerNewRestaurantAccount(@ModelAttribute("restaurantInfo") RestaurantDetails restaurantDetails) {
		System.out.println(restaurantDetails.getRestaurantName());
//		RestaurantDetails restaurantDetails = null;
		restaurantService.addRestaurantDetails(restaurantDetails);

		return "edit_menu_home";
	}

	@RequestMapping(value = "/loginRestaurant")
	public String loginRestaurant(@ModelAttribute("loginInfo") LoginInformation loginInformation) {
		return "restaurant_login";
	}

	@RequestMapping("/login")
	public String loginToAccount(@ModelAttribute("loginInfo") LoginInformation loginInformation,
			HttpServletRequest request) {
		System.out.println(loginInformation.getEmail());
//		LoginInformation loginInformation = null;
		loginInformation.setUserRole("restaurant");
		restaurantService.checkRestaurantLogin(loginInformation);

		RestaurantDetails restaurantDetails = restaurantService
				.fetchRestaurantDetailsByEmail(loginInformation.getEmail());
		request.setAttribute("restaurantDetails", restaurantDetails);
		return "edit_menu_home";
	}

	@RequestMapping("/restaurantHome")
	public String redirectToHome() {
		return "edit_menu_home";
	}

	@RequestMapping("/restaurantInfo")
	public String viewRestaurantInfo() {

//		Fetch details from the sessionObject.
//		restaurantService.fetchRestaurantDetails();

		return "restaurant_profile";
	}

	@RequestMapping("/currentActiveOrders")
	public String viewAllCurrentActiveOrders() {

//		Fetch details from the sessionObject.
//		restaurantService.viewRestaurantOrders("in-progress");

		return "";
	}

	@RequestMapping("/pastOrders")
	public String viewAllPastOrders() {

//		Fetch details from the sessionObject.
//		restaurantService.viewRestaurantOrders("completed");

		return "";
	}

	@RequestMapping("/waitingOrders")
	public String viewAllWaitingOrders() {

//		Fetch details from the sessionObject.
//		restaurantService.viewRestaurantOrders("waiting");

		return "";
	}

	@RequestMapping("/updateOrder")
	public String updateOrderStatus(/* int orderId, String status */) {
		int orderId = 0;
		String status = null;
//		get the OrderId from session and update the status
		restaurantService.updateOrderStatus(orderId, status);
		return "";
	}

	@RequestMapping("/editMenuItems")
	public String editMenuItem(@RequestParam(value = "itemsCount", required = false, defaultValue = "0") int itemsCount,
			@RequestParam(value = "formSubmit", required = false, defaultValue = "Proceed") String formSubmissionValue,
			HttpServletRequest request) {
		System.out.println("Hello this is editMenuItem()");
		if (formSubmissionValue.equals("View Menu")) {
			return "restaurant_view_menu";
		} else if (formSubmissionValue.equals("View All Orders")) {
			return "restaurant_orders";
		} else if (formSubmissionValue.equals("View Restaurant Profile")) {
			return "restaurant_profile";
		} else if (formSubmissionValue.equals("log Out")) {
			return "redirect:/manageRestaurant/restaurantLogout";
		}

		List<RestaurantMenu> restaurantMenu = restaurantService.fetchAllMenuItems(0);
		request.setAttribute("restaurantMenu", restaurantMenu);
		RestaurantDetails restaurantDetails = restaurantService.fetchRestaurantDetails(0);
		request.setAttribute("restaurantDetails", restaurantDetails);
		request.setAttribute("itemsCount", itemsCount);
		return "edit_menu_items";
	}

	@RequestMapping("/addMenuItem")
	public String addMenuItem(/* RestaurantMenu restaurantMenu */ HttpServletRequest request) {
//		RestaurantMenu restaurantMenu = null;
//		restaurantService.addItemsToMenu(restaurantMenu);

		if (request.getParameter("onAddItem").equals("View Menu")) {
			return "redirect:/manageRestaurant/viewMenu";
		}

		Map<String, String[]> menuItemsToAdd = request.getParameterMap();
		int count = 0;
		RestaurantMenu restaurantMenuItem = new RestaurantMenu();
		for (String parameterName : menuItemsToAdd.keySet()) {
			count += 1;
			if (parameterName.contains("itemName")) {
				restaurantMenuItem.setRestaurantMenuItemName(menuItemsToAdd.get(parameterName)[0]);
			}
			if (parameterName.contains("itemPrice")) {
				restaurantMenuItem.setRestaurantMenuItemPrice(Double.parseDouble(menuItemsToAdd.get(parameterName)[0]));
			}
			if (count == 2) {
				restaurantMenuItem.setRestaurantId(0);
				restaurantService.addItemsToMenu(restaurantMenuItem);
				restaurantMenuItem = new RestaurantMenu();
				count = 0;
			}

			System.out.println(parameterName + " : " + menuItemsToAdd.get(parameterName)[0]);
		}

		return "redirect:/manageRestaurant/editMenuItems";
	}

	@RequestMapping("/deleteMenuItem")
	public String deleteMenuItem(@RequestParam("itemId") int itemId, HttpServletRequest request) {
		int restaurantId = 0;
		int menuItemId = itemId;
		System.out.println("itemId: " + itemId);

//		Fetch details from the sessionObject.
		restaurantService.removeItemsFromMenu(restaurantId, menuItemId);

		return "redirect:/manageRestaurant/editMenuItems?itemsCount=" + request.getParameter("itemsCount");
	}

	@RequestMapping("/viewMenu")
	public String viewMenuItems(HttpServletRequest request) {

//		Fetch details from the sessionObject.
//		restaurantService.viewMenu();
		List<RestaurantMenu> restaurantMenu = restaurantService.fetchAllMenuItems(0);
		request.setAttribute("restaurantMenu", restaurantMenu);
		return "restaurant_view_menu";
	}

	@RequestMapping("/restaurantLogout")
	public String logOut() {

		return "redirect:/allRestaurants";
	}
}
