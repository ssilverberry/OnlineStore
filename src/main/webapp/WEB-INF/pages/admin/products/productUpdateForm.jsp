<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 21.06.2018
  Time: 13:50
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

<body>
    <spring:url value="/admin/updateProduct" var="action"/>
    <div>
        <form:form style="margin-left: 400px; margin-right: 400px; margin-top: 50;"
              action="${action}" method="post" modelAttribute="product" id="product-form">

            <h4>New data</h4> <br>

            <spring:bind path="id">
                <form:input path="id" id="id" class="form-control" hidden="true"/>
            </spring:bind>

            <spring:bind path="name">
                <div class="form-group">
                    <label>Product name</label>
                    <form:input path="name" id="name" class="form-control" type="text"/>
                </div>
            </spring:bind>

            <spring:bind path="parentId">
                <div class="form-group">
                    <%--<label>Category</label>--%>
                    <a href="<c:url value="/admin/changeProduct"/>">Category</a>
                    <form:select path="parentId" class="custom-select my-1 mr-sm-2" id="parent-id">
                        <form:options items="${categs}"/>
                    </form:select>
                </div>
            </spring:bind>

            <div class="form-row" style="">
                <div class="form-group" >
                    <div class="col">
                        <label>Attributes</label>
                        <c:forEach var="item" items="${attrs}" >
                            <div class="row pt-1">
                                <input type="text" class="form-control" value="${item.name}" readonly>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <div class="col">
                        <label style="align-content: center">Parameters</label>
                        <c:forEach var="item" items="${product.params}" varStatus="status">
                            <div class="row pt-1">
                                <input name="params[${status.index}].value" value="${item.value}" placeholder="${item.value}" type="text" class="form-control">
                                <input name="params[${status.index}].attrId" value="${item.attrId}" type="text" class="form-control" hidden>
                                <input name="params[${status.index}].productId" value="${item.productId}" type="text" class="form-control" hidden>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button><br><br>
        </form:form>

    </div>

</body>
</html>
