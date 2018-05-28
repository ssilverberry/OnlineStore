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
                        <input type="text" class="email_input" required>
                        <label class="floating_email font_size_20">Name & Surname</label>
                    </div>
                    <div class="pswrd_container">
                        <input type="text" class="pswrd_input" required>
                        <label class="floating_pswrd font_size_20">City</label>
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
                            <a href="#" class="btn_link btn_link_up font_size_20">Accept</a>
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
            <div class="basket_goods_row">
                <div class="basket_item_amount font_size_20" style="padding-left: 20px;">
                    1x
                </div>
                <div class="basket_item_name font_size_20">
                    <p class="basket_item_name_text">Meizu M6s</p>
                </div>
                <div class="basket_item_photo">
                    <p>img</p>
                </div>
            </div>

        </div>
    </div>


</body>
</html>