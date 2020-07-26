<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<c:forEach var="m" items="${MOVIES}">
    <li class="media my-4">
        <img src="${m.imageLink}" class="mr-3" alt="${m.name}">
        <div class="media-body">
            <h5 class="d-inline mt-0 mb-1"><a href="${contextPath}/app/movie/${m.uid}">${m.name}</a></h5>
            <div class="d-inline-flex rating-field mt-3 mb-1" data-raiting-value="${m.rating}">
                <span class="fa fa-star mx-1" data-raiting="1"></span>
                <span class="fa fa-star mx-1" data-raiting="2"></span>
                <span class="fa fa-star mx-1" data-raiting="3"></span>
                <span class="fa fa-star mx-1" data-raiting="4"></span>
                <span class="fa fa-star mx-1" data-raiting="5"></span>
                <span class="badge badge-pill badge-dark raiting-number">${m.rating}</span>
            </div>
            <p class="text-muted ">
                <span>${m.year}</span>
                <span>${m.country.name}</span>
                <span class="d-block">${m.category.name}</span>
                <span>${m.genre.name}</span>
            </p>
            <p class="text-justify d-none d-lg-block">${m.description}</p>
        </div>
    </li>
</c:forEach>
