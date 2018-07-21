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
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-reboot.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-grid.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap.min.css"/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/sidebar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/page.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>"/>
</head>
<body>
<nav class="nav justify-content-center custom-nav-shadow bg-dark font-color-white align-items-center">
    <div class="nav-item">
        <a class="nav-link navbar-brand active" href="<c:url value="/"/>">Shop</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="<c:url value="/payDelive"/>">Payment & Delievery</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="<c:url value="/aboutus"/>">About us</a>
    </div>
    <div class="nav-item">
        <a class="nav-link" href="<c:url value="/contacts"/>">Contacts</a>
    </div>
    <div class="nav-item sign-link" style="cursor: pointer;">
        <a class="nav-link user-name" href="<c:url value="/login"/>">Log in <span class="flag">
            <c:if test="${param.model != null}">
            ${param.model}
        </c:if></span></a>
    </div>
</nav>
<script async>
    var navUserName = document.querySelector('.user-name');
    var flag = document.querySelector(".flag");
    if (flag.value != null)
        navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>
