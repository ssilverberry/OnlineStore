<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/product.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/content.css" />">
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/testFeedback.js"/>"></script>
    <script src="<c:url value="/resources/js/testDescription.js"/>"></script>

    <title>Document</title>

    <c:forEach var="map" items="${products}">
        <c:if test="${'product'.equals(map.key)}">
            <c:set var="product" value="${map.value}"/>
        </c:if>
        <c:if test="${'categories'.equals(map.key)}">
            <c:set var="categs" value="${map.value}"/>
        </c:if>
    </c:forEach>

</head>
<jsp:include page="header.jsp" flush="true" />
<div class="container-fluid justify-content-center" style="min-height: 100vh; position: relative;">
<div class="row">
    <div class="col-2 pt-5">
        <jsp:include page="sidebar.jsp" flush="true" />
    </div>
    <div class="col-9 pt-5">
        <div class="row">
        <div class="content__mainpart__product__photos">
            <div class="content__mainpart__product__header">
                <p class="content__mainpart__product__header__text">
                    ${product.name}
                </p>
            </div>
            <div class="content__mainpart__product__photos__img">
                <div class="img__placeholder">
                    <p class="" style="object-fit:contain; width: 260px; margin: auto">
                        <img src="<c:url value="/resources/images/products/${product.id}.jpg"/>"/>
                    </p>
                </div>
            </div>
        </div>
        <div class="content__mainpart__product__description">
            <div class="description_headers">
                <p class="description_headers_text flag">
                    <a style="cursor: pointer" onclick="enableDescription();secondBgChangr()">Description</a>
                </p>
                <p class="description_headers_text">
                    <a style="cursor: pointer" onclick="getFeedback(${product.id});bgChanger()">Feedback</a>
                </p>
            </div>
            <div class="description_container" style="width: 100%;">
                <div class="desc_text">
                    <c:forEach var="item" items="${product.parameters}">
                        <c:if test="${!'Price'.equals(item.key.name)}">
                            ${item.key.name} : ${item.value.value} <br><br>
                        </c:if>
                        <c:if test="${'Price'.equals(item.key.name)}">
                            <c:set var="price" value="${item.value.value}"/>
                        </c:if>
                    </c:forEach>
                </div>
                <div id="feedback_container" class="feedback_text display_none">
                    Here is feedback !
                </div>
                <div class="product_pane">
                    <div class="product_pane_price">
                        <p class="product_pane_price_text">
                            Price: ${price} $
                        </p>
                    </div>
                    <div class="product_pane_buy_btn">
                        <a href="order" class="product_pane_buy_btn_link">
                            Buy
                        </a>
                    </div>
                    <%--<div class="product_pane_add_btn">
                        <a class="product_pane_add_btn_link" style="cursor: pointer; ">
                            <img src="<c:url value="/resources/icons/add.svg"/>" alt="add button">
                        </a>
                    </div>
                    <div class="product_pane_add_feedbck_btn">
                        <a href="buyProduct" class="product_pane_add_feedbck_btn_link">
                            <img src="<c:url value="/resources/icons/like.svg"/>" alt="like">
                        </a>
                    </div>--%>
                </div>
            </div>
            </div>
        </div>
    </div>

</div>
    <div class="row p-2 bg-light rounded justify-content-center text-primary" style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="footer.jsp" flush="true" />
    </div>
</div>
<script async>
    var signinElem = document.querySelector('.nav__signin');
    var basketElem = document.querySelector('.basket');
    var mainPartList = document.querySelector('.product_result_container');
    var content_mainpart = document.querySelector('.content__mainpart');
    var basketCounter = document.querySelector('.basket_counter');
    var addButton = document.querySelector('.product_pane_add_btn_link');
    var authorization = document.querySelector('.authorization');
    var authorization_overlay = document.querySelector('.overlay_container');
    var closeAuthElem = document.querySelector('.authorization__close');
    var bg_desc = document.querySelectorAll('.description_headers_text');
    var flag = true;
    var counter = 0;
    var request = new XMLHttpRequest();
    var path = "productCategoriesId?";
    var productId = 0;
    var target;
    var text;
    var bool = true;

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                mainPartList.innerHTML = request.responseText;
                content_mainpart.style.display = "none";
            } else {
                console.log('An error occurred during your request: ' +  request.status + ' ' + request.statusText);
            }
        }
    };
    signinElem.addEventListener('click', function() {
        authorizeOverlay();
        if (flag) {
            authorization.classList.remove('display_none');
            basketElem.classList.add('display_none');
            flag = false;
        }

    });
    closeAuthElem.addEventListener('click', function () {
        if (!flag) {
            authorization.classList.add('display_none');
            basketElem.classList.remove('display_none');
            authorizeOverlay();
            flag = true;
        }
    });
    addButton.addEventListener('click', function() {
        ++counter;
        productId = ${product.id};
        localStorage.setItem('counter', counter.toString());
        localStorage.setItem('productId', productId.toString());
        basketCounter.innerHTML = '+' + localStorage.counter;
        console.log(localStorage.productId);
    });
    window.onload = function () {
        if (localStorage.counter)
            basketCounter.innerHTML = '+' + localStorage.counter;
    };
    var getCategoriesList = function (id) {
        console.log("request started !");
        request.open('get', path + "category_id=" + id);
        request.send();
    };
    var authorizeOverlay = function () {
        authorization_overlay.classList.toggle('overlay');
    };

    var bgChanger = function () {
        if (bool) {
            bg_desc[0].classList.remove('flag');
            bg_desc[1].classList.add('flag');
        }
        bool = false;
    };
    var secondBgChangr = function () {
        if (!bool){
            bg_desc[0].classList.add('flag');
            bg_desc[1].classList.remove('flag');
        }
        bool = true;
    }

</script>
</body>
</html>

