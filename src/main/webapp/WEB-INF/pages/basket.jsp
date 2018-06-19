<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 02.06.2018
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/basket.css" />">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <title>Basket</title>
</head>
<body>
<div class="jumbotron jumbotron-fluid bg-dark" style="color: #ffffff">
    <div class="container">
        <h1 class="display-5">Your goods</h1>
        <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
    </div>
</div>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-3 " style=" margin-right: 7px;">
                <div class="card" style="width: 18rem;">
                    <div class="bill_img_container bg-light card-img-top"></div>
                    <div class="card-body alert-success">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="<c:url value="/"/>" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
            <div class="col-3 " style=" margin-right: 7px;">
                <div class="card" style="width: 18rem;">
                    <div class="bill_img_container bg-light card-img-top"></div>
                    <div class="card-body alert-danger">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="<c:url value="/"/>" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
            <div class="col-3 " style=" margin-right: 7px;">
                <div class="card" style="width: 18rem;">
                    <div class="bill_img_container bg-light card-img-top"></div>
                    <div class="card-body alert-warning">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="<c:url value="/"/>" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
