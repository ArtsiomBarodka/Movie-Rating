<%@ page import="com.epam.mrating.configuration.SecurityConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="admin" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="movie-page" class="container-fluid">
    <section class="media row justify-content-center my-4">
        <img src="${MOVIE.imageLink}" class="col-3 mr-3" alt="${MOVIE.name}">
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
                <c:if test="${CURRENT_ACCOUNT_DETAILS.role == admin}">
                    <div id="edit" class="ml-3">
                        <a href="${contextPath}/app/movie/edit/${MOVIE.uid}" class="btn btn-light"><fmt:message key="movie.edit.button"/></a>
                        <a href="${contextPath}/app/movie/delete/${MOVIE.uid}" class="btn btn-light"><fmt:message key="movie.delete.button"/></a>
                    </div>
                </c:if>
            </div>
            <p>
                <div><fmt:message key="movie.year.label"/> : <span>${MOVIE.year}</span></div>
                <div><fmt:message key="movie.country.label"/> : <span>${MOVIE.country.name}</span></div>
                <div><fmt:message key="movie.filmmaker.label"/> : <span>${MOVIE.filmmaker.firstName} ${MOVIE.filmmaker.lastName}</span></div>
                <div><fmt:message key="movie.category.label"/> : <span>${MOVIE.category.name}</span></div>
                <div><fmt:message key="movie.genre.label"/> : <span>${MOVIE.genre.name}</span></div>
                <div><fmt:message key="movie.budget.label"/> : <span><fmt:formatNumber type = "number" value="${MOVIE.budget}"/>$</span></div>
                <div><fmt:message key="movie.fees.label"/> : <span><fmt:formatNumber type = "number" value="${MOVIE.fees}"/>$</span></div>
                <div><fmt:message key="movie.duration.label"/> : <span>${MOVIE.duration}</span></div>
                <div><fmt:message key="movie.added.label"/> : <span><fmt:formatDate pattern="dd.MM.yyyy" value="${MOVIE.added}"/></span></div>
                <div><fmt:message key="movie.rating.label"/> : <span><fmt:formatNumber maxIntegerDigits="1" maxFractionDigits="3" value="${MOVIE.rating}"/></span></div>
                <div><fmt:message key="movie.votes.label"/> : <span>${TOTAL_COMMENTS_COUNT}</span></div>
            </p>
            <p class="text-justify d-block">
                <div><fmt:message key="movie.description.label"/> :</div> ${MOVIE.description}
            </p>
        </div>
    </section>
    <section id="comments" class="row justify-content-center" data-page-count="${PAGE_COUNT}" data-page-number="1">
        <ul id="comments-movie-list" class="list-unstyled col-10">
            <h4 class="d-flex justify-content-center"><fmt:message key="comment.title"/></h4>
            <div class="alert alert-light" role="alert">
                <fmt:message key="comments.found.message.before"/> ${TOTAL_COMMENTS_COUNT} <fmt:message key="comments.found.message.after"/>
            </div>
            <c:if test="${USER != null && !USER.banned && !ALREADY_EXIST_COMMENT}">
                    <form id="comment-form" class="media" method="POST" action="${contextPath}/app/comment/add/movie/${MOVIE.uid}">
                        <img src="${USER.imageLink}" class="align-self-start mr-3" alt="${USER.account.name}">
                        <div class="media-body">
                        <input type="hidden" name="userId" value="${USER.id}">
                        <input type="hidden" name="movieId" value="${MOVIE.id}">
                        <div class="form-group">
                            <label for="select-movie-rating"><fmt:message key="comment.form.title"/></label>
                            <select class="form-control" id="select-movie-rating" name="rating">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3" selected>3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="comment-textarea"><fmt:message key="comment.form.content.label"/></label>
                            <textarea class="form-control" id="comment-textarea" rows="3" name="content"></textarea>
                            <tags:validation field="content" violatons="${VIOLATIONS}"/>
                        </div>
                        <div class="d-flex justify-content-center mb-3">
                            <button type="submit" class="btn btn-dark"><fmt:message key="comment.form.submit"/></button>
                        </div>
                        </div>
                    </form>
            </c:if>
            <jsp:include page="../fragment/comments-list.jsp"/>
        </ul>
    </section>
    <tags:pageable/>
</main>