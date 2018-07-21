<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 26.06.2018
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body lang="en">
<style type="text/css">
    .error {
        font-family: Verdana, serif;
        font-size: 14px;
        margin-left: 10px;
        color: red;
    }
</style>
<jsp:include page="../../header.jsp"/>

<div style="margin-left: 100px; margin-right: 200px; margin-top: 50px; min-height: 100vh; position: relative;">

    <spring:url value="/admin/createCategory" var="action"/>

    <div class="row">

        <jsp:include page="../sidemenu.jsp"/>

        <div class="col-md-9" >
            <form:form action="${action}" method="post" commandName="category">

                <h4>New data</h4> <br>

                <spring:bind path="name">
                    <div class="form-group ">
                        <label class="control-label">Category name</label>
                        <form:input path="name" id="name" class="form-control" type="text" placeholder="Write name.." />
                        <form:errors path="name" cssClass="error"/>
                    </div>
                </spring:bind>

                <p>Subcategories:</p>
                <a class="btn btn-light" style="float: right" role="button" id="add-input">ADD ONE</a>

                <div class="form-group" id="subcategories-list" >

                    <c:if test="${category.params != null && category.params.size() != 0}">
                        <c:forEach var="item" items="${category.params}" varStatus="status">
                            <p class="form-inline">
                                <c:set var="index" value="${status.index}"/>
                                <input name="params[${status.index}].value" class="form-control mb-2 mr-sm-2 mb-sm-0 item"
                                       placeholder="Write name.." type="text" id="first-item" value="${item.value}" />

                                <c:if test="${index > 0}">
                                    <a class="btn btn-danger icon-delete ${index}" role="button">DELETE</a>
                                </c:if>

                                <form:errors path="params[${index}].value" cssClass="error"/>
                            </p>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="row" style="margin-top: 20px">
                    <div class="col-11">
                        <a href="<c:url value="/admin/categoriesOperations"/> " style="float: right; margin-top: 4px"
                           class="btn btn-secondary" role="button">Cancel</a>
                    </div>
                    <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>
                </div>

            </form:form>
        </div>
    </div>
</div>
<div class="container-fluid" style="position: absolute; bottom: 0; left: 0;">
    <div class="col">
        <div class="row align-content-center justify-content-center p-2 text-primary rounded">
            <jsp:include page="../../footer.jsp"/>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var itemIndex = 0;
        var array = ["params[" + itemIndex +"].value"];
        $('#add-input').click(function() {
            itemIndex++;
            array[itemIndex] = ["params[" + itemIndex +"].value"];
            $('<p class="form-inline">\n' +
                '<input name="' + array[itemIndex] + '" class="form-control mb-2 mr-sm-2 mb-sm-0 subItem" ' +
                '       placeholder="Write name.." />\n' +
                '<a class="btn btn-danger icon-delete ' + itemIndex + '" role="button">DELETE</a>\n' +
                '<form:errors path="name" cssClass="error"/></p>').appendTo('#subcategories-list');
        });

        $('#subcategories-list').on('click', '.icon-delete', (function () {

            var temp = $(this).attr('class').split(' ');
            var removed = array.splice(temp[3], 1);

            for (var i = temp[3]; i < array.length; i++){
                array[i] = ["params[" + i +"].value"];
            }

            $(this).parent().remove();
            itemIndex--;

            var itm = 1;
            $(".subItem").each(function () {
                $(this).attr("name", array[itm]);
                itm++;
            });

        }));
    })
</script>
<script>
    var navUserName = document.querySelector('.user-name');
    navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>
</body>
</html>
