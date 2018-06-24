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

<jsp:include page="../../header.jsp" flush="true" />
<jsp:include page="../../cartbar.jsp"/>
<div class="container-fluid justify-content-center" style="min-height: 100vh; position: relative; padding-top: 25px;">
    <div class="row">
        <div class="col-2 pt-3">
            <div class="row">
                <jsp:include page="../../sidebar.jsp"/>
            </div>
        </div>
        <div class="col-9 pt-3">
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
                <div class="content__mainpart__product__description col-5 ml-auto shadow rounded">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true">Description</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                               aria-controls="profile" aria-selected="false">Feedback</a>
                        </li>
                    </ul>
                    <div class="tab-content pt-3" id="myTabContent">
                        <div class="tab-pane fade show active " id="home" role="tabpanel" aria-labelledby="home-tab">
                            <b>Rating: </b> <i style="margin-left: 20px">${rating}</i> <br><br>
                            <c:forEach var="item" items="${prod.parameters}">
                                <c:if test="${!'Price'.equals(item.key.name)}">
                                    <span class="pb-2 pt-2">${item.key.name} : ${item.value.value}</span><br><br>
                                </c:if>
                                <c:if test="${'Price'.equals(item.key.name)}">
                                    <span class="pb-2 pt-2"><c:set var="price" value="${item.value.value}"/></span><br>
                                </c:if>
                            </c:forEach>
                            <div class="row justify-content-center align-items-center">
                                <div class="product_pane_price_text col-4">
                                    Price: ${price} $
                                </div>
                                <div class="col-3 text-center">
                                    <a href="<c:url value="/admin/mainPage"/> " class="btn btn-primary" role="button">
                                        BACK TO OPERATIONS
                                    </a >
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Your feedback" aria-label="Feedback" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button">Send it</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
    <div class="row p-2 bg-light rounded justify-content-center text-primary" style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="../../footer.jsp" flush="true" />
    </div>
</div>
</body>
</html>
<%--comment for issue close commit--%>
