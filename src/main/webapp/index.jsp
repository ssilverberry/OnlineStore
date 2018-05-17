<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:49
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
  <%--<spring:url value="/resources/css/reset.css" var="mainCss" />
  <spring:url value="/resources/css/header.css" var="jqueryJs" />
  <spring:url value="/resources/css/content.css" var="mainJs" />
  <spring:url value="/resources/css/footer.css" var="mainJs" />--%>

  <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
  <link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
  <link rel="stylesheet" href="<c:url value="/resources/css/content.css" />">
  <link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />">

  <title>NC Loft</title>
</head>
<body>
<nav class="nav">
  <div class="nav__logo">
    <a href="#" class="viewResults">
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
  <a class="nav__signin">
    <P class="nav__signin__text">Sign in</P>
    <a href="signIn" style="text-decoration: none; color: #000;">
        <img src="<c:url value="/resources/icons/signin.svg" />" alt="signin icon" 
             class="nav__signin__icon_width_20"/>
    </a>
  </a>
  </div>
</nav>
<div class="content">
  <div class="content__sidebar">
    <p class="content__sidebar__text content__sidebar__text_fontsize_21">
      Categories
    </p>
    <div class="content__sidebar__list__container">
      <ul class="content__sidebar__list__container__categorieslist">
        <li>Laptops & PC</li>
        <li>TV, Electronics</li>
        <li>Appliances</li>
        <li>Sports</li>
        <li>Garden tools</li>
        <li>Clothes & Shoes</li>
        <li>Cars & Bikes</li>
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
  </div>
  <div class="content__mainpart">
    <div class="content__mainpart__toprates">
      <p class="content__mainpart__toprates__text">Top rates</p>
      <div class="content__mainpart__toprates__row">
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/macbook13.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Macbook Pro Retina 13'
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/iphoneSE.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            iphone SE 32 gb
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/ssd.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Samsung SSD 256 Gb
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/marshall.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Sound Amplifier
          </p>
        </div>
      </div>
    </div>
    <div class="content__mainpart__topsales">
      <p class="content__mainpart__toprates__text">Top rates</p>
      <div class="content__mainpart__toprates__row">
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/apple_z_8c1_bh_27_imac_pro_with_1380894.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Macbook Pro Retina 13'
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/iphoneSE.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            iphone SE 32 gb
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/ssd.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Samsung SSD 256 Gb
          </p>
        </div>
        <div class="content__mainpart__toprates__item">
          <img src="<c:url value="/resources/images/marshall.jpg"/>" alt="macbook"
               class="toprates__item">
          <p class="content__mainpart__toprates__item__text">
            Sound Amplifier
          </p>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="footer">
  <div class="footer__social">
    <img src="<c:url value="/resources/icons/insta.svg"/>" alt="insta" class="footer__social__icon">
    <img src="<c:url value="/resources/icons/facebk.svg"/>" alt="facebk" class="footer__social__icon">
    <img src="<c:url value="/resources/icons/twitter.svg"/>" alt="twitter" class="footer__social__icon">
    <img src="<c:url value="/resources/icons/telegram-app.svg"/>" alt="telega" class="footer__social__icon">
  </div>
  <div class="footer__license">
    <p class="footer__license__text">&copy; 2018, NC Loft.com</p>
  </div>
  <div class="footer__mail">
    <img src="<c:url value="/resources/icons/mail.svg"/>" alt="mail icon" class="footer__mail__icon">
  </div>
</div>
</body>
</html>

