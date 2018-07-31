<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 24.06.2018
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    .error {
        font-family: Verdana, serif;
        font-size: 13px;
        color: red;
    }
</style>
    <%--<div class="row mt-3">--%>
        <div class="col-3">
            <label>Attributes</label>
            <c:forEach var="item" items="${attrsList}" >
                <div class="row mt-2 align-items-center">
                    <input type="text" class="form-control" value="${item.name}" readonly>
                </div>
            </c:forEach>
        </div>

        <div class="col-9">
            <label >Parameters</label>
            <c:forEach var="item" items="${product.params}" varStatus="status">
                <c:set var="index" value="${status.index}"/>
                <div class="row mt-2 no-gutters">
                    <div class="col-10">
                        <input name="params[${status.index}].value" value="${item.value}" type="text" class="form-control">
                        <input name="params[${status.index}].attrId" value="${item.attrId}" type="text" class="form-control" hidden>
                    </div>
                    <div class="col-2 align-self-center pl-2">
                        <form:errors path="params[${index}].value" cssClass="error "/>
                    </div>
                </div>
            </c:forEach>
        </div>

    <%--</div>--%>

