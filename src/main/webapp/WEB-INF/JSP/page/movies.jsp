<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="home" class="container-fluid">
    <div class="row">
        <jsp:include page="../fragment/aside.jsp"/>
        <section class="col-lg-9 col-md-8 col-sm-12 pr-4">
            <tags:sort-mode/>
            <div class="alert alert-light" role="alert">
                <fmt:message key="movies.found.message.before"/> ${TOTAL_MOVIES_COUNT} <fmt:message key="movies.found.message.after"/>
            </div>
            <div id="films" data-page-count="${PAGE_COUNT}" data-page-number="1">
                <ul class="list-unstyled">
                   <jsp:include page="../fragment/movies-list.jsp"/>
                </ul>
            </div>
        </section>
    </div>
    <tags:pageable/>
</main>
