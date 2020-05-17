<%@ page import="epam.my.project.configuration.SecurityConfiguration" %><%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="admin" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>


<main id="movie-page" class="container-fluid">
    <section class="media row justify-content-center my-4">
        <img src="${user.imageLink}" class="col-3 mr-3" alt="${user.account.name}">
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${user.account.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" >
                <div class="badge badge-pill badge-warning raiting-number">${user.rating}</div>
                    <c:if test="${CURRENT_ACCOUNT_DETAILS.id == user.account.id || CURRENT_ACCOUNT_DETAILS.role == admin}">
                        <div id="edit" class="ml-3">
                            <a href="" class="btn btn-light">edit</a>
                        </div>
                    </c:if>
            </div>
            <p>
                <div>Account created : <span><fmt:formatDate value="${user.created}" pattern="dd.mm.yyyy"/>18.03.2020</span></div>
                <div>Banned : <span>
                    <c:choose>
                        <c:when test="${user.banned}">
                            yes
                        </c:when>
                        <c:otherwise>
                            not
                        </c:otherwise>
                    </c:choose>
                </span></div>
                <div>Rating : <span>${user.rating}</span></div>
                <div>Comments : <span>${TOTAL_COMMENTS_COUNT}</span></div>
            </p>
        </div>
    </section>
    <section id="comments" class="row justify-content-center" data-page-count="${PAGE_COUNT}" data-page-number="1">
        <ul id="comments-user-list" class="list-unstyled col-10">
            <h4 class="d-flex justify-content-center">Comments</h4>
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
