<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 24.06.2018
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<div class="col">

    <div class="form-row">

            <div class="col-4">
                <label>Attributes</label>
                <c:forEach var="item" items="${attrsList}" varStatus="status">
                    <div class="row pt-1">
                        <input value="${item.name}" type="text" class="form-control" readonly>
                    </div>
                </c:forEach>
            </div>

        <div class="form-group" style="margin-left: 10px">
            <div class="col-12">
                <label>Parameters</label>
                <c:forEach var="item" items="${product.params}" varStatus="status">
                    <div class="row pt-1">
                        <input name="params[${status.index}].value" placeholder="Write parameter..." type="text" class="form-control">
                        <input name="params[${status.index}].attrId"  value="${item.attrId}" type="text" class="form-control" hidden>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


</div>
