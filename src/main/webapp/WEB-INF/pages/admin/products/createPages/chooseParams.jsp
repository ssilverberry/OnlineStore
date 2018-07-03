<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 22.06.2018
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<jsp:include page="../../../header.jsp" />

    <spring:url value="/admin/addProduct" var="actionUrl"/>

    <form:form action="${actionUrl}" class="form-inline" method="post" modelAttribute="params">

        <label>Product parameters</label> <br>

        <div class="form-group">
            <div class="col">Attr_name</div>
            <div class="col">Attr_id</div>
            <div class="col">Prod_id</div>
        </div>

            <c:forEach var="attr" varStatus="status" items="${attributes}">
                <div class="form-row">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="${attr.name}" readonly>
                    </div>
                    <div class="col">
                        <input name="params[${status.index}].value" type="text" class="form-control" placeholder="write parameter">
                    </div>
                    <div class="col">
                        <input name="attributes[${status.index}].attrId" type="text" class="form-control" value="${attr.attrId}" hidden>
                    </div>
                    <div class="col">
                        <input type="attributes[${status.index}].productId" id="prod_id" class="form-control" value="${attr.productId}" hidden>
                    </div>
                </div>
            </c:forEach>

        <button type="submit" class="btn btn-primary my-1">Next</button>
    </form:form>
<div class="container-fluid" style="margin-top: 14rem;">
    <div class="col">
        <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded">
            <jsp:include page="../../../footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>
