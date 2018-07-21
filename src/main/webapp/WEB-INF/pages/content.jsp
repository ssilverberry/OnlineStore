<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 18.06.2018
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="card-columns text-white">
    <a href="<c:url value="/product"> <c:param name="prod_id" value="1000003"/> </c:url>"
       class="card btn border-0 text-center shadow p-2 rounded cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000003.jpg"/>" alt="macbook" style="width: 100%;">
        <div class="card-body">
             Apple MacBook Pro Retina 13"
        </div>
        <div class="card-footer text-muted border-0">
            $ 1500
        </div>
    </a>
    <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000007"/>
                </c:url>" class="card btn border-0 text-center shadow p-3 rounded cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000007.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            Apple iPhone 10 256Gb Space Gray
        </div>
        <div class="card-footer text-muted border-0">
            $ 1250
        </div>
    </a>
    <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="card btn rounded border-0 text-center shadow p-3 bg-white cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            Apple iPhone 7 128Gb Space Gray
        </div>
        <div class="card-footer text-muted border-0">
            $ 899
        </div>
    </a>
    <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="card btn border-0 text-center shadow p-3 bg-white rounded cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
           Iphone 6s plus

        </div>
        <div class="card-footer text-muted border-0">
            $ 669
        </div>
    </a>
    <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000008"/>
                </c:url>" class="card btn border-0 text-center shadow p-3 bg-white rounded cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000008.jpg"/>" alt="macbook" class="cd-img">
        <div class="card-body">
            Iphone 6s plus
        </div>
        <div class="card-footer text-muted border-0">
            $ 715
        </div>
    </a>
    <a href="<c:url value="/product">
                <c:param name="prod_id" value="1000003"/>
                </c:url>" class="card btn border-0 text-center shadow p-3 bg-white rounded cd image-same-size">
        <img src="<c:url value="/resources/images/products/1000003.jpg"/>" alt="macbook" style="width: 100%;">
        <div class="card-body">
           Apple MacBook Pro Retina 13"
        </div>
        <div class="card-footer text-muted border-0">
            $ 1500
        </div>
    </a>
</div>