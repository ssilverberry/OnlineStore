<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 26.06.2018
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="../../header.jsp"/>
<div style="margin-left: 100px; margin-right: 200px; margin-top: 50px; position: relative; min-height: 100vh;">

    <div class="row">
        <jsp:include page="../sidemenu.jsp"/>

        <div class="col-md-9">
            <div class="row pt-3">
                <div class="col-sm-1">
                    <a class="btn btn-primary"  role="button"
                       href="<c:url value="/admin/createCategoryForm"/> ">
                        CREATE NEW CATEGORY
                    </a>
                </div>
            </div>
            <div class="form-group pt-3">
                <div class="list-group">
                    <label class="form-group">Categories</label>
                    <c:forEach var="item" items="${categories}">
                        <div class="row">
                            <div class="col-md">
                                <div class="list-group-item"><strong>${item.key.name}</strong></div>
                            </div>
                            <div class="col-sm-1">
                                <a class="btn btn-danger mt-1"  role="button"
                                   href="<c:url value="/admin/deleteCategory"><c:param name="category_id" value="${item.key.id}"/></c:url>">
                                    DELETE
                                </a>
                            </div>
                        </div>

                            <div class="row-fluid ml-3 mt-3 mb-3">
                                    <c:forEach var="in_item" items="${item.value}">
                                        <div class="row">

                                            <div class="col-md-7 list-group-item ml-5 pt-3 pb-3">
                                                ${in_item.name}
                                            </div>
                                            <div class="col-1 pt-2">
                                                <a class="btn btn-info"  role="button"
                                                   href="<c:url value="/admin/updateSubcategoryForm">
                                                    <c:param name="subcategory_id" value="${in_item.id}"/>
                                                 </c:url>">
                                                    UPDATE
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                            </div>

                    </c:forEach>

                </div>
            </div>
        </div>
    </div>

</div>
<div class="container-fluid" style="position: absolute;">
    <div class="col">
        <div class="row align-content-center justify-content-center bg-light p-2 text-primary bg-secondary rounded">
            <jsp:include page="../../footer.jsp"/>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
</body>
</html>


