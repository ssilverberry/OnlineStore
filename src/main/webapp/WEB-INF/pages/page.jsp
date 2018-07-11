<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 03.06.2018
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" flush="true" />

<div class="container-fluid" style="padding-top: 25px;">
    <div class="row">
        <div class="col-2 pt-3">
            <div class="row">
                <jsp:include page="sidebar.jsp"/>
            </div>
        </div>
        <div class="col-9">
            <div class="row">
                <jsp:include page="content.jsp"/>
            </div>
        </div>
    </div>
    <div class="row align-items-center justify-content-center bg-light p-2 text-primary bg-secondary rounded">
        <jsp:include page="footer.jsp"/>
    </div>
</div>

