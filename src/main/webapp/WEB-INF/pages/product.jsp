<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/product.css" />">

    <title>Document</title>
</head>
<body>
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
<div class="main">
<nav class="nav">
    <div class="nav__logo">
        <a href="<c:url value="/"/>" class="nav__logo__link">
            NC Loft
        </a>
    </div>
    <div class="nav__links">
        <a href="#" class="nav__links__item">
            Payment & Delivery
        </a>
        <a href="#" class="nav__links__item">
            Contacts
        </a>
        <a href="#" class="nav__links__item">
            About us
        </a>
    </div>
    <div class="nav__signin">
        <P class="nav__signin__text">Sign in</P>
        <img src="<c:url value="/resources/icons/signin.svg" />" alt="signin icon"
             class="nav__signin__icon_width_20"/>
    </div>
</nav>
<div class="content">
    <div class="basket">
        <a href="#">
            <img src="<c:url value="/resources/icons/basket.jpg" />" alt="basket" class="basket_icon">
        </a>
    </div>
    <div class="content__sidebar">
        <p class="content__sidebar__text content__sidebar__text_fontsize_21">
            Categories
        </p>
        <div class="content__sidebar__list__container">
            <ul class="content__sidebar__list__container__categorieslist">
                <li><a href="#"> Laptops & PC </a></li>
                <li><a href="#"> TV, Electronics</a> </li>
                <li><a href="">Appliances</a> </li>
                <li><a href="">Sports</a></li>
                <li><a href="">Garden tools</a></li>
                <li><a href="">Clothes & Shoes</a></li>
                <li><a href="">Cars & Bikes</a></li>
            </ul>
        </div>
        <p class="content__sidebar__text__price content__sidebar__text_fontsize_21">
            Price
        </p>
        <div class="content__sidebar__list__container__price">
            <ul class="content__sidebar__list__container__pricelist">
                <li><input type="checkbox" class="margin_right_10"> Under $250</li>
                <li><input type="checkbox" class="margin_right_10"> $250 - $450</li>
                <li><input type="checkbox" class="margin_right_10"> Over $450</li>
                <li>
                    $ <input type="number" class="input__text_width_35"> - to $
                    <input type="number" class="input__text_width_35">
                </li>
            </ul>
        </div>
        <div class="content__sidebar__search__button">
            <a href="search" class="content__sidebar__search__button__btn" onclick="search">seek</a>
        </div>
    </div>
    <div class="content__mainpart">
        <div class="left__border"></div>
        <div class="content__mainpart__product__photos">
            <div class="content__mainpart__product__header">
                <p class="content__mainpart__product__header__text">
                    ${productById.name}
                </p>
            </div>
            <div class="content__mainpart__product__photos__img">
                <img src="<c:url value="/resources/images/macbook13.jpg" />" alt="macbook">
            </div>
            <div class="content__mainpart__product__photos__img">
                <img src="<c:url value="/resources/images/leftsideMacbook.jpg" />" alt="macbook">
            </div>
        </div>
        <div class="content__mainpart__product__description">
            <div class="description_headers">
                <p class="description_headers_text"><a href="#">Description</a></p>
                <p class="description_headers_text"><a href="#">Feedback</a></p>
            </div>
            <div class="description_container">
                <div class="desc_text">
                    <c:forEach var="prods" items="${productList}">
                        <li><a href="<c:url value="/product">
                                        <c:param name="prod_id" value="${prods.id}"/>
                                     </c:url>">${prods.name}</a></li>
                    </c:forEach>
                    <c:forEach var="item" items="${productById.parameters}">
                        ${item.key.name}: ${item.value.value} <br>
                    </c:forEach>
                </div>
                <div class="desc_text">
                    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </div>
                <div class="desc_text">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                </div>
                <div class="product_pane">
                    <div class="product_pane_price">
                        <p class="product_pane_price_text">Price: {value} $</p>
                    </div>
                    <div class="product_pane_buy_btn">
                        <a href="buyProduct" class="product_pane_buy_btn_link">
                            Buy
                        </a>
                    </div>
                    <div class="product_pane_add_btn">
                        <a href="buyProduct" class="product_pane_add_btn_link">
                            <img src="<c:url value="/resources/icons/add.svg"/>" alt="add button">
                        </a>
                    </div>
                    <div class="product_pane_add_feedbck_btn">
                        <a href="buyProduct" class="product_pane_add_feedbck_btn_link">
                            <img src="<c:url value="/resources/icons/like.svg"/>" alt="like">
                        </a>
                    </div>
                </div>
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
</div>
<script>
    var signinElem = document.querySelector('.nav__signin');
    var basketElem = document.querySelector('.basket');
    var navElem = document.querySelector('nav_logo');
    var mainElem = document.querySelector('.main');
    var authorization = document.querySelector('.authorization');
    var closeAuthElem = document.querySelector('.authorization__close');
    var flag = true;

    navElem.addEventListener('click', function () {
        window.location.replace("onlinestore");
    });

    signinElem.addEventListener('click', function() {
        if (flag) {
            authorization.classList.remove('display_none');
            basketElem.classList.add('display_none');
            mainElem.classList.add('display_none');
            flag = false;
        }

    });
    closeAuthElem.addEventListener('click', function () {
        if (!flag) {
            authorization.classList.add('display_none');
            basketElem.classList.remove('display_none');
            mainElem.classList.remove('display_none');
            flag = true;
        }
    });
</script>
</body>
</html>

