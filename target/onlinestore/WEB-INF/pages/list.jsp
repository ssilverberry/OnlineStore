<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 26.05.2018
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="item" items="${productMap}">
    <c:if test="${'products'.equals(item.key)}">
        <c:set var="prodList" value="${item.value}"/>
    </c:if>

    <c:if test="${'subCategs'.equals(item.key)}">
        <c:set var="subCategs" value="${item.value}"/>
    </c:if>
</c:forEach>

<jsp:include page="header.jsp" flush="true" />
<div class="container-fluid" style="padding-top: 25px; min-height: 100vh; position: relative;">
    <div class="row container-row">
        <div class="col-2 pt-3">
            <div class="row">
                <jsp:include page="sidebar.jsp"/> <br><br>
            </div>
        </div>
        <div class="col-9">
            <div class="row">
                <div class="card-columns bg-gradient-secondary text-white">
                    <c:forEach var="product" items="${prodList}">
                        <div class="card border-0 text-center shadow p-3 rounded cd image-same-size">
                            <img src="<c:url value="/resources/images/products/${product.id}.jpg"/>" style="width: 80%;">
                            <div class="card-body">
                                <a href="<c:url value="/product">
                                    <c:param name="prod_id" value="${product.id}"/>
                                    </c:url>" class="btn rounded border-bottom m-auto">${product.name}
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded"
         style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="footer.jsp"/>
    </div>
</div>
<!--privet vsem-->