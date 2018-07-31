<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 02.07.2018
  Time: 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-reboot.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-grid.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap.min.css"/>">
    <title>Title</title>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center pt-5">
            <div class="col-5 mt-5 pt-5">
                <div class="rounded bg-success text-center mt-5">
                    <h2 class="p-5">Success. Our shop assistant will call you back soon.</h2>
                    <p class="p-1">You will redirect back to main page after 5s.</p>
                </div>
            </div>
        </div>
    </div>
    <script>
        setTimeout(function() {
            window.location.replace("./");
        }, 5000);
    </script>
</body>
</html>
