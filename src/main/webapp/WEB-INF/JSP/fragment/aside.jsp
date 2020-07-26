<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<aside class="col-lg-3 col-md-4 col-sm-12">
    <div id="search">
        <form id="form-search" class="form-inline" action="${contextPath}/app/movies/search">
            <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search" name="query">
            <button class="btn btn-dark" type="submit"><fmt:message key="aside.search"/></button>
        </form>
    </div>
    <div id="main-filter">
        <ul class="list-group list-group-flush">
            <li class="list-group-item head"><fmt:message key="aside.search.filter"/></li>
            <li class="list-group-item items">
                <button class="collapsible"><fmt:message key="aside.category"/></button>
                <div class="content">
                    <c:forEach var="category" items="${CATEGORIES}">
                        <label class="container">${category.name}
                            <input type="checkbox" name="category" value="${category.id}" form="form-search">
                            <span class="checkmark"></span>
                        </label>
                    </c:forEach>
                </div>
            </li>
            <li class="list-group-item items">
                <button class="collapsible"><fmt:message key="aside.genre"/></button>
                <div class="content">
                    <c:forEach var="genre" items="${GENRES}">
                        <label class="container">${genre.name}
                            <input type="checkbox" name="genre" value="${genre.id}" form="form-search">
                            <span class="checkmark"></span>
                        </label>
                    </c:forEach>
                </div>
            </li>
            <li class="list-group-item items">
                <button class="collapsible"><fmt:message key="aside.country"/></button>
                <div class="content">
                    <c:forEach var="country" items="${COUNTRIES}">
                        <label class="container">${country.name}
                            <input type="checkbox" name="country" value="${country.id}" form="form-search">
                            <span class="checkmark"></span>
                        </label>
                    </c:forEach>
                </div>
            </li>
        </ul>
    </div>
    <div id="main-genre">
        <ul class="nav flex-column">
            <li class="nav-item head">
                <div><fmt:message key="aside.genres"/></div>
            </li>
            <li class="nav-item">
                <div>
                    <a class="nav-link ${ GENRE == null ? "active disabled" : ""}" href="${contextPath}/app/movies"><fmt:message key="aside.all"/>
                        <span class="badge ${ GENRE == null ? "badge-dark" : "badge-secondary"} badge-pill">${ALL_MOVIES_COUNT}</span>
                        <i class="fa fa-circle ${ GENRE == null ? "" : "invisible"}"></i>
                    </a>
                </div>
            </li>
            <c:forEach var="genre" items="${GENRES}">
                <li class="nav-item">
                    <div>
                        <a class="nav-link ${ GENRE == genre.name ? "active disabled" : ""}" href="${contextPath}/app/movies/genres/${genre.name}">${genre.name}
                            <span class="badge ${ GENRE == genre.name ? "badge-dark" : "badge-secondary"} badge-pill">${genre.moviesCount}</span>
                            <i class="fa fa-circle ${ GENRE == genre.name ? "" : "invisible"}"></i>
                        </a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</aside>

