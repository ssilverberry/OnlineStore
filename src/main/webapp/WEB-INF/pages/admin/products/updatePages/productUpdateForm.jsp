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

<jsp:include page="../../../header.jsp"/>

<style type="text/css">
    .error {
        font-family: Verdana, serif;
        font-size: 13px;
        color: red;
    }
</style>
<div class="container-fluid" style="position: relative; min-height: 100vh;">
    <div style="margin-left: 100px; margin-right: 300px; margin-top: 50px;">
        <div class="row">
            <jsp:include page="../../sidemenu.jsp"/>

            <spring:url value="/admin/updateProduct" var="action"/>

            <div class="col-lg-9 ml-auto" >
                <form:form action="${action}" method="post" modelAttribute="product" id="product-form">
                    <div class="row mb-4">
                        <h4>Updating product</h4> <br>
                    </div>

                    <spring:bind path="id">
                        <form:input path="id" id="id" class="form-control" hidden="true"/>
                    </spring:bind>

                    <spring:bind path="name">
                        <div class="row">
                            <label>Product name</label>
                            <form:input path="name" id="name" class="form-control" type="text"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="parentId">
                        <div class="row mt-3">
                            <label>Category</label>
                            <form:select path="parentId" class="custom-select my-1 ml-auto" id="parent-id">
                                <form:options items="${categs}"/>
                            </form:select>
                        </div>
                    </spring:bind>

                    <div class="row mt-3">
                        <div class="col-sm-3">
                            <label>Attributes</label>
                            <c:forEach var="item" items="${attrs}" >
                                <div class="row mt-2 align-items-center">
                                    <input type="text" class="form-control" value="${item.name}" readonly>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="col-sm-9">
                            <label >Parameters</label>
                            <c:forEach var="item" items="${product.params}" varStatus="status">
                                <c:set var="index" value="${status.index}"/>
                                <div class="row mt-2 no-gutters">
                                    <div class="col-10">
                                        <input name="params[${status.index}].value" value="${item.value}" type="text" class="form-control">
                                        <input name="params[${status.index}].attrId" value="${item.attrId}" type="text" class="form-control" hidden>
                                        <input name="params[${status.index}].productId" value="${item.productId}" type="text" class="form-control" hidden>
                                    </div>
                                    <div class="col-2 align-self-center pl-2">
                                        <form:errors path="params[${index}].value" cssClass="error "/>
                                    </div>
                                </div>

                            </c:forEach>
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
        <div class="row align-content-center justify-content-center p-2 text-primary rounded">
            <jsp:include page="../../../footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>
