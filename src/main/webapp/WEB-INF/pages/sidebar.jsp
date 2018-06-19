<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 18.06.2018
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/script.js"/>"></script>
<div class="sidebar col">
    <div class="list-group">
        <c:forEach var="category" items="${categoryList}">
            <%--/*getCategoriesList(${category.id})*/--%>
            <a class="list-group-item list-group-item-action" href="productCategoriesId?category_id=${category.id}">
                    ${category.name}
            </a>
        </c:forEach>
    </div>
</div>