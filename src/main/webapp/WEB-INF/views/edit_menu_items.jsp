<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!doctype html>
<html>

<head>
    <title>
        Eatery Edit Menu
    </title>
    <link rel="stylesheet" href="..//css//editMenuItems.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=KoHo">
    <script src="..//js//editMenuItems.js"></script>

</head>

<body class="editBody">
    <div>
        <img src="..//images//registerLoginPage//loginCover.jpg" height="40" style="display: inline-block;">
        <p class="editMainHeading">Northeastern Eatery</p>
        <hr>
        <img src="..//images//Restaurant//editMenuHome.png" height="30">
        <p class="editHeading">${requestScope.restaurantDetails.restaurantName}</p>
        <hr width="500px">
        <p class="editSubHeading">Edit your Restaurants Menu</p>
        <hr>
        <form action="addMenuItem">
            <table>
                <tr>
                    <th></th>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.restaurantMenu}" var="restaurantMenuItem">
                <tr>
                    <td><img src="..//images//Restaurant//itemsMarker.png" height="25" style="display: inline-block;" />
                    </td>
                    <td style="font-family: koHo; padding-left: 40px; padding-right: 40px">${restaurantMenuItem.restaurantMenuItemName}</td>
                    <td style="font-family: koHo; padding-left: 40px; padding-right: 40px">$ ${restaurantMenuItem.restaurantMenuItemPrice}</td>
                    <td>
                    <button type="button" style="background: white; border: 0px" onclick="removeItemFromMenu(${restaurantMenuItem.restaurantMenuItemId}, ${requestScope.itemsCount})">
                    <img src="..//images//Restaurant//deleteItems.png" height="25" style="display: inline-block; cursor: pointer;" />
                    </button>
                    </td>
                </tr>
                </c:forEach>
                <c:forEach begin="1" var="i" end="${requestScope.itemsCount}">
                <tr>
                    <td><img src="..//images//Restaurant//itemsMarker.png" height="25" style="display: inline-block;" />
                    </td>
                    <td><input type="text" name="itemName${i}" class="editMenuItemInputBox" /></td>
                    <td><input type="text" name="itemPrice${i}" class="editMenuItemPriceInputBox" /></td>
                    <td><img src="..//images//Restaurant//addItems.png" height="25" style="display: inline-block;" /></td>
                </tr>
                </c:forEach>
            </table>

            <input class="editButton" type="submit" name="onAddItem" value="Save"><br>
            <input class="editButton" type="submit" name="onAddItem" value="View Menu">
        </form>
    </div>
</body>

</html>