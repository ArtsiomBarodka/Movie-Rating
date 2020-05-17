<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${movie.imageLink}" alt="${movie.name}">
            <div class="d-flex justify-content-center">
                <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                    Change image
                </button>
            </div>
        </div>
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
            </div>
            <p>
            <form class="col-9" method="POST">
                <h5 class="d-flex justify-content-center">Edit movie</h5>
                <div class="form-group">
                    <label for="inputName">Name</label>
                    <input type="text" name="name" value="${movie.name}" class="form-control" id="inputName" placeholder="Enter a name">
                </div>
                <div class="form-group">
                    <label for="inputYear">Year</label>
                    <input type="text" name="year" value="<fmt:formatDate value="${movie.year}" pattern="yyyy"/>" class="form-control" id="inputYear" placeholder="Enter an year">
                    <small class="form-text text-muted">
                        Movies year year must match type "yyyy".
                    </small>
                </div>
                <div class="form-group">
                    <label for="inputFees">Fees</label>
                    <input type="text" name="fees" value="<fmt:formatNumber type = "number" value="${movie.fees}"/>" class="form-control" id="inputFees" placeholder="Enter a fees">
                </div>
                <div class="form-group">
                    <label for="inputBudget">Budget</label>
                    <input type="text" name="budget" value="<fmt:formatNumber type = "number" value="${movie.budget}"/>" class="form-control" id="inputBudget" placeholder="Enter a budhet">
                </div>
                <div class="form-group">
                    <label for="inputDuration">Duration</label>
                    <input type="text" name="duration" value="<fmt:formatDate pattern="HH:mm:ss" value="${movie.duration}"/>" class="form-control" id="inputDuration" placeholder="Enter an duration">
                    <small class="form-text text-muted">
                        Movies duration must match type "HH:mm:ss".
                    </small>
                </div>
                <div class="form-group">
                    <label for="selectGenre">Genre</label>
                    <select name="genre" class="custom-select" id="selectGenre">
                        <c:forEach var="genre" items="${GENRES}">
                            <option name="${genre.id}" <c:if test="${genre.id == movie.genre.id}">selected</c:if>>${genre.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCategory">Category</label>
                    <select name="category" class="custom-select" id="selectCategory">
                        <c:forEach var="category" items="${CATEGORIES}">
                            <option name="${category.id}" <c:if test="${category.id == movie.category.id}">selected</c:if>>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectFilmmaker">Filmmaker</label>
                    <select name="filmmaker" class="custom-select" id="selectFilmmaker">
                        <c:forEach var="filmmaker" items="${FILMMAKERS}">
                            <option name="${filmmaker.id}" <c:if test="${filmmaker.id == movie.filmmaker.id}">selected</c:if>>${filmmaker.firstName} ${filmmaker.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCountry">Country</label>
                    <select name="country" class="custom-select" id="selectCountry">
                        <c:forEach var="country" items="${COUNTRIES}">
                            <option name="${country.id}" <c:if test="${country.id == movie.country.id}">selected</c:if>>${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="textareaDescription">Description</label>
                    <textarea name="description" class="form-control" id="textareaDescription" rows="5">movie.description</textarea>
                </div>
                <input type="hidden" name="imageLink" value="${IMAGE_LINK ? IMAGE_LINK : movie.imageLink}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3">Save</button>
                    <a href="#" type="submit" class="btn btn-dark px-4 mx-3">Back</a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
