<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags"   tagdir="/WEB-INF/tags"%>

<main id="home" class="container-fluid">
    <div class="row">
        <jsp:include page="../fragment/aside.jsp"/>
        <section class="col-lg-9 col-md-8 col-sm-12 pr-4">
            <tags:sort-mode/>
            <div id="films" data-page-count="${PAGE_COUNT}" data-page-number="1">
                <ul class="list-unstyled">
                   <jsp:include page="../fragment/movies-list.jsp"/>
                </ul>
            </div>
        </section>
    </div>
    <div id="load-more" class="d-flex justify-content-center">
        <button id="load-more-button" type="button" class="btn btn-dark">load more</button>
        <div id="load-more-spinner" class="spinner-border hidden" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
</main>
