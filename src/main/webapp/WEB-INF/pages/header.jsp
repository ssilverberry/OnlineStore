<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 03.06.2018
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-reboot.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-grid.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/sidebar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/page.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>

</head>
<body>
<jsp:include page="authorize.jsp"/>
<nav class="nav justify-content-center custom-nav-shadow bg-dark font-color-white">
    <div class="nav-item align-self-center">
        <a class="nav-link navbar-brand active" href="<c:url value="/"/>">Shop</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="payDelive">Payment & Delievery</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="aboutus">About us</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="contacts">Contacts</a>
    </div>
    <div class="nav-item sign-link" style="cursor: pointer;">
        <div class="nav-link user-name">Sign in</div>
    </div>
</nav>
<script src="<c:url value="/resources/js/header.js"/>" async></script>
