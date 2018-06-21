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

</head>

<c:if test="${product != null}">
    <c:forEach var="item" items="${product}">
        <c:if test="${'product'.equals(item.key)}">
            <c:set var="prod" value="${item.value}"/>
        </c:if>
        <c:if test="${'rating'.equals(item.key)}">
            <c:set var="rating" value="${item.value}"/>
        </c:if>
    </c:forEach>
</c:if>

<jsp:include page="header.jsp" flush="true" />
<div class="container-fluid justify-content-center" style="min-height: 100vh; position: relative;">
<div class="row">
    <div class="col-2 pt-5">
        <jsp:include page="sidebar.jsp" flush="true" />
    </div>
    <div class="col-9 pt-5">
        <div class="row">
        <div class="content__mainpart__product__photos col-5 ml-auto text-center align-items-center">
            <div class="content__mainpart__product__header text-center align-self-center">
                <p class="content__mainpart__product__header__text">
                    ${prod.name}
                </p>
            </div>
            <div class="content__mainpart__product__photos__img">
                <div class="img__placeholder product-image-same-size">
                    <img src="<c:url value="/resources/images/products/${prod.id}.jpg"/>"/>
                </div>
            </div>
        </div>
        <div class="content__mainpart__product__description col-5 ml-auto">
            <div class="description_headers">
                <p class="description_headers_text flag">
                    <a style="cursor: pointer" onclick="enableDescription();">Description</a>
                </p>
                <p class="description_headers_text">
                    <a style="cursor: pointer" onclick="getFeedback(${prod.id});">Feedback</a>
                </p>
            </div>
            <div class="description_container" style="width: 100%;">
                <div class="desc_text">
                    <b>Rating: </b> <i style="margin-left: 20px">${rating}</i> <br><br>
                    <c:forEach var="item" items="${prod.parameters}">
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
</body>
</html>

