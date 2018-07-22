
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 22.06.2018
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<jsp:include page="../../../header.jsp"/>
<style type="text/css">
    .error {
        font-family: Verdana, serif;
        font-size: 13px;
        color: red;
    }
</style>
<div class="container-fluid" style="position: relative; min-height: 100vh;">
    <div style="margin-left: 100px; margin-top: 50px; margin-right: 300px;">
        <div class="row" >
            <jsp:include page="../../sidemenu.jsp"/>

            <spring:url value="/admin/createProduct" var="actionUrl"/>

            <div class="col-lg-9 ml-auto">
                <form:form action="${actionUrl}" method="post" modelAttribute="product">

                    <div class="row mb-4">
                        <h4> Creating product</h4> <br>
                    </div>

                    <spring:bind path="name">
                        <div class="row">
                            <label>Product name</label>
                            <form:input path="name" id="name" class="form-control" type="text" placeholder="Write name..."/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="parentId">
                        <div class="row pt-3">
                            <label for="category-id"> Product category </label>
                            <select class="custom-select " name="parentId" id="category-id" >
                                <c:choose>
                                    <c:when test="${product.parentId != 0}">
                                        <option selected value="${product.parentId}">${categories.get(product.parentId)}</option>
                                        <c:forEach var="item" items="${categories}">
                                            <c:if test="${product.parentId != item.key}">
                                                <option value="${item.key}">${item.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.key}">${item.value}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </spring:bind>

                    <div class="row-fluid mt-3 ml-auto">

                        <div class="row" id="attrs">
                            <jsp:include page="innerAttrsList.jsp"/>
                        </div>

                    </div>
                    <div class="row mt-5">
                        <div class="col-11">
                            <a href="<c:url value="/admin/productsOperations"/> " style="float: right; margin-top: 4px"
                               class="btn btn-secondary" role="button">Cancel</a>
                        </div>
                        <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>
                    </div>

                </form:form>
            </div>

        </div>
    </div>

</div>

<div class="container-fluid" style="margin-top: 14rem;">
    <div class="col">
        <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded">
            <jsp:include page="../../../footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>

<script>
    function getAttributesByCategory(val) {
        $.ajax({
            url: "createProduct/categoryAttrs",
            data: {categ_id: val},
            method: 'post',
            success: function (data) {
                $('#attrs').html(data);
            }
        });
    }
    var selectList = $("select#category-id");
    $(document).ready(function () {
        if (${attrsList == null}) {
            getAttributesByCategory(selectList.val());
        }
    });

    selectList.change(function () {
        getAttributesByCategory(selectList.val());
    });
</script>
<script>
    var navUserName = document.querySelector('.user-name');
    navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>