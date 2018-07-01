<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 20.06.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <title>Registration</title>
</head>
<body>
<spring:url value="/userin" var="actionUrl"/>
<div class="text-center" style="width: 100%; height: 100%;">
    <div class="overlay-auth-elem auth-elem container-fluid">
        <div class="row justify-content-center">
            <div class="col-6">
        <form:form method="post" action="${actionUrl}" modelAttribute="authForm"
                cssClass="form-signin margin-auto margin-top-5 bg-light"
                cssStyle="width: 18rem; box-shadow: 0px 0px 5px #c5c5c5; padding: 1rem;">

            <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>

            <spring:bind path="email">
                <label for="inputEmail" class="sr-only">Email address</label>
                <form:input path="email" type="email" id="inputEmail" class="form-control email mb-2"
                        placeholder="Email address" required="" autofocus=""/>
                <form:errors path="email" cssClass="col text-danger"/>
            </spring:bind>

            <spring:bind path="password">
                <label for="inputPassword" class="sr-only">Password</label>
                <form:input path="password" type="password" id="inputPassword" class="form-control pswrd mb-2"
                            placeholder="Password" required="" />
                <form:errors path="password" cssClass="col text-danger"/>
            </spring:bind>

            <form:button class="btn btn-lg btn-success btn-block">Sign in</form:button>
            <a href="<c:url value="/signup" />" class="btn btn-lg btn-dark btn-block">Sign up</a>
            <div class="justify-content-center return" style="padding-top: 1rem;">
                <a style="cursor: pointer;" href="<c:url value="/" />">
                    Return to shop
                </a>
            </div>
            <div class="col bg-success text-white text-center info" style="transition: all 0.3s;">

            </div>
        </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>