<%@ page import="epam.my.project.configuration.SecurityConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="main_page" value="/app/movies"/>
<c:set var="admin" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>
<c:set var="user" value="<%=SecurityConfiguration.ROLE_USER%>"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<header class="container-fluid">
    <c:if test="${CURRENT_URI == main_page}">
        <div class="row" id="header-row-image">
            <div class="col" id="header-main-image">
                <h1 class="animate__animated animate__fadeInLeft text-center text-white text-wrap badge badge-danger display-4"><fmt:message key="app.title"/></h1>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-between text-white" id="header-navbar">
        <div class="col-2 align-self-center d-none d-md-block" id="header-project-name">
            <a href="${contextPath}/app/movies" class="text-white"><fmt:message key="app.title"/></a>
        </div>
        <nav class="col-xl-5 col-lg-6 col-md-7 col-sm-12 align-self-center" id="header-account">
            <div class="d-inline-flex">
                <a href="${contextPath}/app/movies" class="text-white"><fmt:message key="header.movie.list"/></a>
            </div>
            <div class="d-inline-flex">
                <a href="${contextPath}/app/movies/top" class="text-white"><fmt:message key="header.movie.top"/></a>
            </div>
        <c:choose>
            <c:when test="${not empty CURRENT_ACCOUNT_DETAILS}">
                <c:if test="${CURRENT_ACCOUNT_DETAILS.role == admin}">
                    <div class="d-inline-flex">
                        <a href="${contextPath}/app/movie/new/create" class="text-white"><fmt:message key="movie.create"/></a>
                    </div>
                </c:if>
                <c:if test="${CURRENT_ACCOUNT_DETAILS.role == user}">
                    <div class="d-inline-flex">
                        <a href="${contextPath}/app/user/${CURRENT_ACCOUNT_DETAILS.uid}" class="text-white">${CURRENT_ACCOUNT_DETAILS.name}</a>
                    </div>
                </c:if>
                <div class="d-inline-flex">
                    <a href="${contextPath}/app/logout" class="text-white"><fmt:message key="app.logout"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="d-inline-flex">
                    <a href="${contextPath}/app/show/sign-in" class="btn btn-light "><fmt:message key="header.sign-in"/></a>
                </div>
            </c:otherwise>
        </c:choose>
        </nav>
    </div>
</header>
