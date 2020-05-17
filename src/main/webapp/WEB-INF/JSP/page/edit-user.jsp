<%@ page import="epam.my.project.configuration.SecurityConfiguration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="adminRole" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>
<c:set var="userRole" value="<%=SecurityConfiguration.ROLE_USER%>"/>

<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${user.imageLink}" alt="${user.account.name}">
            <c:if test="${userRole}">
                <div class="d-flex justify-content-center">
                    <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                        Change image
                    </button>
                </div>
            </c:if>
        </div>
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${user.account.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" >
                <div class="badge badge-pill badge-warning raiting-number">${user.rating}</div>
            </div>
            <p>
            <form class="col-9" method="POST">
                <h5 class="d-flex justify-content-center">Edit user</h5>
                <c:if test="${userRole}">
                    <div class="form-group">
                        <label for="inputName">Name</label>
                        <input type="text" name="name" value="${user.account.name}" class="form-control" id="inputName" placeholder="Enter a name">
                    </div>
                    <input type="hidden" name="imageLink" value="${IMAGE_LINK ? IMAGE_LINK : user.imageLink}">
                </c:if>
                <c:if test="${adminRole}">
                    <div class="form-group">
                        <label for="inputRating">Rating</label>
                        <input type="text" name="rating" value="${user.rating}" class="form-control" id="inputRating" placeholder="Enter an rating">
                    </div>
                    <div class="form-group custom-control custom-switch">
                        <input type="checkbox" name="isBanned" class="custom-control-input" id="isBannedSwitch" <c:if test="${user.banned}">checked</c:if>>
                        <label class="custom-control-label" for="isBannedSwitch">Is banned user</label>
                    </div>
                </c:if>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3">Save</button>
                    <a href="#" type="submit" class="btn btn-dark px-4 mx-3">Back</a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
