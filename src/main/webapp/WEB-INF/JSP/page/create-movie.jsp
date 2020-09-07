<%@ page import="com.epam.mrating.configuration.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="defaultImageLink" value="<%=Constants.DEFAULT_IMAGE_LINK%>"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${defaultImageLink}" alt="unnamed">
            <div class="d-flex justify-content-center">
                <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                    <fmt:message key="movie.image.upload"/>
                </button>
            </div>
        </div>
        <div class="media-body col-8">
            <p>
            <form class="col-9" method="POST" action="${contextPath}/app/movie/create/save">
                <h5 class="d-flex justify-content-center"><fmt:message key="movie.create"/></h5>
                <tags:message/>
                <div class="form-group">
                    <label for="inputName"><fmt:message key="movie.name.label"/></label>
                    <input type="text" name="name" class="form-control" id="inputName" placeholder="Enter a name">
                    <tags:validation field="name" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputYear"><fmt:message key="movie.year.label"/></label>
                    <input type="text" name="year" class="form-control" id="inputYear" placeholder="Enter an year">
                    <small  class="form-text text-muted">
                        <fmt:message key="movie.year.description"/>
                    </small>
                    <tags:validation field="year" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputFees"><fmt:message key="movie.fees.label"/></label>
                    <input type="text" name="fees" class="form-control" id="inputFees" placeholder="Enter a fees">
                    <tags:validation field="fees" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputBudget"><fmt:message key="movie.budget.label"/></label>
                    <input type="text" name="budget" class="form-control" id="inputBudget" placeholder="Enter a budhet">
                    <tags:validation field="budget" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="inputDuration"><fmt:message key="movie.duration.label"/></label>
                    <input type="text" name="duration" class="form-control" id="inputDuration" placeholder="Enter an duration">
                    <small class="form-text text-muted">
                        <fmt:message key="movie.duration.description"/>
                    </small>
                    <tags:validation field="duration" violatons="${VIOLATIONS}"/>
                </div>
                <div class="form-group">
                    <label for="selectGenre"><fmt:message key="movie.genre.label"/></label>
                    <select name="genre" class="custom-select" id="selectGenre">
                        <c:forEach var="genre" items="${GENRES}">
                            <option value="${genre.id}">${genre.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCategory"><fmt:message key="movie.category.label"/></label>
                    <select name="category" class="custom-select" id="selectCategory">
                        <c:forEach var="category" items="${CATEGORIES}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectFilmmaker"><fmt:message key="movie.filmmaker.label"/></label>
                    <select name="filmmaker" class="custom-select" id="selectFilmmaker">
                        <c:forEach var="filmmaker" items="${FILMMAKERS}">
                            <option value="${filmmaker.id}">${filmmaker.firstName} ${filmmaker.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCountry"><fmt:message key="movie.country.label"/></label>
                    <select name="country" class="custom-select" id="selectCountry">
                        <c:forEach var="country" items="${COUNTRIES}">
                            <option value="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="textareaDescription"><fmt:message key="movie.description.label"/></label>
                    <textarea name="description" class="form-control" id="textareaDescription" rows="5"></textarea>
                    <tags:validation field="description" violatons="${VIOLATIONS}"/>
                </div>
                <input id="inputImageLink" type="hidden" name="imageLink" value="${defaultImageLink}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="movie.submit"/></button>
                    <a href="${contextPath}/app/movies" type="submit" class="btn btn-dark px-4 mx-3"><fmt:message key="movie.back"/></a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
