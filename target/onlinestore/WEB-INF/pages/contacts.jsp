<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 16.05.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" flush="true" />

<div class="container-fluid" style="padding-top: 25px; min-height: 100vh; position: relative;">
    <div class="row container-row justify-content-center">
        <div class="col-9">
            <div class="row">
                <div class="container">
                    <div class="jumbotron">
                        <h1 class="display-4">Our contacts</h1>
                        <p class="lead">
                            <span class="text-dark">We are glad to talk with everybody.</span><br>
                        </p>
                        <hr class="my-4">
                        <h3>
                            Call: (+38) 068 123 456 9<br>
                            Mail: ncloft@gmail.com<br>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded"
         style="position: absolute;bottom: 0px; width: 100%;">
        <jsp:include page="footer.jsp"/>
    </div>
</div>

