<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 20.06.2018
  Time: 13:45
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

    <title>Registration</title>
</head>
<body>
<div class="text-center">
    <div class="overlay-auth-elem auth-elem " style="background-color: #f5f5f5;">
        <form class="form-signin margin-auto margin-top-5 bg-light" onsubmit="registration()" style="width: 18rem; box-shadow: 0px 0px 5px #c5c5c5; padding: 1rem;">
            <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>

            <label for="inputEmail" class="sr-only ">Email address</label>
            <input type="email" id="inputEmail" class="form-control mb-2 mail" placeholder="Email" required autofocus>

            <label for="inputName" class="sr-only">Username</label>
            <input type="text" id="inputName" class="form-control mb-2 username" placeholder="Name" required autofocus>

            <label for="inputSecName" class="sr-only ">Second Name</label>
            <input type="text" id="inputSecName" class="form-control mb-2 secondname" placeholder="Second name" required autofocus>

            <label for="inputPhone" class="sr-only">Phone</label>
            <input type="number" id="inputPhone" class="form-control mb-2 phone" placeholder="Phone" required>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control mb-2 pswrd" placeholder="Password" required>

            <label for="inputAddress" class="sr-only">Phone</label>
            <input type="text" id="inputAddress" class="form-control mb-2 address" placeholder="Address" required>

            <a class="btn btn-lg btn-success btn-block mb-2" href="<c:url value="/"/>" onsubmit="registration()">Submit</a>
            <div class="justify-content-center return" style="padding-top: 1rem;">
                <a style="cursor: pointer;" href="<c:url value="/"/>">
                    Return to shop
                </a>
            </div>
            <div class="mytext col"><i>debug text</i></div>
        </form>
    </div>
</div>
<script src="<c:url value="/resources/js/registration.js"/>" async></script>
</body>
</html>
