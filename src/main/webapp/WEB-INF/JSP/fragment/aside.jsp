<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 15.05.2020
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="col-lg-3 col-md-4 col-sm-12">
    <div id="search">
        <form id="form-search" class="form-inline" action="/search">
            <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search" name="query">
            <button class="btn btn-dark" type="submit">Search</button>
        </form>
    </div>
    <div id="main-filter">
        <ul class="list-group list-group-flush">
            <li class="list-group-item head">Search Filter</li>
            <li class="list-group-item items">
                <button class="collapsible">Category</button>
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
                <button class="collapsible">Genre</button>
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
                <button class="collapsible">Country</button>
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
                <div>Genres</div>
            </li>
            <li class="nav-item">
                <div>
                    <a class="nav-link active disabled" href="/movies">All
                        <span class="badge badge-dark badge-pill">153</span>
                        <i class="fa fa-circle"></i>
                    </a>
                </div>
            </li>
            <c:forEach var="genre" items="${GENRES}">
                <li class="nav-item">
                    <div>
                        <a class="nav-link" href="/movies${genre.name}">${genre.name}
                            <span class="badge badge-secondary badge-pill">${genre.moviesCount}</span>
                            <i class="fa fa-circle invisible"></i>
                        </a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</aside>

