<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 21.06.2018
  Time: 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="cartbar bg-dark rounded">
    <div class="col cartText text-primary pb-1">
        your cart <span class="badge badge-primary">3</span>
    </div>
</div>
<div class="col-3 order-md-2 mb-4 cartlist shadow-lg" style="background-color: #ffffff !important;">
    <h4 class="d-flex justify-content-between align-items-center mb-3">
        <span class="text-muted">Your cart</span>
        <span class="badge badge-secondary badge-pill">3</span>
    </h4>
    <ul class="list-group mb-3">
        <li class="list-group-item d-flex justify-content-between lh-condensed">
            <div>
                <h6 class="my-0">Product name</h6>
                <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$12</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
            <div>
                <h6 class="my-0">Second product</h6>
                <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$8</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
            <div>
                <h6 class="my-0">Third item</h6>
                <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$5</span>
        </li>
        <li class="list-group-item d-flex justify-content-between bg-light">
            <div class="text-success">
                <h6 class="my-0">Promo code</h6>
                <small>EXAMPLECODE</small>
            </div>
            <span class="text-success">-$5</span>
        </li>
        <li class="list-group-item d-flex justify-content-between">
            <span>Total (USD)</span>
            <strong>$20</strong>
        </li>
        <li class="list-group-item d-flex justify-content-between">
            <a href="order" class="btn btn-success btn-block">Pay</a>
        </li>
    </ul>

</div>
<style>
    body {
        overflow-x: hidden;
    }
    .cartbar {
        position: absolute;
        right: -40px;
        top: 20rem;
        transform: rotate(-90deg);
        transform-origin: 50% 50%;
        cursor: pointer;
        z-index: 5;
    }
    .cartText {
        font-size: 18px;
    }
    .cartlist {
        visibility: hidden;
        position: absolute;
        right: -350px;
        top: 12rem;
        transition: all 1s;
        z-index: 5;
    }
    .moveleft {
        visibility: visible;
        right: -15px;
        transition: all 1s;
        z-index: 5;
    }

</style>
<script>
    var cartElem = document.querySelector('.cartbar');

    cartElem.addEventListener('click', showCartList());

    function showCartList () {
        var cartListElem = document.querySelector('.cartlist');
        var cartbar = document.querySelector('.cartbar');

        cartbar.addEventListener('click', function() {
            cartListElem.classList.add('moveleft');
            console.log('click');
            cartListElem.addEventListener('mouseleave', function() {
                setTimeout(function() {
                    cartListElem.classList.remove('moveleft');
                    console.log('move right');
                }, 3000);
            });

        });
    }
</script>
