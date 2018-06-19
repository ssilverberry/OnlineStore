<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 18.06.2018
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-columns bg-gradient-secondary text-white">
    <div class="card border-0 text-center shadow p-3 rounded cd">
        <img src="<c:url value="/resources/images/products/1000003.jpg"/>" alt="macbook" style="width: 100%;">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000003"/>
                </c:url>" class="btn rounded border-bottom m-auto">Apple MacBook Pro Retina 13"
            </a>
        </div>
    </div>
    <div class="card border-0 text-center shadow p-3 rounded cd">
        <img src="<c:url value="/resources/images/products/1000007.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000007"/>
                </c:url>" class="btn rounded border-bottom m-auto">Apple iPhone 10 256Gb Space Gray
            </a>
        </div>
    </div>
    <div class="card border-0 text-center shadow p-3 bg-white rounded cd">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="btn rounded border-bottom m-auto">Apple iPhone 7 128Gb Space Gray
            </a>
        </div>
    </div>
    <div class="card border-0 text-center shadow p-3 bg-white rounded cd">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="btn rounded border-bottom m-auto">Iphone 6s plus
            </a>
        </div>
    </div>
    <div class="card border-0 text-center shadow p-3 bg-white rounded cd">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="btn rounded border-bottom m-auto">Iphone 6s plus
            </a>
        </div>
    </div>
    <div class="card border-0 text-center shadow p-3 bg-white rounded cd">
        <img src="<c:url value="/resources/images/products/1000003.jpg"/>" alt="macbook" style="width: 100%;">
        <div class="card-body">
            <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000003"/>
                </c:url>" class="btn rounded border-bottom m-auto">Apple MacBook Pro Retina 13"
            </a>
        </div>
    </div>
</div>
