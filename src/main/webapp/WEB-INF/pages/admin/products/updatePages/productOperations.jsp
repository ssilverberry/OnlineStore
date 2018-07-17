<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 24.06.2018
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="../../../header.jsp"/>
<div class="container-fluid" style="position: relative; min-height: 100vh;">
<div style="margin-left: 100px; margin-right: 200px; margin-top: 50px ">
    <div class="row">

        <jsp:include page="../../sidemenu.jsp"/>

        <div class="col-md-9">
            <div class="form-group pt-1">
                <div class="row">
                    <div class="col-sm-1">
                        <a class="btn btn-primary mb-2"  role="button"
                           href="#">
                            CREATE NEW PRODUCT
                        </a>
                    </div>
                </div>
                <div class="row pt-3 ml-auto">
                    <label for="category-id"> Selecting products by the category </label>
                    <select class="custom-select my-1 mr-sm-2" name="categories" id="category-id" >
                        <option selected> Choose a category </option>
                        <c:forEach var="item" items="${categories}">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div id="prod-list"></div>
        </div>
    </div>

    <script>
        $("select#category-id").change(function () {
            $.ajax({
                url: "updateProduct/productList",
                data: {categ_id: $(this).val()},
                method: 'post',
                success: function (data) {
                    $('#prod-list').html(data);
                }
            })
        })
    </script>
</div>
    <div class="col" style="position: absolute; bottom: 0; left: 0;">
        <div class="row align-content-center justify-content-center p-2 text-primary">
            <jsp:include page="../../../footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>
