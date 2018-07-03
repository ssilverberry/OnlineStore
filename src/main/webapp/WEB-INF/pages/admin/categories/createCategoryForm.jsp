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
        color: red;
    }
</style>
<jsp:include page="../../header.jsp"/>
<div style="margin-left: 400px; margin-right: 400px; margin-top: 50px; min-height: 100vh; position: relative;">

    <spring:url value="/admin/createCategory" var="action"/>

        <form:form action="${action}" method="post" commandName="category">

            <h4>New data</h4> <br>

            <spring:bind path="name">
                <div class="form-group ">
                    <label class="control-label">Category name</label>
                    <form:input path="name" id="name" class="form-control" type="text"/>
                    <form:errors path="name" cssClass="error"/>
                </div>
            </spring:bind>

                <p>Subcategories:</p>
                <div class="form-group" id="subcategories-list" >
                    <p class="form-inline">
                        <input name="params[0].value" class="form-control mb-2 mr-sm-2 mb-sm-0 item" type="text" id="first-item"
                               placeholder="Write name.."/>
                        <a class="btn btn-light" role="button" id="add-input">ADD ONE</a>
                        <form:errors path="name" cssClass="error"/>
                    </p>
                </div>

            <button type="submit" class="btn btn-primary my-1" style="float: right">Next</button>
        </form:form>
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
</body>
</html>
