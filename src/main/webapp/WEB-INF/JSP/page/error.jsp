<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<main id="error-page" class="container-fluid">
    <div class="jumbotron col m-0">
        <h1 class="display-4">${CODE}</h1>
        <c:choose>
            <c:when test="${CODE == 404}">
                <p class="lead"><fmt:message key="error.title.404"/></p>
                <hr class="my-4">
                <p><fmt:message key="error.message.404"/></p>
            </c:when>
            <c:when test="${CODE == 401}">
                <p class="lead"><fmt:message key="error.title.401"/></p>
                <hr class="my-4">
                <p><fmt:message key="error.message.401"/></p>
            </c:when>
            <c:when test="${CODE == 403}">
                <p class="lead"><fmt:message key="error.title.403"/></p>
                <hr class="my-4">
                <p><fmt:message key="error.message.403"/></p>
            </c:when>
            <c:otherwise>
                <p class="lead"><fmt:message key="error.title.500"/></p>
                <hr class="my-4">
                <p><fmt:message key="error.message.500"/></p>
            </c:otherwise>
        </c:choose>
        <p class="lead">
            <a class="btn btn-dark btn-lg" href="${contextPath}/app/movies" role="button"><fmt:message key="error.submit"/></a>
        </p>
    </div>
</main>
