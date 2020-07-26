<%@ page import="epam.my.project.configuration.SortMode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="rating" value="<%=SortMode.RATING.name().toLowerCase()%>"/>
<c:set var="fees" value="<%=SortMode.FEES.name().toLowerCase()%>"/>
<c:set var="budget" value="<%=SortMode.BUDGET.name().toLowerCase()%>"/>
<c:set var="duration" value="<%=SortMode.DURATION.name().toLowerCase()%>"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<main id="home" class="container">
    <div class="float-right my-4 mx-5"><tags:sort-mode showTopColumn="true"/></div>
    <section class="jumbotron mb-5 pb-5">
        <div>
            <h2 class="display-4">
                <fmt:message key="tag.sort-mode.top"/>
                <span>
                    <c:choose>
                        <c:when test="${SORT_MODE == rating}">
                            <fmt:message key="tag.sort-mode.rating"/>
                        </c:when>
                        <c:when test="${SORT_MODE == fees}">
                            <fmt:message key="tag.sort-mode.fees"/>
                        </c:when>
                        <c:when test="${SORT_MODE == budget}">
                            <fmt:message key="tag.sort-mode.budget"/>
                        </c:when>
                        <c:when test="${SORT_MODE == duration}">
                            <fmt:message key="tag.sort-mode.duration"/>
                        </c:when>
                    </c:choose>
                </span>
            </h2>
            <ul class="list-unstyled row">
                <c:forEach var="movie" items="${MOVIE_TOP_LIST}">
                    <li class="col my-2 mb-4">
                        <div class="card">
                            <img src="${movie.imageLink}" class="card-img-top" alt="${movie.name}">
                            <div class="card-body">
                                <h5 class="card-title">${movie.name}</h5>
                                <p class="card-text">
                                    <c:choose>
                                        <c:when test="${SORT_MODE == rating}">
                                            <span class="d-block">${movie.rating}</span>
                                        </c:when>
                                        <c:when test="${SORT_MODE == fees}">
                                            <span class="d-block"><fmt:formatNumber type = "number" value="${movie.fees}"/>$</span>
                                        </c:when>
                                        <c:when test="${SORT_MODE == budget}">
                                            <span class="d-block"><fmt:formatNumber type = "number" value="${movie.budget}"/>$</span>
                                        </c:when>
                                        <c:when test="${SORT_MODE == duration}">
                                            <span class="d-block">${movie.duration}</span>
                                        </c:when>
                                    </c:choose>
                                    <span>(${movie.year})</span>
                                    <span>${movie.country.name}</span>
                                    <span class="d-block">${movie.category.name}</span>
                                    <span>${movie.genre.name}</span></p>
                                <a href="${contextPath}/app/movie/${movie.uid}" class="btn btn-dark"><fmt:message key="top.submit"/></a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>
</main>

