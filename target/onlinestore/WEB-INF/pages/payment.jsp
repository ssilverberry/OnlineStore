<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/content.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />">
    <script src="<c:url value="/resources/js/script.js"/>" async></script>
    <title>NC Loft</title>
</head>
<body>
<div class="overlay_container">
    <div class="authorization display_none">
        <div class="authorization__row">
            <h2 class="welcome_text">Welcome</h2>
            <a href="#" style="text-decoration: none; color: initial;" class="authorization__close">
                <img src="<c:url value="/resources/icons/close.svg" />" alt="close" >
            </a>
        </div>
        <div class="authorization__input">
            <div class="email_container">
                <input type="email" class="email_input" required>
                <label class="floating_email">Email</label>
            </div>
            <div class="pswrd_container">
                <input type="password" class="pswrd_input" required>
                <label class="floating_pswrd">Password</label>
            </div>
        </div>
        <div class="btns_row">
            <div class="btn_signup">
                <a href="#" class="btn_link btn_link_up">Sign up</a>
            </div>
            <div class="btn_signin">
                <a href="#" class="btn_link btn_link_in">Log in</a>
            </div>
        </div>
        <div class="forgott_password">
            <a href="restorePswrd"class="forgott_password_text">
                Forgot password ?
            </a>
            <span class="link_decoration"></span>
        </div>
    </div>
</div>
<div class="main" >
    <nav class="nav">
        <div class="nav__logo">
            <a href="<c:url value="/"/>" class="nav__logo__link">
                NC Loft
            </a>
        </div>
        <div class="nav__links">
            <a href="payDelive" class="nav__links__item">
                Payment & Delivery
            </a>
            <a href="contacts" class="nav__links__item">
                Contacts
            </a>
            <a href="aboutus" class="nav__links__item">
                About us
            </a>
        </div>
        <div class="nav__signin">
            <P class="nav__signin__text">Sign in</P>
            <img src="<c:url value="/resources/icons/signin.svg" />" alt="signin icon"
                 class="nav__signin__icon_width_20"/>
        </div>
    </nav>
    <div class="content" style="min-height: 100%;">
        <div class="basket" onclick="getCategoriesList()">
            <a href="#">
                <img src="<c:url value="/resources/icons/basket.jpg"/>" alt="basket" class="basket_icon">
            </a>
            <div class="basket_text"></div>
        </div>
        <div class="content__mainpart">
            <div class="description_container" style="margin-top: 50px; margin-left: 50px;">
                We deliver to all cities of Ukraine.("nova Post", "night express", "delivery","ukr post" )<br>
                Payment for goods can be made for: Prepayment or postpay. Cash and non-cash payment. <br>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="footer__social">
        <a href="https://www.instagram.com/">
            <img src="<c:url value="/resources/icons/insta.svg"/>" alt="insta" class="footer__social__icon">
        </a>
        <a href="https://www.facebook.com/">
            <img src="<c:url value="/resources/icons/facebk.svg"/>" alt="facebk" class="footer__social__icon">
        </a>
        <a href="https://twitter.com/">
            <img src="<c:url value="/resources/icons/twitter.svg"/>" alt="twitter" class="footer__social__icon">
        </a>
    </div>
    <div class="footer__license">
        <p class="footer__license__text">&copy; 2018, NC Loft.com</p>
    </div>
    <div class="footer__mail">
        <a href="mailto:ncloft@gmail.com?subject=Partnership">
            <img src="<c:url value="/resources/icons/mail.svg"/>" alt="mail icon" class="footer__mail__icon">
        </a>
    </div>
</div>
</body>
</html>

