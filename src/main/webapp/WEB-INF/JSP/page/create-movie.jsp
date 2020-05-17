<%@ page import="epam.my.project.configuration.Constants" %><%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="defaultImageLink" value="<%=Constants.DEFAULT_IMAGE_LINK%>"/>


<main id="movie-page" class="container-fluid">
    <jsp:include page="../fragment/modal-window.jsp"/>
    <section class="media row justify-content-center my-4">
        <div class="col-3 mr-3">
            <img src="${defaultImageLink}" alt="unnamed">
            <div class="d-flex justify-content-center">
                <button type="button" class="btn btn-dark my-2" data-toggle="modal" data-target="#staticBackdrop">
                    Upload image
                </button>
            </div>
        </div>
        <div class="media-body col-8">
            <p>
            <form class="col-9" method="POST" action="/create-movie">
                <h5 class="d-flex justify-content-center">Create movie</h5>
                <div class="form-group">
                    <label for="inputName">Name</label>
                    <input type="text" value="name" class="form-control" id="inputName" placeholder="Enter a name">
                </div>
                <div class="form-group">
                    <label for="inputYear">Year</label>
                    <input type="text" name="year" class="form-control" id="inputYear" placeholder="Enter an year">
                    <small  class="form-text text-muted">
                        Movies year year must match type "yyyy".
                    </small>
                </div>
                <div class="form-group">
                    <label for="inputFees">Fees</label>
                    <input type="text" name="fees" class="form-control" id="inputFees" placeholder="Enter a fees">
                </div>
                <div class="form-group">
                    <label for="inputBudget">Budget</label>
                    <input type="text" name="budget" class="form-control" id="inputBudget" placeholder="Enter a budhet">
                </div>
                <div class="form-group">
                    <label for="inputDuration">Duration</label>
                    <input type="text" name="duration" class="form-control" id="inputDuration" placeholder="Enter an duration">
                    <small class="form-text text-muted">
                        Movies duration must match type "HH:mm:ss".
                    </small>
                </div>
                <div class="form-group">
                    <label for="selectGenre">Genre</label>
                    <select name="genre" class="custom-select" id="selectGenre">
                        <c:forEach var="genre" items="${GENRES}">
                            <option name="${genre.id}">${genre.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCategory">Category</label>
                    <select name="category" class="custom-select" id="selectCategory">
                        <c:forEach var="category" items="${CATEGORIES}">
                            <option name="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectFilmmaker">Filmmaker</label>
                    <select name="filmmaker" class="custom-select" id="selectFilmmaker">
                        <c:forEach var="filmmaker" items="${FILMMAKERS}">
                            <option name="${filmmaker.id}">${filmmaker.firstName} ${filmmaker.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectCountry">Country</label>
                    <select name="country" class="custom-select" id="selectCountry">
                        <c:forEach var="country" items="${COUNTRIES}">
                            <option name="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="textareaDescription">Description</label>
                    <textarea name="description" class="form-control" id="textareaDescription" rows="5"></textarea>
                </div>
                <input type="hidden" name="imageLink" value="${IMAGE_LINK ? IMAGE_LINK : defaultImageLink}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-dark px-4 mx-3">Save</button>
                    <a href="#" type="submit" class="btn btn-dark px-4 mx-3">Back</a>
                </div>
            </form>
            </p>
        </div>
    </section>
</main>
