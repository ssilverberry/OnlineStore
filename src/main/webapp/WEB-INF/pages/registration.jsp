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
    <style>
        .font-size-12 {
            font-size: 12px;
        }
    </style>
</head>
<body>
<spring:url value="addUser" var="actionUrl"/>

<div class="container ">
    <div class="row justify-content-center" style="margin-top: 5rem;">
    <div class="col-4">
        <form:form method="post" action="${actionUrl}" modelAttribute="registrationForm"
                   cssClass="form-signin bg-light"
                   cssStyle="width: 18rem; box-shadow: 0px 0px 5px #c5c5c5; padding: 1.5rem; margin: 0 auto;">
            <h1 class="h3 mb-3 font-weight-normal text-center">Sign Up</h1>
            <div class="row">
            <spring:bind path="email">
                <label for="inputEmail" class="sr-only ">Email address</label>
                <form:input path="email" type="email" id="inputEmail" class="form-control mail email m-1" placeholder="Email"
                        required=""
                        autofocus=""/>
                <form:errors path="email" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <div class="row">
            <spring:bind path="name">
                <label for="inputName" class="sr-only">Username</label>
                <form:input path="name" type="text" id="inputName" class="form-control name m-1" placeholder="Name"
                        required="" autofocus="" />
                <form:errors path="name" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <div class="row">
            <spring:bind path="surname">
                <label for="inputSecName" class="sr-only ">Second Name</label>
                <form:input path="surname" type="text" id="inputSecName" class="form-control surname m-1"
                        placeholder="Surname"
                        required=""
                        autofocus="" />
               <form:errors path="surname" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <div class="row">
            <spring:bind path="phone">
                <label for="inputPhone" class="sr-only">Phone</label>
                <form:input path="phone" type="text" id="inputPhone" class="form-control phone m-1"
                            placeholder="Phone"
                            required=""/>
                <form:errors path="phone" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <div class="row">
            <spring:bind path="password">
                <label for="inputPassword" class="sr-only">Password</label>
                <form:input path="password" type="password" id="inputPassword" class="form-control password m-1"
                            placeholder="Password"
                            required=""/>
                <form:errors path="password" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <div class="row">
            <spring:bind path="address">
                <label for="inputAddress" class="sr-only">Address</label>
                <form:input path="address" type="text" id="inputAddress" class="form-control address m-1"
                            placeholder="Address"
                            required=""/>
                <form:errors path="address" cssClass="text-danger font-size-12 pl-2"/>
            </spring:bind>
            </div>
            <form:button class="btn btn-lg btn-success btn-block my-2">Submit</form:button>

            <div class="justify-content-center return my-1">
                <a class="btn btn-outline-secondary btn-block" style="cursor: pointer;" href="<c:url value="/"/>">
                    Return to shop
                </a>
            </div>
        </form:form>
    </div>
    </div>
</div>
</body>
</html>
