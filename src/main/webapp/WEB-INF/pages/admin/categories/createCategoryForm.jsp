<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 26.06.2018
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-reboot.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap-grid.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapcss/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/authorize.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/sidebar.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/page.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>

    <spring:url value="/admin/createCategory" var="action"/>

        <form:form style="margin-left: 400px; margin-right: 400px; margin-top: 50;"
                   action="${action}" method="post" modelAttribute="category">

            <h4>New data</h4> <br>

            <spring:bind path="name">
                <div class="form-group">
                    <label>Category name</label>
                    <form:input path="name" id="name" class="form-control" type="text"/>
                </div>
            </spring:bind>

            <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>
        </form:form>

</body>
</html>
