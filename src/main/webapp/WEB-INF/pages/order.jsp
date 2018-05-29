<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 28.05.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/order.css" />">
    <title>Order</title>
</head>
<body>
    <div class="row_wrapper">
        <div class="wrapper">
            <div class="booking_text">
                <h1>Booking</h1>
            </div>
            <div class="booking_form">
                <div class="booking_form_text">
                    <div class="email_container">
                        <input type="text" class="email_input " required>
                        <label class="floating_email font_size_20 value">Name & Surname</label>
                    </div>
                    <div class="pswrd_container">
                        <input type="text" class="pswrd_input" required>
                        <label class="floating_pswrd font_size_20 ">City</label>
                    </div>
                    <div class="email_container">
                        <input type="number" class="email_input" required>
                        <label class="floating_email font_size_20">Phone</label>
                    </div>
                    <div class="pswrd_container">
                        <input type="email" class="pswrd_input" required>
                        <label class="floating_pswrd font_size_20">Email</label>
                    </div>
                    <div class="btns_row">
                        <div class="btn_signup">
                            <a href="#" class="btn_link btn_link_up font_size_20" onclick="sendRequest()">Accept</a>
                        </div>
                        <div class="btn_signin">
                            <a href="#" class="btn_link btn_link_in font_size_20">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper" style="border: none; padding: 0px;">
            <div class="booking_text" style="border: 1px solid #C5C5C5; border-radius: 10px; height: 66px;">
                <h2>Your basket</h2>
            </div>
            <div class="basket_goods_row" style="cursor: pointer;">
                <div class="basket_item_amount font_size_20" style="padding-left: 20px;">
                    <p>1x</p>
                </div>
                <div class="basket_item_name font_size_20">
                    <p class="basket_item_name_text">Meizu M6s</p>
                </div>
                <div class="basket_item_photo">
                    <p>img</p>
                </div>
            </div>
            <div class="basket_goods_row width_less_70">
                <div class="basket_item_amount">
                    <p class="font_size_20">Total Price: 150$</p>
                </div>
            </div>

        </div>
    </div>

    <script>
        console.log(localStorage.getItem('productId'));
        var emailValues = document.querySelectorAll('.email_input');
        var pswrdValues = document.querySelectorAll('.pswrd_input');
        /*
        * private String receiverName;
    private String receiverSurname;
    private String receiverPhone;
    private String address;
    private String status;
        * */
        var orderId = 0;
        var userId = 0;
        var request = new XMLHttpRequest();
        var email0 = emailValues[0].value;
        var email1 = emailValues[1].value;
        var email2 = pswrdValues[0].value;

        var jsonObject = {
            delivery: {receiverName: email0,
            receiverPhone: email1,
            address: email2},
            products: {product_id: localStorage.productId}
        };

        var sendRequest = function () {
            /*request.open("POST", 'saveOrder?', true);
            request.setRequestHeader("Content-Type", "application/json");
*/          email0 = emailValues[0].value;
              email1 = emailValues[1].value;
              email2 = pswrdValues[0].value;
              jsonObject.delivery.receiverName = email0;
              jsonObject.delivery.receiverPhone = email1;
              jsonObject.delivery.address = email2;
            request.open('post', 'saveOrder?' + JSON.stringify(jsonObject));
            console.log(jsonObject);
            request.send();
        }
    </script>
</body>
</html>
