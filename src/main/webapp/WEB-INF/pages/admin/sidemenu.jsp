<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 17.07.2018
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="col-sm-2 mr-5">

    <div class="row ">
        <a class="btn btn-light pb-1"  role="button" href="<c:url value="/admin/mainPage"/>">
            HOME
        </a>
    </div>
    <div class="row">
        <a class="btn btn-light pb-1"  role="button" href="<c:url value="/admin/productsOperations"/>">
            PRODUCTS
        </a><br>
    </div>
    <div class="row">
        <a class="btn btn-light pb-1"  role="button" href="<c:url value="/admin/categoriesOperations"/>">
            CATEGORIES
        </a>
    </div>
</nav>

