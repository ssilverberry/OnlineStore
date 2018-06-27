<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 26.06.2018
  Time: 19:05
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/resources/bootstrapjs/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
</head>

<body style="margin-left: 300px; margin-right: 300px; margin-top: 50px ">
    <div class="row pt-3">
        <div class="col=sm-1">
            <a class="btn btn-primary"  role="button"
               href="<c:url value="/admin/createCategoryForm"/> ">
                CREATE NEW CATEGORY
            </a>
        </div>
    </div>
    <div class="form-group pt-3">
        <div class="list-group">
            <label class="form-group">Categories</label>
            <c:forEach var="item" items="${categories}">
                <div class="row">
                    <div class="col-md">
                        <div class="list-group-item">${item.key.name}</div>
                    </div>
                    <div class="col-sm-1">
                        <a class="btn btn-danger"  role="button"
                           href="<c:url value="/admin/deleteCategory"><c:param name="category_id" value="${item.key.id}"/></c:url>">
                            DELETE
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="list-group" style="margin-left: 15px;">
                        <c:forEach var="in_item" items="${item.value}">
                            <div class="list-group-item" style="padding-left: 50px">${in_item.name}</div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</body>

</html>


