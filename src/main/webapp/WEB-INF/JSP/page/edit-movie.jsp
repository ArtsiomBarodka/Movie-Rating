<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${MOVIE.imageLink}" alt="${MOVIE.name}">
            <div class="d-flex justify-content-center">
                <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                    <fmt:message key="movie.image.change"/>
                </button>
            </div>
        </div>
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${MOVIE.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" data-raiting-value="${MOVIE.rating}">
                <span class="fa fa-star mx-1" data-raiting="1"></span>
                <span class="fa fa-star mx-1" data-raiting="2"></span>
                <span class="fa fa-star mx-1" data-raiting="3"></span>
                <span class="fa fa-star mx-1" data-raiting="4"></span>
                <span class="fa fa-star mx-1" data-raiting="5"></span>
                <div class="badge badge-pill badge-dark raiting-number">
                    <fmt:formatNumber maxIntegerDigits="1" maxFractionDigits="3" value="${MOVIE.rating}"/> (${TOTAL_COMMENTS_COUNT})
                </div>
            </div>
            <p>
            <form class="col-9" method="POST" action="/app/movie/edit/save/${MOVIE.uid}">
                <h5 class="d-flex justify-content-center"><fmt:message key="movie.edit.title"/></h5>
                <tags:message/>
                <div class="form-group">
                    <label for="inputName"><fmt:message key="movie.name.label"/></label>
                    <input type="text" name="name" value="${MOVIE.name}" class="form-control" id="inputName" placeholder="Enter a name">
                    <tags:validation field="name" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputYear"><fmt:message key="movie.year.label"/></label>
                    <input type="text" name="year" value="${MOVIE.year}" class="form-control" id="inputYear" placeholder="Enter an year">
                    <small class="form-text text-muted">
                        <fmt:message key="movie.year.description"/>
                    </small>
                    <tags:validation field="year" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputFees"><fmt:message key="movie.fees.label"/></label>
                    <input type="text" name="fees" value="${MOVIE.fees}" class="form-control" id="inputFees" placeholder="Enter a fees">
                    <tags:validation field="fees" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputBudget"><fmt:message key="movie.budget.label"/></label>
                    <input type="text" name="budget" value="${MOVIE.budget}" class="form-control" id="inputBudget" placeholder="Enter a budhet">
                    <tags:validation field="budget" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputDuration"><fmt:message key="movie.duration.label"/></label>
                    <input type="text" name="duration" value="${MOVIE.duration}" class="form-control" id="inputDuration" placeholder="Enter an duration">
                    <small class="form-text text-muted">
                        <fmt:message key="movie.duration.description"/>
                    </small>
                    <tags:validation field="duration" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="selectGenre"><fmt:message key="movie.genre.label"/></label>
                    <select name="genre" class="custom-select" id="selectGenre">
                        <c:forEach var="genre" items="${GENRES}">
                            <option value="${genre.id}" <c:if test="${genre.id == MOVIE.genre.id}">selected</c:if>>${genre.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCategory"><fmt:message key="movie.category.label"/></label>
                    <select name="category" class="custom-select" id="selectCategory">
                        <c:forEach var="category" items="${CATEGORIES}">
                            <option value="${category.id}" <c:if test="${category.id == MOVIE.category.id}">selected</c:if>>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectFilmmaker"><fmt:message key="movie.filmmaker.label"/></label>
                    <select name="filmmaker" class="custom-select" id="selectFilmmaker">
                        <c:forEach var="filmmaker" items="${FILMMAKERS}">
                            <option value="${filmmaker.id}" <c:if test="${filmmaker.id == MOVIE.filmmaker.id}">selected</c:if>>${filmmaker.firstName} ${filmmaker.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCountry"><fmt:message key="movie.country.label"/></label>
                    <select name="country" class="custom-select" id="selectCountry">
                        <c:forEach var="country" items="${COUNTRIES}">
                            <option value="${country.id}" <c:if test="${country.id == MOVIE.country.id}">selected</c:if>>${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="textareaDescription"><fmt:message key="movie.description.label"/></label>
                    <textarea name="description" class="form-control" id="textareaDescription" rows="5">${MOVIE.description}</textarea>
                    <tags:validation field="description" violatons="${VIOLATIONS}"/>
                </div>
                <input type="hidden" name="id" value="${MOVIE.id}">
                <input id="inputImageLink" type="hidden" name="imageLink" value="${MOVIE.imageLink}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="movie.submit"/></button>
                    <a href="/app/movie/${MOVIE.uid}" type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="movie.back"/></a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
