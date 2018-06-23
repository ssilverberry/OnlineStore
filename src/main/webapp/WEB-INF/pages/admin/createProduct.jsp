<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
<div>
    <spring:url value="/admin/showAttrs" var="actionUrl"/>

    <form:form style="margin-left: 250px; margin-top: 50px; margin-right: 250px;"
               action="${actionUrl}" method="get" modelAttribute="product">

        <h4>Step 1 of 2: General info</h4> <br>

        <label class="my-1 mr-2" for="parent-id">Category </label> <br>

        <spring:bind path="parentId">
            <div class="form-group">
                <form:select path="parentId" class="custom-select my-1 mr-sm-2" id="parent-id">
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

        <%--<div class="form-group">
                <label>Product parameters</label>

                <c:forEach var="item" items="${attributes}">
                    <div class="form-row">
                        <div class="col pt-1">
                            <input type="text" class="form-control" placeholder="${item.name}" readonly>
                        </div>
                        <spring:bind path="params">
                            <div class="col pt-1">
                                <form:input path="params" type="text" class="form-control" placeholder="enter value..."/>
                            </div>
                        </spring:bind>
                    </div>
                </c:forEach>
         </div>--%>

            <%--<div class="form-group">
                <label>Product parameters</label>

                <c:forEach var="item" items="${attributes}">
                    <div class="form-row">
                        <div class="col pt-1">
                            <input type="text" class="form-control" placeholder="${item.name}" readonly>
                        </div>
                        <spring:bind path="params">
                            <div class="col pt-1">
                                <form:input path="params" type="text" class="form-control" placeholder="enter value..."/>
                            </div>
                        </spring:bind>
                    </div>
                </c:forEach>
            </div>--%>

        <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>

    </form:form>
</div>
</body>
</html>
