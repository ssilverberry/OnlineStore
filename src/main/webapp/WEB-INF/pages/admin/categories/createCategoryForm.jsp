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

<body lang="ru">
<style type="text/css">
    .error {
        font-family: Verdana, serif;
        font-size: 14px;
        margin-left: 10px;
        color: red;
    }
</style>
<jsp:include page="../../header.jsp"/>

<div style="margin-left: 100px; margin-right: 300px; margin-top: 50px; min-height: 100vh; position: relative;">

    <spring:url value="/admin/createCategory" var="action"/>

    <div class="row">

        <jsp:include page="../sidemenu.jsp"/>

        <div class="col-md-9" >
            <form:form action="${action}" method="post" commandName="category">

                <h4>Creating category</h4> <br>

                <spring:bind path="categoryName">
                    <div class="form-group ">
                        <label class="control-label">Category</label>
                        <form:input path="categoryName" id="name" class="form-control" type="text" placeholder="Write name.." />
                        <form:errors path="categoryName" cssClass="error"/>
                    </div>
                </spring:bind>

                <spring:bind path="subcategoryName">
                    <div class="form-group ">
                        <label class="control-label">Subcategory</label>
                        <form:input path="subcategoryName" id="name" class="form-control" type="text" placeholder="Write name.." />
                        <form:errors path="subcategoryName" cssClass="error"/>
                    </div>
                </spring:bind>

                <p>Attributes:</p>
                <a class="btn btn-light" style="float: right" role="button" id="add-input">ADD ONE</a>

                <div class="form-group" id="subcategories-list" >

                    <c:if test="${category.attributes != null && category.attributes.size() != 0}">
                        <c:forEach var="item" items="${category.attributes}" varStatus="status">
                            <p class="form-inline">
                                <c:set var="index" value="${status.index}"/>
                                <input name="attributes[${status.index}].name" class="form-control mb-2 mr-sm-2 mb-sm-0 item"
                                       placeholder="Write name.." type="text" value="${item.name}" />

                                <c:if test="${index > 0}">
                                    <a class="btn btn-danger icon-delete ${index}" role="button">DELETE</a>
                                </c:if>

                                <form:errors path="attributes[${index}].name" cssClass="error attr"/>
                            </p>
                        </c:forEach>
                    </c:if>
                </div>

                <spring:bind path="update">
                    <form:input path="update" class="form-control" type="text" value="${false}" hidden="true"/>
                </spring:bind>

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
    function updateList(array) {
        var temp = 0;
        $(".item").each(function () {
            array[temp] = ["attributes[" + temp +"].name"];
            temp++;
        });
    }

    $(document).ready(function () {
        var array = [];

        updateList(array);

        var itemIndex = ${category.attributes.size()-1};

        $('#add-input').click(function() {
            itemIndex++;
            array[itemIndex] = ["attributes[" + itemIndex +"].name"];
            $('<p class="form-inline">\n' +
                '<input name="' + array[itemIndex] + '" class="form-control mb-2 mr-sm-2 mb-sm-0 item" ' +
                '       placeholder="Write name.." />\n' +
                '<a class="btn btn-danger icon-delete ' + itemIndex + '" role="button">DELETE</a>\n' +
                '</p>').appendTo('#subcategories-list');
        });

        $('#subcategories-list').on('click', '.icon-delete', (function () {

            var temp = $(this).attr('class').split(' ');
            var removed = array.splice(temp[3], 1);

            for (var i = 0; i < array.length; i++){
                array[i] = ["attributes[" + i +"].name"];
            }

            $(this).parent().remove();
            itemIndex--;

            var itm = 0;
            $(".item").each(function () {
                $(this).attr("name", array[itm]);
                itm++;
            });
            itm = 1;
            $(".icon-delete").each(function () {
                $(this).attr("class", "btn btn-danger icon-delete " + itm);
                itm++;
            });

            itm = 0;
            $(".attr").each(function () {
                $(this).attr("id", "attributes" + itm + ".name.errors");
                itm++;
            })

        }));
    });
</script>
<script>
    var navUserName = document.querySelector('.user-name');
    navUserName.innerHTML = 'Log in ' + localStorage.getItem('username');
</script>
</body>
</html>
