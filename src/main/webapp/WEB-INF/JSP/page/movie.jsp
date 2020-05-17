<%@ page import="epam.my.project.configuration.SecurityConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="admin" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>

<main id="movie-page" class="container-fluid">
    <section class="media row justify-content-center my-4">
        <img src="${movie.imageLink}" class="col-3 mr-3" alt="${movie.name}">
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${movie.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" data-raiting-value="${movie.rating}">
                <span class="fa fa-star mx-1" data-raiting="1"></span>
                <span class="fa fa-star mx-1" data-raiting="2"></span>
                <span class="fa fa-star mx-1" data-raiting="3"></span>
                <span class="fa fa-star mx-1" data-raiting="4"></span>
                <span class="fa fa-star mx-1" data-raiting="5"></span>
                <div class="badge badge-pill badge-dark raiting-number">
                    <fmt:formatNumber maxIntegerDigits="1" maxFractionDigits="3" value="${movie.rating}"/> (${TOTAL_COMMENTS_COUNT})
                </div>
                <c:if test="${CURRENT_ACCOUNT_DETAILS.role == admin}">
                    <div id="edit" class="ml-3">
                        <a href="" class="btn btn-light">edit</a>
                    </div>
                </c:if>
            </div>
            <p>
                <div>Year : <span><fmt:formatDate value="${movie.year}" pattern="yyyy"/></span></div>
                <div>Country : <span>${movie.country.name}</span></div>
                <div>Filmmaker : <span>${movie.filmmaker.firstName} ${movie.filmmaker.lastName}</span></div>
                <div>Category : <span>${movie.category.firstName}</span></div>
                <div>Genre : <span>${movie.genre.firstName}</span></div>
                <div>Budget : <span><fmt:formatNumber type = "number" value="${movie.budget}"/>$</span></div>
                <div>Fees : <span><fmt:formatNumber type = "number" value="${movie.fees}"/>$</span></div>
                <div>Duration : <span><fmt:formatDate pattern="HH:mm:ss" value="${movie.duration}"/></span></div>
                <div>Added : <span><fmt:formatDate pattern="dd.MM.yyyy" value="${movie.added}"/>/span></div>
                <div>Rating : <span><fmt:formatNumber maxIntegerDigits="1" maxFractionDigits="3" value="${movie.rating}"/></span></div>
                <div>Votes : <span>${TOTAL_COMMENTS_COUNT}</span></div>
            </p>
            <p class="text-justify d-block">
                <div>Description :</div> ${movie.description}
            </p>
        </div>
    </section>
    <section id="comments" class="row justify-content-center">
        <ul id="comments-movie-list" class="list-unstyled col-10">
            <h4 class="d-flex justify-content-center">Comments</h4>
            <c:if test="${!USER.banned}">
                <form action="add-comment" method="POST">
                    <input type="hidden" name="userId" value="${USER.id}">
                    <input type="hidden" name="movieId" value="${movie.id}">
                    <div class="form-group">
                        <label for="select-movie-rating">Select movie rating</label>
                        <select class="form-control" id="select-movie-rating" name="rating">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3" selected>3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="comment-textarea">Write your comment</label>
                        <textarea class="form-control" id="comment-textarea" rows="3" name="content"></textarea>
                    </div>
                    <div class="d-flex justify-content-center mb-3">
                        <button type="submit" class="btn btn-dark">Send</button>
                    </div>
                </form>
            </c:if>
            <jsp:include page="../fragment/comments-list.jsp"/>
        </ul>
    </section>
    <div id="load-more" class="d-flex justify-content-center">
        <button id="load-more-button" type="button" class="btn btn-dark">load more</button>
        <div id="load-more-spinner" class="spinner-border hidden" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
</main>