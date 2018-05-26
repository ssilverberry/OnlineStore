<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 26.05.2018
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content__mainpart__toprates" style="align-items: flex-start; margin-top: 50px; height: auto;">
    <div style="padding-left: 25px;">
        <p style="font-size: 26px; color: #000; margin-bottom: 26px;">Products: </p>
        <ul>
        <c:forEach var="prods" items="${productList}">
            <li style="padding-bottom: 20px;"><a href="<c:url value="/product"><c:param name="prod_id" value="${prods.id}"/></c:url>">${prods.name}</a></li>
        </c:forEach>
        </ul>
    </div>
</div>
