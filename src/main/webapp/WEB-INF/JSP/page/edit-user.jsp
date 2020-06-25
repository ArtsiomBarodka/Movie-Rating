<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.my.project.configuration.SecurityConfiguration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="adminRole" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>
<c:set var="userRole" value="<%=SecurityConfiguration.ROLE_USER%>"/>
<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${USER.imageLink}" alt="${USER.account.name}">
            <c:if test="${userRole.equalsIgnoreCase(CURRENT_ACCOUNT_DETAILS.role)}">
                <div class="d-flex justify-content-center">
                    <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                        <fmt:message key="user.image.chang"/>
                    </button>
                </div>
            </c:if>
        </div>
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${USER.account.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" >
                <div class="badge badge-pill badge-warning raiting-number">${USER.rating}</div>
            </div>
            <p>
            <form class="col-9" method="POST" action="/app/user/edit/save/${USER.uid}">
                <h5 class="d-flex justify-content-center"><fmt:message key="user.edit"/></h5>
                <tags:message/>
                <input type="hidden" name="id" value="${USER.id}">
                <c:if test="${userRole.equalsIgnoreCase(CURRENT_ACCOUNT_DETAILS.role)}">
                    <div class="form-group">
                        <label for="inputName"><fmt:message key="user.name.label"/></label>
                        <input type="text" name="name" value="${USER.account.name}" class="form-control" id="inputName" placeholder="Enter a name">
                        <tags:validation field="name" violatons="${VIOLATIONS}"/>
                    </div>
                    <input type="hidden" name="rating" value="${USER.rating}">
                    <c:if test="${USER.banned}">
                        <input type="hidden" name="isBanned" value="${USER.banned}">
                    </c:if>
                    <input id="inputImageLink" type="hidden" name="imageLink" value="${USER.imageLink}">
                </c:if>
                <c:if test="${adminRole.equalsIgnoreCase(CURRENT_ACCOUNT_DETAILS.role)}">
                    <div class="form-group">
                        <label for="inputRating"><fmt:message key="user.rating.label"/></label>
                        <input type="text" name="rating" value="${USER.rating}" class="form-control" id="inputRating" placeholder="Enter an rating">
                        <tags:validation field="rating" violatons="${VIOLATIONS}"/>
                    </div>
                    <div class="form-group custom-control custom-switch">
                        <input type="checkbox" name="isBanned" class="custom-control-input" id="isBannedSwitch" <c:if test="${USER.banned}">checked</c:if>>
                        <label class="custom-control-label" for="isBannedSwitch"><fmt:message key="user.banned.label"/></label>
                    </div>
                    <input type="hidden" name="imageLink" value="${USER.imageLink}">
                    <input type="hidden" name="name" value="${USER.account.name}">
                </c:if>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="user.submit"/></button>
                    <a href="/app/user/${USER.uid}" type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="user.back"/></a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
