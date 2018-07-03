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

<jsp:include page="../header.jsp" flush="true" />

    <spring:url value="/admin/updateProduct" var="action"/>
    <div class="container-fluid">
        <table>
            <tr>
                <td>

                    <form:form style="margin-left: 300px; margin-top: 50px"
                          action="${action}" method="post" modelAttribute="product">

                        <h4>New parameters</h4> <br>

                        <spring:bind path="id">
                            <div class="form-group">
                                <label>Product id</label>
                                <form:input path="id" id="id" class="form-control" readonly="true"/>
                            </div>
                        </spring:bind>

                        <spring:bind path="parentId">
                            <div class="form-group">
                                <label>Parent id</label>
                                <form:input path="parentId" id="parentId" class="form-control" type="text"/>
                            </div>
                        </spring:bind>

                        <spring:bind path="name">
                            <div class="form-group">
                                <label>Product name</label>
                                <form:input path="name" id="name" class="form-control" type="text"/>
                            </div>
                        </spring:bind>

                        <label>Product parameters</label> <br>

                        <div class="form-row">
                            <div class="form-group" >
                                <div class="col">
                                    <c:forEach var="item" items="${attrs}" >
                                        <div class="row pt-1">
                                            <input type="text" class="form-control" value="${item.name}" readonly>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: 10px">
                                <div class="col">
                                    <c:forEach var="item" items="${product.params}" varStatus="status">
                                        <div class="row pt-1">
                                            <input name="params[${status.index}].value" type="text" class="form-control">
                                            <input name="params[${status.index}].attrId" value="${item.attrId}" type="text" class="form-control" hidden>
                                            <input name="params[${status.index}].productId" value="${item.productId}" type="text" class="form-control" hidden>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button><br><br>

                    </form:form>
                </td>
                <td>
                    <form style="margin-left: 100px; margin-top: 50px">
                        <h4>Current parameters</h4> <br>

                        <div class="form-group">
                            <label>Product id</label>
                            <input class="form-control" readonly/>
                        </div>

                        <div class="form-group">
                            <label>Parent id</label>
                            <input type="text" class="form-control" placeholder="${product.parentId}" readonly>
                        </div>

                        <div class="form-group">
                            <label>Product name</label>
                            <input type="text" class="form-control" placeholder="${product.name}" readonly>
                        </div>

                        <label>Product parameters</label> <br>
                        <div class="form-row">
                            <div class="form-group" >
                                <div class="col">
                                    <c:forEach var="item" items="${attrs}" >
                                        <div class="row pt-1">
                                            <input type="text" class="form-control" value="${item.name}" readonly>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: 10px">
                                <div class="col">
                                    <c:forEach var="item" items="${product.params}" >
                                        <div class="row pt-1">
                                            <input type="text" class="form-control" value="${item.value}" readonly>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                </td>
            </tr>
        </table>
        <div class="row align-content-center justify-content-center p-2 text-primary rounded">
            <div class="col-9">
                <div class="row">
                    <jsp:include page="../footer.jsp"/>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
