<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 03.06.2018
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="text-center" style="/*width: 100%; height: 100%;*/ visibility: hidden;">
    <div class="overlay-auth-elem auth-elem " style="background-color: #f5f5f5;">
        <form class="form-signin margin-auto margin-top-5 bg-light" style="width: 18rem; box-shadow: 0px 0px 5px #c5c5c5; padding: 1rem;" onsubmit="authorize()">
            <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control email" placeholder="Email address" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control pswrd" placeholder="Password" required>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-success btn-block" type="submit">Sign in</button>
            <a href="<c:url value="signup" />" class="btn btn-lg btn-dark btn-block">Sign up</a>
            <div class="justify-content-center return" style="padding-top: 1rem;">
                <a style="cursor: pointer;">
                    Return to shop
                </a>
            </div>
            <div class="col bg-success text-white text-center info" style="visibility: hidden; transition: all 0.3s;">

            </div>
        </form>
    </div>
</div>
<script src="<c:url value="/resources/js/authorize.js"/>" async></script>
