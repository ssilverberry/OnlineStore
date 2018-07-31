<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 18.06.2018
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/script.js"/>"></script>

<c:if test="${categoryList != null}">
    <c:set var="categories" value="${categoryList}" scope="session"/>
</c:if>

<div class="sidebar col">
    <div class="list-group">
        <ul>
            <c:forEach var="item" items="${categories}">
                <li class="dropdown-submenu">
                    <a class="list-group-item list-group-item-action" >
                            ${item.key.name}
                    </a>
                    <ul class="dropdown-menu" >
                        <c:forEach var="subCateg" items="${item.value}">
                            <li>
                                <a tabindex="-1" class="list-group-item list-group-item-action"
                                   href="<c:url value="/categoryProducts">
                                            <c:param name="category_id" value="${subCateg.id}"/>
                                         </c:url>">
                                    <c:out value="${subCateg.name}"/>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


