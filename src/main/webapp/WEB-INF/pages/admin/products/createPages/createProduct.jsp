<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 22.06.2018
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
        <title>Title</title>
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
        <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>

</head>
<body style="margin-left: 300px; margin-top: 50px; margin-right: 300px;">
<div>
    <spring:url value="/admin/createProduct" var="actionUrl"/>

    <form:form action="${actionUrl}" method="get" modelAttribute="product">

        <h4>New data</h4> <br>

        <label class="my-1 mr-2" for="category-id">Category </label> <br>

        <spring:bind path="parentId">
            <div class="form-group">
                <form:select path="parentId" class="custom-select my-1 mr-sm-2" id="category-id">
                    <form:options items="${categories}"/>
                </form:select>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group">
                <label class="my-1 mr-2" for="prod-name">Product name</label> <br>
                <input name="product.name" id="prod-name" class="form-control" type="text" />
            </div>
        </spring:bind> <br>


            <div id="attrsParams"> </div>

        <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>

    </form:form>
</div>


</body>
</html>

<script>
    $("select#category-id").change(function () {
        $.ajax({
            url: "createProduct/categoryAttrs",
            data: {categ_id: $(this).val()},
            method: 'post',
            success: function (data) {
                $('#attrsParams').html(data);
            }
        })
    })
</script>
