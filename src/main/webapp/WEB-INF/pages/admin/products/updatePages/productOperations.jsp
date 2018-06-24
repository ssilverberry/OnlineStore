<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 24.06.2018
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
</head>
<body style="margin-left: 300px; margin-right: 300px; margin-top: 50px ">
    <div class="row pt-3">
        <div class="col=sm-1">
        </div>
    </div>
    <div class="form-group pt-1">
        <label for="category-id">Category</label>
        <select class="custom-select my-1 mr-sm-2" name="categories" id="category-id" >
                <option selected> Choose a category </option>
            <c:forEach var="item" items="${categories}">
                <option value="${item.key}">${item.value}</option>
            </c:forEach>
        </select>
    </div>

    <div id="prod-list"></div>

    <script>
        $("select#category-id").change(function () {
            $.ajax({
                url: "updateProduct/productList",
                data: {categ_id: $(this).val()},
                method: 'post',
                success: function (data) {
                    $('#prod-list').html(data);
                }
            })
        })
    </script>

</body>
</html>
