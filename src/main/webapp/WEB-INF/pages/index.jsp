<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>"/>
  <title>NC Loft</title>
</head>

<c:if test="${!user.equals(null)}">
    <c:set var="myRequestModel" value="${user}" scope="session" />
</c:if>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="model" value="${user.name}"/>
</jsp:include>

<div class="container-fluid" style="padding-top: 25px; position: relative; min-height: 100vh;">
    <div class="row">
        <div class="col-2 pt-3">
            <div class="row">
                <jsp:include page="sidebar.jsp"/>
            </div>
        </div>
        <div class="col-9 ml-4">
            <div class="row">
                <jsp:include page="content.jsp"/>
            </div>
        </div>
    </div>
</div>
<div class="row align-items-center justify-content-center bg-light p-2 text-primary bg-secondary rounded"
     style="position: absolute; width: 100%;">
    <jsp:include page="footer.jsp"/>
</div>
<script>
    var foo = '${user.name}';
    localStorage.setItem("username", foo);
</script>
<script>
    var navUserName = document.querySelector('.user-name');
    navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>
</body>
</html>
