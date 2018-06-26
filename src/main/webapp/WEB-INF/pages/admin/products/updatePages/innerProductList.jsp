<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 24.06.2018
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="list-group">
    <c:forEach var="item" items="${productList}">
        <div class="row">
            <div class="col-md">
                <a class="list-group-item list-group-item-action"
                   href="<c:url value="/admin/products" > <c:param name="prod_id" value="${item.id}"/> </c:url>">
                        ${item.name}
                </a>
            </div>
            <div class="col=sm-1">
                <a class="btn btn-primary"  role="button"
                   href="<c:url value="/admin/showUpdateForm"><c:param name="prod_id" value="${item.id}"/></c:url>">
                    UPDATE
                </a>
            </div>
            <div class="col-sm-1">
                <a class="btn btn-danger"  role="button"
                   href="<c:url value="/admin/deleteProduct"><c:param name="prod_id" value="${item.id}"/></c:url>">
                    DELETE
                </a>
            </div>
        </div>
    </c:forEach>
</div>
