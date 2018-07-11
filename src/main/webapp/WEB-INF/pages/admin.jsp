<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 23.06.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Admin</title>
<jsp:include page="header.jsp" flush="true" />

<div class="container-fluid" style="min-height: 100vh; position: relative; padding-top: 25px;">
    <div class="row">
        <div class="col-9">
            <div class="row" style="margin-left: 200px">
                <a class="btn btn-light"  role="button"
                   href="<c:url value="/admin/productsOperations"/>">
                    PRODUCTS
                </a>
                <a class="btn btn-light"  role="button"
                   href="<c:url value="/admin/categoriesOperations"/>">
                    CATEGORIES
                </a>
            </div>
        </div>
    </div>
    <div class="row align-items-center justify-content-center bg-light p-2 text-primary bg-secondary rounded"
         style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="footer.jsp"/>
    </div>
</div>

