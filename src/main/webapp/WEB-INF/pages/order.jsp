<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 28.05.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/product.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/content.css" />">
    <title>Order</title>
</head>
<body class="bg-light">
<jsp:include page="header.jsp"/>
<h1 class="col display-4 bg-dark text-white text-center p-3">
    Billing
</h1>
<spring:url value="success" var="actionUrl"/>
<c:forEach var="i" items="${product.parameters}">
    <c:if test="${'Price'.equals(i.key.name)}">
        <c:set var="price" value="${i.value.value}"/>
    </c:if>
</c:forEach>
<div class="container" style="position: relative; min-height: 100vh;">
    <div class="row justify-content-center mb-4">
        <div class="col-3 m-2">
            <div class="p-2 shadow rounded">
                <img src="<c:url value="/resources/images/products/${product.id}.jpg"/>"/>
            </div>
        </div>
        <!---->
        <div class="col-4 justify-content-center ml-4 my-2">
            <div class="shadow p-3">
                <form:form class="needs-validation" method="get" action="${actionUrl}" modelAttribute="saveOrderForm">
                    <div>
                        <label for="email2">Email address</label>
                        <input type="email" class="form-control" id="email2" aria-describedby="emailHelp" placeholder="Enter email" required>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div>
                        <label for="fname">First Name</label>
                        <input type="text" class="form-control" id="fname" placeholder="Enter firstname" required>
                    </div>
                    <div >
                        <label for="lname">Last  Name</label>
                        <input type="text" class="form-control" id="lname" placeholder="Enter lastname" required>
                    </div>
                    <div>
                        <label for="phone">Phone</label>
                        <input type="text" class="form-control" id="phone" placeholder="Enter phone" required>
                    </div>
                    <hr class="mb-4">
                    <div class="row justify-content-center">
                        <form:button type="submit" class="btn btn-block btn-outline-primary col-3">Confirm</form:button>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="col-4 m-2">
            <div class="shadow p-3">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">${product.name}</span>
                <span class="badge badge-secondary badge-pill">1</span>
            </h4>
            <ul class="list-group mb-3">
            <%--<c:forEach var="item" items="${product.parameters}">
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">${item.key.name}</h6>
                        <small class="text-muted">${item.value.value}</small>
                    </div>
                </li>
            </c:forEach>--%>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$${price}</strong>
                </li>
            </ul>
            </div>
        </div>
    </div>

</div>
<div class="row p-2 bg-light rounded justify-content-center align-items-center text-primary" style="position: absolute; width: 100%;">
    <jsp:include page="footer.jsp" flush="true" />
</div>
<script>
    var navUserName = document.querySelector('.user-name');
    navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';

        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');

            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>
</html>
<!--privet vsem-->